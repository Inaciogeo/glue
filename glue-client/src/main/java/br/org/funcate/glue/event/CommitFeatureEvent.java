package br.org.funcate.glue.event;

import java.util.EventObject;

@SuppressWarnings("serial")
public class CommitFeatureEvent extends EventObject {
	
	public String ip;
	public String osid;
	public boolean selected;
	public boolean network;
	public String ocurrence;
	public String status;
	public String x;
	public String y;

	public CommitFeatureEvent(Object source) {
		super(source);
		
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}


	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOsid() {
		return osid;
	}

	public void setOsid(String osid) {
		this.osid = osid;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isNetwork() {
		return network;
	}

	public void setNetwork(boolean network) {
		this.network = network;
	}

	public String getOcurrence() {
		return ocurrence;
	}

	public void setOcurrence(String ocurrence) {
		this.ocurrence = ocurrence;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
