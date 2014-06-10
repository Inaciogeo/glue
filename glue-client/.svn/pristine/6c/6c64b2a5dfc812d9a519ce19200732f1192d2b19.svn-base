package br.org.funcate.glue.model;

import java.util.List;

public class ThemesService {

	public static ThemeToPlot getThemeToPlotByName(List<ThemeToPlot> themes, String name) {
		for (ThemeToPlot themeToPlot : themes) {
			if(themeToPlot.getName().equalsIgnoreCase(name)) {
				return themeToPlot; 
			}
		}
		return null;
	}
	public static Theme getThemeByName(List<Theme> themes, String name) {
		for (Theme theme : themes) {
			if(theme.getName().equalsIgnoreCase(name)) {
				return theme; 
			}
		}
		return null;
	}

}
