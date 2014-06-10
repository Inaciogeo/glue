package br.org.funcate.glue.model;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.canvas.CanvasService;
import br.org.funcate.glue.model.canvas.CanvasState;
import br.org.funcate.glue.model.canvas.GeneralTileSchema;
import br.org.funcate.glue.model.canvas.UndoRedoService;
import br.org.funcate.glue.model.toolbar.ToolEnum;
import br.org.funcate.glue.model.tree.TreeService;

public abstract class ScaleService {

	public static boolean isZoomMaxScale() {
		if (!CanvasService.hasBDImageSource()) {
			return false;
		}

		CanvasState state = AppSingleton.getInstance().getCanvasState();

		double currentScale = state.getScale();
		double maxScale = state.getView().getMaxScale();

		return currentScale * 2 > maxScale;
	}

	public static void verifyZoomLevelLimits(int newZoomLevel) {
		Mediator mediator = AppSingleton.getInstance().getMediator();

		if (newZoomLevel <= 1) {
			mediator.setToolEnabled(ToolEnum.ZOOMOUT, false);
			mediator.setSelectedTool(ToolEnum.PAN);
		} else {
			mediator.setToolEnabled(ToolEnum.ZOOMOUT, true);
		}

		if (newZoomLevel >= 20) {
			mediator.setToolEnabled(ToolEnum.ZOOMIN, false);
			mediator.setToolEnabled(ToolEnum.ZOOMAREA, false);
			mediator.setSelectedTool(ToolEnum.PAN);
		} else {
			mediator.setToolEnabled(ToolEnum.ZOOMIN, true);
			mediator.setToolEnabled(ToolEnum.ZOOMAREA, true);
		}
	}

	static double getMaxScaleValue() {
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		View view = state.getView();
		return view.getMaxScale();
	}

	static double getMinScaleValue() {
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		View view = state.getView();
		return view.getMinScale();
	}

	static void setScaleValue(double value) {
		CanvasState state = AppSingleton.getInstance().getCanvasState();

		UndoRedoService.addUndoValue();

		double meterPerPixel = value * 0.026458333;

		int meterConversor = CalculatorService.getMeterConversor(state
				.getProjection());

		double resolution = meterPerPixel / meterConversor;

		double centralXPoint = (state.getResolution() * (state.getCanvasWidth() / 2))
				+ state.getBox().getX1();
		double centralYPoint = (state.getResolution() * (state
				.getCanvasHeight() / 2)) + state.getBox().getY1();

		double newBoxX1 = centralXPoint
				- (resolution * (state.getCanvasWidth() / 2));
		double newBoxY1 = centralYPoint
				- (resolution * (state.getCanvasHeight() / 2));

		state.setScale(value);

		CanvasService.setCanvasResolution(resolution);
		CanvasService.configureCanvasBoxX(newBoxX1);
		CanvasService.configureCanvasBoxY(newBoxY1);
		GeneralTileSchema.generateTilesLists(true);
		updateScaleSettings();
	}

	public static void updateScaleSettings() {
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		Mediator mediator = AppSingleton.getInstance().getMediator();
		state.setScale(CalculatorService.getCurrentScale());
		TreeService.updateTreeVisibility();
		mediator.updateScaleComboValue();
		verifyScaleLimits();
	}

	public static void verifyScaleLimits() {
		if (CanvasService.hasBDImageSource()) {
			AppSingleton singleton = AppSingleton.getInstance();
			Mediator mediator = singleton.getMediator();
			CanvasState state = singleton.getCanvasState();

			double currentScale = state.getScale();
			double maxScale = state.getView().getMaxScale();
			double minScale = state.getView().getMinScale();
			if (currentScale * 2 >= maxScale) {
				mediator.setToolEnabled(ToolEnum.ZOOMOUT, false);
				mediator.setToolEnabled(ToolEnum.ZOOMIN, true);
//				mediator.setSelectedTool(ToolEnum.PAN);
				return;
			}
			mediator.setToolEnabled(ToolEnum.ZOOMOUT, true);

			if (currentScale / 2 < minScale) {
				mediator.setToolEnabled(ToolEnum.ZOOMIN, false);
//				mediator.setSelectedTool(ToolEnum.PAN);
			} else {
				mediator.setToolEnabled(ToolEnum.ZOOMIN, true);
			}
		}
	}
}
