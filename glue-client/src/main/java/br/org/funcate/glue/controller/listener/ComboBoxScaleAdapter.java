package br.org.funcate.glue.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.controller.ScaleController;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.view.ComboBoxScale;

public class ComboBoxScaleAdapter implements ActionListener {

	private ScaleController controller;

	public ComboBoxScaleAdapter(ComboBoxScale comboBox) {
		controller = new ScaleController(comboBox);
		Mediator mediator = AppSingleton.getInstance().getMediator();
		mediator.setScaleController(controller);
	}

	
	@SuppressWarnings("rawtypes")
	public ComboBoxModel getModel() {
		return controller.getModel();
	}

	public void actionPerformed(ActionEvent ev) {
		if (controller.isValidateComboBoxEvent()) {
			controller.changeScale();
		}
		controller.setValidateComboBoxEvent(true);
	}
}
