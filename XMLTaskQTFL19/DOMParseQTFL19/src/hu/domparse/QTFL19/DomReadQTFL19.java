package hu.domparse.QTFL19;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

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
        
		try {
			/* Új fájl */
			File OutPutRead = new File("DomReadQTFL19.xml");
			OutputStream os = new FileOutputStream(OutPutRead);
			PrintStream ps = new PrintStream(os);

			/* A konzol kimenetének átirányítása az új fájlba */
			System.setOut(ps);
			
			File xmlFile = new File("XMLQTFL19.xml");
			
			/* Dokumentum Builder létrekozása */
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			System.out.println("\n<" + doc.getDocumentElement().getNodeName() + ">");
			

			/* A könyvesbolt beolvasása */
			NodeList kbList = doc.getElementsByTagName("konyvesbolt");
			for (int i = 0; i < kbList.getLength(); i++) {

				Node nNode = kbList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element elem = (Element) nNode;
					String kbid = elem.getAttribute("KB_ID");

					Node node1 = elem.getElementsByTagName("nev").item(0);
					String kbnev = node1.getTextContent();

					/* a node2 lesz az elérhetőség */

					Node node3 = elem.getElementsByTagName("kapacitas").item(0);
					String kap = node3.getTextContent();

					/* struktúrált ki iratás */
					System.out.println("\n\t<" + nNode.getNodeName() + " KB_ID=" + kbid + ">");
					System.out.println("\t  <nev>" + kbnev + "</nev>");
					
					if (kbList.item(i).getChildNodes().getLength() > 3) {
						int db = 0;
						Node node2 = elem.getElementsByTagName("elerhetoseg").item(0);
						while (node2 != null) {
							node2 = elem.getElementsByTagName("elerhetoseg").item(db);
							if (node2 != null) {
								Node n = elem.getElementsByTagName("email").item(db);
								String em = n.getTextContent();
								System.out.println("\t    <email>" + em + "</email>");

								Node n2 = elem.getElementsByTagName("telefon").item(db);
								String tel = n2.getTextContent();
								System.out.println("\t    <telefon>" + tel + "</telefon>");
							}
							db++;
						}
					}
					System.out.println("\t  <kapacitas>" + kap + "</kapacitas>");
				}
				System.out.println("\t</" + nNode.getNodeName() + ">");
			}



			/* A könyv elem beolvasása */
			NodeList kList = doc.getElementsByTagName("konyv");
			for (int i = 0; i < kList.getLength(); i++) {

				Node nNode = kList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element elem = (Element) nNode;
					String kid = elem.getAttribute("K_ID");

					Node node1 = elem.getElementsByTagName("cim").item(0);
					String cim = node1.getTextContent();

					/* A node2 itt a mufaj */

					Node node3 = elem.getElementsByTagName("iro").item(0);
					String iro = node3.getTextContent();

					/* struktúrált ki iratás */
					System.out.println("\n\t<" + nNode.getNodeName() + " K_ID = " + kid + ">");
					System.out.println("\t  <cim>" + cim + "</cim>");

					if (kList.item(i).getChildNodes().getLength() > 5) {
						int db = 0;
						Node node2 = elem.getElementsByTagName("mufaj").item(0);
						while (node2 != null) {
							node2 = elem.getElementsByTagName("mufaj").item(db);
							if (node2 != null) {
								String rh = node2.getTextContent();
								System.out.println("\t  <mufaj>" + rh + "</mufaj>");
							}
							db++;
						}
					}
					
					System.out.println("\t  <iro>" + iro + "</iro>");
				}
				System.out.println("\t</" + nNode.getNodeName() + ">");
			}



			/* Kiadó elem beolvasása */
			NodeList kiList = doc.getElementsByTagName("kiado");
			for (int i = 0; i < kiList.getLength(); i++) {

				Node nNode = kiList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element elem = (Element) nNode;
					String ado = elem.getAttribute("Adoszam");

					Node node1 = elem.getElementsByTagName("nev").item(0);
					String kinev = node1.getTextContent();

					Node node2 = elem.getElementsByTagName("alapitas").item(0);
					String alap = node2.getTextContent();

					Node node3 = elem.getElementsByTagName("hely").item(0);
					String hely = node3.getTextContent();

					/* struktúrált ki iratás */
					System.out.println("\n\t<" + nNode.getNodeName() + " Adoszam = " + ado + ">");
					System.out.println("\t  <nev>" + kinev + "</nev>");
					System.out.println("\t  <alapitas>" + alap + "</alapitas>");
					System.out.println("\t  <hely>" + hely + "</hely>");
				}
				System.out.println("\t</" + nNode.getNodeName() + ">");
			}


			/* Vásárló egyed beolvasása */
			NodeList vList = doc.getElementsByTagName("vasarlo");
			for (int i = 0; i < vList.getLength(); i++) {

				Node nNode = vList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element elem = (Element) nNode;
					String vid = elem.getAttribute("V_ID");

					Node node1 = elem.getElementsByTagName("nev").item(0);
					String vnev = node1.getTextContent();

					Node node2 = elem.getElementsByTagName("iranyitoszam").item(0);
					String irany = node2.getTextContent();

					Node node3 = elem.getElementsByTagName("email").item(0);
					String vem = node3.getTextContent();

					/* struktúrált ki iratás */
					System.out.println("\n\t<" + nNode.getNodeName() + " V_ID = " + vid + ">");
					System.out.println("\t  <nev>" + vnev + "</nev>");
					System.out.println("\t  <iranyitoszam>" + irany + "</iranyitoszam>");
					System.out.println("\t  <email>" + vem + "</email>");
				}
				System.out.println("\t</" + nNode.getNodeName() + ">");
			}


			/* Kártya egyed beolvasása */
			NodeList kaList = doc.getElementsByTagName("kartya");
			for (int i = 0; i < kaList.getLength(); i++) {

				Node nNode = kaList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element elem = (Element) nNode;
					String von = elem.getAttribute("vonalkod");

					Node node1 = elem.getElementsByTagName("tipus").item(0);
					String tip = node1.getTextContent();

					Node node2 = elem.getElementsByTagName("akcio").item(0);
					String akcio = node2.getTextContent();

					Node node3 = elem.getElementsByTagName("igenyles").item(0);
					String ig = node3.getTextContent();

					/* struktúrált ki iratás */
					System.out.println("\n\t<" + nNode.getNodeName() + " vonalkod = " + von + ">");
					System.out.println("\t  <tipus>" + tip + "</tipus>");
					System.out.println("\t  <akcio>" + akcio + "</akcio>");
					System.out.println("\t  <igenyles>" + ig + "</igenyles>");
				}
				System.out.println("\t</" + nNode.getNodeName() + ">");
			}


			/* vétel kapcsolat */
			NodeList vetList = doc.getElementsByTagName("vetel");
			for (int i = 0; i < vetList.getLength(); i++) {

				Node nNode = vetList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element elem = (Element) nNode;
					String kbid = elem.getAttribute("KB_ID");
					String vid = elem.getAttribute("V_ID");

					Node node1 = elem.getElementsByTagName("vasarlas").item(0);
					String vas = node1.getTextContent();

					/* struktúrált ki iratás */
					System.out.println("\n\t<" + nNode.getNodeName() + " KB_ID = " + kbid + " " + " V_ID = " + vid + ">");
					System.out.println("\t  <vasarlas> " + vas + "</vasarlas>");

				}
				System.out.println("\t</" + nNode.getNodeName() + ">");
			}
			System.out.println("\n</" + doc.getDocumentElement().getNodeName() + ">");

			

			/* Új fájl bezárása */
			ps.close();

    
		}catch (Exception e) {
            e.printStackTrace();
        }
	}
}