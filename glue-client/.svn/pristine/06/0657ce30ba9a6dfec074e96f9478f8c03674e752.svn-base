package br.org.funcate.glue.model.canvas;

import br.org.funcate.glue.main.AppSingleton;

public abstract class PanToolService {

	public static void dragPanTool(float f, float g) {

		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		CanvasGraphicsBuffer canvasGraphicsBuffer = state.getCanvasGraphicsBuffer();
		canvasGraphicsBuffer.setDragMode(true);

		PanToolGraphics pan = getPanToolGraphicsFromSingleton();

		double deltaX = -(AppSingleton.getInstance().getCanvasState().getResolution() * (f - pan.getPanMotionX()));
		double deltaY = (AppSingleton.getInstance().getCanvasState().getResolution() * (g - pan.getPanMotionY()));

		pan.setPanMotionX(f);
		pan.setPanMotionY(g);

		CanvasService.configureCanvasBoxX(AppSingleton.getInstance().getCanvasState().getBox().getX1() + deltaX);
		CanvasService.configureCanvasBoxY(AppSingleton.getInstance().getCanvasState().getBox().getY1() + deltaY);

		canvasGraphicsBuffer.incrementEditionX((int) -Math.round(deltaX / AppSingleton.getInstance().getCanvasState().getResolution()));
		canvasGraphicsBuffer.incrementEditionY((int) Math.round(deltaY / AppSingleton.getInstance().getCanvasState().getResolution()));
		// GeneralTileSchema.generateTilesLists();

		canvasGraphicsBuffer.notifyObservers();
	}

	public static PanToolGraphics getPanToolGraphicsFromSingleton() {
		ToolGraphics tool = AppSingleton.getInstance().getCanvasState().getCanvasGraphicsBuffer().getToolGraphics();

		PanToolGraphics pan;

		if (tool instanceof PanToolGraphics) {
			pan = (PanToolGraphics) tool;
		} else {
			throw new RuntimeException("FATAL ERROR: Atributo toolGraphics no state não é um PanTool!");
		}

		return pan;
	}

	public static void pressPanTool(float f, float g) {

		CanvasState state = AppSingleton.getInstance().getCanvasState();
		CanvasGraphicsBuffer canvasGraphicsBuffer = state.getCanvasGraphicsBuffer();

		PanToolGraphics pan = new PanToolGraphics(f, g);
		canvasGraphicsBuffer.setToolGraphics(pan);

		UndoRedoService.addUndoValue();
	}
}
