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

public class XPathQTFL19 {

	public static void main(String[] args) {
		
		try {		
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse("studentQTFL19.xml");
			
			document.getDocumentElement().normalize();
			XPath xPath = XPathFactory.newInstance().newXPath();
			
		
			//1.
			//String expression = "class / student";

            //2.
			//String expression = "//student[@id='02']";

            //3.
			//String expression = "//student";

            //4.
			//String expression = "class/student[2]";
            
            //5.
			//String expression = "class/student[last()]";

            //6.
			//String expression = "class/student[last()-1]";

            //7.
			//String expression = "class/student[position()<3]";

            //8.
			//String expression = "/class/*";

            //9.
			//String expression = "//student[@*]";

            //10.
			//String expression = "//*";

            //11.
			//String expression = "/class/student[age>20]";

            //12.
			//String expression = "//student/firstname | //student/lastname";
			
			
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
			
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				System.out.println("\nAktuális elem: " + node.getNodeName());
				
				if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
					
					Element element = (Element) node;
					
					System.out.println("Student ID: " + element.getAttribute("id"));
					System.out.println("Keresztnév: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
					System.out.println("Vezetéknév: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					System.out.println("Becenév: " + element.getElementsByTagName("becenev").item(0).getTextContent());
					System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());
				}
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch(SAXException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(XPathExpressionException e) {
			e.printStackTrace();
		}
		
	}
	
}