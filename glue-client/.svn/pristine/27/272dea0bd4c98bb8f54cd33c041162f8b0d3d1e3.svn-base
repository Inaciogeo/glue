package br.org.funcate.glue.event;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import br.org.funcate.glue.model.canvas.BufferEnum;
import br.org.funcate.glue.view.AbstractCanvas;

/**
 * This event can be used to draw specified layers by layer's name. The order of
 * LayerNames list is respected.
 * 
 * @author Moraes, Emerson Leite
 * 
 */
public class DrawLayersEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Layers to draw names.
	 */
	private List<String> layerNames;

	/**
	 * BufferId to draw layers.
	 */
	private BufferEnum bufferId;

	/**
	 * Absctract canvas to draw layers.
	 */
	private AbstractCanvas canvas;

	/**
	 * Constructor
	 * 
	 * @param source
	 */
	public DrawLayersEvent(Object source, BufferEnum bufferId) {
		super(source);
		this.layerNames = new ArrayList<String>();
		this.setBufferId(bufferId);
	}

	/**
	 * Use the to add a layer name to be draw.
	 * 
	 * @return layerNames
	 */
	public List<String> getLayerNames() {
		return this.layerNames;
	}

	/**
	 * @param bufferId
	 *            the bufferId to set
	 */
	public void setBufferId(BufferEnum bufferId) {
		this.bufferId = bufferId;
	}

	/**
	 * @return the bufferId
	 */
	public BufferEnum getBufferId() {
		return bufferId;
	}

	/**
	 * @param canvas
	 *            the canvas to set
	 */
	public void setCanvas(AbstractCanvas canvas) {
		this.canvas = canvas;
	}

	/**
	 * @return the canvas
	 */
	public AbstractCanvas getCanvas() {
		return canvas;
	}
}
