
package xml.dom;


import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import java.io.InputStream;

public class DOMParser{

	public static DOMParser getInstance(){
		return new DOMParser(); 
	}

	public DOMParser() {}

	public void parse(String args[]) throws Exception{

		DocumentBuilderFactory dbf =	DocumentBuilderFactory.newInstance();

		dbf.setNamespaceAware(true);

		dbf.setValidating(dtdValidate || xsdValidate);

		if (xsdValidate) {
			try {
				dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
			} catch (IllegalArgumentException x) {
				System.err.println("Error: JAXP DocumentBuilderFactory attribute not recognized: " + JAXP_SCHEMA_LANGUAGE);
				System.err.println("Check to see if parser conforms to JAXP 1.2 spec.");
				System.exit(1);
			}
		}

		if (schemaSource != null) {
			dbf.setAttribute(JAXP_SCHEMA_SOURCE, new File(schemaSource));
		}

		dbf.setIgnoringComments(ignoreComments);
		dbf.setIgnoringElementContentWhitespace(ignoreWhitespace);
		dbf.setCoalescing(putCDATAIntoText);
		dbf.setExpandEntityReferences(!createEntityRefs);

		DocumentBuilder db = dbf.newDocumentBuilder();

		OutputStreamWriter errorWriter = new OutputStreamWriter(System.err, outputEncoding);
		db.setErrorHandler(new MyErrorHandler(new PrintWriter(errorWriter, true)));

		doc = db.parse(new File(filename));

	}

	public void parse(String XMLData) throws Exception{

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		dbf.setNamespaceAware(true);

		dbf.setValidating(dtdValidate || xsdValidate);
		if (xsdValidate) {
			try {
				dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
			} catch (IllegalArgumentException x) {
				System.err.println("Error: JAXP DocumentBuilderFactory attribute not recognized: "+ JAXP_SCHEMA_LANGUAGE);
				System.err.println("Check to see if parser conforms to JAXP 1.2 spec.");
				System.exit(1);
			}
		}

		if (schemaSource != null) {
			dbf.setAttribute(JAXP_SCHEMA_SOURCE, new File(schemaSource));
		}

		dbf.setIgnoringComments(ignoreComments);
		dbf.setIgnoringElementContentWhitespace(ignoreWhitespace);
		dbf.setCoalescing(putCDATAIntoText);
		dbf.setExpandEntityReferences(!createEntityRefs);

		DocumentBuilder db = dbf.newDocumentBuilder();

		OutputStreamWriter errorWriter = new OutputStreamWriter(System.err, outputEncoding);
		db.setErrorHandler(new MyErrorHandler(new PrintWriter(errorWriter, true)));

		InputSource is =  new InputSource(new StringReader(XMLData));
		is.setEncoding(outputEncoding);

		doc = db.parse(is);
	}

	public void parse(InputStream in) throws Exception{

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		dbf.setNamespaceAware(true);

		dbf.setValidating(dtdValidate || xsdValidate);

		if (xsdValidate) {
			try {
				dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
			} catch (IllegalArgumentException x) {
				System.err.println("Error: JAXP DocumentBuilderFactory attribute not recognized: " + JAXP_SCHEMA_LANGUAGE);
				System.err.println("Check to see if parser conforms to JAXP 1.2 spec.");
				System.exit(1);
			}
		}

		if (schemaSource != null) {
			dbf.setAttribute(JAXP_SCHEMA_SOURCE, new File(schemaSource));
		}

		dbf.setIgnoringComments(ignoreComments);
		dbf.setIgnoringElementContentWhitespace(ignoreWhitespace);
		dbf.setCoalescing(putCDATAIntoText);
		dbf.setExpandEntityReferences(!createEntityRefs);

		DocumentBuilder db = dbf.newDocumentBuilder();

		OutputStreamWriter errorWriter = new OutputStreamWriter(System.err, outputEncoding);
		db.setErrorHandler(new MyErrorHandler(new PrintWriter(errorWriter, true)));

		InputSource is = new InputSource(in);
		is.setEncoding(outputEncoding);
		System.out.println (is.getEncoding());
		doc = db.parse(is);
	}

	public Document getDocument(){
		return this.doc;
	}

	public void setDTDValidate(boolean dtdValidate){
		this.dtdValidate=dtdValidate;
	}

	public void setXSDValidate(boolean xsdValidate){
		this.xsdValidate=xsdValidate;
	}

	public void setSchemaSource(String schemaSource){
		this.schemaSource=schemaSource;
	}

	public void setIgnoreWhitespace(boolean ignoreWhitespace){
		this.ignoreWhitespace=ignoreWhitespace;
	}

	public void setIgnoreComments(boolean ignoreComments){
		this.ignoreComments=ignoreComments;
	}

	public void setPutCDATAIntoText(boolean putCDATAIntoText){
		this.putCDATAIntoText=putCDATAIntoText;
	}

	public void setCreateEntityRefs(boolean createEntityRefs){
		this.createEntityRefs=createEntityRefs;
	}

	public void setFilename(String filename){
		this.filename=filename;
	}

	public void setOutput(PrintWriter out,boolean output){
	}

	public void setEncoding(String outputEncoding){
		this.outputEncoding = outputEncoding;
	}

	static 				String 		outputEncoding 				= "UTF-8";
	private final 		String 		basicIndent 				= "  ";
	public 				Document 	doc							= null;
	private  			boolean 	dtdValidate 				= false; //
	private  			boolean 	xsdValidate 				= false; //
	private 			String 		filename	 				= null;
	private  			String 		schemaSource				= null;
	private  			boolean 	ignoreWhitespace 			= false;
	private 		 	boolean 	ignoreComments   			= false;
	private  			boolean 	putCDATAIntoText 			= false;
	private  			boolean 	createEntityRefs 			= false;
	static final 		String 		JAXP_SCHEMA_LANGUAGE 		= "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	static final 		String 		W3C_XML_SCHEMA 				= "http://www.w3.org/2003/XMLSchema";
	static final 		String 		JAXP_SCHEMA_SOURCE 			= "http://java.sun.com/xml/jaxp/properties/schemaSource";

} 

class MyErrorHandler implements ErrorHandler {
	private PrintWriter out;

	MyErrorHandler(PrintWriter out) {
		this.out = out;
	}

	private String getParseExceptionInfo(SAXParseException spe) {
		String systemId = spe.getSystemId();
		if (systemId == null) {
			systemId = "null";
		}
		String info = "URI=" + systemId +
				" Line=" + spe.getLineNumber() +
				": " + spe.getMessage();
		return info;
	}

	public void warning(SAXParseException spe) throws SAXException {
		out.println("Warning: " + getParseExceptionInfo(spe));
	}

	public void error(SAXParseException spe) throws SAXException {
		String message = "Error: " + getParseExceptionInfo(spe);
		throw new SAXException(message);
	}

	public void fatalError(SAXParseException spe) throws SAXException {
		String message = "Fatal Error: " + getParseExceptionInfo(spe);
		throw new SAXException(message);
	}
}