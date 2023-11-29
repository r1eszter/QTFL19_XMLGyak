package hu.domparse.QTFL19;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomReadQTFL19 {
    
    public static void main(String argv[]) throws SAXException, IOException, ParserConfigurationException {
        
        File xmlFile = new File("XMLQTFL19.xml");
        
        /* Dokumentum Builder létrekozása */
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(xmlFile);

		doc.getDocumentElement().normalize();

		System.out.println("\nRoot element: " + doc.getDocumentElement().getNodeName());
		 /* számolni a gyerek elemek kiiratását */

        /* A könyvesbolt beolvasása */
        NodeList kbList = doc.getElementsByTagName("konyvesbolt");
        int x = 1;

        for (int i = 0; i < kbList.getLength(); i++) {

			Node nNode = kbList.item(i);
            
			System.out.println("\n --- " + nNode.getNodeName() + " " + x + " ---");
            x++;

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) nNode;
				String kbid = elem.getAttribute("KB_ID");

				Node node1 = elem.getElementsByTagName("nev").item(0);
				String kbnev = node1.getTextContent();

                /* a node2 lesz az elérhetőség */

				Node node3 = elem.getElementsByTagName("kapacitas").item(0);
				String kap = node3.getTextContent();

				System.out.println("\tID: " + kbid);
				System.out.println("\tNeve: " + kbnev);
                
                if (kbList.item(i).getChildNodes().getLength() > 3) {
					int db = 0;
					Node node2 = elem.getElementsByTagName("elerhetoseg").item(0);
					while (node2 != null) {
						node2 = elem.getElementsByTagName("elerhetoseg").item(db);
						if (node2 != null) {
							Node n = elem.getElementsByTagName("email").item(db);
							String em = n.getTextContent();
							System.out.println("\tE-mail címe: " + em);

							Node n2 = elem.getElementsByTagName("telefon").item(db);
							String tel = n2.getTextContent();
							System.out.println("\tTelefon száma: " + tel);
						}
						db++;
					}
				}

                System.out.println("\tKapacítása: " + kap);
			}
		}



        /* A könyv elem beolvasása */
        NodeList kList = doc.getElementsByTagName("konyv");
        x = 1;

        for (int i = 0; i < kList.getLength(); i++) {

			Node nNode = kList.item(i);
			System.out.println("\n --- " + nNode.getNodeName() + " " + x + " ---");
            x++;

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) nNode;
				String kid = elem.getAttribute("K_ID");

				Node node1 = elem.getElementsByTagName("cim").item(0);
				String cim = node1.getTextContent();

                /* A node2 itt a mufaj */

                Node node3 = elem.getElementsByTagName("iro").item(0);
				String iro = node3.getTextContent();

                System.out.println("\tID: " + kid);
                System.out.println("\tCíme: " + cim);

                if (kList.item(i).getChildNodes().getLength() > 5) {
					int db = 0;
					Node node2 = elem.getElementsByTagName("mufaj").item(0);
					while (node2 != null) {
						node2 = elem.getElementsByTagName("mufaj").item(db);
						if (node2 != null) {
							String rh = node2.getTextContent();
							System.out.println("\tMufaja: " + rh);
						}
						db++;
					}
				}
                
                System.out.println("\tÍrója: " + iro);
            }
        }



        /* Kiadó elem beolvasása */
        NodeList kiList = doc.getElementsByTagName("kiado");
        x = 1;

        for (int i = 0; i < kiList.getLength(); i++) {

			Node nNode = kiList.item(i);
			System.out.println("\n --- " + nNode.getNodeName() + " " + x +  " ---");
            x++;

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) nNode;
				String ado = elem.getAttribute("Adoszam");

				Node node1 = elem.getElementsByTagName("nev").item(0);
				String kinev = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("alapitas").item(0);
				String alap = node2.getTextContent();

				Node node3 = elem.getElementsByTagName("hely").item(0);
				String hely = node3.getTextContent();

				System.out.println("\tID: " + ado);
                System.out.println("\tNeve: " + kinev);
				System.out.println("\tAlapítása: " + alap);
                System.out.println("\tHelye: " + hely);
            }
        }


        /* Vásárló egyed beolvasása */
        NodeList vList = doc.getElementsByTagName("vasarlo");
        x = 1;

        for (int i = 0; i < vList.getLength(); i++) {

			Node nNode = vList.item(i);
			System.out.println("\n --- " + nNode.getNodeName() + " " + x + " ---");
            x++;

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) nNode;
				String vid = elem.getAttribute("V_ID");

				Node node1 = elem.getElementsByTagName("nev").item(0);
				String vnev = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("iranyitoszam").item(0);
				String irany = node2.getTextContent();

				Node node3 = elem.getElementsByTagName("email").item(0);
				String vem = node3.getTextContent();

				System.out.println("\tID: " + vid);
                System.out.println("\tNeve: " + vnev);
				System.out.println("\tIrányítószáma: " + irany);
                System.out.println("\tEmail címe: " + vem);
            }
        }


        /* Kártya egyed beolvasása */
        NodeList kaList = doc.getElementsByTagName("kartya");
        x = 1;

        for (int i = 0; i < kaList.getLength(); i++) {

			Node nNode = kaList.item(i);
			System.out.println("\n --- " + nNode.getNodeName() + " "+ x + " ---");
            x++;

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element elem = (Element) nNode;
				String von = elem.getAttribute("vonalkod");

				Node node1 = elem.getElementsByTagName("tipus").item(0);
				String tip = node1.getTextContent();

                Node node2 = elem.getElementsByTagName("akcio").item(0);
				String akcio = node2.getTextContent();

				Node node3 = elem.getElementsByTagName("igenyles").item(0);
				String ig = node3.getTextContent();

				System.out.println("\tID: " + von);
                System.out.println("\tTipusa: " + tip);
				System.out.println("\tAkció % értéke: " + akcio);
                System.out.println("\tIgénylés ideje: " + ig);
            }
        }

   
    }
}
