package Gui.panel;

import javax.swing.*;

import Entity.Category;
import Gui.listener.CategoryListener;
import Gui.model.CategoryTableModel;
import Service.CategoryService;
import Util.ColorUtil;
import Util.GUIUtil;

import java.awt.*;

/**
 * �����������ʱ���޷���ʾ����
 * �ư��ˣ�mysql��Ĭ�ϱ�����Latin�����������޸ĵķ�����ͨ�������аѱ��뷽ʽ��Ϊgbk���Ϳ�����
 */
public class CategoryPanel extends WorkingPanel {
    static{
        GUIUtil.useLNF();
    }
    public static CategoryPanel instance = new CategoryPanel();

    //����public����
    public JButton bAdd = new JButton("����");
    public JButton bEdit = new JButton("�༭");
    public JButton bDelete = new JButton("ɾ��");
    public JButton bDetail = new JButton("��ϸ��Ϣ");

    String columnNames[] = new String[]{"��������","���Ѵ���"};

    public CategoryTableModel ctm = new CategoryTableModel();
    public JTable table = new JTable(ctm);

    public CategoryPanel() {
        GUIUtil.setColor(ColorUtil.blueColor, bAdd,bEdit,bDelete,bDetail);
        JScrollPane sp = new JScrollPane(table);
        JPanel pSubmit = new JPanel();
        pSubmit.add(bAdd);
        pSubmit.add(bEdit);
        pSubmit.add(bDelete);
        pSubmit.add(bDetail);

        this.setLayout(new BorderLayout());
        this.add(sp,BorderLayout.CENTER);
        this.add(pSubmit,BorderLayout.SOUTH);

        addListener();
        //updateData();//�����˳�������ʱ�༭��ɾ����ť���õ����
    }

    /**
     * ΪCategoryPanel������һ��getSelectedCategory�������ȡJTable�ϵ�ǰѡ�е�Category����
     */
    public Category getSelectedCategory() {
        int index = table.getSelectedRow();
        return ctm.cs.get(index);
    }

    public void updateData() {
        ctm.cs = new CategoryService().list();
        table.updateUI();
        table.getSelectionModel().setSelectionInterval(0,0);

        if (ctm.cs.size() == 0) {
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
            bDetail.setEnabled(false);
        } else {
            bEdit.setEnabled(true);
            bEdit.setEnabled(true);
            bDetail.setEnabled(true);
        }
    }

    /**
     * Ϊ������ť��Ӽ�����
     */
    public void addListener() {
        CategoryListener listener = new CategoryListener();
        bAdd.addActionListener(listener);
        bEdit.addActionListener(listener);
        bDelete.addActionListener(listener);
        bDetail.addActionListener(listener);
    }

    /**
     * ���Ժ���
     */
    public static void main(String[] args) {
        GUIUtil.showPanel(CategoryPanel.instance);
    }
}
