package jsignalml;

import java.io.File;
import java.io.IOException;

import javax.xml.xpath.XPathExpressionException;

import jsignalml.xml.XMLDocument;
import jsignalml.xml.XMLDocument.NodeError;
import jsignalml.logging.Logger;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLBuffer {
	private static final String XPATH_EVALUATION_TYPE_NODE_CONTENT = "node-content";
	private static final String XPATH_EVALUATION_TYPE_NODE_NAME_LOCAL = "node-name-local";
	private static final String XPATH_EVALUATION_TYPE_NODE_ATTRIBUTE = "node-attribute";
	protected static final Logger log = new Logger(XMLBuffer.class);

	final private XMLDocument source;

	final java.io.File filename;

	public XMLBuffer(File name) {
		log.info("opening xml file %s", name);
		this.filename = name;

		XMLDocument xmlDocument = null;
		try {
			xmlDocument = new XMLDocument(filename.getPath(), new NamespaceRs());
		} catch (IOException e) {
			throw new jsignalml.ExpressionFault.ExternalError(e);
		} catch (SAXException e) {
			throw new jsignalml.ExpressionFault.ExternalError(e);
		}
		this.source = xmlDocument;
	}

	public TypeInt read(TypeString xpathPattern, String xpathType, String xpathAttributeName, TypeInt t) {
		String s = read(xpathPattern, xpathType, xpathAttributeName).value;
		return new TypeInt(s);
	}

	public TypeFloat read(TypeString xpathPattern, String xpathType, String xpathAttributeName, TypeFloat t) {
		String s = read(xpathPattern, xpathType, xpathAttributeName).value;
		return new TypeFloat(Double.parseDouble(s));
	}

	public TypeString read(TypeString xpathPattern, String xpathType, String xpathAttributeName,
			TypeString t) {
		return read(xpathPattern, xpathType, xpathAttributeName);
	}

	public TypeInt read(TypeString xpathPattern, String xpathType, TypeInt t) {
		String s = read(xpathPattern, xpathType).value;
		return new TypeInt(s);
	}

	public TypeFloat read(TypeString xpathPattern, String xpathType, TypeFloat t) {
		String s = read(xpathPattern, xpathType).value;
		return new TypeFloat(Double.parseDouble(s));
	}

	public TypeString read(TypeString xpathPattern, String xpathType,
			TypeString t) {
		return read(xpathPattern, xpathType);
	}

	public TypeBool read(TypeString xpathPattern, String xpathType,
			TypeBool t) {
		String s = read(xpathPattern, xpathType).value;
		return new TypeBool(s);
	}

	public TypeBool read(TypeString xpathPattern, String xpathType, String xpathAttributeName,
			TypeBool t) {
		String s = read(xpathPattern, xpathType, xpathAttributeName).value;
		return new TypeBool(s);
	}

	public TypeBytes read(TypeString xpathPattern, String xpathType,
			TypeBytes t) {
		String s = read(xpathPattern, xpathType).value;
		return new TypeBytes(s);
	}

	public TypeBytes read(TypeString xpathPattern, String xpathType, String xpathAttributeName,
			TypeBytes t) {
		String s = read(xpathPattern, xpathType, xpathAttributeName).value;
		return new TypeBytes(s);
	}

	private TypeString read(TypeString xpathPattern, String xpathType, String xpathAttributeName) {
		String value = null;
		if (xpathType.equals(XPATH_EVALUATION_TYPE_NODE_ATTRIBUTE)){
			value = evaluateXpathNodeAttributeValue(xpathPattern, xpathAttributeName);
		}
		return new TypeString(value);
	}

	private TypeString read(TypeString xpathPattern, String xpathType) {
		String value = null;
		if (xpathType.equals(XPATH_EVALUATION_TYPE_NODE_NAME_LOCAL)) {
			value = evaluateXpathNodeNameLocal(xpathPattern);
		} else if (xpathType.equals(XPATH_EVALUATION_TYPE_NODE_CONTENT)) {
			value = evaluateXpathNodeContentText(xpathPattern);
		}
		return new TypeString(value);
	}

	private String evaluateXpathNodeNameLocal(TypeString xpathPattern) {
		Node node = null;
		String value = null;
		try {
			node = source.getNode(xpathPattern.getValue());
		} catch (XPathExpressionException e) {
			throw new jsignalml.ExpressionFault.ExternalError(e);
		} catch (NodeError e) {
			throw new jsignalml.ExpressionFault.ExternalError(e);
		}
		if ((node != null) && node.hasChildNodes()) {
			NodeList childNodes = node.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node childNode = childNodes.item(i);
				String childNodeLocalName = childNode.getLocalName();
				if (childNodeLocalName != null) {
					value = childNodeLocalName;
					break;
				}
			}
		}
		return value;
	}

	private String evaluateXpathNodeContentText(TypeString xpathPattern) {
		Node node = null;
		String value = null;
		try {
			node = source.getNode(xpathPattern.getValue());
		} catch (XPathExpressionException e) {
			throw new jsignalml.ExpressionFault.ExternalError(e);
		} catch (NodeError e) {
			throw new jsignalml.ExpressionFault.ExternalError(e);
		}
		if ((node != null))
			value = node.getTextContent();
		return value;
	}

	private String evaluateXpathNodeAttributeValue(TypeString xpathPattern, String xpathAttributeName){
		Node node = null;
		String value = null;
		try {
			node = source.getNode(xpathPattern.getValue());
		} catch (XPathExpressionException e) {
			throw new jsignalml.ExpressionFault.ExternalError(e);
		} catch (NodeError e) {
			throw new jsignalml.ExpressionFault.ExternalError(e);
		}
		if ((node != null) && (node.hasAttributes())){
			Node xpathAttributeNameNode = node.getAttributes().getNamedItem(xpathAttributeName);
			if (xpathAttributeNameNode != null){
				value = xpathAttributeNameNode.getFirstChild().getNodeValue();
			}
		}
		return value;
	}
}
