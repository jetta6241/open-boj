package server.db;
import bean.RunBean;
import config.Const;
import java.sql.*;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import server.ServerController;


/**
 * 从数据路中读取数据
 * @author liheyuan
 */
public class DBService implements Runnable
{
    /**待判题目*/
    private final LinkedList<RunBean> unjudge_queue = new LinkedList();
    /**已判题目*/
    private final LinkedList<RunBean> judged_queue = new LinkedList();

    //控制器
    ServerController controller = null;

    //RunDAO
    private RunDAO runDAO = null;

    public DBService(ServerController con)
    {
        controller = con;
    }

    public void run()
    {
        while(true)
        {
            try
            {
                /**读取待判题目，把每个待判的加入到队列中*/
                LinkedList<RunBean> list = runDAO.getUnjuged();
                for(RunBean run:list)
                {
                    //写入到待判队列
                    synchronized(unjudge_queue)
                    {
                        unjudge_queue.add(run);
                    }
                }
                //更新GUI
                controller.handle(ServerController.EVENT_DB_READ, list.size());
                
                /**写回已判题目*/
                int cnt = judged_queue.size();
                synchronized(judged_queue)
                {
                    //调用更新Judge结果
                    runDAO.updateJudged(judged_queue);
                }
                //更新GUI
                controller.handle(ServerController.EVENT_DB_WRITE, cnt);
                
                /**休眠线程*/
                TimeUnit.MILLISECONDS.sleep(300);
            }
            catch(Exception e)
            {
                controller.handle(ServerController.EVENT_EXCEPTION,new Exception("数据库线程出错",e));
            }
        }
    }

    public RunBean getUnjudgeFirst()
    {
        synchronized(unjudge_queue)
        {
            if(unjudge_queue.isEmpty())
            {
                return null;
            }
            else
            {
                return unjudge_queue.getFirst();
            }
        }
    }

    public void removeUnjudgeFirst()
    {
        synchronized(unjudge_queue)
        {
            unjudge_queue.removeFirst();
        }
    }
    
    public void addJudged(RunBean b)
    {
        synchronized(judged_queue)
        {
            judged_queue.add(b);
        }
    }

    /**
     * @return the runDAO
     */
    public RunDAO getRunDAO()
    {
        return runDAO;
    }

    /**
     * @param runDAO the runDAO to set
     */
    public void setRunDAO(RunDAO runDAO)
    {
        this.runDAO = runDAO;
    }

}
