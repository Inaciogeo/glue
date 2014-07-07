package br.org.funcate.glue.main;

import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.PointF;
import br.org.funcate.glue.model.Projection;
import br.org.funcate.glue.model.exception.GlueServerException;

public class AppTeste {
	
	private PointF originLocation; /* radians */
	private int tileSize;
	private double[] resolution;
	private int zoomLevel;
	//double worldExtent = 40075016,686 * meters2unit
	Box tileBox;
	int tileIndexX;
	int tileIndexY;
	boolean loaded;
	double tileIndexX1, tileIndexX2, tileIndexY1, tileIndexY2;
	Projection tileProjection;
	
	/* Constructor*/
	public void ESRILatLongTile (PointF originLocation, int tileSize, double[] resolution){
		this.originLocation = originLocation;
		this.tileSize = tileSize;
		this.resolution = resolution; 
	}
	
	/* calculates the tile index given a planar coordinates of a location */
	public void tileIndex(PointF location){ 	
		setTileIndexX((int) Math.ceil(((-this.originLocation.x + location.x)/this.resolution[this.zoomLevel])));
		setTileIndexY((int) Math.ceil(((this.originLocation.y - location.y)/this.resolution[this.zoomLevel])));
	}
	
	/*calculates the tileBox()*/
	public Box tileBox(double tileIndexX, double tileIndexY){
		PointF ll = new PointF((double)(originLocation.x + tileIndexX*this.resolution[this.zoomLevel]),(double)((originLocation.y)-(tileIndexY+1)*this.resolution[this.zoomLevel]));
		PointF ur = new PointF((double)(originLocation.x + (tileIndexX+1)*this.resolution[this.zoomLevel]),this.originLocation.y-tileIndexY*this.resolution[this.zoomLevel]);
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

	/**
	 * @param args
	 * @throws GlueServerException 
	 */
	public static void main(String[] args) throws GlueServerException {
		double CDR = Math.PI/180;
		double originLatitude = 71.5323878134;
		double originLongitude = -125.1420042616;
		PointF originLocation = new PointF(originLongitude*CDR,originLatitude*CDR);
		/* Valores de resolução extraidos do si-tehttp://geoportal.igc.sp.gov.br:6080/arcgis/rest/services/IGC/GeoPortal_Ortofotos_Mapeamento2010_ImgSrv/ImageServer*/
		double[]resolution = {9.5178440233E-003, 8.3281135204E-003, 7.1383830175E-003, 4.7589220117E-003, 2.3794610058E-003, 1.1897305029E-003, 5.9486525146E-004, 2.3794610058E-004, 1.1897305029E-004, 5.9486525146E-005, 2.3794610058E-005, 1.1897305029E-005,5.948652514575701E-6, 2.37946100583028E-06};
		AppTeste tiles = new AppTeste();
		tiles.setZoomLevel(11);
		tiles.ESRILatLongTile(originLocation, 256, resolution);
		tiles.tileIndex(new PointF(-45.88783968*CDR, -23.18024310*CDR));
		System.out.println(tiles.getTileIndexX());
		System.out.println(tiles.getTileIndexY());
		//tiles.TileMatrix(target, tileIndexX1, tileIndexX2, tileIndexY1, tileIndexY2);
	}

}
