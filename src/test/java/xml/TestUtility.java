package xml;

import java.io.File;
import java.io.FileInputStream;

public class TestUtility {

	public static String xmlFiletoString(String testFilePath){

		try{

			File 					contFile 			= new File(testFilePath);

			if (contFile.exists()) {

				FileInputStream 	fis 				= new FileInputStream(contFile);
				int 				length 				= new Long(contFile.length()).intValue();
				byte[] 				byteSet 			= new byte[length];
				fis.read(byteSet);
				String 				response 			= new String(byteSet);
//				System.out.println("xmlFiletoString().response : " + response);
				return response;
			}

		}catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
