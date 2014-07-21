package br.org.funcate.glue.model.request;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.jdom2.JDOMException;

import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.utilities.PropertiesReader;
import br.org.funcate.glue.view.ScreenRequetServices;

public class CGIRequest {

	// ! This is called when a specific tile needs to be requested from Google
	// Server.
	/*
	 * \param url the google image source that tiles will be requested from.
	 * \param zoomLevel the distance level that will be viewed on canvas \param
	 * tileIndexX horizontal tile index of the Canvas box in the world \param
	 * tileIndexY vertical tile index of the Canvas box in the world
	 */
	static BufferedImage getImage(int zoomLevel, int tileIndexX, int tileIndexY, Box box) throws JDOMException {
		String url = null;
		String finalUrl = null;
		String CGIName = ScreenRequetServices.getValue();
		String positionUrl = null;
		try {
			
			url = PropertiesReader.getProperty("canvas.request.cgi.url."+CGIName);
			finalUrl = url + zoomLevel +"/"+  (int) (Math.pow(2, zoomLevel) / 2 + tileIndexX)+ "/"+ ((int) (Math.pow(2, zoomLevel) / 2 - tileIndexY) - 1)+".png";
			positionUrl = "z:"+ zoomLevel +" x:"+  (int) (Math.pow(2, zoomLevel) / 2 + tileIndexX)+ " y:"+ ((int) (Math.pow(2, zoomLevel) / 2 - tileIndexY) - 1);
			return ImageIO.read(new URL(finalUrl));
			
		} catch (IOException e) {
			BufferedImage errorImage = new BufferedImage(256, 256, 1);
			Graphics g = errorImage.getGraphics();

			if (box.getX2() < -1.9969020765456446E7
					|| box.getY1() > 1.981247773152832E7
					|| box.getX1() > 1.9646150757979687E7
					|| box.getY2() < -1.993966894659492E7) {
				g.setColor(Color.lightGray);
				g.fillRect(0, 0, 256, 256);
				g.setColor(Color.black);
				g.drawString("Fora dos Limites", 20, 100);
				return errorImage;
			}

			System.out.println("Deu erro na URL:");
			System.out.println(finalUrl);

			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 256, 256);
			g.setColor(Color.DARK_GRAY);
			g.setFont(new Font("Arial", Font.BOLD, 12));
			g.drawString("A imagem não pode", 6, 20);
			g.drawString("ser requisitada.", 6, 35);
			g.drawString("Verifique o nível de zoom,", 6, 50);
			g.drawString("ou a conexão com a internet.", 6, 65);
			g.drawString("tile: "+positionUrl, 6, 85);
			return errorImage;
		} 
	}
	public static double y2lat(double aY) {
	    return Math.toDegrees(2* Math.atan(Math.exp(Math.toRadians(aY))) - Math.PI/2);
	}
	public static double[] toGeographic(double mercatorX_lon, double mercatorY_lat){
		double[] coord = {0,0};
	    if (Math.abs(mercatorX_lon) < 180 && Math.abs(mercatorY_lat) < 90)
	        return coord;

	    if ((Math.abs(mercatorX_lon) > 20037508.3427892) || (Math.abs(mercatorY_lat) > 20037508.3427892))
	        return coord;

	    double x = mercatorX_lon;
	    double y = mercatorY_lat;
	    double num3 = x / 6378137.0;
	    double num4 = num3 * 57.295779513082323;
	    double num5 = Math.floor((double)((num4 + 180.0) / 360.0));
	    double num6 = num4 - (num5 * 360.0);
	    double num7 = 1.5707963267948966 - (2.0 * Math.atan(Math.exp((-1.0 * y) / 6378137.0)));
	    mercatorX_lon = num6;
	    mercatorY_lat = num7 * 57.295779513082323;
	    
	    coord[0]= mercatorX_lon;
	    coord[1]= mercatorY_lat;
		return coord;
	}

	// this method makes tile request using Google Static Maps API
	// do not remove it
	// ----------------
//	public static synchronized BufferedImage getImage(GoogleEnum googleType,
//			int zoomLevel, int tileIndexX, int tileIndexY, Box box) {
//		String finalUrl = null;
//		MapTypeEnum mapType = null;
//
//		try {
//			switch (googleType) {
//			case MAP:
//				mapType = MapTypeEnum.ROADMAP;
//				break;
//			case SATELLITE:
//				mapType = MapTypeEnum.SATELLITE;
//				break;
//			case TERRAIN:
//				mapType = MapTypeEnum.TERRAIN;
//				break;
//			case HYBRID:
//				mapType = MapTypeEnum.HYBRID;
//			}
//			tileIndexX = (int) (Math.pow(2, zoomLevel) / 2 + tileIndexX);
//			tileIndexY = (int) ((Math.pow(2, zoomLevel) / 2 - tileIndexY) - 1);
//			double[] tileLatLongCenter = GlobalMapTiles.getTileLatLongCenter(
//					tileIndexX, tileIndexY, zoomLevel);
//			finalUrl = "http://maps.google.com/maps/api/staticmap?center="
//					+ tileLatLongCenter[0] + "," + tileLatLongCenter[1]
//					+ "&zoom=" + zoomLevel + "&size=" + TILE_SIZE + "x"
//					+ (TILE_SIZE + 49) + "&maptype=" + mapType
//					+ "&sensor=false";
//			BufferedImage originalImage = ImageIO.read(new URL(finalUrl));
//			BufferedImage tileImage = new BufferedImage(TILE_SIZE, TILE_SIZE,
//					BufferedImage.TYPE_INT_RGB);
//			Graphics tileGraphics = tileImage.getGraphics();
//			tileGraphics.drawImage(originalImage, 0, -24, null);
//			System.out.println("Total de tiles requisitados = " + ++totalRequisitions);
//			return tileImage;
//
//		} catch (IOException e) {
//			e.printStackTrace();
//			BufferedImage errorImage = new BufferedImage(256, 256, 1);
//			Graphics g = errorImage.getGraphics();
//
//			if (box.getX2() < -1.9969020765456446E7
//					|| box.getY1() > 1.981247773152832E7
//					|| box.getX1() > 1.9646150757979687E7
//					|| box.getY2() < -1.993966894659492E7) {
//				g.setColor(Color.lightGray);
//				g.fillRect(0, 0, 256, 256);
//				g.setColor(Color.black);
//				g.drawString("Fora dos Limites", 20, 100);
//				return errorImage;
//			}
//
//			System.out.println("Deu erro na URL:");
//			System.out.println(finalUrl);
//
//			g.setColor(Color.lightGray);
//			g.fillRect(0, 0, 256, 256);
//			g.setColor(Color.black);
//			g.drawString("A imagem não pode", 6, 10);
//			g.drawString("ser requisitada.", 3, 25);
//			g.drawString("Verifique o nível de zoom,", 6, 40);
//			g.drawString("ou a conexão com a internet.", 3, 55);
//			g.drawString("Talvez tenha excedido o número", 6, 70);
//			g.drawString("de solicitações ao Google Maps", 3, 85);
//			return errorImage;
//		}
//	}
//
//	private static final int TILE_SIZE = PropertiesReader
//			.getIntegerProperty("tile.size");
//
//	private static int totalRequisitions;
}
