package config;

import java.util.ArrayList;

/**
 * Created by jim on 16/6/12.
 */
public class Obj {

    static ArrayList<String>objlist=new ArrayList<>();
    static ArrayList<String>classList=new ArrayList<>();
    public static int getObjNameCount(){
        return objlist.size();
    }
    public static int getClassNOCount(){
        return classList.size();
    }
    public static ArrayList<String>getObjName(){
        return objlist;
    }

    public static ArrayList<String>getClssNo(){
        return classList;
    }
    public static void setClassNo(ArrayList<String> list){
        classList=list;
    }
    public static void setObjName(ArrayList<String> list){
        objlist=list;
    }
}
