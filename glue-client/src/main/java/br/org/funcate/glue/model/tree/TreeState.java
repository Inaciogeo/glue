package br.org.funcate.glue.model.tree;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.Layer;
import br.org.funcate.glue.model.LookAndFeelService;
import br.org.funcate.glue.model.Theme;
import br.org.funcate.glue.model.ThemeVisual;
import br.org.funcate.glue.model.UserType;
import br.org.funcate.glue.model.View;
import br.org.funcate.glue.model.ViewSet;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.model.thread.PlotterController;
import br.org.funcate.glue.model.thread.ViewUpdator;
import br.org.funcate.glue.service.utils.NetworkService;
import br.org.funcate.glue.view.LocalOptionPane;
import br.org.funcate.glue.view.NodeType;

public class TreeState {

	/** < Attribute type String */

	private List<Layer> layers;

	/** < Attribute type DefaultTreeModel > */

	private DefaultTreeModel defaultTreeModel;

	/** < Attribute type CustomNode> */

	private CustomNode currentTheme;

	/** < Attribute type CustomNode> */

	private CustomNode currentView;

	/** < Attribute type CustomNode > */

	private CustomNode root;

	/** < Attribute type ViewSet */

	private ViewSet viewSet;

	/** < Attribute type CustomNode > */

	private CustomNode theme;

	/** < Attribute type CustomNode > */

	private CustomNode view;

	/** < Attribute type CustomNode > */

	private CustomNode selectedNode;

	/** < Attribute type UserType */

	private UserType userType;

	/** < Attribute type CustomNode */

	private CustomNode nodeSource;

	/** < Attribute type CustomNode */

	private CustomNode nodeTarget;

	/** < Attribute type CustomNode */

	private CustomNode copiedNode;

	private ViewUpdator viewUpdator;

	public TreeState() {
	}

	/**
	 * \brief Function that returns the tree model
	 * 
	 * @return An object DefaultTreeModel
	 */

	public DefaultTreeModel getDefaultTreeModel() {
		return defaultTreeModel;
	}

	/**
	 * \brief Function that returns the layers
	 * 
	 * @return A vector containing the layers
	 */

	public List<Layer> getLayers() {
		return layers;
	}

	/**
	 * \brief Function that returns a list of the selected nodes
	 * 
	 * @return A list containing the selected nodes
	 */

	public List<CustomNode> getSelectedThemes() {
		List<CustomNode> selectedThemes = new ArrayList<CustomNode>();
		CustomNode node = getCurrentView();
		for (Enumeration<CustomNode> e = node.children(); e.hasMoreElements();) {

			CustomNode currentNode = e.nextElement();
			if ((currentNode.isSelected()) && (currentNode.getNodeType() != NodeType.VIEW)
					&& (currentNode.getNodeType() != NodeType.TOOLBAR) && (currentNode.getNodeType() != NodeType.ROOT)) {
				selectedThemes.add(currentNode);
			}
		}
		return selectedThemes;
	}

	/**
	 * \brief Function that returns the maximum scale of the current view
	 * 
	 * @return The maximum scale of the current view
	 */

	public Double getCurrentMaxScale() {

		Double maxScale = 15000000.0;

		for (Theme theme : currentView.getView().getThemes()) {
			if (theme.getScaleLimit() != null && theme.getScaleLimit().getMax() < maxScale) {
				maxScale = theme.getScaleLimit().getMax();
			}
		}
		return maxScale;
	}

	public Double getCurrentMinScale() {

		Double minScale = 10.0;

		for (Theme theme : currentView.getView().getThemes()) {
			if (theme.getScaleLimit() != null && theme.getScaleLimit().getMin() > minScale) {
				minScale = theme.getScaleLimit().getMin();
			}
		}
		return minScale;
	}

	/**
	 * \brief Function that modifies the model of the tree
	 * 
	 * @param model
	 *            The DefaultTreeModel that will be added to the tree
	 */

	public void setDefaultTreeModel(DefaultTreeModel model) {
		defaultTreeModel = model;
	}

	/**
	 * \brief Function that modifies which theme is selected
	 * 
	 * @param selectedTheme
	 *            The CustomNode that will be set as the current theme
	 */

	public void setCurrentTheme(CustomNode selectedTheme) {
		this.currentTheme = selectedTheme;
	}

	/**
	 * \brief Function that modifies the vector containing the layers
	 * 
	 * @param layers
	 *            The vector containing the new layers
	 */

	public void setLayers(List<Layer> layers) {
		this.layers = layers;
	}

