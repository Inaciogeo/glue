package br.org.funcate.glue.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.jdom2.Element;
import org.jdom2.JDOMException;

import br.org.funcate.eagles.kernel.dispatcher.EventDispatcher;
import br.org.funcate.eagles.kernel.dispatcher.EventHandler;
import br.org.funcate.eagles.kernel.listener.EventListener;
import br.org.funcate.eagles.kernel.listener.ListenersHandler;
import br.org.funcate.eagles.kernel.listener.ListenersHandlerImpl;
import br.org.funcate.eagles.kernel.transmitter.DirectedEventTransmitter;
import br.org.funcate.eagles.kernel.transmitter.EventTransmitter;
import br.org.funcate.glue.event.ScaleChangedEvent;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.canvas.CanvasService;
import br.org.funcate.glue.model.canvas.CanvasState;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.model.thread.PlotterController;
import br.org.funcate.glue.model.toolbar.ToolEnum;
import br.org.funcate.glue.model.toolbar.ToolService;
import br.org.funcate.glue.model.toolbar.ToolState;
import br.org.funcate.glue.model.tree.TreeService;
import br.org.funcate.glue.os.model.OSState;
import br.org.funcate.glue.os.view.AuthenticationScreen;
import br.org.funcate.glue.os.view.ServiceOrderCreatorScreen;
import br.org.funcate.glue.os.view.ServiceOrderOnMapScreen;
import br.org.funcate.glue.tool.CleanTool;
import br.org.funcate.glue.tool.DistanceTool;
import br.org.funcate.glue.tool.ExportTool;
import br.org.funcate.glue.tool.InfoTool;
import br.org.funcate.glue.tool.PanTool;
import br.org.funcate.glue.tool.ReDoTool;
import br.org.funcate.glue.tool.RebuildTool;
import br.org.funcate.glue.tool.Tool;
import br.org.funcate.glue.tool.UnDoTool;
import br.org.funcate.glue.tool.ZoomAreaTool;
import br.org.funcate.glue.tool.ZoomInTool;
import br.org.funcate.glue.tool.ZoomOutTool;
import br.org.funcate.glue.view.ScreenRequetServices;
import br.org.funcate.glue.view.Toolbar;

/**
 * This class is the controller of Toolbar component.
 * 
 * @author Moraes, Emerson Leite
 */
public class ToolbarController implements EventDispatcher, EventListener {

	private ListenersHandler listeners;
	private EventHandler eventHandler;
	@SuppressWarnings("unused")
	private EventTransmitter transmitter;

	private List<String> eventsToListen;

	/**
	 * Toolbar attribute.
	 */
	private Toolbar toolbar;
	@SuppressWarnings("unused")
	private Element rootNode;
	@SuppressWarnings("unused")
	private String name;

	/**
	 * ToolbarController constructor.
	 * 
	 * @param toolbar
	 */
	public ToolbarController(Toolbar toolbar) {

		listeners = new ListenersHandlerImpl();
		eventHandler = new EventHandler();
		transmitter = new DirectedEventTransmitter(this);
		eventsToListen = new ArrayList<String>();
		eventsToListen.add(ScaleChangedEvent.class.getName());

		this.toolbar = toolbar;
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		mediator.setToolbarController(this);
	}

	/**
	 * This method apply the current view and draws it.
	 */
	void reDrawView() {
		AppSingleton.getInstance().getMediator().applyView();
	}

