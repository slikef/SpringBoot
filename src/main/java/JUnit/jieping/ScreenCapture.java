package JUnit.jieping;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class ScreenCapture extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -5093584478732778943L;
    private JPanel contentPane;
    private JLabel imageLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ScreenCapture frame = new ScreenCapture();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ScreenCapture() {
        setTitle("截图工具");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton button = new JButton("开始截图");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        buttonPanel.add(button);

        JPanel imagePanel = new JPanel();
        contentPane.add(imagePanel, BorderLayout.CENTER);
        imagePanel.setLayout(new BorderLayout(0, 0));

        imageLabel = new JLabel("");
        imageLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePanel.add(imageLabel);
    }
    private Robot robot = null;
    protected void do_button_actionPerformed(ActionEvent e) {

        try {
            robot = new Robot();// 创建Robot对象
            ScreenCapture.this.setVisible(false);//隐藏窗体

            ScreenCapture.this.robot.delay(100);//延时,保证窗体已经隐藏
            Toolkit toolkit = Toolkit.getDefaultToolkit();// 获得Toolkit对象

            Rectangle area = new Rectangle(toolkit.getScreenSize());// 获取屏幕的尺寸,设置截取区域为全屏
//            area = new Rectangle(10, 20, 50-10, 60-20);//这实例一个Rectangle对象的四个参数在这了分别是指截取指定屏幕的X坐标,Y坐标,截取宽度,截取高度.
            // 将BufferedImage转换成Image
            BufferedImage bufferedImage = robot.createScreenCapture(area);
//            ImageProducer producer = bufferedImage.getSource();
//            Image image = toolkit.createImage(producer);
//            imageLabel.setIcon(new ImageIcon(image));// 显示图片e
            ScreenCapture.this.setVisible(true);//显示窗口
            Long name = new Date().getTime();
            File A = new File("E:\\BaiduNetdiskDownload\\Java精彩编程200例\\"+name+".png");
            ImageIO.write(bufferedImage,"png",A);


        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

}
