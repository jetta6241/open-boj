package rank;

import beans.ContestBean;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Date;
import java.text.SimpleDateFormat;
import beans.RunBean;
import beans.RankBean;
import java.util.Arrays;
import java.util.Collection;
import rank.bean.PenaltyBean;

public class Rank
{
    /** Rank Map */
    private HashMap<Integer, RankBean> rankMap = new HashMap<Integer, RankBean>();

    private ContestBean contest = null;

    /**开始时间*/
    private long start = 0;

    public Rank(ContestBean bean)
    {
        contest = bean;
        start = toTime(contest.getCStart());
    }

    /**
     * 返回排序好的Rank
     * @return
     */
    public RankBean [] getRankList()
    {
        RankBean [] list= new RankBean[rankMap.size()];
        rankMap.values().toArray(list);
        Arrays.sort(list);
        return list;
    }

    /** Update Rank by Run */
    public void addRun(RunBean run)
    {
        int uID = run.getUID(), pID = run.getPID();
        //查询rank是否包含当前用户
        if (!rankMap.containsKey(uID))
        {
            //没有则新增用户
            rankMap.put(uID, newRankBean(uID));
        }
        RankBean rank = rankMap.get(uID);
        //查询用户是否提交过这个题目
        if (!rank.getPenalty().containsKey(pID))
        {
            rank.getPenalty().put(pID, newPenaltyBean(pID));
        }
        PenaltyBean penalty = rank.getPenalty().get(pID);
        if (penalty.getAcTime() != -1) // has been Accepted
        {
            return;
        }
        if (run.getRResult().compareTo("AC") == 0)
        {
            penalty.setAcTime(toTime(run.getRSubtime())-start);
            rank.addSolved();
            rank.addTotal(penalty.getAcTime() + penalty.getReject()*20*60);
        }
        else
        {
            penalty.addReject();
        }
        //rankSet.remove(rank);
        //rankSet.add(rank);
    }

    /** New RankBean with uID, user */
    private static RankBean newRankBean(int uID)
    {
        RankBean bean = new RankBean();
        bean.setUID(uID);
        bean.setSolved(0);
        bean.setPenalty(new HashMap<Integer, PenaltyBean>());
        bean.setTotal(0);
        return bean;
    }

    /** New PenaltyBean with uID, user */
    private static PenaltyBean newPenaltyBean(int pID)
    {
        PenaltyBean bean = new PenaltyBean();
        bean.setPID(pID);
        bean.setAcTime(-1);
        bean.setReject(0);
        return bean;
    }

    public static long toTime(String str)
    {
        return dateToLong(str);
    }

    /**
     * 返回秒数
     * @param d
     * @return
     */
    public static long dateToLong(String d)
    {
        try
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(d);
            return date.getTime()/1000;
        }
        catch(Exception e)
        {
            return 0;
        }

    }
    
}
