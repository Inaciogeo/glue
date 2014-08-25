package br.org.funcate.glue.event;

import java.util.ArrayList;
import java.util.EventObject;

@SuppressWarnings("serial")
public class GetOsEvent extends EventObject {

	private ArrayList<String> osIDs;
	private ArrayList<String> ocurrences;
	private ArrayList<String> status;

	public GetOsEvent(Object source) {
		super(source);
	}

	public ArrayList<String> getOsIDs() {
		return osIDs;
	}

	public void setOsIDs(ArrayList<String> osIDs) {
		this.osIDs = osIDs;
	}

	public ArrayList<String> getOcorrences() {
		return ocurrences;
	}

	public void setOcorrences(ArrayList<String> ocorrences) {
		this.ocurrences = ocorrences;
	}

	public ArrayList<String> getStatus() {
		return status;
	}

	public void setStatus(ArrayList<String> status) {
		this.status = status;
	}
}
