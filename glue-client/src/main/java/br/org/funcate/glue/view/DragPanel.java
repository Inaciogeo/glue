package br.org.funcate.glue.view;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

/**
 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
 * 
 *         \brief Class that provide the drag of the panel
 */
class DragPanel extends MouseAdapter implements MouseMotionListener {

	/** */
	private int x = -1;
	private int y = -1;
	
	DragPanel(JFrame pFrame) {
	}

	public void mouseDragged(MouseEvent e) {
		Component b = e.getComponent();
		if ((this.x != -1) && (this.y != -1)) {
			int x = b.getX() + e.getX() - this.x;
			int y = b.getY() + e.getY() - this.y;
			b.setLocation(x, y);

		}
	}

	public void mouseMoved(MouseEvent e) {
		// do nothing
	}

	public void mousePressed(MouseEvent e) {
		// AWTUtilities.setWindowOpacity(frame, 0.5f);
		this.x = e.getX();
		this.y = e.getY();
	}

	public void mouseReleased(MouseEvent e) {
		this.x = -1;
		this.y = -1;
		// AWTUtilities.setWindowOpacity(frame, 1.0f);
	}
}
