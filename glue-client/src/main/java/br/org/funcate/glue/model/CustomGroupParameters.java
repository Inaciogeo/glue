package br.org.funcate.glue.model;

import java.util.Vector;

public class CustomGroupParameters {

	private String mapTitle;
	private Vector<Integer> redList;
	private Vector<Integer> greenList;
	private Vector<Integer> blueList;
	private Vector<String> minValuesList;
	private Vector<String> maxValuesList;
	private Vector<String> descriptionList;

	public CustomGroupParameters() {

	}

	public String getMapTitle() {
		return mapTitle;
	}

	public void setMapTitle(String mapTitle) {
		this.mapTitle = mapTitle;
	}

	public Vector<Integer> getRedList() {
		return redList;
	}

	public void setRedList(Vector<Integer> redList) {
		this.redList = redList;
	}

	public Vector<Integer> getGreenList() {
		return greenList;
	}

	public void setGreenList(Vector<Integer> greenList) {
		this.greenList = greenList;
	}

	public Vector<Integer> getBlueList() {
		return blueList;
	}

	public void setBlueList(Vector<Integer> blueList) {
		this.blueList = blueList;
	}

	public Vector<String> getMinValuesList() {
		return minValuesList;
	}

	public void setMinValuesList(Vector<String> minValuesList) {
		this.minValuesList = minValuesList;
	}

	public Vector<String> getMaxValuesList() {
		return maxValuesList;
	}

	public void setMaxValuesList(Vector<String> maxValuesList) {
		this.maxValuesList = maxValuesList;
	}

	public Vector<String> getDescriptionList() {
		return descriptionList;
	}

	public void setDescriptionList(Vector<String> descriptionList) {
		this.descriptionList = descriptionList;
	}
	
}
