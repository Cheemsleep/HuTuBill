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
        if (!cs.isEmpty()) c = cs.get(0);//��ȡ��һ������
    }

    @Override
    public void setSelectedItem(Object anItem) {
        c = (Category) anItem;
    }

    /**
     * ��д��֧���س��ֵ����⣺����ֻ��һ���������Ƶ�ʱ��ɾ��֮������ʾ֮ǰ�����ƣ�Category c��ѡ��󱻸�ֵ��û�б����Ȼ��JComboBox�����ʾѡ�����ϢӦ���ǵ���getSelectedItem���������õ�
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
