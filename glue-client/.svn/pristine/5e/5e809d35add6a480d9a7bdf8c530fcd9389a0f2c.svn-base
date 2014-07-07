package br.org.funcate.glue.model;

import java.io.Serializable;

/**
 * @author Siqueira, Felipe V. and Ribeiro, Stephen M. and Rocha, Fernanda.
 * 
 *         \brief Class that represents a Theme Representation
 */
public class Representation extends Object implements Serializable {

	private static final long serialVersionUID = -7752500017001910311L;
	/** < Attribute type long > */

	/**
	 * Polygon constant representation id
	 */

	public static final int POLYGON = 1;
	/**
	 * Line constant representation id
	 */

	public static final int LINE = 2;
	/**
	 * Point constant representation id
	 */
	public static final int POINT = 4;
	/**
	 * Text constant representation id
	 */
	public static final int TEXT = 128;
	/**
	 * Cell constant representation id
	 */
	public static final int CELL = 256;
	/**
	 * Raster constant representation id
	 */
	public static final int RASTER = 512;

	/**
	 * Representation name
	 */
	private String name;

	/**
	 * @name Constructors
	 * 
	 *       The functions that create the box
	 */

	// @{

	/**
	 * \brief An empty constructor used to simple instantiate the object
	 * 
	 * @return A new representation
	 */

	public Representation() {

	}

	// @ }

	/**
	 * @name Access
	 * 
	 *       Functions that return the state of the attributes of the
	 *       representation
	 */

	// @{

	/**
	 * \brief Function that returns the name of the representation
	 * 
	 * @return A String containing the name of the representation
	 */

	public String getName() {
		return name;
	}

	// @ }

	/**
	 * @name Set
	 * 
	 *       Functions that modify the state of the attributes
	 */

	// @{

	/**
	 * \brief Function that modifies the name of the representation
	 * 
	 * @param name
	 *            A string containing the value of the new name
	 */

	public void setName(String name) {
		this.name = name;
	}

	// @ }

	/**
	 * @name Override
	 * 
	 *       Functions that were inherited and overridden
	 */

	// @{

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief Equals method override. Date.:
	 *         22/06/2010
	 */
	public boolean equals(java.lang.Object o) {
		if (o instanceof Representation) {
			Representation aux = (Representation) o;

			if (this.name.equals((aux).getName())) {
				return true;
			}

		}

		return false;
	}

	/**
	 * @author MOREIRA, Vinicius Fernandes \brief ToString method override.
	 *         Date.: 22/06/2010
	 */
	public String toString() {
		return "\n\nRepresentation Object\nSerial Version UID.: " + serialVersionUID + "\nName.: " + name;
	}

	// @ }
}