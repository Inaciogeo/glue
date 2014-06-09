package br.org.funcate.glue.model.canvas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DistanceMeasuringGraphics implements ToolGraphics {

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private String value;

	public DistanceMeasuringGraphics(int x1, int y1, int x2, int y2, String value) {
		setValue(value);
		setX1(x1);
		setX2(x2);
		setY1(y1);
		setY2(y2);
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public String getDistanceValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		int x1 = this.getX1();
		int y1 = this.getY1();
		int x2 = this.getX2();
		int y2 = this.getY2();
		g.fillOval(x1 - 2, y1 - 2, 4, 4);
		g.fillOval(x2 - 2, y2 - 2, 4, 4);
		g.drawLine(x1, y1, x2, y2);
		g.setFont(new Font("Default", Font.BOLD, 20));
		g.drawString(String.valueOf(this.getDistanceValue()) + " metros", x1 + 10, y1 - 5);
	}
}
