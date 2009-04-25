package client;

import bean.RunBean;
import client.gui.ClientFrame;
import client.gui.ExceptionJPanel;
import client.gui.StatJPanel;
import config.ClientConf;
import config.Const;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;
import tool.MsgBox;
import tool.Tools;

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

    //UI资源
    ClientFrame frame = null;
    //统计
    private StatJPanel statPanel = null;
    //异常
    private ExceptionJPanel exceptionPanel = null;

    //事件区
    /**显示统计区*/
    public static final byte EVENT_SHOW_STAT = 1;
    /**显示异常区*/
    public static final byte EVENT_SHOW_EXCEPTION = 2;
    /**Judge*/
    public static final byte EVENT_JUDGE = 11;
    /**Rcv*/
    public static final byte EVENT_RCV = 12;
    /**Send*/
    public static final byte EVENT_SEND = 13;
    /**Retry*/
    public static final byte EVENT_RETRY= 14;
    /**/
    public static final byte EVENT_EXCEPTION = 15;

    //统计技术区
    private int iJudge = 0;
    private int iRcv = 0;
    private int iRetry = 0;
    private int iSend = 0;

    /***
     * 控制器的构造函数
     */
    public ClientController(ClientFrame f)
    {
        try
        {
            frame = f;

            //初始化GUI
            statPanel = new StatJPanel(this);
            exceptionPanel  = new ExceptionJPanel(this);

            //读取Client参数，抛出异常：读取参数出错
            ClientConf conf = new ClientConf();

            //创建Rcv和Send
            rcv = new ClientRcvImpl(this);
            send = new ClientSendImpl(this);
            //启动Rcv和Send的线程
            Thread t1 = new Thread(rcv);
            Thread t2 = new Thread(send);
            t1.start();
            t2.start();
            //启动自身控制器线程
            new Thread(this).start();
        }
        catch(Exception e)
        {
            Tools.logException(e);
            e.printStackTrace();
            MsgBox.showWarnBox(null, "初始化失败", "请检查异常，并确认只开启一个Judge");
            System.exit(-1);
        }
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
                    //System.out.println(run.getResult());
                    handle(EVENT_JUDGE, run);
                }
                TimeUnit.MILLISECONDS.sleep(50);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void handle(byte event,Object o)
    {
        switch(event)
        {
            //显示统计区
            case EVENT_SHOW_STAT:
            {
                frame.setPane(statPanel);
            }
            break;
            //显示异常区
            case EVENT_SHOW_EXCEPTION:
            {
                frame.setPane(exceptionPanel);
            }
            break;
            //Judge
            case EVENT_JUDGE:
            {
                statPanel.setRunJudge(++iJudge);
                statPanel.addTableRow((RunBean)(o));
            }
            break;
            //RCV
            case EVENT_RCV:
            {
                statPanel.setRunRcv(++iRcv);
            }
            break;
            //Send
            case EVENT_SEND:
            {
                statPanel.setRunSend(++iSend);
            }
            break;
            //Retry
            case EVENT_RETRY:
            {
                statPanel.setRunRetry(++iRetry);
            }
            break;
            //异常
            case EVENT_EXCEPTION:
            {
                Tools.logException((Exception)o);
            }
            break;
        }
    }

}
