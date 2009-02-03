package client;

import bean.JudgeBean;
import bean.RunBean;
import config.Const;
import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author liheyuan
 */
public class ClientSendImpl implements Runnable
{
    /**待发送的队列*/
    private final LinkedList<RunBean>  queue = new LinkedList();

    public void run()
    {
        service();
    }

    private void service()
    {
        while(true)
        {
            try
            {
                //保证线程同步
                synchronized(queue)
                {
                    if(!queue.isEmpty())
                    {
                        if(sendRunBean(queue.getFirst()))
                        {
                            //如果发送成功，移除
                            queue.removeFirst();
                            System.out.println("发送判题结果");
                        }
                        TimeUnit.MILLISECONDS.sleep(50);
                    }
                    else
                    {
                        TimeUnit.MILLISECONDS.sleep(100);
                    }
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    /***
     *
     * @param  RunBean 要尝试发送的RunBean
     * @return 成功发送，则返回true，否则返回false
     */
    private boolean sendRunBean(RunBean b)
    {
        Socket socket = null;
        try
        {
            socket = new Socket(Const.SERVER_IP,Const.SERVER_RCV_SOCKET);
            //构造Object发送流
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(b);
            //收取Server返回的信息
            Scanner scan = new Scanner(socket.getInputStream());

            if(scan.nextLine().equals("OK"))
            {
                //能成功接受到返回信息，成功
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception e)
        {
            //产生异常，则发送失败
            e.printStackTrace();
            return false;
        }
        finally
        {
            try
            {
                socket.close();
                socket = null;
            }
            catch (Exception ex)
            {
            }
        }
    }
    
    public void push(RunBean r)
    {
        synchronized(queue)
        {
            queue.add(r);
        }
    }


}
