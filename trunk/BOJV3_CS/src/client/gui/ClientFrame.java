/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client.gui;

import client.ClientController;
import config.Const;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import tool.Tools;

/**
 *
 * @author liheyuan
 */
public class ClientFrame extends JFrame
{
    /**窗体宽度*/
    private static final int FORM_WIDTH = 800;
    /**窗体高度*/
    private static final int FORM_HEIGHT = 600;
    /**工具条*/
    private JToolBar toolbar = new JToolBar();


    //GUI区域
    private JPanel curPane = null;


    //
    private ClientController controller = null;

    public ClientFrame ()
    {
        //设置大小，并默认居中
        setSize(FORM_WIDTH,FORM_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tools.setCenter(this);
        //设置标题
        this.setTitle("Open-BOJ Judge端");
        //初始化工具条
        init_toolbar();

        //启动控制器
         controller = new ClientController(this);

        //显示STAT视图
        controller.handle(ClientController.EVENT_SHOW_STAT, null);

    }

    /***
     * 初始化工具条
     */
    private void init_toolbar()
    {
        //统计页面
        JButton stat = new JButton("统计",Tools.getIcon(this.getClass().getResource("/res/tongji.png")));
        stat.addActionListener(new ActionListener (){

            public void actionPerformed(ActionEvent e)
            {
                controller.handle(ClientController.EVENT_SHOW_STAT, null);
            }
        });
        toolbar.add(stat);

        //异常页面
        JButton yichang = new JButton("异常",Tools.getIcon(this.getClass().getResource("/res/yichang.png")));
        yichang.addActionListener(new ActionListener (){

            public void actionPerformed(ActionEvent e)
            {
                controller.handle(ClientController.EVENT_SHOW_EXCEPTION, null);
            }

        });
        toolbar.add(yichang);
        //添加工具条
        toolbar.setFloatable(false);
        this.add(toolbar,BorderLayout.NORTH);
    }
    /***
     * 设置内容窗体
     * @param p
     */
    public void setPane(JPanel p)
    {
        if(curPane==null)
        {
            curPane = p;
        }
        else
        {
            this.remove(curPane);
            curPane = p;
        }
        this.add(curPane,BorderLayout.CENTER);
        this.repaint();
        this.setVisible(true);
    }

    public static void main(String [] args) throws UnsupportedLookAndFeelException
    {
        try
        {
            UIManager.setLookAndFeel(new  org.jvnet.substance.skin.SubstanceSaharaLookAndFeel());
            javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);

            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    ClientFrame f = new ClientFrame();
                    f.setVisible(true);
                }
            });
        }
        catch (UnsupportedLookAndFeelException ex)
        {
            //加载UI外观错误
            ex.printStackTrace();
        }
    }


}
