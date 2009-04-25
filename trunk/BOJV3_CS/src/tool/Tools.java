/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tool;

import config.Const;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JWindow;

/**
 *
 * @author liheyuan
 */
public class Tools
{
    public static ImageIcon getIcon(URL i)
    {
        return new ImageIcon(i);
    }

    //返回现在的时间
    public static String getTime()
    {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = cal.getTime();
        return format.format(d);
    }

    //返回现在的日期
    public static String getDate()
    {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        SimpleDateFormat  format = new SimpleDateFormat("yyyyMMdd");
        Date d = cal.getTime();
        return format.format(d);
    }

    //返回窗口中心
    public static void setCenter(Window com)
    {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        com.setLocation((int)(screen.getWidth()-com.getWidth())/2, (int)(screen.getHeight()-com.getHeight())/2);
    }

    public static void logDebug(String str)
    {
        try
        {
            File f = new File(getDate()+"_debug.txt");
            FileWriter writer = new FileWriter(f,true);
            writer.write(str+"\r\n");
            writer.close();
        }
        catch(Exception e)
        {
            
        }

    }

    /**记录异常*/
    public static void logException(Exception e)
    {
        PrintWriter writer = null;
        try
        {
            //写文件
            writer = new PrintWriter(new FileWriter(Const.EXCEPTION_PATH+Tools.getDate()+".log",true));
            //写入异常
            writer.write("时间："+Tools.getTime()+"\r\n");
            writer.write("异常标题："+e.getMessage()+"\r\n");
            writer.write("异常详情：\r\n");
            e.printStackTrace(writer);
            writer.write("\r\n\r\n\r\n");
            writer.flush();
        }
        catch(Exception ee)
        {
            e.printStackTrace();
        }
        finally
        {
            writer.close();
        }
    }
}
