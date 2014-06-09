/**
 * \file GetFeatureInfo.java
 * This class represents the GetFeatureInfo node of the XML.
 */

package br.org.funcate.glue.model.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * \brief This class represents the GetFeaureInfo node of the XML.
 * 
 * @author Emerson Leite de Moraes
 * @author Willyan Aleksander
 * 
 *         Version 1.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "_format", "_dcpType" })
@XmlRootElement(name = "GetFeatureInfo")
public class GetFeatureInfo {

	@XmlElement(name = "Format")
	protected List<String> _format;
	@XmlElement(name = "DCPType", required = true)
	protected DCPType _dcpType;

	/**
	 * \brief Gets the value of the format property.
	 * 
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * 
	 * 
	 */
	public List<String> getFormat() {
		if (_format == null) {
			_format = new ArrayList<String>();
		}
		return this._format;
	}

	/**
	 * \brief Gets the value of the dcpType property.
	 * 
	 * @return possible object is {@link DCPType }
	 * 
	 */
	public DCPType getDCPType() {
		return _dcpType;
	}

	/**
	 * \brief Sets the value of the dcpType property.
	 * 
	 * @param value
	 *            allowed object is {@link DCPType }
	 * 
	 */
	public void setDCPType(DCPType value) {
		this._dcpType = value;
	}

}
