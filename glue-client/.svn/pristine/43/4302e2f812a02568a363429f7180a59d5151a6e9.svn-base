package br.org.funcate.glue.model;

import br.org.funcate.glue.utilities.PropertiesReader;

/**
 * \class ScaleElement This class contains the values of a specific scale.
 * \brief This class contains the values of a specific scale. \author Henrique
 * and Ricardo \version 1.0 \date 20/08/2010
 * 
 */
public class ScaleElement {

	private double scaleValue;
	/** < the real value of the specific scale, it will be used on calculations */
	private String approximateScaleValue;

	/**
	 * < the value of the specific scale that will be showed for the user, so,
	 * it hasn't to be the exact value
	 */

	/*
	 * ! The contructor of the class
	 * 
	 * \param the real value of the specific scale, it will be used on
	 * calculations \param the value of the specific scale that will be showed
	 * for the user, so, it hasn't to be the exact value
	 */
	public ScaleElement(double value, String approximateValue) {
		this.scaleValue = value;
		this.approximateScaleValue = approximateValue;
	}

	// ! It returns the scale value that will be showed for the user. Returns a
	// value 'centimeters:meters'.
	public String toString() {
		String medidaPadrao = PropertiesReader.getProperty("unidade.padrao.medida.escala");

		if (medidaPadrao.equals("CM")) {
			Double aproximateValue = Double.valueOf(approximateScaleValue);
			return "1:" + ((int) (aproximateValue * 100));
		}

		return "1:" + approximateScaleValue + "";
	}

	// ! It returns the exact scale value that will be used on calculation.
	public double getScale() {
		return scaleValue;
	}

	public String getAproxScale() {
		return approximateScaleValue;
	}
}
