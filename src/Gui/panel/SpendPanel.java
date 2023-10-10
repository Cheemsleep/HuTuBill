package Gui.panel;

import javax.swing.*;

import Gui.page.SpendPage;
import Service.SpendService;
import Util.CircleProgressBar;
import Util.ColorUtil;
import Util.GUIUtil;

import java.awt.*;

/**
 * SpendPanel消费一览面板类，也继承了WorkingPanel。 虽然SpendPanel不需要添加监听，但是要用到updateData()方法更新界面信息
 *
 * 在updateData()方法中根据SpendService获取页面对象
 * SpendPage spend = new SpendService().getSpendPage();
 * 然后把这个页面对象的值，更新到组件里。
 *
 * 如果超支了，那么就把相应的组件设置为红色，否则就是正常的颜色
 */
public class SpendPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();//使用皮肤
    }
    public static SpendPanel instance = new SpendPanel();//静态实例化方便使用

    public JLabel IMonthSpend = new JLabel("本月消费");
    public JLabel ITodaySpend = new JLabel("今日消费");
    public JLabel IAvgSpendPerDay = new JLabel("日均消费");
    public JLabel IMonthLeft = new JLabel("本月剩余");
    public JLabel IDayAvgAvailable = new JLabel("日均可用");
    public JLabel IMonthLeftDay = new JLabel("距离月末");

    public JLabel vMonthSpend = new JLabel("RMB:2300");
    public JLabel vTodaySpend = new JLabel("RMB:25");
    public JLabel vAvgSpendPerDay = new JLabel("RMB:120");
    public JLabel vMonthAvailable = new JLabel("RMB:2084");
    public JLabel vDayAvgAvailable = new JLabel("RMB:389");
    public JLabel vMonthLeftDay = new JLabel("15天");

    CircleProgressBar bar;

    public SpendPanel(){
        this.setLayout(new BorderLayout());
        bar = new CircleProgressBar();
        bar.setBackgroundColor(ColorUtil.blueColor);

        GUIUtil.setColor(ColorUtil.grayColor, IMonthSpend, ITodaySpend, IAvgSpendPerDay, IMonthLeft, IDayAvgAvailable, IMonthLeftDay
                        , vMonthSpend, vTodaySpend, vAvgSpendPerDay, vMonthAvailable, vDayAvgAvailable, vMonthLeftDay);
        GUIUtil.setColor(ColorUtil.blueColor, vMonthSpend, vTodaySpend);

        vMonthSpend.setFont(new Font("微软雅黑", Font.BOLD, 23));
        vTodaySpend.setFont(new Font("微软雅黑", Font.BOLD, 23));

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

//    @Overload 返回类型不用一致
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
     * 测试main函数
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
