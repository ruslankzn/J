package com.javarush.task.task33.task3309;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) {

        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setCoalescing(true);   // specifies that parser produced by this code will convert CDATA nodes to text
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            // Конвертируем объект не в строку, а в объект Document.

            marshaller.marshal(obj, doc);

            //Output the document
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");  // this allows you make \n
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");

            NodeList nodeList = doc.getElementsByTagName("*");
            if (nodeList.getLength() > 0) {
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeName().equals(tagName)) {



                        node.getParentNode().insertBefore(doc.createComment(comment), node);


                    }
                    if (node.getFirstChild().getNodeType() == Node.TEXT_NODE) {
                        Node currentNode = node.getFirstChild();
                        if (currentNode.getTextContent().matches(".*[<>&\'\"].*")) {

                            String content = currentNode.getTextContent();
                            CDATASection cdataSection = doc.createCDATASection(content);
                            node.replaceChild(cdataSection, currentNode);

                        }
                    }

                }
            }


            // Convert doc to string
            StringWriter write = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(write));
            return write.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws TransformerException, JAXBException, ParserConfigurationException {
//
//        System.out.println(toXmlWithComment(new First(), "nothing", "it's a comment"));
//        System.out.println("--------------------------------------------------------------------------------------------------------------------");
//        System.out.println(toXmlWithComment(new First(), "second", "it's a comment"));

    }

//    @XmlRootElement(name = "first")
//    public static class First {
//        @XmlElement(name = "second")
//        public String item1 = "some string";
//        @XmlElement(name = "second")
//        public String item2 = "need CDATA because of <second>";
//        @XmlElement(name = "second")
//        public String item3 = "";
//        @XmlElement(name = "third")
//        public String item4;
//        @XmlElement(name = "forth")
//        public Second[] third = new Second[]{new Second()};
//        @XmlElement(name = "fifth")
//        public String item5 = "need CDATA because of \"";
//    }
//
//    public static class Second {
//        @XmlElement(name = "second")
//        public String item1 = "some string";
//        @XmlElement(name = "second")
//        public String item2 = "need CDATA because of <second>";
//    }
}
