package br.org.funcate.glue.event;

import java.util.EventObject;

import br.org.funcate.glue.model.canvas.CanvasState;

@SuppressWarnings("serial")
public class GetCanvasStateEvent extends EventObject {
	
	private CanvasState canvasState; 

	public GetCanvasStateEvent(Object source) {
		super(source);
	}

	public void setCanvasState(CanvasState canvasState) {
		this.canvasState = canvasState;
	}

	public CanvasState getCanvasState() {
		return canvasState;
	}
}
