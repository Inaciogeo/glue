package br.org.funcate.glue.event;

import java.util.EventObject;

import br.org.funcate.glue.model.canvas.BufferEnum;

@SuppressWarnings("serial")
public class CleanBufferEvent extends EventObject {

	private BufferEnum bufferId;

	public CleanBufferEvent(Object source, BufferEnum bufferId) {
		super(source);
		this.setBufferId(bufferId);
	}

	public BufferEnum getBufferId() {
		return bufferId;
	}

	public void setBufferId(BufferEnum bufferId) {
		this.bufferId = bufferId;
	}

}
