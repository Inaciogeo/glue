package br.org.funcate.glue.view;

import java.awt.Color;
import java.awt.Dimension;

import br.org.funcate.glue.controller.listener.CanvasAdapter;
import javax.swing.JLabel;

/**
 * @brief Class that creates the system canvas.
 * @author Henrique and Ricardo
 * @version 2.0
 * @date 16/05/2011
 */
@SuppressWarnings("serial")
class CanvasView extends AbstractCanvas {

	private Color backgroundColor;

	public CanvasView() {
	}

	CanvasView(int width, int height) {
		Dimension d = new Dimension(width, height);
		this.setSize(d);

		this.backgroundColor = Color.white;

		this.setBackground(backgroundColor);
		this.addAdapter();
	}

	private void addAdapter() {
		CanvasAdapter canvasAdapter = new CanvasAdapter(this);

		this.addMouseListener(canvasAdapter);
		this.addMouseMotionListener(canvasAdapter);
		this.addMouseWheelListener(canvasAdapter);
		this.addKeyListener(canvasAdapter);

	}

}