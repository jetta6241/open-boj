package dao;

import db.MySQLDB;
import java.sql.*;
import tool.Const;

/**
 *
 * @author liheyuan
 */
public class ConfigDAO
{
    /***
     * 更新滚动和首页的文字
     * @param top
     * @param index
     */
    public static void updateMsg(String top) throws Exception
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = MySQLDB.getConn();
            pstmt = conn.prepareCall("update config set CONFIG_TOP_TEXT=?");
            pstmt.setString(1, top);
            pstmt.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        finally
        {
            MySQLDB.close(pstmt);
            MySQLDB.close(conn);
        }
    }

    /***
     * 从数据库中读取配置
     */
    public static void loadConfig() throws Exception
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try
        {
            conn = MySQLDB.getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from config");
            if(rs.next())
            {
                Const.CONFIG_ALLOW_REG = rs.getString("CONFIG_ALLOW_REG");
                Const.CONFIG_TOP_TEXT = rs.getString("CONFIG_TOP_TEXT");
                Const.CONFIG_INDEX_TEXT = rs.getString("CONFIG_INDEX_TEXT");
                Const.ADMIN_USER = rs.getString("ADMIN_USER");
                Const.ADMIN_PASS = rs.getString("ADMIN_PASS");
                Const.BALLON_PASS = rs.getString("BALLON_PASS");
                Const.RANK_PATH = rs.getString("RANK_PATH");
                Const.DATA_PATH = rs.getString("DATA_PATH");
                Const.PROBLEM_PATH = rs.getString("PROBLEM_PATH");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        finally
        {
            MySQLDB.close(rs);
            MySQLDB.close(stmt);
            MySQLDB.close(conn);
        }
    }
}
