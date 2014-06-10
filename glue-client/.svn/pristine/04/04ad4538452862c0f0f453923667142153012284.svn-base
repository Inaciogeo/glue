package br.org.funcate.glue.model.cache;

import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

import br.org.funcate.glue.utilities.Clock;

/**
 * \brief Class responsible for storing Tile objects coming from the server. Has
 * an organized storage and cleaning structure, where Tile objects most
 * requested stay longer than the others.
 * 
 * @author Juliana & Vinícius
 * @version 1.0.0
 */

public class TileCache {

	/** < Attribute type TreeSet<Tile> > */
	private TreeSet<Tile> _cache;

	/** < Attribute type int > */
	private final int _MAX_TILE;

	/** < Attribute type Comparator > */
	private TileComparator _structureOrganizer;

	/**
	 * \brief Constructor that configure the structure capacity of the TileCache
	 * object that'll be created.
	 * 
	 * @param maxT
	 *            Maximum Amount of Tile hence the cache size.
	 */
	public TileCache(int maxT) {
		_MAX_TILE = maxT;
		_structureOrganizer = new TileComparator();
		_cache = new TreeSet<Tile>(_structureOrganizer);
	}

	/**
	 * \brief Method to try insert a new Tile in the cache.
	 * 
	 * @param tileDef
	 *            Target Tile to be inserted
	 */
	public boolean insertCandidate(Tile tileDef) {
		tileDef.setLastMinuteAccessed(Clock.getCurrentTime());

		synchronized (_cache) {
			for (Tile td : _cache) {
				if (tileDef.equals(td) == true) {
					return tileDef.transferImage(td);

				}
			}

			if (_cache.size() >= _MAX_TILE) {
				_cache.pollFirst();
			}
			return _cache.add(tileDef);
		}
	}

	/**
	 * \brief Method for seeking a specific Tile through its features. If Tile
	 * with these characteristics do not exist, then it is created and inserted.
	 * Date.: 13/07/2010
	 * 
	 * @param indexX
	 *            The name portion of the tile represented the index X.
	 * @param indexY
	 *            The name portion of the tile represented the index Y.
	 * @param resolution
	 *            The tile resolution
	 * @return A Tile object.
	 */
	public Tile getTile(int indexX, int indexY, double resolution, TileType type) {
		Tile tileDef = new Tile(null, indexX, indexY, resolution, type);

		synchronized (_cache) {
			for (Tile td : _cache) {
				if (tileDef.equals(td)) {
					td.setAccessedTimes(tileDef.getAccessedTimes() + 1);
					td.setLastMinuteAccessed(Clock.getCurrentTime());
					return td;
				}
			}
		}

		insertCandidate(tileDef);

		return tileDef;
	}

	/**
	 * \brief Method that returns the number of Tile objects stored. Date.:
	 * 13/07/2010
	 * 
	 * @return The number of Tile objects stored.
	 */
	public int getRetrievedTiles() {
		synchronized (_cache) {
			return _cache.size();
		}
	}

	/**
	 * \brief Method to return the Comparator object responsible for the
	 * ordering of the structure. Date.: 13/07/2010
	 * 
	 * @return The Comparator object.
	 */
	@SuppressWarnings("rawtypes")
	public Comparator getTileCompar() {
		return _structureOrganizer;
	}

	/**
	 * \brief Method to make reverse current form of organization. Organization
	 * default: less ordered to be removed. Date.: 13/07/2010
	 */
	public void activateReverseOrganization() {
		_structureOrganizer = (TileComparator) Collections.reverseOrder(_structureOrganizer);
	}

	/**
	 * \brief Method that returns the maximum numbers of Tile objects supported.
	 * Date.: 13/07/2010
	 * 
	 * @return Maximum numbers of Tile objects supported.
	 */
	public int getStructureCapacity() {
		return _MAX_TILE;
	}

	/**
	 * \brief Method to remove a target Tile object from the cache. Date.:
	 * 13/07/2010
	 * 
	 * @param targetTile
	 *            The target Tile object that will be removed.
	 * @return TRUE - Removed successfully ! FALSE - Not removed.
	 */
	public boolean removeCandidate(Tile targetTile) {
		return _cache.remove(targetTile);
	}

	/**
	 * \brief Method to remove all Tile objects. Date.: 13/07/2010
	 * 
	 * @return TRUE - All objects removed successfully !
	 */
	public boolean clean() {
		if (_cache == null)
			return false;

		_cache.clear();

		return _cache.isEmpty();
	}

}
