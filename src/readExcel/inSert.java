package readExcel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.Statement;

import chinhSuaThongTin.ChinhSuaThongTin;
import database.Database;

public class inSert {
	public String driver = ChinhSuaThongTin.driver;
	public String url = ChinhSuaThongTin.url;
	public String user = ChinhSuaThongTin.user;
	public String password = ChinhSuaThongTin.password;
	
	Connection connection;
	Database database;
	
	public inSert()
	{
		try {
			Class.forName(driver);
			this.connection = (Connection) DriverManager.getConnection(url, user, password);
			database = new Database();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean run(String table,Vector cot, Vector value){
        try {
            Statement statement =  (Statement) connection.createStatement();
            String sql = "insert into " + table + "(";
            for(int i = 0; i < cot.size(); i++)
            {
                sql += cot.elementAt(i).toString() + ",";
            }
            sql += ")";
            sql = sql.replace(",)", ")");
            sql = sql + " values(";
            for(int i = 0; i < value.size(); i++)
            {
                sql += "'" + value.elementAt(i).toString() + "',";
            }
            sql += ")";
            sql = sql.replace("',)", "')");
            if(statement.executeUpdate(sql) >= 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         
        return false;
    }

}
