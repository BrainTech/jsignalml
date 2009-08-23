package org.signalml.jsignalml;

import org.apache.log4j.BasicConfigurator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
	throws org.xml.sax.SAXException,
	       java.io.IOException,
	       javax.xml.parsers.ParserConfigurationException,
	       javax.xml.xpath.XPathExpressionException,
	       XMLDocument.NoNodeError
    {
	BasicConfigurator.configure();
        System.out.println( "Hello World!" );
	Codec codec = new Codec(args[0]);
    }
}
