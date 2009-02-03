package client.compile;

import bean.RunBean;
import config.Const;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author liheyuan
 */
public class C_Compiler extends Compiler
{
    @Override
    protected String getCompileStr()
    {
        StringBuilder ss = new StringBuilder();

        ss.append(Const.GCC_PATH);
        ss.append(" \""+ srcFile +"\" " );
        ss.append("-o \""+ run.getBin() +"\"");

        return ss.toString();
    }

    @Override
    protected StringBuffer getCompileError(Process p)
    {
        Scanner scan = new Scanner(p.getErrorStream());
        StringBuffer ss = new StringBuffer();

        //读取前10行的错误信息
        try
        {
            for(int i=0;scan.hasNextLine() && i<10;i++)
            {
                ss.append(scan.nextLine());
                ss.append("\n");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            try
            {
                p.getErrorStream().close();
                scan.close();
            }
            catch(Exception e){}
        }

        //过滤mingw系统路径
        Pattern pattern = Pattern.compile("^[a-zA-Z]:(\\\\[^\\\\/:\"<>\\|]+)+", Pattern.MULTILINE|Pattern.UNIX_LINES);
        StringBuffer ss1 = new StringBuffer(pattern.matcher(ss).replaceAll(""));
        return ss1;
    }

    @Override
    protected void makeSrcFile()
    {
        FileWriter fout = null;
        //try 写入文件
        try
        {
            //生成源文件位置
            StringBuilder sb = new StringBuilder(Const.SRC_PATH);
            sb.append(run.getRid());
            sb.append(".c");
            srcFile = sb.toString();

            //把run bean中的源代码写入源文件
            fout = new FileWriter(srcFile);
            fout.write(run.getSrc().toString());
        } 
        catch (IOException ex)
        {
            ex.printStackTrace();
        }//end try 读写文件
        finally
        {
            try
            {
                run.setSrc(null);
                fout.close();
            } 
            catch (IOException ex)
            {
                ex.printStackTrace();
            }//end try close句柄
        }//end finally 读写文件
    }

    @Override
    /***
     * 在run bean中设定编译生成的可执行文件的位置
     */
    protected void makeBinPath()
    {
        StringBuilder sb = new StringBuilder(Const.BIN_PATH);
        sb.append(run.getRid());
        sb.append(".exe");
        run.setBin(sb.toString());
    }

}
