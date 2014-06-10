/**
 * \file ToolWMS.java
 * This class creates a screen for a WMS Panel.
 */

package br.org.funcate.glue.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.org.funcate.glue.utilities.patterns.Observer;
import br.org.funcate.glue.utilities.patterns.Subject;

/**
 * \brief This class creates a screen Observer for a WMS Panel \author Emerson
 * Leite de Moraes \author Willyan Aleksander
 * 
 * Version 1.0
 * 
 */

@SuppressWarnings("serial")
public class ToolWMS extends JFrame implements Observer {

	private InterfaceWMS _iWMS;
	/** <Attribute type InterfaceWMS */
	private Subject _principal;
	/** <Attribute type Subject */
	private JPanel _pnlWMS;
	/** <Attribute type JPanel */
	private static ToolWMS _instance;

	/** <Attribute type ToolWMS (Singleton) */

	/** Contructor */
	private ToolWMS() {

		_iWMS = InterfaceWMS.getInstance();
		// MouseActionSuject.getInstance().pauseTimerThread();
		_iWMS.setBounds(5, 5, 520, 360);
		_iWMS.setVisible(true);
		_iWMS.setListener(1);
		_principal = _iWMS;
		_principal.addObserver(this);
		_pnlWMS = new JPanel();
		_pnlWMS.setSize(530, 370);
		_pnlWMS.add(_iWMS);
		_pnlWMS.setLayout(null);
		_pnlWMS.setVisible(true);
		_pnlWMS.setBackground(new Color(198, 226, 250));
		DragPanel drag = new DragPanel(this);
		this.addMouseListener(drag);
		this.addMouseMotionListener(drag);
		setUndecorated(true);
		setAlwaysOnTop(true);
		setSize(530, 370);
		add(_pnlWMS);

		setLocation((int) (550 - this.getSize().getWidth() / 2), (int) (365 - this.getSize().getHeight() / 2));

	}

	/** Singleton method of this class */
	public static ToolWMS getInstance() {
		if (_instance == null) {
			_instance = new ToolWMS();
			_instance.setFocusable(true);
		}
		return _instance;
	}

	@Override
	public void exit() {
		dispose();
	}
}
