/**
 * \file XMLReader.java
 * This class reads a XML File
 */
package br.org.funcate.glue.model.xml;

import java.net.URL;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.org.funcate.glue.view.InterfaceWMS;
import br.org.funcate.glue.view.LocalOptionPane;

/**
 * \brief This reads a XML File \author Emerson Leite de Moraes \author Willyan
 * Aleksander
 * 
 * Version 1.0
 * 
 */
public class XMLReader {

	private LatLonBoundingBox bBox;
	/** <Attribute LatLonBoudingBox */

	private JAXBContext _context;
	/** < Attribute type JAXBContext */
	private Unmarshaller _unmarshaller;
	/** < Attribute type Unmarshaller */
	private WMTMSCapabilities _element;
	/** <Attribute type WMTMSCapabilities */
	private ArrayList<String> _jlNotSelectedLayers, _jlNotSelectedTitles, _jlSelectedTitles, _jlSelectedLayers, _cbImageFormt;

	public ArrayList<String> get_jlNotSelectedLayers() {
		return _jlNotSelectedLayers;
	}

	public void set_jlNotSelectedLayers(ArrayList<String> jlNotSelectedLayers) {
		_jlNotSelectedLayers = jlNotSelectedLayers;
	}

	public ArrayList<String> get_jlNotSelectedTitles() {
		return _jlNotSelectedTitles;
	}

	public void set_jlNotSelectedTitles(ArrayList<String> jlNotSelectedTitles) {
		_jlNotSelectedTitles = jlNotSelectedTitles;
	}

	public ArrayList<String> get_jlSelectedTitles() {
		return _jlSelectedTitles;
	}

	public void set_jlSelectedTitles(ArrayList<String> jlSelectedTitles) {
		_jlSelectedTitles = jlSelectedTitles;
	}

	public ArrayList<String> get_jlSelectedLayers() {
		return _jlSelectedLayers;
	}

	public void set_jlSelectedLayers(ArrayList<String> jlSelectedLayers) {
		_jlSelectedLayers = jlSelectedLayers;
	}

	public ArrayList<String> get_cbImageFormt() {
		return _cbImageFormt;
	}

	public void set_cbImageFormt(ArrayList<String> cbImageFormt) {
		_cbImageFormt = cbImageFormt;
	}

	/** Constructor */
	public XMLReader(URL Xml) {
		try {
			_context = JAXBContext.newInstance("br.org.funcate.glue.model.xml");
			_unmarshaller = _context.createUnmarshaller();
			_element = (WMTMSCapabilities) _unmarshaller.unmarshal(Xml);
		} catch (JAXBException e) {
			LocalOptionPane.getInstance("Erro ao ler o XML.", "Aviso!");
		}
	}

	/** Returns XML Version */
	public String getVersion() {
		String v1 = _element.getVersion();
		return v1;
	}

	/**
	 * @name Populate
	 * 
	 *       These methods populates the nodes of XML.
	 */

	// @{

	/** Populate Capability node */
	public Capability populateCapability() {
		Capability c1 = _element.getCapability();
		return c1;
	}

	/** Populate GetCapabilities node */
	public GetCapabilities populateGetCapabilities() {
		GetCapabilities c1 = _element.getCapability().getRequest().getGetCapabilities();
		return c1;
	}

	/** Populate GetCapabilities GetOnlineResource node */
	public OnlineResource populateCapabilitiesGetOnlineResource() {
		OnlineResource o1 = _element.getCapability().getRequest().getGetCapabilities().getDCPType().getHTTP().getGet().getOnlineResource();
		return o1;
	}

	/** Populate GetCapabilities PostOnlineResource node */
	public OnlineResource populateCapabilitiesPostOnlineResource() {
		OnlineResource o1 = _element.getCapability().getRequest().getGetCapabilities().getDCPType().getHTTP().getPost().getOnlineResource();
		return o1;
	}

	/** Populate GetMap node */
	public GetMap populateGetMap() {
		GetMap g1 = _element.getCapability().getRequest().getGetMap();
		return g1;
	}

	/** Populate GetMap GetOnlineResource node */
	public OnlineResource populateMapGetOnlineResource() {
		OnlineResource o1 = _element.getCapability().getRequest().getGetMap().getDCPType().getHTTP().getGet().getOnlineResource();
		return o1;
	}

	/** Populate GetMap PostOnlineResource node */
	public OnlineResource populateMapPostOnlineResource() {
		OnlineResource o1 = _element.getCapability().getRequest().getGetMap().getDCPType().getHTTP().getPost().getOnlineResource();
		return o1;
	}

	/** Populate GetFeatureInfo node */
	public GetFeatureInfo populateGetFeatureInfo() {
		GetFeatureInfo g1 = _element.getCapability().getRequest().getGetFeatureInfo();
		return g1;
	}

