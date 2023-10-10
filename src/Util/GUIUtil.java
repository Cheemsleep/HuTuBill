package Util;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * �������������ʽ�ĵ������ģʽ�����Դ����ٴ����ظ���
 */
public class GUIUtil {
    public static String imageFolder = "D:\\IDEA_project\\HuTuBill\\src\\image";

    /**
     * ����ť����ͼ���Լ���ʾ����
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
     * ������������ǰ��ɫ
     * @param color
     * @param cs
     */
    public static void setColor(Color color, JComponent... cs) {//...����������
        for(JComponent jc : cs) {
            jc.setForeground(color);
        }
        //������ɫ
    }

    /**
     *������ʾһ����������
     * @param p
     * @param strechRate �������1����������Ļ
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
     * �����������Ƿ�������
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
            JOptionPane.showMessageDialog(null, input + "��Ҫ����");
            tf.grabFocus();
            return false;
        }
    }

    public static boolean checkZero(JTextField tf, String input) {
        if (!checkNumber(tf, input)) return false;
        String text = tf.getText().trim();//trim() ����ɾ���ַ�����ͷβ�հ׷�
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, input + "���ֲ���Ϊ0");
            tf.grabFocus();
            return false;
        }
    }

    public static boolean checkEmpty(JTextField tf, String input) {
        String text = tf.getText().trim();
        if (0 == text.length()) {
            JOptionPane.showMessageDialog(null, input + "����Ϊ��");
            tf.grabFocus();
            return false;
        }
        return true;
    }

    /**
     * ʹ��ˮ��Ƥ��
     */
    public static void useLNF() {
        try {
            javax.swing.UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception e) {
            System.out.println("������");
            e.printStackTrace();
        }
    }

    public static int getInt(JTextField tf) {
        return Integer.parseInt(tf.getText());
    }


}
