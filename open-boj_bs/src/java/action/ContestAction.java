package action;

import java.util.LinkedList;
import beans.*;
import com.opensymphony.xwork2.ActionSupport;
import dao.ContestDAO;
import facade.ContestFacade;
import facade.ProblemFacade;
import tool.Const;
/**
 * Show All the Contest in DB table CONTEST
 * @author liheyuan
 */
public class ContestAction extends ActionSupport
{
    /**All the contest Information*/
    private LinkedList<ContestBean> list = new LinkedList();
    /**比赛的ID*/
    private int cid;
    /**All the PROBLEM Information*/
    private LinkedList<ProblemBean> listProblem = new LinkedList();
    /**竞赛的信息*/
    private ContestBean contest = null;

    /***
     * 显示某个Contest
     * @return
     */
    public String show()
    {
        try
        {
            //查询比赛信息
            contest = ContestFacade.getContest(getCid());
            //如果比赛已经开始才查询题目
            if(!contest.getCStatus().equals(Const.CONTEST_PEND))
            {
                listProblem = ProblemFacade.listProblem(contest);
            }
            return SUCCESS;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            this.addActionError("Show Contest Details fail : "+e.getMessage());
            return ERROR;
        }
    }

    /**
     * 显示所有的竞赛
     * @return
     */
    public String list()
    {
        //Query Through DAO
        try
        {
            this.setList(ContestFacade.listContest());
            return SUCCESS;
        }
        catch(Exception e)
        {
            this.addActionError("Query Contest Fail : "+e.getMessage());
            return ERROR;
        }
    }

    /**
     * @return the list
     */
    public LinkedList<ContestBean> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(LinkedList<ContestBean> list) {
        this.list = list;
    }

    /**
     * @return the listProblem
     */
    public LinkedList<ProblemBean> getListProblem()
    {
        return listProblem;
    }

    /**
     * @param listProblem the listProblem to set
     */
    public void setListProblem(LinkedList<ProblemBean> listProblem)
    {
        this.listProblem = listProblem;
    }

    /**
     * @return the contest
     */
    public ContestBean getContest()
    {
        return contest;
    }

    /**
     * @param contest the contest to set
     */
    public void setContest(ContestBean contest)
    {
        this.contest = contest;
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
    
}
