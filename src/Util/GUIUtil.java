package Util;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * 该类采用特殊形式的单例设计模式，可以大大减少代码重复度
 */
public class GUIUtil {
    public static String imageFolder = "D:\\IDEA_project\\HuTuBill\\src\\image";

    /**
     * 给按钮设置图标以及提示文字
     * @param b
     * @param fileName
     * @param tip
     */
    public static void setImageIcon(JButton b, String fileName, String tip) {
        ImageIcon i = new ImageIcon(new File(imageFolder, fileName).getAbsolutePath());
        b.setIcon(i);
        b.setPreferredSize(new Dimension(61, 81));
        b.setToolTipText(tip);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);
    }

    /**
     * 给多个组件设置前景色
     * @param color
     * @param cs
     */
    public static void setColor(Color color, JComponent... cs) {//...不定量变量
        for(JComponent jc : cs) {
            jc.setForeground(color);
        }
        //设置颜色
    }

    /**
     *快速显示一个面板的内容
     * @param p
     * @param strechRate 拉伸比例1代表铺满屏幕
     */
    public static void showPanel(JPanel p,double strechRate) {
        GUIUtil.useLNF();
        JFrame f = new JFrame();
        f.setSize(500, 500);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(strechRate);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        cp.show(p);
    }

    public static void showPanelNew(JPanel p,double strech) {
        GUIUtil.useLNF();
        JFrame f = new JFrame();
        f.setSize(500, 500);
        f.setLocationRelativeTo(null);
        CenterPanel cp = new CenterPanel(strech);
        f.setContentPane(cp);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        f.setVisible(true);
        cp.show(p);
    }
    public static void showPanelNew(JPanel p) {
        showPanelNew(p, 1);
    }

    public static void showPanel(JPanel p) {
        showPanel(p,0.85);
    }

    /**
     * 检查组件内容是否是数字
     * @param tf
     * @param input
     * @return
     */
    public static boolean checkNumber(JTextField tf, String input) {
        if(!checkEmpty(tf,input)) return false;
        String text = tf.getText().trim();
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, input + "需要整数");
            tf.grabFocus();
            return false;
        }
    }

    public static boolean checkZero(JTextField tf, String input) {
        if (!checkNumber(tf, input)) return false;
        String text = tf.getText().trim();//trim() 用于删除字符串的头尾空白符
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, input + "数字不能为0");
            tf.grabFocus();
            return false;
        }
    }

    public static boolean checkEmpty(JTextField tf, String input) {
        String text = tf.getText().trim();
        if (0 == text.length()) {
            JOptionPane.showMessageDialog(null, input + "不能为空");
            tf.grabFocus();
            return false;
        }
        return true;
    }

    /**
     * 使用水晶皮肤
     */
    public static void useLNF() {
        try {
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception e) {
            System.out.println("包错误");
            e.printStackTrace();
        }
    }

    public static int getInt(JTextField tf) {
        return Integer.parseInt(tf.getText());
    }


}
