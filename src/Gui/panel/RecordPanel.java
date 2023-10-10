package Gui.panel;

import Entity.Category;
import Entity.Record;
import Gui.listener.RecordListener;
import Gui.model.CategoryComboBoxModel;
import Service.CategoryService;
import Util.ColorUtil;
import org.jdesktop.swingx.JXDatePicker;

import Util.GUIUtil;
import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * RecordPanel ���Ѽ�¼����������¸Ķ�
 * 1. �̳���WorkingPanel���Ӷ������ṩaddListener()��updateData()����
 * 2. �ṩgetSelectedCategory()���ڻ�ȡ��ǰѡ�еķ������
 * 3. addListener() ����ť��Ӽ���
 * 4. updateData()�������ݣ���Ҫ�Ǹ����������еķ�����Ϣ�������ø����������Ϣ���ã��Լ��ý���ͣ���ڽ����������
 */
public class RecordPanel extends WorkingPanel {
    static{
        GUIUtil.useLNF();
    }
    public static RecordPanel instance = new RecordPanel();

    JLabel lSpend = new JLabel("����(RMB)");
    JLabel lCategory = new JLabel("����");
    JLabel lComment = new JLabel("��ע");
    JLabel lDate = new JLabel("����");

    public JTextField tfSpend = new JTextField("0");

    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
    public JComboBox<Category> cbCategory = new JComboBox<>(cbModel);
    public JTextField tfComment = new JTextField();
    public JXDatePicker datePicker = new JXDatePicker(new Date());

    JButton bSubmit = new JButton("��һ��");

    public RecordPanel() {
        GUIUtil.setColor(ColorUtil.grayColor, lSpend,lCategory,lComment,lDate);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);
        JPanel pInput = new JPanel();
        JPanel pSubmit = new JPanel();
        int gap = 40;

        pInput.setLayout(new GridLayout(4,2,gap,gap));
        pInput.add(lSpend);
        pInput.add(tfSpend);
        pInput.add(lCategory);
        pInput.add(cbCategory);
        pInput.add(lComment);
        pInput.add(tfComment);
        pInput.add(lDate);
        pInput.add(datePicker);
        pSubmit.add(bSubmit);

        this.setLayout(new BorderLayout());
        this.add(pInput,BorderLayout.NORTH);
        this.add(pSubmit,BorderLayout.CENTER);

        addListener();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(RecordPanel.instance);
    }

    public Category getSelectedCategory() {
        return (Category) cbCategory.getSelectedItem();
    }

    @Override
    public void updateData() {
        cbModel.cs = new CategoryService().list();
        cbCategory.updateUI();
        resetInput();
        tfSpend.grabFocus();
    }

    public void resetInput() {
        tfSpend.setText("0");
        tfComment.setText("");
        if (cbModel.cs.size() != 0) {
            cbCategory.setSelectedIndex(0);
        }
        datePicker.setDate(new Date());
    }

    @Override
    public void addListener() {
        RecordListener listener = new RecordListener();
        bSubmit.addActionListener(listener);
    }
}
