package br.org.funcate.glue.view;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import br.org.funcate.glue.controller.listener.InfoToolAdapter;
import br.org.funcate.glue.model.canvas.ThemeAttributesInfoTool;

@SuppressWarnings("serial")
public class InfoToolView extends JFrame {

	private static InfoToolView instance;

	private JPanel mainPanel;
	private JLabel lblTitle;
	private JButton btMap;
	private JButton btClose;
	private JTabbedPane themesTabbedPane;
	@SuppressWarnings("unused")
	private static int width = 500;
	@SuppressWarnings("unused")
	private static int height = 500;
	private JPanel panel_1;

	private InfoToolView() {
		setBackground(new Color(176, 196, 222));
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.white);
		mainPanel.setBounds(5, 5, 540, 298);
		mainPanel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 196, 222));
		panel_1.setBounds(0, 0, 543, 25);
		mainPanel.add(panel_1);
		panel_1.setLayout(null);

		lblTitle = new JLabel("Resultado do Apontamento");
		lblTitle.setForeground(new Color(105, 105, 105));
		lblTitle.setBounds(0, 1, 543, 25);
		panel_1.add(lblTitle);
		lblTitle.setBackground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

		InfoToolAdapter adapter = new InfoToolAdapter(this);

		btMap = new JButton("Ver mapa");
		btMap.setVisible(false);
		btMap.setBackground(new Color(255, 255, 255));
		btMap.setBounds(330, 345, 100, 25);
		btMap.addActionListener(adapter);
		mainPanel.add(btMap);

		btClose = new JButton("Fechar");
		btClose.setBackground(new Color(255, 255, 255));
		btClose.setBounds(459, 260, 72, 28);
		btClose.addActionListener(adapter);
		mainPanel.add(btClose);

		themesTabbedPane = new JTabbedPane();
		themesTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		themesTabbedPane.setRequestFocusEnabled(false);
		themesTabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
		themesTabbedPane.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 10));
		themesTabbedPane.setBackground(new Color(255, 255, 255));
		themesTabbedPane.setBounds(35, 50, 469, 198);
		mainPanel.add(themesTabbedPane);

		getContentPane().add(mainPanel);

		DragPanel drag = new DragPanel(this);
		this.addMouseListener(drag);
		this.addMouseMotionListener(drag);

		getContentPane().setLayout(null);

		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		//this.setSize(554, 387);
		this.setBounds(0, 0, 551, 309);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
	}

	/**
	 * @return the themesTabbedPane
	 */
	public JTabbedPane getThemesTabbedPane() {
		return themesTabbedPane;
	}

	/**
	 * @return the btMap
	 */
	public JButton getBtMap() {
		return btMap;
	}

	/**
	 * @return the btClose
	 */
	public JButton getBtClose() {
		return btClose;
	}

	public static InfoToolView getInstance(List<ThemeAttributesInfoTool> info) {
		if (instance == null) {
			instance = new InfoToolView();
		}
		instance.setModel(info);
		instance.setVisible(true);

		return instance;
	}

	private void setModel(List<ThemeAttributesInfoTool> info) {
		if (themesTabbedPane.getTabCount() > 0) {
			themesTabbedPane.removeAll();
		}
		for (ThemeAttributesInfoTool themeInfo : info) {
			addTab(themeInfo);
		}
	}

	private void addTab(ThemeAttributesInfoTool themeAttributesInfoTool) {
		ThemeAttributesPanel panel = new ThemeAttributesPanel(
				themeAttributesInfoTool);
		themesTabbedPane.add(themeAttributesInfoTool.getThemeName().toUpperCase(), panel);
		themesTabbedPane.setSelectedIndex(themesTabbedPane.getTabCount() - 1);
		themesTabbedPane.paintAll(themesTabbedPane.getGraphics());
	}

	public void removeTab(String themeName) {
		int index = getSelectedTabIndex(themeName);
		if (index > -1) {
			themesTabbedPane.remove(index);
			themesTabbedPane.paintAll(themesTabbedPane.getGraphics());
		}
	}

	private int getSelectedTabIndex(String themeName) {
		int index = -1;
		for (int i = 0; i < themesTabbedPane.getTabCount(); i++) {
			if (themesTabbedPane.getTitleAt(i).equals(themeName)) {
				index = i;
				break;
			}
		}
		return index;
	}

	public void setSelectedTab(String themeName) {
		for (int i = 0; i < themesTabbedPane.getTabCount(); i++) {
			String themeTabName = themesTabbedPane.getTitleAt(i);
			if (themeTabName.equals(themeName)) {
				themesTabbedPane.setSelectedIndex(i);
				break;
			}
		}
	}
}
