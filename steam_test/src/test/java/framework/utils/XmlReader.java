package framework.utils;

import org.w3c.dom.Document;
import project.enums.Genre;
import project.enums.TableTab;
import project.models.Dictionary;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import static framework.logger.MyLogger.logger;

public class XmlReader {

    public static Dictionary readDictionary(String dictionaryFile, TableTab tab, Genre genre) throws Exception {
        String xmlFile = "./dictionary/" + dictionaryFile;
        String langXml = "dictionary/language";
        String tabXml = "dictionary/tabName/name[@name='%s']";
        String genreXml = "dictionary/genreName/name[@name='%s']";

        //Get DOM
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document xml = db.parse(xmlFile);

        //Get XPath
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();

        //Get first match
        String language = (String) xpath.evaluate(langXml, xml, XPathConstants.STRING);
        String tabName = (String) xpath.evaluate(String.format(tabXml, tab.getAttr()), xml, XPathConstants.STRING);
        String genreTab = (String) xpath.evaluate(String.format(genreXml, genre.getAttr()), xml, XPathConstants.STRING);
        logger.info("reading dictionary:" +language+tabName+genreTab);

        return new Dictionary(language, genreTab, tabName);
    }
}