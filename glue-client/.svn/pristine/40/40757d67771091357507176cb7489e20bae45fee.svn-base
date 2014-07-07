package br.org.funcate.glue.model;

import java.io.Serializable;

/** \file Database.java
 * This file defines the properties of a database connection
 */

/**
 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
 * 
 *         \brief Database Class that represents a model of database connection
 */

public class Database implements Serializable {

	private static final long serialVersionUID = -5946965896751108707L;
	/** < Attribute type long > */
	private String host;
	/** < String that contains the host address */
	private String user;
	/** < String that contains the user name */
	private String password;
	/** < String that contains the user password */
	private String database;
	/** < String that contains which database is used */
	private Integer port;
	/** < String that contains the database server port */
	private Integer dbType;

	/** < String that contains the database type */

	/**
	 * @name Access
	 * 
	 *       Functions that return the state of the attributes of the Database
	 */

	// @{

	/**
	 * \brief Function that returns the host of the database
	 * 
	 * @return A String containing the host of the database
	 */

	public String getHost() {
		return host;
	}

	/**
	 * \brief Function that returns the user of the database
	 * 
	 * @return A String containing the user of the database
	 */

	public String getUser() {
		return user;
	}

	/**
	 * \brief Function that returns the password of the database
	 * 
	 * @return A String containing the password of the database
	 */

	public String getPassword() {
		return password;
	}

	/**
	 * \brief Function that returns which database is used
	 * 
	 * @return A String containing which database is used
	 */

	public String getDatabase() {
		return database;
	}

	/**
	 * \brief Function that returns the port of the database
	 * 
	 * @return A String containing the port of the database
	 */

	public Integer getPort() {
		return port;
	}

	/**
	 * \brief Function that returns the type of the database
	 * 
	 * @return A String containing the type of the database
	 */

	public Integer getDbType() {
		return dbType;
	}

	// @}

	/**
	 * @name Set
	 * 
	 *       Functions that modify the state of the attributes
	 */

	// @{

	/**
	 * \brief Function that modifies the host of the database
	 * 
	 * @param host
	 *            A string containing the value of the new host
	 */

	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * \brief Function that modifies the user of the database
	 * 
	 * @param user
	 *            A string containing the value of the new user
	 */

	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * \brief Function that modifies the password of the database
	 * 
	 * @param host
	 *            A string containing the value of the new password
	 */

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * \brief Function that modifies the database that is being used
	 * 
	 * @param host
	 *            A string containing the value of the new database being used
	 */

	public void setDatabase(String database) {
		this.database = database;
	}

	/**
	 * \brief Function that modifies the port of the database
	 * 
	 * @param port
	 *            A string containing the value of the new port
	 */

	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * \brief Function that modifies the type of the database
	 * 
	 * @param dbType
	 *            A string containing the value of the new type
	 */

	public void setDbType(Integer dbType) {
		this.dbType = dbType;
	}

	// @}

	/**
	 * @name Override
	 * 
	 *       Functions that were inherited and overridden
	 */

	// @{

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Equals method override. Date.:
	 *         21/06/2010
	 */
	public boolean equals(java.lang.Object o) {
		if (o instanceof Database) {
			Database aux = (Database) o;

			if (this.host.equals(aux.getHost()) && this.user.equals(aux.getUser()) && this.password.equals(aux.getPassword())
					&& this.database.equals(aux.getDatabase()) && this.port.equals(aux.getPort()) && this.dbType.equals(aux.getDbType())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief ToString method override.
	 *         Date.: 21/06/2010
	 */
	public String toString() {
		return "\n\nDatabase object\nSerial Version UID.: " + serialVersionUID + "\nHost.: " + host + "\nUser.: " + user + "\nPassword.: "
				+ password + "\nDatabase.: " + database + "\nPort.: " + port + "\nDatabase Type.: " + dbType;
	}

	// @ }
}
