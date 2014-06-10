package br.org.funcate.glue.controller;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.utilities.PropertiesReader;
import br.org.funcate.glue.view.AbstractCanvas;
import br.org.funcate.glue.view.MainPanel;

public class MainPanelController {

	private MainPanel mainPanel;
	private JLabel loadingStatusLabel;
	private JLabel terraLibText;

	public MainPanelController(MainPanel mainPanel, JLabel terraLibText, JLabel loadingStatusLabel) {
		this.mainPanel = mainPanel;
		this.loadingStatusLabel = loadingStatusLabel;
		this.terraLibText = terraLibText;
		
		Mediator mediator = AppSingleton.getInstance().getMediator();
		mediator.setMainPanelController(this);
		mediator.draw(true, true);
	}

	public Point getLocation() {
		return mainPanel.getLocation();
	}

	public int getWidth() {
		return mainPanel.getWidth();
	}

	public int getHeight() {
		return mainPanel.getHeight();
	}

	void resizeToApplet(int[] size) {
		this.loadingStatusLabel.setBounds(size[0] - 44, loadingStatusLabel.getY(),
				44, 44);
		this.terraLibText.setBounds(size[0] - 167, size[1] -38, 166, 19);
	}

	public void setImageLoadingStatus(ImageIcon img) {
		loadingStatusLabel.setIcon(img);
	}

	public void setCanvas(AbstractCanvas canvas) {
		int[] bounds = new int[4];

		bounds[0] = Integer.parseInt(PropertiesReader
				.getProperty("canvas.bound.x0"));
		bounds[1] = Integer.parseInt(PropertiesReader
				.getProperty("canvas.bound.y0"));
		bounds[2] = Integer.parseInt(PropertiesReader
				.getProperty("canvas.width"));
		bounds[3] = Integer.parseInt(PropertiesReader
				.getProperty("canvas.height"));

		canvas.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);

		this.mainPanel.remove(this.mainPanel.getCanvas());

		this.mainPanel.setCanvas(canvas);

		this.mainPanel.add(this.mainPanel.getCanvas());
	}
}
