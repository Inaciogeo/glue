package br.org.funcate.glue.model;

import java.io.Serializable;

/** \file Coord.java
 * This file defines x and y point of a coordinate
 */

/**
 * \brief Coord This class defines the x and y points of a coordinate
 * 
 * @author Ribeiro, Stephen M.
 */

public class Coord implements Serializable {

	/** < Attribute type Long > */

	private static final long serialVersionUID = -2636748256464401940L;

	/** < Attribute type Double > */

	private Double x = 0.0;

	/** < Attribute type Double > */

	private Double y = 0.0;

	/**
	 * @name Constructors
	 * 
	 *       The functions that create the Coordinate
	 */

	// @{

	/**
	 * \brief An empty constructor used to simple instantiate the object
	 * 
	 * @return A new Coordinate with default points
	 */

	public Coord() {

	}

	/**
	 * \brief A constructor that receives the points as separate values and sets
	 * the points of the new coordinate
	 * 
	 * @param x
	 *            The x axis of the coordinate
	 * @param y
	 *            The y axis of the coordinate
	 * 
	 * @return A new coordinate with the desired points
	 */

	public Coord(Double x, Double y) {
		setX(x);
		setY(y);
	}

	// @}

	/**
	 * @name Access
	 * 
	 *       Functions that return the state of the attributes of the Coordinate
	 */

	// @{

	/**
	 * \brief Function that returns the value of the x axis point
	 * 
	 * @return The value of the x axis point
	 */

	public Double getX() {
		return x;
	}

	/**
	 * \brief Function that returns the value of the y axis point
	 * 
	 * @return The value of the y axis point
	 */

	public Double getY() {
		return y;
	}

	// @}

	/**
	 * @name Set
	 * 
	 *       Functions that modify the state of the attributes
	 */

	// @{

	/**
	 * \brief Function that modifies the value of the x axis point
	 * 
	 * @param x
	 *            A double containing the value of the x axis point
	 */

	public void setX(Double x) {
		this.x = x;
	}

	/**
	 * \brief Function that modifies the value of the y axis point
	 * 
	 * @param y
	 *            A double containing the value of the y axis point
	 */

	public void setY(Double y) {
		this.y = y;
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
		if (o instanceof Coord) {
			Coord aux = (Coord) o;

			if (this.x.equals(aux.getX()) && this.y.equals(aux.getY())) {
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
		return "\n\nCoord Object\nSerial Version UID.: " + serialVersionUID + "\n X.: " + x + "\n Y.: " + y;
	}

}
