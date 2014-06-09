package br.org.funcate.glue.service.utils;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import au.com.bytecode.opencsv.CSVReader;
import br.org.funcate.glue.utilities.PropertiesReader;

public class GoogleContinent {

	private static final InputStream INPUTSTREAM;
	private static HashMap<String, Continent> countries;
	private static String country;

	static {
		INPUTSTREAM = PropertiesReader.class.getClassLoader()
				.getResourceAsStream(
						"br"+File.separator+"org"+File.separator+"funcate"+File.separator+"glue"+File.separator+"resource"+File.separator+"selectedIPs.csv");

		countries = new HashMap<String, Continent>();

		// insere a relação de paises e continentes
		countries.put("ARGENTINA", Continent.SOUTH_AMERICA);
		countries.put("AUSTRALIA", Continent.OCEANIA);
		countries.put("BRAZIL", Continent.BRAZIL);
		countries.put("CANADA", Continent.NORTH_AMERICA);
		countries.put("CHILE", Continent.SOUTH_AMERICA);
		countries.put("CHINA", Continent.ASIA);
		countries.put("FRANCE", Continent.EUROPE);
		countries.put("GERMANY", Continent.EUROPE);
		countries.put("INDIA", Continent.ASIA);
		countries.put("ITALY", Continent.EUROPE);
		countries.put("JAPAN", Continent.ASIA);
		countries.put("NEW ZEALAND", Continent.OCEANIA);
		countries.put("PORTUGAL", Continent.EUROPE);
		countries.put("SPAIN", Continent.EUROPE);
		countries.put("SOUTH AFRICA", Continent.AFRICA);
		countries.put("UNITED KINGDON", Continent.EUROPE);
		countries.put("UNITED STATES", Continent.NORTH_AMERICA);

	}

	public static String getContinent(Long IP) throws RuntimeException {

		try {
			// recupera o país que contém o ip
			CSVReader reader;
			reader = new CSVReader(new InputStreamReader(INPUTSTREAM));
			String[] nextLine;
			while ((nextLine = reader.readNext()) != null) {
				long initialIP = Long.parseLong(nextLine[0]);
				long finalIP = Long.parseLong(nextLine[1]);
				System.out.println("país = " + nextLine[2]);
				if (IP >= initialIP && IP <= finalIP) {
					country = nextLine[2];
					break;
				}
			}
		} catch (Exception e) {
			country = "BRAZIL";
			throw new RuntimeException(
					"Ocorreu um erro ao tentar recuperar o país que contém o IP. ");
		}

		return countries.get(country).toString();
	}
}
