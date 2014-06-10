package br.org.funcate.glue.event;

import java.util.EventObject;

import br.org.funcate.glue.view.AbstractCanvas;

@SuppressWarnings("serial")
public class BoxChangedEvent extends EventObject {

	private AbstractCanvas canvas;

	public BoxChangedEvent(Object source) {
		super(source);
	}

	/**
	 * @param canvas
	 *            the canvas to set
	 */
	public void setCanvas(AbstractCanvas canvas) {
		this.canvas = canvas;
	}

	/**
	 * @return the canvas
	 */
	public AbstractCanvas getCanvas() {
		return canvas;
	}
}
