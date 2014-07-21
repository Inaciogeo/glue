package br.org.funcate.glue.view;

/** 
 * \file ThemeOptionsView.java
 * This class creates one option screen
 * */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.LookAndFeelService;
import br.org.funcate.glue.model.tree.CustomNode;

/**
 * \brief ThemeOptionsView creates one option screen
 * 
 * @author Moraes, Emerson Leite.
 * @author Aleksander, Willyan.
 * @version 1.1.0
 * 
 */

@SuppressWarnings("serial")
public class ThemeOptionsView extends JFrame implements MouseMotionListener {

	/************************************************************
	 * Components to use on JPanel
	 ************************************************************/
	JPanel _pnlTreeScreen;
	JPanel _pnlExtern;
	JPanel _pnlArquive;
	JPanel _pnlWMS;
	JLabel _lblTreeScreen;
	JLabel _lblExtern;
	JLabel _lblArchive;
	JLabel _lblWMS;
	JLabel _lblConfirm;
	JLabel _lblCancel;

	int _xSel;
	/** < Int attribute > */
	int _ySel;
	/** < Int atribbute > */
	CustomNode _view;
	/** < CustomNode attribute> */
	CustomNode parent;
	/** < CustomNode attribute> */
	/** < JTree attribute > */
	String _selectedView, _url;
	/** < String attribute > */
	static ThemeOptionsView _tov;
	/** < ThemeOptionsView attribute > */
	@SuppressWarnings("rawtypes")
	static JComboBox jcbTypeTheme;
	/** < JComboBox attribute > */

	double _xMin, _yMin, _xMax, _yMax;
	/** < Box Values attribute > */

	static int _width, _heigth;

	/** < Width and Heigth > */

	/**
	 * @author Emerson Leite de Moraes
	 * 
	 * @author Willyan Aleksander
	 * 
	 *         \brief Gets ThemeOptionsView Width
	 * @return _width
	 */
	public static int get_Width() {
		return _width;
	}

	/**
	 * @author Emerson Leite de Moraes
	 * @author Willyan Aleksander
	 * 
	 *         \brief Sets ThemeOptionsView Width
	 */

	public static void set_Width(int w) {
		_width = w;
	}

	/**
	 * @author Emerson Leite de Moraes
	 * @author Willyan Aleksander
	 * 
	 *         \brief Gets ThemeOptionsView Heigth
	 * @return _heigth
	 */

	public static int get_Heigth() {
		return _heigth;
	}

	/**
	 * @author Emerson Leite de Moraes
	 * @author Willyan Aleksander
	 * 
	 *         \brief Sets ThemeOptionsView Heigth
	 */
	public static void set_Heigth(int h) {
		_heigth = h;
	}

	public static Point _location;
	/** <Point Location attribute > */

	private static ThemeOptionsView _instance;

	/** <ThemeOptionsView (singleton) attribute */

	/**
	 * @author Emerson Leite de Moraes
	 * @author Willyan Aleksander
	 * 
	 *         \brief Gets Point Location
	 * @return _location
	 */
	public static Point get_Location() {
		return _location;
	}

	/**
	 * @author Emerson Leite de Moraes
	 * @author Willyan Aleksander
	 * 
	 *         \brief Sets Point Location
	 */
	public static void set_location(Point location) {
		_location = location;
	}

	/**
	 * @param parent
	 * @param _tree
	 *            *
	 *********************************************************/

	// ! Constructor

