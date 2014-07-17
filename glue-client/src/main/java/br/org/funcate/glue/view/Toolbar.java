package br.org.funcate.glue.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import br.org.funcate.glue.controller.listener.ToolbarListener;
import java.awt.Color;

/**
 * \class Toolbar Creates One ToolBar. \brief Creates One ToolBar. \author
 * Moraes, Emerson Leite \author Alexsander, Willyan \version 1.0
 */
@SuppressWarnings("serial")
public class Toolbar extends JToolBar {

	/*
	 * ! You need to set boxLayout of the panel with toolBar will be added.
	 * p.setLayout(new BoxLayout(p, BoxLayout.YAXIS));
	 */

	/*
	 * !
	 * 
	 * @name Buttons
	 * 
	 * Buttons to use in ToolBar
	 */
	// @{

	private static JButton paint;

	private JButton rebuild;

	private JButton zoomIn;

	private JButton zoomOut;

	private JButton selectArea;

	private static JButton pan;

	private static JButton distance;

	private static JButton unDo;

	private static JButton reDo;

	private static JButton photoLocation;

	private static JButton info;

	private static JButton links;

	private static JButton atribs;

	private static JButton clean;

	private static JButton pdf;

	private static JButton export;

	private static JButton helpOnline;

	private static JButton openTileRequest;

	private static JButton wms;

	private static JButton terraLib;

	// @}

	/*
	 * !
	 * 
	 * @name Icons
	 * 
	 * Icons of Buttons is setting
	 */

	// @{

	private ImageIcon iPaint = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/bt01.gif", Toolbar.class);
	private ImageIcon iPaintNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newbt01.gif", Toolbar.class);
	private ImageIcon iRebuild = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/bt02.gif", Toolbar.class);
	private ImageIcon iRebuildNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newbt02.gif", Toolbar.class);
	private ImageIcon iZoomIn = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/bt03.gif", Toolbar.class);
	private ImageIcon iZoomInNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newbt03.gif", Toolbar.class);
	private ImageIcon iZoomOut = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/bt04.gif", Toolbar.class);
	private ImageIcon iZoomOutNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newbt04.gif", Toolbar.class);
	private ImageIcon iSelectArea = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/bt05.gif", Toolbar.class);
	private ImageIcon iSelectAreaNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newbt05.gif", Toolbar.class);
	private ImageIcon iPan = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/bt06.gif", Toolbar.class);
	private ImageIcon iPanNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newbt06.gif", Toolbar.class);
	private ImageIcon iDistance = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/bt07.gif", Toolbar.class);
	private ImageIcon iDistanceNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newbt07.gif", Toolbar.class);
	private ImageIcon iPhotoLocation = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/bt08.gif", Toolbar.class);
	private ImageIcon iPhotoLocationNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newbt08.gif", Toolbar.class);
	private ImageIcon iInfo = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/bt09.gif", Toolbar.class);
	private ImageIcon iInfoNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newbt09.gif", Toolbar.class);
	private ImageIcon iLinks = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/bt10.gif", Toolbar.class);
	private ImageIcon iLinksNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newbt10.gif", Toolbar.class);
	private ImageIcon iAtribs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/bt11.gif", Toolbar.class);
	private ImageIcon iAtribsNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newbt11.gif", Toolbar.class);
	private ImageIcon iClean = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/bt12.gif", Toolbar.class);
	private ImageIcon iCleanNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newbt12.gif", Toolbar.class);
	private ImageIcon iPdf = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/bt13.gif", Toolbar.class);
	private ImageIcon iPdfNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newbt13.gif", Toolbar.class);
	private ImageIcon iExport = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/bt14.gif", Toolbar.class);
	private ImageIcon iExportNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newbt14.gif", Toolbar.class);
	private ImageIcon iHelpOnline = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/bt15.gif", Toolbar.class);
	private ImageIcon iHelpOnlineNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newbt15.gif", Toolbar.class);
	private ImageIcon iArrowr = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/arrow_redo.gif", Toolbar.class);
	private ImageIcon iArrowrNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newarrow_redo.gif", Toolbar.class);
	private ImageIcon iArrowu = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/arrow_undo.gif", Toolbar.class);
	private ImageIcon iArrowuNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newarrow_undo.gif", Toolbar.class);
	private ImageIcon iGoogle = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/OpenStreetMapLogo.png", Toolbar.class);
	private ImageIcon iWms = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/iconeToolWMS.gif", Toolbar.class);
	private ImageIcon iGoogleNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/OpenStreetMapLogo.png", Toolbar.class);
	private ImageIcon iWmsNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newiconeToolWMS.gif", Toolbar.class);
	private ImageIcon iTerraLib = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/terraview.gif", Toolbar.class);
	private ImageIcon iTerraLibNs = ImageIconLoader.createImageIcon(
			"br/org/funcate/glue/image/newterraview.gif", Toolbar.class);

