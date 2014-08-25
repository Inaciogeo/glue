package br.org.funcate.glue.os.view;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class TechnicalGroupScreen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private final JComboBox<String> cboHours = new JComboBox<String>();
	private JTextField textSelectedGroup;
	private JButton btnNewGroup;
	private JButton btnDeleteSelected;
	private JButton btnFinish;
	private static TechnicalGroupScreen instance; 
	
	public static TechnicalGroupScreen getInstance(){
		if(instance == null)
			instance = new TechnicalGroupScreen();
		
		return instance;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TechnicalGroupScreen dialog = new TechnicalGroupScreen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TechnicalGroupScreen() {
		setTitle("Technical Group");
		setBounds(100, 100, 493, 351);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(6, 6, 465, 22);
		contentPanel.add(panel);
		
		JLabel lblManagerTechnicalGroup = new JLabel("Manager Technical Group");
		lblManagerTechnicalGroup.setFont(new Font("Arial", Font.BOLD, 14));
		lblManagerTechnicalGroup.setBounds(145, 3, 200, 17);
		panel.add(lblManagerTechnicalGroup);
		
		JLabel lblAvaibleGroup = new JLabel("Technical Groups Information");
		lblAvaibleGroup.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblAvaibleGroup.setBounds(22, 37, 189, 14);
		contentPanel.add(lblAvaibleGroup);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 52, 441, 106);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textSelectedGroup.setText((String) table.getValueAt(table.getSelectedRow(), 0));
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setSelectionBackground(new Color(144, 238, 144));
		table.setModel(new DefaultTableModel(
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
		table.getColumnModel().getColumn(0).setPreferredWidth(140);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(45);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(192, 192, 192)));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(22, 190, 438, 76);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		cboHours.setFont(new Font("SansSerif", Font.BOLD, 13));
		cboHours.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8"}));
		cboHours.setBackground(new Color(255, 255, 255));
		cboHours.setBounds(329, 21, 86, 26);
		panel_1.add(cboHours);
		
		JLabel lblHours = new JLabel("Define Hours");
		lblHours.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblHours.setBounds(331, 5, 73, 14);
		panel_1.add(lblHours);
		
		textSelectedGroup = new JTextField();
		textSelectedGroup.setEditable(false);
		textSelectedGroup.setFont(new Font("Arial", Font.BOLD, 12));
		textSelectedGroup.setColumns(10);
		textSelectedGroup.setBorder(new LineBorder(new Color(192, 192, 192)));
		textSelectedGroup.setBounds(20, 21, 297, 26);
		panel_1.add(textSelectedGroup);
		
		JLabel lblGroup = new JLabel("Selected group to service order");
		lblGroup.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblGroup.setBounds(21, 5, 200, 14);
		panel_1.add(lblGroup);
		
		btnFinish = new JButton("Finish");
		btnFinish.setBackground(Color.WHITE);
		btnFinish.setBounds(22, 274, 96, 30);
		contentPanel.add(btnFinish);
		
		btnNewGroup = new JButton("New Group");
		btnNewGroup.setBackground(Color.WHITE);
		btnNewGroup.setBounds(22, 151, 96, 30);
		contentPanel.add(btnNewGroup);
		
		btnDeleteSelected = new JButton("Delete Selected");
		btnDeleteSelected.setBackground(Color.WHITE);
		btnDeleteSelected.setBounds(123, 151, 122, 30);
		contentPanel.add(btnDeleteSelected);
	}
}
