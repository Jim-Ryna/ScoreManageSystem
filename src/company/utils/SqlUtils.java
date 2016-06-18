package company.utils;

import company.ScoresWindow;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by jim on 16/6/18.
 */
public class SqlUtils {
    static Statement statement;
    public static void deleteDataFromMySQL(String key, String value, String table){
        String command="delete from "+table+" where "+key+"="+"'"+value+"'";
        try {
            statement= ScoresWindow.connection.createStatement();
            statement.execute(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateDataToMySQL(String table, String key, String value, String condition, String conzhi){
        String command="update "+table+" set "+key+"="+value+" where "+condition+"="+"'"+conzhi+"'"+";";
        System.out.print(command);
        try {
            statement= ScoresWindow.connection.createStatement();
            statement.execute(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getColName(){
        return null;
    }
}
