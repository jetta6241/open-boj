package config;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 读取Client相关的参数
 * @author liheyuan
 */
public class ClientConf
{
    Properties p = new Properties();

    /***
     *
     * @throws java.lang.Exception 读取参数出错
     */
    public ClientConf() throws Exception
    {
        //读取参数文件
        try
        {
            p.load(new FileInputStream("judge_conf.ini"));
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }

        //读取服务器的相关参数
        Const.SERVER_IP = p.getProperty("SERVER_IP");
        Const.SERVER_RCV_PORT = Integer.parseInt(p.getProperty("SERVER_RCV_PORT"));

        //Client参数设置
        Const.CLIENT_RCV_PORT = Integer.parseInt(p.getProperty("CLIENT_RCV_PORT"));

        //编译参数设置
        Const.COMPILE_TIME = Integer.parseInt(p.getProperty("COMPILE_TIME"));
        Const.GCC_PATH = p.getProperty("GCC_PATH");
        Const.CPP_PATH = p.getProperty("CPP_PATH");
        Const.JAVA_PATH = p.getProperty("JAVA_PATH");

        //目录参数设置
        Const.SRC_PATH = p.getProperty("SRC_PATH");
        Const.BIN_PATH = p.getProperty("BIN_PATH");
        Const.DATA_PATH = p.getProperty("DATA_PATH");

        //运行时参数设置
        Const.USER = p.getProperty("USER");
        Const.PASS = p.getProperty("PASS");
        Const.JUDGE_CORE = p.getProperty("JUDGE_CORE");
        Const.JVM = p.getProperty("JVM");
        Const.JAVA_LIMIT = Integer.parseInt(p.getProperty("JAVA_LIMIT"));

        //异常文件夹位置
        Const.EXCEPTION_PATH = p.getProperty("EXCEPTION_PATH");
    }

}
