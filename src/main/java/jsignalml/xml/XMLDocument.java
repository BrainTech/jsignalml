package jsignalml.xml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.EntityResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jsignalml.logging.Logger;

public class XMLDocument
{
	protected static final Logger log = new Logger(XMLDocument.class);

	public static final String signalml_doctype_name = "signalml";
	public static final String signalml_doctype_id = "SignalML_2_0.dtd";
	public static final String signalml_doctype_resource =
		"/jsignalml/specs/schema/" + signalml_doctype_id;

	public static class NodeError extends Exception {
		private static final long serialVersionUID = -473104514836817338L;
		public final String xpath;
		public NodeError(String xpath) {
			this.xpath = xpath;
		}
		public String toString() {
			return getClass().getName() + " " + xpath;
		}
	}

	protected static final XPathFactory xfactory
		= XPathFactory.newInstance();

	final static DocumentBuilder docbuilder;
	protected static NamespaceContext namespaceContext;
	static {
		final DocumentBuilderFactory builderFactory
			= DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		try {
			docbuilder = builderFactory.newDocumentBuilder();
		} catch (javax.xml.parsers.ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
		docbuilder.setEntityResolver(new SimpleEntityResolver());

		log.info("builderFactory is %s", builderFactory.getClass());
	}

	public static class SimpleEntityResolver implements EntityResolver {
		@Override
		public InputSource resolveEntity(String publicId, String systemId)
			throws java.io.IOException,
			       org.xml.sax.SAXException
		{
			log.info("resolving %s/%s", publicId, systemId);
			if (systemId.endsWith("/" + signalml_doctype_id)) {
				InputStream dtdStream = XMLDocument.class
					.getResourceAsStream(signalml_doctype_resource);
				return new InputSource(dtdStream);
			} else {
				// use default behaviour
				return null;
			}
		}
	}

	final Document document;

	public XMLDocument(InputStream stream)
		throws java.io.IOException,
		       org.xml.sax.SAXException
	{
		XMLDocument.namespaceContext = null;
		this.document = docbuilder.parse(stream);
	}

	public XMLDocument(File filename)
		throws java.io.IOException,
		       org.xml.sax.SAXException
	{
		this(new FileInputStream(filename));
	}

	public XMLDocument(InputStream stream, NamespaceContext namespaceContext)
		throws java.io.IOException,
		       org.xml.sax.SAXException
	{
		XMLDocument.namespaceContext = namespaceContext;
		this.document = docbuilder.parse(stream);
	}

	public XMLDocument(String filename, NamespaceContext namespaceContext)
		throws java.io.IOException,
		       org.xml.sax.SAXException
	{
		this(new FileInputStream(filename), namespaceContext);
	}

	public Node getNode(String xpath)
		throws javax.xml.xpath.XPathExpressionException,
		       NodeError
	{
		return subNode(this.document, xpath);
	}

	public Element getElement(String xpath)
		throws javax.xml.xpath.XPathExpressionException,
		       NodeError
	{
		return subElement(this.document, xpath);
	}

	public Element getElement_re(String xpath)
		throws NodeError
	{
		try {
			return subElement(this.document, xpath);
		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Retrieve subelement of where described by xpath.
	 * Do not throw an an exception if not found, return null instead.
	 */
	public static Node _subNode(Node where, String xpath)
		throws javax.xml.xpath.XPathExpressionException
	{
		XPath getter = xfactory.newXPath();
		if (namespaceContext != null){
			getter.setNamespaceContext(namespaceContext);
		}
		Object node = getter.evaluate(xpath, where,
		                              XPathConstants.NODE);
		return (Node) node;
	}

	public static Node _subNode_re(Node where, String xpath)
	{
		try {
			return _subNode(where, xpath);
		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Retrieve subelement of where described by xpath.
	 * Throw NodeError if not found.
	 */
	public static Node subNode(Node where, String xpath)
		throws javax.xml.xpath.XPathExpressionException,
		       NodeError
	{
		Node node = _subNode(where, xpath);
		if (node == null)
			throw new NodeError(xpath);
		return (Node) node;
	}

	public static Element subElement(Node where, String xpath)
		throws javax.xml.xpath.XPathExpressionException,
		       NodeError
	{
		return (Element) subNode(where, xpath);
	}

	/**
	 * Retrieve subelement of where described by xpath.
	 * Do not throw an an exception if not found, return null instead.
	 */
	public static Element _subElement(Node where, String xpath)
		throws javax.xml.xpath.XPathExpressionException
	{
		return (Element) _subNode(where, xpath);
	}

	public <T extends Node> Iterable<T> getNodes(String xpath)
		throws javax.xml.xpath.XPathExpressionException
	{
		return subNodes(this.document, xpath);
	}

	public <T extends Node> Iterable<T> getNodes_re(String xpath)
	{
		try {
			return getNodes(xpath);
		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T extends Node> Iterable<T> subNodes(Node where, String xpath)
		throws javax.xml.xpath.XPathExpressionException
	{
		XPath getter = xfactory.newXPath();
		if (namespaceContext != null){
			getter.setNamespaceContext(namespaceContext);
		}
		Object nodes = getter.evaluate(xpath, where, XPathConstants.NODESET);
		return new DOMIterable<T>((NodeList) nodes);
	}

	public static <T extends Node> Iterable<T> subNodes_re(Node where, String xpath)
	{
		try {
			return subNodes(where, xpath);
		} catch (XPathExpressionException e) {
			throw new RuntimeException(e);
		}
	}
}
