package tool;

import com.opensymphony.xwork2.ActionContext;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author liheyuan
 */
public class Tools
{
    private static SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /***
     * Get Time as 2009-4-1 04:04:04
     * @return the Formated time
     */
    public static String getTime()
    {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        Date d = cal.getTime();
        return format.format(d);
    }

        /***
     * Get Time as 2009-4-1 04:04:04
     * @return the Formated time
     */
    public static long getTimeLong()
    {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        Date d = cal.getTime();
        return d.getTime();
    }

    //返回SHA加密的数值
    public static String SHA_1(String str)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            return byte2hexString(md.digest(str.getBytes()));
        }
        catch(Exception e)
        {
            return "";
        }
    }

    //格式化byte代码为String
    public static String byte2hexString(byte[] bytes)
    {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++)
        {
            if (((int) bytes[i] & 0xff) < 0x10)
            {
                buf.append("0");
            }
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buf.toString().toUpperCase();
    }

    /***
     * 把日期转化成长整型的秒数
     * @return
     */
    public static long dateToLong(String d) throws Exception
    {
        Date date = format.parse(d);
        return date.getTime();
    }

    /***
     * 拷贝源文件
     * @param src
     * @param dst
     */
    public static void copy(File src, File dst) throws Exception
    {
        try
        {
            InputStream in = null;
            OutputStream out = null;
            try
            {
                System.out.println(src.getAbsoluteFile());
                System.out.println(dst.getAbsoluteFile());
                in = new BufferedInputStream(new FileInputStream(src), 1024);
                out = new BufferedOutputStream(new FileOutputStream(dst), 1024);
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len=in.read(buffer))!=-1)
                {
                    out.write(buffer,0,len);
                }
            } finally
            {
                if (null != in)
                {
                    in.close();
                }
                if (null != out)
                {
                    out.close();
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new Exception("Copy file fail:"+e.getMessage());
        }
    }

    public static String getRootPath(ActionContext ac)
    {
        //获得html路径
        ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
        String path = sc.getRealPath("/");

        return path;
    }

}
