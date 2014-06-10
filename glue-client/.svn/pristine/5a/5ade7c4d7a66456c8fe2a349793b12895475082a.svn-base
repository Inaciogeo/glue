package br.org.funcate.glue.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.controller.listener.ExpansionListener;
import br.org.funcate.glue.controller.listener.TreeAdapter;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.LookAndFeelService;
import br.org.funcate.glue.model.UserType;
import br.org.funcate.glue.model.tree.CustomNode;

@SuppressWarnings("serial")
public class TreeView extends JPanel {

	/** < Attribute type JPopupMenu > */

	private JPopupMenu popMenu;

	/** < Attribute type CustomNode > */

	private static CustomNode tool;

	/** < Attribute type JScrollPane > */

	public JScrollPane splScroll;

	/** < Attribute type JTree > */

	private JTree tree;

	/** < Attribute type CustomNode > */

	private CustomNode root;

	/** < Attribute type TreePath */

	private TreePath path;

	private TreeAdapter treeAdapter;
	private JMenuItem createChildTheme;
	private JMenuItem renameTheme;
	private JMenuItem deleteTheme;
	private JMenuItem exportTheme;
	private JMenuItem propertiesTheme;
	private JMenuItem srchAtribTheme;
	private JMenuItem srchSpclTheme;
	private JMenuItem expand;
	private JMenuItem collapse;
	private JMenuItem createTheme;
	private JMenuItem renameView;
	private JMenuItem deleteView;
	private JMenuItem importView;
	private JMenuItem persist;
	private JMenuItem revert;
	private JMenuItem properties;
	private JMenuItem createView;
	private Mediator mediator;

	public TreeView() {
		// LookAndFeelService.changeLookAndFeelForDefault();
		this.setLayout(new BorderLayout());
		treeAdapter = new TreeAdapter(this);
		mediator = AppSingleton.getInstance().getMediator();
		mediator.setUserType(UserType.ADMIN);
		tool = mediator.createToolBar();
		root = mediator.createRoot();
		tree = createTree(root);
		treeAdapter.setTree(tree);
		/*
		 * JLabel lbl = new JLabel("Legenda:");
		 * lbl.setFont(lbl.getFont().deriveFont(Font.BOLD)); lbl.setBounds(5,
		 * 403, 150, 20); add(lbl); add(createSubtitle());
		 */

		UIManager.put("FileChooser.lookInLabelText", "Escolher caminho");
		UIManager.put("FileChooser.fileNameLabelText", "Nome do arquivo:");
		UIManager.put("FileChooser.filesOfTypeLabelText", "Arquivo do tipo:");
		UIManager.put("FileChooser.cancelButtonText", "Cancelar");
		UIManager.put("FileChooser.saveButtonText", "Salvar");
		UIManager.put("FileChooser.openButtonText", "Abrir");
		UIManager.put("FileChooser.upFolderToolTipText", "Um nível acima");
		UIManager.put("FileChooser.newFolderToolTipText", "Criar nova pasta");
		UIManager.put("FileChooser.listViewButtonToolTipText", "Lista");
		UIManager.put("FileChooser.detailsViewButtonToolTipText", "Detalhes");
		UIManager.put("FileChooser.fileNameHeaderText", "Nome");
		UIManager.put("FileChooser.fileSizeHeaderText", "Tamanho");
		UIManager.put("FileChooser.fileTypeHeaderText", "Tipo");
		UIManager.put("FileChooser.fileDateHeaderText", "Data");
		UIManager.put("FileChooser.fileAttrHeaderText", "Atributos");
		UIManager.put("FileChooser.updateButtonText", "Alterar");
		UIManager.put("OptionPane.yesButtonText", "Sim");
		UIManager.put("OptionPane.cancelButtonText", "Cancelar");
		UIManager.put("OptionPane.noButtonText", "Não");
		
		if(root.isLeaf() == false){
			mediator.setCurrentView((CustomNode) root.getFirstChild());

			treeAdapter.getTree().setSelectionRow(0);
		}
	}

	/**
	 * \brief Function that returns the tree
	 * 
	 * @return An object JTree
	 */

	public JTree getTree() {
		return tree;
	}

