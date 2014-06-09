package br.org.funcate.glue.utilities;

public abstract class VisualUtils {
	public static String getFontPathByName(String fontName) {
		String fontFileName = PropertiesReader.getProperty("visual.resource.fonts.package") + fontName.toLowerCase() + ".ttf";
		String fontPath = VisualUtils.class.getClassLoader().getResource(fontFileName).getFile();
		return fontPath;
	}
}
