package br.org.funcate.glue.event;

import java.util.EventObject;

import br.org.funcate.glue.model.tree.CustomNode;

@SuppressWarnings("serial")
public class TreeThemeChangeEvent extends EventObject {
	public boolean change;
	public CustomNode OldTheme;
	
	public TreeThemeChangeEvent(Object source) {
		super(source);
	}

	public boolean isChange() {
		return change;
	}


	public void setChange(boolean change) {
		this.change = change;
	}

	public CustomNode getOldTheme() {
		return OldTheme;
	}

	public void setOldTheme(CustomNode theme) {
		this.OldTheme = theme;
	}

	
}
