package br.org.funcate.glue.event;

import java.util.EventObject;
import java.util.List;

import br.org.funcate.glue.model.ThemeAttribute;

/**
 * This event can be used to retrieve the list of {@link ThemeAttribute} of
 * a TerraLib Theme.
 * 
 * The View name and Theme name must be correctly set in the constructor
 * before the dispatch. After the dispatch the {@link ThemeAttribute} list
 * can be retrieved using getAttributes() method.
 * 
 * @author Moraes, Emerson Leite
 *
 */
@SuppressWarnings("serial")
public class GetThemeAttributesEvent extends EventObject{

	private String viewName;

	private String themeName;
	
	private List<ThemeAttribute> attributes;
	
	public GetThemeAttributesEvent(Object source, String viewName, String themeName){
		super(source);
		this.viewName = viewName;
		this.themeName = themeName;
	}

	/**
	 * Sets the attributes of the Theme.
	 * @param attributes the attributes to set
	 */
	public void setAttributes(List<ThemeAttribute> attributes) {
		this.attributes = attributes;
	}

	/**
	 * Gets the attributes of the Theme.
	 * @return the attributes
	 */
	public List<ThemeAttribute> getAttributes() {
		return attributes;
	}


	/**
	 * @return the viewName
	 */
	public String getViewName() {
		return viewName;
	}

	/**
	 * @return the themeName
	 */
	public String getThemeName() {
		return themeName;
	}
}
