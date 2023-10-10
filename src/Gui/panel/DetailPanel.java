package Gui.panel;

import Entity.Category;
import Entity.Record;
import Gui.listener.DetailListener;
import Gui.model.CategoryComboBoxModel;
import Gui.model.CategoryTableModel;
import Gui.model.DetailTableModel;
import Service.CategoryService;
import Service.RecordService;
import Util.ColorUtil;
import Util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class DetailPanel extends WorkingPanel{
    static{
        GUIUtil.useLNF();

    }
    public static DetailPanel instance = new DetailPanel();

    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");


    public DetailTableModel dtm = new DetailTableModel();
    public JTable t = new JTable(dtm);

    private DetailPanel() {
        GUIUtil.setColor(ColorUtil.blueColor,bEdit,bDelete);
        JScrollPane sp = new JScrollPane(t);
        JPanel pSubmit = new JPanel();
        pSubmit.add(bEdit);
        pSubmit.add(bDelete);

        this.setLayout(new BorderLayout());
        this.add(sp,BorderLayout.CENTER);
        this.add(pSubmit,BorderLayout.SOUTH);

        addListener();
    }


    public static void main(String[] args) {
        GUIUtil.showPanel(DetailPanel.instance);
    }


    @Override
    public void updateData() {

    }

    public void updateData(Category category) {
        dtm.rs = new RecordService().list(category);
        t.updateUI();
        t.getSelectionModel().setSelectionInterval(0,0);

        if (dtm.rs.size() == 0) {
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        } else {
            bEdit.setEnabled(true);
            bEdit.setEnabled(true);
        }
    }
    /**
     * 为DetailPanel新增加一个getSelectedRecord，方便获取JTable上当前选中的Record对象
     */
    public Record getSelectedRecord() {
        int index = t.getSelectedRow();
        return dtm.rs.get(index);
    }

    @Override
    public void addListener() {
        DetailListener listener = new DetailListener();
        bEdit.addActionListener(listener);
        bDelete.addActionListener(listener);
    }
}
