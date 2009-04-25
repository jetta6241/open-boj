package beans;

/**
 * Bean to Describe an Problem
 * @author liheyuan
 */
public class ProblemBean
{
    private int pID;
    private String pTitle;
    private long pTLE;
    private long pMLE;
    private String pFile;
    private int pSubmit;
    private int pAC;
    private boolean isSpj;
    private boolean isLock;
    private String symbol;

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
     * @return the pTitle
     */
    public String getPTitle() {
        return pTitle;
    }

    /**
     * @param pTitle the pTitle to set
     */
    public void setPTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    /**
     * @return the pTLE
     */
    public long getPTLE() {
        return pTLE;
    }

    /**
     * @param pTLE the pTLE to set
     */
    public void setPTLE(long pTLE) {
        this.pTLE = pTLE;
    }

    /**
     * @return the pMLE
     */
    public long getPMLE() {
        return pMLE;
    }

    /**
     * @param pMLE the pMLE to set
     */
    public void setPMLE(long pMLE) {
        this.pMLE = pMLE;
    }

    /**
     * @return the pFile
     */
    public String getPFile() {
        return pFile;
    }

    /**
     * @param pFile the pFile to set
     */
    public void setPFile(String pFile) {
        this.pFile = pFile;
    }

    /**
     * @return the pSubmit
     */
    public int getPSubmit() {
        return pSubmit;
    }

    /**
     * @param pSubmit the pSubmit to set
     */
    public void setPSubmit(int pSubmit) {
        this.pSubmit = pSubmit;
    }

    /**
     * @return the pAC
     */
    public int getPAC() {
        return pAC;
    }

    /**
     * @param pAC the pAC to set
     */
    public void setPAC(int pAC) {
        this.pAC = pAC;
    }

    /**
     * @return the isSpj
     */
    public boolean isIsSpj() {
        return isSpj;
    }

    /**
     * @param isSpj the isSpj to set
     */
    public void setIsSpj(String isSpj) {
        this.isSpj = isSpj.equals("YES");
    }

    /**
     * @return the isLock
     */
    public boolean isIsLock() {
        return isLock;
    }

    /**
     * @param isLock the isLock to set
     */
    public void setIsLock(String isLock) {
        this.isLock = isLock.equals("LOCK");
    }

    /**
     * @return the symbol
     */
    public String getSymbol()
    {
        return symbol;
    }

    /**
     * @param symbol the symbol to set
     */
    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }
}
