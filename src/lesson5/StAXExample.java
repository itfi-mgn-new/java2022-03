package lesson5;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class StAXExample {

	public static void main(String[] args) throws XMLStreamException {
		//Get XMLOutputFactory instance.
		XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
		XMLEventWriter xmlEventWriter = xmlOutputFactory.createXMLEventWriter(System.err);
		XMLEventFactory xmlEventFactory = XMLEventFactory.newInstance();
 
		//Create and add xmlEvents.
		XMLEvent xmlEvent=xmlEventFactory.createStartDocument();
		xmlEventWriter.add(xmlEvent);
 
		xmlEvent = xmlEventFactory.createStartElement("class", "", "document");
		xmlEventWriter.add(xmlEvent);
 
		xmlEvent = xmlEventFactory.createStartElement("student", "", "document");
		xmlEventWriter.add(xmlEvent);
 
		xmlEvent = xmlEventFactory.createAttribute("name", "Prabhjot");
		xmlEventWriter.add(xmlEvent);
 
		xmlEvent = xmlEventFactory.createEndElement("student", "", "document");
		xmlEventWriter.add(xmlEvent);
 
		xmlEvent = xmlEventFactory.createEndElement("class", "", "document");
		xmlEventWriter.add(xmlEvent);
 
		//Write content on XML file and close xmlEventWriter.
		xmlEventWriter.flush();
		xmlEventWriter.close();
	}

}
