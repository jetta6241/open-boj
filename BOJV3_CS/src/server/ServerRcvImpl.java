package server;

import bean.RunBean;
import config.Const;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
    ServerController controller = null;
    
    public ServerRcvImpl(DBService d,ServerController con) throws Exception
    {
        //启动监听
        server = new ServerSocket(Const.SERVER_RCV_PORT);
        dbs = d;
        controller = con;
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
            String ip = null;
            try
            {
                Socket socket = server.accept();
                ip = socket.getInetAddress().getHostAddress();
                ois = new ObjectInputStream(socket.getInputStream());
                //读取发来的第一个对象
                Object obj = ois.readObject();
                if(obj instanceof RunBean)
                {
                    //接受已判题目
                    dbs.addJudged((RunBean)obj);
                    //System.out.println("Server收到判题结果:"+(RunBean)obj);
                    socket.getOutputStream().write("OK\r\n".getBytes());
                    //成功
                    controller.handle(ServerController.EVENT_RCV_SUCCESS, ip);
                }
            }
            catch(Exception e)
            {
                controller.handle(ServerController.EVENT_EXCEPTION,new Exception("接受失败",e));
                controller.handle(ServerController.EVENT_RCV_FAIL, ip);
            }
        }
    }

    
}