	public CustomNode createRoot() throws GlueServerException {

		root = new CustomNode(1l, "Raiz", true, NodeType.ROOT);

		viewSet = AppSingleton.getInstance().getServices().getViewSet(false);
		layers = viewSet.getLayers();
		CustomNode view = null;
		CustomNode theme = null;
		for (int i = 0; i < viewSet.getViews().size(); i++) {

			view = new CustomNode(viewSet.getViews().get(i));
			view.setPersisted(true);
			root.add(view);

			if (viewSet.getViews().get(i).getThemes() != null) {
				for (int j = 0; j < viewSet.getViews().get(i).getThemes().size(); j++) {

					theme = new CustomNode(viewSet.getViews().get(i).getThemes().get(j));
					theme.setIndex(j);
					view.add(theme);
				}
			}
			root.add(view);
			NetworkService networkService = AppSingleton.getInstance().getNetworkService();
			networkService.readView(view);
		}

		return root;
	}

	/**
	 * \brief Function that removes a node from it's selectedNode. In the case
	 * that the node to be removed is a view, the user will have the choice to
	 * remove it from the database or just remove it from the applet.
	 */

	public void removeNode() {

		LookAndFeelService.changeLookAndFeelForNimbus();

		if (selectedNode == null)
			return;
		if (selectedNode.getNodeType() == NodeType.VIEW) {
			if (userType == UserType.ADMIN) {

				if (selectedNode.isPersisted()) {

					String msg = "Deseja remover a vista " + selectedNode.getName() + " do banco de dados?";
					int option = JOptionPane.showConfirmDialog(null, msg, "Remover vista", 0);
					if (option == 0) {
						ViewUpdator update = new ViewUpdator(false, false, true);
						update.start();
					} else if (option == 1) {
						return;
					} else {
						return;
					}
				}

				if (selectedNode.isApplied()) {
					String msg = "Deseja remover a vista " + selectedNode.getName();
					int option = JOptionPane.showConfirmDialog(null, msg, "Remover vista", 0);
					if (option == 0) {
						ViewUpdator update = new ViewUpdator(false, true, true);
						update.start();
					} else if (option == 1) {
						return;
					} else {
						return;
					}
				}

				if (!selectedNode.isPersisted() && !selectedNode.isApplied()) {
					String msg = "Deseja remover a vista " + selectedNode.getName();
					int option = JOptionPane.showConfirmDialog(null, msg, "Remover vista", 0);
					if (option == 0) {
						defaultTreeModel.removeNodeFromParent(selectedNode);
					} else if (option == 1) {
						return;
					} else {
						return;
					}
				}

			}
		} else if (selectedNode.getNodeType().isTheme()) {

			view = getNodeView(selectedNode);

			if (selectedNode == null)
				return;
			if (selectedNode.getNodeType() == NodeType.TOOLBAR)
				return;

			/* Remove the node */

			String msg = "Deseja remover o tema " + selectedNode.getName() + " ?";
			int option = JOptionPane.showConfirmDialog(null, msg, "Remover tema", 0);

			if (option == 0) {
				defaultTreeModel.removeNodeFromParent(selectedNode);
				view.rearrangeThemes();
				view.setPersisted(false);
				view.setApplied(false);
				view.setIsNodeChanged(true);
				setCurrentView(view);
				refreshIndex(view);
				PlotterController.getInstance().startPlotter();
			} else {
				return;
			}
		}
		LookAndFeelService.changeLookAndFeelForDefault();
	}

	/**
	 * @param currentView
	 *            the currentView to set
	 */

	public void setCurrentView(CustomNode currentView) {

		if (currentView != null && currentView.getNodeType() != NodeType.VIEW) {
			currentView = getNodeView(currentView);
		}
		this.currentView = currentView;
		/*
		 * Verifies if the user clicked on an unselected view, in that case
		 * set's the clicked view as selected and set's all other views as
		 * unselected ( only one view can be selected at a time)
		 */

		if (this.currentView.isSelected() == false && this.currentView.getNodeType() == NodeType.VIEW) {
			CustomNode visitNode;
			if (getRoot().getChildCount() >= 0) {
				for (Enumeration<CustomNode> e = getRoot().children(); e.hasMoreElements();) {
					visitNode = (CustomNode) e.nextElement();
					visitNode.setSelected(false);
				}
			}
			this.currentView.setSelected(true);
		}

		AppSingleton.getInstance().getMediator().repaint();
	}

	/**
	 * \brief This function is responsible for checking if the user is applying
	 * a view correctly and calling the thread that will update the view in the
	 * database
	 * 
	 */

