package br.org.funcate.glue.model;

import java.io.Serializable;

/**
 * @author Siqueira, Felipe V. and Ribeiro, Stephen M. and Rocha, Fernanda.
 * 
 *        \brief Class that represents a View Projection
 */
public class Projection implements Serializable {

	private static final long serialVersionUID = -986063926310517926L;
	/** < Attribute type long > */

	/**
	 * Conversion factor: degrees to radians
	 */
	public static final Double TECDR = 0.01745329251994329576;
	/**
	 * Default UTM X offset
	 */
	public static final String UTMOFFSETX = "500000.000";
	/**
	 * Default UTM Y offset
	 */
	public static final String UTMOFFSETY = "10000000.000";
	/**
	 * Projection Name
	 */
	private String name;
	/**
	 * Projection Ellipsoid
	 */
	private String datum;
	/**
	 * Projection unit
	 */
	private String units;
	/**
	 * Projection UTM Zone
	 */
	private Integer zone;
	/**
	 * Projection Latitude
	 */
	private Double lat0;
	/**
	 * Projection Longitude
	 */
	private Double lon0;
	/**
	 * Projection Default Parallel 1
	 */
	private Double stlat1;
	/**
	 * Projection Default Parallel 2
	 */
	private Double stlat2;
	/**
	 * Projection Scale
	 */
	private Double scale;
	/**
	 * Projection Offset X
	 */
	private String offx;
	/**
	 * Projection Offset Y
	 */
	private String offy;
	/**
	 * Is North Hemisphere
	 */
	private Boolean hemNorth;
	/**
	 * Is selected
	 */
	private Boolean selected;

	/**
	 * @name Access
	 * 
	 *       Functions that return the state of the attributes of the projection
	 */

	// @ {

	/**
	 * \brief Function that returns selection state of the projection
	 * 
	 * @return The selection state of the projection
	 */

	public Boolean getSelected() {
		return selected;
	}

	/**
	 * \brief Function that returns the name of the projection
	 * 
	 * @return The name of the projection
	 */

	public String getName() {
		return name;
	}

	/**
	 * \brief Function that returns the datum of the projection
	 * 
	 * @return The datum of the projection
	 */

	public String getDatum() {
		return datum;
	}

	/**
	 * \brief Function that returns the units of the projection
	 * 
	 * @return The units of the projection
	 */

	public String getUnits() {
		return units;
	}

	/**
	 * \brief Function that returns the scale of the projection
	 * 
	 * @return The scale of the projection
	 */

	public Double getScale() {
		return scale;
	}

	/**
	 * \brief Function that returns the lat0 value of the projection
	 * 
	 * @return The latitude 0 value of the projection
	 */

	public Double getLat0() {
		return lat0;
	}

	/**
	 * \brief Function that returns the Longitude 0 value of the projection
	 * 
	 * @return The units of the projection
	 */

	public Double getLon0() {
		return lon0;
	}

	/**
	 * \brief Function that returns the default parallel 1 of the projection
	 * 
	 * @return The default parallel 1 of the projection
	 */

	public Double getStlat1() {
		return stlat1;
	}

	/**
	 * \brief Function that returns the default parallel 2 of the projection
	 * 
	 * @return The dafault parallel 2 of the projection
	 */

	public Double getStlat2() {
		return stlat2;
	}

	/**
	 * \brief Function that returns the offset x value of the projection
	 * 
	 * @return The offset x of the projection
	 */

	public String getOffx() {
		return offx;
	}

	/**
	 * \brief Function that returns the offset y of the projection
	 * 
	 * @return The offset y of the projection
	 */

	public String getOffy() {
		return offy;
	}

	/**
	 * \brief Function that returns a boolean that indicates if this projection
	 * is referred to the north hemisphere
	 * 
	 * @return A boolean that indicates if this projection is referred to the
	 *         north hemisphere
	 */

	public Boolean getHemNorth() {
		return hemNorth;
	}

