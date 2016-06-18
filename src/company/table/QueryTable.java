package company.table;

import company.utils.ComboBoxCellEditor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 * Created by jim on 16/6/14.
 */
public class QueryTable extends JTable{
    public QueryTable(DefaultTableModel model){
        super(model);
    }
    public TableCellEditor getCellEditor(int row, int column) {
        // TODO Auto-generated method stub
        System.out.print(row+" "+column+" ");
        if(row ==1 && column == 1){
            return new ComboBoxCellEditor();
        }
        return super.getCellEditor(row, column);
    }
}
