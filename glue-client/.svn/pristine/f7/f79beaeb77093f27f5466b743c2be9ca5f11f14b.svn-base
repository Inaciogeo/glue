package br.org.funcate.glue.service.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ServiceLocator {

	private ServiceLocator() {
	}

	public static Connection getConnection(String dataSouce)
			throws NamingException, SQLException, NullPointerException {
		Connection result = null;
		InitialContext initialContext;
		Context context;
		DataSource dataSource;
		initialContext = new InitialContext();
		context = (Context) initialContext.lookup("java:comp/env");
		dataSource = (DataSource) context.lookup(dataSouce);
		result = dataSource.getConnection();
		if (result == null) {
			throw new NullPointerException("Conexão nula para: " + dataSouce);
		}
		return result;
	}
	
	public static void releaseConnection(Connection conn) {
		if(conn == null) return;
		try {
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
