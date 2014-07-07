package br.org.funcate.glue.event;

import java.awt.event.MouseEvent;
import java.util.EventObject;

public class MouseReleasedEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private double x, y;

	private MouseEvent event;

	public MouseReleasedEvent(Object source, double x, double y, MouseEvent event) {
		super(source);
		this.x = x;
		this.y = y;
		this.event = event;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	/**
	 * @return the event
	 */
	public MouseEvent getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(MouseEvent event) {
		this.event = event;
	}
}
