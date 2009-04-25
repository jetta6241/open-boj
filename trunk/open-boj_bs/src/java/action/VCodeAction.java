package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.*;
import tool.RandomNumUtil;

/**
 *
 * @author liheyuan
 */
public class VCodeAction extends ActionSupport
{
    /**Stream for Image*/
    private ByteArrayInputStream inputStream;

    /***
     * Generate Image
     * @return
     * @throws java.lang.Exception
     */
    public String generate() throws Exception
    {
        RandomNumUtil rdnu = RandomNumUtil.Instance();
        this.setInputStream(rdnu.getImage());//取得带有随机字符串的图片
        ActionContext.getContext().getSession().put("code", rdnu.getString());//取得随机字符串放入HttpSession
        return SUCCESS;
    }

    public void setInputStream(ByteArrayInputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    public ByteArrayInputStream getInputStream()
    {
        return inputStream;
    }
}
