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

    String[] columnNames = new String[]{"��������","���","��ע","����"};
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
     * getValueAt������ͨ��������ȡspend cid comment date
     * @param rowIndex        the row whose value is to be queried
     * @param columnIndex     the column whose value is to be queried
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (0==columnIndex)//���
            return rs.get(rowIndex).getCid();
        if (1==columnIndex)//���
            return rs.get(rowIndex).getSpend();
        //why index = 2��-- ���ص���rs���� ��Record
        if (2==columnIndex)//��ע
            return rs.get(rowIndex).getComment();
        if (3==columnIndex)//����
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
