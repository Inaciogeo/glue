package br.org.funcate.glue.controller.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.ScaleElement;
import br.org.funcate.glue.model.ScaleLimit;
import br.org.funcate.glue.model.thread.PlotterController;
import br.org.funcate.glue.model.tree.CustomNode;
import br.org.funcate.glue.model.tree.TreeService;
import br.org.funcate.glue.view.NodeType;
import br.org.funcate.glue.view.ScaleView;

public class ScaleViewAdapter implements ActionListener {

	private ScaleView scaleView;
	
	public ScaleViewAdapter(ScaleView scaleView) {
		this.scaleView = scaleView;
	}

	public void actionPerformed(ActionEvent e) {
		if (scaleView.isActionPerformedBlocked()) {
			scaleView.setActionPerformedBlocked(false);
			return;
		}

		if (e.getSource() == scaleView.getBtCancel()) {
			scaleView.setVisible(false);
			return;
		}

		if (e.getSource() == scaleView.getBtConfirm()) {
			e.getActionCommand();
			Mediator mediator = AppSingleton.getInstance().getMediator();
			CustomNode currentTheme = mediator.getCurrentTheme();
			updateScale(currentTheme);
			mediator.getNodeView(currentTheme).setApplied(false);
			mediator.getNodeView(currentTheme).setPersisted(false);
			mediator.getNodeView(currentTheme).setIsNodeChanged(true);
			currentTheme.getTheme().setIsScalelimitChanged(true);
			double[] comboValues = getComboValues();
			currentTheme.getTheme().setScaleLimit(new ScaleLimit(comboValues[0] + 0.0, comboValues[1] + 0.0));
			TreeService.updateTreeVisibility();
			AppSingleton.getInstance().getMediator().repaint();
			PlotterController.getInstance().startPlotter();
			scaleView.dispose();
		}

		validateCombos();
	}

	public double[] getComboValues() {
		ScaleElement minScale = (ScaleElement) scaleView.getCmbMinima().getSelectedItem();
		ScaleElement maxScale = (ScaleElement) scaleView.getCmbMaxima().getSelectedItem();
		double[] ret = { minScale.getScale(), maxScale.getScale() };
		return ret;
	}

	private boolean validateCombos() {
		double[] comboValues = getComboValues();

		if (comboValues[1] <= comboValues[0]) {
			JOptionPane.showMessageDialog(null, "A escala máxima não pode ser menor ou igual à escala mínima", "Atenção",
					JOptionPane.INFORMATION_MESSAGE);
			scaleView.resetCombos();
			scaleView.requestFocus();
			return false;
		}

		return true;
	}

	private void updateScale(CustomNode updatedNode) {
		double[] comboValues = getComboValues();
		updatedNode.setScaleLimit(new ScaleLimit(comboValues[0] + 0.0, comboValues[1] + 0.0));
		updatedNode.setIsScaleLimitChanged(true);

		CustomNode currentTheme = AppSingleton.getInstance().getMediator().getCurrentTheme();
		if ((currentTheme.getNodeType() == NodeType.TREE_THEME) || (currentTheme.getNodeType() == NodeType.NETWORK_THEME)) {
			for (int x = 0; x < updatedNode.getChildCount(); x++) {
				updateScale((CustomNode) updatedNode.getChildAt(x));
			}
		}
	}
}