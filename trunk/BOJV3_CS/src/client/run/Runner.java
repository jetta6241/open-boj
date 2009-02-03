package client.run;
import bean.RunBean;

/**
 * 提供运行时，校验答案的接口
 * @author liheyuan
 */
public interface Runner
{
    /***
     * 运行程序
     * @return 如果出现任何错误，返回false，全部正确返回true
     */
    public boolean runOnce(RunBean run);

}
