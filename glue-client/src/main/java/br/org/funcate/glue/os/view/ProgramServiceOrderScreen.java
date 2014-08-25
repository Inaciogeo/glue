package br.org.funcate.glue.os.view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;

import br.org.funcate.glue.os.model.OSState;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTree;


@SuppressWarnings("serial")
public class ProgramServiceOrderScreen extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private static ProgramServiceOrderScreen instance;
	private JTextField textGroup;
	private JTable tableTech;
	private JTable tableLocal;
	private JTextField textOS;
	private JTable tableGroup;
	private JComboBox<String> cbxHours;
	
	public static ProgramServiceOrderScreen getInstance() {
		if(instance == null)
			instance = new ProgramServiceOrderScreen();
		return instance;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ProgramServiceOrderScreen dialog = new ProgramServiceOrderScreen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ProgramServiceOrderScreen() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				String os = OSState.getOsOnMap();
				if(os!="")
					textOS.setText(os);
			}
		});
		setBounds(100, 100, 515, 560);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(11, 6, 482, 22);
		contentPanel.add(panel);
		
		JLabel lblProgramServiceOrder = new JLabel("Program Service Order");
		lblProgramServiceOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgramServiceOrder.setFont(new Font("Arial", Font.BOLD, 14));
		lblProgramServiceOrder.setBounds(62, 3, 378, 17);
		panel.add(lblProgramServiceOrder);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(6, 111, 487, 223);
		contentPanel.add(panel_2);
		
		JLabel label_1 = new JLabel("Technical Groups Information");
		label_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		label_1.setBounds(9, 2, 189, 14);
		panel_2.add(label_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 17, 470, 106);
		panel_2.add(scrollPane);
		
		tableTech = new JTable();
		tableTech.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String group = tableTech.getValueAt(tableTech.getSelectedRow(), 0).toString();
				if(group!="")
					textGroup.setText(group);
				
				tableGroup.setModel(new DefaultTableModel(
						new Object[][] {
							{"Lamp MR  30W", "6", "10"},
							{"Cap m-120", "1", "2"},
							{"Reator RT1", "3", "12"},
						},
						new String[] {
							"Component", "Need", "Inventory "
						}
					));
			}
		});
		tableTech.setSelectionBackground(new Color(144, 238, 144));
		tableTech.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tableTech.setModel(new DefaultTableModel(
				new Object[][] {
					{"Tec Alpha", "ADF", "N\u00E3o", "5"},
					{"Tec Beta", "AD", "Sim", "0"},
					{"Tec Mega", "ADF", "Sim", "0"},
					{"Tec FI", "A", "N\u00E3o", "2"},
				},
				new String[] {
					"Group", "Modality", "Available", "Hours"
				}
			));
		scrollPane.setViewportView(tableTech);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(10, 154, 464, 60);
		panel_2.add(panel_4);
		
		cbxHours = new JComboBox<String>();
		cbxHours.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		cbxHours.setFont(new Font("SansSerif", Font.BOLD, 13));
		cbxHours.setBackground(Color.WHITE);
		cbxHours.setBounds(329, 21, 86, 26);
		panel_4.add(cbxHours);
		
		JLabel label_2 = new JLabel("Define Hours");
		label_2.setHorizontalTextPosition(SwingConstants.RIGHT);
		label_2.setBounds(331, 6, 73, 14);
		panel_4.add(label_2);
		
		textGroup = new JTextField();
		textGroup.setFont(new Font("Arial", Font.BOLD, 12));
		textGroup.setEditable(false);
		textGroup.setColumns(10);
		textGroup.setBorder(new LineBorder(new Color(192, 192, 192)));
		textGroup.setBounds(20, 21, 297, 26);
		panel_4.add(textGroup);
		
		JLabel label_3 = new JLabel("Selected group to service order");
		label_3.setHorizontalTextPosition(SwingConstants.RIGHT);
		label_3.setBounds(21, 5, 200, 14);
		panel_4.add(label_3);
		
		JButton button = new JButton("Finish");
		button.setBackground(Color.WHITE);
		button.setBounds(22, 274, 96, 30);
		panel_2.add(button);
		
		JButton button_1 = new JButton("New Group");
		button_1.setFocusPainted(false);
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(9, 116, 96, 30);
		panel_2.add(button_1);
		
		JButton button_2 = new JButton("Delete Selected");
		button_2.setFocusPainted(false);
		button_2.setBackground(Color.WHITE);
		button_2.setBounds(110, 115, 122, 30);
		panel_2.add(button_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(7, 39, 240, 63);
		contentPanel.add(panel_3);
		
		textOS = new JTextField();
		textOS.setFont(new Font("Arial", Font.BOLD, 16));
		textOS.setEditable(false);
		textOS.setColumns(10);
		textOS.setBorder(new LineBorder(new Color(192, 192, 192)));
		textOS.setBounds(15, 26, 210, 26);
		panel_3.add(textOS);
		
		JLabel lblServiceOrder = new JLabel("Service Order Number");
		lblServiceOrder.setBounds(15, 11, 210, 14);
		panel_3.add(lblServiceOrder);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel_1.setBounds(6, 340, 487, 124);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(3, 18, 239, 98);
		panel_1.add(scrollPane1);

		tableLocal = new JTable();
		tableLocal.setSelectionBackground(new Color(144, 238, 144));
		tableLocal.setModel(new DefaultTableModel(
			new Object[][] {
				{"Lamp MR  30W", "6", "1200"},
				{"Cap m-120", "1", "200"},
				{"Reator RT1", "3", "120"},
			},
			new String[] {
				"Component", "Need", "Inventory "
			}
		));
		scrollPane1.setViewportView(tableLocal);
		
		JLabel lblManageInventory = new JLabel("Local Inventory");
		lblManageInventory.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblManageInventory.setBounds(5, 7, 125, 14);
		panel_1.add(lblManageInventory);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(245, 18, 239, 98);
		panel_1.add(scrollPane_1);
		
		tableGroup = new JTable();
		tableGroup.setSelectionBackground(new Color(144, 238, 144));
		
		scrollPane_1.setViewportView(tableGroup);
		
		JLabel lblGroupInventory = new JLabel("Group Inventory");
		lblGroupInventory.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblGroupInventory.setBounds(246, 7, 85, 14);
		panel_1.add(lblGroupInventory);
	}

	public JTextField getTextGroup() {
		return textGroup;
	}

	public void setTextGroup(JTextField text) {
		this.textGroup = text;
	}

	public JTable getTable() {
		return tableLocal;
	}

	public void setTable(JTable table) {
		this.tableLocal = table;
	}

	public JTextField getTextOS() {
		return textOS;
	}

	public void setTextOS(JTextField textOS) {
		this.textOS = textOS;
	}
}
