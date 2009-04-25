package beans;

import rank.bean.PenaltyBean;
import java.util.HashMap;
import java.util.Iterator;

public class RankBean implements Comparable<RankBean>
{
    /** User ID */
    private int uID;
    /** Problem Solved */
    private int solved;
    /** Problem Penalty pID->penalty */
    private HashMap<Integer, PenaltyBean> penalty;
    /** Total Penalty */
    private long total;

    /** ReCalculate Solved Problems And Total Penalty */
    public void reCalc()
    {
        solved = 0;
        total = 0;
        Iterator<Integer> iter = penalty.keySet().iterator();
        PenaltyBean pb;
        while (iter.hasNext())
        {
            pb = penalty.get(iter.next());
            if (pb != null && pb.getAcTime() != -1)
            {
                ++solved;
                total += pb.getAcTime() + pb.getReject() * 20;
            }
        }
    }

    /** Compare RankBean */
    public int compareTo(RankBean other)
    {
        if (solved > other.solved || (solved == other.solved && total < other.total))
        {
            return -1;
        }
        if (solved == other.solved && total == other.total)
        {
            return 0;
        }
        return 1;
    }

    /** isEqual */
    @Override
    public boolean equals(Object other)
    {
        if (uID == ((RankBean) other).uID)
        {
            return true;
        }
        return false;
    }

    public int getUID()
    {
        return uID;
    }

    public void setUID(int uid)
    {
        uID = uid;
    }



    public int getSolved()
    {
        return solved;
    }

    public void setSolved(int solved)
    {
        this.solved = solved;
    }

    public void addSolved()
    {
        ++this.solved;
    }

    public HashMap<Integer, PenaltyBean> getPenalty()
    {
        return penalty;
    }

    public void setPenalty(HashMap<Integer, PenaltyBean> penalty)
    {
        this.penalty = penalty;
    }

    public long getTotal()
    {
        return total;
    }

    public void setTotal(long total)
    {
        this.total = total;
    }

    public void addTotal(long t)
    {
        this.total += t;
    }
}
