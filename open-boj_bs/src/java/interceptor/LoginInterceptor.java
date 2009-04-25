package interceptor;

import beans.UserBean;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import tool.Tools;

/**
 *
 * @author liheyuan
 */
public class LoginInterceptor extends AbstractInterceptor
{
    /**标准密码*/
    private String pass0;
    
    /***
     * 拦截登录
     * @param arg0
     * @return
     * @throws java.lang.Exception
     */
    public String intercept(ActionInvocation o) throws Exception
    {
        //之前没有登录
        UserBean user = (UserBean) ActionContext.getContext().getSession().get("login");
        if(user==null)
        {
            return Action.LOGIN;
        }
        //登录成功
        return o.invoke();
    }

    /**
     * @return the pass0
     */
    public String getPass0()
    {
        return pass0;
    }

    /**
     * @param pass0 the pass0 to set
     */
    public void setPass0(String pass0)
    {
        this.pass0 = pass0;
    }
    
}
