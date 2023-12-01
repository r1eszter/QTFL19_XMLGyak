package hu.domparse.QTFL19;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DomModifyQTFL19 {

    public static void main(String argv[]) {

        try {
            File xmlFile = new File("XMLQTFL19.xml");

            /* Dokumentum Builder létrekozása */
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			
			/* 1. Könyvesbolt kapacitásának cseréja HA az attribútuma kb1 */
            NodeList kbNodeList = doc.getElementsByTagName("konyvesbolt");
			for (int i=0; i<kbNodeList.getLength(); i++) {
                Element kbNode = (Element) kbNodeList.item(i);
				String kbAttr = kbNode.getAttribute("KB_ID");
				
				if (kbAttr.equals("kb1")) {
					kbNode.getElementsByTagName("kapacitas").item(0).setTextContent("20.400");
					System.out.print("1. Módosított kapacitás : " + kbNode.getElementsByTagName("kapacitas").item(0).getTextContent());
				}
            }

			/* 2. Könyv  */
			NodeList kiNodeList = doc.getElementsByTagName("kiado");
			for(int i=0; i<kiNodeList.getLength(); i++) {
				Element kiNode = (Element) kiNodeList.item(i);
				String ki_hely = kiNode.getElementsByTagName("hely").item(0).getTextContent();

				if (ki_hely.length() < 10) {
					kiNode.getElementsByTagName("hely").item(0).setTextContent(ki_hely + " (új hely szükséges)");
					System.out.print("\n\n2. Új hely:" + kiNode.getElementsByTagName("hely").item(0).getTextContent());
				}	
			}

			/* 3. Megváltoztatja az első könyv címét */
			NodeList kNodeList = doc.getElementsByTagName("konyv");
			Element kNode = (Element) kNodeList.item(0);
			kNode.getElementsByTagName("cim").item(0).setTextContent("League of Legends");
			System.out.print("\n\n3. Új cím:" + kNode.getElementsByTagName("cim").item(0).getTextContent());

			/* 4. A vásárlóknak akinek van á a nevükben meg változtatja a nevüket + szöveggel*/
			NodeList vNodeList = doc.getElementsByTagName("vasarlo");
			for(int i=0; i<vNodeList.getLength(); i++) {
				Element vNode = (Element) vNodeList.item(i);
				String v_nev = vNode.getElementsByTagName("nev").item(0).getTextContent();

				if (v_nev.matches(".+á.+")) {
					vNode.getElementsByTagName("nev").item(0).setTextContent(v_nev + " van egy á a nevemben");
					System.out.print("\n\n4. Van á?:" + vNode.getElementsByTagName("nev").item(0).getTextContent());
				}	
			}

			/* 5. Az első három kártya kivételével mindnek meg cseréli a tipusát Ajándékra  */
			NodeList kaNodeList = doc.getElementsByTagName("kartya");
			for(int i=0; i<kaNodeList.getLength(); i++) {
				Element kaNode = (Element) kaNodeList.item(i);
				
				if (i > 2) {
					kaNode.getElementsByTagName("tipus").item(0).setTextContent("Ajandek");
					System.out.print("\n\n5. Új tipus: " + kaNode.getElementsByTagName("tipus").item(0).getTextContent());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

        

    }
}
