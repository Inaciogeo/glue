package br.org.funcate.glue.model.canvas;

import java.awt.Graphics;

public class PanToolGraphics implements ToolGraphics {
	/**
	 * Saves the first location X point of mouse. It is used to calculate deltaX
	 */
	private float panMotionX = 0;
	/**
	 * Saves the first location Y point of mouse. It is used to calculate deltaY
	 */
	private float panMotionY = 0;

	public PanToolGraphics(float f, float g) {
		this.panMotionX = f;
		this.panMotionY = g;
	}

	public float getPanMotionX() {
		return panMotionX;
	}

	public void setPanMotionX(float f) {
		this.panMotionX = f;
	}

	public float getPanMotionY() {
		return panMotionY;
	}

	public void setPanMotionY(float g) {
		this.panMotionY = g;
	}

	public void paint(Graphics g) {

	}
}
