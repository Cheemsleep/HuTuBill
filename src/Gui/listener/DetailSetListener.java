package Gui.listener;

import Entity.Record;
import Gui.panel.CategoryPanel;
import Gui.panel.DetailPanel;
import Gui.panel.DetailSetPanel;
import Service.RecordService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailSetListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        DetailSetPanel detailSetPanel = DetailSetPanel.instance;
        DetailPanel detailPanel = DetailPanel.instance;
        JButton button = (JButton) e.getSource();//获取点击的按钮
        //获取编辑所需的数据
        Record record = detailPanel.getSelectedRecord();
        if (!Util.GUIUtil.checkZero(detailSetPanel.tfSpend, "花费金额")) return;
        record.setSpend(Integer.parseInt(detailSetPanel.tfSpend.getText()));
        record.setCid(detailSetPanel.getSelectedCategory().getId());
        record.setComment(detailSetPanel.tfComment.getText());
        record.setDate(detailSetPanel.datePicker.getDate());
        if (button == detailSetPanel.bApply) {
            new RecordService().update(record);
            JOptionPane.showMessageDialog(detailSetPanel,"修改成功");
            return;
        }
        detailPanel.updateData(CategoryPanel.instance.getSelectedCategory());
    }
}
