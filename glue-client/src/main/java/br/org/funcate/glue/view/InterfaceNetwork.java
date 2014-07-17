package br.org.funcate.glue.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.Layer;
import br.org.funcate.glue.model.LookAndFeelService;
import br.org.funcate.glue.model.Representation;

/** \file InterfaceTree.java
 * This file creates the interface to create a theme of the following types hidden tree, tree 
 */

/**
 * \brief Creates a screen to create Tree themes
 * 
 * @author OLIVEIRA, Paulo Renato M.
 */
@SuppressWarnings("serial")
public class InterfaceNetwork extends JPanel implements ActionListener,
		MouseListener {

	/** < Attribute type JLabel > */
	private JLabel _lblFinalizar, _lblCancelar, _lblLayerLines,
			_lblLayerPoints;

	/** < Attribute type JPanel > */
	private JPanel _pnlFinalizar, _pnlCancelar;

	/** < Attribute type JComboBox > */
	@SuppressWarnings("rawtypes")
	public JComboBox _cmbLayerLines, _cmbLayerPoints;

	/** < Single instance of the class > */
	private static InterfaceNetwork _instance;

	/**
	 * Get the points layer name
	 * 
	 * @return layer selected in the combobox
	 * @author OLIVEIRA, Paulo Renato M.
	 */

	public String getPointsLayerName() {
		return (String) _cmbLayerPoints.getSelectedItem();
	}
	
	/**
	 * Get the lines layer name
	 * 
	 * @return layer selected in the combobox
	 * @author OLIVEIRA, Paulo Renato M.
	 */
	
	public String getLinesLayerName() {
		return (String) _cmbLayerLines.getSelectedItem();
	}

	/**
	 * @return the instance of this class
	 * @author OLIVEIRA, Paulo Renato M.
	 */

	public static InterfaceNetwork getInstance() {
		if (_instance == null) {
			_instance = new InterfaceNetwork();
		} else {
			_instance.invalidate();
			_instance = new InterfaceNetwork();
		}
		return _instance;
	}

	/**
	 * \brief Creates a new instance of InterfaceTree
	 * 
	 * @author OLIVEIRA, Paulo Renato M.
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private InterfaceNetwork() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		LookAndFeelService.changeLookAndFeelForNimbus();
		
		
		List<String> layersPoints =  new ArrayList<String>();
		List<String> layerslines = new ArrayList<String>();
		Mediator mediator = AppSingleton.getInstance().getMediator();		
		List<Layer> layers = mediator.getLayers();
		
		for (int i = 0; i < layers.size(); i++) {
			Layer layer = layers.get(i);
			for (Representation representation : layer.getRepresentations()) {
				if(representation.getId() == Representation.POINT){
					layersPoints.add(layer.getName());
				}
				if(representation.getId() == Representation.LINE){
					layerslines.add(layer.getName());
				}
			}						
		}
		
		
	

		
		// Layer de pontos
		_lblLayerPoints = new JLabel("Selecione o plano de informação de Pontos:");
		_lblLayerPoints.setBounds(15, 5, 250, 25);
		
		Vector<String> layerPointsVec = new Vector<String>(layersPoints);
		_cmbLayerPoints = new JComboBox(layerPointsVec);
		_cmbLayerPoints.setSelectedIndex(-1);

		_cmbLayerPoints.setBounds(15, 30, 250, 25);
		_cmbLayerPoints.addActionListener(this);
		
		// Layer de linhas

		_lblLayerLines = new JLabel("Selecione o plano de informação de Linhas:");
		_lblLayerLines.setBounds(15, 60, 250, 25);

		Vector<String> layerLinesVec = new Vector<String>(layerslines);
		_cmbLayerLines = new JComboBox(layerLinesVec);
		_cmbLayerLines.setSelectedIndex(-1);
		
		_cmbLayerLines.setBounds(15, 85, 250, 25);
		_cmbLayerLines.addActionListener(this);
		
		
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

		add(_lblLayerPoints);
		add(_cmbLayerPoints);
		

		add(_lblLayerLines);
		add(_cmbLayerLines);
		
	}

	/**
	 * \brief Sets InterfaceNetwork Visible and Bounds
	 * 
	 * @author OLIVEIRA, Paulo Renato M.
	 */

	public void enableNetwork(boolean pVisible) {
		setBounds(5, 120, 330, 120);
		setVisible(pVisible);
	}

	/**
	 * \brief This method Cancel the InterfaceTree Panel
	 * 
	 * @author OLIVEIRA, Paulo Renato M.
	 */
	private void panelCancel() {
		enableNetwork(false);
		ThemeOptionsView.cancelThemePane();
		ThemeOptionsView.setDefaultComboBox();
	}

	/**
	 * \brief This method creates a Link Text
	 * 
	 * @author OLIVEIRA, Paulo Renato M.
	 */

	public String createLinkText(String text, String cor) {
		text = "<html><font color=\"#" + cor + "\"><u>" + text
				+ "</u></font></html>";
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
			
			if(validateFields()){			
				LocalOptionPane.getInstance("Clique em Adicionar para finalizar", "Glue - Criar Tema");
			}
		}

	}

	public boolean validateFields() {
		
		if (_cmbLayerPoints.getSelectedIndex() == -1) {
			LocalOptionPane.getInstance("Selecione um plano de informação de Pontos", "Glue - Criar Tema");
			return false;
		} 
		if (_cmbLayerLines.getSelectedIndex() == -1) {
			LocalOptionPane.getInstance("Selecione um plano de informação de Linhas", "Glue - Criar Tema");
			return false;
		} 
		
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

}
