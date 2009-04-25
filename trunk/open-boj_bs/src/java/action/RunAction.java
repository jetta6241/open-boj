package action;

import beans.RunBean;
import com.opensymphony.xwork2.ActionSupport;
import dao.RunDAO;
import java.util.LinkedList;

/**
 *
 * @author liheyuan
 */
public class RunAction extends ActionSupport
{

    /**
     * @return the compileError
     */
    public static String getCompileError()
    {
        return compileError;
    }

    /**
     * @param aCompileError the compileError to set
     */
    public static void setCompileError(String aCompileError)
    {
        compileError = aCompileError;
    }
    /**The list of RunBean*/
    private LinkedList<RunBean> list;
    /**Error Message*/
    private String error;
    /**Start Run_ID*/
    private int start;
    /**PER_PAGE*/
    private static final int PER_PAGE = 30;
    /**要显示的CompileError信息*/
    private static String compileError;

    /***
     * 列出
     * @return
     * @throws java.lang.Exception
     */
    public String list()throws Exception
    {
        try
        {
            LinkedList<RunBean> l = RunDAO.queryBasicByRunIDRange(getStart(),PER_PAGE);
            setList(l);
            return SUCCESS;
        }
        catch(Exception e)
        {
            setError("Get run list fail : "+e.getMessage());
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
     * @return the start
     */
    public int getStart()
    {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(int start)
    {
        this.start = start;
    }

    /**
     * @return the list
     */
    public LinkedList<RunBean> getList()
    {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(LinkedList<RunBean> list)
    {
        this.list = list;
    }

    

}
