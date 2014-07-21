package br.org.funcate.glue.model.canvas;

import java.util.ArrayList;
import java.util.List;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.Representation;
import br.org.funcate.glue.model.Theme;
import br.org.funcate.glue.model.ThemeToPlot;
import br.org.funcate.glue.model.View;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.service.TerraJavaClient;
import br.org.funcate.glue.utilities.PropertiesReader;

public abstract class InfoToolService {

	@SuppressWarnings("unchecked")
	public static List<ThemeAttributesInfoTool> getInfoByClick(
			double xPosition, double yPosition) throws GlueServerException {

		boolean priorityEnabled = new Boolean(
				PropertiesReader.getProperty("infoTool.enable.priority"));

		List<ThemeToPlot> themesWithObjects = null;

		if (priorityEnabled) {
			themesWithObjects = getThemesWithPriority(xPosition, yPosition);
		} else {
			themesWithObjects = getAllThemesObjects(xPosition, yPosition);
		}

		if (themesWithObjects == null) {
			return null;
		}

		List<ThemeAttributesInfoTool> themesToShowAttributes = new ArrayList<ThemeAttributesInfoTool>();

		for (ThemeToPlot theme : themesWithObjects) {
			String object = theme.getObjectsToDraw().get(0);
			List<Object> attributes = getServiceFromSingleton()
					.fetchAttributes(object, theme.getName(), theme.getThemeId());
			themesToShowAttributes.add(new ThemeAttributesInfoTool(theme
					.getName(), object, attributes));
		}

		return themesToShowAttributes;
	}

	private static List<ThemeToPlot> getAllThemesObjects(double xPosition,
			double yPosition) throws GlueServerException {
		List<Theme> themes = getThemesFromView();
		TerraJavaClient service = getServiceFromSingleton();
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState canvasState = singleton.getCanvasState();
		Box box = canvasState.getBox();

		List<ThemeToPlot> themesWithObjects = new ArrayList<ThemeToPlot>();

		for (Theme theme : themes) {
			if (theme.isVisibility() && theme.isScaleVisibility()) {
				List<String> objects = service.locateObject(box,
						canvasState.getCanvasWidth(),
						canvasState.getCanvasHeight(), xPosition, yPosition,
						theme);
				if (objects != null) {
					if (!objects.isEmpty()) {
						ThemeToPlot themeToPlot = new ThemeToPlot(
								theme.getName(), theme.getType(), theme.getId());
						themeToPlot.setObjectsToDraw(objects.subList(0, 1));
						themesWithObjects.add(themeToPlot);
					}
				}
			}
		}

		if (themesWithObjects.isEmpty()) {
			return null;
		}

		return themesWithObjects;
	}

	private static List<ThemeToPlot> getThemesWithPriority(double xPosition,
			double yPosition) throws GlueServerException {
		List<Theme> themes = getThemesFromView();

		String[] repType = { "4", "2", "1" };

		int i = 0;

		while (i < repType.length) {
			List<ThemeToPlot> themesWithObjects = getThemesObjectsByRepresentation(
					xPosition, yPosition, repType[i], themes);
			if (!themesWithObjects.isEmpty()) {
				return themesWithObjects;
			}
			i++;
		}

		return null;
	}

	private static List<ThemeToPlot> getThemesObjectsByRepresentation(
			double xPosition, double yPosition, String rep, List<Theme> themes)
			throws GlueServerException {
		TerraJavaClient service = getServiceFromSingleton();
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState canvasState = singleton.getCanvasState();
		Box box = canvasState.getBox();
		List<ThemeToPlot> themesWithObjects = new ArrayList<ThemeToPlot>();

		for (Theme theme : themes) {
			if (theme.isVisibility() && theme.isScaleVisibility()) {
				List<Representation> reps = theme.getReps();
				String representation = reps.get(0).getName();
				if (representation.equals(rep)) {
					List<String> objects = service.locateObject(box,
							canvasState.getCanvasWidth(),
							canvasState.getCanvasHeight(), xPosition,
							yPosition, theme);
					if (objects != null && !objects.isEmpty()) {
						ThemeToPlot themeToPlot = new ThemeToPlot(
								theme.getName(), theme.getType(), theme.getId());
						themeToPlot.setObjectsToDraw(objects.subList(0, 1));
						themesWithObjects.add(themeToPlot);
					}
				}

			}
		}

		return themesWithObjects;
	}

	private static List<Theme> getThemesFromView() {
		AppSingleton singleton = AppSingleton.getInstance();
		CanvasState state = singleton.getCanvasState();
		View view = state.getView();
		List<Theme> themes = view.getThemes();
		return themes;
	}

	private static TerraJavaClient getServiceFromSingleton() {
		AppSingleton singleton = AppSingleton.getInstance();
		return singleton.getServices();
	}
}
