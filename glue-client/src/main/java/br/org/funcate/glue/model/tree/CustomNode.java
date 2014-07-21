package br.org.funcate.glue.model.tree;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.Layer;
import br.org.funcate.glue.model.Projection;
import br.org.funcate.glue.model.Representation;
import br.org.funcate.glue.model.ScaleLimit;
import br.org.funcate.glue.model.Theme;
import br.org.funcate.glue.model.ThemeType;
import br.org.funcate.glue.model.ThemeVisual;
import br.org.funcate.glue.model.View;
import br.org.funcate.glue.model.thread.PlotterController;
import br.org.funcate.glue.view.NodeType;
import br.org.funcate.glue.view.TreeToolbar;

/** \file CustomNode.java
 * This file creates a CustomNode, the customized node
 * of our tree and it's functions.
 */

/**
 * \brief This class defines the attributes and the functions of a CustomNode.
 * 
 * @author André Gomes de Oliveira
 * @author Paulo Renato Morais Oliveira
 */

@SuppressWarnings("serial")
public class CustomNode extends DefaultMutableTreeNode {

	/** Attribute type View. */

	private View view;

	/** Attribute type Theme. */

	private Theme theme;

	/** Attribute type String. */

	private String previousName;

	/** Attribute type boolean. */

	private boolean visible;

	/** Attribute type allows boolean. */

	private boolean allowsChildren;

	/** Attribute type boolean. */

	private boolean hiddenExpanded;

	/** Attribute type boolean. */

	private boolean persisted;

	/** Attribute type boolean. */

	private boolean applied;

	/** Attribute type Icon. */

	private Icon icon;

	/** Attribute type NodeType. */

	private NodeType nodeType;

	/** Attribute type TreeToolbar. */

	private TreeToolbar toolbar;

	/** Attribute type String. */

	private String url;

	/** Attribute type String */

	private String toolTipText;

	/** Attribute type Boolean */

	private Boolean isNodeChanged = false;

	/** Attribute type Boolean */

	private Boolean isScaleLimitChanged = false;

	/** Attribute type int */

	private int transparency;

	/**
	 * @name Constructors
	 * 
	 *       The constructors of this class
	 */

	// @{

	/**
	 * \brief This constructor defines the id, name, selection and the type of
	 * the new CustomNode
	 * 
	 * @param id
	 *            Identification of the node
	 * @param name
	 *            The name of the node that is displayed at the Applet
	 * @param selected
	 *            Indicates if the node is selected
	 * @param nodeType
	 *            Indicates the type of the node
	 */

	public CustomNode(Long id, String name, boolean selected, NodeType nodeType) {
		this.nodeType = nodeType;
		if (nodeType.isTheme()) {
			theme = new Theme();
			theme.setName(name);
			theme.setId(id);			
			theme.setType(getType());
			theme.setVisibility(selected);
		} else {
			view = new View();
			view.setName(name);
			view.setId(id);
		}

		this.previousName = name;
		this.visible = selected;

		if (nodeType == NodeType.TOOLBAR) {

			this.icon = null;
			toolbar = new TreeToolbar();
		}

		if ((nodeType != NodeType.NETWORK_THEME) && (nodeType != NodeType.TREE_THEME) && (nodeType != NodeType.VIEW))
			setAllowsChildren(false);

		else {
			setAllowsChildren(true);
		}
		this.nodeType = nodeType;
		if (nodeType.isTheme())
			theme.setScaleVisibility(true);

		if (this.getParent() != null) {
			if (((CustomNode) this.getParent()).getNodeType() == NodeType.VIEW) {
				setParentID(0l);
			} else {
				if (getParentID() == null) {
					setParentID(0l);
				} else {
					setParentID(getParentID());
				}
			}
		}

	}

