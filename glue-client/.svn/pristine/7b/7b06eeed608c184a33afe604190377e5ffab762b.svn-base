/**
 * \file Layer.java
 * This class represents the Layer node of the XML.
 */

package br.org.funcate.glue.model.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * \brief This class represents the Layer node of the XML.
 * 
 * @author Emerson Leite de Moraes
 * @author Willyan Aleksander
 * 
 *         Version 1.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "_name", "_title", "_abstract", "_keywordList", "_srs", "_latLonBoundingBox", "_boundingBox", "_style",
		"_layer" })
@XmlRootElement(name = "Layer")
public class Layer {

	@XmlElement(name = "Name", required = true)
	protected String _name;
	@XmlElement(name = "Title", required = true)
	protected String _title;
	@XmlElement(name = "Abstract")
	protected String _abstract;
	@XmlElement(name = "KeywordList")
	protected KeywordList _keywordList;
	@XmlElement(name = "SRS")
	protected List<String> _srs;
	@XmlElement(name = "LatLonBoundingBox")
	protected LatLonBoundingBox _latLonBoundingBox;
	@XmlElement(name = "BoundingBox")
	protected BoundingBox _boundingBox;
	@XmlElement(name = "Style")
	protected Style _style;
	@XmlElement(name = "Layer")
	protected List<Layer> _layer;
	@XmlAttribute
	protected Byte _queryable;
	@XmlAttribute
	protected Byte _opaque;
	@XmlAttribute
	protected Byte _cascaded;

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
	 * \brief Gets the value of the abstract property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAbstract() {
		return _abstract;
	}

	/**
	 * \brief Sets the value of the abstract property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAbstract(String value) {
		this._abstract = value;
	}

	/**
	 * \brief Gets the value of the keywordList property.
	 * 
	 * @return possible object is {@link KeywordList }
	 * 
	 */
	public KeywordList getKeywordList() {
		return _keywordList;
	}

	/**
	 * \brief Sets the value of the keywordList property.
	 * 
	 * @param value
	 *            allowed object is {@link KeywordList }
	 * 
	 */
	public void setKeywordList(KeywordList value) {
		this._keywordList = value;
	}

	/**
	 * \brief Gets the value of the srs property.
	 * 
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * 
	 * 
	 */
	public List<String> getSRS() {
		if (_srs == null) {
			_srs = new ArrayList<String>();
		}
		return this._srs;
	}

	/**
	 * \brief Gets the value of the latLonBoundingBox property.
	 * 
	 * @return possible object is {@link LatLonBoundingBox }
	 * 
	 */
	public LatLonBoundingBox getLatLonBoundingBox() {
		return _latLonBoundingBox;
	}

	/**
	 * \brief Sets the value of the latLonBoundingBox property.
	 * 
	 * @param value
	 *            allowed object is {@link LatLonBoundingBox }
	 * 
	 */
	public void setLatLonBoundingBox(LatLonBoundingBox value) {
		this._latLonBoundingBox = value;
	}

	/**
	 * \brief Gets the value of the boundingBox property.
	 * 
	 * @return possible object is {@link BoundingBox }
	 * 
	 */
	public BoundingBox getBoundingBox() {
		return _boundingBox;
	}

	/**
	 * \brief Sets the value of the boundingBox property.
	 * 
	 * @param value
	 *            allowed object is {@link BoundingBox }
	 * 
	 */
	public void setBoundingBox(BoundingBox value) {
		this._boundingBox = value;
	}

	/**
	 * \brief Gets the value of the style property
	 * 
	 * @return possible object is {@link Style }
	 * 
	 */
	public Style getStyle() {
		return _style;
	}

	/**
	 * \brief Sets the value of the style property.
	 * 
	 * @param value
	 *            allowed object is {@link Style }
	 * 
	 */
	public void setStyle(Style value) {
		this._style = value;
	}

	/**
	 * \brief Gets the value of the layer property.
	 * 
	 * Objects of the following type(s) are allowed in the list {@link Layer }
	 * 
	 * 
	 */
	public List<Layer> getLayer() {
		if (_layer == null) {
			_layer = new ArrayList<Layer>();
		}
		return this._layer;
	}

	/**
	 * \brief Gets the value of the queryable property.
	 * 
	 * @return possible object is {@link Byte }
	 * 
	 */
	public Byte getQueryable() {
		return _queryable;
	}

	/**
	 * \brief Sets the value of the queryable property.
	 * 
	 * @param value
	 *            allowed object is {@link Byte }
	 * 
	 */
	public void setQueryable(Byte value) {
		this._queryable = value;
	}

	/**
	 * \brief Gets the value of the opaque property.
	 * 
	 * @return possible object is {@link Byte }
	 * 
	 */
	public Byte getOpaque() {
		return _opaque;
	}

	/**
	 * \brief Sets the value of the opaque property.
	 * 
	 * @param value
	 *            allowed object is {@link Byte }
	 * 
	 */
	public void setOpaque(Byte value) {
		this._opaque = value;
	}

	/**
	 * \brief Gets the value of the cascaded property.
	 * 
	 * @return possible object is {@link Byte }
	 * 
	 */
	public Byte getCascaded() {
		return _cascaded;
	}

	/**
	 * \brief Sets the value of the cascaded property.
	 * 
	 * @param value
	 *            allowed object is {@link Byte }
	 * 
	 */
	public void setCascaded(Byte value) {
		this._cascaded = value;
	}

}
