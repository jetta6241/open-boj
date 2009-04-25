package action;
import beans.UserBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.UserDAO;
import tool.Const;

/**
 *
 * @author liheyuan
 */
public class UserAction extends ActionSupport
{
    /**用户，注册或者登录都算*/
    private UserBean user = null;
    /**注册用的验证第二次密码*/
    private String pass2 = null;

    /***
     * 注册的参数检查过滤
     * @return
     */
    public void validateReg()
    {
        //先判断是否能注册
        if(!Const.CONFIG_ALLOW_REG.equals("YES"))
        {
            //不允许注册
            this.addFieldError("user.uUser", "Sorry , reg is not allowed.");
        }
        if(user.getUUser()==null || !user.getUUser().matches("[a-zA-Z0-9_*]{4,30}"))
        {
            this.addFieldError("user.uUser", "User shoule be consisted of A-Z a-z 0-9 _ * , length : 4-30");
        }
        if(user.getUPass()==null || !user.getUPass().matches("[a-zA-Z0-9_*]{4,30}"))
        {
            this.addFieldError("user.uPass", "User shoule be consisted of A-Z a-z 0-9 _ * , length : 4-30");
        }
        if(user.getUPass()==null || !user.getUPass().equals(pass2))
        {
            this.addFieldError("pass2", "The pass1 is not equal with pass2");
        }
        if(user.getUSchool()!=null && user.getUSchool().matches("[;@#$]"))
        {
            this.addFieldError("school", "Nick should't contains ';@#$");
        }
        if(user.getUEmail()!=null && !user.getUEmail().isEmpty() && !user.getUEmail().matches("[^\\s@]+@[^\\s@]+"))
        {
            this.addFieldError("user.uEmail", "Not an legal Email-address");
        }
    }

    /***
     * 注册的Action处理
     * @return
     */
    public String reg()
    {
        try
        {
            UserDAO.addUser(user);
            return SUCCESS;
        }
        catch(Exception e)
        {
            this.addActionError("Reg fail : "+e.getMessage());
            return ERROR;
        }
    }

    /***
     * 验证编辑用户的信息
     */
    public void validateEdit()
    {
        if(user.getUPass()!=null && !user.getUPass().isEmpty() && !user.getUPass().matches("[a-zA-Z0-9_*]{4,30}"))
        {
            this.addFieldError("user.uPass", "Old Pass shoule be consisted of A-Z a-z 0-9 _ * , length : 4-30");
        }
        if(pass2!=null && !pass2.isEmpty() && !pass2.matches("[a-zA-Z0-9_*]{4,30}"))
        {
            this.addFieldError("pass2", "New Pass shoule be consisted of A-Z a-z 0-9 _ * , length : 4-30");
        }
        if(user.getUSchool().matches("[;@#$]"))
        {
            this.addFieldError("school", "Nick should't contains ';@#$");
        }
        if(!user.getUEmail().isEmpty() && !user.getUEmail().matches("[^\\s@]+@[^\\s@]+"))
        {
            this.addFieldError("user.uEmail", "Not an legal Email-address");
        }
    }

    /***
     * 编辑用户
     * @return
     */
    public String edit()
    {
        try
        {
            UserBean uOld = (UserBean)ActionContext.getContext().getSession().get("login");
            uOld.setUPass(pass2);
            UserDAO.editUser(user, uOld);
            user = UserDAO.queryUser(uOld.getUID());
            ActionContext.getContext().getSession().put("login", user);
            this.addActionMessage("Update profile success.");
            return SUCCESS;
        }
        catch(Exception e)
        {
            this.addActionError("Edit Profile fail : "+e.getMessage());
            return ERROR;
        }
    }

    /***
     * 检查登录的字段是否合法
     */
    public void validateLogin()
    {
        if(!user.getUUser().matches("[a-zA-Z0-9_*]{4,30}"))
        {
            this.addFieldError("user.uUser", "User shoule be consisted of A-Z a-z 0-9 _ * , length : 4-30");
        }
        if(!user.getUPass().matches("[a-zA-Z0-9_*]{4,30}"))
        {
            this.addFieldError("user.uPass", "User shoule be consisted of A-Z a-z 0-9 _ * , length : 4-30");
        }
    }
    
    /***
     * 登录校验
     * @return
     */
    public String login()
    {
        try
        {
            UserBean u = UserDAO.chkLogin(user);
            //登录失败
            if(u==null)
            {
                addActionError("Password or Username not correct");
                return ERROR;
            }
            //登录成功
            user = u;
            ActionContext.getContext().getSession().put("login", user);
            return SUCCESS;
        }
        catch(Exception e)
        {
            this.addActionError("Sorry : "+e.getMessage());
            return ERROR;
        }
    }

    /***
     * 登出
     * @return
     */
    public String logout()
    {
        ActionContext.getContext().getSession().remove("login");
        return SUCCESS;
    }

    /**
     * @return the user
     */
    public UserBean getUser()
    {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserBean user)
    {
        this.user = user;
    }

    /**
     * @return the pass2
     */
    public String getPass2()
    {
        return pass2;
    }

    /**
     * @param pass2 the pass2 to set
     */
    public void setPass2(String pass2)
    {
        this.pass2 = pass2;
    }


    
}