	/**
	 * \brief This constructor defines the id, name,selection, visibility, type,
	 * box, scale, url and transparency of the new CustomNode
	 * 
	 * @param id
	 *            Identification of the node
	 * @param name
	 *            The name of the node that is displayed at the Applet
	 * @param selected
	 *            Indicates if the node is selected
	 * @param visible
	 *            Indicates if the node is visible
	 * @param nodeType
	 *            Indicates the type of the node
	 * @param x1
	 *            The x1 point of the node's box
	 * @param x2
	 *            The x2 point of the node's box
	 * @param y1
	 *            The y1 point of the node's box
	 * @param y2
	 *            The y2 point of the node's box
	 * @param scaleMin
	 *            Indicates the minimum scale that the node can be visible at
	 * @param scaleMax
	 *            Indicates the minimum scale that the node can be visible at
	 * @param url
	 *            The url of the WMS or google
	 * @param transparency
	 *            The transparency of the node
	 */

	public CustomNode(Long id, String name, boolean selected, boolean visible, NodeType nodeType, ScaleLimit scaleLimit, String url,
			int transparency) {

		this.nodeType = nodeType;
		theme = new Theme();
		setName(name);
		previousName = name;
		visible = selected;
		theme.setScaleVisibility(visible);
		theme.setVisibility(visible);
		setScaleLimit(scaleLimit);
		setId(id);

		this.url = url;
		if (nodeType != NodeType.VIEW && nodeType != NodeType.TOOLBAR)
			setToolTipText(nodeType.toString());
		if (nodeType == NodeType.TOOLBAR)
			this.icon = null;

		if ((nodeType != NodeType.NETWORK_THEME) && (nodeType != NodeType.TREE_THEME) && (nodeType != NodeType.VIEW))
			setAllowsChildren(false);

		else {
			setAllowsChildren(true);
		}

	}

	/**
	 * \brief This constructor defines the id, name, selection, visibility, type
	 * and layer of the new CustomNode
	 * 
	 * @param id
	 *            Identification of the node
	 * @param name
	 *            The name of the node that is displayed at the Applet
	 * @param selected
	 *            Indicates if the node is selected
	 * @param visible
	 *            Indicates if the node is visible
	 * @param type
	 *            Indicates the type of the node
	 * @param layer
	 *            Indicates the name of the layer
	 */

	public CustomNode(Long id, String name, boolean selected, boolean visible, NodeType nodeType, String layer) {

		theme = new Theme();
		List<Layer> layers = AppSingleton.getInstance().getTreeState().getLayers();
		List<ThemeVisual> visuals = null;

		theme.setName(name);
		this.previousName = name;
		this.visible = selected;
		theme.setScaleVisibility(visible);

		for (int i = 0; i < layers.size(); i++) {
			if (layer.equals(layers.get(i).getName())) {
				setLayer(layers.get(i));
				break;
			}
		}
		setRepresentation(getLayer().getRepresentations());

		visuals = new ArrayList<ThemeVisual>(getRepresentation().size());

		for (int i = 0; i < getRepresentation().size(); i++) {
			visuals.add(i, new ThemeVisual(Integer.parseInt(getRepresentation().get(i).getId().toString())));
		}

		setThemeVisuals(visuals);
		setScaleLimit(new ScaleLimit());

		this.nodeType = nodeType;

		if (nodeType == NodeType.TOOLBAR)
			this.icon = null;

		if ((nodeType != NodeType.NETWORK_THEME) && (nodeType != NodeType.TREE_THEME) && (nodeType != NodeType.VIEW))
			setAllowsChildren(false);
		else {
			setAllowsChildren(true);
		}
		if (nodeType != NodeType.VIEW && nodeType != NodeType.TOOLBAR)
			setToolTipText(nodeType.toString());

		setId(id);
		if (this.getParent() != null) {
			if (((CustomNode) this.getParent()).getNodeType() == NodeType.VIEW) {
				setParentID(0l);
			} else {
				if (getParentID() == null) {
					setParentID(0l);
				} else {
					setParentID(getParentID());
				}
			}
		}

		if (nodeType.isTheme()) {
			theme.setType(getType());
		}

		theme.setVisibility(selected);
	}

	/**
	 * \brief This constructor builds a CustomNode based on the parameters of a
	 * given View
	 * 
	 * @param View
	 *            The view that will be copied
	 */

	public CustomNode(View view) {

		this.previousName = view.getName();
		this.visible = false;
		this.nodeType = NodeType.VIEW;

		@SuppressWarnings("unused")
		Box box;
		try {
			view.getBox().getX1();
			box = view.getBox();
		} catch (Exception e) {
			view.setBox(new Box(0.0, 0.0, 0.0, 0.0));
		}

		this.view = view;
		this.allowsChildren = true;
	}

