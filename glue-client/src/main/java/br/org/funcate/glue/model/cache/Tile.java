package br.org.funcate.glue.model.cache;

import java.awt.image.BufferedImage;

/**
 * \brief Class to store the characteristics of a Tile. It's attributes include:
 * index X and Y, resolution, last minute accessed, number of times required,
 * and a reference to the loaded image (Tile).
 * 
 * @author Juliana & Vinicius
 */
public class Tile {
	/** < Attribute type BufferedImage > */
	private BufferedImage image;

	/** < Attribute type int > */
	private int accessedTimes;

	/** < Attribute type String > */
	private String lastMinuteAccessed;

	/** < Attribute type int > */
	private int indexX;

	/** < Attribute type int > */
	private int indexY;

	/** < Attribute type double > */
	private double resolution;

	/** < Attribute type TileType > */
	private TileType type;

	/** < Attribute type TileStatus > */
	private TileStatus loaded;

	/**
	 * @param tileImage
	 *            The reference to the object image (Tile)
	 * @param indexX
	 *            The name portion of the tile represented the index X
	 * @param indexY
	 *            The name portion of the tile represented the index Y
	 * @param resolution
	 *            The tile resolution
	 */
	public Tile(BufferedImage tileImage, int indexX, int indexY, double resolution, TileType type) {
		super();

		if (tileImage == null) {
			loaded = TileStatus.NOT_LOADED;
		} else {
			loaded = TileStatus.LOADED;
		}

		this.image = tileImage;
		this.indexX = indexX;
		this.indexY = indexY;
		this.resolution = resolution;
		this.type = type;
		this.lastMinuteAccessed = "00:00:00";
	}

	/**
	 * \brief Method to return the name part of the tile representing it's index
	 * X. Date.: 13/07/2010
	 * 
	 * @return Tile's index X.
	 */
	public int getIndexX() {
		return indexX;
	}

	/**
	 * \brief Method to return the name part of the tile representing its index
	 * Y. Date.: 13/07/2010
	 * 
	 * @return Tile's index Y.
	 */
	public int getIndexY() {
		return indexY;
	}

	/**
	 * \brief Method to return the tile resolution. Date.: 13/07/2010
	 * 
	 * @return Tile's resolution.
	 */
	public double getResolution() {
		return resolution;
	}

	/**
	 * \brief Method to return the object reference image
	 * 
	 * @return The object reference image
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * \brief Method to get the number of times requested by this object
	 * TileDefinition
	 * 
	 * @return the number of times required by this object TileDefinition
	 */
	public int getAccessedTimes() {
		return accessedTimes;
	}

	/**
	 * \brief Method to set the number of times the TileDefinition object was
	 * requested
	 * 
	 * @param accessedTimes
	 *            The number of times the TileDefinition object was requested
	 */
	public void setAccessedTimes(int accessedTimes) {
		this.accessedTimes = accessedTimes;
	}

	/**
	 * \brief Method to get the last time the object was requested
	 * 
	 * @return The last time the object was requested. Ex: 20:05:30
	 */
	public String getLastMinuteAccessed() {
		return lastMinuteAccessed;
	}

	/**
	 * \brief Method to set the last time the object was requested
	 * 
	 * @param lastAccess
	 *            The last time the object was requested
	 */
	public void setLastMinuteAccessed(String lastAccess) {
		this.lastMinuteAccessed = lastAccess;
	}

	/**
	 * \brief Method to compare the current object is identical to another
	 * TileDefinition object
	 * 
	 * @param tileDef
	 *            The TileDefinition object to be compared
	 * @return True - If they are equal False - If they aren't equal
	 */
	public boolean equals(Tile tileDef) {
		if (tileDef instanceof Tile) {
			if ((this.indexX == ((Tile) tileDef).indexX) && (this.indexY == ((Tile) tileDef).indexY)
					&& (this.resolution == ((Tile) tileDef).resolution) && (this.type == ((Tile) tileDef).type)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	public boolean equals(Object obj) {
		if (obj instanceof Tile) {
			return this.equals((Tile) obj);
		}
		return false;
	}

	/**
	 * \brief Method to generate a String representation of this TileDefinition
	 * object.
	 * 
	 * @see java.lang.Object
	 */
	public String toString() {
		return "IndexX.: " + indexX + "\nIndexY.: " + indexY + "\nResolution.: " + resolution + "\nLast Access.: " + lastMinuteAccessed
				+ "\nAcessed Times.: " + accessedTimes + "\nType.: " + type + "\nStatus.: " + loaded;
	}

	/**
	 * Method to generate a distinct hashcode for each object of this class.
	 * 
	 * @see java.lang.Object
	 */
	public int hashCode() {
		int hash = (indexX * indexY);
		String[] hour = lastMinuteAccessed.split(":");
		int[] numbers = new int[hour.length];

		for (int i = 0; i < hour.length; i++) {
			numbers[i] = Integer.parseInt(hour[i]);

			if (numbers[i] == 0) {
				continue;
			}

			hash *= numbers[i];
		}

		return hash;
	}

	/**
	 * \brief Method that checks if the Tile image is loaded. Date.: 13/07/2010
	 * 
	 * @return An image status. Can be: TileStatus.LOADING, TileStatus.LOADED,
	 *         TileStatus.NOT_LOADED
	 */

	public TileStatus imageIsLoaded() {
		return loaded;
	}

	/**
	 * \brief Method to set the image status. Date.: 13/07/2010
	 * 
	 * @param imageStatus
	 *            TileStatus.LOADING TileStatus. LOADED TileStatus.NOT_LOADED
	 */

	public void setImageStatus(TileStatus imageStatus) {
		this.loaded = imageStatus;
	}

	/**
	 * \brief Method to set the image reference. Date.: 13/07/2010
	 * 
	 * @param tileImage
	 *            Image reference of a Tile.
	 */
	public void setImage(BufferedImage tileImage) {
		this.image = tileImage;
	}

	/**
	 * \brief Method to return the Tile's type. Date.: 13/07/2010
	 * 
	 * @return The Tile's type.
	 */
	public TileType getType() {
		return type;
	}

	/**
	 * \brief Method to transfer the image of this object to another that does
	 * not. For correct operation, both objects should be considered identical,
	 * and the object which called it must have a picture. Date.: 13/07/2010
	 * 
	 * @param tdTarget
	 *            The TileDefinition object chosen to receive the image.
	 * @return True - Transfer successful! False - It was not possible to
	 *         transfer the image.
	 */
	public boolean transferImage(Tile tdTarget) {
		if (this.equals(tdTarget)) {
			if (this.loaded == TileStatus.LOADED && tdTarget.imageIsLoaded() == TileStatus.LOADING) {
				tdTarget.setImage(this.image);
				tdTarget.setImageStatus(TileStatus.LOADED);
				return true;
			}
		}

		return false;
	}

	public boolean equalsWithoutTileType(Tile tile) {
		if (tile instanceof Tile) {
			if ((this.indexX == ((Tile) tile).indexX) && (this.indexY == ((Tile) tile).indexY)
					&& (this.resolution == ((Tile) tile).resolution)) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

}
