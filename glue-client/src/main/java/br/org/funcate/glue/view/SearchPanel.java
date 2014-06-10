package br.org.funcate.glue.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.CalculatorService;
import br.org.funcate.glue.model.canvas.CanvasState;
import br.org.funcate.glue.model.canvas.ZoomToolService;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.service.TerraJavaClient;
import br.org.funcate.glue.service.utils.SQLService;
import br.org.funcate.glue.tool.ZoomAreaTool;
import br.org.funcate.glue.utilities.Utils;
import br.org.funcate.plugin.GluePluginService;

public class SearchPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textSearch;
	private JButton btnSearch;
	private final JList<String> list;
	private DefaultListModel<String> listModel;
	private final JLabel lblGO;
	private final JScrollPane scrollPane;
	private static ArrayList<String> resultIds;
	// private static ArrayList<Integer> resultGeomId;
	private static String markId;

	public static String getMarkId() {
		return markId;
	}

	public static ArrayList<String> getResultIds() {
		return resultIds;
	}

	public static void setMarkId(String markId) {
		SearchPanel.markId = markId;
	}

	public static void setResultIds(ArrayList<String> resultIds) {
		SearchPanel.resultIds = resultIds;
	}

	/**
	 * Create the panel.
	 */
	public SearchPanel() {

		setBorder(new LineBorder(new Color(192, 192, 192)));
		setBackground(new Color(255, 255, 255));
		setLayout(null);

		textSearch = new JTextField();
		textSearch.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent arg0) {
				listModel = new DefaultListModel<String>();
				scrollPane.setBounds(11, 74, 420, 76);
				String searchText = textSearch.getText().toUpperCase();
				if (!searchText.isEmpty()) {
					SQLService.connect();
					setBounds(200, 80, 440, 180);
					ResultSet rs = SQLService
							.buildSelect("select distinct nome from log_lin where nome LIKE '%"
									+ searchText + "%'");

					try {
						listModel.clear();
						while (rs.next()) {
							listModel.addElement(rs.getString(1));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					list.setModel(listModel);
				}
			}
		});
		textSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// AppSingleton singleton = AppSingleton.getInstance();
				// CanvasState state = singleton.getCanvasState();
				// int center = ((state.getCanvasWidth())/2);
				setBounds(200, 80, 440, 45);
				lblGO.setVisible(false);
			}
		});

		textSearch.setForeground(new Color(128, 128, 128));
		textSearch.setBorder(new LineBorder(new Color(192, 192, 192)));
		textSearch.setFont(new Font("Arial", Font.PLAIN, 14));
		textSearch.setBounds(10, 11, 370, 23);
		add(textSearch);
		textSearch.setColumns(10);
		btnSearch = new JButton("Ir");

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AppSingleton singleton = AppSingleton.getInstance();
				TerraJavaClient services = singleton.getServices();
				Mediator mediator = singleton.getMediator();
				CanvasState canvasState = singleton.getCanvasState();

				resultIds = new ArrayList<String>();
				SQLService.connect();
				String searchText = textSearch.getText().toUpperCase();
				if (searchText != null && searchText != ""
						&& !searchText.isEmpty()) {
					ResultSet rs = SQLService
							.buildSelect("select object_id from log_lin where nome LIKE '%"
									+ searchText + "'");
					try {
						while (rs.next()) {
							resultIds.add(rs.getString(1));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					int size = resultIds.size();
					int idx = Math.abs(size / 2);

					canvasState.getScale();

					AppSingleton.getInstance().getMediator()
							.setCurrentTool(new ZoomAreaTool());

					try {
						Box box = services.getCurrentThemeBox("Arruamentos",
								"geom_id=" + resultIds.get(idx) + "");
						double[] coordIn = CalculatorService
								.convertFromWorldToPixel(box.getX1(),
										box.getY1());
						double[] coordOut = CalculatorService
								.convertFromWorldToPixel(box.getX2(),
										box.getY2());
						GluePluginService.setCurrentTool(new ZoomAreaTool());
						ZoomToolService.pressZoomArea((int) (coordIn[0]),
								(int) coordIn[1]);
						ZoomToolService.releaseZoomArea((int) (coordOut[0]),
								(int) coordOut[1]);
						ZoomToolService.zoomOut((int) (coordIn[0]),
								(int) coordIn[1], true);

						mediator.setToolBarSource("0");// set openStreetMap
						// mediator.setToolBarSource(null);

					} catch (GlueServerException e) {
						e.printStackTrace();
					}
				} else {
					GlueMessageDialog.show(
							"Campo de pequisa deve ser preenchido!", null, 2);
				}
			}
		});

		btnSearch.setBorder(null);
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(95, 158, 160));
		btnSearch.setFont(new Font("Arial", Font.BOLD, 14));
		btnSearch.setBounds(381, 8, 53, 28);
		add(btnSearch);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(11, 66, 423, 58);
		add(scrollPane);

		list = new JList<String>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					textSearch.setText(list.getSelectedValue().toLowerCase());
					lblGO.setVisible(true);
					setBounds(200, 80, 440, 120);
					scrollPane.setBounds(11, 74, 420, 26);
				}
			}
		});
		list.setFont(new Font("Arial", Font.ITALIC, 12));
		list.setBorder(null);
		scrollPane.setColumnHeaderView(list);
		Utils.moveScreen(this);

		lblGO = new JLabel(
				"clique em \"Ir\" para indicar no mapa o endere\u00E7o selecionado:");
		lblGO.setVisible(false);
		lblGO.setFont(new Font("Arial", Font.ITALIC, 12));
		lblGO.setBounds(10, 31, 422, 23);
		add(lblGO);

	}

	public String createLinkText(String text, String cor) {
		text = "<html><font color=\"#" + cor + "\"><u>" + text
				+ "</u></font></html>";
		return text;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public JTextField getTextSearch() {
		return textSearch;
	}

	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}

	public void setTextSearch(JTextField textSearch) {
		this.textSearch = textSearch;
	}
}
