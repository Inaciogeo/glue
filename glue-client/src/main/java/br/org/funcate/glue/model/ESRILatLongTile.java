package br.org.funcate.glue.model;

public class ESRILatLongTile {
	private int tileSize;
	private int zoomLevel;
	private Box tileBox;
	private int tileIndexX;
	private int tileIndexY;
	private double tileIndexX1, tileIndexX2, tileIndexY1, tileIndexY2;
	private Projection tileProjection;
	private static double[]resolution = {9.5178440233E-003, 8.3281135204E-003, 7.1383830175E-003, 4.7589220117E-003, 2.3794610058E-003, 1.1897305029E-003, 5.9486525146E-004, 2.3794610058E-004, 1.1897305029E-004, 5.9486525146E-005, 2.3794610058E-005, 1.1897305029E-005,5.948652514575701E-6}; //2.37946100583028E-06
	static double CDR = Math.PI/180;
	static double originLatitude = 400.0;
	static double originLongitude = -400.0;
	public static PointF originLocation = new PointF(originLongitude,originLatitude);	
	/* Constructor*/
	public ESRILatLongTile (PointF originLocation, int tileSize, double[] resolution){
		ESRILatLongTile.originLocation = originLocation;
		ESRILatLongTile.resolution = resolution; 
		this.tileSize = tileSize;
	}
	
	/* calculates the tile index given a planar coordinates of a location */
	public void tileIndex(PointF location){ 	
		setTileIndexX((int) Math.ceil(((-ESRILatLongTile.originLocation.x + location.x)/ESRILatLongTile.resolution[this.zoomLevel])));
		setTileIndexY((int) Math.ceil(((ESRILatLongTile.originLocation.y - location.y)/ESRILatLongTile.resolution[this.zoomLevel])));
	}
	
	/*calculates the tileBox()*/
	public Box tileBox(double tileIndexX, double tileIndexY){
		PointF ll = new PointF((double)(originLocation.x + tileIndexX*ESRILatLongTile.resolution[this.zoomLevel]),(double)((originLocation.y)-(tileIndexY+1)*ESRILatLongTile.resolution[this.zoomLevel]));
		PointF ur = new PointF((double)(originLocation.x + (tileIndexX+1)*ESRILatLongTile.resolution[this.zoomLevel]),ESRILatLongTile.originLocation.y-tileIndexY*ESRILatLongTile.resolution[this.zoomLevel]);
		return new Box(ll.getX(),ll.getX(),ur.getX(),ur.getY());
	}
	
	/* Calculates the range of tiles for a given box in planar coordinates*/
	public void TileMatrix(Box target, double tileIndexX1, double tileIndexX2, double tileIndexY1, double tileIndexY2)
	{
		tileIndex(new PointF(target.getX1(),target.getY1()));
		tileIndex(new PointF(target.getX2(),target.getY2()));
	}
	
	public int getZoomLevel() {
		return zoomLevel;
	}

	public void setZoomLevel(int zoomLevel) {
		this.zoomLevel = zoomLevel;
	}

	public int getTileIndexX() {
		return tileIndexX;
	}

	public void setTileIndexX(int tileIndexX) {
		this.tileIndexX = tileIndexX;
	}

	public int getTileIndexY() {
		return tileIndexY;
	}

	public void setTileIndexY(int tileIndexY) {
		this.tileIndexY = tileIndexY;
	}

	public PointF getOriginLocation() {
		return originLocation;
	}

	public void setOriginLocation(PointF originLocation) {
		ESRILatLongTile.originLocation = originLocation;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public Box getTileBox() {
		return tileBox;
	}

	public void setTileBox(Box tileBox) {
		this.tileBox = tileBox;
	}

	public double getTileIndexX1() {
		return tileIndexX1;
	}

	public void setTileIndexX1(double tileIndexX1) {
		this.tileIndexX1 = tileIndexX1;
	}

	public double getTileIndexX2() {
		return tileIndexX2;
	}

	public void setTileIndexX2(double tileIndexX2) {
		this.tileIndexX2 = tileIndexX2;
	}

	public double getTileIndexY1() {
		return tileIndexY1;
	}

	public void setTileIndexY1(double tileIndexY1) {
		this.tileIndexY1 = tileIndexY1;
	}

	public double getTileIndexY2() {
		return tileIndexY2;
	}

	public void setTileIndexY2(double tileIndexY2) {
		this.tileIndexY2 = tileIndexY2;
	}

	public Projection getTileProjection() {
		return tileProjection;
	}

	public void setTileProjection(Projection tileProjection) {
		this.tileProjection = tileProjection;
	}

	public static double[] getResolution() {
		return resolution;
	}

	public static void setResolution(double[] resolution) {
		ESRILatLongTile.resolution = resolution;
	}
	
}
