/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.db;

import bean.RunBean;
import config.Const;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import server.ServerController;
import tool.Tools;

/**
 *
 * @author liheyuan
 */
public class RunDAO
{
    /**数据库连接*/
    Connection conn = null;
    /**数据库pre stmt*/
    private PreparedStatement pstmt_read = null;
    /**数据库pre stmt*/
    private PreparedStatement pstmt_write = null;
    /**数据库pre stmt*/
    private PreparedStatement pstmt_update = null;

    /**控制器 */
    ServerController controller = null;
    /***
     * 初始化Connection
     */
    public RunDAO(ServerController con) throws Exception
    {
        controller = con;
        init();
    }

    /***
     * 初始化数据库访问资源
     */
    public void init() throws Exception
    {
        conn = MySQLDB.getConn();
        pstmt_read = conn.prepareStatement("select R_ID,run.P_ID,R_SRC,R_RESULT,R_LANG,R_SUB_TIME,P_TLE,P_MLE,P_SPECIAL from run,problem where R_RESULT='WAIT' AND run.P_ID=problem.P_ID order by R_ID limit 0,20", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        pstmt_update = conn.prepareStatement("update run set R_RESULT=? where R_ID=?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        pstmt_write = conn.prepareStatement("update run set R_RESULT=?,R_COMP=?,R_TIME=?,R_MEM=?,R_JUDGE_TIME=? where R_ID=?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
    }

    /***
     * 获得没有Judge的数据
     * @return
     * @throws java.lang.Exception
     */
    public LinkedList<RunBean> getUnjuged() throws Exception
    {
        ResultSet rs = pstmt_read.executeQuery();
        LinkedList list = new LinkedList();
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
            list.add(run);
            pstmt_update.setString(1, "QUEUE");
            pstmt_update.setInt(2, run.getRid());
            pstmt_update.execute();
        }
        rs.close();
        return list;
    }

    /***
     * 将Judged的结果回写
     * @param list
     */
    public void updateJudged(LinkedList<RunBean> list) throws Exception
    {
        try
        {
            while(!list.isEmpty())
            {
                RunBean run = list.removeFirst();

                pstmt_write.setString(1, run.getResult());
                pstmt_write.setString(2, run.getComp().toString());
                pstmt_write.setLong(3, run.getTime());
                pstmt_write.setLong(4, run.getMem());
                pstmt_write.setString(5, Tools.getTime());
                pstmt_write.setInt(6, run.getRid());
                pstmt_write.executeUpdate();
            }
        }
        catch(Exception e)
        {
            controller.handle(ServerController.EVENT_EXCEPTION, new Exception("回写Judge结果错误",e));
        }
    }

    //Rejudge所有的题目
    public void rejudgeAll() throws Exception
    {
        PreparedStatement pstmt = conn.prepareStatement("update run set R_RESULT='WAIT'");
        pstmt.executeUpdate();
    }

    //根据PIDRejudge所有的题目
    public void rejudgeByPID(int pid) throws Exception
    {
        PreparedStatement pstmt = conn.prepareStatement("update run set R_RESULT='WAIT' where p_id=?");
        pstmt.setInt(1, pid);
        pstmt.executeUpdate();
        pstmt.close();
    }

    //根据UIDRejudge所有的题目
    public void rejudgeByUID(int uid) throws Exception
    {
        PreparedStatement pstmt = conn.prepareStatement("update run set R_RESULT='WAIT' where u_id=?");
        pstmt.setInt(1, uid);
        pstmt.executeUpdate();
        pstmt.close();
    }

    //清除所有等待返回结果的即R_RESULT='QUEUE'的
    public void clearQueue() throws SQLException
    {
        Statement stmt = conn.createStatement();
        stmt.execute("update run set R_RESULT='WAITE' where R_RESULT = 'QUEUE'");
    }
}
