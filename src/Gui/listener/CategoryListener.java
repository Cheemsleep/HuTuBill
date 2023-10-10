package Gui.listener;

import Entity.Category;
import Gui.panel.CategoryPanel;
import Gui.panel.DetailPanel;
import Service.CategoryService;
import Util.CenterPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CategoryListener监听器，增加，编辑和删除按钮都使用这个监听器。
 *
 * 1. 与监听器ToolBarListener 类似的，根据事件的发出源，判断是哪个按钮触发了这个监听器，并做相应的功能。
 *
 * 2. 如果是增加，弹出输入框，校验输入内容不为空后，通过CategoryService.add()添加到数据库。
 *
 * 3. 如果是编辑，弹出输入框，校验输入内容不为空后，根据CategoryPanel中getSelectedCategory()获取id, 然后再通过CategoryService.update更新到数据库
 *
 * 4. 如果是删除，首先判断是否有消费记录，如果有消费记录，则不能删除。 接着进行删除确认提示，确认后，通过CategoryService.delete()进行删除。
 *
 * 5. 最后调用p.updateData();更新数据
 */
public class CategoryListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryPanel panel = CategoryPanel.instance;
        DetailPanel detailPanel = DetailPanel.instance;
        JButton button = (JButton) e.getSource();
        if (button == panel.bAdd) {
            String name = JOptionPane.showInputDialog(null);
            if (name == null) {
                JOptionPane.showMessageDialog(panel,"分类名称不能为空");
                return;
            }
            new CategoryService().add(name);
        }
        if (button == panel.bEdit) {
            Category c = panel.getSelectedCategory();
            int id = c.getId();
            String name = JOptionPane.showInputDialog("修改分类名称",c.getName());
            if (name == null) {
                JOptionPane.showMessageDialog(panel,"分类名称不能为空");
                return;
            }
            new CategoryService().update(id,name);
        }
        if (button == panel.bDelete) {
            Category c = panel.getSelectedCategory();
            if (c.getRecordNumber() != 0) {
                JOptionPane.showMessageDialog(panel,"本分类下存在消费记录，无法删除");
                return;
            }
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(panel,"确认删除?")) return;
            int id = c.getId();
            new CategoryService().delete(id);
        }
        if (button == panel.bDetail) {
            //需要将监听事件绑定，否则永远获取的是第一个分类
            Category c = panel.getSelectedCategory();
            if (c.getRecordNumber() == 0) {
                JOptionPane.showMessageDialog(panel,"没有消费记录，无法查看详细");
                return;
            }
            detailPanel.updateData(c);
            Util.GUIUtil.showPanelNew(detailPanel,1);
        }
        panel.updateData();
    }
}
