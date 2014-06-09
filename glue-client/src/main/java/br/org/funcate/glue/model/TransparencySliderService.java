package br.org.funcate.glue.model;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.canvas.CanvasService;
import br.org.funcate.glue.model.canvas.CanvasState;
import br.org.funcate.glue.model.canvas.GeneralTileSchema;

public abstract class TransparencySliderService {

	public static void changeTransparencyFactor(Integer transparencyValue) {
		float transparencyFactor = (float) (100 - transparencyValue) / 100;
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState model = singleton.getCanvasState();
		model.setTransparencyFactor(transparencyFactor);

		if (CanvasService.isForegroundEnabled()) {
			GeneralTileSchema.generateTilesLists(false);
		}
	}
}
