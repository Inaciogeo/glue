package br.org.funcate.glue.controller;

import java.awt.Component;
import java.awt.Point;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import br.org.funcate.eagles.kernel.listener.ListenersHandler;
import br.org.funcate.glue.controller.listener.ResizeAdapter;
import br.org.funcate.glue.event.AfterToolChangedEvent;
import br.org.funcate.glue.event.BeforeToolChangedEvent;
import br.org.funcate.glue.model.Layer;
import br.org.funcate.glue.model.ScaleService;
import br.org.funcate.glue.model.UserType;
import br.org.funcate.glue.model.canvas.ThemeAttributesInfoTool;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.model.toolbar.ToolEnum;
import br.org.funcate.glue.model.tree.CustomNode;
import br.org.funcate.glue.tool.Tool;
import br.org.funcate.glue.view.AbstractCanvas;
import br.org.funcate.glue.view.InfoToolView;
import br.org.funcate.glue.view.JOutlookBar;

/**
 * This class is the Mediator of GLUE application.
 * 
 * @author Moraes, Emerson Leite
 * 
 */
public class Mediator {

	/**
	 * Controller of Toolbar component.
	 */
	private ToolbarController toolbarController;

	/**
	 * Controller of Tree component.
	 */
	private TreeController treeController;

	/**
	 * Controller of Canvas component.
	 */
	private CanvasController canvasController;

	/*	*//**
	 * Controller for geometries Edition area.
	 */
	/*
	 * private EditionController editionController;
	 */

	@SuppressWarnings("unused")
	private PanelAddViewThemeAdapterController panelAddViewThemeController;

	private TransparencySliderController transparencySliderController;

	private ScaleController scaleController;

	private MainPanelController mainPanelController;

	private LabelCanvasExpanderController labelCanvasExpanderController;

	private ResizeAdapter resizeController;

	private OutlookBarController outlookBarController;

	private TabbedToolBarsController tabbedToolBarsController;

	private Tool tool;

	private Tool lastTool;

	/**
	 * Mediator's Contructor
	 */
	public Mediator() {
	}

	/**
	 * @return the treeController
	 */
	public TreeController getTreeController() {
		return treeController;
	}

	/**
	 * Sets toolbar's controller.
	 * 
	 * @param toolbarController
	 */
	public void setToolbarController(ToolbarController toolbarController) {
		this.toolbarController = toolbarController;
	}

	public void setLabelCanvasExpanderController(
			LabelCanvasExpanderController labelCanvasExpanderController) {
		this.labelCanvasExpanderController = labelCanvasExpanderController;
	}

	public void setMainPanelController(MainPanelController mainPanelController) {
		this.mainPanelController = mainPanelController;
	}

	public void setResizeController(ResizeAdapter resizeController) {
		this.resizeController = resizeController;
	}

	/**
	 * @param outlookBarController
	 *            the outlookBarController to set
	 */
	public void setOutlookBarController(
			OutlookBarController outlookBarController) {
		this.outlookBarController = outlookBarController;
	}

	/**
	 * @param tabbedToolBarsController
	 *            the tabbedToolBarsController to set
	 */
	public void setTabbedToolBarsController(
			TabbedToolBarsController tabbedToolBarsController) {
		this.tabbedToolBarsController = tabbedToolBarsController;
	}

	/**
	 * Sets tree's controller.
	 * 
	 * @param treeController
	 */
	public void setTreeController(TreeController treeController) {
		this.treeController = treeController;
	}

	/**
	 * Sets canvas's controller.
	 * 
	 * @param canvasController
	 */
	public void setCanvasController(CanvasController canvasController) {
		this.canvasController = canvasController;
	}

	public void setPanelAddViewThemeController(
			PanelAddViewThemeAdapterController panelAddViewThemeController) {
		this.panelAddViewThemeController = panelAddViewThemeController;
	}

