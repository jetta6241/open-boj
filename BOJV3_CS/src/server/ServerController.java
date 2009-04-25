package server;

import bean.JudgeBean;
import config.Const;
import config.ServerConf;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import server.db.DBService;
import server.db.RunDAO;
import server.gui.ExceptionJPanel;
import server.gui.MaintainJPanel;
import server.gui.ServerFrame;
import server.gui.TestJPanel;
import tool.MsgBox;
import tool.Tools;

/**
 *
 * @author liheyuan
 */
public class ServerController
{
    /**Server接受已判题目的线程*/
    ServerRcvImpl s_rcv = null;
    /**Server发送待判题目的线程*/
    ServerSendImpl s_send = null;
    /**DB服务*/
    DBService dbs = null;

    /**接受线程*/
    private Thread t_rcv = null;
    /**发送线程*/
    private Thread t_send = null;
    /**DB线程*/
    private Thread t_dbs = null;

    /**Judge队列，存放各个Judge的信息*/
    private LinkedList<JudgeBean> listJudge = new LinkedList();
    /**当前该向哪个Judge发送待判题目*/
    private int curJudge = 0;

    /**DAO区*/
    private RunDAO dao_run = null;

    /**主Frame*/
    private ServerFrame frame= null;
    /**统计面板*/
    private TestJPanel stat = null;
    /**维护面板*/
    private MaintainJPanel maintain = null;
    /**异常面板*/
    private ExceptionJPanel exception = null;

    //send，rcv的各种事件区
    /**发送成功*/
    public static final byte EVENT_SEND_SUCCESS = 1;
   /**发送失败*/
    public static final byte EVENT_SEND_FAIL = 2;
    /**接受成功*/
    public static final byte EVENT_RCV_SUCCESS = 3;
    /**接受失败*/
    public static final byte EVENT_RCV_FAIL = 4;
    /**读数据库更新*/
    public static final byte EVENT_DB_READ = 5;
    /**写数据库更新*/
    public static final byte EVENT_DB_WRITE = 6;
    /**获得异常*/
    public static final byte EVENT_EXCEPTION = 7;
    //GUI的各种事件区
    /**显示“统计”面板*/
    public static final byte EVENT_SHOW_TONGJI = 10;
    /**显示“维护”面板*/
    public static final byte EVENT_SHOW_WEIHU = 11;
    /**显示“异常”面板*/
    public static final byte EVENT_SHOW_YICHANG = 13;

    /**重试次数过多*/
    public static final byte EVENT_RETRY_END = 21;

    //Stat面板的相关绑定数值
    private Integer dbRead = new Integer(0);
    private Integer dbWrite = new Integer(0);
    private Integer runSend = new Integer(0);
    private Integer runRcv = new Integer(0);
    private Integer retry = new Integer(0);

    public ServerController(ServerFrame main)
    {
        try
        {
            frame = main;
            //从server_conf.ini中读取Server参数
            loadServerConfig();

            //数据库服务,Server发送和接收线程的对象
            dbs = new DBService(this);
            s_rcv = new ServerRcvImpl(dbs,this);
            s_send = new ServerSendImpl(dbs,this);

            //GUI资源初始化
            stat = new TestJPanel(this);
            exception = new ExceptionJPanel(this);
            maintain = new MaintainJPanel(this);

            //DAO初始化
            dao_run = new RunDAO(this);
            dbs.setRunDAO(dao_run);
            maintain.setRunDAO(dao_run);

            //读取的Judge机列表
            loadJudges();

            powerOn();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            MsgBox.showWarnBox(null, "系统初始化异常，即将退出", "系统初始化异常，请检查是否已经有Server启动。查看异常后，即将退出");
            Tools.logException(e);
            System.exit(-1);
        }
    }

    /***
     * 正常开启各个线程
     */
    public void powerOn()
    {
        //启动Server 接受和发送线程,dbs线程
        t_rcv = new Thread(s_rcv);
        t_send = new Thread(s_send);
        t_dbs = new Thread(dbs);
        //启动各个线程
        t_rcv.start();
        t_send.start();
        t_dbs.start();
    }

