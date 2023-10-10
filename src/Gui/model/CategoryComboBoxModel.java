package Gui.model;

import Entity.Category;
import Service.CategoryService;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;

public class CategoryComboBoxModel implements ComboBoxModel<Category> {

    public List<Category> cs = new CategoryService().list();
    public Category c;

    public CategoryComboBoxModel() {
        if (!cs.isEmpty()) c = cs.get(0);//获取第一个分类
    }

    @Override
    public void setSelectedItem(Object anItem) {
        c = (Category) anItem;
    }

    /**
     * 不写分支返回出现的问题：在类只有一个分类名称的时候删除之后仍显示之前的名称，Category c被选择后被赋值后没有被清空然后JComboBox里的显示选择的信息应该是调用getSelectedItem来进行设置的
     * @return
     */
    @Override
    public Object getSelectedItem() {
        if (!cs.isEmpty()) return c;
        else return null;
    }

    @Override
    public int getSize() {
        return cs.size();
    }

    @Override
    public Category getElementAt(int index) {
        return cs.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