	public void applyView() {

		selectedNode = AppSingleton.getInstance().getMediator().getLastSelectedPathComponent();
		if (selectedNode == null)
			return;
		setCurrentView(getNodeView(selectedNode));
		for (Enumeration<CustomNode> e = currentView.children(); e.hasMoreElements();) {

			CustomNode element = (CustomNode) e.nextElement();

			if ((element).getNodeType() != NodeType.TOOLBAR) {
				if (element.getId() == 0l) {
					LocalOptionPane.getInstance(
							"Por favor, salve a vista " + currentView.getName() + " para aplicar o tema " + element.getName(),
							"Glue - Aplicar");
					return;
				}
			}
		}

		setCurrentView(getNodeView(currentView));
		currentView.rearrangeThemes();
		ViewUpdator update = new ViewUpdator(false, true, false);
		update.start();

	}

	/**
	 * \brief This function is responsible for renaming the currently selected
	 * node
	 * 
	 */

	public void renameNode() {
		selectedNode = AppSingleton.getInstance().getMediator().getLastSelectedPathComponent();

		if (selectedNode == null)
			return;

		if (selectedNode.getNodeType() == NodeType.TOOLBAR)
			return;

		LookAndFeelService.changeLookAndFeelForNimbus();

		String name = JOptionPane.showInputDialog("Digite o nome desejado", selectedNode.getName());

		LookAndFeelService.changeLookAndFeelForDefault();

		if (name == null)
			return;

		name = name.trim();
		if (name.equals("") || name.length() > 20){
			LocalOptionPane.getInstance("Nome invalido!", "Glue - Renomear");
			return;
		}
		if (name.equals(selectedNode.getName()))
			return;

		CustomNode compare = new CustomNode(0l, name, true, selectedNode.getNodeType());

		if (checkAllNodes(root, compare)) {
			LocalOptionPane.getInstance("Este nome já existe", "Glue - Renomear");

		}

		else {

			selectedNode.setPreviousName(selectedNode.getName());
			selectedNode.setName(name);
			getNodeView(selectedNode).setPersisted(false);
			getNodeView(selectedNode).setIsNodeChanged(true);
			setCurrentView(getNodeView(selectedNode));

		}

		defaultTreeModel.reload(selectedNode);
	}

	/**
	 * \brief This function is called when the user releases the button, and
	 * moves the selected CustomNode to another CustomNode
	 * 
	 * @param targetView
	 *            The view that is receiving the node
	 * @param sourceView
	 *            The view that is sending the node
	 */

	public void moveNode(CustomNode targetView, CustomNode sourceView) {
		this.nodeTarget = targetView;
		CustomNode verifyNode;
		if (nodeTarget.getNodeType() == NodeType.TOOLBAR)
			return;
		if (nodeSource.getNodeType() == NodeType.VIEW)
			return;
		if (!nodeTarget.isAllowsChildren()) {
			LocalOptionPane.getInstance("Este tema não pode ter subtemas ", "Glue - Mover");
			return;
		}
		if (nodeTarget.isNodeAncestor(nodeSource)) {
			LocalOptionPane.getInstance("Não é permitido ", "Glue - Mover");
			return;
		}

		/* Verify if the source and the target node are in the same view */

		if (sourceView.equals(targetView)) {
			int index = ((CustomNode) nodeTarget.getParent()).getIndex(targetView);
			rearrange(index, targetView, nodeSource);

		} else {

			for (Enumeration<CustomNode> e = nodeSource.children(); e.hasMoreElements();) {
				verifyNode = (CustomNode) e.nextElement();
				if (checkAllNodes(targetView, verifyNode)) {
					LocalOptionPane.getInstance("O subtema (" + verifyNode + ") já existe na vista (" + targetView
							+ "). \n Renomeie o tema antes de movê-lo!", "Glue - Mover");
					return;
				}
			}

			if (checkAllNodes(targetView, nodeSource)) {

				LookAndFeelService.changeLookAndFeelForNimbus();

				String name = JOptionPane.showInputDialog("Altere o nome : ", "Cópia de " + nodeSource.getName());

				LookAndFeelService.changeLookAndFeelForDefault();

				if (name == null || name.trim().equals(""))
					return;
				nodeSource.setName(name);

				if (checkAllNodes(targetView, nodeSource)) {
					LocalOptionPane.getInstance("Este nome já existe", "Glue - Mover");
					return;
				}
			}
		}

		if (nodeSource.getId() < 0l) {
			LocalOptionPane.getInstance("Por favor, salve a vista " + getNodeView(nodeSource).getName() + " para mover o tema "
					+ nodeSource.getName(), "Glue - Mover");
			return;
		}
		defaultTreeModel.removeNodeFromParent(nodeSource);
		sourceView.setApplied(false);
		sourceView.setPersisted(false);
		sourceView.setIsNodeChanged(true);
		sourceView.rearrangeThemes();
		nodeTarget.add(nodeSource);
		AppSingleton.getInstance().getMediator().setSelectionPath(new TreePath(nodeTarget.getPath()));
		setCurrentView(getNodeView(nodeTarget));
		refreshIndex(targetView);
		nodeTarget.setApplied(false);
		nodeTarget.setPersisted(false);
		nodeTarget.setIsNodeChanged(true);
		targetView.setIsNodeChanged(true);
		targetView.rearrangeThemes();
		selectedNode = nodeTarget;
		PlotterController.getInstance().startPlotter();
		defaultTreeModel.reload(nodeTarget);
	}

