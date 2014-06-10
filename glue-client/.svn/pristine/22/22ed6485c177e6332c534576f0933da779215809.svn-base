package br.org.funcate.glue.controller.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import br.org.funcate.glue.controller.LabelCanvasExpanderController;
import br.org.funcate.glue.view.MainPanel;

public class LabelCanvasExpanderAdapter extends MouseAdapter {

	private LabelCanvasExpanderController controller;
	
	public LabelCanvasExpanderAdapter(JLabel label, MainPanel mainPanel) {
		controller = new LabelCanvasExpanderController(label, mainPanel);
		
	}

	public void mouseReleased(MouseEvent e) {
		controller.executeMouseReleased();
		
	}
}
