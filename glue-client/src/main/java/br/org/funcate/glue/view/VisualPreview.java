package br.org.funcate.glue.view;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * \brief Class to show a preview image of how points, lines or polygons will be
 * show in canvas
 * 
 * @author MACHADO, Willyan Aleksander
 * @version 1.0
 */
@SuppressWarnings("serial")
public class VisualPreview extends JLabel {

	/**
	 * colors to fill points or lines, background color, polygon color and boder
	 * color
	 */
	private Color _pCor, _bgColor, _polygonColor, _borderColor;
	/** strings to set visual preview mode, such as type, style or border */
	private String _pType = "null", _polygonStyle = "null", _borderStyle = "null";
	/** intergers to set size of point and border */
	private int _pSize, _borderSize;
	/** Stroke to set type of line */
	Stroke _espes;
	/** intergers to set width and length of mini canvas of visual preview */
	private int _larg, _alt;
	/** float to set transparency of polygon */
	private float _polygonTrans;
	/** a graphics2d to draw everything of visual preview needs */
	Graphics2D g;

	/**
	 * Main constructor to initialize some basic variables
	 * 
	 * @author MACHADO, Willyan Aleksander
	 */
	public VisualPreview() {
		setBounds(0, 0, 101, 101);
		setLayout(null);
		_larg = 100;
		_alt = 100;
	}

	/**
	 * Alternative constructor, receives some parameters and initialize the
	 * preview canvas in polygon mode
	 * 
	 * @author MACHADO, Willyan Aleksander
	 * @param _polygonColor
	 *            color of polygon
	 * @param _transp
	 *            value of transparency
	 * @param _polygonStyle
	 *            style of polygon
	 * @param _borderColor
	 *            color of border
	 * @param _borderEsp
	 *            thickness of border
	 * @param _borderStyle
	 *            style of border
	 */
	public VisualPreview(Color _polygonColor, int _transp, String _polygonStyle, Color _borderColor, int _borderEsp, String _borderStyle) {

		setBounds(100, 100, 101, 101);
		setLayout(null);

		set_polygonColor(_polygonColor);
		set_polygonTrans(_transp);
		set_polygonStyle(_polygonStyle);
		set_borderColor(_borderColor);
		set_borderSize(_borderEsp);
		set_borderStyle(_borderStyle);

		_larg = 100;
		_alt = 100;
	}

	/**
	 * Another constructor that initialize the visual preview setting color,
	 * size and mode
	 * 
	 * @author MACHADO, Willyan Aleksander
	 * @param _cor
	 *            set color to paint
	 * @param _size
	 *            set size to paint objects in screen
	 * @param _type
	 *            set mode of visual preview
	 */
	public VisualPreview(Color _cor, int _size, String _type) {
		set_pCor(_cor);
		set_pSize(_size);
		set_pType(_type);
		_larg = 150;
		_alt = 150;
		set_bgColor(Color.white);
	}

