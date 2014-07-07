/**
 * This class is a adapted portion of the original Python code globalmaptiles.py created by Klokan Petr Pridal on 2008-07-03.
 * 
 * 
 * 
 * @author Ricardo Pontes Bonfiglioli
 *
 */
/*!
 * Copyright (c) 2008 Klokan Petr Pridal. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 */

package br.org.funcate.glue.model.canvas;

import br.org.funcate.glue.utilities.PropertiesReader;

public abstract class GlobalMapTiles {

	public static final int TILE_SIZE = PropertiesReader.getIntegerProperty("tile.size");
	private static final double INITIAL_RESOLUTION;
	private static final double ORIGIN_SHIFT;

	/**
	 * Inicialize the TMS Global Mercator pyramid
	 */
	static {
		double constValue = 2 * Math.PI * 6378137;
		INITIAL_RESOLUTION = constValue / TILE_SIZE;
		ORIGIN_SHIFT = constValue / 2.0;
	}

	/**
	 * Returns resolution (meters/pixel) for a given zoom level (measured at
	 * Equator)
	 * 
	 * @param zoomLevel
	 * @return
	 */
	private static double getResolution(int zoomLevel) {
		return INITIAL_RESOLUTION / Math.pow(2, zoomLevel);
	}

	/**
	 * Converts XY point from Spherical Mercator EPSG:900913 to lat/lon in WGS84
	 * Datum
	 * 
	 * @param mx
	 * @param my
	 * @return
	 */
	private static double[] convertMetersToLatLong(double mx, double my) {
		double lon = (mx / ORIGIN_SHIFT) * 180.0;
		double lat = (my / ORIGIN_SHIFT) * 180.0;
		lat = 180 / Math.PI * (2 * Math.atan(Math.exp(lat * Math.PI / 180.0)) - Math.PI / 2.0);
		return new double[] { lat, lon };
	}

	/**
	 * Converts pixel coordinates in given zoom level of pyramid to EPSG:900913
	 * 
	 * @param px
	 * @param py
	 * @param zoomLevel
	 * @return
	 */
	private static double[] convertPixelsToMeters(double px, double py, int zoomLevel) {
		double resolution = getResolution(zoomLevel);
		double mx = px * resolution - ORIGIN_SHIFT;
		double my = py * resolution - ORIGIN_SHIFT;
		return new double[] { mx, my };
	}

	/**
	 * Returns bounds of the given tile in EPSG:900913 coordinates
	 * 
	 * @param tx
	 * @param ty
	 * @param zoomLevel
	 * @return
	 */
	private static double[] getTileBounds(int tx, int ty, int zoomLevel) {
		int xPosition = tx * TILE_SIZE;
		int yPosition = ty * TILE_SIZE;
		double[] minPoint = convertPixelsToMeters(xPosition, yPosition, zoomLevel);
		double[] maxPoint = convertPixelsToMeters(xPosition + TILE_SIZE, yPosition + TILE_SIZE, zoomLevel);
		return new double[] { minPoint[0], minPoint[1], maxPoint[0], maxPoint[1] };
	}

	/**
	 * Returns bounds of the given tile in latitude/longitude using WGS84 datum
	 * 
	 * @param tx
	 * @param ty
	 * @param zoomLevel
	 * @return
	 */
	private static double[] getTileLatLongBounds(int tx, int ty, int zoomLevel) {
		double[] bounds = getTileBounds(tx, ty, zoomLevel);
		double[] minLatLong = convertMetersToLatLong(bounds[0], bounds[1]);
		double[] maxLatLong = convertMetersToLatLong(bounds[2], bounds[3]);
		return new double[] { minLatLong[0], minLatLong[1], maxLatLong[0], maxLatLong[1] };
	}

	// /**
	// * Converts TMS tile coordinates to Google Tile coordinates
	// *
	// * @param tx
	// * @param ty
	// * @param zoomLevel
	// * @return
	// */
	// private static int[] getGoogleTile (int tx, int ty, int zoomLevel) {
	// return new int[] {tx, (int) Math.round((Math.pow(2, zoomLevel) - 1) -
	// ty)};
	// }

	/**
	 * Returns latitude/longitude of a given tile
	 * 
	 * @param tx
	 * @param ty
	 * @param zoomLevel
	 * @return
	 */
	public static double[] getTileLatLongCenter(int tx, int ty, int zoomLevel) {
		double[] tileLatLongBounds = getTileLatLongBounds(tx, ty, zoomLevel);
		return new double[] { ((tileLatLongBounds[0] + tileLatLongBounds[2]) / 2) * (-1), (tileLatLongBounds[1] + tileLatLongBounds[3]) / 2 };
	}
}
