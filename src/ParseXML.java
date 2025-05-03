import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ParseXML {

    public List<TrainDetails> parseTrainData(String xmlData) throws ParserConfigurationException, SAXException, IOException {
        List<TrainDetails> trains = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //parse xml
        Document document = builder.parse(new InputSource(new StringReader(xmlData)));
        document.getDocumentElement().normalize();

        NodeList nodeList = document.getElementsByTagName("objStationData");
        
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);

            TrainDetails train = new TrainDetails();
            train.origin = getValue(element, "Origin");
            train.destination = getValue(element, "Destination");
            train.expectedArrival = getValue(element, "Exparrival");
            train.expectedDeparture = getValue(element, "Expdepart");
            train.destinationTime = getValue(element, "Destinationtime");
            train.trainCode = getValue(element, "Traincode");
            train.trainDate = getValue(element, "Traindate");
            train.status = getValue(element, "Status");

            trains.add(train);}

        return trains;
    }

    private String getValue(Element parent, String elementName) {
        NodeList nodeList = parent.getElementsByTagName(elementName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }
}
