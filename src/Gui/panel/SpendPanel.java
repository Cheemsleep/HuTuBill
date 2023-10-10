package Gui.panel;

import javax.swing.*;

import Gui.page.SpendPage;
import Service.SpendService;
import Util.CircleProgressBar;
import Util.ColorUtil;
import Util.GUIUtil;

import java.awt.*;

/**
 * SpendPanel����һ������࣬Ҳ�̳���WorkingPanel�� ��ȻSpendPanel����Ҫ��Ӽ���������Ҫ�õ�updateData()�������½�����Ϣ
 *
 * ��updateData()�����и���SpendService��ȡҳ�����
 * SpendPage spend = new SpendService().getSpendPage();
 * Ȼ������ҳ������ֵ�����µ�����
 *
 * �����֧�ˣ���ô�Ͱ���Ӧ���������Ϊ��ɫ�����������������ɫ
 */
public class SpendPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();//ʹ��Ƥ��
    }
    public static SpendPanel instance = new SpendPanel();//��̬ʵ��������ʹ��

    public JLabel IMonthSpend = new JLabel("��������");
    public JLabel ITodaySpend = new JLabel("��������");
    public JLabel IAvgSpendPerDay = new JLabel("�վ�����");
    public JLabel IMonthLeft = new JLabel("����ʣ��");
    public JLabel IDayAvgAvailable = new JLabel("�վ�����");
    public JLabel IMonthLeftDay = new JLabel("������ĩ");

    public JLabel vMonthSpend = new JLabel("RMB:2300");
    public JLabel vTodaySpend = new JLabel("RMB:25");
    public JLabel vAvgSpendPerDay = new JLabel("RMB:120");
    public JLabel vMonthAvailable = new JLabel("RMB:2084");
    public JLabel vDayAvgAvailable = new JLabel("RMB:389");
    public JLabel vMonthLeftDay = new JLabel("15��");

    CircleProgressBar bar;

    public SpendPanel(){
        this.setLayout(new BorderLayout());
        bar = new CircleProgressBar();
        bar.setBackgroundColor(ColorUtil.blueColor);

        GUIUtil.setColor(ColorUtil.grayColor, IMonthSpend, ITodaySpend, IAvgSpendPerDay, IMonthLeft, IDayAvgAvailable, IMonthLeftDay
                        , vMonthSpend, vTodaySpend, vAvgSpendPerDay, vMonthAvailable, vDayAvgAvailable, vMonthLeftDay);
        GUIUtil.setColor(ColorUtil.blueColor, vMonthSpend, vTodaySpend);

        vMonthSpend.setFont(new Font("΢���ź�", Font.BOLD, 23));
        vTodaySpend.setFont(new Font("΢���ź�", Font.BOLD, 23));

        this.add(center(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);
    }



    private JPanel center() {
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        jp.add(west(), BorderLayout.WEST);
        jp.add(center2(), BorderLayout.CENTER);

        return jp;
    }
    private Component center2() {
        return bar;
    }

    private Component west() {
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(4,1));
        jp.add(IMonthSpend);
        jp.add(vMonthSpend);
        jp.add(ITodaySpend);
        jp.add(vTodaySpend);
        return jp;
    }

//    @Overload �������Ͳ���һ��
//    public String south(String str) {
//        return null;
//    }
    private JPanel south() {
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(2,4));

        jp.add(IAvgSpendPerDay);
        jp.add(IMonthLeft);
        jp.add(IDayAvgAvailable);
        jp.add(IMonthLeftDay);
        jp.add(vAvgSpendPerDay);
        jp.add(vMonthAvailable);
        jp.add(vDayAvgAvailable);
        jp.add(vMonthLeftDay);

        return jp;
    }

    /**
     * ����main����
     * @param args
     */
    public static void main(String[] args) {
        GUIUtil.showPanel(SpendPanel.instance);
    }

    @Override
    public void updateData() {
        SpendPage spend = new SpendService().getSpendPage();
        vMonthSpend.setText(spend.monthSpend);
        vTodaySpend.setText(spend.todaySpend);
        vAvgSpendPerDay.setText(spend.avgSpendPerDay);
        vMonthAvailable.setText(spend.monthAvailable);
        vDayAvgAvailable.setText(spend.dayAvgAvailable);
        vMonthLeftDay.setText(spend.monthLeftDay);

        bar.setProgress(spend.usagePercentage);
        if (spend.isOverSpend) {
            vMonthAvailable.setForeground(ColorUtil.warningColor);
            vMonthSpend.setForeground(ColorUtil.warningColor);
            vTodaySpend.setForeground(ColorUtil.warningColor);
        } else {
            vMonthAvailable.setForeground(ColorUtil.grayColor);
            vMonthSpend.setForeground(ColorUtil.blueColor);
            vTodaySpend.setForeground(ColorUtil.blueColor);
        }
        bar.setForegroundColor(ColorUtil.getByPercentage(spend.usagePercentage));
        addListener();
    }

    @Override
    public void addListener() {

    }
}
