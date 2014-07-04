package br.org.funcate.glue.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import br.org.funcate.glue.controller.CanvasController;
import br.org.funcate.glue.controller.Mediator;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.CalculatorService;
import br.org.funcate.glue.model.ComboBoxScaleService;
import br.org.funcate.glue.model.canvas.CanvasService;
import br.org.funcate.glue.model.canvas.ZoomToolService;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.service.TerraJavaClient;
import br.org.funcate.glue.service.utils.SQLService;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import java.awt.Cursor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class SearchPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textSearch;
	private final JList<String> list;
	private DefaultListModel<String> listModel;
	private final JLabel lblShowResults;
	private final JScrollPane scrollPane;
	private static ArrayList<String> resultIds;
	// private static ArrayList<Integer> resultGeomId;
	private static String markId;
	private JLabel lbl_inf;
	private static boolean isLote = false;

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
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				setBounds(200, 80, 475, 45);
			}
		});
		setBorder(new LineBorder(new Color(192, 192, 192), 2));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		textSearch = new JTextField();
		textSearch.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent arg0) {
				execAlphaNumericLocation();
			}
		});
		textSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				setBounds(200, 80, 440, 45);
			}
		});
		
		textSearch.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) { 
					execGeolocation();
				}
			}
		} );
		textSearch.setForeground(new Color(128, 128, 128));
		textSearch.setBorder(new LineBorder(new Color(192, 192, 192)));
		textSearch.setFont(new Font("Arial", Font.PLAIN, 14));
		textSearch.setBounds(10, 19, 424, 23);
		add(textSearch);
		textSearch.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(11, 66, 423, 56);
		add(scrollPane);

		list = new JList<String>();
		list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setForeground(new Color(100, 149, 237));
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					execGeolocation();
					if(isLote)
						CanvasService.createMark();
					else
						CanvasService.deleteMark();
				}		
			}
		});
		list.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		list.setBorder(null);
		scrollPane.setColumnHeaderView(list);
		lblShowResults = new JLabel("carregando...");
		lblShowResults.setForeground(new Color(112, 128, 144));
		lblShowResults.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		lblShowResults.setBounds(10, 40, 422, 23);
		add(lblShowResults);
		
		JLabel lblNewLabel = new JLabel("Pesquisa geogr\u00E1fica: ");
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBackground(new Color(65, 105, 225));
		lblNewLabel.setForeground(new Color(95, 158, 160));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setBounds(32, 2, 137, 16);
		add(lblNewLabel);
		
		lbl_inf = new JLabel("");
		lbl_inf.setToolTipText("pesquisa de arruamentos ex: \"rua brasil\" ou \"rua brasil, 254\" ou ainda a pequisa de lotes por inscri\u00E7\u00E3o ex:\"20032630034234\"");
		lbl_inf.setIcon(new ImageIcon(SearchPanel.class.getResource("/br/org/funcate/glue/image/iconInfo.png")));
		lbl_inf.setBounds(10, 2, 16, 16);
	
		add(lbl_inf);

	}

