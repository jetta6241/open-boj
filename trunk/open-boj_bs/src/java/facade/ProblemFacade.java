/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import beans.ContestBean;
import beans.ProblemBean;
import dao.ProblemDAO;
import java.util.LinkedList;

/**
 *
 * @author liheyuan
 */
public class ProblemFacade
{
    /***
     * 显示某个竞赛的题目
     * @param cid
     * @return
     */
    public static LinkedList<ProblemBean> listProblem(ContestBean bean) throws Exception
    {
        //竞赛开始才能查询
        if(!bean.getCStatus().equals("Pending"))
        {
            return ProblemDAO.queryBasicByCID(bean.getCID());
        }
        else
        {
            return null;
        }
    }
}
