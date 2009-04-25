/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package config;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 读取Server相关的参数
 * @author liheyuan
 */
public class ServerConf
{
    Properties p = new Properties();

    /***
     *
     * @throws java.lang.Exception 读取参数出错
     */
    public ServerConf() throws Exception
    {
        //读取参数文件
        try
        {
            p.load(new FileInputStream("server_conf.ini"));
        } 
        catch (Exception ex)
        {
            System.out.println(ex);
        }

        //读取服务器的相关参数
        Const.SERVER_IP = p.getProperty("SERVER_IP");
        Const.SERVER_RCV_PORT = Integer.parseInt(p.getProperty("SERVER_RCV_PORT"));
        Const.DB_URL = p.getProperty("DB_URL");
        Const.DB_USER = p.getProperty("DB_USER");
        Const.DB_PASS = p.getProperty("DB_PASS");
        //异常文件夹位置
        Const.EXCEPTION_PATH = p.getProperty("EXCEPTION_PATH");
    }

}
