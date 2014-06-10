package br.org.funcate.glue.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.LookAndFeelService;
import br.org.funcate.glue.model.tree.CustomNode;
import br.org.funcate.glue.view.LocalOptionPane;
import br.org.funcate.glue.view.NodeType;
import br.org.funcate.glue.view.PanelViewThemeAddButton;
import br.org.funcate.glue.view.ThemeOptionsView;

public class PanelAddViewThemeAdapterController extends MouseAdapter {

	private PanelViewThemeAddButton panelAddButton;
	private Mediator mediator;

	public PanelAddViewThemeAdapterController(
			PanelViewThemeAddButton panelAddButton) {
		this.panelAddButton = panelAddButton;
		mediator = AppSingleton.getInstance().getMediator();
		mediator.setPanelAddViewThemeController(this);
	}

	public void setVisible(boolean visible) {
		panelAddButton.getLblAddTheme().setVisible(visible);
		panelAddButton.getLblAddView().setVisible(visible);
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		if (evt.getSource() == panelAddButton.getLblAddView()) {
			CustomNode root = mediator.getRoot();

			LookAndFeelService.changeLookAndFeelForNimbus();

			String name = "";

			do {
				name = JOptionPane.showInputDialog(
						"Digite o nome da nova vista: ", name);
				if (name == null) { // cancelado
					return;
				} else if (name.trim().equals("")) { // escolhido, mas vazio
					JOptionPane.showMessageDialog(null,
							"Nenhum nome selecionado. Tente novamente.");
				} else if (name.length() > 20) { // escolhido, mas muito longo
					JOptionPane.showMessageDialog(null,
							"Nome excede 20 caracteres. Tente novamente.");
				}
			} while (name.length() > 20 || name.trim().length() == 0);

			LookAndFeelService.changeLookAndFeelForDefault();

			CustomNode compare = new CustomNode(0l, name, true, NodeType.VIEW);

			if (mediator.checkAllNodes(root, compare)) {
				LocalOptionPane.getInstance("Este nome já existe",
						"Glue - Informação Duplicada");
			}

			else {
				CustomNode node = new CustomNode(0l, name, false, NodeType.VIEW);
				root.add(node);
				node.setApplied(false);
				node.setPersisted(false);
				node.setIsNodeChanged(true);
			}
			mediator.reload();
		} else if (evt.getSource() == panelAddButton.getLblAddTheme()) {
			CustomNode parent = mediator.getLastSelectedPathComponent();

			if (parent == null) {
				parent = mediator.getCurrentView();
				if (parent == null)
					return;
			}

			if (parent.getNodeType() == NodeType.TOOLBAR) {
				parent = (CustomNode) parent.getParent();

			}
			if (!parent.isAllowsChildren()) {
				LocalOptionPane.getInstance("Este tema não pode ter subtemas ",
						"Glue - Temas");
				return;
			}

			ThemeOptionsView.getInstance(parent);
		}
	}
}