	/**
	 * \brief This constructor builds a CustomNode based on the parameters of a
	 * given Theme
	 * 
	 * @param theme
	 *            The theme that will be copied
	 */

	public CustomNode(Theme theme) {
		this.add(createToolBar());
		this.previousName = theme.getName();
		this.visible = theme.isVisibility();
		this.theme = theme;
		this.theme.setScaleVisibility(true);

		switch (this.theme.getType()) {
		case 0:
			this.nodeType = NodeType.DEFAULT;
			break;
		case 1:
			this.nodeType = NodeType.TREE_THEME;
			break;
		case 2:
			this.nodeType = NodeType.EXTERN_THEME;
			break;
		case 3:
			this.nodeType = NodeType.FILE_THEME;
			break;
		case 4:
			this.nodeType = NodeType.WMS_THEME;
			break;
		case 5:
			this.nodeType = NodeType.WFS_THEME;
			break;
		}

		// Setting default theme parameters

		if (!(this.getNodeType() == NodeType.TREE_THEME) && !(this.getNodeType() == NodeType.NETWORK_THEME)) {

		}

		// If this node has child themes, they're added.

		else {
			for (int i = 0; i < this.theme.getChildThemes().size(); i++) {
				this.add((CustomNode) new CustomNode(this.theme.getChildThemes().get(i)));
			}
		}

		// Setting tool tip for theme

		if (this.nodeType != NodeType.VIEW && this.nodeType != NodeType.TOOLBAR)
			setToolTipText(nodeType.toString());

		// Checking if the node can have children

		if ((this.nodeType != NodeType.NETWORK_THEME) && (this.nodeType != NodeType.TREE_THEME) && (this.nodeType != NodeType.VIEW))
			setAllowsChildren(false);
		else
			setAllowsChildren(true);
	}

	// @}

	/**
	 * @name Modify
	 * 
	 *       Functions that modify the state of the attributes of the CustomNode
	 */

	// @{

	/**
	 * \brief Function that modifies the attribute that indicates if the node's
	 * scale has changed
	 * 
	 * @param scaleLimitChanged
	 *            A boolean that indicates if the node's scale has changed
	 */

	public void setIsScaleLimitChanged(Boolean scaleLimitChanged) {
		isScaleLimitChanged = scaleLimitChanged;
	}

	/**
	 * \brief Function that modifies the attribute that indicates if the node's
	 * visual has changed
	 * 
	 * @param visualChanged
	 *            A boolean that indicates if the node's visual has changed
	 */

	public void setIsVisualChanged(Boolean visualChanged) {
		this.theme.setIsVisualChanged(visualChanged);
	}

	/**
	 * \brief Function that modifies the Scale of the CustomNode
	 * 
	 * @param scaleLimit
	 *            The object containing the new Scale of the CustomNode
	 */

	public void setScaleLimit(ScaleLimit scaleLimit) {
		this.theme.setScaleLimit(scaleLimit);
	}

	/**
	 * \brief Function that modifies the attribute that indicates if the node
	 * has changed
	 * 
	 * @param nodeChanged
	 *            A boolean that indicates if the node has changed
	 */

	public void setIsNodeChanged(Boolean nodeChanged) {
		isNodeChanged = nodeChanged;
	}

	/**
	 * \brief Function that modifies the attribute that indicates the id of this
	 * node's parent
	 * 
	 * @param parentID
	 *            the node's parent id
	 */

	public void setParentID(Long parentID) {
		theme.setParentID(parentID);
	}

	/**
	 * \brief Function that modifies the vector containing the visuals of the
	 * node
	 * 
	 * @param visuals
	 *            The vector containing the new visuals of the node.
	 */

	public void setThemeVisuals(List<ThemeVisual> visuals) {
		this.theme.setThemeVisuals(visuals);
	}

	/**
	 * \brief Function that modifies the tooltip of the node
	 * 
	 * @param layer
	 *            ToolTip of the node
	 */

	public void setToolTipText(String toolTipText) {
		this.toolTipText = toolTipText;
	}

