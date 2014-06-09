package br.org.funcate.glue.controller;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.LabelExpandCanvasService;
import br.org.funcate.glue.model.canvas.CanvasService;
import br.org.funcate.glue.view.MainPanel;

public class LabelCanvasExpanderController {

	private JLabel labelExpander;
	MainPanel mainPanel;

	public LabelCanvasExpanderController(JLabel label, MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		this.labelExpander = label;
		ImageIcon imageIcon = LabelExpandCanvasService
				.getImageIconLabelExpanded(false);
		label.setIcon(imageIcon);
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		mediator.setLabelCanvasExpanderController(this);
	}

	public void setExpanderButtonVisible(boolean b) {
		boolean expandedCanvas = CanvasService.isExpandedCanvas();
		resetLabelExpand(expandedCanvas, mainPanel);
		labelExpander.setVisible(b);
	}

	public void executeMouseReleased() {
		boolean expandedCanvas = CanvasService.isExpandedCanvas();

		expandedCanvas = !expandedCanvas;

		Mediator mediator = AppSingleton.getInstance().getMediator();
		mediator.expandedCanvas(expandedCanvas);

		resetLabelExpand(expandedCanvas, mainPanel);
	}

	private void resetLabelExpand(boolean expandedCanvas, MainPanel mainPanel) {
		ImageIcon imageIcon = LabelExpandCanvasService
				.getImageIconLabelExpanded(expandedCanvas);

		labelExpander.setIcon(imageIcon);

		int[] bounds = LabelExpandCanvasService.getBoundsForLabelExpanded(
				labelExpander.getLocation().x, expandedCanvas);

		labelExpander.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
		mainPanel.getCanvas().setBounds(bounds[0], bounds[1],
				mainPanel.getCanvas().getWidth(),
				mainPanel.getCanvas().getHeight());
		mainPanel.getLblDragCanvas()
		.setBounds(mainPanel.getCanvas().getX(),70, 4, 1100);
	}
}
