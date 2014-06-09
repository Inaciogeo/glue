package br.org.funcate.glue.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 
 * @author Aleksander, Willyan
 * @author Asakura, Henrique
 * 
 *         \brief This class creates one Canvas for preview a WMS image.
 * @version 1.1.0
 */
@SuppressWarnings("serial")
public class CanvasPreview extends java.awt.Canvas {

	/*******************************************
	 ********* CanvasPreview settings **********
	 *******************************************/
	private static final int _canvasSize = 120;
	/** < The canvasPreview size */
	private double _boxX1;
	/** < Attribute type double x1 of box */
	private double _boxY1;
	/** < Attribute type double y1 of box */
	private double _boxX2;
	/** < Attribute type double x2 of box */
	private double _boxY2;
	/** < Attribute type double y2 of box */
	private double _resolution;
	/** < Attribute type double resolution */
	private boolean _enabled;
	/** < Attribute type boolean */
	private BufferedImage _buffer;
	/** < Attribute type Buffered Image */
	private Graphics _bufferGraphics;

	/** < Attribute type Graphics */

	/*******************************************/

	/** Constructor */
	public CanvasPreview() {
		_enabled = false;
		_buffer = new BufferedImage(120, 120, 1);
		_bufferGraphics = _buffer.getGraphics();
	}

	/**
	 * This method creates a preview of how the WMS will plot
	 * 
	 * @param url
	 * @param xMin
	 * @param yMin
	 * @param xMax
	 * @param yMax
	 */
	public void preview(String url, double xMin, double yMin, double xMax, double yMax) {
		resizeBox(xMin, yMin, xMax, yMax);
		BufferedImage previewImage;
		try {
			previewImage = ImageIO.read(new URL(url + _boxX1 + "," + _boxY1 + "," + _boxX2 + "," + _boxY2 + "&WIDTH=120&HEIGHT=120"));
			if (previewImage == null) {
				LocalOptionPane.getInstance("Algum dos temas não pode ser retonado pelo servidor ou o formato de imagem não suportado!",
						"Glue - Temas/Imagens");
			}
		} catch (IOException ex) {
			LocalOptionPane.getInstance("Este link parece estar corrompido!", "Glue - Dados Corrompidos");
			return;
		}
		_bufferGraphics.drawImage(previewImage, 0, 0, this);
		_enabled = true;
		repaint();
	}

	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 120, 120);
		if (_enabled) {
			g.drawImage(_buffer, 0, 0, this);
		}
	}

	/**
	 * This method adapts the image that comes from the WMS server to the size
	 * of the Canvas.
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	private void resizeBox(double x1, double y1, double x2, double y2) {
		double largura = x2 - x1;
		double altura = y2 - y1;
		double resX = largura / _canvasSize;
		double resY = altura / _canvasSize;
		if (resX > resY) {
			this._resolution = resX;
			this._boxX1 = x1;
			this._boxX2 = x2;
			this._boxY1 = y1 + altura / 2 - _canvasSize / 2 * _resolution;
			this._boxY2 = this._boxY1 + _canvasSize * _resolution;
		} else {
			this._resolution = resY;
			this._boxY1 = y1;
			this._boxY2 = y2;
			this._boxX1 = x1 + largura / 2 - _canvasSize / 2 * _resolution;
			this._boxX2 = this._boxX1 + _canvasSize * _resolution;
		}
	}
}
