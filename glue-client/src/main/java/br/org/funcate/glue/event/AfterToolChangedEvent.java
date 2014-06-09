package br.org.funcate.glue.event;

import java.util.EventObject;

@SuppressWarnings("serial")
public class AfterToolChangedEvent extends EventObject {

	public AfterToolChangedEvent(Object source) {
		super(source);
	}
}
