package br.org.funcate.glue.model;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.canvas.CanvasState;
import br.org.funcate.glue.model.canvas.HighlightObjects;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.model.request.BDRequest;
import br.org.funcate.glue.service.TerraJavaClient;
import br.org.funcate.glue.view.ImageIconLoader;
import br.org.funcate.glue.view.JOutlookBar;

public class LegendService {

	/**
	 * Updates the Legend on {@link JOutlookBar} component.
	 */
	public static void updateLegend() {
		AppSingleton singleton = AppSingleton.getInstance();

		Mediator mediator = singleton.getMediator();
		JPanel panelAccord = (JPanel) mediator.getSidebar("Legenda");
		JScrollPane scroll = (JScrollPane) panelAccord.getComponent(0);
		JLabel labelLegend = (JLabel) scroll.getViewport().getView();

		Integer imageType = 0;
		Boolean canvasOpaque = true;
		Integer quality = 75;
		int width = 300; // largura da imagem da legenda

		View view = mediator.getCurrentView().getView();

		ViewToPlot viewToPlot = view.getViewToPlot();

		if (BDRequest.isInvalidThemes(viewToPlot)) {
			labelLegend.setIcon(new ImageIcon());
			return;
		}

		TerraJavaClient services = singleton.getServices();

		ContextToGroupMap contextToGroupMap = singleton.getGroupMapParameters();

		CanvasState canvasState = singleton.getCanvasState();
		viewToPlot.setCanvasBackgroundColor(canvasState.getBackgroundColor());
		List<HighlightObjects> highlightObjects = canvasState
				.getHighlightObjects();

		if (!highlightObjects.isEmpty()) {
			List<ThemeToPlot> themes = viewToPlot.getThemes();

			for (ThemeToPlot themeToPlot : themes) {
				for (HighlightObjects highlight : highlightObjects) {
					if (themeToPlot.getName().equals(highlight.getThemeName())) {
						themeToPlot.getObjectsToDraw().addAll(
								highlight.getObjects());
					}
				}
			}
		}

		ThemeVisual visualText = new ThemeVisual(128);
		visualText.setRed(0);
		visualText.setGreen(0);
		visualText.setBlue(0);
		// TODO: Andre Carvalho em 17/03/2012
		// Colocar fontes no server e criar um metodo no serviço para recuperar
		// os nomes de fontes.
		// Pois quem desenha os textos tanto na legenda quanto no mapa eh a
		// TeGDCanvas, e portanto os arquivos de fonte devem estar lah.
		visualText.setFontName("arial");
		visualText.setStyle(1);
		visualText.setTransparency(0);
		visualText.setCountourColorRed(255);
		visualText.setCountourColorGreen(255);
		visualText.setCountourColorBlue(255);
		visualText.setWidthCountour(7);
		visualText.setCountourTransparency(0);

		byte[] legend = null;

		try {
			legend = services.plotLegendViewThreadSafe(viewToPlot, visualText,
					width, imageType, canvasOpaque, quality, contextToGroupMap);
		} catch (GlueServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ImageIcon legendImage = null;
			if (legend == null) {
				String imagePath = "br/org/funcate/glue/image/warning.png";
				legendImage = ImageIconLoader.createImageIcon(imagePath,
						LoadingStatusService.class);
			} else {
				legendImage = new ImageIcon(legend);
			}
			labelLegend.setBounds(0, 0, legendImage.getIconWidth(),
					legendImage.getIconHeight());
			labelLegend.setIcon(legendImage);

			// redesenho do outlookBar para remover efeito estranho de lixo no
			// painel de legenda
			mediator.repaintOutlookBar();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
