package br.org.funcate.glue.model.thread;

import javax.swing.tree.TreePath;

import br.org.funcate.glue.controller.Mediator;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.tree.CustomNode;

public class ViewUpdator extends Thread {

	private Boolean reload;
	private Boolean memory;
	private Boolean remove;
	private Boolean result = false;

	public ViewUpdator(Boolean reload, Boolean memory, Boolean remove) {
		this.reload = reload;
		this.memory = memory;
		this.remove = remove;
	}

	public void run() {
		Mediator mediator = AppSingleton.getInstance().getMediator();

		try {

			mediator.disableTree();
			CustomNode currentView = mediator.getCurrentView();

			if (remove) {
				if (memory) {
					result = AppSingleton.getInstance().getServices().removeViewMem(currentView.getName());
				} else {
					result = AppSingleton.getInstance().getServices().removeView(currentView.getName());
				}
				if (result) {
					mediator.removeNodeFromParent(currentView);
					mediator.setCurrentView((CustomNode) mediator.getRoot().getChildAt(0));
					currentView.setSelected(true);
					mediator.setSelectionPath(new TreePath(currentView.getPath()));
				}
			} else {
				result = mediator.updateSelectedView(reload, memory);
			}

			mediator.enableTree();

			// Plot view if update is OK
			if (result) {
				mediator.draw(true, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mediator.enableTree();
			mediator.draw(true, false);
		}
	}
}