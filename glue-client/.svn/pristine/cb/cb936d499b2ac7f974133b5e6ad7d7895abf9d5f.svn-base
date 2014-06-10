package br.org.funcate.glue.event;

import java.awt.event.MouseEvent;
import java.util.EventObject;

@SuppressWarnings("serial")
public class MouseClickedEvent extends EventObject {

	private double x;
	private double y;
	private MouseEvent mouseEvent;

	public MouseClickedEvent(Object source, double x, double y, MouseEvent e) {
		super(source);
		this.x = x;
		this.y = y;
		this.setMouseEvent(e);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public MouseEvent getMouseEvent() {
		return mouseEvent;
	}

	public void setMouseEvent(MouseEvent mouseEvent) {
		this.mouseEvent = mouseEvent;
	}
}
