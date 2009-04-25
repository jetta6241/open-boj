package action.admin;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import tool.Const;
import tool.Tools;

/**
 * 
 * @author liheyuan
 */
public class LoginAction extends ActionSupport
{
    /**用户名*/
    private String user = null;
    /**密码*/
    private String pass = null;
    /**验证码*/
    private String vcode = null;

    /***
     * 为验证动作做校验器
     */
    public void validateLogin()
    {
        //验证用户名合法
        if(!user.matches("[a-zA-Z0-9*_]{4,15}"))
        {
            this.addFieldError("user", "Username should be consist of A-Z,a-z,0-9,*,_ , length:4-15");
        }
        //验证密码
        if(!pass.matches("[a-zA-Z0-9*_]{4,15}"))
        {
            this.addFieldError("pass", "Password should be consist of A-Z,a-z,0-9,*,_ , length:4-15");
        }
        //校验码
        String code=(String)(ActionContext.getContext().getSession().get("code"));
        if(!code.equals(vcode))
        {
            this.addFieldError("vcode", "Please enter the right vcode");
        }
    }

    /***
     * 登录动作
     */
    public String login()
    {
        if(user.equals(Const.ADMIN_USER) && Const.ADMIN_PASS.equals(Tools.SHA_1(pass)))
        {
            //成功后写入Session
            ActionContext.getContext().getSession().put("admin", pass);
            return SUCCESS;
        }
        else
        {
            this.addActionError("User or Pass not correct.");
            return ERROR;
        }
    }

    /**
     * @return the user
     */
    public String getUser()
    {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user)
    {
        this.user = user;
    }

    /**
     * @return the pass
     */
    public String getPass()
    {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass)
    {
        this.pass = pass;
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
