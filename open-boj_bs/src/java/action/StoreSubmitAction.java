package action;

import beans.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.IntRangeFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import dao.RunDAO;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
/**
 * Show All the Problem in Table problem for certain Contest-ID
 * @author liheyuan
 */
@Validation()
public class StoreSubmitAction extends ActionSupport
{
    /**Problem ID*/
    private int pid;
    /**Language*/
    private String lang;
    /**All the certain PROBLEM*/
    private String src = null;
    /**Store Error Message*/
    private String error = "";
    /**User*/
    private UserBean user;
    /***/
    private String vcode = "";
    /**Request*/

    /***
     * Validate fro the v-code
     */
    public void validate()
    {
        //Check for the code
        String code=(String)(ActionContext.getContext().getSession().get("code"));
        if(!code.equals(vcode))
        {
            this.addFieldError("vcode", "Wrong Random Code.Please enter the code below.");
        }
        //Check for Language
        if(!(lang.equals("C") || lang.equals("C++") || lang.equals("Java")))
        {
            this.addFieldError("lang", "Wrong language,please don't attack the system.");
        }
    }

    /***
     * Store Problem to Database
     * @return
     */
    public String execute()
    {
        try
        {
            user = (UserBean) ActionContext.getContext().getSession().get("login");
            if(user==null)
            {
                return LOGIN;
            }
            //Submit&Store Problem Through DAO
            RunDAO.addNewRun(pid, user.getUID(), lang, src);
            return SUCCESS;
        }
        catch(Exception e)
        {
            //e.printStackTrace();
            this.setError("Submit Run Fail : "+e.getMessage());
            return ERROR;
        }
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(String error) {
        this.error = error;
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

    /**
     * @return the lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * @param lang the lang to set
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * @return the src
     */
    public String getSrc() {
        return src;
    }

    /**
     * @param src the src to set
     */
    public void setSrc(String src) {
        this.src = src+"\r\n";
    }

    /**
     * @return the vcode
     */
    public String getVcode()
    {
        return vcode;
    }

    /**
     * @param vcode the vcode to set
     */
    public void setVcode(String vcode)
    {
        this.vcode = vcode;
    }
}
