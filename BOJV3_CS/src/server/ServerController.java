package server;

import java.io.*;
import java.util.Scanner;
import server.db.DBService;

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
    
    public ServerController()
    {
        dbs = new DBService();
        s_rcv = new ServerRcvImpl(dbs);
        s_send = new ServerSendImpl(dbs);
        loadJudges();
        //启动Server 接受和发送线程
        new Thread(s_rcv).start();
        new Thread(s_send).start();

        //启动控制器线程
        new Thread(dbs).start();
    }

    /***
     * 为Send待判题目线程添加Judge信息
     */
    public void loadJudges()
    {
        try
        {
            Scanner scan = new Scanner(new FileInputStream(new File("judge.ini")));
            String line = null;
            while(scan.hasNextLine())
            {
                line = scan.nextLine();
                if(!line.startsWith("#"))
                {
                    //#开头为注释，否则提取每行Judge信息
                    String arr [] = line.split(":");
                    s_send.addJudge(arr[0], Integer.parseInt(arr[1]));
                }
            }
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        

    }

    public static void main(String [] args)
    {
        ServerController s = new ServerController();
    }
}
