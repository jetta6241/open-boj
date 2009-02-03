package server;

import bean.RunBean;
import config.Const;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import server.db.DBService;

/**
 *
 * @author liheyuan
 */
public class ServerRcvImpl implements Runnable
{
    /**数据库服务实例句柄*/
    private DBService dbs = null;
    /**Server监听器*/
    private ServerSocket server = null;

    public ServerRcvImpl(DBService d)
    {
        try
        {
            //启动监听
            server = new ServerSocket(Const.SERVER_RCV_SOCKET);
            dbs = d;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        service();
    }

    private void service()
    {
        ObjectInputStream ois = null;
        while(true)
        {
            try
            {
                Socket socket = server.accept();
                ois = new ObjectInputStream(socket.getInputStream());
                //读取发来的第一个对象
                Object obj = ois.readObject();
                if(obj instanceof RunBean)
                {
                    //接受已判题目
                    dbs.addJudged((RunBean)obj);
                    System.out.println("Server收到判题结果:"+(RunBean)obj);
                    socket.getOutputStream().write("OK\r\n".getBytes());
                }
            }
            catch(Exception e){e.printStackTrace();}
        }
    }

    
}
