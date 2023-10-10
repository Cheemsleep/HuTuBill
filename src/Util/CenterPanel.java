package Util;

import Gui.panel.WorkingPanel;

import java.awt.Component;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CenterPanel extends JPanel {
    private double rate;//拉伸比例
    private JComponent c;//显示的组件
    private boolean strech;//是否拉伸

    public CenterPanel(double rate, boolean strech) {
        this.setLayout(null);//这里出错
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
     * show方法是导致重复显示的罪魁祸首
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
        if (p instanceof WorkingPanel) ((WorkingPanel) p).updateData();//点击工具栏上的按钮，不仅可以切换到不同的面板，而且面板上的数据也马上从数据库中更新。
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