	private ThemeOptionsView(CustomNode _parent) {

		/************************************************************
		 * creates JPanel location on the screen. adds color to the JPanel.
		 ************************************************************/
		DragPanel d1 = new DragPanel(this);
		this.addMouseListener(d1);
		this.addMouseMotionListener(d1);
		setTitle("Novo tema");
		setResizable(false);
		setAlwaysOnTop(true);
		setSize(320, 270);

		LookAndFeelService.changeLookAndFeelForNimbus();

		setLocation((int) (550 - this.getSize().getWidth() / 2), (int) (365 - this.getSize().getHeight() / 2));

		setBackground(new Color(255, 255, 255));
		setLayout(null);
		this.setUndecorated(true);

		/************************************************************/

		/************************************************************
		 * instantiates the components and creates events
		 ************************************************************/

		_pnlTreeScreen = new JPanel();
		_pnlTreeScreen.setBounds(0, 0, 600, 600);
		_pnlTreeScreen.setBackground(new Color(198, 226, 250));
		_pnlTreeScreen.setVisible(true);
		_pnlTreeScreen.setLayout(null);

		/*****************************************************/

		// add(lblTreeScreen);
		add(_pnlTreeScreen);
		addMouseMotionListener(this);

		this.parent = _parent;
		if (_parent.getNodeType() == NodeType.VIEW) {
			_view = _parent;
			_selectedView = _view.toString();

		} else {
			this._view = (CustomNode) _parent.getParent();
			while (_view.getNodeType() != NodeType.VIEW) {
				_view = (CustomNode) _view.getParent();
			}
			_selectedView = _view.toString();

		}
		populateTreeThemeAdd(_pnlTreeScreen);
	}

	/**
	 * This method is the Singleton of this class.
	 * 
	 * @param parent
	 * @param _tree
	 * @return
	 */
	public static ThemeOptionsView getInstance(CustomNode _parent) {
		if (_instance == null) {
			_instance = new ThemeOptionsView(_parent);
			_instance.setVisible(true);
			_instance.setFocusable(true);

		} else {
			_instance.dispose();
			_instance = new ThemeOptionsView(_parent);
			_instance.setVisible(true);
			_instance.setFocusable(true);
		}
		return _instance;
	}

	/**
	 * This method returns the configuration screen to default.
	 */
	public static void cancelThemePane() {
		_instance.setSize(320, 270);
	}

