package xml.axiom;

import static org.junit.Assert.*;

import java.io.StringBufferInputStream;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axiom.om.impl.llom.util.AXIOMUtil;
import org.apache.axiom.om.xpath.AXIOMXPath;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import xml.TestUtility;

public class TestAxiomParser {

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

		XMLStreamReader parser;
		
		try {
			parser 															= XMLInputFactory.newInstance().createXMLStreamReader(new StringBufferInputStream(((String)TestUtility.xmlFiletoString("/rezsystem/workspace/demo-parsers/src/main/java/xml/sample/xmlRQ.xml")).replaceAll("xmlns=\"http://traveltalk.com/wsHotelAvail\"", "")));
			OMElement 					documentElement 					= new StAXOMBuilder(parser).getDocumentElement();
//			System.out.println("test().documentElement : " + documentElement);
			
			AXIOMXPath 					hotelValuedAvailRQAxiomxPath 		= new AXIOMXPath("/HotelValuedAvailRQ");
			
			OMElement 					hotelValuedAvailRQElement			= (OMElement)hotelValuedAvailRQAxiomxPath.selectSingleNode(documentElement);
//			System.out.println("test().hotelValuedAvailRQElement : " + hotelValuedAvailRQElement);
			
			String 					firstRoom 						=  (String) new AXIOMXPath("string(OccupancyList/HotelOccupancy[1]/Room)").selectSingleNode(hotelValuedAvailRQElement);
			System.out.println("test().firstRoom : " + firstRoom);
			
			String 					secondRoom 						=  (String) new AXIOMXPath("string(OccupancyList/HotelOccupancy[2]/Room)").selectSingleNode(hotelValuedAvailRQElement);
			System.out.println("test().secondRoom : " + secondRoom);
			
			String 					arg2RoomCode 						=  (String) new AXIOMXPath("string(OccupancyList/HotelOccupancy[@arg = 'arg-value2']/Room/@code)").selectSingleNode(hotelValuedAvailRQElement);
			System.out.println("test().arg2RoomCode : " + arg2RoomCode);
			
			OMElement 					occupancyListElement					= (OMElement)new AXIOMXPath("OccupancyList").selectSingleNode(hotelValuedAvailRQElement);
			Iterator 					hotelOccupancyIterator 			= occupancyListElement.getChildrenWithLocalName("HotelOccupancy");
			System.out.println("test().hotelOccupancyIterator : " + hotelOccupancyIterator);
			
			while(hotelOccupancyIterator.hasNext()){
				
				OMElement 				hotelOccupancyElement 					= (OMElement)hotelOccupancyIterator.next();				
				System.out.println("test().hotelOccupancyElement : " + hotelOccupancyElement);
				
				OMElement  				roomElement 			= hotelOccupancyElement.getFirstChildWithName(new QName("Room"));
				System.out.println("test().roomElement : " + roomElement);
				
				String 					arg 					=  hotelOccupancyElement.getAttributeValue(new QName("arg"));
				System.out.println("test().arg : " + arg);
				
				String 					roomName 						=  (String) new AXIOMXPath("string()").selectSingleNode(roomElement);
				System.out.println("test().roomName : " + roomName);
				
				String 					roomCode 						=  (String) new AXIOMXPath("string(@code)").selectSingleNode(roomElement);
				System.out.println("test().roomCode : " + roomCode);
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testMadhurangaXML() {
		
	XMLStreamReader parser;
		
		try {

			parser 															= XMLInputFactory.newInstance().createXMLStreamReader(new StringBufferInputStream(((String)TestUtility.xmlFiletoString("/home/namal/Downloads/testttt.xml")).replaceAll("xmlns=\"http://traveltalk.com/wsHotelAvail\"", "")));
			OMElement 					documentElement 					= new StAXOMBuilder(parser).getDocumentElement();
			
			AXIOMXPath 					runSecondTicketsLeaseNumberPath 				= new AXIOMXPath("string(/xsi:RunTickets/RunTicket[2]/OriginDetails/LeaseDetails/LeaseNumber)");
			runSecondTicketsLeaseNumberPath.addNamespace("xsi", 		"http://www.w3.org/2001/XMLSchema-instance");
			
			String runSecondTicketsLeaseNumber  = (String)runSecondTicketsLeaseNumberPath.selectSingleNode(documentElement);
			System.out.println("testMadhurangaXML().runSecondTicketsLeaseNumber : " + runSecondTicketsLeaseNumber);

		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
