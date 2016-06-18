package company.listener;

/**
 * Created by jim on 16/6/12.
 */
import company.ScoresWindow;
import company.model.myModel;
import company.pannel.QueryPannel;
import company.utils.Util;
import config.Obj;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryAction implements ActionListener {
    Statement statement;
    ResultSet resultSet1;
    myModel model;

    public QueryAction(myModel myModel) {
        this.model = myModel;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            model.removeAll();
            Object name = null;
            String noString = QueryPannel.queryNOJTextField.getText();
            StringBuilder sb = new StringBuilder("select student.class, student.NUMBER,name,  ");

            for(int obj = 0; obj < Obj.getObjNameCount(); ++obj) {
                sb.append("objvalue" + obj);
                if(obj == Obj.getObjNameCount() - 1) {
                    break;
                }

                sb.append(",");
            }

            sb.append(" from student,scores where student.NUMBER like \'" + noString + "%\' and scores.NUMBER=student.NUMBER");
            String commandString1 = sb.toString();
            System.out.println(commandString1.toString());
            this.statement = ScoresWindow.connection.createStatement();
            this.resultSet1 = this.statement.executeQuery(commandString1);
            Object[] var13 = new Object[3 + Obj.getObjNameCount()];
            while(resultSet1.next()) {
                for (int i = 0; i < 3 + Obj.getObjNameCount(); i++) {
                    var13[i] = this.resultSet1.getString(i + 1);
                    System.out.print(var13[i] + "");
                }
                this.model.addRow(var13);
            }
            this.statement.closeOnCompletion();
        } catch (Exception var12) {
            Util.alterJComponent(QueryPannel.queryJPanel, "学号为10位！请重新输入！\n" + var12.toString() + "");
        }

    }
}

