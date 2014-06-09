package br.org.funcate.glue.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.canvas.BufferEnum;
import br.org.funcate.glue.model.canvas.CanvasGraphicsBuffer;
import br.org.funcate.glue.model.canvas.CanvasState;
import br.org.funcate.glue.model.canvas.GraphicsPainter;
import br.org.funcate.glue.utilities.patterns.CanvasObserver;

/**
 * This abstract class defines an interface for objects that can draw.
 * 
 * The canvas is an output object. The result of drawing operations will be
 * showed in a canvas draw object. The canvas also have methods to set paint
 * mode (e.g XOR mode), add draw buffers, clipping, mapping from / to viewport
 * coordinates system from / to world coordinates system.
 * 
 * @author Moraes, Emerson Leite
 * 
 */
@SuppressWarnings("serial")
public abstract class AbstractCanvas extends JComponent implements CanvasObserver {

	/**
	 * This is the Id of an {@link AbstractCanvas}.
	 */
	private int id;

	/**
	 * Contains all buffers that will be painted on canvas.
	 */
	protected GraphicsPainter canvasGraphicsBuffer;

	/**
	 * @param id
	 *            to set an {@link AbstractCanvas}.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the id of an {@link AbstractCanvas}.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns canvas dimensions.
	 * 
	 * @return {@link Dimension} canvas dimensions.
	 */
	public Dimension getDimension() {
		Dimension dimension = new Dimension(this.getWidth(), this.getHeight());
		return dimension;
	}

	/**
	 * Returns the width dimension in Widget coordinate system.
	 * 
	 * @return Canvas width in widget coordinate system.
	 */
	// public abstract int getWidth();

	/**
	 * Returns the height dimension in Widget coordinate system.
	 * 
	 * @return Canvas height in widget coordinate system.
	 */
	// public abstract int getHeight();

	/**
	 * Sets Canvas dimensions
	 * 
	 * @param d
	 *            dimensions (width / height) in Widget coordinate system.
	 */
	// public abstract void setDimension (Dimension d);

	/**
	 * Updates the canvas, giving a new group of graphical elements to be
	 * painted, and an order to repaint the screen.
	 * 
	 * @param graphicsPainter
	 *            A new object that contains a group of graphical elements to be
	 *            painted on canvas.
	 */
	public void update(GraphicsPainter graphicsPainter) {
		this.canvasGraphicsBuffer = graphicsPainter;
		this.repaint();
	}

	/**
	 * Updates screen always when new events happens in Canvas (when repaint()
	 * is called), so all tools and tiles are repainted at this moment.
	 */
	public void update(Graphics g) {
		paint(g);
	}

	/**
	 * Paints the canvasGraphicsBuffer on canvas.
	 */
	public void paint(Graphics g) {
		if (canvasGraphicsBuffer == null) {
			return;
		}

		canvasGraphicsBuffer.paint(g);
	}

	/**
	 * This method return the BufferedImage represented by {@link BufferEnum}
	 * 
	 * @param bufferId
	 * @return BufferedImage
	 */
	public BufferedImage getBuffer(BufferEnum bufferId) { // NO_UCD (unused
															// code)
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		canvasGraphicsBuffer = state.getCanvasGraphicsBuffer();
		return ((CanvasGraphicsBuffer) canvasGraphicsBuffer)
				.getBuffer(bufferId);
	}

	public CanvasGraphicsBuffer getBufferManager() {
		CanvasState state = AppSingleton.getInstance().getCanvasState();

		canvasGraphicsBuffer = state.getCanvasGraphicsBuffer();

		return (CanvasGraphicsBuffer) canvasGraphicsBuffer;
	}
}
