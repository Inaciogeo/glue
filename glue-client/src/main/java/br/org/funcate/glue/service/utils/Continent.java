package br.org.funcate.glue.service.utils;

public enum Continent {

	BRAZIL ("Brazil"), 
	SOUTH_AMERICA ("South America"), 
	NORTH_AMERICA ("North America"),
	EUROPE ("Europe"),
	AFRICA ("Africa"),
	ASIA ("Asia"),
	OCEANIA ("Oceania");
	
	private String name;
	
	Continent (String name) {
		this.name = name;
	}
	
	public String toString () {
		return name;
	}
}
