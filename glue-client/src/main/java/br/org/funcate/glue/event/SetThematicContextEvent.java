package br.org.funcate.glue.event;

import java.util.EventObject;

import br.org.funcate.glue.model.ContextToGroupMap;

@SuppressWarnings("serial")
public class SetThematicContextEvent extends EventObject{
		
	private ContextToGroupMap parameters;
	
	public SetThematicContextEvent(Object source, ContextToGroupMap parameters) {
		super(source);
		this.setParameters(parameters);
	}

	public ContextToGroupMap getParameters() {
		return parameters;
	}

	public void setParameters(ContextToGroupMap parameters) {
		this.parameters = parameters;
	}

}
