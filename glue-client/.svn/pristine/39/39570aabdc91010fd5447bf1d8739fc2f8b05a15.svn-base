package br.org.funcate.glue.view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;

import br.org.funcate.glue.utilities.Utils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FeaturesFeedbackWindow extends JDialog implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblLoadMsg;
	private JButton btnExit;
	private JLabel lblLoadimg;
	private JPanel panelLoad;
	private static FeaturesFeedbackWindow instance;
	
	public static FeaturesFeedbackWindow getInstance() {
		if(instance == null){
			FeaturesFeedbackWindow window = new FeaturesFeedbackWindow();
			instance = window;
			return instance;
		}	
		return instance;
	}

	public void run() {
		FeaturesFeedbackWindow.getInstance().setVisible(true);		
	}
	
	public static void hideWindow(){
		FeaturesFeedbackWindow.getInstance().setVisible(false);	
	}

	/**
	 * Create the panel.
	 */
	public FeaturesFeedbackWindow() {
		setBackground(new Color(192, 192, 192));
		//getContentPane().setLayout(null);
		//setUndecorated(true);
		//setModal(true);
		setBounds(0,0,395,121);
		panelLoad = new JPanel();
		panelLoad.setBackground(new Color(255, 255, 255));
		panelLoad.setBorder(null);
		panelLoad.setBounds(0, 0, 380, 79);
		//getContentPane().add(panelLoad);
		panelLoad.setLayout(null);
		setFocusable(true);
		lblLoadMsg = new JLabel("Carregando geometrias, por favor aguarde...");
		lblLoadMsg.setForeground(new Color(128, 128, 128));
		lblLoadMsg.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLoadMsg.setIcon(null);
		lblLoadMsg.setBounds(20, 6, 354, 30);
		panelLoad.add(lblLoadMsg);
		
		btnExit = new JButton("Sair");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FeaturesFeedbackWindow.getInstance().setVisible(false);	
			}
		});
		btnExit.setBorder(null);
		btnExit.setFocusable(false);
		btnExit.setFocusPainted(false);
		btnExit.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBackground(new Color(128, 0, 0));
		btnExit.setBounds(313, 44, 61, 30);
		panelLoad.add(btnExit);
		
		lblLoadimg = new JLabel("");
		lblLoadimg.setIcon(new ImageIcon(FeaturesFeedbackWindow.class.getResource("/br/org/funcate/glue/image/loading_bar.gif")));
		lblLoadimg.setBounds(20, 42, 199, 30);
		panelLoad.add(lblLoadimg);
		Utils.setScreenToCenter(this);

	}
}
