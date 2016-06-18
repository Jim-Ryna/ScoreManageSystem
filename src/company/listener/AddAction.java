package company.listener;

/**
 * Created by jim on 16/6/12.
 */
import company.ScoresWindow;
import company.pannel.InputPannel;
import company.utils.Util;
import config.Obj;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTextField;

public class AddAction implements ActionListener {
    Statement statement;
    JTextField[] objTextFields;
    ArrayList<String> objName;

    public AddAction(ArrayList<String> objName, JTextField[] objTextFields) {
        this.objTextFields = objTextFields;
        this.objName = objName;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String noString = InputPannel.noJTextField.getText(0, 10);
            System.out.println("class" + InputPannel.classJComboBox.getSelectedItem());
            String nCommandString = "INSERT INTO student (NUMBER, name, class) VALUES (\'" + noString + "\',\'" + InputPannel.nameJTextField.getText() + "\',\'" + InputPannel.classJComboBox.getSelectedItem() + "\');";
            StringBuilder e2 = new StringBuilder();
            e2.append("INSERT INTO scores (NUMBER");

            int i;
            for(i = 0; i < Obj.getObjNameCount(); ++i) {
                e2.append(",objvalue" + i);
                e2.append(",objkey" + i);
            }

            e2.append(")values(");
            e2.append("\'" + InputPannel.noJTextField.getText() + "\',");

            for(i = 0; i < Obj.getObjNameCount(); ++i) {
                e2.append(this.objTextFields[i].getText());
                e2.append(",");
                e2.append("\'" + (String)this.objName.get(i) + "\'");
                if(i == Obj.getObjNameCount() - 1) {
                    break;
                }

                e2.append(",");
            }

            e2.append(");");
            System.out.println(e2);
            this.statement = ScoresWindow.connection.createStatement();
            this.statement.execute(nCommandString);
            this.statement.execute(e2.toString());
            this.statement.closeOnCompletion();
            Util.alterJComponent(InputPannel.inputPanel, "添加成功！");
            this.statement.closeOnCompletion();
        } catch (Exception var6) {
            Util.alterJComponent(InputPannel.inputPanel, "学号为10位！请重新输入！\n" + var6.toString() + "");
        }

    }
}