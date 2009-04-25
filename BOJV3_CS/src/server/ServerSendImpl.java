package server;

import bean.JudgeBean;
import bean.RunBean;
import java.net.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import server.db.DBService;

/**
 * 向ClientRcv发送待判题目
 * @author liheyuan
 */
public class ServerSendImpl implements Runnable
{
    /**DB服务*/
    private DBService dbs = null;
    /**Server控制器*/
    private ServerController controller = null;
    public ServerSendImpl(DBService d,ServerController con)
    {
        dbs = d;
        controller = con;
    }

    /***
     * Runnable接口的主流程函数
     */
    public void run()
    {
        service();
    }

    /**从队列中取出*/
    public void service()
    {
        while(true)
        {
            //try 发送待判题目
            try
            {
                RunBean r = dbs.getUnjudgeFirst();
                if(r!=null)
                {
                    //非空，发送
                    JudgeBean j = controller.getNextJudge();
                    if(sendRunBean(r,j))
                    {
                        //如果发送成功，移除
                        dbs.removeUnjudgeFirst();
                        //成功事件
                        controller.handle(controller.EVENT_SEND_SUCCESS, j);
                    }
                    else
                    {
                        controller.handle(controller.EVENT_SEND_FAIL, j);
                    }
                    TimeUnit.MILLISECONDS.sleep(50);
                    //如果发送失败，不弹出队列头元素，等待下次重发
                }
                else
                {
                    //定时休眠线程
                    TimeUnit.MILLISECONDS.sleep(100);
                }

            }
            catch(Exception e)
            {
                controller.handle(ServerController.EVENT_EXCEPTION,new Exception("发送题目线程出错",e));
                //e.printStackTrace();
            }//end catch 发送待判题目
        }
    }

    /***
     *
     * @param  RunBean 要尝试发送的RunBean
     * @return 成功发送，则返回true，否则返回false
     */
    private boolean sendRunBean(RunBean b,JudgeBean judge)
    {

        if(judge==null)
        {
            //没有可用的Client
            return false;
        }

        Socket socket = null;
        try
        {

            //获取当前要使用的Judge
            socket = new Socket(judge.getIp(),judge.getPort());
            //构造Object发送流
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(b);
            //收取Judge返回的信息
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
            controller.handle(ServerController.EVENT_EXCEPTION,new Exception("发送线程发送题目失败",e));
            return false;
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch (Exception ex)
            {
            }
        }
    }


}
