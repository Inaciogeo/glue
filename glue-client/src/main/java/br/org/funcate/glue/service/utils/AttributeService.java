package br.org.funcate.glue.service.utils;

import java.sql.Connection;
import java.util.Vector;

public class AttributeService {
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Vector associateAliasToFetchedAttributes(String objectid,Vector fetchedAttributes, String themeName, Long themeId) {
		Vector result = new Vector();
		Vector aliasValues = null;
		Connection conn = null;
		
		try {
			//busca pelo arquivo de configuracao de canexao - META-INF/context.xml
			//conn = ServiceLocator.getConnection("glue-db");
			//AttributeDAO dao = new AttributeDAO(conn);
			AliasService aliasService = new AliasService();
			aliasValues = aliasService.getAlias(themeId);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao obter objeto de conexao", e);
		} finally {
			ServiceLocator.releaseConnection(conn);
		}
		for (int i = 0; i < fetchedAttributes.size(); i = i + 2) {
			
			String currentLabel = (String) fetchedAttributes.get(i);
			String currentValue = (String) fetchedAttributes.get(i + 1);
			String values[] = currentLabel.split("\\.");
			
			int aliasIndex = aliasValues.indexOf(values[1]);
			
			if (aliasIndex > -1) {	// 1st position (alias)
				String currentAlias = (String) aliasValues.get(aliasIndex + 1);
				result.add(currentAlias);
				result.add(currentValue);
				
			} 
//			else {	// alternative 1st position (original label as alias)
//				result.add(values[1]);
//			}
//			result.add(values[1]);	// 2nd position (original label)
//			result.add(currentValue);	// 3rd position (original value)
		
	}
		return result;
	}
}