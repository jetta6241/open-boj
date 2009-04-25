package tool;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author liheyuan
 */
public class MsgBox
{
    
    public static void showMsgBox(Component parent,String title,String msg)
    {
        JOptionPane.showMessageDialog(parent,msg , title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showWarnBox(Component parent,String title,String msg)
    {
        JOptionPane.showMessageDialog(parent,msg , title, JOptionPane.WARNING_MESSAGE);
    }

    public static boolean showYesNoBox(Component parent,String title,String msg)
    {
        int res;
        res = JOptionPane.showOptionDialog(parent,msg , title, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,new String[]{"是","否"},null);

        return res==0;
    }
}
