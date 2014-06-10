package br.org.funcate.glue.view;

import java.awt.Component;
import java.net.URL;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import br.org.funcate.glue.model.tree.CustomNode;

/** \file Renderer.java
 * This file creates a renderer that defines the visual of each node
 */

/**
 * \brief Renderer In this class it is defined the visual of each node. \author
 * André Gomes de Oliveira \author Paulo Renato Morais Oliveira
 */

@SuppressWarnings("serial")
public class Renderer extends DefaultTreeCellRenderer {

	/* ! Receives the component of the tree and defines its visual */

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

		/* Cast the object into a CustomNode */

		CustomNode _node = (CustomNode) value;

		if (_node.getNodeType() == NodeType.TOOLBAR) {
			_node.getToolbar().setOpaque(false);
			return _node.getToolbar();
		} else if (_node.getNodeType() == NodeType.VIEW) {

			/*
			 * if(!node.isPersisted() && !node.isLeaf() && !node.isSelected()){
			 * URL url = getClass().getClassLoader().getResource
			 * ("br/org/funcate/glue/image/vistaApplet.png");
			 * node.setIcon(Tree.getImage(url)); }
			 * 
			 * else if(!node.isPersisted() && node.isLeaf()&& !node.isSelected()
			 * ){ URL url = getClass().getClassLoader().getResource
			 * ("br/org/funcate/glue/image/vistaAppletVazia.png");
			 * node.setIcon(Tree.getImage(url)); }
			 * 
			 * else if(!node.isPersisted() && node.isLeaf() &&
			 * node.isSelected()){ URL url =
			 * getClass().getClassLoader().getResource
			 * ("br/org/funcate/glue/image/vistaAppletVaziaSelecionada.png");
			 * node.setIcon(Tree.getImage(url)); }
			 * 
			 * else if(!node.isPersisted() && !node.isLeaf() &&
			 * node.isSelected()){ URL url =
			 * getClass().getClassLoader().getResource
			 * ("br/org/funcate/glue/image/vistaAppletSelecionada.png");
			 * node.setIcon(Tree.getImage(url)); }
			 * 
			 * 
			 * else
			 */if (/* node.isPersisted() && */_node.isLeaf() && _node.isSelected()) {
				URL url = getClass().getClassLoader().getResource("br/org/funcate/glue/image/vistaPersistidaVaziaSelecionada.png");
				_node.setIcon(TreeView.getImage(url));

			}

			else if (/* node.isPersisted() && */_node.isLeaf() && !_node.isSelected()) {
				URL url = getClass().getClassLoader().getResource("br/org/funcate/glue/image/vistaPersistidaVazia.png");
				_node.setIcon(TreeView.getImage(url));
			}

			else if (/* node.isPersisted() && */!_node.isLeaf() && _node.isSelected()) {
				URL url = getClass().getClassLoader().getResource("br/org/funcate/glue/image/vistaPersistidaSelecionada.png");
				_node.setIcon(TreeView.getImage(url));
			}

			else {
				URL url = getClass().getClassLoader().getResource("br/org/funcate/glue/image/vistaPersistida.png");
				_node.setIcon(TreeView.getImage(url));
			}
		} else if (_node.getNodeType() == NodeType.GOOGLE) {
			if (_node.isSelected() && _node.isScaleVisible()) {
				URL url = getClass().getClassLoader().getResource("br/org/funcate/glue/image/OpenStreetMapLogo.png");
				_node.setIcon(TreeView.getImage(url));
			} else if ((!_node.isSelected() && _node.isScaleVisible()) || (!_node.isSelected() && !_node.isScaleVisible())) {
				URL url = getClass().getClassLoader().getResource("br/org/funcate/glue/image/temaNaoSelecionado.gif");
				_node.setIcon(TreeView.getImage(url));
			}
		} else if (_node.getNodeType() == NodeType.NETWORK_THEME) {
			if (_node.isSelected()) {
				URL url = getClass().getClassLoader().getResource("br/org/funcate/glue/image/temaNetwork.gif");
				_node.setIcon(TreeView.getImage(url));
			} else if ((!_node.isSelected() && _node.isScaleVisible()) || (!_node.isSelected() && !_node.isScaleVisible())) {
				URL url = getClass().getClassLoader().getResource("br/org/funcate/glue/image/temaNaoSelecionado.gif");
				_node.setIcon(TreeView.getImage(url));
			}
		} else if (_node.getNodeType() == NodeType.WMS_THEME) {
			if (_node.isSelected() && _node.isScaleVisible()) {
				URL url = getClass().getClassLoader().getResource("br/org/funcate/glue/image/temaWMS.gif");
				_node.setIcon(TreeView.getImage(url));
			} else if ((!_node.isSelected() && _node.isScaleVisible()) || (!_node.isSelected() && !_node.isScaleVisible())) {
				URL url = getClass().getClassLoader().getResource("br/org/funcate/glue/image/temaNaoSelecionado.gif");
				_node.setIcon(TreeView.getImage(url));
			} else {
				URL url = getClass().getClassLoader().getResource("br/org/funcate/glue/image/temaEscala.gif");
				_node.setIcon(TreeView.getImage(url));
			}
		} else if (_node.getNodeType().isTheme()) {
			if (_node.isSelected() && _node.isScaleVisible()) {
				URL url = getClass().getClassLoader().getResource("br/org/funcate/glue/image/temaVisivel.gif");
				_node.setIcon(TreeView.getImage(url));
			} else if ((!_node.isSelected() && _node.isScaleVisible()) || (!_node.isSelected() && !_node.isScaleVisible())) {
				URL url = getClass().getClassLoader().getResource("br/org/funcate/glue/image/temaNaoSelecionado.gif");
				_node.setIcon(TreeView.getImage(url));
			} else {
				URL url = getClass().getClassLoader().getResource("br/org/funcate/glue/image/temaEscala.gif");
				_node.setIcon(TreeView.getImage(url));
			}
		}

		setIcon(_node.getIcon());
		return this;
	}

}