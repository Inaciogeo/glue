package br.org.funcate.glue.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for viewToPlot complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="viewToPlot">
 *   &lt;complexContent>
 *     &lt;extension base="{http://terrajavaservices.glueservices.funcate.org.br/}object">
 *       &lt;sequence>
 *         &lt;element name="canvasBackgroundColor" type="{http://terrajavaservices.glueservices.funcate.org.br/}rgbColor" minOccurs="0"/>
 *         &lt;element name="projection" type="{http://terrajavaservices.glueservices.funcate.org.br/}projection" minOccurs="0"/>
 *         &lt;element name="_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_themes" type="{http://terrajavaservices.glueservices.funcate.org.br/}themeToPlot" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "viewToPlot", propOrder = { "canvasBackgroundColor", "projection", "name", "themes", "box" })
public class ViewToPlot extends Object {

	protected RgbColor canvasBackgroundColor;
	protected Projection projection;
	@XmlElement(name = "_name")
	protected String name;
	@XmlElement(name = "_themes", nillable = true)
	protected List<ThemeToPlot> themes;
    protected Box box;

	public ViewToPlot(View viewToPlot, Projection foregroundProjection) {
		this.setName(viewToPlot.getName());
		if (foregroundProjection != null) {
			this.setProjection(foregroundProjection);
		} else {
			this.setProjection(viewToPlot.getProjection());
		}
		if (viewToPlot.getThemes() == null) {
			return;
		} else {
			Vector<ThemeToPlot> themesVec = new Vector<ThemeToPlot>();
			for (int i = 0; i < viewToPlot.getThemes().size(); i++) {
				buildThemesToPlot(viewToPlot.getThemes().get(i), themesVec);
			}

			themes = new ArrayList<ThemeToPlot>(themesVec.size());
			for (int i = themesVec.size() - 1; i >= 0; i--) {
				themes.add((themesVec.size() - 1) - i, themesVec.elementAt(i));
			}
		}
	}

	public ViewToPlot() {
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */

	private void buildThemesToPlot(Theme theme2Plot, Vector<ThemeToPlot> themes) {

		if (theme2Plot.getType() != null && theme2Plot.isVisibility() != null && theme2Plot.isScaleVisibility() != null)
			if (theme2Plot.getType() == 0 && theme2Plot.isVisibility() && theme2Plot.isScaleVisibility()) {
				ThemeToPlot theme = new ThemeToPlot(theme2Plot.getName(), theme2Plot.getType(), theme2Plot.getThemeVisuals(), theme2Plot.getId());
				theme.setGroup(theme2Plot.isGroup());
				themes.add(theme);

			} else if (theme2Plot.getType() == 1 && theme2Plot.getChildThemes() != null) {

				for (int i = 0; i < theme2Plot.getChildThemes().size(); i++) {
					buildThemesToPlot(theme2Plot.getChildThemes().get(i), themes);
				}
			}
		return;
	}

	/**
	 * Gets the value of the canvasBackgroundColor property.
	 * 
	 * @return possible object is {@link RgbColor }
	 * 
	 */
	public RgbColor getCanvasBackgroundColor() {
		return canvasBackgroundColor;
	}

	/**
	 * Sets the value of the canvasBackgroundColor property.
	 * 
	 * @param value
	 *            allowed object is {@link RgbColor }
	 * 
	 */
	public void setCanvasBackgroundColor(RgbColor value) {
		this.canvasBackgroundColor = value;
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
	 * Objects of the following type(s) are allowed in the list
	 * {@link ThemeToPlot }
	 * 
	 * 
	 */
	public List<ThemeToPlot> getThemes() {
		if (themes == null) {
			themes = new ArrayList<ThemeToPlot>();
		}
		return this.themes;
	}
	
    /**
     * Gets the value of the box property.
     * 
     * @return
     *     possible object is
     *     {@link Box }
     *     
     */
    public Box getBox() {
        return box;
    }

    /**
     * Sets the value of the box property.
     * 
     * @param value
     *     allowed object is
     *     {@link Box }
     *     
     */
    public void setBox(Box value) {
        this.box = value;
    }

}
