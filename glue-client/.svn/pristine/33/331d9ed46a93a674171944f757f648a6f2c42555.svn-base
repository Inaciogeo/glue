package br.org.funcate.glue.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.ListSelectionModel;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.canvas.CanvasGraphicsBuffer;
import br.org.funcate.glue.model.canvas.CanvasService;
import br.org.funcate.glue.model.request.GoogleEnum;
import br.org.funcate.glue.utilities.Utils;
import br.org.funcate.glue.utilities.XMLJDomReader;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.AbstractListModel;

public class ScreenRequetServices extends JDialog {

	private static final long serialVersionUID = -7144045290502352908L;
	private final JPanel contentPanel = new JPanel();
	private JList<String> list;
	private DefaultListModel<String> listModel;
	private HashMap<String, String> requestMap;
	private HashMap<String, String> descMap;
	private String name;
	private JLabel lbl_url;
	private JTextArea textAreaDesc;
	private Element rootNode;
	private HashMap<String, String> idMap;
	private static String value;
	private static ScreenRequetServices instance;
	private JLabel lblImage;
	private JButton btnCleanMap;
	
	public static ScreenRequetServices getInstance(){
		if(instance == null){
			instance = new ScreenRequetServices();
		}
		return instance;
	}

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		try {
			ScreenRequetServices dialog = new ScreenRequetServices();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "serial", "rawtypes" })
	public ScreenRequetServices() {
		setModal(false);
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				Toolbar.getWms().setEnabled(true);
//				  try {
					  listModel = new DefaultListModel<String>();
					  requestMap = new HashMap<String, String>();
					  descMap = new HashMap<String, String>();
					  idMap = new HashMap<String,String>();
					  
//					  rootNode = XMLJDomReader.JDomRead("../glue-client/src/main/java/br/org/funcate/glue/utilities/TilePropertyRequest.xml");
//					  List nodeList = rootNode.getChildren("content");
					  
					  Toolbar.getBtnTileRequest().setEnabled(false);
					  
//					for (int i = 0; i < nodeList.size(); i++) {
//					   
//					   Element node = (Element) nodeList.get(i);
//					   name = node.getChildText("name");
//					   listModel.addElement(name);
//					   list.setModel(listModel);
//					   requestMap.put(node.getChildText("name"),node.getChildText("url"));
//					   descMap.put(node.getChildText("name"),node.getChildText("desc"));
//					   idMap.put(node.getChildText("name"),node.getChildText("id"));
					  
					   
//					}
			 
//				  } catch (IOException io) {
//					  io.printStackTrace();
//				  } catch (JDOMException jdomex) {
//					  jdomex.printStackTrace();
//				  }	
			idMap.put("OpenStreetMap","0");
			idMap.put("GoogleMaps","2");
			idMap.put("Bauru","1");
			idMap.put("Tr\u00E2nsito","1");
					  
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
				Toolbar.getWms().setEnabled(true);
			}
		});
		
		setBounds(100, 100, 404, 208);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnApply = new JButton("Aplicar");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mediator mediator = AppSingleton.getInstance().getMediator();
				String value = list.getSelectedValue();
				if(value!=null && value!=""){
					//if(!requestMap.get(list.getSelectedValue()).isEmpty())
						mediator.setToolBarSource(idMap.get(list.getSelectedValue()));
				}else
					GlueMessageDialog.show("selecione um opção de mapa", null, 2);	
			}
		});
		btnApply.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnApply.setBackground(new Color(255, 255, 255));
		btnApply.setBounds(19, 128, 75, 30);
		contentPanel.add(btnApply);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(10, 11, 389, 24);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Servi\u00E7o de Requisi\u00E7\u00E3o Web  ");
		lblNewLabel.setBackground(new Color(192, 192, 192));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblNewLabel.setBounds(6, 1, 377, 22);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 51, 232, 75);
		contentPanel.add(scrollPane);
		
		list = new JList<String>();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"OpenStreetMap", "GoogleMaps", "Bauru", "Tr\u00E2nsito"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
		list.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lbl_url.setText(requestMap.get(list.getSelectedValue()));
				textAreaDesc.setText(descMap.get(list.getSelectedValue()));
				
				setValue(list.getSelectedValue());
				
				String idImage = idMap.get(list.getSelectedValue());
				
				if(idImage.equals("0"))//if '0' set image openstreetmap 
					lblImage.setIcon(new ImageIcon(ScreenRequetServices.class.getResource("/br/org/funcate/glue/image/OpenStreetMap.png")));
				else if(idImage.equals("2"))//if '1' set image google
					lblImage.setIcon(new ImageIcon(ScreenRequetServices.class.getResource("/br/org/funcate/glue/image/rioMap.gif")));
				else{ // set image cgi
					lblImage.setIcon(new ImageIcon(ScreenRequetServices.class.getResource("/br/org/funcate/glue/image/World.gif")));
				}
					
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		list.setBorder(new LineBorder(new Color(192, 192, 192)));
		
		JPanel panel_1 = new JPanel();
		panel_1.setVisible(false);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(192, 192, 192));
		panel_1.setBounds(10, 164, 389, 24);
		contentPanel.add(panel_1);
		
		JLabel lblUrl = new JLabel("URL :");
		lblUrl.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblUrl.setBackground(new Color(230, 230, 250));
		lblUrl.setBounds(6, 1, 36, 22);
		panel_1.add(lblUrl);
		
		lbl_url = new JLabel("");
		lbl_url.setForeground(new Color(0, 0, 128));
		lbl_url.setFont(new Font("SansSerif", Font.ITALIC, 13));
		lbl_url.setBounds(47, 5, 336, 16);
		panel_1.add(lbl_url);
		
		textAreaDesc = new JTextArea();
		textAreaDesc.setVisible(false);
		textAreaDesc.setBackground(new Color(255, 255, 255));
		textAreaDesc.setLineWrap(true);
		textAreaDesc.setFont(new Font("SansSerif", Font.ITALIC, 13));
		textAreaDesc.setBounds(10, 211, 372, 44);
		contentPanel.add(textAreaDesc);
		
		JLabel lblDesc = new JLabel("Descri\u00E7\u00E3o:");
		lblDesc.setVisible(false);
		lblDesc.setFont(new Font("SansSerif", Font.BOLD, 12));  
		lblDesc.setBackground(new Color(230, 230, 250));
		lblDesc.setBounds(10, 191, 75, 22);
		contentPanel.add(lblDesc); 
		
		lblImage = new JLabel("");
		lblImage.setBounds(264, 51, 106, 99);
		contentPanel.add(lblImage);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setIcon(new ImageIcon(ScreenRequetServices.class.getResource("/br/org/funcate/glue/image/World.gif")));
		
		btnCleanMap = new JButton("Remover Camada");
		btnCleanMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mediator mediator = AppSingleton.getInstance().getMediator();
				mediator.setToolBarSource(null);
			}
		});
		btnCleanMap.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnCleanMap.setBackground(Color.WHITE);
		btnCleanMap.setBounds(95, 128, 137, 30);
		contentPanel.add(btnCleanMap);
		Utils.setScreenToCenter(this);
	}

	public static String getValue() {
		return value;
	}

	public static void setValue(String value) {
		ScreenRequetServices.value = value;
	}
}
