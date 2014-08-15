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
import br.org.funcate.glue.model.canvas.CanvasState;
import br.org.funcate.glue.model.canvas.ZoomToolService;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.service.TerraJavaClient;
import br.org.funcate.glue.service.utils.GeographicalSearchService;
import br.org.funcate.glue.utilities.PropertiesReader;

import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import java.awt.Cursor;

/**
 * Brief:This class is responsible for initializing 
 * the graphics components of geographical research
 * and the implementation of methods of alphanumeric 
 * and geographic search. 
 */
//TODO: MVC
public class SearchPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField textSearch;
	private final JList<String> list;
	private DefaultListModel<String> listModel;
	private final JLabel lblShowResults;
	private final JScrollPane scrollPane;
	private static ArrayList<String> streetIds;
	private static String markId;
	private static ArrayList<String> lotIds;
	private JLabel lbl_numbers;
	private String streetTable;
	private String streetColumn;
	private String lotTable;
	private String lotColumn1;
	private String lotColumn2;
	private String lotThemeName;
	private String streetThemeName;
	private String lotColumn3;
	
	public static String getMarkId() {
		return markId;
	}

	public static ArrayList<String> getStreetIds() {
		return streetIds;
	}

	public static void setMarkId(String markId) {
		SearchPanel.markId = markId;
	}

	public static void setStreetIds(ArrayList<String> streetIds) {
		SearchPanel.streetIds = streetIds;
	}
	
	public static ArrayList<String> getLotIds() {
		return lotIds;
	}

	public static void setLotIds(ArrayList<String> lotIds) {
		SearchPanel.lotIds = lotIds;
	}

	public JList<String> getList() {
		return list;
	}

	/**
	 * Create the panel.
	 */
	public SearchPanel() {
		
		streetTable = PropertiesReader.getProperty("search.street.table.name");
		streetColumn = PropertiesReader.getProperty("search.street.table.column1");
		lotTable = PropertiesReader.getProperty("search.lot.table.name");
		lotColumn1 = PropertiesReader.getProperty("search.lot.table.column1");
		lotColumn2 = PropertiesReader.getProperty("search.lot.table.column2");
		lotColumn3 = PropertiesReader.getProperty("search.lot.table.column3");
		lotThemeName = PropertiesReader.getProperty("search.lot.themeName");
		streetThemeName = PropertiesReader.getProperty("search.street.themeName");
		
		setBorder(new LineBorder(new Color(192, 192, 192)));
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
				setSize(440, 45);
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
					//execGeolocation();
				}
			}
		} );
		textSearch.setForeground(new Color(128, 128, 128));
		textSearch.setBorder(new LineBorder(new Color(192, 192, 192)));
		textSearch.setFont(new Font("Arial", Font.PLAIN, 14));
		textSearch.setBounds(10, 18, 424, 22);
		add(textSearch);
		textSearch.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(11, 66, 423, 56);
		add(scrollPane);

		list = new JList<String>();
		list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setForeground(new Color(0, 0, 128));
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {
					execGeolocation();
				}	
			}
		});
		list.setFont(new Font("Arial", Font.BOLD, 12));
		list.setBorder(null);
		scrollPane.setColumnHeaderView(list);
		lblShowResults = new JLabel("Base de dados invalida..!");
		lblShowResults.setForeground(new Color(128, 128, 128));
		lblShowResults.setFont(new Font("Arial", Font.PLAIN, 12));
		lblShowResults.setBounds(10, 42, 422, 14);
		add(lblShowResults);
		
		JLabel lblNewLabel = new JLabel("Pesquisa geogr\u00E1fica: ");
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBackground(new Color(65, 105, 225));
		lblNewLabel.setForeground(new Color(128, 128, 128));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		lblNewLabel.setBounds(9, 3, 137, 16);
		add(lblNewLabel);
		
		lbl_numbers = new JLabel("");
		lbl_numbers.setForeground(new Color(128, 128, 128));
		lbl_numbers.setBackground(new Color(192, 192, 192));
		lbl_numbers.setFont(new Font("Arial", Font.PLAIN, 12));
		lbl_numbers.setBounds(10, 56, 424, 16);
		add(lbl_numbers);
		

	}

