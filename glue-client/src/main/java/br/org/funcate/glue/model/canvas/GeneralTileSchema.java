package br.org.funcate.glue.model.canvas;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.cache.Tile;
import br.org.funcate.glue.model.cache.TileCache;
import br.org.funcate.glue.model.cache.TileStatus;
import br.org.funcate.glue.model.cache.TileType;
import br.org.funcate.glue.model.request.TileRequest;
import br.org.funcate.glue.tool.TileThreadTimeAuxiliar;
import br.org.funcate.glue.utilities.PropertiesReader;

public abstract class GeneralTileSchema {

	private static final int TILE_SIZE = Integer.parseInt(PropertiesReader
			.getProperty("tile.size"));

	private static boolean drawText;
	private static boolean wheelMoved;

	public static void setWheelMoved(boolean wheelMoved) {
		GeneralTileSchema.wheelMoved = wheelMoved;
	}

	public static void setDrawText(boolean drawText) {
		GeneralTileSchema.drawText = drawText;
	}

	/**
	 * It returns the horizontal lowest left tile index of the Canvas box in the
	 * world.
	 */
	private static int getTileIndexX() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState model = singleton.getCanvasState();
		Box box = model.getBox();
		// Projection projection = model.getProjection();
		double canvasBoxX1 = box.getX1();
		double offSetX = 0;// Double.parseDouble(projection.getOffx());
		double canvasResolution = model.getResolution();
		int tileIndexX = (int) (Math.floor((canvasBoxX1 - offSetX)/(TILE_SIZE * canvasResolution)));
		return tileIndexX;
	}

	/**
	 * It returns the vertical lowest left tile index of the Canvas box in the
	 * world.
	 */
	private static int getTileIndexY() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState model = singleton.getCanvasState();
		Box box = model.getBox();
		// Projection projection = model.getProjection();
		double canvasBoxY1 = box.getY1();
		double offSetY = 0;// Double.valueOf(projection.getOffy());
		double canvasResolution = model.getResolution();
		int tileIndexY = (int) (Math.floor((canvasBoxY1 - offSetY)
				/ (TILE_SIZE * canvasResolution)));
		return tileIndexY;
	}

	/**
	 * It returns an array with the coordinates concerning to the tile lower
	 * left corner and upper right corner in relation to the world
	 */
	private static double[] getTile(int tileIndexX, int tileIndexY) {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState model = singleton.getCanvasState();
		double canvasResolution = model.getResolution();
		double tileX1 = tileIndexX * canvasResolution * TILE_SIZE;
		double tileY1 = tileIndexY * canvasResolution * TILE_SIZE;
		double tileX2 = tileX1 + TILE_SIZE * canvasResolution;
		double tileY2 = tileY1 + TILE_SIZE * canvasResolution;
		double[] tile = { tileX1, tileY1, tileX2, tileY2 };
		return tile;
	}

	/**
	 * It returns an array with the pixels coordinates concerning to the
	 * sub-tile upper left corner and lower right corner and the Canvas
	 * corresponding coordinates
	 */
	private static int[] getSubTile(int tileIndexX, int tileIndexY) {
		CanvasState state = AppSingleton.getInstance().getCanvasState();

		double canvasResolution = state.getResolution();
		int canvasHeight = state.getCanvasHeight();
		int canvasWidth = state.getCanvasWidth();
		Box box = state.getBox();

		double canvasBoxX1 = box.getX1();
		double canvasBoxY1 = box.getY1();
		double canvasBoxX2 = box.getX2();
		double canvasBoxY2 = box.getY2();

		double[] tile = getTile(tileIndexX, tileIndexY);
		int tileLeftTopY1 = (int) (tile[3] <= canvasBoxY2 ? 0 : Math
				.round((tile[3] - canvasBoxY2) / canvasResolution));
		int tileLeftTopX1 = (int) (tile[0] >= canvasBoxX1 ? 0 : Math
				.round((canvasBoxX1 - tile[0]) / canvasResolution));
		int tileRightDownY2 = (int) (tile[1] >= canvasBoxY1 ? (TILE_SIZE - 1)
				: Math.round(TILE_SIZE - (canvasBoxY1 - tile[1])
						/ canvasResolution)); // -1
		int tileRightDownX2 = (int) (tile[2] <= canvasBoxX2 ? (TILE_SIZE - 1)
				: Math.round((canvasBoxX2 - tile[0]) / canvasResolution)); // -1
		int canvasLeftTopY1 = (int) (tile[3] >= canvasBoxY2 ? 0 : Math
				.round((canvasBoxY2 - tile[3]) / canvasResolution));
		int canvasLeftTopX1 = (int) (tile[0] <= canvasBoxX1 ? 0 : Math
				.round((tile[0] - canvasBoxX1) / canvasResolution));
		int canvasRightDownY2 = (int) (tile[1] <= canvasBoxY1 ? (canvasHeight - 1)
				: Math.round((canvasBoxY2 - tile[1]) / canvasResolution)); // -1
		int canvasRightDownX2 = (int) (tile[2] >= canvasBoxX2 ? (canvasWidth - 1)
				: Math.round((tile[2] - canvasBoxX1) / canvasResolution)); // -1
		int[] subTile = { tileLeftTopX1, tileLeftTopY1, tileRightDownX2,
				tileRightDownY2, canvasLeftTopX1, canvasLeftTopY1,
				canvasRightDownX2, canvasRightDownY2 };
		return subTile;
	}

	/**
	 * Verifies if the box of a tile intercepts canvas box.
	 */
	private static boolean verifyCanvasInterception(int tileIndexX,
			int tileIndexY) {
		double[] tileBox = getTile(tileIndexX, tileIndexY);
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState model = singleton.getCanvasState();
		Box box = model.getBox();
		double canvasBoxX1 = box.getX1();
		double canvasBoxY1 = box.getY1();
		double canvasBoxX2 = box.getX2();
		double canvasBoxY2 = box.getY2();
		if (canvasBoxX1 < tileBox[2] && canvasBoxY2 > tileBox[1]
				&& canvasBoxX2 >= tileBox[2] && canvasBoxY1 <= tileBox[1])
			return true;
		if (canvasBoxX1 < tileBox[2] && canvasBoxY1 < tileBox[3]
				&& canvasBoxX2 >= tileBox[2] && canvasBoxY2 >= tileBox[3])
			return true;
		if (canvasBoxX2 > tileBox[0] && canvasBoxY1 < tileBox[3]
				&& canvasBoxX1 <= tileBox[0] && canvasBoxY2 >= tileBox[3])
			return true;
		if (canvasBoxX2 > tileBox[0] && canvasBoxY2 > tileBox[1]
				&& canvasBoxX1 <= tileBox[0] && canvasBoxY1 <= tileBox[1])
			return true;
		return false;
	}

	/**
	 * Starts the process of generation of the tiles list.
	 * 
	 * @param drawText
	 *            TODO
	 */
	public static synchronized void generateTilesLists(boolean drawText) {
		GeneralTileSchema.drawText = drawText;
		if (drawText) {
			CanvasService.cleanBuffer(BufferEnum.TEXT);
			GeneralTileSchema.drawText = true;
		}
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState model = singleton.getCanvasState();
		double canvasResolution = model.getResolution();
		List<Tile> backgroundTiles = model.getBackgroundTiles();
		List<Tile> foregroundTiles = model.getForegroundTiles();
		List<Tile> foregroundAux = model.getForegroundAux();
		backgroundTiles.clear();
		foregroundTiles.clear();
		foregroundAux.clear();
		int j;
		int tileIndexX = getTileIndexX();
		int tileIndexY = getTileIndexY();
		for (int i = tileIndexY; true; i++) {
			for (j = tileIndexX; true; j++) {
				if (verifyCanvasInterception(j, i)) {
					backgroundTiles.add(new Tile(null, j, i, canvasResolution,
							null));
					if (CanvasService.isForegroundEnabled()) {
						foregroundTiles.add(new Tile(null, j, i,
								canvasResolution, null));
					}
				} else
					break;
			}
			if (j == tileIndexX)
				break;
		}
		//Atenção: esta condição controla a requisição de Tiles
		if (!TileThreadTimeAuxiliar.timeIsOn)
			plotTiles();
	}

	/**
	 * Repaints the foreground tile if a background tile was painted over the
	 * tile.
	 */
	private static void plotForegroundTile(Tile tile, int[] sub) {
		CanvasState state = AppSingleton.getInstance().getCanvasState();

		List<Tile> foregroundAux = state.getForegroundAux();

		for (int i = 0; i < foregroundAux.size(); i++) {
			if (tile.equals(foregroundAux.get(i))) {
				CanvasGraphicsBuffer canvasGraphicsBuffer = state
						.getCanvasGraphicsBuffer();

				canvasGraphicsBuffer.drawTileImage(foregroundAux.get(i)
						.getImage(), sub[4], sub[5], sub[6], sub[7], sub[0],
						sub[1], sub[2], sub[3]);
			}
		}
	}

	/**
	 * Starts painting the tiles on canvas buffer.
	 */
	public synchronized static void plotTiles() {

		Tile tile;
		BufferedImage tileImageBuffer;

		CanvasState state = AppSingleton.getInstance().getCanvasState();

		List<Tile> backgroundTiles = state.getBackgroundTiles();
		List<Tile> foregroundTiles = state.getForegroundTiles();
		List<Tile> foregroundAux = state.getForegroundAux();

		CanvasGraphicsBuffer canvasGraphicsBuffer = state
				.getCanvasGraphicsBuffer();

		TileCache tileCache = state.getTileCache();

		ImageSourceDefinition background = state.getBackground();

		for (int i = 0; i < backgroundTiles.size(); i++) {
			int indexX = backgroundTiles.get(i).getIndexX();
			int indexY = backgroundTiles.get(i).getIndexY();
			double res = backgroundTiles.get(i).getResolution();
			if (background.getType() == TileType.GOOGLE)
				tile = tileCache.getTile(indexX, indexY, res, TileType.GOOGLE);
			else if (background.getType() == TileType.WMS)
				tile = tileCache.getTile(indexX, indexY, res, TileType.WMS);
			else if (background.getType() == TileType.OPENSTREET)
				tile = tileCache.getTile(indexX, indexY, res, TileType.OPENSTREET);
			else if (background.getType() == TileType.CGI)
				tile = tileCache.getTile(indexX, indexY, res, TileType.CGI);
			else if (background.getType() == TileType.INSTITUTO)
				tile = tileCache.getTile(indexX, indexY, res, TileType.INSTITUTO);
			else
				tile = tileCache.getTile(indexX, indexY, res, TileType.TERRALIB);
			if (tile.imageIsLoaded() == TileStatus.LOADED) {
				tileImageBuffer = tile.getImage();
				int[] sub = getSubTile(indexX, indexY);
				canvasGraphicsBuffer.drawTileImage(tileImageBuffer, sub[4],
						sub[5], sub[6], sub[7], sub[0], sub[1], sub[2], sub[3]);
				if (CanvasService.isForegroundEnabled()) {
					plotForegroundTile(backgroundTiles.get(i), sub);
				}
				backgroundTiles.remove(i);
				i--;
				if (backgroundTiles.size() == 0
						&& CanvasService.hasBDImageSource()) {
					if (drawText) {
						CanvasService.drawText();
						drawText = false;
					}
				}
			} else {
				int[] sub = getSubTile(indexX, indexY);
				if (ToolRedirectionService.isPanTool() && !wheelMoved) {
					if (!CanvasService.isForegroundEnabled()) {
						canvasGraphicsBuffer.drawWhiteTile(sub[4], sub[5],
								sub[6] - sub[4], sub[7] - sub[5]);
					} else if (!isForegroundTileReady(tile)) {
						canvasGraphicsBuffer.drawWhiteTile(sub[4], sub[5],
								sub[6] - sub[4], sub[7] - sub[5]);
					}
					wheelMoved = false;
				}
				if (tile.imageIsLoaded() == TileStatus.NOT_LOADED) {
					tile.setImageStatus(TileStatus.LOADING);
					double[] arrayBox = getTile(indexX, indexY);
					Box box = new Box(arrayBox[0], arrayBox[1], arrayBox[2],
							arrayBox[3]);
					TileRequest tileRequest = new TileRequest(tile, background,
							box, state.getZoomLevel(), TILE_SIZE,
							CanvasService.isForegroundEnabled(), tileCache);
					tileRequest.start();

				}
			}
		}

		if (CanvasService.isForegroundEnabled()) {
			ImageSourceDefinition foregroundImageDefinition = state
					.getForeground();
			for (int i = 0; i < foregroundTiles.size(); i++) {
				int indexX = foregroundTiles.get(i).getIndexX();
				int indexY = foregroundTiles.get(i).getIndexY();
				double res = foregroundTiles.get(i).getResolution();
				tile = tileCache
						.getTile(indexX, indexY, res, TileType.TERRALIB);
				if (tile.imageIsLoaded() == TileStatus.LOADED) {
					tileImageBuffer = tile.getImage();
					int[] sub = getSubTile(indexX, indexY);
					BufferedImage transparecyImage = new BufferedImage(
							TILE_SIZE, TILE_SIZE, BufferedImage.TRANSLUCENT);
					Graphics2D g2 = (Graphics2D) transparecyImage.getGraphics();
					g2.setComposite(AlphaComposite.getInstance(
							AlphaComposite.SRC_OVER,
							state.getTransparencyFactor()));
					g2.drawImage(tileImageBuffer, 0, 0, null);
					canvasGraphicsBuffer.drawTileImage(transparecyImage,
							sub[4], sub[5], sub[6], sub[7], sub[0], sub[1],
							sub[2], sub[3]);
					Tile memoryTile = new Tile(null, indexX, indexY, res, null);
					memoryTile.setImage(transparecyImage);
					foregroundAux.add(memoryTile);
					foregroundTiles.remove(i);
					i--;
				} else {
					if (tile.imageIsLoaded() == TileStatus.NOT_LOADED) {
						tile.setImageStatus(TileStatus.LOADING);
						double[] arrayBox = getTile(indexX, indexY);
						Box box = new Box(arrayBox[0], arrayBox[1],
								arrayBox[2], arrayBox[3]);
						
						
						

						TileRequest tileRequest = new TileRequest(tile,
								foregroundImageDefinition, box,
								state.getZoomLevel(), TILE_SIZE, true,
								state.getTileCache());
						tileRequest.start();
					}
				}
			}
		}
		canvasGraphicsBuffer.repaintTilesBufferOnCanvas();
		canvasGraphicsBuffer.notifyObservers();
	}

	private static boolean isForegroundTileReady(Tile tile) {
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		List<Tile> foregroundAux = state.getForegroundAux();

		boolean contains = false;

		for (int i = 0; i < foregroundAux.size(); i++) {
			if (foregroundAux.get(i).equalsWithoutTileType(tile)) {
				contains = true;
			}
		}

		return contains;
	}

	public static boolean isDrawText() {
		return drawText;
	}
}
