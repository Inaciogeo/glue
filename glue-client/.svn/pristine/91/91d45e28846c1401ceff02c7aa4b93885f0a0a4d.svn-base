package br.org.funcate.glue.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for scaleLimit complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="scaleLimit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_max" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="_min" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "scaleLimit", propOrder = { "max", "min" })
public class ScaleLimit {

	@XmlElement(name = "_max")
	protected Double max = 15000000.0;
	@XmlElement(name = "_min")
	protected Double min = 10.0;

	public ScaleLimit(Double pMin, Double pMax) {
		setMin(pMin);
		setMax(pMax);
	}

	public ScaleLimit() {
	}

	/**
	 * Gets the value of the max property.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getMax() {
		return max;
	}

	/**
	 * Sets the value of the max property.
	 * 
	 * @param value
	 *            allowed object is {@link Double }
	 * 
	 */
	public void setMax(Double value) {
		this.max = value;
	}

	/**
	 * Gets the value of the min property.
	 * 
	 * @return possible object is {@link Double }
	 * 
	 */
	public Double getMin() {
		return min;
	}

	/**
	 * Sets the value of the min property.
	 * 
	 * @param value
	 *            allowed object is {@link Double }
	 * 
	 */
	public void setMin(Double value) {
		this.min = value;
	}

}
