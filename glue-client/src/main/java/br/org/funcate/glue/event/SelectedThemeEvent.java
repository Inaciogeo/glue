package br.org.funcate.glue.event;

import java.util.EventObject;

import br.org.funcate.glue.model.Theme;

@SuppressWarnings("serial")
public class SelectedThemeEvent extends EventObject {

	private Theme theme;

	public SelectedThemeEvent(Object source, Theme theme) {
		super(source);
		this.theme = theme;
	}

	public Theme getTheme() {
		return theme;
	}
}
