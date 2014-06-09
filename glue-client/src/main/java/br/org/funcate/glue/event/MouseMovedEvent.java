package br.org.funcate.glue.event;

import java.util.EventObject;

/**
 * This event can be used to retrieve the mouse moved event with world coordinates.
 * 
 * @author Moraes, Emerson Leite
 * 
 */
public class MouseMovedEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double x = 0;
	private double y = 0;

	public MouseMovedEvent(Object source, double x, double y) {
		super(source);
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

}