	/**
	 * Sets Current tool using the Enumeration ToolEnum.
	 * 
	 * @param tool
	 */
	public void setSelectedTool(ToolEnum tool) {

		if (tool == null) {
			toolbarController.setDefaultCursor();
			return;
		}

		switch (tool) {

		case TERRALIB:
			toolbarController.setTerraLib();
			break;

		case GOOGLE:
			toolbarController.setTile();
			break;
		case WMS:
	//		toolbarController.setWMS();
			break;

		case PAINT:
			toolbarController.setPaint();
			break;

		case REBUILD:
			toolbarController.setRebuild();
			break;

		case ZOOMIN:
			toolbarController.setZoomIn();
			break;

		case ZOOMOUT:
			toolbarController.setZoomOut();
			break;

		case ZOOMAREA:
			toolbarController.setZoomArea();
			break;

		case PAN:
			toolbarController.setPan();
			break;

		case DISTANCE:
			toolbarController.setDistance();
			break;

		case UNDO:
			toolbarController.setUnDo();
			break;

		case REDO:
			toolbarController.setReDo();
			break;

		case PHOTOLOCATION:
			toolbarController.setPhotoLocation();
			break;

		case INFO:
			toolbarController.setInfo();
			break;

		case LINKS:
			toolbarController.setLinks();
			break;

		case ATRIBS:
			toolbarController.setAtribs();
			break;

		case CLEAN:
			toolbarController.setClean();
			break;

		case PDF:
			toolbarController.setPdf();
			break;

		case EXPORT:
			toolbarController.setExport();
			break;

		case HELPONLINE:
			toolbarController.setHelpOnline();
			break;
		}
	}

	public void setToolBarSource(Object source) {
		canvasController.setToolBarSource(source);
	}

	/**
	 * This method calls the ToolbarController to enable/disable one tool of
	 * toolbar (true to enable and false to disable).
	 * 
	 * @param tool
	 * @param enabled
	 */
	public void setToolEnabled(ToolEnum tool, boolean enabled) {
		toolbarController.setToolEnabled(tool, enabled);
	}

	/**
	 * This method enables all toolbar's tools (the tools TerraLib, WMS, Google
	 * and Paint don't enter in this business logic).
	 */
	public void enableAllTools() {
		toolbarController.setAllToolsEnabled(true);
	}

	/**
	 * This method disable all toolbar's tools (the tools TerraLib, WMS, Google
	 * and Paint don't enter in this business logic).
	 */
	public void disableAllTools() {
		toolbarController.setAllToolsEnabled(false);
	}

	/**
	 * This method return if terraLib is selected or not.
	 * 
	 * @return boolean
	 */
	public boolean isTerraLibSelected() {
		return toolbarController.isTerraLibSelected();
	}

	/**
	 * This method return selected tool.
	 * 
	 * @throws NullPointerException
	 * @return tool
	 */
	public ToolEnum getSelectedTool() {
		return toolbarController.getSelectedTool();
	}

	/**
	 * This method apply the current view and draws it.
	 */

	public void reDrawView() {
		this.toolbarController.reDrawView();
	}

	void expandedCanvas(boolean expanded) {
		outlookBarController.setVisible(!expanded);
		scaleController.move(expanded);
		canvasController.expandCanvas(expanded);
		resizeController.updateViewSizeToApplet();
	}

	public void setTransparencySliderController(
			TransparencySliderController transparencySliderController) {
		this.transparencySliderController = transparencySliderController;
	}

	public void exportImage() {
		canvasController.exportImage();
	}

	public void draw(boolean clearCache, boolean enablePanTool) {
		canvasController.draw(clearCache, enablePanTool);
	}

	public void resetLayerBoxPosition() {
		canvasController.resetLayerBoxPosition();
	}

	void updateCanvasCursor() {
		canvasController.updateCanvasCursor();
	}

	public void updateScaleComboValue() {
		scaleController.updateScaleValue();
	}