	/**
	 * \brief Function that modifies the layer
	 * 
	 * @param layer
	 *            Name of the layer
	 */

	public void setLayer(Layer layer) {
		theme.setLayer(layer);
	}

	/**
	 * \brief Function that modifies the list of theme representations
	 * 
	 * @param representation
	 *            List of theme representation
	 */

	public void setRepresentation(List<Representation> representation) {
		this.theme.setReps(representation);
	}

	/**
	 * \brief Function that modifies the numeric identification of the
	 * CustomNode
	 * 
	 * @param id
	 *            The numeric identification of the custom node
	 */

	public void setId(Long id) {
		if (nodeType.isTheme())
			theme.setId(id);
		else
			view.setId(id);
	}

	/**
	 * \brief Function that modifies the url of the theme
	 * 
	 * @param url
	 *            The url of the custom node
	 */

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * \brief Function that modifies the name of the CustomNode
	 * 
	 * @param name
	 *            The name of the custom node
	 */

	public void setName(String name) {
		if (nodeType.isTheme())
			theme.setName(name);
		else
			view.setName(name);
	}

	/**
	 * \brief Function that modifies the previous name of the CustomNode
	 * 
	 * @param newName
	 *            The new name of the custom node
	 */

	public void setPreviousName(String newName) {
		this.previousName = newName;
	}

	/**
	 * \brief Function that modifies the expanded state of the CustomNode
	 * 
	 * @param hiddenExpanded
	 *            The boolean attribute that indicates if the node is expanded
	 *            or not
	 */

	public void setHiddenExpanded(boolean hiddenExpanded) {
		this.hiddenExpanded = hiddenExpanded;
	}

	/**
	 * \brief Function that modifies the permission to have children of the
	 * CustomNode
	 * 
	 * @param allowsChildren
	 *            The boolean attribute that indicates if the node is allowed to
	 *            have children
	 */

	public void setAllowsChildren(boolean allowsChildren) {
		this.allowsChildren = allowsChildren;
	}

	/**
	 * \brief Function that modifies the selection state of the CustomNode
	 * 
	 * @param visible
	 *            The boolean attribute that indicates if the node is selected
	 *            or not
	 */

	public void setSelected(boolean visible) {
		if (nodeType.isTheme()) {
			theme.setVisibility(visible);
			if(nodeType == NodeType.NETWORK_THEME){
				for (int i = 0; i < getChildCount(); i++) {
					CustomNode node = (CustomNode) getChildAt(i);
					node.setSelected(visible);
				}
			}
			PlotterController.getInstance().startPlotter();			
		} else
			this.visible = visible;
	}

	/**
	 * \brief Function that modifies the visibility state of the CustomNode
	 * 
	 * @param visible
	 *            The boolean attribute that indicates if the node is visible or
	 *            not
	 */

	public void setScaleVisibility(boolean visible) {
		theme.setScaleVisibility(visible);
	}

	/**
	 * \brief Function that modifies the toolbar of the CustomNode
	 * 
	 * @param tool
	 *            This attribute is the toolbar of the node.
	 */

	public void setToolbar(TreeToolbar tool) {
		this.toolbar = tool;
	}

	/**
	 * \brief Function that modifies the icon of the CustomNode
	 * 
	 * @param icon
	 *            This attribute is the image icon of the node.
	 */

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	/**
	 * \brief Function that modifies the persisted state of the node
	 * 
	 * @param persisted
	 *            The boolean attribute that indicates if the node is persisted
	 *            or not
	 */

	public void setPersisted(boolean persisted) {
		this.persisted = persisted;
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();

		try {

			if (nodeType == NodeType.VIEW && !persisted && !applied) {
				mediator.disableAllTools();
				/*
				 * MainPanel.getInstance().getPnlToolbar().getbar()
				 * .disableToolbar();
				 */
			} else if (nodeType == NodeType.VIEW && persisted) {
				mediator.enableAllTools();
				/*
				 * MainPanel.getInstance().getPnlToolbar().getbar()
				 * .enableToolbar();
				 */
			}
		} catch (Exception e) {
		}
	}

	/**
	 * \brief Function that modifies the pplied in memory state of the node
	 * 
	 * @param applied
	 *            The boolean attribute that indicates if the node is applied in
	 *            memory or not
	 */

