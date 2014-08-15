package br.org.funcate.glue.model;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.canvas.CanvasState;

public class CalculatorService {

	private static final double GPstlat1 = 0;
	private static final double GPlon0 = 0;
	private static final double GPoffx = 0;
	private static final double GPoffy = 0;

	public static double generateScaleFromBox(double x1, double y1, double x2, double y2) {
		double largura = x2 - x1;
		double altura = y2 - y1;
		double resX = largura / AppSingleton.getInstance().getCanvasState().getCanvasWidth();
		double resY = altura / AppSingleton.getInstance().getCanvasState().getCanvasHeight();
		double resolution;
		if (resX > resY)
			resolution = resX;
		else
			resolution = resY;
		int meterConversor = getMeterConversor(AppSingleton.getInstance().getCanvasState().getProjection());
		double meterPerPixel = meterConversor * resolution;
		double scale = meterPerPixel / 0.026458333; /* 1 pixel = 0.026458333cm */
		return scale;
	}

	public static double[] convertFromWorldToPixel(double x1, double y1) {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		Box box = state.getBox();
		
		double boxX1WorldPosition = box.getX1();
		double boxY1WorldPosition = box.getY1();

		double resolution = state.getResolution();

		double x1Increment = x1 - boxX1WorldPosition;
		double y1Increment = y1 - boxY1WorldPosition;

		double xFinalPosition = (x1Increment / resolution);
		double yFinalPosition = state.getCanvasHeight() - (y1Increment / resolution);

		double[] positionInWorld = { xFinalPosition, yFinalPosition };

		return positionInWorld;
	}
	
	@SuppressWarnings("unused")
	public static double[] convertFromWorldToPixel2(double x1, double y1) {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		Box box = state.getBox();
		
		//double xFinalPosition = Math.round((x1*state.getCanvasWidth())/360);
		//double yFinalPosition = Math.round((y1*state.getCanvasHeight())/180);
		
		// get x value
		double xFinalPosition = ((y1)+180)*(state.getCanvasWidth()/360);

		// convert from degrees to radians
		double latRad = ((x1)*Math.PI)/180;

		// get y value
		double mercN = Math.log(Math.tan((Math.PI/4)+(latRad/2)));
		double yFinalPosition  = (state.getCanvasHeight()/2)-(state.getCanvasWidth()*mercN/(2*Math.PI));
		
//		double boxX1WorldPosition = box.getX1();
//		double boxY1WorldPosition = box.getY1();
//
//		double resolution = (state.getCanvasHeight()*state.getCanvasWidth());
//
//		double x1Increment = x1 - boxX1WorldPosition;
//		double y1Increment = y1 - boxY1WorldPosition;
//
//		double xFinalPosition = (x1Increment / resolution);
//		double yFinalPosition = state.getCanvasHeight() - (y1Increment / resolution);
//
		double[] positionInWorld = { xFinalPosition, yFinalPosition };

		return positionInWorld;
	}
	/**
	 * 
	 * @param ptll
	 * @return
	 */
	
	public static double[] WGS84LatLongToVirtualEarthMercato(Coord ptll) {
		double equad, // Squared eccentricity
		aux1, // Ancillary variables
		aux2, aux3, aux4, aux5, aux6, ptllx, ptlly;

		// double Pflt = GPdatum.flattening();
		double Pflt = 0; // To be compatible with Virtual Eart Mercator (WGS84 spherical)to simulate P4 +nadgrids=null
		double Prd = 6378137.0;//GPdatum.radius();
		// conversão em graus
		ptllx = ptll.getX()*(Math.PI/180);
		ptlly = ptll.getY()*(Math.PI/180);

		equad = 2.0 * Pflt;
		equad -= Pflt * Pflt;
		double a1, a2, a3;

		a1 = Math.tan(ptlly / (double) 2);
		a2 = 1.0 + a1;
		a3 = 1.0 - a1;
		aux1 = a2 / a3;

		a1 = (equad * equad) / 4.0;
		a1 += equad;
		a2 = (equad * equad * equad) / 8.0;
		a3 = Math.sin(ptlly);
		aux2 = (a1 + a2) * a3;

		a1 = (equad * equad) / 12.0;
		a2 = (equad * equad * equad) / 16.0;
		a3 = Math.sin((double) 3 * ptlly);
		aux3 = (a1 + a2) * a3;

		a1 = (equad * equad * equad) / 80.0;
		a2 = Math.sin((double) 5 * ptlly);
		aux4 = a1 * a2;
		aux5 = Math.cos(GPstlat1);

		a1 = Math.sin(GPstlat1);
		a1 *= a1;
		a1 *= equad;
		a2 = Math.sqrt((double) 1 - a1);
		aux6 = 1.0 / a2;

		ptllx = Prd * (ptllx - GPlon0) * aux5 * aux6;
		ptlly = Prd * ((Math.log(Math.abs(aux1)) - (aux2 + aux3 - aux4) * (aux5 * aux6)));
		

		// converts from meters, if necessary
//		if (GPunitsEnum != TeMeters) {
//			ptllx = TeConvertUnits(ptllx, TeMeters, GPunitsEnum);
//			ptlly = TeConvertUnits(ptlly, TeMeters, GPunitsEnum);
//		}

		ptllx = ptllx + GPoffx;
		ptlly = ptlly + GPoffy;
		
		double[] positionInWorld ={ptllx,ptlly};
		return positionInWorld;
	}

