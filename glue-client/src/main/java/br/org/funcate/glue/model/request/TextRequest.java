package br.org.funcate.glue.model.request;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.ContextToLabelConfig;
import br.org.funcate.glue.model.LabelConfig;
import br.org.funcate.glue.model.LoadingStatusService;
import br.org.funcate.glue.model.ThemeToPlot;
import br.org.funcate.glue.model.ThemeVisual;
import br.org.funcate.glue.model.ThemesService;
import br.org.funcate.glue.model.ViewToPlot;
import br.org.funcate.glue.model.canvas.BufferEnum;
import br.org.funcate.glue.model.canvas.CanvasGraphicsBuffer;
import br.org.funcate.glue.model.canvas.CanvasService;
import br.org.funcate.glue.model.canvas.CanvasState;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.service.TerraJavaClient;

public class TextRequest extends Thread {
	
	private static ContextToLabelConfig contextToLabelConfig;
	public static byte[] imgByteArray;
	
	public void run() {
		LoadingStatusService.addThreadCount();
		CanvasGraphicsBuffer bufferManager = AppSingleton.getInstance()
				.getCanvasState().getCanvasGraphicsBuffer();
		CanvasService.cleanBuffer(BufferEnum.TEXT);
		BufferedImage textBuffer = bufferManager.getBuffer(BufferEnum.TEXT);
		Graphics textGraphics = textBuffer.createGraphics();
		//plotText();
		if (imgByteArray != null) {
			ImageIcon image = new ImageIcon(imgByteArray);
			textGraphics.drawImage(image.getImage(), 0, 0, null);
		}
		bufferManager.setEditionX(0);
		bufferManager.setEditionY(0);
		bufferManager.notifyObservers();
		LoadingStatusService.removeThreadCount();
	}

	
	public static ContextToLabelConfig getContextToLabelConfig() {
		return contextToLabelConfig;
	}
	
	public static void setContextToLabelConfig(ContextToLabelConfig contextToLabelConfig) {
		TextRequest.contextToLabelConfig = contextToLabelConfig;
	}

	private void plotText() {
		CanvasState canvasState = AppSingleton.getInstance().getCanvasState();
		int width = canvasState.getCanvasWidth();
		int height = canvasState.getCanvasHeight();
		TerraJavaClient services = AppSingleton.getInstance().getServices();
		
		if(getContextToLabelConfig() == null){
			setDefaultContext();
		}
		
		String themeName = getContextToLabelConfig().getThemeName();
		
		ThemeToPlot themeToPlot = ThemesService.getThemeToPlotByName(
				canvasState.getView().getViewToPlot().getThemes(), themeName);
		byte[] image = null;
		
		if (themeToPlot != null && themeToPlot.isLabel()==false) {

			ViewToPlot v = new ViewToPlot();
			v.setName(canvasState.getView().getName());
			v.setCanvasBackgroundColor(canvasState.getBackgroundColor());
			v.setProjection(canvasState.getBackground().getProjection());
			ThemeToPlot tp = new ThemeToPlot();
			tp.setName(themeName);
			
			v.getThemes().add(tp);
			
			tp.setLabelConfig(getContextToLabelConfig().getLabelConfig());
			try {
				image = services.drawThemeText(v, canvasState.getBox(), width, height, 0, false, 99);
			} catch (GlueServerException e) {
				e.printStackTrace();
			}	
		}	
		TextRequest.imgByteArray = image;
	}
	
	public void setDefaultContext(){
		ContextToLabelConfig context = new ContextToLabelConfig();
		LabelConfig labelConfig = new LabelConfig();
		labelConfig.setDescTextPriorityOrder(true);
		labelConfig.setDetectConflict(true);
		labelConfig.setMinCollisionTol(0);
		labelConfig.setField("nome");
		labelConfig.setPriorityField("");
		labelConfig.setUrbanMode(false);
		ThemeVisual visual = new ThemeVisual();
		visual.setBlue(0);
		visual.setGreen(0);
		visual.setRed(0);
		visual.setWidthCountour(8);
		visual.setFontName("ariblk");
		labelConfig.setVisual(visual);
		context.setThemeName("arruamento");
		context.setLabelConfig(labelConfig);
		
		TextRequest.setContextToLabelConfig(context);
	}

}
