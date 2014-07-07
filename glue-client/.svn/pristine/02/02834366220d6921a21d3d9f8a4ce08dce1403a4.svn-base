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
import br.org.funcate.glue.event.BoxChangedEvent;
import br.org.funcate.glue.event.MousePressedEvent;
import br.org.funcate.glue.model.CalculatorService;
import br.org.funcate.glue.model.canvas.ToolRedirectionService;
import br.org.funcate.glue.model.canvas.ZoomToolService;
import br.org.funcate.glue.view.ImageIconLoader;

public class ZoomOutTool implements Tool {

	private ListenersHandler listeners;
	private EventHandler eventHandler;
	private EventTransmitter transmitter;

	private List<String> eventsToListen;

	private Cursor cursor;

	public ZoomOutTool() {
		listeners = new ListenersHandlerImpl();
		eventHandler = new EventHandler();
		transmitter = new DirectedEventTransmitter(this);

		ImageIcon imgIconZoomOut = ImageIconLoader.createImageIcon("br/org/funcate/glue/image/cursor02.gif", ToolRedirectionService.class);
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(imgIconZoomOut.getImage(), new Point(10, 10), "Menos zoom");

		eventsToListen = new ArrayList<String>();
		eventsToListen.add(MousePressedEvent.class.getName());
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
		}
	}

	private void handle(MousePressedEvent e) throws Exception {
		double[] point = CalculatorService.convertFromWorldToPixel(e.getX(), e.getY());
		new TileThreadTimer().start();
		ZoomToolService.pressZoomOut((int) point[0], (int) point[1]);
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
