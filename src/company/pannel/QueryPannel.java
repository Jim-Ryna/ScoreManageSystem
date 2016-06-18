package company.pannel;

/**
 * Created by jim on 16/6/12.
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import company.listener.QueryAction;
import company.model.myModel;
import company.table.QueryTable;
import company.utils.SqlUtils;
import company.utils.Util;
import config.Obj;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellEditor;

public class QueryPannel{
    JButton queryJButton = new JButton("查询");
    public static JTextField queryNOJTextField = new JTextField(10);
    public static JPanel queryJPanel = new JPanel(false);
    Object[] classnameObject;
    myModel model;
    JTable jTable;

    static int INIT=-1;
    static int RESET=-1;
    static int editedRow=INIT; //记录被编辑过的行
    ArrayList<Integer> changeList=new ArrayList<Integer>();//记录修改过的列
    Map<Integer, String> preModel=new HashMap<>();//记录发生修改前的行信息,以备恢复
    public QueryPannel() {
        int totalItem = 3 + Obj.getObjNameCount();
        this.classnameObject = new Object[totalItem];
        this.setClassnameObject();
        this.model = new myModel(this.classnameObject, 0);
        this.jTable = new JTable(this.model);

        jTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(jTable.getSelectedRow() != editedRow && editedRow != INIT){
                    new Util() {
                        @Override
                        public void setOKOption() {
                            changeData(changeList);
                        }

                        @Override
                        public void setCancelOption() {
                            reverse();
                            System.out.println("doextra");
                            changeList.clear();
                            editedRow=RESET;
                            model.setEditLock(1);
                        }
                    }.optionJComponent(queryJPanel);
                }
                if(e.getButton()==MouseEvent.BUTTON3){
                    System.out.println("mouse"+jTable.getSelectedRow()+jTable.getSelectedColumn());
                    JPopupMenu menu=new JPopupMenu(){
                    };
                    JMenuItem jmi1=new JMenuItem("删除");
                    JMenuItem jmi2=new JMenuItem("修改");
                    JMenuItem jmi3=new JMenuItem("保存");
                    jmi1.addActionListener(e1 -> {
                        String no= (String) model.getValueAt(jTable.getSelectedRow(), 1);
                        System.out.println(no);
                        SqlUtils.deleteDataFromMySQL("NUMBER", no, "scores");
                        SqlUtils.deleteDataFromMySQL("NUMBER", no, "student");
                        model.removeRow(jTable.getSelectedRow());
                    });
                    jmi2.addActionListener(e1 -> {
                        System.out.print("修改");
                        model.setEditble(jTable.getSelectedRow());
                        for(int i=3; i<model.getColumnCount(); i++){
                            preModel.put(i, (String) model.getValueAt(jTable.getSelectedRow(), i));
                        }
                        model.addTableModelListener(e2 -> {
                            editedRow=jTable.getEditingRow();
                            System.out.println("change");
                            System.out.println(""+e2.getColumn());
                            changeList.add(e2.getColumn());

                        });
                    });
                    jmi3.addActionListener(e1 -> {
                        changeData(changeList);
                    });
                    menu.add(jmi1);
                    menu.add(jmi2);
                    menu.add(jmi3);
                    menu.show(queryJPanel, e.getX(), e.getY());

                }

            }
        });
    }

    private void reverse() {
        int row=editedRow;
        for(int i=3; i<model.getColumnCount();i++){
            //System.out.println("edit"+editedRow);
            model.setValueAt(preModel.get(i), row, i);
        }
        preModel.clear();
    }

    private void changeData(ArrayList<Integer> changeList){
        if(changeList.size()!=0){
            for(int i:changeList){
                SqlUtils.updateDataToMySQL("scores", "objvalue"+String.valueOf(i-3), (String) model.getValueAt(editedRow, i),"NUMBER", (String) model.getValueAt(editedRow, 1));
            }
            Util.alterJComponent(queryJPanel, "保存成功");
            changeList.clear();
            model.setEditLock(1);
            editedRow=RESET;
        }
    }
    private void setClassnameObject() {
        byte i = 0;
        this.classnameObject[i] = new String("班级");
        int var3 = i + 1;
        this.classnameObject[var3] = new String("学号");
        ++var3;
        this.classnameObject[var3] = new String("姓名");

        for (int j = 0; j < Obj.getObjNameCount(); ++j) {
            ++var3;
            this.classnameObject[var3] = new String((String) Obj.getObjName().get(j));
        }

    }

    public JComponent queryJComponent() {
        JLabel noLabel = new JLabel("学号：");
        queryNOJTextField.setPreferredSize(Util.getDimension());
        queryJPanel.setLayout(new BorderLayout());
        JPanel northPanel = new JPanel(false);
        northPanel.add(noLabel);
        northPanel.add(queryNOJTextField);
        this.queryJButton.addActionListener(new QueryAction(this.model));
        northPanel.add(this.queryJButton);
        JScrollPane scrollPane = new JScrollPane(this.jTable);
        queryJPanel.add(scrollPane, "Center");
        queryJPanel.add(northPanel, "North");
        return queryJPanel;
    }

}
