package br.org.funcate.glue.controller;

import java.awt.Component;

import javax.swing.JTabbedPane;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.utilities.PropertiesReader;

public class TabbedToolBarsController {

	private JTabbedPane tabbedToolBars;

	public TabbedToolBarsController(JTabbedPane tabbedToolBars) {
		this.tabbedToolBars = tabbedToolBars;
		Mediator mediator = AppSingleton.getInstance().getMediator();
		mediator.setTabbedToolBarsController(this);
	}

	void resizeTabbedToolBarsToApplet(int[] size) {
		String canvasXPosition = PropertiesReader.getProperty("canvas.bound.x0");
		int width = size[0] - Integer.parseInt(canvasXPosition) - 44;

		tabbedToolBars.setSize(width, tabbedToolBars.getHeight());
	}

	void insertPluginToolBar(String title, Component toolBar, String tip) {
		tabbedToolBars.addTab(title, null, toolBar, tip);
	}
}
