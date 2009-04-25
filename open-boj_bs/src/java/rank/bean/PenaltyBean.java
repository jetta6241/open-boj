package rank.bean;

public class PenaltyBean {
	/** Problem ID */
	private int pID;
	/** Accept Time */
	private long acTime;
	/** Reject Times */
	private int reject;
	
	public int getPID() {
		return pID;
	}
	public void setPID(int pid) {
		pID = pid;
	}
	public long getAcTime() {
		return acTime;
	}
	public void setAcTime(long acTime) {
		this.acTime = acTime;
	}
	public int getReject() {
		return reject;
	}
	public void setReject(int reject) {
		this.reject = reject;
	}
	public void addReject() {
		++this.reject;
	}
}
