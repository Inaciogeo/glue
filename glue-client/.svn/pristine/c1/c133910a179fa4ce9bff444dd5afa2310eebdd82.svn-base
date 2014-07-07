/**
 * \file Style.java
 * This class represents the Style node of the XML.
 */

package br.org.funcate.glue.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * \brief This class represents the Style node of the XML.
 * 
 * @author Emerson Leite de Moraes
 * @author Willyan Aleksander
 * 
 *         Version 1.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "_name", "_title", "_legendURL" })
@XmlRootElement(name = "Style")
public class Style {

	@XmlElement(name = "Name", required = true)
	protected String _name;
	@XmlElement(name = "Title", required = true)
	protected String _title;
	@XmlElement(name = "LegendURL", required = true)
	protected LegendURL _legendURL;

	/**
	 * \brief Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return _name;
	}

	/**
	 * \brief Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this._name = value;
	}

	/**
	 * \brief Gets the value of the title property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTitle() {
		return _title;
	}

	/**
	 * \brief Sets the value of the title property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTitle(String value) {
		this._title = value;
	}

	/**
	 * \brief Gets the value of the legendURL property.
	 * 
	 * @return possible object is {@link LegendURL }
	 * 
	 */
	public LegendURL getLegendURL() {
		return _legendURL;
	}

	/**
	 * \brief Sets the value of the legendURL property.
	 * 
	 * @param value
	 *            allowed object is {@link LegendURL }
	 * 
	 */
	public void setLegendURL(LegendURL value) {
		this._legendURL = value;
	}

}
