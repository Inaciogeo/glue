package br.org.funcate.glue.view;

/** 
 * \file TreeToolbar.java
 * This class creates one ToolBar
 * */

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 * \class TreeToolbar Creates One ToolBar for the tree. \brief TreeToolbar
 * Creates One ToolBar for the tree. \author Emerson Leite de Moraes \author
 * Willyan Aleksander \version 1.0
 */

@SuppressWarnings("serial")
public class TreeToolbar extends JToolBar {

	public JButton _color, _style, _scale;

	// ! create BufferedImageIcon
	/*
	 * ! \param _dir directory of image
	 */
	public static BufferedImage getImg(URL _dir) throws IOException {

		// BufferedImage im =
		// ImageIO.read(TreeToolbar.class.getResourceAsStream(_dir));
		BufferedImage im = ImageIO.read(_dir);
		return im;
	}

	// ! Constructor
	public TreeToolbar() {

		/*
		 * !
		 * 
		 * Try to capture images and add the JButton and try to add events to
		 * JButton and populates toolbar
		 */

		try {
			URL url = getClass().getClassLoader().getResource("br/org/funcate/glue/image/bt19.png");
			_color = new JButton(new ImageIcon(getImg(url)));
			_color.setMaximumSize(new java.awt.Dimension(15, 15));
			_color.setToolTipText("Habilita ferramenta para alteração de cores.");
			_color.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});

			URL url2 = getClass().getClassLoader().getResource("br/org/funcate/glue/image/bt20.png");
			_style = new JButton(new ImageIcon(getImg(url2)));
			_style.setMaximumSize(new java.awt.Dimension(15, 15));
			_style.setToolTipText("Habilita ferramenta para alteração de estilos.");
			_style.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});

			URL url3 = getClass().getClassLoader().getResource("br/org/funcate/glue/image/bt21.png");
			_scale = new JButton(new ImageIcon(getImg(url3)));
			_scale.setMaximumSize(new java.awt.Dimension(15, 15));
			_scale.setToolTipText("Habilita ferramenta de escala.");
			_scale.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});

		} catch (IOException e1) {
			e1.printStackTrace();

		}
		Insets m = new Insets(0, -1, -4, -1);
		this.setMargin(m);

		this.setBorderPainted(false);
		add(_color);
		add(_style);
		add(_scale);
		setFloatable(false); // não permite que a barra seja movida
	}
}
