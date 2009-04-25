package dao;
import beans.BallonBean;
import beans.ContestBean;
import db.MySQLDB;
import java.sql.*;
import java.util.LinkedList;

/**
 *
 * @author liheyuan
 */
public class BallonDAO
{
    /***
     * 根据比赛ID向其中更新气球
     */
    public static void addBallon(ContestBean contest) throws Exception
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            conn = MySQLDB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select R_ID,P_ID,U_ID from run where exists(select P_ID from con_problem where C_ID="+contest.getCID()+" " +
                    "and con_problem.P_ID=run.P_ID) and R_RESULT='AC' and R_SUB_TIME between '"+contest.getCStart()+"' and '"+contest.getCEnd()+"'");
            
            //对每个找到的Run进行查询
            pstmt = conn.prepareStatement("insert into ballon values(NULL,?,?,?,?)");
            pstmt2 = conn.prepareStatement("select R_ID from run where exists(select U_ID,P_ID,C_ID from ballon where C_ID="+contest.getCID()+"" +
                    " and P_ID=? and U_ID=?)");
            while(rs.next())
            {
                //先检查是否已经AC过了
                pstmt2.setInt(1, rs.getInt("P_ID"));
                pstmt2.setInt(2, rs.getInt("U_ID"));
                ResultSet rs2 = pstmt2.executeQuery();
                boolean  flag= rs2.next();
                rs2.close();

                //不存在气球才添加
                if(!flag)
                {
                    pstmt.setInt(1, contest.getCID());
                    pstmt.setInt(2, rs.getInt("U_ID"));
                    pstmt.setInt(3, rs.getInt("P_ID"));
                    pstmt.setString(4, "No");
                    pstmt.executeUpdate();
                }
            }
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
                MySQLDB.close(rs);
                MySQLDB.close(stmt);
                MySQLDB.close(pstmt);
                MySQLDB.close(pstmt2);
                MySQLDB.close(conn);
        }
    }

    public static LinkedList<BallonBean> queryBallon(ContestBean contest) throws Exception
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            conn = MySQLDB.getConn();
            //对每个找到的Run进行查询
            pstmt = conn.prepareStatement("select ballon.B_ID,ballon.C_ID,ballon.U_ID,ballon.P_ID,send,SYMBOL,user.U_USER from ballon,user,con_problem where ballon.c_id=? and send='No' and user.U_ID=ballon.U_ID and con_problem.C_ID=ballon.C_ID and con_problem.P_ID=ballon.P_ID");
            pstmt.setInt(1, contest.getCID());
            rs = pstmt.executeQuery();
            LinkedList<BallonBean> list = new LinkedList();
            while(rs.next())
            {
                BallonBean bean = new BallonBean();
                bean.setCID(rs.getInt("C_ID"));
                bean.setPID(rs.getInt("P_ID"));
                bean.setUser(rs.getString("U_USER"));
                bean.setUID(rs.getInt("U_ID"));
                bean.setSend(rs.getString("Send").equals("Yes"));
                bean.setSymbol(rs.getString("SYMBOL"));
                bean.setBID(rs.getInt("B_ID"));
                list.add(bean);
            }
            return list;
        }
        catch(Exception e)
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

    public static void markSend(int bid) throws Exception
    {
        Connection conn = null;
        Statement stmt = null;
        try
        {
            conn = MySQLDB.getConn();
            stmt = conn.createStatement();
            stmt.executeUpdate("update ballon set send='Yes' where b_id="+bid);
        }
        catch(Exception e)
        {
            throw new Exception("标记气球发送出错："+e.getMessage(),e);
        }
        finally
        {
            MySQLDB.close(stmt);
            MySQLDB.close(conn);
        }
    }
}
