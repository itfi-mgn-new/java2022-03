package lesson5;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import lesson4.XmlTest;

public class SAXExample {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();

		MyHandler handler = new MyHandler();
		saxParser.parse(SAXExample.class.getResourceAsStream("test.xml"), handler);
		System.err.println("Completed...");
	}

	static class MyHandler extends DefaultHandler {
		 @Override
	    public void characters(char[] ch, int start, int length) throws SAXException {
			 System.err.println("TEXt: "+new String(ch,start,length));
		}
		 
		 @Override
	    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
			 System.err.println("Start: "+qName);
		 } 

		 @Override
	    public void endElement(String uri, String lName, String qName) throws SAXException {
			 System.err.println("end: "+qName);
		 } 
}
	
}
