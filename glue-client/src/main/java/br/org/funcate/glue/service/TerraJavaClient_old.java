package br.org.funcate.glue.service;
//
//import java.awt.Color;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.List;
//
//import br.org.funcate.glue.model.Attribute;
//import br.org.funcate.glue.model.Box;
//import br.org.funcate.glue.model.Layer;
//import br.org.funcate.glue.model.Projection;
//import br.org.funcate.glue.model.Representation;
//import br.org.funcate.glue.model.Theme;
//import br.org.funcate.glue.model.ThemeVisual;
//import br.org.funcate.glue.model.UserType;
//import br.org.funcate.glue.model.View;
//import br.org.funcate.glue.model.ViewSet;
//import br.org.funcate.glue.model.ViewToPlot;
//import br.org.funcate.glue.model.exception.GlueServerException;
//import br.org.funcate.glue.utilities.PropertiesReader;
//import br.org.funcate.glueservices.terrajavaservices.ContextToGroupMap;
//import br.org.funcate.glueservices.terrajavaservices.GlueServerException_Exception;
//import br.org.funcate.glueservices.terrajavaservices.TerraJavaServices;
//import br.org.funcate.glueservices.terrajavaservices.TerraJavaServicesService;
//
public class TerraJavaClient_old {
//
//	private static TerraJavaServices _service;
//	private TerraJavaServicesService _porta;
//
//	private String webServerStr = null;
//	public String hostName = PropertiesReader.getProperty("service.ip");
//	public String port = PropertiesReader.getProperty("service.port");
//	public String servletPath = PropertiesReader
//			.getProperty("terrajava.servlet");
//	public static String _sessionId;
//	public static String _DBUserName;
//	public static UserType _userType;
//
//	public TerraJavaClient_old() throws GlueServerException {
//
//		webServerStr = "http://" + hostName + ":" + port + servletPath;
//
//		_sessionId = getSessionId();
//
//		_userType = getUserType();
//
//		_DBUserName = getUserName();
//
//		_porta = new TerraJavaServicesService();
//		_service = _porta.getTerraJavaServicesPort();
//
//		connect(_userType.toString());
//
//	}
//
//	private Boolean connect(String pUserType) throws GlueServerException {
//		try {
//			return _service.connect(pUserType, _sessionId);
//		} catch (GlueServerException_Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new GlueServerException(e.getMessage());
//		}
//	}
//
//	public Boolean destroySession() throws GlueServerException {
//		try {
//			return _service.destroySession(_sessionId);
//		} catch (GlueServerException_Exception e) {
//			throw new GlueServerException(
//					"Não foi possível destruir a sessão do usuário.", e);
//		}
//	}
//
//	public Box recompose(String currentView, List<Integer> themesIds)
//			throws GlueServerException {
//		try {
//			return _service.recompose(currentView, themesIds, _DBUserName,
//					_sessionId);
//		} catch (GlueServerException_Exception e) {
//			throw new GlueServerException(
//					"Não foi possível recompor a vista solicitada utilizando o serviço.",
//					e);
//		}
//
//	}
//
//	public View updateView(View newView, Boolean isReload, Boolean isMemView)
//			throws GlueServerException {
//
//		try {
//			return _service.updateView(newView, isReload, isMemView,
//					_DBUserName, _sessionId);
//		} catch (GlueServerException_Exception e) {
//			throw new GlueServerException(
//					"Não foi possível atualizar a vista solicitada utilizando o serviço.",
//					e);
//		}
//	}
//
//	public boolean removeViewMem(String currentView) throws GlueServerException {
//		try {
//			return _service.removeViewMem(currentView, _DBUserName, _sessionId);
//		} catch (GlueServerException_Exception e) {
//			throw new GlueServerException(
//					"Não foi possível remover a vista em memória solicitada utilizando o serviço.",
//					e);
//		}
//	}
//
//	public boolean removeView(String currentView) throws GlueServerException {
//
//		try {
//			return _service.removeView(currentView, _DBUserName, _sessionId);
//		} catch (GlueServerException_Exception e) {
//			throw new GlueServerException(
//					"Não foi possível remover a vista solicitada utilizando o serviço.",
//					e);
//		}
//	}
//
//	public boolean setWorkProjection(Projection projection)
//			throws GlueServerException {
//		try {
//			return _service.setWorkProjection(projection, _sessionId);
//		} catch (GlueServerException_Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new GlueServerException(e.getMessage());
//		}
//
//	}
//
//	public synchronized byte[] plotView(ViewToPlot viewToPlot, Box plotBox,
//			int width, int height, Integer imageType, Boolean canvasOpaque,
//			Integer quality, ContextToGroupMap contextToGroupMap)
//			throws GlueServerException {
//		try {
//			return _service.plotView(viewToPlot, plotBox, width, height,
//					imageType, canvasOpaque, quality, _DBUserName,
//					contextToGroupMap, _sessionId);
//		} catch (GlueServerException_Exception e) {
//			throw new GlueServerException(
//					"Não foi possível desenhar a vista solicitada utilizando o serviço.",
//					e);
//		}
//	}
//
//	public byte[] plotViewThreadSafe(ViewToPlot viewToPlot, Box plotBox,
//			int width, int height, Integer imageType, Boolean canvasOpaque,
//			Integer quality, ContextToGroupMap contextToGroupMap)
//			throws GlueServerException {
//
//		try {
//			return _service.plotViewThreadSafe(viewToPlot, plotBox, width,
//					height, imageType, canvasOpaque, quality, _DBUserName,
//					contextToGroupMap, _sessionId);
//		} catch (GlueServerException_Exception e) {
//			throw new GlueServerException(
//					"Não foi possível desenhar paralelamente a vista solicitada utilizando o serviço.",
//					e);
//		}
//	}
//
//	public byte[] plotLegendViewThreadSafe(ViewToPlot viewToPlot,
//			ThemeVisual visualText, int width, Integer imageType,
//			Boolean canvasOpaque, Integer quality,
//			ContextToGroupMap contextToGroupMap) throws GlueServerException {
//
//		try {
//			return _service.plotLegendViewThreadSafe(viewToPlot, visualText,
//					width, imageType, canvasOpaque, quality, contextToGroupMap,
//					_sessionId);
//		} catch (GlueServerException_Exception e) {
//			throw new GlueServerException(
//					"Não foi possível desenhar paralelamente a legenda da vista solicitada utilizando o serviço.",
//					e);
//		}
//	}
//
//	public List<Layer> getLayersSet(boolean forceReload)
//			throws GlueServerException {
//
//		try {
//			return _service.getLayerSet(forceReload, _sessionId);
//		} catch (GlueServerException_Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new GlueServerException(e.getMessage());
//		}
//
//	}
//
//	public ViewSet getViewSet(Boolean forceReload) throws GlueServerException {
//
//		try {
//			return _service.getViewSet(_DBUserName, forceReload, _sessionId);
//		} catch (GlueServerException_Exception e) {
//			throw new GlueServerException(
//					"Não foi possível recuperar a lista de vista, temas e layers utilizando o serviço.",
//					e);
//		}
//	}
//
//	private UserType getUserType() {
//
//		return UserType.ADMIN;
//	}
//
//	public String getSessionId() {
//
//		ObjectInputStream inputFromServlet = null;
//		String result = null;
//
//		try {
//
//			String servletGET = webServerStr + "?"
//					+ URLEncoder.encode("selectedService") + "="
//					+ URLEncoder.encode("GetSessionId");
//
//			// connect to the servlet
//
//			URL studentDBservlet = new URL(servletGET);
//			URLConnection servletConnection = studentDBservlet.openConnection();
//
//			// Read the input from the servlet.
//			//
//			// The servlet will return a serialized vector containing
//			// student entries.
//			//
//
//			inputFromServlet = new ObjectInputStream(
//					servletConnection.getInputStream());
//
//			result = readString2(inputFromServlet);
//
//		} catch (Exception e) {
//			System.out.println("Não foi abrir conexao com o servidor");
//			System.out.println(e.toString());
//		}
//
//		return result;
//
//	}
//
//	public String readString2(ObjectInputStream theInputFromServlet) {
//		// TODO Auto-generated method stub
//		String StringResult = null;
//
//		try {
//			StringResult = (String) theInputFromServlet.readObject();
//
//			theInputFromServlet.close();
//		} catch (IOException e) {
//			System.out.println(e.toString());
//		} catch (ClassNotFoundException e) {
//			System.out.println(e.toString());
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//
//		return StringResult;
//	}
//
//	private String getUserName() {
//		// need implementation
//		String userName = PropertiesReader.getProperty("db.username");
//
//		return userName;
//	}
//
//	public Boolean configCanvas(String currentView, Projection projection,
//			Color rgb) throws GlueServerException {
//
//		try {
//			return _service.configCanvas(currentView, projection, rgb.getRed(),
//					rgb.getGreen(), rgb.getBlue(), _DBUserName, _sessionId);
//		} catch (GlueServerException_Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new GlueServerException(e.getMessage());
//		}
//
//	}
//
//	public Box remapCoordinates(Box box, Projection sourceProj,
//			Projection destProj) throws GlueServerException {
//		List<Projection> projList = new ArrayList<Projection>();
//		projList.add(sourceProj);
//		projList.add(destProj);
//		try {
//			return _service.remapCoordinates(box, projList, _sessionId);
//		} catch (GlueServerException_Exception e) {
//			e.printStackTrace();
//			throw new GlueServerException(
//					"Não foi possível reprojetar coordenadas utilizando o serviço.",
//					e);
//		}
//	}
//
//	public static String getRegion(Long Ip) throws GlueServerException {
//		try {
//			return _service.getContinent(Ip);
//		} catch (GlueServerException_Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new GlueServerException(e.getMessage());
//		}
//	}
//
//	public List<String> locateObject(Box currentBox, int canvasWidth,
//			int canvasHeight, double x, double y, Theme theme)
//			throws GlueServerException {
//		try {
//			return _service.locateObject(currentBox, canvasWidth, canvasHeight,
//					x, y, theme, _sessionId);
//		} catch (GlueServerException_Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new GlueServerException(e.getMessage());
//		}
//	}
//
//	/**
//	 * List [alias, rotulo, valor]*
//	 * 
//	 * @param objectid
//	 * @param themeName
//	 * @return
//	 * @throws GlueServerException
//	 */
//	public List<Object> fetchAttributes(String objectid, String themeName, Long themeId)
//			throws GlueServerException {
//		try {
//			return _service.fetchAttributes(objectid, themeName, _sessionId, themeId);
//		} catch (GlueServerException_Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new GlueServerException(e.getMessage());
//		}
//	}
//
//	public Box getCurrentThemeBox(String themeName, String restrictions)
//			throws GlueServerException {
//		try {
//			return _service.getCurrentThemeBox(themeName, restrictions,
//					_sessionId);
//		} catch (GlueServerException_Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new GlueServerException(e.getMessage());
//		}
//	}
//
//	public List<String> getAttributesList(String themeName, String viewName)
//			throws GlueServerException {
//		try {
//			return _service.getAttributesList(viewName, themeName, _DBUserName,
//					_sessionId);
//		} catch (GlueServerException_Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new GlueServerException(e.getMessage());
//		}
//	}
//
//	public boolean addGeometry(int representation, List<Object> verticeList,
//			List<Object> attrList, String layerName, Projection projection)
//			throws GlueServerException {
//		try {
//			return _service.addGeometry(representation, verticeList, attrList,
//					layerName, projection, _sessionId);
//		} catch (Exception e) {
//			throw new GlueServerException(
//					"Não foi possível reprojetar coordenadas utilizando o serviço.",
//					e);
//		}
//	}
//
//	public List<Double> getCentroidForBiggestGeometry(String viewName,
//			String themeName, String objectId)/*
//											 * throws GlueServerException
//											 */{
//		try {
//			return _service.getCentroidForBiggestGeometry(objectId, viewName,
//					themeName, _DBUserName, _sessionId);
//		} catch (GlueServerException_Exception e) {
//			e.printStackTrace();
//			/*
//			 * throw new GlueServerException( TRATAR A EXCEÇÃO DEPOIS DE TESTES
//			 * "Não foi possível requisitar o centróide.", e);
//			 */
//			return null;
//		}
//	}
//
//	public boolean buildCollection(String objectId, String viewName,
//			String themeName) {
//		try {
//			return _service.buildCollection(objectId, viewName, themeName,
//					_DBUserName, _sessionId);
//		} catch (GlueServerException_Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	public boolean updateThemeBox(String viewName, String themeName) {
//		try {
//			return _service.updateThemeBox(viewName, themeName, _DBUserName,
//					_sessionId);
//		} catch (GlueServerException_Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	public byte[] drawThemeText(ViewToPlot view, Box plotBox, int width,
//			int height, int imageType, boolean canvasOpaque, int quality) {
//		try {
//			return _service.drawThemeText(view, plotBox, width, height,
//					imageType, canvasOpaque, quality);
//		} catch (GlueServerException_Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	/*public boolean createLayer(String layerName, Projection projection, Representation representation,
//			Box box, List<Attribute> attributes, String sessionId){
//		try{
//			return _service.createLayer(layerName, projection, representation, box, attributes
//					, sessionId);
//		}catch(GlueServerException_Exception e){
//			e.printStackTrace();
//		}
//		return false;
//	}*/
//
//	/**
//	 * @author Severino, Bruno de Oliveira.
//	 * 
//	 * @param layerName
//	 * @param projection
//	 * @param representation
//	 * @param box
//	 * @param attributes
//	 * @return
//	 * @throws GlueServerException_Exception
//	 * @throws IllegalAccessException_Exception
//	 * @throws InstantiationException_Exception
//	 */
////	public boolean createLayer(String layerName, Projection projection, Representation representation,
////			Box box, List<Attribute> attributes)
////			throws GlueServerException_Exception, IllegalAccessException_Exception, InstantiationException_Exception{
////		
////		return _service.createLayer(layerName, projection, representation, box, attributes, _sessionId);
////	}
////	
////	/**
////	 * @author Severino, Bruno de Oliveira.
////	 * 
////	 * @param newThemeName
////	 * @param layerName
////	 * @return
////	 * @throws GlueServerException_Exception
////	 */
////	public boolean createTheme(String newThemeName, String layerName) throws GlueServerException_Exception{
////		return _service.createTheme(newThemeName, layerName, _sessionId);
////	}
////	
////	/**
////	 * @author Severino, Bruno de Oliveira.
////	 * 
////	 * @param viewName
////	 * @return
////	 */
////	public boolean createView(String viewName){
////		return _service.createView(viewName, "", _sessionId);
////	}
}
