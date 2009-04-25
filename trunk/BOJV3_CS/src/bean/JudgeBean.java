package bean;

/**
 * 保存各个Judge的信息，IP,端口，重试次数，判题数量等
 * @author liheyuan
 */
public class JudgeBean
{
    /**Judge的IP*/
    private String ip;
    /**Judge的端口*/
    private int port;
    /**Judge已判题目（收到）的数量*/
    private long judges = 0;
    /**Judge已收到题目的数量*/
    private long send = 0;
    /**Judge重试次数*/
    private int retry = 0;
    /**Judge最后活动时间 */
    private String time;
    
    /***
     * 增加重试计数
     */
    public void incRetry()
    {
        retry++;
    }

   /***
    * 增加Judge已判题计数
    */
    public void incJudges()
    {
        judges++;
    }

   /***
    * 增加Judge已发送计数
    */
    public void incSend()
    {
        send++;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the judges
     */
    public long getJudges() {
        return judges;
    }

    /**
     * @return the retry
     */
    public int getRetry() {
        return retry;
    }

    /**
     * @return the time
     */
    public String getTime()
    {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time)
    {
        this.time = time;
    }

    /**
     * @return the send
     */
    public long getSend()
    {
        return send;
    }

    /**
     * @param send the send to set
     */
    public void setSend(long send)
    {
        this.send = send;
    }

}