	public void setApplied(boolean applied) {
		this.applied = applied;
		AppSingleton singleton = AppSingleton.getInstance();
		Mediator mediator = singleton.getMediator();

		try {

			if (nodeType == NodeType.VIEW && !applied && !persisted) {
				mediator.disableAllTools();
				/*
				 * MainPanel.getInstance().getPnlToolbar().getbar()
				 * .disableToolbar();
				 */
			} else if (nodeType == NodeType.VIEW && applied) {
				mediator.enableAllTools();
				/*
				 * MainPanel.getInstance().getPnlToolbar().getbar()
				 * .enableToolbar();
				 */
			}
		} catch (Exception e) {
		}
	}

	/**
	 * \brief Function that modifies the transparency of the node
	 * 
	 * @param transparency
	 * 
	 */

	public void setTransparency(int transparency) {
		this.transparency = transparency;
	}

	/**
	 * \brief Function that modifies the projection of the node
	 * 
	 * @param projection
	 *            the Projection to set
	 */

	public void setProjection(Projection projection) {
		view.setProjection(projection);
	}

	/**
	 * \brief Function that modifies the view of the node
	 * 
	 * @param view
	 *            the new view of the node
	 */

	public void setView(View view) {
		this.view = view;
	}

	/**
	 * \brief Function that modifies the theme of the node
	 * 
	 * @param view
	 *            the new theme of the node
	 */

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	/**
	 * \brief Function that modifies the tindex of
	 * 
	 * @param view
	 *            the new theme of the node
	 */

	public void setIndex(int index) {
		theme.setIndex(index);
	}

	/**
	 * \brief Function that creates and returns a toolbar
	 * 
	 * @return The CustomNode that contains the toolbar
	 */

	public static CustomNode createToolBar() {
		return new CustomNode(0l, "ToolBar", false, NodeType.TOOLBAR);

	}

	// @}

	/**
	 * @name Access
	 * 
	 *       Functions that return the state of the attributes of the CustomNode
	 */

	// @{

	/**
	 * \brief Function that returns the view the node
	 * 
	 * @return The view of the node
	 */

	public View getView() {
		return view;
	}

	/**
	 * \brief Function that returns the theme of the node
	 * 
	 * @return The theme of the node
	 */

	public Theme getTheme() {
		return theme;
	}

	/**
	 * \brief Function that returns scaleLimit object related to the node
	 * 
	 * @return The sclaLimit of the node
	 */

	public ScaleLimit getScaleLimit() {
		return theme.getScaleLimit();
	}

	/**
	 * \brief Function that returns a boolean that indicates if the node us
	 * changed
	 * 
	 * @return A boolean that indicates if the node is changed
	 */

	public Boolean getIsNodeChanged() {
		return isNodeChanged;
	}

	/**
	 * \brief Function that returns the id of the node's parent
	 * 
	 * @return This node's
	 */

	public Long getParentID() {
		return theme.getParentID();
	}

	/**
	 * \brief Function that returns the tooltip of the node
	 * 
	 * @return the tooltip text
	 */

	public String getToolTipText() {
		return toolTipText;
	}

	/**
	 * \brief Function that returns the layer of the node
	 * 
	 * @return the layer name
	 */

	public Layer getLayer() {
		return theme.getLayer();
	}

	/**
	 * \brief Function that returns a vector containing the theme visuals of the
	 * node
	 * 
	 * @return A map containing all the visuals of the node
	 */

	public Map<Integer, ThemeVisual> getVisual() {

		Map<Integer, ThemeVisual> visual;

		if (getThemeVisuals() == null || getRepresentation() == null) {
			return visual = new HashMap<Integer, ThemeVisual>();
		} else {
			visual = new HashMap<Integer, ThemeVisual>();
			for (int i = 0; i < getThemeVisuals().size(); i++) {
				visual.put(getRepresentation().get(i).getId().intValue(), getThemeVisuals().get(i));
			}

			return visual;
		}
	}

	/**
	 * \brief Function that returns a vector containing the theme visuals of the
	 * node
	 * 
	 * @return A vector containing the theme visuals of the node
	 */

