package dao;

import beans.RankBean;
import beans.UserBean;
import db.MySQLDB;
import java.sql.*;
import java.util.LinkedList;
import tool.Tools;

/**
 *
 * @author liheyuan
 */
public class UserDAO
{
    /***
     * 向数据库中添加新用户
     * @param u
     * @throws java.lang.Exception
     */
    public static void addUsers(LinkedList<UserBean> list) throws Exception
    {
        Connection conn = MySQLDB.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            for (UserBean u : list)
            {
                //先查询是否存在该用户
                pstmt = conn.prepareStatement("select * from user where U_USER = ?");
                pstmt.setString(1, u.getUUser());
                rs = pstmt.executeQuery();
                if (rs.next())
                {
                    rs.close();
                    //已经找到了同名用户
                    throw new Exception("This Username is already exist.");
                }
                rs.close();
                pstmt.close();
                //创建
                pstmt = conn.prepareStatement("insert into user values (NULL,?,PASSWORD(?),?,?,NULL)");
                pstmt.setString(1, u.getUUser());
                pstmt.setString(2, u.getUPass());
                pstmt.setString(3, u.getUSchool());
                pstmt.setString(4, u.getUEmail());
                pstmt.executeUpdate();
            }
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

    /***
     * 向数据库中添加新用户
     * @param u
     * @throws java.lang.Exception
     */
    public static void addUser(UserBean u) throws Exception
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            conn = MySQLDB.getConn();
            //先查询是否存在该用户
            pstmt = conn.prepareStatement("select * from user where U_USER = ?");
            pstmt.setString(1, u.getUUser());
            rs = pstmt.executeQuery();
            if (rs.next())
            {
                rs.close();
                //已经找到了同名用户
                throw new Exception("This Username is already exist.");
            }
            rs.close();
            pstmt.close();
            //创建
            pstmt = conn.prepareStatement("insert into user values (NULL,?,PASSWORD(?),?,?,NULL)");
            pstmt.setString(1, u.getUUser());
            pstmt.setString(2, u.getUPass());
            pstmt.setString(3, u.getUSchool());
            pstmt.setString(4, u.getUEmail());
            pstmt.executeUpdate();
        } catch (Exception e)
        {
            throw e;
        } finally
        {
            MySQLDB.close(rs);
            MySQLDB.close(pstmt);
            MySQLDB.close(conn);
        }
    }

    public static UserBean queryUser(int uid) throws Exception
    {
        Connection conn = MySQLDB.getConn();
        //先查询是否存在该用户
        PreparedStatement pstmt = conn.prepareStatement("select * from user where U_ID=?");
        ResultSet rs = null;
        try
        {
            pstmt.setInt(1, uid);
            rs = pstmt.executeQuery();

            if (rs.next())
            {
                //存在该用户
                UserBean bean = new UserBean();
                bean.setUID(rs.getInt("U_ID"));
                bean.setUUser(rs.getString("U_USER"));
                bean.setUPass(rs.getString("U_PASS"));
                bean.setUSchool(rs.getString("U_SCHOOL"));
                bean.setUEmail(rs.getString("U_EMAIL"));
                //返回查询的用户结果
                return bean;
            }
            else
            {
                return null;
            }
        } catch (Exception e)
        {
            throw new Exception("User or Pass not correct ",e);
        } finally
        {
            MySQLDB.close(rs);
            MySQLDB.close(pstmt);
            MySQLDB.close(conn);
        }
    }

    /***
     * 查询User信息
     */
    public static UserBean chkLogin(UserBean user) throws Exception
    {
        Connection conn = MySQLDB.getConn();
        //先查询是否存在该用户
        PreparedStatement pstmt = conn.prepareStatement("select * from user where U_USER = ? and U_PASS=password(?)",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE );
        ResultSet rs = null;
        try
        {
            pstmt.setString(1, user.getUUser());
            pstmt.setString(2, user.getUPass());
            rs = pstmt.executeQuery();
            
            if (rs.next())
            {
                //验证成功
                UserBean bean = new UserBean();
                bean.setUID(rs.getInt("U_ID"));
                bean.setUUser(rs.getString("U_USER"));
                bean.setUPass(rs.getString("U_PASS"));
                bean.setUSchool(rs.getString("U_SCHOOL"));
                bean.setUEmail(rs.getString("U_EMAIL"));
                //更新最后登录时间
                rs.updateString("U_LAST_LOGIN", Tools.getTime());
                rs.updateRow();
                //关闭相应资源
                rs.close();
                pstmt.close();
                conn.close();
                //返回查询的用户结果
                return bean;
            }
            else
            {
                return null;
            }
        } catch (Exception e)
        {
            throw new Exception("User or Pass not correct ",e);
        } finally
        {
            MySQLDB.close(rs);
            MySQLDB.close(pstmt);
            MySQLDB.close(conn);
        }
    }
    
    /**
     * 编辑用户信息
     */
    public static void editUser(UserBean user,UserBean userOld) throws Exception
    {
        Connection conn = MySQLDB.getConn();
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        try
        {
            pstmt2 = conn.prepareStatement("update user set u_email=?,u_school=?,u_Pass=password(?) where u_id=? and u_pass=password(?)");
            //验证身份成功
            pstmt2.setString(1, user.getUEmail());
            pstmt2.setString(2, user.getUSchool());
            //是否需要更改密码
            if(!user.getUPass().isEmpty())
            {
                //需要更改
                pstmt2.setString(3, user.getUPass());
            }
            else
            {
                //不需要更改
                pstmt2.setString(3, userOld.getUPass());
            }
            pstmt2.setInt(4, userOld.getUID());
            pstmt2.setString(5, userOld.getUPass());
            if(pstmt2.executeUpdate()!=1)
            {
                throw new Exception("Password error");
            }
        } catch (Exception e)
        {
            throw e;
        } finally
        {
            MySQLDB.close(rs);
            MySQLDB.close(pstmt);
            MySQLDB.close(pstmt2);
            MySQLDB.close(conn);
        }
    }

    /***
     * 为Rank查询User信息
     */
    public static LinkedList<UserBean> queryUserByRank(RankBean[] rank) throws Exception
    {
        Connection conn = MySQLDB.getConn();
        //先查询是否存在该用户
        PreparedStatement pstmt = conn.prepareStatement("select * from user where U_ID=?");
        try
        {
            LinkedList<UserBean> list = new LinkedList();
            for (RankBean r : rank)
            {
                pstmt.setInt(1, r.getUID());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next())
                {
                    UserBean bean = new UserBean();
                    bean.setUID(rs.getInt("U_ID"));
                    bean.setUUser(rs.getString("U_USER"));
                    bean.setUSchool(rs.getString("U_SCHOOL"));
                    bean.setUEmail(rs.getString("U_EMAIL"));
                    //关闭相应资源
                    rs.close();
                    //返回查询的用户结果
                    list.add(bean);
                }
            }
            return list;
        } catch (Exception e)
        {
            throw e;
        } finally
        {
            MySQLDB.close(pstmt);
            MySQLDB.close(conn);
        }
    }
}
