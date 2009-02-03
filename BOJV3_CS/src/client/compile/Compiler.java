package client.compile;
import bean.RunBean;
import config.Const;
import java.io.File;

/**
 * 提供编译源代码的接口
 * @author liheyuan
 */
public abstract class Compiler
{
    /**运行bean*/
    RunBean run = null;
    /**编译源文件位置*/
    String srcFile;

    /***
     * 所有的子类继承本方法。调用之即可编译。
     * @param run 用于传入：包含需要编译run的源代码src字段。用于传出：将编译信息及相应错误写入该bean中。
     * @return
     */
    public boolean compile(RunBean r)
    {
        try
        {
            run = r;
            //生成编译目标BIN位置
            
            //生成编译源文件
            makeSrcFile();
            //目的二进制文件位置
            makeBinPath();
            
            //获得编译字符串
            String compStr = getCompileStr();

           //运行编译器进程
           CompilerWatcher watch = new CompilerWatcher(compStr);
           Thread t = new Thread(watch);
           t.start();

           //等待直到超时
           try
           {
               if(run.getLang()==Const.JAVA)
               {
                   //Java编译超时为普通的2倍
                   t.join(Const.COMPILE_TIME*2);
               }
               else
               {
                   t.join(Const.COMPILE_TIME);
               }
           }
           catch(Exception e){}

           //获得进程句柄并强制结束
           Process p = watch.getProcess();
           p.destroy();

           //获得返回值
           int ret = 0;

           try
           {
               ret = p.exitValue();
               if(ret!=0)
               {
                   throw new Exception();
               }
               else
               {
                   return true;
               }
           }//end try 获取返回值
           catch(Exception e)
           {
               //获取exitValue出现异常，或者进程返回值不等于0，都为编译错误。
                run.setComp(getCompileError(p));
                run.setResult(Const.CE);
                return false;
           }//end catch 获取返回值
       }//end try 最外层
        catch(Exception e)
        {
            e.printStackTrace();
            run.setResult(Const.CE);
            return false;
        }
        finally
        {
            //删除编译生成的临时SRC文件
            File f = new File(srcFile);
            f.delete();
        }
    }

    abstract protected void makeSrcFile();

    abstract protected void makeBinPath();

    protected abstract String getCompileStr();
    protected abstract StringBuffer getCompileError(Process p);

    private static C_Compiler c = new C_Compiler();
    private static Cpp_Compiler cpp = new Cpp_Compiler();
    private static Java_Compiler java = new Java_Compiler();

    /***
     * 根据bean中的语言类型调用对应的编译器
     * 调用编译，并返回相应结果。
     * @param run
     * @return
     */
    public static boolean autoCompile(RunBean run)
    {
        if(run.getLang()==Const.C)
        {
            return c.compile(run);
        }
        else if(run.getLang()==Const.CPP)
        {
            return cpp.compile(run);
        }
        else if(run.getLang()==Const.JAVA)
        {
            return java.compile(run);
        }
        else
        {
            //语言混乱，默认为C语言
            return c.compile(run);
        }
    }
}
