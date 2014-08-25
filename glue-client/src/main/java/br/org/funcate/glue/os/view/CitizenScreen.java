package br.org.funcate.glue.os.view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

import br.org.funcate.glue.os.model.OSState;
import br.org.funcate.glue.utilities.Utils;
import br.org.funcate.glue.view.GlueMessageDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CitizenScreen extends JDialog {

	private JPanel contentPane;
	private JFormattedTextField textCPF;
	private JTextField textAddress;
	private JTextField textCel;
	private JTextField textEmail;
	private JTextField textName;
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CitizenScreen frame = new CitizenScreen();
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
	public CitizenScreen() {
		setTitle("Citizen");
		
		setBounds(100, 100, 500, 331);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel.setBounds(10, 38, 465, 195);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textName = new JTextField();
		textName.setFont(new Font("Arial", Font.BOLD, 12));
		textName.setBorder(new LineBorder(new Color(192, 192, 192)));
		textName.setBounds(12, 21, 308, 26);
		panel.add(textName);
		textName.setColumns(10);
		
		textCPF = new JFormattedTextField(Utils.setMask("###.###.###-##"));
		textCPF.setFont(new Font("Arial", Font.BOLD, 12));
		textCPF.setColumns(10);
		textCPF.setBorder(new LineBorder(new Color(192, 192, 192)));
		textCPF.setBounds(332, 21, 120, 26);		
		panel.add(textCPF);
	
		textAddress = new JTextField();
		textAddress.setFont(new Font("Arial", Font.BOLD, 12));
		textAddress.setColumns(10);
		textAddress.setBorder(new LineBorder(new Color(192, 192, 192)));
		textAddress.setBounds(13, 64, 440, 26);
		panel.add(textAddress);
		
		textCel = new JFormattedTextField(Utils.setMask("(##)#####-####"));
		textCel.setFont(new Font("Arial", Font.BOLD, 12));
		textCel.setColumns(10);
		textCel.setBorder(new LineBorder(new Color(192, 192, 192)));
		textCel.setBounds(14, 143, 146, 26);
		panel.add(textCel);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Arial", Font.BOLD, 12));
		textEmail.setColumns(10);
		textEmail.setBorder(new LineBorder(new Color(192, 192, 192)));
		textEmail.setBounds(170, 143, 285, 26);
		panel.add(textEmail);
		
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(12, 4, 55, 16);
		panel.add(lblNewLabel);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(331, 5, 55, 16);
		panel.add(lblCpf);
		
		JLabel lblAdress = new JLabel("Address");
		lblAdress.setBounds(13, 49, 55, 16);
		panel.add(lblAdress);
		
		JLabel lblCel = new JLabel("Cel");
		lblCel.setBounds(13, 127, 55, 16);
		panel.add(lblCel);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(171, 127, 55, 16);
		panel.add(lblEmail);
		
		JButton btnSetAddress = new JButton("Transport IP Address");
		btnSetAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(OSState.getAddress()!=null)
					textAddress.setText(OSState.getAddress());
				else{
					GlueMessageDialog.show("No address in the transfer area", null, 3);
				}
			}
		});
		btnSetAddress.setBackground(new Color(255, 255, 255));
		btnSetAddress.setBounds(282, 90, 170, 28);
		panel.add(btnSetAddress);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 6, 465, 22);
		contentPane.add(panel_1);
		
		JLabel lblRegistre = new JLabel("Requestor citizen registration");
		lblRegistre.setFont(new Font("Arial", Font.BOLD, 14));
		lblRegistre.setBounds(122, 3, 223, 16);
		panel_1.add(lblRegistre);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBackground(Color.WHITE);
		btnOk.setBounds(10, 249, 170, 28);
		contentPane.add(btnOk);
	}
}
