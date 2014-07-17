package br.org.funcate.glue.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.LookAndFeelService;

/** \file InterfaceDefault.java
 * This file creates the interface to create a theme of the type default
 */

/**
 * \brief Creates a screen to create Default themes
 * 
 * @author OLIVEIRA, Paulo Renato M.
 */

@SuppressWarnings("serial")
public class InterfaceDefault extends JPanel implements ActionListener, MouseListener {

	/** < Attribute type JLabel > */
	private JLabel _lblFinalizar, _lblCancelar, _lblLayer;

	/** < Attribute type JPanel > */
	private JPanel _pnlFinalizar, _pnlCancelar;

	/** < Attribute type JComboBox > */
	@SuppressWarnings("rawtypes")
	public JComboBox _cmbLayer;

	/** < Single instance of the class > */
	private static InterfaceDefault _instance;

	/** < Attribute type String[] > */
	private String[] _layers;

	/**
	 * Get the layer name
	 * 
	 * @return layer selected in the combobox
	 */

	public String getLayerName() {
		return (String) _cmbLayer.getSelectedItem();
	}

	/**
	 * \brief Method to get the single instance of this class.
	 * 
	 * @author OLIVEIRA, Paulo Renato M.
	 * @return The single instance of this class.
	 */

	public static InterfaceDefault getInstance() {
		if (_instance == null) {
			_instance = new InterfaceDefault();
		} else {
			_instance.invalidate();
			_instance = new InterfaceDefault();
		}
		return _instance;
	}

	/**
	 * \brief Creates a new instance of InterfaceDefault
	 * 
	 * @author OLIVEIRA, Paulo Renato M.
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private InterfaceDefault() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		LookAndFeelService.changeLookAndFeelForNimbus();
		_lblLayer = new JLabel("Selecione o plano de informação:");
		_lblLayer.setBounds(15, 5, 250, 25);

		try {
			_layers = new String[AppSingleton.getInstance().getMediator().getLayers().size()];
			for (int i = 0; i < AppSingleton.getInstance().getMediator().getLayers().size(); i++) {
				_layers[i] = AppSingleton.getInstance().getMediator().getLayers().get(i).getName();
			}
			_cmbLayer = new JComboBox(_layers);
			_cmbLayer.setSelectedIndex(-1);
		} catch (Exception e) {
			_cmbLayer = new JComboBox();
			System.out.println("Não foi possível conectar ao banco");
		}

		_cmbLayer.setBounds(15, 30, 250, 25);
		_cmbLayer.addActionListener(this);

		// getLayersName
		_lblFinalizar = new JLabel(createLinkText("Finalizar", "CFCFCF"));
		_lblFinalizar.setBounds(4, 2, 60, 25);
		_lblFinalizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		_pnlFinalizar = new JPanel();
		_pnlFinalizar.setBounds(140, 80, 68, 29);
		_pnlFinalizar.setBackground(new Color(255, 255, 255));
		_pnlFinalizar.add(_lblFinalizar);
		_pnlFinalizar.setToolTipText("Finaliza seleção de tema e finalizar.");
		_pnlFinalizar.addMouseListener(this);

		_lblCancelar = new JLabel(createLinkText("Cancelar", "000080"));
		_lblCancelar.setBounds(4, 2, 60, 25);
		_lblCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		_pnlCancelar = new JPanel();
		_pnlCancelar.setBounds(70, 80, 68, 29);
		_pnlCancelar.setBackground(new Color(255, 255, 255));
		_pnlCancelar.add(_lblCancelar);
		_pnlCancelar.setToolTipText("Cancelar criação do tema e sair");
		_pnlCancelar.addMouseListener(this);

		add(_lblLayer);
		add(_cmbLayer);
		// add(_pnlFinalizar);
		// add(_pnlCancelar);

	}

	/**
	 * \brief Sets InterfaceDefalut Visible and Bounds
	 * 
	 * @author OLIVEIRA, Paulo Renato M.
	 */

	public void enableDefault(boolean pVisible) {
		setBounds(5, 120, 330, 120);
		setVisible(pVisible);
	}

	/**
	 * \brief This method Cancel the InterfaceDefault Panel
	 * 
	 * @author OLIVEIRA, Paulo Renato M.
	 */
	private void panelCancel() {
		enableDefault(false);
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
	public void actionPerformed(ActionEvent e) {
		if (_cmbLayer.getSelectedIndex() != -1) {
			_lblFinalizar.setText(createLinkText("Finalizar", "000080"));
		}
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

		if (e.getSource() == _pnlCancelar) {
			LookAndFeelService.changeLookAndFeelForDefault();
			panelCancel();
		}
		if (e.getSource() == _pnlFinalizar) {

			if (_cmbLayer.getSelectedIndex() == -1) {
				LocalOptionPane.getInstance("Selecione um plano de informação", "Glue - Criar Tema");
			} else {
				LocalOptionPane.getInstance("Clique em Adicionar para finalizar", "Glue - Criar Tema");
			}
		}

	}

	/**
	 * \brief This method finalizes a InterfaceGoogle Panel in the most
	 * appropriate form
	 * 
	 * @author OLIVEIRA, Paulo Renato M.
	 */

	public void labelFinalizar() {
		if (_cmbLayer.getSelectedIndex() == -1) {
			LocalOptionPane.getInstance("Selecione um plano de informação", "Glue - Criar Tema");
		} else {
			return;
		}
	}

}
