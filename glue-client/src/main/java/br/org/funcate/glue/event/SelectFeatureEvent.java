package br.org.funcate.glue.event;

import java.util.EventObject;

@SuppressWarnings("serial")
public class SelectFeatureEvent extends EventObject {
	private String source;
	private String type;
	public SelectFeatureEvent(Object source) {
		super(source);
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
