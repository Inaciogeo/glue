package br.org.funcate.plugin;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JComponent;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.CalculatorService;
import br.org.funcate.glue.service.TerraJavaClient;
import br.org.funcate.glue.tool.Tool;
import br.org.funcate.glue.view.AbstractCanvas;

public abstract class GluePluginService {

	public static void insertPluginToolBar(String title, Component toolBar, String tip) {
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		mediator.insertPluginToolBar(title, toolBar, tip);
	}

	public static void setCurrentTool(Tool tool) {
		AppSingleton.getInstance().getMediator().setCurrentTool(tool);
	}

	public static void addSideBar(String name, Icon icon, JComponent component) {
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		mediator.addSideBar(name, icon, component);
	}

	public static void removeSideBar(String name) {
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		mediator.removeSideBar(name);
	}
	
	public static void setLastBarVisible() {
		AppSingleton.getInstance().getMediator().setLastBarVisible();
	}

	public static TerraJavaClient getServices() {
		TerraJavaClient services = AppSingleton.getInstance().getServices();
		return services;
	}

	public static void setCanvas(AbstractCanvas canvas) {
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediador = singleton.getMediator();
		mediador.setCanvas(canvas);
	}
	
	public static double[] convertFromWorldToPixel(double x1, double y1) {
		return CalculatorService.convertFromWorldToPixel(x1, y1);
	}
	
}
