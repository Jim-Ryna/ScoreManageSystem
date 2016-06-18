package company.utils;

/**
 * Created by jim on 16/6/12.
 */

import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public abstract class Util {
    public Util() {
    }

    public static JComponent alterJComponent(JComponent jComponent, String infosString) {
        JOptionPane jOptionPane = new JOptionPane();
        JOptionPane.showMessageDialog(jComponent, infosString);
        return jOptionPane;
    }

    public JComponent optionJComponent(JComponent jComponent){
        Object[] options={"确定","取消"};
        JOptionPane jOptionPane = new JOptionPane();
        int response = JOptionPane.showOptionDialog(jComponent, "您是否保存刚才的修改", null, JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
                options[0]);

        switch (response){
            case 0:
                setOKOption();
                System.out.println("option"+"do");
                break;
            case 1:
                setCancelOption();
                break;
        }
        return jOptionPane;
    }

    public abstract void setOKOption();
    public abstract void setCancelOption();
    public static Dimension getDimension() {
        return new Dimension(100, 25);
    }
}