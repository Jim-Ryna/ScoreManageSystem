package company.listener;

/**
 * Created by jim on 16/6/12.
 */
import company.ScoresWindow;
import company.pannel.SortPannel;
import company.utils.Util;
import config.Obj;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SortAction implements ActionListener {
    Statement statement;
    ResultSet resultSet1;
    ResultSet resultSet2;
    private static int i = 0;
    String sCommandString1;
    String sCommandString3;
    String sCommandString2;
    DefaultTableModel tableModel;
    JTable table;
    Object[] object1 = new Object[4 + Obj.getObjNameCount()];
    Object[] object2 = new Object[5];

    public SortAction(DefaultTableModel tableModel, JTable table) {
        this.tableModel = tableModel;
        this.table = table;
        this.sCommandString1 = "SELECT * FROM scores ORDER BY total DESC";
    }

    public static void setitem(int item) {
        i = item;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if(this.tableModel.getRowCount() > 0) {
                this.tableModel = (DefaultTableModel)this.table.getModel();
                this.tableModel.setRowCount(SortPannel.rows.intValue() * 2);
                this.table.setModel(this.tableModel);
                this.table.repaint();
            }

            if(0 != i) {
                --i;
                this.statement = ScoresWindow.connection.createStatement();
                this.sCommandString3 = "SELECT NUMBER,objvalue" + i + " FROM scores ORDER BY " + "objvalue" + i + " DESC";
                System.out.println("sCommandString3  " + this.sCommandString3);
                this.resultSet1 = this.statement.executeQuery(this.sCommandString3);

                while(this.resultSet1.next()) {
                    this.statement = ScoresWindow.connection.createStatement();
                    this.sCommandString2 = "select name,class from student where NUMBER=\'" + this.resultSet1.getString("NUMBER") + "\'";
                    System.out.println(this.sCommandString2);
                    this.resultSet2 = this.statement.executeQuery(this.sCommandString2);
                    this.resultSet2.next();
                    this.tableModel.addRow(new Object[]{this.resultSet2.getString("class"), this.resultSet1.getString("NUMBER"), this.resultSet2.getString("name"), this.resultSet1.getString("objvalue" + i), null, null, null});
                }
            } else {
                this.statement = ScoresWindow.connection.createStatement();
                this.resultSet1 = this.statement.executeQuery(this.sCommandString1);
                System.out.println(this.resultSet1);

                while(this.resultSet1.next()) {
                    System.out.println(this.resultSet1);
                    this.statement = ScoresWindow.connection.createStatement();
                    this.sCommandString2 = "select name,class from student where NUMBER=\'" + this.resultSet1.getString("NUMBER") + "\'";
                    System.out.println(this.sCommandString2);
                    this.resultSet2 = this.statement.executeQuery(this.sCommandString2);
                    this.resultSet2.next();
                    byte e2 = 0;
                    this.object1[e2] = new String(this.resultSet2.getString("class"));
                    int var5 = e2 + 1;
                    this.object1[var5] = new String(this.resultSet1.getString("NUMBER"));
                    ++var5;
                    this.object1[var5] = new String(this.resultSet2.getString("name"));

                    for(int j = 0; j < Obj.getObjNameCount(); ++j) {
                        ++var5;
                        this.object1[var5] = new String(this.resultSet1.getString("Objvalue" + j));
                    }

                    this.tableModel.addRow(this.object1);
                }
            }

            this.statement.closeOnCompletion();
        } catch (Exception var4) {
            Util.alterJComponent(SortPannel.sortJPanel, "错误！\n" + var4.toString() + "");
        }

    }
}
