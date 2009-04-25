package interceptor;

import beans.UserBean;
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
public class BallonInterceptor extends AbstractInterceptor
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
        String bPass = (String) ActionContext.getContext().getSession().get(Const.TOKEN_BALLON);
        if(bPass==null || !Const.BALLON_PASS.equals(bPass))
        {
            return Action.LOGIN;
        }

        //登录成功
        return o.invoke();
    }

    
}
