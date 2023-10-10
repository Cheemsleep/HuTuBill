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
    //�������
    JLabel lBudget = new JLabel("����Ԥ��");
    public JTextField tfBudget = new JTextField("0");

    JLabel lMysql = new JLabel("Mysql��װĿ¼");
    public JTextField tfMysqlPath = new JTextField("");

    JButton bSubmit = new JButton("����");

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

        this.setText();//ÿ�δ򿪽���ʱ   ������е���ʾֵ���Դ����ݿ��ж�ȡ
        addListener();
    }

    private void setText(){   //�����ݿ��л�ȡֵ    ��ʾ���������
        ConfigService cs=new ConfigService(); //�Զ�����static�ڳ��������ݲ����� �ͳ�ʼ����
        String s1=cs.get(ConfigService.budget);
        String s2=cs.get(ConfigService.mysqlPath);
        tfBudget.setText(s1);
        tfMysqlPath.setText(s2);
    }

    /**
     * ��ΪConfigPanel֮ǰû��updateData()��������Ҫ�ṩ�÷���
     * ��updateData()�У�ͨ��ConfigService��ȡԤ���MYSQL·�����ݣ�����ʾ�������
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
