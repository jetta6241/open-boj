package action.admin;

import com.opensymphony.xwork2.ActionSupport;
import dao.ConfigDAO;
import tool.Const;

/**
 *
 * @author liheyuan
 */
public class MessageAction extends ActionSupport
{
    private String topMsg = null;

    /***
     * 显示
     * @return
     */
    public String show()
    {
        setTopMsg(Const.CONFIG_TOP_TEXT);
        return SUCCESS;
    }

    /***
     * 发送信息
     * @return
     */
    public String post()
    {
        try
        {
            //更新设置
            ConfigDAO.updateMsg(getTopMsg());
            //重新加载设置
            ConfigDAO.loadConfig();
            return SUCCESS;
        }
        catch(Exception e)
        {
            this.addActionError("Sorry , update text fail : "+e.getMessage());
            return ERROR;
        }
    }

    /**
     * @return the topMsg
     */
    public String getTopMsg()
    {
        return topMsg;
    }

    /**
     * @param topMsg the topMsg to set
     */
    public void setTopMsg(String topMsg)
    {
        this.topMsg = topMsg;
    }

}
