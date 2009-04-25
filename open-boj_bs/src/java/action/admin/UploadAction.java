/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package action.admin;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import tool.Const;
import tool.Tools;

/**
 *
 * @author liheyuan
 */
public class UploadAction extends ActionSupport
{
    /**要上传的数据*/
    private File file = null;
    /**要上传的原始文件名*/
    private String fileFileName = null;
    /**要上传的MIME信息*/
    private String fileContentType = null;
    /**数据命名文件名*/
    private String filename = null;
    /**题目ID*/
    private int pid;

    /***
     * 上传数据文件
     * @return
     */
    public String uploadData()
    {
        try
        {
            //上传文件
            if(getFile()!=null)
            {
                Tools.copy(getFile(),new File(Const.DATA_PATH+getFilename()));
            }
            this.addActionMessage("Upload " +getFilename()+" success !");
            return SUCCESS;
        }
        catch(Exception e)
        {
            this.addActionError("Upload Data Error : "+e.getMessage());
            return ERROR;
        }
    }

    /***
     * 上传文件
     * @return
     */
    public String uploadPic()
    {
        try
        {
            //判断图片是否合法
            if(!fileContentType.contains("gif")
                    && !fileContentType.contains("jpg") 
                    && fileContentType.contains("png"))
            {
                this.addActionError("Picture format not support.");
                return ERROR;
            }
            File fnew = new File(Tools.getRootPath(ActionContext.getContext())+Const.PROBLEM_PATH+"img_"+Tools.getTimeLong()+".jpg");
            //上传图片文件到题目路径
            if(getFile()!=null)
            {
                Tools.copy(getFile(),fnew);
            }
            //将上传信息写入Session
            ActionContext.getContext().getSession().put("pic", fnew.getName());
            return SUCCESS;
        }
        catch(Exception e)
        {
            this.addActionError("Upload Fail : "+e.getMessage());
            return ERROR;
        }
    }

    /**
     * @return the file
     */
    public File getFile()
    {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file)
    {
        this.file = file;
    }

    /**
     * @return the filename
     */
    public String getFilename()
    {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    /**
     * @return the fileFileName
     */
    public String getFileFileName()
    {
        return fileFileName;
    }

    /**
     * @param fileFileName the fileFileName to set
     */
    public void setFileFileName(String fileFileName)
    {
        this.fileFileName = fileFileName;
    }


    /**
     * @return the fileContentType
     */
    public String getFileContentType()
    {
        return fileContentType;
    }

    /**
     * @param fileContentType the fileContentType to set
     */
    public void setFileContentType(String fileContentType)
    {
        this.fileContentType = fileContentType;
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
}
