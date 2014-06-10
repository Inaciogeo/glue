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
import br.org.funcate.glue.event.AfterToolChangedEvent;
import br.org.funcate.glue.event.MousePressedEvent;
import br.org.funcate.glue.main.AppSingleton;

public class ExportTool implements Tool {

	private ListenersHandler listeners;
	private EventHandler eventHandler;
	@SuppressWarnings("unused")
	private EventTransmitter transmitter;

	private List<String> eventsToListen;

	private Cursor cursor;

	public ExportTool() {
		listeners = new ListenersHandlerImpl();
		eventHandler = new EventHandler();
		transmitter = new DirectedEventTransmitter(this);

		cursor = new Cursor(Cursor.DEFAULT_CURSOR);

		eventsToListen = new ArrayList<String>();
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
	public void handle(EventObject e) {
		if (e instanceof MousePressedEvent) {
			this.handle((AfterToolChangedEvent) e);
		}
	}

	public void handle(AfterToolChangedEvent e) {
		AppSingleton.getInstance().getMediator().exportImage();
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
