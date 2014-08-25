package br.org.funcate.glue.os.view;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

import br.org.funcate.glue.os.model.OSState;
import br.org.funcate.glue.utilities.Utils;
import br.org.funcate.glue.view.Toolbar;


@SuppressWarnings("serial")
public class AuthenticationScreen extends JDialog {

	private JPanel contentPane;
	private JTextField textUser;
	private JPasswordField passwordField;
	private String bdpass = "321";
	private String bduser= "inacio";
	private JLabel lblErroMensage;
	private static boolean validUser;
	private static boolean validPass;
	private JLabel lblHelp;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {	
				try {
					AuthenticationScreen frame = new AuthenticationScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AuthenticationScreen() {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 360, 245);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Arial", Font.PLAIN, 13));
		lblUser.setBounds(23, 35, 33, 16);
		contentPane.add(lblUser);
		
		textUser = new JTextField();
		textUser.setBorder(new LineBorder(new Color(192, 192, 192)));
		textUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent fe) {
				
				boolean check = checkUser(textUser.getText().trim());	
				if(!check){
					textUser.setBorder(new LineBorder(Color.RED,2));
					lblErroMensage.setVisible(true);
					lblErroMensage.setText("User not found.!");
					validUser = false;
				}else{
					textUser.setBorder(new LineBorder(Color.BLUE,2));
					lblErroMensage.setVisible(false);
					validUser = true;
				}
			}
		});
		
		textUser.setBounds(22, 49, 240, 28);
		contentPane.add(textUser);
		textUser.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPassword.setBounds(22, 81, 68, 16);
		contentPane.add(lblPassword);
		
		JLabel lblIMissMy = new JLabel("Miss your password?");
		lblIMissMy.setForeground(new Color(0, 0, 205));
		lblIMissMy.setBackground(new Color(255, 255, 255));
		lblIMissMy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblIMissMy.setBounds(115, 161, 125, 16);
		contentPane.add(lblIMissMy);
		
		passwordField = new JPasswordField();
		passwordField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				boolean checkPass = checkPassword(passwordField.getPassword());
				if(!checkPass){
					passwordField.setBorder(new LineBorder(Color.RED,2));
					validPass = false;
				}else{
					passwordField.setBorder(new LineBorder(Color.BLUE,2));
					validPass = true;
				}
			}
		});
		passwordField.setBorder(new LineBorder(new Color(192, 192, 192)));
		passwordField.setBackground(new Color(255, 255, 255));
		passwordField.setBounds(22, 96, 240, 28);
		contentPane.add(passwordField);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 5, 342, 22);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAuthentication = new JLabel("Authentication");
		lblAuthentication.setFont(new Font("Arial", Font.BOLD, 14));
		lblAuthentication.setBounds(126, 3, 106, 16);
		panel.add(lblAuthentication);
		
		lblErroMensage = new JLabel("erro");
		lblErroMensage.setHorizontalAlignment(SwingConstants.CENTER);
		lblErroMensage.setVisible(false);
		lblErroMensage.setForeground(new Color(128, 128, 128));
		lblErroMensage.setIcon(new ImageIcon(AuthenticationScreen.class.getResource("/br/org/funcate/glue/image/erro16.png")));
		lblErroMensage.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 11));
		lblErroMensage.setBounds(6, 139, 342, 16);
		contentPane.add(lblErroMensage);
		
		lblHelp = new JLabel("Help");
		lblHelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelp.setForeground(new Color(0, 0, 205));
		lblHelp.setBackground(Color.WHITE);
		lblHelp.setBounds(151, 182, 53, 16);
		contentPane.add(lblHelp);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(validUser && validPass){
					Toolbar.getBtnOsSelect().setVisible(true);
					Toolbar.getBtnOsShow().setVisible(true);
					Toolbar.getBtnSelectOs().setVisible(true);
					lblErroMensage.setVisible(false);
					OSState.setAuth(true);
					dispose();
				}else{
					Toolbar.getBtnOsSelect().setVisible(false);
					Toolbar.getBtnOsShow().setVisible(false);
					Toolbar.getBtnSelectOs().setVisible(false);
					OSState.setAuth(false);
					lblErroMensage.setVisible(true);
					lblErroMensage.setText("you have no permission!");
				}
			}
		});
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(274, 88, 68, 38);
		contentPane.add(btnLogin);
		Utils.setScreenToCenter(this);	
	}
	

	private boolean checkUser(String user) {
		boolean checkUser = false;
		if(user.equals(this.bduser)){
			checkUser = true;
		}else{
			checkUser = false;
		}
		return checkUser;	
	}
	
	private boolean checkPassword(char[] pass) {
		boolean checkPass = false;
		String passStr = String.valueOf(pass);
		
		if(passStr.equals(this.bdpass)){
			checkPass = true;
		}else{
			checkPass = false;
		}
		
		return checkPass;	
	}
	
	
}
