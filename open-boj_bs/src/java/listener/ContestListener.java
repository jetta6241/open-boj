package listener;

import dao.ConfigDAO;
import java.util.Timer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContestListener implements ServletContextListener
{
    /**定时器*/
    private Timer timer = null;

    /***
     * 初始化监听器
     * @param event
     */
    public void contextInitialized(ServletContextEvent event)
    {
        //读取参数
        loadConfig(event);
        event.getServletContext().log("读取参数成功");
        //在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能
        timer = new Timer(true);
        //添加更新比赛日志的任务
        timer.schedule(new ContestTask(event.getServletContext()), 0, 1500);
        event.getServletContext().log("open-boj：添加“更新比赛状态”任务");
        //添加更新Rank的任务
        timer.schedule(new RankTask(event.getServletContext()), 0, 5000);
        event.getServletContext().log("open-boj：添加“更新气球状态”任务");
    }

    public void contextDestroyed(ServletContextEvent event)
    {//在这里关闭监听器，所以在这里销毁定时器。
        timer.cancel();
        event.getServletContext().log("open-boj：定时器销毁");
    }

    /***
     * 读取参数
     */
    public void loadConfig(ServletContextEvent event)
    {
        try
        {
            ConfigDAO.loadConfig();
        }
        catch(Exception e)
        {
            event.getServletContext().log("读取参数出错:"+e.getMessage());
        }
    }
}  
