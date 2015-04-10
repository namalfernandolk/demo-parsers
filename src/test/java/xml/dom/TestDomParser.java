package xml.dom;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xml.TestUtility;

public class TestDomParser {

	String xmlFileStr = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Ignore
	public void test() {

		try {

			DOMParser 										parser   							= DOMParser.getInstance();
			parser.parse((String)TestUtility.xmlFiletoString("/home/namal/Downloads/testttt.xml"));
			Document 										myDoc 								= parser.getDocument();

			NodeList										hotelValuedAvailRQList				= myDoc.getElementsByTagName("HotelValuedAvailRQ");

			for (int i = 0; i < hotelValuedAvailRQList.getLength(); i++) {

				NodeList									subHotelValuedAvailRQList 				= hotelValuedAvailRQList.item(i).getChildNodes();

				for (int j = 0; j < subHotelValuedAvailRQList.getLength(); j++) {

					if (subHotelValuedAvailRQList.item(j).getLocalName() != null) {


						if (subHotelValuedAvailRQList.item(j).getLocalName().equals("OccupancyList")) {

							NodeList									subOccupancyList 				= subHotelValuedAvailRQList.item(j).getChildNodes();

							for (int k = 0; k < subOccupancyList.getLength(); k++) {

								if (subOccupancyList.item(k).getLocalName() != null) {

									if (subOccupancyList.item(k).getLocalName().equals("HotelOccupancy")) {

										NodeList									subHotelOccupancyList 				= subOccupancyList.item(k).getChildNodes();

										for (int l = 0; l < subHotelOccupancyList.getLength(); l++) {

											if (subHotelOccupancyList.item(l).getLocalName() != null) {

												if (subHotelOccupancyList.item(l).getLocalName().equals("Room")) {

													String roomCode = subHotelOccupancyList.item(l).getAttributes().getNamedItem("code").getNodeValue();
													String roomName = subHotelOccupancyList.item(l).getFirstChild().getNodeValue();

													System.out.println("test().roomCode : " + roomCode + " | roomName : " + roomName);

												}

											}

										}

									}

								}

							}

						}

					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMadhurangaXML() {
		// TODO Auto-generated method stub			

		try {

			DOMParser 										parser   							= DOMParser.getInstance();
			parser.parse((String)TestUtility.xmlFiletoString("/home/namal/Downloads/testttt.xml"));

			Document 										myDoc 								= parser.getDocument();

			NamespaceContext ctx = new NamespaceContext() {
				public String getNamespaceURI(String prefix) {
					return 
							prefix.equals("xsi") ? "http://www.w3.org/2001/XMLSchema-instance" :
								null; 
				}
				public Iterator getPrefixes(String val) {
					return null;
				}
				public String getPrefix(String uri) {
					return null;
				}
			};
			
			Node runTickets = myDoc.getFirstChild();
			System.out.println("testMadhurangaXML().runTickets : " + runTickets);

			System.out.println("testMadhurangaXML().For Each Node (Direct) : =============================================================================");
			
			XPath 				xPath 						=  XPathFactory.newInstance().newXPath();
			xPath.setNamespaceContext(ctx);

			String 			secondRunTicketNumber 		= xPath.compile("string(RunTicket[2]/RunTicketNumber)").evaluate(runTickets);
			System.out.println("testMadhurangaXML().First secondRunTicketNumber -- : " + secondRunTicketNumber);
			
			
			String 			forthRunTicketNumber 		= xPath.compile("string(RunTicket[4]/RunTicketNumber)").evaluate(runTickets);
			System.out.println("testMadhurangaXML().Forth forthRunTicketNumber -- : " + forthRunTicketNumber);
			
			
			
			System.out.println("testMadhurangaXML().For Each Node (Loop) : =============================================================================");

			NodeList										hotelValuedAvailRQList				= myDoc.getElementsByTagName("RunTicket");
			System.out.println("testMadhurangaXML().hotelValuedAvailRQList : " + hotelValuedAvailRQList);
			
			for (int i = 0; i < hotelValuedAvailRQList.getLength(); i++) {

				if (hotelValuedAvailRQList.item(i).getLocalName().equals("RunTicket")) {
					System.out.println("testMadhurangaXML().RunTicket : ---- ["+i+"]" + hotelValuedAvailRQList.item(i));
					
					XPath 				xPath2 						=  XPathFactory.newInstance().newXPath();
					xPath2.setNamespaceContext(ctx);

					String 			runTicketNumber 		= xPath2.compile("RunTicketNumber").evaluate(hotelValuedAvailRQList.item(i));
					System.out.println("testMadhurangaXML().runTicketNumber : " + runTicketNumber);
				}

			}

			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
