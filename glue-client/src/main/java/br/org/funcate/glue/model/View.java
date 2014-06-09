package br.org.funcate.glue.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for view complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="view">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_box" type="{http://terrajavaservices.glueservices.funcate.org.br/}box" minOccurs="0"/>
 *         &lt;element name="_id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_projection" type="{http://terrajavaservices.glueservices.funcate.org.br/}projection" minOccurs="0"/>
 *         &lt;element name="_themes" type="{http://terrajavaservices.glueservices.funcate.org.br/}theme" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="_viewToPlot" type="{http://terrajavaservices.glueservices.funcate.org.br/}viewToPlot" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "view", propOrder = { "box", "id", "name", "projection", "foregroundProjection", "themes", "viewToPlot" })
public class View {

	@XmlElement(name = "_box")
	protected Box box;
	@XmlElement(name = "_id")
	protected Long id;
	@XmlElement(name = "_name")
	protected String name;
	@XmlElement(name = "_projection")
	protected Projection projection;
	@XmlElement(name = "foregroundProjection")
	protected Projection foregroundProjection;
	@XmlElement(name = "_themes", nillable = true)
	protected List<Theme> themes;
	@XmlElement(name = "_viewToPlot")
	protected ViewToPlot viewToPlot;

	/**
	 * @name Constructors
	 * 
	 *       The functions that create the View
	 */

	// @ {

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M. and Rocha, Fernanda.
	 * 
	 *         \brief A constructor that defines how many themes will the view
	 *         have
	 * 
	 * @param pSize
	 *            The number of themes that the view will have
	 */

	public View(int pSize) {
		setThemes(new ArrayList<Theme>(pSize));
	}

	/**
	 * \brief An empty constructor used to simple instantiate the object
	 * 
	 * @return A new view
	 */

	public View() {

	}

	// @ }
	public void buildViewToPlot() {
		viewToPlot = new ViewToPlot(this, foregroundProjection);
	}

	/**
	 * Gets the value of the box property.
	 * 
	 * @return possible object is {@link Box }
	 * 
	 */
	public Box getBox() {
		return box;
	}

	/**
	 * Sets the value of the box property.
	 * 
	 * @param value
	 *            allowed object is {@link Box }
	 * 
	 */
	public void setBox(Box value) {
		this.box = value;
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
	 * Gets the value of the projection property.
	 * 
	 * @return possible object is {@link Projection }
	 * 
	 */
	public Projection getProjection() {
		return projection;
	}

	/**
	 * Sets the value of the projection property.
	 * 
	 * @param value
	 *            allowed object is {@link Projection }
	 * 
	 */
	public void setProjection(Projection value) {
		this.projection = value;
	}

	/**
	 * Gets the value of the themes property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the themes property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getThemes().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Theme }
	 * 
	 * 
	 */
	public List<Theme> getThemes() {
		if (themes == null) {
			themes = new ArrayList<Theme>();
		}
		return this.themes;
	}

	/**
	 * Gets the value of the viewToPlot property.
	 * 
	 * @return possible object is {@link ViewToPlot }
	 * 
	 */
	public ViewToPlot getViewToPlot() {
		return viewToPlot;
	}

	/**
	 * Sets the value of the viewToPlot property.
	 * 
	 * @param value
	 *            allowed object is {@link ViewToPlot }
	 * 
	 */
	public void setViewToPlot(ViewToPlot value) {
		this.viewToPlot = value;
	}

	public void setThemes(List<Theme> themes) {
		this.themes = themes;

	}

	public boolean equals(View otherView) {
		boolean nameChanged = otherView.getName().equals(this.name);
		boolean themesListChanged = this.themes.equals(otherView.themes);
		return nameChanged && themesListChanged;
	}

	public Double getMinScale() {

		Double minScale = 15000000.0;

		for (Theme theme : this.themes) {
			if (theme.getScaleLimit().getMin() < minScale) {
				minScale = theme.getScaleLimit().getMin();
			}
		}

		return minScale;
	}

	public Double getMaxScale() {

		Double maxScale = 10.0;

		for (Theme theme : this.themes) {
			if (theme.getScaleLimit().getMax() > maxScale) {
				maxScale = theme.getScaleLimit().getMax();
			}
		}

		return maxScale;
	}

	public boolean hasTheme(Theme theme) {
		for (Theme recTheme : themes) {
			if (theme.equals(recTheme)) {
				return true;
			}
		}

		return false;
	}

	public boolean hasTheme(String themeName) {
		for (Theme recTheme : themes) {
			if (themeName.equals(recTheme.getName())) {
				return true;
			}
		}

		return false;
	}

	public Theme getThemeByName(String themeName) {
		for (Theme recTheme : themes) {
			if (themeName.equals(recTheme.getName())) {
				return recTheme;
			}
		}

		return null;
	}

	public Projection getForegroundProjection() {
		return foregroundProjection;
	}

	public void setForegroundProjection(Projection foregroundProjection) {
		this.foregroundProjection = foregroundProjection;
	}

	@Override
	public String toString() {
		return name;
	}

}