//	public String createLinkText(String text, String cor) {
//		text = "<html><font color=\"#" + cor + "\"><u>" + text
//				+ "</u></font></html>";
//		return text;
//	}

	
	public JTextField getTextSearch() {
		return textSearch;
	}
	
	public void cleanDataSource(){
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
//		CanvasState state = singleton.getCanvasState();
//		state.setDataSource("OpenStreetMap");
//		ScreenRequetServices.setValue("OpenStreetMap");
		mediator.setToolBarSource(null);	
	}

	public void setTextSearch(JTextField textSearch) {
		this.textSearch = textSearch;
	}

	/** 
	 * Brief: run geographical search of lots and streets, returned the box to the world map, 
	 * and still loads geometries for the highlight on the map. 
	 */
	public void execGeolocation(){
		cleanDataSource();
		String street = "";
		//String number = "";
		if (list.getSelectedValue() != null&& !list.getSelectedValue().isEmpty())
			textSearch.setText(list.getSelectedValue().toLowerCase());
		
		String searchText = textSearch.getText().toUpperCase();
		
		scrollPane.setBounds(11, 74, 420, 26);

		AppSingleton singleton = AppSingleton.getInstance();
		TerraJavaClient services = singleton.getServices();
		Mediator mediator = singleton.getMediator();
		CanvasState state = singleton.getCanvasState();
		streetIds = new ArrayList<String>();
		String[] lot = searchText.split("LOTE:"); 
		String[] exp = lot[0].split(",");
		street = exp[0];

			if (searchText != null && searchText != "" && !searchText.isEmpty()) {
				if (!searchText.matches("[0-9]+") && lot.length<=1){
					
				//if (exp.length <= 1) {
					
					ResultSet rs = GeographicalSearchService
						.buildSelect("select object_id from "+streetTable+" where "+streetColumn+" ='"
									+ street.trim() + "'");
					try {
						while (rs.next()) {
							if(rs.getString(1)!=null)
								streetIds.add(rs.getString(1));
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
//				} else { usado anteriormente para peaquisar rua pelo endereço
//					number = exp[1];
//					ResultSet rs = GeographicalSearchService
//							.buildSelect("select distinct object_id from "+lotTable+" where "+lotColumn1+" ='"
//									+ street.trim()
//									+ "' and "+lotColumn2+" = "
//									+ "'"+number.trim()+"'");
//					try {
//						while (rs.next()) {
//							if(rs.getString(1)!=null)
//								streetIds.add(rs.getString(1));
//						}
//					} catch (SQLException ex) {
//						ex.printStackTrace();
//					}
//				}
				int size = streetIds.size();
				int idx = Math.abs(size / 2);
					try {
						ComboBoxScaleService.changeScaleValue(200);
						Box box;
						box = services.getCurrentThemeBox(streetThemeName,"geom_id=" + streetIds.get(idx) + "");
						double[] coordIn = CalculatorService.convertFromWorldToPixel(box.getX1(), box.getY1());
						ZoomToolService.pressZoomIn((int) (coordIn[0]+20),(int) coordIn[1]-20);
						ComboBoxScaleService.changeScaleValue(100);
						
					} catch (GlueServerException e) {
						e.printStackTrace();
					}	
				}else{
					ResultSet rs;
					lotIds = new ArrayList<String>();
					if(lot.length<=1){
						rs = GeographicalSearchService.buildSelect("select object_id from "+lotTable+" where "+lotColumn2+" ='"+lot[0]+"'");
					}else{
						rs = GeographicalSearchService.buildSelect("select object_id from "+lotTable+" where "+lotColumn2+" ='"+lot[1]+"'");
					}
						
					try {
						while (rs.next()) {
							lotIds.add(rs.getString(1));
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
					
					try {
						ComboBoxScaleService.changeScaleValue(24);
						Box box = services.getCurrentThemeBox(lotThemeName,"geom_id=" + lotIds.get(0) + "");
						double[] coordIn = CalculatorService.convertFromWorldToPixel(box.getX1(), box.getY1());
						ZoomToolService.pressZoomIn((int) (coordIn[0]+40), (int) (coordIn[1]-40));
						ComboBoxScaleService.changeScaleValue(12);
						
					} catch (GlueServerException e) {
						e.printStackTrace();
					}	
				}
	
			CanvasController canvasController = mediator.getCanvasController();
			// thread que executa dispatch do evento de drawFeature.
			state.setGvSource("DrawFeatureEvent");
			Thread drawStreet = new Thread(canvasController);
			drawStreet.start();
		
			} else {
				GlueMessageDialog.show("Campo de pequisa deve ser preenchido!",
						null, 2);
			}
			lbl_numbers.setText("");
	}
	/** 
	 * Brief: executes search in the database of registration batches and streets by street names,
	 * and may return batches or streets depending on the type of input: numeric or textual 
	 * or both when separated by commas.
	 */
	public void execAlphaNumericLocation(){
		int size=0;
		@SuppressWarnings("unused")
		String streetName = "";
		@SuppressWarnings("unused")
		String streetNumber = "";
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
					setSize(440, 180);
					ResultSet rs = GeographicalSearchService
							.buildSelect("select distinct "+streetColumn+" from "+streetTable+" where "+streetColumn+" LIKE '%"
									+ street.trim() + "%' order by "+streetColumn);

					try {
						listModel.clear();
						while (rs.next()) {
							listModel.addElement(rs.getString(1));
							size++;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if (size > 4)
						lblShowResults.setText("A pesquisa retornou " + size
								+ " ARRUAMENTO(S), exibidos: 4");
					else
						lblShowResults.setText("A pesquisa retornou " + size
								+ " ARRUAMENTO(S), exibidos: " + size);
				} else {
					number = exp[1];
					setSize(440, 180);
					ResultSet rs = GeographicalSearchService
							.buildSelect("select distinct "+lotColumn1+","+lotColumn2+","+lotColumn3+" from "+lotTable+" where "+lotColumn1+" LIKE '%"
									+ street.trim()+ "%'" 
									+ " and "+lotColumn3+" LIKE '%"
									+ ""+number.trim()+"%'"+" order by "+lotColumn3);

					try {
						listModel.clear();
						while (rs.next()) {
							listModel.addElement(rs.getString(1)+","+rs.getString(3)+"   lote:"+rs.getString(2));
							streetName = rs.getString(1);
							streetNumber += rs.getString(3)+",";
							size++;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					list.setModel(listModel);
					
					lblShowResults.setText("Encontrado(s) "+ size +" resultado(s)");
					//lbl_numbers.setText(streetNumber);
				}

				list.setModel(listModel);
			}
		}else{
			if (searchText != null && searchText != "" && !searchText.isEmpty()) {
				setSize(440, 180);
				ResultSet rs = GeographicalSearchService
						.buildSelect("select distinct "+lotColumn2+" from "+lotTable+" where "+lotColumn2+" like '"+searchText.trim()+"%'");

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
