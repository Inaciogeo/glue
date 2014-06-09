/**
 * \file ContactAdress.java
 * This class represents the ContactInformation node of the XML.
 */

package br.org.funcate.glue.model.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * \brief This class represents the ContactInformation node of the XML.
 * 
 * @author Emerson Leite de Moraes
 * @author Willyan Aleksander
 * 
 *         Version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "_contactPersonPrimary", "_contactPosition", "_contactAddress", "_contactElectronicMailAddress" })
@XmlRootElement(name = "ContactInformation")
public class ContactInformation {

	@XmlElement(name = "ContactPersonPrimary", required = true)
	protected ContactPersonPrimary _contactPersonPrimary;
	@XmlElement(name = "ContactPosition", required = true)
	protected String _contactPosition;
	@XmlElement(name = "ContactAddress", required = true)
	protected ContactAddress _contactAddress;
	@XmlElement(name = "ContactElectronicMailAddress", required = true)
	protected String _contactElectronicMailAddress;

	/**
	 * \brief Gets the value of the contactPersonPrimary property.
	 * 
	 * @return possible object is {@link ContactPersonPrimary }
	 * 
	 */
	public ContactPersonPrimary getContactPersonPrimary() {
		return _contactPersonPrimary;
	}

	/**
	 * \brief Sets the value of the contactPersonPrimary property.
	 * 
	 * @param value
	 *            allowed object is {@link ContactPersonPrimary }
	 * 
	 */
	public void setContactPersonPrimary(ContactPersonPrimary value) {
		this._contactPersonPrimary = value;
	}

	/**
	 * \brief Gets the value of the contactPosition property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContactPosition() {
		return _contactPosition;
	}

	/**
	 * \brief Sets the value of the contactPosition property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContactPosition(String value) {
		this._contactPosition = value;
	}

	/**
	 * \brief Gets the value of the contactAddress property.
	 * 
	 * @return possible object is {@link ContactAddress }
	 * 
	 */
	public ContactAddress getContactAddress() {
		return _contactAddress;
	}

	/**
	 * \brief Sets the value of the contactAddress property.
	 * 
	 * @param value
	 *            allowed object is {@link ContactAddress }
	 * 
	 */
	public void setContactAddress(ContactAddress value) {
		this._contactAddress = value;
	}

	/**
	 * \brief Gets the value of the contactElectronicMailAddress property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContactElectronicMailAddress() {
		return _contactElectronicMailAddress;
	}

	/**
	 * \brief Sets the value of the contactElectronicMailAddress property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContactElectronicMailAddress(String value) {
		this._contactElectronicMailAddress = value;
	}

}