	/**
	 * A method paint, that have a logical of visual preview. Verify the mode
	 * and parameters to paint correct polygons, points or lines in screen
	 * 
	 * @author MACHADO, Willyan Aleksander
	 * @param gra
	 *            a graphics to initialize the other graphics2d
	 */
	public void paint(Graphics gra) {
		g = (Graphics2D) gra;
		_espes = new BasicStroke(1.5f);
		g.setStroke(_espes);
		if (_pType.equalsIgnoreCase("cruz")) {
			g.setColor(_bgColor);
			g.fillRect(0, 0, _larg, _alt);

			g.setColor(_pCor);

			g.drawLine((_larg / 2), (_alt / 2) - get_pSize() / 2, (_larg / 2), (_alt / 2) + get_pSize() / 2);
			g.drawLine((_larg / 2) - get_pSize() / 2, (_alt / 2), (_larg / 2) + get_pSize() / 2, (_alt / 2));

		}
		if (_pType.equalsIgnoreCase("estrela")) {
			g.setColor(_bgColor);
			g.fillRect(0, 0, _larg, _alt);

			g.setColor(_pCor);

			g.drawLine((_larg / 2), (_alt / 2) - get_pSize() / 2, (_larg / 2), (_alt / 2) + get_pSize() / 2);
			g.drawLine((_larg / 2) - (int) (_pSize * Math.sqrt(3) / 4), (_alt / 2) - (get_pSize() / 3),
					(_larg / 2) + (int) (_pSize * Math.sqrt(3) / 4), (_alt / 2) + (get_pSize() / 3));
			g.drawLine((_larg / 2) - (int) (_pSize * Math.sqrt(3) / 4), (_alt / 2) + (get_pSize() / 3),
					(_larg / 2) + (int) (_pSize * Math.sqrt(3) / 4), (_alt / 2) - (get_pSize() / 3));
		}
		if (_pType.equalsIgnoreCase("círculo")) {
			g.setColor(_bgColor);
			g.fillRect(0, 0, _larg, _alt);

			g.setColor(_pCor);

			g.fillOval((_larg / 2) - _pSize / 2, (_alt / 2) - _pSize / 2, _pSize, _pSize);
		}
		if (_pType.equalsIgnoreCase("x")) {
			g.setColor(_bgColor);
			g.fillRect(0, 0, _larg, _alt);

			g.setColor(_pCor);

			g.drawLine((_larg / 2) - _pSize / 2, (_alt / 2) - _pSize / 2, (_larg / 2) + _pSize / 2, (_alt / 2) + _pSize / 2);
			g.drawLine((_larg / 2) - _pSize / 2, (_alt / 2) + _pSize / 2, (_larg / 2) + _pSize / 2, (_alt / 2) - _pSize / 2);

		}
		if (_pType.equalsIgnoreCase("caixa")) {
			g.setColor(_bgColor);
			g.fillRect(0, 0, _larg, _alt);

			g.setColor(_pCor);
			g.fillRect((_larg / 2) - _pSize / 2, (_alt / 2) - _pSize / 2, _pSize, _pSize);
		}
		if (_pType.equalsIgnoreCase("losango")) {
			g.setColor(_bgColor);
			g.fillRect(0, 0, _larg, _alt);

			g.setColor(_pCor);
			g.rotate(Math.PI / 4);
			g.fillRect(((int) Math.sqrt((_larg * _larg) * 2)) / 2 - _pSize / 2, -_pSize / 2, _pSize, _pSize);
			g.rotate(-1 * Math.PI / 4);
		}
		if (_pType.equalsIgnoreCase("círculo vazado")) {
			g.setColor(_bgColor);
			g.fillRect(0, 0, _larg, _alt);

			g.setColor(_pCor);
			g.drawOval((_larg / 2) - _pSize / 2, (_alt / 2) - _pSize / 2, _pSize, _pSize);
		}
		if (_pType.equalsIgnoreCase("caixa vazado")) {
			g.setColor(_bgColor);
			g.fillRect(0, 0, _larg, _alt);

			g.setColor(_pCor);
			g.drawRect((_larg / 2) - _pSize / 2, (_alt / 2) - _pSize / 2, _pSize, _pSize);
		}
		if (_pType.equalsIgnoreCase("losango vazado")) {
			g.setColor(_bgColor);
			g.fillRect(0, 0, _larg, _alt);

			g.setColor(_pCor);
			g.rotate(Math.PI / 4);
			g.drawRect(((int) Math.sqrt((_larg * _larg) * 2)) / 2 - _pSize / 2, -_pSize / 2, _pSize, _pSize);
			g.rotate(-1 * Math.PI / 4);
		}

		if (_pType.equalsIgnoreCase("linha solida")) {
			g.setColor(_pCor);

			g.setStroke(new BasicStroke((float) _pSize));

			g.drawLine(8, 37, 142, 37);
			g.drawLine(8, 74, 142, 74);
			g.drawLine(8, 111, 142, 111);
		}
		if (_pType.equalsIgnoreCase("linha tracejada")) {
			g.setColor(_pCor);
			if ( _pSize <= 0 ) _pSize = 1;
			float dash[] = { _pSize * 2, _pSize + 1, _pSize * 2, _pSize + 1 };
			g.setStroke(new BasicStroke((float) _pSize, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, dash, 0.0f));

			g.drawLine(8, 37, 142, 37);
			g.drawLine(8, 74, 142, 74);
			g.drawLine(8, 111, 142, 111);
		}
		if (_pType.equalsIgnoreCase("linha pontilhada")) {
			g.setColor(_pCor);

			float dash[] = { _pSize, _pSize + 1, _pSize, _pSize + 1 };
			g.setStroke(new BasicStroke((float) _pSize, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, dash, 0.0f));

			g.drawLine(8, 37, 142, 37);
			g.drawLine(8, 74, 142, 74);
			g.drawLine(8, 111, 142, 111);
		}
		if (_pType.equalsIgnoreCase("linha traço-ponto")) {
			g.setColor(_pCor);

			float dash[] = { (float) (2.1 * _pSize), _pSize + 1, _pSize, _pSize + 1 };
			g.setStroke(new BasicStroke((float) _pSize, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, dash, 0.0f));

			g.drawLine(8, 37, 142, 37);
			g.drawLine(8, 74, 142, 74);
			g.drawLine(8, 111, 142, 111);
		}
		if (_pType.equalsIgnoreCase("linha traço-ponto-ponto")) {
			g.setColor(_pCor);

			float dash[] = { 10.0f, 4.0f, 3.0f, 4.0f, 3.0f, 4.0f };
			g.setStroke(new BasicStroke((float) _pSize, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, dash, 0.0f));

			g.drawLine(8, 37, 142, 37);
			g.drawLine(8, 74, 142, 74);
			g.drawLine(8, 111, 142, 111);
		}
		if (_pType.equalsIgnoreCase("nenhum")) {
			g.setColor(_pCor);
		}
		if (_pType.equalsIgnoreCase("oriented line")) {
			g.setColor(_pCor);
			if (_pSize < 3) {
				g.setStroke(new BasicStroke((float) _pSize));

				g.drawLine(8, 37, 142 - _pSize, 37);
				g.drawLine(8, 74, 142 - _pSize, 74);
				g.drawLine(8, 111, 142 - _pSize, 111);

				_pSize = 3;

				int trigonX1[] = { 148, (int) (148 - (_pSize * 1.7)), (int) (148 - (_pSize * 1.7)) };
				int trigonY1[] = { 37, (int) (37 - _pSize * 1.5), (int) (37 + _pSize * 1.5) };
				g.fillPolygon(trigonX1, trigonY1, 3);

				int trigonX2[] = { 148, (int) (148 - (_pSize * 1.7)), (int) (148 - (_pSize * 1.7)) };
				int trigonY2[] = { 2 * 37, (int) (2 * 37 - _pSize * 1.5), (int) (2 * 37 + _pSize * 1.5) };
				g.fillPolygon(trigonX2, trigonY2, 3);

				int trigonX3[] = { 148, (int) (148 - (_pSize * 1.7)), (int) (148 - (_pSize * 1.7)) };
				int trigonY3[] = { 3 * 37, (int) (3 * 37 - _pSize * 1.5), (int) (3 * 37 + _pSize * 1.5) };
				g.fillPolygon(trigonX3, trigonY3, 3);
			} else {
				int trigonX1[] = { 148, (int) (148 - (_pSize * 1.7)), (int) (148 - (_pSize * 1.7)) };
				int trigonY1[] = { 37, (int) (37 - _pSize * 1.5), (int) (37 + _pSize * 1.5) };
				g.fillPolygon(trigonX1, trigonY1, 3);

				int trigonX2[] = { 148, (int) (148 - (_pSize * 1.7)), (int) (148 - (_pSize * 1.7)) };
				int trigonY2[] = { 2 * 37, (int) (2 * 37 - _pSize * 1.5), (int) (2 * 37 + _pSize * 1.5) };
				g.fillPolygon(trigonX2, trigonY2, 3);

				int trigonX3[] = { 148, (int) (148 - (_pSize * 1.7)), (int) (148 - (_pSize * 1.7)) };
				int trigonY3[] = { 3 * 37, (int) (3 * 37 - _pSize * 1.5), (int) (3 * 37 + _pSize * 1.5) };
				g.fillPolygon(trigonX3, trigonY3, 3);

				g.setStroke(new BasicStroke((float) _pSize));

				g.drawLine(8, 37, 142, 37);
				g.drawLine(8, 74, 142, 74);
				g.drawLine(8, 111, 142, 111);
			}
		}
		if (_pType.equalsIgnoreCase("oriented middle line")) {
			g.setColor(_pCor);

			if (_pSize < 3) {
				g.setStroke(new BasicStroke((float) _pSize));

				g.drawLine(8, 37, 142 - _pSize, 37);
				g.drawLine(8, 74, 142 - _pSize, 74);
				g.drawLine(8, 111, 142 - _pSize, 111);

				_pSize = 3;

				int trigonX1[] = { _pSize / 2 + 80, (int) (80 - (_pSize * 1.7)), (int) (80 - (_pSize * 1.7)) };
				int trigonY1[] = { 37 - 1, (int) (37 - _pSize * 1.5), (int) (37 + _pSize * 1.5) };
				g.fillPolygon(trigonX1, trigonY1, 3);

				int trigonX2[] = { _pSize / 2 + 80, (int) (80 - (_pSize * 1.7)), (int) (80 - (_pSize * 1.7)) };
				int trigonY2[] = { 2 * 37, (int) (2 * 37 - _pSize * 1.5), (int) (2 * 37 + _pSize * 1.5) };
				g.fillPolygon(trigonX2, trigonY2, 3);

				int trigonX3[] = { _pSize / 2 + 80, (int) (80 - (_pSize * 1.7)), (int) (80 - (_pSize * 1.7)) };
				int trigonY3[] = { 3 * 37, (int) (3 * 37 - _pSize * 1.5), (int) (3 * 37 + _pSize * 1.5) };
				g.fillPolygon(trigonX3, trigonY3, 3);
			} else {
				int trigonX1[] = { _pSize / 2 + 80, (int) (80 - (_pSize * 1.7)), (int) (80 - (_pSize * 1.7)) };
				int trigonY1[] = { 37 - 1, (int) (37 - _pSize * 1.5), (int) (37 + _pSize * 1.5) };
				g.fillPolygon(trigonX1, trigonY1, 3);

				int trigonX2[] = { _pSize / 2 + 80, (int) (80 - (_pSize * 1.7)), (int) (80 - (_pSize * 1.7)) };
				int trigonY2[] = { 2 * 37, (int) (2 * 37 - _pSize * 1.5), (int) (2 * 37 + _pSize * 1.5) };
				g.fillPolygon(trigonX2, trigonY2, 3);

				int trigonX3[] = { _pSize / 2 + 80, (int) (80 - (_pSize * 1.7)), (int) (80 - (_pSize * 1.7)) };
				int trigonY3[] = { 3 * 37, (int) (3 * 37 - _pSize * 1.5), (int) (3 * 37 + _pSize * 1.5) };
				g.fillPolygon(trigonX3, trigonY3, 3);

				g.setStroke(new BasicStroke((float) _pSize));
				g.drawLine(8, 37, 142 - _pSize, 37);
				g.drawLine(8, 74, 142 - _pSize, 74);
				g.drawLine(8, 111, 142 - _pSize, 111);
			}
		}

		if (_polygonStyle.equalsIgnoreCase("transparente")) {
			// do nothing
		}
		if (_polygonStyle.equalsIgnoreCase("sólido")) {
			g.setColor(_polygonColor);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, _polygonTrans));

