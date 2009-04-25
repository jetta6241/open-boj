package dao;

import beans.ProblemBean;
import db.MySQLDB;
import java.util.LinkedList;
import java.sql.*;

/**
 *
 * @author liheyuan
 */
public class ProblemDAO
{

    public static LinkedList<ProblemBean> queryBasicAll() throws Exception
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            //Query All Problem for Contest cid
            conn = MySQLDB.getConn();
            stmt = conn.createStatement();
            //查询所有的题目
            rs = stmt.executeQuery("select problem.P_ID,problem.P_TITLE from problem order by PROBLEM.P_ID");
            //封装成链表
            LinkedList list = new LinkedList();
            while (rs.next())
            {
                //Create an Contest Info Bean through result set
                ProblemBean bean = new ProblemBean();
                bean.setPID(rs.getInt("P_ID"));
                bean.setPTitle(rs.getString("P_TITLE"));
                //Add to List
                list.add(bean);
            }
            //Return the List<ContestBean>
            return list;
        } catch (Exception e)
        {
            throw e;
        } finally
        {
            rs.close();
            stmt.close();
            conn.close();
        }

    }

    public static LinkedList<ProblemBean> queryBasicByCIDAdmin(int cid) throws Exception
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            //Query All Problem for Contest cid
            conn = MySQLDB.getConn();
            stmt = conn.createStatement();
            //查询所有的题目
            rs = stmt.executeQuery("select problem.P_ID,problem.P_TITLE,problem.P_TLE,con_problem.symbol from problem,con_problem where C_ID=" + cid + " and con_problem.P_ID=problem.P_ID order by PROBLEM.P_ID");
            //封装成链表
            LinkedList list = new LinkedList();
            while (rs.next())
            {
                //Create an Contest Info Bean through result set
                ProblemBean bean = new ProblemBean();
                bean.setPID(rs.getInt("P_ID"));
                bean.setPTitle(rs.getString("P_TITLE"));
                bean.setSymbol(rs.getString("SYMBOL"));
                //Add to List
                list.add(bean);
            }
            //Return the List<ContestBean>
            return list;
        } catch (Exception e)
        {
            throw e;
        } finally
        {
            rs.close();
            stmt.close();
            conn.close();
        }

    }

    public static LinkedList<ProblemBean> queryBasicByCID(int cid) throws Exception
    {
        //Query All Problem for Contest cid
        Connection conn = null;
        Statement stmt = null;
        //查询所有的题目
        ResultSet rs = null;
        try
        {
            conn = MySQLDB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select problem.p_id,problem.p_title,problem.p_ac,problem.p_submit,symbol from con_problem,problem where C_ID=" + cid + " and con_problem.P_ID=problem.P_ID and P_STATE='UNLOCK' order by con_problem.symbol");
            //封装成链表
            LinkedList list = new LinkedList();
            while (rs.next())
            {
                //Create an Contest Info Bean through result set
                ProblemBean bean = new ProblemBean();
                bean.setPID(rs.getInt("P_ID"));
                bean.setPTitle(rs.getString("P_TITLE"));
                bean.setPSubmit(rs.getInt("P_SUBMIT"));
                bean.setPAC(rs.getInt("P_AC"));
                bean.setSymbol(rs.getString("SYMBOL"));
                //Add to List
                list.add(bean);
            }
            //Return the List<ContestBean>
            return list;
        }
        catch(Exception e)
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

    public static ProblemBean queryProblemByPID(int pid) throws Exception
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            //Query All Problem for Contest cid
            conn = MySQLDB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from problem where P_ID=" + pid + " and P_STATE='UNLOCK'");
            //转换成Bean
            ProblemBean bean = null;
            if (rs.next())
            {
                bean = new ProblemBean();
                bean.setPID(rs.getInt("P_ID"));
                bean.setPTitle(rs.getString("P_TITLE"));
                bean.setPTLE(rs.getLong("P_TLE"));
                bean.setPMLE(rs.getLong("P_MLE"));
                bean.setPFile(rs.getString("P_FILE"));
                bean.setPSubmit(rs.getInt("P_SUBMIT"));
                bean.setPAC(rs.getInt("P_AC"));
                bean.setIsLock(rs.getString("P_STATE"));
                bean.setIsSpj(rs.getString("P_SPECIAL"));
            }
            return bean;
        }
        catch(Exception e)
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

    public static ProblemBean queryProblemByPIDAdmin(int pid) throws Exception
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            //Query All Problem for Contest cid
            conn = MySQLDB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from problem where P_ID=" + pid + "");
            //转换成Bean
            ProblemBean bean = null;
            if (rs.next())
            {
                bean = new ProblemBean();
                bean.setPID(rs.getInt("P_ID"));
                bean.setPTitle(rs.getString("P_TITLE"));
                bean.setPTLE(rs.getLong("P_TLE"));
                bean.setPMLE(rs.getLong("P_MLE"));
                bean.setPFile(rs.getString("P_FILE"));
                bean.setPSubmit(rs.getInt("P_SUBMIT"));
                bean.setPAC(rs.getInt("P_AC"));
                bean.setIsLock(rs.getString("P_STATE"));
                bean.setIsSpj(rs.getString("P_SPECIAL"));
            }
            return bean;
        }
        catch(Exception e)
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

    public static LinkedList<ProblemBean> toProblemBeans(ResultSet rs) throws Exception
    {
        LinkedList list = new LinkedList();
        while (rs.next())
        {
            //Create an Contest Info Bean through result set
            ProblemBean bean = new ProblemBean();
            bean.setPID(rs.getInt("P_ID"));
            bean.setPTitle(rs.getString("P_TITLE"));
            bean.setPTLE(rs.getLong("P_TLE"));
            bean.setPMLE(rs.getLong("P_MLE"));
            bean.setPFile(rs.getString("P_FILE"));
            bean.setPSubmit(rs.getInt("P_SUBMIT"));
            bean.setPAC(rs.getInt("P_AC"));
            bean.setIsLock(rs.getString("P_STATE"));
            bean.setIsSpj(rs.getString("P_SPECIAL"));
            bean.setSymbol(rs.getString("SYMBOL"));
            //Add to List
            list.add(bean);
        }

        return list;
    }

    public static int addProblem(String title, long tle, long mle, boolean spj, boolean lock, String file) throws Exception
    {
        //获取数据库连接
        Connection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = MySQLDB.getConn();
            pstmt = conn.prepareStatement("insert into problem(P_ID,P_TITLE,P_TLE,P_MLE,P_SPECIAL,P_STATE,P_FILE) values (NULL,?,?,?,?,?,?)");
            //缓冲添加
            pstmt.setString(1, title);
            pstmt.setLong(2, tle);
            pstmt.setLong(3, mle);
            //是否SPJ
            if (spj)
            {
                pstmt.setString(4, "YES");
            } else
            {
                pstmt.setString(4, "NO");
            }
            //是否被锁定
            if (lock)
            {
                pstmt.setString(5, "LOCK");
            } else
            {
                pstmt.setString(5, "UNLOCK");
            }
            //题目详细信息对应的文件名
            pstmt.setString(6, file);
            pstmt.executeUpdate();
            pstmt.close();

            //获得最新插入那个题目的ID
            pstmt = conn.prepareStatement("select P_ID from problem where P_TITLE=? and P_FILE=?");
            pstmt.setString(1, title);
            pstmt.setString(2, file);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
            {
                int pid = rs.getInt("P_ID");
                rs.close();
                return pid;
            } else
            {
                rs.close();
                throw new Exception("Query new P_ID fail");
            }
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

    public static void editProblem(int pid,String title, long tle, long mle, boolean spj, boolean lock) throws Exception
    {
        //获取数据库连接
        Connection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = MySQLDB.getConn();
            pstmt = conn.prepareStatement("update problem set P_TITLE=?,P_TLE=?,P_MLE=?,P_SPECIAL=?,P_STATE=? where P_ID = ?");
            //缓冲添加
            pstmt.setString(1, title);
            pstmt.setLong(2, tle);
            pstmt.setLong(3, mle);
            //是否SPJ
            if (spj)
            {
                pstmt.setString(4, "YES");
            } else
            {
                pstmt.setString(4, "NO");
            }
            //是否被锁定
            if (lock)
            {
                pstmt.setString(5, "LOCK");
            } else
            {
                pstmt.setString(5, "UNLOCK");
            }
            pstmt.setInt(6, pid);
            pstmt.executeUpdate();
            pstmt.close();

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
}
