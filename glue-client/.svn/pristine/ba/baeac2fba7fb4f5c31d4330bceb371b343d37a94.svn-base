package br.org.funcate.glue.event;

import java.util.EventObject;

public class MousePressedEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private int button;
	private double x, y,xScreen,yScreen;
	

	public MousePressedEvent(Object source, int button, double x, double y,
			double xScreen, double yScreen) {
		super(source);
		this.button = button;
		this.x = x;
		this.y = y;
		this.xScreen = xScreen;
		this.yScreen = yScreen;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getButton() {
		return button;
	}

	public double getxScreen() {
		return xScreen;
	}

	public void setxScreen(double xScreen) {
		this.xScreen = xScreen;
	}

	public double getyScreen() {
		return yScreen;
	}

	public void setyScreen(double yScreen) {
		this.yScreen = yScreen;
	}
	
}
