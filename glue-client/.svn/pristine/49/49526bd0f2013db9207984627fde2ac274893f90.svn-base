package br.org.funcate.glue.model.canvas;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.model.toolbar.ToolEnum;
import br.org.funcate.glue.tool.TileThreadTimer;
import br.org.funcate.glue.utilities.BrowserLauncher;
import br.org.funcate.glue.utilities.PropertiesReader;
import br.org.funcate.glue.view.ImageIconLoader;
import br.org.funcate.glue.view.LocalOptionPane;

public abstract class ToolRedirectionService {

	public static void executeMouseMovedEvent(int mouseClickXPosition, int mouseClickYPosition) {
		if (CanvasService.isGoogleActive() && mouseClickXPosition > AppSingleton.getInstance().getCanvasState().getCanvasWidth() - 80
				&& mouseClickYPosition > AppSingleton.getInstance().getCanvasState().getCanvasHeight() - 17) {

			CanvasService.setLinkCursor(true);
		} else {
			CanvasService.setLinkCursor(false);
		}
	}

	public static void executeMouseWheelEvent(int x, int y, int wheelRotation) throws GlueServerException {
		new TileThreadTimer().start();
		ZoomToolService.executeMouseWheelEvent(x, y, wheelRotation);
	}

	public static void executeMouseClickedEvent(int mouseClickXPosition, int mouseClickYPosition, int clickCount, int button)
			throws GlueServerException {

		Mediator mediator = AppSingleton.getInstance().getMediator();
		ToolEnum currentTool = mediator.getSelectedTool();

		if (CanvasService.isGoogleActive()) {
			if (mouseClickXPosition > AppSingleton.getInstance().getCanvasState().getCanvasWidth() - 80
					&& mouseClickYPosition > AppSingleton.getInstance().getCanvasState().getCanvasHeight() - 17) {
				BrowserLauncher.getInstance().launchBrowser(PropertiesReader.getProperty("google.doc.terms"));
				return;
			}
		}

		if (currentTool != ToolEnum.PAN || clickCount != 2) {
			return;
		}

		if (button == 1) {
			if (!mediator.isToolEnabled(ToolEnum.ZOOMIN)) {
				return;
			} else {
				ZoomToolService.zoomIn(mouseClickXPosition, mouseClickYPosition);
			}
		}

		if (button == 3) {
			if (!mediator.isToolEnabled(ToolEnum.ZOOMOUT)) {
				return;
			} else {
				ZoomToolService.zoomOut(mouseClickXPosition, mouseClickYPosition, true);
			}
		}
	}

	public static void saveCurrentImage(BufferedImage buffer) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "jpg");
		FileNameExtensionFilter filter1 = new FileNameExtensionFilter("gif", "gif");
		FileNameExtensionFilter filter2 = new FileNameExtensionFilter("png", "png");
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.addChoosableFileFilter(filter);
		chooser.addChoosableFileFilter(filter1);
		chooser.addChoosableFileFilter(filter2);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		chooser.setDialogTitle("Salvar imagem atual");
		chooser.setFileHidingEnabled(true);
		int returnVal = chooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String name = chooser.getSelectedFile().getName();
			boolean ok = false;
			for ( FileFilter fileFilter : chooser.getChoosableFileFilters()){
				if (name.endsWith("."+fileFilter.getDescription())){
					ok = true;
					chooser.setFileFilter(fileFilter);
					break;
				}
			}
			if (!ok) name = name.concat("."+chooser.getFileFilter().getDescription());
			File arquivo = new File(chooser.getCurrentDirectory() + File.separator + name);
			if (arquivo.exists()) {
				int confirm = JOptionPane.showConfirmDialog(null, "O arquivo selecionado já existe no diretório, deseja sobrescrevê-lo?",
						"Arquivo existente", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CLOSED_OPTION) {
					return;
				}
			}
			try {
				ImageIO.write(buffer, chooser.getFileFilter().getDescription(), arquivo);
				LocalOptionPane.getInstance("Imagem exportado com sucesso!", "Glue - Exportar");
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Um erro inesperado ocorreu", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

	}

	public static Cursor getCursor() {
		Mediator mediator = AppSingleton.getInstance().getMediator();
		Cursor cursor = mediator.getCurrentTool().getCursor();
		ToolEnum selectedTool = mediator.getSelectedTool();
		if (cursor == null) {
			return new Cursor(Cursor.DEFAULT_CURSOR);
		}
		if (selectedTool != null
				&& (selectedTool.equals(ToolEnum.ATRIBS) || selectedTool.equals(ToolEnum.GOOGLE)
						|| selectedTool.equals(ToolEnum.HELPONLINE) || selectedTool.equals(ToolEnum.LINKS)
						|| selectedTool.equals(ToolEnum.PHOTOLOCATION) || selectedTool.equals(ToolEnum.TERRALIB) || selectedTool
						.equals(ToolEnum.WMS))) {
			return new Cursor(Cursor.DEFAULT_CURSOR);
		}
		return cursor;
	}

	public static Cursor createCursor(Image img, String name, int pointerLocation) {
		return Toolkit.getDefaultToolkit().createCustomCursor(img, new Point(pointerLocation, pointerLocation), name);
	}

	public static boolean isPanTool() {
		Mediator mediator = AppSingleton.getInstance().getMediator();
		ToolEnum currentTool = mediator.getSelectedTool();

		return currentTool == ToolEnum.PAN;
	}

	public static Cursor getPressingHandCursor() {
		ImageIcon imgIconPan = ImageIconLoader.createImageIcon("br/org/funcate/glue/image/cursor0302.gif", ToolRedirectionService.class);
		return createCursor(imgIconPan.getImage(), "Mão Fechada", 10);
	}

	public static Cursor getNormalPanHandCursor() {
		ImageIcon imgIconPan = ImageIconLoader.createImageIcon("br/org/funcate/glue/image/cursor03.gif", ToolRedirectionService.class);
		return createCursor(imgIconPan.getImage(), "Mão", 10);
	}
}
