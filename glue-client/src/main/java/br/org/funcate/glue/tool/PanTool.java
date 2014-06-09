package br.org.funcate.glue.tool;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.ImageIcon;

import br.org.funcate.eagles.kernel.dispatcher.EventHandler;
import br.org.funcate.eagles.kernel.listener.ListenersHandler;
import br.org.funcate.eagles.kernel.listener.ListenersHandlerImpl;
import br.org.funcate.eagles.kernel.transmitter.DirectedEventTransmitter;
import br.org.funcate.eagles.kernel.transmitter.EventTransmitter;
import br.org.funcate.glue.event.AfterToolChangedEvent;
import br.org.funcate.glue.event.BeforeToolChangedEvent;
import br.org.funcate.glue.event.BoxChangedEvent;
import br.org.funcate.glue.event.MouseDraggedEvent;
import br.org.funcate.glue.event.MousePressedEvent;
import br.org.funcate.glue.event.MouseReleasedEvent;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.CalculatorService;
import br.org.funcate.glue.model.canvas.CanvasService;
import br.org.funcate.glue.model.canvas.GeneralTileSchema;
import br.org.funcate.glue.model.canvas.PanToolService;
import br.org.funcate.glue.model.canvas.ToolRedirectionService;
import br.org.funcate.glue.model.toolbar.ToolEnum;
import br.org.funcate.glue.model.toolbar.ToolService;
import br.org.funcate.glue.model.toolbar.ToolState;
import br.org.funcate.glue.view.ImageIconLoader;

public class PanTool implements Tool {

	private ListenersHandler listeners;
	private EventHandler eventHandler;
	private EventTransmitter transmitter;

	private List<String> eventsToListen;

	private Cursor cursor;

	public PanTool() {
		listeners = new ListenersHandlerImpl();
		eventHandler = new EventHandler();
		transmitter = new DirectedEventTransmitter(this);

		ImageIcon imgIconPan = ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/cursor03.gif",
				ToolRedirectionService.class);
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				imgIconPan.getImage(), new Point(10, 10), "Mão");

		eventsToListen = new ArrayList<String>();
		eventsToListen.add(MousePressedEvent.class.getName());
		eventsToListen.add(MouseDraggedEvent.class.getName());
		eventsToListen.add(MouseReleasedEvent.class.getName());
		eventsToListen.add(BeforeToolChangedEvent.class.getName());
		eventsToListen.add(AfterToolChangedEvent.class.getName());
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
	public void handle(EventObject e) throws Exception {
		if (e instanceof MousePressedEvent) {
			this.handle((MousePressedEvent) e);
		} else if (e instanceof MouseDraggedEvent) {
			this.handle((MouseDraggedEvent) e);
		} else if (e instanceof MouseReleasedEvent) {
			this.handle((MouseReleasedEvent) e);
		} else if (e instanceof BeforeToolChangedEvent) {
			this.handle((BeforeToolChangedEvent) e);
		} else if (e instanceof AfterToolChangedEvent) {
			this.handle((AfterToolChangedEvent) e);
		}
	}
	
	private void handle(AfterToolChangedEvent e) {
		ToolState toolState = ToolService.getTool(ToolEnum.PAN);
		toolState.setSelected(true);
		AppSingleton.getInstance().getMediator().updateToolBar();
	}

	private void handle(BeforeToolChangedEvent e) {
		ToolState toolState = ToolService.getTool(ToolEnum.PAN);
		toolState.setSelected(false);
		AppSingleton.getInstance().getMediator().updateToolBar();
	}

	private void handle(MousePressedEvent e) {
		double[] point = CalculatorService.convertFromWorldToPixel(e.getX(),
				e.getY());
		CanvasService.setPressingHandPanCursor(true);
		PanToolService.pressPanTool((int) point[0], (int) point[1]);
	}

	private void handle(MouseDraggedEvent e) {
		double[] point = CalculatorService.convertFromWorldToPixel(e.getX(),
				e.getY());
		PanToolService.dragPanTool((int) point[0], (int) point[1]);
	}

	private void handle(MouseReleasedEvent e) throws Exception {
		CanvasService.setPressingHandPanCursor(false);
		GeneralTileSchema.generateTilesLists(true);
		dispatch(transmitter, new BoxChangedEvent(this));
	}

	@Override
	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}

	@Override
	public Cursor getCursor() {
		return cursor;
	}

	@Override
	public List<String> getEventsToListen() {
		return eventsToListen;
	}
}
