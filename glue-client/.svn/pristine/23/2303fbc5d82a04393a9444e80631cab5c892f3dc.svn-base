package br.org.funcate.glue.tool;

import java.awt.Cursor;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import br.org.funcate.eagles.kernel.dispatcher.EventHandler;
import br.org.funcate.eagles.kernel.listener.ListenersHandler;
import br.org.funcate.eagles.kernel.listener.ListenersHandlerImpl;
import br.org.funcate.eagles.kernel.transmitter.DirectedEventTransmitter;
import br.org.funcate.eagles.kernel.transmitter.EventTransmitter;
import br.org.funcate.glue.event.BoxChangedEvent;
import br.org.funcate.glue.event.MouseDraggedEvent;
import br.org.funcate.glue.event.MousePressedEvent;
import br.org.funcate.glue.event.MouseReleasedEvent;
import br.org.funcate.glue.model.CalculatorService;
import br.org.funcate.glue.model.canvas.ZoomToolService;

public class ZoomAreaTool implements Tool {

	private ListenersHandler listeners;
	private EventHandler eventHandler;
	private EventTransmitter transmitter;

	private List<String> eventsToListen;

	private Cursor cursor;

	public ZoomAreaTool() {

		listeners = new ListenersHandlerImpl();
		eventHandler = new EventHandler();
		transmitter = new DirectedEventTransmitter(this);

		cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);

		eventsToListen = new ArrayList<String>();
		eventsToListen.add(MousePressedEvent.class.getName());
		eventsToListen.add(MouseDraggedEvent.class.getName());
		eventsToListen.add(MouseReleasedEvent.class.getName());
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
		}
	}

	public void handle(MousePressedEvent e) {
		double[] point = CalculatorService.convertFromWorldToPixel(e.getX(), e.getY());
		ZoomToolService.pressZoomArea((int) point[0], (int) point[1]);
	}

	public void handle(MouseDraggedEvent e) {
		double[] point = CalculatorService.convertFromWorldToPixel(e.getX(), e.getY());
		ZoomToolService.dragZoomArea((int) point[0], (int) point[1]);
	}

	public void handle(MouseReleasedEvent e) throws Exception {
		double[] point = CalculatorService.convertFromWorldToPixel(e.getX(), e.getY());
		ZoomToolService.releaseZoomArea((int) point[0], (int) point[1]);
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
