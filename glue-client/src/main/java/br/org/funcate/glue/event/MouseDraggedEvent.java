package br.org.funcate.glue.event;

import java.util.EventObject;

public class MouseDraggedEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private double x, y;

	public MouseDraggedEvent(Object source, double x, double y) {
		super(source);
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
