package listener;
import beans.ContestBean;
import beans.ProblemBean;
import beans.RankBean;
import beans.RunBean;
import beans.UserBean;
import dao.ContestDAO;
import dao.ProblemDAO;
import dao.RunDAO;
import dao.UserDAO;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TimerTask;
import javax.servlet.ServletContext;
import rank.Rank;
import rank.bean.PenaltyBean;
import tool.Const;
import tool.Tools;

/**
 * 定时更新比赛任务的Task
 * @author liheyuan
 */
public class RankTask extends TimerTask
{
    private ServletContext context = null;
    private String rootPath = null;

    public String getRootPath(ServletContext ctx)
    {
        return ctx.getRealPath("/");
    }

    public RankTask(ServletContext context)
    {
        this.context = context;
        rootPath = getRootPath(context);
    }

    @Override
    public void run()
    {
        try
        {
            LinkedList<ContestBean> list = ContestDAO.queryRunContest();
            //更新每一个Running的Contest
            for(ContestBean b:list)
            {
                //判断是否封榜
                long end = Tools.dateToLong(b.getCEnd());
                long now = Tools.getTimeLong();
                if(end-now<=3600*1000)
                {
                    //最后一小时封榜
                    continue;
                }

                LinkedList<RunBean> listRun = RunDAO.queryBasicByCID(b);
                Rank rank = new Rank(b);
                //更新每一个Run
                for(RunBean run:listRun)
                {
                    rank.addRun(run);
                }
                //输出Rank
                writeRank(b,rank.getRankList());
            }
        }
        catch(Exception e)
        {
            context.log("open-boj:更新Rank失败。"+e.getMessage());
        }
    }

    public void writeRank(ContestBean contest,RankBean [] rank) throws Exception
    {
        //获得webapp的根绝对目录
        //生成输出的文件
        StringBuilder sb = new StringBuilder();
        //生成头部文件
        writeHead(sb,contest);
        //生成Rank表格
        writeRankTable(sb,contest,rank);

        //生成尾部
        writeTail(sb,contest);

        //写入文件
        OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(rootPath+Const.RANK_PATH+contest.getCID()+".html"),"utf-8");
        fout.write(sb.toString());
        fout.close();
    }

    /**
     * 生成头部信息
     * @param sb
     * @param contest
     */
    private void writeHead(StringBuilder sb,ContestBean contest)
    {
        sb.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
        sb.append("<html>\r\n");
        sb.append("<head>\r\n");
        sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
        sb.append("<title>Rank of "+contest.getCTitle()+"</title>\r\n");
        sb.append("<link rel=\"stylesheet\" href=\"rank.css\" type=\"text/css\"/>");
        sb.append("</head>\r\n<body>\r\n");
        sb.append("<h1>Rank of "+contest.getCTitle()+"</h1>");
        sb.append("<p>"+contest.getCStart()+" ------ "+contest.getCEnd()+"</p>");
        return ;
    }

    /**
     * 生成头部信息
     * @param sb
     * @param contest
     */
    private void writeRankTable(StringBuilder sb,ContestBean contest,RankBean [] rank) throws Exception
    {
        LinkedList<ProblemBean> problems= ProblemDAO.queryBasicByCID(contest.getCID());

        sb.append("<table>\r\n");
        //写入标题行
        sb.append("<tr><th style=\"width:5%\">Rank</th><th style=\"width:5%\">Name</th><th style=\"width:3%\">Accepted</th><th style=\"width:5%\">Penalty</th>");

        for(ProblemBean p:problems)
        {
            sb.append("<th style=\"width:4%\">"+p.getSymbol()+"</th>");
        }
        sb.append("</tr>\r\n");

        //对于每一个Rank上的用户写入一行
        int i=0;
        LinkedList<UserBean> user = UserDAO.queryUserByRank(rank);
        for(RankBean r:rank)
        {
            sb.append("<tr><td>"+(i+1)+"</td><td>"+user.get(i).getUUser()+"<br>("+user.get(i).getUSchool()+")</td>");
            sb.append("<td>"+r.getSolved()+"</td><td>"+toHHMMSSTime(r.getTotal())+"</td>");

            HashMap<Integer,PenaltyBean> submit = r.getPenalty();
            for(ProblemBean p:problems)
            {
                if(submit.containsKey(p.getPID()))
                {
                    //没过
                    if(submit.get(p.getPID()).getAcTime()==-1)
                    {
                        sb.append("<td>(-"+submit.get(p.getPID()).getReject()+")</td>");
                    }
                    else if(submit.get(p.getPID()).getReject()==0)//1Y
                    {
                        sb.append("<td>"+toHHMMSSTime(submit.get(p.getPID()).getAcTime())+"</td>");
                    }
                    else
                    {
                        sb.append("<td>"+toHHMMSSTime(submit.get(p.getPID()).getAcTime())+"<br>(-"+submit.get(p.getPID()).getReject()+")</td>");
                    }
                }
                else
                {
                    sb.append("<td></td>");
                }
            }

            sb.append("</tr>");
            i++;
        }

        sb.append("</table>\r\n");
        return ;
    }

    /***
     * 生成尾部信息
     * @param sb
     * @param contest
     */
    private void writeTail(StringBuilder sb,ContestBean contest)
    {
        sb.append("<p>Last update time : "+Tools.getTime()+"</p>");
        sb.append("</body>\r\n</html>");
        return ;
    }

    /**
     * 转化成HH:MM:SS的时间格式
     * long为秒数
     */
    private String toHHMMSSTime(long time)
    {
        long h = time/3600;
        time-=3600*h;
        long m = time/60;
        time-=m*60;
        long s = time%60;
        return h+":"+m+":"+s;
    }
}
