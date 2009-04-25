package action.admin;
import beans.ProblemBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.ProblemDAO;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import tool.Const;
import tool.Tools;

/**
 *
 * @author liheyuan
 */
public class ProblemAction extends ActionSupport
{
    /**返回的信息*/
    private String returnMsg =null;
    /**题目的标题*/
    private String title = null;
    /**时间限制*/
    private long tle;
    /**内存限制*/
    private long mle;
    /**题目描述*/
    private String description= null;
    /**题目的输入*/
    private String input = null;
    /**题目的输出*/
    private String output = null;
    /**样例输入*/
    private String sampleinput = null;
    /**样例输出*/
    private String sampleoutput = null;
    /**题目来源*/
    private String source = null;
    /**题目的提示*/
    private String hint = null;
    /**题目是否可见*/
    private boolean lock = false;

    /**题目的输入数据*/
    private File datain = null;
    /**题目的输出数据*/
    private File dataout = null;

    //下面为显示题目列表
    private LinkedList<ProblemBean> listProblem = null;

    //编辑题目用
    /**题目的ID*/
    private int pid;
    /**题面HTML文件*/
    private String html;

    /***
     * 生成题目详细信息所需要的XML文件
     */
    public void generateXML(String filename) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        //XML头以及样式表引用
        //描述
        sb.append("<h3>Description</h3>\r\n");
        sb.append("<p>"+description+"</p>\r\n");

        //输入
        sb.append("<h3>Input</h3>\r\n");
        sb.append("<p>"+input+"</p>\r\n");

        //输出
        sb.append("<h3>Output</h3>\r\n");
        sb.append("<p>"+output+"</p>\r\n");

        //样例输入
        sb.append("<h3>Sample Input</h3>\r\n");
        sb.append("<p>"+sampleinput+"</p>\r\n");

        //样例输出
        sb.append("<h3>Sample Output</h3>\r\n");
        sb.append("<p>"+sampleoutput+"</p>\r\n");

        //来源
        sb.append("<h3>Source</h3>\r\n");
        sb.append("<p>"+source+"</p>\r\n");