	/**
	 * \brief This function receives a CustomNode and check all of it's
	 * children, setting their selected attribute to false.
	 * 
	 * @param node
	 *            The node that will be unselected (along with it's children)
	 */

	public void deselectAllNodes(CustomNode node) {
		if (node.getChildCount() >= 0) {
			for (Enumeration<CustomNode> e = node.children(); e.hasMoreElements();) {
				node = (CustomNode) e.nextElement();
				node.setSelected(false);
				deselectAllNodes(node);
			}
		}
	}

	/**
	 * \brief This function receives a CustomNode and check all of it's
	 * children, setting their selected attribute to true.
	 * 
	 * @param node
	 *            The node that will be selected (along with it's children)
	 */

	public void selectAllNodes(CustomNode node) {
		if (node.getChildCount() >= 0) {
			for (Enumeration<CustomNode> e = node.children(); e.hasMoreElements();) {
				node = (CustomNode) e.nextElement();
				node.setSelected(true);
				selectAllNodes(node);
			}
		}
	}

	/**
	 * \brief This function receives the current node and the new one and check
	 * if there's another node with the same name;
	 * 
	 * @param node
	 *            The node that will be checked
	 * @param selectedNode
	 *            The node that will be compared with the other node
	 * @return A boolean that indicates if a node with the same name of that
	 *         node already exists in the selectedNode
	 */

	public boolean checkAllNodes(CustomNode parent, CustomNode node) {

		if (parent.getChildCount() >= 0) {

			for (Enumeration<CustomNode> e = parent.children(); e.hasMoreElements();) {
				parent = (CustomNode) e.nextElement();

				if (parent.getNodeType() == NodeType.TOOLBAR)
					if (e.hasMoreElements()) {
						parent = (CustomNode) e.nextElement();
					} else {
						break;
					}
				if (node.getName().equals(parent.getName())) {
					return true;
				}

				if (checkAllNodes(parent, node))
					return true;
			}
		}
		return false;
	}

	/**
	 * \brief This function is called when the user releases the button while
	 * holding the control key,it rearranges the nodes by placing the selected
	 * node above the target node
	 * 
	 * @param index
	 *            The index that indicates the new position of the node in the
	 *            row
	 * @param target
	 *            The node that contains the node that is being rearranged
	 * @param source
	 *            The node that is being rearranged
	 */

	public void rearrange(int index, CustomNode target, CustomNode source) {

		if (source.getNodeType() == NodeType.VIEW && target.getNodeType() == NodeType.ROOT) {
			defaultTreeModel.removeNodeFromParent(source);

			defaultTreeModel.insertNodeInto(source, target, index);

			defaultTreeModel.reload(target);
			return;
		}
		if (target.getNodeType() == NodeType.TOOLBAR)
			return;
		if (target.getNodeType() == NodeType.WMS_THEME || target.getNodeType() == NodeType.GOOGLE) {
			return;
		}

		CustomNode targetView = getNodeView(target);

		// Putting the theme into the last position of it's selectedNode

		if (target.getChildCount() - 1 == index) {
			defaultTreeModel.insertNodeInto(source, target, target.getChildCount() - 1);

		} else {
			defaultTreeModel.insertNodeInto(source, target, index);
		}

		// Updating current view with the new themes order
		refreshIndex(targetView);
		targetView.setApplied(false);
		targetView.setPersisted(false);
		targetView.setIsNodeChanged(true);
		targetView.rearrangeThemes();
		setCurrentView(targetView);
		defaultTreeModel.reload(target);
		AppSingleton.getInstance().getMediator().setSelectionPath(new TreePath(source.getPath()));
		PlotterController.getInstance().startPlotter();
	}

	/**
	 * \brief This function receives the current scale of visualization to check
	 * which nodes are visible
	 * 
	 * @param scale
	 *            The value that indicates the current scale of visualization
	 */

