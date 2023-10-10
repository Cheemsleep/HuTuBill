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
 * 在新增分类的时候无法显示中文
 * 破案了，mysql的默认编码是Latin，网上找了修改的方法，通过命令行把编码方式改为gbk，就可以了
 */
public class CategoryPanel extends WorkingPanel {
    static{
        GUIUtil.useLNF();
    }
    public static CategoryPanel instance = new CategoryPanel();

    //设置public属性
    public JButton bAdd = new JButton("新增");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");
    public JButton bDetail = new JButton("详细信息");

    String columnNames[] = new String[]{"分类名称","消费次数"};

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
        //updateData();//避免了初次启动时编辑与删除按钮可用的情况
    }

    /**
     * 为CategoryPanel新增加一个getSelectedCategory，方便获取JTable上当前选中的Category对象
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
     * 为三个按钮添加监听器
     */
    public void addListener() {
        CategoryListener listener = new CategoryListener();
        bAdd.addActionListener(listener);
        bEdit.addActionListener(listener);
        bDelete.addActionListener(listener);
        bDetail.addActionListener(listener);
    }

    /**
     * 测试函数
     */
    public static void main(String[] args) {
        GUIUtil.showPanel(CategoryPanel.instance);
    }
}
