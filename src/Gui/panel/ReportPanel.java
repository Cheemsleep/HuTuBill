package Gui.panel;

import Entity.Record;
import Service.ReportService;
import Util.ChartUtil;
import Util.GUIUtil;
import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * ReportPanel���������̳�WorkingPanel��ʵ��updateDate()���������½����ϵ�����
 *
 */
public class ReportPanel extends WorkingPanel {
    static{
        GUIUtil.useLNF();
    }
    public static ReportPanel instance = new ReportPanel();
    //��������
    public JLabel label = new JLabel();
    public ReportPanel(){
        this.setLayout(new BorderLayout());
        Image image = ChartUtil.getImage(400,300);
        ImageIcon icon = new ImageIcon(image);
        label.setIcon(icon);
        this.add(label);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ReportPanel.instance);
    }

    @Override
    public void updateData() {
        List<Record> rs = new ReportService().listThisMonthRecords();
        Image i = ChartUtil.getImage(rs, 350, 250);
        ImageIcon icon = new ImageIcon(i);
        label.setIcon(icon);
    }

    @Override
    public void addListener() {
        //pass
    }
}
