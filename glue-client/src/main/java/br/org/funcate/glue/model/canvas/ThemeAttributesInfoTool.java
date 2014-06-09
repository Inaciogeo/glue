package br.org.funcate.glue.model.canvas;

import java.util.List;

public class ThemeAttributesInfoTool {

	private String themeName;
	private String objectId;
	private List<Object> attributes;

	public ThemeAttributesInfoTool() {

	}

	ThemeAttributesInfoTool(String themeName, String objectId, List<Object> attributes) {
		this.themeName = themeName;
		this.objectId = objectId;
		this.attributes = attributes;
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
	 * @return the objectId
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId
	 *            the objectId to set
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the attributes
	 */
	public List<Object> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes
	 *            the attributes to set
	 */
	public void setAttributes(List<Object> attributes) {
		this.attributes = attributes;
	}
}
