package beans;

import tool.Const;

/**
 * Record a Contest Info in this Bean
 * @author liheyuan
 */
public class ContestBean
{
    /**Contest ID*/
    private int cID;
    /**Contest Title*/
    private String cTitle;
    /**Author of Contest*/
    private String cAuthor;
    /**Start time*/
    private String cStart;
    /**End time*/
    private String cEnd;
    /**End time*/
    private String cStatus=PENDING;
    /**State of Contest*/
    public static final String PENDING = "Pending";
    public static final String START = "Start";
    public static final String END = "End";

    /**
     * @return the cID
     */
    public int getCID() {
        return cID;
    }

    /**
     * @param cID the cID to set
     */
    public void setCID(int cID) {
        this.cID = cID;
    }

    /**
     * @return the cTitle
     */
    public String getCTitle() {
        return cTitle;
    }

    /**
     * @param cTitle the cTitle to set
     */
    public void setCTitle(String cTitle) {
        this.cTitle = cTitle;
    }

    /**
     * @return the cAuthor
     */
    public String getCAuthor() {
        return cAuthor;
    }

    /**
     * @param cAuthor the cAuthor to set
     */
    public void setCAuthor(String cAuthor) {
        this.cAuthor = cAuthor;
    }

    /**
     * @return the cStart
     */
    public String getCStart() {
        return cStart;
    }

    /**
     * @param cStart the cStart to set
     */
    public void setCStart(String cStart) {
        this.cStart = cStart;
    }

    /**
     * @return the cEnd
     */
    public String getCEnd() {
        return cEnd;
    }

    /**
     * @param cEnd the cEnd to set
     */
    public void setCEnd(String cEnd) {
        this.cEnd = cEnd;
    }

    /**
     * @return the cStatus
     */
    public String getCStatus() {
        return cStatus;
    }

    /**
     * @param cStatus the cStatus to set
     */
    public void setCStatus(String cStatus) {
        this.cStatus = cStatus;
    }

    /***
     * 比赛是否开始
     */
    public boolean isRun()
    {
        return Const.CONTEST_RUN.equals(cStatus);
    }

    /***
     * 比赛是否结束
     */
    public boolean isEnd()
    {
        return Const.CONTEST_END.equals(cStatus);
    }

    /***
     * 比赛是否没有开始
     */
    public boolean isPend()
    {
        return Const.CONTEST_PEND.equals(cStatus);
    }
}
