package br.org.funcate.glue.controller;

import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.util.EventObject;
import java.util.List;

import javax.swing.JOptionPane;

import br.org.funcate.eagles.kernel.dispatcher.EventDispatcher;
import br.org.funcate.eagles.kernel.dispatcher.EventHandler;
import br.org.funcate.eagles.kernel.listener.EventListener;
import br.org.funcate.eagles.kernel.listener.ListenersHandler;
import br.org.funcate.eagles.kernel.listener.ListenersHandlerImpl;
import br.org.funcate.eagles.kernel.transmitter.DirectedEventTransmitter;
import br.org.funcate.eagles.kernel.transmitter.EventTransmitter;
import br.org.funcate.glue.event.BoxChangedEvent;
import br.org.funcate.glue.event.CanvasDrawEvent;
import br.org.funcate.glue.event.CleanBufferEvent;
import br.org.funcate.glue.event.DrawFeatureEvent;
import br.org.funcate.glue.event.DrawLayersEvent;
import br.org.funcate.glue.event.DrawTilesEvent;
import br.org.funcate.glue.event.GetCanvasStateEvent;
import br.org.funcate.glue.event.GetLocaleEvent;
import br.org.funcate.glue.event.GetScreenCoordinates;
import br.org.funcate.glue.event.GetWorldCoordinates;
import br.org.funcate.glue.event.KeyPressedEvent;
import br.org.funcate.glue.event.KeyReleasedEvent;
import br.org.funcate.glue.event.MouseClickedEvent;
import br.org.funcate.glue.event.MouseDraggedEvent;
import br.org.funcate.glue.event.MouseMovedEvent;
import br.org.funcate.glue.event.MousePressedEvent;
import br.org.funcate.glue.event.MouseReleasedEvent;
import br.org.funcate.glue.event.ScaleChangedEvent;
import br.org.funcate.glue.event.SelectFeatureEvent;
import br.org.funcate.glue.event.SelectedThemeEvent;
import br.org.funcate.glue.event.UnselectedThemeEvent;
import br.org.funcate.glue.event.UpdateCursorEvent;
import br.org.funcate.glue.main.AppSingleton;

import br.org.funcate.glue.model.CalculatorService;
import br.org.funcate.glue.model.canvas.BufferEnum;
import br.org.funcate.glue.model.canvas.CanvasService;
import br.org.funcate.glue.model.canvas.CanvasState;
import br.org.funcate.glue.model.canvas.GeneralTileSchema;
import br.org.funcate.glue.model.canvas.HighlightObjectsService;
import br.org.funcate.glue.model.canvas.ThemeAttributesInfoTool;
import br.org.funcate.glue.model.canvas.ToolRedirectionService;
import br.org.funcate.glue.model.canvas.UndoRedoService;
import br.org.funcate.glue.model.exception.GlueServerException;

import br.org.funcate.glue.view.AbstractCanvas;
import br.org.funcate.glue.view.InfoToolView;
import br.org.funcate.glue.view.SearchPanel;


public class CanvasController implements EventDispatcher, EventListener, Runnable {

	private AbstractCanvas canvasView;
	private ListenersHandler listeners;
	private EventHandler eventHandler;
	private EventTransmitter transmitter;

	public CanvasController(AbstractCanvas canvasView) {
		this.canvasView = canvasView;
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		state.getCanvasGraphicsBuffer().addObserver(canvasView);
		state.setImageObserver(canvasView);

		listeners = new ListenersHandlerImpl();
		eventHandler = new EventHandler();
		transmitter = new DirectedEventTransmitter(this);
		this.initListeners();
	}

