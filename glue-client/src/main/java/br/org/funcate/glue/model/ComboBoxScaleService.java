package br.org.funcate.glue.model;

import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.canvas.CanvasService;
import br.org.funcate.glue.model.canvas.CanvasState;

public abstract class ComboBoxScaleService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ComboBoxModel generateComboBoxModel() {
		ScaleElement[] scaleElements = getScaleElementValues();
		return new DefaultComboBoxModel(scaleElements);
	}

	public static ScaleElement[] getScaleElementValues() {
		ScaleElement[] scaleElements = { new ScaleElement(0.01, "0.01"),
				new ScaleElement(0.1, "0.1"), new ScaleElement(0.25, "0.25"),
				new ScaleElement(0.50, "0.50"), new ScaleElement(1, "1"),
				new ScaleElement(5, "5"), new ScaleElement(10, "10"),
				new ScaleElement(25, "25"), new ScaleElement(50, "50"),
				new ScaleElement(100, "100"), new ScaleElement(250, "250"),
				new ScaleElement(500, "500"), new ScaleElement(1000, "1000"),
				new ScaleElement(2000, "2000"), new ScaleElement(5000, "5000"),
				new ScaleElement(10000, "10000"),
				new ScaleElement(25000, "25000"),
				new ScaleElement(50000, "50000"),
				new ScaleElement(100000, "100000"),
				new ScaleElement(250000, "250000"),
				new ScaleElement(500000, "500000"),
				new ScaleElement(1000000, "1000000"),
				new ScaleElement(2500000, "2500000"),
				new ScaleElement(5000000, "5000000"),
				new ScaleElement(5000000, "5000000") };

		return scaleElements;
	}

	public static void changeScaleValue(double value) {
		ScaleService.setScaleValue(value);
	}

	public static ScaleElement getCurrentScaleElementValue() {
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		double scaleValue = state.getScale();
		ScaleElement scale = new ScaleElement(scaleValue,
				String.valueOf((int) scaleValue));
		return scale;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ComboBoxModel getRebuildedScaleComboModel() {
		ScaleElement[] scaleElements = getScaleElementValues();

		if (!CanvasService.hasBDImageSource()) {
			return new DefaultComboBoxModel(scaleElements);
		}

		Vector<ScaleElement> scaleValues = new Vector<ScaleElement>();

		double minScale = ScaleService.getMinScaleValue();
		double maxScale = ScaleService.getMaxScaleValue();

		for (int i = 0; i < scaleElements.length; i++) {
			ScaleElement item = scaleElements[i];
			if (!(item.getScale() < minScale || item.getScale() > maxScale)) {
				scaleValues.add(item);
			}
		}

		return new DefaultComboBoxModel(scaleValues);
	}
}
