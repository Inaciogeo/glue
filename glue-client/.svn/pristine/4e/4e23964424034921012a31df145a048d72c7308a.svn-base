package br.org.funcate.glue.service.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class AliasService {

	/**
	 * @param args
	 */
	private static final String URL = "jdbc:postgresql://localhost/glue";
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String USUARIO = "postgres";
	private static final String SENHA = "postgres";
	
//	private static final String URL = "jdbc:firebirdsql:localhost/3050:/var/lib/firebird/2.5/system/accsses.fdb";
//	private static final String DRIVER = "org.firebirdsql.jdbc.FBDriver";
//	private static final String USUARIO = "SYSDBA";
//	private static final String SENHA = "masterkey";

	
	public static Connection openConnection() throws SQLException {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USUARIO, SENHA);
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getMessage());
		}
	}

	public Vector<String>getAlias(double d) {
		String theme = String.valueOf(d) ;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Vector<String>result = new Vector<String>();
		
		try {
			con = openConnection();
			pstmt = (PreparedStatement) con.prepareStatement("SELECT a.\"NAME_COLUMN\" ,a.\"ALIAS\",a.\"THEME_ID\" FROM \"CIN_CONTROLE_INFO\" a WHERE \"THEME_ID\"="+theme);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result.add(rs.getString(1));
				result.add(rs.getString(2));
				result.add((String.valueOf(rs.getDouble(3))));	
			}
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
					con.close();
				} catch (Exception e) {

				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		AliasService banco = new AliasService();
		try {
			AliasService.openConnection();
			System.out.println(banco.getAlias(20));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
