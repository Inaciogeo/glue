package br.org.funcate.glue.model;

import java.io.Serializable;

/**
 * @author Silva, Paulo Luan \brief
 */

public class FilterAttribute implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String attributeName;
	private String relationalOperator;
	private String value;
	private String logicOperator;

	/**
	 * Identification to FilterAttribute of persistence in database.
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getRelationalOperator() {
		return relationalOperator;
	}

	public void setRelationalOperator(String relationalOperator) {
		this.relationalOperator = relationalOperator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLogicOperator() {
		return logicOperator;
	}

	public void setLogicOperator(String logicOperator) {
		this.logicOperator = logicOperator;
	}
}
