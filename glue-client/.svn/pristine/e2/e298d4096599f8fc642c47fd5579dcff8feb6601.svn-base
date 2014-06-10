package br.org.funcate.glue.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for theme complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="theme">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reps" type="{http://terrajavaservices.glueservices.funcate.org.br/}representation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="themeBox" type="{http://terrajavaservices.glueservices.funcate.org.br/}box" minOccurs="0"/>
 *         &lt;element name="_childThemes" type="{http://terrajavaservices.glueservices.funcate.org.br/}theme" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="_id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_index" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="_isScalelimitChanged" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="_isVisualChanged" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="_layer" type="{http://terrajavaservices.glueservices.funcate.org.br/}layer" minOccurs="0"/>
 *         &lt;element name="_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_parentID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_scaleLimit" type="{http://terrajavaservices.glueservices.funcate.org.br/}scaleLimit" minOccurs="0"/>
 *         &lt;element name="_scaleVisibility" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="_themeVisuals" type="{http://terrajavaservices.glueservices.funcate.org.br/}themeVisual" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="_type" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="_visibility" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "theme", propOrder = { "reps", "themeBox", "childThemes", "id", "index", "isScalelimitChanged", "isVisualChanged", "layer",
		"name", "parentID", "scaleLimit", "scaleVisibility", "themeVisuals", "type", "visibility", "group" })
public class Theme {

	@XmlElement(nillable = true)
	protected List<Representation> reps;
	protected Box themeBox;
	@XmlElement(name = "_childThemes", nillable = true)
	protected List<Theme> childThemes;
	@XmlElement(name = "_id")
	protected Long id;
	@XmlElement(name = "_index")
	protected int index;
	@XmlElement(name = "_isScalelimitChanged")
	protected Boolean isScalelimitChanged;
	@XmlElement(name = "_isVisualChanged")
	protected Boolean isVisualChanged;
	@XmlElement(name = "_layer")
	protected Layer layer;
	@XmlElement(name = "_name")
	protected String name;
	@XmlElement(name = "_parentID")
	protected Long parentID;
	@XmlElement(name = "_scaleLimit")
	protected ScaleLimit scaleLimit;
	@XmlElement(name = "_scaleVisibility")
	protected Boolean scaleVisibility;
	@XmlElement(name = "_themeVisuals", nillable = true)
	protected List<ThemeVisual> themeVisuals;
	@XmlElement(name = "_type")
	protected Integer type;
	@XmlElement(name = "_visibility")
	protected Boolean visibility;
	@XmlElement(name = "group")
	protected boolean group;

	/**
	 * Gets the value of the reps property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the reps property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getReps().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link Representation }
	 * 
	 * 
	 */
	public List<Representation> getReps() {
		if (reps == null) {
			reps = new ArrayList<Representation>();
		}
		return this.reps;
	}

	/**
	 * \brief Function that modifies the representations of the theme
	 * 
	 * @param reps
	 *            A vector containing the new representations of this theme
	 */
	public void setReps(List<Representation> reps) {
		this.reps = reps;
	}

	/**
	 * Gets the value of the themeBox property.
	 * 
	 * @return possible object is {@link Box }
	 * 
	 */
	public Box getThemeBox() {
		return themeBox;
	}

	/**
	 * Sets the value of the themeBox property.
	 * 
	 * @param value
	 *            allowed object is {@link Box }
	 * 
	 */
	public void setThemeBox(Box value) {
		this.themeBox = value;
	}

	/**
	 * Gets the value of the childThemes property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the childThemes property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getChildThemes().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Theme }
	 * 
	 * 
	 */
	public List<Theme> getChildThemes() {
		if (childThemes == null) {
			childThemes = new ArrayList<Theme>();
		}
		return this.childThemes;
	}

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setId(Long value) {
		this.id = value;
	}

	/**
	 * Gets the value of the index property.
	 * 
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the value of the index property.
	 * 
	 */
	public void setIndex(int value) {
		this.index = value;
	}

