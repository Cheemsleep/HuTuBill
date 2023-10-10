package Gui.model;

import Entity.Category;
import Service.CategoryService;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class CategoryTableModel extends AbstractTableModel {
    String[] columnNames = new String[]{"分类名称", "消费次数"};

    // 使用从Service返回的List作为TableModel的数据
    public List<Category> cs = new CategoryService().list();// 存放的数据由String集合，改为了Category集合，并且从CategoryService的list()方法获取数据

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
     *  getValueAt方法，第一列取Category 对象的name值，第二列取recordNumber值。
     * @param rowIndex        the row whose value is to be queried
     * @param columnIndex     the column whose value is to be queried
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Category h = cs.get(rowIndex);
        // 没有显示名字显示类地址的原因
        // if (columnIndex == 0) return cs.get(rowIndex);
        if (columnIndex == 0) return h.getName();
        if (columnIndex == 1) return h.getRecordNumber();
        return null;
    }

}
