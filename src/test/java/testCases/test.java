package testCases;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;

import genericUtilities.ExcelFileUtility;


public class test {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		ExcelFileUtility ex= new ExcelFileUtility("./resources/Salesforce.xlsx");
		Map<String, Object> map=ex.getDataFromSingleRowAsMap("Salesforce", "Tc_01", 0);

		String c=(String) ex.getValueFromMap(map, "Code");
//		String code= Double.toString(c);
		System.out.println(c);
		
		
	}

}
