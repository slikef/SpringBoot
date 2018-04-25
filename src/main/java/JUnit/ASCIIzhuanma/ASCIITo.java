package JUnit.ASCIIzhuanma;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

    /**
    对单个字符进行转码
     */
    public class ASCIITo extends JFrame {
        private static final long serialVersionUID = -6067423561196663639L;
        private JPanel contentPane;
        private JTextField asciiTextField;
        private JTextField numberTextField;
        private JLabel label3;
        private JLabel label6;

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
                    ASCIITo frame = new ASCIITo();
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
        public ASCIITo() {
            setTitle("ASCII编码查看器");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 450, 150);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            contentPane.setLayout(new BorderLayout(0, 0));
            setContentPane(contentPane);

            JPanel panel = new JPanel();
            contentPane.add(panel, BorderLayout.CENTER);
            panel.setLayout(new GridLayout(2, 1, 5, 5));

            JPanel asciiPanel = new JPanel();
            asciiPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            panel.add(asciiPanel);
            asciiPanel.setLayout(new GridLayout(1, 5, 5, 5));

            JLabel label1 = new JLabel("输入字符");
            label1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            asciiPanel.add(label1);

            asciiTextField = new JTextField();
            asciiTextField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            asciiPanel.add(asciiTextField);
            asciiTextField.setColumns(3);

            JLabel label2 = new JLabel("转换结果");
            label2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            asciiPanel.add(label2);

            label3 = new JLabel("");
            label3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            asciiPanel.add(label3);
//changTo
            JButton toNumberButton = new JButton("转换");
            toNumberButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    do_toNumberButton_actionPerformed(e);
                }
            });
            toNumberButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            asciiPanel.add(toNumberButton);

            JPanel numberPanel = new JPanel();
            numberPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            panel.add(numberPanel);
            numberPanel.setLayout(new GridLayout(1, 5, 5, 5));

            JLabel label4 = new JLabel("输入数字");
            label4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            numberPanel.add(label4);

            numberTextField = new JTextField();
            numberTextField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            numberPanel.add(numberTextField);
            numberTextField.setColumns(3);

            JLabel label5 = new JLabel("转换结果");
            label5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            numberPanel.add(label5);

            label6 = new JLabel("");
            label6.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            numberPanel.add(label6);

            JButton toASCIIButton = new JButton("数字转化");
            toASCIIButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            toASCIIButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    do_toASCIIButton_actionPerformed(e);
                }
            });
            toASCIIButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
            numberPanel.add(toASCIIButton);
        }

        protected void do_toNumberButton_actionPerformed(ActionEvent e) {
            String ascii = asciiTextField.getText();
            if(!ascii.isEmpty()) {
                int i = Character.codePointAt(ascii, 0);
                label3.setText("" + i);
            }else {
                label3.setText("请填字符" );
            }
        }

        protected void do_toASCIIButton_actionPerformed(ActionEvent e) {
            String number = numberTextField.getText();
            if(!number.isEmpty()){
                char[] a = Character.toChars(Integer.parseInt(number));
                label6.setText(new String(a));
            }else {
                label6.setText("请填数字");
            }
        }
    }
