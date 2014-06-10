package br.org.funcate.glue.model;

import javax.swing.ImageIcon;

import br.org.funcate.glue.view.ImageIconLoader;

public abstract class LabelExpandCanvasService {

	static int xPosition = 0;

	public static ImageIcon getImageIconLabelExpanded(boolean expandedCanvas) {
		if (expandedCanvas) {
			return ImageIconLoader.createImageIcon(
					"br/org/funcate/glue/image/to_right2.png",
					LabelExpandCanvasService.class);
		} else {
			return ImageIconLoader.createImageIcon(
					"br/org/funcate/glue/image/to_left2.png",
					LabelExpandCanvasService.class);
		}
	}

	public static int[] getBoundsForLabelExpanded(int x, boolean expandedCanvas) {
		if (expandedCanvas) {
			xPosition = x;
			int[] bounds = { 0, 70, 20, 25 };
			return bounds;
		} else {
			int[] bounds = { xPosition, 70, 20, 25 };
			return bounds;
		}
	}
}