	public Double updateVisibility(double scale) {
		checkNodesVisibility(currentView, scale);
		currentView.getView().buildViewToPlot();
		return getCurrentMaxScale();
	}

	/**
	 * \brief This function receives the current node and the scale of
	 * visualization and hen determines whether the node is visible in that
	 * scale or not
	 * 
	 * @param node
	 *            The node that will be checked
	 * @param scale
	 *            The value that indicates the current scale of visualization
	 */

	public void checkNodesVisibility(CustomNode node, Double scale) {

		if (node.getChildCount() >= 0) {
			for (Enumeration<CustomNode> e = node.children(); e.hasMoreElements();) {
				node = (CustomNode) e.nextElement();

				if (node.getNodeType() == NodeType.TOOLBAR)
					if (e.hasMoreElements()) {
						node = (CustomNode) e.nextElement();
					} else {
						break;
					}

				if (node.getNodeType() != NodeType.VIEW) {
					if (node.getScaleLimit() != null && scale > node.getScaleLimit().getMin() && scale < node.getScaleLimit().getMax()) {
						node.setScaleVisibility(true);
					} else {
						node.setScaleVisibility(false);
					}
				}
				checkNodesVisibility(node, scale);
			}
		}
		AppSingleton.getInstance().getMediator().repaint();
	}

	/**
	 * \brief This function is called when the user presses the control key +
	 * the c key on the keyboard, it copies the selected CustomNode
	 */

	public void copyNode() {
		TreePath path = AppSingleton.getInstance().getMediator().getSelectionPath();
		if (path == null)
			return;
		nodeSource = (CustomNode) path.getLastPathComponent();
		if (!nodeSource.getNodeType().isTheme())
			nodeSource = null;

	}

	/**
	 * \brief This function is called when the user presses the control key +
	 * the x key on the keyboard, it cuts the selected CustomNode (remove it
	 * from it's selectedNode to be copied)
	 */

	public void cutNode() {
		TreePath path = AppSingleton.getInstance().getMediator().getSelectionPath();
		if (path == null)
			return;
		nodeSource = (CustomNode) path.getLastPathComponent();
		if (!nodeSource.getNodeType().isTheme()) {
			nodeSource = null;
			return;
		}
		view = getNodeView(nodeSource);
		defaultTreeModel.removeNodeFromParent(nodeSource);
		view.rearrangeThemes();
		view.setPersisted(false);
		view.setApplied(false);
		view.setIsNodeChanged(true);
		setCurrentView(view);
		refreshIndex(view);

	}

	/**
	 * \brief This function is called when the user presses the control key +
	 * the x key on the keyboard, it pastes the selected CustomNode (adds it to
	 * the selected selectedNode)
	 */

