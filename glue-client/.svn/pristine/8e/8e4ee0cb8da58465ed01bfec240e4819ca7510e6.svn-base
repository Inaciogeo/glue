package br.org.funcate.glue.controller;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;


import br.org.funcate.eagles.kernel.dispatcher.EventDispatcher;
import br.org.funcate.eagles.kernel.dispatcher.EventHandler;
import br.org.funcate.eagles.kernel.listener.EventListener;
import br.org.funcate.eagles.kernel.listener.ListenersHandler;
import br.org.funcate.eagles.kernel.listener.ListenersHandlerImpl;
import br.org.funcate.eagles.kernel.transmitter.DirectedEventTransmitter;
import br.org.funcate.eagles.kernel.transmitter.EventTransmitter;
import br.org.funcate.glue.event.CleanThematicEvent;
import br.org.funcate.glue.event.DrawFeatureEvent;
import br.org.funcate.glue.event.GetThemeAttributesEvent;
import br.org.funcate.glue.event.GetViewsEvent;
import br.org.funcate.glue.event.TreeThemeChangeEvent;
import br.org.funcate.glue.event.SetLabelContextEvent;
import br.org.funcate.glue.event.SetThematicContextEvent;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.ContextToGroupMap;
import br.org.funcate.glue.model.ContextToLabelConfig;
import br.org.funcate.glue.model.Layer;
import br.org.funcate.glue.model.LoadingStatusService;
import br.org.funcate.glue.model.UserType;
import br.org.funcate.glue.model.canvas.CanvasService;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.model.request.TextRequest;
import br.org.funcate.glue.model.tree.CustomNode;
import br.org.funcate.glue.model.tree.TreeService;
import br.org.funcate.glue.view.SearchPanel;
import br.org.funcate.glue.view.TreeView;

public class TreeController implements EventDispatcher, EventListener {

	private TreeView treeView;
	private CustomNode lastTheme;
	private ListenersHandler listenersHandler;
	private EventHandler eventHandler;
	private EventTransmitter eventTransmitter;

	private List<String> eventsToListen;

	public TreeController(TreeView treeView) {
		this.treeView = treeView;
		AppSingleton.getInstance().getMediator().setTreeController(this);

		listenersHandler = new ListenersHandlerImpl();
		eventHandler = new EventHandler();
		eventTransmitter = new DirectedEventTransmitter(this);

		eventsToListen = new ArrayList<String>();
		eventsToListen.add(GetViewsEvent.class.getName());
		eventsToListen.add(GetThemeAttributesEvent.class.getName());
		eventsToListen.add(SetThematicContextEvent.class.getName());
		eventsToListen.add(CleanThematicEvent.class.getName());
		eventsToListen.add(SetLabelContextEvent.class.getName());
		eventsToListen.add(TreeThemeChangeEvent.class.getName());
		eventsToListen.add(DrawFeatureEvent.class.getName());
		
	}

	public List<String> getEventsToListen() {
		return this.eventsToListen;
	}

	CustomNode createRoot() {
		try {
			return TreeService.createRoot();
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			return null;
		}
	}

	public CustomNode getCurrentView() {
		return TreeService.getCurrentView();
	}

	public void setCurrentView(CustomNode view) {
		TreeService.setCurrentView(view);
	}

	public void setDefaultTreeModel(DefaultTreeModel defaultTreeModel) {
		TreeService.setDefaultTreeModel(defaultTreeModel);
	}

	public DefaultTreeModel getDefaultTreeModel() {
		return TreeService.getDefaultTreeModel();
	}

	public void renameNode() {
		TreeService.renameNode();
		
	}

	public void deleteNode() {
		TreeService.removeNode();
	}

	public CustomNode getCurrentTheme() {
		return TreeService.getCurrentTheme();
	}

	public void setCurrentTheme(CustomNode theme) {
		TreeService.setCurrentTheme(theme);
		boolean change = TreeService.checkThemeChange(theme.getName());
		TreeThemeChangeEvent e = new TreeThemeChangeEvent(this);
		e.setChange(change);
		e.setOldTheme(getLastTheme());
		dispatch(e);
	}
	
	
	public CustomNode getLastTheme() {
		return lastTheme;
	}

	public void setLastTheme(CustomNode lastTheme) {
		this.lastTheme = lastTheme;
	}

	public void createViewUpdator(Boolean reload, Boolean memory, Boolean remove) {
		TreeService.createViewUpdator(reload, memory, remove);

	}

	public boolean checkAllNodes(CustomNode root, CustomNode compare) {
		return TreeService.checkAllNodes(root, compare);
	}

	public void setUserType(UserType userType) {
		TreeService.setUserType(userType);
	}

	public UserType getUserType() {
		return TreeService.getUserType();
	}

	public void setSelectedNode(CustomNode selectedNode) {
		TreeService.setSelectedNode(selectedNode);
	}

	public CustomNode getSelectedNode() {
		return TreeService.getSelectedNode();

	}

	CustomNode createToolBar() {
		return TreeService.createToolbar();
	}

	public void copyNode() {
		TreeService.copyNode();
	}

	public void cutNode() {
		TreeService.cutNode();
	}

