package br.org.funcate.glue.model.canvas;

import br.org.funcate.glue.model.Projection;
import br.org.funcate.glue.model.cache.TileType;

/**
 * 
 * @class ImageSourceDefinition
 * 
 * @brief This class contains the specifications for a particular type of map
 *        source to be visualized, which can be Google, database or WMS.
 * 
 * @version 1.0
 * 
 * @date 20/08/2010
 * 
 * @author Henrique and Ricardo
 * 
 */
public class ImageSourceDefinition {

	/**
	 * horizontal coordinate of the lower left corner of the initial box
	 */
	private double x1;
	/**
	 * vertical coordinate of the lower left corner of the initial box
	 */
	private double y1;
	/**
	 * horizontal coordinate of the upper right corner of the initial box
	 */
	private double x2;
	/**
	 * vertical coordinate of the upper right corner of the initial box
	 */
	private double y2;
	/**
	 * Contains needed information to request images from the respective source
	 */
	private java.lang.Object source;
	/**
	 * The type of image source which can be Google, database or WMS
	 */
	private TileType type;
	/**
	 * The projection of this image source. Needed to box calculations.
	 */
	private Projection projection;
	/**
	 * The name of image source.
	 */
	private String sourceName;

	/**
	 * @name Constructor The constructor of ImageSourceDefinition class
	 * @param x1
	 *            horizontal coordinate of the lower left corner of the initial
	 *            box
	 * @param y1
	 *            vertical coordinate of the lower left corner of the initial
	 *            box
	 * @param x2
	 *            horizontal coordinate of the upper right corner of the initial
	 *            box
	 * @param y2
	 *            vertical coordinate of the upper right corner of the initial
	 *            box
	 * @param pSource
	 *            Contains needed information to request images from the
	 *            respective source
	 * @param type
	 *            The type of image source which can be Google, TerraLib
	 *            database or WMS
	 */
	public ImageSourceDefinition(double x1, double y1, double x2, double y2, java.lang.Object pSource, TileType type,
			Projection projection, String sourceName) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.source = pSource;
		this.type = type;
		this.projection = projection;
		this.sourceName = sourceName;
	}

	/**
	 * It returns the horizontal coordinate of the lower left corner of the
	 * initial box
	 */
	public double getX1() {
		return x1;
	}

	/**
	 * It returns the vertical coordinate of the lower left corner of the
	 * initial box
	 */
	public double getY1() {
		return y1;
	}

	/**
	 * It returns the horizontal coordinate of the upper right corner of the
	 * initial box
	 */
	public double getX2() {
		return x2;
	}

	/**
	 * It returns the vertical coordinate of the upper right corner of the
	 * initial box
	 */
	public double getY2() {
		return y2;
	}

	/**
	 * It returns the image source object that contains need informations for
	 * requests
	 */
	public java.lang.Object getSource() {
		return source;
	}

	/**
	 * It returns the type of image source which can be Google, database or WMS
	 */
	public TileType getType() {
		return type;
	}

	/**
	 * It sets the image source object that contains need informations for
	 * requests
	 */
	public void setSource(Object source) {
		this.source = source;
	}

	/**
	 * It returns the image source projection
	 */
	public Projection getProjection() {
		return projection;
	}

	/**
	 * It sets the image source projection
	 */
	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	/**
	 * It sets the name of image source
	 */
	public String getSourceName() {
		return sourceName;
	}

	/**
	 * It returns the name of image source
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}

	public void setType(TileType type) {
		this.type = type;
	}
	
	
}
