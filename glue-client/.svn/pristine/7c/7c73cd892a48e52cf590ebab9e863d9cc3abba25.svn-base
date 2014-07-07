package br.org.funcate.glue.event;

import java.util.EventObject;

@SuppressWarnings("serial")
public class MouseWheelMovedEvent extends EventObject {

	private double x, y;
	private int wheelRotation;

	public MouseWheelMovedEvent(Object source, double x, double y, int clicksWheelRotated) {
		super(source);
		this.x = x;
		this.y = y;
		this.wheelRotation = clicksWheelRotated;
	}

	public void setClicksWheelRotated(int clicksWheelRotated) {
		this.wheelRotation = clicksWheelRotated;
	}
}
