package br.org.funcate.glue.main;

import java.util.Locale;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.model.ContextToGroupMap;
import br.org.funcate.glue.model.InfoClick;
import br.org.funcate.glue.model.canvas.CanvasState;
import br.org.funcate.glue.model.toolbar.ToolbarState;
import br.org.funcate.glue.model.tree.TreeState;
import br.org.funcate.glue.service.TerraJavaClient;
import br.org.funcate.glue.service.utils.NetworkService;

public class AppSingleton {

	private static AppSingleton instance;
	private TreeState treeState;
	private ToolbarState toolbarState;
	private Mediator mediator;
	private CanvasState canvasState;
	private TerraJavaClient services;
	private ContextToGroupMap contextToGroupMap;
	private Locale locale;
	private InfoClick infoClick;
	private NetworkService networkService;
	
	private AppSingleton() {
		services = new TerraJavaClient();		
		toolbarState = new ToolbarState();
		treeState = new TreeState();
		mediator = new Mediator();
		canvasState = new CanvasState();
		this.contextToGroupMap = new ContextToGroupMap();
		networkService = new NetworkService();
		locale = Locale.getDefault();
		this.infoClick = new InfoClick();
	}

	public static AppSingleton getInstance() {

		if (instance == null) {
			instance = new AppSingleton();
		}
		return instance;

	}

	public TerraJavaClient getServices() {
		return services;
	}

	public Mediator getMediator() {
		return mediator;
	}

	public ToolbarState getToolbarState() {
		return toolbarState;
	}

	public CanvasState getCanvasState() {
		return canvasState;
	}

	public TreeState getTreeState() {
		return treeState;
	}

	public ContextToGroupMap getGroupMapParameters() {
		return contextToGroupMap;
	}

	public void setGroupMapParameters(ContextToGroupMap groupMapParameters) {
		this.contextToGroupMap = groupMapParameters;
	}

	public Locale getLocale() {
		return locale;
	}

	public InfoClick getInfoClick() {
		return this.infoClick;
	}

	public NetworkService getNetworkService() {
		return networkService;
	}
	
	
}