	/** Populate FeatureInfo GetOnlineResource node */
	public OnlineResource populateFeatureGetOnlineResource() {
		OnlineResource o1 = _element.getCapability().getRequest().getGetFeatureInfo().getDCPType().getHTTP().getGet().getOnlineResource();
		return o1;
	}

	/** Populate FeatureInfo PostOnlineResource node */
	public OnlineResource populateFeaturePostOnlineResource() {
		OnlineResource o1 = _element.getCapability().getRequest().getGetMap().getDCPType().getHTTP().getPost().getOnlineResource();
		return o1;
	}

	/** Populate DescribeLayer node */
	public DescribeLayer populateDescribeLayer() {
		DescribeLayer d1 = _element.getCapability().getRequest().getDescribeLayer();
		return d1;
	}

	/** Populate DescribeLayer GetOnlineResource node */
	public OnlineResource populateDecribeGetOnlineResource() {
		OnlineResource o1 = _element.getCapability().getRequest().getDescribeLayer().getDCPType().getHTTP().getGet().getOnlineResource();
		return o1;
	}

	/** Populate Describelayer PostOnlineResource node */
	public OnlineResource populateDecribePostOnlineResource() {
		OnlineResource o1 = _element.getCapability().getRequest().getDescribeLayer().getDCPType().getHTTP().getPost().getOnlineResource();
		return o1;
	}

	/** Populate GetLegendGrafic node */
	public GetLegendGraphic populateGetLegendGraphic() {
		GetLegendGraphic g1 = _element.getCapability().getRequest().getGetLegendGraphic();
		return g1;
	}

	/** Populate GetLegendGrafic GetOnlineResource node */
	public OnlineResource populateLegendGetOnlineResource() {
		OnlineResource o1 = _element.getCapability().getRequest().getGetLegendGraphic().getDCPType().getHTTP().getGet().getOnlineResource();
		return o1;
	}

	/** Populate GetLegendGrafic PostOnlineResource node */
	public OnlineResource populateLegendPostOnlineResource() {
		OnlineResource o1 = _element.getCapability().getRequest().getGetLegendGraphic().getDCPType().getHTTP().getPost()
				.getOnlineResource();
		return o1;
	}

	/** Populate GetStyles node */
	public GetStyles populateGetStyles() {
		GetStyles g1 = _element.getCapability().getRequest().getGetStyles();
		return g1;
	}

	/** Populate GetStyles GetOnlineResource node */
	public OnlineResource populateStylesGetOnlineResource() {
		OnlineResource o1 = _element.getCapability().getRequest().getGetStyles().getDCPType().getHTTP().getGet().getOnlineResource();
		return o1;
	}

	/** Populate GetStyles PostOnlineResource node */
	public OnlineResource populateStylesPostOnlineResource() {
		OnlineResource o1 = _element.getCapability().getRequest().getGetStyles().getDCPType().getHTTP().getPost().getOnlineResource();
		return o1;
	}

	/** Populate Exception node */
	public Exception populateException() {
		Exception e1 = _element.getCapability().getException();
		return e1;
	}

	/** Populate UserDefinedSymbolization node */
	public UserDefinedSymbolization populateUserDefinedSymbolization() {
		UserDefinedSymbolization u1 = _element.getCapability().getUserDefinedSymbolization();
		return u1;
	}

	/** Populate Layer node */
	public Layer populateLayer() {
		Layer l1 = _element.getCapability().getLayer();
		return l1;
	}

	/** Populate LatLonBoundingBox node */
	public LatLonBoundingBox populateLatLonBoundingBox() {
		LatLonBoundingBox l1 = _element.getCapability().getLayer().getLatLonBoundingBox();
		return l1;
	}

	/** Populate BoundingBox node */
	public BoundingBox populateBoundingBox() {
		BoundingBox b1 = _element.getCapability().getLayer().getBoundingBox();
		return b1;
	}

	/** Populate SubLayer node */
	public Layer populateSubLayer(int i) {
		Layer l1 = _element.getCapability().getLayer().getLayer().get(i);
		return l1;
	}

	/** Populate SubLayer KeywordList node */
	public KeywordList populateSubLayerKeywordList(int i) {
		Layer l1 = populateSubLayer(i);
		KeywordList k1 = l1.getKeywordList();
		return k1;
	}

	/** Populate SubLayer LatLonBoundingBox node */
	public LatLonBoundingBox populateSubLayerLatLonBoundingBox(int i) {
		Layer l1 = populateSubLayer(i);
		LatLonBoundingBox b1 = l1.getLatLonBoundingBox();
		return b1;
	}

