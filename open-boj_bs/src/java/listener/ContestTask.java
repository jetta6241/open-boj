
package listener;

import dao.ContestDAO;
import db.MySQLDB;
import java.sql.*;
import java.util.TimerTask;
import javax.servlet.ServletContext;
import tool.Const;

/**
 * 定时更新比赛任务的Task
 * @author liheyuan
 */
public class ContestTask extends TimerTask
{
    private long count = 1;
    private ServletContext context = null;

    public ContestTask(ServletContext context)
    {
        this.context = context;
    }


    @Override
    public void run()
    {
        try
        {
            //获得连接
            //每1次更新所有的Pending
            ContestDAO.updateContestState(Const.CONTEST_PEND);
            //每1次更新所有的Running中的比赛
            ContestDAO.updateContestState(Const.CONTEST_RUN);
            //每30次更新所有的Ended比赛信息
            if(count%30 == 0)
            {
                ContestDAO.updateContestState(Const.CONTEST_END);
            }
            count++;
        }
        catch(Exception e)
        {
            context.log("open-boj:更新比赛状态出错"+e.getMessage());
        }
    }
    
}
