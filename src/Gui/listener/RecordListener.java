package Gui.listener;

import Entity.Category;
import Gui.panel.CategoryPanel;
import Gui.panel.MainPanel;
import Gui.panel.RecordPanel;
import Gui.panel.SpendPanel;
import Service.RecordService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * 给界面上的"记一笔“ 按钮添加监听
 * 1. 首先判断是否有消费分类信息，如果没有提示先增加消费分类
 * 2. 输入的金额不能为0
 * 3. 调用RecordService的add添加消费记录
 * 4. 提示添加成功
 * 5. 添加成功后，切换到消费一览
 */
public class RecordListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordPanel recordPanel = RecordPanel.instance;
        if (recordPanel.cbModel.cs.size() == 0) {
            JOptionPane.showMessageDialog(recordPanel,"暂无消费分类，无法添加，先添加消费分类");
            MainPanel.instance.workingPanel.show(CategoryPanel.instance);
            return;
        }
        if (!Util.GUIUtil.checkZero(recordPanel.tfSpend, "花费金额")) return;
        int spend = Integer.parseInt(recordPanel.tfSpend.getText());
        Category c = recordPanel.getSelectedCategory();
        String comment = recordPanel.tfComment.getText();
        Date date = recordPanel.datePicker.getDate();
        new RecordService().add(spend, c, comment, date);
        JOptionPane.showMessageDialog(recordPanel, "添加成功");

        MainPanel.instance.workingPanel.show(SpendPanel.instance);
    }
}
