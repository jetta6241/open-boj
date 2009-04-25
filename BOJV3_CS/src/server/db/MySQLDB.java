package server.db;

import config.Const;
import java.sql.*;
import tool.Tools;


/**
 * MySQL数据库的逻辑连接层类
 * @author liheyuan
 */
public class MySQLDB
{
    /**数据库连接*/
    private static Connection conn;
    /**是否加载过了Class*/
    private static boolean inited = false;

    /**返回数据库的Connection*/
    public static Connection getConn() throws Exception
    {
        //若没初始化驱动或者连接已经失效，先初始化
        if(!inited || conn.isClosed())
        {
            try
            {
                //没有加载数据库驱动
                Class.forName("com.mysql.jdbc.Driver");
                inited = true;
                //得到连接
                conn = DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASS);
            }
            catch (Exception ex)
            {
                Tools.logException(ex);
                return null;
            }
        }
        //返回Connection
        return conn;

    }
}
