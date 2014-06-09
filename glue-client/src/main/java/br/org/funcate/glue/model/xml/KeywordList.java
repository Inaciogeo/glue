/**
 * \file KeywordList.java
 * This class represents the KeywordList node of the XML.
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
 * \brief This class represents the KeywordList node of the XML.
 * 
 * @author Emerson Leite de Moraes
 * @author Willyan Aleksander
 * 
 *         Version 1.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "_keyword" })
@XmlRootElement(name = "KeywordList")
public class KeywordList {

	@XmlElement(name = "Keyword")
	protected List<String> _keyword;

	/**
	 * \brief Gets the value of the keyword property.
	 * 
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * 
	 * 
	 */
	public List<String> getKeyword() {
		if (_keyword == null) {
			_keyword = new ArrayList<String>();
		}
		return this._keyword;
	}

}
