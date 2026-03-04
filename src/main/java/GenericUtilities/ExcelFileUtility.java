package GenericUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {

	String filePath;
	//constructor
	public ExcelFileUtility(String filePath) {
		this.filePath=filePath;
	}
	
	/**
	 * This method will convert the single row data into Map 
	 * @param sheetName
	 * @param uniqueValue
	 * @param uniqueValueRowIdx
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public Map<String, Object> getDataFromSingleRowAsMap(String sheetName, String uniqueValue, int uniqueColIdx) throws EncryptedDocumentException, IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		File file = new File(filePath);
		Workbook workbook = WorkbookFactory.create(file);
		
		Sheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
		throw new IllegalArgumentException("Sheet not found: " + sheetName);
		}
		
		int lastRow = sheet.getLastRowNum();
		
		int rowNum =-1;
	
		for(int i=0;i<=lastRow;i++) {
			try {
				if(sheet.getRow(i).getCell(uniqueColIdx).getStringCellValue() != null) {
					if(getCellValue(sheet.getRow(i).getCell(uniqueColIdx)).equals(uniqueValue)) {
						rowNum = i;
						System.out.println(rowNum);
					}
				}
			}catch(Exception e) {};
			
		}
	
		if(rowNum == -1) {
			System.out.println("No unique value in specified unique col number "+ uniqueColIdx);
			return null;
		}
	
		
		for(int i=0;i<sheet.getRow(rowNum).getLastCellNum();i++) {
				map.put(sheet.getRow(rowNum-1).getCell(i).toString(),getCellValue(sheet.getRow(rowNum).getCell(i)));
		}
		
		workbook.close();
		return map;
		
		}
	
	
	private Object getCellValue(Cell cell) {
	    switch (cell.getCellType()) {
	        case STRING:
	            return cell.getStringCellValue();
	        case NUMERIC:
	            if (DateUtil.isCellDateFormatted(cell)) {
	                return cell.getDateCellValue();
	            }
	            return cell.getNumericCellValue();
	        case BOOLEAN:
	            return cell.getBooleanCellValue();
	        case FORMULA:
	            return cell.getCellFormula();
	        case BLANK:
	        default:
	            return null;
	    }
	}
	
	/**
	 * this method will fetch the  value based on key in map
	 * @param map
	 * @param key
	 * @return
	 */
	public Object getValueFromMap(Map map,String key) {

		if (map.containsKey(key)) {
//		    System.out.println("Value: " + map.get(key));
			return map.get(key);
		} else {
		    System.out.println("Key not found: " + key);
		    return null;
		}
		
	}

	/**
	 * This method will return the multirow data as a Map
	 * @param sheetName
	 * @param uniquevalue
	 * @param uniqueRowIdx
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public Map<String,Object> getdataFromMultiRowAsMap(String sheetName, String uniquevalue, int uniqueRowIdx) throws EncryptedDocumentException, IOException{

		Map<String, Object> map = new HashMap<String, Object>();
		File file = new File(filePath);
		Workbook workbook = WorkbookFactory.create(file);
		
		Sheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
		throw new IllegalArgumentException("Sheet not found: " + sheetName);
		}
		
		int colNum = -1;
		for(int i=0;i<sheet.getRow(uniqueRowIdx).getLastCellNum();i++) {
			try {
				if((sheet.getRow(uniqueRowIdx).getCell(i).toString()).equals(uniquevalue)){
					colNum = i;
				}
			}catch(Exception e) {}
		}
		
		if(colNum == -1) {
			System.out.println("No unique value in specified row Number "+uniqueRowIdx);
			return null;
		}
		
		for(int i=0;i<=sheet.getLastRowNum();i++) {
			try {
				map.put(sheet.getRow(i).getCell(colNum-1).toString(), getCellValue(sheet.getRow(i).getCell(colNum)));
			}catch(Exception e) {}
		}
		
		return map;
	}
	
	/**
	 * This method will read the data from excel based on row and column index
	 * @param sheetName
	 * @param rowIdx
	 * @param colIdx
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public Object getDataFromExcelByRowColIndex(String sheetName, int rowIdx, int colIdx) throws EncryptedDocumentException, IOException {
		
		File file = new File(filePath);
		Workbook workbook = WorkbookFactory.create(file);
		Object data = null;
		try {
			data = getCellValue(workbook.getSheet(sheetName).getRow(rowIdx).getCell(colIdx));
		}catch(Exception e) {}
		return data;
	}
	
	/**
	 * This method will write the data into singleRow and returns the boolean
	 * @param sheetName
	 * @param uniqueValue
	 * @param uniqueColIdx
	 * @param Key
	 * @param value
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public boolean writeDataToExcelInSingleRow(String sheetName, String uniqueValue, int  uniqueColIdx, String Key, String value) throws EncryptedDocumentException, IOException {
		
		Map<String, Object> map = new HashMap<String, Object>();

		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = WorkbookFactory.create(fis);
		
		Sheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
		throw new IllegalArgumentException("Sheet not found: " + sheetName);
		}
		
		int lastRow = sheet.getLastRowNum();
		
		int rowNum =-1;
	
		for(int i=0;i<=lastRow;i++) {
			try {
				if(sheet.getRow(i).getCell(uniqueColIdx).getStringCellValue() != null) {
					if(getCellValue(sheet.getRow(i).getCell(uniqueColIdx)).equals(uniqueValue)) {
						rowNum = i;
						System.out.println(rowNum);
					}
				}
			}catch(Exception e) {};
			
		}
	
		if(rowNum == -1) {
			System.out.println("No unique value in specified unique col number "+ uniqueColIdx);
			return false;
		}
		
		Cell  cell =sheet.getRow(rowNum).getCell(uniqueColIdx);
		try {
			cell.setCellValue(value);
		}catch(Exception e) {}
		
		// Save changes
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);

        fos.close();
        workbook.close();
        
		return true;
	}
	
	/**
	 * This method will write the data into multi row excel
	 * @param sheetName
	 * @param uniqueValue
	 * @param uniqueRowIdx
	 * @param key
	 * @param value
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public boolean writeDataIntoMultiRowExcel(String sheetName, String uniqueValue, int uniqueRowIdx, String key, String value) throws EncryptedDocumentException, IOException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = WorkbookFactory.create(fis);
		
		Sheet sheet = workbook.getSheet(sheetName);
		if (sheet == null) {
		throw new IllegalArgumentException("Sheet not found: " + sheetName);
		}
		
		int colNum = -1;
		for(int i=0;i<sheet.getRow(uniqueRowIdx).getLastCellNum();i++) {
			try {
				if((sheet.getRow(uniqueRowIdx).getCell(i).toString()).equals(uniqueValue)){
					colNum = i;
				}
			}catch(Exception e) {}
		}
		
		if(colNum == -1) {
			System.out.println("No unique value in specified row Number "+uniqueRowIdx);
			return false;
		}
		
		int row =-1;
		for(int i=0;i <= sheet.getLastRowNum();i++) {
			if((sheet.getRow(i).getCell(colNum-1).toString()).equals(key)){
				row = i;
			}
		}
		Cell cell = sheet.getRow(row).getCell(colNum);
		try {
			cell.setCellValue(value);
		}catch(Exception e) {}
		
		// Save changes
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);

        fos.close();
        workbook.close();
		
		return true;
	}
	
	/**
	 * This method will write the data back to excel with specified row and column index
	 * @param sheetName
	 * @param rowNum
	 * @param colNum
	 * @param value
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public boolean writeDataIntoExcelByRowColNumber(String sheetName, int rowNum, int colNum, String value) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = WorkbookFactory.create(fis);
		Cell cell =workbook.getSheet(sheetName).getRow(rowNum).getCell(colNum);
		try {
			cell.setCellValue(value);
		}catch(Exception e) {}
		
		// Save changes
        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);

        fos.close();
        workbook.close();
        
		return true;
	}
	
	/**
	 * This method will return the lastRow number in a given sheet
	 * @param sheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getLastRow(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(filePath);
		
		return WorkbookFactory.create(fis).getSheet(sheetName).getLastRowNum();
	}
	
	/**
	 * This method will return last cell number ina specified row and sheet name
	 * @param sheetName
	 * @param rowIdx
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getLastColumn(String sheetName, int rowIdx) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream(filePath);
		
		return WorkbookFactory.create(fis).getSheet(sheetName).getRow(rowIdx).getLastCellNum();
	}
	
	
	
}

