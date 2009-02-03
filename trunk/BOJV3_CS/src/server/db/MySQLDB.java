package server.db;

import config.Const;
import java.sql.*;


/**
 * MySQL数据库的逻辑连接层类
 * @author liheyuan
 */
public class MySQLDB
{
    /**数据库连接*/
    private Connection conn;
    /**是否加载过了Class*/
    private static boolean inited = false;

    /**返回数据库的Connection*/
    public static Connection getConn()
    {
        //若没初始化驱动，先初始化
        if(!inited)
        {
            try
            {
                //没有加载数据库驱动
                Class.forName("com.mysql.jdbc.Driver");
                inited = true;
            }
            catch (ClassNotFoundException ex)
            {
                ex.printStackTrace();
                return null;
            }
        }
        //返回Connection
        try
        {
            //返回Connection
            return DriverManager.getConnection(Const.DB_URL, Const.DB_USER, Const.DB_PASS);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