	public void pasteNode() {
		CustomNode targetView;
		CustomNode sourceView;
		String name;
		CustomNode verifyNode;
		TreePath path = AppSingleton.getInstance().getMediator().getSelectionPath();
		nodeTarget = (CustomNode) path.getLastPathComponent();

		if (nodeTarget == nodeSource)
			return;
		if (nodeTarget == null || nodeSource == null)
			return;
		targetView = getNodeView(nodeTarget);
		if (nodeSource.getParent() == null) {

			copiedNode = (CustomNode) nodeSource.clone();

			List<ThemeVisual> tvs = new ArrayList<ThemeVisual>(nodeSource.getThemeVisuals().size());
			for (int i = 0; i < nodeSource.getThemeVisuals().size(); i++) {
				tvs.add(i, nodeSource.getThemeVisuals().get(i).clone());
			}

			Theme copiedTheme = new Theme();
			copiedTheme = nodeSource.getTheme().clone();
			copiedNode.setTheme(copiedTheme);

			copiedNode.setThemeVisuals(tvs);

			/* Verify if the source and the target node are in the same view */
			if (nodeTarget.getNodeType() == NodeType.TOOLBAR)
				return;

			if (!nodeTarget.isAllowsChildren()) {
				LocalOptionPane.getInstance("Este tema não pode ter subtemas ", "Glue - Copiar");
				return;
			}

			for (Enumeration<CustomNode> e = nodeSource.children(); e.hasMoreElements();) {
				verifyNode = (CustomNode) e.nextElement();

				if (checkAllNodes(targetView, verifyNode)) {
					LocalOptionPane.getInstance("O subtema (" + verifyNode + ") já existe na vista (" + targetView
							+ "). \n Renomeie o tema antes de movê-lo!", "Glue - Copiar");
					return;
				}
			}

			if (checkAllNodes(targetView, copiedNode)) {
				name = null;
				name = JOptionPane.showInputDialog("Altere o nome : ", "Cópia de " + copiedNode.getName());
				if (name == null || name.trim().equals(""))
					return;
				copiedNode.setName(name);

				if (checkAllNodes(targetView, copiedNode)) {
					LocalOptionPane.getInstance("Este nome já existe", "Glue - Copiar");
					return;
				}

			}

			if (nodeSource.getId() < 0l) {
				LocalOptionPane.getInstance("Por favor, salve a vista " + getNodeView(nodeSource).getName() + " para copiar o tema "
						+ nodeSource.getName(), "Glue - Copiar e Colar");
			} else {

				if (nodeSource.getChildCount() == 1) {
					copiedNode.add(createToolBar());
					nodeTarget.add(copiedNode);

				} else {
					nodeTarget.add(copyAllNodes(nodeSource, copiedNode));

				}
				refreshIndex(targetView);
				setCurrentView(getNodeView(nodeTarget));
				currentView.rearrangeThemes();
				// currentView.getView().buildViewToPlot();
				currentView.setIsNodeChanged(true);
				currentView.setApplied(false);
				currentView.setPersisted(false);

				defaultTreeModel.reload(nodeTarget);
			}
		} else {
			sourceView = getNodeView(nodeSource);

			copiedNode = (CustomNode) nodeSource.clone();
			List<ThemeVisual> tvs = new ArrayList<ThemeVisual>(nodeSource.getThemeVisuals().size());
			for (int i = 0; i < nodeSource.getThemeVisuals().size(); i++) {
				tvs.add(i, nodeSource.getThemeVisuals().get(i).clone());
			}

			Theme copiedTheme = new Theme();
			copiedTheme = nodeSource.getTheme().clone();
			copiedNode.setTheme(copiedTheme);
			copiedNode.setThemeVisuals(tvs);

			/* Verify if the source and the target node are in the same view */
			if (nodeTarget.getNodeType() == NodeType.TOOLBAR)
				return;

			if (!nodeTarget.isAllowsChildren()) {
				LocalOptionPane.getInstance("Este tema nÃ£o pode ter subtemas ", "Glue - Copiar");
				return;
			}
			if (sourceView.equals(targetView)) {

				if (checkAllNodes(targetView, copiedNode)) {
					name = JOptionPane.showInputDialog("Altere o nome : ", "Cópia de " + copiedNode.getName());
					if (name == null || name.trim().equals(""))
						return;
					copiedNode.setName(name);

					if (checkAllNodes(targetView, copiedNode)) {
						LocalOptionPane.getInstance("Este nome já existe", "Glue - Copiar");
						return;
					}
				}
			} else {
				for (Enumeration<CustomNode> e = nodeSource.children(); e.hasMoreElements();) {
					verifyNode = (CustomNode) e.nextElement();

					if (checkAllNodes(targetView, verifyNode)) {
						LocalOptionPane.getInstance("O subtema (" + verifyNode + ") já existe na vista (" + targetView
								+ "). \n Renomeie o tema antes de movê-lo!", "Glue - Copiar");
						return;
					}
				}

				if (checkAllNodes(targetView, copiedNode)) {

					name = JOptionPane.showInputDialog("Altere o nome : ", copiedNode.getName() + "(" + copiedNode.getChildCount() + ")");
					if (name == null || name.trim().equals(""))
						return;
					copiedNode.setName(name);

					if (checkAllNodes(targetView, copiedNode)) {
						LocalOptionPane.getInstance("Este nome já existe", "Glue - Copiar");
						return;
					}
				}
			}

			if (nodeSource.getId() < 0l) {
				LocalOptionPane.getInstance("Você precisa salvar esta vista para copiar este tema", "Glue - Copiar e Colar");
			} else {

				if (nodeSource.getChildCount() == 1) {
					copiedNode.add(createToolBar());
					nodeTarget.add(copiedNode);
					PlotterController.getInstance().startPlotter();

				} else {
					nodeTarget.add(copyAllNodes(nodeSource, copiedNode));
					PlotterController.getInstance().startPlotter();
				}
				refreshIndex(targetView);
				setCurrentView(getNodeView(nodeTarget));
				currentView.rearrangeThemes();
				// currentView.getView().buildViewToPlot();
				currentView.setIsNodeChanged(true);
				currentView.setApplied(false);
				currentView.setPersisted(false);
				defaultTreeModel.reload(nodeTarget);
			}
		}
		PlotterController.getInstance().startPlotter();
	}

	/**
	 * \brief This function receives a CustomNode and copy all of it's children
	 * to a new node,
	 * 
	 * @param node
	 *            The node that will be cloned
	 * @param newNode
	 *            The new node that will be added to the tree
	 * @return newNode The node that was copied
	 */

