package br.org.funcate.glue.os.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.LineBorder;

import br.org.funcate.glue.controller.CanvasController;
import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.controller.TreeController;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.canvas.CanvasState;
import br.org.funcate.glue.os.model.GenericTableModel;
import br.org.funcate.glue.os.model.OSState;
import br.org.funcate.glue.view.SearchPanel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class ServiceOrderCreatorScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtServiceOrderNumber;
	public JTextField txtAddress;
	public JButton btnOk;
	public JButton btnCancel;
	public JTextField txtDate;
	private JPanel panel;
	private SearchPanel searchPanel;
	private static ServiceOrderCreatorScreen instance;
	private JList<String> listIpIds;
	private JButton btnDefine;
	private Random generator;
	private JComboBox<String> cboOcurrence;
	private JButton button;
	private JTable table;
	private JTextField textIP;
	private JTextField textField_1;
	private ArrayList<String> ips = new ArrayList<String>();
	private ArrayList<String> ocurrences = new ArrayList<String>();
	
	//singleton
	public static ServiceOrderCreatorScreen getInstance() {
		if(instance==null)
			instance = new ServiceOrderCreatorScreen();
			
		return instance;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					ServiceOrderCreatorScreen frame = new ServiceOrderCreatorScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public ServiceOrderCreatorScreen() {
		initializeScreen();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initializeScreen() {
		//setModalityType(ModalityType.APPLICATION_MODAL);
		//setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ServiceOrderCreatorScreen.class.getResource("/br/org/funcate/glue/image/access.png")));
		setTitle("Service Order");
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent arg0) {
				instance = null;
				TreeController.listModel = new DefaultListModel<String>();
			}
			@Override
			public void componentShown(ComponentEvent arg0) {
				populateAddress();
				populateTable();
			}
		});
		setResizable(false);
		setBounds(100, 100, 470, 430);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new LineBorder(new Color(128, 128, 128)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		searchPanel = new SearchPanel();
		searchPanel.setBounds(13, 39, 440, 45);
		
		searchPanel.getTextSearch().addMouseListener(new MouseAdapter() {	
		public void mouseReleased(MouseEvent arg0) {
			String[] text = searchPanel.getTextSearch().getText().split("lote:");
			txtAddress.setText(text[0].toUpperCase());
			OSState.setAddress(text[0].toUpperCase());
			}
		});
		
		//contentPane.add(searchPanel);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel.setBounds(13, 38, 439, 199);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblOsNumber = new JLabel("OS Number");
		lblOsNumber.setBounds(6, 5, 65, 14);
		panel.add(lblOsNumber);

		txtServiceOrderNumber = new JTextField();
		txtServiceOrderNumber.setFont(new Font("Arial", Font.BOLD, 12));
		txtServiceOrderNumber.setBounds(6, 19, 285, 26);
		panel.add(txtServiceOrderNumber);
		txtServiceOrderNumber.setEditable(false);
		txtServiceOrderNumber.setBorder(new LineBorder(new Color(192, 192, 192)));
		txtServiceOrderNumber.setName("OSId");
		txtServiceOrderNumber.setColumns(10);
		txtServiceOrderNumber.setText(genetateID());

		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Arial", Font.BOLD, 12));
		txtAddress.setBounds(6, 61, 427, 26);
		panel.add(txtAddress);
		txtAddress.setBorder(new LineBorder(new Color(192, 192, 192)));
		txtAddress.setColumns(10);

		JLabel lblAddress = new JLabel("Address (IP)");
		lblAddress.setBounds(6, 46, 187, 14);
		panel.add(lblAddress);

		JLabel lblIpId = new JLabel("Selected IP for service order");
		lblIpId.setBounds(7, 95, 158, 14);
		panel.add(lblIpId);
		lblIpId.setHorizontalTextPosition(SwingConstants.RIGHT);

		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtDate.setBounds(303, 19, 130, 26);
		panel.add(txtDate);
		txtDate.setBorder(new LineBorder(new Color(192, 192, 192)));
		txtDate.setText(getSystemDate(""));

		JLabel lblData = new JLabel("Date");
		lblData.setBounds(302, 5, 65, 14);
		panel.add(lblData);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 103, 149, 88);
		//panel.add(scrollPane_1);
		
		listIpIds = new JList<String>();
		listIpIds.setSelectionBackground(new Color(230, 230, 250));
		listIpIds.setFont(new Font("Arial", Font.BOLD, 14));
		//scrollPane_1.setViewportView(listIpIds);

		btnDefine = new JButton("");
		btnDefine.setToolTipText("Define");
		btnDefine.setIcon(new ImageIcon(ServiceOrderCreatorScreen.class.getResource("/br/org/funcate/glue/image/globe_left.gif.png")));
		btnDefine.setFocusPainted(false);

		btnDefine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ips.add(textIP.getText());
				ocurrences.add(cboOcurrence.getSelectedItem().toString());
				
				String[] columnNames = {"IP","Ocurrence"};
				Object[][] data = new Object[ips.size()][columnNames.length];
				
				for (int i = 0; i < ips.size() ; i++) {
					data[i][0] = ips.get(i);
					data[i][1] = ocurrences.get(i);
				}
				
				GenericTableModel model = new GenericTableModel(columnNames, data);
				table.setModel(model);
				AppSingleton singleton = AppSingleton.getInstance();
				Mediator mediator = singleton.getMediator();
				CanvasState state = singleton.getCanvasState();
				state.setGvSource("CommitFeatureEvent");

				CanvasController canvasController = mediator.getCanvasController();
				Thread ip = new Thread(canvasController);
				ip.start();
				
			}
		});
		btnDefine.setBackground(Color.WHITE);
		btnDefine.setBounds(172, 107, 55, 30);
		panel.add(btnDefine);
		
		cboOcurrence = new JComboBox<String>();
		cboOcurrence.setFont(new Font("SansSerif", Font.BOLD, 12));
		
		cboOcurrence.setModel(new DefaultComboBoxModel(new String[] {"LMP Apagada", "LM Quebrada", "PST Danificado"}));
		cboOcurrence.setBackground(new Color(255, 255, 255));
		cboOcurrence.setBounds(227, 106, 206, 30);
		panel.add(cboOcurrence);
		
		JLabel lblOcurrence = new JLabel("Ocurrence");
		lblOcurrence.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblOcurrence.setBounds(229, 95, 91, 14);
		panel.add(lblOcurrence);
		
		button = new JButton("Citizen Register");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CitizenScreen screen = new CitizenScreen();
				screen.setVisible(true);
			}
		});
		button.setBounds(14, 141, 141, 30);
		panel.add(button);
		button.setBackground(Color.WHITE);
		
		textIP = new JTextField();
		textIP.setEditable(false);
		textIP.setFont(new Font("Arial", Font.BOLD, 12));
		textIP.setColumns(10);
		textIP.setBorder(new LineBorder(new Color(192, 192, 192)));
		textIP.setBounds(6, 110, 163, 26);
		panel.add(textIP);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setFont(new Font("Arial", Font.BOLD, 12));
		textField_1.setColumns(10);
		textField_1.setBorder(new LineBorder(new Color(192, 192, 192)));
		textField_1.setBounds(164, 143, 265, 26);
		panel.add(textField_1);

		JButton btnNewButton_2 = new JButton("Help");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(191, 354, 55, 38);
		contentPane.add(btnNewButton_2);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBackground(new Color(255, 255, 255));
		btnCancel.setBounds(120, 354, 67, 38);
		contentPane.add(btnCancel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(8, 6, 449, 22);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Manager Service Order");
		lblNewLabel_1.setBounds(142, 4, 185, 17);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1.add(lblNewLabel_1);

		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		btnOk.setBounds(13, 354, 103, 38);
		contentPane.add(btnOk);
		btnOk.setBackground(new Color(255, 255, 255));
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(371, 360, 82, 30);
		contentPane.add(btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AppSingleton singleton = AppSingleton.getInstance();
				Mediator mediator = singleton.getMediator();
				CanvasState state = singleton.getCanvasState();
				state.setGvSource("CommitFeatureEvent");

				CanvasController canvasController = mediator.getCanvasController();
				Thread ip = new Thread(canvasController);
				ip.start();
			}
		});
		btnCreate.setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(13, 246, 439, 102);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"IP", "Ocurrence"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		redirectScreenToLeft();
	}

	protected void populateTable() {
//		String[]columnNames ={"","","","",""};
//		Object[][]data={};
//		GenericTableModel tableModel = new GenericTableModel(columnNames, data);
	}

	private void populateAddress() {
		String address = OSState.getAddress();
		if(address!=null)
			txtAddress.setText(address.toUpperCase());
	}

	/**
	 * Sets the default interface look and feel to 'nimbus'
	 */
	public void setNimbusLookAndFeel() {
		try {
			LookAndFeelInfo[] lookAndFeels = UIManager
					.getInstalledLookAndFeels();

			for (UIManager.LookAndFeelInfo info : lookAndFeels) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, fall back to cross-platform
			try {
				UIManager.setLookAndFeel(UIManager
						.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ex) {
				// not worth my time
			}
		}
	}
	/**
	 * redirects this screen to the left for better visualization of map.
	 */
	private void redirectScreenToLeft() {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		int h = size.height;
		setLocation(10, h-(h-160));
	}
	/**
	 * return local system date and hour
	 * @param format 
	 * @return string formated date.
	 */
	public String getSystemDate(String format){
		
		if(format==null || format=="")
			format = "dd/MM/yyyy HH:mm:ss";
		
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormat.format(date).toString();
	}
	
	private String genetateID(){
		generator = new Random();
		int i = generator.nextInt(1000) + 1;
		String format = "ddMMHHmmss";
		return String.valueOf(i).concat(getSystemDate(format));
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	@SuppressWarnings("unchecked")
	public JList<String> getListIpIds() {
		return listIpIds;
	}

	public void setListIpIds(JList<String> listIpIds) {
		this.listIpIds = listIpIds;
	}

	public JComboBox<String> getCboOcurrence() {
		return cboOcurrence;
	}

	public void setCboOcurrence(JComboBox<String> cboOcurrence) {
		this.cboOcurrence = cboOcurrence;
	}

	public JTextField getTxtServiceOrderNumber() {
		return txtServiceOrderNumber;
	}

	public void setTxtServiceOrderNumber(JTextField txtServiceOrderNumber) {
		this.txtServiceOrderNumber = txtServiceOrderNumber;
	}

	public JTextField getTextIP() {
		return textIP;
	}

	public void setTextIP(JTextField textIP) {
		this.textIP = textIP;
	}
	
}
