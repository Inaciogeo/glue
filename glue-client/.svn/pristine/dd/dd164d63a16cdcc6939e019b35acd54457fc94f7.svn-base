package br.org.funcate.glue.controller.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import br.org.funcate.glue.controller.InterfaceGoogleController;
import br.org.funcate.glue.view.InterfaceGoogle;

public class InterfaceGoogleListener extends MouseAdapter {

	private InterfaceGoogle interfaceGoogle;

	private InterfaceGoogleController googleController;

	public InterfaceGoogleListener(InterfaceGoogle google) {
		this.interfaceGoogle = google;
		this.googleController = new InterfaceGoogleController(interfaceGoogle);

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (e.getSource() == interfaceGoogle.getPnlGoogleCancelar()) {
			googleController.panelCancel();
		}
		if (e.getSource() == interfaceGoogle.getLblMapGoogle()) {
			googleController.verifyMapGoogle();
		}
		if (e.getSource() == interfaceGoogle.getLblSatGoogle()) {
			googleController.verifySatGoogle();
		}
		if (e.getSource() == interfaceGoogle.getLblTerGoogle()) {
			googleController.verifyTerGoogle();
		}
		if (e.getSource() == interfaceGoogle.getLblHybridGoogle()) {
			googleController.verifyHybridGoogle();
		}
		if (e.getSource() == interfaceGoogle.getPnlUseTerms()) {
			googleController.panelUserTerms();
		}
		if (e.getSource() == interfaceGoogle.getPnlGoogleFinalizar()) {
			googleController.finalizarGoogle();
		}
	}
}
