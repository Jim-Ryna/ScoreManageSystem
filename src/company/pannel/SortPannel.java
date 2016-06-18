package company.pannel;

/**
 * Created by jim on 16/6/12.
 */
import company.listener.SelectAction;
import company.listener.SortAction;
import company.model.myModel;
import config.Obj;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SortPannel {
    public static JButton sortJButton = new JButton("查询");
    private int totalItem = 4 + Obj.getObjNameCount();
    public Object[] classnameObject;
    public static Integer rows = Integer.valueOf(0);
    public static JPanel sortJPanel = new JPanel(false);

    public SortPannel() {
        this.classnameObject = new Object[this.totalItem];
    }

    public JComponent sortJComponent() {
        this.setClassnameObject();
        JComboBox jComboBox = new JComboBox(this.getSelect());
        myModel tableModel = new myModel(this.classnameObject, rows.intValue());
        JTable table = new JTable(tableModel);
        table.setEnabled(false);
        sortJPanel.setLayout(new BorderLayout());
        sortJPanel.add(new JScrollPane(table), "Center");
        SortAction sortAction = new SortAction(tableModel, table);
        JPanel sortButtonJPanel = new JPanel(false);
        sortButtonJPanel.setLayout(new FlowLayout());
        sortJButton.addActionListener(sortAction);
        jComboBox.addActionListener(new SelectAction(this.classnameObject, tableModel));
        sortButtonJPanel.add(jComboBox);
        sortButtonJPanel.add(sortJButton);
        sortJPanel.add(sortButtonJPanel, "North");
        return sortJPanel;
    }

    private void setClassnameObject() {
        byte i = 0;
        this.classnameObject[i] = new String("班级");
        int var3 = i + 1;
        this.classnameObject[var3] = new String("学号");
        ++var3;
        this.classnameObject[var3] = new String("姓名");

        for(int j = 0; j < Obj.getObjNameCount(); ++j) {
            ++var3;
            this.classnameObject[var3] = new String((String)Obj.getObjName().get(j));
        }

        ++var3;
        this.classnameObject[var3] = new String("总成绩");
    }

    private String[] getSelect() {
        String[] select = new String[1 + Obj.getObjNameCount()];
        int i = 0;
        select[i] = new String("总情况统计");

        String s;
        for(Iterator var3 = Obj.getObjName().iterator(); var3.hasNext(); select[i] = new String(s + "统计")) {
            s = (String)var3.next();
            ++i;
        }

        return select;
    }
}