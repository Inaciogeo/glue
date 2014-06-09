package br.org.funcate.glue.controller;

import javax.swing.JSlider;
import javax.swing.JTextField;

import br.org.funcate.glue.model.TransparencySliderService;
import br.org.funcate.glue.model.canvas.CanvasService;
import br.org.funcate.glue.view.TransparencySliderView;

public class TransparencySliderController {

	private TransparencySliderView transparencySliderView;

	public TransparencySliderController(TransparencySliderView transparencySliderView) {
		this.transparencySliderView = transparencySliderView;
	}

	public void changeTransparencyFactor() {
		JSlider sldTransparency = transparencySliderView.getSldTransparency();
		Integer transparencyValue = sldTransparency.getValue();
		TransparencySliderService.changeTransparencyFactor(transparencyValue);
		JTextField txfTransparencyShow = transparencySliderView.getTxfTransparencyShow();
		txfTransparencyShow.setText(transparencyValue.toString());
	}

	void updateTransparencyEnabled() {
		transparencySliderView.setTransparencyEnabled(CanvasService.isForegroundEnabled());
	}
}
