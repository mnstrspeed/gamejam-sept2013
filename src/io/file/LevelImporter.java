package io.file;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import game.Level;

public class LevelImporter {
	public static Level importLevel(String filename) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		LevelXMLHandler handler = new LevelXMLHandler();
		try {
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(filename, handler);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return handler.getLevel();
	}
}
