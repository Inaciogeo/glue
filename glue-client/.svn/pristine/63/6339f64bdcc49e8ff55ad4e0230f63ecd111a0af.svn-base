package br.org.funcate.glue.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.org.funcate.glue.controller.InfoToolController;
import br.org.funcate.glue.view.InfoToolView;

public class InfoToolAdapter implements ActionListener {

	private InfoToolView infoToolView;
	private InfoToolController infoToolController;

	public InfoToolAdapter(InfoToolView infoToolView) {
		this.infoToolView = infoToolView;
		this.infoToolController = new InfoToolController(infoToolView);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == infoToolView.getBtClose()) {
			this.infoToolController.close();
		} else if (e.getSource() == infoToolView.getBtMap()) {
			this.infoToolController.getMap();
		}
	}

}
