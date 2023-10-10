package Util;


import Entity.Record;
import com.objectplanet.chart.BarChart;
import com.objectplanet.chart.Chart;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 以前的ChartUtil是模拟数据，现在ChartUtil要根据ReportService.listThisMonthRecords() 返回的集合来生成相应的图表
 *
 *
 * public static Image getImage(List<Record> rs, int width, int height) {
 *     // 根据消费记录得到的样本数据
 *     double[] sampleValues = sampleValues(rs);
 *     // 根据消费记录得到的下方日期文本
 *     String[] sampleLabels = sampleLabels(rs);
 *     ....
 *     ....
 * }
 */

public class ChartUtil {

    private static String[] sampleLabels(List<Record> rs) {
        String[] sampleLabels = new String[rs.size()];

        for (int i = 0; i < sampleLabels.length; i++) {
            if (i % 5==0) {
                sampleLabels[i] = String.valueOf(i + 1 + "日");
            }
        }
        return sampleLabels;
    }
    private static String[] sampleLabels() {
        String[] sampleLabels = new String[30];

        for (int i = 0; i < sampleLabels.length; i++) {
            if (0 == i % 5)
                sampleLabels[i] = String.valueOf(i + 1 + "日");
        }
        return sampleLabels;
    }

    private static double[] sampleValues() {

        double[] result = new double[30];
        for (int i = 0; i < result.length; i++) {
            result[i] = (int) (Math.random() * 300);
        }
        return result;

    }
    private static double[] sampleValues(List<Record> rs) {
        double[] sampleValues = new double[rs.size()];
        for (int i = 0; i < sampleValues.length; i++) {
            sampleValues[i] = rs.get(i).getSpend();
        }
        return sampleValues;
    }



    public static int max(double[] sampleValues){
        int max = 0;
        for (double v : sampleValues) {
            if (v > max) max = (int) v;
        }
        return max;
    }

    public static Image getImage(List<Record> rs, int width, int height) {
        double[] sampleValues = sampleValues(rs); //根据消费记录得到的样本数据
        String[] sampleLabels = sampleLabels(rs); //根据消费记录得到下方的日期文本
        int max = max(sampleValues); //样本最大值
        Color[] sampleColors = new Color[] {ColorUtil.blueColor};

        BarChart chart = new BarChart(); //柱状图

        chart.setSampleCount(sampleValues.length);//样本个数
        chart.setSampleValues(0, sampleValues); //设置样本数据
        chart.setSampleLabels(sampleLabels); //样本文字
        chart.setSampleColors(sampleColors); //样本颜色
        chart.setRange(0, max * 1.2); //取值范围
        chart.setValueLinesOn(true); //背景横线
        chart.setSampleLabelsOn(true);//显示文字
        chart.setSampleLabelStyle(Chart.BELOW); //文字显示在下方

        chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12));//设置字体
        chart.setLegendOn(true); //设置图例说明
        chart.setLegendPosition(Chart.LEFT); //图例说明放在左侧
        chart.setLegendLabels(new String[] {"月消费报表"}); //图例说明文字
        chart.setFont("legendFont", new Font("Dialog", Font.BOLD, 13));//图例字体
        chart.setFont("sampleLabelFont", new Font("Dialog", Font.BOLD, 13));//下方文字字体
        chart.setChartBackground(Color.WHITE); //图标中间颜色
        chart.setBackground(ColorUtil.backgroundColor); //背景颜色
        Image im = chart.getImage(width, height);
        return im;
    }

    public static Image getImage(int width, int height) {
        double[] sampleValues = sampleValues(); //模拟样本数据
        String[] sampleLabels = sampleLabels();//下方显示的文字 注意：静态方法无法调用非静态方法
        int max = max(sampleValues);//样本最大值
        Color[] sampleColors = new Color[] {ColorUtil.blueColor}; //数据颜色，通过匿名类调用
        BarChart chart = new BarChart();//柱状图
        chart.setSampleCount(sampleValues.length);//设置样本个数
        chart.setSampleValues(0, sampleValues);//设置样本数据
        chart.setSampleLabels(sampleLabels);//设置文字
        chart.setSampleColors(sampleColors);//设置样本颜色
        chart.setRange(0, max * 1.2);//设置取值范围
        chart.setValueLinesOn(true);//设置背景横线
        chart.setSampleLabelsOn(true);//显示文字
        chart.setSampleLabelStyle(Chart.BELOW);//将文字显示在下方

        chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD,12));//样本的字体
        chart.setLegendOn(true);//显示图例说明
        chart.setLegendPosition(Chart.LEFT);//将图例放在左边
        chart.setLegendLabels(new String[] {"月度消费报表"});//图例说明的文字
        chart.setFont("legendFont", new Font("Dialog", Font.BOLD,13));//图例说明的文字
        chart.setFont("sampleLabelFont",new Font("Dialog",Font.BOLD,13));//下方文字的字体
        chart.setChartBackground(Color.WHITE);//图标中间的背景颜色
        chart.setBackground(ColorUtil.backgroundColor);//图标整体背景颜色
        Image image = chart.getImage(width,height);//把图标转为Image类型
        return image;
    }




    /**
     * 测试函数
     * @param args
     */
    public static void main(String[] args) {
        JPanel jp = new JPanel();
        JLabel jl = new JLabel();
        Image img = ChartUtil.getImage(400,300);
        Icon icon = new ImageIcon(img);
        jl.setIcon(icon);
        jp.add(jl);
        GUIUtil.showPanel(jp);
    }
}
