package br.org.funcate.glue.model.cache;

import java.util.Comparator;

/**
 * \brief Class responsible for making the comparison between Tile objects in a
 * collection. The comparison is made as follows: first is to compare the number
 * of times each Tile was required, and then the collection will be ordered from
 * the less ordered to more ordered. In criterion of a tie, will be considered
 * the time at which each was accessed, ie, one that was clicked will be chosen
 * later.
 * 
 * @author Juliana & Vinicius
 */

public class TileComparator implements Comparator<Tile> {
	/**
	 * \brief Method to compare the number of times required for two objects
	 * Tile In criterion of a tie, the private method compareHour () is called
	 * to tie the comparison
	 * 
	 * @param td1
	 *            First Tile to be compared
	 * @param td2
	 *            Second Tile to be compared
	 * @see java.util.Comparator
	 */
	public int compare(Tile td1, Tile td2) {
		if (td1.equals(td2)) {
			return 0;
		}

		String hourTd1 = td1.getLastMinuteAccessed();
		String hourTd2 = td2.getLastMinuteAccessed();

		String[] hourNumbersTd1 = hourTd1.split(":");
		String[] hourNumbersTd2 = hourTd2.split(":");

		int numberTd1;
		int numberTd2;

		for (int i = 0; i < hourNumbersTd1.length; i++) {

			numberTd1 = Integer.parseInt(hourNumbersTd1[i]);
			numberTd2 = Integer.parseInt(hourNumbersTd2[i]);

			if (numberTd1 < numberTd2) {
				return 1;
			} else if (numberTd1 == numberTd2) {
				continue;
			} else {
				return compareAccess(td1, td2);
			}
		}

		return compareAccess(td1, td2);
	}

	/**
	 * \brief Method to compare the last minute accessed between two objects
	 * Tile
	 * 
	 * @param td1
	 *            First Tile to be compared
	 * @param td2
	 *            Second Tile to be compared
	 * @return 0 - If the values are identical 1 - If td1 was accessed more
	 *         recently -1 - If td1 has an access time older than td2
	 */
	private int compareAccess(Tile o1, Tile o2) {
		if (o1.getAccessedTimes() < o2.getAccessedTimes()) {
			return 1;
		} else if (o1.getAccessedTimes() > o2.getAccessedTimes()) {
			return -1;
		} else {
			return -1;
		}
	}

}
