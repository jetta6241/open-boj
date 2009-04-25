package beans;

/**
 *
 * @author liheyuan
 */
public class BallonBean
{
    private String user = null;
    private int bID;
    private int uID;
    private int cID;
    private int pID;
    private boolean send;
    private String symbol;

    /**
     * @return the user
     */
    public String getUser()
    {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user)
    {
        this.user = user;
    }

    /**
     * @return the uID
     */
    public int getUID()
    {
        return uID;
    }

    /**
     * @param uID the uID to set
     */
    public void setUID(int uID)
    {
        this.uID = uID;
    }

    /**
     * @return the cID
     */
    public int getCID()
    {
        return cID;
    }

    /**
     * @param cID the cID to set
     */
    public void setCID(int cID)
    {
        this.cID = cID;
    }

    /**
     * @return the pID
     */
    public int getPID()
    {
        return pID;
    }

    /**
     * @param pID the pID to set
     */
    public void setPID(int pID)
    {
        this.pID = pID;
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

    /**
     * @return the send
     */
    public boolean isSend()
    {
        return send;
    }

    /**
     * @param send the send to set
     */
    public void setSend(boolean send)
    {
        this.send = send;
    }

    /**
     * @return the bID
     */
    public int getBID()
    {
        return bID;
    }

    /**
     * @param bID the bID to set
     */
    public void setBID(int bID)
    {
        this.bID = bID;
    }
}
