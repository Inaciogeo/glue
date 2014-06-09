/**
 * \file HTTP.java
 * This class represents the HTTP node of the XML.
 */

package br.org.funcate.glue.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * \brief This class represents the HTTP node of the XML.
 * 
 * @author Emerson Leite de Moraes
 * @author Willyan Aleksander
 * 
 *         Version 1.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "_get", "_post" })
@XmlRootElement(name = "HTTP")
public class HTTP {

	@XmlElement(name = "Get", required = true)
	protected Get _get;
	@XmlElement(name = "Post", required = true)
	protected Post _post;

	/**
	 * \brief Gets the value of the get property.
	 * 
	 * @return possible object is {@link Get }
	 * 
	 */
	public Get getGet() {
		return _get;
	}

	/**
	 * \brief Sets the value of the get property.
	 * 
	 * @param value
	 *            allowed object is {@link Get }
	 * 
	 */
	public void setGet(Get value) {
		this._get = value;
	}

	/**
	 * \brief Gets the value of the post property.
	 * 
	 * @return possible object is {@link Post }
	 * 
	 */
	public Post getPost() {
		return _post;
	}

	/**
	 * \brief Sets the value of the post property.
	 * 
	 * @param value
	 *            allowed object is {@link Post }
	 * 
	 */
	public void setPost(Post value) {
		this._post = value;
	}

}