	private void initListeners() {
		TreeController treeController = AppSingleton.getInstance()
				.getMediator().getTreeController();
		this.getListenersHandler().attachListener(treeController,
				treeController.getEventsToListen());
		treeController.getListenersHandler().attachListener(this, null);
		
		ToolbarController toolBarController = AppSingleton.getInstance()
				.getMediator().getToolbarController();
		this.getListenersHandler().attachListener(toolBarController,
				toolBarController.getEventsToListen());
		toolBarController.getListenersHandler().attachListener(this, null);
		
		ScaleController scaleController = AppSingleton.getInstance()
				.getMediator().getScaleController();
		this.getListenersHandler().attachListener(scaleController,
				scaleController.getEventsToListen());
		toolBarController.getListenersHandler().attachListener(this, null);
	}

	void draw(boolean clearCache, boolean enablePanTool) {
		try {
			CanvasService.draw(clearCache, enablePanTool);
		} catch (GlueServerException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void setToolBarSource(Object source) {
		try {
			CanvasService.setToolBarSource(source);
			CanvasService.draw(true, true);
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	void resetLayerBoxPosition() {
		try {
			CanvasService.resetLayerBoxPosition();
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	void undo() {
		UndoRedoService.undo();
	}

	void redo() {
		UndoRedoService.redo();
	}

	void updateCanvasCursor() {
		Cursor cursor = ToolRedirectionService.getCursor();
		canvasView.setCursor(cursor);
	}

	public void executeMouseMove(int mouseXPosition, int mouseYPosition) {
		ToolRedirectionService.executeMouseMovedEvent(mouseXPosition,
				mouseYPosition);
		double[] point = CalculatorService.convertFromPixelToWorld(
				mouseXPosition, mouseYPosition);
		try {
			this.dispatch(transmitter, new MouseMovedEvent(this, point[0],
					point[1]));
		} catch (GlueServerException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void executeMouseDrag(MouseEvent e) {
		double[] point = CalculatorService.convertFromPixelToWorld(e.getX(),
				e.getY());
		MouseDraggedEvent mouseDraggedEvent = new MouseDraggedEvent(this,
				point[0], point[1]);
		GeneralTileSchema.setWheelMoved(false);
		try {
			dispatch(transmitter, mouseDraggedEvent);
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void executeKeyPress(KeyEvent event) {
		try {
			CanvasService.executeKeyPressedEvent(event.getKeyCode());
		} catch (GlueServerException e1) {

			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		try {
			this.dispatch(transmitter, new KeyPressedEvent(this, event));
		} catch (GlueServerException e1) {

			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void executeMouseWheelMove(MouseWheelEvent e) {
	
		try {
			ToolRedirectionService.executeMouseWheelEvent(e.getX(), e.getY(),
					e.getWheelRotation());
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		this.cleanBuffer(BufferEnum.EDITION);
		GeneralTileSchema.setWheelMoved(true);
		BoxChangedEvent event = new BoxChangedEvent(this);
		ScaleChangedEvent scaleChangedEvent = new ScaleChangedEvent(this);
		
		try {
			this.dispatch(transmitter,scaleChangedEvent);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			this.handle(event);
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public void executeMousePress(MouseEvent e) {
		CanvasService.deleteMark();
		double[] point = CalculatorService.convertFromPixelToWorld(e.getX(),e.getY());
		MousePressedEvent mousePressedEvent = new MousePressedEvent(this, e.getButton(), point[0],
				point[1], e.getX(), e.getY());
		try {
			dispatch(transmitter, mousePressedEvent);
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public void executeMouseClick(MouseEvent e) {
		try {
			ToolRedirectionService.executeMouseClickedEvent(e.getX(), e.getY(),
					e.getClickCount(), e.getButton());
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		double[] point = CalculatorService.convertFromPixelToWorld(e.getX(),
				e.getY());
		MouseClickedEvent mouseClickedEvent = new MouseClickedEvent(this,
				point[0], point[1], e);
		try {
			dispatch(transmitter, mouseClickedEvent);

		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (e.getClickCount() == 2) {
			this.cleanBuffer(BufferEnum.EDITION);
			BoxChangedEvent event = new BoxChangedEvent(this);
			try {
				this.handle(event);
			} catch (GlueServerException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public void executeMouseRelease(MouseEvent e) {
		double[] point = CalculatorService.convertFromPixelToWorld(e.getX(),
				e.getY());
		MouseReleasedEvent mouseReleasedEvent = new MouseReleasedEvent(this,
				point[0], point[1], e);
		
//		if(ToolService.getSelectedTool().equals(ToolEnum.ZOOMAREA)||
//				ToolService.getSelectedTool().equals(ToolEnum.ZOOMIN)||
//				ToolService.getSelectedTool().equals(ToolEnum.ZOOMOUT)){
			
			ScaleChangedEvent scaleChangedEvent = new ScaleChangedEvent(this);
			try {
				this.dispatch(transmitter, scaleChangedEvent);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		//}
		try {
			dispatch(transmitter, mouseReleasedEvent);
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	void expandCanvas(boolean expanded) {
		int[] bounds = CanvasService.expandCanvas(expanded);
		canvasView.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
		try {
			CanvasService.draw(false, true);
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		this.cleanBuffer(BufferEnum.EDITION);
		BoxChangedEvent event = new BoxChangedEvent(this);
		try {
			this.handle(event);
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void exportImage() {
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		BufferedImage image = state.getCanvasGraphicsBuffer().getCanvasBuffer();
		ToolRedirectionService.saveCurrentImage(image);
	}

	void clearToolGraphics() {
		CanvasService.cleanToolGraphics();
	}

	public void setPressingHandPanCursor(boolean b) {
		if (b) {
			canvasView
					.setCursor(ToolRedirectionService.getPressingHandCursor());
		} else {
			canvasView.setCursor(ToolRedirectionService
					.getNormalPanHandCursor());
		}
	}

	public void setLinkCursor(boolean b) {
		if (b) {
			canvasView.setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else {
			canvasView.setCursor(ToolRedirectionService.getCursor());
		}
	}

	void resizeCanvas(int[] size) {
		int[] finalSize = CanvasService
				.getCanvasSizesToApplet(size[0], size[1]);
		canvasView.setSize(finalSize[0], finalSize[1]);
		this.cleanBuffer(BufferEnum.EDITION);
		BoxChangedEvent event = new BoxChangedEvent(this);
		try {
			this.handle(event);
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// CanvasService.draw(false);
	}

	void clearSelectedObjects() {
		try {
			HighlightObjectsService.cleanSelection();
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	InfoToolView showInfo(List<ThemeAttributesInfoTool> info) {
		if (info == null) {
			JOptionPane.showMessageDialog(canvasView,
					"Não existem objetos na área apontada!", "Atenção",
					JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		return InfoToolView.getInstance(info);
	}

	@Override
	public ListenersHandler getListenersHandler() {
		return this.listeners;
	}

	@Override
	public EventHandler getEventHandler() {
		return this.eventHandler;
	}

	public EventTransmitter getTransmitter() {
		return transmitter;
	}

	public void dispatch(EventObject e) throws Exception {
		this.dispatch(transmitter, e);
	}
	
	@Override
	public void dispatch(EventTransmitter tc, EventObject e)throws Exception {
		tc.dispatch(e);
	}

	@Override
	public void handle(EventObject event) throws Exception {
		if (event instanceof UpdateCursorEvent) {
			this.handle((UpdateCursorEvent) event);
		} else if (event instanceof BoxChangedEvent) {
			this.handle((BoxChangedEvent) event);
		} else if (event instanceof CleanBufferEvent) {
			this.handle((CleanBufferEvent) event);
		} else if (event instanceof GetLocaleEvent) {
			this.handle((GetLocaleEvent) event);
		} else if (event instanceof DrawLayersEvent) {
			this.handle((DrawLayersEvent) event);
		} else if (event instanceof DrawTilesEvent) {
			this.handle((DrawTilesEvent) event);
		} else if (event instanceof GetCanvasStateEvent) {
			this.handle((GetCanvasStateEvent) event);
		} else if (event instanceof CanvasDrawEvent) {
			this.handle((CanvasDrawEvent) event);
		} else if (event instanceof GetScreenCoordinates) {
			this.handle((GetScreenCoordinates) event);
		} else if (event instanceof GetWorldCoordinates) {
			this.handle((GetWorldCoordinates) event);
		} else if (event instanceof SelectedThemeEvent) {
			this.handle((SelectedThemeEvent) event);
		} else if (event instanceof UnselectedThemeEvent) {
			this.handle((UnselectedThemeEvent) event);
		} else if (event instanceof ScaleChangedEvent) {
			this.handle((ScaleChangedEvent) event);
		} else if (event instanceof DrawFeatureEvent){
			this.handle((DrawFeatureEvent)event);
		}else {
			this.dispatch(event);
		}
	}
	
	private void handle(DrawFeatureEvent event) throws Exception{
		this.dispatch(transmitter, event);
	}
	
	private void handle(ScaleChangedEvent event) throws Exception {
		this.dispatch(transmitter, event);	
	}
	
	private void handle (SelectedThemeEvent e) throws Exception {
//		LoadingStatusService.setThemeChanged(true);
		this.dispatch(transmitter, e);
	}

	private void handle (UnselectedThemeEvent e) throws Exception {
//		LoadingStatusService.setThemeChanged(true);
		this.dispatch(transmitter, e);
	}
	
	private void handle(CanvasDrawEvent event) {
		try {
			CanvasService.draw(event.isClearCache(), false);
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	private void handle(GetCanvasStateEvent event) {
		event.setCanvasState(AppSingleton.getInstance().getCanvasState());
	}

	private void handle(DrawTilesEvent event) {
		GeneralTileSchema.generateTilesLists(false);
	}

	private void handle(BoxChangedEvent event) throws Exception {
		this.cleanBuffer(BufferEnum.EDITION);
		event.setCanvas(canvasView);
		this.dispatch(transmitter, event);
	}

	private void handle(DrawLayersEvent event) throws Exception {
		event.setCanvas(canvasView);
		this.dispatch(transmitter, event);
	}

	private void handle(CleanBufferEvent event) {
		if (event == null || event.getBufferId() == null) {
			return;
		}

		this.cleanBuffer(event.getBufferId());
	}

	private void handle(UpdateCursorEvent e) {
		this.updateCanvasCursor();
	}

	private void handle(GetLocaleEvent event) {
		event.setLocale(AppSingleton.getInstance().getLocale());
	}

	private void handle(GetScreenCoordinates event) {
		double[] values = CalculatorService.convertFromWorldToPixel(
				event.getX(), event.getY());
		event.setX(values[0]);
		event.setY(values[1]);
	}

	private void handle(GetWorldCoordinates event) {
		double[] values = CalculatorService.convertFromPixelToWorld(
				(int) event.getX(), (int) event.getY());
		event.setX(values[0]);
		event.setY(values[1]);
	}

	private void cleanBuffer(BufferEnum bufferId) {
		canvasView.update(canvasView.getGraphics());
		CanvasService.cleanBuffer(bufferId);
	}

	public void executeKeyRelease(KeyEvent k) {
		try {
			this.dispatch(transmitter, new KeyReleasedEvent(this, k));
		} catch (GlueServerException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//pesquisa de endereço 
	@Override
	public void run() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		
		if(state.getGvSource()=="DrawFeatureEvent"){
			doDrawFeatureEvent();
		}else if(state.getGvSource()=="SelectFeatureEvent"){
			doSelectFeatureEvent();
		}
	}
	
	public void doDrawFeatureEvent(){
		DrawFeatureEvent drawFeatureEvent = new DrawFeatureEvent(this);
		drawFeatureEvent.setLineIds(SearchPanel.getStreetIds());
		drawFeatureEvent.setPolygonIds(SearchPanel.getLotIds());
		try {
			this.dispatch(transmitter,drawFeatureEvent);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void doSelectFeatureEvent(){
		SelectFeatureEvent selectFeatureEvent = new SelectFeatureEvent(this);
		try {
			this.dispatch(transmitter,selectFeatureEvent);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