	/**
	 * \brief Function that returns the popup menu
	 * 
	 * @return An object JPopupMenu
	 */

	public JPopupMenu getPopMenu() {
		return popMenu;
	}

	/**
	 * \brief Function that returns the root
	 * 
	 * @return The currently selected theme
	 */

	public CustomNode getRoot() {
		return root;
	}

	/**
	 * \brief Function that gets the image path and returns a JLabel containing
	 * the image
	 * 
	 * @return An ImageIcon containing the selected image
	 */

	public static ImageIcon getImage(URL path) {
		/* Returns the icon */
		return new ImageIcon(path);
	}

	/**
	 * @name Set
	 * 
	 *       Functions that modify the state of the attributes
	 */

	public void setTreeVisible(boolean bool) {
		tree.setVisible(bool);
	}

	/**
	 * \brief Function that modifies the popup menu
	 * 
	 * @param popupMenu
	 *            The JPopupMenu that will be added to the tree
	 */

	public void setPopMenu(JPopupMenu popupMenu) {
		popMenu = popupMenu;
	}

	/**
	 * \brief Function that creates the tree and set the renderer, mouse
	 * listener, tree expansion listener and drag and drop listeners
	 * 
	 * @return The customized tree
	 */

	public JTree createTree(CustomNode root) {

		mediator.setDefaultTreeModel(new DefaultTreeModel(root));
		tree = new JTree(mediator.getDefaultTreeModel()) {
			public String getToolTipText(MouseEvent evt) {
				if (getRowForLocation(evt.getX(), evt.getY()) == -1)
					return null;
				TreePath path = getPathForLocation(evt.getX(), evt.getY());
				return ((CustomNode) path.getLastPathComponent()).getToolTipText();
			}
		};

		tree.setToolTipText("");
		tree.addKeyListener(treeAdapter);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
		tree.setShowsRootHandles(true);
		tree.setBorder(null);
		tree.setCellRenderer(new Renderer());
		tree.addMouseListener(treeAdapter);
		tree.setEditable(false);
		tree.addTreeExpansionListener(new ExpansionListener());
		tree.setRootVisible(false);
		splScroll = createScrollPane(tree);
		tree.addMouseMotionListener(treeAdapter);
		setVisible(true);
		return tree;
	}

	/**
	 * \brief Function that creates the ScrollPane
	 * 
	 * @return The scroll pane of the tree
	 */

	public JScrollPane createScrollPane(JTree tree) {
		splScroll = new JScrollPane(tree);
		// splScroll.setBounds(0, 4, 150, 300);

		this.add(splScroll, BorderLayout.CENTER);
		splScroll.setVisible(true);
		return splScroll;
	}

	/**
	 * \brief Function that creates a specific menu to the theme and implements
	 * the events for each item of the menu
	 * 
	 * @return The popup menu that contains the actions related to the themes
	 */

	public JPopupMenu createPopupTheme(CustomNode node) {

		LookAndFeelService.changeLookAndFeelForDefault();

		popMenu = new JPopupMenu();

		/* Creates menu's items */

		createChildTheme = new JMenuItem("Criar Tema");
		renameTheme = new JMenuItem("Renomear");
		deleteTheme = new JMenuItem("Excluir");
		exportTheme = new JMenuItem("Exportar");
		propertiesTheme = new JMenuItem("Propriedades");
		srchAtribTheme = new JMenuItem("Consulta por atributo");
		srchSpclTheme = new JMenuItem("Consulta espacial");
		expand = new JMenuItem("Expandir");
		collapse = new JMenuItem("Ocultar");

		if (node.getNodeType() == NodeType.NETWORK_THEME && !node.isHiddenExpanded()) {
			expand.setVisible(true);
			collapse.setVisible(false);

		} else {
			expand.setVisible(false);
			collapse.setVisible(true);
		}

		if ((node.getNodeType() != NodeType.NETWORK_THEME) && (node.getNodeType() != NodeType.TREE_THEME)) {
			createChildTheme.setVisible(false);
		} else {
			createChildTheme.setVisible(true);
		}

		if (node.getNodeType() != NodeType.NETWORK_THEME) {
			expand.setVisible(false);
			collapse.setVisible(false);
		}

		/* Add the items to the menu */

		switch (mediator.getUserType()) {
		case ADMIN:
			// popMenu.add(createChildTheme);
			popMenu.add(renameTheme);
			popMenu.add(deleteTheme);
			// popMenu.add(exportTheme);
			// popMenu.add(propertiesTheme);
			// popMenu.add(srchAtribTheme);
			// popMenu.add(srchSpclTheme);
			popMenu.add(expand);
			popMenu.add(collapse);
			break;
		case DEFAULT:
			popMenu.add(expand);
			popMenu.add(collapse);
			break;
		default:
			break;

		}
		renameTheme.addActionListener(treeAdapter);
		deleteTheme.addActionListener(treeAdapter);
		expand.addActionListener(treeAdapter);
		exportTheme.addActionListener(treeAdapter);
		collapse.addActionListener(treeAdapter);
		createChildTheme.addActionListener(treeAdapter);
		propertiesTheme.addActionListener(treeAdapter);
		srchAtribTheme.addActionListener(treeAdapter);
		srchSpclTheme.addActionListener(treeAdapter);

		return popMenu;
	}

