package br.org.funcate.glue.model;

import javax.swing.ImageIcon;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.canvas.GeneralTileSchema;
import br.org.funcate.glue.view.FeaturesFeedbackWindow;
import br.org.funcate.glue.view.ImageIconLoader;
import br.org.funcate.glue.view.MainPanel;

public abstract class LoadingStatusService {

	private static int tileRequestThreadCount = 0;
	private static boolean loading = false;

	private static boolean themeChanged = true;

	public static void setThemeChanged(boolean themeChanged) {
		LoadingStatusService.themeChanged = themeChanged;
	}

	public static synchronized void addThreadCount() {
		tileRequestThreadCount++;
		if (!loading) {
			startLoading();
		}
	}

	public static synchronized void removeThreadCount() {
		tileRequestThreadCount--;
		if (tileRequestThreadCount == 0) {
//			if (themeChanged) {
//				themeChanged = false;
				LegendService.updateLegend();
//			}
			stopLoading();
		}
		if (!GeneralTileSchema.isDrawText() && tileRequestThreadCount > 0) {
			GeneralTileSchema.setDrawText(true);
		}
		
	}

	private static void startLoading() {
		loading = true;

		String imagePath;

		if (LookAndFeelService.isNimbusActivated()) {
			imagePath = "br/org/funcate/glue/image/loadingTrueNimbus.gif";
		} else {
			imagePath = "br/org/funcate/glue/image/loadingTrue.gif";
		}

		setImageLoadingStatus(imagePath);
	}

	private static void stopLoading() {
		loading = false;

		String imagePath;

		if (LookAndFeelService.isNimbusActivated()) {
			imagePath = "br/org/funcate/glue/image/loadingFalseNimbus.gif";
		} else {
			imagePath = "br/org/funcate/glue/image/loadingFalse.gif";
		}
		
		setImageLoadingStatus(imagePath);
	}

	private static void setImageLoadingStatus(String imagePath) {
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		ImageIcon img = ImageIconLoader.createImageIcon(imagePath,
				LoadingStatusService.class);
		mediator.setImageLoadingStatus(img);
	}
}
