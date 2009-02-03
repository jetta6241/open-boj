package bean;

import config.Const;
import java.io.Serializable;

/***
 * Run表中一条运行记录的Bean
 * @author 李赫元
 * @version 0.4
 */
public class RunBean implements Serializable
{
    /**bean版本号，任意*/
    private static long serialVersionUID = 1104L;
    /**对应数据表RUN中的R_ID*/
    private int rid;
    /**对应数据表RUN中的P_ID*/
    private int pid;
    /**对应数据表RUN中的R_RESULT*/
    private String result = Const.QUEUE;
    /**对应数据表中的R_SRC*/
    private StringBuffer src = null;
    /**对应数据表中的R_COMP*/
    private StringBuffer comp = new StringBuffer(32);
    /**对应数据表中的R_TIME，运行时间ms*/
    private long time = 0;
    /**对应数据表中的R_MEM，运行占用内存KB*/
    private long mem = 0;
    /**对应数据表中的R_JUDGE，Judge的附加信息*/
    private StringBuffer judge = new StringBuffer(64);
    /**对应数据表中的R_LANG，Judge的编译类型*/
    private byte lang;
    /**对应数据表中的R_SUB_TIME*/
    private String sub_time = null;
    /**时间限制*/
    private long T_LIMIT = 0;
    /**内存限制*/
    private long M_LIMIT = 0;
    /**是否是SpecialJudge*/
    private boolean special = false;

    /**编译之后的可执行文件位置*/
    private String bin;
    /**标准数据输入文件*/
    private StringBuilder stdIN;
    /**标准数据输出文件*/
    private StringBuilder stdOUT;

    /**
     * @return the rid
     */
    public int getRid() {
        return rid;
    }

    /**
     * @param rid the rid to set
     */
    public void setRid(int rid) {
        this.rid = rid;
    }

    /**
     * @return the pid
     */
    public int getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(int pid) {
        this.pid = pid;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return the src
     */
    public StringBuffer getSrc() {
        return src;
    }

    /**
     * @param src the src to set
     */
    public void setSrc(String src)
    {
        if(src!=null)
        {
            this.src = new StringBuffer(src);
        }
        else
        {
            src = null;
        }
    }

    /**
     * @return the comp
     */
    public StringBuffer getComp()
    {
        return comp;
    }

    /**
     * @param comp the comp to set
     */
    public void setComp(StringBuffer c) 
    {
        if(comp==null)
        {
            comp = new StringBuffer(512);
        }
        else
        {
            comp.setLength(0);
        }
        
        comp.append(c);
    }

    /**
     * @return the time
     */
    public long getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * @return the mem
     */
    public long getMem() {
        return mem;
    }

    /**
     * @param mem the mem to set
     */
    public void setMem(long mem) {
        this.mem = mem;
    }

    /**
     * @return the judge
     */
    public StringBuffer getJudge() {
        return judge;
    }

    /**
     * @param judge the judge to set
     */
    public void setJudge(StringBuffer j) {
        judge.setLength(0);
        judge.append(j);
    }

    /**
     * @return the sub_time
     */
    public String getSub_time() {
        return sub_time;
    }

    /**
     * @param sub_time the sub_time to set
     */
    public void setSub_time(String sub_time) {
        this.sub_time = sub_time;
    }

    /**
     * @return the bin
     */
    public String getBin() {
        return bin;
    }

    /**
     * @param bin the bin to set
     */
    public void setBin(String bin) {
        this.bin = bin;
    }

    /**
     * @return the stdIN
     */
    public StringBuilder getStdIN() {
        return stdIN;
    }

    /**
     * @param stdIN the stdIN to set
     */
    public void setStdIN(StringBuilder stdIN) {
        this.stdIN = stdIN;
    }

    /**
     * @return the stdOUT
     */
    public StringBuilder getStdOUT() {
        return stdOUT;
    }

    /**
     * @param stdOUT the stdOUT to set
     */
    public void setStdOUT(StringBuilder stdOUT) {
        this.stdOUT = stdOUT;
    }

    /**
     * @return the lang
     */
    public byte getLang() {
        return lang;
    }

    /**
     * @param lang the lang to set
     */
    public void setLang(byte lang) {
        this.lang = lang;
    }

    /**
     * @return the T_LIMIT
     */
    public long getT_LIMIT() {
        return T_LIMIT;
    }

    /**
     * @param T_LIMIT the T_LIMIT to set
     */
    public void setT_LIMIT(long T_LIMIT) {
        this.T_LIMIT = T_LIMIT;
    }

    /**
     * @return the M_LIMIT
     */
    public long getM_LIMIT() {
        return M_LIMIT;
    }

    /**
     * @param M_LIMIT the M_LIMIT to set
     */
    public void setM_LIMIT(long M_LIMIT) {
        this.M_LIMIT = M_LIMIT;
    }

    /**
     * @return the special
     */
    public boolean isSpecial() {
        return special;
    }

    /**
     * @param special the special to set
     */
    public void setSpecial(boolean special) {
        this.special = special;
    }
}