	/**
	 * This method controls the state of Toolbar's TerraLib button.
	 * 
	 */
	public void setTerraLib() {
		Mediator mediator = AppSingleton.getInstance().getMediator();
		ToolState terraLib = ToolService.getTool(ToolEnum.TERRALIB);

		if (terraLib.isSelected() == false) {
			ToolService.setSelectedTool(ToolEnum.TERRALIB);
			this.toolbar.setTerraLibSelected(true);
			mediator.expandedCanvas(false);
			mediator.setExpanderButtonVisible(true);
		} else {
			ToolService.setSelectedTool(ToolEnum.TERRALIB);
			this.toolbar.setTerraLibSelected(false);
			mediator.expandedCanvas(true);
			mediator.setExpanderButtonVisible(false);
		}
		this.updateToolbar();
		try {
			CanvasService.draw(false, true);
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	/**
	 * This method controls the state of Toolbar's Google button.
	 * @throws IOException 
	 * @throws JDOMException 
	 * 
	 */
	public void setTile() {
		
		//Mediator mediator = AppSingleton.getInstance().getMediator();
		//if (toolbar.isGoogleSelected() == false) {
			
			ToolService.setSelectedTool(ToolEnum.GOOGLE);
			this.toolbar.setGoogleSelected(true);
			
			try {
				ScreenRequetServices dialog = ScreenRequetServices.getInstance();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
//		} else {
//			ToolService.setSelectedTool(ToolEnum.PAN);
//			mediator.setToolBarSource(null);
//			this.toolbar.setGoogleSelected(false);

//		}
//
//		this.updateToolbar();
	}

//	/**
//	 * This method controls the state of Toolbar's WMS button
//	 */
//	public void setWMS() {
//		Mediator mediator = AppSingleton.getInstance().getMediator();
//		ToolWMS wms = null;
//		ToolState wmsTool = ToolService.getTool(ToolEnum.WMS);
//
//		if (wmsTool.isSelected() == false) {
//			try {
//				wms = ToolWMS.getInstance();
//				wms.setVisible(true);
//				ToolService.setSelectedTool(ToolEnum.WMS);
//				this.toolbar.setWmsSelected(true);
//			} catch (NullPointerException e) {
//				ToolService.setSelectedTool(ToolEnum.WMS);
//				this.toolbar.setWmsSelected(true);
//			}
//		} else {
//			ToolService.setSelectedTool(ToolEnum.WMS);
//			this.toolbar.setWmsSelected(false);
//			mediator.setToolBarSource(null);
//		}
//		this.updateToolbar();
//	}

	/**
	 * This method gets toolbar.
	 * 
	 * @return Toolbar
	 */
	public Toolbar getToolbar() {
		return this.toolbar;
	}

	/**
	 * This method sets toolbar.
	 * 
	 * @param toolbar
	 */
	public void setToolbar(Toolbar toolbar) {
		this.toolbar = toolbar;
	}

	public void setPaint() {
		PlotterController.getInstance().pausePlotter();
		this.reDrawView();
		ToolService.setSelectedTool(ToolEnum.PAINT);
		this.updateToolbar();
		ToolService.setSelectedTool(ToolEnum.PAN);
		this.setCurrentTool(PanTool.class);

	}

	public void setRebuild() {
		ToolService.setSelectedTool(ToolEnum.REBUILD);
		this.setCurrentTool(RebuildTool.class);
		this.updateButtonIconAndCanvasCursor();
		ToolService.setSelectedTool(ToolEnum.PAN);
		this.setCurrentTool(PanTool.class);
	}

	public void setZoomIn() {
		ToolService.setSelectedTool(ToolEnum.ZOOMIN);
		this.setCurrentTool(ZoomInTool.class);
		this.updateButtonIconAndCanvasCursor();
	}

	public void setZoomOut() {
		ToolService.setSelectedTool(ToolEnum.ZOOMOUT);
		this.setCurrentTool(ZoomOutTool.class);
		this.updateButtonIconAndCanvasCursor();
	}

	public void setZoomArea() {
		ToolService.setSelectedTool(ToolEnum.ZOOMAREA);
		this.setCurrentTool(ZoomAreaTool.class);
		this.updateButtonIconAndCanvasCursor();
	}

	public void setPan() {
		ToolService.setSelectedTool(ToolEnum.PAN);
		this.setCurrentTool(PanTool.class);
		this.updateButtonIconAndCanvasCursor();
	}

	public void setDistance() {
		ToolService.setSelectedTool(ToolEnum.DISTANCE);
		this.setCurrentTool(DistanceTool.class);
		this.updateButtonIconAndCanvasCursor();
	}

	void setDefaultCursor() {
		ToolService.setSelectedTool(ToolEnum.PAINT);
		this.updateButtonIconAndCanvasCursor();
	}

	public void setUnDo() {
		ToolService.setSelectedTool(ToolEnum.UNDO);
		this.setCurrentTool(UnDoTool.class);
		this.updateButtonIconAndCanvasCursor();
		this.setLastTool();
	}

	public void setReDo() {
		ToolService.setSelectedTool(ToolEnum.REDO);
		this.setCurrentTool(ReDoTool.class);
		this.updateButtonIconAndCanvasCursor();
		this.setLastTool();
	}

	private void setLastTool() {
		Mediator mediator = AppSingleton.getInstance().getMediator();
		mediator.setCurrentTool(mediator.getLastTool());
	}

	public void setPhotoLocation() {
		ToolService.setSelectedTool(ToolEnum.PHOTOLOCATION);
		this.updateButtonIconAndCanvasCursor();
		// TODO disabled tool
	}

	public void setInfo() {
		ToolService.setSelectedTool(ToolEnum.INFO);
		this.setCurrentTool(InfoTool.class);
		this.updateButtonIconAndCanvasCursor();
	}

	public void setLinks() {
		ToolService.setSelectedTool(ToolEnum.LINKS);
		this.updateButtonIconAndCanvasCursor();
		// TODO disabled tool

	}

	public void setAtribs() {
		ToolService.setSelectedTool(ToolEnum.ATRIBS);
		this.updateButtonIconAndCanvasCursor();
		// TODO disabled tool
	}

	public void setClean() {
		ToolService.setSelectedTool(ToolEnum.CLEAN);
		TreeService.cleanGroupThemes();
		this.setCurrentTool(CleanTool.class);
		this.updateButtonIconAndCanvasCursor();
	}

	public void setPdf() {
		ToolService.setSelectedTool(ToolEnum.PDF);
		this.updateButtonIconAndCanvasCursor();
		// TODO disabled tool
	}

	public void setExport() {
		ToolService.setSelectedTool(ToolEnum.EXPORT);
		this.setCurrentTool(ExportTool.class);
		this.updateButtonIconAndCanvasCursor();
		AppSingleton.getInstance().getMediator().exportImage();
	}

	public void setHelpOnline() {
		ToolService.setSelectedTool(ToolEnum.HELPONLINE);
		this.updateButtonIconAndCanvasCursor();
		// TODO disabled tool
	}

	private void updateButtonIconAndCanvasCursor() {
		this.updateToolbar();
		Mediator mediator = AppSingleton.getInstance().getMediator();
		mediator.updateCanvasCursor();
	}

	private void setCurrentTool(Class<?> tool) {
		Mediator mediator = AppSingleton.getInstance().getMediator();
		try {
			Tool toolInstance = (Tool) tool.newInstance();
			mediator.setCurrentTool(toolInstance);
		} catch (InstantiationException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao instanciar a ferramenta corrente",
					"Erro de ferramenta corrente", JOptionPane.ERROR_MESSAGE);
		} catch (IllegalAccessException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao acessar a ferramenta corrente",
					"Erro de ferramenta corrente", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * This method is responsible for management of toolbar buttons.
	 */
	void updateToolbar() {

		for (ToolEnum tool : ToolEnum.values()) {

			ToolState currentTool = ToolService.getTool(tool);

			switch (tool) {

			case TERRALIB:
				JButton terraLib = toolbar.getTerraLib();
				terraLib.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					terraLib.setIcon(toolbar.getiTerraLibNs());
				} else {
					terraLib.setIcon(toolbar.getiTerraLib());
				}
				break;

			case GOOGLE:
				JButton google = Toolbar.getBtnTileRequest();
				google.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					google.setIcon(toolbar.getiGoogleNs());
				} else {
					google.setIcon(toolbar.getiGoogle());
				}
				break;

			case WMS:
				JButton wms = Toolbar.getWms();
				wms.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					wms.setIcon(toolbar.getiWmsNs());
				} else {
					wms.setIcon(toolbar.getiWms());
				}

			case PAINT:
				JButton paint = toolbar.getPaint();
				paint.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					paint.setIcon(toolbar.getiPaintNs());
				} else {
					paint.setIcon(toolbar.getiPaint());
				}
				break;

			case REBUILD:
				JButton rebuild = toolbar.getRebuild();
				rebuild.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					rebuild.setIcon(toolbar.getiRebuildNs());
				} else {
					rebuild.setIcon(toolbar.getiRebuild());
				}
				break;

			case ZOOMIN:
				JButton zoomIn = toolbar.getZoomIn();
				zoomIn.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					zoomIn.setIcon(toolbar.getiZoomInNs());
				} else {
					zoomIn.setIcon(toolbar.getiZoomIn());
				}
				break;

			case ZOOMOUT:
				JButton zoomOut = toolbar.getZoomOut();
				zoomOut.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					zoomOut.setIcon(toolbar.getiZoomOutNs());
				} else {
					zoomOut.setIcon(toolbar.getiZoomOut());
				}
				break;

