package br.org.funcate.glue.main;

import javax.swing.JFrame;

import br.org.funcate.glue.controller.listener.ResizeAdapter;
import br.org.funcate.glue.view.MainPanel;

/**
 * @brief Main class of GLUE system.
 * 
 * @author FATEC_TEAM
 * @version 2.0
 * @see javax.swing.JApplet
 */
@SuppressWarnings("serial")
public class Main extends JFrame {
	public Main() {

	}

	public void init() {
		
		MainPanel panel = new MainPanel(false, null);
		add(panel);
		addComponentListener(new ResizeAdapter(this));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		MainPanel.setInstance(panel);
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.init();
	}
	
}
