package br.org.funcate.glue.service.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import br.org.funcate.glue.model.Database;
import br.org.funcate.glue.model.UserType;

public class SQLService {
	
	
	private static String host;
	private static String user;
	private static String password;
	private static String database;
	private static Database db;
	//private static String port;
	private static Connection conn;
	private static String dbType;
	private static Statement stm;
	private static ResultSet rs;
	private final static UserType userType = UserType.ADMIN;

	public static void connect(){
		
		db = DatabaseConnection.getDatabase(userType.toString());
		host = db.getHost();
		user = db.getUser();
		password = db.getPassword();
		database = db.getDatabase();
		//port = db.getPort().toString();
		dbType = db.getDbType().toString();

		try {
			if(dbType.equals("2")){
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection("jdbc:postgresql://"+host+"/"+database+"",user,password);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static ResultSet buildSelect(String sql){
		try {
			stm =conn.createStatement();
			rs = stm.executeQuery(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null)
					conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs;	
	}
}
