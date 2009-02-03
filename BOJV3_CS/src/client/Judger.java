package client;
import bean.RunBean;

/**
 * 提供判题的接口，返回相应的结果。
 * @author liheyuan
 */
public interface Judger
{
    /***
     * @param run 传入参数：包含待判run所需的全部信息。并将相应的判题结果写回这个bean
     *
     */
    public void judge(RunBean run);
}
