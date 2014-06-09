package br.org.funcate.glue.event;

import java.util.EventObject;

@SuppressWarnings("serial")
public class GetWorldCoordinates extends EventObject{

	private double x;
	
	private double y;
	
	public GetWorldCoordinates(Object source, double x, double y) {
		super(source);
		this.setX(x);
		this.setY(y);
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
}
