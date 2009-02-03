package server.db;

import bean.RunBean;
import config.Const;
import java.sql.*;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;


/**
 * 从数据路中读取数据
 * @author liheyuan
 */
public class DBService implements Runnable
{
    /**待判题目*/
    private final LinkedList<RunBean> unjudge_queue = new LinkedList();
    /**已判题目*/
    private final LinkedList<RunBean> judged_queue = new LinkedList();
    /**数据库连接Conn*/
    private Connection conn = null;
    /**数据库pre stmt*/
    private PreparedStatement pstmt_read = null;
    /**数据库pre stmt*/
    private PreparedStatement pstmt_write = null;
    /**数据库pre stmt*/
    private PreparedStatement pstmt_update = null;
    
    public DBService()
    {
        conn = MySQLDB.getConn();
        try
        {
            if(conn!=null)
            {
                try
                {
                    pstmt_read = conn.prepareStatement("select R_ID,run.P_ID,R_SRC,R_RESULT,R_LANG,R_SUB_TIME,P_TLE,P_MLE,P_SPECIAL from run,problem where R_RESULT='WAIT' AND run.P_ID=problem.P_ID order by R_ID limit 0,20", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                    pstmt_update = conn.prepareStatement("update run set R_RESULT=? where R_ID=?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                    pstmt_write = conn.prepareStatement("update run set R_RESULT=?,R_COMP=?,R_TIME=?,R_MEM=?,R_JUDGE=? where R_ID=?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
                
            }
            else
            {
                //出错处理
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void run()
    {
        while(true)
        {
            try
            {
                //读取待判题目
                read();
                //写回已判题目
                write();
                //休眠线程
                TimeUnit.MILLISECONDS.sleep(400);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /***
     * 从数据库中读取待判题目
     */
    public void read()
    {
        try
        {
            ResultSet rs = pstmt_read.executeQuery();
            while(rs.next())
            {
                //读取一个待判题目
                RunBean run = new RunBean();
                String str = rs.getString("R_LANG");
                byte lang = 0;
                if(str.equals("C++"))
                {
                    lang = Const.CPP;
                }
                else if(str.equals("Java"))
                {
                    lang = Const.JAVA;
                }
                else
                {
                    lang = Const.C;
                }
                run.setLang(lang);
                run.setM_LIMIT(rs.getLong("P_MLE"));
                run.setRid(rs.getInt("R_ID"));
                run.setPid(rs.getInt("P_ID"));
                run.setSpecial(rs.getString("P_SPECIAL").equals("YES"));
                run.setSrc(rs.getString("R_SRC"));
                run.setT_LIMIT(rs.getLong("P_TLE"));
                run.setSub_time(rs.getString("R_SUB_TIME"));
                //写入到队列
                synchronized(unjudge_queue)
                {
                    unjudge_queue.add(run);
                    System.out.println("读取一个待判题目");
                }
                pstmt_update.setString(1, "QUEUE");
                pstmt_update.setInt(2, run.getRid());
                pstmt_update.execute();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /***
     * 把判完的题目写回数据库
     */
    public void write()
    {
        //update run set R_RESULT=?,R_COMP=?,R_TIME=?,R_MEM=?,R_JUDGE=? where R_ID=?
        synchronized(judged_queue)
        {
            while(!judged_queue.isEmpty())
            {
                RunBean run = judged_queue.removeFirst();
                try
                {
                    pstmt_write.setString(1, run.getResult());
                    pstmt_write.setString(2, run.getComp().toString());
                    pstmt_write.setLong(3, run.getTime());
                    pstmt_write.setLong(4, run.getMem());
                    pstmt_write.setString(5, run.getJudge().toString());
                    pstmt_write.setInt(6, run.getRid());
                    pstmt_write.executeUpdate();
                } 
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    /***
     * 关闭，释放所有的数据库资源
     */
    public void close()
    {
        try
        {
            pstmt_read.close();
            pstmt_write.close();
            conn.close();
        }
        catch(Exception e){}
    }

    public RunBean getUnjudgeFirst()
    {
        synchronized(unjudge_queue)
        {
            if(unjudge_queue.isEmpty())
            {
                return null;
            }
            else
            {
                return unjudge_queue.getFirst();
            }
        }
    }

    public void removeUnjudgeFirst()
    {
        synchronized(unjudge_queue)
        {
            unjudge_queue.removeFirst();
        }
    }
    
    public void addJudged(RunBean b)
    {
        synchronized(judged_queue)
        {
            judged_queue.add(b);
        }
    }

}
