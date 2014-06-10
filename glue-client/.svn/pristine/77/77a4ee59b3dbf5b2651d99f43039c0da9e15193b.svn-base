package br.org.funcate.glue.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.LookAndFeelService;
import br.org.funcate.glue.model.Representation;
import br.org.funcate.glue.model.ThemeVisual;
import br.org.funcate.glue.model.enumeration.RepresentationEnum;
import br.org.funcate.glue.model.thread.PlotterController;
import br.org.funcate.glue.model.tree.CustomNode;

/**
 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
 * 
 *         \brief Panel that contains visual options
 */

@SuppressWarnings("serial")
public class VisualOptionsView extends JFrame implements MouseListener {
	private static VisualOptionsView _instance;
	/** <Attribute VisualOptionsView (Singleton) */
	private static PolygonsPanel pnlPolygons;
	/** < Options Panel type attribute */
	private static LinesPanel pnlLines;
	/** < Options Panel type attribute */
	private static PointsPanel pnlPoints;
	/** < Options Panel type attribute */
	private static TextsPanel pnlTexts;
	/** < Options Panel type attribute */
	private JLabel lblConfirm;
	/** < Options Panel Label type attribute */
	private JLabel lblCancel;
	/** < Options Panel Label type attribute */
	private JLabel lblXExit;
	/** < Options Panel Label type attribute */
	private JPanel pnlConfirm;
	/** < Options Panel Label type attribute */
	private JPanel pnlCancel;
	/** < Options Panel Label type attribute */
	private JPanel pnlXExit;
	/** < Options Panel Label type attribute */

	private JPanel panelPrincipal = new JPanel();

	// Variable that represents the current theme
	private CustomNode node;
	private static ThemeVisual themeVisual;
	private static ThemeVisual themeLine;
	private static ThemeVisual themePoint;
	private static ThemeVisual themeText;
	static Color themeColor;
	static Color themeContour;
	static Color lineColor;
	static Color pointColor;
	static Color textColor;
	private int rep;

	/**
	 * This Singleton method of this class.
	 * 
	 * @return
	 */
	public static VisualOptionsView getInstance() {
		if (_instance == null) {
			_instance = new VisualOptionsView();
			_instance.setVisible(true);
			_instance.setFocusable(true);
		} else {

			_instance.dispose();
			_instance = new VisualOptionsView();
			_instance.setVisible(true);
			_instance.setFocusable(true);
		}
		return _instance;
	}

	/**
	 * This method returns the instance of this class
	 * 
	 * @return _instance
	 */
	static VisualOptionsView getVisualInstance() {
		return _instance;
	}