	public void setScaleComboEnabled(boolean b) {
		scaleController.setScaleComboEnabled(b);
	}

	public void rebuildScaleCombo() {
		scaleController.rebuildScaleCombo();
	}

	public void setOtherComponentsEnabled(boolean b) {
		scaleController.setScaleComboEnabled(b);
		transparencySliderController.updateTransparencyEnabled();
		toolbarController.setAllToolsEnabled(true);
	}

	public void undo() {
		canvasController.undo();
	}

	public void redo() {
		canvasController.redo();
	}

	public void clearToolGraphics() {
		canvasController.clearToolGraphics();
	}

	public void setPressingHandPanCursor(boolean b) {
		canvasController.setPressingHandPanCursor(b);
	}

	public CustomNode getCurrentTheme() {
		return treeController.getCurrentTheme();
	}

	void applyView() {
		treeController.applyView();
	}

	public CustomNode getNodeView(CustomNode node) {
		return treeController.getNodeView(node);
	}

	public void setCurrentView(CustomNode view) {
		treeController.setCurrentView(view);

	}

	public List<CustomNode> getSelectedThemes() {
		if (getCurrentView() == null) {
			setCurrentView((CustomNode) treeController.getRoot().getChildAt(0));
			getCurrentView().setSelected(true);
			treeController.setSelectionPath(new TreePath(getCurrentView()
					.getPath()));

		}
		return treeController.getSelectedThemes();
	}

	public CustomNode getCurrentView() {
		return treeController.getCurrentView();
	}

	public void setUserType(UserType userType) {
		treeController.setUserType(userType);
	}

	public CustomNode createToolBar() {
		return treeController.createToolBar();
	}

	public CustomNode createRoot() {
		return treeController.createRoot();
	}

	public DefaultTreeModel getDefaultTreeModel() {
		return treeController.getDefaultTreeModel();
	}

	public void setDefaultTreeModel(DefaultTreeModel defaultTreeModel) {
		treeController.setDefaultTreeModel(defaultTreeModel);
	}

	public UserType getUserType() {
		return treeController.getUserType();
	}

	public void setSelectedNode(CustomNode selectedNode) {
		treeController.setSelectedNode(selectedNode);
	}

	public CustomNode getSelectedNode() {
		return treeController.getSelectedNode();
	}

	public void repaint() {
		treeController.repaint();
	}

	public CustomNode getLastSelectedPathComponent() {
		return treeController.getLastSelectedPathComponent();
	}

	public void setSelectionPath(TreePath treePath) {
		treeController.setSelectionPath(treePath);
	}

	public TreePath getSelectionPath() {
		return treeController.getSelectionPath();
	}

	public void expandPath(TreePath path) {
		treeController.expandPath(path);
	}

	public void scrollPathToVisible(TreePath path) {
		treeController.scrollPathToVisible(path);
	}

	public void disableTree() {
		treeController.disableTree();
	}

	public void enableTree() {
		treeController.enableTree();
	}

	public void verifyScaleLimits() {
		ScaleService.verifyScaleLimits();
	}

	public void collapsePath(TreePath path) {
		treeController.collapsePath(path);
	}

	public void addNode(CustomNode node, CustomNode parent) {
		treeController.addNode(node, parent);
	}

	public List<Layer> getLayers() {
		return treeController.getLayers();
	}

	public Point getLocation() {
		return mainPanelController.getLocation();
	}

	public int getHeigth() {
		return mainPanelController.getHeight();
	}

	public int getWidth() {
		return mainPanelController.getWidth();
	}

	public void removeNodeFromParent(CustomNode node) {
		treeController.removeNodeFromParent(node);
	}

	public CustomNode getRoot() {
		return treeController.getRoot();
	}

	public Boolean updateSelectedView(Boolean reload, Boolean memory) {
		return treeController.updateSelectedView(reload, memory);
	}

	boolean checkAllNodes(CustomNode root, CustomNode compare) {
		return treeController.checkAllNodes(root, compare);
	}