	public void pasteNode() {
		TreeService.pasteNode();
	}

	public CustomNode getNodeView(CustomNode parent) {
		return TreeService.getNodeView(parent);
	}

	public void rearrange(int index, CustomNode root, CustomNode nodeSource) {
		TreeService.rearrange(index, root, nodeSource);
	}

	public void moveNode(CustomNode targetView, CustomNode sourceView) {
		TreeService.moveNode(targetView, sourceView);
	}

	public CustomNode getRoot() {
		return TreeService.getRoot();
	}

	public void setVisible(boolean visible) {
		treeView.setVisible(visible);
	}

	void applyView() {
		TreeService.applyView();
	}

	public List<CustomNode> getSelectedThemes() {
		return TreeService.getSelectedThemes();
	}

	public void setSelectionPath(TreePath treePath) {
		treeView.getTree().setSelectionPath(treePath);
	}

	void repaint() {
		treeView.repaint();
	}

	public CustomNode getLastSelectedPathComponent() {
		return (CustomNode) treeView.getTree().getLastSelectedPathComponent();
	}

	public TreePath getSelectionPath() {
		return treeView.getTree().getSelectionPath();
	}

	void expandPath(TreePath path) {
		treeView.getTree().expandPath(path);
	}

	void scrollPathToVisible(TreePath path) {
		treeView.getTree().scrollPathToVisible(path);
	}

	void disableTree() {
		LoadingStatusService.addThreadCount();
		treeView.setVisible(false);
	}

	void enableTree() {
		treeView.setVisible(true);
		LoadingStatusService.removeThreadCount();
	}

	void collapsePath(TreePath path) {
		treeView.getTree().collapsePath(path);
	}

	void addNode(CustomNode node, CustomNode parent) {
		TreeService.addNode(node, parent);
	}

	public List<Layer> getLayers() {
		return TreeService.getLayers();
	}

	void removeNodeFromParent(CustomNode node) {
		TreeService.removeNodeFromParent(node);
	}

	Boolean updateSelectedView(Boolean isReload, Boolean isViewMem) {
		try {
			return TreeService.updateSelectedView(isReload, isViewMem);
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			return false;
		}
	}

	void reload() {
		TreeService.reload();
	}

	public void setNodeSource(CustomNode nodeSource) {
		TreeService.setNodeSource(nodeSource);
	}

	@Override
	public void handle(EventObject e) {
		if (e instanceof GetViewsEvent) {
			this.handle((GetViewsEvent) e);
		} else if (e instanceof GetThemeAttributesEvent) {
			this.handle((GetThemeAttributesEvent) e);
		} else if (e instanceof SetThematicContextEvent) {
			this.handle((SetThematicContextEvent) e);
		} else if (e instanceof CleanThematicEvent) {
			this.handle((CleanThematicEvent) e);
		} else if (e instanceof SetLabelContextEvent) {
			this.handle((SetLabelContextEvent) e);
		} else if (e instanceof TreeThemeChangeEvent){
			this.handle((TreeThemeChangeEvent) e);
		} else if (e instanceof DrawFeatureEvent){
			this.handle((DrawFeatureEvent)e);
		}
	}
	private void handle(DrawFeatureEvent e){
		
	}
	
	private void handle(SetLabelContextEvent e){
		ContextToLabelConfig contextToLabelConfig = e.getContext();
		TextRequest.setContextToLabelConfig(contextToLabelConfig);
		new TextRequest().start();		
	}
	
	
	private void handle(TreeThemeChangeEvent e) {
	}
	

	private void handle(CleanThematicEvent e) {
		this.cleanGroupThemes();
		try {
			CanvasService.draw(true, false);
		} catch (GlueServerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void handle(GetViewsEvent e) {
		e.setViews(TreeService.getViews());
	}

	private void handle(GetThemeAttributesEvent e) {
		try {
			e.setAttributes(TreeService.getAttributes(e.getViewName(),
					e.getThemeName()));
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}
	
	private void handle(SetThematicContextEvent e) {
		AppSingleton singleton = AppSingleton.getInstance();
		ContextToGroupMap contextToGroupMap = e.getParameters();
		singleton.setGroupMapParameters(contextToGroupMap);
		this.setThematicTheme(contextToGroupMap.getView(),
				contextToGroupMap.getTheme());
		try {
			CanvasService.draw(true, false);
		} catch (GlueServerException exception) {
			exception.printStackTrace();
			// TODO AGUARDANDO IMPLEMENTAÇÃO DO EXCEPTION HANDLER

		}
	}

	@Override
	public ListenersHandler getListenersHandler() {
		return listenersHandler;
	}

	@Override
	public EventHandler getEventHandler() {
		return eventHandler;
	}

	@Override
	public void dispatch(EventTransmitter tc, EventObject e) throws Exception {
		tc.dispatch(e);
	}

	private void setThematicTheme(String viewName, String themeName) {
		TreeService.setThematicTheme(viewName, themeName);
	}

	private void cleanGroupThemes() {
		TreeService.cleanGroupThemes();
	}

	public void dispatch(EventObject e) {
		try {
			dispatch(this.eventTransmitter, e);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