//	public String createLinkText(String text, String cor) {
//		text = "<html><font color=\"#" + cor + "\"><u>" + text
//				+ "</u></font></html>";
//		return text;
//	}

	
	public JTextField getTextSearch() {
		return textSearch;
	}

	public void setTextSearch(JTextField textSearch) {
		this.textSearch = textSearch;
	}
	
	/**
	 * 
	 */
	public void execGeolocation(){
			String street = "";
			String number = "";
			if(list.getSelectedValue()!= null && !list.getSelectedValue().isEmpty())
				textSearch.setText(list.getSelectedValue().toLowerCase());
			String searchText = textSearch.getText().toUpperCase();
			
			setBounds(200, 80, 440, 120);
			scrollPane.setBounds(11, 74, 420, 26);

			AppSingleton singleton = AppSingleton.getInstance();
			TerraJavaClient services = singleton.getServices();
			Mediator mediator = singleton.getMediator();
		
			resultIds = new ArrayList<String>();
			SQLService.connect();

			String[] exp = searchText.split(",");
			street = exp[0];

			if (searchText != null && searchText != "" && !searchText.isEmpty()) {
				if (!searchText.matches("[0-9]+")){
					isLote = false;
				if (exp.length <= 1) {
					ResultSet rs = SQLService
							.buildSelect("select object_id from log_lin where nome_logradouro ='"
									+ street.trim() + "'");
					try {
						while (rs.next()) {
							if(rs.getString(1)!=null)
								resultIds.add(rs.getString(1));
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				} else {
					number = exp[1];
					ResultSet rs = SQLService
							.buildSelect("select object_id from lotes_bauru where nome_logradouro ='"
									+ street.trim()
									+ "' and sql = "
									+ number.trim());
					try {
						while (rs.next()) {
							if(rs.getString(1)!=null)
								resultIds.add(rs.getString(1));
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
				int size = resultIds.size();
				int idx = Math.abs(size / 2);
					try {
						ComboBoxScaleService.changeScaleValue(400);
						Box box;
						box = services.getCurrentThemeBox("Arruamentos","geom_id=" + resultIds.get(idx) + "");
						double[] coordIn = CalculatorService.convertFromWorldToPixel(box.getX1(), box.getY1());
						ZoomToolService.pressZoomIn((int) (coordIn[0]+40),(int) coordIn[1]-40);
						ComboBoxScaleService.changeScaleValue(200);
						//mediator.setToolBarSource(ScreenRequetServices.getMapId());
					} catch (GlueServerException e) {
						e.printStackTrace();
					}	
				}else{
					isLote = true;
					ResultSet rs = SQLService
							.buildSelect("select object_id from lotes_bauru where sql ='"+searchText+"'");
					try {
						while (rs.next()) {
							resultIds.add(rs.getString(1));
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					
					try {
						ComboBoxScaleService.changeScaleValue(12);
						Box box = services.getCurrentThemeBox("Lotes","geom_id=" + resultIds.get(0) + "");
						double[] coordIn = CalculatorService.convertFromWorldToPixel(box.getX1(), box.getY1());
						ZoomToolService.pressZoomIn((int) (coordIn[0]+80), (int) (coordIn[1]-80));
						ComboBoxScaleService.changeScaleValue(6);
						//mediator.setToolBarSource(ScreenRequetServices.getMapId());
					} catch (GlueServerException e) {
						e.printStackTrace();
					}	
				}
	
			CanvasController canvasController = mediator.getCanvasController();
			// thread que executa dispatch do evento de drawFeature.
			Thread drawStreet = new Thread(canvasController);
			drawStreet.start();
				
			} else {
				GlueMessageDialog.show("Campo de pequisa deve ser preenchido!",
						null, 2);
			}
	}
	/**
	 *
	 */
	public void execAlphaNumericLocation(){
		int size=0;
		
		String street = "";
		String number = "";
		
		listModel = new DefaultListModel<String>();
		scrollPane.setBounds(11, 74, 420, 76);
		String searchText = textSearch.getText().toUpperCase();
		
		if (!searchText.matches("[0-9]+")){
			String[] exp = searchText.split(",");
			street = exp[0];

			if (searchText != null && searchText != "" && !searchText.isEmpty()) {
				if (exp.length <= 1) {
					SQLService.connect();
					setBounds(200, 80, 440, 180);
					ResultSet rs = SQLService
							.buildSelect("select distinct nome_logradouro from log_lin where nome_logradouro LIKE '%"
									+ street.trim() + "%' order by nome_logradouro ");

					try {
						listModel.clear();
						while (rs.next()) {
							listModel.addElement(rs.getString(1));
							size++;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					number = exp[1];
					SQLService.connect();
					setBounds(200, 80, 440, 180);
					ResultSet rs = SQLService
							.buildSelect("select distinct nome_logradouro from log_lin where nome_logradouro LIKE '%"
									+ street.trim()
									+ "%' and tch_ofinal = "
									+ number.trim()+" order by nome_logradouro");

					try {
						listModel.clear();
						while (rs.next()) {
							listModel.addElement(rs.getString(1) + ", "
									+ number.trim());
							size++;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				list.setModel(listModel);
				if (size > 4)
					lblShowResults.setText("A pesquisa retornou " + size
							+ " registro(s) de ARRUAMENTO(S), exibidos: 4");
				else
					lblShowResults.setText("A pesquisa retornou " + size
							+ " registro(s) de ARRUAMENTO(S), exibidos: " + size);

			}
		}else{
			if (searchText != null && searchText != "" && !searchText.isEmpty()) {
				
				SQLService.connect();
				setBounds(200, 80, 440, 180);
				ResultSet rs = SQLService
						.buildSelect("select sql from lotes_bauru where sql like '"+searchText.trim()+"%'");

				try {
					listModel.clear();
					while (rs.next()) {
						listModel.addElement(rs.getString(1));
						size++;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				list.setModel(listModel);
				if (size > 4)
					lblShowResults.setText("A pesquisa retornou " + size
							+ " registro(s) de LOTE(S), exibidos: 4");
				else
					lblShowResults.setText("A pesquisa retornou " + size
							+ " registro(s) de LOTE(S), exibidos: " + size);
				
			}
		}
	}
}
