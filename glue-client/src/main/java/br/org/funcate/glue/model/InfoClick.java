package br.org.funcate.glue.model;

public class InfoClick {

	private String selectedThemeName = "none";
	
	private double xPosition;
	private double yPosition;
	
	public double getxPosition() {
		return xPosition;
	}
	
	public void setxPosition(double xPosition) {
		this.xPosition = xPosition;
	}
	
	public double getyPosition() {
		return yPosition;
	}
	
	public void setyPosition(double yPosition) {
		this.yPosition = yPosition;
	}
	
	public String getSelectedThemeName() {
		return selectedThemeName;
	}
	
	public void setSelectedThemeName(String selectedTheme) {
		this.selectedThemeName = selectedTheme;
	}
}
