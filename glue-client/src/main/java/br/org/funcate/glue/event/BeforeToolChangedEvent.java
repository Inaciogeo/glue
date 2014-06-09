package br.org.funcate.glue.event;

import java.util.EventObject;

@SuppressWarnings("serial")
public class BeforeToolChangedEvent extends EventObject {

	public BeforeToolChangedEvent(Object source) {
		super(source);
	}
}
