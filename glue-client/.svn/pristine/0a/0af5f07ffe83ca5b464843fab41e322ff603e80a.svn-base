
package br.org.funcate.glue.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for layer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="layer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_AttributeTables" type="{http://terrajavaservices.glueservices.funcate.org.br/}attributeTable" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="_id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="_projection" type="{http://terrajavaservices.glueservices.funcate.org.br/}projection" minOccurs="0"/>
 *         &lt;element name="_representations" type="{http://terrajavaservices.glueservices.funcate.org.br/}representation" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "layer", propOrder = {
    "attributeTables",
    "id",
    "name",
    "projection",
    "representations"
})
public class Layer {

    @XmlElement(name = "_AttributeTables", nillable = true)
    protected List<AttributeTable> attributeTables;
    @XmlElement(name = "_id")
    protected Integer id;
    @XmlElement(name = "_name")
    protected String name;
    @XmlElement(name = "_projection")
    protected Projection projection;
    @XmlElement(name = "_representations", nillable = true)
    protected List<Representation> representations;


	/**
     * Gets the value of the attributeTables property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributeTables property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeTables().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttributeTable }
     * 
     * 
     */
    public List<AttributeTable> getAttributeTables() {
        if (attributeTables == null) {
            attributeTables = new ArrayList<AttributeTable>();
        }
        return this.attributeTables;
    }
    
	/**
	 * \brief Method that set attributes of tables in layer
	 * 
	 * @param attributeTables
	 *            Sets the list of Tables in layer
	 */
	public void setAttributeTables(ArrayList<AttributeTable> attributeTables) {
		this.attributeTables = attributeTables;
	}

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
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
     * Gets the value of the projection property.
     * 
     * @return
     *     possible object is
     *     {@link Projection }
     *     
     */
    public Projection getProjection() {
        return projection;
    }

    /**
     * Sets the value of the projection property.
     * 
     * @param value
     *     allowed object is
     *     {@link Projection }
     *     
     */
    public void setProjection(Projection value) {
        this.projection = value;
    }

    /**
     * Gets the value of the representations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the representations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRepresentations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Representation }
     * 
     * 
     */
    public List<Representation> getRepresentations() {
        if (representations == null) {
            representations = new ArrayList<Representation>();
        }
        return this.representations;
    }
    
    /**
     * Sets the value of the representations property.
     * 
     * @param value
     *     list of representation {@link Representation }
     *     
     */
    public void setRepresentations(List<Representation> representations) {
		this.representations = representations;
	}

}
