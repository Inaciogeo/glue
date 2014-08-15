package br.org.funcate.glue.event;

import java.util.EventObject;

@SuppressWarnings("serial")
public class GetSelectFeatureEvent extends EventObject{
	private String featureId;

	public GetSelectFeatureEvent(Object source) {
		super(source);
	}

	public String getFeatureId() {
		return featureId;
	}

	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}

}