	// @}

	private boolean googleSelected, wmsSelected, terraLibSelected;

	// ! create ImageIcon
	/*
	 * ! \param path directory of image
	 */

	public boolean isTerraLibSelected() {
		return terraLibSelected;
	}

	// ! create BufferedImageIcon
	/*
	 * ! \param s directory of image
	 */

	// public static BufferedImage getImg(URL path) throws IOException {
	// BufferedImage im = ImageIO.read(path);
	// return im;
	// }

	// ! set Default Icons

	// public void iconDefault() {
	// paint.setIcon(iPaint);
	// rebuild.setIcon(iRebuild);
	// zoomIn.setIcon(iZoomIn);
	// zoomOut.setIcon(iZoomOut);
	// selectArea.setIcon(iSelectArea);
	// pan.setIcon(iPan);
	// distance.setIcon(iDistance);
	// Mediator mediator = AppSingleton.getInstance().getMediator();
	// mediator.clearToolGraphics();
	// photoLocation.setIcon(iPhotoLocation);
	// info.setIcon(iInfo);
	// links.setIcon(iLinks);
	// atribs.setIcon(iAtribs);// TODO Auto-generated method stub
	// clean.setIcon(iClean);
	// pdf.setIcon(iPdf);
	// export.setIcon(iExport);
	// helpOnline.setIcon(iHelpOnline);
	// unDo.setIcon(iArrowu);
	// reDo.setIcon(iArrowr);
	// }

//	public void zoomInSelect() {
//		zoomIn.setIcon(iZoomInNs);
//	}

//	public void zoomOutSelect() {
//		zoomOut.setIcon(iZoomOutNs);
//	}

//	public void checkUpdate(boolean updated) {
//
//		if (updated) {
//			this.setEnabled(false);
//		} else {
//			this.setEnabled(true);
//		}
//	}

	// ! Constructor

