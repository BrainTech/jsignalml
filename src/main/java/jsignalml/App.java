package jsignalml;

import org.apache.log4j.BasicConfigurator;

/**
 * Hello world!
 *
 */
public class App
{
	public static void main(String[] args)
	throws org.xml.sax.SAXException,
		java.io.IOException,
		javax.xml.xpath.XPathExpressionException,
		XMLDocument.NodeError
	{
		BasicConfigurator.configure();
		System.out.println("Hello World!");
		Codec codec = new Codec(args[0]);
	}
}
