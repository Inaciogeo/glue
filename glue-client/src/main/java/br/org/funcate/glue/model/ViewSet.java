package br.org.funcate.glue.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for viewSet complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="viewSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_layers" type="{http://terrajavaservices.glueservices.funcate.org.br/}layer" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="_views" type="{http://terrajavaservices.glueservices.funcate.org.br/}view" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "viewSet", propOrder = { "layers", "views" })
public class ViewSet {

	@XmlElement(name = "_layers", nillable = true)
	protected List<Layer> layers;
	@XmlElement(name = "_views", nillable = true)
	protected List<View> views;
	
	/**
	 * @name Constructors
	 * 
	 *       The functions that create the View
	 */

	// @ {

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M. and Rocha, Fernanda.
	 * 
	 *         \brief A constructor that defines how many views will the ViewSet
	 *         have
	 * @param pSize
	 *            The number of views that the ViewSet will have
	 */

	public ViewSet(int pSize) {

		setViews(new ArrayList<View>(pSize));

	}

	/**
	 * \brief An empty constructor used to simple instantiate the object
	 * 
	 * @return A new ViewSet
	 */

	public ViewSet() {

	}

	// @ }

	/**
	 * Gets the value of the layers property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the layers property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getLayers().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Layer }
	 * 
	 * 
	 */
	public List<Layer> getLayers() {
		if (layers == null) {
			layers = new ArrayList<Layer>();
		}
		return this.layers;
	}

	/**
	 * Gets the value of the views property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the views property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getViews().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link View }
	 * 
	 * 
	 */
	public List<View> getViews() {
		if (views == null) {
			views = new ArrayList<View>();
		}
		return this.views;
	}
	
	/**
	 * @name Set
	 * 
	 *       Functions that modify the state of the attributes
	 */

	// @ {

	/**
	 * \brief Function that modify the vector of layers of the ViewSet
	 * 
	 * @param layers
	 *            A vector containing the new layers of the ViewSet
	 */

	public void setLayers(List<Layer> layers) {
		this.layers = layers;
	}
	
	/**
	 * \brief Function that modify the vector of views of the ViewSet
	 * 
	 * @param layers
	 *            A vector containing the new views of the ViewSet
	 */

	public void setViews(List<View> views) {
		this.views = views;
	}

}