	public List<ThemeVisual> getThemeVisuals() {
		return theme.getThemeVisuals();
	}

	/**
	 * \brief Function that returns the projection of the node
	 * 
	 * @return the Projection of the node
	 */

	public Projection getProjection() {
		return view.getProjection();
	}

	/**
	 * \brief Function that returns the previous name of the node
	 * 
	 * @return the previous name of the node
	 */

	public String getPreviousName() {
		return previousName;
	}

	/**
	 * \brief Function that returns the list of representation
	 * 
	 * @return The list of representation
	 */

	public List<Representation> getRepresentation() {
		if (theme.getReps() == null) {
			theme.setReps(new ArrayList<Representation>());
			return theme.getReps();
		}
		return theme.getReps();
	}

	/**
	 * \brief Function that returns the box of the node
	 * 
	 * @return The box of the node
	 */

	public Box getBox() {
		if (nodeType.isTheme())
			return theme.getThemeBox();
		else
			return view.getBox();
	}

	/**
	 * \brief Function that returns the numerical identification of the
	 * CustomNode
	 * 
	 * @return The numeric identification of the node
	 */

	public Long getId() {
		if (nodeType.isTheme())
			return theme.getId();
		else
			return view.getId();
	}

	/**
	 * \brief Function that returns the type of the CustomNode The possible
	 * values are root, view, theme and toolbar
	 * 
	 * @return The node type of the node
	 */

	public NodeType getNodeType() {
		return nodeType;
	}

	/**
	 * \brief Function that returns the name of the CustomNode
	 * 
	 * @return The name of the node
	 */

	public String getName() {
		if (nodeType.isTheme())
			return theme.getName();
		else
			return view.getName();
	}

	/**
	 * \brief Function that returns the icon of the CustomNode
	 * 
	 * @return The icon of the node
	 */

	public Icon getIcon() {
		return icon;
	}

	/**
	 * \brief Function that returns the toolbar of the CustomNode
	 * 
	 * @return The toolbar of the node
	 */

	public TreeToolbar getToolbar() {
		return toolbar;
	}

	/**
	 * \brief Function that returns the url of the CustomNode
	 * 
	 * @return The url of the node
	 */

	public String getUrl() {
		return url;
	}

	/**
	 * \brief Function that returns the nodes current transparency
	 * 
	 * @return the transparency of the CustomNode
	 */

	public int getTransparency() {
		return transparency;
	}

	/**
	 * \brief Function that returns the index of the node's theme
	 * 
	 * @return The index of the node's theme
	 */

	public int getIndex() {
		return theme.getIndex();
	}

	// @}

	/**
	 * @name Verify
	 * 
	 *       Functions that verify the state of the attributes
	 */

	// @{

	/**
	 * \brief Function that indicates if the CustomNode is selected or not
	 * 
	 * @return The boolean that indicates if the node is selected
	 */

	public boolean isSelected() {
		if (nodeType.isTheme())
			return theme.isVisibility();
		else
			return visible;
	}

	/**
	 * \brief Function that indicates if the CustomNode can have children or not
	 * 
	 * @return The boolean that indicates if the node can have children
	 */

	public boolean isAllowsChildren() {
		return allowsChildren;
	}

	/**
	 * \brief Function that indicates the persisted sate of the node
	 * 
	 * @return The boolean that indicates if the node is persisted
	 */

	public boolean isPersisted() {
		return persisted;
	}

	/**
	 * 
	 * \brief Function that indicates the applied in memory sate of the node
	 * 
	 * @return The boolean that indicates if the node is applied in memory
	 */

	public boolean isApplied() {
		return applied;
	}

	/**
	 * \brief Function that indicates if the CustomNode is expanded or not
	 * 
	 * @return Function that indicates if the CustomNode is expanded or not
	 */

	public boolean isHiddenExpanded() {
		return hiddenExpanded;
	}

	/**
	 * \brief Function that indicates if the CustomNode is visible or not
	 * 
	 * @return Boolean that indicates if the CustomNode is visible or not
	 */

	public boolean isScaleVisible() {
		return theme.isScaleVisibility();
	}

	/**
	 * \brief Function that indicates if the scale of the CustomNode has changed
	 * 
	 * @return Boolean that indicates if the scale of the CustomNode has changed
	 */

