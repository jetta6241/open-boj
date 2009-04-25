package dao;

import beans.ContestBean;
import db.MySQLDB;
import java.util.LinkedList;
import java.sql.*;
import tool.Tools;

/**
 *
 * @author liheyuan
 */
public class ContestDAO
{

    /***
     * 更新比赛的状态
     * @param condition
     * @throws java.lang.Exception
     */
    public static void updateContestState(String condition) throws Exception
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            //Query All Contest
            conn = MySQLDB.getConn();
            stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            //根据条件查询所有要更新状态的比赛
            rs = stmt.executeQuery("select * from contest where C_STATE='" + condition + "'");
            String s, e;
            long start, now, end;
            //更新状态
            while (rs.next())
            {
                //转化开始和结束时间为Long
                s = rs.getString("C_START");
                e = rs.getString("C_END");
                start = Tools.dateToLong(s);
                end = Tools.dateToLong(e);
                now = Tools.getTimeLong();
                //更新比赛的状态
                if (now < start && !condition.equals("Pending"))
                {
                    rs.updateString("C_STATE", "Pending");
                } else if (now > start && now < end && !condition.equals("Running"))
                {
                    rs.updateString("C_STATE", "Running");
                    //第一次变为开始比赛，更新所有的对应题目状态
                    Statement stmt2 = conn.createStatement();
                    stmt2.executeUpdate("update problem set P_STATE = 'UNLOCK' where P_ID in (select P_ID from con_problem where C_ID=" + rs.getInt("C_ID") + ")");
                    stmt2.close();
                } else if (now > end && !condition.equals("Ended"))
                {
                    rs.updateString("C_STATE", "Ended");
                }
                rs.updateRow();
            }
        } catch (Exception e)
        {
            throw e;
        }
        finally
        {
            //Clear all resource
            rs.close();
            stmt.close();
            conn.close();
        }
    }

    public static LinkedList<ContestBean> queryRunContest() throws Exception
    {
        Connection conn = null;
        Statement stmt = null;
        try
        {
            //Query All Contest
            conn = MySQLDB.getConn();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from contest where c_state='Running'");
            LinkedList list = toContestBeans(rs);

            //Return the List<ContestBean>
            return list;
        } catch (Exception e)
        {
            throw e;
        } finally
        {
            //Clear all resource
            stmt.close();
            conn.close();
        }

    }

    public static LinkedList<ContestBean> queryAllContest() throws Exception
    {
        Connection conn = null;
        Statement stmt = null;
        try
        {
            //Query All Contest
            conn = MySQLDB.getConn();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from contest order by c_id desc");
            LinkedList list = toContestBeans(rs);

            //Return the List<ContestBean>
            return list;
        } catch (Exception e)
        {
            throw e;
        } finally
        {
            //Clear all resource
            stmt.close();
            conn.close();
        }
    }

    public static ContestBean queryContestByCID(int cid) throws Exception
    {

        //Query All Contest
        Connection conn = null;
        Statement stmt = null;
        try
        {
            conn = MySQLDB.getConn();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from contest where C_ID=" + cid);
            LinkedList<ContestBean> list = toContestBeans(rs);

            if (list.size() == 0)
            {
                throw new Exception("No Such an Contest");
            }
            //Return the ContestBean
            return list.removeFirst();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            //Clear all resource
            stmt.close();
            conn.close();
        }
    }

    public static LinkedList<ContestBean> toContestBeans(ResultSet rs) throws Exception
    {
        LinkedList list = new LinkedList();
        while (rs.next())
        {
            //Create an Contest Info Bean through result set
            ContestBean bean = new ContestBean();
            bean.setCID(rs.getInt("C_ID"));
            bean.setCTitle(rs.getString("C_TITLE"));
            bean.setCAuthor(rs.getString("C_AUTHOR"));
            bean.setCStart(rs.getString("C_START"));
            bean.setCEnd(rs.getString("C_END"));
            bean.setCStatus(rs.getString("C_STATE"));
            //Add to List
            list.add(bean);
        }

        return list;
    }

    /***
     * 添加一个比赛
     * @param bean
     * @throws java.lang.Exception
     */
    public static void addContest(ContestBean bean) throws Exception
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = MySQLDB.getConn();
            pstmt = conn.prepareStatement("insert into contest(C_ID,C_TITLE,C_AUTHOR,C_START,C_END,C_STATE) values (NULL,?,?,?,?,?)");
            pstmt.setString(1, bean.getCTitle());
            pstmt.setString(2, bean.getCAuthor());
            pstmt.setString(3, bean.getCStart());
            pstmt.setString(4, bean.getCEnd());
            pstmt.setString(5, bean.getCStatus());
            pstmt.executeUpdate();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            pstmt.close();
            conn.close();
        }
    }

    /***
     * 更新一个比赛
     * @param bean
     * @throws java.lang.Exception
     */
    public static void editContest(ContestBean bean) throws Exception
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = MySQLDB.getConn();
            pstmt = conn.prepareStatement("update contest set C_TITLE=?,C_AUTHOR=?,C_START=?,C_END=?,C_STATE=? where C_ID=?");
            pstmt.setString(1, bean.getCTitle());
            pstmt.setString(2, bean.getCAuthor());
            pstmt.setString(3, bean.getCStart());
            pstmt.setString(4, bean.getCEnd());
            pstmt.setString(5, bean.getCStatus());
            pstmt.setInt(6, bean.getCID());
            pstmt.executeUpdate();

        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            pstmt.close();
            conn.close();
        }
    }

    /***
     * 向比赛添加一个题目
     * @throws java.lang.Exception
     */
    public static void addProblem(int cid, int pid, String symbol) throws Exception
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = MySQLDB.getConn();
            pstmt = conn.prepareStatement("insert into con_problem values (?,?,?)");
            pstmt.setInt(1, cid);
            pstmt.setInt(2, pid);
            pstmt.setString(3, symbol);
            pstmt.executeUpdate();
            pstmt.close();
            pstmt = conn.prepareStatement("update problem set P_STATE='LOCK' where p_id=?");
            pstmt.setInt(1, pid);
            pstmt.executeUpdate();
        }
        catch(Exception e)
        {
            throw e;
        }
        finally
        {
            pstmt.close();
            conn.close();
        }
    }
}
