package config;

/**
 * 定义程序使用的各种常量
 * @author liheyuan
 */
public class Const
{
    /**判题结果：等待*/
    public static final String WAIT= "WAIT";
    /**判题结果：队列，待判*/
    public static final String QUEUE = "QUEUE";
    /**判题结果：通过*/
    public static final String AC = "AC";
    /**判题结果：答案错误*/
    public static final String WA = "WA";
    /**判题结果：超时*/
    public static final String TLE = "TLE";
    /**判题结果：超内存*/
    public static final String MLE = "MLE";
    /**判题结果：运行时错误*/
    public static final String PE = "PE";
    /**判题结果：运行时错误*/
    public static final String RE = "RE";
    /**判题结果：编译错误*/
    public static final String CE = "CE";

    /**语言类型，C语言*/
    public static final byte C = 1;
    /**语言类型，C++*/
    public static final byte CPP = 2;
    /**语言类型，Java*/
    public static final byte JAVA = 3;

    /**编译超时，毫秒*/
    public static int COMPILE_TIME = 0;
    /**临时存放源文件的位置*/
    public static String SRC_PATH = null;
    /**临时存放二进制文件的位置*/
    public static String BIN_PATH = null;

    /**异常记录的位置*/
    public static String EXCEPTION_PATH = null;

    /**gcc编译器的位置*/
    public static String GCC_PATH = null;
    /**g++编译器的位置*/
    public static String CPP_PATH = null;
    /**Java编译器*/
    public static String JAVA_PATH = null;

    /**测试数据文件夹*/
    public static String DATA_PATH = null;
    /**Judge内核*/
    public static String JUDGE_CORE = null;

    /**JAVA放宽限制倍数*/
    public static int JAVA_LIMIT = 3;
    /**Java虚拟机位置*/
    public static String JVM = null;

    /**运行内核的windows限制帐号的用户名*/
    public static String USER = null;
    /**运行内核的windows限制帐号的用户名*/
    public static String PASS =  null;
    /**Client的接受端口*/
    public static int CLIENT_RCV_PORT = 0;

    /**Server的IP*/
    public static String SERVER_IP = null;
    /**Server的端口*/
    public static int SERVER_RCV_PORT = 0;

    /**数据库服务器url*/
    public static String DB_URL = null;
    /**数据库用户名*/
    public static String DB_USER = null;
    /**数据库密码*/
    public static String DB_PASS = null;

    /**更新数据的FTP批处理*/
    public static String FTP_BAT = null;
}