	/**
	 * Gets the value of the isScalelimitChanged property.
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isIsScalelimitChanged() {
		return isScalelimitChanged;
	}

	/**
	 * Sets the value of the isScalelimitChanged property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setIsScalelimitChanged(Boolean value) {
		this.isScalelimitChanged = value;
	}

	/**
	 * Gets the value of the isVisualChanged property.
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isIsVisualChanged() {
		return isVisualChanged;
	}

	/**
	 * Sets the value of the isVisualChanged property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setIsVisualChanged(Boolean value) {
		this.isVisualChanged = value;
	}

	/**
	 * Gets the value of the layer property.
	 * 
	 * @return possible object is {@link Layer }
	 * 
	 */
	public Layer getLayer() {
		return layer;
	}

	/**
	 * Sets the value of the layer property.
	 * 
	 * @param value
	 *            allowed object is {@link Layer }
	 * 
	 */
	public void setLayer(Layer value) {
		this.layer = value;
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the value of the parentID property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getParentID() {
		return parentID;
	}

	/**
	 * Sets the value of the parentID property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setParentID(Long value) {
		this.parentID = value;
	}

	/**
	 * Gets the value of the scaleLimit property.
	 * 
	 * @return possible object is {@link ScaleLimit }
	 * 
	 */
	public ScaleLimit getScaleLimit() {
		return scaleLimit;
	}

	/**
	 * Sets the value of the scaleLimit property.
	 * 
	 * @param value
	 *            allowed object is {@link ScaleLimit }
	 * 
	 */
	public void setScaleLimit(ScaleLimit value) {
		this.scaleLimit = value;
	}

	/**
	 * Gets the value of the scaleVisibility property.
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isScaleVisibility() {
		return scaleVisibility;
	}

	/**
	 * Sets the value of the scaleVisibility property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setScaleVisibility(Boolean value) {
		this.scaleVisibility = value;
	}

	/**
	 * Gets the value of the themeVisuals property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the themeVisuals property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getThemeVisuals().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link ThemeVisual }
	 * 
	 * 
	 */
	public List<ThemeVisual> getThemeVisuals() {
		if (themeVisuals == null) {
			themeVisuals = new ArrayList<ThemeVisual>();
		}
		return this.themeVisuals;
	}

	/**
	 * \brief Function that modifies the visuals of the theme
	 * 
	 * @param themeVisuals
	 *            A vector containing the new visuals of the theme
	 */
	public void setThemeVisuals(List<ThemeVisual> themeVisuals) {
		this.themeVisuals = themeVisuals;
	}

	/**
	 * Gets the value of the type property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setType(Integer value) {
		this.type = value;
	}

	/**
	 * Gets the value of the visibility property.
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isVisibility() {
		return visibility;
	}

	/**
	 * Sets the value of the visibility property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setVisibility(Boolean value) {
		this.visibility = value;
	}

	/**
	 * @return the group
	 */
	public boolean isGroup() {
		return group;
	}
	
	/**
	 * \brief Function that modifies the child themes of the theme
	 * 
	 * @param themes
	 *            A vector containing the new subthemes of the theme
	 */

	public void setChildThemes(List<Theme> themes) {
		childThemes = themes;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(boolean group) {
		this.group = group;
	}

	/**
	 * \brief Function that instantiates a new theme with all values equal to
	 * this theme
	 * 
	 * @return A copied theme
	 */
	public Theme clone() {
		Theme _copiedTheme = new Theme();

		_copiedTheme.setId(getId());
		_copiedTheme.setName(getName());
		_copiedTheme.setParentID(getParentID());
		_copiedTheme.setScaleLimit(getScaleLimit());
		_copiedTheme.setThemeBox(getThemeBox());
		_copiedTheme.setLayer(getLayer());
		_copiedTheme.setType(getType());
		_copiedTheme.setReps(getReps());
		_copiedTheme.setThemeVisuals(getThemeVisuals());
		_copiedTheme.setChildThemes(getChildThemes());
		_copiedTheme.setIndex(getIndex());
		_copiedTheme.setVisibility(isVisibility());
		_copiedTheme.setScaleVisibility(isScaleVisibility());
		_copiedTheme.setIsScalelimitChanged(isIsScalelimitChanged());
		_copiedTheme.setIsVisualChanged(isIsVisualChanged());

		return _copiedTheme;
	}


	@Override
	public String toString() {
		return name;
	}
}
