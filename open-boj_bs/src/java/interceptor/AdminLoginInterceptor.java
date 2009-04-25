package interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import tool.Const;
import tool.Tools;

/**
 *
 * @author liheyuan
 */
public class AdminLoginInterceptor extends AbstractInterceptor
{
    
    /***
     * 拦截登录
     * @param arg0
     * @return
     * @throws java.lang.Exception
     */
    public String intercept(ActionInvocation o) throws Exception
    {
        //之前没有登录
        String pass = (String) ActionContext.getContext().getSession().get("admin");
        if(pass==null || !Const.ADMIN_PASS.equals(Tools.SHA_1(pass)))
        {
            return Action.LOGIN;
        }
        //登录成功
        return o.invoke();
    }
    
}
