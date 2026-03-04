package demo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {

    public static Map<String, String> getRowDataAsMap(
            String filePath,
            String sheetName,
            int uniqueColumnIdx,
            String uniqueValue) {

        Map<String, String> dataMap = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            // 👉 Header Row (Row 0)
            Row headerRow = sheet.getRow(0);

            // Iterate through rows
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                if (currentRow == null) continue;

                Cell uniqueCell = currentRow.getCell(uniqueColumnIdx);
                if (uniqueCell == null) continue;

                if (uniqueCell.getStringCellValue().equalsIgnoreCase(uniqueValue)) {

                    // Fetch all headers & values
                    for (int j = 0; j < headerRow.getLastCellNum(); j++) {

//                        if (j == uniqueColumnIdx) continue; // skip tc_name

                        String key = headerRow.getCell(j).getStringCellValue();
                        Cell valueCell = currentRow.getCell(j);

                        String value = (valueCell == null)
                                ? ""
                                : valueCell.toString();

                        dataMap.put(key, value);
                    }
                    break;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel data", e);
        }

        return dataMap;
    }
    
    public static void main(String[] args) {
    	Map<String, String> map =getRowDataAsMap("C:\\Users\\User\\Documents\\Excel_data\\TechTarget_ManualTestCases.xlsx", "Sheet2", 0, "unique value 3");
    	System.out.println(map);
	}
}
