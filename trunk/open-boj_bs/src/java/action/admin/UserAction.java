package action.admin;

import beans.UserBean;
import com.opensymphony.xwork2.ActionSupport;
import dao.UserDAO;
import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author liheyuan
 */
public class UserAction extends ActionSupport
{
    private File user;

    /**
     * 
     */
    public LinkedList<UserBean> parseUsers() throws Exception
    {
        FileInputStream in = new FileInputStream(user);
        Scanner scan = new Scanner(in);
        LinkedList<UserBean> list = new LinkedList();
        //逐行添加
        while(scan.hasNextLine())
        {
            UserBean u = new UserBean();
            String line = scan.nextLine();
            String [] tmp = line.split("[\\s]+");
            u.setUUser(tmp[0]);
            u.setUPass(tmp[1]);
            if(tmp.length>=3)
            {
                u.setUSchool(tmp[2]);
            }
            list.add(u);
        }
        return list;
    }
    
    /***
     * 添加用户
     * @return
     */
    public String add()
    {
        try
        {
            UserDAO.addUsers(parseUsers());

            return SUCCESS;
        }
        catch(Exception e)
        {
            this.addActionError("Add users error : "+e.getMessage());
            return ERROR;
        }
    }

    /**
     * @return the user
     */
    public File getUser()
    {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(File user)
    {
        this.user = user;
    }

}
