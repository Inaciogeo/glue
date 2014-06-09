package br.org.funcate.glue.model;

import java.io.Serializable;
import java.util.Vector;

/** \file Box.java
 * This file defines the box (y axis and x axis coordinates)
 *  of views and themes
 */

/**
 * \brief Box This class defines the attributes of the box of views and themes
 * and the methods used to access and modify those values
 * 
 * @author Ribeiro, Stephen M.
 * @author Siqueira, Felipe V.
 * @author Oliveira, André G.
 * 
 */

public class Box implements Serializable {

	/** < Attribute type long > */

	private static final long serialVersionUID = 7844206716278449531L;

	/** < Attribute type Double > */

	private Double x1 = 0.0;

	/** < Attribute type Double > */

	private Double y1 = 0.0;

	/** < Attribute type Double > */

	private Double x2 = 0.0;

	/** < Attribute type Double > */

	private Double y2 = 0.0;

	/**
	 * @name Constructors
	 * 
	 *       The functions that create the box
	 */

	// @ {

	/**
	 * \brief An empty constructor used to simple instantiate the object
	 * 
	 * @return A new box with default coordinates
	 */

	public Box() {

	}

	/**
	 * \brief A constructor that receives the coordinates as separate values and
	 * sets the coordinates of the new box
	 * 
	 * @param x1
	 *            The x axis first coordinate
	 * @param y1
	 *            The y axis first coordinate
	 * @param x2
	 *            The x axis second coordinate
	 * @param y2
	 *            The y axis second coordinate
	 * 
	 * @return A new box with the desired coordinates
	 */

	public Box(Double x1, Double y1, Double x2, Double y2) {

		setX1(x1);
		setY1(y1);
		setX2(x2);
		setY2(y2);

	}

	/**
	 * \brief A constructor that receives the coordinates as a vector and sets
	 * the coordinates of the new box
	 * 
	 * @param Box
	 *            the vector that will be used to set the cordinates the
	 *            coordinates must be in this order: (x1, y1, x2, y2)
	 * 
	 * @return A new box with the desired coordinates
	 */

	public Box(Vector<?> Box) {
		setX1((Double) Box.toArray()[0]);
		setY1((Double) Box.toArray()[1]);
		setX2((Double) Box.toArray()[2]);
		setY2((Double) Box.toArray()[3]);
	}

	// @ }

	/**
	 * @name Access
	 * 
	 *       Functions that return the state of the attributes of the Box
	 */

	// @ {

	/**
	 * \brief Function that returns the first coordinate of the x axis
	 * 
	 * @return A double containing the value of the first coordinate of the x
	 *         axis
	 */

	public Double getX1() {
		return x1;
	}

	/**
	 * \brief Function that returns the first coordinate of the y axis
	 * 
	 * @return A double containing the value of the first coordinate of the y
	 *         axis
	 */

	public Double getY1() {
		return y1;
	}

	/**
	 * \brief Function that returns the second coordinate of the x axis
	 * 
	 * @return A double containing the value of the second coordinate of the x
	 *         axis
	 */

	public Double getX2() {
		return x2;
	}

	/**
	 * \brief Function that returns the second coordinate of the y axis
	 * 
	 * @return A double containing the value of the second coordinate of the y
	 *         axis
	 */

	public Double getY2() {
		return y2;
	}

	/**
	 * \brief Function that returns all the values of the box as a string
	 * 
	 * @return A string containing the values of all the coordinates of the box
	 */

	public String toString() {
		return "x1 = " + x1 + " y1 = " + y1 + " x2 = " + x2 + " y2 = " + y2;
	}

	// @ }

	/**
	 * @name Set
	 * 
	 *       Functions that modify the state of the attributes
	 */

	// @ {

	/**
	 * \brief Function that modifies the first coordinate of the x axis
	 * 
	 * @param x1
	 *            A double containing the value of the first coordinate of the x
	 *            axis
	 */

	public void setX1(Double x1) {
		this.x1 = x1;
	}

	/**
	 * \brief Function that modifies the first coordinate of the y axis
	 * 
	 * @param y1
	 *            A double containing the value of the first coordinate of the y
	 *            axis
	 */

	public void setY1(Double y1) {
		this.y1 = y1;
	}

	/**
	 * \brief Function that modifies the second coordinate of the x axis
	 * 
	 * @param x2
	 *            A double containing the value of the second coordinate of the
	 *            x axis
	 */

	public void setX2(Double x2) {
		this.x2 = x2;
	}

	/**
	 * \brief Function that modifies the second coordinate of the y axis
	 * 
	 * @param y2
	 *            A double containing the value of the second coordinate of the
	 *            y axis
	 */

	public void setY2(Double y2) {
		this.y2 = y2;
	}

	// @ }
}