	public CustomNode copyAllNodes(CustomNode node, CustomNode newNode) {
		if (node.getChildCount() >= 0) {
			for (Enumeration<CustomNode> e = node.children(); e.hasMoreElements();) {
				node = (CustomNode) e.nextElement();
				CustomNode otherNode = (CustomNode) node.clone();
				newNode.add(otherNode);
				copyAllNodes(node, otherNode);
			}
			getNodeView(newNode).setApplied(false);
			getNodeView(newNode).setPersisted(false);
		}
		return newNode;
	}

	/**
	 * \brief Function that receives a CustomNode and updates the index of all
	 * it's child nodes based on their position on the tree
	 * 
	 * @param view
	 *            the view that will have it's themes updated
	 */

	public void refreshIndex(CustomNode view) {

		for (int i = 0; i < view.getChildCount(); i++) {
			CustomNode node = (CustomNode) view.getChildAt(i);
			node.setIndex(i);
		}
	}

	/**
	 * \brief Function that receives a CustomNode, verifies if it already exists
	 * if it does, asks the user the change the node name,if it doesn't it will
	 * be added to the JTree with a toolbar.
	 * 
	 * @param node
	 *            The CustomNode that is being added into another CustomNode
	 * @param selectedNode
	 *            The CustomNode that is receiving the node
	 * @return A boolean that indicates if the node was added successfully
	 */

	public boolean addNode(CustomNode node, CustomNode parent) {

		if ((node.getNodeType() == NodeType.VIEW) || (node.getNodeType() == NodeType.ROOT))
			return false;

		if (checkAllNodes(parent, node)) {
			String name = JOptionPane.showInputDialog("Altere o nome : ", "Cópia de " +

			node.getName());
			if (name == null || name.trim().equals(""))
				return false;
			node.setName(name);

			if (checkAllNodes(parent, node)) {
				LocalOptionPane.getInstance("Este nome já existe", "Glue - Adicionar");
				return false;
			}
		}

		if (node.isLeaf()
				&& (node.getNodeType() != NodeType.VIEW && node.getNodeType() != NodeType.ROOT && node.getNodeType() != NodeType.TOOLBAR && node.getNodeType() != NodeType.NETWORK_THEME)) {
			node.add(createToolBar());
		}

		int index = parent.getChildCount();

		for (int i = parent.getChildCount() - 1; i >= 0; i--) {
			CustomNode cNode = (CustomNode) parent.getChildAt(i);
			if (cNode.getNodeType() == NodeType.GOOGLE) {
				index = i;
			}
			if (cNode.getNodeType() == NodeType.WMS_THEME) {
				index = i;
			}
		}

		defaultTreeModel.insertNodeInto(node, parent, index);

		if (((CustomNode) node.getParent()).getNodeType() == NodeType.VIEW) {
			node.setParentID(0l);
		} else {
			node.setParentID(((CustomNode) node.getParent()).getId());
		}

		AppSingleton.getInstance().getMediator().disableAllTools();
		setCurrentView(getNodeView(node));
		currentView.setApplied(false);
		currentView.setPersisted(false);
		currentView.setIsNodeChanged(true);
		currentView.rearrangeThemes();
		TreeService.updateTreeVisibility();
		defaultTreeModel.reload(parent);
		return true;
	}

	/**
	 * \brief This function receives a CustomNode and creates a View based on
	 * the node
	 * 
	 * @param treeView
	 *            The CustomNode that will be checked
	 * @return A vector containing the themes
	 * @throws GlueServerException 
	 */

