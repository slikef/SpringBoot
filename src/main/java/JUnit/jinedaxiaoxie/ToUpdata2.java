package JUnit.jinedaxiaoxie;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToUpdata2 extends JFrame{
    private JPanel contentPane;
    private JTextField ipField;
    // 大写数字
    private final static String[] STR_NUMBER = { "零", "壹", "贰", "叁", "肆", "伍",
            "陆", "柒", "捌", "玖" };
    private final static String[] STR_UNIT = { "", "拾", "佰", "仟", "万", "拾",
            "佰", "仟", "亿", "拾", "佰", "仟" };// 整数单位
    private final static String[] STR_UNIT2 = { "角", "分", "厘" };// 小数单位

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ToUpdata2 frame = new ToUpdata2();
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
    public ToUpdata2() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 400, 400, 200);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblip = new JLabel("请输入小写金额：");
        lblip.setBounds(60, 40, 162, 20);
        contentPane.add(lblip);

        ipField = new JTextField();
        ipField.setBounds(160, 40, 160, 25);
        contentPane.add(ipField);

        JButton button = new JButton("转为大写");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_button_actionPerformed(e);
            }
        });
        button.setBounds(160, 80, 90, 30);
        contentPane.add(button);
    }

    protected void do_button_actionPerformed(ActionEvent e) {
        String text = ipField.getText();// 获取用户输入
        String info = convert(text);;// 对输入文本进行IP验证
        JOptionPane.showMessageDialog(null, info);// 用对话框输出验证结果

    }

    /**
     * 获取可数部分
     *
     * @param num
     *            金额
     * @return 金额整数部分
     */
    public static String getInteger(String num) {
        int indexOf = StringUtils.indexOf(num, ".");
        if (indexOf != -1) { // 判断是否包含小数点
            num = StringUtils.substring(num,0, indexOf);
        }
        num = new StringBuffer(num).reverse().toString(); // 反转字符串
        StringBuffer temp = new StringBuffer(); // 创建一个StringBuffer对象
        for (int i = 0; i < num.length(); i++) {// 加入单位
            temp.append(STR_UNIT[i]);
            temp.append(STR_NUMBER[num.charAt(i) - 48]); //把char型数字转换成的int型数字，因为它们的ASCII码值恰好相差48
            // 因此把char型数字减去48得到int型数字
        }
        num = temp.reverse().toString();// 反转字符串
        num = numReplace(num, "零拾", "零"); // 替换字符串的字符
        num = numReplace(num, "零佰", "零"); // 替换字符串的字符
        num = numReplace(num, "零仟", "零"); // 替换字符串的字符
        num = numReplace(num, "零万", "万"); // 替换字符串的字符
        num = numReplace(num, "零亿", "亿"); // 替换字符串的字符
        num = numReplace(num, "零零", "零"); // 替换字符串的字符
        num = numReplace(num, "亿万", "亿"); // 替换字符串的字符
        // 如果字符串以零结尾将其除去
        int lastIndexOf = StringUtils.lastIndexOf(num, "零");
        if (lastIndexOf == num.length() - 1) {
            num = num.substring(0, lastIndexOf);
        }
        return num;
    }

    /**
     * 获取小数部分
     *
     * @param num
     *            金额
     * @return 金额的小数部分
     */
    public static String getDecimal(String num) {
        // 判断是否包含小数点
        int indexNum = StringUtils.indexOf(num, ".");
        if (indexNum == -1) {
            return "";
        }
        num = StringUtils.substring(num,indexNum + 1);
        // 创建一个StringBuffer对象
        StringBuffer temp = new StringBuffer();
        // 加入单位
        for (int i = 0; i < num.length(); i++) {
            temp.append(STR_NUMBER[num.charAt(i) - 48]);
            temp.append(STR_UNIT2[i]);
        }
        num = temp.toString(); // 替换字符串的字符
        num = numReplace(num, "零角", "零"); // 替换字符串的字符
        num = numReplace(num, "零分", "零"); // 替换字符串的字符
        num = numReplace(num, "零厘", "零"); // 替换字符串的字符
        num = numReplace(num, "零零", "零"); // 替换字符串的字符
        // 如果字符串以零结尾将其除去
        int lastIndexOf = StringUtils.lastIndexOf(num, "零");
        if (lastIndexOf == num.length() - 1) {
            num = num.substring(0, lastIndexOf);
        }
        return num;
    }

    /**
     * 替换字符串中内容
     *
     * @param num
     *            字符串
     * @param oldStr
     *            被替换内容
     * @param newStr
     *            新内容
     * @return 替换后的字符串
     */
    public static String numReplace(String num, String oldStr, String newStr) {
        while (true) {
            // 判断字符串中是否包含指定字符
            int index = StringUtils.indexOf(num, oldStr);
            if (index == -1) {
                break;
            }
            // 替换字符串
            num = StringUtils.replaceAll(num, oldStr, newStr);
        }
        // 返回替换后的字符串
        return num;
    }

    /**
     * 金额转换
     *
     * @param d
     *            金额
     * @return 转换成大写的全额
     */
    public static String convert(String d) {
        if(!isNumeric(d)) return "请输入正确的小写金额！";
        // 实例化DecimalFormat对象
        DecimalFormat df = new DecimalFormat("#0.###");
//        DecimalFormat df1 = new DecimalFormat("#0.000");
        // 格式化double数字
        String strNum = df.format(Double.parseDouble(d));
        // 判断是否包含小数点
        String point = "元整";// 小数点
        int strLength = strNum.length();
        int index = StringUtils.indexOf(strNum, ".");
        if (index != -1) {
            point = "元";
            strLength = index;
        }
        // 整数部分大于12不能转换
        if (strLength > 12) {
            return "数字太大，不能完成转换！";
        }
        // 转换结果
        String result = getInteger(strNum) + point + getDecimal(strNum);
        if (result.startsWith("元")) { // 判断是字符串是否已"元"结尾
            result = result.substring(1, result.length()); // 截取字符串
        }
        return result; // 返回新的字符串
    }

    /***
     * 判断字符串是否为数字（包含小数点）
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]+\\.*[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
