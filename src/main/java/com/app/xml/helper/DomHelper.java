package com.app.xml.helper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DomHelper {
	
	public static Document getDocument(InputStream is){
		
		Document document = null;
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			document = builder.parse(is);
		} 
		catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		return document ;
	}
	
	public static void saveXML(Document document, Path path){
		
		TransformerFactory factory = TransformerFactory.newInstance();
		factory.setAttribute("indent-number", 2);
		try {
			Transformer transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(path.toFile());
			transformer.transform(domSource, streamResult);
		} 
		catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
