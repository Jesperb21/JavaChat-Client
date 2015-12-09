package Services.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created by JesperB on 09-12-2015.
 * ...todo look over how jakob wrote ReadXMLFile and storePort
 */
public class XMLHandler {
    public void WritePortToXML(Integer Port){
        storePort(Port.toString());
    }
    public int readPortFromXML(){
        return Integer.parseInt(ReadXMLFile());
    }


    private String ReadXMLFile() {

        String test = "";

        try {
            File inputFile = new File("C:\\File.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Properties");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    test = eElement.getElementsByTagName("Port").item(0).getTextContent();
                    System.out.println(eElement.getElementsByTagName("Port").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }
    private void storePort(String Port) {

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Properties");
            doc.appendChild(rootElement);

            // port elements
            Element port = doc.createElement("Port");
            port.appendChild(doc.createTextNode(Port));
            rootElement.appendChild(port);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\file.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }
}
