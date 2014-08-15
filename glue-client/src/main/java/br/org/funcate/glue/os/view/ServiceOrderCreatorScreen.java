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
import br.org.funcate.glue.view.Toolbar;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

public class ServiceOrderCreatorScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtServiceOrderNumber;
	public JTextField txtAddress;
	public JButton btnOk;
	public JButton btnCancel;
	public JButton btnChooseCitizen;
	public JTextField txtDate;
	private JPanel panel;
	private SearchPanel searchPanel;
	private static ServiceOrderCreatorScreen instance;
	private JButton btnLogoutAndExit;
	private JTable table;
	private JList<String> listIpIds;
	
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
		setBounds(100, 100, 470, 500);
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
		
		contentPane.add(searchPanel);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel.setBounds(13, 96, 439, 197);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblOsNumber = new JLabel("OS Number");
		lblOsNumber.setBounds(6, 6, 65, 14);
		panel.add(lblOsNumber);

		txtServiceOrderNumber = new JTextField();
		txtServiceOrderNumber.setFont(new Font("Arial", Font.BOLD, 12));
		txtServiceOrderNumber.setBounds(6, 19, 285, 26);
		panel.add(txtServiceOrderNumber);
		txtServiceOrderNumber.setEditable(false);
		txtServiceOrderNumber
				.setBorder(new LineBorder(new Color(192, 192, 192)));
		txtServiceOrderNumber.setName("OSId");
		txtServiceOrderNumber.setColumns(10);
		txtServiceOrderNumber.setText(UUID.randomUUID().toString());

		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Arial", Font.BOLD, 12));
		txtAddress.setBounds(6, 61, 426, 26);
		panel.add(txtAddress);
		txtAddress.setBorder(new LineBorder(new Color(192, 192, 192)));
		txtAddress.setColumns(10);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(6, 46, 187, 14);
		panel.add(lblAddress);

		JLabel lblIpId = new JLabel("IP Id");
		lblIpId.setBounds(9, 92, 41, 14);
		panel.add(lblIpId);
		lblIpId.setHorizontalTextPosition(SwingConstants.RIGHT);

		btnChooseCitizen = new JButton("Citizen Register");
		btnChooseCitizen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CitizenScreen screen = new CitizenScreen();
				screen.setVisible(true);
			}
		});
		btnChooseCitizen.setBounds(245, 117, 155, 30);
		panel.add(btnChooseCitizen);
		btnChooseCitizen.setBackground(new Color(255, 255, 255));

		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtDate.setBounds(303, 18, 130, 26);
		panel.add(txtDate);
		txtDate.setBorder(new LineBorder(new Color(192, 192, 192)));
		txtDate.setText(getSystemDate());

		JLabel lblData = new JLabel("Date");
		lblData.setBounds(302, 5, 65, 14);
		panel.add(lblData);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 105, 127, 86);
		panel.add(scrollPane_1);
		
		listIpIds = new JList<String>();
		listIpIds.setSelectionBackground(new Color(230, 230, 250));
		listIpIds.setFont(new Font("Arial", Font.BOLD, 12));
		scrollPane_1.setViewportView(listIpIds);

		JButton btnSelectIp = new JButton("Select IP");

		btnSelectIp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AppSingleton singleton = AppSingleton.getInstance();
				Mediator mediator = singleton.getMediator();
				CanvasState state = singleton.getCanvasState();
				state.setGvSource("SelectFeatureEvent");

				CanvasController canvasController = mediator.getCanvasController();
				Thread ip = new Thread(canvasController);
				ip.start();
				dispose();

			}
		});
		btnSelectIp.setBackground(Color.WHITE);
		btnSelectIp.setBounds(126, 115, 82, 30);
		panel.add(btnSelectIp);

		JButton btnSelectTechnicalGroup = new JButton("Select Technical Group");
		btnSelectTechnicalGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TechnicalGroupScreen groupScreen = new TechnicalGroupScreen();
				groupScreen.setVisible(true);
			}
		});
		btnSelectTechnicalGroup.setBackground(Color.WHITE);
		btnSelectTechnicalGroup.setBounds(245, 146, 155, 30);
		panel.add(btnSelectTechnicalGroup);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBackground(Color.WHITE);
		btnRemove.setBounds(126, 151, 82, 30);
		panel.add(btnRemove);

		btnOk = new JButton("Open Service ");
		btnOk.setBackground(new Color(255, 255, 255));
		btnOk.setBounds(13, 427, 110, 38);
		contentPane.add(btnOk);

		JButton btnNewButton_2 = new JButton("Help");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(189, 427, 55, 38);
		contentPane.add(btnNewButton_2);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBackground(new Color(255, 255, 255));
		btnCancel.setBounds(122, 427, 67, 38);
		contentPane.add(btnCancel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(8, 6, 449, 22);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Opening service order");
		lblNewLabel_1.setBounds(144, 4, 160, 17);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		panel_1.add(lblNewLabel_1);

		btnLogoutAndExit = new JButton("Logout and Exit");
		btnLogoutAndExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OSState.setAuth(false);
				dispose();
				Toolbar.getOpenOS().setText("Off");
			}
		});
		btnLogoutAndExit.setBackground(Color.WHITE);
		btnLogoutAndExit.setBounds(337, 427, 112, 25);
		contentPane.add(btnLogoutAndExit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 301, 440, 125);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setSelectionBackground(new Color(144, 238, 144));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Lamp MR  30W", "6", "1200", "10"},
				{"Cap m-120", "1", "200", "4"},
				{"Reator RT1", "3", "120", "2"},
			},
			new String[] {
				"SONeed", "N", "N Local", "N Movel"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(130);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		scrollPane.setViewportView(table);
		redirectScreenToLeft();

	}

	protected void populateTable() {
		String[]columnNames ={"","","","",""};
		Object[][]data={};
		GenericTableModel tableModel = new GenericTableModel(columnNames, data);
		
	}

	private void populateAddress() {
		String address = OSState.getAddress();
		if(address!=null)
			txtAddress.setText(address);
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
	 * @return string formated date.
	 */
	public String getSystemDate(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date).toString();
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
}
