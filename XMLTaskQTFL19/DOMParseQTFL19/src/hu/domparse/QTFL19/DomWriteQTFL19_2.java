package hu.domparse.QTFL19;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DomWriteQTFL19 {
    
    public static void main(String argv[]) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();

		Document doc = dBuilder.newDocument();

        Element root = doc.createElementNS("XMLQTFL19", "konyvesvasarlas");
        doc.appendChild(root);

        /* könyvesboltok */
        String[] email = {"lapos@gmail.com"};
        String[] tel = {"06305984652"};
        root.appendChild(createKbolt(doc, "kb1", "LAPos", email, tel, "19.020"));
        String[] email2 = {"alap@freemail.hu"};
        String[] tel2 = {"06201523495"};
        root.appendChild(createKbolt(doc, "kb2", "aLAP", email2, tel2, "45.000"));
        String[] email3 = {"lapoz@gmail.hu"};
        String[] tel3 = {"06708623562"};
        root.appendChild(createKbolt(doc, "kb3", "LAPoz", email3, tel3, "20.900"));
        
        Element elem = (Element) doc.getElementsByTagName("konyvesbolt").item(0);
        Comment com = doc.createComment("Konyvesboltok");
        elem.getParentNode().insertBefore(com, elem);


        /* könyvek */
        String[] mufaj = {"dráma", "romantikus", "komédia"};
        root.appendChild(createKonyv(doc, "k1", "Oppa és Yeobo", mufaj, "Tóth Péter Tamás"));
        String[] mufaj2 = {"dráma", "romantikus", "komédia"};
        root.appendChild(createKonyv(doc, "k2", "Bankrablás", mufaj2, "Szabó János"));
        String[] mufaj3 = {"dráma", "romantikus", "komédia"};
        root.appendChild(createKonyv(doc, "k3", "Shuriken", mufaj3, "Bogyó Eszter"));
        String[] mufaj4 = {"dráma", "romantikus", "komédia"};
        root.appendChild(createKonyv(doc, "k4", "Egyetem", mufaj4, "Garamszegi Márton"));
        
        elem = (Element) doc.getElementsByTagName("konyv").item(0);
        com = doc.createComment("Konyvek");
        elem.getParentNode().insertBefore(com, elem);


        /* kiadók */
        root.appendChild(createKiado(doc, "1234567895", "Pub", "1980.10.21", "California"));
        root.appendChild(createKiado(doc, "1578623598", "Money", "2005.09.13", "Bikinifenék"));
        root.appendChild(createKiado(doc, "1452368952", "OneTrickPony", "2010.02.30", "New York"));
        root.appendChild(createKiado(doc, "1982332959", "KiAD", "1996.05.19", "Budapest"));

        elem = (Element) doc.getElementsByTagName("kiado").item(0);
        com = doc.createComment("Kiadok");
        elem.getParentNode().insertBefore(com, elem);


        /* vásárlók */
        root.appendChild(createVas(doc, "v1", "Súkeník Erik", "8596", "EriKing@gmail.com"));
        root.appendChild(createVas(doc, "v2", "Szabó Dávid", "1045", "distrect@citromail.hu"));
        root.appendChild(createVas(doc, "v3", "Magyar János", "1139", "makkos98@outlook.hu"));
        root.appendChild(createVas(doc, "v4", "Bogyó Márta", "9562", "bogyo@gmail.com"));
        root.appendChild(createVas(doc, "v5", "Morty Smith", "6243", "smith@gmail.com"));
    
        elem = (Element) doc.getElementsByTagName("vasarlo").item(0);
        com = doc.createComment("Vasarlok");
        elem.getParentNode().insertBefore(com, elem);


        /* kártyák */
        root.appendChild(createKar(doc, "45236874", "Törzsvársálói", "15", "2015.04.28"));
        root.appendChild(createKar(doc, "85632982", "Törzsvársálói", "90", "1980.09.10"));
        root.appendChild(createKar(doc, "65369848", "Ajándék", "30", "2020.12.20"));
        root.appendChild(createKar(doc, "53248725", "Törzsvársálói", "30", "1998.06.20"));
        root.appendChild(createKar(doc, "35986147", "Ajándék", "30", "2023.08.02"));

        elem = (Element) doc.getElementsByTagName("kartya").item(0);
        com = doc.createComment("Kartyak");
        elem.getParentNode().insertBefore(com, elem);


        /* vétel kapcsolatok */
        root.appendChild(createVet(doc, "kb1", "v3", "2018.11.04"));
        root.appendChild(createVet(doc, "kb2", "v1", "2022.03.15"));
        root.appendChild(createVet(doc, "kb3", "v2", "2023.08.06"));
        root.appendChild(createVet(doc, "kb2", "v4", "2023.04.23"));
        root.appendChild(createVet(doc, "kb1", "v5", "2020.10.08"));
        root.appendChild(createVet(doc, "kb3", "v4", "2021.07.10"));

        elem = (Element) doc.getElementsByTagName("vetel").item(0);
        com = doc.createComment("Vetel kapcsolatok");
        elem.getParentNode().insertBefore(com, elem);



        /* Transform */
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();

		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");

		// File létrehozása
		DOMSource source = new DOMSource(doc);
		File OutPutFile = new File("XMLQTFL191.xml");

		// Konzolra való kiíratás
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(OutPutFile);

		transf.transform(source, console);
		transf.transform(source, file);
    }
   

    private static Node createElement(Document doc, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));

		return node;
	}

	private static Node[] appendArray(Document doc, String name, String[] value) {
		Element nodes[] = new Element[value.length];

		for (int i = 0; i < value.length; i++) {

			nodes[i] = doc.createElement(name);
			nodes[i].appendChild(doc.createTextNode(value[i]));

		}
		return nodes;
	}

    private static Node createKbolt(Document doc, String kbid, String nev, String[] email, String[] telefon, String kapacitas) {
        Element kb = doc.createElement("konyvesbolt");

        kb.setAttribute("KB_ID", kbid);
        kb.appendChild(createElement(doc, "nev", nev));
        Node[] node_email = appendArray(doc, "email", email);
        Node[] node_tel = appendArray(doc, "telefon", telefon);
        kb.appendChild(createElement(doc, "kapacitas", kapacitas));

        for (int i=0; i<email.length; i++) {
            Element eler = doc.createElement("elerhetoseg");
            eler.appendChild(node_email[i]);
            eler.appendChild(node_tel[i]);
            kb.appendChild(eler);
        }
        return kb;
    }


    private static Node createKonyv(Document doc, String kid, String cim, String[] mufaj, String iro) {
        Element k = doc.createElement("konyv");

        k.setAttribute("K_ID", kid);
        k.appendChild(createElement(doc, "cim", cim));
        Node[] node_muf = appendArray(doc, "mufaj", mufaj);
        k.appendChild(createElement(doc, "iro", iro));

        for(int i=0; i<mufaj.length; i++) {
            k.appendChild(node_muf[i]);
        }
        return k;
    }


    private static Node createKiado(Document doc, String ado, String nev, String alap, String hely) {
        Element ki = doc.createElement("kiado");

        ki.setAttribute("Adoszam", ado);
        ki.appendChild(createElement(doc, "nev", nev));
        ki.appendChild(createElement(doc, "alapitas", alap));
        ki.appendChild(createElement(doc, "hely", hely));
        return ki;
    }


    private static Node createVas(Document doc, String vid, String nev, String irany, String email) {
        Element vas = doc.createElement(("vasarlo"));
        
        vas.setAttribute("V_ID", vid);
        vas.appendChild(createElement(doc, "nev", nev));
        vas.appendChild(createElement(doc, "iranyitoszam", irany));
        vas.appendChild(createElement(doc, "email", email));
        return vas;
    }


    private static Node createKar(Document doc, String von, String tip, String akc, String igeny) {
        Element kar = doc.createElement("kartya");

        kar.setAttribute("vonalkod", von);
        kar.appendChild(createElement(doc, "tipus", tip));
        kar.appendChild(createElement(doc, "akcio", akc));
        kar.appendChild(createElement(doc, "igenyles", igeny));
        return kar;
    }


    private static Node createVet(Document doc, String kbid, String vid, String vasar) {
        Element vet = doc.createElement("vetel");

        vet.setAttribute("KB_ID", kbid);
        vet.setAttribute("V_ID", vid);
        vet.appendChild(createElement(doc, "vasarlas", vasar));
        return vet;
    }



}