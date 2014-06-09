package br.org.funcate.glue.event;

import java.util.EventObject;
import java.util.List;

import br.org.funcate.glue.model.View;

/**
 * This event can be used to retrieve the {@link View} list, of GLUE
 * application.
 * 
 * @author Moraes, Emerson Leite
 */
@SuppressWarnings("serial")
public class GetViewsEvent extends EventObject {

	/**
	 * A list of Views to be populated by GLUE.
	 */
	private List<View> views;

	/**
	 * Constructor.
	 */
	public GetViewsEvent(Object source) {
		super(source);
	}

	/**
	 * Gets the {@link View} list.
	 * 
	 * @return the views
	 */
	public List<View> getViews() {
		return views;
	}

	/**
	 * Sets the {@link View} list.
	 * 
	 * @param views
	 */
	public void setViews(List<View> views) {
		this.views = views;
	}
}
