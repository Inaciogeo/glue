package br.org.funcate.glue.view;

/** 
 * \file InterfaceWMS.java
 * This class creates one screen with wms interface
 * */

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.LookAndFeelService;
import br.org.funcate.glue.model.cache.TileType;
import br.org.funcate.glue.model.canvas.ImageSourceDefinition;
import br.org.funcate.glue.model.toolbar.ToolEnum;

import br.org.funcate.glue.model.xml.XMLReader;
import br.org.funcate.glue.utilities.Utils;
import br.org.funcate.glue.utilities.patterns.Observer;
import br.org.funcate.glue.utilities.patterns.Subject;
import javax.swing.JSeparator;


/**
 * \class InterfaceWMS Creates One screen to use WMS. \brief InterfaceWMS
 * Creates One screen to use WMS.
 * 
 * @author Emerson Leite de Moraes
 * @author Willyan Aleksander
 * 
 *         \version 1.3
 */

@SuppressWarnings("serial")
public class InterfaceWMS extends JPanel implements MouseListener, KeyListener, Subject {

	/*
	 * !
	 * 
	 * @name XMLReader
	 * 
	 * Instance of class xmlreader, used to decoder xml getcapabilities
	 */
	// @{
	private XMLReader _xml;
	// @}

	private Observer _obsrv;

	private int listener;

	private static InterfaceWMS _instance;

	private double _xMin, _xMax, _yMin, _yMax;

	/*
	 * !
	 * 
	 * @name Combobox
	 * 
	 * ComboBox to use in interface
	 */
	// @{
	@SuppressWarnings("rawtypes")
	private JComboBox _cbImageFormat;

	// @}

	/*
	 * !
	 * 
	 * @name Labels
	 * 
	 * Labels to use in interface
	 */
	// @{
	private JLabel _lblServ, _lblSearch, _lblThemeName, _lblEx, _lblAdd, _lblRemove, _lblUp, _lblDown, _lblPreviewMap, _lblFinalizar,
			_lblCancel, _lblReset, _lblImageSelection;
	// @}

	JPanel _pnlCancel, _pnlReset, _pnlFinalizar, _pnlPreviewMap;

	/*
	 * !
	 * 
	 * @name List
	 * 
	 * List to use in interface
	 */
	// @{
	@SuppressWarnings("rawtypes")
	private JList _jlLayers, _jlSelectedLayers;
	// @}

	/*
	 * !
	 * 
	 * @name TestFilds
	 * 
	 * text filds to use interface and gatcapability request
	 */

	// @{
	private JTextField _jtfSearch;

	// @}

	/*
	 * !
	 * 
	 * @name CanvasPreview
	 * 
	 * canvas o preview wms request
	 */

	// @{
	private CanvasPreview _canv;
	// @}

	/*
	 * !
	 * 
	 * @name DefaultListModel
	 * 
	 * Array to load layers and JList
	 */

	// @{
	@SuppressWarnings("rawtypes")
	private DefaultListModel _dlmTitles, _dlmSelectedTitles, _dlmNames;
	@SuppressWarnings("rawtypes")
	private DefaultListModel _dlmSelectedNames;
	// @}

	/*
	 * !
	 * 
	 * @name String[]
	 * 
	 * Array to load JComboBox
	 */

	// @{
	private String[] _imageFormat;
	// @}

	/*
	 * !
	 * 
	 * @name String
	 * 
	 * String to load wms request url without boudingbox
	 */

	// @{
	private static String _urlWMS, _sFormat;

	/**
	 * Gets the value of _sFormat.
	 * 
	 * @return String
	 */
	public String get_sFormat() {
		return _sFormat;
	}

	/**
	 * Sets _sFormat value
	 * 
	 * @param sFormat
	 */
	public void set_sFormat(String sFormat) {
		_sFormat = sFormat;
	}