	/**
	 * @author Moraes, Emerson Leite
	 * @brief This method sets visible of pnlConfirm
	 * @param pVisible
	 */
	public void setPnlsVisible(boolean pVisible) {
		pnlConfirm.setVisible(pVisible);
		pnlCancel.setVisible(pVisible);
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Class constructor
	 */
	private VisualOptionsView() {

		// Setting basic informations about the panel like bounds, color and
		// layout
		setBounds(0, 0, 450, 400);
		setBackground(new Color(198, 226, 255));
		setLayout(null);
		setUndecorated(true);
		setAlwaysOnTop(true);

		LookAndFeelService.changeLookAndFeelForNimbus();

		panelPrincipal.setBounds(0, 0, 450, 400);
		panelPrincipal.setBackground(new Color(198, 226, 255));
		panelPrincipal.setLayout(null);

		// setDoubleBuffered(true);
		// setBorder(BorderFactory.createLineBorder(new Color(198, 226, 255)));

		DragPanel drag = new DragPanel(this);
		this.addMouseListener(drag);
		this.addMouseMotionListener(drag);

		try {
			// Create the Options Panel Objects
			pnlPolygons = new PolygonsPanel();
			pnlLines = new LinesPanel();
			pnlPoints = new PointsPanel();
			pnlTexts = new TextsPanel();

			// Sets Visible false in all panels
			disableAll();
			node = AppSingleton.getInstance().getMediator().getCurrentTheme();
			List<Representation> representations = node.getRepresentation();

			// Sets panels true
			rep = generateRepresentations(representations);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(this, "Este Tema não possui Representações");
			return;
		}
		// Create the Exit label and assign an event to it
		lblXExit = new JLabel("X");
		lblXExit.setBounds(4, 0, 15, 15);
		pnlXExit = new JPanel();
		pnlXExit.setBackground(new Color(198, 226, 255));
		pnlXExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnlXExit.setLayout(null);
		pnlXExit.setBounds(430, 3, 15, 15);
		pnlXExit.add(lblXExit);
		pnlXExit.addMouseListener(this);

		// Create the Confirm link label and assign an event to it
		lblConfirm = new JLabel(createLinkText("Confirmar"));
		lblConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblConfirm.setBounds(5, 0, 60, 19);
		pnlConfirm = new JPanel();
		pnlConfirm.setLayout(null);
		pnlConfirm.setBounds(1, 380, 69, 19);
		pnlConfirm.setBackground(new Color(198, 226, 255));
		pnlConfirm.add(lblConfirm);
		pnlConfirm.addMouseListener(this);

		// Create the Cancel link label and assign an event to it
		lblCancel = new JLabel(createLinkText("Cancelar"));
		lblCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblCancel.setBounds(5, 0, 60, 19);
		pnlCancel = new JPanel();
		pnlCancel.setLayout(null);
		pnlCancel.setBounds(70, 380, 60, 19);
		pnlCancel.setBackground(new Color(198, 226, 255));
		pnlCancel.add(lblCancel);
		pnlCancel.addMouseListener(this);

		// Add the link labels
		panelPrincipal.add(pnlConfirm);
		panelPrincipal.add(pnlCancel);
		panelPrincipal.add(pnlXExit);

		add(panelPrincipal);

		// MouseActionSuject.getInstance().pauseTimerThread();

		setLocation((int) (550 - this.getSize().getWidth() / 2), (int) (365 - this.getSize().getHeight() / 2));

		setVisible(true);

		/*
		 * try { ComponentWritter.writeInTextFile(new
		 * File("C:\\Documents and Settings\\Funcat\\Desktop\\teste.txt"),
		 * this); } catch (IOException e) {
		 * 
		 * <<<<<<< VisualOptionsView.java e.printStackTrace(); }
		 */

	}

	/**
	 * This method returns representation type of the Theme.
	 * 
	 * @param pRep
	 * @return
	 */
	public int generateRepresentations(List<Representation> pRep) {
		int reps = 0;
		for (int i = 0; i < pRep.size(); i++) {
			reps += pRep.get(i).getId();
		}
		setRepresentationsTrue(reps);
		return reps;
	}

	/**
	 * This method sets a Polygon Visual Screen.
	 */
	private void setPolygonsScreen() {
		themeVisual = node.getVisual().get(1);
		// themeVisual =
		// Principal._pnlTree.getCurrentTheme().getVisual().get(1);
		themeColor = new Color(themeVisual.getRed(), themeVisual.getGreen(), themeVisual.getBlue());
		themeContour = new Color(themeVisual.getCountourColorRed(), themeVisual.getCountourColorGreen(), themeVisual.getCountourColorBlue());
		// pnlPolygons.setVisible(true);
		panelPrincipal.add(pnlPolygons);
		pnlPolygons.sldTransparency.setValue(themeVisual.getTransparency());
		pnlPolygons.cboAreaStyle.setSelectedIndex(themeVisual.getStyle());
		pnlPolygons.cboContourStyle.setSelectedIndex(themeVisual.getStyleCountour());
		pnlPolygons.spnWidth.setValue(themeVisual.getWidthCountour());
		pnlPolygons.setVisualPreview();
		panelPrincipal.add(pnlPolygons.getLink());
	}

	/**
	 * This method sets a Lines Visual Screen.
	 */

	private void setLinesScreen() {
		themeLine = node.getVisual().get(2);
		// themeLine = Principal._pnlTree.getCurrentTheme().getVisual().get(2);
		lineColor = new Color(themeLine.getRed(), themeLine.getGreen(), themeLine.getBlue());
		pnlLines.setLineColors();
		pnlLines.cboWidth.setSelectedIndex(themeLine.getLineWidth() - 1);
		pnlLines.cboStyle.setSelectedIndex(themeLine.getStyle());
		panelPrincipal.add(pnlLines);
		panelPrincipal.add(pnlLines.getLink());
	}

	/**
	 * This method sets a Points Visual Screen.
	 */
	private void setPointsScreen() {
		themePoint = node.getVisual().get(4);
		// themePoint = Principal._pnlTree.getCurrentTheme().getVisual().get(4);
		pointColor = new Color(themePoint.getRed(), themePoint.getGreen(), themePoint.getBlue());
		pnlPoints.cboSize.setSelectedIndex(themePoint.getPointSize());
		pnlPoints.cboType.setSelectedIndex(themePoint.getStyle());
		pnlPoints.setPointsColor();
		panelPrincipal.add(pnlPoints);
		panelPrincipal.add(pnlPoints.getLink());

	}

	/**
	 * This method sets a Text Visual Screen.
	 */
	private void setTextsScreen() {
		themeText = node.getVisual().get(128);
		// themeText =
		// Principal._pnlTree.getCurrentTheme().getVisual().get(128);
		textColor = new Color(themeText.getRed(), themeText.getGreen(), themeText.getBlue());
		pnlTexts.setTextColor();
		pnlTexts.cbbFont.setSelectedItem(themeText.getFontName());
		pnlTexts.chkBold.setSelected(themeText.isBold());
		pnlTexts.chkItalic.setSelected(themeText.isItalic());
		pnlTexts.txtSize.setText(themeText.getPointSize().toString());
		panelPrincipal.add(pnlTexts);
		panelPrincipal.add(pnlTexts.getLink());
	}

	/**
	 * This method populates the screen according to the representations of the
	 * theme.
	 * 
	 * @param pRep
	 */
	public void setRepresentationsTrue(Integer pRep) {
		if((pRep.intValue() & RepresentationEnum.POLYGON.value())==RepresentationEnum.POLYGON.value())
		{
			setPolygonsScreen();
		}else{
			panelPrincipal.add(pnlPolygons.getLink());
			pnlPolygons.getLink().setEnabled(false);
		}
		
		if((pRep.intValue() & RepresentationEnum.LINE.value())==RepresentationEnum.LINE.value())
		{
			setLinesScreen();
		}else{
			panelPrincipal.add(pnlLines.getLink());
			pnlLines.getLink().setEnabled(false);
		}
		
		if((pRep.intValue() & RepresentationEnum.POINT.value())==RepresentationEnum.POINT.value())
		{
			setPointsScreen();
		}else{
			panelPrincipal.add(pnlPoints.getLink());
			pnlPoints.getLink().setEnabled(false);
		}
		
		if((pRep.intValue() & RepresentationEnum.TEXT.value())==RepresentationEnum.TEXT.value())
		{
			setTextsScreen();
		}else{
			panelPrincipal.add(pnlTexts.getLink());
			pnlTexts.getLink().setEnabled(false);
		}
		
		if((pRep.intValue() & RepresentationEnum.POLYGON.value())==RepresentationEnum.POLYGON.value())
			pnlPolygons.initThisComponent();
		else if((pRep.intValue() & RepresentationEnum.LINE.value())==RepresentationEnum.LINE.value())
			pnlLines.initThisComponent();
		else if((pRep.intValue() & RepresentationEnum.POINT.value())==RepresentationEnum.POINT.value())
			pnlPoints.initThisComponent();
		else if((pRep.intValue() & RepresentationEnum.TEXT.value())==RepresentationEnum.TEXT.value())
			pnlTexts.initThisComponent();
	}

	/**
	 * This method PanelPolygons color.
	 */
	public void setPanelColor() {

		pnlPolygons.cchColorChooser.setColor(themeVisual.getRed(), themeVisual.getGreen(), themeVisual.getBlue());

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that close the main panel
	 */
	public void closePanel() {
		// MouseActionSuject.getInstance().startTimerThread();

		setVisible(false);

	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 * 
	 *         \brief Method that finalize all the components
	 * 
	 */
	public static void disableAll() {

		pnlPolygons.finalizeThisComponent();
		pnlLines.finalizeThisComponent();
		pnlPoints.finalizeThisComponent();
		pnlTexts.finalizeThisComponent();

	}

	/**
	 * This method finalize Polygon's Screen.
	 */
	public void finalizePolygon() {
		themeVisual.setRed(themeColor.getRed());
		themeVisual.setGreen(themeColor.getGreen());
		themeVisual.setBlue(themeColor.getBlue());
		themeVisual.setCountourColorRed(themeContour.getRed());
		themeVisual.setCountourColorGreen(themeContour.getGreen());
		themeVisual.setCountourColorBlue(themeContour.getBlue());
		themeVisual.setTransparency(pnlPolygons.sldTransparency.getValue());
		themeVisual.setStyleCountour(pnlPolygons.cboContourStyle.getSelectedIndex());
		themeVisual.setStyle(pnlPolygons.cboAreaStyle.getSelectedIndex());
		themeVisual.setWidthCountour((Integer) pnlPolygons.spnWidth.getValue());

		node.setIsVisualChanged(true);

		node.rearrangeThemes();
		// Principal._pnlTree.getCurrentTheme().getVisual().put(1, themeVisual);
	}

	/**
	 * This method finalize Line's Screen.
	 */
	private void finalizeLine() {
		themeLine.setRed(lineColor.getRed());
		themeLine.setGreen(lineColor.getGreen());
		themeLine.setBlue(lineColor.getBlue());
		themeLine.setLineWidth(Integer.parseInt((String) pnlLines.cboWidth.getSelectedItem()));
		themeLine.setStyle(pnlLines.cboStyle.getSelectedIndex());

		node.setIsVisualChanged(true);
		node.rearrangeThemes();

		// Principal._pnlTree.getCurrentTheme().getVisual().put(2, themeLine);
	}

	/**
	 * This method finalize Point's Screen.
	 */
	private void finalizePoint() {
		themePoint.setRed(pointColor.getRed());
		themePoint.setGreen(pointColor.getGreen());
		themePoint.setBlue(pointColor.getBlue());
		themePoint.setPointSize(pnlPoints.cboSize.getSelectedIndex());
		themePoint.setStyle(pnlPoints.cboType.getSelectedIndex());

		node.setIsVisualChanged(true);
		node.rearrangeThemes();
		// Principal._pnlTree.getCurrentTheme().getVisual().put(4, themePoint);
	}

	/**
	 * This method finalize Text's Screen.
	 */
	private void finalizeText() {
		themeText.setRed(textColor.getRed());
		themeText.setGreen(textColor.getGreen());
		themeText.setBlue(textColor.getBlue());
		themeText.setFontName((String) pnlTexts.cbbFont.getSelectedItem());
		themeText.setBold(pnlTexts.chkBold.isSelected());
		themeText.setItalic(pnlTexts.chkItalic.isSelected());

		node.setIsVisualChanged(true);
		node.rearrangeThemes();
		// Principal._pnlTree.getCurrentTheme().getVisual().put(128, themeText);
	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that change the font of the String to looks like to
	 *         a link of internet
	 * 
	 *         \parm String parameter that will be transformed
	 */
	static String createLinkText(String text) {

		text = "<html><font color=\"#000080\"><u>" + text + "</u></font></html>";

		return text;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		// Action if the component is pnlCancel
		if (e.getSource() == pnlCancel)
			pnlCancel.setBackground(new Color(135, 206, 255));

		// Action if the component is pnlConfirm
		if (e.getSource() == pnlConfirm)
			pnlConfirm.setBackground(new Color(135, 206, 255));

		// Action if the component is pnlXExit
		if (e.getSource() == pnlXExit)
			pnlXExit.setBackground(new Color(135, 206, 255));
	}

	@Override
	public void mouseExited(MouseEvent e) {

		// Action if the component is pnlCancel
		if (e.getSource() == pnlCancel)
			pnlCancel.setBackground(new Color(198, 226, 255));

		// Action if the component is pnlConfirm
		if (e.getSource() == pnlConfirm)
			pnlConfirm.setBackground(new Color(198, 226, 255));

		// Action if the component is pnlXExit
		if (e.getSource() == pnlXExit)
			pnlXExit.setBackground(new Color(198, 226, 255));
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == pnlConfirm) {
			if((rep & RepresentationEnum.POLYGON.value()) == RepresentationEnum.POLYGON.value())
				finalizePolygon();
			if((rep & RepresentationEnum.LINE.value()) == RepresentationEnum.LINE.value())
				finalizeLine();
			if((rep & RepresentationEnum.POINT.value()) == RepresentationEnum.POINT.value())
				finalizePoint();
			if((rep & RepresentationEnum.TEXT.value()) == RepresentationEnum.TEXT.value())
				finalizeText();
			
			closePanel();
			
			CustomNode currentNode = AppSingleton.getInstance().getMediator().getNodeView(node);
			AppSingleton.getInstance().getMediator().setCurrentView(currentNode);
			currentNode.setIsNodeChanged(true);
			currentNode.setPersisted(false);
			currentNode.setApplied(false);

			PlotterController.getInstance().startPlotter();
			AppSingleton.getInstance().getMediator().repaint();
		}

		if (e.getSource() == pnlCancel) {
			LookAndFeelService.changeLookAndFeelForDefault();
			closePanel();
		}
		if (e.getSource() == pnlXExit) {
			LookAndFeelService.changeLookAndFeelForDefault();
			closePanel();
		}

	}
}

// -------------------------------------------------------------------------------------------

/**
 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
 * 
 *         \brief Class that return a JColorChooser component
 */
@SuppressWarnings("serial")
class ColorChooser extends JColorChooser {

	public ColorChooser() {

		setBounds(2, 2, 445, 320);
		setVisible(true);

	}

}

// -------------------------------------------------------------------------------------------------------

/**
 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
 * 
 *         \brief Class that provide a alpha effect
 */
@SuppressWarnings("serial")
class MyAlpha extends JLabel {

	float alpha = 0.5f;
	Graphics2D g1;
	int thickness = 1;
	int x = 1, y = 1, w = 98, h = 98;
	Color _backColor = Color.red;
	Color _borderColor = Color.black;

	public MyAlpha() {

		setBounds(100, 100, 100, 100);
		setLayout(null);
		setBorder(BorderFactory.createLineBorder(_borderColor, thickness));

	}

	public void paint(Graphics g) {

		super.paint(g);

		Graphics2D g1 = (Graphics2D) g;

		g1.setPaint(_backColor);
		g1.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g1.fillRect(x, y, w, h);

	}

	public void chageAlpha(float alpha) {
		this.alpha = alpha;
		repaint();
	}

	public void changeBackColor(Color color) {
		_backColor = color;
		repaint();
	}

	public void changeBorderColor(Color color) {
		_borderColor = color;
		setBorder(BorderFactory.createLineBorder(_borderColor, thickness));
		repaint();
	}

	public void changeThickness(int pThickness) {

		int newThickness = pThickness + (pThickness - 1);

		if (thickness != newThickness) {
			int oldThickness = thickness;
			thickness = newThickness;

			int q2 = (thickness - oldThickness);

			if (q2 > 0) {
				q2 -= 1;
			}

			else {
				q2 += 1;
			}

			// JOptionPane.showMessageDialog(null, this.getY()-q2);
			this.setBounds(this.getX() - q2, this.getY() - q2, this.getWidth() + (q2 * 2), this.getHeight() + (q2 * 2));
			x = thickness;
			y = thickness;
			w = this.getWidth() - (2 * thickness);
			h = this.getHeight() - (2 * thickness);
			setBorder(BorderFactory.createLineBorder(_borderColor, thickness));
			repaint();
		}

	}

}

// ---------------------------------------------------------------------------------------------
/**
 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
 * 
 *         \brief Panel related to polygons configurations options of a
 *         determined layer
 */
@SuppressWarnings("serial")
class PolygonsPanel extends JPanel implements MouseListener, ActionListener, ChangeListener {

	JLabel lblLabel;
	/** < Label type attribute */
	JPanel pnlLink;
	/** < Panel type attribute */
	int _x = 1;
	/** < Attribute that define the position X of the Panel */
	int _y = 20;
	/** < Attribute that define the position Y of the Panel */
	int _w = 448;
	/** < Attribute that define the width of the Panel */
	int _h = 360;
	/** < Attribute that define the height of the Panel */
	Color _color;
	/** < Attribute that define color of the Panel */
	JLabel lblInf1;
	JLabel lblAreaColor;
	JLabel lblTransparency;
	JLabel lblAreaStyle;
	JLabel lblInf2;
	JLabel lblContourColor;
	JLabel lblWidth;
	JLabel lblContourStye;
	JLabel lblResult;
	JLabel lblResultImage;
	JComboBox cboContourStyle;
	VisualPreview vpPolygonPane;

	JSpinner spnWidth;
	JSlider sldTransparency;
	JTextField txtTransp;
	MyAlpha myAlpha;
	JComboBox cboAreaStyle;
	ColorChooser cchColorChooser = null;
	JLabel lblApplyColor;
	JLabel lblCancelColorChooser;
	int _colorFlag;
	Integer _borderWidth = 2;
	Color _borderColor = new Color(0, 0, 0);

	public PolygonsPanel() {

		_color = new Color(255, 255, 255);

		setBounds(_x, _y, _w, _h);
		setBackground(_color);
		setVisible(false);
		setLayout(null);

		// Create the link label, the Polygons link panel and assign an event to
		// the Polygons link panel
		lblLabel = new JLabel(VisualOptionsView.createLinkText("Polígonos"));
		lblLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblLabel.setBounds(6, 0, 60, 19);
		pnlLink = new JPanel();
		pnlLink.add(lblLabel);
		pnlLink.setLayout(null);
		pnlLink.setBounds(1, 1, 69, 19);
		pnlLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnlLink.setBackground(new Color(255, 255, 255));
		pnlLink.addMouseListener(this);
		populatePanel();
	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that return the link panel
	 * 
	 */
	public JPanel getLink() {

		return pnlLink;

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method finalize this component
	 * 
	 */
	public void finalizeThisComponent() {

		this.setVisible(false);
		pnlLink.setBackground(new Color(198, 226, 255));

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method initialize this component
	 * 
	 */
	public void initThisComponent() {

		this.setVisible(true);
		pnlLink.setBackground(new Color(255, 255, 255));

	}

	public void showColorChooser() {

		cleanPanel();
		VisualOptionsView.getVisualInstance().setPnlsVisible(false);

		if (cchColorChooser != null) {
			cchColorChooser.setVisible(true);
			lblApplyColor.setVisible(true);
			lblCancelColorChooser.setVisible(true);
		} else {

			cchColorChooser = new ColorChooser();

			lblApplyColor = new JLabel(VisualOptionsView.createLinkText("Aplicar"));
			lblApplyColor.setBounds(300, 330, 50, 20);
			lblApplyColor.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblApplyColor.addMouseListener(this);

			lblCancelColorChooser = new JLabel(VisualOptionsView.createLinkText("Descartar"));
			lblCancelColorChooser.setBounds(350, 330, 60, 20);
			lblCancelColorChooser.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblCancelColorChooser.addMouseListener(this);

			add(cchColorChooser);
			repaint();
			add(lblApplyColor);
			add(lblCancelColorChooser);
		}

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that set invisible all components
	 * 
	 */
	public void cleanPanel() {

		lblInf1.setVisible(false);
		lblAreaColor.setVisible(false);
		lblTransparency.setVisible(false);
		lblAreaStyle.setVisible(false);
		lblInf2.setVisible(false);
		lblContourColor.setVisible(false);
		lblWidth.setVisible(false);
		lblContourStye.setVisible(false);
		lblResult.setVisible(false);
		lblResultImage.setVisible(false);
		cboContourStyle.setVisible(false);
		spnWidth.setVisible(false);
		sldTransparency.setVisible(false);
		cboAreaStyle.setVisible(false);
		txtTransp.setVisible(false);

		if (cchColorChooser != null) {
			cchColorChooser.setVisible(false);
			lblApplyColor.setVisible(false);
			lblCancelColorChooser.setVisible(false);
		}

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that set all components visible
	 * 
	 */
	public void setAllComponentsVisible() {

		lblInf1.setVisible(true);
		lblAreaColor.setVisible(true);
		lblTransparency.setVisible(true);
		lblAreaStyle.setVisible(true);
		lblInf2.setVisible(true);
		lblContourColor.setVisible(true);
		lblWidth.setVisible(true);
		lblContourStye.setVisible(true);
		lblResult.setVisible(true);
		lblResultImage.setVisible(true);
		cboContourStyle.setVisible(true);
		spnWidth.setVisible(true);
		sldTransparency.setVisible(true);
		cboAreaStyle.setVisible(true);
		txtTransp.setVisible(true);

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that manipulate the result image component
	 * 
	 */

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method populate this component
	 * 
	 */
	public void populatePanel() {

		String[] _areaStyleOptions = new String[] { "Transparente", "Sólido", "Horizontal", "Vertical", "FDiagonal", "BDiagonal", "Cruz",
				"Cruz Diagonal" };

		String[] _contourStyleOptions = new String[] { "Linha Solida", "Linha Tracejada", "Linha Pontilhada", "Linha Traço-Ponto",
				"Linha Traço-Ponto-Ponto", "Nenhum" };

		lblInf1 = new JLabel("Visual da Área do Polígono");
		lblInf1.setBounds(10, 10, 200, 20);

		lblAreaColor = new JLabel(VisualOptionsView.createLinkText("Alterar cor do desenho"));
		lblAreaColor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblAreaColor.setBounds(30, 40, 150, 20);
		lblAreaColor.addMouseListener(this);

		lblAreaStyle = new JLabel("Estilo:");
		lblAreaStyle.setBounds(30, 120, 50, 20);

		cboAreaStyle = new JComboBox(_areaStyleOptions);
		cboAreaStyle.setBounds(30, 150, 110, 20);
		cboAreaStyle.addActionListener(this);

		lblInf2 = new JLabel("Visual da Contorno do Polígono");
		lblInf2.setBounds(10, 200, 200, 20);

		lblContourColor = new JLabel(VisualOptionsView.createLinkText("Alterar cor do contorno"));
		lblContourColor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblContourColor.setBounds(30, 230, 150, 20);
		lblContourColor.addMouseListener(this);

		lblWidth = new JLabel("Largura:");
		lblWidth.setBounds(80, 260, 120, 20);

		lblTransparency = new JLabel("Transparência(%):");
		lblTransparency.setBounds(30, 70, 120, 20);

		txtTransp = new JTextField("50");
		txtTransp.setEnabled(true);
		txtTransp.setBounds(140, 70, 35, 25);
		txtTransp.addActionListener(this);

		sldTransparency = new JSlider();
		sldTransparency.setValue(50);
		sldTransparency.setBounds(30, 100, 150, 20);
		sldTransparency.setBackground(Color.white);
		sldTransparency.addChangeListener(this);

		spnWidth = new JSpinner();
		spnWidth.setValue(1);
		spnWidth.setBounds(140, 260, 50, 25);
		spnWidth.setToolTipText("Valores validos somente de 1~10");
		spnWidth.addChangeListener(this);

		lblContourStye = new JLabel("Estilo:");
		lblContourStye.setBounds(30, 290, 50, 20);

		cboContourStyle = new JComboBox(_contourStyleOptions);
		cboContourStyle.setBounds(30, 320, 160, 20);
		cboContourStyle.addActionListener(this);

		lblResult = new JLabel("Visual Resultante");
		lblResult.setBounds(300, 60, 100, 20);

		lblResultImage = new JLabel();
		lblResultImage.setOpaque(true);
		lblResultImage.setBounds(280, 80, 150, 150);
		lblResultImage.setBorder(BorderFactory.createLineBorder(Color.black));
		lblResultImage.setBackground(Color.white);

		// /////////////////////////////////////////////////////////////////////

		URL url1 = getClass().getClassLoader().getResource("br/org/funcate/glue/image/cristo.gif");
		ImageIcon _crist = null;
		try {
			_crist = new ImageIcon(getImg(url1));
		} catch (IOException e) {
			System.out.println("Img (cristo) não carregada");
			// e.printStackTrace();
		}
		lblResultImage.setIcon(_crist);

		// /////////////////////////////////////////////////////////////////////

		vpPolygonPane = new VisualPreview();
		vpPolygonPane.setBounds(25, 25, 101, 101);
		lblResultImage.add(vpPolygonPane);

		this.add(lblInf1);
		this.add(lblAreaColor);
		this.add(lblTransparency);
		this.add(sldTransparency);
		this.add(txtTransp);
		this.add(lblAreaStyle);
		this.add(cboAreaStyle);
		this.add(lblInf2);
		this.add(lblContourColor);
		this.add(lblWidth);
		this.add(spnWidth);
		this.add(lblContourStye);
		this.add(cboContourStyle);
		this.add(lblResult);
		this.add(lblResultImage);

	}

//	public void setMyAlpha() {
//		myAlpha.changeBackColor(VisualOptionsView.themeColor);
//		myAlpha.changeBorderColor(VisualOptionsView.themeContour);
//	}

	public static BufferedImage getImg(URL path) throws IOException {
		BufferedImage im = ImageIO.read(path);
		return im;
	}

	public void setVisualPreview() {
		vpPolygonPane.set_polygonColor(VisualOptionsView.themeColor);
		vpPolygonPane.set_polygonTrans(100 - sldTransparency.getValue());
		vpPolygonPane.set_polygonStyle(cboAreaStyle.getSelectedItem().toString());
		vpPolygonPane.set_borderColor(VisualOptionsView.themeContour);
		vpPolygonPane.set_borderSize((Integer) spnWidth.getValue());
		vpPolygonPane.set_borderStyle(cboContourStyle.getSelectedItem().toString());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

		if (arg0.getSource() == pnlLink && !pnlLink.isEnabled()) {
			pnlLink.setToolTipText("Este Tema não Possui Poligonos");
			return;
		}

		if (arg0.getSource() == pnlLink) {
			VisualOptionsView.disableAll();
			initThisComponent();
			repaint();
		}
		if (arg0.getSource() == lblApplyColor) {
			if (_colorFlag == 1) {
				// myAlpha.changeBackColor(cchColorChooser.getColor());
				vpPolygonPane.set_polygonColor(cchColorChooser.getColor());
				VisualOptionsView.themeColor = cchColorChooser.getColor();
				VisualOptionsView.getVisualInstance().setPnlsVisible(true);
			}

			if (_colorFlag == 2) {
				_borderColor = cchColorChooser.getColor();
				// myAlpha.changeBorderColor(_borderColor);
				vpPolygonPane.set_borderColor(_borderColor);
				VisualOptionsView.themeContour = cchColorChooser.getColor();
				VisualOptionsView.getVisualInstance().setPnlsVisible(true);

			}

			cleanPanel();
			setAllComponentsVisible();
			repaint();

		}
		if (arg0.getSource() == lblCancelColorChooser) {
			cleanPanel();
			setAllComponentsVisible();
			repaint();
			VisualOptionsView.getVisualInstance().setPnlsVisible(true);
		}

		if (arg0.getSource() == lblContourColor) {
			showColorChooser();
			cchColorChooser.setColor(VisualOptionsView.themeContour);
			_colorFlag = 2;

		}
		if (arg0.getSource() == lblAreaColor) {
			showColorChooser();
			cchColorChooser.setColor(VisualOptionsView.themeColor);
			_colorFlag = 1;

		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == txtTransp) {

			int temp = Integer.parseInt(txtTransp.getText());

			if (temp > 100) {
				temp = 100;
				txtTransp.setText("100");

			}
			if (temp < 0) {
				temp = 0;
				txtTransp.setText("0");

			}

			// myAlpha.chageAlpha(temp / 100);
			vpPolygonPane.set_polygonTrans(temp);
			sldTransparency.setValue(temp);

		}
		if (arg0.getSource() == cboAreaStyle) {
			if (cboAreaStyle.getSelectedItem().toString().equalsIgnoreCase("transparente")) {
				txtTransp.setEnabled(false);
				sldTransparency.setEnabled(false);
			} else {
				txtTransp.setEnabled(true);
				sldTransparency.setEnabled(true);
			}
			vpPolygonPane.set_polygonStyle(cboAreaStyle.getSelectedItem().toString());
		}

		if (arg0.getSource() == cboContourStyle) {
			vpPolygonPane.set_borderStyle(cboContourStyle.getSelectedItem().toString());
		}

	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Receive an url and return a Image Icon
	 */
	protected ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		}
		System.err.println("arquivo não encontrado: " + path);
		return null;
	}

	@Override
	public void stateChanged(ChangeEvent e) {

		if (e.getSource() == sldTransparency) {

			float x = sldTransparency.getValue();
			// myAlpha.chageAlpha((100 - x) / 100);
			Integer temp = sldTransparency.getValue();
			txtTransp.setText(temp.toString());
			vpPolygonPane.set_polygonTrans((int) (100 - x));

		}

		if (e.getSource() == spnWidth) {

			String temp = spnWidth.getValue().toString();
			int thickness = Integer.parseInt(temp);

			if (thickness > 10) {

				thickness = 10;
				spnWidth.setValue(10);

			}
			if (thickness < 1) {

				thickness = 1;
				spnWidth.setValue(1);

			}
			// myAlpha.changeThickness(thickness);
			vpPolygonPane.set_borderSize(thickness);

		}

	}

}

// -----------------------------------------------------------------------------------

/**
 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
 * 
 *         \brief Panel related to lines configurations options of a determined
 *         theme
 */
@SuppressWarnings("serial")
class LinesPanel extends JPanel implements MouseListener, ActionListener {

	JLabel lblLabel;
	/** < Label type attribute */
	JPanel pnlLink;
	/** < Panel type attribute */
	int _x = 1;
	/** < Attribute that define the position X of the Panel */
	int _y = 20;
	/** < Attribute that define the position Y of the Panel */
	int _w = 448;
	/** < Attribute that define the width of the Panel */
	int _h = 360;
	/** < Attribute that define the height of the Panel */
	Color _color;
	/** < Attribute that define color of the Panel */
	JLabel lblInf;
	/** < Label type attribute */
	JLabel lblLinesColor;
	/** < Label type attribute */
	JLabel lblWidth;
	/** < Label type attribute */
	JLabel lblStyle;
	/** < Label type attribute */
	JComboBox cboStyle;
	/** < ComboBox type attribute */
	JComboBox cboWidth;
	/** < ComboBox type attribute */
	JLabel lblResult;
	/** < Label type attribute */
	JPanel lblResultImage;
	/** < Label type attribute */
	JLabel lblApplyColor = null;
	/** < Label type attribute */
	JLabel lblCancelColorChooser = null;
	/** < Label type attribute */
	ColorChooser cchColorChooser = null;
	/** < ColorChooser type attribute */
	VisualPreview vpPreviewLine;

	JPanel lblResultImageLine;
	/** < Label type attribute */
	JPanel lblResultImageLine1;
	/** < Label type attribute */
	JPanel lblResultImageLine2;

	/** < Label type attribute */

	public LinesPanel() {

		_color = new Color(255, 255, 255);

		setBounds(_x, _y, _w, _h);
		setBackground(_color);
		setVisible(false);
		setLayout(null);
		populatePanel();

		// Create the link label, the Polygons link panel and assign an event to
		// the Polygons link panel
		lblLabel = new JLabel(VisualOptionsView.createLinkText("Linhas"));
		lblLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblLabel.setBounds(6, 0, 60, 19);
		pnlLink = new JPanel();
		pnlLink.setLayout(null);
		pnlLink.add(lblLabel);
		pnlLink.setBounds(70, 1, 50, 19);
		pnlLink.setBackground(new Color(198, 226, 255));
		pnlLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnlLink.addMouseListener(this);

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that return the link panel
	 * 
	 */
	public JPanel getLink() {

		return pnlLink;

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method finalize this component
	 * 
	 */
	public void finalizeThisComponent() {

		this.setVisible(false);
		pnlLink.setBackground(new Color(198, 226, 255));

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method initialize this component
	 * 
	 */
	public void initThisComponent() {

		this.setVisible(true);
		pnlLink.setBackground(new Color(255, 255, 255));

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that provide a color chooser
	 * 
	 */
	public void showColorChooser() {

		cleanPanel();

		VisualOptionsView.getVisualInstance().setPnlsVisible(false);

		if (cchColorChooser != null) {
			cchColorChooser.setVisible(true);
			lblApplyColor.setVisible(true);
			lblCancelColorChooser.setVisible(true);
		} else {

			cchColorChooser = new ColorChooser();

			lblApplyColor = new JLabel(VisualOptionsView.createLinkText("Aplicar"));
			lblApplyColor.setBounds(300, 330, 50, 20);
			lblApplyColor.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblApplyColor.addMouseListener(this);

			lblCancelColorChooser = new JLabel(VisualOptionsView.createLinkText("Descartar"));
			lblCancelColorChooser.setBounds(350, 330, 60, 20);
			lblCancelColorChooser.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblCancelColorChooser.addMouseListener(this);

			add(cchColorChooser);
			repaint();
			add(lblApplyColor);
			add(lblCancelColorChooser);
		}

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that set invisible all components
	 * 
	 */
	public void cleanPanel() {

		lblInf.setVisible(false);
		lblLinesColor.setVisible(false);
		lblWidth.setVisible(false);
		lblStyle.setVisible(false);
		cboStyle.setVisible(false);
		cboWidth.setVisible(false);
		lblResult.setVisible(false);
		lblResultImage.setVisible(false);

		if (cchColorChooser != null) {
			cchColorChooser.setVisible(false);
			lblApplyColor.setVisible(false);
			lblCancelColorChooser.setVisible(false);
		}

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that set all components visible
	 * 
	 */
	public void setAllComponentsVisible() {

		lblInf.setVisible(true);
		lblLinesColor.setVisible(true);
		lblWidth.setVisible(true);
		lblStyle.setVisible(true);
		cboStyle.setVisible(true);
		cboWidth.setVisible(true);
		lblResult.setVisible(true);
		lblResultImage.setVisible(true);

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that manipulate the result image component
	 * 
	 */
	public void manipulateResultImage(String h) {

		Integer h1 = Integer.parseInt(h);
		if (h1 > 20) {
			lblResultImageLine.setSize(130, 20);
			lblResultImageLine1.setSize(130, 20);
			lblResultImageLine2.setSize(130, 20);
		} else if (h1 < 1) {
			lblResultImageLine.setSize(130, 1);
			lblResultImageLine1.setSize(130, 1);
			lblResultImageLine2.setSize(130, 1);
		} else {
			lblResultImageLine.setSize(130, h1);
			lblResultImageLine1.setSize(130, h1);
			lblResultImageLine2.setSize(130, h1);
		}

		repaint();

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method populate this component
	 * 
	 */
	public void populatePanel() {

		String[] _contourStyleOptions = new String[] { "Linha Solida", "Linha Tracejada", "Linha Pontilhada", "Linha Traço-Ponto",
				"Linha Traço-Ponto-Ponto", "Nenhum", "Customizado", "Oriented Line", "Oriented Middle Line" };

		String[] _widthValues = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20" };

		lblInf = new JLabel("Visual das Linhas");
		lblInf.setBounds(10, 10, 200, 20);

		lblLinesColor = new JLabel(VisualOptionsView.createLinkText("Cor das linhas"));
		lblLinesColor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblLinesColor.setBounds(30, 40, 150, 20);
		lblLinesColor.addMouseListener(this);

		lblStyle = new JLabel("Estilo:");
		lblStyle.setBounds(30, 100, 50, 20);

		lblWidth = new JLabel("Largura:");
		lblWidth.setBounds(30, 70, 120, 20);

		cboWidth = new JComboBox(_widthValues);
		cboWidth.setBounds(100, 70, 50, 20);
		cboWidth.setSelectedIndex(5);
		cboWidth.addActionListener(this);
		cboWidth.setEditable(true);

		cboStyle = new JComboBox(_contourStyleOptions);
		cboStyle.setBounds(30, 130, 160, 20);
		cboStyle.addActionListener(this);

		lblResult = new JLabel("Visual Resultante");
		lblResult.setBounds(300, 60, 100, 20);

		vpPreviewLine = new VisualPreview(VisualOptionsView.lineColor, cboWidth.getSelectedIndex(), cboStyle.getSelectedItem().toString());
		vpPreviewLine.setBounds(0, 0, 150, 150);
		vpPreviewLine.setVisible(true);

		lblResultImage = new JPanel();
		lblResultImage.setBounds(280, 80, 150, 150);
		lblResultImage.setLayout(null);
		lblResultImage.setBorder(BorderFactory.createLineBorder(Color.black));
		lblResultImage.setBackground(Color.white);
		lblResultImage.add(vpPreviewLine);

		this.add(lblInf);
		this.add(lblLinesColor);
		this.add(lblStyle);
		this.add(lblWidth);
		this.add(cboWidth);
		this.add(cboStyle);
		this.add(lblResult);
		this.add(lblResultImage);

	}

	public void setLineColors() {
		vpPreviewLine.set_pCor(VisualOptionsView.lineColor);
		vpPreviewLine.set_pSize(cboWidth.getSelectedIndex());
		vpPreviewLine.set_pType(cboStyle.getSelectedItem().toString());
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

		if (e.getSource() == pnlLink && !pnlLink.isEnabled()) {
			pnlLink.setToolTipText("Este Tema não Possui Linhas");
			return;
		}
		if (e.getSource() == pnlLink) {
			VisualOptionsView.disableAll();
			initThisComponent();
			repaint();
		}
		if (e.getSource() == lblLinesColor) {
			showColorChooser();
			cchColorChooser.setColor(VisualOptionsView.lineColor);
		}
		if (e.getSource() == lblApplyColor) {
			VisualOptionsView.lineColor = cchColorChooser.getColor();
			cleanPanel();
			setAllComponentsVisible();
			VisualOptionsView.getVisualInstance().setPnlsVisible(true);
			repaint();
			vpPreviewLine.set_pCor(VisualOptionsView.lineColor);
		}
		if (e.getSource() == lblCancelColorChooser) {
			cleanPanel();
			setAllComponentsVisible();
			VisualOptionsView.getVisualInstance().setPnlsVisible(true);
			repaint();

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == cboWidth) {
			vpPreviewLine.set_pSize(cboWidth.getSelectedIndex());
		}
		if (e.getSource() == cboStyle) {
			vpPreviewLine.set_pType(cboStyle.getSelectedItem().toString());
		}
	}

}

// --------------------------------------------------------------------------------------------------

/**
 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
 * 
 *         \brief Panel related to points configurations options of a determined
 *         theme
 */
@SuppressWarnings("serial")
class PointsPanel extends JPanel implements MouseListener, ActionListener {

	JLabel lblLabel;
	/** < Label type attribute */
	JPanel pnlLink;
	/** < Panel type attribute */
	int _x = 1;
	/** < Attribute that define the position X of the Panel */
	int _y = 20;
	/** < Attribute that define the position Y of the Panel */
	int _w = 448;
	/** < Attribute that define the width of the Panel */
	int _h = 360;
	/** < Attribute that define the height of the Panel */
	Color _color;
	/** < Attribute that define color of the Panel */
	JLabel lblInf;
	JLabel lblPointColor;
	JLabel lblSize;
	JLabel lblType;
	JLabel lblResult;
	// JLabel lblResultImage;
	JPanel lblResultImage;
	JComboBox cboType;
	JComboBox cboSize;
	JLabel lblApplyColor = null;
	JLabel lblCancelColorChooser = null;
	ColorChooser cchColorChooser = null;
	VisualPreview vpPreviewPoint;

	public PointsPanel() {

		_color = new Color(255, 255, 255);

		setBounds(_x, _y, _w, _h);
		setBackground(_color);
		setVisible(false);
		setLayout(null);
		populatePanel();

		// Create the link label, the Polygons link panel and assign an event to
		// the Polygons link panel
		lblLabel = new JLabel(VisualOptionsView.createLinkText("Pontos"));
		lblLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblLabel.setBounds(5, 0, 60, 19);
		pnlLink = new JPanel();
		pnlLink.add(lblLabel);
		pnlLink.setLayout(null);
		pnlLink.setBounds(120, 1, 50, 19);
		pnlLink.setBackground(new Color(198, 226, 255));
		pnlLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnlLink.addMouseListener(this);

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that return the link panel
	 * 
	 */
	public JPanel getLink() {

		return pnlLink;

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method finalize this component
	 * 
	 */
	public void finalizeThisComponent() {

		this.setVisible(false);
		pnlLink.setBackground(new Color(198, 226, 255));

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method initialize this component
	 * 
	 */
	public void initThisComponent() {

		this.setVisible(true);
		pnlLink.setBackground(new Color(255, 255, 255));

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that provide a color chooser
	 * 
	 */
	public void showColorChooser() {

		cleanPanel();

		VisualOptionsView.getVisualInstance().setPnlsVisible(false);

		if (cchColorChooser != null) {
			cchColorChooser.setVisible(true);
			lblApplyColor.setVisible(true);
			lblCancelColorChooser.setVisible(true);
		} else {

			cchColorChooser = new ColorChooser();

			lblApplyColor = new JLabel(VisualOptionsView.createLinkText("Aplicar"));
			lblApplyColor.setBounds(300, 330, 50, 20);
			lblApplyColor.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblApplyColor.addMouseListener(this);

			lblCancelColorChooser = new JLabel(VisualOptionsView.createLinkText("Descartar"));
			lblCancelColorChooser.setBounds(350, 330, 60, 20);
			lblCancelColorChooser.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblCancelColorChooser.addMouseListener(this);

			add(cchColorChooser);
			repaint();
			add(lblApplyColor);
			add(lblCancelColorChooser);
		}

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that set invisible all components
	 * 
	 */
	public void cleanPanel() {

		lblInf.setVisible(false);
		lblPointColor.setVisible(false);
		lblSize.setVisible(false);
		lblType.setVisible(false);
		lblResult.setVisible(false);
		lblResultImage.setVisible(false);
		cboType.setVisible(false);
		cboSize.setVisible(false);

		if (cchColorChooser != null) {
			cchColorChooser.setVisible(false);
			lblApplyColor.setVisible(false);
			lblCancelColorChooser.setVisible(false);
		}

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that set all components visible
	 * 
	 */
	public void setAllComponentsVisible() {

		lblInf.setVisible(true);
		lblPointColor.setVisible(true);
		lblSize.setVisible(true);
		lblType.setVisible(true);
		lblResult.setVisible(true);
		lblResultImage.setVisible(true);
		cboType.setVisible(true);
		cboSize.setVisible(true);

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method populate this component
	 * 
	 */
	public void populatePanel() {

		String[] _sizeValues = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "19", "20" };

		String[] _typeOptions = new String[] { "Cruz", "Estrela", "Círculo", "X", "Caixa", "Losango", "Círculo Vazado", "Caixa Vazado",
				"Losango Vazado" };

		lblInf = new JLabel("Visual dos Pontos");
		lblInf.setBounds(10, 10, 200, 20);

		lblPointColor = new JLabel(VisualOptionsView.createLinkText("Alterar cor dos pontos"));
		lblPointColor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblPointColor.setBounds(30, 40, 150, 20);
		lblPointColor.addMouseListener(this);

		lblSize = new JLabel("Tamanho:");
		lblSize.setBounds(30, 70, 120, 20);

		cboSize = new JComboBox(_sizeValues);
		cboSize.setBounds(140, 70, 50, 20);
		cboSize.addActionListener(this);

		lblType = new JLabel("Tipo:");
		lblType.setBounds(30, 100, 50, 20);

		cboType = new JComboBox(_typeOptions);
		cboType.setBounds(30, 130, 110, 20);
		cboType.addActionListener(this);

		lblResult = new JLabel("Visual Resultante");
		lblResult.setBounds(300, 60, 100, 20);

		vpPreviewPoint = new VisualPreview(VisualOptionsView.pointColor, cboSize.getSelectedIndex(), cboType.getSelectedItem().toString());
		vpPreviewPoint.setBounds(0, 0, 150, 150);
		vpPreviewPoint.setVisible(true);

		lblResultImage = new JPanel();
		lblResultImage.setBounds(280, 80, 150, 150);
		lblResultImage.setLayout(null);
		lblResultImage.setBorder(BorderFactory.createLineBorder(Color.black));
		lblResultImage.setBackground(Color.white);
		lblResultImage.add(vpPreviewPoint);

		this.add(lblInf);
		this.add(lblPointColor);
		this.add(lblSize);
		this.add(cboSize);
		this.add(lblType);
		this.add(cboType);
		this.add(lblResult);
		this.add(lblResultImage);
	}

	public void setPointsColor() {
		vpPreviewPoint.set_pCor(VisualOptionsView.pointColor);
		vpPreviewPoint.set_pSize(cboSize.getSelectedIndex());
		vpPreviewPoint.set_pType(cboType.getSelectedItem().toString());
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

		if (e.getSource() == pnlLink && !pnlLink.isEnabled()) {
			pnlLink.setToolTipText("Este Tema não Possui Pontos");
			return;
		}

		if (e.getSource() == pnlLink) {
			VisualOptionsView.disableAll();
			initThisComponent();
			repaint();
		}
		if (e.getSource() == lblApplyColor) {
			lblResultImage.setBackground(cchColorChooser.getColor());

			VisualOptionsView.pointColor = cchColorChooser.getColor();

			cleanPanel();
			setAllComponentsVisible();
			VisualOptionsView.getVisualInstance().setPnlsVisible(true);
			repaint();
			vpPreviewPoint.set_pCor(VisualOptionsView.pointColor);
		}
		if (e.getSource() == lblCancelColorChooser) {
			cleanPanel();
			setAllComponentsVisible();
			VisualOptionsView.getVisualInstance().setPnlsVisible(true);
			repaint();
		}
		if (e.getSource() == lblPointColor) {
			showColorChooser();
			cchColorChooser.setColor(VisualOptionsView.pointColor);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == cboSize) {
			vpPreviewPoint.set_pSize(cboSize.getSelectedIndex());
		}
		if (e.getSource() == cboType) {
			vpPreviewPoint.set_pType(cboType.getSelectedItem().toString());
		}
	}

}

// ------------------------------------------------------------------------------------------------------

/**
 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
 * 
 *         \brief Panel related to texts configurations options of a determined
 *         theme
 */
@SuppressWarnings("serial")
class TextsPanel extends JPanel implements MouseListener, ActionListener {

	JLabel lblLabel;
	/** < Label type attribute */
	JPanel pnlLink;
	/** < Panel type attribute */
	int _x = 1;
	/** < Attribute that define the position X of the Panel */
	int _y = 20;
	/** < Attribute that define the position Y of the Panel */
	int _w = 448;
	/** < Attribute that define the width of the Panel */
	int _h = 360;
	/** < Attribute that define the height of the Panel */
	Color _color;
	/** < Attribute that define color of the Panel */
	JLabel lblInf;
	/** < Label type attribute */
	JLabel lblTextColor;
	/** < Label type attribute */
	JLabel lblFont;
	/** /**< Label type attribute */
	JLabel lblSize;
	/** < Label type attribute */
	JTextField txtSize;
	/** < Text field attribut */
	JLabel lblStyle;
	/** < Label type attribute */
	JCheckBox chkBold;
	/** < Check box attribute */
	JCheckBox chkItalic;
	/** < Check box attribute */
	JLabel lblBold;
	/** < Label type attribute */
	JLabel lblItalic;
	/** < Label type attribute */
	JLabel lblResult;
	/** < Label type attribute */
	JLabel lblResultImage;
	/** < Label type attribute */
	JComboBox cbbFont;
	ColorChooser cchColorChooser = null;
	/** < Color chooser atribute */
	JLabel lblApplyColor;
	/** < Label type attribute */
	JLabel lblCancelColorChooser;
	/** < Label type attribute */
	JLabel lblFont1;
	/** < Label type attribute */
	JLabel lblFont2;
	/** < Label type attribute */
	Color _txtColor = new Color(0, 0, 0);
	/** < Color type attribute */
	String _fontName = "arial";
	/** < String that define the name of the font */
	int _fontSize = 12;
	/** < Int that define the size of the font */
	int _fontType = Font.TRUETYPE_FONT;

	/** < Int that define the type of the font */

	public TextsPanel() {

		_color = new Color(255, 255, 255);

		setBounds(_x, _y, _w, _h);
		setBackground(_color);
		setVisible(false);
		setLayout(null);
		populatePanel();

		// Create the link label, the Polygons link panel and assign an event to
		// the Polygons link panel
		lblLabel = new JLabel(VisualOptionsView.createLinkText("Textos"));
		lblLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblLabel.setBounds(5, 0, 60, 19);
		pnlLink = new JPanel();
		pnlLink.add(lblLabel);
		pnlLink.setLayout(null);
		pnlLink.setBounds(170, 1, 50, 19);
		pnlLink.setBackground(new Color(198, 226, 255));
		pnlLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnlLink.addMouseListener(this);

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that return the link panel
	 * 
	 */
	public JPanel getLink() {

		return pnlLink;

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method finalize this component
	 * 
	 */
	public void finalizeThisComponent() {

		this.setVisible(false);
		pnlLink.setBackground(new Color(198, 226, 255));

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method initialize this component
	 * 
	 */
	public void initThisComponent() {

		this.setVisible(true);
		pnlLink.setBackground(new Color(255, 255, 255));

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that provide a color chooser
	 * 
	 */
	public void showColorChooser() {

		cleanPanel();

		VisualOptionsView.getVisualInstance().setPnlsVisible(false);

		if (cchColorChooser != null) {
			cchColorChooser.setVisible(true);
			lblApplyColor.setVisible(true);
			lblCancelColorChooser.setVisible(true);
		} else {

			cchColorChooser = new ColorChooser();

			lblApplyColor = new JLabel(VisualOptionsView.createLinkText("Aplicar"));
			lblApplyColor.setBounds(300, 330, 50, 20);
			lblApplyColor.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblApplyColor.addMouseListener(this);

			lblCancelColorChooser = new JLabel(VisualOptionsView.createLinkText("Descartar"));
			lblCancelColorChooser.setBounds(350, 330, 60, 20);
			lblCancelColorChooser.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblCancelColorChooser.addMouseListener(this);

			add(cchColorChooser);
			repaint();
			add(lblApplyColor);
			add(lblCancelColorChooser);
		}

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that set invisible all components
	 * 
	 */
	public void cleanPanel() {

		lblInf.setVisible(false);
		lblTextColor.setVisible(false);
		lblFont.setVisible(false);
		lblSize.setVisible(false);
		txtSize.setVisible(false);
		lblStyle.setVisible(false);
		chkBold.setVisible(false);
		chkItalic.setVisible(false);
		lblBold.setVisible(false);
		lblItalic.setVisible(false);
		lblResult.setVisible(false);
		lblResultImage.setVisible(false);
		cbbFont.setVisible(false);

		if (cchColorChooser != null) {
			cchColorChooser.setVisible(false);
			lblApplyColor.setVisible(false);
			lblCancelColorChooser.setVisible(false);
		}

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method that set all components visible
	 * 
	 */
	public void setAllComponentsVisible() {

		lblInf.setVisible(true);
		lblTextColor.setVisible(true);
		lblFont.setVisible(true);
		lblSize.setVisible(true);
		txtSize.setVisible(true);
		lblStyle.setVisible(true);
		chkBold.setVisible(true);
		chkItalic.setVisible(true);
		lblBold.setVisible(true);
		lblItalic.setVisible(true);
		lblResult.setVisible(true);
		lblResultImage.setVisible(true);
		cbbFont.setVisible(true);

	}

	/**
	 * @author Siqueira, Felipe V.
	 * 
	 *         \brief Method populate this component
	 * 
	 */
	public void populatePanel() {

		lblInf = new JLabel("Visual dos textos");
		lblInf.setBounds(10, 10, 200, 20);

		lblTextColor = new JLabel(VisualOptionsView.createLinkText("Alterar cor do texto"));
		lblTextColor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblTextColor.setBounds(30, 40, 150, 20);
		lblTextColor.addMouseListener(this);

		lblFont = new JLabel("Fonte: ");
		lblFont.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblFont.setBounds(30, 70, 150, 20);

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

		String[] fonts = ge.getAvailableFontFamilyNames();
		cbbFont = new JComboBox(fonts);
		cbbFont.setBounds(30, 90, 150, 20);
		cbbFont.addActionListener(this);

		lblSize = new JLabel("Tamanho:");
		lblSize.setBounds(30, 130, 150, 20);

		txtSize = new JTextField();
		txtSize.setBounds(90, 130, 30, 25);
		txtSize.addActionListener(this);

		lblStyle = new JLabel("Estilo:");
		lblStyle.setBounds(30, 160, 150, 20);

		chkBold = new JCheckBox();
		chkBold.setBounds(75, 190, 20, 20);
		chkBold.setBackground(new Color(255, 255, 255));
		chkBold.addActionListener(this);

		lblBold = new JLabel("Negrito");
		lblBold.setBounds(30, 190, 100, 20);

		lblItalic = new JLabel("Italico");
		lblItalic.setBounds(100, 190, 100, 20);

		chkItalic = new JCheckBox();
		chkItalic.setBounds(140, 190, 20, 20);
		chkItalic.setBackground(new Color(255, 255, 255));
		chkItalic.addActionListener(this);

		lblResult = new JLabel("Visual Resultante");
		lblResult.setBounds(300, 60, 100, 20);

		lblResultImage = new JLabel();
		lblResultImage.setOpaque(false);
		lblResultImage.setBounds(280, 80, 150, 150);
		lblResultImage.setBorder(BorderFactory.createLineBorder(Color.black));
		lblResultImage.setBackground(Color.black);

		lblFont1 = new JLabel("ABCD abcd");
		lblFont1.setBounds(10, 20, 130, 50);
		lblFont1.setForeground(_txtColor);
		lblFont1.setFont(new Font(_fontName, _fontType, _fontSize));

		lblFont2 = new JLabel("ABCD abcd");
		lblFont2.setBounds(10, 80, 130, 50);
		lblFont2.setForeground(_txtColor);
		lblFont2.setFont(new Font(_fontName, _fontType, _fontSize));

		lblResultImage.add(lblFont1);
		lblResultImage.add(lblFont2);

		this.add(lblInf);
		this.add(lblTextColor);
		this.add(lblFont);
		this.add(cbbFont);
		this.add(lblSize);
		this.add(txtSize);
		this.add(chkBold);
		this.add(chkItalic);
		this.add(lblBold);
		this.add(lblItalic);
		this.add(lblStyle);
		this.add(lblResult);
		this.add(lblResultImage);

	}

	public void setTextColor() {
		lblFont1.setForeground(VisualOptionsView.textColor);
		lblFont2.setForeground(VisualOptionsView.textColor);
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

		if (e.getSource() == pnlLink && !pnlLink.isEnabled()) {
			pnlLink.setToolTipText("Este Tema não Possui Texto");
			return;
		}

		if (e.getSource() == pnlLink) {
			VisualOptionsView.disableAll();
			initThisComponent();
			repaint();
		}
		if (e.getSource() == lblApplyColor) {
			_txtColor = cchColorChooser.getColor();
			lblFont1.setForeground(_txtColor);
			lblFont2.setForeground(_txtColor);

			VisualOptionsView.textColor = cchColorChooser.getColor();

			cleanPanel();
			setAllComponentsVisible();
			VisualOptionsView.getVisualInstance().setPnlsVisible(true);
			repaint();

		}
		if (e.getSource() == lblCancelColorChooser) {
			cleanPanel();
			setAllComponentsVisible();
			VisualOptionsView.getVisualInstance().setPnlsVisible(true);
			repaint();
		}
		if (e.getSource() == lblTextColor) {
			showColorChooser();
			cchColorChooser.setColor(VisualOptionsView.textColor);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == chkBold) {

			if (chkBold.isSelected()) {
				if (chkItalic.isSelected()) {
					_fontType = Font.BOLD + Font.ITALIC;
				} else {
					_fontType = Font.BOLD;
				}

			} else {

				if (chkItalic.isSelected()) {
					_fontType = Font.ITALIC;
				} else {
					_fontType = Font.TRUETYPE_FONT;
				}

			}

			lblFont1.setFont(new Font(_fontName, _fontType, _fontSize));
			lblFont2.setFont(new Font(_fontName, _fontType, _fontSize));

		}

		if (arg0.getSource() == chkItalic) {

			if (chkItalic.isSelected()) {
				if (chkBold.isSelected()) {
					_fontType = Font.BOLD + Font.ITALIC;
				} else {
					_fontType = Font.ITALIC;
				}

			} else {

				if (chkBold.isSelected()) {
					_fontType = Font.BOLD;
				} else {
					_fontType = Font.TRUETYPE_FONT;
				}

			}

			lblFont1.setFont(new Font(_fontName, _fontType, _fontSize));
			lblFont2.setFont(new Font(_fontName, _fontType, _fontSize));

		}
		if (arg0.getSource() == txtSize) {

			int size = Integer.parseInt(txtSize.getText());
			if (size > 50)
				size = 50;
			if (size < 8)
				size = 8;

			_fontSize = size;

			lblFont1.setFont(new Font(_fontName, _fontType, _fontSize));
			lblFont2.setFont(new Font(_fontName, _fontType, _fontSize));

		}
		if (arg0.getSource() == cbbFont) {

			_fontName = cbbFont.getSelectedItem().toString();

			lblFont1.setFont(new Font(_fontName, _fontType, _fontSize));
			lblFont2.setFont(new Font(_fontName, _fontType, _fontSize));

		}

	}

}