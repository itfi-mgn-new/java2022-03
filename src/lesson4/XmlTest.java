package lesson4;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlTest {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, TransformerException {
		// TODO Auto-generated method stub
		try(final InputStream is = XmlTest.class.getResourceAsStream("test.xml")) {
			DocumentBuilderFactory 	factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder 		builder = factory.newDocumentBuilder();
			Document 				document = builder.parse(is);
			
			System.err.println("Completed");
			Element	el = document.getDocumentElement();
			
			NodeList list = el.getElementsByTagName("action");
			print(list);

			System.err.println("---------------");
			XPath xPath = XPathFactory.newInstance().newXPath();
		    String expression = "/*/*/action[@cmd='triggeron']";
		    print((NodeList)xPath.compile(expression).evaluate(el, XPathConstants.NODESET));
		
		    	 TransformerFactory transformerFactory = TransformerFactory.newInstance();
		         Transformer transformer = transformerFactory.newTransformer();
		         DOMSource source = new DOMSource(document);
		         StreamResult result = new StreamResult(System.out);

		         transformer.transform(source, result);  	
		}
	}

	static void print(final NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Node item = list.item(i);
            
            NamedNodeMap attributes = item.getAttributes();
            System.err.println("Name="+item.getNodeName());
            System.err.println("Content="+item.getTextContent());
            
            for (int index = 0; index < attributes.getLength(); index++) {
	            System.err.println("Attr: "+attributes.item(index).getNodeName()+" = "+attributes.item(index).getNodeValue());	
            }
        }
	}
}
