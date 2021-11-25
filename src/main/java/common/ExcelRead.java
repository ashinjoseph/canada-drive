package common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelRead {

	/*public String[][] getTableArray(String xlFilePath, String sheetName,
			String tableName) throws Exception {

		String[][] tabArray = null;

		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
		Sheet sheet = workbook.getSheet(sheetName);
		int startRow, startCol, endRow, endCol, ci, cj;
		Cell tableStart = sheet.findCell(tableName);
		startRow = tableStart.getRow();
		startCol = tableStart.getColumn();
		int endCols = sheet.getColumns();
		int endRows = sheet.getRows();
		System.out.println("startRow :" + startRow + "startCol :" + startCol);
		
		 * endCols and endRows is given to the application for parsing the data.
		 * The data which are beyond endCol and endRow will not be fetched and
		 * testcase either gets failed or skipped.
		 
		//sheet.findCell(arg0)
		Cell tableEnd1 = sheet.findCell(startCol, startRow, endCols,
				endRows, true);

		endRow = tableEnd1.getRow();
		endCol = tableEnd1.getColumn();
		System.out.println("startRow=" + startRow + ", endRow=" + endRow + ", "
				+ "startCol=" + startCol + ", endCol=" + endCol);

		tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
		// System.out.println("tableEnd :"+tableEnd1);
		ci = 0;

		for (int i = startRow + 1; i < endRow; i++, ci++) {
			cj = 0;
			for (int j = startCol + 1; j < endCol; j++, cj++) {
				tabArray[ci][cj] = sheet.getCell(j, i).getContents();
			}
		}
		for (int i = 0; i < tabArray.length; i++) {
			for (int j = 0; j < tabArray[i].length; j++) {

			}
			System.out.println("");
		}

		return (tabArray);

	}*/

	@SuppressWarnings("unused")
	public Object[][] getMapArray(String xlFilePath, String sheetName,
			String tableName) throws Exception {

		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
		Sheet sheet = workbook.getSheet(sheetName);
		int startRow, startCol, endRow, endCol, ci, cj;
		Cell tableStart = sheet.findCell(tableName+".start");
		startRow = tableStart.getRow();
		startCol = tableStart.getColumn();
		int endCols = sheet.getColumns();
		int endRows = sheet.getRows();
		System.out.println("startRow :" + startRow + "startCol :" + startCol);

		Cell tableEnd1 = sheet.findCell(tableName+".end");

		endRow = tableEnd1.getRow();
		endCol = tableEnd1.getColumn();
		System.out.println("startRow=" + startRow + ", endRow=" + endRow + ", "
				+ "startCol=" + startCol + ", endCol=" + endCol);

		// System.out.println("tableEnd :"+tableEnd1);
		ci = 0;
		int k = 0;
		Object[][] obj = new Object[endRow - startRow - 1][1];
		// int h = startRow;
		for (int i = startRow + 1; i < endRow; i++, ci++) {
			cj = 0;
			Map<Object, Object> datamap = new HashMap<Object, Object>();
			for (int j = startCol + 1; j < endCol; j++, cj++) {

				datamap.put(sheet.getCell(j, startRow).getContents(), sheet
						.getCell(j, i).getContents());

			}
			datamap.remove("");
			obj[k][0] = datamap;
			k++;
		}
	
		return (obj);

	}

//	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	/*public void writeDataInExcel(Map map, String xlFilePath, String sheetName,
			String tableName) throws Exception {
		try {
			List<String> keys = new ArrayList(map.keySet());
			List<String> values = new ArrayList(map.values());
			ExcelRead excelRead = new ExcelRead();
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			Sheet sheet = workbook.getSheet(sheetName);

			int startRow, startCol, endRow, endCol;
			Cell tableStart = sheet.findCell(tableName);
			startRow = tableStart.getRow();
			startCol = tableStart.getColumn();

			Cell[] row = sheet.getRow(startRow);

			for (int k = 0; k < keys.size(); k++) {
				for (Cell cell : row) {

					if (keys.get(k).equalsIgnoreCase(cell.getContents()))

					{
						excelRead.setCellValue(xlFilePath, sheetName,
								startRow + 1, cell.getColumn(), values.get(k));
						break;
					}
				}
			}
		} catch (Exception e) {
		}
	}

	public void setCellValue(String xlFilePath, String sheetName, int rowNum,
			int columnNum, String columnValue) {
		try {

			FileInputStream jis = new FileInputStream(xlFilePath);
			org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory
					.create(jis);
			org.apache.poi.ss.usermodel.Sheet s = wb.getSheet(sheetName);
			s.getRow(rowNum).createCell(columnNum).setCellValue(columnValue);
			FileOutputStream out = new FileOutputStream(new File(xlFilePath));
			wb.write(out);
			jis.close();
		} catch (Exception ex) {
			System.out.println(ex);

		}
	}*/
}
