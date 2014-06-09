package br.org.funcate.glue.service.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.org.funcate.glue.model.Database;
import br.org.funcate.glue.model.UserType;

/**
 * \brief This Class defines the attributes and the functions required to handle a database connection
 * 
 * @author Siqueira, Felipe V.
 * @author Ribeiro, Stephen M.
 */



public class DatabaseConnection {
	
	/**
	 * \brief Function that returns the database obtained with the connection
	 * 
	 * @return The database obtained with the connection
	 */
	
	
	public static Database getDatabase(String userType){
		
	
		//DatabaseConnectionSettings dataSettingsview = new DatabaseConnectionSettings();
		
		//dataSettingsview.show();
		
		File xmlFile = new File("databaseConfig.xml");
		
	if(!xmlFile.exists()){
		
		return null;
		
	}
	
////			try {
//				/*DatabaseConnectionSettings dbWindow = new DatabaseConnectionSettings();	
//				bdConn = new DatabaseConnectionSettingsController(dbWindow);
//				bdConn.openConfigUI();
//				bdConn.getDatabase();
//				*/
//				
//				//DatabaseConnectionSettings dbWindow = new DatabaseConnectionSettings();
//				//dbWindow.show();
//				
//				
//				//createXMLDatabaseConfig("192.168.4.2", "postgres", "postgres", "glue_421_stress_test", "5432", 2);
//		
//			
//		}
//	
		
		Database database = new Database();
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
	
			docBuilder = docBuilderFactory.newDocumentBuilder();
	
			Document doc = null;
			
			if(userType.equals(UserType.ADMIN.toString())){
				InputStream is = new FileInputStream("databaseConfig.xml");//DatabaseConnection.class.getResourceAsStream("DatabaseConfig.xml");
				doc = docBuilder.parse(is);
			}
			else if(userType.equals(UserType.WEB_USER.toString())){
				InputStream is = new FileInputStream("databaseConfig.xml");//DatabaseConnection.class.getResourceAsStream("./DatabaseConfig.xml");
				doc = docBuilder.parse(is);			}
			
			// normalize text representation
			doc.getDocumentElement().normalize();
			// Find "image" tags
			NodeList tagsReportElement = doc.getElementsByTagName("database");
			NamedNodeMap nnm = tagsReportElement.item(0).getAttributes();
	
			database.setHost(nnm.getNamedItem("host").getNodeValue());
			database.setUser(nnm.getNamedItem("user").getNodeValue());
			database.setPassword(nnm.getNamedItem("password").getNodeValue());
			database.setDatabase(nnm.getNamedItem("database").getNodeValue());
			database.setPort(Integer.parseInt(nnm.getNamedItem("port")
					.getNodeValue()));
			database.setDbType(Integer.parseInt(nnm.getNamedItem("dbType")
					.getNodeValue()));
			
			
		} catch (ParserConfigurationException e) {
			throw new RuntimeException("Ocorreu um erro ao retornar o banco de dados. ", e);
		} catch (SAXException e) {
			throw new RuntimeException("Ocorreu um erro ao retornar o banco de dados. ", e);
		} catch (IOException e) {
			throw new RuntimeException("Ocorreu um erro ao retornar o banco de dados. ", e);
		}
		return database;
	}


	@SuppressWarnings("unused")
	public static void createXMLDatabaseConfig(String host, String password, String user, String database, String port, int dbType) throws IOException{
		
		 OutputStream os = new FileOutputStream("databaseConfig.xml");
	     OutputStreamWriter osw = new OutputStreamWriter(os);
	     BufferedWriter bw = new BufferedWriter(osw);
	 
	     bw.write("<terrajava>");
	     bw.write("<database host='"+host+"' user='"+user+"' password='"+password+"' database='"+database+"' port='"+port+"' dbType='"+ dbType +"'></database>");
	     bw.write("</terrajava>");

	     
	     bw.close();
		
	}
	
	static boolean hasDataBaseConfig(){
		File xmlFile = new File("databaseConfig.xml");
		
		if(!xmlFile.exists()){
			return false;
		}
		return true;
	}

}
