package br.org.funcate.glue.model;

/** \file UserType.java
 * This file contains the types of the users
 */

/**
 * \brief UserType In this class are defined the possible types of users.
 * \author André Gomes de Oliveira \author Paulo Renato Morais Oliveira
 */

public enum UserType {

	/** < enum value Admin. > */

	ADMIN,

	/** < enum value DEFAULT. > */

	DEFAULT,

	/** < enum value WEB_USER. > */

	WEB_USER;

	/**
	 * @name Override
	 * 
	 *       Functions that were inherited and overridden
	 */

	// @ {

	/**
	 * \brief A method that verify the type of user and returns a string with a
	 * matching name
	 */

	public String toString() {
		switch (this) {

		case DEFAULT:

			/** < enum value DEFAULT returns the string "Default". > */

			return "Default";

		case WEB_USER:

			/** < enum value WEB_USER returns the string "Web User". > */

			return "Web";

		case ADMIN:

			/** < enum value ADMIN returns the string "Admin". > */

			return "Admin";
		}
		return null;
	}

	// @ }

}
