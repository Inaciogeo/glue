package br.org.funcate.glue.service;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import br.org.funcate.glue.exception.DailyExceptionLogger;
import br.org.funcate.glue.model.Attribute;
import br.org.funcate.glue.model.AttributeTable;
import br.org.funcate.glue.model.Box;
import br.org.funcate.glue.model.ContextToGroupMap;
import br.org.funcate.glue.model.Coord;
import br.org.funcate.glue.model.CustomGroupParameters;
import br.org.funcate.glue.model.Database;
import br.org.funcate.glue.model.FilterAttribute;
import br.org.funcate.glue.model.Layer;
import br.org.funcate.glue.model.Projection;
import br.org.funcate.glue.model.Representation;
import br.org.funcate.glue.model.ScaleLimit;
import br.org.funcate.glue.model.Theme;
import br.org.funcate.glue.model.ThemeToPlot;
import br.org.funcate.glue.model.ThemeType;
import br.org.funcate.glue.model.ThemeVisual;
import br.org.funcate.glue.model.UserType;
import br.org.funcate.glue.model.View;
import br.org.funcate.glue.model.ViewSet;
import br.org.funcate.glue.model.ViewToPlot;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.service.utils.AttributeService;
import br.org.funcate.glue.service.utils.DatabaseConnection;
import br.org.funcate.glue.service.utils.GoogleContinent;
import br.org.funcate.glue.utilities.PropertiesReader;
import br.org.funcate.glue.utilities.VisualUtils;

/**
 * @author Siqueira, Felipe V. and Ribeiro, Stephen M. and Rocha, Fernanda.
 * 
 *         \brief Class that contains the TerraJava services
 */

public class TerraJavaClient {

	private static TerraJavaJNI terraJava;

	/**
	 * @author Oliveira, André G.
	 * 
	 *         \brief Function that receives a long that represents an ip
	 *         address and returns a string that represents the continent with
	 *         that ip
	 * @return A string containing a continent's name
	 * @throws GlueServerException
	 */

