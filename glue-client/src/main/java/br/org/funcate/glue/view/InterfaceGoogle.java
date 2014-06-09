package br.org.funcate.glue.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.org.funcate.glue.controller.listener.InterfaceGoogleListener;
import br.org.funcate.glue.model.LookAndFeelService;

@SuppressWarnings("serial")
public class InterfaceGoogle extends JFrame {

	private static InterfaceGoogle instance;

	private JLabel lblGoogleFinalizar, lblGoogleCancelar, lblMapGoogle,
			lblSatGoogle, lblHybridGoogle, lblTerGoogle, lblTitleGoogle,
			lblMapNameGoogle, lblSatNameGoogle, lblHybridNameGoogle,
			lblTerNameGoogle, lblUseTerms;
	/** <Attribute type JLabel */

	private JPanel pnlGoogleFinalizar, pnlGoogleCancelar, pnlUseTerms,
			pnlInterfaceGoogle;
	/** <Attribute type JPanel */

	private boolean oneSelected, okPressed, mapSelected, satSelected,
			terSelected, hybridSelected;

	/**
	 * <Attribute type boolean
	 */

	public InterfaceGoogle() {

		this.setOneSelected(false);
		this.setOkPressed(false);

		LookAndFeelService.changeLookAndFeelForNimbus();

		this.lblTitleGoogle = new JLabel("Escolha um tema google:");
		this.lblTitleGoogle.setBounds(15, 5, 150, 25);

		// /////////////////////////////////////////////////////////////////////

		this.lblMapNameGoogle = new JLabel("Mapa:");
		this.lblMapNameGoogle.setBounds(65, 40, 60, 25);

		URL url0 = getClass().getClassLoader().getResource(
				"br/org/funcate/glue/image/rioMap.gif");
		try {
			this.lblMapGoogle = new JLabel(new ImageIcon(getImg(url0)));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		this.lblMapGoogle.setBounds(25, 65, 120, 120);
		this.lblMapGoogle.setBorder(BorderFactory.createLineBorder(Color.black,
				1));
		this.lblMapGoogle.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// /////////////////////////////////////////////////////////////////////

		this.lblSatNameGoogle = new JLabel("Satelite:");
		this.lblSatNameGoogle.setBounds(210, 40, 80, 25);

		URL url1 = getClass().getClassLoader().getResource(
				"br/org/funcate/glue/image/rioSat.gif");
		try {
			this.lblSatGoogle = new JLabel(new ImageIcon(getImg(url1)));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.lblSatGoogle.setBounds(175, 65, 120, 120);
		this.lblSatGoogle.setBorder(BorderFactory.createLineBorder(Color.black,
				1));
		this.lblSatGoogle.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// /////////////////////////////////////////////////////////////////////

		this.lblTerNameGoogle = new JLabel("Terreno:");
		this.lblTerNameGoogle.setBounds(65, 208, 80, 25);

		URL url2 = getClass().getClassLoader().getResource(
				"br/org/funcate/glue/image/rioTer.gif");
		try {
			this.lblTerGoogle = new JLabel(new ImageIcon(getImg(url2)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.lblTerGoogle.setBounds(25, 233, 120, 120);
		this.lblTerGoogle.setBorder(BorderFactory.createLineBorder(Color.black,
				1));
		this.lblTerGoogle.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// /////////////////////////////////////////////////////////////////////

		this.lblHybridNameGoogle = new JLabel("Hibrido:");
		this.lblHybridNameGoogle.setBounds(210, 208, 80, 25);

		URL url3 = getClass().getClassLoader().getResource(
				"br/org/funcate/glue/image/rioHybrid.gif");
		try {
			lblHybridGoogle = new JLabel(new ImageIcon(getImg(url3)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.lblHybridGoogle.setBounds(175, 233, 120, 120);
		this.lblHybridGoogle.setBorder(BorderFactory.createLineBorder(
				Color.black, 1));
		this.lblHybridGoogle.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// /////////////////////////////////////////////////////////////////////

		this.lblGoogleFinalizar = new JLabel(createLinkText("Finalizar",
				"CFCFCF"));
		this.lblGoogleFinalizar.setBounds(4, 2, 60, 25);
		this.lblGoogleFinalizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.pnlGoogleFinalizar = new JPanel();
		this.pnlGoogleFinalizar.setBounds(140, 388, 68, 29);
		this.pnlGoogleFinalizar.setBackground(new Color(255, 255, 255));
		this.pnlGoogleFinalizar.add(lblGoogleFinalizar);
		this.pnlGoogleFinalizar.setToolTipText("Finaliza seleção de temas");
		this.pnlGoogleFinalizar.setVisible(true);

		// /////////////////////////////////////////////////////////////////////

		this.lblGoogleCancelar = new JLabel(
				createLinkText("Cancelar", "000080"));
		this.lblGoogleCancelar.setBounds(4, 2, 60, 25);
		this.lblGoogleCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.pnlGoogleCancelar = new JPanel();
		this.pnlGoogleCancelar.setBounds(235, 388, 68, 29);
		this.pnlGoogleCancelar.setBackground(new Color(255, 255, 255));
		this.pnlGoogleCancelar.add(lblGoogleCancelar);
		this.pnlGoogleCancelar.setToolTipText("Cancelar criação de temas");
		this.pnlGoogleCancelar.setVisible(true);

		this.lblUseTerms = new JLabel(createLinkText("Termos de Uso", "000080"));
		this.lblUseTerms.setBounds(4, 2, 70, 25);
		this.lblUseTerms.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.pnlUseTerms = new JPanel();
		this.pnlUseTerms.setBounds(10, 388, 130, 29);
		this.pnlUseTerms.setBackground(new Color(255, 255, 255));
		this.pnlUseTerms.add(lblUseTerms);
		this.pnlUseTerms.setToolTipText("Abrir termos de uso do Google");
		this.pnlUseTerms.setVisible(true);

		this.pnlInterfaceGoogle = new JPanel();
		this.pnlInterfaceGoogle.setBounds(5, 5, 320, 420);
		this.pnlInterfaceGoogle.setVisible(true);
		this.pnlInterfaceGoogle.setBackground(new Color(255, 255, 255));
		this.pnlInterfaceGoogle.setLayout(null);

		InterfaceGoogleListener listener = new InterfaceGoogleListener(this);
		this.addListener(listener);

		this.pnlInterfaceGoogle.add(lblTitleGoogle);

		this.pnlInterfaceGoogle.add(lblMapNameGoogle);
		this.pnlInterfaceGoogle.add(lblMapGoogle);

		this.pnlInterfaceGoogle.add(lblSatNameGoogle);
		this.pnlInterfaceGoogle.add(lblSatGoogle);

		this.pnlInterfaceGoogle.add(lblTerNameGoogle);
		this.pnlInterfaceGoogle.add(lblTerGoogle);

		this.pnlInterfaceGoogle.add(lblHybridNameGoogle);
		this.pnlInterfaceGoogle.add(lblHybridGoogle);

		this.pnlInterfaceGoogle.add(pnlGoogleFinalizar);
		this.pnlInterfaceGoogle.add(pnlGoogleCancelar);
		this.pnlInterfaceGoogle.add(pnlUseTerms);

		this.add(pnlInterfaceGoogle);

		DragPanel drag = new DragPanel(this);
		this.addMouseListener(drag);
		this.addMouseMotionListener(drag);

		this.setUndecorated(true);

		this.setBackground(new Color(255, 255, 255));
		this.setLayout(null);

		this.setAlwaysOnTop(true);

		this.setBounds(0, 0, 330, 430);

		this.setLocation((int) (550 - this.getSize().getWidth() / 2),
				(int) (365 - this.getSize().getHeight() / 2));

		this.setVisible(true);
	}

	public static InterfaceGoogle getInstance() {
		if (instance == null) {
			instance = new InterfaceGoogle();
			instance.setFocusable(true);
		}
		return instance;
	}

	private void addListener(InterfaceGoogleListener listener) {
		this.lblMapGoogle.addMouseListener(listener);
		this.lblSatGoogle.addMouseListener(listener);
		this.lblTerGoogle.addMouseListener(listener);
		this.lblHybridGoogle.addMouseListener(listener);
		this.pnlGoogleFinalizar.addMouseListener(listener);
		this.pnlGoogleCancelar.addMouseListener(listener);
		this.pnlUseTerms.addMouseListener(listener);
	}

	private String createLinkText(String text, String cor) {
		text = "<html><font color=\"#" + cor + "\"><u>" + text
				+ "</u></font></html>";
		return text;
	}

	private static BufferedImage getImg(URL path) throws IOException {
		BufferedImage im = ImageIO.read(path);
		return im;
	}

	public JLabel getLblGoogleFinalizar() {
		return this.lblGoogleFinalizar;
	}

	public void setLblGoogleFinalizar(JLabel lblGoogleFinalizar) {
		this.lblGoogleFinalizar = lblGoogleFinalizar;
	}

	public JLabel getLblGoogleCancelar() {
		return this.lblGoogleCancelar;
	}

	public void setLblGoogleCancelar(JLabel lblGoogleCancelar) {
		this.lblGoogleCancelar = lblGoogleCancelar;
	}

	public JLabel getLblMapGoogle() {
		return this.lblMapGoogle;
	}

	public void setLblMapGoogle(JLabel lblMapGoogle) {
		this.lblMapGoogle = lblMapGoogle;
	}

	public JLabel getLblSatGoogle() {
		return this.lblSatGoogle;
	}

	public void setLblSatGoogle(JLabel lblSatGoogle) {
		this.lblSatGoogle = lblSatGoogle;
	}

	public JLabel getLblHybridGoogle() {
		return this.lblHybridGoogle;
	}

	public void setLblHybridGoogle(JLabel lblHybridGoogle) {
		this.lblHybridGoogle = lblHybridGoogle;
	}

	public JLabel getLblTerGoogle() {
		return this.lblTerGoogle;
	}

	public void setLblTerGoogle(JLabel lblTerGoogle) {
		this.lblTerGoogle = lblTerGoogle;
	}

	public JLabel getLblTitleGoogle() {
		return this.lblTitleGoogle;
	}

	public void setLblTitleGoogle(JLabel lblTitleGoogle) {
		this.lblTitleGoogle = lblTitleGoogle;
	}

	public JLabel getLblMapNameGoogle() {
		return this.lblMapNameGoogle;
	}

	public void setLblMapNameGoogle(JLabel lblMapNameGoogle) {
		this.lblMapNameGoogle = lblMapNameGoogle;
	}

	public JLabel getLblSatNameGoogle() {
		return this.lblSatNameGoogle;
	}

	public void setLblSatNameGoogle(JLabel lblSatNameGoogle) {
		this.lblSatNameGoogle = lblSatNameGoogle;
	}

	public JLabel getLblHybridNameGoogle() {
		return this.lblHybridNameGoogle;
	}

	public void setLblHybridNameGoogle(JLabel lblHybridNameGoogle) {
		this.lblHybridNameGoogle = lblHybridNameGoogle;
	}

	public JLabel getLblTerNameGoogle() {
		return this.lblTerNameGoogle;
	}

	public void setLblTerNameGoogle(JLabel lblTerNameGoogle) {
		this.lblTerNameGoogle = lblTerNameGoogle;
	}

	public JLabel getLblUseTerms() {
		return this.lblUseTerms;
	}

	public void setLblUseTerms(JLabel lblUseTerms) {
		this.lblUseTerms = lblUseTerms;
	}

	public JPanel getPnlGoogleFinalizar() {
		return this.pnlGoogleFinalizar;
	}

	public void setPnlGoogleFinalizar(JPanel pnlGoogleFinalizar) {
		this.pnlGoogleFinalizar = pnlGoogleFinalizar;
	}

	public JPanel getPnlGoogleCancelar() {
		return this.pnlGoogleCancelar;
	}

	public void setPnlGoogleCancelar(JPanel pnlGoogleCancelar) {
		this.pnlGoogleCancelar = pnlGoogleCancelar;
	}

	public JPanel getPnlUseTerms() {
		return this.pnlUseTerms;
	}

	public void setPnlUseTerms(JPanel pnlUseTerms) {
		this.pnlUseTerms = pnlUseTerms;
	}

	public boolean isOneSelected() {
		return this.oneSelected;
	}

	public void setOneSelected(boolean oneSelected) {
		this.oneSelected = oneSelected;
	}

	public boolean isOkPressed() {
		return this.okPressed;
	}

	public void setOkPressed(boolean okPressed) {
		this.okPressed = okPressed;
	}

	public boolean isMapSelected() {
		return this.mapSelected;
	}

	public void setMapSelected(boolean mapSelected) {
		this.mapSelected = mapSelected;
	}

	public boolean isSatSelected() {
		return this.satSelected;
	}

	public void setSatSelected(boolean satSelected) {
		this.satSelected = satSelected;
	}

	public boolean isTerSelected() {
		return this.terSelected;
	}

	public void setTerSelected(boolean terSelected) {
		this.terSelected = terSelected;
	}

	public boolean isHybridSelected() {
		return this.hybridSelected;
	}

	public void setHybridSelected(boolean hybridSelected) {
		this.hybridSelected = hybridSelected;
	}
}
