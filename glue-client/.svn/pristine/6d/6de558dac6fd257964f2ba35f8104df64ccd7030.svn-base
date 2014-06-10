package br.org.funcate.glue.main;

import javax.swing.JFrame;

import br.org.funcate.glue.controller.listener.ResizeAdapter;
import br.org.funcate.glue.view.MainPanel;

/**
 * @brief Class whose instance will be loaded into Surf Client
 * 
 * @author FATEC_TEAM
 * @version 2.0
 * @see javax.swing.JApplet
 */

public class KernelStarter {

	public void startKernel(JFrame frame, ClassLoader loader) {
		MainPanel panel = new MainPanel(true, loader);

		frame.add(panel);

		frame.addComponentListener(new ResizeAdapter(frame));

		frame.repaint();
	}
}
