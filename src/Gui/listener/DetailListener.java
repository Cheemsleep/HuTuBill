package Gui.listener;


import Entity.Category;
import Entity.Record;
import Gui.panel.CategoryPanel;
import Gui.panel.DetailPanel;
import Gui.panel.DetailSetPanel;
import Gui.panel.MainPanel;
import Service.CategoryService;
import Service.RecordService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * 1.先判读那是否有记录，没有则返回
 * 2.输入金额不能为0
 * 3.调用RecordService的update更新记录
 * 用于监听DetailPanel中的编辑与删除操作
 * 点击编辑，弹出框，将各个属性显示在面板中
 * 点击删除，将选中的记录删除
 */
public class DetailListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        DetailPanel detailPanel = DetailPanel.instance;
        DetailSetPanel detailSetPanel = DetailSetPanel.instance;
        JButton button = (JButton) e.getSource();//获取点击的按钮
        if (detailPanel.dtm.rs.size() == 0) {
            JOptionPane.showMessageDialog(detailPanel,"暂无消费记录，无法修改");
            MainPanel.instance.workingPanel.show(CategoryPanel.instance);
            return;
        }
        if (button == detailPanel.bEdit) {
            Util.GUIUtil.showPanelNew(detailSetPanel);
        }
        if (button == detailPanel.bDelete) {
            Record record = detailPanel.getSelectedRecord();
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(detailPanel, "确认删除吗？")) return;
            int id = record.getId();
            System.out.println(id);
            new RecordService().delete(id);
        }
        detailPanel.updateData(CategoryPanel.instance.getSelectedCategory());
    }
}
