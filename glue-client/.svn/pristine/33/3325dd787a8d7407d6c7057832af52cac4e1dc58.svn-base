package br.org.funcate.glue.utilities;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.text.MaskFormatter;

public class Utils {

	private static MaskFormatter mask;
	private static Point point = new Point();

	public static MaskFormatter setMask(String maskcustom) {
		try {
			mask = new MaskFormatter(maskcustom);
		} catch (java.text.ParseException e) {
			System.err.println(e.getMessage());
		}
		return mask;
	}

	public static MaskFormatter setMask(char maskchar, int length) {
		String maskcustom = "";
		for (int i = 0; i <= length; i++) {
			maskcustom += maskchar;
		}
		setMask(maskcustom);
		return mask;
	}

	public static JDialog setScreenToOpen(Component root, Container target, Dimension dimension) {
		while (!((root instanceof JDialog) || (root instanceof JFrame))) {
			root = root.getParent();
		}
		final JDialog dialog;
		if (root instanceof JFrame) {
			dialog = new JDialog((JFrame) root);
			dialog.setContentPane(target);
		} else {
			dialog = (JDialog) target;
		}
		dialog.setSize(dimension);
		dialog.setPreferredSize(dimension);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setResizable(false);
		dialog.setModal(true);
		dialog.setLayout(null);
		dialog.setLocationRelativeTo(null);
		dialog.toFront();
		// O setVisible() deve ser atribuídos por chamada.

		// Adicionando uma nova ação
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Utils.setScreenToClose(dialog);
			}
		});
		return dialog;
	}

	public static void setScreenToClose(Component object) {
		while (!((object instanceof JDialog) || (object instanceof JFrame))) {
			object = object.getParent();
		}

		if (object instanceof JDialog) {
			((JDialog) object).setVisible(false);
			((JDialog) object).dispose();
		} else {
			((JFrame) object).setVisible(false);
			((JFrame) object).dispose();
		}
	}

	public static void setScreenToCenter(Component object) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();

		int width = screenSize.width;
		int height = screenSize.height;
		width = width - object.getWidth();
		height = height - object.getHeight();

		object.setLocation(width / 2, height / 2);
	}

	public static String getFormatedDate(String dateString) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date();
		try {
			d = df.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		return df2.format(d);
	}

	public static Date stringToDate(String s) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		if (s != null && !s.isEmpty())
			date = formatter.parse(s);
		return date;
	}

	public static String dateToString(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		if (d != null)
			return formatter.format(d);
		else
			return null;
	}
	
	/**
	 *	Defining a screen undecorated as draggable 
	 *	@author Inacio
	 *	@since  03/07/2012
	 */
	public static void moveScreen(final Component screen){
		screen.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				 point.x = e.getX();
			     point.y = e.getY();
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		}); 
		
		screen.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				Point p = screen.getLocation();
		        screen.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
			}
		});
		
		
	}
}
