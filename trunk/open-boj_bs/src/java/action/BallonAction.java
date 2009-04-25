package action;

import beans.BallonBean;
import beans.ContestBean;
import beans.ProblemBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.BallonDAO;
import dao.ContestDAO;
import java.util.LinkedList;
import tool.Const;

/**
 *
 * @author liheyuan
 */
public class BallonAction extends ActionSupport
{
    private String ballonPass = null;
    private LinkedList<BallonBean> ballonList = null;
    private LinkedList<ContestBean> contestList = null;
    /**要查询气球状态的竞赛id*/
    private int cid = 0;
    /**要标记为“已经发送”的气球id*/
    private int bid = 0;
    /***
     * 登录发送气球的程序
     * @return
     */
    public String login()
    {
        if(ballonPass.equals(Const.BALLON_PASS))
        {
            //在Session中添加标识
            ActionContext.getContext().getSession().put(Const.TOKEN_BALLON,ballonPass);
            return SUCCESS;
        }
        else
        {
            this.addActionError("Wrong password for view ballons.");
            return ERROR;
        }
        
    }

    /***
     * 显示某个比赛的气球信息
     * @return
     */
    public String show()
    {
        try
        {
            setContestList(ContestDAO.queryRunContest());
            ContestBean contest = null;
            //如果没有指定更新哪个，默认更新第一个
            if(cid==0)
            {
                contest = getContestList().getFirst();
            }
            else
            {
                for(ContestBean bean:getContestList())
                {
                    if(bean.getCID()==cid)
                    {
                        contest = bean;
                    }
                }
            }
            //更新气球的情况，并查询气球
            if(contest!=null)
            {
                BallonDAO.addBallon(ContestDAO.queryContestByCID(contest.getCID()));
                setBallonList(BallonDAO.queryBallon(contest));
            }

            return SUCCESS;
        }
        catch(Exception e)
        {
            this.addActionError("Show ballons error : "+e.getMessage());
            return ERROR;
        }
        
        
    }

    /***
     * 标记气球为已经发送
     * @return
     */
    public String send()
    {
        try
        {
            //标记请求的气球为已经发送
            BallonDAO.markSend(bid);
            return SUCCESS;
        }
        catch(Exception e)
        {
            addActionError(e.getMessage());
            return ERROR;
        }
        
    }

    /**
     * @return the ballonPass
     */
    public String getBallonPass()
    {
        return ballonPass;
    }

    /**
     * @param ballonPass the ballonPass to set
     */
    public void setBallonPass(String ballonPass)
    {
        this.ballonPass = ballonPass;
    }

    /**
     * @return the cid
     */
    public int getCid()
    {
        return cid;
    }

    /**
     * @param cid the cid to set
     */
    public void setCid(int cid)
    {
        this.cid = cid;
    }

    /**
     * @return the ballonList
     */
    public LinkedList<BallonBean> getBallonList()
    {
        return ballonList;
    }

    /**
     * @param ballonList the ballonList to set
     */
    public void setBallonList(LinkedList<BallonBean> ballonList)
    {
        this.ballonList = ballonList;
    }

    /**
     * @return the contestList
     */
    public LinkedList<ContestBean> getContestList()
    {
        return contestList;
    }

    /**
     * @param contestList the contestList to set
     */
    public void setContestList(LinkedList<ContestBean> contestList)
    {
        this.contestList = contestList;
    }

    /**
     * @return the bid
     */
    public int getBid()
    {
        return bid;
    }

    /**
     * @param bid the bid to set
     */
    public void setBid(int bid)
    {
        this.bid = bid;
    }
}
