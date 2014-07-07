/**
 * \file Capability.java
 * This class represents the Capability node of the XML.
 */

package br.org.funcate.glue.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * \brief This class represents the Capability node of the XML.
 * 
 * @author Emerson Leite de Moraes
 * @author Willyan Aleksander
 * 
 *         Version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "_request", "_exception", "_vendorSpecificCapabilities", "_userDefinedSymbolization", "_layer" })
@XmlRootElement(name = "Capability")
public class Capability {

	@XmlElement(name = "Request", required = true)
	protected Request _request;
	@XmlElement(name = "Exception", required = true)
	protected Exception _exception;
	@XmlElement(name = "VendorSpecificCapabilities", required = true)
	protected String _vendorSpecificCapabilities;
	@XmlElement(name = "UserDefinedSymbolization", required = true)
	protected UserDefinedSymbolization _userDefinedSymbolization;
	@XmlElement(name = "Layer", required = true)
	protected Layer _layer;

	/**
	 * \brief Gets the value of the request property.
	 * 
	 * @return possible object is {@link Request }
	 * 
	 */
	public Request getRequest() {
		return _request;
	}

	/**
	 * \brief Sets the value of the request property.
	 * 
	 * @param value
	 *            allowed object is {@link Request }
	 * 
	 */
	public void setRequest(Request value) {
		this._request = value;
	}

	/**
	 * \brief Gets the value of the exception property.
	 * 
	 * @return possible object is {@link Exception }
	 * 
	 */
	public Exception getException() {
		return _exception;
	}

	/**
	 * \brief Sets the value of the exception property.
	 * 
	 * @param value
	 *            allowed object is {@link Exception }
	 * 
	 */
	public void setException(Exception value) {
		this._exception = value;
	}

	/**
	 * \brief Gets the value of the vendorSpecificCapabilities property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVendorSpecificCapabilities() {
		return _vendorSpecificCapabilities;
	}

	/**
	 * \brief Sets the value of the vendorSpecificCapabilities property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVendorSpecificCapabilities(String value) {
		this._vendorSpecificCapabilities = value;
	}

	/**
	 * \brief Gets the value of the userDefinedSymbolization property.
	 * 
	 * @return possible object is {@link UserDefinedSymbolization }
	 * 
	 */
	public UserDefinedSymbolization getUserDefinedSymbolization() {
		return _userDefinedSymbolization;
	}

	/**
	 * \brief Sets the value of the userDefinedSymbolization property.
	 * 
	 * @param value
	 *            allowed object is {@link UserDefinedSymbolization }
	 * 
	 */
	public void setUserDefinedSymbolization(UserDefinedSymbolization value) {
		this._userDefinedSymbolization = value;
	}

	/**
	 * \brief Gets the value of the layer property.
	 * 
	 * @return possible object is {@link Layer }
	 * 
	 */
	public Layer getLayer() {
		return _layer;
	}

	/**
	 * \brief Sets the value of the layer property.
	 * 
	 * @param value
	 *            allowed object is {@link Layer }
	 * 
	 */
	public void setLayer(Layer value) {
		this._layer = value;
	}

}
