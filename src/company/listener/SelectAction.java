package company.listener;

/**
 * Created by jim on 16/6/12.
 */
import company.listener.SortAction;
import company.pannel.SortPannel;
import config.Obj;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class SelectAction implements ActionListener {
    String selected = null;
    int item = 0;
    DefaultTableModel tableModel;
    Object[] classnameObject;

    public SelectAction(Object[] classnameObject, DefaultTableModel tableModel) {
        this.tableModel = tableModel;
        this.classnameObject = classnameObject;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        JComboBox jComboBox = (JComboBox)actionEvent.getSource();
        this.selected = (String)jComboBox.getSelectedItem();
        if(this.selected.equals("总情况统计")) {
            this.item = 0;
            System.out.println("zong");
            this.tableModel.setColumnIdentifiers(this.classnameObject);
            this.tableModel.setRowCount(SortPannel.rows.intValue());
        } else {
            for(int i = 0; i < Obj.getObjNameCount(); ++i) {
                if(this.selected.equals((String)Obj.getObjName().get(i) + "统计")) {
                    this.item = i + 1;
                    Object[] clonameObject = new Object[]{"班级", "学号", "姓名", Obj.getObjName().get(i)};
                    this.tableModel.setColumnIdentifiers(clonameObject);
                    this.tableModel.setRowCount(SortPannel.rows.intValue());
                }
            }
        }

        SortAction.setitem(this.item);
    }
}

