package dao;

import beans.ContestBean;
import beans.RunBean;
import db.MySQLDB;
import java.util.LinkedList;
import java.sql.*;
import tool.Tools;

/**
 *
 * @author liheyuan
 */
public class RunDAO
{

    /***
     * Query not include Src
     * @param pStart
     * @param pEnd
     * @return
     * @throws java.lang.Exception
     */
    public static LinkedList<RunBean> queryBasicByCID(ContestBean contest) throws Exception
    {
        //Query All Problem for Contest cid
        Connection conn = MySQLDB.getConn();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select R_ID,P_ID,U_ID,R_RESULT,R_SUB_TIME from run where exists (select * from con_problem where c_id=" + contest.getCID() + " and con_problem.p_id=run.p_id) and " +
                    "R_SUB_TIME BETWEEN '" + contest.getCStart() + "' and '" + contest.getCEnd() + "' order by R_ID");
        try
        {
           LinkedList<RunBean> list = new LinkedList();
            while (rs.next())
            {
                RunBean run = new RunBean();
                run.setRID(rs.getInt("R_ID"));
                run.setPID(rs.getInt("P_ID"));
                run.setUID(rs.getInt("U_ID"));
                run.setRResult(rs.getString("R_RESULT"));
                run.setRSubtime(rs.getString("R_SUB_TIME"));
                list.add(run);
            }

            return list;
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            //Clear all resource
            MySQLDB.close(rs);
            MySQLDB.close(stmt);
            MySQLDB.close(conn);
        }
    //Return the RunBeans
    }

    /***
     * Query not include Src
     * @param pStart
     * @param pEnd
     * @return
     * @throws java.lang.Exception
     */
    public static LinkedList<RunBean> queryBasicByRunIDRange(int pStart, int cnt) throws Exception
    {
        
        //Query All Problem for Contest cid
        Connection conn = MySQLDB.getConn();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select R_ID,U_USER,P_ID,run.U_ID,R_RESULT,R_TIME,R_MEM,R_LANG,R_SUB_TIME from run,user where" +
                    "  run.U_ID = user.U_ID order by R_ID desc limit " + pStart + "," + cnt + " ");
        try
        {
            LinkedList list = toBasicBeans(rs);
            //Return the RunBeans
            return list;
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            MySQLDB.close(rs);
            MySQLDB.close(stmt);
            MySQLDB.close(conn);
        }        
    }

    /***
     * Insert an new Run to DB
     * @param pid
     * @param uid
     * @param src
     */
    public static void addNewRun(int pid, int uid, String lang, String src) throws Exception
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = MySQLDB.getConn();
            pstmt = conn.prepareStatement("insert into run values(NULL,?,?,'WAIT',?,NULL,NULL,NULL,NULL,?,?)");
            pstmt.setInt(1, pid);
            pstmt.setInt(2, uid);
            pstmt.setString(3, src);
            pstmt.setString(4, lang);
            pstmt.setString(5, Tools.getTime());
            pstmt.executeUpdate();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            MySQLDB.close(pstmt);
            MySQLDB.close(conn);
        }
    }

    /***
     * Not including SRC
     * @param rs
     * @return
     * @throws java.lang.Exception
     */
    public static LinkedList<RunBean> toBasicBeans(ResultSet rs) throws Exception
    {
        LinkedList list = new LinkedList();
        while (rs.next())
        {
            //Create an Run Info Bean through result set
            RunBean bean = new RunBean();
            bean.setPID(rs.getInt("P_ID"));
            bean.setRID(rs.getInt("R_ID"));
            bean.setUID(rs.getInt("U_ID"));
            bean.setUser(rs.getString("U_USER"));
            bean.setRLang(rs.getString("R_LANG"));
            bean.setRMemory(rs.getLong("R_MEM"));
            bean.setRTime(rs.getLong("R_TIME"));
            bean.setRResult(rs.getString("R_RESULT"));
            bean.setRSubtime(rs.getString("R_SUB_TIME"));
            //Add to List
            list.add(bean);
        }
        return list;
    }

    /***
     * 查询编译错误
     * @param rid
     * @return
     */
    public static String getCompileErr(int rid) throws Exception
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            conn = MySQLDB.getConn();
            pstmt = conn.prepareStatement("select R_COMP from run where R_ID=?");
            pstmt.setInt(1, rid);
            rs = pstmt.executeQuery();
            rs.next();
            String res = rs.getString("R_COMP");
            return res;
        } catch (Exception e)
        {
            throw e;
        }
        finally
        {
            MySQLDB.close(rs);
            MySQLDB.close(pstmt);
            MySQLDB.close(conn);
        }
    }
}