	public Boolean isScaleLimitChanged() {
		return isScaleLimitChanged;
	}

	/**
	 * \brief Function that indicates if the visual of the CustomNode has
	 * changed
	 * 
	 * @return Boolean that indicates if the visual of the CustomNode has
	 *         changed
	 */

	public Boolean isVisualChanged() {
		return theme.isIsVisualChanged();
	}

	// @}

	/**
	 * @name converters
	 * 
	 *       Functions that convert the type of the attributes
	 */

	// @{

	/**
	 * 
	 * \brief Function that converts the type of the attribute name to a String
	 * 
	 * @return The name of the node returned strictly as a string
	 */

	@Override
	public String toString() {
		return getName();
	}

	/**
	 * \brief Function that converts the type of the attribute NodeType to an
	 * integer
	 * 
	 * @return The number that represents the NodeType in database
	 */

	public int getType() {

		if (nodeType == NodeType.DEFAULT) {
			return 0;
		} else if (nodeType == NodeType.TREE_THEME) {
			return 1;
		} else if (nodeType == NodeType.EXTERN_THEME) {
			return 2;
		} else if (nodeType == NodeType.FILE_THEME) {
			return 3;
		} else if (nodeType == NodeType.WMS_THEME) {
			return 4;
		} else if (nodeType == NodeType.WFS_THEME) {
			return 5;
		} else if (nodeType == NodeType.NETWORK_THEME) {
			return 6;
		}
		else {
			return 7;
		}
	}

	// @}

	/**
	 * @name Node checking
	 * 
	 *       The functions responsible for checking several nodes and their
	 *       states
	 */

	// @{

	/**
	 * \brief Function that checks all child nodes of a given node to verify of
	 * there's atleast one cjild that is selected
	 * 
	 * @param node
	 *            The node that will be checked
	 * @return A boolean that indicates if there is at least one child selected
	 *         at the given node
	 */

	public Boolean getChildSelection(CustomNode node) {

		Boolean selection = false;

		for (@SuppressWarnings("unchecked")
		Enumeration<CustomNode> e = node.children(); e.hasMoreElements();) {

			CustomNode currentNode = e.nextElement();

			if ((currentNode.isSelected()) && (currentNode.getNodeType() != NodeType.VIEW)
					&& (currentNode.getNodeType() != NodeType.TOOLBAR) && (currentNode.getNodeType() != NodeType.ROOT)) {
				selection = true;
				return selection;
			} else {
				selection = false;
			}

			if (currentNode.getNodeType() == NodeType.NETWORK_THEME || currentNode.getNodeType() == NodeType.TREE_THEME) {
				if (!currentNode.isLeaf()) {
					selection = getChildSelection(currentNode);
					if (selection) {
						return selection;
					}
				}
			}
		}

		return selection;
	}

	/**
	 * \brief Function that updates a view by updating all the themes it
	 * contains with the latest changes performed by the user
	 */

	@SuppressWarnings("unused")
	public void rearrangeThemes() {

		if (getNodeType() == NodeType.VIEW) {
			int childCount = getChildCount();
			List<Theme> themes = new ArrayList<Theme>();
			Map<Integer, CustomNode> networkThemes = new HashMap<Integer, CustomNode>();
			for (int i = 0; i < getChildCount(); i++) {
				CustomNode node = (CustomNode) getChildAt(i);				
				Theme theme = node.getTheme();
				if(theme.getType() == ThemeType.NETWORK_THEME.ordinal()){
					int count = node.getChildCount();
					if(count == 2){
						CustomNode pointsNode = (CustomNode) node.getChildAt(0);
						Theme pointsTheme = pointsNode.getTheme();
						themes.add(pointsTheme);
						
						CustomNode linesNode = (CustomNode) node.getChildAt(1);
						Theme linesTheme = linesNode.getTheme();
						themes.add(linesTheme);
					}
					continue;
				}
				
				Long id = ((CustomNode) getChildAt(i)).getParentID();
				
				if (id != null) {
					theme.setParentID(id);
				}
				
				themes.add(theme);

			}
			
			view.setThemes(themes);
		}
	}

	// @}
}