package Gui.panel;

import Entity.Record;
import Service.ReportService;
import Util.ChartUtil;
import Util.GUIUtil;
import javax.swing.*;
import java.awt.*;
import java.util.List;


/**
 * ReportPanel报表面板类继承WorkingPanel，实现updateDate()方法来更新界面上的数据
 *
 */
public class ReportPanel extends WorkingPanel {
    static{
        GUIUtil.useLNF();
    }
    public static ReportPanel instance = new ReportPanel();
    //设置属性
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
