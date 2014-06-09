package br.org.funcate.glue.model.canvas;

import java.util.List;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.exception.GlueServerException;

public abstract class HighlightObjectsService {

	static void addHightlightObjects(String themeName, List<String> objects) {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		List<HighlightObjects> highlightObjectsList = state.getHighlightObjects();

		HighlightObjects highlight = new HighlightObjects(themeName, objects);
		highlightObjectsList.add(highlight);
	}

	public static void cleanSelection() throws GlueServerException {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		List<HighlightObjects> highlightObjectsList = state.getHighlightObjects();

		if (!highlightObjectsList.isEmpty()) {
			highlightObjectsList.clear();
			CanvasService.draw(true, false);
		}
	}
}
