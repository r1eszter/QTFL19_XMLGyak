package domQTFL191115;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DomQueryQTFL19 {

    public static void main(String[] args) {

        List<String> kurzus_lista = new ArrayList<>();
        List<String> hallgato_lista = new ArrayList<>();
        List<String> oktato_lista = new ArrayList<>();

        try {
            File inputFile = new File("kurzusfelvetelQTFL19.xml");

            
            /* kurzus */
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList kurzusNodeList = doc.getElementsByTagName("kurzusnev");
            System.out.print("Kurzusnév : [ ");

            for (int i=0; i<kurzusNodeList.getLength(); i++) {
                Element kurzusElement = (Element) kurzusNodeList.item(i);
                String kurzus = kurzusElement.getTextContent();
                kurzus_lista.add(kurzus);
                if(i == kurzusNodeList.getLength()-1) {
                    System.out.print(kurzus + " ]");
                }
                else {
                    System.out.print(kurzus + ", ");
                }
            }


            /* hallgato */
            NodeList hallgatoNodeList = doc.getElementsByTagName("hallgato");

            Document newDoc = dBuilder.newDocument();
            Element rootElement = newDoc.createElement("hallgatok");            
            newDoc.appendChild(rootElement);

            for (int i=0; i<hallgatoNodeList.getLength(); i++) {
                Element hallgatoElement = (Element) hallgatoNodeList.item(i);
                String hallgato = hallgatoElement.getTextContent();
                hallgato_lista.add(hallgato);
                Element hallgatoNode = newDoc.createElement("hallgato");
                hallgatoNode.setAttribute("id", hallgatoElement.getAttribute("id"));
                rootElement.appendChild(hallgatoNode);

                System.out.println("\n\n<hallgato id=\"" + hallgatoElement.getAttribute("id") + "\">");


                Element hnevNode = newDoc.createElement("hnev");
                hnevNode.appendChild(newDoc.createTextNode(hallgatoElement.getElementsByTagName("hnev").item(0).getTextContent()));
                hallgatoNode.appendChild(hnevNode);

                System.out.println("\t <hnev>" + hallgatoElement.getElementsByTagName("hnev").item(0).getTextContent() + "<hnev>");


                Element szulevNode = newDoc.createElement("szulev");
                szulevNode.appendChild(newDoc.createTextNode(hallgatoElement.getElementsByTagName("szulev").item(0).getTextContent()));
                hallgatoNode.appendChild(szulevNode);

                System.out.println("\t <szulev>" + hallgatoElement.getElementsByTagName("szulev").item(0).getTextContent() + "<szulev>");


                Element szakNode = newDoc.createElement("szak");
                szakNode.setAttribute("evf", hallgatoElement.getElementsByTagName("szak").item(0).getAttributes().getNamedItem("evf").getNodeValue());
                szakNode.appendChild(newDoc.createTextNode(hallgatoElement.getElementsByTagName("szak").item(0).getTextContent()));
                hallgatoNode.appendChild(szakNode);

                System.out.println("\t <szak evf =\"" + hallgatoElement.getElementsByTagName("szak").item(0).getAttributes().getNamedItem("evf").getNodeValue() + "\">" + hallgatoElement.getElementsByTagName("szak").item(0) + "<szak>");
            }

            System.out.println("</hallgato> \n\n");


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(newDoc);
            StreamResult result = new StreamResult(new File("hallgato_fajl.xml"));
            transformer.transform(source, result);
        

            /* oktatok */
            NodeList oktatoNodeList = doc.getElementsByTagName("oktato");
            System.out.println("Oktatók :");
            for (int i = 0; i < oktatoNodeList.getLength(); i++) {
                Element oktatoElement = (Element) oktatoNodeList.item(i);
                String oktato = oktatoElement.getTextContent();
                oktato_lista.add(oktato);
                System.out.println(oktato);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
