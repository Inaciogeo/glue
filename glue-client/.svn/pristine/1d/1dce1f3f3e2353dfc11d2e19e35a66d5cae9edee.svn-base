package br.org.funcate.glue.model.canvas;

import java.awt.Color;
import java.awt.Graphics;

public class ZoomAreaGraphics implements ToolGraphics {

	private int x1;
	private int y1;
	private int x2;
	private int y2;

	private boolean validate;

	private int auxX;
	private int auxY;

	public ZoomAreaGraphics(int x1, int y1, int x2, int y2) {
		setX1(x1);
		setX2(x2);
		setY1(y1);
		setY2(y2);
		validate = true;
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

	public int getAuxX() {
		return auxX;
	}

	public void setAuxX(int auxX) {
		this.auxX = auxX;
	}

	public int getAuxY() {
		return auxY;
	}

	public void setAuxY(int auxY) {
		this.auxY = auxY;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(x1, y1, x2 - x1, y2 - y1);

	}
}
