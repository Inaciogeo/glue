package br.org.funcate.glue.controller;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.ComboBoxModel;

import br.org.funcate.eagles.kernel.dispatcher.EventDispatcher;
import br.org.funcate.eagles.kernel.dispatcher.EventHandler;
import br.org.funcate.eagles.kernel.listener.EventListener;
import br.org.funcate.eagles.kernel.listener.ListenersHandler;
import br.org.funcate.eagles.kernel.listener.ListenersHandlerImpl;
import br.org.funcate.eagles.kernel.transmitter.DirectedEventTransmitter;
import br.org.funcate.eagles.kernel.transmitter.EventTransmitter;
import br.org.funcate.glue.controller.listener.ScaleViewAdapter;
import br.org.funcate.glue.event.BoxChangedEvent;
import br.org.funcate.glue.event.ScaleChangedEvent;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.ComboBoxScaleService;
import br.org.funcate.glue.model.ScaleElement;
import br.org.funcate.glue.view.ComboBoxScale;
import br.org.funcate.glue.view.ScaleView;

public class ScaleController implements EventDispatcher, EventListener {

	private ComboBoxScale comboBox;
	private boolean validateComboBoxEvent = true;
	private ListenersHandler listeners;
	private EventHandler eventHandler;
	private EventTransmitter transmitter;
	private List<String> eventsToListen;
	private ScaleView scaleView;

	public ScaleController(ComboBoxScale comboBox) {
		this();
		this.comboBox = comboBox;
		this.scaleView = ScaleView.getInstance();
		ScaleViewAdapter scaleAdapter = new ScaleViewAdapter(scaleView);
		this.scaleView.setAdapter(scaleAdapter);
		/*
		 * deve haver apenas uma instancia de ScaleContrroler para que o
		 * CanvasController trate ou despache eventos deste
		 */
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		mediator.setScaleController(this);
	}

	private ScaleController() {
		listeners = new ListenersHandlerImpl();
		eventHandler = new EventHandler();
		transmitter = new DirectedEventTransmitter(this);
		eventsToListen = new ArrayList<String>();
		eventsToListen.add(BoxChangedEvent.class.getName());
	}

	@Override
	public EventHandler getEventHandler() {
		return this.eventHandler;
	}

	@Override
	public ListenersHandler getListenersHandler() {
		return this.listeners;
	}

	@Override
	public void handle(EventObject e) throws Exception {
		if (e instanceof BoxChangedEvent) {
			this.handle((BoxChangedEvent) e);
		}
	}

	private void handle(BoxChangedEvent e) {
		// TODO
	}

	@Override
	public void dispatch(EventTransmitter tc, EventObject e) throws Exception {
		tc.dispatch(e);
	}

	public void dispatch(EventObject e) throws Exception {
		this.dispatch(transmitter, e);
	}

	public List<String> getEventsToListen() {
		return this.eventsToListen;
	}

	public boolean isValidateComboBoxEvent() {
		return validateComboBoxEvent;
	}

	public void setValidateComboBoxEvent(boolean validateComboBoxEvent) {
		this.validateComboBoxEvent = validateComboBoxEvent;
	}

	public void changeScale() {
		ScaleElement element = (ScaleElement) comboBox.getSelectedItem();

		if (element == null) {
			return;
		}

		double value = element.getScale();
		ComboBoxScaleService.changeScaleValue(value);

		// TODO
		try {
			this.dispatch(new ScaleChangedEvent(this));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ComboBoxModel getModel() {
		return ComboBoxScaleService.generateComboBoxModel();
	}

	void move(boolean expanded) {
		if (expanded)
			comboBox.setBounds(9, 43, 130, 22);
		else
			comboBox.setBounds(9, 43, 130, 22);
	}

	void updateScaleValue() {
		ScaleElement element = ComboBoxScaleService
				.getCurrentScaleElementValue();
		validateComboBoxEvent = false;

		if (comboBox.getItemCount() != 0) {
			comboBox.removeItemAt(comboBox.getItemCount() - 1);
			validateComboBoxEvent = false;
		}
		comboBox.addItem(element);
		comboBox.setSelectedItem(element);
	}

	void rebuildScaleCombo() {
		ComboBoxModel model = ComboBoxScaleService
				.getRebuildedScaleComboModel();
		comboBox.setModel(model);
	}

	public void setScaleComboEnabled(boolean b) {
		comboBox.setEnabled(b);
	}
}
