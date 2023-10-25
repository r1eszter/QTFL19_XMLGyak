package hu.saxQTFL19;

import java.io.File;

import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;


public class XsdQTFL19 {

	public static void main(String[] args) {

		if(validateXMLSchema())
			System.out.println("Validation successful!");
		else
			System.out.println("Unsuccessful validation!");
		
	}

	public static boolean validateXMLSchema() {

		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File("QTFL19_kurzusfelvetel.xsd"));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File("QTFL19_kurzusfelvetel.xml")));
		} catch (IOException | SAXException e) {
			System.out.println("Exception: " + e.getMessage());
			return false;
		}
		return true;
	}

}