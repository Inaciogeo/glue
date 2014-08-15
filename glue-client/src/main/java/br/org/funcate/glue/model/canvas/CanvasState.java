package br.org.funcate.glue.model.canvas;

import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.Projection;
import br.org.funcate.glue.model.RgbColor;
import br.org.funcate.glue.model.View;
import br.org.funcate.glue.model.cache.Tile;
import br.org.funcate.glue.model.cache.TileCache;
import br.org.funcate.glue.utilities.PropertiesReader;

public class CanvasState {

	private CanvasGraphicsBuffer canvasGraphicsBuffer;

	private int canvasWidth;
	private int canvasHeight;

	private double resolution;

	private int zoomLevel;

	private Projection projection;

	private View view;

	private Box box;

	private Stack<UndoRedoItem> undoMemory;
	private Stack<UndoRedoItem> redoMemory;

	private List<Tile> backgroundTiles;
	private List<Tile> foregroundTiles;
	private List<Tile> foregroundAux;

	private TileCache tileCache;

	private ImageSourceDefinition background;
	private ImageSourceDefinition foreground;
	private ImageSourceDefinition toolbarSource;

	private float transparencyFactor;

	private boolean canvasExpanded;

	private double scale;

	private ImageObserver imgObs;

	private List<HighlightObjects> highlightObjects;

	private RgbColor backgroundColor;
	
	private String dataSource;
	
	private String gvSource;

	public CanvasState() {
		this.canvasWidth = Integer.parseInt(PropertiesReader.getProperty("canvas.width"));
		this.canvasHeight = Integer.parseInt(PropertiesReader.getProperty("canvas.height"));
		this.canvasGraphicsBuffer = new CanvasGraphicsBuffer(canvasWidth, canvasHeight);
		this.highlightObjects = new ArrayList<HighlightObjects>();
		this.box = new Box(0.0, 0.0, 0.0, 0.0);
		this.undoMemory = new Stack<UndoRedoItem>();
		this.redoMemory = new Stack<UndoRedoItem>();
		this.backgroundTiles = new ArrayList<Tile>();
		this.foregroundTiles = new ArrayList<Tile>();
		this.foregroundAux = new ArrayList<Tile>();
		this.tileCache = new TileCache(1024);
		this.canvasExpanded = false;
		this.transparencyFactor = 0.5f;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public boolean isCanvasExpanded() {
		return canvasExpanded;
	}

	public void setCanvasExpanded(boolean canvasExpanded) {
		this.canvasExpanded = canvasExpanded;
	}

	public int getCanvasWidth() {
		return canvasWidth;
	}

	public void setCanvasWidth(int canvasWidth) {
		this.canvasWidth = canvasWidth;
	}

	public int getCanvasHeight() {
		return canvasHeight;
	}

	public void setCanvasHeight(int canvasHeight) {
		this.canvasHeight = canvasHeight;
	}

	public double getResolution() {
		return resolution;
	}

	public void setResolution(double resolution) {
		this.resolution = resolution;
	}

	public int getZoomLevel() {
		return zoomLevel;
	}

	public void setZoomLevel(int zoomLevel) {
		this.zoomLevel = zoomLevel;
	}

	public Projection getProjection() {
		return projection;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Box getBox() {
		return box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public ImageSourceDefinition getBackground() {
		return background;
	}

	public void setBackground(ImageSourceDefinition background) {
		this.background = background;
	}

	public ImageSourceDefinition getForeground() {
		return foreground;
	}

	public void setForeground(ImageSourceDefinition foreground) {
		this.foreground = foreground;
	}

	public void setToolbarSource(ImageSourceDefinition toolbarSource) {
		this.toolbarSource = toolbarSource;
	}

	public ImageSourceDefinition getToolbarSource() {
		return toolbarSource;
	}

	public float getTransparencyFactor() {
		return transparencyFactor;
	}

	public void setTransparencyFactor(float transparencyFactor) {
		this.transparencyFactor = transparencyFactor;
	}

	public CanvasGraphicsBuffer getCanvasGraphicsBuffer() {
		return canvasGraphicsBuffer;
	}

	public Stack<UndoRedoItem> getUndoMemory() {
		return undoMemory;
	}

	public Stack<UndoRedoItem> getRedoMemory() {
		return redoMemory;
	}

	public List<Tile> getBackgroundTiles() {
		return backgroundTiles;
	}

	public List<Tile> getForegroundTiles() {
		return foregroundTiles;
	}

	public List<Tile> getForegroundAux() {
		return foregroundAux;
	}

	public TileCache getTileCache() {
		return tileCache;
	}

	public void setImageObserver(ImageObserver imgObs) {
		this.imgObs = imgObs;
	}

	public ImageObserver getImgObs() {
		return imgObs;
	}

	/**
	 * @return the highlightObjects
	 */
	public List<HighlightObjects> getHighlightObjects() {
		return highlightObjects;
	}

	public RgbColor getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(RgbColor backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getGvSource() {
		return gvSource;
	}

	public void setGvSource(String gvSource) {
		this.gvSource = gvSource;
	}
	
}