	public Toolbar() {
		setBackground(new Color(255, 255, 255));
		setFloatable(false);

		terraLib = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/newterraview.gif", Toolbar.class));
		terraLibSelected = true;
		terraLib.setToolTipText("Habilita/Desabilita Árvore");
		terraLib.setVisible(false);
		paint = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/bt01.gif", Toolbar.class));
		// paint = new JButton(new ImageIcon(getImg("bt01.gif")));
		paint.setToolTipText("Desenhar");

		rebuild = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/bt02.gif", Toolbar.class));
		rebuild.setToolTipText("Recompor");

		zoomIn = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/bt03.gif", Toolbar.class));
		zoomIn.setToolTipText("Zoom Mais");

		zoomOut = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/bt04.gif", Toolbar.class));
		zoomOut.setToolTipText("Zoom Menos");

		selectArea = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/bt05.gif", Toolbar.class));
		selectArea.setToolTipText("Zoom Área");

		pan = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/bt06.gif", Toolbar.class));
		pan.setToolTipText("Pan");

		distance = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/bt07.gif", Toolbar.class));
		distance.setToolTipText("Calcular Distância");

		unDo = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/arrow_undo.gif", Toolbar.class));
		unDo.setToolTipText("Voltar Última Visualização");

		reDo = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/arrow_redo.gif", Toolbar.class));
		reDo.setToolTipText("Avançar Visualização");

		photoLocation = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/bt08.gif", Toolbar.class));
		photoLocation.setVisible(false);
		photoLocation.setToolTipText("Fotos Lotes");

		info = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/bt09.gif", Toolbar.class));
		info.setToolTipText("Informação Objeto");

		links = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/bt10.gif", Toolbar.class));
		links.setVisible(false);
		links.setToolTipText("Habilitar/Desabilitar Links");

		atribs = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/bt11.gif", Toolbar.class));
		atribs.setVisible(false);
		atribs.setToolTipText("Pesquisar");

		clean = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/bt12.gif", Toolbar.class));
		clean.setToolTipText("Limpar Seleção");

		pdf = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/bt13.gif", Toolbar.class));
		pdf.setVisible(false);
		pdf.setToolTipText("Gerar Relatório");

		export = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/bt14.gif", Toolbar.class));
		export.setVisible(false);
		export.setToolTipText("Exportar");

		helpOnline = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/bt15.gif", Toolbar.class));
		helpOnline.setVisible(false);
		helpOnline.setToolTipText("Ajuda");

		openTileRequest = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/OpenStreetMapLogo.png", Toolbar.class));
		openTileRequest.setBorderPainted(false);
		openTileRequest.setFocusPainted(false);
		openTileRequest.setToolTipText("Ferramenta de requisi\u00E7\u00E3o de mapas remotos ");

		wms = new JButton(ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/iconeToolWMS.gif", Toolbar.class));
		wms.setVisible(false);
		wms.setToolTipText("Criar uma vizualização wms");

		ToolbarListener listener = new ToolbarListener(this);
		this.addListener(listener);

		this.add(terraLib);
		this.add(openTileRequest);
		this.add(wms);
		this.add(paint);
		this.add(rebuild);
		this.add(zoomIn);
		this.add(zoomOut);
		this.add(selectArea);
		this.add(pan);
		this.add(distance);
		this.add(unDo);
		this.add(reDo);

		this.add(photoLocation);
		this.add(info);
		this.add(links);
		this.add(atribs);
		this.add(clean);
		this.add(pdf);
		this.add(export);

		this.add(helpOnline);

		/*
		 * ! Sets the alignment of the toolbar with value between 0 and 1
		 * representing the horizontal position of the end of the toolbar
		 */

		setAlignmentX((float) 0.95);

	}

	private void addListener(ToolbarListener listener) {
		terraLib.addActionListener(listener);
		paint.addActionListener(listener);
		rebuild.addActionListener(listener);
		zoomIn.addActionListener(listener);
		zoomOut.addActionListener(listener);
		selectArea.addActionListener(listener);
		pan.addActionListener(listener);
		distance.addActionListener(listener);
		unDo.addActionListener(listener);
		reDo.addActionListener(listener);
		photoLocation.addActionListener(listener);
		info.addActionListener(listener);
		links.addActionListener(listener);
		atribs.addActionListener(listener);
		clean.addActionListener(listener);
		pdf.addActionListener(listener);
		export.addActionListener(listener);
		helpOnline.addActionListener(listener);
		openTileRequest.addActionListener(listener);
		wms.addActionListener(listener);
		
	}

	/**
	 * Disable tools.
	 */
//	public void disableToolbar() {
//		rebuild.setEnabled(false);
//		zoomIn.setEnabled(false);
//		zoomOut.setEnabled(false);
//		selectArea.setEnabled(false);
//		pan.setEnabled(false);
//		distance.setEnabled(false);
//		unDo.setEnabled(false);
//		reDo.setEnabled(false);
//		photoLocation.setEnabled(false);
//		info.setEnabled(false);
//		links.setEnabled(false);
//		atribs.setEnabled(false);
//		clean.setEnabled(false);
//		pdf.setEnabled(false);
//		export.setEnabled(false);
//		helpOnline.setEnabled(false);
//	}

	/**
	 * Disable tools.
	 */
//	public void enableToolbar() {
//		paint.setEnabled(true);
//		rebuild.setEnabled(true);
//		zoomIn.setEnabled(true);
//		zoomOut.setEnabled(true);
//		selectArea.setEnabled(true);
//		pan.setEnabled(true);
//		distance.setEnabled(true);
//		photoLocation.setEnabled(true);
//		info.setEnabled(true);
//		links.setEnabled(true);
//		atribs.setEnabled(true);
//		clean.setEnabled(true);
//		pdf.setEnabled(true);
//		export.setEnabled(true);
//		helpOnline.setEnabled(true);
//	}

	/**
	 * Sets Draw enable true(enabled) or false (disabled)
	 * 
	 * @param pEnable
	 */
	public void setDrawEnabled(boolean pEnable) {
		paint.setEnabled(pEnable);
	}

	/**
	 * Sets UnDo enable true(enabled) or false (disabled).
	 * 
	 * @param pEnable
	 */
	public void setUndoEnabled(boolean pEnable) {
		unDo.setEnabled(pEnable);
	}

	/**
	 * Sets UnDo enable true(enabled) or false (disabled).
	 * 
	 * @param pEnable
	 */
	public void setRedoEnabled(boolean pEnable) {
		reDo.setEnabled(pEnable);
	}

	/**
	 * Disable Google Button
	 */
//	public void disableGoogle() {
//		google.setEnabled(false);
//	}

	/**
	 * Disable WMS Button.
	 */
//	public void disableWMS() {
//		wms.setEnabled(false);
//	}

	/**
	 * Enable Google Button
	 */
//	public void enableGoogle() {
//		google.setEnabled(true);
//	}

	/**
	 * Enable WMS Button
	 */
//	public void enableWMS() {
//		wms.setEnabled(true);
//	}

	public void setZoomInEnabled(boolean isEnabled) {
		zoomIn.setEnabled(isEnabled);
	}

	public void setZoomOutEnabled(boolean isEnabled) {
		zoomOut.setEnabled(isEnabled);
	}

	public boolean isZoomInEnabled() {
		return zoomIn.isEnabled();
	}

	public boolean isZoomOutEnabled() {
		return zoomOut.isEnabled();
	}

	public void setZoomAreaEnabled(boolean isEnabled) {
		selectArea.setEnabled(isEnabled);
	}

	public boolean isZoomAreaEnabled() {
		return selectArea.isEnabled();
	}

	public JButton getPaint() {
		return paint;
	}

	public void setPaint(JButton paint) {
		Toolbar.paint = paint;
	}

	public JButton getRebuild() {
		return rebuild;
	}

	public void setRebuild(JButton rebuild) {
		this.rebuild = rebuild;
	}

	public JButton getZoomIn() {
		return zoomIn;
	}

	public void setZoomIn(JButton zoomIn) {
		this.zoomIn = zoomIn;
	}

	public JButton getZoomOut() {
		return zoomOut;
	}

	public void setZoomOut(JButton zoomOut) {
		this.zoomOut = zoomOut;
	}

	public JButton getSelectArea() {
		return selectArea;
	}

	public void setSelectArea(JButton selectArea) {
		this.selectArea = selectArea;
	}

	public JButton getPan() {
		return pan;
	}

	public void setPan(JButton pan) {
		Toolbar.pan = pan;
	}

	public JButton getDistance() {
		return distance;
	}

	public void setDistance(JButton distance) {
		Toolbar.distance = distance;
	}

	public JButton getUnDo() {
		return unDo;
	}

	public void setUnDo(JButton unDo) {
		Toolbar.unDo = unDo;
	}

	public JButton getReDo() {
		return reDo;
	}

	public void setReDo(JButton reDo) {
		Toolbar.reDo = reDo;
	}

	public JButton getPhotoLocation() {
		return photoLocation;
	}

	public void setPhotoLocation(JButton photoLocation) {
		Toolbar.photoLocation = photoLocation;
	}

	public JButton getInfo() {
		return info;
	}

	public void setInfo(JButton info) {
		Toolbar.info = info;
	}

	public JButton getLinks() {
		return links;
	}

	public void setLinks(JButton links) {
		Toolbar.links = links;
	}

	public JButton getAtribs() {
		return atribs;
	}

	public void setAtribs(JButton atribs) {
		Toolbar.atribs = atribs;
	}

	public JButton getClean() {
		return clean;
	}

	public void setClean(JButton clean) {
		Toolbar.clean = clean;
	}

	public JButton getPdf() {
		return pdf;
	}

	public void setPdf(JButton pdf) {
		Toolbar.pdf = pdf;
	}

	public JButton getExport() {
		return export;
	}

	public void setExport(JButton export) {
		Toolbar.export = export;
	}

	public JButton getHelpOnline() {
		return helpOnline;
	}

	public void setHelpOnline(JButton helpOnline) {
		Toolbar.helpOnline = helpOnline;
	}

	public static JButton getBtnTileRequest() {
		return openTileRequest;
	}

	public void setBtnTileRequest(JButton btnTile) {
		Toolbar.openTileRequest = btnTile;
	}

	public static JButton getWms() {
		return wms;
	}

	public void setWms(JButton wms) {
		Toolbar.wms = wms;
	}

	public JButton getTerraLib() {
		return terraLib;
	}

	public void setTerraLib(JButton terraLib) {
		Toolbar.terraLib = terraLib;
	}
	
	public ImageIcon getiPaint() {
		return iPaint;
	}

	public ImageIcon getiPaintNs() {
		return iPaintNs;
	}

	public ImageIcon getiRebuild() {
		return iRebuild;
	}

	public ImageIcon getiRebuildNs() {
		return iRebuildNs;
	}

	public ImageIcon getiZoomIn() {
		return iZoomIn;
	}

	public ImageIcon getiZoomInNs() {
		return iZoomInNs;
	}

	public ImageIcon getiZoomOut() {
		return iZoomOut;
	}

	public ImageIcon getiZoomOutNs() {
		return iZoomOutNs;
	}

	public ImageIcon getiSelectArea() {
		return iSelectArea;
	}

	public ImageIcon getiSelectAreaNs() {
		return iSelectAreaNs;
	}

	public ImageIcon getiPan() {
		return iPan;
	}

	public ImageIcon getiPanNs() {
		return iPanNs;
	}

	public ImageIcon getiDistance() {
		return iDistance;
	}

	public ImageIcon getiDistanceNs() {
		return iDistanceNs;
	}

	public ImageIcon getiPhotoLocation() {
		return iPhotoLocation;
	}

	public ImageIcon getiPhotoLocationNs() {
		return iPhotoLocationNs;
	}

	public ImageIcon getiInfo() {
		return iInfo;
	}

	public ImageIcon getiInfoNs() {
		return iInfoNs;
	}

	public ImageIcon getiLinks() {
		return iLinks;
	}

	public ImageIcon getiLinksNs() {
		return iLinksNs;
	}

	public ImageIcon getiAtribs() {
		return iAtribs;
	}

	public ImageIcon getiAtribsNs() {
		return iAtribsNs;
	}

	public ImageIcon getiClean() {
		return iClean;
	}

	public ImageIcon getiCleanNs() {
		return iCleanNs;
	}

	public ImageIcon getiPdf() {
		return iPdf;
	}

	public ImageIcon getiPdfNs() {
		return iPdfNs;
	}

	public ImageIcon getiExport() {
		return iExport;
	}

	public ImageIcon getiExportNs() {
		return iExportNs;
	}

	public ImageIcon getiHelpOnline() {
		return iHelpOnline;
	}

	public ImageIcon getiHelpOnlineNs() {
		return iHelpOnlineNs;
	}

	public ImageIcon getiArrowr() {
		return iArrowr;
	}

	public ImageIcon getiArrowrNs() {
		return iArrowrNs;
	}

	public ImageIcon getiArrowu() {
		return iArrowu;
	}

	public ImageIcon getiArrowuNs() {
		return iArrowuNs;
	}

	public ImageIcon getiGoogle() {
		return iGoogle;
	}

	public ImageIcon getiWms() {
		return iWms;
	}

	public ImageIcon getiGoogleNs() {
		return iGoogleNs;
	}

	public ImageIcon getiWmsNs() {
		return iWmsNs;
	}

	public ImageIcon getiTerraLib() {
		return iTerraLib;
	}

	public ImageIcon getiTerraLibNs() {
		return iTerraLibNs;
	}

	public boolean isWmsSelected() {
		return wmsSelected;
	}

	public void setWmsSelected(boolean wmsSelected) {
		this.wmsSelected = wmsSelected;
	}

	public void setTerraLibSelected(boolean terraLibSelected) {
		this.terraLibSelected = terraLibSelected;
	}

	public boolean isGoogleSelected() {
		return googleSelected;
	}

	public void setGoogleSelected(boolean googleSelected) {
		this.googleSelected = googleSelected;
	}

}
