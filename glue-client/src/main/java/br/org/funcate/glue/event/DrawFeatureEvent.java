package br.org.funcate.glue.event;

import java.util.ArrayList;
import java.util.EventObject;

@SuppressWarnings("serial")
public class DrawFeatureEvent extends EventObject{
	ArrayList<String> lineIds;
	ArrayList<String> polygonIds;
	public DrawFeatureEvent(Object source){
		super(source);	
	}

	public ArrayList<String> getLineIds() {
		return lineIds;
	}

	public void setLineIds(ArrayList<String> ids) {
		this.lineIds = ids;
	}

	public ArrayList<String> getPolygonIds() {
		return polygonIds;
	}

	public void setPolygonIds(ArrayList<String> polygonIds) {
		this.polygonIds = polygonIds;
	}
	
	
}
