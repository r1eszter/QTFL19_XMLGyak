package hu.domparse.QTFL19;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DomQueryQTFL19 {

	public static void main(String[] args) {
        
        try{
            File xmlFile = new File("XMLQTFL19.xml");

            /* Dokumentum Builder létrekozása */
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();

            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            /* xPath készítése */
			XPath xPath = XPathFactory.newInstance().newXPath();

            /* Lekérdezések */
            /* 1. Lekérdezi a root (konyvesvasarlas) elem konyvesbolt gyerekelemeit */
            //String expression = "konyvesvasarlas / konyvesbolt";

            /* 2. Lekérdezi a harmadik vasarlot */
            //String expression = "konyvesvasarlas / vasarlo[3]";

            /* 3. Lekérdezi azokat a könyveket amiknek a mufajai között található thriller */
            //String expression = "//konyv[mufaj='thriller']";

            /* 4. Lekérdezi a vasarlo első két elemét */
            //String expression = "//vasarlo[position()<3]";

            /* 5. Lekérdezi a 85632982 id-val rendelkező kártyát */
            //String expression = "//kartya[@vonalkod='85632982']";

            /* 6. Lekérdezi azokat a konyvesboltokat ahol a kapacitás több mint 20.000 db */
            //String expression = "//konyvesbolt[kapacitas>20.000]";

            /* 7. Lekérdezi a kartyakat amik bármilyen tipusuak lehetnek*/
            //String expression = "//kartya[tipus=*]";

            /* 8. Lekérdezi */
            //String expression = "//kartya[akcio>30]/tipus";

            /* 9. Lekérdezi az első kettő kivételével az összes kiado nevét */
            //String expression = "//kiado[position()>2]/nev";

            /* 10. Lekérdezi azon könyvek címét amelyeknek bármilyen attribútuma lehet */
            String expression = "//konyv[@*]/cim";
            

            /* Lista ami az xPath kifejezést le fordítja és ki értékeli  */
			NodeList kList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            /* Végig megyünk a nodeList csomópontjain */
            for (int i = 0; i < kList.getLength(); i++) {
				Node node = kList.item(i);

				System.out.println("\nAktuális elem: " + node.getNodeName());

				// Könyvesbolt csomópont megvizsgálása
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("konyvesbolt")) {

					Element element = (Element) node;

					System.out.println("ID: " + element.getAttribute("KB_ID"));
					System.out.println("Neve: " + element.getElementsByTagName("nev").item(0).getTextContent());

                    if (kList.item(i).getChildNodes().getLength() > 3) {
						int db = 0;
						Node node2 = element.getElementsByTagName("elerhetoseg").item(0);
						while (node2 != null) {
							node2 = element.getElementsByTagName("elerhetoseg").item(db);
							if (node2 != null) {
								Node n = element.getElementsByTagName("email").item(db);
								String isz = n.getTextContent();
								System.out.println("Email: " + isz);
								Node n2 = element.getElementsByTagName("telefon").item(db);
								String u = n2.getTextContent();
								System.out.println("Telefon szam: " + u);
							}
							db++;
						}
					}

					System.out.println("Kapacitas: " + element.getElementsByTagName("kapacitas").item(0).getTextContent());
				}


                /* Könyv */
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("konyv")) {

					Element element = (Element) node;

					System.out.println("ID: " + element.getAttribute("K_ID"));
					System.out.println("Címe: " + element.getElementsByTagName("cim").item(0).getTextContent());

					if (kList.item(i).getChildNodes().getLength() > 5) {
						int db = 0;
						Node node2 = element.getElementsByTagName("mufaj").item(0);
						while (node2 != null) {
							node2 = element.getElementsByTagName("mufaj").item(db);
							if (node2 != null) {
								String rh = node2.getTextContent();
								System.out.println("Mufaj: " + rh);
							}
							db++;
						}
					}
                    					
                    System.out.println("Író: " + element.getElementsByTagName("iro").item(0).getTextContent());
                }

                /* Könyv címe */
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("cim")) {
                    Element element = (Element) node;
                    System.out.println("Könyv címe: " + element.getTextContent());
                }


                /* Kiadó */
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("kiado")) {

					Element element = (Element) node;

					System.out.println("Adószám: " + element.getAttribute("Adoszam"));
					System.out.println("Neve: " + element.getElementsByTagName("nev").item(0).getTextContent());    
					System.out.println("Alapítása: " + element.getElementsByTagName("alapitas").item(0).getTextContent());    
					System.out.println("Helye: " + element.getElementsByTagName("hely").item(0).getTextContent());  
                }  

                /* Kiadó neve */
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("nev")) {
                    Element element = (Element) node;
                    System.out.println("Kiadó neve: " + element.getTextContent());
                }

                /* Vásárló */
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("vasarlo")) {

					Element element = (Element) node;

					System.out.println("Adószám: " + element.getAttribute("V_ID"));
					System.out.println("Neve: " + element.getElementsByTagName("nev").item(0).getTextContent());    
					System.out.println("Irányítószáma: " + element.getElementsByTagName("iranyitoszam").item(0).getTextContent());    
					System.out.println("E-mail címe: " + element.getElementsByTagName("email").item(0).getTextContent());  
                }  


                /* Kártya */
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("kartya")) {

					Element element = (Element) node;

					System.out.println("Vonalkód: " + element.getAttribute("vonalkod"));
					System.out.println("Tipusa: " + element.getElementsByTagName("tipus").item(0).getTextContent());    
					System.out.println("Akció értéke: " + element.getElementsByTagName("akcio").item(0).getTextContent());    
					System.out.println("Igénylés dátuma: " + element.getElementsByTagName("igenyles").item(0).getTextContent());  
                }  

                /* Kártya tipus */
                if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("tipus")) {
                    Element element = (Element) node;
                    System.out.println("Kártya tipusa: " + element.getTextContent());
                }

            }

        } catch (ParserConfigurationException e) {
        e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

    }
}