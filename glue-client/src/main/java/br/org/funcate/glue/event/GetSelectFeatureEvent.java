package br.org.funcate.glue.event;

import java.util.EventObject;

@SuppressWarnings("serial")
public class GetSelectFeatureEvent extends EventObject{
	private String featureId;
	private String osX;
	private String osY;
	private String osIP;

	public GetSelectFeatureEvent(Object source) {
		super(source);
	}

	public String getFeatureId() {
		return featureId;
	}

	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}

	public String getOsX() {
		return osX;
	}

	public void setOsX(String osX) {
		this.osX = osX;
	}

	public String getOsY() {
		return osY;
	}

	public void setOsY(String osY) {
		this.osY = osY;
	}

	public String getOsIP() {
		return osIP;
	}

	public void setOsIP(String osIP) {
		this.osIP = osIP;
	}
	
}