	/**
	 * \brief Function that returns the zone of the projection
	 * 
	 * @return The zone of the projection
	 */

	public Integer getZone() {
		return zone;
	}

	// @ }

	/**
	 * @name Set
	 * 
	 *       Functions that modify the state of the attributes of the Layer
	 */

	// @ {

	/**
	 * \brief Function that modifies the selection state of the projection
	 * 
	 * @param selected
	 *            A boolean that indicates if the projection is selected
	 */

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	/**
	 * \brief Function that modifies the name of the projection
	 * 
	 * @param name
	 *            The new name of the projection
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * \brief Function that modifies the datum of the projection
	 * 
	 * @param datum
	 *            The new datum of the projection
	 */

	public void setDatum(String datum) {
		this.datum = datum;
	}

	/**
	 * \brief Function that modifies the units of the projection
	 * 
	 * @param units
	 *            The new units of the projection
	 */

	public void setUnits(String units) {
		this.units = units;
	}

	/**
	 * \brief Function that modifies the latitude 0 value of the projection
	 * 
	 * @param lat0
	 *            The new latitude 0 value of the projection
	 */

	public void setLat0(Double lat0) {
		this.lat0 = lat0;
	}

	/**
	 * \brief Function that modifies the longitude 0 of the projection
	 * 
	 * @param lon0
	 *            The new longitude 0 of the projection
	 */

	public void setLon0(Double lon0) {
		this.lon0 = lon0;
	}

	/**
	 * \brief Function that modifies the default parallel 1 of the projection
	 * 
	 * @param stLat1
	 *            The new default parallel 1 of the projection
	 */

	public void setStlat1(Double stlat1) {
		this.stlat1 = stlat1;
	}

	/**
	 * \brief Function that modifies the default parallel 2 of the projection
	 * 
	 * @param stLat2
	 *            The new default parallel 2 of the projection
	 */

	public void setStlat2(Double stlat2) {
		this.stlat2 = stlat2;
	}

	/**
	 * \brief Function that modifies the scale of the projection
	 * 
	 * @param stLat
	 *            1 The new scale of the projection
	 */

	public void setScale(Double scale) {
		this.scale = scale;
	}

	/**
	 * \brief Function that modifies the offset X of the projection
	 * 
	 * @param offx
	 *            The new offset x of the projection
	 */

	public void setOffx(String offx) {
		this.offx = offx;
	}

	/**
	 * \brief Function that modifies the offset y of the projection
	 * 
	 * @param offy
	 *            The new offset y of the projection
	 */

	public void setOffy(String offy) {
		this.offy = offy;
	}

	/**
	 * \brief Function that modifies the boolean that indicates if this
	 * projection is referred to the north hemisphere
	 * 
	 * @param hemNorth
	 *            The boolean that indicates if this projection is referred to
	 *            the north hemisphere
	 */

	public void setHemNorth(Boolean hemNorth) {
		this.hemNorth = hemNorth;
	}

	/**
	 * \brief Function that modifies the zoneof the projection
	 * 
	 * @param offy
	 *            The new zone of the projection
	 */

	public void setZone(Integer zone) {
		this.zone = zone;
	}

	// @ }

	/**
	 * @name Initialization Functions that initialize the variables
	 */

	// @ {

	/**
	 * \brief Function that initialize the variables of the projection
	 * 
	 */

	public void initiateValues() {
		name = (name == null ? "" : name);
		datum = (datum == null ? "" : datum);
		units = (units == null ? "" : units);
		lat0 = (lat0 == null ? 0. : lat0);
		lon0 = (lon0 == null ? 0. : lon0);
		stlat1 = (stlat1 == null ? 0. : stlat1);
		stlat2 = (stlat2 == null ? 0. : stlat2);
		scale = (scale == null ? 0. : scale);
		offx = (offx == null ? "0" : offx);
		offy = (offy == null ? "0" : offy);
		hemNorth = (hemNorth == null ? false : hemNorth);
	}

	// @ }

}
