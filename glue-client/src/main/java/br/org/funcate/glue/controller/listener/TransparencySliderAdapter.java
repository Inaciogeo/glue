package br.org.funcate.glue.controller.listener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.controller.TransparencySliderController;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.view.TransparencySliderView;

public class TransparencySliderAdapter implements ChangeListener {

	private TransparencySliderController transparencySliderController;

	public TransparencySliderAdapter(TransparencySliderView transparencySliderView) {
		transparencySliderController = new TransparencySliderController(transparencySliderView);

		Mediator mediator = AppSingleton.getInstance().getMediator();
		mediator.setTransparencySliderController(transparencySliderController);
	}

	public void stateChanged(ChangeEvent e) {
		transparencySliderController.changeTransparencyFactor();
	}
}
