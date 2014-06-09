/**
 * \file Get.java
 * This class represents the Get node of the XML.
 */

package br.org.funcate.glue.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * \brief This class represents the Get node of the XML.
 * 
 * @author Emerson Leite de Moraes
 * @author Willyan Aleksander
 * 
 *         Version 1.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "_onlineResource" })
@XmlRootElement(name = "Get")
public class Get {

	@XmlElement(name = "OnlineResource", required = true)
	protected OnlineResource _onlineResource;

	/**
	 * \brief Gets the value of the onlineResource property.
	 * 
	 * @return possible object is {@link OnlineResource }
	 * 
	 */
	public OnlineResource getOnlineResource() {
		return _onlineResource;
	}

	/**
	 * \brief Sets the value of the onlineResource property.
	 * 
	 * @param value
	 *            allowed object is {@link OnlineResource }
	 * 
	 */
	public void setOnlineResource(OnlineResource value) {
		this._onlineResource = value;
	}

}
