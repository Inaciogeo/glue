package br.org.funcate.glue.model.request;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.LoadingStatusService;
import br.org.funcate.glue.model.View;
import br.org.funcate.glue.model.cache.Tile;
import br.org.funcate.glue.model.cache.TileCache;
import br.org.funcate.glue.model.cache.TileType;
import br.org.funcate.glue.model.canvas.CanvasService;
import br.org.funcate.glue.model.canvas.GeneralTileSchema;
import br.org.funcate.glue.model.canvas.ImageSourceDefinition;
/**
 * @author inacio
 *
 */
public class TileRequest extends Thread {
	private Tile tile;
	private TileType tileType;
	private BufferedImage threadTileImage;
	private java.lang.Object source;
	private Box box;
	private int zoomLevel;
	private int tileSize;
	private TileCache tileCache;
	private boolean isForegroundTile;

	public TileRequest(Tile tileDef, ImageSourceDefinition theme, Box box,
			int zoomLevel, int tileSize, boolean isForegroundTile,
			TileCache tileCache) {
		this.tile = tileDef;
		this.tileType = theme.getType();
		this.box = box;
		this.zoomLevel = zoomLevel;
		this.tileSize = tileSize;
		this.isForegroundTile = isForegroundTile;
		this.source = theme.getSource();
		this.tileCache = tileCache;
	}

	public void run() {
		LoadingStatusService.addThreadCount();
		switch (tileType) {
		case GOOGLE:
			try {
				threadTileImage = GoogleRequest.getImage(GoogleEnum.MAP ,zoomLevel,tile.getIndexX(),tile.getIndexY(), box);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case OPENSTREET:
			try {
				threadTileImage = OpenStreetMapRequest.getImage(zoomLevel, tile.getIndexX(), tile.getIndexY(), box);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case CGI:
			try {
				threadTileImage = CGIRequest.getImage(zoomLevel, tile.getIndexX(), tile.getIndexY(), box);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case INSTITUTO:
			try {
				threadTileImage = InstitutoCartograficoRequest.getImage(zoomLevel,tile.getIndexX(), tile.getIndexY(),box);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case WMS:
			try {//bira
				if(source instanceof String){
					threadTileImage = ImageIO.read(new URL((String) source
						+ box.getX1() + "," + box.getY1() + "," + box.getX2()
						+ "," + box.getY2() + "&WIDTH=" + tileSize + "&HEIGHT="
						+ tileSize));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case TERRALIB:
			try {
				byte[] imgByteArray = BDRequest.plotView((View) source, box);

				if (imgByteArray == null) {
					threadTileImage = new BufferedImage(tileSize, tileSize,BufferedImage.BITMASK);
					Graphics graph = threadTileImage.getGraphics();
					graph.fillRect(-10, -10, tileSize + 10, tileSize + 10);
					break;
				}

				if (isForegroundTile) {
					ImageIcon image = new ImageIcon(imgByteArray);
					threadTileImage = new BufferedImage(tileSize, tileSize,
							BufferedImage.BITMASK);
					Graphics graph = threadTileImage.getGraphics();
					graph.drawImage(image.getImage(), 0, 0, null);
				} else {
					InputStream image = new ByteArrayInputStream(imgByteArray);
					threadTileImage = javax.imageio.ImageIO.read(image);
				}
			} catch (Exception e) {
				BufferedImage errorImage = new BufferedImage(256, 256, 1);
				Graphics errorTile = errorImage.getGraphics();
				errorTile.setColor(Color.WHITE);
				errorTile.fillRect(0, 0, 256, 256);
				errorTile.setColor(Color.black);
				errorTile.drawString("TerraLib:", 3, 20);
				errorTile.drawString("Erro ao requisitar imagem.", 3, 50);
				errorTile.drawString("Verifique a conex�o com o banco", 3, 65);
				errorTile.drawString("ou tente novamente mais tarde.", 3, 80);
				threadTileImage = errorImage;
				e.printStackTrace();
			}
			break;
		}
		
		if(threadTileImage != null){
			Tile newTileDefinition = new Tile(threadTileImage, tile.getIndexX(), tile.getIndexY(), tile.getResolution(), tileType);
			tileCache.insertCandidate(newTileDefinition);
			
			if (!CanvasService.isEmptyBox()) {
				GeneralTileSchema.plotTiles();
			}
			LoadingStatusService.removeThreadCount();
		}
	}
}
