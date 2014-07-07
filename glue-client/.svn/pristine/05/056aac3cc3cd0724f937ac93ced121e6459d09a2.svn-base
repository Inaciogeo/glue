
package br.org.funcate.glue.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for themeToPlot complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="themeToPlot">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="group" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="labelConfig" type="{http://terrajavaservices.glueservices.funcate.org.br/}labelConfig" minOccurs="0"/>
 *         &lt;element name="objectsToDraw" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="themeId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="themeVisuals" type="{http://terrajavaservices.glueservices.funcate.org.br/}themeVisual" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_type" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "themeToPlot", propOrder = {
    "group",
    "label",
    "labelConfig",
    "objectsToDraw",
    "themeId",
    "themeVisuals",
    "name",
    "type"
})
public class ThemeToPlot {

    protected boolean group;
    protected boolean label;
    protected LabelConfig labelConfig;
    @XmlElement(nillable = true)
    protected List<String> objectsToDraw;
    protected Long themeId;
    @XmlElement(nillable = true)
    protected List<ThemeVisual> themeVisuals;
    @XmlElement(name = "_name")
    protected String name;
    @XmlElement(name = "_type")
    protected Integer type;
    
  
    public ThemeToPlot() {
		super();
	}

	public ThemeToPlot(String name, Integer type,List<ThemeVisual> themeVisuals, Long themeId) {
		this.name = name;
		this.type = type;
		this.themeVisuals = themeVisuals;
		this.themeId = themeId;
	}

	public ThemeToPlot(String name, Integer type, Long themeId) {
		this.name = name;
		this.type = type;
		this.themeId = themeId;
	}

	/**
     * Gets the value of the group property.
     * 
     */
    public boolean isGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     */
    public void setGroup(boolean value) {
        this.group = value;
    }

    /**
     * Gets the value of the label property.
     * 
     */
    public boolean isLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     */
    public void setLabel(boolean value) {
        this.label = value;
    }

    /**
     * Gets the value of the labelConfig property.
     * 
     * @return
     *     possible object is
     *     {@link LabelConfig }
     *     
     */
    public LabelConfig getLabelConfig() {
        return labelConfig;
    }

    /**
     * Sets the value of the labelConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link LabelConfig }
     *     
     */
    public void setLabelConfig(LabelConfig value) {
        this.labelConfig = value;
    }

    /**
     * Gets the value of the objectsToDraw property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectsToDraw property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectsToDraw().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    
    
    public List<String> getObjectsToDraw() {
        if (objectsToDraw == null) {
            objectsToDraw = new ArrayList<String>();
        }
        return this.objectsToDraw;
    }

    public void setObjectsToDraw(List<String> objectsToDraw) {
		this.objectsToDraw = objectsToDraw;
	}

	/**
     * Gets the value of the themeId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getThemeId() {
        return themeId;
    }

    /**
     * Sets the value of the themeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setThemeId(Long value) {
        this.themeId = value;
    }

    /**
     * Gets the value of the themeVisuals property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the themeVisuals property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getThemeVisuals().add(newItem);
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
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setType(Integer value) {
        this.type = value;
    }

}
