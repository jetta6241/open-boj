package tool;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 定义各种常量
 * @author liheyuan
 */
public class Const
{

    //比赛状态
    /**等待*/
    public static final String CONTEST_PEND = "Pending";
    /**运行*/
    public static final String CONTEST_RUN = "Running";
    /**结束*/
    public static final String CONTEST_END= "Ended";

    //管理密码
    /**管理员用户名*/
    public static  String ADMIN_USER = "root";
    /**管理员密码*/
    public static  String ADMIN_PASS = "6B8E49836C0C29251833227E3B0F7FB7F8DC67CE";
    /**气球程序的密码*/
    public static  String BALLON_PASS = "ballon1234";

    //各类Token，一般情况下不要改动
    /**用于ballon的身份验证标识*/
    public static final String TOKEN_BALLON = "ballon";
    /**用于Sesion的身份验证标识*/
    public static final String TOKEN_LOGIN = "login";

    //路径参数
    /**用于生成rank的目录*/
    public static  String RANK_PATH= "rank/";
    /**数据的存储目录*/
    public static  String DATA_PATH= "D:\\boj_v3\\data\\";
    /**题目的存储目录*/
    public static  String PROBLEM_PATH= "problems/";

    //动态配置
    /**是否允许注册*/
    public static  String CONFIG_ALLOW_REG = "YES";
    /**当前滚动条文字*/
    public static  String CONFIG_TOP_TEXT = "YES";
    /**首页文字*/
    public static  String CONFIG_INDEX_TEXT = "YES";
}
