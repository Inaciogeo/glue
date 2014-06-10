package br.org.funcate.glue.event;

import java.util.EventObject;

import br.org.funcate.glue.main.AppSingleton;

@SuppressWarnings("serial")
public class ScaleChangedEvent extends EventObject {
	private double scale; 
	public ScaleChangedEvent(Object source) {
		super(source);
		setScale(AppSingleton.getInstance().getCanvasState().getScale());
	}
	public double getScale() {
		return scale;
	}
	public void setScale(double scale) {
		this.scale = scale;
	}
	
	
}