	/**
	 * \brief Function that creates a specific menu to the view and implements
	 * the events for each item menu
	 * 
	 * @return The popup menu that contains the actions related to the views
	 */

	public JPopupMenu createPopupView() {

		LookAndFeelService.changeLookAndFeelForDefault();

		popMenu = new JPopupMenu();

		mediator.setSelectedNode((CustomNode) tree.getLastSelectedPathComponent());

		/* Creates menu's items */

		createTheme = new JMenuItem("Criar Tema");
		renameView = new JMenuItem("Renomear");
		deleteView = new JMenuItem("Excluir");
		importView = new JMenuItem("Importar");
		persist = new JMenuItem("Salvar");
		revert = new JMenuItem("Reverter");
		properties = new JMenuItem("Propriedades");
		/* Add the items to the menu */

		switch (mediator.getUserType()) {
		case ADMIN:
			popMenu.add(renameView);
			popMenu.add(deleteView);
			// popMenu.add(importView);
			popMenu.add(createTheme);
			// popMenu.add(properties);

			if (!mediator.getSelectedNode().isPersisted() && !mediator.getSelectedNode().getId().equals(0l)) {
				popMenu.add(revert);
			}

			popMenu.add(persist);

			break;
		case DEFAULT:
			popMenu.add(importView);
			break;
		default:
			break;
		}
		createTheme.addActionListener(treeAdapter);
		renameView.addActionListener(treeAdapter);
		deleteView.addActionListener(treeAdapter);
		importView.addActionListener(treeAdapter);
		persist.addActionListener(treeAdapter);
		revert.addActionListener(treeAdapter);
		properties.addActionListener(treeAdapter);

		return popMenu;
	}

	/**
	 * \brief Function that creates a default menu to the tree and implements
	 * the event the item menu
	 * 
	 * @return The popup menu that contains the actions accessed when the user
	 *         clicks on an empty space
	 */

	public JPopupMenu createPopupDefault() {

		LookAndFeelService.changeLookAndFeelForDefault();

		popMenu = new JPopupMenu();
		mediator.setSelectedNode(root);
		JMenuItem createView = new JMenuItem("Criar Vista");

		switch (mediator.getUserType()) {
		case ADMIN:

			popMenu.add(createView);
			break;
		default:
			break;
		}

		createView.addActionListener(treeAdapter);

		return popMenu;

	}

	/**
	 * \brief Function that creates a Subtitle so that the user can understand
	 * the different types of views. user type
	 * 
	 * @return The JPanel box containing the subtitles
	 */

	public JPanel createSubtitle() {
		JPanel pnlsubtitle = new JPanel();
		GridLayout layout = new GridLayout(3, 1);
		pnlsubtitle.setLayout(layout);
		pnlsubtitle.setBounds(0, 421, 150, 50);

		URL urlP = getClass().getClassLoader().getResource("br/org/funcate/glue/image/temaVisivel.gif");
		JLabel lblP = new JLabel("Selecionado", getImage(urlP), JLabel.LEFT);
		pnlsubtitle.add(lblP);
		URL urlA = getClass().getClassLoader().getResource("br/org/funcate/glue/image/temaEscala.gif");
		JLabel lblA = new JLabel("Não Visível", getImage(urlA), JLabel.LEFT);
		pnlsubtitle.add(lblA);
		URL url = getClass().getClassLoader().getResource("br/org/funcate/glue/image/temaNaoSelecionado.gif");
		JLabel lblV = new JLabel("Não selecionado", getImage(url), JLabel.LEFT);
		pnlsubtitle.add(lblV);
		return pnlsubtitle;
	}

