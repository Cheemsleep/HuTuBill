package Util;


import Entity.Record;
import com.objectplanet.chart.BarChart;
import com.objectplanet.chart.Chart;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * ��ǰ��ChartUtil��ģ�����ݣ�����ChartUtilҪ����ReportService.listThisMonthRecords() ���صļ�����������Ӧ��ͼ��
 *
 *
 * public static Image getImage(List<Record> rs, int width, int height) {
 *     // �������Ѽ�¼�õ�����������
 *     double[] sampleValues = sampleValues(rs);
 *     // �������Ѽ�¼�õ����·������ı�
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
                sampleLabels[i] = String.valueOf(i + 1 + "��");
            }
        }
        return sampleLabels;
    }
    private static String[] sampleLabels() {
        String[] sampleLabels = new String[30];

        for (int i = 0; i < sampleLabels.length; i++) {
            if (0 == i % 5)
                sampleLabels[i] = String.valueOf(i + 1 + "��");
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
        double[] sampleValues = sampleValues(rs); //�������Ѽ�¼�õ�����������
        String[] sampleLabels = sampleLabels(rs); //�������Ѽ�¼�õ��·��������ı�
        int max = max(sampleValues); //�������ֵ
        Color[] sampleColors = new Color[] {ColorUtil.blueColor};

        BarChart chart = new BarChart(); //��״ͼ

        chart.setSampleCount(sampleValues.length);//��������
        chart.setSampleValues(0, sampleValues); //������������
        chart.setSampleLabels(sampleLabels); //��������
        chart.setSampleColors(sampleColors); //������ɫ
        chart.setRange(0, max * 1.2); //ȡֵ��Χ
        chart.setValueLinesOn(true); //��������
        chart.setSampleLabelsOn(true);//��ʾ����
        chart.setSampleLabelStyle(Chart.BELOW); //������ʾ���·�

        chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD, 12));//��������
        chart.setLegendOn(true); //����ͼ��˵��
        chart.setLegendPosition(Chart.LEFT); //ͼ��˵���������
        chart.setLegendLabels(new String[] {"�����ѱ���"}); //ͼ��˵������
        chart.setFont("legendFont", new Font("Dialog", Font.BOLD, 13));//ͼ������
        chart.setFont("sampleLabelFont", new Font("Dialog", Font.BOLD, 13));//�·���������
        chart.setChartBackground(Color.WHITE); //ͼ���м���ɫ
        chart.setBackground(ColorUtil.backgroundColor); //������ɫ
        Image im = chart.getImage(width, height);
        return im;
    }

    public static Image getImage(int width, int height) {
        double[] sampleValues = sampleValues(); //ģ����������
        String[] sampleLabels = sampleLabels();//�·���ʾ������ ע�⣺��̬�����޷����÷Ǿ�̬����
        int max = max(sampleValues);//�������ֵ
        Color[] sampleColors = new Color[] {ColorUtil.blueColor}; //������ɫ��ͨ�����������
        BarChart chart = new BarChart();//��״ͼ
        chart.setSampleCount(sampleValues.length);//������������
        chart.setSampleValues(0, sampleValues);//������������
        chart.setSampleLabels(sampleLabels);//��������
        chart.setSampleColors(sampleColors);//����������ɫ
        chart.setRange(0, max * 1.2);//����ȡֵ��Χ
        chart.setValueLinesOn(true);//���ñ�������
        chart.setSampleLabelsOn(true);//��ʾ����
        chart.setSampleLabelStyle(Chart.BELOW);//��������ʾ���·�

        chart.setFont("rangeLabelFont", new Font("Arial", Font.BOLD,12));//����������
        chart.setLegendOn(true);//��ʾͼ��˵��
        chart.setLegendPosition(Chart.LEFT);//��ͼ���������
        chart.setLegendLabels(new String[] {"�¶����ѱ���"});//ͼ��˵��������
        chart.setFont("legendFont", new Font("Dialog", Font.BOLD,13));//ͼ��˵��������
        chart.setFont("sampleLabelFont",new Font("Dialog",Font.BOLD,13));//�·����ֵ�����
        chart.setChartBackground(Color.WHITE);//ͼ���м�ı�����ɫ
        chart.setBackground(ColorUtil.backgroundColor);//ͼ�����屳����ɫ
        Image image = chart.getImage(width,height);//��ͼ��תΪImage����
        return image;
    }




    /**
     * ���Ժ���
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
