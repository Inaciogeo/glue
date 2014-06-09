/**
 * \file WMTMSCapabilities.java
 * This class represents the root of the XML Version 1.1.1
 */

package br.org.funcate.glue.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * \brief This class represents the root of the XML Version 1.1.1
 * 
 * @author Emerson Leite de Moraes
 * @author Willyan Aleksander
 * 
 *         Version 1.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "_service", "_capability" })
@XmlRootElement(name = "WMT_MS_Capabilities")
public class WMTMSCapabilities {

	@XmlElement(name = "Service", required = true)
	protected Service _service;
	@XmlElement(name = "Capability", required = true)
	protected Capability _capability;
	@XmlAttribute
	protected String _version;

	/**
	 * \brief Gets the value of the service property.
	 * 
	 * @return An object Service
	 */
	public Service getService() {
		return _service;
	}

	/**
	 * \brief Sets the value of the capability property.
	 * 
	 * @param value
	 */
	public void setService(Service value) {
		this._service = value;
	}

	/**
	 * \brief Gets the value of the capability property.
	 * 
	 * @return An object Capability
	 */
	public Capability getCapability() {
		return _capability;
	}

	/**
	 * \brief Sets the value of the capability property.
	 * 
	 * @param value
	 */
	public void setCapability(Capability value) {
		this._capability = value;
	}

	/**
	 * \brief Gets the value of the version property.
	 * 
	 * @return An object String
	 * 
	 */
	public String getVersion() {
		return _version;
	}

	/**
	 * \brief Sets the value of the version property.
	 * 
	 * @param value
	 */
	public void setVersion(String value) {
		this._version = value;
	}

}
