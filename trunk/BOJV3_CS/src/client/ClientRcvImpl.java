package client;
import bean.RunBean;
import client.gui.ClientFrame;
import config.Const;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author liheyuan
 */
public class ClientRcvImpl implements Runnable
{
    /**服务器监听器*/
    private ServerSocket server = null;
    /**Client收到的待判题目队列*/
    private final LinkedList<RunBean> queue = new LinkedList();

    

    /**控制器*/
    ClientController controller = null;

    /***
     * Runnable的主函数
     */
    public void run()
    {
        //转入service进行处理
        service();
    }

    public ClientRcvImpl(ClientController con) throws IOException
    {
        controller = con;

        server = new ServerSocket(Const.CLIENT_RCV_PORT);

    }

    /***
     * Client的服务程序
     */
    public void service()
    {
        Socket socket = null;
        ObjectInputStream ois = null;
        while(true)
        {
            try
            {
                //如果长时间没有与Server连接，自动重连
                socket = server.accept();
                ois = new ObjectInputStream(socket.getInputStream());
                //读取发来的第一个对象
                Object obj = ois.readObject();
                if(obj instanceof RunBean)
                {
                    //接受待判题目
                    synchronized(queue)
                    {
                        queue.add((RunBean)obj);
                        //System.out.println("收到RunBean");
                        controller.handle(ClientController.EVENT_RCV, null);
                    }
                    socket.getOutputStream().write("OK\r\n".getBytes());
                }
            }
            catch(Exception e)
            {
                controller.handle(ClientController.EVENT_RETRY, null);
                controller.handle(ClientController.EVENT_EXCEPTION, new Exception("接受线程出错",e));
            }
            finally
            {
                try
                {
                    ois.close();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * @return the queue
     */
    public LinkedList<RunBean> getQueue()
    {
        return queue;
    }

    /***
     * 移除队列中的第一个RunBean
     * @return
     */
    public RunBean getFirstRunBean()
    {
        //要保持队列的同步
        synchronized(queue)
        {
            if(queue.isEmpty())
            {
                return null;
            }
            else
            {
                return queue.removeFirst();
            }
        }
    }

}