			case ZOOMAREA:
				JButton zoomArea = toolbar.getSelectArea();
				zoomArea.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					zoomArea.setIcon(toolbar.getiSelectAreaNs());
				} else {
					zoomArea.setIcon(toolbar.getiSelectArea());
				}
				break;

			case PAN:
				JButton pan = toolbar.getPan();
				pan.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					pan.setIcon(toolbar.getiPanNs());
				} else {
					pan.setIcon(toolbar.getiPan());
				}
				break;

			case DISTANCE:
				JButton distance = toolbar.getDistance();
				distance.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					distance.setIcon(toolbar.getiDistanceNs());
				} else {
					distance.setIcon(toolbar.getiDistance());
				}
				break;

			case UNDO:
				JButton unDo = toolbar.getUnDo();
				unDo.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					unDo.setIcon(toolbar.getiArrowuNs());
				} else {
					unDo.setIcon(toolbar.getiArrowu());
				}
				break;

			case REDO:
				JButton reDo = toolbar.getReDo();
				reDo.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					reDo.setIcon(toolbar.getiArrowrNs());
				} else {
					reDo.setIcon(toolbar.getiArrowr());
				}
				break;

			case PHOTOLOCATION:
				JButton photoLocation = toolbar.getPhotoLocation();
				photoLocation.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					photoLocation.setIcon(toolbar.getiPhotoLocationNs());
				} else {
					photoLocation.setIcon(toolbar.getiPhotoLocation());
				}
				break;

			case INFO:
				JButton info = toolbar.getInfo();
				info.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					info.setIcon(toolbar.getiInfoNs());
				} else {
					info.setIcon(toolbar.getiInfo());
				}
				break;

			case LINKS:
				JButton links = toolbar.getLinks();
				links.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					links.setIcon(toolbar.getiLinksNs());
				} else {
					links.setIcon(toolbar.getiLinks());
				}
				break;

			case ATRIBS:
				JButton atribs = toolbar.getAtribs();
				atribs.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					atribs.setIcon(toolbar.getiAtribsNs());
				} else {
					atribs.setIcon(toolbar.getiAtribs());
				}
				break;

			case CLEAN:
				JButton clean = toolbar.getClean();
				clean.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					clean.setIcon(toolbar.getiCleanNs());
				} else {
					clean.setIcon(toolbar.getiClean());
				}
				break;

			case PDF:
				JButton pdf = toolbar.getPdf();
				pdf.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					pdf.setIcon(toolbar.getiPdfNs());
				} else {
					pdf.setIcon(toolbar.getiPdf());
				}
				break;

			case EXPORT:
				JButton export = toolbar.getExport();
				export.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					export.setIcon(toolbar.getiExportNs());
				} else {
					export.setIcon(toolbar.getiExport());
				}
				break;

			case HELPONLINE:
				JButton helpOnline = toolbar.getHelpOnline();
				helpOnline.setEnabled(currentTool.isEnabled());
				if (currentTool.isSelected()) {
					helpOnline.setIcon(toolbar.getiHelpOnlineNs());
				} else {
					helpOnline.setIcon(toolbar.getiHelpOnline());
				}
				break;
			}
		}
	}

	/**
	 * This method calls the ToolService to enable/disable one tool.
	 * 
	 * @param tool
	 * @param enabled
	 */
	void setToolEnabled(ToolEnum tool, boolean enabled) {
		ToolService.setToolEnabled(tool, enabled);
		this.updateToolbar();
	}

	/**
	 * This method calls the ToolService to enable/disable all tools (except
	 * TerraLib, WMS, Google and Paint).
	 * 
	 * @param enabled
	 */
	public void setAllToolsEnabled(boolean enabled) {
		ToolService.setAllToolsEnabled(enabled);
		this.updateToolbar();
	}

	/**
	 * This method return if terraLib is selected or not.
	 * 
	 * @return boolean
	 */
	public boolean isTerraLibSelected() {
		return ToolService.isTerraLibSelected();
	}

	/**
	 * This method return selected tool.
	 * 
	 * @throws NullPointerException
	 * @return tool
	 */
	public ToolEnum getSelectedTool() {
		return ToolService.getSelectedTool();
	}

	boolean isToolEnabled(ToolEnum tool) {
		return ToolService.isToolEnabled(tool);
	}

	@Override
	public ListenersHandler getListenersHandler() {
		return this.listeners;
	}

	@Override
	public EventHandler getEventHandler() {
		return this.eventHandler;
	}

	@Override
	public void dispatch(EventTransmitter tc, EventObject e) throws Exception {
		tc.dispatch(e);
	}

	@Override
	public void handle(EventObject e) {
		if (e instanceof ScaleChangedEvent) {
			this.handle((ScaleChangedEvent) e);
		}
	}

	private void handle(ScaleChangedEvent e) {
		// TODO
	}

	public List<String> getEventsToListen() {
		return this.eventsToListen;
	}

	public void setAuthentication() {
		if(OSState.isAuth()){
			Toolbar.getBtnOsSelect().setVisible(false);
			Toolbar.getBtnOsShow().setVisible(false);
			OSState.setAuth(false);
		}else{
			AuthenticationScreen screen = new AuthenticationScreen();
			screen.setVisible(true);
		}
	}

	public void setNewOS() {
		ServiceOrderCreatorScreen osScreen = ServiceOrderCreatorScreen.getInstance();
		osScreen.setVisible(true);	
	}

	public void setSelected() {
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		CanvasState state = singleton.getCanvasState();
		state.setGvSource("SelectFeatureEvent");
		state.setGvSourceType("ip");

		CanvasController canvasController = mediator.getCanvasController();
		Thread ip = new Thread(canvasController);
		ip.start();
	}

	public void setShowOS() {
		ServiceOrderOnMapScreen mapScreen = ServiceOrderOnMapScreen.getInstance();
		mapScreen.setVisible(true);
	}

	public void selectOS() {
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		CanvasState state = singleton.getCanvasState();
		state.setGvSource("SelectFeatureEvent");
		state.setGvSourceType("os");

		CanvasController canvasController = mediator.getCanvasController();
		Thread ip = new Thread(canvasController);
		ip.start();
		
	}
}