	public static double[] convertFromPixelToWorld(int mouseClickPositionX, int mouseClickPositionY) {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		Box box = state.getBox();

		double boxX1WorldPosition = box.getX1();
		double boxY1WorldPosition = box.getY1();

		double resolution = state.getResolution();

		double x1IncrementToWorld = mouseClickPositionX * resolution;
		double y1IncrementToWorld = (state.getCanvasHeight() - mouseClickPositionY) * resolution;

		double xFinalPosition = boxX1WorldPosition + x1IncrementToWorld;
		double yFinalPosition = boxY1WorldPosition + y1IncrementToWorld;

		double[] positionInWorld = { xFinalPosition, yFinalPosition };

		return positionInWorld;
	}

	public static double getCurrentScale() {
		int meterConversor = CalculatorService.getMeterConversor(AppSingleton.getInstance().getCanvasState().getProjection());
		double meterPerPixel = meterConversor * AppSingleton.getInstance().getCanvasState().getResolution();
		double scale = meterPerPixel / 0.026458333; /* 1 pixel = 0.026458333cm */
		return scale;
	}

	public void resizeBox(double boxX1, double boxY1, double boxX2, double boxY2) {
		int defaultWidth = 870;
		int defaultHeight = 500;

		double resolution;

		double largura = boxX2 - boxX1;
		double altura = boxY2 - boxY1;
		double resX = largura / defaultWidth;
		double resY = altura / defaultHeight;

		if (resX > resY) {
			resolution = resX;
			resizeBoxX(resolution, boxX1, boxX2, defaultWidth);
			boxY1 = boxY1 + altura / 2 - defaultHeight / 2 * resolution;
			resizeBoxY(resolution, boxY1, boxY2, defaultHeight);
		} else {
			resolution = resY;
			resizeBoxY(resolution, boxY1, boxY2, defaultHeight);
			boxX1 = boxX1 + largura / 2 - defaultWidth / 2 * resolution;
			resizeBoxX(resolution, boxX1, boxX2, defaultWidth);
		}
	}

	private void resizeBoxX(double resolution, double boxX1, double boxX2, double width) {
		boxX1 = (int) (boxX1 / resolution) * resolution;
		boxX2 = boxX1 + width * resolution;
	}

	private void resizeBoxY(double resolution, double boxY1, double boxY2, double height) {
		boxY1 = (int) (boxY1 / resolution) * resolution;
		boxY2 = boxY1 + height * resolution;
	}

	public static double calculateScale(Projection projection, double boxX1, double boxY1, double boxX2, double boxY2) {
		int defaultWidth = 870;
		int defaultHeight = 500;

		int meterConversor;

		double resolution = calculateResolution(defaultWidth, defaultHeight, boxX1, boxY1, boxX2, boxY2);

		//if (projection.getName().equals("LatLong")|| projection.getName().equals("ESRI"))
		if (projection.getName().equals("LatLong"))
			meterConversor = 111133;
		else
			meterConversor = 1;
		double meterPerPixel = meterConversor * resolution;
		double scale = meterPerPixel / 0.026458333; /* 1 pixel = 0.026458333cm */
		return scale * 2; // MULTIPLICA POR DOIS PRA COMPENSAR O DESLOCAMENTO DE
							// CONVERSÃO DE PROJEÇÃO
	}

	public static int getMeterConversor(Projection projection) {
		int meterConversor = 1;

		//if ("LatLong".equals(projection.getName()) || projection.getName().equals("ESRI")) {
		if ("LatLong".equals(projection.getName())) {
			meterConversor = 111133;
		}

		return meterConversor;
	}

	private static double calculateResolution(int width, int height, double boxX1, double boxY1, double boxX2, double boxY2) {
		double resolution;

		double largura = boxX2 - boxX1;
		double altura = boxY2 - boxY1;
		double resX = largura / width;
		double resY = altura / height;

		if (resX > resY)
			resolution = resX;
		else
			resolution = resY;

		return resolution;
	}

}
