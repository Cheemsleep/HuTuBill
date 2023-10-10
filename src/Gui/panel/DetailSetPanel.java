package Gui.panel;

import Entity.Category;
import Gui.listener.DetailListener;
import Gui.listener.DetailSetListener;
import Gui.model.CategoryComboBoxModel;
import Gui.model.CategoryTableModel;
import Service.CategoryService;
import Util.ColorUtil;
import Util.GUIUtil;
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class DetailSetPanel extends WorkingPanel{
    static{
        GUIUtil.useLNF();
    }

    public static DetailSetPanel instance = new DetailSetPanel();

    public JButton bApply = new JButton("应用");

    public JLabel lCategory = new JLabel("分类");
    public JLabel lSpend = new JLabel("花费");
    public JLabel lComment = new JLabel("备注");
    public JLabel lDate = new JLabel("日期");

    public JTextField tfSpend = new JTextField("0");
    public JTextField tfComment = new JTextField("");

    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
    public JComboBox<Category> cbCategory = new JComboBox<>(cbModel);
    public JXDatePicker datePicker = new JXDatePicker(new Date());


    private DetailSetPanel() {
        GUIUtil.setColor(ColorUtil.grayColor, lSpend,lComment,lDate,lCategory);
        GUIUtil.setColor(ColorUtil.blueColor, bApply);
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

        pSubmit.add(bApply);

        this.setLayout(new BorderLayout());
        this.add(pInput,BorderLayout.NORTH);
        this.add(pSubmit,BorderLayout.CENTER);

        addListener();
    }

    @Override
    public void updateData() {
        cbModel.cs = new CategoryService().list();
        cbCategory.updateUI();
        resetInput();
        tfSpend.grabFocus();
    }

    @Override
    public void addListener() {
        DetailSetListener listener = new DetailSetListener();
        bApply.addActionListener(listener);
    }

    public void resetInput() {
        tfSpend.setText("0");
        tfComment.setText("");
        if (cbModel.cs.size() != 0) {
            cbCategory.setSelectedIndex(0);
        }
        datePicker.setDate(new Date());
    }

    public Category getSelectedCategory() {
        return (Category) cbCategory.getSelectedItem();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(DetailSetPanel.instance);
    }
}
