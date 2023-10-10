package Util;

import Gui.panel.WorkingPanel;

import java.awt.Component;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CenterPanel extends JPanel {
    private double rate;//�������
    private JComponent c;//��ʾ�����
    private boolean strech;//�Ƿ�����

    public CenterPanel(double rate, boolean strech) {
        this.setLayout(null);//�������
        this.rate = rate;
        this.strech = strech;
    }
    public CenterPanel(double rate) {
        this(rate, true);
    }
    public void repaint() {
        if (null != c) {
            Dimension containerSize = this.getSize();
            Dimension componentSize= c.getPreferredSize();

            if(strech)
                c.setSize((int) (containerSize.width * rate), (int) (containerSize.height * rate));
            else
                c.setSize(componentSize);

            c.setLocation(containerSize.width / 2 - c.getSize().width / 2, containerSize.height / 2 - c.getSize().height / 2);
        }
        super.repaint();
    }

    /**
     * show�����ǵ����ظ���ʾ���������
     * @param p
     */
    public void show(JComponent p) {
        this.c = p;
        // Component[] cs = c.getComponents(); c.getComponents()????????????????
        Component[] cs = getComponents();
        for(Component c : cs) {
            remove(c);
        }
        add(p);
        if (p instanceof WorkingPanel) ((WorkingPanel) p).updateData();//����������ϵİ�ť�����������л�����ͬ����壬��������ϵ�����Ҳ���ϴ����ݿ��и��¡�
        this.updateUI();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(200,200);
        frame.setLocationRelativeTo(null);
        CenterPanel centerPanel = new CenterPanel(0.85,true);
        frame.setContentPane(centerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        JButton button = new JButton("abc");
        centerPanel.show(button);
    }
}
