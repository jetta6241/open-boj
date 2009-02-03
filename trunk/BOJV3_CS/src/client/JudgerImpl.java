package client;
import bean.RunBean;
import config.Const;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实现了Judge接口
 * @author liheyuan
 */
public class JudgerImpl implements Judger
{
    /**RunBean，Bean存储着所有Judge所需要的参数*/
    RunBean run = null;
    /**标准测试数据STDIN*/
    private String stdIN = null;
    /**标准测试数据STDOUT*/
    private String stdOUT = null;
    /**调用内核的命令行*/
    private String judge_cmd = null;
    
    /**类内部公用的StringBuilder*/
    private StringBuilder sb = new StringBuilder(512);
    
    /***
     * 提供给外部的Judge接口
     * 调用本方法即可完成Judge判题过程
     * 并将结果写回RunBean
     * 
     * @param run
     */
    public void judge(RunBean r)
    {
        run = r;
        if(client.compile.Compiler.autoCompile(run))
        {
            //编译成功，继续判题
            judge();
        }
        else
        {
            //编译失败，返回
            return ;
        }
    }

    /***
     * 判题的主流程，由judge(RunBean)调用。
     */
    private void judge()
    {
        //生成Judge所需要的各种额外参数
        makeJudgeArg();
        //判题
        Process p;
        try
        {
            p = Runtime.getRuntime().exec(judge_cmd);
            //获得结果
            getResult(p);
            p.waitFor();
            
            p.destroy();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        

        //清理
        clean();
    }

    private void getResult(Process p)
    {
        InputStream in = null;
        OutputStream out = null;
        Scanner scan = null;
        //尝试读取Judge结果
        try
        {
            in = p.getInputStream();
            out = p.getOutputStream();
            out.close();
            scan = new Scanner(in);
            sb.setLength(0);

            //读取Judge所有的输出
            while(scan.hasNextLine())
            {
                sb.append(scan.nextLine());
                sb.append("\n");
            }
            
            //根据正则表达式获得结果
            /*
             *匹配结果 ^Result:(\w+)$
             *匹配时间 ^Time:([0-9]*)ms
             *匹配内存 ^Memory:([0-9]*)KB$
             */

            //获取结果类型
            Pattern p1 = Pattern.compile("^Result:([a-zA-Z]+)$",Pattern.MULTILINE);
            Matcher m = p1.matcher(sb);
            
            if(m.find())
            {
                String res = m.group(1);
                if(res.equals(Const.WA))
                {
                    run.setResult(Const.WA);
                }
                else if(res.equals(Const.TLE))
                {
                    run.setResult(Const.TLE);
                }
                else if(res.equals(Const.MLE))
                {
                    run.setResult(Const.MLE);
                }
                else if(res.equals(Const.PE))
                {
                    run.setResult(Const.PE);
                }
                else if(res.equals(Const.AC))
                {
                    run.setResult(Const.AC);

                    //读取时间
                    Pattern p2 = Pattern.compile("^Time:([0-9]*)ms",Pattern.MULTILINE);
                    Matcher m2 = p2.matcher(sb);
                    if(!m2.find())
                    {
                        //转入RE
                        throw new Exception();
                    }
                    else
                    {
                        run.setTime(Long.parseLong(m2.group(1)));
                    }

                    //读取内存
                    //读取时间
                    Pattern p3 = Pattern.compile("^Memory:([0-9]*)KB$",Pattern.MULTILINE);
                    Matcher m3 = p3.matcher(sb);
                    if(!m3.find())
                    {
                        //转入RE
                        throw new Exception();
                    }
                    else
                    {
                        run.setMem(Long.parseLong(m3.group(1)));
                    }
                }
                else
                {
                    //转入RE
                    throw new Exception();
                }
            }
            else
            {
                //没有显示结果，出错
                run.setResult(Const.RE);
            }
        }//end try 尝试读取Judge结果
        catch(Exception e)
        {
            run.setResult(Const.RE);
            return ;
        }//end catch 尝试读取Judge结果
        finally
        {
            try
            {
                in.close();
                scan.close();
            } 
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
            
        }//finally 尝试读取Judge结果
    }

    /***
     * 生成Judge所需要的各种额外参数
     */
    private void makeJudgeArg()
    {
        sb.setLength(0);
        sb.append(Const.DATA_PATH);
        sb.append(run.getPid());
        
        stdIN = sb.toString()+".in";
        
        //根据是否是SpecialJudge，决定是OUT文件，还是测试exe
        if(run.isSpecial())
        {
            stdOUT = sb.toString()+".exe";
        }
        else
        {
            stdOUT = sb.toString()+".out";
        }

        //设定内核、带测试程序，std数据，
        sb.setLength(0);
        sb.append(Const.JUDGE_CORE);
        sb.append(" -F ");
        sb.append(run.getBin());
        sb.append(" -I ");
        sb.append(stdIN);
        sb.append(" -O ");
        sb.append(stdOUT);       
        sb.append(" -u ");
        //设定windows限制帐号的用户名和密码
        sb.append(Const.USER);
        sb.append(" -p ");
        sb.append(Const.PASS);

        //根据special设定模式
        if(run.isSpecial())
        {
            sb.append(" -m special ");
        }
        else
        {
            sb.append(" -m normal");
        }
        
        //根据是否是Java决定是否放宽时间、内存限制
        if(run.getLang()==Const.JAVA)
        {
            //是Java，根据JAVA_LIMIT进行放宽
            sb.append(" -T ");
            sb.append(run.getT_LIMIT()*Const.JAVA_LIMIT);
            sb.append(" -M ");
            sb.append(run.getM_LIMIT()*Const.JAVA_LIMIT);
            //加虚拟机位置
            sb.append(" -j ");
            sb.append(Const.JVM);
        }
        else
        {
            //不是Java
            sb.append(" -T ");
            sb.append(run.getT_LIMIT());
            sb.append(" -M ");
            sb.append(run.getM_LIMIT());
        }

        this.judge_cmd = sb.toString();
    }

    private void clean()
    {
        if(run.getLang()==Const.JAVA)
        {
            run.setBin(run.getBin()+".class");
        }
        File f = new File(run.getBin());
        f.delete();
        run.setBin(null);
    }

}
