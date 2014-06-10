package br.org.funcate.glue.event;

import java.util.EventObject;

@SuppressWarnings("serial")
public class CanvasDrawEvent extends EventObject {
	
	private boolean clearCache;

	public CanvasDrawEvent(Object source, boolean clearCache) {
		super(source);
		this.clearCache = clearCache;
	}

	public boolean isClearCache() {
		return clearCache;
	}
}
