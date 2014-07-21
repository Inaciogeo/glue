package br.org.funcate.glue.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.org.funcate.glue.model.LookAndFeelService;
import br.org.funcate.glue.utilities.Utils;


@SuppressWarnings("serial")
public class GlueMessageDialog extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private String message;
	private int iconType;
	private JPanel buttonPane;
	private static boolean isOK;
	private JButton btnCancelar;
	private static GlueMessageDialog instance;

	/**
	 * Launch the application for test.
	 */
	public static void main(String[] args) {
		try {
			GlueMessageDialog dialog = new GlueMessageDialog("teste de mensagem do glue","detalhes", 3);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static GlueMessageDialog getInstance(){
		return GlueMessageDialog.instance;
	}

	/**
	 * Create the dialog.
	 * @param message : message to be displayed
	 * @param showDetails : message details (exception for example)  
	 * @param iconType : 1 - error, 2 - warning, 3 - generic message 
	 * @author Inacio
	 */
	public static void show(String message,String showDetails, int iconType){
		try {
			GlueMessageDialog dialog = new GlueMessageDialog(message, showDetails, iconType);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(){
		
	}
	
	public GlueMessageDialog(String message, String showDetails, int iconType) {
		LookAndFeelService.initializeNimbusLookAndFeel();
		setResizable(false);
		this.message = message;
		this.iconType = iconType;
		//this.details = showDetails;
		
		//setModal(true);
		//setUndecorated(true);
		setBounds(100, 100, 485, 210);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 480, 183);
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(null);
		
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(10, 50, 464, 59);
		contentPanel.add(scrollPane);

		textArea = new JTextArea();
		textArea.setFont(new Font("Dialog", Font.BOLD, 13));
		textArea.setForeground(new Color(105, 105, 105));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setText(this.message);
		scrollPane.setViewportView(textArea);
			
		
		buttonPane = new JPanel();
		buttonPane.setBackground(new Color(211, 211, 211));
		buttonPane.setBounds(1, 143, 483, 37);
		contentPanel.add(buttonPane);
		buttonPane.setLayout(null);
		
		okButton = new JButton("OK");
		okButton.setFocusPainted(false);
		okButton.setForeground(new Color(0, 0, 0));
		okButton.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setOK(true);
				dispose();
			}
		});
		okButton.setBounds(16, 4, 80, 30);
		okButton.setBackground(new Color(255, 255, 255));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setVisible(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setOK(false);
				dispose();
			}
		});
		btnCancelar.setForeground(new Color(0, 0, 0));
		btnCancelar.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		btnCancelar.setFocusPainted(false);
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setActionCommand("OK");
		btnCancelar.setBounds(100, 4, 100, 30);
		buttonPane.add(btnCancelar);
		
		lblNewLabel = new JLabel("");	
		
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setBounds(10, 2, 420, 40);
		
		if(this.iconType == 1 ){
			lblNewLabel.setIcon(new ImageIcon(GlueMessageDialog.class.getResource("/br/org/funcate/glue/image/erro_mensage.png")));
			lblNewLabel.setText("Ocorreu um Erro a tarefa não pode ser concluída");
			//buttonPane.setBackground(new Color(128, 0, 0));
		}
		if(this.iconType == 2 ){
			lblNewLabel.setIcon(new ImageIcon(GlueMessageDialog.class.getResource("/br/org/funcate/glue/image/Error.png")));
			lblNewLabel.setText("Atenção");
			btnCancelar.setVisible(true);
			//buttonPane.setBackground(new Color(240, 230, 140));
		}
		if(this.iconType == 3 ){
			lblNewLabel.setIcon(new ImageIcon(GlueMessageDialog.class.getResource("/br/org/funcate/glue/image/Comments.png")));
			lblNewLabel.setText("Mensagem");
			//buttonPane.setBackground(new Color(176, 196, 222));
		}
		
		contentPanel.add(lblNewLabel);
		
		
		Utils.setScreenToCenter(this);
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}

	public static boolean isOK() {
		return isOK;
	}

	public static void setOK(boolean isOK) {
		GlueMessageDialog.isOK = isOK;
	}
}
