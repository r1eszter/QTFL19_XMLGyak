package hu.domparse.QTFL19;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DomReadQTFL19_2 {
    
    public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException {
		
		try {			
			File xmlFile = new File("XMLQTFL19.xml");
           
            /* Dokumentum Builder létrekozása */
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = factory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            StringWriter sw = new StringWriter();
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(sw));
            String xmlString = sw.toString();
           
            /* XmlString to File */
            Writer output = null;
            File file = new File("DomReadQTFL19.xml");
            output = new OutputStreamWriter(new FileOutputStream(file));
            output.write(xmlString);
            output.close();

            System.out.println(xmlString);
    
		}catch (Exception e) {
            e.printStackTrace();
		}	


	}
}