			g.fillRect(0, 0, _larg, _alt);
		}
		if (_polygonStyle.equalsIgnoreCase("horizontal") || _polygonStyle.equalsIgnoreCase("cruz")) {
			g.setColor(_polygonColor);
			g.setStroke(new BasicStroke(2.0f));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, _polygonTrans));
			for (int i = 0; i < 13; i++) {
				g.drawLine(0, 2 + 8 * i, _larg - _borderSize / 2, 2 + 8 * i);
			}
		}
		if (_polygonStyle.equalsIgnoreCase("vertical") || _polygonStyle.equalsIgnoreCase("cruz")) {
			g.setColor(_polygonColor);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, _polygonTrans));
			for (int i = 0; i < 13; i++) {
				g.drawLine(2 + 8 * i, 0, 2 + 8 * i, _alt - _borderSize / 2);
			}
		}
		if (_polygonStyle.equalsIgnoreCase("fdiagonal") || _polygonStyle.equalsIgnoreCase("cruz Diagonal")) {
			g.setColor(_polygonColor);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, _polygonTrans));
			for (int i = 0; i < 10; i++) {
				g.drawLine(10 * i, 0, _larg, _alt - 10 * i);
			}
			for (int i = 1; i < 10; i++) {
				g.drawLine(0, 10 * i, _larg - 10 * i, _alt);
			}
		}
		if (_polygonStyle.equalsIgnoreCase("bdiagonal") || _polygonStyle.equalsIgnoreCase("cruz Diagonal")) {
			g.setColor(_polygonColor);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, _polygonTrans));
			for (int i = 0; i < 10; i++) {
				g.drawLine(_larg - i * 10, 0, 0, _alt - i * 10);
			}
			for (int i = 1; i < 10; i++) {
				g.drawLine(_larg, 10 * i, 10 * i, _alt);
			}
		}
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		if (_borderStyle.equalsIgnoreCase("linha solida")) {
			g.setColor(_borderColor);
			g.setStroke(new BasicStroke(_borderSize));

			g.drawLine(0, _borderSize / 2, _larg, _borderSize / 2);
			g.drawLine(_borderSize / 2, 0, _borderSize / 2, _alt);
			if (_borderSize % 2 == 0) {
				g.drawLine(0, _alt - _borderSize / 2 + 1, _larg, _alt - _borderSize / 2 + 1);
				g.drawLine(_larg - _borderSize / 2 + 1, 0, _larg - _borderSize / 2 + 1, _alt);
			} else {
				g.drawLine(0, _alt - _borderSize / 2, _larg, _alt - _borderSize / 2);
				g.drawLine(_larg - _borderSize / 2, 0, _larg - _borderSize / 2, _alt);
			}
		}
		if (_borderStyle.equalsIgnoreCase("linha tracejada")) {
			g.setColor(_borderColor);
			float dash[] = { _borderSize * 2, _borderSize + 1, _borderSize * 2, _borderSize + 1 };
			g.setStroke(new BasicStroke((float) _borderSize, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, dash, 0.0f));

			g.drawLine(0, _borderSize / 2, _larg, _borderSize / 2);
			g.drawLine(_borderSize / 2, 0, _borderSize / 2, _alt);
			if (_borderSize % 2 == 0) {
				g.drawLine(0, _alt - _borderSize / 2 + 1, _larg, _alt - _borderSize / 2 + 1);
				g.drawLine(_larg - _borderSize / 2 + 1, 0, _larg - _borderSize / 2 + 1, _alt);
			} else {
				g.drawLine(0, _alt - _borderSize / 2, _larg, _alt - _borderSize / 2);
				g.drawLine(_larg - _borderSize / 2, 0, _larg - _borderSize / 2, _alt);
			}
		}
		if (_borderStyle.equalsIgnoreCase("linha pontilhada")) {
			g.setColor(_borderColor);
			float dash[] = { _borderSize, _borderSize + 1, _borderSize, _borderSize + 1 };
			g.setStroke(new BasicStroke((float) _borderSize, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, dash, 0.0f));

			g.drawLine(0, _borderSize / 2, _larg, _borderSize / 2);
			g.drawLine(_borderSize / 2, 0, _borderSize / 2, _alt);
			if (_borderSize % 2 == 0) {
				g.drawLine(0, _alt - _borderSize / 2 + 1, _larg, _alt - _borderSize / 2 + 1);
				g.drawLine(_larg - _borderSize / 2 + 1, 0, _larg - _borderSize / 2 + 1, _alt);
			} else {
				g.drawLine(0, _alt - _borderSize / 2, _larg, _alt - _borderSize / 2);
				g.drawLine(_larg - _borderSize / 2, 0, _larg - _borderSize / 2, _alt);
			}
		}
		if (_borderStyle.equalsIgnoreCase("linha traço-ponto")) {
			g.setColor(_borderColor);
			float dash[] = { (float) (2.1 * _borderSize), _borderSize + 1, _borderSize, _borderSize + 1 };
			g.setStroke(new BasicStroke((float) _borderSize, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, dash, 0.0f));

			g.drawLine(0, _borderSize / 2, _larg, _borderSize / 2);
			g.drawLine(_borderSize / 2, 0, _borderSize / 2, _alt);
			if (_borderSize % 2 == 0) {
				g.drawLine(0, _alt - _borderSize / 2 + 1, _larg, _alt - _borderSize / 2 + 1);
				g.drawLine(_larg - _borderSize / 2 + 1, 0, _larg - _borderSize / 2 + 1, _alt);
			} else {
				g.drawLine(0, _alt - _borderSize / 2, _larg, _alt - _borderSize / 2);
				g.drawLine(_larg - _borderSize / 2, 0, _larg - _borderSize / 2, _alt);
			}
		}
		if (_borderStyle.equalsIgnoreCase("Linha traço-ponto-ponto")) {
			g.setColor(_borderColor);
			float dash[] = { (float) (2.1 * _borderSize), _borderSize + 1, _borderSize, _borderSize + 1, _borderSize, _borderSize + 1 };
			g.setStroke(new BasicStroke((float) _borderSize, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, dash, 0.0f));

			g.drawLine(0, _borderSize / 2, _larg, _borderSize / 2);
			g.drawLine(_borderSize / 2, 0, _borderSize / 2, _alt);
			if (_borderSize % 2 == 0) {
				g.drawLine(0, _alt - _borderSize / 2 + 1, _larg, _alt - _borderSize / 2 + 1);
				g.drawLine(_larg - _borderSize / 2 + 1, 0, _larg - _borderSize / 2 + 1, _alt);
			} else {
				g.drawLine(0, _alt - _borderSize / 2, _larg, _alt - _borderSize / 2);
				g.drawLine(_larg - _borderSize / 2, 0, _larg - _borderSize / 2, _alt);
			}
		}
		if (_borderStyle.equalsIgnoreCase("nenhum")) {
			// do nothing
		}
	}

	/**
	 * method to get imageIcon
	 * 
	 * @author MACHADO, Willyan Aleksander
	 * @param path
	 *            path of image
	 * @return returns imageIcon
	 */
	protected ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("arquivo não encontrado: " + path);
			return null;
		}
	}

	/**
	 * sets enable or disable the window
	 * 
	 * @param b
	 */
	public void setEnable(boolean b) {
		this.setVisible(b);
	}

	/**
	 * sets the color to paint objects in screen
	 * 
	 * @param pCor
	 */
	public void set_pCor(Color pCor) {
		_pCor = pCor;
		repaint();
	}

	/**
	 * sets the size of objects to paint in screen
	 * 
	 * @param pSize
	 */
	public void set_pSize(int pSize) {
		_pSize = pSize;
		repaint();
	}

	/**
	 * sets the type of screen
	 * 
	 * @param pType
	 */
	public void set_pType(String pType) {
		_pType = pType;
		repaint();
	}

	/**
	 * sets background color
	 * 
	 * @param bgColor
	 */
	public void set_bgColor(Color bgColor) {
		_bgColor = bgColor;
		repaint();
	}

	/**
	 * gets the color of objects
	 * 
	 * @return the color
	 */
	public Color get_pCor() {
		return _pCor;
	}

	/**
	 * gets background color
	 * 
	 * @return the color
	 */
	public Color get_bgColor() {
		return _bgColor;
	}

	/**
	 * gets type of objects are painting in screen
	 * 
	 * @return string of type
	 */
	public String get_pType() {
		return _pType;
	}

	/**
	 * gets size of object are painting in screen
	 * 
	 * @return the int size
	 */
	public int get_pSize() {
		return _pSize;
	}

	/**
	 * sets polygon color
	 * 
	 * @param polygonColor
	 *            the _polygonColor to set
	 */
	public void set_polygonColor(Color polygonColor) {
		_polygonColor = polygonColor;
		repaint();
	}

	/**
	 * sets border color
	 * 
	 * @param borderColor
	 *            the _borderColor to set
	 */
	public void set_borderColor(Color borderColor) {
		_borderColor = borderColor;
		repaint();
	}

	/**
	 * sets polygon style
	 * 
	 * @param polygonStyle
	 *            the _polygonStyle to set
	 */
	public void set_polygonStyle(String polygonStyle) {
		_polygonStyle = polygonStyle;
		repaint();
	}

	/**
	 * sets border style
	 * 
	 * @param borderStyle
	 *            the _borderStyle to set
	 */
	public void set_borderStyle(String borderStyle) {
		_borderStyle = borderStyle;
		repaint();
	}

	/**
	 * sets border size
	 * 
	 * @param borderSize
	 *            the _borderSize to set
	 */
	public void set_borderSize(int borderSize) {
		_borderSize = borderSize;
		repaint();
	}

	/**
	 * sets transparency of polygons
	 * 
	 * @param polygonTrans
	 *            the _polygonTrans to set
	 */
	public void set_polygonTrans(int polygonTrans) {
		_polygonTrans = (float) polygonTrans / 100;
		repaint();
	}

	/**
	 * gets color of polygons
	 * 
	 * @return the _polygonColor
	 */
	public Color get_polygonColor() {
		return _polygonColor;
	}

	/**
	 * gets border color
	 * 
	 * @return the _borderColor
	 */
	public Color get_borderColor() {
		return _borderColor;
	}

	/**
	 * gets polygon style
	 * 
	 * @return the _polygonStyle
	 */
	public String get_polygonStyle() {
		return _polygonStyle;
	}

	/**
	 * get border style
	 * 
	 * @return the _borderStyle
	 */
	public String get_borderStyle() {
		return _borderStyle;
	}

	/**
	 * gets border size
	 * 
	 * @return the _borderSize
	 */
	public int get_borderSize() {
		return _borderSize;
	}

	/**
	 * gets polygon transparency
	 * 
	 * @return the _polygonTrans
	 */
	public float get_polygonTrans() {
		return _polygonTrans;
	}
}