	/** Populate SubLayer BoundingBox node */
	public BoundingBox populateSubLayerBoundingBox(int i) {
		Layer l1 = populateSubLayer(i);
		BoundingBox b1 = l1.getBoundingBox();
		return b1;
	}

	/** Populate SubLayer Style node */
	public Style populateSubLayerStyle(int i) {
		Layer l1 = populateSubLayer(i);
		Style s1 = l1.getStyle();
		return s1;
	}

	/** Populate SubLayer LegendURL node */
	public LegendURL populateSubLayerLegendUrl(int i) {
		Style s1 = populateSubLayerStyle(i);
		LegendURL l1 = s1.getLegendURL();
		return l1;
	}

	/** Populate LegendURL OnlineResource node */
	public OnlineResource populateLegendUrlOnlineResource(int i) {
		LegendURL l1 = populateSubLayerLegendUrl(i);
		OnlineResource o1 = l1.getOnlineResource();
		return o1;
	}

	/** Populate Service node */
	public Service populateService() {
		Service s1 = _element.getService();
		return s1;
	}

	/** Populate Service KeywordListnode */
	public KeywordList populateServiceKeywordList() {
		KeywordList k1 = _element.getService().getKeywordList();
		return k1;
	}

	/** Populate Service OnlineResource node */
	public OnlineResource populateServiceOnlineResource() {
		OnlineResource o1 = _element.getService().getOnlineResource();
		return o1;
	}

	/** Populate Service ContactInformation node */
	public ContactInformation populateServiceContactInfo() {
		ContactInformation c1 = _element.getService().getContactInformation();
		return c1;
	}

	/** Populate Service ContactPersonPrimary node */
	public ContactPersonPrimary populateServicePersonPrimary() {
		ContactPersonPrimary c1 = _element.getService().getContactInformation().getContactPersonPrimary();
		return c1;
	}

	/** Populate Service ContactAddress node */
	public ContactAddress populateServiceContactAddress() {
		ContactAddress c1 = _element.getService().getContactInformation().getContactAddress();
		return c1;
	}

	// @}

	/** This method creates a URL link to a WMS request */
	public void startXMLReader() {
		int i = 0;
		_jlNotSelectedLayers = new ArrayList<String>();
		_jlNotSelectedTitles = new ArrayList<String>();
		_cbImageFormt = new ArrayList<String>();

		OnlineResource onlineR = populateMapGetOnlineResource();
		try {
			onlineR.getHref();
		} catch (java.lang.NullPointerException e) {
			LocalOptionPane.getInstance("Este serviço WMS está corrompido ou faltando informações(Href)", "Glue - Inconsistência!");
		}

		GetMap g1 = populateGetMap();
		try {
			g1.getFormat();
		} catch (java.lang.NullPointerException e) {
			LocalOptionPane.getInstance("Este serviço WMS está corrompido ou faltando informações(Format)", "Glue - Inconsistência!");
		}

		Layer l1 = populateLayer();
		try {
			l1.getLayer();
		} catch (java.lang.NullPointerException e) {
			LocalOptionPane.getInstance("Este serviço WMS está corrompido ou faltando informações(Layer)", "Glue - Inconsistência!");
		}

		Layer layer1 = populateSubLayer(i);
		try {
			layer1.getName();
			layer1.getTitle();
		} catch (java.lang.NullPointerException e) {
			LocalOptionPane.getInstance("Este serviço WMS está corrompido ou faltando informações(SubLayer)", "Glue - Inconsistência!");
		}

		Style style = populateSubLayerStyle(i);

		try {
			style.getName();
		} catch (java.lang.NullPointerException e1) {
			for (int h = 1; h < l1.getLayer().size(); h++) {
				style = populateSubLayerStyle(h);
				if (style.getName() != null) {
					break;
				} else {
					LocalOptionPane
							.getInstance("Este serviço WMS está corrompido ou faltando informações(Style)", "Glue - Inconsistência!");
				}
			}
		}
		bBox = populateLatLonBoundingBox();
		try {
			bBox.getMaxx();
			bBox.getMaxy();
			bBox.getMinx();
			bBox.getMiny();
			if (bBox.getMaxx() < -181 || bBox.getMaxx() > 181 || bBox.getMaxy() < -181 || bBox.getMaxy() > 181 || bBox.getMinx() < -181
					|| bBox.getMinx() > 181 || bBox.getMiny() < -181 || bBox.getMiny() > 181) {
				int j = 0;
				for (j = 0; j < l1.getLayer().size(); j++) {
					bBox = populateSubLayerLatLonBoundingBox(j);
					if (bBox.getMaxx() < -181 || bBox.getMaxx() > 181 || bBox.getMaxy() < -181 || bBox.getMaxy() > 181
							|| bBox.getMinx() < -181 || bBox.getMinx() > 181 || bBox.getMiny() < -181 || bBox.getMiny() > 181) {
						continue;
					} else
						break;
				}
				if (j == l1.getLayer().size()) {
					LocalOptionPane.getInstance("Este serviço WMS está corrompido ou faltando informações(BBox)", "Glue - Inconsistência!");
				}

			}
		} catch (java.lang.NullPointerException e) {
			int j;
			for (j = 0; j < l1.getLayer().size(); j++) {
				bBox = populateSubLayerLatLonBoundingBox(j);
				if (bBox.getMaxx() < -181 || bBox.getMaxx() > 181 || bBox.getMaxy() < -181 || bBox.getMaxy() > 181 || bBox.getMinx() < -181
						|| bBox.getMinx() > 181 || bBox.getMiny() < -181 || bBox.getMiny() > 181) {
					continue;
				} else
					break;
			}
			if (j == l1.getLayer().size()) {
				LocalOptionPane.getInstance("Este serviço WMS está corrompido ou faltando informações(BBox)", "Glue - Inconsistência!");
			}
		}

		for (int j = 0; j < l1.getLayer().size(); j++) {

			Layer subLayer = populateSubLayer(j);
			_jlNotSelectedTitles.add(subLayer.getTitle());
			_jlNotSelectedLayers.add(subLayer.getName());

		}

		for (int j = 0; j < g1.getFormat().size(); j++) {
			_cbImageFormt.add(g1.getFormat().get(j));
		}
	}

