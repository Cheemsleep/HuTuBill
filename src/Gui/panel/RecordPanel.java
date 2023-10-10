package Gui.panel;

import Entity.Category;
import Entity.Record;
import Gui.listener.RecordListener;
import Gui.model.CategoryComboBoxModel;
import Service.CategoryService;
import Util.ColorUtil;
import org.jdesktop.swingx.JXDatePicker;

import Util.GUIUtil;
import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * RecordPanel 消费记录面板做了如下改动
 * 1. 继承了WorkingPanel，从而必须提供addListener()和updateData()方法
 * 2. 提供getSelectedCategory()用于获取当前选中的分类对象。
 * 3. addListener() 给按钮添加监听
 * 4. updateData()更新数据，主要是更新下拉框中的分类信息，并且让各个输入框信息重置，以及让焦点停留在金额的输入框上
 */
public class RecordPanel extends WorkingPanel {
    static{
        GUIUtil.useLNF();
    }
    public static RecordPanel instance = new RecordPanel();

    JLabel lSpend = new JLabel("花费(RMB)");
    JLabel lCategory = new JLabel("分类");
    JLabel lComment = new JLabel("备注");
    JLabel lDate = new JLabel("日期");

    public JTextField tfSpend = new JTextField("0");

    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
    public JComboBox<Category> cbCategory = new JComboBox<>(cbModel);
    public JTextField tfComment = new JTextField();
    public JXDatePicker datePicker = new JXDatePicker(new Date());

    JButton bSubmit = new JButton("记一笔");

    public RecordPanel() {
        GUIUtil.setColor(ColorUtil.grayColor, lSpend,lCategory,lComment,lDate);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);
        JPanel pInput = new JPanel();
        JPanel pSubmit = new JPanel();
        int gap = 40;

        pInput.setLayout(new GridLayout(4,2,gap,gap));
        pInput.add(lSpend);
        pInput.add(tfSpend);
        pInput.add(lCategory);
        pInput.add(cbCategory);
        pInput.add(lComment);
        pInput.add(tfComment);
        pInput.add(lDate);
        pInput.add(datePicker);
        pSubmit.add(bSubmit);

        this.setLayout(new BorderLayout());
        this.add(pInput,BorderLayout.NORTH);
        this.add(pSubmit,BorderLayout.CENTER);

        addListener();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(RecordPanel.instance);
    }

    public Category getSelectedCategory() {
        return (Category) cbCategory.getSelectedItem();
    }

    @Override
    public void updateData() {
        cbModel.cs = new CategoryService().list();
        cbCategory.updateUI();
        resetInput();
        tfSpend.grabFocus();
    }

    public void resetInput() {
        tfSpend.setText("0");
        tfComment.setText("");
        if (cbModel.cs.size() != 0) {
            cbCategory.setSelectedIndex(0);
        }
        datePicker.setDate(new Date());
    }

    @Override
    public void addListener() {
        RecordListener listener = new RecordListener();
        bSubmit.addActionListener(listener);
    }
}
