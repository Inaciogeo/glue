package br.org.funcate.glue.controller.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import br.org.funcate.glue.controller.CanvasController;
import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.view.AbstractCanvas;

public class CanvasAdapter extends MouseAdapter implements KeyListener {

	private CanvasController canvasController;

	private long mouseWheelEventAux;

	public CanvasAdapter(AbstractCanvas canvasView) {
		canvasController = new CanvasController(canvasView);
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		mediator.setCanvasController(canvasController);
	}

	public void mouseWheelMoved(MouseWheelEvent event) {
		if ((event.getWhen() - this.mouseWheelEventAux) > 500) {
			canvasController.executeMouseWheelMove(event);
			mouseWheelEventAux = event.getWhen();
		}
	}

	public void mouseDragged(MouseEvent event) {
		canvasController.executeMouseDrag(event);
	}

	public void mouseMoved(MouseEvent event) {
		canvasController.executeMouseMove(event.getX(), event.getY());
	}

	public void mousePressed(MouseEvent event) {

		canvasController.executeMousePress(event);
	}

	public void mouseClicked(MouseEvent event) {
		canvasController.executeMouseClick(event);
	}

	public void mouseReleased(MouseEvent event) {
		canvasController.executeMouseRelease(event);
	}

	public void keyPressed(KeyEvent k) {
		canvasController.executeKeyPress(k);
	}

	public void keyReleased(KeyEvent k) {
		canvasController.executeKeyRelease(k);
	}

	public void keyTyped(KeyEvent arg0) {
	}
}