package br.org.funcate.glue.os.view;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JTable;

import br.org.funcate.glue.controller.CanvasController;
import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.controller.TreeController;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.canvas.CanvasState;
import br.org.funcate.glue.os.model.HighlightRenderer;
import br.org.funcate.glue.os.model.OSState;
import br.org.funcate.glue.utilities.Utils;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServiceOrderOnMapScreen extends JDialog implements Runnable {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFind;
	private JTable table;
	private static ServiceOrderOnMapScreen instance;
	private JScrollPane scrollPane;
	private JButton btnLoad;
	private JButton btnProgram;
	
	public static ServiceOrderOnMapScreen getInstance() {
		if(instance == null)
			instance = new ServiceOrderOnMapScreen();
		return instance;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ServiceOrderOnMapScreen dialog = new ServiceOrderOnMapScreen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ServiceOrderOnMapScreen() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				AppSingleton singleton = AppSingleton.getInstance();
				CanvasState state = singleton.getCanvasState();
				Mediator mediator = singleton.getMediator();
				CanvasController canvasController = mediator.getCanvasController();
				
				state.setGvSource("OSDrawFeatureEvent");
				Thread drawStreet = new Thread(canvasController);
				drawStreet.start();
			}
		});
		setBounds(100, 100, 460, 360);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 426, 22);
		contentPanel.add(panel);
		
		JLabel lblService = new JLabel("Service Orders List");
		lblService.setFont(new Font("Arial", Font.BOLD, 14));
		lblService.setBounds(142, 3, 154, 17);
		panel.add(lblService);
		
		JLabel lblNewLabel = new JLabel("All orders services on the map");
		lblNewLabel.setBounds(22, 95, 186, 14);
		contentPanel.add(lblNewLabel);
		
		textFind = new JTextField();
		textFind.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				String value = textFind.getText();

	            for (int row = 0; row <= table.getRowCount() - 1; row++) {

	                for (int col = 0; col <= table.getColumnCount() - 1; col++) {

	                    if (value.equals(table.getValueAt(row, 0))) {

	                        // this will automatically set the view of the scroll in the location of the value
	                        table.scrollRectToVisible(table.getCellRect(row, 0, true));

	                        // this will automatically set the focus of the searched/selected row/value
	                        table.setRowSelectionInterval(row, row);

	                        for (int i = 0; i <= table.getColumnCount() - 1; i++) {
	                            table.getColumnModel().getColumn(i).setCellRenderer(new HighlightRenderer());
	                        }
	                    }
	                }
	            }
			}
		});
		textFind.setFont(new Font("Arial", Font.BOLD, 12));
		textFind.setColumns(10);
		textFind.setBorder(new LineBorder(new Color(192, 192, 192)));
		textFind.setBounds(19, 58, 407, 26);
		contentPanel.add(textFind);
		
		JLabel lblFind = new JLabel("Find");
		lblFind.setBounds(20, 43, 44, 14);
		contentPanel.add(lblFind);
		
		scrollPane = new JScrollPane();
		
		scrollPane.setBounds(19, 111, 407, 157);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String ip = table.getValueAt(table.getSelectedRow(), 0).toString();
				if(ip!="")
					OSState.setOsOnMap(ip);
					textFind.setText(ip);
			}
		});
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		
		btnLoad = new JButton("Load services ordes in area ");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.setModel(TreeController.dataModel);//precisa melhorar arquitetura de acesso
			}
		});
		btnLoad.setBackground(Color.WHITE);
		btnLoad.setBounds(22, 260, 186, 35);
		contentPanel.add(btnLoad);
		
		btnProgram = new JButton("Program Service Order");
		btnProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProgramServiceOrderScreen screen = new ProgramServiceOrderScreen();
				screen.setVisible(true); 
			}
		});
		btnProgram.setBackground(Color.WHITE);
		btnProgram.setBounds(260, 275, 164, 30);
		contentPanel.add(btnProgram);
		Utils.setScreenToCenter(this);
	}

	public JTextField getTextFind() {
		return textFind;
	}

	public void setTextFind(JTextField textFind) {
		this.textFind = textFind;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	@Override
	public void run() {
		
	}
}

