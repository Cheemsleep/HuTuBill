package Gui.model;

import Entity.Category;
import Entity.Record;
import Gui.panel.CategoryPanel;
import Gui.panel.DetailPanel;
import Gui.panel.DetailSetPanel;
import Service.RecordService;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class DetailTableModel extends AbstractTableModel {

    String[] columnNames = new String[]{"消费类型","金额","备注","日期"};
    public List<Record> rs = new RecordService().list(CategoryPanel.instance.getSelectedCategory());


    public void reSelected(Category category) {
        rs = new RecordService().list(category);
    }
    @Override
    public int getRowCount() {
        return rs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * getValueAt方法，通过行来获取spend cid comment date
     * @param rowIndex        the row whose value is to be queried
     * @param columnIndex     the column whose value is to be queried
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (0==columnIndex)//类别
            return rs.get(rowIndex).getCid();
        if (1==columnIndex)//金额
            return rs.get(rowIndex).getSpend();
        //why index = 2？-- 返回的是rs对象 即Record
        if (2==columnIndex)//备注
            return rs.get(rowIndex).getComment();
        if (3==columnIndex)//日期
            return rs.get(rowIndex).getDate();
        return null;
    }
    @Override
    public String getColumnName(int columnIndex) {
        // TODO Auto-generated method stub
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // TODO Auto-generated method stub
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return false;
    }

}
