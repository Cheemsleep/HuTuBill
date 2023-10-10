package Gui.panel;

import javax.swing.*;

import Gui.listener.ConfigListener;
import Service.ConfigService;
import Util.ColorUtil;
import Util.GUIUtil;

import java.awt.*;

public class ConfigPanel extends WorkingPanel {
    static{
        GUIUtil.useLNF();
    }
    public static ConfigPanel instance = new ConfigPanel();
    //组件属性
    JLabel lBudget = new JLabel("本月预算");
    public JTextField tfBudget = new JTextField("0");

    JLabel lMysql = new JLabel("Mysql安装目录");
    public JTextField tfMysqlPath = new JTextField("");

    JButton bSubmit = new JButton("更新");

    public ConfigPanel() {
        GUIUtil.setColor(ColorUtil.grayColor, lBudget, lMysql);
        GUIUtil.setColor(ColorUtil.blueColor,bSubmit);

        JPanel pInput = new JPanel();
        JPanel pSubmit = new JPanel();
        int gap = 40;
        pInput.setLayout(new GridLayout(4,1,gap,gap));

        pInput.add(lBudget);
        pInput.add(tfBudget);
        pInput.add(lMysql);
        pInput.add(tfMysqlPath);
        this.setLayout(new BorderLayout());
        this.add(pInput,BorderLayout.NORTH);
        pSubmit.add(bSubmit);
        this.add(pSubmit,BorderLayout.CENTER);

        this.setText();//每次打开界面时   输入框中的显示值可以从数据库中读取
        addListener();
    }

    private void setText(){   //从数据库中获取值    显示在输入框中
        ConfigService cs=new ConfigService(); //自动调用static内程序（若数据不存在 就初始化）
        String s1=cs.get(ConfigService.budget);
        String s2=cs.get(ConfigService.mysqlPath);
        tfBudget.setText(s1);
        tfMysqlPath.setText(s2);
    }

    /**
     * 因为ConfigPanel之前没有updateData()，所以需要提供该方法
     * 在updateData()中，通过ConfigService获取预算和MYSQL路径数据，并显示在组件上
     */
    @Override
    public void updateData() {
        String budget = new ConfigService().get(ConfigService.budget);
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        tfBudget.setText(budget);
        tfMysqlPath.setText(mysqlPath);
        tfBudget.grabFocus();
    }

    public void addListener() {
        ConfigListener cl = new ConfigListener();
        bSubmit.addActionListener(cl);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ConfigPanel.instance);
    }


}
