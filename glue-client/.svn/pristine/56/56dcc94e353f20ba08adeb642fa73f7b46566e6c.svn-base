package br.org.funcate.glue.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Silva, Paulo Luan e Durvale, Arquimedes
 * 
 *         \brief Representa a entidade Mapa Temático.
 * 
 * @since 09/02/2012
 */

public class ContextToGroupMap implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String description;
	private String view;
	private String theme;
	private String groupAttribute;
	private Integer groupType;
	private int precision;
	private int normAttribute;
	private int slices;
	private int red;
	private int green;
	private int blue;
	private String keyword;
	private List<FilterAttribute> filterAttributes;
	private CustomGroupParameters customParameter;

	private String tableInfo;

	private String linkCol;

	private int attributeType;

	/* só é usado quando o algoritmo selecionado for o personalizado */
	/*
	 * the annotation @Transient specifies that the property or field is not
	 * persistent.
	 */

	/**
	 * Identification to ContextToGroupMap of persistence in database.
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getGroupAttribute() {
		return groupAttribute;
	}

	/*
	 * Receives a groupAttribute in a parameter but insert only the name of this
	 * Object into a database.
	 */
	public void setGroupAttribute(String groupAttribute) {
		this.groupAttribute = groupAttribute;
	}
	
	public String getTableInfo(){
		return this.tableInfo;		
	}
	
	public void setTableInfo(String tableInfo) {
		this.tableInfo = tableInfo;
	}
	
	public String getLinkCol(){
		return this.linkCol;		
	}
	
	public void setLinkCol(String linkCol) {
		this.linkCol = linkCol; 
	}
	
	public int getAttributeType(){
		return this.attributeType;		
	}
	
	public void setAttributeType(int attributeType) {
		this.attributeType = attributeType;
	}
		
	public int getGroupType() {
		return groupType;
	}

	/*
	 * takes the enum value on GroupType of the correspondent String passed on
	 * parameter.
	 */
	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getNormAttribute() {
		return normAttribute;
	}

	public void setNormAttribute(int normAttribute) {
		this.normAttribute = normAttribute;
	}

	public int getSlices() {
		return slices;
	}

	public void setSlices(int slices) {
		this.slices = slices;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<FilterAttribute> getFilterAttributes() {
		return filterAttributes;
	}

	public void setFilterAttributes(List<FilterAttribute> filterAttributes) {
		this.filterAttributes = filterAttributes;
	}

	public void setCustomParameter(CustomGroupParameters customParameter) {
		this.customParameter = customParameter;
	}

	public CustomGroupParameters getCustomParameter() {
		return customParameter;
	}

	@Override
	public boolean equals(java.lang.Object object) {
		if (!(object instanceof ContextToGroupMap)) {
			return false;
		}
		ContextToGroupMap other = (ContextToGroupMap) object;
		if (other.getId() != 0 || this.getId() != other.getId()) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.org.funcate.gmt.model.dto.ContextToGroupMap[ id=" + id
				+ " ]";
	}
	
	public boolean isIntegrity(){
		return true;
	}
}