        //提示
        if(!hint.isEmpty())
        {
            sb.append("<h3>Hint</h3>\r\n");
            sb.append("<p>"+hint+"</p>\r\n");
        }
        //写入到文件
        File f = new File(filename);
        System.out.println(f.getAbsolutePath());
        OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(filename),"utf-8");
        fout.write(sb.toString());
        fout.close();
    }

    /***
     * 执行存储过程
     * @return
     */
    public String storeAdd()
    {
        try
        {
            //获得项目发布的绝对路径
            ActionContext ac = ActionContext.getContext();
            ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
            String path = sc.getRealPath("/");
            //生成文件，命名：SHA(当前的时间)，以保证无重复名称
            String filename = Const.PROBLEM_PATH+Tools.SHA_1(Tools.getTime())+".html";
            generateXML(path+filename);

            //调用DAO添加进数据库，并获得最新的这个题目的P_ID
            int pid = ProblemDAO.addProblem(title, tle,getMle(), false, lock,filename);
            
            //上传文件
            if(datain!=null)
            {
                Tools.copy(getDatain(),new File(Const.DATA_PATH+pid+".in"));
            }
            if(dataout!=null)
            {
                Tools.copy(getDataout(),new File(Const.DATA_PATH+pid+".out"));
            }

            //成功
            setReturnMsg("Add problem success !");
            return SUCCESS;
        }
        catch(Exception e)
        {
            setReturnMsg("Add problem fail : " + e.getMessage());
            return ERROR;
        }
    }

    /**
     * 列表显示出所有的题目
     * @return
     */
    public String list()
    {
        try
        {
            listProblem = ProblemDAO.queryBasicAll();
            return SUCCESS;
        }
        catch(Exception e)
        {
            this.addActionError("List Problem error : "+e.getMessage());
            return ERROR;
        }
    }

    /***
     * 编辑题目
     * @return
     */
    public String beforeEdit() throws Exception
    {
        FileInputStream fin = null;
        InputStreamReader reader = null;
        try
        {
            //获得要修改的信息
            ProblemBean p = ProblemDAO.queryProblemByPIDAdmin(getPid());
            title = p.getPTitle();
            tle = p.getPTLE();
            setMle(p.getPMLE());
            lock = p.isIsLock();
            //获得html路径
            ActionContext ac = ActionContext.getContext();
            ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
            String path = sc.getRealPath("/")+p.getPFile();
            fin = new FileInputStream(path);
            reader = new InputStreamReader(fin,"utf-8");
            //读取HTML
            char buf[] = new char[1024];
            int len = 0;
            StringBuilder sb = new StringBuilder();
            while((len=reader.read(buf))!=-1)
            {
                sb.append(buf,0,len);
            }
            html = sb.toString();
            return SUCCESS;
        }
        catch(Exception e)
        {
            this.addActionError("Show Problem fail : "+e.getMessage());
            return ERROR;
        }
        finally
        {
            try
            {
                fin.close();
            }
            catch(Exception e){}
            try
            {
                reader.close();
            }
            catch(Exception e){}
        }
    }

    /***
     * 存储编辑
     * @return
     */
    public String edit()
    {
        FileOutputStream fout = null;
        OutputStreamWriter writer = null;
        try
        {
            ProblemDAO.editProblem(pid, title, tle, mle, false, lock);
            ProblemBean p = ProblemDAO.queryProblemByPIDAdmin(pid);
            //获得html路径
            ActionContext ac = ActionContext.getContext();
            ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
            String path = sc.getRealPath("/")+p.getPFile();
            //更新HTML
            fout = new FileOutputStream(path);
            writer = new OutputStreamWriter(fout,"utf-8");
            writer.write(html);
            writer.flush();
            return SUCCESS;
        }
        catch(Exception e)
        {
            this.addActionError("Edit problem fail : "+e.getMessage());
            return ERROR;
        }
        finally
        {
            try
            {
                fout.close();
            }
            catch(Exception e){}
            try
            {
                writer.close();
            }
            catch(Exception e){}
        }
    }

    /**
     * @return the returnMsg
     */
    public String getReturnMsg()
    {
        return returnMsg;
    }

    /**
     * @param returnMsg the returnMsg to set
     */
    public void setReturnMsg(String returnMsg)
    {
        this.returnMsg = returnMsg;
    }

    /**
     * @return the tle
     */
    public long getTle()
    {
        return tle;
    }

    /**
     * @param tle the tle to set
     */
    public void setTle(long tle)
    {
        this.tle = tle;
    }

    /**
     * @return the mle
     */
    public long getMle()
    {
        return mle;
    }

    /**
     * @param mle the mle to set
     */
    public void setMle(long mle)
    {
        this.mle = mle;
    }


    /**
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return the input
     */
    public String getInput()
    {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(String input)
    {
        this.input = input;
    }

    /**
     * @return the output
     */
    public String getOutput()
    {
        return output;
    }

    /**
     * @param output the output to set
     */
    public void setOutput(String output)
    {
        this.output = output;
    }

    /**
     * @return the sampleinput
     */
    public String getSampleinput()
    {
        return sampleinput;
    }

    /**
     * @param sampleinput the sampleinput to set
     */
    public void setSampleinput(String sampleinput)
    {
        this.sampleinput = sampleinput;
    }

    /**
     * @return the sampleoutput
     */
    public String getSampleoutput()
    {
        return sampleoutput;
    }

    /**
     * @param sampleoutput the sampleoutput to set
     */
    public void setSampleoutput(String sampleoutput)
    {
        this.sampleoutput = sampleoutput;
    }

    /**
     * @return the source
     */
    public String getSource()
    {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source)
    {
        this.source = source;
    }

    /**
     * @return the hint
     */
    public String getHint()
    {
        return hint;
    }

    /**
     * @param hint the hint to set
     */
    public void setHint(String hint)
    {
        this.hint = hint;
    }

    /**
     * @return the lock
     */
    public boolean isLock()
    {
        return lock;
    }

    /**
     * @param lock the lock to set
     */
    public void setLock(boolean lock)
    {
        this.lock = lock;
    }

    /**
     * @return the datain
     */
    public /**题目的输入数据*/
    File getDatain()
    {
        return datain;
    }

    /**
     * @param datain the datain to set
     */
    public void setDatain(File datain)
    {
        this.datain = datain;
    }

    /**
     * @return the dataout
     */
    public /**题目的输出数据*/
    File getDataout()
    {
        return dataout;
    }

    /**
     * @param dataout the dataout to set
     */
    public void setDataout(File dataout)
    {
        this.dataout = dataout;
    }

    /**
     * @return the listProblem
     */
    public LinkedList<ProblemBean> getListProblem()
    {
        return listProblem;
    }

    /**
     * @param listProblem the listProblem to set
     */
    public void setListProblem(LinkedList<ProblemBean> listProblem)
    {
        this.listProblem = listProblem;
    }

    /**
     * @return the pid
     */
    public int getPid()
    {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(int pid)
    {
        this.pid = pid;
    }

    /**
     * @return the html
     */
    public String getHtml()
    {
        return html;
    }

    /**
     * @param html the html to set
     */
    public void setHtml(String html)
    {
        this.html = html;
    }
}
