package db;

import java.sql.*;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author liheyuan
 */
public class MySQLDB
{
    private static Connection conn = null;

    /**
     * @return the Connection to Database
     */
    public static Connection getConn() throws Exception
    {
        //Get DB Connection from pool
        DataSource ds = null;
        InitialContext ctx = new InitialContext();
        ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ins");
        return ds.getConnection();
    }

    public static void close(Statement stmt)
    {
        try
        {
            if(stmt!=null)
            {
                stmt.close();
            }
        }
        catch(Exception e)
        {
            
        }
    }

    public static void close(ResultSet rs)
    {
        try
        {
            if(rs!=null)
            {
                rs.close();
            }
        }
        catch(Exception e)
        {

        }
    }

    public static void close(Connection conn)
    {
        try
        {
            if(conn!=null)
            {
                conn.close();
            }
        }
        catch(Exception e)
        {

        }
    }

}
