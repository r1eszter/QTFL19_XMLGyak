package hu.domparse.QTFL19;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class DomQueryQTFL19 {

	public static void main(String[] args) {

        List<String> kb_lista = new ArrayList<>(); /* könyvesbolt lista */
        List<String> k_lista = new ArrayList<>(); /* könyv lista */
        List<String> v_lista = new ArrayList<>(); /* vasarlo lista */
        List<String> ki_lista = new ArrayList<>(); /* kiado lista */
        List<String> kar_lista = new ArrayList<>(); /* kiado lista */

        try{
            File xmlFile = new File("XMLQTFL19.xml");

            /* Dokumentum Builder létrekozása */
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            /* 1. Lekérdezi a könyvesboltok nevét és kapacitását azoknak ahol az email címében található gmail rész */
            NodeList kbNodeList = doc.getElementsByTagName("konyvesbolt");
            System.out.print("\n1. Könyvesboltok gmail-el lekérdezés");

            for (int i=0; i<kbNodeList.getLength(); i++) {
                Element kbElement = (Element) kbNodeList.item(i);
                String kb = kbElement.getTextContent();
                kb_lista.add(kb);

                String kb_email = kbElement.getElementsByTagName("email").item(0).getTextContent();

                if (kb_email.matches(".+@gmail.+")){
                    System.out.print("\n" + kbElement.getElementsByTagName("nev").item(0).getTextContent() + " : " + kbElement.getElementsByTagName("kapacitas").item(0).getTextContent() + "db");
                }
            }

            /* 2. Lekérdezi az összes könyv címét és íróját */
            NodeList kNodeList = doc.getElementsByTagName("konyv");
            System.out.print("\n\n\n2. Összes könyv lekérdezése (cím, író)");

            for (int i=0; i<kNodeList.getLength(); i++) {
                Element kElement = (Element) kNodeList.item(i);
                String k_sum = kElement.getTextContent();
                k_lista.add(k_sum);
                
                System.out.print("\nKönyv címe : " + kElement.getElementsByTagName("cim").item(0).getTextContent());
                System.out.print("\t írója : " + kElement.getElementsByTagName("iro").item(0).getTextContent());
            }

            /* 3. Lekérdezi egy konkrét vásárló adatait (Súkeník Erik) */
            NodeList vNodeList = doc.getElementsByTagName("vasarlo");
            System.out.print("\n\n\n3. Súkeník Erik adatainak lekérdezése");

            for (int i=0; i<vNodeList.getLength(); i++) {
                Element vElement = (Element) vNodeList.item(i);
                String v_sum = vElement.getTextContent();
                v_lista.add(v_sum);

                String v_name = vElement.getElementsByTagName("nev").item(0).getTextContent();
                
                if (v_name.equals("Súkeník Erik")) {
                    System.out.print("\nID : " + vElement.getAttribute("V_ID") + "\nIrányítószáma : " + vElement.getElementsByTagName("iranyitoszam").item(0).getTextContent() + "\nEmail címe : " + vElement.getElementsByTagName("email").item(0).getTextContent());
                }
            }

            /* 4. Lekérdezi az összes kiadó nevét, alapítási dátumát és helyét ahol az alapítás 2000 előtt történt */
            NodeList kiNodeList = doc.getElementsByTagName("kiado");
            System.out.print("\n\n\n4. Kiadó alapítás lekérdezés");

            for (int i=0; i<kiNodeList.getLength(); i++) {
                Element kiElement = (Element) kiNodeList.item(i);
                String kiado = kiElement.getTextContent();
                ki_lista.add(kiado);

                String ki_alap = kiElement.getElementsByTagName("alapitas").item(0).getTextContent();

                if (ki_alap.startsWith("19")) {
                    System.out.print("\nNeve : " + kiElement.getElementsByTagName("nev").item(0).getTextContent() + "\nAlapítása : " + kiElement.getElementsByTagName("alapitas").item(0).getTextContent() + "\nHelye : " + kiElement.getElementsByTagName("hely").item(0).getTextContent() + "\n");
                }
            }

            /* 5. Lekérdezi az összes kártyát amelyiknek a kulcsában (vonalkódjában) található kettes */
            NodeList karNodeList = doc.getElementsByTagName("kartya");
            System.out.print("\n\n\n5. Kártya vonalkód lekérdezés");

            for (int i=0; i<karNodeList.getLength(); i++) {
                Element karElement = (Element) karNodeList.item(i);
                String kartya = karElement.getTextContent();
                kar_lista.add(kartya);

                String kar_id = karElement.getAttribute("vonalkod");

                if (kar_id.matches(".+2.+")){
                    System.out.print("\nTipus : " + karElement.getElementsByTagName("tipus").item(0).getTextContent() + "\nAkció : " + karElement.getElementsByTagName("akcio").item(0).getTextContent() + "\n");
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}