	public static String getContinent(Long IP) throws GlueServerException {
		try {
			return GoogleContinent.getContinent(IP);
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	/** <TerraJavaJNI type attribute > */

	private Database database;

	/** <Database type attribute > */

	private final String userName;

	private final UserType userType;

	/**
	 * @name General purpose
	 * 
	 *       Functions of several terraJava features
	 */

	// @ {

	private final String sessionId;

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method to return the TerraJava object
	 * @return A TerraJavaJNI instance
	 */

	public TerraJavaClient() {
		sessionId = "1";
		userType = UserType.ADMIN;

		database = /* dbControll.getDatabase(); */DatabaseConnection
				.getDatabase(userType.toString());
		userName = database.getUser();
		try {
			connect(database);
		} catch (GlueServerException e) {
			e.printStackTrace();
		}
	}

	public Vector<String> addFeatures(List<String> listGeometries) {
		Vector<String> vecGeom = new Vector<String>(listGeometries);
		return terraJava.addFeatures(vecGeom, sessionId);
	}

	/**
	 * @author jose.thomazini Writes a geometry (point, line, polygon) to a
	 *         given layer.
	 * @param representation
	 * @param verticeList
	 * @param attrList
	 * @param layerName
	 * @param projectionHashMap
	 * @return true if the geometry was correctly added, GlueServerException
	 *         otherwise
	 * @throws GlueServerExceptionHandler
	 */

	public boolean addGeometry(int representation, Vector<Object> verticeList,
			Vector<Object> attrList, String layerName, Projection projection)
			throws GlueServerException {

		if (terraJava == null) {
			terraJava = new TerraJavaJNI();
		}
		try {
			Vector<Object> vectorHashVertice = new Vector<Object>();
			HashMap<String, Object> verticeMap = new HashMap<String, Object>();
			verticeMap.put("x", verticeList.get(0));
			verticeMap.put("y", verticeList.get(1));
			vectorHashVertice.add(verticeMap);

			Vector<Object> vectorHashAttr = new Vector<Object>();

			for (int i = 0; i < attrList.size(); i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("key", attrList.get(i));
				map.put("value", attrList.get(++i));
				vectorHashAttr.add(map);
			}

			return terraJava.addGeometry(representation, vectorHashVertice,
					vectorHashAttr, layerName,
					getHashMapFromProjection(projection), sessionId);
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	public boolean buildCollection(String objectId, String viewName,
			String themeName) {

		if (terraJava == null) {
			terraJava = new TerraJavaJNI();
		}
		try {
			terraJava.setCurrentView(viewName, userName, sessionId);
			terraJava.setTheme(themeName, 0, sessionId);
			return terraJava.buildCollection(objectId, sessionId);
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			return false;
		}

	}

	/**
	 * @author Ribeiro, Stephen M.
	 * 
	 *         \brief Method to configure the TerraManger canvas in order to
	 *         plot
	 */

	public Boolean configCanvas(String currentView, Projection projection,
			Color color) throws GlueServerException {

		try {

			// Setting the backgroung color and the view to plot
			terraJava.setCanvasBackgroundColor(color.getRed(),
					color.getGreen(), color.getBlue(), sessionId);
			terraJava.setCurrentView(currentView, userName, sessionId);

			// Changing the current TerraManager Canvas Projection

			terraJava.setWorkProjection(getHashMapFromProjection(projection),sessionId);

		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}

		return true;
	}

	/**
	 * @author Oliveira, Andrï¿½ G. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that reconfigures a theme
	 * @throws GlueServerException
	 */

	private Boolean configureTheme(String sessionId, Boolean persist,
			Theme oldTheme, Theme newTheme) throws GlueServerException {

		try {

			Boolean result = true;

			// Remove theme if theme doesn't exist anymore
			if (newTheme == null) {
				System.out
						.println("UPDATEVIEW -> *TEMA APAGADO, REMOVER TEMA*");

				result = terraJava.setTheme(oldTheme.getName(),
						oldTheme.getType(), sessionId);
				if (persist) {
					if (oldTheme.getId() < 0l) {
						result = terraJava.removeThemeMem(
								Integer.parseInt(oldTheme.getId().toString()),
								sessionId);
					} else {
						result = terraJava.removeTheme(sessionId);
					}
				} else {
					result = terraJava.removeThemeMem(
							Integer.parseInt(oldTheme.getId().toString()),
							sessionId);
				}
			}

			// If its a new or existing theme, configure the theme
			else {

				// Create theme if there's a new theme on new view
				if (oldTheme == null || (oldTheme.getId() < 0l && persist)) {

					System.out
							.println("UPDATEVIEW -> *TEMA CRIADO, CRIAR TEMA*");
					if (persist) {
						// Removing the existing memory theme before creating
						// the
						// new theme
						if (oldTheme != null) {
							if (oldTheme.getId() < 0l) {
								result = terraJava.removeThemeMem(Integer
										.parseInt(oldTheme.getId().toString()),
										sessionId);
							}
						}
						if (newTheme.getType() == ThemeType.NETWORK_THEME
								.ordinal())
							return true;
						result = terraJava.createTheme(newTheme.getName(),
								newTheme.getLayer().getName(), sessionId);
					} else {
						terraJava.loadTheme2View(
								Integer.parseInt(newTheme.getId().toString()),
								sessionId);
						return true;
					}

					result = terraJava.setTheme(newTheme.getName(),
							newTheme.getType(), sessionId);

				}

				// Configure theme if already exists
				else {

					result = terraJava.setTheme(oldTheme.getName(),
							oldTheme.getType(), sessionId);

					// If the theme name has changed, do this:
					if (isThemeNameChanged(oldTheme, newTheme)) {
						System.out
								.println("UPDATEVIEW -> *TEMA EXISTENTE, NOME DIFERENTE*");

						result = terraJava.setTheme(oldTheme.getName(),
								oldTheme.getType(), sessionId);
						if (persist) {
							result = terraJava.updateThemeName(
									newTheme.getName(), sessionId);
						} else {
							result = terraJava.renameThemeMem(
									newTheme.getName(), sessionId);
						}
					}
				}

				System.out.println("UPDATEVIEW -> *CONFIGURAR TEMA*");

				// Scale limits configuration
				Boolean scaleLimitChanged = newTheme.isIsScalelimitChanged();
				if (scaleLimitChanged != null) {
					ScaleLimit scaleLimit = newTheme.getScaleLimit();
					if ((scaleLimitChanged || persist) && scaleLimit != null) {
						result = terraJava.setThemeScaleLimit(newTheme
								.getScaleLimit().getMin(), newTheme
								.getScaleLimit().getMax(), persist, sessionId);
					}
				}
				// Visual configurations
				if (newTheme.isIsVisualChanged() != null) {
					if ((newTheme.isIsVisualChanged() || persist)
							&& newTheme.getThemeVisuals() != null
							&& newTheme.getThemeVisuals().size() > 0) {
						result = configureThemeVisual(sessionId, persist,
								newTheme);
					}
				}
			}
			return result;

		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}

	}

	/**
	 * @author Oliveira, Andrï¿½ G. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that reconfigures a theme visual
	 * @throws GlueServerException
	 */

	private Boolean configureThemeVisual(String sessionId, Boolean persist,
			Theme newTheme) throws GlueServerException {

		try {

			Boolean result = false;

			for (int j = 0; j < newTheme.getReps().size(); j++) {
				String repType = newTheme.getReps().get(j).getName();

				switch (Integer.parseInt(repType)) {
				case 1:
					result = setThemeVisualPolygon(newTheme.getThemeVisuals()
							.get(j), persist, sessionId);
					break;
				case 2:
					result = setThemeVisualLine(
							newTheme.getThemeVisuals().get(j), persist,
							sessionId);
					break;
				case 4:
					result = setThemeVisualPoint(newTheme.getThemeVisuals()
							.get(j), persist, sessionId);
					break;
				case 128:
					result = setThemeVisualText(
							newTheme.getThemeVisuals().get(j), persist,
							sessionId);
					break;
				case 256:
					// Implents in the future
					break;
				case 512:
					// Implents in the future
					break;

				default:
					break;
				}
			}
			return result;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	/**
	 * @author Oliveira, Andrï¿½ G. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that reconfigures a view
	 * @throws GlueServerException
	 */

	private Boolean configureView(Boolean persist, View _newView,
			View _oldView, Integer nNewViewThemes, Integer nOldViewThemes)
			throws GlueServerException {

		try {

			Boolean result = false;

			// Creating a new view
			if (_oldView == null || (_oldView.getId() < 0l && persist)) {

				if (persist) {

					System.out.println("UPDATEVIEW -> *VISTA NOVA*");

					// Remove the memory view before creating a persisted view
					if (_oldView != null && _oldView.getId() < 0l) {

						// _terraJSP.saveView2DB(_oldView.getName(), userName,
						// sessionId);

						result = terraJava.setCurrentView(_oldView.getName(),
								userName, sessionId);
						result = terraJava.removeViewMem(_oldView.getName(),
								userName, sessionId);

					}

					result = terraJava.createView(_newView.getName(), userName,
							sessionId);
					result = terraJava.setCurrentView(_newView.getName(),
							userName, sessionId);
				} else {

					System.out
							.println("UPDATEVIEWMEMORY -> *VISTA MEMï¿½RIA NOVA*");
					result = terraJava.createViewMem(_newView.getName(),
							userName, null, null, sessionId);
					result = terraJava.setCurrentView(_newView.getName(),
							userName, sessionId);
				}
			}
			// Updating an existing view
			else {
				System.out.println("UPDATEVIEW -> *VISTA EXISTENTE*");

				result = terraJava.setCurrentView(_oldView.getName(), userName,
						sessionId);

				// If the view exists in the database, but it's name is changed,
				// do
				// this:
				if (!_newView.getName().equals(_oldView.getName())) {
					System.out.println("UPDATEVIEW -> *NOME DIFERENTE*");
					if (persist) {
						result = terraJava.updateViewName(_newView.getName(),
								sessionId);
						_oldView.setName(_newView.getName());
					} else {
						if (_oldView.getId() < 0l) {
							result = terraJava.updateViewName(
									_newView.getName(), sessionId);
							_oldView.setName(_newView.getName());
						}
					}
				}
			}

			// CREATING AND CONFIGURING THEMES
			for (int i = 0; i < nNewViewThemes; i++) {

				// If the theme already exists in the database, do this:
				Integer pos = findThemeOnView(_oldView, _newView.getThemes()
						.get(i).getId());
				if (pos != null) {

					result = configureTheme(sessionId, persist, _oldView
							.getThemes().get(pos), _newView.getThemes().get(i));
				}

				// If the theme does not exists in the database, do this:
				else {

					System.out.println("UPDATEVIEW -> *TEMA NAO EXISTENTE*");

					result = configureTheme(sessionId, persist, null, _newView
							.getThemes().get(i));
				}
			}

			// REMOVING THEMES
			for (int i = 0; i < nOldViewThemes; i++) {

				// If the theme no longer exists in the new view, do this:
				if (findThemeOnView(_newView, _oldView.getThemes().get(i)
						.getId()) == null) {

					result = terraJava.setCurrentView(_oldView.getName(),
							userName, sessionId);
					result = configureTheme(sessionId, persist, _oldView
							.getThemes().get(i), null);
				}
			}
			return result;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());

		}
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M. and Rocha, Fernanda.
	 * 
	 *         \brief Method to connect with database
	 */

	private void connect(Database database) throws GlueServerException {
		try {
			if (terraJava == null) {
				terraJava = new TerraJavaJNI();

				terraJava.connect(database.getHost(), database.getUser(),
						database.getPassword(), database.getDatabase(),
						database.getPort(), database.getDbType(), sessionId);
			}
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());

		}
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M. and Rocha, Fernanda.
	 * 
	 *         \brief Method to connect with database
	 * 
	 * @param pUserType
	 *            A string containing the user of the database
	 * @param sessionId
	 *            A string containing the session id of the user
	 * @return A boolean that indicates if the operation was successfully
	 *         concluded
	 * @throws Throwable
	 */

	public Boolean connect(String pUserType) throws GlueServerException {

		try {
			if (terraJava == null) {
				database = /* dbControll.getDatabase(); */DatabaseConnection
						.getDatabase(pUserType);
				terraJava = new TerraJavaJNI();
			}
			terraJava.connect(database.getHost(), database.getUser(),
					database.getPassword(), database.getDatabase(),
					database.getPort(), database.getDbType(), sessionId);

		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}

		return true;
	}

	/**
	 * @author Ribeiro, Stephen M.
	 * 
	 *         \brief Function that receives a box in a given projection and
	 *         converts it's values to those of a new projection
	 * 
	 * @param box
	 *            The box that will be converted
	 * @param currentProjName
	 *            The actual projection of the box
	 * @param estinationProjName
	 *            The new projection of the box
	 * @return The box that was obtained after the conversion
	 * @throws GlueServerException
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Box convertBox(Box box, String currentProjName,
			String destinationProjName) throws GlueServerException {

		// Variables
		Box convertedBox = null;
		Vector<Object> vectorCoordList = new Vector<Object>();

		// Getting box coordinates
		HashMap coord = new HashMap<String, Double>();
		coord.put("x", box.getX1());
		coord.put("y", box.getY1());
		HashMap coord2 = new HashMap<String, Double>();
		coord2.put("x", box.getX2());
		coord2.put("y", box.getY2());

		// Adding coordinates to vector
		vectorCoordList.add(coord);
		vectorCoordList.add(coord2);

		// Populating the projections
		// Another projections cases will be implemented in the future
		if ((!currentProjName.equals("LatLong")||!currentProjName.equals("ESRI"))&&!currentProjName.equals("Google")) {
			System.err.println("Projeï¿½ï¿½o invï¿½lida");
		}
		if ((!destinationProjName.equals("LatLong")||!destinationProjName.equals("ESRI")) && !destinationProjName.equals("Google")) {
			System.err.println("Projeï¿½ï¿½o invï¿½lida");
		} else if ((currentProjName.equals("LatLong")||(currentProjName.equals("ESRI"))&& destinationProjName.equals("Google"))) {
			getLatLongProj();
			getGoogleProj();
			getESRIProj();
		} else if (currentProjName.equals("Google") && (destinationProjName.equals("ESRI")|| destinationProjName.equals("LatLong"))) {
			getGoogleProj();
			getLatLongProj();
			getESRIProj();
		}
		
		
		Vector<Object> vectorCoordListResult = null;
		// Converting the box coordinates
		try {

			vectorCoordListResult = terraJava.remapCoordinates(vectorCoordList,
					getLatLongProj(), getGoogleProj(), sessionId);

		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}

		convertedBox = new Box();
		// Updating the box for plotting
		convertedBox.setX1((Double) ((HashMap) vectorCoordListResult
				.elementAt(0)).get("x"));
		convertedBox.setY1((Double) ((HashMap) vectorCoordListResult
				.elementAt(0)).get("y"));
		convertedBox.setX2((Double) ((HashMap) vectorCoordListResult
				.elementAt(1)).get("x"));
		convertedBox.setY2((Double) ((HashMap) vectorCoordListResult
				.elementAt(1)).get("y"));

		return convertedBox;
	}

	private HashMap<String, Object> getESRIProj() {
		HashMap<String, Object> ESRIlatLongProj = new HashMap<String, Object>();

		ESRIlatLongProj.put("projDatum", "SAD69");
//		ESRIlatLongProj.put("projDatum", "WGS84");//BIRA	
		ESRIlatLongProj.put("projUnits", "DecimalDegrees");
//		ESRIlatLongProj.put("projName", "LatLong");
		ESRIlatLongProj.put("projName", "ESRI");//BIRA
		ESRIlatLongProj.put("projLat0", 0);
		ESRIlatLongProj.put("projLon0", 0);
		ESRIlatLongProj.put("projStLat1", 0);
		ESRIlatLongProj.put("projStLat2", 0);
		ESRIlatLongProj.put("projScale", 0);
		ESRIlatLongProj.put("projOffx", 0);
		ESRIlatLongProj.put("projOffy", 0);
		ESRIlatLongProj.put("projNorthHemisphere", false);

		return ESRIlatLongProj;
	}
	
	/**
	 * @author Ribeiro, Stephen M.
	 * 
	 *         \brief Method that creates a file theme
	 * @throws GlueServerException
	 */

	public Boolean createFileTheme(String themeName, String path,
			Integer parentId, // este
			// atributo
			// parece n ser
			// necessario
			Projection projectionMap) throws GlueServerException {

		try {

			HashMap<String, Object> hashmap = getHashMapFromProjection(projectionMap);

			return terraJava.createFileTheme(themeName, path, parentId,
					hashmap, sessionId);
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	/**
	 * @author Severino, Bruno de Oliveira.
	 * 
	 *         \brief Method to create a Layer
	 * 
	 * @param layerName
	 * @param projection
	 * @param representation
	 * @param box
	 * @param attributes
	 * @return
	 * @throws GlueServerException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */

	public boolean createLayer(String layerName, Projection projection,
			Representation representation, Box box, Vector<Attribute> attributes)
			throws GlueServerException, IllegalAccessException,
			InstantiationException {

		Vector<Integer> reps = new Vector<Integer>();
		reps.add(representation.getId().intValue());

		return terraJava.createLayer(layerName,
				getHashMapFromProjection(projection), attributes, box.getX1(),
				box.getY1(), box.getX2(), box.getY2(), reps, sessionId);
	}

	@SuppressWarnings("unchecked")
	private boolean createThematicMap(ContextToGroupMap contextToGroupMap)
			throws GlueServerException {

		CustomGroupParameters groupCustomParameters = contextToGroupMap
				.getCustomParameter();

		try {
			if (contextToGroupMap.isIntegrity()) {

				terraJava.setCustomGroupParameters(
						groupCustomParameters.getMapTitle(),
						groupCustomParameters.getRedList(),
						groupCustomParameters.getGreenList(),
						groupCustomParameters.getBlueList(),
						groupCustomParameters.getMinValuesList(),
						groupCustomParameters.getMaxValuesList(),
						groupCustomParameters.getDescriptionList(),
						new Vector(), sessionId);

				terraJava.drawGroupSql(contextToGroupMap.getAttributeType(), // parsear
																				// pra
																				// pegar
																				// o
																				// tipo
																				// do
																				// atributo
																				// (numerico(0)
																				// ou
																				// textual(1))
						contextToGroupMap.getGroupAttribute(), // groupCustomParameters.getColFaixa(),
																// //
																// groupAttribute
																// e parsear
																// TABELA.COLUNA
																// (numero) |
																// TABELA.COLUNA
																// (numero)
						contextToGroupMap.getTableInfo(), // groupCustomParameters.getTabelaInfo(),
						contextToGroupMap.getLinkCol(), // groupCustomParameters.getColLigacao(),
						"", 5, 5, false, false, false, 2, 0, sessionId);

				return true;
			} else
				return false;

		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private HashMap<String, Object> createThematicMapThreadSafe(
			ContextToGroupMap contextToGroupMap, String sessionId) {

		if (contextToGroupMap.isIntegrity()) {

			HashMap<String, Object> themeGroupingMap = new HashMap<String, Object>();
			themeGroupingMap.put("groupingAttributeType", 0);// 0 = numerico e 1
																// = textual
			themeGroupingMap.put("groupingType",
					contextToGroupMap.getGroupType());
			themeGroupingMap.put("fields",
					contextToGroupMap.getGroupAttribute()); // TODO: verificar
															// se é este
															// atributo mesmo.
			themeGroupingMap
					.put("fromClause", contextToGroupMap.getTableInfo());
			themeGroupingMap.put("linkAttr", contextToGroupMap.getLinkCol());

			String filter = "";
			List<FilterAttribute> filterAttributes = contextToGroupMap
					.getFilterAttributes();
			if (filterAttributes != null) {

				for (FilterAttribute filterAttribute : filterAttributes) {
					filter += filterAttribute.getAttributeName() + " "
							+ filterAttribute.getRelationalOperator() + " "
							+ filterAttribute.getValue();
					// caso seja o último filtro, não inclui o operador lógico
					if (!filterAttributes.get(filterAttributes.size() - 1)
							.equals(filterAttribute)) {
						filter += " " + filterAttribute.getLogicOperator()
								+ " ";
					}
				}
			}
			themeGroupingMap.put("restrictionExpression", filter);
			themeGroupingMap.put("precision", contextToGroupMap.getPrecision());
			// Para o caso de agrupamento por desvio padrão, o valor selecionado
			// pelo usuário foi guardado na variável slice.
			// TODO: criar uma variável na classe ContextToGroupMap stdDeviation
			// para armazenar este valor e não causar duvida aqui.
			themeGroupingMap.put("stdDev", contextToGroupMap.getSlices());
			themeGroupingMap.put("numSlices", contextToGroupMap.getSlices());

			HashMap<String, Object> rampColors = new HashMap<String, Object>();
			rampColors.put("colorRed", contextToGroupMap.getRed() != 0);
			rampColors.put("colorGreen", contextToGroupMap.getGreen() != 0);
			rampColors.put("colorBlue", contextToGroupMap.getBlue() != 0);
			themeGroupingMap.put("rampColorsMap", rampColors);

			Vector<HashMap> slicesList = new Vector<HashMap>();
			CustomGroupParameters customGroupParameters = contextToGroupMap
					.getCustomParameter();

			int customCode = 5; // código de agrupamento do tipo customizado,
								// pensar em trazer o enum pro GLUEServer e não
								// deixar dúvida.
			if (contextToGroupMap.getGroupType() == customCode
					&& customGroupParameters != null) {

				for (int i = 0; i < customGroupParameters.getRedList().size(); i++) {
					HashMap<String, Object> sliceMap = new HashMap<String, Object>();
					sliceMap.put("from", customGroupParameters
							.getMinValuesList().get(i));
					sliceMap.put("to", customGroupParameters.getMaxValuesList()
							.get(i));
					sliceMap.put("count", 0); // (Integer)
												// customGroupParameters.getCustomNumObjsSlices().get(i));
					sliceMap.put("description", customGroupParameters
							.getDescriptionList().get(i));

					HashMap<String, Object> sliceColorMap = new HashMap<String, Object>();
					sliceColorMap.put("colorRed", customGroupParameters
							.getRedList().get(i).intValue());
					sliceColorMap.put("colorGreen", customGroupParameters
							.getGreenList().get(i).intValue());
					sliceColorMap.put("colorBlue", customGroupParameters
							.getBlueList().get(i).intValue());
					sliceMap.put("sliceColorMap", sliceColorMap);

					slicesList.add(sliceMap);

				}
			}

			themeGroupingMap.put("slicesList", slicesList);

			return themeGroupingMap;
		} else
			throw new RuntimeException(
					"Erro nos parametros para criação do mapa temático");
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M. and Rocha, Fernanda.
	 * 
	 *         \brief Method that creates a theme on the database Terralib
	 * @throws GlueServerException
	 */

	private Boolean createTheme(String newThemeName, String layerName,
			Integer parentID, String restriction, String sessionId)
			throws GlueServerException {

		try {

			String themeRestriction = null;
			if (restriction == null) {
				themeRestriction = "";
			}

			return terraJava.createTheme(newThemeName, layerName, parentID,
					themeRestriction, sessionId);

		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	/**
	 * @author Ribeiro, Stephen M.
	 * 
	 *         \brief Method that creates a view on memory
	 * @throws GlueServerException
	 */

	public Boolean createViewMem(String viewName, String userName,
			Long[] themesIds, Projection proj) throws GlueServerException {

		try {

			System.out.println("WEBSERVICE -> CREATE VIEW MEM -> SESSION :"
					+ sessionId);

			Vector<Integer> themesIdsVec = new Vector<Integer>();
			HashMap<String, Object> projectionMap = new HashMap<String, Object>();

			if (themesIds != null) {

				for (int i = 0; i < themesIds.length; i++) {
					themesIdsVec.add(Integer.parseInt(themesIds[i].toString()));

				}
			}
			if (proj != null) {
				projectionMap = getHashMapFromProjection(proj);
			}

			return terraJava.createViewMem(viewName, userName, themesIdsVec,
					projectionMap, sessionId);
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	public boolean deleteFeatures(List<String> listIds) {
		Vector<String> geomIds = new Vector<String>(listIds);
		return terraJava.deleteFeatures(geomIds, sessionId);
	}

	public boolean deleteGeometries(List<String> geomIdsList, Integer themeType) {
		Vector<String> geomIds = new Vector<String>(geomIdsList);
		return terraJava.deleteGeometries(geomIds, sessionId);
	}

	/**
	 * @author Oliveira, Andrï¿½ G.
	 * 
	 *         \brief Function that destroys a terraManager instance
	 * 
	 * @param sessionId
	 *            The session id of the instance that will be destroyed
	 * @return A boolean that indicates if the instance was successfully
	 *         destroyed
	 * @throws GlueServerException
	 */

	public Boolean destroySession(String sessionId) throws GlueServerException {
		try {
			Boolean result = false;
			if (sessionId != null) {
				result = terraJava.destroySession(sessionId);
			}
			return result;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
		// TODO:verificar
	}

	private void drawCurrentTheme(String sessionId, ThemeToPlot theme)
			throws GlueServerException {

		try {
			terraJava.setTheme(theme.getName(), theme.getType(), sessionId);
			terraJava.drawCurrentTheme(sessionId);

			String propertyKey = "service.theme.name";
			String fieldName = "uf_nome";

			this.insertLabelCurrentTheme(sessionId, propertyKey, theme,
					fieldName);

		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that returns an image of the current View
	 */

	@SuppressWarnings("rawtypes")
	public byte[] drawThemeText(ViewToPlot view, Box plotBox, Integer width,
			Integer height, Integer imageType, Boolean canvasOpaque,
			Integer quality) throws GlueServerException {// Attribute name for
															// grouping is empty
		try {

			if (view.getThemes().iterator().hasNext()) {
				ThemeToPlot theme = view.getThemes().iterator().next();
				HashMap<String, Object> themeMap = new HashMap<String, Object>();
				themeMap.put("themeName", theme.getName());

				HashMap<String, Integer> canvasBackgroundColor = new HashMap<String, Integer>();
				canvasBackgroundColor.put("r", view.getCanvasBackgroundColor()
						.getRed());
				canvasBackgroundColor.put("g", view.getCanvasBackgroundColor()
						.getGreen());
				canvasBackgroundColor.put("b", view.getCanvasBackgroundColor()
						.getBlue());

				HashMap projectionMap = getHashMapFromProjection(view
						.getProjection());

				boolean useScaleControl = PropertiesReader
						.getBooleanProperty("terrajava.scale.control.enabled");

				if (theme.getLabelConfig() != null) {
					themeMap.put("useLabelConfig", true);
					HashMap<String, Object> labelConfigMap = new HashMap<String, Object>();
					labelConfigMap.put("field", theme.getLabelConfig()
							.getField());

					labelConfigMap.put("detectConflict", theme.getLabelConfig()
							.isDetectConflict());
					labelConfigMap.put("priorityField", theme.getLabelConfig()
							.getPriorityField());
					labelConfigMap.put("urbanMode", theme.getLabelConfig()
							.isUrbanMode());
					labelConfigMap.put("descTextPriorityOrder", theme
							.getLabelConfig().isDescTextPriorityOrder());
					labelConfigMap.put("minCollisionTol", theme
							.getLabelConfig().getMinCollisionTol());
					HashMap<String, Object> visualMap = new HashMap<String, Object>();
					if (theme.getLabelConfig().getVisual() != null) {
						visualMap.put("colorRed", theme.getLabelConfig()
								.getVisual().getRed());
						visualMap.put("colorGreen", theme.getLabelConfig()
								.getVisual().getGreen());
						visualMap.put("colorBlue", theme.getLabelConfig()
								.getVisual().getBlue());
						String fontPath = VisualUtils.getFontPathByName(theme
								.getLabelConfig().getVisual().getFontName());
						visualMap.put("fontFamily", fontPath);
						visualMap.put("width", theme.getLabelConfig()
								.getVisual().getWidthCountour());
						labelConfigMap.put("visualMap", visualMap);
					}

					themeMap.put("labelConfigMap", labelConfigMap);
				} else {
					themeMap.put("useLabelConfig", false);
				}

				return terraJava.drawThemeText(themeMap, plotBox.getX1(),
						plotBox.getY1(), plotBox.getX2(), plotBox.getY2(),
						width, height, false, imageType, canvasOpaque, quality,
						projectionMap, canvasBackgroundColor, useScaleControl);
			} else
				throw new GlueServerException(
						"Tema para desenho de textos não encontrado.");
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());

		}
	}

	public String exportToShapeFile(Box box, String viewName, String themeName,
			String baseName, Vector<String> atributos)
			throws IllegalAccessException, InstantiationException,
			GlueServerException {
		// TerraJavaJNI tj = new TerraJavaJNI();
		String finish = null;

		if (terraJava == null) {
			terraJava = new TerraJavaJNI();
		}
		terraJava.setCurrentView(viewName, userName, sessionId);
		terraJava.setWorld(box.getX1(), box.getY1(), box.getX2(), box.getY2(),
				600, 800, sessionId);
		// tj.setTheme(themeName,0, sessionId);//--> Depois tentaremos através
		// do nome
		terraJava.setTheme(themeName, 0, sessionId);
		try {
			terraJava.saveThemeToFile("/var/www/glue/shape/" + sessionId,
					atributos, sessionId);
			finish = "finish";
		} catch (Exception e) {
			finish = null;
		}
		return finish;
	}

	public Vector fetchAttributes(String objectid, String theme, Long themeId)
			throws GlueServerException {
		try {
			terraJava.setTheme(theme, 0, sessionId);
			Vector fetchedAttributes = terraJava.fetchAttributes(objectid, 0,
					sessionId);
			return AttributeService.associateAliasToFetchedAttributes(objectid,
					fetchedAttributes, theme, themeId);
		} catch (RuntimeException e) {

			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	/**
	 * \brief This function receives a view and searches if there are any themes
	 * with the same id
	 * 
	 * @param view
	 *            The view that will be checked
	 * @param themeID
	 *            The theme that will be searched
	 * @return An Integer that indicates the theme position on view
	 * @throws GlueServerException
	 * 
	 */

	private Integer findThemeOnView(View view, Long themeID)
			throws GlueServerException {

		return searchThemeOnView(view, themeID, null);

	}

	/**
	 * \brief This function receives a view and searches if there are any themes
	 * with the same id
	 * 
	 * @param view
	 *            The view that will be checked
	 * @param themeName
	 *            The theme that will be searched
	 * @return An Integer that indicates the theme position on view
	 * @throws GlueServerException
	 * 
	 */

	private Integer findThemeOnView(View view, String themeName)
			throws GlueServerException {

		return searchThemeOnView(view, null, themeName);

	}

	public List<String> getAttributesList(String viewName, String themeName)
			throws GlueServerException {

		if (terraJava == null) {
			terraJava = new TerraJavaJNI();
		}
		try {
			terraJava.setCurrentView(viewName, userName, sessionId);
			terraJava.setTheme(themeName, 0, sessionId);
			return terraJava.getAttributesList(sessionId);
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}

	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that receives a hashmap and converts it's
	 *         properties into a theme
	 * 
	 * @param viewName
	 *            The name of the view that the theme will belong to
	 * @param pForceReload
	 *            A boolean that indicates if the terraManager memory needs to
	 *            be cleaned
	 * @param themeMap
	 *            The hashMap that will be converted into a theme
	 * @param Layers
	 *            A vector containing the layers
	 * @return A new theme resulted from the conversion of the hashmaps
	 *         properties
	 * @throws GlueServerException
	 */

	private Box getBoxFromView(View view) throws GlueServerException {
		try {
			terraJava.setCurrentView(view.getName(), userName, sessionId);

			Vector viewBox = terraJava.getCurrentViewBox(sessionId);

			Box box = new Box((Double) viewBox.get(0), (Double) viewBox.get(1),
					(Double) viewBox.get(2), (Double) viewBox.get(3));
			return box;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	public List<Double> getCentroidForBiggestGeometry(String objectId,
			String viewName, String themeName) throws GlueServerException {

		if (terraJava == null) {
			terraJava = new TerraJavaJNI();
		}
		try {
			terraJava.setCurrentView(viewName, userName, sessionId);
			terraJava.setTheme(themeName, 0, sessionId);
			HashMap<String, Double> centroidHashMap = terraJava
					.getCentroidForBiggestGeometry(objectId, sessionId);
			List<Double> centroidList = new ArrayList<Double>();
			centroidList.add(centroidHashMap.get("x"));
			centroidList.add(centroidHashMap.get("y"));
			return centroidList;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}

	}

	public Box getCurrentThemeBox(String themeName, String restrictions)
			throws GlueServerException {
		try {

		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}

		try {
			terraJava.setTheme(themeName, 0, sessionId);
			Vector vectorBox = terraJava.getCurrentThemeBox(0, restrictions,
					sessionId);
			Box box = new Box(vectorBox);

			return box;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method to returns the theme visual
	 * @throws GlueServerException
	 */

	private ThemeVisual getDefaultVisual(Integer rep, String sessionId)
			throws GlueServerException {

		try {

			Vector themeVisualVec = terraJava.getDefaultVisual(rep, sessionId);

			ThemeVisual t = new ThemeVisual();

			System.out.println("VECTOR VISUAL: " + themeVisualVec.size());

			System.out.println(rep);
			t.setRep(rep);

			System.out.println(themeVisualVec.get(0));
			t.setStyle((Integer) themeVisualVec.get(0));

			System.out.println(themeVisualVec.get(1));
			Color color = ((Color) themeVisualVec.get(1));
			t.setRed(color.getRed());
			t.setGreen(color.getGreen());
			t.setBlue(color.getBlue());

			System.out.println(themeVisualVec.get(2));
			t.setTransparency((Integer) themeVisualVec.get(2));

			System.out.println(themeVisualVec.get(3));
			t.setLineWidth((Integer) themeVisualVec.get(3));

			System.out.println(themeVisualVec.get(4));
			t.setStyleCountour((Integer) themeVisualVec.get(4));

			System.out.println(themeVisualVec.get(5));
			Color color1 = ((Color) themeVisualVec.get(5));
			t.setCountourColorRed(color1.getRed());
			t.setCountourColorGreen(color1.getGreen());
			t.setCountourColorBlue(color1.getBlue());

			System.out.println(themeVisualVec.get(6));
			t.setCountourTransparency((Integer) themeVisualVec.get(6));

			System.out.println(themeVisualVec.get(7));
			t.setWidthCountour((Integer) themeVisualVec.get(7));

			System.out.println(themeVisualVec.get(8));
			t.setPointSize((Integer) themeVisualVec.get(8));

			System.out.println(themeVisualVec.get(9));
			t.setPtAngle((Integer) themeVisualVec.get(9));

			System.out.println((String) themeVisualVec.get(10));
			t.setFontName((String) themeVisualVec.get(10));

			System.out.println(themeVisualVec.get(11));
			t.setBold((Boolean) themeVisualVec.get(11));

			System.out.println(themeVisualVec.get(12));
			t.setItalic((Boolean) themeVisualVec.get(12));

			System.out.println(themeVisualVec.get(13));
			t.setVerticalAlign((Double) themeVisualVec.get(13));

			System.out.println(themeVisualVec.get(14));
			t.setHorizontalAlign((Double) themeVisualVec.get(14));

			System.out.println(themeVisualVec.get(15));
			t.setTabSize((Integer) themeVisualVec.get(15));

			System.out.println(themeVisualVec.get(16));
			t.setLineSpace((Integer) themeVisualVec.get(16));

			System.out.println(themeVisualVec.get(17));
			t.setFixedSize((Boolean) themeVisualVec.get(17));

			return t;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}

	}

	public Vector<String> getFeaturesByIds(List<String> listIds)
			throws GlueServerException {
		Vector<String> vecIds = new Vector<String>(listIds);
		try {
			return terraJava.getFeaturesByIds(vecIds, sessionId);
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	public Vector<String> getFeaturesIds() throws GlueServerException {
		try {
			return terraJava.getFeaturesIds(sessionId);
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	public Vector<String> getFeaturesInBox(Box box) {
		return terraJava.getFeaturesInBox(box.getX1(), box.getY1(),
				box.getX2(), box.getY2(), sessionId);
	}

	public Vector<String> getFeaturesWithRestriction(String restriction)
			throws GlueServerException {
		try {
			return terraJava.getFeaturesWithRestriction(restriction, sessionId);
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that returns a hashmap containing the values of a
	 *         google projection
	 */

	private HashMap<String, Object> getGoogleProj() {

		HashMap<String, Object> googleProj = new HashMap<String, Object>();

		googleProj.put("projDatum", "WGS84");
		googleProj.put("projUnits", "Meters");
		googleProj.put("projName", "VirtualEarthMercator");
		googleProj.put("projLat0", 0);
		googleProj.put("projLon0", 0);
		googleProj.put("projStLat1", 0);
		googleProj.put("projStLat2", 0);
		googleProj.put("projScale", 0);
		googleProj.put("projOffx", 0);
		googleProj.put("projOffy", 0);
		googleProj.put("projNorthHemisphere", false);

		return googleProj;
	}

	/**
	 * @author Ribeiro, Stephen M.
	 * 
	 *         \brief Method that converts a Projection object to a
	 *         HashMap<String,Object>
	 */

	private HashMap<String, Object> getHashMapFromProjection(Projection proj) {

		HashMap<String, Object> hashMapProj = new HashMap<String, Object>();

		hashMapProj.put("projName", proj.getName());
		hashMapProj.put("projDatum", proj.getDatum());
		hashMapProj.put("projOffx", Double.parseDouble(proj.getOffx()));
		hashMapProj.put("projOffy", Double.parseDouble(proj.getOffy()));
		hashMapProj.put("projLon0", proj.getLon0());
		hashMapProj.put("projLat0", proj.getLat0());
		hashMapProj.put("projUnits", proj.getUnits());
		hashMapProj.put("projScale", proj.getScale());
		hashMapProj.put("projNorthHemisphere", proj.getHemNorth());
		hashMapProj.put("projStLat1", proj.getStlat1());
		hashMapProj.put("projStLat2", proj.getStlat2());

		return hashMapProj;
	}

	/**
	 * @author Oliveira, Andrï¿½ G. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that converts all visibilities of a theme into a
	 *         Vector<HashMap>
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Vector<HashMap> getHashMapFromThemesVisibility(View updatedView,
			View newView) {

		Vector<HashMap> themeVisibVec = new Vector<HashMap>();

		for (int i = 0; i < updatedView.getThemes().size(); i++) {
			HashMap themeVisibMap = new HashMap();

			themeVisibMap.put(
					"themeId",
					Integer.parseInt(updatedView.getThemes().get(i).getId()
							.toString()));
			themeVisibMap.put("visibility", newView.getThemes().get(i)
					.isVisibility());

			themeVisibVec.add(themeVisibMap);
		}

		return themeVisibVec;
	}

	/**
	 * @author Ribeiro, Stephen M.
	 * 
	 *         \brief Method that converts a Projection object to a
	 *         HashMap<String,Object>
	 */

	private HashMap<String, Object> getHashMapFromThemeVisual(ThemeVisual visual) {

		HashMap<String, Object> visualMap = new HashMap<String, Object>();
		if (visual.getRep() != null) {
			visualMap.put("geomRep", visual.getRep());
			visualMap.put("colorRed", visual.getRed());
			visualMap.put("colorGreen", visual.getGreen());
			visualMap.put("colorBlue", visual.getBlue());
			visualMap.put("styleId", visual.getStyle());
			visualMap.put("transparency", visual.getTransparency());

			if (visual.getRep() == Representation.POLYGON) {
				visualMap.put("contourColorRed", visual.getCountourColorRed());
				visualMap.put("contourColorGreen",
						visual.getCountourColorGreen());
				visualMap
						.put("contourColorBlue", visual.getCountourColorBlue());
				visualMap.put("contourStyleId", visual.getStyleCountour());
				visualMap.put("contourTransparency",
						visual.getCountourTransparency());
				visualMap.put("width", visual.getWidthCountour());
			} else if (visual.getRep() == Representation.LINE) {
				visualMap.put("width", visual.getWidthCountour());
			} else if (visual.getRep() == Representation.POINT) {
				visualMap.put("size", visual.getPointSize());
			} else if (visual.getRep() == Representation.TEXT) {
				visualMap.put("width", visual.getWidthCountour());
				String fontPath = VisualUtils.getFontPathByName(visual
						.getFontName());
				visualMap.put("family", fontPath);
			}
		}
		return visualMap;
	}

	private ThemeVisual getHightlightVisualFromConfigFile(int representation) {
		ThemeVisual visual = new ThemeVisual();
		visual.setRep(representation);
		visual.setRed(Integer.parseInt(PropertiesReader
				.getProperty("visual.highlight.red")));
		visual.setGreen(Integer.parseInt(PropertiesReader
				.getProperty("visual.highlight.green")));
		visual.setBlue(Integer.parseInt(PropertiesReader
				.getProperty("visual.highlight.blue")));

		if (representation == 1) {
			visual.setStyle(Integer.parseInt(PropertiesReader
					.getProperty("visual.highlight.stylePolygon")));
		} else {
			visual.setStyle(Integer.parseInt(PropertiesReader
					.getProperty("visual.highlight.style")));
		}

		visual.setLineWidth(Integer.parseInt(PropertiesReader
				.getProperty("visual.highlight.lineWidth")));
		visual.setFontName(PropertiesReader
				.getProperty("visual.highlight.font"));
		visual.setCountourColorRed(Integer.parseInt(PropertiesReader
				.getProperty("visual.highlight.countourColorRed")));
		visual.setCountourColorGreen(Integer.parseInt(PropertiesReader
				.getProperty("visual.highlight.countourColorGreen")));
		visual.setCountourColorBlue(Integer.parseInt(PropertiesReader
				.getProperty("visual.highlight.countourColorBlue")));
		visual.setStyleCountour(Integer.parseInt(PropertiesReader
				.getProperty("visual.highlight.countourStyle")));
		visual.setWidthCountour(Integer.parseInt(PropertiesReader
				.getProperty("visual.highlight.countourWidth")));
		return visual;
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that returns a hashmap containing the values of a
	 *         latLong projection
	 */

	private HashMap<String, Object> getLatLongProj() {

		HashMap<String, Object> latLongProj = new HashMap<String, Object>();

		latLongProj.put("projDatum", "SAD69");
//		latLongProj.put("projDatum", "WGS84");//BIRA	
		latLongProj.put("projUnits", "DecimalDegrees");
		latLongProj.put("projName", "LatLong");
//		latLongProj.put("projName", "ESRI");//BIRA
		latLongProj.put("projLat0", 0);
		latLongProj.put("projLon0", 0);
		latLongProj.put("projStLat1", 0);
		latLongProj.put("projStLat2", 0);
		latLongProj.put("projScale", 0);
		latLongProj.put("projOffx", 0);
		latLongProj.put("projOffy", 0);
		latLongProj.put("projNorthHemisphere", false);

		return latLongProj;
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that receives a hashmap and a vector of layers and
	 *         returns the layer with the hashmpa id
	 * 
	 * @param hashap
	 *            The hashMap that will have it's properties checked
	 * @param Layers
	 *            A vector containing the layers
	 * @return The layer with the hasMpa's id
	 */

	private Layer getLayerFromHashMap(
			@SuppressWarnings("rawtypes") HashMap hashMap, List<Layer> layers) {

		// Variables
		String layerId = null;

		Layer layer = null;

		// Getting the layer id from hashmap
		layerId = (String) hashMap.get("layerId");

		// Comparing layers from database to get the correct layer
		for (int i = 0; i < layers.size(); i++) {

			if (layerId.equals(layers.get(i).getId().toString())) {
				layer = layers.get(i);
				break;
			}
		}

		return layer;
	}

	/**
	 * @author Oliveira, Andrï¿½ G. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that recovers a set of layers related to a user
	 * @param forceReload
	 *            A boolean that indicates if the terraManager memory has to be
	 *            cleaned or not
	 * @param sessionId
	 *            sessionId A string containing the user's sessionId
	 * @return An array of layers
	 * @throws GlueServerException
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Layer> getLayerSet(Boolean forceReload)
			throws GlueServerException {

		try {

			Vector<HashMap> layerSet = terraJava.getLayerSet(forceReload,
					sessionId);

			List<Layer> layers = new ArrayList<Layer>(layerSet.size());

			for (int i = 0; i < layerSet.size(); i++) {

				// Reading layer information
				HashMap<String, Object> layerMap = layerSet.get(i);

				Layer layer = new Layer();
				layer.setId((Integer) layerMap.get("id"));
				layer.setName((String) layerMap.get("name"));
				layer.setProjection(getProjectionFromHashMap((HashMap<String, Object>) layerMap
						.get("projection")));

				// Reading layer representations
				Vector<String> layerReps = (Vector<String>) layerMap
						.get("layerReps");
				List<Representation> repres = getRepresentationsFromVector(layerReps);

				layer.setRepresentations(repres);
				layer.setAttributeTables(new ArrayList<AttributeTable>());
				Vector<HashMap> tablesVector = (Vector<HashMap>) layerMap
						.get("attrsTables");
				ArrayList<AttributeTable> listTables = new ArrayList<AttributeTable>();

				for (int j = 0; j < tablesVector.size(); j++) {
					AttributeTable table = new AttributeTable();
					HashMap tableMap = tablesVector.get(j);
					table.setName((String) tableMap.get("tableName"));
					table.setAttrLink((String) tableMap.get("attrLink"));
					table.setAttributes(new ArrayList<Attribute>());
					Vector<HashMap> attributeVector = (Vector<HashMap>) tableMap
							.get("attributes");
					ArrayList<Attribute> listAttrib = new ArrayList<Attribute>();

					for (int k = 0; k < attributeVector.size(); k++) {
						Attribute attribute = new Attribute();
						String nameAttr = (String) attributeVector.get(k).get(
								"attrName");
						String type = (String) attributeVector.get(k).get(
								"attrType");
						int size = (Integer) attributeVector.get(k).get(
								"attrColumnSize");
						attribute.setName(nameAttr);
						attribute.setDataType(type);
						attribute.setSize(size);
						listAttrib.add(attribute);

					}
					table.setAttributes(listAttrib);
					listTables.add(table);
				}

				layer.setAttributeTables(listTables);
				layers.add(i, layer);
			}
			return layers;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}

	}

	// @ }

	/**
	 * @name Memory views manipulation
	 * 
	 *       Functions that manipulete views in the TerraManager's memory
	 */

	// @ {

	/**
	 * @author Ribeiro, Stephen M.
	 * 
	 *         \brief Method that retrieves a set of Layers names
	 * @throws GlueServerException
	 */
	private String[] getLayersName() throws GlueServerException {
		try {
			Vector layersNames = terraJava.getLayersName(sessionId);
			String[] layers = new String[layersNames.size()];

			for (int i = 0; i < layersNames.size(); i++) {

				layers[i] = (String) layersNames.get(i);
			}
			return layers;
		} catch (RuntimeException e) {

			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());

		}
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that receives a hashmap and converts it's
	 *         properties into a projection
	 * 
	 * @param hashMap
	 *            The hashMap that will be analyzed
	 * @return A new projection resulted from the conversion of the hashmap's
	 *         properties
	 */

	private Projection getProjectionFromHashMap(HashMap<String, Object> hashMap) {

		Projection proj = new Projection();

		proj.setOffy(hashMap.get("projOffy").toString());
		proj.setOffx(hashMap.get("projOffx").toString());
		proj.setUnits(hashMap.get("projUnits").toString());
		proj.setDatum(hashMap.get("projDatum").toString());
		proj.setName(hashMap.get("projName").toString());
		proj.setHemNorth(((hashMap.get("projNorthHemisphere")).equals("true") ? true
				: false));
		proj.setStlat2((Double) hashMap.get("projStLat2"));
		proj.setLon0((Double) hashMap.get("projLon0"));
		proj.setStlat1((Double) hashMap.get("projStLat1"));
		proj.setScale((Double) hashMap.get("projScale"));
		proj.setLat0((Double) hashMap.get("projLat0"));

		return proj;
	}

	public byte[] getRasterMatrix(Box box, int width, int height, Theme theme)
			throws GlueServerException {
		try {
			terraJava.setTheme(theme.getName(), 0, sessionId);
			byte[] rasterMatrix = terraJava.getRasterMatrix(box.getX1(),
					box.getY1(), box.getX2(), box.getY2(), width, height,
					sessionId);
			return rasterMatrix;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	// @ }

	/**
	 * @name Normal views manipulation
	 * 
	 *       Functions that manipulete views in the TerraManager's memory
	 */

	// @ {

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that receives a vector and converts it's properties
	 *         into a vector of representantons
	 * 
	 * @param reps
	 *            The vector containing the properties of the representations
	 * @return A vector of representations resulted from the conversion
	 */

	private List<Representation> getRepresentationsFromVector(
			Vector<String> reps) {
		List<Representation> repres = new ArrayList<Representation>(reps.size());

		for (int k = 0; k < reps.size(); k++) {

			repres.add(k, new Representation());
			repres.get(k).setId(Long.parseLong(reps.get(k)));
			repres.get(k).setName(repres.get(k).getId().toString());

		}
		return repres;
	}

	public String getSessionId() {
		return sessionId;
	}

	public TerraJavaJNI getTerraJava() {
		return terraJava;
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that receives a hashmap and converts it's
	 *         properties into a theme
	 * 
	 * @param viewName
	 *            The name of the view that the theme will belong to
	 * @param pForceReload
	 *            A boolean that indicates if the terraManager memory needs to
	 *            be cleaned
	 * @param pDBUserName
	 *            A string containing the user's name
	 * @param sessionId
	 *            A string containing the users session's id
	 * @param themeMap
	 *            The hashMap that will be converted into a theme
	 * @param Layers
	 *            A vector containing the layers
	 * @return A new view resulted from the conversion of the hashmaps
	 *         properties
	 * @throws GlueServerException
	 */

	@SuppressWarnings("rawtypes")
	private Theme getThemesFromHashMap(String viewName, Boolean pForceReload,
			String pDBUserName, String sessionId, HashMap themeMap,
			List<Layer> layers) throws GlueServerException {

		Theme theme = new Theme();

		// Setting global theme parameters(default, group)
		theme.setId(Long.parseLong((String) themeMap.get("themeId")));
		theme.setName((String) themeMap.get("themeName"));
		theme.setVisibility((themeMap.get("visibility").equals("1") ? true
				: false));
		theme.setType((Integer) themeMap.get("type"));

		Long parent = Long.parseLong(((Integer) themeMap.get("parentId"))
				.toString());

		// Only to show theme groups, they cant have default themes children
		if (theme.getType() == 1) {
			theme.setParentID(parent);
		} else {// Always set parent id 0 for default themes. Not possible to
			// work with theme group now
			theme.setParentID(0l);
		}

		// Setting default theme parameters

		if ((theme.getParentID().equals(0l) || theme.getParentID().equals(
				theme.getId()))
				&& theme.getType() != 1) {
			theme.setLayer(getLayerFromHashMap((HashMap) themeMap.get("layer"),
					layers));
			theme.setReps(getRepresentationsFromVector((Vector<String>) themeMap
					.get("themeReps")));
			theme.setThemeVisuals(getVisualsFromTheme(viewName, pDBUserName,
					sessionId, theme));
			theme.setScaleLimit(new ScaleLimit((Double) themeMap
					.get("minScale"), (Double) themeMap.get("maxScale")));
		}
		// Getting themes from themeGroup
		else {
			Vector<HashMap> themesVecMap = (Vector<HashMap>) themeMap
					.get("themes");
			List<Theme> childThemes = new ArrayList<Theme>(themesVecMap.size());
			for (int i = 0; i < themesVecMap.size(); i++) {
				childThemes.set(
						i,
						getThemesFromHashMap(viewName, pForceReload,
								pDBUserName, sessionId,
								themesVecMap.elementAt(i), layers));
			}
			theme.setChildThemes(childThemes);
		}

		/*
		 * if(Integer.parseInt(theme.getReps()[0].getId().toString()) == 512){
		 * 
		 * } else{ _terraJava.setTheme(theme.getName(), theme.getType(),
		 * sessionId); theme.setThemeBox(new Box( _terraJava.getCurrentThemeBox(
		 * theme.getType(), "", sessionId))); }
		 */

		return theme;

	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method to returns the theme visual
	 * @throws GlueServerException
	 */

	private ThemeVisual getThemeVisual(String currentView, String currentTheme,
			Integer themeType, Integer rep) throws GlueServerException {

		try {

			terraJava.setCurrentView(currentView, userName, sessionId);
			terraJava.setTheme(currentTheme, themeType, sessionId);

			Vector themeVisualVec;
			ThemeVisual t = new ThemeVisual();

			if (rep != 512) {

				themeVisualVec = terraJava.getThemeVisual(rep, sessionId);

				t.setRep(rep);
				t.setStyle((Integer) themeVisualVec.get(0));
				Color color = ((Color) themeVisualVec.get(1));
				t.setRed(color.getRed());
				t.setGreen(color.getGreen());
				t.setBlue(color.getBlue());
				t.setTransparency((Integer) themeVisualVec.get(2));
				t.setLineWidth((Integer) themeVisualVec.get(3));
				t.setStyleCountour((Integer) themeVisualVec.get(4));
				Color color1 = ((Color) themeVisualVec.get(5));
				t.setCountourColorRed(color1.getRed());
				t.setCountourColorGreen(color1.getGreen());
				t.setCountourColorBlue(color1.getBlue());
				t.setCountourTransparency((Integer) themeVisualVec.get(6));
				t.setWidthCountour((Integer) themeVisualVec.get(7));
				t.setPointSize((Integer) themeVisualVec.get(8));
				t.setPtAngle((Integer) themeVisualVec.get(9));
				t.setFontName((String) themeVisualVec.get(10));
				t.setBold((Boolean) themeVisualVec.get(11));
				t.setItalic((Boolean) themeVisualVec.get(12));
				t.setVerticalAlign((Double) themeVisualVec.get(13));
				t.setHorizontalAlign((Double) themeVisualVec.get(14));
				t.setTabSize((Integer) themeVisualVec.get(15));
				t.setLineSpace((Integer) themeVisualVec.get(16));
				t.setFixedSize((Boolean) themeVisualVec.get(17));

			}

			return t;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}

	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that returns a hashmap containing the values of an
	 *         utm projection
	 */

	private HashMap<String, Object> getUTMProj() {

		HashMap<String, Object> googleProj = new HashMap<String, Object>();

		googleProj.put("projDatum", "SAD69");
		googleProj.put("projUnits", "Meters");
		googleProj.put("projName", "UTM");
		googleProj.put("projLat0", 0);
		googleProj.put("projLon0", -45);
		googleProj.put("projStLat1", 0);
		googleProj.put("projStLat2", 0);
		googleProj.put("projScale", 0.9996);
		googleProj.put("projOffx", 500000);
		googleProj.put("projOffy", 10000000);
		googleProj.put("projNorthHemisphere", false);

		return googleProj;
	}

	/**
	 * @author Ribeiro, Stephen M.
	 * 
	 *         \brief Method that converts an array of coordinates to a
	 *         Vector<Object>
	 */

	private Vector<Object> getVectorFromCoordsArray(Coord[] coordsList) {

		Vector<Object> vectorCoordList = new Vector<Object>();

		for (int i = 0; i < coordsList.length; i++) {

			HashMap<String, Double> coord = new HashMap<String, Double>();
			coord.put("x", coordsList[i].getX());
			coord.put("y", coordsList[i].getY());

			vectorCoordList.add(coord);
		}

		return vectorCoordList;
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that retrieves a set of Views from the database
	 * @throws GlueServerException
	 */

	private View getView(Long viewId, String pDBUserName, Boolean pForceReload,
			String sessionId) throws GlueServerException {

		return searchView(viewId, null, pDBUserName, pForceReload, sessionId);
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that retrieves a set of Views from the database
	 * @throws GlueServerException
	 */

	private View getView(String viewName, String pDBUserName,
			Boolean pForceReload, String sessionId) throws GlueServerException {
		System.out.println("ESTAMOS NO GETVIEW");

		return searchView(null, viewName, pDBUserName, pForceReload, sessionId);
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that receives a hashmap and converts it's
	 *         properties into a view
	 * 
	 * @param pForceReload
	 *            A boolean that indicates if the terraManager memory needs to
	 *            be cleaned
	 * @param pDBUserName
	 *            A string containing the user's name
	 * @param sessionId
	 *            A string containing the users session's id
	 * @param viewMap
	 *            The hashMap that will be converted into a view
	 * @param Layers
	 *            A vector containing the layers
	 * @return A new view resulted from the conversion of the hashmaps
	 *         properties
	 * @throws GlueServerException
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private View getViewFromHashMap(Boolean pForceReload, String pDBUserName,
			String sessionId, HashMap viewMap, List<Layer> layers)
			throws GlueServerException {

		Vector<HashMap> themeSet = (Vector<HashMap>) viewMap.get("themes");

		// Setting view parameters
		View view = new View(themeSet.size());
		view.setId((Long.parseLong((String) viewMap.get("viewId"))));
		view.setName((String) viewMap.get("viewName"));
		view.setProjection(getProjectionFromHashMap((HashMap<String, Object>) viewMap
				.get("viewProjection")));

		// 0 theme
		// 1/2 external WMS
		// 3 fileTheme

		try {
			if (themeSet.size() != 0) {
				for (int j = 0; j < themeSet.size(); j++) {
					terraJava.setCurrentView(view.getName(), pDBUserName,
							sessionId);
					view.getThemes().add(
							j,
							getThemesFromHashMap(view.getName(), pForceReload,
									pDBUserName, sessionId,
									themeSet.elementAt(j), layers));
				}

				view.setBox(getBoxFromView(view));
			}
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}

		return view;
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M. and Rocha, Fernanda.
	 * 
	 *         \brief Method that retrieves a set of Views from the database
	 * @throws GlueServerException
	 */

	@SuppressWarnings("rawtypes")
	public ViewSet getViewSet(

	Boolean pForceReload) throws GlueServerException {

		try {

			terraJava.setAutomaticScaleControlEnable(false, sessionId);

			Vector<HashMap> vector = terraJava.getViewSetTree(pForceReload,
					sessionId);

			for (int i = 0; i < vector.size(); i++) {
				System.out.println(vector.get(i));
			}

			ViewSet viewSet = new ViewSet(vector.size());
			viewSet.setLayers(getLayerSet(pForceReload));

			for (int i = 0; i < vector.size(); i++) {
				HashMap viewMap = vector.get(i);

				View view = getViewFromHashMap(pForceReload, userName,
						sessionId, viewMap, viewSet.getLayers());

				viewSet.getViews().add(i, view);

			}
			System.out.println("GETVIEWSET -> FIM ");
			return viewSet;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that receives a hashmap and converts it's
	 *         properties into a theme
	 * 
	 * @param viewName
	 *            The name of the view that the analyzed theme belongs to
	 * @param theme
	 *            The theme that will have it's visual's analyzed
	 * @return A new theme resulted from the conversion of the hashmaps
	 *         properties
	 * @throws GlueServerException
	 */

	private List<ThemeVisual> getVisualsFromTheme(String viewName,
			String pDBUserName, String sessionId, Theme theme)
			throws GlueServerException {
		List<ThemeVisual> visuals = new ArrayList<ThemeVisual>(theme.getReps()
				.size());
		try {
			for (int k = 0; k < theme.getReps().size(); k++) {

				visuals.add(
						k,
						getThemeVisual(
								viewName,
								theme.getName(),
								theme.getType(),
								Integer.parseInt(theme.getReps().get(k).getId()
										.toString())));
			}
			return visuals;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());

		}
	}

	private void insertLabelCurrentTheme(String sessionId, String propertyKey,
			ThemeToPlot theme, String fieldName) {
		if (PropertiesReader.getProperty(propertyKey).equals(theme.getName())) {
			terraJava.setLabelField(fieldName, sessionId);
			terraJava.setDefaultVisual(128, 0, 0, 0, 0, 10, "/tmp/arial.ttf",
					0, 0, 0, 0, 0, sessionId);

			terraJava.setPriorityField("CODLOG", sessionId);

			terraJava.setMinCollisionTolerance(5, sessionId);
			terraJava.setConflictDetect(true, sessionId);
			terraJava.drawLineAngleTextLabeling(sessionId);
		}
	}

	/**
	 * \brief This function receives two themes, a new and an old one, and
	 * compares them in order to determine if the name of the theme has changed
	 * 
	 * @param oldTheme
	 *            The old theme that will be compared
	 * @param newTheme
	 *            The new theme that will be compared
	 * @return A Boolean that indicates if a theme with the same id of that
	 *         theme already exists in the database
	 */

	private Boolean isThemeNameChanged(Theme oldTheme, Theme newTheme) {

		if (newTheme.getId().equals(oldTheme.getId())
				&& !newTheme.getName().equals(oldTheme.getName()))
			return true;

		return false;
	}

	public boolean loadNetwork(Integer layerId) throws GlueServerException {

		return terraJava.loadNetwork(layerId, sessionId);
	}

	public List<String> locateObject(Box canvasBox, int canvasWidth,
			int canvasHeight, Double x, Double y, Theme theme)
			throws GlueServerException {

		try {

			List<String> result = null;

			terraJava.setWorld(canvasBox.getX1(), canvasBox.getY1(),
					canvasBox.getX2(), canvasBox.getY2(), canvasWidth,
					canvasHeight, sessionId);
			terraJava.setTheme(theme.getName(), theme.getType(), sessionId);
			result = terraJava.locateObject(x, y, sessionId);
			return result;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());

		}

	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that receives a hashmap and converts it's
	 *         properties into a theme
	 * 
	 * @param viewName
	 *            The name of the view that the theme will belong to
	 * @param pForceReload
	 *            A boolean that indicates if the terraManager memory needs to
	 *            be cleaned
	 * @param pDBUserName
	 *            A string containing the user's name
	 * @param sessionId
	 *            A string containing the users session's id
	 * @param themeMap
	 *            The hashMap that will be converted into a theme
	 * @param Layers
	 *            A vector containing the layers
	 * @return A new view resulted from the conversion of the hashmaps
	 *         properties
	 */

	/**
	 * @author Carvalho, Andre
	 * 
	 *         \brief Method that returns an legend image of the current View
	 * 
	 * @param view
	 *            - The ViewToPlot object with all themes and parameters.
	 * @param visualText
	 *            - The themeVisual that will be persisted.
	 * @param width
	 *            - The width of the generated legend.
	 * @param imageType
	 *            - The type of the image. Valid values is 0=png, 1=jpeg, 2=gif
	 * @param canvasOpaque
	 *            - If the legend image is opaque or transparent.
	 * @param quality
	 *            - The quality of the image if imageType is jpeg.
	 * @param contextToGroupMap
	 *            - The object with all parameters to create thematic map.
	 * @return The legend image in byte array format.
	 */

	public byte[] plotLegendViewThreadSafe(ViewToPlot view,
			ThemeVisual visualText, Integer width, Integer imageType,
			Boolean canvasOpaque, Integer quality,
			ContextToGroupMap contextToGroupMap) throws GlueServerException {
		try {
			Vector<HashMap<String, Object>> themesList = new Vector<HashMap<String, Object>>();

			for (ThemeToPlot theme : view.getThemes()) {

				Vector<HashMap<String, Object>> visualMapList = new Vector<HashMap<String, Object>>();
				Vector<HashMap<String, Object>> highlightVisualMapList = new Vector<HashMap<String, Object>>();
				Vector<String> highlightObjectsList = new Vector<String>();

				for (ThemeVisual visual : theme.getThemeVisuals()) {
					if (visual.getRep() != null) {
						HashMap<String, Object> visualMap = getHashMapFromThemeVisual(visual);
						visualMapList.add(visualMap);
					}
				}

				if (theme.getObjectsToDraw() != null
						&& !theme.getObjectsToDraw().isEmpty()) {

					ThemeVisual polygonVisual = getHightlightVisualFromConfigFile(Representation.POLYGON);
					ThemeVisual lineVisual = getHightlightVisualFromConfigFile(Representation.LINE);
					ThemeVisual pointVisual = getHightlightVisualFromConfigFile(Representation.POINT);

					highlightVisualMapList
							.add(getHashMapFromThemeVisual(polygonVisual));
					highlightVisualMapList
							.add(getHashMapFromThemeVisual(lineVisual));
					highlightVisualMapList
							.add(getHashMapFromThemeVisual(pointVisual));

					highlightObjectsList = new Vector<String>(
							theme.getObjectsToDraw());

				}

				HashMap<String, Object> themeMap = new HashMap<String, Object>();
				themeMap.put("themeName", theme.getName());
				themeMap.put("themeVisualList", visualMapList);
				themeMap.put("highlightObjectList", highlightObjectsList);
				themeMap.put("highlightVisualList", highlightVisualMapList);
				themeMap.put("thematicMap", false);

				themesList.add(themeMap);

				if (theme.isGroup()) {

					HashMap<String, Object> themeMapForGroup = new HashMap<String, Object>();

					themeMapForGroup.put("themeName", theme.getName());
					themeMapForGroup.put("themeVisualList", visualMapList);
					themeMapForGroup.put("highlightObjectList", new Vector());
					themeMapForGroup.put("highlightVisualList", new Vector());
					themeMapForGroup.put("thematicMap", true);

					HashMap<String, Object> themeGroupingMap = createThematicMapThreadSafe(
							contextToGroupMap, sessionId);
					themeMapForGroup.put("themeGroupingMap", themeGroupingMap);

					themesList.add(themeMapForGroup);

				}

			}

			HashMap<String, Object> visualTextHashMap = getHashMapFromThemeVisual(visualText);

			HashMap<String, Integer> canvasBackgroundColor = new HashMap<String, Integer>();
			canvasBackgroundColor.put("r", view.getCanvasBackgroundColor()
					.getRed());
			canvasBackgroundColor.put("g", view.getCanvasBackgroundColor()
					.getGreen());
			canvasBackgroundColor.put("b", view.getCanvasBackgroundColor()
					.getBlue());

			return terraJava.drawLegendThemes(themesList, visualTextHashMap,
					canvasBackgroundColor, imageType, canvasOpaque, quality,
					width);
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());

		}
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that returns an image of the current View
	 * @throws GlueServerException
	 */

	public byte[] plotView(ViewToPlot view, Box plotBox, Integer width,
			Integer height, Integer imageType, Boolean canvasOpaque,
			Integer quality, ContextToGroupMap contextToGroupMap)
			throws GlueServerException {

		ThemeToPlot themeGroup = null;
		try {

			terraJava.setWorld(plotBox.getX1(), plotBox.getY1(),
					plotBox.getX2(), plotBox.getY2(), width, height, sessionId);

			List<ThemeToPlot> themesToDrawObjects = new ArrayList<ThemeToPlot>();

			for (ThemeToPlot theme : view.getThemes()) {

				if (theme.isGroup()) {
					themeGroup = theme;
				}
				terraJava.setTheme(theme.getName(), theme.getType(), sessionId);

				if (theme.getObjectsToDraw() != null) {
					themesToDrawObjects.add(theme);
					continue;
				}

				drawCurrentTheme(sessionId, theme);
			}

			if (!themesToDrawObjects.isEmpty()) {
				for (ThemeToPlot theme : themesToDrawObjects) {
					drawCurrentTheme(sessionId, theme);
					int representation = terraJava.getThemeRepresentation(0,
							sessionId);
					ThemeVisual visual = getHightlightVisualFromConfigFile(representation);
					terraJava.setDefaultVisual(new Integer(representation),
							visual.getRed(), visual.getGreen(),
							visual.getBlue(), visual.getStyle(),
							visual.getLineWidth(), visual.getFontName(),
							visual.getCountourColorRed(),
							visual.getCountourColorGreen(),
							visual.getCountourColorBlue(),
							visual.getStyleCountour(),
							visual.getWidthCountour(), sessionId);
					terraJava.drawSelectedObjects(
							new Vector(theme.getObjectsToDraw()), sessionId);
				}
			}

			if (themeGroup != null && contextToGroupMap != null) {
				terraJava.setTheme(themeGroup.getName(), themeGroup.getType(),
						sessionId);
				this.createThematicMap(contextToGroupMap);
			}

			return terraJava.getCanvasImage(imageType, canvasOpaque, quality,
					sessionId);

		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that returns an image of the current View
	 * @throws GlueServerException
	 */

	public byte[] plotView2(ViewToPlot view, Box plotBox, Integer width,
			Integer height, Integer imageType, Boolean canvasOpaque,
			Integer quality) throws GlueServerException {

		try {

			terraJava.setWorld(plotBox.getX1(), plotBox.getY1(),
					plotBox.getX2(), plotBox.getY2(), width, height, sessionId);

			List<ThemeToPlot> themesToDrawObjects = new ArrayList<ThemeToPlot>();

			for (ThemeToPlot theme : view.getThemes()) {

				if (theme.isGroup()) {
				}
				terraJava.setTheme(theme.getName(), theme.getType(), sessionId);

				if (theme.getObjectsToDraw() != null) {
					themesToDrawObjects.add(theme);
					continue;
				}

				drawCurrentTheme(sessionId, theme);
			}

			if (!themesToDrawObjects.isEmpty()) {
				for (ThemeToPlot theme : themesToDrawObjects) {
					drawCurrentTheme(sessionId, theme);
					int representation = terraJava.getThemeRepresentation(0,
							sessionId);
					ThemeVisual visual = getHightlightVisualFromConfigFile(representation);
					terraJava.setDefaultVisual(new Integer(representation),
							visual.getRed(), visual.getGreen(),
							visual.getBlue(), visual.getStyle(),
							visual.getLineWidth(), visual.getFontName(),
							visual.getCountourColorRed(),
							visual.getCountourColorGreen(),
							visual.getCountourColorBlue(),
							visual.getStyleCountour(),
							visual.getWidthCountour(), sessionId);
					terraJava.drawSelectedObjects(
							new Vector(theme.getObjectsToDraw()), sessionId);
				}
			}

			return terraJava.getCanvasImage(imageType, canvasOpaque, quality,
					sessionId);
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that returns an image of the current View
	 */

	@SuppressWarnings("rawtypes")
	public byte[] plotViewThreadSafe(ViewToPlot view, Box plotBox,
			Integer width, Integer height, Integer imageType,
			Boolean canvasOpaque, Integer quality,
			ContextToGroupMap contextToGroupMap) throws GlueServerException {// Attribute
																				// name
																				// for
																				// grouping
																				// is
																				// empty
		try {
			Vector<HashMap<String, Object>> themesList = new Vector<HashMap<String, Object>>();

			for (ThemeToPlot theme : view.getThemes()) {

				Vector<HashMap<String, Object>> visualMapList = new Vector<HashMap<String, Object>>();
				Vector<HashMap<String, Object>> highlightVisualMapList = new Vector<HashMap<String, Object>>();
				Vector<String> highlightObjectsList = new Vector<String>();

				for (ThemeVisual visual : theme.getThemeVisuals()) {
					if (visual.getRep() != null) {
						HashMap<String, Object> visualMap = getHashMapFromThemeVisual(visual);
						visualMapList.add(visualMap);
					}

				}

				if (theme.getObjectsToDraw() != null
						&& !theme.getObjectsToDraw().isEmpty()) {

					ThemeVisual polygonVisual = getHightlightVisualFromConfigFile(Representation.POLYGON);
					ThemeVisual lineVisual = getHightlightVisualFromConfigFile(Representation.LINE);
					ThemeVisual pointVisual = getHightlightVisualFromConfigFile(Representation.POINT);

					highlightVisualMapList
							.add(getHashMapFromThemeVisual(polygonVisual));
					highlightVisualMapList
							.add(getHashMapFromThemeVisual(lineVisual));
					highlightVisualMapList
							.add(getHashMapFromThemeVisual(pointVisual));

					highlightObjectsList = new Vector<String>(
							theme.getObjectsToDraw());

				}

				HashMap<String, Object> themeMap = new HashMap<String, Object>();
				themeMap.put("themeName", theme.getName());
				themeMap.put("themeVisualList", visualMapList);
				themeMap.put("highlightObjectList", highlightObjectsList);
				themeMap.put("highlightVisualList", highlightVisualMapList);
				themeMap.put("thematicMap", false);
				themeMap.put("useLabelConfig", false);
				themesList.add(themeMap);

				if (theme.isGroup()) {

					HashMap<String, Object> themeMapForGroup = new HashMap<String, Object>();

					themeMapForGroup.put("themeName", theme.getName());
					themeMapForGroup.put("themeVisualList", visualMapList);
					themeMapForGroup.put("highlightObjectList", new Vector());
					themeMapForGroup.put("highlightVisualList", new Vector());
					themeMapForGroup.put("thematicMap", true);
					themeMapForGroup.put("useLabelConfig", false);
					HashMap<String, Object> themeGroupingMap = createThematicMapThreadSafe(
							contextToGroupMap, sessionId);
					themeMapForGroup.put("themeGroupingMap", themeGroupingMap);

					themesList.add(themeMapForGroup);

				}
			}

			HashMap<String, Integer> canvasBackgroundColor = new HashMap<String, Integer>();
			canvasBackgroundColor.put("r", view.getCanvasBackgroundColor()
					.getRed());
			canvasBackgroundColor.put("g", view.getCanvasBackgroundColor()
					.getGreen());
			canvasBackgroundColor.put("b", view.getCanvasBackgroundColor()
					.getBlue());

			HashMap projectionMap = getHashMapFromProjection(view
					.getProjection());

			boolean useScaleControl = PropertiesReader
					.getBooleanProperty("terrajava.scale.control.enabled");

			// insertLabelCurrentTheme(sessionId, propertyKey, theme,
			// fieldName);

			return terraJava.drawThemes(themesList, plotBox.getX1(),
					plotBox.getY1(), plotBox.getX2(), plotBox.getY2(), width,
					height, false, imageType, canvasOpaque, quality,
					projectionMap, canvasBackgroundColor, useScaleControl);
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());

		}
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that returns a HashMasp with the projection
	 * @throws GlueServerException
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Box recompose(String currentView, Integer[] ids)
			throws GlueServerException {

		try {

			Vector themesIdsVec = new Vector();

			for (int i = 0; i < ids.length; i++) {

				themesIdsVec.add(ids[i]);

			}
			// _terraJSP.setCurrentView(currentView, userName, sessionId);
			HashMap hm = terraJava.getThemesBox(themesIdsVec, sessionId);

			Box box = new Box((Double) hm.get("x1"), (Double) hm.get("y1"),
					(Double) hm.get("x2"), (Double) hm.get("y2"));

			return box;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}

	}

	// @ }

	/**
	 * @author Ribeiro, Stephen M.
	 * 
	 *         \brief Method that remap coordinates based on a new projection
	 * @throws GlueServerException
	 */

	public Box remapCoordinates(Box box, Projection projFrom, Projection projTo)
			throws GlueServerException {

		try {

			Vector<Object> result = null;
			Coord[] coords = new Coord[2];

			coords[0] = new Coord(box.getX1(), box.getY1());
			coords[1] = new Coord(box.getX2(), box.getY2());

			result = terraJava.remapCoordinates(
					getVectorFromCoordsArray(coords),
					getHashMapFromProjection(projFrom),
					getHashMapFromProjection(projTo), sessionId);

			Coord[] coordsListResult = new Coord[result.size()];

			for (int i = 0; i < result.size(); i++) {

				coordsListResult[i] = new Coord();

				HashMap<String, Double> coord = (HashMap<String, Double>) result
						.elementAt(i);

				coordsListResult[i].setX(coord.get("x"));
				coordsListResult[i].setY(coord.get("y"));

			}

			Box resultBox = new Box(coordsListResult[0].getX(),
					coordsListResult[0].getY(), coordsListResult[1].getX(),
					coordsListResult[1].getY());

			return resultBox;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}

	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M. and Rocha, Fernanda.
	 * 
	 *         \brief Method that removes a view on the database Terralib
	 * @param currentView
	 * @throws GlueServerException
	 */

	public Boolean removeView(String currentView) throws GlueServerException {

		try {

			terraJava.setCurrentView(currentView, userName, sessionId);

			return terraJava.removeView(sessionId);
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M. and Rocha, Fernanda.
	 * 
	 *         \brief Method that removes a view on the database Terralib
	 * @param currentView
	 * @throws GlueServerException
	 */

	public Boolean removeViewMem(String currentView) throws GlueServerException {

		try {

			terraJava.setCurrentView(currentView, userName, sessionId);

			return terraJava.removeViewMem(currentView, userName, sessionId);
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	public void saveFeature(byte[] featureStream) throws GlueServerException {

	}

	/**
	 * @author Oliveira, Andrï¿½ G. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that persists a memory view into the database
	 * 
	 * @param name
	 *            The name of the view that will be saved
	 * @return A boolean that indicates if the operation was successfully
	 *         concluded
	 * @throws GlueServerException
	 */

	public Boolean saveView2DB(String name) throws GlueServerException {
		boolean result = false;

		try {
			result = terraJava.saveView2DB(name, userName, sessionId);
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
		return result;
	}

	/**
	 * \brief This function receives a view and searches the database to check
	 * if there are any themes with the same id
	 * 
	 * @param view
	 *            The view that will be checked
	 * @param themeName
	 *            The theme that will be searched
	 * @return A Boolean that indicates if a theme with the same id of that
	 *         theme already exists in the database
	 * @throws GlueServerException
	 */

	private Integer searchThemeOnView(View view, Long themeId, String themeName)
			throws GlueServerException {

		try {
			if (view.getThemes() != null) {
				for (int i = 0; i < view.getThemes().size(); i++) {
					System.out.println("Essa VIEW TEM :"
							+ view.getThemes().size());
					if (themeId != null) {
						if (themeId.equals(view.getThemes().get(i).getId())) {
							System.out.println("retornou :" + i);
							return i;
						}
					} else {
						if (themeName.equals(view.getThemes().get(i).getName())) {
							System.out.println("retornou :" + i);
							return i;
						}
					}
				}
			} else {
				System.err
						.println("UPDATEVIEW -> FINDTHEME -> nÃ£o achou tema");
				return null;
			}

		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
		return null;
	}

	/**
	 * @author Oliveira, Andrï¿½ G. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that retrieves a set of Views from the database
	 * @throws GlueServerException
	 */

	@SuppressWarnings("rawtypes")
	private View searchView(Long viewId, String viewName, String pDBUserName,
			Boolean pForceReload, String sessionId) throws GlueServerException {
		try {
			Vector<HashMap> vector = terraJava.getViewSetTree(pForceReload,
					sessionId);

			for (int i = 0; i < vector.size(); i++) {
				System.out.println(vector.get(i));
			}

			List<Layer> layers = getLayerSet(pForceReload);

			View view = null;

			for (int i = 0; i < vector.size(); i++) {

				HashMap viewMap = vector.elementAt(i);

				if (viewName == null) {
					String id = (String) (viewMap).get("viewId");
					Long longId = Long.parseLong(id);

					if (longId.equals(viewId)) {
						view = getViewFromHashMap(pForceReload, pDBUserName,
								sessionId, viewMap, layers);
						System.out.println("VISTA ENCONTRADA");
						return view;
					}
				}
				if (viewId == null) {

					String name = (String) (viewMap).get("viewName");

					if (name.equals(viewName)) {
						view = getViewFromHashMap(pForceReload, pDBUserName,
								sessionId, viewMap, layers);
						System.out.println("VISTA ENCONTRADA");
						return view;
					}
				}

			}

			System.err.println("VISTA NÃƒO ENCONTRADA");

			return view;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that persists a visual into a theme
	 * @param themeVisual
	 *            The themeVisual that will be persisted
	 * @param persistence
	 *            A boolean that indicates if the visual will be persisted in
	 *            the database (true) or just saved in the terraManager
	 *            memory(false)
	 * @param sessionId
	 *            A string containing the user session's id
	 * @throws GlueServerException
	 */

	private Boolean setDefaultVisual(ThemeVisual themeVisual, String sessionId)
			throws GlueServerException {

		Boolean se = false;

		try {
			se = terraJava.setDefaultVisual(themeVisual.getRep(),
					themeVisual.getRed(), themeVisual.getGreen(),
					themeVisual.getBlue(), themeVisual.getStyle(),
					themeVisual.getLineWidth(), themeVisual.getFontName(),
					themeVisual.getCountourColorRed(),
					themeVisual.getCountourColorGreen(),
					themeVisual.getCountourColorBlue(),
					themeVisual.getStyleCountour(),
					themeVisual.getWidthCountour(), sessionId);
			return se;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());

		}

	}

	public boolean setTheme(String themeName, Integer themeType) {
		return terraJava.setTheme(themeName, themeType, sessionId);
	}

	public void setTHEMETO(ThemeToPlot t) {
	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that persists a line visual into a theme
	 * @param themeVisual
	 *            The themeVisual that will be persisted
	 * @param persistence
	 *            A boolean that indicates if the visual will be persisted in
	 *            the database (true) or just saved in the terraManager
	 *            memory(false)
	 * @param sessionId
	 *            A string containing the user session's id
	 * @throws GlueServerException
	 */

	private Boolean setThemeVisualLine(ThemeVisual themeVisual,
			Boolean persistence, String sessionId) throws GlueServerException {

		Boolean se = false;

		try {
			se = terraJava.setThemeVisualLine(themeVisual.getStyle(),
					themeVisual.getRed(), themeVisual.getGreen(),
					themeVisual.getBlue(), themeVisual.getTransparency(),
					themeVisual.getLineWidth(), persistence, sessionId);

		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());

		}

		return se;

	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that persists a point visual into a theme
	 * @param themeVisual
	 *            The themeVisual that will be persisted
	 * @param persistence
	 *            A boolean that indicates if the visual will be persisted in
	 *            the database (true) or just saved in the terraManager
	 *            memory(false)
	 * @param sessionId
	 *            A string containing the user session's id
	 * @throws GlueServerException
	 */

	private Boolean setThemeVisualPoint(ThemeVisual themeVisual,
			Boolean persistence, String sessionId) throws GlueServerException {

		Boolean se = false;

		try {
			se = terraJava.setThemeVisualPoint(themeVisual.getStyle(),
					themeVisual.getRed(), themeVisual.getGreen(),
					themeVisual.getBlue(), themeVisual.getPointSize(),
					persistence, sessionId);

		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}

		return se;

	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that persists a polygon visual into a theme
	 * @param themeVisual
	 *            The themeVisual that will be persisted
	 * @param persistence
	 *            A boolean that indicates if the visual will be persisted in
	 *            the database (true) or just saved in the terraManager
	 *            memory(false)
	 * @param sessionId
	 *            A string containing the user session's id
	 * @throws GlueServerException
	 * 
	 */

	private Boolean setThemeVisualPolygon(ThemeVisual themeVisual,
			Boolean persistence, String sessionId) throws GlueServerException {

		Boolean se = false;

		try {

			se = terraJava.setThemeVisualPolygon(themeVisual.getStyle(),
					themeVisual.getRed(), themeVisual.getGreen(),
					themeVisual.getBlue(), themeVisual.getTransparency(),
					themeVisual.getStyleCountour(),
					themeVisual.getCountourColorRed(),
					themeVisual.getCountourColorGreen(),
					themeVisual.getCountourColorBlue(),
					themeVisual.getCountourTransparency(),
					themeVisual.getWidthCountour(), persistence, sessionId);

		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());

		}

		return se;

	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that persists a text visual into a theme
	 * @param themeVisual
	 *            The themeVisual that will be persisted
	 * @param persistence
	 *            A boolean that indicates if the visual will be persisted in
	 *            the database (true) or just saved in the terraManager
	 *            memory(false)
	 * @param sessionId
	 *            A string containing the user session's id
	 * @throws GlueServerException
	 */

	private Boolean setThemeVisualText(ThemeVisual themeVisual,
			Boolean persistence, String sessionId) throws GlueServerException {

		Boolean se = false;

		try {

			se = terraJava.setThemeVisualText(themeVisual.getRed(),
					themeVisual.getGreen(), themeVisual.getBlue(),
					themeVisual.getCountourColorRed(),
					themeVisual.getCountourColorGreen(),
					themeVisual.getCountourColorBlue(),
					themeVisual.getLineWidth(), themeVisual.getFontName(),
					themeVisual.isBold(), themeVisual.isItalic(),
					themeVisual.getVerticalAlign(),
					themeVisual.getHorizontalAlign(), themeVisual.getTabSize(),
					themeVisual.getLineSpace(), persistence, sessionId);

		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}

		return se;

	}

	/**
	 * @author Siqueira, Felipe V. and Ribeiro, Stephen M.
	 * 
	 *         \brief
	 * @throws GlueServerException
	 */

	public Boolean setWorkProjection(Projection projection)
			throws GlueServerException {

		try {

			Boolean result = false;

			result = terraJava.setWorkProjection(
					getHashMapFromProjection(projection), sessionId);

			return result;
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
	}

	public boolean updateFeatures(List<String> listGeometries) {
		Vector<String> vecGeom = new Vector<String>(listGeometries);
		return terraJava.updateFeatures(vecGeom, sessionId);
	}

	public boolean updateThemeBox(String viewName, String themeName) {

		if (terraJava == null) {
			terraJava = new TerraJavaJNI();
		}
		try {
			terraJava.setCurrentView(viewName, userName, sessionId);
			terraJava.setTheme(themeName, 0, sessionId);
			return terraJava.updateThemeBox(sessionId);
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			return false;
		}

	}

	/**
	 * @author Oliveira, Andrï¿½ G. and Ribeiro, Stephen M.
	 * 
	 *         \brief Method that updates a view on the database
	 * @throws GlueServerException
	 */

	@SuppressWarnings("rawtypes")
	public View updateView(View newView, Boolean isReload, Boolean isMemView)
			throws GlueServerException {

		try {

			// Variables
			Boolean result = true;
			Boolean persist = false;
			View _newView = newView;
			View _oldView = null;
			View _updatedView = null;
			Integer nNewViewThemes = 0;
			Integer nOldViewThemes = 0;
			Vector<Integer> themesIdsVec = null;

			_oldView = getView(_newView.getId(), userName, false, sessionId);

			// If user wants to revert his view
			if (isReload)
				return _oldView;

			if (isMemView) {
				persist = false;
			} else {
				persist = true;
			}

			// Testing how many themes each view has
			if (_newView.getThemes() != null) {
				nNewViewThemes = _newView.getThemes().size();
			}

			if (_oldView != null) {
				if (_oldView.getThemes() != null) {
					nOldViewThemes = _oldView.getThemes().size();
				}
			}

			result = configureView(persist, _newView, _oldView, nNewViewThemes,
					nOldViewThemes);

			if (result) {

				if (_oldView == null) {
					_updatedView = getView(_newView.getName(), userName, false,
							sessionId);
				} else {
					_updatedView = getView(_oldView.getName(), userName, false,
							sessionId);
				}

				// Setting themes visibilities and priority order to database
				if (nNewViewThemes > 0) {

					// Setting themes visibilities
					Vector<HashMap> themesVisibilities = getHashMapFromThemesVisibility(
							_updatedView, _newView);

					if (themesVisibilities != null) {
						result = terraJava.setCurrentView(
								_updatedView.getName(), userName, sessionId);
						result = terraJava.setThemesVisibility(
								themesVisibilities, persist, sessionId);
					}

					// Setting themes ids into a vector to update themes
					// priority
					// order later
					themesIdsVec = new Vector<Integer>();
					for (int i = 0; i < nNewViewThemes; i++) {
						// nï¿½o pode ser updatedview pois esta nao tem as
						// prioridades.
						// nï¿½o pode ser o id da newview pois esta nem sempre
						// tera
						// todos os ids

						List<Theme> themes = _newView.getThemes();
						Theme theme = themes.get(i);
						themesIdsVec.add(Integer.parseInt(theme.getId()
								.toString()));
					}

					terraJava.setThemesPriorityOrder(themesIdsVec, persist,
							sessionId);

					Integer themePos = null;
					Theme auxTheme = null;

					// Rearranging the themes from the updated view
					for (int i = 0; i < nNewViewThemes; i++) {

						themePos = findThemeOnView(_updatedView, _newView
								.getThemes().get(i).getName());

						if (themePos != null) {
							auxTheme = _updatedView.getThemes().get(i);
							_updatedView.getThemes().set(i,
									_updatedView.getThemes().get(themePos));
							_updatedView.getThemes().set(themePos, auxTheme);
						}
					}

					// Setting the themes visibilities in the updatedView
					for (int i = 0; i < nNewViewThemes; i++) {
						_updatedView
								.getThemes()
								.get(i)
								.setVisibility(
										_newView.getThemes().get(i)
												.isVisibility());
					}

				}

				return _updatedView;
			}
		} catch (RuntimeException e) {
			DailyExceptionLogger.logging(e.getMessage(), e);
			throw new GlueServerException(e.getMessage());
		}
		return null;
	}
}