	/**
	 * Gets the value of _urlWMS.
	 * 
	 * @return String
	 */
	public static String get_urlWMS() {
		return _urlWMS;
	}

	/**
	 * Sets _urlWMS value
	 */
	public void set_urlWMS() {
		_urlWMS = _xml.generateURL(_dlmSelectedNames, _sFormat);
	}

	// @}

	/*
	 * !
	 * 
	 * @name int
	 * 
	 * index to manipulate layers selected
	 */

	// @{
	private int _selectedIndex, _index2, _index1;
	// @}

	/*
	 * !
	 * 
	 * @name JScrollPane
	 * 
	 * add scroll to scrolling DefaultListModel
	 */

	// @{
	private JScrollPane _scrollNotSelectedLayers, _scrollSelectedLayers;
	// @}

	/*
	 * !
	 * 
	 * @name boolean
	 * 
	 * Status of Ok button
	 */

	// @{
	private static boolean _okStatus, _wmsSearched, _okPressed;

	/**
	 * Gets value of _okPressed
	 * 
	 * @return boolean
	 */
	public static boolean is_okPressed() {
		return _okPressed;
	}

	/**
	 * Sets _okPressed value
	 * 
	 * @param okPressed
	 */
	public static void set_okPressed(boolean okPressed) {
		_okPressed = okPressed;
	}

	/**
	 * Gets value of _wmsSearched
	 * 
	 * @return boolean
	 */
	public boolean get_wmsSearched() {
		return _wmsSearched;
	}

	/**
	 * Sets _wmsSearched value
	 * 
	 * @param wmsSearched
	 */
	public void set_wmsSearched(boolean wmsSearched) {
		_wmsSearched = wmsSearched;
	}

	/**
	 * Gets value of _okStatus
	 * 
	 * @return boolean
	 */
	public static boolean get_okStatus() {
		return _okStatus;
	}

	/**
	 * Sets _okStatus value
	 * 
	 * @param okStatus
	 */
	public void set_okStatus(boolean okStatus) {
		_okStatus = okStatus;
	}

	// @}

	/*
	 * !
	 * 
	 * @name float
	 * 
	 * floats to storage bounding box value
	 */

	// @{
	static float _minX, _minY, _maxX, _maxY;

	// @}
	/**
	 * Gets value of _minX
	 * 
	 * @return float
	 */
	public static float get_minX() {
		return _minX;
	}

	/**
	 * Sets _minX value
	 * 
	 * @param minX
	 */
	public static void set_minX(float minX) {
		_minX = minX;
	}

	/**
	 * Gets value of _minY .
	 * 
	 * @return float
	 */
	public static float get_minY() {
		return _minY;
	}

	/**
	 * Sets _minY value
	 * 
	 * @param minY
	 */
	public static void set_minY(float minY) {
		_minY = minY;
	}

	/**
	 * Gets value of _maxX .
	 * 
	 * @return float
	 */
	public static float get_maxX() {
		return _maxX;
	}

	/**
	 * Sets _maxX value
	 * 
	 * @param maxX
	 */
	public static void set_maxX(float maxX) {
		_maxX = maxX;
	}

	/**
	 * Gets value of _maxY.
	 * 
	 * @return float
	 */
	public static float get_maxY() {
		return _maxY;
	}

	/**
	 * Sets _maxY value
	 * 
	 * @param maxY
	 */
	public static void set_maxY(float maxY) {
		_maxY = maxY;
	}

	/**
	 * This method is a Singleton implementation of this class
	 * 
	 * @return InterfaceWMS instance
	 */
	public static InterfaceWMS getInstance() {
		if (_instance == null) {
			_instance = new InterfaceWMS();
		}
		return _instance;
	}

