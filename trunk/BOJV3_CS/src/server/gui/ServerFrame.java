package server.gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import server.ServerController;
import tool.Tools;

/**
 * Server的窗体
 * @author liheyuan
 */
public class ServerFrame extends JFrame
{
    /**窗体宽度*/
    private static final int FORM_WIDTH = 800;
    /**窗体高度*/
    private static final int FORM_HEIGHT = 600;
    /**工具条*/
    private JToolBar toolbar = new JToolBar();

    /**控制器*/
    private ServerController controller = null;
    /**当前的内容窗体*/
    private JPanel curPane = null;

    /***
     * 构造函数
     */
    public ServerFrame()
    {
        //Send，Rcv线程
        try
        {
            controller = new ServerController(this);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        //设置大小，并默认居中
        setSize(FORM_WIDTH,FORM_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tools.setCenter(this);
        //设置标题
        this.setTitle("Open-BOJ 服务器端");
        //初始化工具条
        init_toolbar();
        controller.handle(ServerController.EVENT_SHOW_TONGJI, null);
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
                controller.handle(ServerController.EVENT_SHOW_TONGJI, null);
            }
            
        });
        toolbar.add(stat);

        //维护页面
        JButton man = new JButton("维护",Tools.getIcon(this.getClass().getResource("/res/weihu.png")));
        man.addActionListener(new ActionListener (){

            public void actionPerformed(ActionEvent e)
            {
                controller.handle(ServerController.EVENT_SHOW_WEIHU, null);
            }

        });
        toolbar.add(man);
        
        //异常页面
        JButton yichang = new JButton("异常",Tools.getIcon(this.getClass().getResource("/res/yichang.png")));
        yichang.addActionListener(new ActionListener (){

            public void actionPerformed(ActionEvent e)
            {
                controller.handle(ServerController.EVENT_SHOW_YICHANG, null);
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
            UIManager.setLookAndFeel(new  org.jvnet.substance.skin.SubstanceNebulaLookAndFeel());
            javax.swing.JFrame.setDefaultLookAndFeelDecorated(true);

            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    ServerFrame f = new ServerFrame();
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
