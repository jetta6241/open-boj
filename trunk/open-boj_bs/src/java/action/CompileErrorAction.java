package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.RunDAO;

/**
 *
 * @author liheyuan
 */
public class CompileErrorAction extends ActionSupport
{
    private String error = null;
    private int rid = 0;

    /***
     * 控制显示编译错误
     * @return
     */
    public String show()
    {
        try
        {
            error = RunDAO.getCompileErr(getRid());
            return SUCCESS;
        }
        catch(Exception e)
        {
            this.addActionError("Fail to get compile error : "+e.getMessage());
            return ERROR;
        }
    }

    /**
     * @return the error
     */
    public String getError()
    {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error)
    {
        this.error = error;
    }

    /**
     * @return the rid
     */
    public int getRid()
    {
        return rid;
    }

    /**
     * @param rid the rid to set
     */
    public void setRid(int rid)
    {
        this.rid = rid;
    }

}
