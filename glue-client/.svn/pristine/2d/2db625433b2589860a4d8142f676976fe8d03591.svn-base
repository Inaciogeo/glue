package br.org.funcate.glue.main;


import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.service.TerraJavaClient;
import br.org.funcate.glue.service.utils.SQLService;
import br.org.funcate.glue.utilities.XMLJDomReader;


public class AppTeste {
	
	/**
	 * @param args
	 * @throws GlueServerException 
	 */
	public static void main(String[] args) throws GlueServerException {
		TerraJavaClient client = new TerraJavaClient();
		client.getTerraJava().setCurrentView("São Paulo", "postgres", "1");
		client.setTheme("Arruamentos", 0);
			
		//List<String> list = client.getAttributesList("São Paulo", "Municipios");
		//System.out.println(list); 
		
		String objectId = "12";
		
//		String out = "object_id = '"+ objectId + "'";
//		
//		System.out.println(out);
		
		//System.out.println(CalculatorService.WGS84LatLongToVirtualEarthMercato(new Coord(-49.0565555,-22.32644444)));
		
		//System.out.println(client.getTerraJava().fetchAttributes("123", 0, "1"));
		
//		Theme theme = new Theme();
//		theme.setType(0);
//		theme.setName("Municipios");
//		
//		System.out.println(client.locateObject(client.getCurrentThemeBox("Municipios",""), 800, 700, -23.55014,-46.636384, theme));

		SQLService.connect();
		ArrayList list2 = new ArrayList<String>();
		list2.add("1");
		
		//System.out.println(client.getFeaturesByIds(list2));
//		try {
//			ResultSet rs = SQLService.buildSelect("select distinct object_id from log_lin where nome LIKE '%JUL%'");
//			while(rs.next()){
//				System.out.println(client.getCurrentThemeBox("Municipios","geom_id = '"+ rs.getString(1)+ "'"));
//				//System.out.println(rs.getString(1));
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		try {
			XMLJDomReader.JaxBRead("../glue-client/src/main/java/br/org/funcate/glue/utilities/TilePropertyRequest.xml");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
