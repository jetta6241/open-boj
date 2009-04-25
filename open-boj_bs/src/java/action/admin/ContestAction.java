package action.admin;

import beans.ContestBean;
import beans.ProblemBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.ContestDAO;
import dao.ProblemDAO;
import java.util.LinkedList;
import org.apache.struts2.ServletActionContext;

/**
 * 比赛管理相关的Action
 * @author liheyuan
 */
public class ContestAction extends ActionSupport
{
    /**比赛的Bean*/
    private ContestBean contest= null;
    /**返回的信息*/
    private String returnMsg = null;
    /**所有比赛的Bean列表*/
    private LinkedList<ContestBean> contestList = new LinkedList();
    /**某个比赛的题目列表*/
    private LinkedList<ProblemBean> problemList = new LinkedList();
    /**待修改比赛的ID*/
    private int cid;
    /**待修改比赛的ID*/
    private String nextSymbol;
    /**添加题目的ID*/
    private int pid;

    /**显示修改的比赛信息*/
    public String beforeEdit()
    {
        try
        {
            contest = ContestDAO.queryContestByCID(cid);
            setProblemList(ProblemDAO.queryBasicByCIDAdmin(cid));
            //设置下一个建议的题目标号
            setNextSymbol("" + (char) (problemList.size()+'A'));
            //成功
            return SUCCESS;
        }
        catch(Exception e)
        {
            this.addActionError("Show edit contest fail : "+e.getMessage());
            return ERROR;
        }
    }
    
    /***
     * 保存编辑的比赛
     * @return
     */
    public String storeEdit()
    {
        try
        {
            contest.setCID(cid);
            ContestDAO.editContest(contest);
            returnMsg = "Store Edit contest success.";
            return SUCCESS;
        }
        catch(Exception e)
        {
            returnMsg = "Store edit contest fail : "+e.getMessage();
            return ERROR;
        }
    }

    /***
     * 存储向比赛中添加题目
     * @return
     */
    public String addProblem()
    {
        try
        {
            ContestDAO.addProblem(cid,getPid(),getNextSymbol());
            //成功
            returnMsg = "Add problem to contest success.";
            return SUCCESS;
        }
        catch(Exception e)
        {
            returnMsg = "Add problem to contest fail : "+e.getMessage();
            return ERROR;
        }
    }

    /***
     * 列出显示所有的比赛
     * @return
     */
    public String list() throws Exception
    {
        try
         {
             contestList = ContestDAO.queryAllContest();
             //返回给视图
             return SUCCESS;
         }
        catch(Exception e)
        {
            setReturnMsg("List contest fail : " + e.getMessage());
            return ERROR;
        }
        
    }

    /***
     * 存储添加的比赛
     * @return
     */
    public String storeAdd()
    {
        try
        {
            //已经通过Struts2注入了比赛的Bean
            //交给DAO处理新的Bean
            ContestDAO.addContest(contest);
            
            //成功
            setReturnMsg("Add contest success!");
            return SUCCESS;
        }
        catch(Exception e)
        {
            setReturnMsg("Add contest fail : " + e.getMessage());
            return ERROR;
        }
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
     * @return the returnMsg
     */
    public String getReturnMsg()
    {
        return returnMsg;
    }

    /**
     * @param returnMsg the returnMsg to set
     */
    public void setReturnMsg(String returnMsg)
    {
        this.returnMsg = returnMsg;
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
     * @return the problemList
     */
    public LinkedList<ProblemBean> getProblemList()
    {
        return problemList;
    }

    /**
     * @param problemList the problemList to set
     */
    public void setProblemList(LinkedList<ProblemBean> problemList)
    {
        this.problemList = problemList;
    }

    /**
     * @return the pid
     */
    public int getPid()
    {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(int pid)
    {
        this.pid = pid;
    }

    /**
     * @return the nextSymbol
     */
    public String getNextSymbol()
    {
        return nextSymbol;
    }

    /**
     * @param nextSymbol the nextSymbol to set
     */
    public void setNextSymbol(String nextSymbol)
    {
        this.nextSymbol = nextSymbol;
    }

    

}
