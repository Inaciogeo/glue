package br.org.funcate.glue.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import br.org.funcate.glue.utilities.PropertiesReader;
import br.org.funcate.terrajava.persistencia.TerraJava;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TerraJavaJNI {
	
	TerraJava terraJava = new TerraJava();
	
	public HashMap<String, Double> getCentroidForBiggestGeometry(
			String objectId, String sessionId) throws RuntimeException {
		HashMap<String, Double> result;
		try {
			result = terraJava
					.getCentroidForBiggestGeometry(objectId, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao calcular centróide da maior geometria associada ao objectId. ",
					e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao calcular centróide da maior geometria associada ao objectId. ",
					e);
		}
		return result;
	}

	public List<String> getAttributesList(String sessionId)
			throws RuntimeException {
		Vector<String> attributesList;
		try {
			attributesList = terraJava.getAttributesList(sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar a lista de atributos.", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar a lista de atributos.", e);
		}
		List<String> attrTheme = new ArrayList<String>(attributesList);
		return attrTheme;
	}

	public boolean buildCollection(String objectId, String sessionId)
			throws RuntimeException {
		try {
			return terraJava.buildCollection(objectId, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao criar as tabelas coleção para um tema corrente. ",
					e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao criar as tabelas coleção para um tema corrente. ",
					e);
		}
	}

	public boolean addGeometry(int representation, Vector<Object> verticeList,
			Vector<Object> attrList, String layerName,
			HashMap<String, Object> projectionHashMap, String sessionId)
			throws RuntimeException {
		try {
			return terraJava.addGeometry(representation, verticeList, attrList,
					layerName, projectionHashMap, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Não foi possível reprojetar coordenadas utilizando o serviço.",
					e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Não foi possível reprojetar coordenadas utilizando o serviço.",
					e);
		}
	}

	// Connection Methods
	public void connect(String host, String user, String password, String base,
			Integer port, Integer dbType, String sessionId)
			throws RuntimeException {
		try {
			boolean usePool = PropertiesReader
					.getBooleanProperty("terrajava.connection.usePool");

			if (usePool) {

				int maxConnections = PropertiesReader
						.getIntegerProperty("terrajava.connection.pool.max.connections");
				int maxWait = PropertiesReader
						.getIntegerProperty("terrajava.connection.pool.max.wait");
				int maxIdle = PropertiesReader
						.getIntegerProperty("terrajava.connection.pool.max.idle");
				terraJava.useConnectionPool(true);
				terraJava.setMaxPoolConnections(maxConnections);
				terraJava.setMaxPoolIdle(maxIdle);
				terraJava.setMaxPoolWait(maxWait);
			} else {
				terraJava.useConnectionPool(false);
			}
			terraJava.connect(host, user, password, base, port, dbType,
					sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao tentar conectar com o banco de dados modelo TerraLib. ",
					e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao tentar conectar com o banco de dados modelo TerraLib. ",
					e);
		}
	}

	public Boolean destroySession(String sessionId) throws RuntimeException {

		try {
			return terraJava.destroySession(sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Não foi possível destruir a sessão do usuário.", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Não foi possível destruir a sessão do usuário.", e);
		}
	}

	/* ******GETS***** */

	// ViewSet Construction Methods
	public Vector<HashMap> getViewSet(String dbUsername, Boolean forceReload,
			String sessionId) throws RuntimeException {

		try {
			return terraJava.getViewSet(dbUsername, forceReload, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Não foi possível recuperar a lista de vista, temas e layers utilizando o serviço.",
					e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Não foi possível recuperar a lista de vista, temas e layers utilizando o serviço.",
					e);
		}
	}

	public Vector<HashMap> getViewSetTree(Boolean forceReload, String sessionId)
			throws RuntimeException {

		try {
			return terraJava.getViewSetTree(forceReload, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao recuperar metadados. ");
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao recuperar metadados. ");
		}
	}

	public HashMap getThemesBox(Vector themesIds, String sessionaId)
			throws RuntimeException {

		try {
			return terraJava.getThemesBox(themesIds, sessionaId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException();
		} catch (InstantiationException e) {
			throw new RuntimeException();
		}

	}

	public Vector getThemeVisual(Integer rep, String sessionId)
			throws RuntimeException {

		try {
			return terraJava.getThemeVisual(rep, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar o visual. ", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar o visual. ", e);
		}
	}

	public Vector getLayerSet(Boolean forceReload, String sessionId)
			throws RuntimeException {

		try {
			return terraJava.getLayerSet(forceReload, true, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar o conjunto de camadas.", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar o conjunto de camadas.", e);
		}
	}

	public Vector getLayersName(String sessionId) throws RuntimeException {

		try {
			return terraJava.getLayersName(sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao recuperar camada de nomes. ", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao recuperar camada de nomes. ", e);
		}
	}

	public Integer getThemeRepresentation(Integer themeType, String sessionId)
			throws RuntimeException {

		try {
			return terraJava.getThemeRepresentation(themeType, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar representação do tema. ", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar representação do tema.", e);
		}
	}

	public HashMap getThemeScaleLimit(String sessionId) throws RuntimeException {

		try {
			return terraJava.getThemeScaleLimit(sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar limite da escala do tema. ", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar limite da escala do tema. ", e);
		}
	}

	public Vector<Integer> getThemes2Plot(String sessionId)
			throws RuntimeException {
		try {
			return terraJava.getThemesToPlot(sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar a lista de Id's dos temas que podem ser plotados na BBox e escala atuais. ",
					e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar a lista de Id's dos temas que podem ser plotados na BBox e escala atuais. ",
					e);
		}
	}

	public Vector getDefaultVisual(Integer rep, String sessionId)
			throws RuntimeException {

		try {
			return terraJava.getDefaultVisual(rep, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Não foi possível retornar o visual do tema.", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Não foi possível retornar o visual do tema.", e);
		}
	}

	// Ploting Gets Methods
	public byte[] getCanvasImage(Integer type, Boolean opaque, Integer quality,
			String sessionId) throws RuntimeException {
		try {
			return terraJava.getCanvasImage(type, opaque, quality, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao gerar a imagem da área do desenho. ", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao gerar a imagem da área do desenho. ", e);
		}
	}

	public byte[] getLegendImage(Integer type, Boolean b, Integer opacity,
			String sessionId) throws RuntimeException {
		try {
			return terraJava.getLegendImage(type, b, opacity, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar a legenda formato .PNG. ", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar a legenda formato .PNG. ", e);
		}
	}

	public Vector getCurrentViewBox(String sessionId) throws RuntimeException {
		try {
			return terraJava.getCurrentViewBox(sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar coordenadas do retângulo que envolve o conteúdo da vista corrente. ",
					e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar coordenadas do retângulo que envolve o conteúdo da vista corrente. ",
					e);
		}
	}

	public Vector getCurrentThemeBox(Integer themeType, String restriction,
			String sessionId) throws RuntimeException {
		try {
			return terraJava.getThemeBox(themeType, restriction, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar o box do tema corrente. ", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar o box do tema corrente. ", e);
		}
	}

	/* ******SETS***** */

	// Control Methods

	public Boolean setThemesPriorityOrder(Vector<Integer> themesIds,
			Boolean persisit, String sessionId) throws RuntimeException {
		try {
			return terraJava.setThemesPriorityOrder(themesIds, persisit,
					sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir a prioridade do tema", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir a prioridade do tema", e);
		}
	}

	public void setAutomaticScaleControlEnable(Boolean enable, String sessionId)
			throws RuntimeException {
		try {
			terraJava.setAutomaticScaleControlEnable(enable, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao ativar o controle de escala. ", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao ativar o controle de escala. ", e);
		}
	}

	public Boolean setCurrentView(String currentView, String userName,
			String sessionId) throws RuntimeException {
		try {
			return terraJava.setCurrentView(currentView, userName, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Ocorreu um erro ao configurar vista.",
					e);
		} catch (InstantiationException e) {
			throw new RuntimeException("Ocorreu um erro ao configurar vista.",
					e);
		}
	}

	public Boolean setTheme(String themeName, Integer themeType,
			String sessionId) throws RuntimeException {
		try {
			return terraJava.setTheme(themeName, themeType, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir tema como corrente. ", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir tema como corrente. ", e);
		}
	}

	public void setDrawTextRepresentation(Boolean drawText, String sessionId)
			throws RuntimeException {

		try {
			terraJava.setDrawTextRepresentation(drawText, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao ativar representação de texto. ", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao ativar representação de texto. ", e);
		}
	}

	public Boolean setThemesVisibility(Vector<HashMap> themesVec,
			Boolean persist, String sessionId) throws RuntimeException {

		try {
			return terraJava.setThemesVisibility(themesVec, persist, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreuu um erro ao persistir os dados. ", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreuu um erro ao persistir os dados. ", e);
		}

	}

	public Boolean setWorkProjection(HashMap<String, Object> projection,
			String sessionId) throws RuntimeException {
		//{projOffy=0.0, projOffx=0.0, projUnits=DecimalDegrees, projDatum=SAD69, projName=LatLong, projNorthHemisphere=false, projStLat2=0.0, projLon0=0.0, projStLat1=0.0, projScale=1.0, projLat0=0.0}
		//inacio
		if(projection.get("projName").equals("ESRI"))
			projection.put("projName", "LatLong");

		try {
			terraJava.setWorkProjection(projection, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao tentar retornar o tema. ", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao tentar retornar o tema. ", e);
		}
		return true;

	}

	public Boolean setThemeScaleLimit(Double minScale, Double maxScale,
			Boolean persisScaleLimit, String sessionId) throws RuntimeException {

		try {
			return terraJava.setThemeScaleLimit(minScale, maxScale,
					persisScaleLimit, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir o limite da escala. ", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir o limite da escala. ", e);
		}

	}

	public void setCanvasBackgroundColor(Integer r, Integer g, Integer b,
			String sessionId) throws RuntimeException {
		try {
			terraJava.setCanvasBackgroundColor(r, g, b, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir a cor de fundo.", e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir a cor de fundo.", e);
		}
	}

	public void setWorld(Double xmin, Double ymin, Double xmax, Double ymax,
			Integer width, Integer height, String sessionId)
			throws RuntimeException {
		try {
			terraJava.setWorld(xmin, ymin, xmax, ymax, width, height, true, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar a lista de Id's dos temas. ",
					e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar a lista de Id's dos temas. ",
					e);
		}
	}

	public void setTextOutLineEnable(Boolean turnon, String sessionId)
			throws RuntimeException {
		try {
			terraJava.setTextOutLineEnable(turnon, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir se o texto de rótulo estático desenhado será desenhado com borda. ",
					e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir se o texto de rótulo estático desenhado será desenhado com borda. ",
					e);
		}
	}

	// Visual Methods
	public Boolean setThemeVisualText(Integer red, Integer green, Integer blue,
			Integer redContour, Integer greenContour, Integer blueContour,
			Integer size, String familyPath, Boolean bold, Boolean italic,
			Double alignmentVert, Double alignmentHoriz, Integer tabSize,
			Integer lineSpace, Boolean persistence, String sessionId)
			throws RuntimeException {

		try {
			return terraJava.setThemeVisualText(red, green, blue, redContour,
					greenContour, blueContour, size, familyPath, bold, italic,
					alignmentVert, alignmentHoriz, tabSize, lineSpace,
					persistence, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao persistir nome no tema. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao persistir nome no tema. ", e);

		}

	}

	public Boolean setThemeVisualPoint(Integer styleId, Integer red,
			Integer green, Integer blue, Integer size, Boolean persistence,
			String sessionId) throws RuntimeException {

		try {
			return terraJava.setThemeVisualPoint(styleId, red, green, blue,
					size, persistence, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Ocorreu um erro ao persistir ponto. ",
					e);

		} catch (InstantiationException e) {
			throw new RuntimeException("Ocorreu um erro ao persistir ponto. ",
					e);

		}

	}

	public Boolean setThemeVisualLine(Integer styleId, Integer red,
			Integer green, Integer blue, Integer transparency, Integer width,
			Boolean persistence, String sessionId) throws RuntimeException {

		try {
			return terraJava.setThemeVisualLine(styleId, red, green, blue,
					transparency, width, persistence, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Ocorreu um erro ao persistir linha. ",
					e);

		} catch (InstantiationException e) {
			throw new RuntimeException("Ocorreu um erro ao persistir linha. ",
					e);

		}

	}

	public Boolean setThemeVisualPolygon(Integer jstyleId, Integer jred,
			Integer jgreen, Integer jblue, Integer jtransparency,
			Integer jcontourStyleId, Integer jredContour,
			Integer jgreenContour, Integer jblueContour,
			Integer jtransparencyContour, Integer jwidthContour,
			Boolean jpersistence, String jsessionId) throws RuntimeException {

		try {
			return terraJava.setThemeVisualPolygon(jstyleId, jred, jgreen,
					jblue, jtransparency, jcontourStyleId, jredContour,
					jgreenContour, jblueContour, jtransparencyContour,
					jwidthContour, jpersistence, jsessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao persistir polígono no tema. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao persistir polígono no tema. ", e);

		}
	}

	public Boolean setDefaultVisual(Integer rep, Integer red, Integer green,
			Integer blue, Integer style, Integer width, String fontName,
			Integer redCountour, Integer greenCountour, Integer blueCountour,
			Integer styleCountour, Integer widthCountour, String sessionId)
			throws RuntimeException {

		try {
			return terraJava.setDefaultVisual(rep, red, green, blue, style,
					width, fontName, redCountour, greenCountour, blueCountour,
					styleCountour, widthCountour, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao persistir visual no tema. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao persistir visual no tema. ", e);

		}
	}

	// Creation and Removing Methods

	public void saveCurrentView(String path, String sessionId)
			throws RuntimeException {
		try {
			terraJava.saveCurrentView(path, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao salvar a vista corrente. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao salvar a vista corrente. ", e);

		}
	}

	public void saveThemeToFile(String path, Vector<String> attrList, String sessionId)
			throws RuntimeException {
		try {
			terraJava.saveThemeToFile(path, attrList, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao salvar o tema para arquivo. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao salvar o tema para arquivo. ", e);

		}
	}
	
	public Boolean saveView2DB(String name, String user, String sessionId)
			throws RuntimeException {
		try {
			return terraJava.saveView2DB(name, user, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Ocorreu um erro ao salvar a vista. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException("Ocorreu um erro ao salvar a vista. ", e);

		}
	}

	public Boolean createThemeGroup(String themeName, Integer themeType,
			String sessionId) throws RuntimeException {
		try {
			return terraJava.createThemeGroup(themeName, themeType, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao criar tema no banco de dados. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao criar tema no banco de dados. ", e);

		}
	}

	public Boolean createViewMem(String viewName, String userName,
			Vector<Integer> themesIds, HashMap<String, Object> projectionMap,
			String sessionId) throws RuntimeException {
		try {
			return terraJava.createViewMem(viewName, userName, themesIds,
					projectionMap, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao manipular vista na memória do TerraManager",
					e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao manipular vista na memória do TerraManager",
					e);
		}
	}

	public Boolean removeViewMem(String viewName, String userName,
			String sessionId) throws RuntimeException {
		try {
			return terraJava.removeViewMem(viewName, userName, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao remover a vista em memória solicitada.",
					e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao remover a vista em memória solicitada.",
					e);
		}
	}

	// este metodo era assim: public Boolean createFileTheme(String
	// themeName,String path,HashMap<String,Object> projectionMap,String
	// sessionId){
	public Boolean createFileTheme(String themeName, String path,
			Integer parentId, HashMap<String, Object> projectionMap,
			String sessionId) throws RuntimeException {
		try {
			return terraJava.createFileTheme(themeName, path, parentId,
					projectionMap, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao criar nome de tema.", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao criar nome de tema.", e);

		}

	}

	public void loadTheme2View(Integer themeId, String sessionId)
			throws RuntimeException {

		try {
			terraJava.loadTheme2View(themeId, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Ocorreu um erro ao copiar o tema. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException("Ocorreu um erro ao copiar o tema. ", e);

		}

	}

	public Boolean removeThemeMem(Integer themeId, String sessionId)
			throws RuntimeException { // throws
		// TerraJavaDAOException;

		try {
			return terraJava.removeThemeMem(themeId, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao remover tema da memória. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao remover tema da memória. ", e);

		}
	}

	public Boolean createView(String viewName, String username, String sessionId)
			throws RuntimeException {

		try {
			return terraJava.createView(viewName, username, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Ocorreu um erro ao criar vista. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException("Ocorreu um erro ao criar vista. ", e);

		}
	}

	public Boolean removeView(String sessionId) throws RuntimeException {

		try {
			return terraJava.removeView(sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Não foi possível remover a vista solicitada utilizando o serviço.",
					e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Não foi possível remover a vista solicitada utilizando o serviço.",
					e);

		}
	}

	public Boolean createTheme(String newThemeName, String layerName,
			String sessionId) throws RuntimeException {

		try {
			return terraJava.createTheme(newThemeName, layerName, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Ocorreu um erro ao criar tema. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException("Ocorreu um erro ao criar tema. ", e);

		}
	}

	public Boolean createTheme(String newThemeName, String layerName,
			Integer parentID, String restriction, String sessionId)
			throws RuntimeException {

		try {
			return terraJava.createTheme(newThemeName, layerName, parentID,
					restriction, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Ocorreu um erro ao criar tema. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException("Ocorreu um erro ao criar tema. ", e);

		}
	}

	public Boolean removeTheme(String sessionId) throws RuntimeException {

		try {
			return terraJava.removeTheme(sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Ocorreu um erro ao remover o tema. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException("Ocorreu um erro ao remover o tema. ", e);

		}
	}

	// Updating Methods
	public Boolean renameThemeMem(String themeNewName, String sessionId)
			throws RuntimeException {
		/*
		 * este mï¿½todo era assim: public Boolean renameThemeMem(Integer
		 * themeId, String themeNewName, String sessionId){
		 */
		try {
			return terraJava.renameThemeMem(themeNewName, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Ocorreu um erro ao renomear tema. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException("Ocorreu um erro ao renomear tema. ", e);

		}
	}

	public Boolean updateView(String viewName, String sessionId)
			throws RuntimeException {

		try {
			return terraJava.updateView(viewName, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Não foi possível atualizar a vista solicitada utilizando o serviço.",
					e);
		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Não foi possível atualizar a vista solicitada utilizando o serviço.",
					e);
		}
	}

	public Boolean updateViewName(String newViewName, String sessionId)
			throws RuntimeException {

		try {
			return terraJava.updateViewName(newViewName, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao atualizar o nome da vista. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao atualizar o nome da vista. ", e);

		}
	}

	public Boolean updateThemeName(String newThemeName, String sessionId)
			throws RuntimeException {

		try {
			return terraJava.updateTheme(newThemeName, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao atualizar o nome do tema. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao atualizar o nome do tema. ", e);

		}
	}

	// Plot Methods
	public void drawCurrentTheme(String sessionId) throws RuntimeException {
		try {
			terraJava.drawCurrentTheme(sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar tema corrente. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar tema corrente. ", e);

		}
	}

	public Vector<Object> remapCoordinates(Vector<Object> coordsList,
			HashMap<String, Object> dataProjectionMap,
			HashMap<String, Object> destinationProjectionMap, String sessionId)
			throws RuntimeException {
		//inacio
		if(destinationProjectionMap.get("projName").equals("ESRI"))
			destinationProjectionMap.put("projName", "LatLong");

		try {
			return terraJava.remapCoordinates(coordsList, dataProjectionMap,
					destinationProjectionMap, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Não foi possível remapear as coordenadas da nova projeção",
					e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Não foi possível remapear as coordenadas da nova projeção",
					e);

		}
	}

	public boolean loadNetwork(int layerId, String sessionId) {
		return false;
		// return terraJava.loadNetwork(layerId, sessionId);
	}

	public List<String> locateObject(double x, double y, String sessionId)
			throws RuntimeException {
		Vector<String> vectorIDs;
		try {
			vectorIDs = terraJava.locateObject(x, y, 12.0, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao realizar a consulta. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao realizar a consulta. ", e);

		}
		return vectorIDs;
	}

	public void setCustomGroupParameters(String legendName, Vector<Integer> r,
			Vector<Integer> g, Vector<Integer> b, Vector minList,
			Vector maxList, Vector descList, Vector numObjList, String sessionId)
			throws RuntimeException {
		try {
			terraJava.setCustomGroupParameters(legendName, r, g, b, minList,
					maxList, descList, numObjList, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir os parâmetros para agrupamento. ",
					e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir os parâmetros para agrupamento. ",
					e);

		}

	}

	public void setLabelField(String fieldName, String sessionId)
			throws RuntimeException {
		try {
			terraJava.setLabelField(fieldName, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir a coluna da tabela de atributo. ",
					e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir a coluna da tabela de atributos. ",
					e);

		}
	}

	public boolean setDefaultVisual(int rep, int red, int green, int blue,
			int style, int width, String fontName, int rcontour, int gcontour,
			int bcontour, int stylecontour, int widthcontour, String sessionId)
			throws RuntimeException {

		try {
			return terraJava.setDefaultVisual(rep, red, green, blue, style,
					width, fontName, rcontour, gcontour, bcontour,
					stylecontour, widthcontour, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir o visual corrente. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir o visual corrente. ", e);

		}
	}

	public void setPriorityField(String fieldName, String sessionId)
			throws RuntimeException {
		try {
			terraJava.setPriorityField(fieldName, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir a prioridade do campo. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir a prioridade do campo. ", e);

		}
	}

	public void setMinCollisionTolerance(int numPixels, String sessionId)
			throws RuntimeException {
		try {
			terraJava.setMinCollisionTolerance(numPixels, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir a tolerância de colisão. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir a tolerância de colisão. ", e);

		}
	}

	public void setConflictDetect(boolean conflictDetect, String sessionId)
			throws RuntimeException {
		try {
			terraJava.setConflictDetect(conflictDetect, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao desabilitar a detecção de conflito", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao desabilitar a detecção de conflito", e);

		}
	}

	public void drawLineAngleTextLabeling(String sessionId)
			throws RuntimeException {
		try {
			terraJava.drawLineAngleTextLabeling(sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir rótulo de texto sobre a geometria. ",
					e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir rótulo de texto sobre a geometria. ",
					e);

		}

	}

	public void setTextOutLineColor(int r, int g, int b, String sessionId)
			throws RuntimeException {
		try {
			terraJava.setTextOutLineColor(r, g, b, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir a cor da borda do texto. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir a cor da borda do texto. ", e);

		}
	}

	public Vector drawGroupSql(int typeFaixa, String colFaixa,
			String tabelaInfo, String colLigacao, String whereClause, int i,
			int j, boolean b, boolean c, boolean d, int k, int l,
			String sessionId) throws RuntimeException {

		try {
			return terraJava.drawGroupSql(typeFaixa, colFaixa, tabelaInfo,
					colLigacao, whereClause, i, j, b, c, d, k, l, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir o número de casas decimais consideradas usada na apresentação dos intervalos de cada faixa gerada.",
					e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao definir o número de casas decimais consideradas usada na apresentação dos intervalos de cada faixa gerada.",
					e);

		}
	}

	public void saveCanvasImage(String imagePath, String sessionId)
			throws RuntimeException {
		try {
			terraJava.saveCanvasImage(imagePath, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao salvar a imagem em disco", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao salvar a imagem em disco", e);

		}
	}

	public void setDefaultVisual(int rep, int red, int green, int blue,
			int style, int width, String fontName, String sessionId)
			throws RuntimeException {
		try {
			terraJava.setDefaultVisual(rep, red, green, blue, style, width,
					fontName, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao ajustar o visual da representação geométrica a ser desenhada.",
					e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao ajustar o visual da representação geométrica a ser desenhada.",
					e);

		}
	}

	public void drawLegends(Vector<Vector> legends,
			Vector<String> legendTitles, int i, int j, boolean b, boolean c,
			String sessionId) throws RuntimeException {
		try {
			terraJava.drawLegends(legends, legendTitles, i, j, b, c, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao desenhar legendas. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao desenhar legendas. ", e);

		}
	}

	public void drawSelectedObjects(Vector objArray, String sessionId)
			throws RuntimeException {
		try {
			terraJava.drawSelectedObjects(objArray, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao tentar desenhar o(s) objeto(s). ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao tentar desenhar o(s) objeto(s). ", e);

		}
	}

	public Vector fetchAttributes(String objectid, int themeType,
			String sessionId) throws RuntimeException {
		try {
			return terraJava.fetchAttributes(objectid, themeType, sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar lista de atributos do objeto. ",
					e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar lista de atributos do objeto. ",
					e);

		}
	}
	
	public byte[] drawLegendThemes(Vector themesList, HashMap<String, Object> visualText, HashMap<String, Integer> canvasBackgroundColor,
			int imageType, boolean opaque,
			int quality, int width) throws RuntimeException {
		try {
			byte[] b = terraJava.drawLegendThemes(themesList, visualText, canvasBackgroundColor, width,
					imageType, opaque, quality);
			System.err.println("TerraJavaJNI> drawLegendThemes: b.length = "+b.length);
			return b;
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao desenhar legenda dos temas. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao desenhar legenda dos temas. ", e);

		}
	}

	public byte[] drawThemes(Vector themesList, double x1, double y1,
			double x2, double y2, int width, int height,
			boolean keepAspectRatio, int imageType, boolean opaque,
			int quality, HashMap projectionMap,
			HashMap<String, Integer> canvasBackgroundColor,
			boolean useScaleControl) throws RuntimeException {
		try {
			return terraJava.drawThemes(themesList, x1, y1, x2, y2, width,
					height, keepAspectRatio, imageType, opaque, quality,
					projectionMap, canvasBackgroundColor, useScaleControl);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao desenhar lista de temas. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao desenhar lista de temas. ", e);

		}
	}

	public byte[] getRasterMatrix(double x1, double y1, double x2, double y2,
			int width, int height, String sessionId) throws RuntimeException {
		try {

			double[][] matrix = terraJava.getRasterMatrix(x1, y1, x2, y2, width,
					height, sessionId);
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
			objectOut.writeObject(matrix);
			objectOut.flush();
			objectOut.close();
			byteOut.close();
			byte[] data = byteOut.toByteArray();
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Ocorreu um erro ao retornar matriz do TRaster. ", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar matriz do TRaster. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao retornar matriz do TRaster. ", e);

		}
	}

	public boolean updateThemeBox(String sessionId) throws RuntimeException {
		try {
			return terraJava.updateThemeBox(sessionId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Ocorreu um erro ao atualizar Box. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException("Ocorreu um erro ao atualizar Box. ", e);

		}
	}
	
	public byte[] drawThemeText(HashMap themesMap, double x1, double y1,
			double x2, double y2, int width, int height,
			boolean keepAspectRatio, int imageType, boolean opaque,
			int quality, HashMap projectionMap,
			HashMap<String, Integer> canvasBackgroundColor,
			boolean useScaleControl) throws RuntimeException {
		try {
			return terraJava.drawThemeText(themesMap, x1, y1, x2, y2, width,
					height, keepAspectRatio, imageType, opaque, quality,
					projectionMap, canvasBackgroundColor, useScaleControl);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao desenhar texto. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao desenhar texto. ", e);

		}
	}
	
	public boolean createLayer(String layerName, HashMap<String, Object> projectionHashMap, 
			Vector attList, double x1, double y1, double x2, double y2, Vector geomRepVec,
			String sessionId) throws RuntimeException{
		try{
			return terraJava.createLayer(layerName, projectionHashMap, attList, x1, y1, x2, y2, geomRepVec, sessionId);
		}catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao criar layer. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao criar layer. ", e);

		}
	}
	
	public Vector<String> addFeatures(Vector<String> geometries, String sessionId){
		try{
			return terraJava.addFeatures(geometries, sessionId);
		}catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao salvar as geometrias. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao salvar as geometrias. ", e);

		}
	}
	
	public boolean updateFeatures(Vector<String> geometries, String sessionId){
		try{
			return terraJava.updateFeatures(geometries, sessionId);
		}catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao salvar as geometrias. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao salvar as geometrias. ", e);

		}
	}
	
	public Vector<String> getFeaturesByIds(Vector<String> objectIds, String sessionId){
		try{
			return terraJava.getFeaturesByIds(objectIds, sessionId);
		}catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao recuperar as geometrias. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao recuperar as geometrias. ", e);

		}
	}
	
	public Vector<String> getFeaturesWithRestriction(String restriction, String sessionId){
		try{
			return terraJava.getFeaturesWithRestriction(restriction, sessionId);
		}catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao recuperar as geometrias. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao recuperar as geometrias. ", e);

		}
	}
	
	public Vector<String> getFeaturesInBox(double x1, double y1, double x2, double y2, String sessionId){
		try{
			return terraJava.getFeaturesInBox(x1, y1, x2, y2, sessionId);
			
		}catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao recuperar as geometrias. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao recuperar as geometrias. ", e);

		}
	}

	public Vector<String> getFeaturesIds(String sessionId) {
		try{
			return terraJava.getFeaturesIds(sessionId);
		}catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao recuperar as geometrias. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao recuperar as geometrias. ", e);

		}
	}
	
	public boolean deleteGeometries(Vector<String> geomIds, String sessionId) {
		try{
			return terraJava.deleteGeometries(geomIds, sessionId);
		}catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao recuperar as geometrias. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao recuperar as geometrias. ", e);

		}
	}

	public boolean deleteFeatures(Vector<String> listIds, String sessionId) {
		try{
			return terraJava.deleteFeatures(listIds, sessionId);
		}catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao recuperar as geometrias. ", e);

		} catch (InstantiationException e) {
			throw new RuntimeException(
					"Ocorreu um erro ao recuperar as geometrias. ", e);

		}
	}
	
}