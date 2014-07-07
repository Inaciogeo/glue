package br.org.funcate.glue.model.canvas;

import java.awt.Point;
import java.text.DecimalFormat;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.CalculatorService;

public abstract class DistanceMeasuringToolService {

	public static void dragDistanceTool(int mouseClickXPosition, int mouseClickYPosition) {
		DistanceMeasuringGraphics distance = getDistanceGraphicsFromSingleton();

		distance.setX2(mouseClickXPosition);
		distance.setY2(mouseClickYPosition);
		Point p = new Point(distance.getX1(), distance.getY1());
		double pixelsDistance = (p.distance(distance.getX2(), distance.getY2()));

		double dist = (CalculatorService.getMeterConversor(AppSingleton.getInstance().getCanvasState().getProjection())
				* AppSingleton.getInstance().getCanvasState().getResolution() * pixelsDistance);
		DecimalFormat df = new DecimalFormat("#,###.00");

		distance.setValue(df.format(dist));

		if (!distanceToolValidation()) {
			cancelDistanceTool();
		}

		AppSingleton.getInstance().getCanvasState().getCanvasGraphicsBuffer().notifyObservers();
	}

	public static void cancelDistanceTool() {
		DistanceMeasuringGraphics distance = getDistanceGraphicsFromSingleton();

		distance.setValue("");
		distance.setX1(0);
		distance.setX2(0);
		distance.setY1(0);
		distance.setY2(0);
	}

	public static void pressDistanceTool(int mouseClickXPosition, int mouseClickYPosition) {
		AppSingleton.getInstance().getCanvasState().getCanvasGraphicsBuffer()
				.setToolGraphics(new DistanceMeasuringGraphics(mouseClickXPosition, mouseClickYPosition, 0, 0, ""));
	}

	public static DistanceMeasuringGraphics getDistanceGraphicsFromSingleton() {
		ToolGraphics tool = AppSingleton.getInstance().getCanvasState().getCanvasGraphicsBuffer().getToolGraphics();

		DistanceMeasuringGraphics distance;

		if (tool instanceof DistanceMeasuringGraphics) {
			distance = (DistanceMeasuringGraphics) tool;
		} else {
			throw new RuntimeException("Atributo toolGraphics no state não é um DistanceTool!");
		}

		return distance;
	}

	public static boolean distanceToolValidation() {
		DistanceMeasuringGraphics distance = getDistanceGraphicsFromSingleton();

		return !(distance.getX2() < 0 || distance.getX2() > AppSingleton.getInstance().getCanvasState().getCanvasWidth() - 3
				|| distance.getY2() < 0 || distance.getY2() > AppSingleton.getInstance().getCanvasState().getCanvasHeight() - 3);
	}
}