	public synchronized Boolean updateSelectedView(Boolean isReload, Boolean isMemView) throws GlueServerException {

		Boolean result = !currentView.getIsNodeChanged();
		View newView = currentView.getView();
		View updatedView;
		
		// Updates network themes configuration		
		NetworkService networkService  = AppSingleton.getInstance().getNetworkService();
		networkService.updateView(currentView);
		
		
		Mediator mediator = AppSingleton.getInstance().getMediator();
		if (currentView != null && currentView.isSelected()
				&& (currentView.getIsNodeChanged() || currentView.getId() == 0l || currentView.isApplied())) {

			if (!currentView.getName().equals(currentView.getPreviousName()) && currentView.getId() > 0l && isMemView) {
				LocalOptionPane.getInstance("É preciso salvar esta vista para renomeá-la", "Glue - Atualizar vista");
			}
			PlotterController.getInstance().pausePlotter();
			updatedView = AppSingleton.getInstance().getServices().updateView(newView, isReload, isMemView);

			if (updatedView != null) {

				int index = root.getIndex(currentView);
				defaultTreeModel.removeNodeFromParent(currentView);
				setCurrentView(getNodeFromView(updatedView, isMemView));
				currentView.setView(updatedView);
				defaultTreeModel.insertNodeInto(currentView, root, index);
				currentView.setSelected(true);
				//currentView.setApplied(true);
				checkNodesVisibility(root, AppSingleton.getInstance().getCanvasState().getScale());
				currentView.rearrangeThemes();
				TreePath path = new TreePath(currentView.getPath());
				mediator.setSelectionPath(path);
				mediator.expandPath(path);
				mediator.scrollPathToVisible(path);

				result = true;
			}

			else {
				TreePath path = new TreePath(view.getPath());
				mediator.setSelectionPath(path);
				mediator.expandPath(path);
				mediator.scrollPathToVisible(path);
				result = false;
			}
		}

		if (!currentView.getIsNodeChanged()) {
			return result;
		}

		else if (result) {

			// Reloading a view from database
			if (isReload && !isMemView) {
				currentView.setApplied(false);
				currentView.setPersisted(true);
			}
			if (isReload && isMemView) {
				currentView.setApplied(true);
				currentView.setPersisted(false);
			}
			// Updating a view in TerraManager memory
			if (isMemView) {
				currentView.setApplied(true);
				currentView.setPersisted(false);
			}
			// Updating a view in database
			if (!isMemView) {
				currentView.setApplied(false);
				currentView.setPersisted(true);
			}
		} else {
			LocalOptionPane.getInstance("Não foi possível atualizar a vista", "Glue - Atualizar");
		}

		return result;
	}

	public ViewSet getViewSet() {
		return viewSet;
	}

	public void setViewSet(ViewSet viewSet) {
		this.viewSet = viewSet;
	}

	public CustomNode getCurrentTheme() {
		return currentTheme;
	}

	public CustomNode getCurrentView() {
		return currentView;
	}

	/**
	 * \brief Function that returns the view of a node
	 * 
	 * @return The view of the given node
	 */

	public CustomNode getNodeView(CustomNode node) {

		if (node.getNodeType() == NodeType.VIEW)
			return node;
		else {

			while (node.getNodeType() != NodeType.VIEW) {

				node = (CustomNode) node.getParent();
				if (node.getNodeType() == NodeType.VIEW) {
					return node;
				}
			}
			return node;
		}
	}

	public void createViewUpdator(boolean reload, boolean memory, boolean remove) {
		viewUpdator = new ViewUpdator(reload, memory, remove);
		viewUpdator.start();
	}

	public UserType getUserType() {
		return userType;
	}

	public CustomNode getTheme() {
		return theme;
	}

	public void setTheme(CustomNode theme) {
		this.theme = theme;
	}

	public CustomNode getView() {
		return view;
	}

	public void setView(CustomNode view) {
		this.view = view;
	}

	public ViewUpdator getViewUpdator() {
		return viewUpdator;
	}

	public void setViewUpdator(ViewUpdator viewUpdator) {
		this.viewUpdator = viewUpdator;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	private CustomNode getNodeFromView(View dView, Boolean isMemView) {
		CustomNode treeView;

		treeView = new CustomNode(dView);

		if (isMemView) {
			treeView.setApplied(true);
			treeView.setPersisted(false);
		} else {
			treeView.setApplied(false);
			treeView.setPersisted(true);
		}

		for (Theme theme : dView.getThemes()) {
			CustomNode newTheme = new CustomNode(theme);
			addNode(newTheme, treeView);
		}

		return treeView;
	}

	public CustomNode getRoot() {
		return root;
	}

	public void setRoot(CustomNode root) {
		this.root = root;
	}

	public CustomNode getSelectedNode() {
		return selectedNode;
	}

	public CustomNode getNodeSource() {
		return nodeSource;
	}

	public void setNodeSource(CustomNode nodeSource) {
		this.nodeSource = nodeSource;
	}

	public CustomNode getNodeTarget() {
		return nodeTarget;
	}

	public void setNodeTarget(CustomNode nodeTarget) {
		this.nodeTarget = nodeTarget;
	}

	public CustomNode getCopiedNode() {
		return copiedNode;
	}

	public void setCopiedNode(CustomNode copiedNode) {
		this.copiedNode = copiedNode;
	}

	public void setSelectedNode(CustomNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	/**
	 * \brief Function that creates and returns a toolbar
	 * 
	 * @return The CustomNode that contains the toolbar
	 */

	public CustomNode createToolBar() {
		return new CustomNode(0l, "ToolBar", false, NodeType.TOOLBAR);

	}

}