	// ! Constructor
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private InterfaceWMS() {
		setBorder(null);
		
		
		/*
		 * ! Declare the components of screen
		 */

		set_okPressed(false);

		_selectedIndex = 0;
		_index2 = 0;
		_index1 = 0;
		_wmsSearched = false;

		LookAndFeelService.changeLookAndFeelForNimbus();

		_dlmNames = new DefaultListModel();
		_dlmTitles = new DefaultListModel();
		_jlLayers = new JList(_dlmTitles);
		_jlLayers.setDragEnabled(true);
		_jlLayers.setBounds(20, 70, 150, 210);
		_jlLayers.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		_jlLayers.setToolTipText("Layers presentes no servidor WMS");
		_scrollNotSelectedLayers = new JScrollPane(_jlLayers);
		_scrollNotSelectedLayers.setBounds(20, 70, 150, 210);

		_dlmSelectedNames = new DefaultListModel();
		_dlmSelectedTitles = new DefaultListModel();
		_jlSelectedLayers = new JList(_dlmSelectedTitles);
		_jlSelectedLayers.setDragEnabled(true);
		_jlSelectedLayers.setBounds(206, 70, 150, 210);
		_jlSelectedLayers.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		_jlSelectedLayers.setToolTipText("Layers selecionados");
		_scrollSelectedLayers = new JScrollPane(_jlSelectedLayers);
		_scrollSelectedLayers.setBounds(206, 70, 150, 210);

		_canv = new CanvasPreview();
		_canv.setBounds(365, 85, 140, 140);

		_lblPreviewMap = new JLabel(createLinkText("Map Preview", "000080"));
		_lblPreviewMap.setBounds(4, 2, 75, 20);
		_lblPreviewMap.setCursor(new Cursor(Cursor.HAND_CURSOR));
		_pnlPreviewMap = new JPanel();
		_pnlPreviewMap.setLayout(null);
		_pnlPreviewMap.setBounds(393, 239, 78, 19);
		_pnlPreviewMap.setBackground(new Color(255, 255, 255));
		_pnlPreviewMap.add(_lblPreviewMap);
		_pnlPreviewMap.setToolTipText("Pré-visualizar seleção de temas");
		_pnlPreviewMap.addMouseListener(this);

		_lblImageSelection = new JLabel("Escolha o formato de img:");
		_lblImageSelection.setFont(new Font("SansSerif", Font.BOLD, 12));
		_lblImageSelection.setBounds(25, 298, 160, 18);

		_lblReset = new JLabel(createLinkText("Reset", "000080"));
		_lblReset.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 13));
		_lblReset.setBounds(10, 4, 70, 15);
		_lblReset.setCursor(new Cursor(Cursor.HAND_CURSOR));
		_pnlReset = new JPanel();
		_pnlReset.setLayout(null);
		_pnlReset.setBounds(263, 321, 70, 24);
		_pnlReset.setBackground(new Color(255, 255, 255));
		_pnlReset.add(_lblReset);
		_pnlReset.setToolTipText("Resetar servidor WMS");
		_pnlReset.addMouseListener(this);

		set_okStatus(false);
		_lblFinalizar = new JLabel(createLinkText("Finalizar", "CFCFCF"));
		_lblFinalizar.setBackground(new Color(128, 128, 128));
		_lblFinalizar.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 13));
		_lblFinalizar.setBounds(4, 0, 50, 15);
		_lblFinalizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		_pnlFinalizar = new JPanel();
		_pnlFinalizar.setBounds(334, 318, 70, 24);
		_pnlFinalizar.setBackground(new Color(255, 255, 255));
		_pnlFinalizar.add(_lblFinalizar);
		_pnlFinalizar.setToolTipText("Finalizar seleção de tema e poder adicioná-lo.");
		_pnlFinalizar.addMouseListener(this);
		_pnlFinalizar.setVisible(false);
		_pnlCancel = new JPanel();
		_pnlCancel.setBounds(416, 318, 73, 24);
		_pnlCancel.setBackground(new Color(255, 255, 255));
		_pnlCancel.setToolTipText("Cancelar criação do tema e sair");
		_pnlCancel.addMouseListener(this);
		_pnlCancel.setVisible(false);

		_lblAdd = new JLabel(ImageIconLoader.createImageIcon("br/org/funcate/glue/image/right.gif", InterfaceWMS.class));
		_lblAdd.setBounds(178, 135, 20, 20);
		_lblAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
		_lblAdd.setToolTipText("Adicionar tema");
		_lblAdd.addMouseListener(this);

		_lblRemove = new JLabel(ImageIconLoader.createImageIcon("br/org/funcate/glue/image/left.gif", InterfaceWMS.class));
		_lblRemove.setBounds(178, 155, 20, 20);
		_lblRemove.setCursor(new Cursor(Cursor.HAND_CURSOR));
		_lblRemove.setToolTipText("Retirar tema");
		_lblRemove.addMouseListener(this);

		_lblUp = new JLabel(ImageIconLoader.createImageIcon("br/org/funcate/glue/image/up.gif", InterfaceWMS.class));
		_lblUp.setBounds(178, 175, 20, 20);
		_lblUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
		_lblUp.setToolTipText("Subir nível do tema");
		_lblUp.addMouseListener(this);

		_lblDown = new JLabel(ImageIconLoader.createImageIcon("br/org/funcate/glue/image/down.gif", InterfaceWMS.class));
		_lblDown.setBounds(178, 195, 20, 20);
		_lblDown.setCursor(new Cursor(Cursor.HAND_CURSOR));
		_lblDown.setToolTipText("Descer tema do nível");
		_lblDown.addMouseListener(this);

		_lblServ = new JLabel("Servidor WMS: ");
		_lblServ.setFont(new Font("SansSerif", Font.BOLD, 12));
		_lblServ.setBounds(5, 6, 105, 20);

		_lblEx = new JLabel("Ex: http://mapas.mma.gov.br/cgi-bin/mapserv?map=/opt/www/html/webservices/biorregioes.map&");
		_lblEx.setForeground(new Color(0, 0, 128));
		_lblEx.setBounds(8, 29, 510, 20);
		_lblEx.setFont(new Font("Serif", Font.ITALIC, 12));
		_lblEx.setCursor(new Cursor(Cursor.HAND_CURSOR));
		_lblEx.addMouseListener(this);

		_jtfSearch = new JTextField(null);
		_jtfSearch.setBounds(90, 3, 410, 26);

		_lblThemeName = new JLabel("Nome do tema: ");
		_lblThemeName.setBounds(2, 42, 105, 20);

		_lblSearch = new JLabel(ImageIconLoader.createImageIcon("br/org/funcate/glue/image/right.gif", InterfaceWMS.class));
		_lblSearch.setToolTipText("Carregar");
		_lblSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
		_lblSearch.setBounds(500, 8, 15, 15);
		_lblSearch.addMouseListener(this);

		_jtfSearch.addKeyListener(this);

		setFunctionalButtons(false);

		add(_lblServ);
		add(_jtfSearch);
		add(_lblSearch);
		add(_lblEx);
		add(_lblImageSelection);
		add(_lblAdd);
		add(_lblRemove);
		add(_lblUp);
		add(_lblDown);
		add(_pnlPreviewMap);
		add(_scrollNotSelectedLayers);
		add(_scrollSelectedLayers);
		add(_pnlReset);
		add(_canv);
		add(_pnlFinalizar);
		add(_pnlCancel);
		
		_lblCancel = new JLabel(createLinkText("Cancelar", "000080"));
		_lblCancel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 13));
		_pnlCancel.add(_lblCancel);
		_lblCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBounds(2, 2, 520, 360);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setVisible(true);
		labelReset();
		Utils.setScreenToCenter(this);
	}

	// ! change enable or disable functional buttons
	/*
	 * 
	 */

	public void setFunctionalButtons(boolean status) {
		_lblAdd.setEnabled(status);
		_lblRemove.setEnabled(status);
		_lblDown.setEnabled(status);
		_lblUp.setEnabled(status);
	}

	// ! call focus in search field
	/*
	 * 
	 */

	public void focusTextField() {
		_jtfSearch.requestFocus(true);
	}

	// ! create ImageIcon
	/*
	 * ! \param pImage directory of image
	 */

	protected static ImageIcon createImageIcon(String pImage) {
		java.net.URL imgURL = InterfaceWMS.class.getResource(pImage);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			// System.err.println("Couldn't find file: " + pImage);
			return null;
		}
	}

	// ! create appearance html link
	/*
	 * \param text is the text of the label
	 */

	public String createLinkText(String text, String cor) {
		text = "<html><font color=\"#" + cor + "\"><u>" + text + "</u></font></html>";
		return text;
	}

	// ! set this screen visible
	/*
	 * ! \param pVisible true or false visibility of screen
	 */
	public void enableWMS(boolean pVisible) {
		setBounds(5, 120, 520, 360);
		setVisible(pVisible);
	}

	// ! set the _urlWMS
	/*
	 * 
	 */
	public void generateURL() {
		_sFormat = (String) _cbImageFormat.getSelectedItem();
		_urlWMS = _xml.generateURL(_dlmSelectedNames, _sFormat);

	}

	// ! get a preview visualization of wms service
	/*
	 * 
	 */

	private void previewMap() {
		if (get_wmsSearched() == false) {
			LocalOptionPane.getInstance("Entre com um link de WMS.", "Glue - Serviço");
		} else {
			if (_okStatus == false) {
				LocalOptionPane.getInstance("Selecione um tema no mínimo ou modifique sua seleção!", "Glue - Temas");
			} else {
				set_sFormat((String) _cbImageFormat.getSelectedItem());
				_canv.preview(_xml.generatePreviewMap(_dlmSelectedNames, _sFormat), _minX, _minY, _maxX, _maxY);
			}
		}
	}

	// ! reset screen status of wms screen
	/*
	 * 
	 */

	@SuppressWarnings("rawtypes")
	public void labelReset() {
		remove(_canv);
		_dlmTitles.clear();
		_dlmSelectedTitles.clear();
		_dlmNames.clear();
		_dlmSelectedNames.clear();

		String[] _string = new String[0];
		_imageFormat = _string;

		_cbImageFormat = new JComboBox();
		_cbImageFormat.setVisible(false);
		_cbImageFormat.setBackground(new Color(214, 217, 223));
		_cbImageFormat.setBounds(23, 315, 147, 22);

		_canv = new CanvasPreview();
		_canv.setBounds(378, 100, 120, 120);

		add(_canv);
		add(_cbImageFormat);

		_jtfSearch.setText("");
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(128, 128, 128));
		separator.setBounds(365, 228, 138, 2);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.GRAY);
		separator_1.setBounds(365, 80, 138, 2);
		add(separator_1);

		set_wmsSearched(false);
		_selectedIndex = 0;
		_index2 = 0;
		_index1 = 0;

		repaint();
	}

	// ! finalize wms selection and plot ins canvas
	/*
	 * 
	 */

	public void labelFinalizar() {
		if (listener == 0) {
			if (get_wmsSearched() == false) {
				LocalOptionPane.getInstance("Entre com um link de WMS.", "Glue - Serviço");
			} else {
				if (_okStatus == false) {
					LocalOptionPane.getInstance("Selecione um tema no mínimo ou modifique sua seleção!", "Glue - Temas");
				} else {
					set_okPressed(true);

					set_sFormat((String) _cbImageFormat.getSelectedItem());
					set_urlWMS();

					set_okStatus(false);
					_lblFinalizar.setText(createLinkText("Finalizar", "CFCFCF"));
					this.labelReset();
				
					/*
					 * LocalOptionPane
					 * .getInstance("Clique em Adicionar para finalizar.");
					 */
				}
			}
		}
		if (listener == 1) {
			if (get_wmsSearched() == false) {
				LocalOptionPane.getInstance("Entre com um link de WMS.", "Glue - Serviço");
			} else {
				if (_okStatus == false) {
					LocalOptionPane.getInstance("Selecione um tema no mínimo ou modifique sua seleção!", "Glue - Temas");
				} else {
					set_okPressed(true);

					set_sFormat((String) _cbImageFormat.getSelectedItem());
					set_urlWMS();
					_xMin = get_minX();
					_yMin = get_minY();
					_xMax = get_maxX();
					_yMax = get_maxY();
					ImageSourceDefinition toolTheme = new ImageSourceDefinition(_xMin, _yMin, _xMax, _yMax, get_urlWMS(), TileType.WMS,
							null, null);
					Mediator mediator = AppSingleton.getInstance().getMediator();
					mediator.setToolBarSource(toolTheme);
					/*
					 * MainPanel.getInstance().getPnlToolbar().get_bar()
					 * .disableGoogle();
					 */
					notifyObserver();

					set_okStatus(false);

					notifyObserver();
					_lblFinalizar.setText(createLinkText("Finalizar", "CFCFCF"));
					this.labelReset();
					
					
					/*
					 * LocalOptionPane.getInstance(
					 * "Clique em Adicionar para finalizar." ,
					 * ThemeOptionsView.get_Location(), ThemeOptionsView
					 * .get_Width(), ThemeOptionsView.get_Heigth());
					 */
				}
			}
		}
	}

	// ! cancel and close wms selection
	/*
	 * 
	 */

	private void labelCancel() {
		enableWMS(false);
		ThemeOptionsView.cancelThemePane();
		ThemeOptionsView.setDefaultComboBox();
		this.labelReset();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getSource() == _pnlPreviewMap)
			_pnlPreviewMap.setBackground(new Color(135, 206, 255));
		if (arg0.getSource() == _pnlReset)
			_pnlReset.setBackground(new Color(135, 206, 255));
		if (arg0.getSource() == _pnlCancel)
			_pnlCancel.setBackground(new Color(135, 206, 255));
		if (arg0.getSource() == _pnlFinalizar)
			_pnlFinalizar.setBackground(new Color(135, 206, 255));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

		if (arg0.getSource() == _pnlPreviewMap)
			_pnlPreviewMap.setBackground(new Color(255, 255, 255));
		if (arg0.getSource() == _pnlReset)
			_pnlReset.setBackground(new Color(255, 255, 255));
		if (arg0.getSource() == _pnlCancel)
			_pnlCancel.setBackground(new Color(255, 255, 255));
		if (arg0.getSource() == _pnlFinalizar)
			_pnlFinalizar.setBackground(new Color(255, 255, 255));

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (arg0.getSource() == _pnlPreviewMap)
			previewMap();
		if (arg0.getSource() == _pnlReset)
			labelReset();
		if (listener == 0) {

			if (arg0.getSource() == _pnlCancel)
				labelCancel();
			if (arg0.getSource() == _pnlFinalizar)
					labelFinalizar();
		}
		if (listener == 1) {
			if (arg0.getSource() == _pnlCancel) {

				AppSingleton singleton = AppSingleton.getInstance();
				Mediator mediator = singleton.getMediator();
				mediator.setSelectedTool(ToolEnum.WMS);
				notifyObserver();
			}
			if (arg0.getSource() == _pnlFinalizar) {
				labelFinalizar();

			}
		}
		if (arg0.getSource() == _lblSearch) {
			doSearch();
		}
		if (arg0.getSource() == _lblEx) {
			_jtfSearch.setText("http://mapas.mma.gov.br/cgi-bin/mapserv?map=/opt/www/html/webservices/biorregioes.map&");
		}
		if (arg0.getSource() == _lblDown) {
			layerDown();
		}
		if (arg0.getSource() == _lblUp) {
			layerUp();
		}
		if (arg0.getSource() == _lblRemove) {
			layerRemove();
		}
		if (arg0.getSource() == _lblAdd) {
			layerAdd();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doSearch() {
		if (_jtfSearch.getText().equals("") || _jtfSearch.getText().equals(" ")) {
			LocalOptionPane.getInstance("Insira um servidor WMS.", "Glue - Serviço");
		} else {

			URL _url;
			try {
				_url = new URL(_jtfSearch.getText().trim() + "request=GetCapabilities&service=WMS&version=1.1.1");
				_xml = new XMLReader(_url);
				_xml.startXMLReader();
				_xml.populateWMSBox();

				_imageFormat = new String[_xml.get_cbImageFormt().size()];

				_dlmTitles.clear();

				for (int i = 0; i < _xml.get_jlNotSelectedTitles().size(); i++) {

					_dlmTitles.add(i, _xml.get_jlNotSelectedTitles().get(i));
					_dlmNames.add(i, _xml.get_jlNotSelectedLayers().get(i));
				}

				for (int i = 0; i < _xml.get_cbImageFormt().size(); i++) {

					_imageFormat[i] = _xml.get_cbImageFormt().get(i);
				}

				_index1 = _dlmTitles.getSize();
				_index2 = _dlmSelectedTitles.getSize();

				remove(_cbImageFormat);
				_cbImageFormat = new JComboBox(_imageFormat);
				_cbImageFormat.setBounds(25, 320, 140, 20);
				_cbImageFormat.setToolTipText("Selecione o formato de imagem");

				setFunctionalButtons(true);

				add(_cbImageFormat);

				set_wmsSearched(true);

				repaint();

			} catch (MalformedURLException e1) {
				LocalOptionPane.getInstance("Este servidor WMS parece estar corrompido, insira um servidor válido.",
						"Glue - Dados Corrompidos");
			} catch (java.lang.NullPointerException e1) {
				LocalOptionPane.getInstance("Este servidor WMS esta corrompido.", "Glue - Dados Corrompidos");
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void layerAdd() {
		if (_lblAdd.isEnabled()) {
			if (get_wmsSearched()) {
				try {
					if (_dlmTitles.getSize() == 0) {
						LocalOptionPane.getInstance("Acabaram os temas!", "Glue - Temas");
					} else {
						if (_jlLayers.getSelectedValue() == null) {
							LocalOptionPane.getInstance("Selecione um tema!", "Glue - Temas");
						} else {

							_lblFinalizar.setText(createLinkText("Finalizar", "000080"));
							set_okStatus(true);
							set_okPressed(false);

							_selectedIndex = _dlmTitles.indexOf(_jlLayers.getSelectedValue());

							_index2 = _dlmSelectedTitles.getSize();

							_dlmSelectedTitles.add(_index2, (_jlLayers.getSelectedValue()));
							_dlmSelectedNames.add(_index2, _dlmNames.elementAt(_jlLayers.getSelectedIndex()));
							_dlmTitles.remove(_selectedIndex);
							_dlmNames.remove(_selectedIndex);

							if (_dlmSelectedNames.getSize() == 0) {
								_lblFinalizar.setText(createLinkText("Finalizar", "CFCFCF"));
								set_okStatus(false);
							}
						}

					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e1) {
					LocalOptionPane.getInstance("Selecione um tema!", "Glue - Temas");
					if (_dlmSelectedNames.getSize() == 0) {
						_lblFinalizar.setText(createLinkText("Finalizar", "CFCFCF"));
						set_okStatus(false);
					}
				}
			} else {
				LocalOptionPane.getInstance("Entre com um servidor WMS.", "Glue - Serviço");
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void layerRemove() {
		if (_lblRemove.isEnabled()) {
			if (get_wmsSearched()) {
				try {
					if (_dlmSelectedTitles.getSize() == 0) {
						LocalOptionPane.getInstance("Selecione um tema!", "Glue - Temas");

					} else {

						if (_jlSelectedLayers.getSelectedValue() == null) {

						} else {

							_index1 = _dlmTitles.getSize();
							_selectedIndex = _dlmSelectedTitles.indexOf(_jlSelectedLayers.getSelectedValue());
							_dlmTitles.add(_index1, (_jlSelectedLayers.getSelectedValue()));
							_dlmNames.add(_index1, _dlmSelectedNames.elementAt(_jlSelectedLayers.getSelectedIndex()));
							_dlmSelectedTitles.remove(_selectedIndex);
							_dlmSelectedNames.remove(_selectedIndex);

							_lblFinalizar.setText(createLinkText("Finalizar", "000080"));
							set_okStatus(true);
							set_okPressed(false);

						}
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e1) {
					LocalOptionPane.getInstance("Selecione um tema!", "Glue - Temas");
					_lblFinalizar.setText(createLinkText("Finalizar", "CFCFCF"));
					set_okStatus(false);
				}
			} else {
				LocalOptionPane.getInstance("Entre com um servidor WMS.", "Glue - Serviço");
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void layerUp() {
		if (_lblUp.isEnabled()) {
			if (get_wmsSearched()) {
				try {
					_lblFinalizar.setText(createLinkText("Finalizar", "000080"));
					set_okStatus(true);
					set_okPressed(false);
					int _selectedListLayer = _jlSelectedLayers.getSelectedIndex();
					int _selectedListIndex = _jlSelectedLayers.getSelectedIndex() - 1;

					_dlmSelectedTitles.add(_selectedListIndex, _dlmSelectedTitles.elementAt(_selectedListLayer));
					_dlmSelectedNames.add(_selectedListIndex, _dlmSelectedNames.elementAt(_selectedListLayer));

					_dlmSelectedTitles.remove(_selectedListLayer + 1);
					_dlmSelectedNames.remove(_selectedListLayer + 1);

				} catch (java.lang.ArrayIndexOutOfBoundsException e1) {
					LocalOptionPane.getInstance("Selecione um tema!", "Glue - Temas");
				}
			} else {
				LocalOptionPane.getInstance("Entre com um servidor WMS.", "Glue - Serviço");
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void layerDown() {
		if (_lblDown.isEnabled()) {
			if (get_wmsSearched()) {
				try {
					_lblFinalizar.setText(createLinkText("Finalizar", "000080"));
					set_okStatus(true);
					set_okPressed(false);
					int _selectedListLayer = _jlSelectedLayers.getSelectedIndex();
					int _selectedListIndex = _jlSelectedLayers.getSelectedIndex() + 2;

					_dlmSelectedTitles.add(_selectedListIndex, _dlmSelectedTitles.elementAt(_selectedListLayer));
					_dlmSelectedNames.add(_selectedListIndex, _dlmSelectedNames.elementAt(_selectedListLayer));

					_dlmSelectedTitles.remove(_selectedListLayer);
					_dlmSelectedNames.remove(_selectedListLayer);

				} catch (java.lang.ArrayIndexOutOfBoundsException e1) {
					LocalOptionPane.getInstance("Selecione um tema!", "Glue - Temas");
				}
			} else {
				LocalOptionPane.getInstance("Entre com um servidor WMS.", "Glue - Serviço");
			}
		}
	}

	@Override
	public void addObserver(Observer pObserver) {
		_obsrv = pObserver;

	}

	@Override
	public void notifyObserver() {
		_obsrv.exit();

	}

	// ! set the number of listener to verify if origin is wms tree or wms
	// toolbar
	/*
	 * 
	 */

	public void setListener(int pListener) {
		listener = pListener;
		if (listener == 1) {
			_pnlCancel.setVisible(true);
			_pnlFinalizar.setVisible(true);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == _jtfSearch) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				doSearch();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}