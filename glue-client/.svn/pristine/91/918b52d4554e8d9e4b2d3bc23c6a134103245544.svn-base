package br.org.funcate.glue.model.request;

import java.util.List;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.ContextToGroupMap;
import br.org.funcate.glue.model.ThemeToPlot;
import br.org.funcate.glue.model.View;
import br.org.funcate.glue.model.ViewToPlot;
import br.org.funcate.glue.model.canvas.CanvasState;
import br.org.funcate.glue.model.canvas.HighlightObjects;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.service.TerraJavaClient;
import br.org.funcate.glue.utilities.PropertiesReader;

/**
 * \class BDRequest This class provides an inflow to solicit tiles from
 * DataBase. \brief This class provides an inflow to solicit tiles from
 * DataBase. \author Henrique and Ricardo \version 1.0 \date 20/08/2010
 * 
 */
public abstract class BDRequest {

	// ! This is called when a specific tile needs to be requested from
	// DataBase.

	/*
	 * \param view The current enabled View defined in the Tree. The requested
	 * tile contains an image relative to this View. \param box The values that
	 * define the tile area. The requested tile has coordinates that defines
	 * your respective area, this is a square shape.
	 */

	static byte[] plotView(View view, Box box)
			throws GlueServerException {
		Integer imageType = 0;
		Boolean canvasOpaque = false;
		Integer quality = 75;

		ViewToPlot viewToPlot = view.getViewToPlot();

		if (isInvalidThemes(viewToPlot)) {
			return null;
		}

		/*
		 * if (!GeneralTileSchema.verifyCanvasInterception(box)) { return null;
		 * }
		 */

		AppSingleton singleton = AppSingleton.getInstance();
		TerraJavaClient services = singleton.getServices();

		ContextToGroupMap contextToGroupMap = singleton.getGroupMapParameters();

		CanvasState canvasState = singleton.getCanvasState();
		viewToPlot.setCanvasBackgroundColor(canvasState.getBackgroundColor());
		List<HighlightObjects> highlightObjects = canvasState
				.getHighlightObjects();

		List<ThemeToPlot> themes = viewToPlot.getThemes();

		for (ThemeToPlot themeToPlot : themes) {
			if (!highlightObjects.isEmpty()) {
				for (HighlightObjects highlight : highlightObjects) {
					if (themeToPlot.getName().equals(highlight.getThemeName())) {
						themeToPlot.getObjectsToDraw().addAll(
								highlight.getObjects());
					}
				}
			}
		}

		viewToPlot.setBox(canvasState.getBox());

		boolean threadedTile = PropertiesReader
				.getBooleanProperty("terrajava.tiles.threaded");

		if (threadedTile) {
			return services.plotViewThreadSafe(viewToPlot, box, 256, 256,
					imageType, canvasOpaque, quality, contextToGroupMap);
		}
		return services.plotView(viewToPlot, box, 256, 256, imageType,
				canvasOpaque, quality, contextToGroupMap);

	}

	public static boolean isInvalidThemes(ViewToPlot viewToPlot) {
		return viewToPlot.getThemes().isEmpty();
	}
}
