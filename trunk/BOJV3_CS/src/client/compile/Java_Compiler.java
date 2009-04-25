package client.compile;

import config.Const;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author liheyuan
 */
public class Java_Compiler extends Compiler
{
    @Override
    protected String getCompileStr()
    {
        StringBuilder ss = new StringBuilder();

        ss.append(Const.JAVA_PATH);
        ss.append(" \""+ srcFile +"\" " );
        ss.append(" -d ");
        ss.append(Const.BIN_PATH);

        return ss.toString();
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
            srcFile = "Main.java";
            sb.append(srcFile);

            //把run bean中的源代码写入源文件
            fout = new FileWriter(sb.toString());
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
        sb.append("Main");
        run.setBin(sb.toString());
    }

}