	public static CustomNode getTool() {
		return tool;
	}

	public static void setTool(CustomNode tool) {
		TreeView.tool = tool;
	}

	public JScrollPane getSplScroll() {
		return splScroll;
	}

	public void setSplScroll(JScrollPane splScroll) {
		this.splScroll = splScroll;
	}

	public TreePath getPath() {
		return path;
	}

	public void setPath(TreePath path) {
		this.path = path;
	}

	public TreeAdapter getTreeAdapter() {
		return treeAdapter;
	}

	public void setTreeAdapter(TreeAdapter treeAdapter) {
		this.treeAdapter = treeAdapter;
	}

	public JMenuItem getCreateChildTheme() {
		return createChildTheme;
	}

	public void setCreateChildTheme(JMenuItem createTheme) {
		this.createChildTheme = createTheme;
	}

	public JMenuItem getRenameTheme() {
		return renameTheme;
	}

	public void setRenameTheme(JMenuItem renameTheme) {
		this.renameTheme = renameTheme;
	}

	public JMenuItem getDeleteTheme() {
		return deleteTheme;
	}

	public void setDeleteTheme(JMenuItem deleteTheme) {
		this.deleteTheme = deleteTheme;
	}

	public JMenuItem getExportTheme() {
		return exportTheme;
	}

	public void setExportTheme(JMenuItem exportTheme) {
		this.exportTheme = exportTheme;
	}

	public JMenuItem getPropertiesTheme() {
		return propertiesTheme;
	}

	public void setPropertiesTheme(JMenuItem propertiesTheme) {
		this.propertiesTheme = propertiesTheme;
	}

	public JMenuItem getSrchAtribTheme() {
		return srchAtribTheme;
	}

	public void setSrchAtribTheme(JMenuItem srchAtribTheme) {
		this.srchAtribTheme = srchAtribTheme;
	}

	public JMenuItem getSrchSpclTheme() {
		return srchSpclTheme;
	}

	public void setSrchSpclTheme(JMenuItem srchSpclTheme) {
		this.srchSpclTheme = srchSpclTheme;
	}

	public JMenuItem getExpand() {
		return expand;
	}

	public void setExpand(JMenuItem expand) {
		this.expand = expand;
	}

	public JMenuItem getCollapse() {
		return collapse;
	}

	public void setCollapse(JMenuItem collapse) {
		this.collapse = collapse;
	}

	public void setTree(JTree tree) {
		this.tree = tree;
	}

	public void setRoot(CustomNode root) {
		this.root = root;
	}

	public JMenuItem getCreateTheme() {
		return createTheme;
	}

	public void setCreateTheme(JMenuItem createTheme) {
		this.createTheme = createTheme;
	}

	public JMenuItem getRenameView() {
		return renameView;
	}

	public void setRenameView(JMenuItem renameView) {
		this.renameView = renameView;
	}

	public JMenuItem getDeleteView() {
		return deleteView;
	}

	public void setDeleteView(JMenuItem removeView) {
		this.deleteView = removeView;
	}

	public JMenuItem getImportView() {
		return importView;
	}

	public void setImportView(JMenuItem importView) {
		this.importView = importView;
	}

	public JMenuItem getPersist() {
		return persist;
	}

	public void setPersist(JMenuItem persist) {
		this.persist = persist;
	}

	public JMenuItem getRevert() {
		return revert;
	}

	public void setRevert(JMenuItem revert) {
		this.revert = revert;
	}

	public JMenuItem getProperties() {
		return properties;
	}

	public void setProperties(JMenuItem properties) {
		this.properties = properties;
	}

	public JMenuItem getCreateView() {
		return createView;
	}

	public void setCreateView(JMenuItem createView) {
		this.createView = createView;
	}

}
