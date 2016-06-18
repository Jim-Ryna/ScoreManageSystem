package company.model;

/**
 * Created by jim on 16/6/12.
 */
import javax.swing.table.DefaultTableModel;

public class myModel extends DefaultTableModel {
    private int editLock=1;   //1表示被锁,0表示开锁
    private int row;  //可编辑的行
    public myModel(Object[] var1, int var2) {
        super(var1, var2);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        System.out.println("r"+row+"c"+column);
        if(1==editLock) return false;
        else if(0==editLock && this.row==row && column>=3){
            System.out.print("do");
            return true;
        }
        return false;
    }

    public int getEditLock() {
        return editLock;
    }

    public void setEditble(int row) {
        this.editLock = 0;
        this.row=row;
    }

    public void setEditLock(int editLock){
        this.editLock = editLock;
    }

    public void removeAll(){
        while(getRowCount()>0){
            removeRow(getRowCount()-1);
        }
    }
}
