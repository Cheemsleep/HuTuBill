package Gui.model;

import Entity.Category;
import Service.CategoryService;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class CategoryTableModel extends AbstractTableModel {
    String[] columnNames = new String[]{"��������", "���Ѵ���"};

    // ʹ�ô�Service���ص�List��ΪTableModel������
    public List<Category> cs = new CategoryService().list();// ��ŵ�������String���ϣ���Ϊ��Category���ϣ����Ҵ�CategoryService��list()������ȡ����

    @Override
    public int getRowCount() {
        return cs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }


    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    /**
     *  getValueAt��������һ��ȡCategory �����nameֵ���ڶ���ȡrecordNumberֵ��
     * @param rowIndex        the row whose value is to be queried
     * @param columnIndex     the column whose value is to be queried
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category h = cs.get(rowIndex);
        // û����ʾ������ʾ���ַ��ԭ��
        // if (columnIndex == 0) return cs.get(rowIndex);
        if (columnIndex == 0) return h.getName();
        if (columnIndex == 1) return h.getRecordNumber();
        return null;
    }

}