	// ! populate JPanel
	/*
	 * ! \param mainPanel is the panel to be populated
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void populateTreeThemeAdd(JPanel mainPanel) {

		JLabel lblSelectedView;
		JLabel lblSelectedView2;
		JLabel lblThemeName;
		JLabel lblTypeTheme;
		final JLabel lblAddTheme;
		final JLabel lblCancel;
		final JTextField jtfThemeName;

		final JCheckBox cbSelected;
		final InterfaceDefault iDefault;
		final InterfaceTree iTree;
		final InterfaceNetwork iNetwork;
		String[] _typeThemes;
		if (parent.getNodeType() == NodeType.VIEW) {
			if (parent.isPersisted())
				_typeThemes = new String[] { "Selecione o tipo do tema", "Padrão", /*"Árvore",*/ "Rede", "Externo", "Arquivo", "WFS" };
			else
				_typeThemes = new String[] { "Selecione o tipo do tema", "Externo", "Arquivo", "WFS" };
		} else {
			_typeThemes = new String[] { "Selecione o tipo do tema", "Padrão", /*"Árvore",*/ "Rede", "Externo", "Arquivo" };
		}
		lblSelectedView = new JLabel("Vista: ");
		lblSelectedView.setBounds(10, 10, 60, 20);

		lblSelectedView2 = new JLabel(_selectedView);
		lblSelectedView2.setBounds(55, 9, 100, 22);

		lblThemeName = new JLabel("Nome do Tema: ");
		lblThemeName.setBounds(10, 35, 105, 20);

		lblTypeTheme = new JLabel("Tipo do Tema: ");
		lblTypeTheme.setBounds(10, 65, 200, 20);

		iDefault = InterfaceDefault.getInstance();
		iTree = InterfaceTree.getInstance();
		iNetwork = InterfaceNetwork.getInstance();

		lblCancel = new JLabel(createLinkText("Cancelar"));
		lblCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblCancel.setBounds(85, 120, 80, 20);
		lblCancel.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {

			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseReleased(MouseEvent arg0) {
				setVisible(false);
			}
		});

		lblAddTheme = new JLabel(createLinkText("Adicionar"));
		lblAddTheme.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblAddTheme.setBounds(15, 120, 60, 20);

		jtfThemeName = new JTextField(30);
		jtfThemeName.setBounds(100, 34, 200, 26);

		jcbTypeTheme = new JComboBox(_typeThemes);
		jcbTypeTheme.setBounds(95, 63, 205, 25);
		jcbTypeTheme.setBackground(new Color(255, 255, 255));

		jcbTypeTheme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (jcbTypeTheme.getSelectedItem() == "WMS") {
					iDefault.enableDefault(false);
					iTree.enableTree(false);
					iNetwork.enableNetwork(false);
					setSize(530, 515);
					set_Heigth(ThemeOptionsView.this.getHeight());
					set_Width(ThemeOptionsView.this.getWidth());
					set_location(getLocation());
					lblAddTheme.setLocation(400, 485);
					lblCancel.setLocation(470, 485);
					repaint();
				} else if (jcbTypeTheme.getSelectedItem() == "Google") {
					iDefault.enableDefault(false);
					iTree.enableTree(false);
					iNetwork.enableNetwork(false);
					setSize(340, 577);
					lblAddTheme.setLocation(210, 547);
					lblCancel.setLocation(275, 547);
					repaint();
				} else if (jcbTypeTheme.getSelectedItem() == "Padrão") {
					iDefault.enableDefault(true);
					iTree.enableTree(false);
					iNetwork.enableNetwork(false);
					_pnlTreeScreen.add(iDefault);
					setSize(340, 272);
					lblAddTheme.setLocation(210, 242);
					lblCancel.setLocation(275, 242);
					repaint();
				} else if (jcbTypeTheme.getSelectedItem() == "Árvore") {
					iTree.enableTree(true);
					iDefault.enableDefault(false);
					_pnlTreeScreen.add(iTree);
					setSize(340, 272);
					lblAddTheme.setLocation(210, 242);
					lblCancel.setLocation(275, 242);
					repaint();
				} else if (jcbTypeTheme.getSelectedItem() == "Rede"){
					iTree.enableTree(false);
					iDefault.enableDefault(false);
					iNetwork.enableNetwork(true);
					_pnlTreeScreen.add(iNetwork);
					setSize(340, 272);
					lblAddTheme.setLocation(210, 242);
					lblCancel.setLocation(275, 242);
					repaint();
				} else {
					iDefault.enableDefault(false);
					iTree.enableTree(false);
					lblAddTheme.setLocation(175, 120);
					lblCancel.setLocation(248, 120);
					setSize(320, 270);
					repaint();
				}
			}
		});

		cbSelected = new JCheckBox("Selecionado");
		cbSelected.setSelected(true);
		cbSelected.setBackground(new Color(255, 255, 255));
		cbSelected.setBounds(10, 90, 200, 20);

		mainPanel.add(lblSelectedView);
		mainPanel.add(lblSelectedView2);
		mainPanel.add(lblThemeName);
		mainPanel.add(jtfThemeName);
		mainPanel.add(lblTypeTheme);
		mainPanel.add(jcbTypeTheme);
		mainPanel.add(cbSelected);
		mainPanel.add(lblAddTheme);
		mainPanel.add(lblCancel);

		lblAddTheme.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseReleased(MouseEvent arg0) {
				String _layer = null;
				String _name = jtfThemeName.getText();
				String _type = (String) jcbTypeTheme.getItemAt(jcbTypeTheme.getSelectedIndex());
				boolean _selected = cbSelected.isSelected();
				CustomNode node = null;

				if (_name == null || _name.trim().equals("")) {
					jtfThemeName.requestFocus();
					LocalOptionPane.getInstance("Preencha o campo nome", "Glue - Falta de Informação");
					return;
				}
				
				if(_name.length() >= 20) {
					jtfThemeName.requestFocus();
					LocalOptionPane.getInstance("Nome excede 20 caracteres. Tente novamente.", "Glue - Falta de Informação");
					return;
				}

				NodeType _eType = null;
				if (_type == "Selecione o tipo do tema") {
					LocalOptionPane.getInstance("Selecione o tipo do tema", "Glue - Tipos");
					return;
				}

				if (_type == "Padrão") {
					_eType = NodeType.DEFAULT;
					set_Heigth(ThemeOptionsView.this.getHeight());
					set_Width(ThemeOptionsView.this.getWidth());
					set_location(getLocation());
					if (iDefault._cmbLayer.getSelectedIndex() == -1) {
						LocalOptionPane.getInstance("Selecione um plano de informação", "Glue - Planos");
						return;
					} else {
						iDefault.labelFinalizar();
						_layer = iDefault.getLayerName();
						node = new CustomNode(0l, _name, _selected, true, _eType, _layer);

						if (parent.getNodeType() == NodeType.TREE_THEME || parent.getNodeType() == NodeType.NETWORK_THEME) {
							node.setParentID(parent.getId());
						}
					}
				}
				if (_type == "Árvore") {
					_eType = NodeType.TREE_THEME;
					set_Heigth(ThemeOptionsView.this.getHeight());
					set_Width(ThemeOptionsView.this.getWidth());
					set_location(getLocation());
					if (iTree._cmbLayer.getSelectedIndex() == -1) {
						LocalOptionPane.getInstance("Selecione um plano de informação", "Glue - Planos");
						return;
					} else {
						iTree.labelFinalizar();
						_layer = iTree.getLayerName();
						node = new CustomNode(0l, _name, _selected, true, _eType, _layer);
						node.setAllowsChildren(true);
						if (parent.getNodeType() == NodeType.TREE_THEME || parent.getNodeType() == NodeType.NETWORK_THEME) {
							node.setParentID(parent.getId());
						}
					}
				}
				if (_type == "Rede") {
					_eType = NodeType.NETWORK_THEME;
					set_Heigth(ThemeOptionsView.this.getHeight());
					set_Width(ThemeOptionsView.this.getWidth());
					set_location(getLocation());
					if(iNetwork.validateFields()){
						node = new CustomNode(0l, _name, _selected, _eType);
						if (parent.getNodeType() == NodeType.TREE_THEME || parent.getNodeType() == NodeType.NETWORK_THEME) {
							node.setParentID(parent.getId());
						}
					}	
				}
				if (_type == "Arquivo") {
					_eType = NodeType.FILE_THEME;
					node = new CustomNode(0l, _name, _selected, _eType);
					if (parent.getNodeType() == NodeType.TREE_THEME || parent.getNodeType() == NodeType.NETWORK_THEME) {
						node.setParentID(parent.getId());
					}
				}
				if (_type == "Externo") {
					_eType = NodeType.EXTERN_THEME;
					node = new CustomNode(0l, _name, _selected, _eType);
					if (parent.getNodeType() == NodeType.TREE_THEME || parent.getNodeType() == NodeType.NETWORK_THEME) {
						node.setParentID(parent.getId());
					}
				}
				AppSingleton.getInstance().getMediator().addNode(node, parent);
				AppSingleton.getInstance().getMediator().getNodeView(node).getView();
				
				if (_eType == NodeType.NETWORK_THEME) {
					String pointsLayerName = iNetwork.getPointsLayerName();
					String pointsThemename = _name + "_Pontos";
					CustomNode pointsNode = new CustomNode(0l, pointsThemename, _selected, true, NodeType.DEFAULT, pointsLayerName);
					AppSingleton.getInstance().getMediator().addNode(pointsNode, node);
					
					String linesLayerName = iNetwork.getLinesLayerName();
					String linesThemename = _name + "_Linhas";
					CustomNode linesNode = new CustomNode(0l, linesThemename, _selected, true, NodeType.DEFAULT, linesLayerName);
					AppSingleton.getInstance().getMediator().addNode(linesNode, node);
				}
				
				setVisible(false);
				LocalOptionPane.getInstance("Tema criado com sucesso!", "Glue - Sucesso");
			}
		});
		set_Heigth(this.getHeight());
		set_Width(this.getWidth());
		set_location(getLocation());
	}

	/**
	 * This method returns the configuration of jcbTypeTheme ComboBox to
	 * default.
	 */
	public static void setDefaultComboBox() {
		jcbTypeTheme.setSelectedIndex(0);
	}

	// ! define visibility panel
	/**
	 * \param b is a boolean(true or false)
	 */

	public void enableTreeThemeAdd(boolean b) {
		_pnlTreeScreen.setVisible(b);
	}

	// ! create appearance html link
	/**
	 * \param text is the text of the label
	 */

	public String createLinkText(String text) {

		text = "<html><font color=\"#000080\"><u>" + text + "</u></font></html>";

		return text;
	}

	// !create event for mouse drag
	/**
	 * \param arg0 is a mouse event
	 */

	public void mouseDragged(MouseEvent arg0) {
		set_location(getLocation());
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

}
