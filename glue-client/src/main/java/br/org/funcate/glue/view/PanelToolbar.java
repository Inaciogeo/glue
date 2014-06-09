package br.org.funcate.glue.view;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * \brief Panel that have the application's toolbar.
 * 
 * @author
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class PanelToolbar extends JPanel {
	/** The panel's toolbar. */
	private Toolbar bar;

	public PanelToolbar() {
		setLayout(null);
		bar = new Toolbar();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		setBackground(Color.white);
		setVisible(true);

		add(bar);
	}

	/**
	 * \brief Method to get the panel's toolbar.
	 * 
	 * @return The panel's toolbar.
	 */
	public Toolbar get_bar() {
		return bar;
	}

}
