/**
 * \file ContactPersonPrimary.java
 * This class represents the ContactPersonPrimary node of the XML.
 */

package br.org.funcate.glue.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * \brief This class represents the ContactPersonPrimary node of the XML.
 * 
 * @author Emerson Leite de Moraes
 * @author Willyan Aleksander
 * 
 *         Version 1.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "_contactPerson", "_contactOrganization" })
@XmlRootElement(name = "ContactPersonPrimary")
public class ContactPersonPrimary {

	@XmlElement(name = "ContactPerson", required = true)
	protected String _contactPerson;
	@XmlElement(name = "ContactOrganization", required = true)
	protected String _contactOrganization;

	/**
	 * \brief Gets the value of the contactPerson property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContactPerson() {
		return _contactPerson;
	}

	/**
	 * \brief Sets the value of the contactPerson property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContactPerson(String value) {
		this._contactPerson = value;
	}

	/**
	 * \brief Gets the value of the contactOrganization property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContactOrganization() {
		return _contactOrganization;
	}

	/**
	 * \brief Sets the value of the contactOrganization property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContactOrganization(String value) {
		this._contactOrganization = value;
	}

}
