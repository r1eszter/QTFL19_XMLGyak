package domQTFL191115;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class DomModifyQTFL19 {

    public static void main(String argv[]) {

		try {
			File inputFile = new File("kurzusfelvetelQTFL19.xml");
            
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document doc = documentBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();
            NodeList kurzusNodeList = doc.getElementsByTagName("kurzus");

            for (int i=0; i<kurzusNodeList.getLength(); i++) {
                Element kurzusNode = (Element) kurzusNodeList.item(i);
                Element oraadoNode = (Element) kurzusNode.getElementsByTagName("oraado").item(0);
                String nyelvAttr = kurzusNode.getAttribute("nyelv");

                if (oraadoNode == null) {
                    oraadoNode = doc.createElement("oraado");
                    oraadoNode.appendChild(doc.createTextNode("Agárdi Anita"));
                    kurzusNode.appendChild(oraadoNode);
                }

                if (nyelvAttr.equals("angol")) {
                    kurzusNode.setAttribute("nyelv", "német");
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

            File outputFile = new File("kurzusfelvetelModify1QTFL19.xml");
            OutputStream outputStream = new FileOutputStream(outputFile);
            StreamResult fileResult = new StreamResult(outputStream);
            transformer.transform(source, fileResult);
            outputStream.close();

        } catch (ParserConfigurationException | IOException | TransformerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}