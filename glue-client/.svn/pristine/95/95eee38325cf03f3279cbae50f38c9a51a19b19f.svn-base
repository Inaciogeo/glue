package br.org.funcate.glue.controller.listener;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.canvas.CanvasService;
import br.org.funcate.glue.model.exception.GlueServerException;

/**
 * @brief Private inner listener class to resize the applet.
 * 
 * @author FATEC_TEAM
 * @version 1.0.0
 */
public class ResizeAdapter extends ComponentAdapter {

	private JFrame frame;
	private int timeToUpdate = 0;
	private boolean appletStarted = false;
	private UpdatorViewPortSizeThread thread = null;

	public ResizeAdapter(JFrame frame) {
		this.frame = frame;
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		mediator.setResizeController(this);
	}

	public void componentResized(ComponentEvent e) {
		timeToUpdate = 0;

		updateViewSizeToApplet();

		if (thread == null) {
			thread = new UpdatorViewPortSizeThread();
			thread.start();
		}
	}

	public void componentShown(ComponentEvent e) {
		int width = frame.getWidth();
		int height = frame.getHeight();

		if (width < 300 || height < 300) {
			frame.setSize(1120, 610);
		}

		updateViewSizeToApplet(); // adicionado com o surf
	}

	public synchronized void updateViewSizeToApplet() {
		int[] size = { frame.getWidth()-15, frame.getHeight()-40 };

		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		mediator.resizeViewToApplet(size);
	}

	private class UpdatorViewPortSizeThread extends Thread {
		public void run() {
			while (true) {
				if (timeToUpdate == 1) {
					thread = null;
					timeToUpdate = 0;

					if (!appletStarted) {
						appletStarted = true;
					} else {
						try {
							CanvasService.draw(false, false);
						} catch (GlueServerException e) {
							e.printStackTrace();
							// TODO AGUARDANDO IMPLEMENTAÇÃO DO EXCEPTION
							// HANDLER
						}
					}
					return;
				}

				timeToUpdate++;

				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}