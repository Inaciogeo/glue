package br.org.funcate.glue.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import br.org.funcate.glue.controller.MainPanelController;
import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.controller.TabbedToolBarsController;
import br.org.funcate.glue.controller.listener.DragOutlookBarAdapter;
import br.org.funcate.glue.controller.listener.LabelCanvasExpanderAdapter;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.CalculatorService;
import br.org.funcate.glue.model.LookAndFeelService;
import br.org.funcate.glue.model.canvas.CanvasGraphicsBuffer;
import br.org.funcate.glue.model.canvas.CanvasState;
import br.org.funcate.glue.utilities.PropertiesReader;
import br.org.funcate.plugin.GluePlugin;

/**
 * @brief Main system's panel. This class have all other GUI components of the
 *        system.
 * @author Henrique Asakura
 * @version 2.0
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel{
	private static MainPanel instance;
	public static MainPanel getInstance() {
		if(instance == null){
			//MainPanel window = new MainPanel();
			//instance = window;
			return instance;
		}	
		return instance;
	}
	
	
	public static void setInstance(MainPanel instance) {
		MainPanel.instance = instance;
	}


//	public MainPanel() {
//		
//	}

	/**
	 * This is the left side component of application, and it shows the
	 * geographical informations that can be viewed on the screen.
	 */
	private JOutlookBar outlookBar;

	public JOutlookBar getOutlookBar() {
		return this.outlookBar;
	}

	public void setOutlookBar(JOutlookBar outlookBar) {
		this.outlookBar = outlookBar;
	}

	/**
	 * It brings all tools that can be used in the application.
	 */
	private JTabbedPane tabbedToolBars;
	/**
	 * It shows all the geographic informations selected by user.
	 */
	private AbstractCanvas canvas;
	/**
	 * This comboBox has the available scale values that can be selected by
	 * user.
	 */
	private ComboBoxScale comboScale;
	/**
	 * This is a slider that is used to set the transparency factor of the
	 * foreground image on canvas.
	 */
	private TransparencySliderView transparencySlider;
	/**
	 * It contains the button "Add view" (Add a new group of themes) and the
	 * button "Add Theme" (Add a new theme, that contains a type of geographic
	 * information.
	 */
	private PanelViewThemeAddButton viewThemeAddButtonView;
	
	
	public PanelViewThemeAddButton getViewThemeAddButtonView() {
		return viewThemeAddButtonView;
	}

	public void setViewThemeAddButtonView(
			PanelViewThemeAddButton viewThemeAddButtonView) {
		this.viewThemeAddButtonView = viewThemeAddButtonView;
	}

	/**
	 * This button-shaped label set the size of the canvas, and this can be
	 * expanded to wide screen or normal size.
	 */
	private JLabel lblExpandingCanvasButton;
	private JLabel terraLibText;

	public JLabel getTerraLibText() {
		return terraLibText;
	}

	public JLabel getLblExpandingCanvasButton() {
		return lblExpandingCanvasButton;
	}

	public void setLblExpandingCanvasButton(JLabel lblExpandingCanvasButton) {
		this.lblExpandingCanvasButton = lblExpandingCanvasButton;
	}

	/**
	 * this component is responsible for preview loading status of canvas
	 */
	private JLabel loadingStatusLabel;
	/**
	 * this component is responsible for outlookBar resizing
	 */
	private JLabel lblDragCanvas;
	private JLabel lblStaticBar;
	private JLabel markeImage;

	public JLabel getLblDragCanvas() {
		return lblDragCanvas;
	}

	public void setLblDragCanvas(JLabel lblDragCanvas) {
		this.lblDragCanvas = lblDragCanvas;
	}

	// private GVClient gv;
	/**
	 * @param b
	 * @brief Constructor of NewMainPanel class. It creates and inserts all the
	 *        components
	 * 
	 */
	public MainPanel(boolean withPlugins, ClassLoader loader) {
		long loadingTime = System.currentTimeMillis();
		LookAndFeelService.initializeNimbusLookAndFeel();
		this.setBounds(0, 0, 1020, 570);
		this.setLayout(null);
		try {
			createOutlookBar();
		} catch (Exception e) {
			e.printStackTrace();
			JLabel lblError = new JLabel(
					"Servidor fora do ar. Por favor, tente novamente mais tarde.");
			lblError.setFont(new Font("default", Font.BOLD, 16));
			lblError.setBounds(100, 100, 500, 25);
			this.add(lblError);
			return;
		}
		createSeach();
		createLoadingStatusLabel();
		createTerraLibText();
		createExpandingCanvasButton();
		createDragLabel();
		createScaleCombo();
		createTabbedToolBars();
		createCanvas();
		createTransparencySlider();
		//loadOSM();
		
		System.out.println("The application has loaded in "
				+ (System.currentTimeMillis() - loadingTime) / 1000.f
				+ " seconds.");
		new MainPanelController(this, terraLibText, loadingStatusLabel);
		
		GluePluginsStarter.startPlugins(withPlugins, loader);
	}
	private void createSeach(){
//		AppSingleton singleton = AppSingleton.getInstance();
//		CanvasState state = singleton.getCanvasState();
//		int center = state.getCanvasWidth()/2;
		SearchPanel searchPanel = new SearchPanel();
		searchPanel.setBounds(200, 80, 440, 45);
		this.add(searchPanel);
		setComponentZOrder(searchPanel, 0);
	}
	public void loadOSM(){
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();
		mediator.setToolBarSource("0");
	}
	
	private void createTerraLibText() {
		terraLibText = new JLabel(
				new ImageIcon(
						MainPanel.class
								.getResource("/br/org/funcate/glue/image/terralib_glue.png")));
		terraLibText.setBounds(AppSingleton.getInstance().getCanvasState()
				.getCanvasWidth() - 190, AppSingleton.getInstance()
				.getCanvasState().getCanvasHeight() + 100, 166, 19);
		this.add(terraLibText);
	}

	private void createDragLabel() {
		lblStaticBar = new JLabel("");
		lblStaticBar.setIcon(new ImageIcon(MainPanel.class
				.getResource("/br/org/funcate/glue/image/staticBar.png")));
		lblStaticBar.setBounds(147, 0, 4, 70);
		this.add(lblStaticBar);
		lblDragCanvas = new JLabel("");
		lblDragCanvas.setIcon(new ImageIcon(MainPanel.class
				.getResource("/br/org/funcate/glue/image/dragBar.png")));
		lblDragCanvas.setBounds(147, 70, 4, 1100);
		lblDragCanvas.addMouseListener(new DragOutlookBarAdapter(this));
		lblDragCanvas.addMouseMotionListener(new DragOutlookBarAdapter(this));
		this.add(lblDragCanvas);
	}
	
	private void createExpandingCanvasButton() {
		lblExpandingCanvasButton = new JLabel();
		lblExpandingCanvasButton
				.addMouseListener(new LabelCanvasExpanderAdapter(
						lblExpandingCanvasButton, this));
		lblExpandingCanvasButton.setBounds(150, 70, 20, 25);
		lblExpandingCanvasButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(lblExpandingCanvasButton);
	}

	private void createLoadingStatusLabel() {
		ImageIcon img = ImageIconLoader.createImageIcon(
				"br/org/funcate/glue/image/loadingFalse.gif", MainPanel.class);
		
		loadingStatusLabel = new JLabel(img);
		loadingStatusLabel.setBounds(974, 24, 44, 45);
		this.add(loadingStatusLabel);
	}
	
	public void createMark(int x,int y){
		
		double[] pixel =  CalculatorService.convertFromWorldToPixel(-23.566505,-46.645632);
		
		markeImage = new JLabel("");
		markeImage.setIcon(new ImageIcon(MainPanel.class.getResource("/br/org/funcate/glue/image/Hand-Point-270.png")));
		markeImage.setBounds( x, y, 32, 32);
		markeImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
		//markeImage.setBounds(Math.abs((int)pixel[1])/100,Math.abs((int)pixel[0])/100, 32, 32);
		add(markeImage);
		setComponentZOrder(markeImage, 1);
		
	}

	private void createTransparencySlider() {
		transparencySlider = new TransparencySliderView();
		transparencySlider.setBounds(0, 0, 147, 68);
		this.add(transparencySlider);
	}

	private void createScaleCombo() {
		comboScale = new ComboBoxScale();
		comboScale.setBounds(9, 43, 130, 22);
		this.add(comboScale);
	}

	private void createTabbedToolBars() {
		tabbedToolBars = new JTabbedPane();
		//tabbedToolBars.insertTab("TerraLib", null, createViewThemeAddButton(),"Ferramentas para manipular os dados TerraLib", 0);
		tabbedToolBars.insertTab("Navegação", null, createToolBar(),"Ferramentas para manipular a área de visualização", 0);
		//tabbedToolBars.setSelectedIndex(1);
		tabbedToolBars.setBounds(150, 0, 802, 68);
		new TabbedToolBarsController(tabbedToolBars);
		this.add(tabbedToolBars);
	}

	private PanelViewThemeAddButton createViewThemeAddButton() {
		viewThemeAddButtonView = new PanelViewThemeAddButton();
		viewThemeAddButtonView.setBackground(Color.white);
		viewThemeAddButtonView.setBorder(new LineBorder(Color.black));
		viewThemeAddButtonView.setBounds(0, 0, 155, 38);
		return viewThemeAddButtonView;
	}

	private PanelToolbar createToolBar() {
		return new PanelToolbar();
	}

	private void createCanvas() {
		int[] bounds = new int[4];
		bounds[0] = Integer.parseInt(PropertiesReader
				.getProperty("canvas.bound.x0"));
		bounds[1] = Integer.parseInt(PropertiesReader
				.getProperty("canvas.bound.y0"));
		bounds[2] = Integer.parseInt(PropertiesReader
				.getProperty("canvas.width"));
		bounds[3] = Integer.parseInt(PropertiesReader
				.getProperty("canvas.height"));
		canvas = new CanvasView(bounds[2], bounds[3]);
		canvas.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
		this.add(canvas);
	}

	/**
	 * @param canvas
	 *            the canvas to set
	 */
	public void setCanvas(AbstractCanvas canvas) {
		this.canvas = canvas;
	}

	/**
	 * @return the canvas
	 */
	public AbstractCanvas getCanvas() {
		return canvas;
	}

	private void createOutlookBar() {
		outlookBar = new JOutlookBar();
		outlookBar.setBounds(0, 70, 147, 450);
		URL urlTree = getClass().getClassLoader().getResource(
				"br/org/funcate/glue/image/outlookbar-tree.png");
		URL urlLegend = getClass().getClassLoader().getResource(
				"br/org/funcate/glue/image/outlookbar-legend.png");
		outlookBar.addBar("Árvore", new ImageIcon(urlTree), createTree());
		outlookBar.addBar("Legenda", new ImageIcon(urlLegend),
				createLegendPanel());
		outlookBar.setVisibleBar(0);
		this.add(outlookBar);
	}

	private TreeView createTree() {
		TreeView tree = new TreeView();
		return tree;
	}

	private JPanel createLegendPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel();
		label.setVerticalAlignment(SwingConstants.TOP);
		JScrollPane scroll = new JScrollPane(label);
		panel.setPreferredSize(new Dimension(135, 400));
		scroll.setAutoscrolls(true);
		panel.add(scroll, BorderLayout.CENTER);
		return panel;
	}

	private abstract static class GluePluginsStarter {
		private static File identifyPluginConfigurationFile() {
			String pathPluginsConfigFile = System.getProperty("user.home");
			pathPluginsConfigFile += File.separator
					+ PropertiesReader.getProperty("surf.server.appname.dir");
			pathPluginsConfigFile += File.separator
					+ PropertiesReader.getProperty("plugins.config.file");
			return new File(pathPluginsConfigFile);
		}

		public static void startPlugins(boolean withPlugins, ClassLoader loader) {
			if (!withPlugins)
				return;
			File pluginsConfigFile = identifyPluginConfigurationFile();
			if (pluginsConfigFile.exists()) {
				FileReader fr = null;
				BufferedReader br = null;
				try {
					fr = new FileReader(pluginsConfigFile);
					br = new BufferedReader(fr);
					String pluginAccessClassFullName;
					while ((pluginAccessClassFullName = br.readLine()) != null) {
						pluginAccessClassFullName = pluginAccessClassFullName
								.trim();
						if (pluginAccessClassFullName.equals("")
								|| !Pattern.compile("^[a-zA-Z_$]")
										.matcher(pluginAccessClassFullName)
										.find()) {
							continue;
						}
						executeGluePluginRunMethod(pluginAccessClassFullName,
								loader);
					}
				} catch (FileNotFoundException e) {
					throw new RuntimeException("Arquivo " + pluginsConfigFile
							+ " não existe", e);
				} catch (IOException e) {
					throw new RuntimeException("Erro de leitura no arquivo "
							+ pluginsConfigFile, e);
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							throw new RuntimeException(
									"Não conseguiu fechar o buffer de leitura",
									e);
						}
					}
					if (fr != null) {
						try {
							fr.close();
						} catch (IOException e) {
							throw new RuntimeException(
									"Não conseguiu fechar o arquivo "
											+ pluginsConfigFile, e);
						}
					}
				}
			}
		}

		private static void executeGluePluginRunMethod(
				String pluginAccessClassFullName, ClassLoader loader) {
			Class<?> pluginAccessClass;
			try {
				pluginAccessClass = loader.loadClass(pluginAccessClassFullName);
				Object instance = pluginAccessClass.newInstance();
				if (instance instanceof GluePlugin) {
					GluePlugin pluginAccessInstance = (GluePlugin) instance;
					pluginAccessInstance.run();
				}
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Não conseguiu localizar a classe "
						+ pluginAccessClassFullName
						+ " definida no arquivo de configuração", e);
			} catch (InstantiationException e) {
				throw new RuntimeException(
						"Não conseguiu criar uma instancia de "
								+ pluginAccessClassFullName, e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException("Não pode acessar a instancia de "
						+ pluginAccessClassFullName, e);
			}
		}
	}
}
