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
import br.org.funcate.glue.event.MouseDraggedEvent;
import br.org.funcate.glue.event.MousePressedEvent;
import br.org.funcate.glue.model.CalculatorService;
import br.org.funcate.glue.model.canvas.DistanceMeasuringToolService;

public class DistanceTool implements Tool {

	private ListenersHandler listeners;
	private EventHandler eventHandler;
	@SuppressWarnings("unused")
	private EventTransmitter transmitter;

	private List<String> eventsToListen;

	private Cursor cursor;

	public DistanceTool() {
		listeners = new ListenersHandlerImpl();
		eventHandler = new EventHandler();
		transmitter = new DirectedEventTransmitter(this);

		cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);

		eventsToListen = new ArrayList<String>();
		eventsToListen.add(MousePressedEvent.class.getName());
		eventsToListen.add(MouseDraggedEvent.class.getName());
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
		if (e instanceof MousePressedEvent) {
			this.handle((MousePressedEvent) e);
		} else if (e instanceof MouseDraggedEvent) {
			this.handle((MouseDraggedEvent) e);
		}
	}

	public void handle(MousePressedEvent e) {
		double[] point = CalculatorService.convertFromWorldToPixel(e.getX(), e.getY());
		DistanceMeasuringToolService.pressDistanceTool((int) point[0], (int) point[1]);
	}

	public void handle(MouseDraggedEvent e) {
		double[] point = CalculatorService.convertFromWorldToPixel(e.getX(), e.getY());
		DistanceMeasuringToolService.dragDistanceTool((int) point[0], (int) point[1]);
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
