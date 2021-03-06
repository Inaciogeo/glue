package br.org.funcate.glue.model.canvas;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.CalculatorService;
import br.org.funcate.glue.model.ESRILatLongTile;
import br.org.funcate.glue.model.Projection;
import br.org.funcate.glue.model.RgbColor;
import br.org.funcate.glue.model.ScaleService;
import br.org.funcate.glue.model.Theme;
import br.org.funcate.glue.model.View;
import br.org.funcate.glue.model.ViewSet;
import br.org.funcate.glue.model.cache.TileCache;
import br.org.funcate.glue.model.cache.TileType;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.model.request.GoogleEnum;
import br.org.funcate.glue.model.request.TextRequest;
import br.org.funcate.glue.model.toolbar.ToolEnum;
import br.org.funcate.glue.model.tree.CustomNode;
import br.org.funcate.glue.model.tree.TreeService;
import br.org.funcate.glue.model.tree.TreeState;
import br.org.funcate.glue.service.TerraJavaClient;
import br.org.funcate.glue.utilities.PropertiesReader;
import br.org.funcate.glue.view.LocalOptionPane;
import br.org.funcate.glue.view.ToolWMS;

public abstract class CanvasService {

	private static final double EXTENT = Double.parseDouble(PropertiesReader
			.getProperty("google.parameter.extent"));
	public static final int TILE_SIZE = Integer.parseInt(PropertiesReader
			.getProperty("tile.size"));
	private static int zoom;

