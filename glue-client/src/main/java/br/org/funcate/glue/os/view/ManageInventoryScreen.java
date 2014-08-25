package br.org.funcate.glue.os.view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

@SuppressWarnings("serial")
public class ManageInventoryScreen extends JDialog {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageInventoryScreen frame = new ManageInventoryScreen();
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
	public ManageInventoryScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 371);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(10, 189, 486, 132);
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
		
		
		
	}
}