	/**
	 * This method generates one preview of the WMS map.
	 * 
	 * @param layers
	 * @param format
	 * @return
	 */
	public String generatePreviewMap(DefaultListModel layers, String format) {

		StringBuffer _selectedLayers = new StringBuffer(layers.size());

		OnlineResource onlineR = populateMapGetOnlineResource();

		Style style = populateSubLayerStyle(0);
		int j;

		try {
			style.getName();
		} catch (java.lang.NullPointerException e1) {
			for (j = 1; j < layers.size(); j++) {
				style = populateSubLayerStyle(j);
				if (style.getName() != null) {
					break;
				} else {
					LocalOptionPane
							.getInstance("Este serviço WMS está corrompido ou faltando informações(Style)", "Glue - Inconsistência!");
				}
			}
		}

		Layer l1 = populateLayer();

		for (int i = layers.size() - 1; i > -1; i--) {
			if (i > 0) {
				_selectedLayers.append(layers.getElementAt(i) + ",");
			} else {
				_selectedLayers.append(layers.getElementAt(i));
			}
		}

		String link = onlineR.getHref() + "REQUEST=GetMap&SERVICE=WMS&VERSION=" + getVersion() + "&LAYERS=" + _selectedLayers.toString()
				+ "&STYLES=" + style.getName() + "&FORMAT=" + format + "&BGCOLOR=0xFFFFFF&TRANSPARENT=FALSE&SRS=" + l1.getSRS().get(0)
				+ "&BBOX=";

		return link;
	}

	/**
	 * This method generates a request URL for WMS.
	 * 
	 * @param layers
	 * @param format
	 * @return
	 */
	public String generateURL(DefaultListModel layers, String format) {

		StringBuffer _selectedLayers = new StringBuffer(layers.size());

		OnlineResource onlineR = populateMapGetOnlineResource();

		Style style = populateSubLayerStyle(0);
		int j;

		try {
			style.getName();
		} catch (java.lang.NullPointerException e1) {
			for (j = 1; j < layers.size(); j++) {
				style = populateSubLayerStyle(j);
				if (style.getName() != null) {
					break;
				} else {

				}
			}
		}

		Layer l1 = populateLayer();

		for (int i = layers.size() - 1; i > -1; i--) {
			if (i > 0) {
				_selectedLayers.append(layers.getElementAt(i) + ",");
			} else {
				_selectedLayers.append(layers.getElementAt(i));
			}
		}

		String link = onlineR.getHref() + "REQUEST=GetMap&SERVICE=WMS&VERSION=" + getVersion() + "&LAYERS=" + _selectedLayers.toString()
				+ "&STYLES=" + style.getName() + "&FORMAT=" + format + "&BGCOLOR=0xFFFFFF&TRANSPARENT=FALSE&SRS=" + l1.getSRS().get(0)
				+ "&BBOX=";

		return link;
	}

	/**
	 * This method populates a WMS Box.
	 */
	public void populateWMSBox() {
		InterfaceWMS.set_maxX(bBox.getMaxx());
		InterfaceWMS.set_maxY(bBox.getMaxy());
		InterfaceWMS.set_minX(bBox.getMinx());
		InterfaceWMS.set_minY(bBox.getMiny());
	}
}
