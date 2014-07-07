package br.org.funcate.glue.event;

import java.util.EventObject;

import br.org.funcate.glue.model.ContextToLabelConfig;

@SuppressWarnings("serial")
public class SetLabelContextEvent extends EventObject {
	private ContextToLabelConfig context;
	
	public SetLabelContextEvent(Object source,ContextToLabelConfig context) {
		super(source);
		setContext(context);
	}

	public ContextToLabelConfig getContext() {
		return context;
	}

	public void setContext(ContextToLabelConfig context) {
		this.context = context;
	}

}
