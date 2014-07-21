package br.org.funcate.glue.controller;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.request.GoogleEnum;
import br.org.funcate.glue.model.toolbar.ToolEnum;
import br.org.funcate.glue.utilities.BrowserLauncher;
import br.org.funcate.glue.view.InterfaceGoogle;
import br.org.funcate.glue.view.LocalOptionPane;

public class InterfaceGoogleController {

	private InterfaceGoogle interfaceGoogle;

	public InterfaceGoogleController(InterfaceGoogle google) {
		this.interfaceGoogle = google;
	}

	private String createLinkText(String text, String cor) {
		text = "<html><font color=\"#" + cor + "\"><u>" + text + "</u></font></html>";
		return text;
	}

	/** Method to restore default borders for all labels */
	private void clearAllBorder() {
		this.interfaceGoogle.getLblMapGoogle().setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.interfaceGoogle.getLblSatGoogle().setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.interfaceGoogle.getLblHybridGoogle().setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.interfaceGoogle.getLblTerGoogle().setBorder(BorderFactory.createLineBorder(Color.black, 1));

		this.setAllDisable();
	}

	/** Method to set disable all labels */
	private void setAllDisable() {
		this.interfaceGoogle.setHybridSelected(false);
		this.interfaceGoogle.setMapSelected(false);
		this.interfaceGoogle.setSatSelected(false);
		this.interfaceGoogle.setTerSelected(false);
	}

	/**
	 * Method to put a combined border to represent a selected label
	 * 
	 * @param label
	 */

	private void putBorder(JLabel label) {
		clearAllBorder();

		//setAllDisable();

		label.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black, 1),
				BorderFactory.createLineBorder(new Color(198, 226, 250), 8)));
	}

// TODO Remove unused code found by UCDetector
// 	public void enableGoogle(boolean visible) {
// 		this.interfaceGoogle.setBounds(0, 0, 355, 465);
// 		this.interfaceGoogle.setVisible(visible);
// 	}

	public void panelCancel() {
		this.clearAllBorder();
		this.interfaceGoogle.dispose();
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		mediator.setSelectedTool(ToolEnum.GOOGLE);
	}

	public void verifyMapGoogle() {
		if (this.interfaceGoogle.isMapSelected() == false) {
			this.clearAllBorder();
			this.putBorder(this.interfaceGoogle.getLblMapGoogle());

			this.interfaceGoogle.setMapSelected(true);

			this.interfaceGoogle.setOneSelected(true);
			this.interfaceGoogle.setOkPressed(true);
			this.interfaceGoogle.getLblGoogleFinalizar().setText(createLinkText("Finalizar", "000080"));
		} else {
			this.clearAllBorder();

			this.interfaceGoogle.setOneSelected(false);
			this.interfaceGoogle.setOkPressed(false);
			this.interfaceGoogle.getLblGoogleFinalizar().setText(createLinkText("Finalizar", "CFCFCF"));
		}
	}

	public void verifySatGoogle() {
		if (this.interfaceGoogle.isSatSelected() == false) {
			this.clearAllBorder();
			this.putBorder(this.interfaceGoogle.getLblSatGoogle());

			this.interfaceGoogle.setSatSelected(true);

			this.interfaceGoogle.setOneSelected(true);
			this.interfaceGoogle.setOkPressed(true);
			this.interfaceGoogle.getLblGoogleFinalizar().setText(createLinkText("Finalizar", "000080"));
		} else {
			this.clearAllBorder();

			this.interfaceGoogle.setOneSelected(false);
			this.interfaceGoogle.setOkPressed(false);
			this.interfaceGoogle.getLblGoogleFinalizar().setText(createLinkText("Finalizar", "CFCFCF"));
		}
	}

	public void verifyTerGoogle() {
		if (this.interfaceGoogle.isTerSelected() == false) {
			this.clearAllBorder();
			this.putBorder(this.interfaceGoogle.getLblTerGoogle());

			this.interfaceGoogle.setTerSelected(true);

			this.interfaceGoogle.setOneSelected(true);
			this.interfaceGoogle.setOkPressed(true);
			this.interfaceGoogle.getLblGoogleFinalizar().setText(createLinkText("Finalizar", "000080"));
		} else {
			this.clearAllBorder();

			this.interfaceGoogle.setOneSelected(false);
			this.interfaceGoogle.setOkPressed(false);
			this.interfaceGoogle.getLblGoogleFinalizar().setText(createLinkText("Finalizar", "CFCFCF"));
		}
	}

	public void verifyHybridGoogle() {
		if (this.interfaceGoogle.isHybridSelected() == false) {
			this.clearAllBorder();
			this.putBorder(this.interfaceGoogle.getLblHybridGoogle());

			this.interfaceGoogle.setHybridSelected(true);

			this.interfaceGoogle.setOneSelected(true);
			this.interfaceGoogle.setOkPressed(true);
			this.interfaceGoogle.getLblGoogleFinalizar().setText(createLinkText("Finalizar", "000080"));
		} else {
			this.clearAllBorder();

			this.interfaceGoogle.setOneSelected(false);
			this.interfaceGoogle.setOkPressed(false);
			this.interfaceGoogle.getLblGoogleFinalizar().setText(createLinkText("Finalizar", "CFCFCF"));
		}
	}

	public void panelUserTerms() {
		BrowserLauncher.getInstance().launchBrowser("http://maps.google.com/intl/pt-BR/help/terms_maps.html");
	}

	public void finalizarGoogle() {
		
		if (this.interfaceGoogle.isMapSelected()) {

			this.interfaceGoogle.setOkPressed(true);

			this.interfaceGoogle.getLblGoogleFinalizar().setText(createLinkText("Finalizar", "CFCFCF"));

			AppSingleton singleton = AppSingleton.getInstance();
			Mediator mediator = singleton.getMediator();
			mediator.setToolBarSource(GoogleEnum.MAP);
			
		}

		if (this.interfaceGoogle.isSatSelected()) {

			this.interfaceGoogle.setOkPressed(true);

			this.interfaceGoogle.getLblGoogleFinalizar().setText(createLinkText("Finalizar", "CFCFCF"));

			//this.interfaceGoogle.setVisible(false);

			AppSingleton singleton = AppSingleton.getInstance();
			Mediator mediator = singleton.getMediator();
			mediator.setToolBarSource(GoogleEnum.SATELLITE);
		}

		if (this.interfaceGoogle.isTerSelected()) {

			this.interfaceGoogle.setOkPressed(true);

			this.interfaceGoogle.getLblGoogleFinalizar().setText(createLinkText("Finalizar", "CFCFCF"));

			//this.interfaceGoogle.setVisible(false);

			AppSingleton singleton = AppSingleton.getInstance();
			Mediator mediator = singleton.getMediator();
			mediator.setToolBarSource(GoogleEnum.TERRAIN);
		}

		if (this.interfaceGoogle.isHybridSelected()) {

			this.interfaceGoogle.setOkPressed(true);

			this.interfaceGoogle.getLblGoogleFinalizar().setText(createLinkText("Finalizar", "CFCFCF"));

			//this.interfaceGoogle.setVisible(false);

			AppSingleton singleton = AppSingleton.getInstance();
			Mediator mediator = singleton.getMediator();
			mediator.setToolBarSource(GoogleEnum.HYBRID);
		}

		if (this.interfaceGoogle.isOkPressed() == false) {
			LocalOptionPane.getInstance("Selecione um tipo e clique em Finalizar.", "Glue - Tipos");
		}
		this.clearAllBorder();
		
		this.interfaceGoogle.dispose();
	}
}
