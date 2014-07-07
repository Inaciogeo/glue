package br.org.funcate.glue.controller.listener;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.TreePath;

import br.org.funcate.glue.model.LookAndFeelService;
import br.org.funcate.glue.model.tree.CustomNode;
import br.org.funcate.glue.view.NodeType;

public class ExpansionListener implements TreeExpansionListener {

	/*
	 * ! Check if the use is trying to expand a hidden tree CustomNode unless
	 * the user uses the expand function of the theme popup the node is not
	 * expanded
	 */

	public void treeExpanded(TreeExpansionEvent evt) {
		TreePath path = evt.getPath();
		CustomNode node = (CustomNode) path.getLastPathComponent();
		if (node.getNodeType() != NodeType.VIEW) {
			LookAndFeelService.changeLookAndFeelForNimbus();
		}
		
		// Not a hidden theme anymore
		/*if (node.getNodeType() == NodeType.NETWORK_THEME && !node.isHiddenExpanded())
			AppSingleton.getInstance().getMediator().collapsePath(path);*/
	}

	/*
	 * ! Check if the use is trying to collapse a hidden tree CustomNode unless
	 * the user uses the collapse function of the theme popup the node is not
	 * collapsed
	 */

	public void treeCollapsed(TreeExpansionEvent evt) {
		TreePath path = evt.getPath();
		CustomNode _node = (CustomNode) path.getLastPathComponent();
		
		// Not a hidden theme anymore
/*		if (_node.getNodeType() == NodeType.NETWORK_THEME && _node.isHiddenExpanded()) {
			AppSingleton.getInstance().getMediator().expandPath(path);
		}*/
	}

}
