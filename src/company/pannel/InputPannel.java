package company.pannel;

/**
 * Created by jim on 16/6/12.
 */
import company.listener.AddAction;
import company.utils.Util;
import config.Obj;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputPannel {
    public static JButton addJButton = new JButton("确认");
    public static JTextField noJTextField = new JTextField();
    public static JTextField nameJTextField = new JTextField();
    public static JComboBox classJComboBox = new JComboBox();
    JTextField[] textFields = new JTextField[Obj.getObjNameCount()];
    public static JPanel inputPanel = new JPanel(false);

    public InputPannel() {
    }

    public void setClassJComboBox(JComboBox classJComboBox) {
        Iterator var2 = Obj.getClssNo().iterator();

        while(var2.hasNext()) {
            String s = (String)var2.next();
            classJComboBox.addItem(s);
        }

    }

    public JComponent inputJComponent() {
        JLabel nolJLabel = new JLabel("学号");
        JLabel nameJLabel = new JLabel("姓名");
        JLabel classJLabel = new JLabel("班级");
        JLabel scoresJLabel = new JLabel("成绩");
        ArrayList labels = new ArrayList();
        Iterator boxJPanel = Obj.getObjName().iterator();

        while(boxJPanel.hasNext()) {
            String i = (String)boxJPanel.next();
            labels.add(new JLabel(i));
        }

        this.setClassJComboBox(classJComboBox);
        Box studentLabelBox = Box.createVerticalBox();
        studentLabelBox.add(nolJLabel);
        studentLabelBox.add(Box.createVerticalGlue());
        studentLabelBox.add(nameJLabel);
        studentLabelBox.add(Box.createVerticalGlue());
        studentLabelBox.add(classJLabel);
        studentLabelBox.add(Box.createVerticalGlue());
        studentLabelBox.add(scoresJLabel);
        boxJPanel = labels.iterator();

        while(boxJPanel.hasNext()) {
            JLabel var12 = (JLabel)boxJPanel.next();
            studentLabelBox.add(Box.createVerticalGlue());
            studentLabelBox.add(var12);
        }

        Box studentFieldBox = Box.createVerticalBox();
        studentFieldBox.add(noJTextField);
        studentFieldBox.add(Box.createVerticalStrut(5));
        studentFieldBox.add(nameJTextField);
        studentFieldBox.add(Box.createVerticalStrut(5));
        studentFieldBox.add(classJComboBox);
        studentFieldBox.add(Box.createVerticalStrut(45));

        for(int var11 = 0; var11 < Obj.getObjNameCount(); ++var11) {
            this.textFields[var11] = new JTextField();
            studentFieldBox.add(this.textFields[var11]);
            if(var11 == Obj.getObjNameCount() - 1) {
                break;
            }

            studentFieldBox.add(Box.createVerticalStrut(10));
        }

        addJButton.addActionListener(new AddAction(Obj.getObjName(), this.textFields));
        Box baseBox = Box.createHorizontalBox();
        baseBox.add(studentLabelBox);
        baseBox.add(studentFieldBox);
        JPanel var13 = new JPanel(false);
        var13.setLayout(new FlowLayout());
        var13.add(baseBox);
        var13.add(addJButton);
        noJTextField.setPreferredSize(Util.getDimension());
        nameJTextField.setPreferredSize(Util.getDimension());

        for(int var14 = 0; var14 < Obj.getObjNameCount(); ++var14) {
            this.textFields[var14].setPreferredSize(Util.getDimension());
        }

        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(var13, "Center");
        return inputPanel;
    }
}
