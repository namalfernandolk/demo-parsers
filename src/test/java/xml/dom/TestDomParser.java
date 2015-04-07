package xml.dom;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
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
	public void test() {
		
		try {
			
			DOMParser 										parser   							= DOMParser.getInstance();
			parser.parse((String)TestUtility.xmlFiletoString("/rezsystem/workspace/demo-parsers/src/main/java/xml/sample/xmlRQ.xml"));
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
	
}
