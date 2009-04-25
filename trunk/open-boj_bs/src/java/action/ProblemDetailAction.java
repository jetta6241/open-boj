package action;

import beans.*;
import com.opensymphony.xwork2.ActionSupport;

import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import dao.ProblemDAO;
import facade.ContestFacade;
/**
 * Show All the Problem in Table problem for certain Contest-ID
 * @author liheyuan
 */
@Validation()
public class ProblemDetailAction extends ActionSupport
{
    /**All the certain PROBLEM*/
    private ProblemBean problem = null;
    /**Problem ID for query*/
    private int pid;

    public String execute()
    {
        try
        {
            //检出所有的题目
            //筛选出不是Lock的题目
            setProblem(ProblemDAO.queryProblemByPID(getPid()));
            return SUCCESS;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            this.addActionError("Query Problem Fail : "+e.getMessage());
            return ERROR;
        }
    }


    /**
     * @return the problem
     */
    public ProblemBean getProblem() {
        return problem;
    }

    /**
     * @param problem the problem to set
     */
    public void setProblem(ProblemBean problem) {
        this.problem = problem;
    }

    /**
     * @return the pid
     */
    public int getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    @IntRangeFieldValidator(min = "1", max = "9999", message = "Invaild Problem ID")
    public void setPid(int pid) {
        this.pid = pid;
    }
}