	void reload() {
		treeController.reload();
	}

	public boolean isToolEnabled(ToolEnum tool) {
		return toolbarController.isToolEnabled(tool);
	}

	public void setExpanderButtonVisible(boolean b) {
		labelCanvasExpanderController.setExpanderButtonVisible(b);
	}

	public void setLinkCursor(boolean b) {
		canvasController.setLinkCursor(b);
	}

	public void resizeViewToApplet(int[] size) {
		outlookBarController.resizeToApplet(size);
		tabbedToolBarsController.resizeTabbedToolBarsToApplet(size);
		canvasController.resizeCanvas(size);
		mainPanelController.resizeToApplet(size);
	}

	public void clearSelectedObjects() {
		canvasController.clearSelectedObjects();
	}

	public InfoToolView showInfo(List<ThemeAttributesInfoTool> info) {
		return canvasController.showInfo(info);
	}

	public void insertPluginToolBar(String title, Component toolBar, String tip) {
		tabbedToolBarsController.insertPluginToolBar(title, toolBar, tip);
	}

	public void addSideBar(String name, Icon icon, JComponent component) {
		outlookBarController.addSideBar(name, icon, component);
	}

	public void removeSideBar(String name) {
		outlookBarController.removeSideBar(name);
	}

	public void setLastBarVisible() {
		outlookBarController.setLastVisibleBar();
	}

	public void repaintOutlookBar() {
		outlookBarController.repaintOutlookBar();
	}

	public ToolbarController getToolbarController() {
		return toolbarController;
	}

	public synchronized Tool getCurrentTool() {
		return this.tool;
	}

	public synchronized void setCurrentTool(Tool tool) {
		if (tool != null) {
			if (this.tool == null) {
				this.tool = tool;
				canvasController.getListenersHandler().attachListener(tool,
						tool.getEventsToListen());
				tool.getListenersHandler().attachListener(canvasController,
						null);
				try {
					canvasController.dispatch(
							canvasController.getTransmitter(),
							new AfterToolChangedEvent(canvasController));
				} catch (GlueServerException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.updateCanvasCursor();
				return;
			}
			if (!this.tool.getClass().getName()
					.equals(tool.getClass().getName())) {
				this.lastTool = this.tool;
				try {
					canvasController.dispatch(
							canvasController.getTransmitter(),
							new BeforeToolChangedEvent(canvasController));
				} catch (GlueServerException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ListenersHandler listenersHandler = canvasController
						.getListenersHandler();
				listenersHandler.detachListener(this.tool);
				this.tool = tool;
				listenersHandler.attachListener(tool, tool.getEventsToListen());
				tool.getListenersHandler().attachListener(canvasController,
						null);
				try {
					canvasController.dispatch(
							canvasController.getTransmitter(),
							new AfterToolChangedEvent(canvasController));
				} catch (GlueServerException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.updateCanvasCursor();
			}
		}
	}

	public Tool getLastTool() {
		return lastTool;
	}

	public void setImageLoadingStatus(ImageIcon img) {
		mainPanelController.setImageLoadingStatus(img);
	}

	public void setCanvas(AbstractCanvas canvas) {
		mainPanelController.setCanvas(canvas);
	}

	public void updateToolBar() {
		this.toolbarController.updateToolbar();
	}

	/**
	 * Gets the {@link JComponent} by name.
	 * 
	 * @param name
	 * @return the {@link JComponent}. If the {@link JOutlookBar} doesn't have a
	 *         component with the specified name this method return null.
	 */
	public JComponent getSidebar(String name) {
		return this.outlookBarController.getSideBar(name);
	}

	public CanvasController getCanvasController() {
		return this.canvasController;
	}

	public void setScaleController(ScaleController scaleController) {
		this.scaleController = scaleController;
	}

	public ScaleController getScaleController() {
		return this.scaleController;
	}
}