	public static boolean isEmptyBox() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		Box box = state.getBox();
		if (box.getX1() == 0.0 && box.getY1() == 0.0 && box.getX2() == 0.0
				&& box.getY2() == 0.0) {
			return true;
		}
		return false;
	}

	/**
	 * Sets the canvas box, according to canvas size and its resolution,
	 * ajusting the resolution to a possible zoom level,if necessary and
	 * ajusting the box to the canvas aspect relation. 
	 * @param box
	 */
	static void setBox(Box box) {
		double largura = box.getX2() - box.getX1();
		double altura = box.getY2() - box.getY1();
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		int canvasWidth = state.getCanvasWidth();
		int canvasHeight = state.getCanvasHeight();
		double resX = largura / canvasWidth;
		double resY = altura / canvasHeight;
		
		if (resX > resY) {//
//			if ("VirtualEarthMercator".equals(currentProjection.getName())) {
				setCanvasResolution(resX);//ajusta o zoom level
//			} else {
//				state.setResolution(resX);
//			}
			configureCanvasBoxX(box.getX1());
			box.setY1(box.getY1() + altura / 2 - canvasHeight / 2
					* state.getResolution());
			configureCanvasBoxY(box.getY1());
		} else {
//			if ("VirtualEarthMercator".equals(currentProjection.getName())) {
				setCanvasResolution(resY);
//			} else {
//				state.setResolution(resY);
//			}
			configureCanvasBoxY(box.getY1());
			box.setX1(box.getX1() + largura / 2 - canvasWidth / 2
					* state.getResolution());
			configureCanvasBoxX(box.getX1());
		}

		double scale = CalculatorService.getCurrentScale();
		state.setScale(scale);
	}

	/**
	 * It modifies the resolution of canvas according to projection
	 * 
	 * @param r
	 *            The new resolution
	 */
	public static void setCanvasResolution(double r) {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		state.setResolution(r);
		Projection currentProjection = state.getProjection();
		if ("VirtualEarthMercator".equals(currentProjection.getName())) {
			setZoomLevel();
			double newResolution = EXTENT
					/ ((Math.pow(2, state.getZoomLevel())) * TILE_SIZE);
			state.setResolution(newResolution);
		}
		
	if ("Instituto Geogr�fico e Cartogr�fico".equals(state.getDataSource())) {//BIRA RESOLUTION
		//if("LatLong".equals(currentProjection.getName())) {
			
			for (int i = 0; i < ESRILatLongTile.getResolution().length; i++) {
				zoom = i;
				if(r>=ESRILatLongTile.getResolution()[i]){
					//zoom = i;
					break;
				}	
			}
			double newResolution = ESRILatLongTile.getResolution()[zoom];
			state.setResolution(newResolution);
			state.setZoomLevel(zoom);
			
		}
	}

	/**
	 * It defines the zoom level of the Google projection according to the
	 * canvas resolution
	 * 
	 */
	private static void setZoomLevel() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		double numberOfTiles = EXTENT / state.getResolution() / TILE_SIZE;
		int newZoomLevel = (int) (Math.round(Math.log(numberOfTiles)/ Math.log(2)));
		state.setZoomLevel(newZoomLevel);
		ScaleService.verifyZoomLevelLimits(newZoomLevel);
	}

	/**
	 * It modifies the _canvasBoxX1 and the _canvasBoxX2 according to x
	 * 
	 * @param x
	 *            horizontal coordinate of Canvas box lower left corner
	 */
	public static void configureCanvasBoxX(double x) {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		double canvasResolution = state.getResolution();
		Box canvasBox = state.getBox();
		double canvasBoxX1 = x;
		double canvasBoxX2 = canvasBoxX1 + state.getCanvasWidth()
				* canvasResolution;
		canvasBox.setX1(canvasBoxX1);
		canvasBox.setX2(canvasBoxX2);
	}

	/**
	 * It modifies the _canvasBoxY1 and the _canvasBoxY2 according to y
	 * 
	 * @param y
	 *            vertical coordinate of Canvas box lower left corner
	 */
	public static void configureCanvasBoxY(double y) {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		double canvasResolution = state.getResolution();
		Box canvasBox = state.getBox();
		double canvasBoxY1 = y;
		double canvasBoxY2 = canvasBoxY1 + state.getCanvasHeight()
				* canvasResolution;
		canvasBox.setY1(canvasBoxY1);
		canvasBox.setY2(canvasBoxY2);
	}

	public static void draw(boolean clearCache,
			boolean enablePanTool) throws GlueServerException {
		boolean hasSomethingToPaint = manageImageSource();

		if (enablePanTool) {
			AppSingleton.getInstance().getMediator().setSelectedTool(ToolEnum.PAN);
		}

		if (!hasSomethingToPaint) {
			clearCanvas();
			return;
		}

		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		ImageSourceDefinition background = state.getBackground();
		ImageSourceDefinition foreground = state.getForeground();
		Mediator mediator = singleton.getMediator();
		// mediator.setSelectedTool(ToolEnum.PAN); // WITH PANTOOL SETTING
		Box box = null;

		boolean isNewBox = false;

		boolean predefinedBoxEnabled = new Boolean(
				PropertiesReader.getProperty("box.predefined.enabled"));

		if (predefinedBoxEnabled) {
			configPredefinedBox(background, foreground);
			TreeService.updateTreeVisibility();
		}

		if (isViewChanged() && isEmptyBox()) {
			box = getNewBoxFromSource();
			isNewBox = true;
		} else {
			box = state.getBox();
		}

		box = convertBoxUsingProjection(box, isNewBox);
		state.setProjection(background.getProjection());
		setBox(box);
		state.setView(getViewFromImageSource());

		if (clearCache) {
			clearCache();
		}

		if (isGoogleActive()) {
			createGoogleCopyrightText();
		}

		if (hasBDImageSource()) {
			createTerraLibTecnologyText();
		}

		setOtherComponentsEnabled(true);
		mediator.rebuildScaleCombo();
		ScaleService.updateScaleSettings();
		UndoRedoService.clearUndoRedoMemory();
		GeneralTileSchema.generateTilesLists(true);
	}

	@SuppressWarnings("unused")
	private static void createTerraLibTecnologyText() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		CanvasGraphicsBuffer canvasGraphicsBuffer = state
				.getCanvasGraphicsBuffer();
	}

	private static void createGoogleCopyrightText() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		CanvasGraphicsBuffer canvasGraphicsBuffer = state
				.getCanvasGraphicsBuffer();

		Color color = Color.black;

		// ImageSourceDefinition background = state.getBackground();
		// GoogleEnum type = (GoogleEnum) background.getSource();
		// if (type == GoogleEnum.HYBRID || type == GoogleEnum.SATELLITE) {
		// color = Color.white;
		// }

		canvasGraphicsBuffer.createImgGoogleCopyright(color);
	}
	//inacio - poc, somente para apresetacao
	public static void createMark(){
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		CanvasGraphicsBuffer canvasGraphicsBuffer = state.getCanvasGraphicsBuffer();
		canvasGraphicsBuffer.createMarker();
		
	}
	//inacio - poc, somente para apresetacao
	public static void deleteMark(){
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		CanvasGraphicsBuffer canvasGraphicsBuffer = state.getCanvasGraphicsBuffer();
		canvasGraphicsBuffer.deleteMarker();	
	}

	private static void clearCache() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		TileCache tileCache = state.getTileCache();
		tileCache.clean();
	}

	public static boolean isForegroundEnabled() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		return state.getForeground() != null;
	}

	private static boolean manageImageSource() throws GlueServerException {
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		CanvasState state = singleton.getCanvasState();
		TerraJavaClient services = singleton.getServices();

		ImageSourceDefinition toolBarSource = state.getToolbarSource();
		ImageSourceDefinition background = null;
		ImageSourceDefinition foreground = null;

		boolean isTreeEnabled = mediator.isTerraLibSelected();

		if (isTreeEnabled) {
			CustomNode nodeView = mediator.getCurrentView();
			if(nodeView != null){
				View terraLibView = nodeView.getView();
				if (terraLibView == null) {
					isTreeEnabled = false;
					System.out.println("FATAL ERROR: Selected view is null!");
				} else {
					terraLibView.buildViewToPlot();
					background = getImageSourceFromView(terraLibView);
					state.setBackground(background);
					state.setForeground(null);
					terraLibView.setForegroundProjection(null);
					services.configCanvas(background.getSourceName(),background.getProjection(), Color.white);

					RgbColor backgroundColor = new RgbColor();
					backgroundColor.setRed(Color.white.getRed());
					backgroundColor.setGreen(Color.white.getGreen());
					backgroundColor.setBlue(Color.white.getBlue());

					AppSingleton.getInstance().getCanvasState()
							.setBackgroundColor(backgroundColor);
				}
			}			
		}

		if (!isTreeEnabled && toolBarSource == null) {
			clearCanvas();
			return false;
		}

		if (toolBarSource != null && isTreeEnabled) {
			foreground = background;
			background = toolBarSource;
			View terraLibView = mediator.getCurrentView().getView();
			terraLibView.setForegroundProjection(toolBarSource.getProjection());
			services.configCanvas(foreground.getSourceName(),background.getProjection(), Color.white);
		} else if (toolBarSource != null && !isTreeEnabled) {
			background = toolBarSource;
		}

		state.setBackground(background);
		state.setForeground(foreground);
		return true;
	}

	public static int[] expandCanvas(boolean expanded) {
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		state.setCanvasExpanded(expanded);

		int[] bounds = new int[4];

		if (expanded) {
			bounds[0] = 0;
			bounds[1] = Integer.parseInt(PropertiesReader
					.getProperty("canvas.bound.y0"));
			bounds[2] = Integer.parseInt(PropertiesReader
					.getProperty("canvas.bound.x0")) + state.getCanvasWidth();
			state.setCanvasWidth(bounds[2]);
			bounds[3] = state.getCanvasHeight();
			state.setCanvasHeight(bounds[3]);
		} else {
			bounds[0] = Integer.parseInt(PropertiesReader
					.getProperty("canvas.bound.x0"));
			bounds[1] = Integer.parseInt(PropertiesReader
					.getProperty("canvas.bound.y0"));
			bounds[2] = Integer.parseInt(PropertiesReader
					.getProperty("canvas.width"));
			state.setCanvasWidth(bounds[2]);
			bounds[3] = Integer.parseInt(PropertiesReader
					.getProperty("canvas.height"));
			state.setCanvasHeight(bounds[3]);
		}

		updateCanvasSizeSettings();
		return bounds;
	}

	public static void updateCanvasSizeSettings() {
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		configureCanvasBoxX(state.getBox().getX1());
		configureCanvasBoxY(state.getBox().getY1());
		state.getCanvasGraphicsBuffer().redefineBuffers();
	}

	private static void setOtherComponentsEnabled(boolean b) {
		Mediator mediator = AppSingleton.getInstance().getMediator();
		mediator.setOtherComponentsEnabled(b);
	}

	private static Box convertBoxUsingProjection(Box box, boolean isNewBox)
			throws GlueServerException {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		TerraJavaClient services = singleton.getServices();

		Projection currentProjection = state.getProjection();
		ImageSourceDefinition background = state.getBackground();
		Projection newProjection = background.getProjection();

		if (isNewBox) {
			if (isForegroundEnabled()) {
				return services
						.remapCoordinates(box, state.getForeground()
								.getProjection(), state.getBackground()
								.getProjection());
			}
			return box;
		}

		if (currentProjection == null) {
			return box;
		}

		box = services.remapCoordinates(box, currentProjection, newProjection);
		return box;
	}

	private static boolean isViewChanged() {
		View view = getViewFromImageSource();
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		View currentView = state.getView();
		if (view != null && currentView == null) {
			return true;
		}

		if (view != null && currentView != null) {
			return !view.equals(currentView);
		}

		return false;
	}

	static boolean isGoogleActive() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		ImageSourceDefinition background = state.getBackground();

		if (background == null) {
			return false;
		}

		return background.getType() == TileType.GOOGLE;
	}

	public static void executeKeyPressedEvent(int keyCode)
			throws GlueServerException {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		Box currentBox = state.getBox();
		switch (keyCode) {

		case KeyEvent.VK_B:
			double x1 = currentBox.getX1();
			double y1 = currentBox.getY1();
			double x2 = currentBox.getX2();
			double y2 = currentBox.getY2();

			String sX1 = "x1 -> " + x1;
			String sY1 = "y1 -> " + y1;
			String sX2 = "x2 -> " + x2;
			String sY2 = "y2 -> " + y2;

			System.out.println("------------------------------");
			System.out.println("--- Canvas Box Coordinates ---");
			System.out.println(sX1);
			System.out.println(sY1);
			System.out.println(sX2);
			System.out.println(sY2);
			System.out.println("------------------------------");

			JOptionPane.showMessageDialog(null, sX1 + "\n" + sY1 + "\n" + sX2
					+ "\n" + sY2 + "\n", "Canvas Box Coordinates",
					JOptionPane.INFORMATION_MESSAGE);

			break;
		case KeyEvent.VK_P:
			Projection proj = state.getProjection();
			String projName = proj.getName();

			System.out.println("---------------------------------------");
			System.out.println("Canvas Projection -> " + projName);
			System.out.println("---------------------------------------");

			JOptionPane.showMessageDialog(null, projName, "Canvas Projection",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case KeyEvent.VK_R:
			double res = state.getResolution();

			System.out.println("----------------------------------");
			System.out.println("Canvas Resolution -> " + res);
			System.out.println("----------------------------------");

			JOptionPane.showMessageDialog(null, res, "Canvas Resolution",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case KeyEvent.VK_S:
			double scale = state.getScale();

			System.out.println("----------------------------------");
			System.out.println("Canvas Scale -> " + scale);
			System.out.println("----------------------------------");

			JOptionPane.showMessageDialog(null, scale, "Canvas Scale",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case KeyEvent.VK_M:
			double maxScale = state.getView().getMaxScale();

			System.out.println("----------------------------------");
			System.out.println("Maximal Scale -> " + maxScale);
			System.out.println("----------------------------------");

			JOptionPane.showMessageDialog(null, maxScale, "Maximal Scale",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case KeyEvent.VK_N:
			double minScale = state.getView().getMinScale();

			System.out.println("----------------------------------");
			System.out.println("Minimal Scale -> " + minScale);
			System.out.println("----------------------------------");

			JOptionPane.showMessageDialog(null, minScale, "Minimal Scale",
					JOptionPane.INFORMATION_MESSAGE);
			break;
		case KeyEvent.VK_F5:
			draw(true, true);
			break;
		}
	}

	private static View getViewFromImageSource() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		ImageSourceDefinition background = state.getBackground();
		ImageSourceDefinition foreground = state.getForeground();
		View view = null;
		if (foreground != null) {
			if (foreground.getType() == TileType.TERRALIB) {
				view = (View) foreground.getSource();
			}
		}

		if (background != null) {
			if (background.getType() == TileType.TERRALIB) {
				view = (View) background.getSource();
			}
		}

		return view;
	}

	private static Box getNewBoxFromSource() {
		Box box;
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		ImageSourceDefinition background = state.getBackground();
		ImageSourceDefinition foreground = state.getForeground();

		if (foreground == null) {
			box = new Box(background.getX1(), background.getY1(),
					background.getX2(), background.getY2());
		} else {
			box = new Box(foreground.getX1(), foreground.getY1(),
					foreground.getX2(), foreground.getY2());
		}
		
		return box;
	}

	private static Box configPredefinedBox(ImageSourceDefinition background,
			ImageSourceDefinition foreground) throws GlueServerException {
		AppSingleton singleton = AppSingleton.getInstance();
		TerraJavaClient services = singleton.getServices();
		
		double x1 = Double.parseDouble(PropertiesReader
				.getProperty("box.predefined.x1"));
		double y1 = Double.parseDouble(PropertiesReader
				.getProperty("box.predefined.y1"));
		double x2 = Double.parseDouble(PropertiesReader
				.getProperty("box.predefined.x2"));
		double y2 = Double.parseDouble(PropertiesReader
				.getProperty("box.predefined.y2"));

		Box box = new Box(x1, y1, x2, y2);

		if (foreground == null) {
			box = services.remapCoordinates(box, createLatLongProjection(),
					background.getProjection());
			background.setX1(box.getX1());
			background.setY1(box.getY1());
			background.setX2(box.getX2());
			background.setY2(box.getY2());
		} else {
			box = services.remapCoordinates(box, createLatLongProjection(),
					foreground.getProjection());
			foreground.setX1(box.getX1());
			foreground.setY1(box.getY1());
			foreground.setX2(box.getX2());
			foreground.setY2(box.getY2());
		}

		return box;
	}

	private static ImageSourceDefinition getImageSourceFromView(View view) {
		try {
			Box box = view.getBox();
			ImageSourceDefinition terraLibSource = new ImageSourceDefinition(
					box.getX1().doubleValue(), box.getY1().doubleValue(), box
							.getX2().doubleValue(), box.getY2().doubleValue(),
					view, TileType.TERRALIB, view.getProjection(),
					view.getName());
			return terraLibSource;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static void clearCanvas() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		Mediator mediator = singleton.getMediator();

		setOtherComponentsEnabled(false);
		UndoRedoService.clearUndoRedoMemory();

		CanvasGraphicsBuffer canvasGraphicsBuffer = state
				.getCanvasGraphicsBuffer();

		cleanToolGraphics();

		canvasGraphicsBuffer.drawImageNullCanvas(getImageNullCanvas(),
				state.getCanvasWidth(), state.getCanvasHeight());

		mediator.disableAllTools();
		mediator.setSelectedTool(null);

		state.setView(null);
		state.setBox(new Box(0.0, 0.0, 0.0, 0.0));
	}

	public static void cleanToolGraphics() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		CanvasGraphicsBuffer canvasGraphicsBuffer = state
				.getCanvasGraphicsBuffer();
		canvasGraphicsBuffer.setToolGraphics(null);
		canvasGraphicsBuffer.notifyObservers();
	}

	private static Image getImageNullCanvas() {
		return new ImageIcon("br/org/funcate/glue/image/fundo.png").getImage();
	}

	public static void setToolBarSource(Object source) {
		ImageSourceDefinition imageSource = null;
				
		if(source != null){
			if (source.equals("2")) {
				imageSource = createGoogleSourceDefinition(GoogleEnum.MAP);
			}
			if (source.equals("1")) {
				imageSource = createGoogleSourceDefinition(GoogleEnum.MAP);
				imageSource.setType(TileType.CGI);
			}
			if (source.equals("0")) {
				imageSource = createGoogleSourceDefinition(GoogleEnum.MAP);
				imageSource.setType(TileType.OPENSTREET);
			}
			if (source.equals("3")) {
				
				ToolWMS wms = null;
				wms = ToolWMS.getInstance();
				wms.setVisible(true);
				AppSingleton singleton = AppSingleton.getInstance();
				CanvasState state = singleton.getCanvasState();
				imageSource = state.getBackground();
				imageSource.setProjection(createLatLongProjection());
				imageSource.setType(TileType.WMS);
			}
			if (source.equals("4")) {
				AppSingleton singleton = AppSingleton.getInstance();
				CanvasState state = singleton.getCanvasState();
				imageSource = state.getBackground();
				imageSource.setProjection(createLatLongProjection());
				imageSource.setType(TileType.INSTITUTO);
			}
		}
		
		if (source instanceof ImageSourceDefinition) {
			imageSource = (ImageSourceDefinition) source;
			imageSource.setProjection(createLatLongProjection());
		}
		
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		state.setToolbarSource(imageSource);
	}
	
	/**
	 * This method controls the state of Toolbar's WMS button
	 * TODO:apagar, metodo antigo usado no toobarController 
	 */
//	public void setWMS() {
//		Mediator mediator = AppSingleton.getInstance().getMediator();
//		ToolWMS wms = null;
//		ToolState wmsTool = ToolService.getTool(ToolEnum.WMS);
//
//		if (wmsTool.isSelected() == false) {
//			try {
//				wms = ToolWMS.getInstance();
//				wms.setVisible(true);
//				ToolService.setSelectedTool(ToolEnum.WMS);
//				//this.toolbar.setWmsSelected(true);
//			} catch (NullPointerException e) {
//				ToolService.setSelectedTool(ToolEnum.WMS);
//				//this.toolbar.setWmsSelected(true);
//			}
//		} else {
//			ToolService.setSelectedTool(ToolEnum.WMS);
//			//this.toolbar.setWmsSelected(false);
//			mediator.setToolBarSource(null);
//		}
//		
//	}
		

	private static Projection createGoogleProjection() {
		Projection googleProjection = new Projection();
		googleProjection.setDatum("WGS84");
		googleProjection.setUnits("Meters");
		googleProjection.setName("VirtualEarthMercator");
		googleProjection.setLat0(0.0);
		googleProjection.setLon0(0.0);
		googleProjection.setStlat1(0.0);
		googleProjection.setStlat2(0.0);
		googleProjection.setScale(0.0);
		googleProjection.setOffx("0.0");
		googleProjection.setOffy("0.0");
		googleProjection.setHemNorth(false);
		googleProjection.setZone(0);
		googleProjection.setSelected(false);
		return googleProjection;
	}

	private static Projection createLatLongProjection() {
		Projection wmsProjection = new Projection();
		wmsProjection.setDatum("SAD69");
		//wmsProjection.setDatum("WGS84");//bira
		wmsProjection.setUnits("DecimalDegrees");
		wmsProjection.setName("LatLong");
		//wmsProjection.setName("ESRI");//bira
		wmsProjection.setLat0(0.0);
		wmsProjection.setLon0(0.0);
		wmsProjection.setStlat1(0.0);
		wmsProjection.setStlat2(0.0);
		wmsProjection.setScale(1.0);
		wmsProjection.setOffx("0.0");
		wmsProjection.setOffy("0.0");
		wmsProjection.setHemNorth(false);
		wmsProjection.setZone(-2147483648);
		wmsProjection.setSelected(false);
		return wmsProjection;
	}
	/**
	 * creates the projection to request tiles from the ESRI server, 
	 * used by the cartographic and geographical instituted of S�o Paulo
	 * @return
	 */
	@SuppressWarnings("unused")
	private static Projection createESRIProjection() {
		Projection ESRIProjection = new Projection();
		//ESRIProjection.setDatum("SAD69");
		ESRIProjection.setDatum("WGS84");//bira
		ESRIProjection.setUnits("DecimalDegrees");
		ESRIProjection.setName("LatLong");//bira
		ESRIProjection.setLat0(0.0);
		ESRIProjection.setLon0(0.0);
		ESRIProjection.setStlat1(0.0);
		ESRIProjection.setStlat2(0.0);
		ESRIProjection.setScale(1.0);
		ESRIProjection.setOffx("0.0");
		ESRIProjection.setOffy("0.0");
		ESRIProjection.setHemNorth(false);
		ESRIProjection.setZone(-2147483648);
		ESRIProjection.setSelected(false);
		return ESRIProjection;
	}

	private static ImageSourceDefinition createGoogleSourceDefinition(
			GoogleEnum googleEnum) {
		ImageSourceDefinition googleSource = GoogleRegionBoxService.getImageSourceDefinition();
		googleSource.setSource(googleEnum);
		googleSource.setProjection(createGoogleProjection());
		return googleSource;
	}

	static void setLinkCursor(boolean b) {
		Mediator mediator = AppSingleton.getInstance().getMediator();
		mediator.setLinkCursor(b);
	}

	public static void setPressingHandPanCursor(boolean b) {
		Mediator mediator = AppSingleton.getInstance().getMediator();
		mediator.setPressingHandPanCursor(b);
	}

	static void setUndoRedoBox(UndoRedoItem item) {
		setCanvasResolution(item.getResolution());
		configureCanvasBoxX(item.getBoxX1());
		configureCanvasBoxY(item.getBoxY1());
		GeneralTileSchema.generateTilesLists(true);
		ScaleService.updateScaleSettings();
	}

	public static void resetLayerBoxPosition() throws GlueServerException {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		ImageSourceDefinition background = state.getBackground();
		ImageSourceDefinition foreground = state.getForeground();

		if (isForegroundEnabled()) {
			Box box = new Box(foreground.getX1(), foreground.getY1(),
					foreground.getX2(), foreground.getY2());
			TerraJavaClient services = singleton.getServices();
			box = services.remapCoordinates(box, foreground.getProjection(),
					background.getProjection());
			setBox(box);
		} else {
			Box box = new Box(background.getX1(), background.getY1(),
					background.getX2(), background.getY2());
			setBox(box);
		}
		ScaleService.updateScaleSettings();
		GeneralTileSchema.generateTilesLists(true);
	}

	public static boolean isExpandedCanvas() {
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		return state.isCanvasExpanded();
	}

	// ! Returns if some BD Image Source is activated on canvas.
	public static boolean hasBDImageSource() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		ImageSourceDefinition background = state.getBackground();
		return background.getType() == TileType.TERRALIB
				|| isForegroundEnabled();
	}

	public static int[] getCanvasSizesToApplet(int appletWidth, int appletHeight) {
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		int[] size = new int[2];
		int minWidthValue = Integer.parseInt(PropertiesReader
				.getProperty("applet.min.width"));
		int minHeightValue = Integer.parseInt(PropertiesReader
				.getProperty("applet.min.height"));

		if (!state.isCanvasExpanded()) {
			int xPosition = Integer.parseInt(PropertiesReader
					.getProperty("canvas.bound.x0"));

			appletWidth = appletWidth - xPosition;
		}

		if (minWidthValue < appletWidth) {
			size[0] = appletWidth;
		} else {
			size[0] = minWidthValue;
		}

		int yPosition = Integer.parseInt(PropertiesReader
				.getProperty("canvas.bound.y0"));

		appletHeight = appletHeight - yPosition;

		if (minHeightValue < appletHeight) {
			size[1] = appletHeight;
		} else {
			size[1] = minHeightValue;
		}

		state.setCanvasWidth(size[0]);
		state.setCanvasHeight(size[1]);
		updateCanvasSizeSettings();

		return size;
	}

	public static void selectObject(String themeName, String objectId)
			throws GlueServerException {
		AppSingleton singleton = AppSingleton.getInstance();
		TerraJavaClient services = singleton.getServices();
		CanvasState canvasState = singleton.getCanvasState();
		View view = canvasState.getView();

		if (!view.hasTheme(themeName)) {
			LocalOptionPane.getInstance(
					"A sele��o n�o pode ser realizada na vista selecionada!",
					"Aten��o");
			return;
		}

		Box box = services.getCurrentThemeBox(themeName, "object_id = '"
				+ objectId + "'");

		if (box == null) {
			JOptionPane.showMessageDialog(null,
					"Selecione o tema correto para ser visualizado!",
					"Aten��o", JOptionPane.WARNING_MESSAGE);
			return;
		}

		List<String> list = new ArrayList<String>();
		list.add(objectId);
		HighlightObjectsService.addHightlightObjects(themeName, list);

		setBox(box);

		int xZoomPosition = canvasState.getCanvasWidth() / 2;
		int yZoomPosition = canvasState.getCanvasHeight() / 2;

		clearCache();
		ZoomToolService.zoomOut(xZoomPosition, yZoomPosition, false);
		setOtherComponentsEnabled(true);
		Mediator mediator = AppSingleton.getInstance().getMediator();
		mediator.rebuildScaleCombo();
		ScaleService.updateScaleSettings();
		GeneralTileSchema.generateTilesLists(true);
	}

	public static void selectStreet(String themeName, String restriction,
			List<String> objects) throws GlueServerException {
		AppSingleton singleton = AppSingleton.getInstance();
		TerraJavaClient services = singleton.getServices();

		Box box = services.getCurrentThemeBox(themeName, restriction);
		if (box == null) {
			JOptionPane.showMessageDialog(null,
					"Selecione o tema correto para ser visualizado!",
					"Aten��o", JOptionPane.WARNING_MESSAGE);
			return;
		}

		HighlightObjectsService.addHightlightObjects(themeName, objects);

		setBox(box);
		setOtherComponentsEnabled(true);
		Mediator mediator = AppSingleton.getInstance().getMediator();
		mediator.rebuildScaleCombo();
		ScaleService.updateScaleSettings();
		GeneralTileSchema.generateTilesLists(true);
	}

	public static void addPoint(double pointX, double pointY,
			Projection pointProjection, Vector<Object> attributes, Theme theme) {
		AppSingleton singleton = AppSingleton.getInstance();
		TerraJavaClient services = singleton.getServices();

		Vector<Object> verticeList = new Vector<Object>();
		verticeList.add(new Double(pointX));
		verticeList.add(new Double(pointY));

		String layerName = theme.getLayer().getName();

		try {
			services.addGeometry(4, verticeList, attributes, layerName,
					pointProjection);
		} catch (GlueServerException e) {
			// TODO TRATAR EXCE��O AP�S REALIZAR V�RIOS TESTES NESTE M�TODO
			e.printStackTrace();
		}
	}

	public static List<Double> getCentroid(String viewName, String themeName,
			String objectId) throws GlueServerException {
		AppSingleton singleton = AppSingleton.getInstance();
		TerraJavaClient services = singleton.getServices();
		List<Double> centroid;
		try {
			centroid = services.getCentroidForBiggestGeometry(viewName,
					themeName, objectId);

			return centroid;
		} catch (GlueServerException e) {
			throw new GlueServerException(e.getMessage());
		}
	}

	public static Projection getProjection(String viewName, String themeName) {
		AppSingleton singleton = AppSingleton.getInstance();
		TreeState treeState = singleton.getTreeState();
		ViewSet viewSet = treeState.getViewSet();
		List<View> views = viewSet.getViews();
		for (View view : views) {
			if (view.getName().equals(viewName)) {
				Theme theme = view.getThemeByName(themeName);
				return theme.getLayer().getProjection();
			}
		}

		return null;
	}

	public static boolean buildCollection(String objectId, String viewName,
			String themeName) {
		AppSingleton singleton = AppSingleton.getInstance();
		TerraJavaClient services = singleton.getServices();
		return services.buildCollection(objectId, viewName, themeName);
	}

	public static boolean updateThemeBox(String viewName, String themeName) {
		AppSingleton singleton = AppSingleton.getInstance();
		TerraJavaClient services = singleton.getServices();
		return services.updateThemeBox(viewName, themeName);
	}

	public static void cleanBuffer(BufferEnum bufferId) {
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		CanvasGraphicsBuffer bufferManager = state.getCanvasGraphicsBuffer();

		BufferedImage image = bufferManager.getBuffer(bufferId);
		Graphics2D g2d = image.createGraphics();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
		Rectangle2D rect = new Rectangle2D.Double(0, 0, image.getWidth(),
				image.getHeight());
		g2d.fill(rect);
		GeneralTileSchema.generateTilesLists(false);
	}
	
	static void drawText() {
		new TextRequest().start();
	}
}
