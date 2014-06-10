package br.org.funcate.glue.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.LookAndFeelService;

/**
 * \brief Class to create a new OptionPane with Glue Visual.
 * 
 * @author MACHADO, Willyan Aleksander
 * @version 1.0
 */

@SuppressWarnings("serial")
public class LocalOptionPane extends JFrame implements MouseListener {

	/** Labels show messages and to be Ok button. */
	private JLabel _lblMessage, _lblOk, _lblTitleBar;
	/** Panel to container labels. */
	JPanel _pnlPrincipal, _pnlTitleBar, _pnlOkButton;
	/** Instance of class. */
	private static LocalOptionPane _instance;
	/** String to show in screen. */
	String text;

	/**
	 * Main constructor that receives a message to show and the title of window.
	 * 
	 * @author MACHADO, Willyan Aleksander
	 * @param pMsg
	 *            The message to show in window
	 * @param pTitle
	 *            The message to show in title of window
	 */
	private LocalOptionPane(String pMsg, String pTitle) {

		text = pMsg;
		setSize(text.length() * 7 + 50, 120);

		LookAndFeelService.changeLookAndFeelForNimbus();

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		Point p = AppSingleton.getInstance().getMediator().getLocation();
		int x = (int) p.getX();
		int y = (int) p.getY();

		setLocation(((AppSingleton.getInstance().getMediator().getWidth() - this.getWidth()) / 2) + x, ((AppSingleton.getInstance()
				.getMediator().getHeigth() - this.getHeight()) / 2)
				+ y);
		setLayout(null);

		_lblTitleBar = new JLabel(pTitle);
		_lblTitleBar.setBounds(0, 0, this.getWidth(), 20);

		_pnlTitleBar = new JPanel();
		_pnlTitleBar.setLayout(null);
		_pnlTitleBar.setSize(this.getWidth(), 20);
		_pnlTitleBar.setBackground(new Color(198, 226, 250));
		_pnlTitleBar.add(_lblTitleBar);

		_lblMessage = new JLabel(text, JLabel.CENTER);
		_lblMessage.setBounds(0, 44, this.getWidth(), 25);

		_lblOk = new JLabel("Ok", JLabel.CENTER);
		_lblOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
		_lblOk.setBounds(2, 3, 16, 10);
		_pnlOkButton = new JPanel();
		_pnlOkButton.setLayout(null);
		_pnlOkButton.setBounds((LocalOptionPane.this.getWidth() / 2) - 10, 80, 21, 16);
		_pnlOkButton.setBackground(new Color(255, 255, 255));
		_pnlOkButton.add(_lblOk);
		_pnlOkButton.addMouseListener(this);

		new JTextField(text);
		_pnlPrincipal = new JPanel();
		_pnlPrincipal.setLayout(null);
		_pnlPrincipal.setBackground(Color.WHITE);
		_pnlPrincipal.add(_pnlTitleBar);
		_pnlPrincipal.add(_lblMessage);
		_pnlPrincipal.add(_pnlOkButton);
		_pnlPrincipal.setSize(LocalOptionPane.this.getWidth() - 4, LocalOptionPane.this.getHeight() - 4);
		_pnlPrincipal.setLocation(2, 2);

		setTitle("ALERTA!");
		setResizable(false);
		setUndecorated(true);
		setAlwaysOnTop(true);
		setBackground(Color.white);
		setVisible(true);

		setLocation((int) (550 - this.getSize().getWidth() / 2), (int) (365 - this.getSize().getHeight() / 2));

		add(_pnlPrincipal);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					setVisible(false);
				}
			}
		});

		DragPanel drag = new DragPanel(this);

		this.addMouseMotionListener(drag);
		this.addMouseListener(drag);
	}

	/**
	 * @author Moraes, Emerson Leite
	 * @brief This method Gets instance of LocalOptionPane
	 * @param pMsg
	 * @param pTitle
	 * @return _instance
	 */
	public static LocalOptionPane getInstance(String pMsg, String pTitle) {
		if (_instance == null) {
			_instance = new LocalOptionPane(pMsg, pTitle);

		} else {

			_instance.dispose();
			_instance = new LocalOptionPane(pMsg, pTitle);

			// _instance.set_lblMessage(pMsg);
			// _instance.resizePane();
			_instance.setVisible(true);
		}
		return _instance;
	}

	/**
	 * method to set label message.
	 * 
	 * @author MACHADO, Willyan Aleksander
	 * @param lblMessage
	 *            The message to show in window
	 */

	public void set_lblMessage(String lblMessage) {
		_lblMessage.setText(lblMessage);
		text = lblMessage;
	}

	/**
	 * Resizes the size of window with proportional with length of message.
	 * 
	 * @author MACHADO, Willyan Aleksander
	 */

	public void resizePane() {
		setSize(text.length() * 7 + 50, 120);

		_pnlTitleBar.setSize(this.getWidth(), 20);

		_lblMessage.setBounds(0, 44, this.getWidth(), 25);

		_pnlOkButton.setBounds((LocalOptionPane.this.getWidth() / 2) - 10, 80, 21, 16);

		_pnlPrincipal.setSize(LocalOptionPane.this.getWidth() - 4, LocalOptionPane.this.getHeight() - 4);

		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getSource() == _pnlOkButton)
			_pnlOkButton.setBackground(new Color(135, 206, 255));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (arg0.getSource() == _pnlOkButton)
			_pnlOkButton.setBackground(new Color(255, 255, 255));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (arg0.getSource() == _pnlOkButton) {
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.dispose();
		}
	}
}
