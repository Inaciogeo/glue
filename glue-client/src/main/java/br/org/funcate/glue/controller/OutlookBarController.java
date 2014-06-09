package br.org.funcate.glue.controller;

import javax.swing.Icon;
import javax.swing.JComponent;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.utilities.PropertiesReader;
import br.org.funcate.glue.view.JOutlookBar;

public class OutlookBarController {

	private JOutlookBar outlookBar;

	public OutlookBarController(JOutlookBar outlookBar) {
		this.outlookBar = outlookBar;
		AppSingleton.getInstance().getMediator().setOutlookBarController(this);
	}

	public void setVisible(boolean visible) {
		outlookBar.setVisible(visible);
	}

	void resizeToApplet(int[] size) {
		String canvasYPosition = PropertiesReader.getProperty("canvas.bound.y0");
		int height = size[1] - Integer.parseInt(canvasYPosition);
		outlookBar.setSize(outlookBar.getWidth(), height);
		outlookBar.updateUI();
	}

	void addSideBar(String name, Icon icon, JComponent component) {
		outlookBar.addBar(name, icon, component);
	}

	void removeSideBar(String name) {
		outlookBar.removeBar(name);
	}

	void setLastVisibleBar() {
		outlookBar.setLastVisibleBar();
	}
	
	/**
	 * Gets the {@link JComponent} by name
	 * @param name
	 * @return the {@link JComponent}. If the {@link JOutlookBar} doesn't have a component with the specified
	 * name this method return null.
	 */
	JComponent getSideBar(String name){
		return outlookBar.getBarComponent(name);
	}
	
	void repaintOutlookBar() {
		outlookBar.repaint();
	}
	
	
}
