package utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public static String getData(String sSheetName, String sTestCaseID) {
		String sJson = null;
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		XSSFRow row = null;
		// XSSFCell cell = null;
		boolean bFlag = false;
		String path = null;
		try {
			path = System.getProperty("user.dir") + "\\TestData\\testData.xlsx";
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sSheetName);

			row = sheet.getRow(0);

			for (int head = 0; head < row.getLastCellNum(); head++) {
				String headValue = row.getCell(head).getStringCellValue();

				if (headValue.equals("Test Case ID")) {
					int icell = row.getCell(head).getColumnIndex();
					int rowsCount = sheet.getLastRowNum();

					for (int row1 = 1; row1 <= rowsCount; row1++) {

						String cellName = sheet.getRow(row1).getCell(icell).getStringCellValue();

						if (!cellName.isEmpty()) {
							if (cellName.equals(sTestCaseID)) {

								for (int head1 = 0; head1 < row.getLastCellNum(); head1++) {
									String headValue1 = row.getCell(head1).getStringCellValue();
									if (headValue1.equals("Json")) {

										sJson = sheet.getRow(row1).getCell(head1).getStringCellValue();
										bFlag = true;
									}
								}
								if (bFlag == true) {
									break;
								}
							}
						} else {
							break;
						}

					}
				} else {
					break;
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return sJson;
	}
}
