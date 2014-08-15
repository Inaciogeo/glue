package br.org.funcate.glue.model.request;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import org.jdom2.JDOMException;

import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.ESRILatLongTile;
import br.org.funcate.glue.model.canvas.CanvasService;
import br.org.funcate.glue.utilities.PropertiesReader;


/**
 * \class InstitutoCartograficoRequest This class provides an inflow to solicit tiles
 */
abstract class InstitutoCartograficoRequest {


	// ! This is called when a specific tile needs to be requested from ESRI
	// Server.
	/*
	 * \param url the ESRI image source that tiles will be requested from.
	 * \param zoomLevel the distance level that will be viewed on canvas \param
	 * tileIndexX horizontal tile index of the Canvas box in the world \param
	 * tileIndexY vertical tile index of the Canvas box in the world
	 */
	static BufferedImage getImage(int zoomLevel, int tileIndexX, int tileIndexY , Box box) throws JDOMException {
		String url = null;
		String finalUrl = null;

		try {
			String Y =  String.valueOf((int)Math.floor((ESRILatLongTile.originLocation.y/(ESRILatLongTile.getResolution()[zoomLevel]*CanvasService.TILE_SIZE))-tileIndexY));
			String X =  String.valueOf((int)Math.ceil((-ESRILatLongTile.originLocation.x/(ESRILatLongTile.getResolution()[zoomLevel]*CanvasService.TILE_SIZE))+tileIndexX));
			url = PropertiesReader.getProperty("canvas.request.cgi.url.Instituto");
			finalUrl = url + String.valueOf(zoomLevel)+"/"+Y+"/"+X;
			BufferedImage tileImg = ImageIO.read(new URL(finalUrl));
			return tileImg;
			
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
			g.setColor(Color.black);
			g.drawString("A imagem não pode", 14, 20);
			g.drawString("ser requisitada.", 3, 35);
			g.drawString("Verifique o nível de zoom,", 3, 50);
			g.drawString("ou a conexão com a internet.", 6, 65);
			return errorImage;
		} 
	}
}
