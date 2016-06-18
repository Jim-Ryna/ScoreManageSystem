

package company;

import company.pannel.InputPannel;
import company.pannel.QueryPannel;
import company.pannel.SortPannel;
import company.utils.Util;
import config.Obj;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.sql.*;

public class ScoresWindow extends JFrame {
    private static final long serialVersionUID = -6924196421756933199L;
    public static Connection connection;

    public ScoresWindow(String titleString) {
        super(titleString);
        this.getConnectDataBase();
        this.setConfig();
        JTabbedPane jTabbedPane = new JTabbedPane();
        JComponent inputPanel = (new InputPannel()).inputJComponent();
        JComponent queryPanel = (new QueryPannel()).queryJComponent();
        JComponent sortPanel = (new SortPannel()).sortJComponent();
        jTabbedPane.addTab("输入成绩", inputPanel);
        jTabbedPane.addTab("查询成绩", queryPanel);
        jTabbedPane.addTab("成绩排序", sortPanel);
        this.setLayout(new GridLayout(1, 1));
        this.add(jTabbedPane);
        jTabbedPane.setTabLayoutPolicy(1);
        this.setBounds(500, 100, 500, 600);
        this.setVisible(true);
        this.validate();
        this.setDefaultCloseOperation(3);
    }

    private void setConfig() {
        this.setClass();
        this.setObj();
    }

    private void setClass() {
        try {
            ArrayList e = new ArrayList();
            Statement statement = connection.createStatement();
            String commondString = "select classno from class order by classno ASC";
            ResultSet resultSet = null;
            resultSet = statement.executeQuery(commondString);

            while(resultSet.next()) {
                e.add(resultSet.getString("classno"));
            }

            Obj.setClassNo(e);
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

    }

    private void setObj() {
        try {
            ArrayList e = new ArrayList();
            Statement statement = connection.createStatement();
            String commondString = "select objname from objects";
            ResultSet resultSet = null;
            resultSet = statement.executeQuery(commondString);

            while(resultSet.next()) {
                e.add(resultSet.getString("objname"));
            }

            Obj.setObjName(e);
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

    }

    protected void getConnectDataBase() {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("MySQL驱动成功加载！");
            } catch (ClassNotFoundException var2) {
                System.out.println(var2);
            }

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8", "root", "201601");
            System.out.println("已成功连接到数据库！");
            Util.alterJComponent(InputPannel.inputPanel, "已成功连接到数据库！");
        } catch (Exception var3) {
            System.out.println(var3);
            Util.alterJComponent(InputPannel.inputPanel, "错误！\n" + var3.toString() + "");
        }

    }
}
