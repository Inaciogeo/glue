package br.org.funcate.glue.utilities;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLJDomReader {

	public XMLJDomReader() {
		super();
	}
	
	public static Element JDomRead(String fileLocation) throws JDOMException, IOException{
		
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(fileLocation);
		Document document = (Document) builder.build(xmlFile);
		Element rootNode = document.getRootElement();

		return rootNode;
	}
	
	public static Object JaxBRead(String fileLocation) throws JAXBException{
//		File file = new File(fileLocation);
//		JAXBContext jaxbContext = JAXBContext.newInstance(Content.class);
//		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//		Content tr = (Content) jaxbUnmarshaller.unmarshal(file);
		
		return null;
	}
	
}
