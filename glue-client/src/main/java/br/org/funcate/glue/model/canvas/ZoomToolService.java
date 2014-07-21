package br.org.funcate.glue.model.canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.CalculatorService;
import br.org.funcate.glue.model.ESRILatLongTile;
import br.org.funcate.glue.model.toolbar.ToolEnum;

public abstract class ZoomToolService {

	private static final int ZOOM_OUT = 1;
	private static final int ZOOM_IN = 2;
	private static final int ZOOM_AREA = 3;

	private static ZoomAreaGraphics getZoomAreaGraphicsFromSingleton() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		CanvasGraphicsBuffer canvasGraphicsBuffer = state
				.getCanvasGraphicsBuffer();
		ToolGraphics tool = canvasGraphicsBuffer.getToolGraphics();

		ZoomAreaGraphics zoomArea;

		if (tool instanceof ZoomAreaGraphics) {
			zoomArea = (ZoomAreaGraphics) tool;
		} else {
			throw new RuntimeException(
					"Atributo toolGraphics no state não é um ZoomAreaGraphics!");
		}
		return zoomArea;
	}

	private static boolean zoomAreaValidation() {

		ZoomAreaGraphics zoomArea = getZoomAreaGraphicsFromSingleton();
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		int canvasWidth = state.getCanvasWidth();
		int canvasHeight = state.getCanvasHeight();
		return !(zoomArea.getX1() < 0 || zoomArea.getY1() < 0
				|| zoomArea.getX2() < 0 || zoomArea.getY2() < 0
				|| zoomArea.getX1() > canvasWidth - 2
				|| zoomArea.getY1() > canvasHeight - 2
				|| zoomArea.getX2() > canvasWidth - 2 || zoomArea.getY2() > canvasHeight - 2);
	}

	public static void dragZoomArea(int mouseClickXPosition,
			int mouseClickYPosition) {

		ZoomAreaGraphics zoomArea = getZoomAreaGraphicsFromSingleton();

		zoomArea.setX2(mouseClickXPosition);
		zoomArea.setY2(mouseClickYPosition);

		if (zoomArea.getX2() <= zoomArea.getX1()) {
			zoomArea.setX2(zoomArea.getAuxX());
			zoomArea.setX1(mouseClickXPosition);
		}

		if (zoomArea.getY2() <= zoomArea.getY1()) {
			zoomArea.setY2(zoomArea.getAuxY());
			zoomArea.setY1(mouseClickYPosition);
		}

		if (!zoomAreaValidation()) {
			zoomArea.setValidate(false);
			cancelZoomArea();
		}
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		CanvasGraphicsBuffer canvasGraphicsBuffer = state
				.getCanvasGraphicsBuffer();
		canvasGraphicsBuffer.notifyObservers();
	}

	private static void cancelZoomArea() {

		ZoomAreaGraphics zoomArea = getZoomAreaGraphicsFromSingleton();

		zoomArea.setX1(0);
		zoomArea.setAuxX(0);
		zoomArea.setY1(0);
		zoomArea.setAuxY(0);
		zoomArea.setX2(0);
		zoomArea.setY2(0);
	}

	public static void pressZoomIn(int mouseClickXPosition,
			int mouseClickYPosition) {
		zoomIn(mouseClickXPosition, mouseClickYPosition);
	}

	public static void pressZoomOut(int mouseClickXPosition,
			int mouseClickYPosition) {
		zoomOut(mouseClickXPosition, mouseClickYPosition, true);
	}

	public static void pressZoomArea(int mouseClickXPosition,
			int mouseClickYPosition) {

		ZoomAreaGraphics zoomArea = new ZoomAreaGraphics(mouseClickXPosition,
				mouseClickYPosition, mouseClickXPosition + 1,
				mouseClickYPosition + 1);

		zoomArea.setAuxX(zoomArea.getX1());
		zoomArea.setAuxY(zoomArea.getY1());

		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		CanvasGraphicsBuffer canvasGraphicsBuffer = state
				.getCanvasGraphicsBuffer();
		canvasGraphicsBuffer.setToolGraphics(zoomArea);
		canvasGraphicsBuffer.notifyObservers();
	}

	static void executeMouseWheelEvent(int x, int y, int wheelRotation) {

		Mediator mediator = AppSingleton.getInstance().getMediator();

		if (wheelRotation > 0) {
			if (mediator.isToolEnabled(ToolEnum.ZOOMOUT)) {

				ZoomToolService.zoomOut(x, y, true);
			}
		} else {
			if (mediator.isToolEnabled(ToolEnum.ZOOMIN)) {
				ZoomToolService.zoomIn(x, y);
			}
		}

	}

	public static void releaseZoomArea(int mouseClickXPosition,
			int mouseClickYPosition) {
		ZoomAreaGraphics zoomArea = getZoomAreaGraphicsFromSingleton();
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		CanvasGraphicsBuffer canvasGraphicsBuffer = state
				.getCanvasGraphicsBuffer();
		if (!zoomArea.isValidate()) {
			zoomArea.setValidate(true);
			cancelZoomArea();
			canvasGraphicsBuffer.notifyObservers();
			return;
		}
		zoomArea();
	}

	private static void paintFastZoom(int zoomType, int xFastZoom,
			int yFastZoom, int x2FastZoom, int y2FastZoom, int zoomLevel) {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();

		int canvasWidth = state.getCanvasWidth();
		int canvasHeight = state.getCanvasHeight();

		CanvasGraphicsBuffer canvasGraphicsBuffer = state
				.getCanvasGraphicsBuffer();

		BufferedImage tilesBuffer = canvasGraphicsBuffer.getTilesBuffer();
		Graphics tilesBufferGraph = canvasGraphicsBuffer.getTilesBufferGraph();
		int width = canvasWidth / 2;
		int height = canvasHeight / 2;
		if (zoomType == ZOOM_IN) {
			xFastZoom = xFastZoom - (canvasWidth / 4);
			yFastZoom = yFastZoom - (canvasHeight / 4);
			BufferedImage zoomImage = new BufferedImage(width, height, 1);
			Graphics zoomImageGraph = zoomImage.getGraphics();
			zoomImageGraph.setColor(Color.white);
			zoomImageGraph.fillRect(-canvasWidth, -canvasHeight,
					canvasWidth * 3, canvasHeight * 3);
			zoomImageGraph.drawImage(tilesBuffer, 0, 0, width, height,
					xFastZoom, yFastZoom, xFastZoom + width,
					yFastZoom + height, state.getImgObs());
			Image scaledImage = zoomImage.getScaledInstance(canvasWidth,
					canvasHeight, Image.SCALE_FAST);
			tilesBufferGraph.drawImage(scaledImage, 0, 0, state.getImgObs());
			canvasGraphicsBuffer.repaintTilesBufferOnCanvas();

		} else if (zoomType == ZOOM_OUT) {
			BufferedImage zoomImage = new BufferedImage(width, height, 1);
			Graphics zoomImageGraph = zoomImage.getGraphics();
			Image scaledImage = tilesBuffer.getScaledInstance(canvasWidth / 2,
					canvasHeight / 2, Image.SCALE_FAST);
			zoomImageGraph.drawImage(scaledImage, 0, 0, state.getImgObs());
			tilesBufferGraph.setColor(Color.white);
			tilesBufferGraph.fillRect(0, 0, canvasWidth, canvasHeight);
			tilesBufferGraph.drawImage(zoomImage, (canvasWidth / 2)
					- (xFastZoom / 2), (canvasHeight / 2) - (yFastZoom / 2),
					state.getImgObs());
			canvasGraphicsBuffer.repaintTilesBufferOnCanvas();
		} else if (zoomType == ZOOM_AREA) {
			int largura = x2FastZoom - xFastZoom;
			int altura = y2FastZoom - yFastZoom;
			int xCentral = xFastZoom + (largura / 2);
			int yCentral = yFastZoom + (altura / 2);
			double aumento = (state.getZoomLevel() - zoomLevel) * 2;
			width = (int) Math.round(canvasWidth / aumento);
			height = (int) Math.round(canvasHeight / aumento);
			if (CanvasService.isGoogleActive()) {
				xFastZoom = xCentral - (width / 2);
				yFastZoom = height / 2;
				if (aumento == 0.0) {
					canvasGraphicsBuffer.notifyObservers();
					GeneralTileSchema.generateTilesLists(false);
					return;
				}
			} else {
				double aumentoX = canvasWidth / largura;
				double aumentoY = canvasHeight / altura;
				if (aumentoX < aumentoY)
					aumento = aumentoX;
				else
					aumento = aumentoY;
				xFastZoom = (int) Math.round(xCentral
						- (canvasWidth / (aumento * 2)));
				yFastZoom = (int) Math.round(yCentral
						- (canvasHeight / (aumento * 2)));
				width = (int) Math.round(canvasWidth / aumento);
				height = (int) Math.round(canvasHeight / aumento);
			}
			BufferedImage zoomImage = new BufferedImage(width, height, 1);
			Graphics zoomImageGraph = zoomImage.getGraphics();
			zoomImageGraph.setColor(Color.white);
			zoomImageGraph.fillRect(-canvasWidth, -canvasHeight,
					canvasWidth * 3, canvasHeight * 3);
			zoomImageGraph.drawImage(tilesBuffer, 0, 0, width, height,
					xFastZoom, yFastZoom, xFastZoom + width,
					yFastZoom + height, state.getImgObs());
			Image scaledImage = zoomImage.getScaledInstance(canvasWidth,
					canvasHeight, Image.SCALE_FAST);
			tilesBufferGraph.drawImage(scaledImage, 0, 0, state.getImgObs());
			canvasGraphicsBuffer.repaintTilesBufferOnCanvas();
		}

		canvasGraphicsBuffer.resetEditionBuffers();
		canvasGraphicsBuffer.notifyObservers();

	}

	private static void zoomArea() {
		int zoomLevel = 0;
		ZoomAreaGraphics zoomArea = getZoomAreaGraphicsFromSingleton();
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		//if (CanvasService.isGoogleActive()) {
			zoomLevel = state.getZoomLevel(); 
		//}

		int xFastZoom = zoomArea.getX1();
		int yFastZoom = zoomArea.getY1();
		int x2FastZoom = zoomArea.getX2();
		int y2FastZoom = zoomArea.getY2();

		UndoRedoService.addUndoValue();

		Box currentBox = state.getBox();
		double currentResolution = state.getResolution();
		int canvasHeight = state.getCanvasHeight();

		double x1 = currentBox.getX1() + (zoomArea.getX1() * currentResolution);
		double y1 = currentBox.getY1()
				+ ((canvasHeight - zoomArea.getY2()) * currentResolution);
		double x2 = currentBox.getX1() + (zoomArea.getX2() * currentResolution);
		double y2 = currentBox.getY1()
				+ ((canvasHeight - zoomArea.getY1()) * currentResolution);

		cancelZoomArea();
		CanvasGraphicsBuffer canvasGraphicsBuffer = state
				.getCanvasGraphicsBuffer();
		if (CanvasService.hasBDImageSource()) {
			if (CalculatorService.generateScaleFromBox(x1, y1, x2, y2) < state
					.getView().getMinScale()) {
//				LocalOptionPane.getInstance(
//						"O zoom realizado é maior do que o permitido!",
//						"Atenção");
				canvasGraphicsBuffer.notifyObservers();
				return;
			}
		}
		CanvasService.setBox(new Box(x1, y1, x2, y2));
		paintFastZoom(ZOOM_AREA, xFastZoom, yFastZoom, x2FastZoom, y2FastZoom,
				zoomLevel);
	}

	public static void zoomOut(int xClick, int yClick, boolean paintFastZoom) {
		int xFastZoom = xClick;
		int yFastZoom = yClick;

		AppSingleton singleton = AppSingleton.getInstance();
		UndoRedoService.addUndoValue();
		
		CanvasState state = singleton.getCanvasState();
		int zoomLevel = state.getZoomLevel();
		Box box = state.getBox();
		int canvasWidth = state.getCanvasWidth();
		int canvasHeight = state.getCanvasHeight();
		double canvasResolution = state.getResolution();
		double xClickPosition = box.getX1() + (canvasResolution * xClick);
		double yClickPosition = box.getY1() + (canvasResolution * (canvasHeight - yClick));
		
		if("Instituto Geográfico e Cartográfico".equals(state.getDataSource())){
			if( zoomLevel>0){
				canvasResolution = (ESRILatLongTile.getResolution()[--zoomLevel]);
			}else{
				canvasResolution = (ESRILatLongTile.getResolution()[zoomLevel]);
			}
		}else{
			canvasResolution = canvasResolution*2;
		}
		
		double xDistance = canvasWidth * (canvasResolution);
		double yDistance = canvasHeight * (canvasResolution);
		
		double x1ZoomBox = xClickPosition - (xDistance / 2);
		double y1ZoomBox = yClickPosition - (yDistance / 2);

		CanvasService.setCanvasResolution(canvasResolution);
		CanvasService.configureCanvasBoxX(x1ZoomBox);
		CanvasService.configureCanvasBoxY(y1ZoomBox);
		
		
		
		if (paintFastZoom) {
			paintFastZoom(ZOOM_OUT, xFastZoom, yFastZoom, 0, 0, 0);
		}
	}

	public static void zoomIn(int xClick, int yClick) {

		int xFastZoom = xClick;
		int yFastZoom = yClick;

		AppSingleton singleton = AppSingleton.getInstance();

		UndoRedoService.addUndoValue();

		CanvasState state = singleton.getCanvasState();
		Box box = state.getBox();
		int canvasWidth = state.getCanvasWidth();
		int canvasHeight = state.getCanvasHeight();
		double canvasResolution = state.getResolution();
		int zoomLevel = state.getZoomLevel();
		double xClickPosition = box.getX1() + (canvasResolution * xClick);
		double yClickPosition = (box.getY1())+ (canvasResolution * (canvasHeight - yClick));
		
		if("Instituto Geográfico e Cartográfico".equals(state.getDataSource())){
			if( ESRILatLongTile.getResolution().length > zoomLevel){
				canvasResolution = (ESRILatLongTile.getResolution()[++zoomLevel]);
			}else{
				canvasResolution = (ESRILatLongTile.getResolution()[zoomLevel]);
			}		
		}else{
			canvasResolution = canvasResolution/2;
		}
		
		double xDistance = canvasWidth * (canvasResolution);
		double yDistance = canvasHeight * (canvasResolution);
		
		double x1ZoomBox = xClickPosition - (xDistance / 2);
		double y1ZoomBox = yClickPosition - (yDistance / 2);
		CanvasService.setCanvasResolution(canvasResolution);
		CanvasService.configureCanvasBoxX(x1ZoomBox);
		CanvasService.configureCanvasBoxY(y1ZoomBox);
	
		paintFastZoom(ZOOM_IN, xFastZoom, yFastZoom, 0, 0, 0);
	}
}
