package client;

import bean.RunBean;
import java.util.concurrent.TimeUnit;

/**
 * Client端的控制器，控制ClientRcv和ClientSend
 * @author liheyuan
 */
public class ClientController implements Runnable
{
    /**Client Rcv*/
    private ClientRcvImpl rcv = null;
    /**Client Send*/
    private ClientSendImpl send = null;
    /**Judge判题*/
    private Judger judge = new JudgerImpl();


    /***
     * 控制器的构造函数
     */
    public ClientController()
    {
        //创建Rcv和Send
        rcv = new ClientRcvImpl();
        send = new ClientSendImpl();
        //启动Rcv和Send的线程
        Thread t1 = new Thread(rcv);
        Thread t2 = new Thread(send);
        t1.start();
        t2.start();
        //启动自身控制器线程
        new Thread(this).start();
    }

    /***
     * 当Client Rcv收到待判题目的时候，通知本函数
     * 本函数将获取这个收到的Bean，并交给Judge进行判题
     */
    public void run()
    {
        while(true)
        {
            try
            {
                //获取一个待判题目，并判别
                RunBean run = rcv.getFirstRunBean();
                if(run!=null)
                {
                    judge.judge(run);
                    send.push(run);
                    System.out.println(run.getResult());
                }
                TimeUnit.MILLISECONDS.sleep(50);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String [] args)
    {
        ClientController c = new ClientController();
    }
}
