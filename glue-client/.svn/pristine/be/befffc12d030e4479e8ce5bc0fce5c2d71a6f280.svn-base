package br.org.funcate.glue.controller;

import javax.swing.JTabbedPane;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.canvas.CanvasService;
import br.org.funcate.glue.model.canvas.HighlightObjectsService;
import br.org.funcate.glue.model.canvas.ThemeAttributesInfoTool;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.tool.PanTool;
import br.org.funcate.glue.view.InfoToolView;
import br.org.funcate.glue.view.ThemeAttributesPanel;

public class InfoToolController {

	private InfoToolView infoToolView;

	public InfoToolController(InfoToolView infoToolView) {
		this.infoToolView = infoToolView;
	}

	public void close() {
		infoToolView.setVisible(false);
		AppSingleton.getInstance().getMediator().setCurrentTool(new PanTool());
		// AppSingleton.getInstance().getInfoClick().setxPosition(0);
		return;
	}

	public void getMap() {
		JTabbedPane themesTabbedPane = infoToolView.getThemesTabbedPane();
		ThemeAttributesPanel themeAttributesPanel = (ThemeAttributesPanel) themesTabbedPane
				.getSelectedComponent();
		ThemeAttributesInfoTool info = themeAttributesPanel.getInfo();
		/*
		 * List<String> list = new ArrayList<String>();
		 * list.add(info.getObjectId());
		 * HighlightObjectsService.addHightlightObjects(info.getThemeName(),
		 * list); CanvasService.draw(true);
		 */
		try {
			HighlightObjectsService.cleanSelection();
		} catch (GlueServerException e1) {
			e1.printStackTrace();
			// TODO AGUARDANDO IMPLEMENTAÇÃO DO EXCEPTION HANDLER
		}
		try {
			CanvasService.selectObject(info.getThemeName(), info.getObjectId());
		} catch (GlueServerException e1) {
			e1.printStackTrace();
			// TODO AGUARDANDO IMPLEMENTAÇÃO DO EXCEPTION HANDLER

		}
	}

}
