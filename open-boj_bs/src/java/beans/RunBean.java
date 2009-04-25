package beans;

/**
 * Bean for table Run
 * @author liheyuan
 */
public class RunBean
{
    /**Run ID*/
    private int rID;
    /**Problem ID*/
    private int pID;
    /**User ID*/
    private int uID;
    /**User Name*/
    private String user;
    /**Result*/
    private String rResult;
    /**Source Code*/
    private String rSrc;
    /**Run Compile Information*/
    private String rComp;
    /**Run use Time*/
    private long rTime;
    /**Run use Memory*/
    private long rMemory;
    /**Judge Information*/
    private long rJudge;
    /**Language*/
    private String rLang;
    /**Submit Time*/
    private String rSubtime;

    /**
     * @return the rID
     */
    public int getRID() {
        return rID;
    }

    /**
     * @param rID the rID to set
     */
    public void setRID(int rID) {
        this.rID = rID;
    }

    /**
     * @return the pID
     */
    public int getPID() {
        return pID;
    }

    /**
     * @param pID the pID to set
     */
    public void setPID(int pID) {
        this.pID = pID;
    }

    /**
     * @return the uID
     */
    public int getUID() {
        return uID;
    }

    /**
     * @param uID the uID to set
     */
    public void setUID(int uID) {
        this.uID = uID;
    }

    /**
     * @return the rResult
     */
    public String getRResult() {
        return rResult;
    }

    /**
     * @param rResult the rResult to set
     */
    public void setRResult(String rResult) {
        this.rResult = rResult;
    }

    /**
     * @return the rSrc
     */
    public String getRSrc() {
        return rSrc;
    }

    /**
     * @param rSrc the rSrc to set
     */
    public void setRSrc(String rSrc) {
        this.rSrc = rSrc;
    }

    /**
     * @return the rComp
     */
    public String getRComp() {
        return rComp;
    }

    /**
     * @param rComp the rComp to set
     */
    public void setRComp(String rComp) {
        this.rComp = rComp;
    }

    /**
     * @return the rTime
     */
    public long getRTime() {
        return rTime;
    }

    /**
     * @param rTime the rTime to set
     */
    public void setRTime(long rTime) {
        this.rTime = rTime;
    }

    /**
     * @return the rMemory
     */
    public long getRMemory() {
        return rMemory;
    }

    /**
     * @param rMemory the rMemory to set
     */
    public void setRMemory(long rMemory) {
        this.rMemory = rMemory;
    }

    /**
     * @return the rJudge
     */
    public long getRJudge() {
        return rJudge;
    }

    /**
     * @param rJudge the rJudge to set
     */
    public void setRJudge(long rJudge) {
        this.rJudge = rJudge;
    }

    /**
     * @return the rLang
     */
    public String getRLang() {
        return rLang;
    }

    /**
     * @param rLang the rLang to set
     */
    public void setRLang(String rLang) {
        this.rLang = rLang;
    }

    /**
     * @return the rSubtime
     */
    public String getRSubtime() {
        return rSubtime;
    }

    /**
     * @param rSubtime the rSubtime to set
     */
    public void setRSubtime(String rSubtime) {
        this.rSubtime = rSubtime;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }
}
