package br.org.funcate.glue.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

/**
 * @author Willyan Aleksander de Paula Machado
 */
public abstract class PropertiesReader {
	/**
	 * Url to load properties file.
	 */
	private static Properties properties;
	/**
	 * ImputStream to be loaded in Properties object.
	 */
	private static InputStream is;

	/**
	 * Constructor method to load .properties file and enable to Glue access
	 * your properties.
	 */

	static {

		properties = new Properties();

		is = PropertiesReader.class.getClassLoader().getResourceAsStream("br/org/funcate/glue/utilities/conf.properties");

		if (is == null) {
			JOptionPane.showMessageDialog(null, "Falha ao ler o arquivo .properties");
			throw new RuntimeException("Falha ao ler o arquivo de propriedades");
		} else {
			try {
				properties.load(is);
				is.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "Falha ao ler o arquivo .properties");
			}
		}
	}

	public static String getProperty(String propertyKey) {
		String propriedade = properties.getProperty(propertyKey);
		if (propriedade == null) {
			throw new RuntimeException("Propriedade " + propertyKey + " não existe.");
		}
		return propriedade;
	}

	public static boolean getBooleanProperty(String propertyKey) {
		String propertyStr = getProperty(propertyKey);
		boolean property = Boolean.parseBoolean(propertyStr);
		return property;
	}

	public static int getIntegerProperty(String propertyKey) {
		String propertyStr = getProperty(propertyKey);
		int property = Integer.parseInt(propertyStr);
		return property;
	}

	public static double getDoubleProperty(String propertyKey) {
		String propertyStr = getProperty(propertyKey);
		double property = Double.parseDouble(propertyStr);
		return property;
	}
}
