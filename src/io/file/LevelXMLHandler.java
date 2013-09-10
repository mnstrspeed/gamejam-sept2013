package io.file;

import game.Game;
import game.Level;
import game.objects.Platform;
import game.objects.resources.ResourceKind;

import java.text.ParseException;

import nl.tomsanders.game.engine.util.Size;
import nl.tomsanders.game.engine.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LevelXMLHandler extends DefaultHandler {
	@SuppressWarnings("serial")
	private class UnexpectedElementException extends ParseException {
		public UnexpectedElementException(String element, String context,
				int errorOffset) {
			super("Unexpected element " + element + " (" + context + ")",
					errorOffset);
		}
	}

	@SuppressWarnings("serial")
	private class MissingElementException extends ParseException {
		public MissingElementException(String element, String context,
				int errorOffset) {
			super("Missing element " + element + " (" + context + ")",
					errorOffset);
		}
	}

	@SuppressWarnings("serial")
	private class MissingAttributeException extends ParseException {
		public MissingAttributeException(String attribute, String element,
				int errorOffset) {
			super("Missing attribute " + attribute + " in " + element,
					errorOffset);
		}
	}

	@SuppressWarnings("serial")
	private class UnexpectedAttributeValueException extends ParseException {
		public UnexpectedAttributeValueException(String value,
				String attribute, String element, String context,
				int errorOffset) {
			super("Unexpected attribute value " + value + " for " + attribute
					+ " in " + element + " (" + context + ")", errorOffset);
		}
	}
	
	@SuppressWarnings("serial")
	private class MissingTextContentException extends ParseException {
		public MissingTextContentException(String element,
				int errorOffset) {
			super("Missing text content in " + element,
					errorOffset);
		}
	}
	
	@SuppressWarnings("serial")
	private class UnexpectedTextContentValueException extends ParseException {
		public UnexpectedTextContentValueException(String value, String element, String context,
				int errorOffset) {
			super("Unexpected text content " + value + " in " + element + " (" + context + ")",
					errorOffset);
		}
	}

	private Locator locator;
	private Level level;
	
	private static final int FLAG_READ_RESOURCES = 1;
	private static final int FLAG_READ_RESOURCES_LIFE = 2;
	private static final int FLAG_READ_RESOURCES_STONE = 4;
	private static final int FLAG_READ_RESOURCES_WOOD = 8;
	
	private int elementMask;

	public LevelXMLHandler() {
		this.elementMask = 0;
	}
	
	public Level getLevel() {
		return level;
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		this.locator = locator;
	}

	@Override
	public void startDocument() throws SAXException {

	}

	@Override
	public void endDocument() throws SAXException {
		
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		String strX, strY, strWidth, strHeight;
		double dblX, dblY, dblWidth, dblHeight;
		Size size;
		Vector position;
		try {
			this.checkElementStartContext(qName);
			switch (qName) {
			case "lvl":
				strWidth = attributes.getValue("width");
				dblWidth = this.castAttributeValueToDouble(strWidth, "width",
						qName);
				strHeight = attributes.getValue("height");
				dblHeight = this.castAttributeValueToDouble(strHeight,
						"height", qName);
				size = new Size(Double.valueOf(strWidth),
						Double.valueOf(strHeight));
				this.level = new Level(size);
				break;
			case "pltfrm":
				strX = attributes.getValue("x");
				dblX = this.castAttributeValueToDouble(strX, "x", qName);
				strY = attributes.getValue("y");
				dblY = this.castAttributeValueToDouble(strY, "y", qName);
				strWidth = attributes.getValue("width");
				dblWidth = this.castAttributeValueToDouble(strWidth, "width",
						qName);
				strHeight = attributes.getValue("height");
				dblHeight = this.castAttributeValueToDouble(strHeight,
						"height", qName);
				position = new Vector(dblX, dblY);
				size = new Size(dblWidth, dblHeight);
				this.level.addGameObject(new Platform(position, size));
				break;
			case "plyrStrt":
				strX = attributes.getValue("x");
				dblX = this.castAttributeValueToDouble(strX, "x", qName);
				strY = attributes.getValue("y");
				dblY = this.castAttributeValueToDouble(strY, "y", qName);
				position = new Vector(dblX, dblY);
				this.level.addPlayerStart(position);
				break;
			case "rsrcs":
				this.elementMask = this.elementMask | LevelXMLHandler.FLAG_READ_RESOURCES;
				break;
			case "lf":
				this.elementMask = this.elementMask | LevelXMLHandler.FLAG_READ_RESOURCES_LIFE;
				break;
			case "stn":
				this.elementMask = this.elementMask | LevelXMLHandler.FLAG_READ_RESOURCES_STONE;
				break;
			case "wd":
				this.elementMask = this.elementMask | LevelXMLHandler.FLAG_READ_RESOURCES_WOOD;
				break;
			default:
				throw new UnexpectedElementException(qName, "unknown",
						this.locator.getLineNumber());
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		try {
			this.checkElementEndContext(qName);
			switch(qName) {
			case "rsrcs":
				this.elementMask = this.elementMask & ~FLAG_READ_RESOURCES;
				break;
			case "lf":
				this.elementMask = this.elementMask & ~FLAG_READ_RESOURCES_LIFE;
				break;
			case "stn":
				this.elementMask = this.elementMask & ~FLAG_READ_RESOURCES_STONE;
				break;
			case "wd":
				this.elementMask = this.elementMask & ~FLAG_READ_RESOURCES_WOOD;
				break;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void characters(char ch[], int start, int length) {
	try {
		String content = new String(ch, start, length);
		if((this.elementMask & FLAG_READ_RESOURCES_LIFE) == FLAG_READ_RESOURCES_LIFE) {
			int amount = castStringContentToInt(content, "lf");
			this.level.setStartResourceAmount(ResourceKind.Life, amount);
		}
		else if((this.elementMask & FLAG_READ_RESOURCES_STONE) == FLAG_READ_RESOURCES_STONE) {
			int amount = castStringContentToInt(content, "stn");
			this.level.setStartResourceAmount(ResourceKind.Stone, amount);
		}
		else if((this.elementMask & FLAG_READ_RESOURCES_WOOD) == FLAG_READ_RESOURCES_WOOD) {
			int amount = castStringContentToInt(content, "wd");
			this.level.setStartResourceAmount(ResourceKind.Wood, amount);
		} 
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	private void checkElementStartContext(String element) throws ParseException {
		switch (element) {
		case "pltfrm": // Intended fall through
		case "plyrStrt": // Intended fall through
		case "rscrcs":
			if (this.level == null) {
				throw new UnexpectedElementException(element,
						"no level started", this.locator.getLineNumber());
			}
			if (element.equals("plyrStrt")) {
				if (this.level.getPlayerStarts().size() > Game.GAME_NUM_PLAYERS) {
					throw new UnexpectedElementException(element, "maximum of "
							+ Game.GAME_NUM_PLAYERS + " players exceeded",
							this.locator.getLineNumber());
				}
			}
			break;
		case "lf": // Intended fall through
		case "st":  // Intended fall through
		case "wd":
			if((this.elementMask & FLAG_READ_RESOURCES) != FLAG_READ_RESOURCES) {
				throw new UnexpectedElementException(element, "resource reading not started", this.locator.getLineNumber());
			}
			break;
		}
	}

	private void checkElementEndContext(String element) throws ParseException {
		switch (element) {
		case "lvl":
			if (this.level.getPlayerStarts().size() < Game.GAME_NUM_PLAYERS) {
				throw new MissingElementException("plyrStrt", "minimum of "
						+ Game.GAME_NUM_PLAYERS + " players not reached",
						this.locator.getLineNumber());
			}
			break;
		case "rscrcs":
			if((this.elementMask & FLAG_READ_RESOURCES) != FLAG_READ_RESOURCES) {
				throw new UnexpectedElementException(element, "finished reading without starting", 0);
			}
			break;
		case "lf":
			if((this.elementMask & FLAG_READ_RESOURCES_LIFE) != FLAG_READ_RESOURCES_LIFE) {
				throw new UnexpectedElementException(element, "finished reading without starting", 0);
			}
			break;
		case "stn":
			if((this.elementMask & FLAG_READ_RESOURCES_STONE) != FLAG_READ_RESOURCES_STONE) {
				throw new UnexpectedElementException(element, "finished reading without starting", 0);
			}
			break;
		case "wd":
			if((this.elementMask & FLAG_READ_RESOURCES_WOOD) != FLAG_READ_RESOURCES_WOOD) {
				throw new UnexpectedElementException(element, "finished reading without starting", 0);
			}
		}
	}

	private double castAttributeValueToDouble(String strValue,
			String attribute, String element) throws ParseException {
		double dblValue;
		if (strValue == null) {
			throw new MissingAttributeException(attribute, element,
					this.locator.getLineNumber());
		}
		try {
			dblValue = Double.valueOf(strValue);
		} catch (NumberFormatException e) {
			throw new UnexpectedAttributeValueException(strValue, attribute,
					element, "number expected", this.locator.getLineNumber());
		}
		return dblValue;
	}
	
	private int castStringContentToInt(String strValue, String element) throws ParseException {
		int intValue;
		if (strValue.isEmpty()) {
			throw new MissingTextContentException(element, this.locator.getLineNumber());
		}
		try {
			intValue = Integer.valueOf(strValue);
		} catch (NumberFormatException e) {
			throw new UnexpectedTextContentValueException(strValue, element, "number expected", this.locator.getLineNumber());
		}
		return intValue;
	}
}
