package br.org.funcate.glue.model.toolbar;

/**
 * 
 * @author Moraes, Emerson Leite
 * 
 *         This class represents the state of one tool of toolbar.
 * 
 */
public class ToolState {

	/**
	 * This Attribute represents if a button is Enabled or not.
	 */
	private boolean isEnabled;
	/**
	 * This Attribute represents if a button is Selected or not.
	 */
	private boolean isSelected;

	/**
	 * Constructor
	 * 
	 * @param isEnabled
	 * @param isSelected
	 */
	public ToolState(boolean isEnabled, boolean isSelected) {
		this.isEnabled = isEnabled;
		this.isSelected = isSelected;
	}

	/**
	 * This method sets isEnabled attribute
	 * 
	 * @param isEnabled
	 */
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * This gets isEnabled attribute.
	 * 
	 * @return isEnabled
	 */
	public boolean isEnabled() {
		return isEnabled;
	}

	/**
	 * This method sets isSelected attribute.
	 * 
	 * @param isSelected
	 */
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	/**
	 * This gets isSelected attribute.
	 * 
	 * @return isSelected
	 */
	public boolean isSelected() {
		return isSelected;
	}

}
