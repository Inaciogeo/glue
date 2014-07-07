package br.org.funcate.glue.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.org.funcate.glue.model.LookAndFeelService;

/** \file InterfaceTree.java
 * This file creates the interface to create a theme of the following types hidden tree, tree 
 */

/**
 * \brief Creates a screen to create Tree themes
 * 
 * @author OLIVEIRA, Paulo Renato M.
 */
@SuppressWarnings("serial")
public class InterfaceTree extends JPanel implements ActionListener, MouseListener {

	/** < Attribute type JLabel > */
	private JLabel _lblLayer;

	/** < Attribute type JPanel > */
	private JPanel _pnlFinalizar, _pnlCancelar;

	/** < Attribute type JComboBox > */
	public JComboBox _cmbLayer;

	/** < Attribute type String[] > */
	private String[] _layers;

	/** < Single instance of the class > */
	private static InterfaceTree _instance;

	/**
	 * Get the layer name
	 * 
	 * @return layer selected in the combobox
	 * @author OLIVEIRA, Paulo Renato M.
	 */

	public String getLayerName() {
		return (String) _cmbLayer.getSelectedItem();
	}

	/**
	 * @return the instance of this class
	 * @author OLIVEIRA, Paulo Renato M.
	 */

	public static InterfaceTree getInstance() {
		if (_instance == null) {
			_instance = new InterfaceTree();
		} else {
			_instance.invalidate();
			_instance = new InterfaceTree();
		}
		return _instance;
	}

	/**
	 * \brief Method to get the single instance of this class.
	 * 
	 * @author OLIVEIRA, Paulo Renato M.
	 * @return The single instance of this class.
	 */

	public static InterfaceTree get_instance() {
		return _instance;
	}

	/**
	 * \brief Method to set the single instance of this class.
	 * 
	 * @author OLIVEIRA, Paulo Renato M.
	 * @return The single instance of this class.
	 * 
	 * @param _instance
	 *            the instance of InterfaceTree to set
	 */
	public static void set_instance(InterfaceTree _instance) {
		InterfaceTree._instance = _instance;
	}

	/**
	 * \brief Creates a new instance of InterfaceTree
	 * 
	 * @author OLIVEIRA, Paulo Renato M.
	 */

	private InterfaceTree() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		LookAndFeelService.changeLookAndFeelForNimbus();
		
	}

	/**
	 * \brief Sets InterfaceTree Visible and Bounds
	 * 
	 * @author OLIVEIRA, Paulo Renato M.
	 */

	public void enableTree(boolean pVisible) {
		setBounds(5, 120, 330, 120);
		setVisible(pVisible);
	}

	/**
	 * \brief This method Cancel the InterfaceTree Panel
	 * 
	 * @author OLIVEIRA, Paulo Renato M.
	 */
	private void panelCancel() {
		enableTree(false);
		ThemeOptionsView.cancelThemePane();
		ThemeOptionsView.setDefaultComboBox();
	}

	/**
	 * \brief This method creates a Link Text
	 * 
	 * @author OLIVEIRA, Paulo Renato M.
	 */

	public String createLinkText(String text, String cor) {
		text = "<html><font color=\"#" + cor + "\"><u>" + text + "</u></font></html>";
		return text;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (e.getSource() == _pnlCancelar)
			panelCancel();
		if (e.getSource() == _pnlFinalizar) {
			
		}

	}

	public void labelFinalizar() {
		return;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

}
