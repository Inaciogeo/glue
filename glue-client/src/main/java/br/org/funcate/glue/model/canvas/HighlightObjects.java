package br.org.funcate.glue.model.canvas;

import java.util.List;

public class HighlightObjects {

	private String themeName;
	private List<String> objects;

	public HighlightObjects() {

	}

	public HighlightObjects(String themeName, List<String> objects) {
		this.themeName = themeName;
		this.objects = objects;
	}

	/**
	 * @return the themeName
	 */
	public String getThemeName() {
		return themeName;
	}

	/**
	 * @param themeName
	 *            the themeName to set
	 */
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	/**
	 * @return the objects
	 */
	public List<String> getObjects() {
		return objects;
	}

	/**
	 * @param objects
	 *            the objects to set
	 */
	public void setObjects(List<String> objects) {
		this.objects = objects;
	}

}