    /***
     * 关闭
     */
    public void powerOff()
    {
        t_rcv.interrupt();
        t_rcv.interrupt();
        t_dbs.interrupt();
    }

    /***
     * 读取服务器的相关配置
     */
    public void loadServerConfig() throws Exception
    {
        ServerConf conf= new ServerConf();
    }

    /***
     * 按照排队轮询，获得下一个Judge的信息
     * @return
     */
    public JudgeBean getNextJudge()
    {
        if(curJudge>=listJudge.size())
        {
            curJudge = 0;
        }
        if(listJudge.size()==0)
        {
            return null;
        }
        else
        {
            return listJudge.get(curJudge++);
        }
    }

    /***
     * 添加一个Judge的信息
     * @param ip
     * @param port
     */
    public void addJudge(String ip,int port)
    {
        JudgeBean j = new JudgeBean();
        j.setIp(ip);
        j.setPort(port);
        listJudge.add(j);
    }

    /***
     * 为Send待判题目线程添加Judge信息
     */
    public void loadJudges()
    {
        try
        {
            Scanner scan = new Scanner(new FileInputStream(new File("judges.ini")));
            String line = null;
            while(scan.hasNextLine())
            {
                line = scan.nextLine();
                if(!line.startsWith("#"))
                {
                    //#开头为注释，否则提取每行Judge信息
                    String arr [] = line.split(":");
                    this.addJudge(arr[0], Integer.parseInt(arr[1]));
                }
            }
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }

    /***
     * 处理Send，Rcv的各类事件
     * @param event
     * @param o
     */
    public void handle(byte event,Object o)
    {
        switch(event)
        {
            //成功发送
            case EVENT_SEND_SUCCESS:
            {
                String time = Tools.getTime();
                ((JudgeBean)o).incSend();
                ((JudgeBean)o).setTime(time);
                stat.setJudgeTable(listJudge);
                stat.setRunSend(++runSend);
                stat.setLastActive(time);
            }
            break;
            //失败发送
            case EVENT_SEND_FAIL:
            {
                ((JudgeBean)o).incRetry();
                stat.setJudgeTable(listJudge);
            }
            break;
            //成功接受
            case EVENT_RCV_SUCCESS:
            {
                String ip = (String)o;
                String time = Tools.getTime();
                for(JudgeBean b:listJudge)
                {
                    if(b.getIp().equals(ip))
                    {
                        b.incJudges();
                        b.setTime(time);
                        break;
                    }
                }
                stat.setJudgeTable(listJudge);
                stat.setRunRcv(++runRcv);
                stat.setLastActive(time);
            }
            break;
            //失败接受
            case EVENT_RCV_FAIL:
            {
                //更新Judge信息
                String ip = (String)o;
                for(JudgeBean b:listJudge)
                {
                    if(b.getIp().equals(ip))
                    {
                        b.incRetry();
                        break;
                    }
                }
                stat.setJudgeTable(listJudge);
            }
            break;
            //读数据库更新
            case EVENT_DB_READ:
            {
                int d = (Integer)o;
                if(d!=0)
                {
                    dbRead+=d;
                    stat.setDbRead(dbRead);
                }
            }
            break;
            //写数据库更新
            case EVENT_DB_WRITE:
            {
                int d = (Integer)o;
                if(d!=0)
                {
                    dbWrite+=d;
                    stat.setDbWrite(dbWrite);
                }
            }
            break;
            //异常
            case EVENT_EXCEPTION:
            {
                //记录异常到文件
                Tools.logException((Exception)o);
                //向面板添加
            }
            break;
            //统计面板
            case EVENT_SHOW_TONGJI:
            {
                frame.setPane(stat);
            }
            break;
            //维护面板
            case EVENT_SHOW_WEIHU:
            {
                frame.setPane(maintain);
            }
            break;
            //异常面板
            case EVENT_SHOW_YICHANG:
            {
                frame.setPane(exception);
            }
            break;
            //重试次数过多
            case EVENT_RETRY_END:
            {
                this.retry = 0;
                powerOff();
            }
            break;
        }
    }




}
