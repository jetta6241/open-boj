/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;


import beans.ContestBean;
import dao.ContestDAO;
import java.util.LinkedList;
import java.util.LinkedList;
import tool.*;

/**
 *
 * @author liheyuan
 */
public class ContestFacade
{
    /***
     * 根据起始时间，当前时间，结束时间，决定当前Contest的状态
     */
    public static void setContestState(ContestBean bean) throws Exception
    {
        long now = Tools.getTimeLong();
        long start = Tools.dateToLong(bean.getCStart());
        long end = Tools.dateToLong(bean.getCEnd());
        //Pending
        if(now<start)
        {
            bean.setCStatus(Const.CONTEST_PEND);
        }
        else if(now>start && now<end)
        {
            bean.setCStatus(Const.CONTEST_RUN);
        }
        else
        {
            bean.setCStatus(Const.CONTEST_END);
        }
    }

/***
     * 根据起始时间，当前时间，结束时间，决定当前Contest的状态
     */
    public static void setContestState(LinkedList<ContestBean> list) throws Exception
    {
        for(ContestBean bean:list)
        {
            setContestState(bean);
        }
    }

    /***
     * 显示某个竞赛的信息
     */
    public static ContestBean getContest(int cid) throws Exception
    {
        //查询指定竞赛的信息
        ContestBean contest = ContestDAO.queryContestByCID(cid);
        return contest;
    }

    public static LinkedList<ContestBean> listContest() throws Exception
    {
        LinkedList<ContestBean> list = ContestDAO.queryAllContest();
        return list;
    }
}
