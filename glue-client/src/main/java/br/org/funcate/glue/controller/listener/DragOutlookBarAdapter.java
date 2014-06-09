package br.org.funcate.glue.controller.listener;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;

import br.org.funcate.glue.controller.DragOutlookBarController;
import br.org.funcate.glue.view.MainPanel;

public class DragOutlookBarAdapter implements MouseListener, MouseMotionListener {
	private MainPanel mainPanelScreen;
	private DragOutlookBarController controller;

	public DragOutlookBarAdapter(MainPanel panelScreen) {
		super();
		this.mainPanelScreen = panelScreen;
		this.controller = new DragOutlookBarController(mainPanelScreen);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * method that changes the mouse pointer to resize
	 */

	@Override
	public void mouseEntered(MouseEvent e) {
		controller.getMainPanelScreen().setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
		controller.getMainPanelScreen().getLblDragCanvas().setIcon(new ImageIcon(MainPanel.class.getResource("/br/org/funcate/glue/image/dragBarSelected.png")));
	}

	/**
	 * method that changes the mouse pointer to default
	 */

	@Override
	public void mouseExited(MouseEvent e) {
		controller.getMainPanelScreen().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		controller.getMainPanelScreen().getLblDragCanvas().setIcon(new ImageIcon(MainPanel.class.getResource("/br/org/funcate/glue/image/dragBar.png")));

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * method responsible for outlookBar resizing 
	 */

	@Override
	public void mouseDragged(MouseEvent e) {
		int realDrag = e.getX();
		int xDrag = e.getX() + controller.getMainPanelScreen().getLblDragCanvas().getX();

		int outlookBarHeigth = controller.getMainPanelScreen().getOutlookBar().getBounds().height;

		int canvasHeight = controller.getMainPanelScreen().getCanvas().getBounds().height;
		int canvasWidth = controller.getMainPanelScreen().getCanvas().getBounds().width;
		
		if (xDrag >= 147) {

			controller.getMainPanelScreen().setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));

			controller.getMainPanelScreen().getOutlookBar().setBounds(0, 70, xDrag, outlookBarHeigth);
			controller.getMainPanelScreen().getLblDragCanvas().setBounds(xDrag, 70, 4, 1100);
			controller.getMainPanelScreen().getCanvas().setBounds(xDrag + 4, 70, canvasWidth - realDrag, canvasHeight);
		}
		
		if (xDrag >= 150) {
			controller.getMainPanelScreen().getLblExpandingCanvasButton().setVisible(false);
			
		} else {
			controller.getMainPanelScreen().getLblExpandingCanvasButton().setVisible(true);
			controller.getMainPanelScreen().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				controller.getMainPanelScreen().getOutlookBar().revalidate();
				controller.getMainPanelScreen().getLblExpandingCanvasButton().revalidate();
				controller.getMainPanelScreen().getLblDragCanvas().revalidate();
			}
		});
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
