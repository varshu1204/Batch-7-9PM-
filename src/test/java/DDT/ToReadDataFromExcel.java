package DDT;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import genericUtility.PropertiesFileUtility;

public class ToReadDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {


		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Campaign");
		Row r = sh.getRow(1);
		String campname = r.getCell(2).getStringCellValue();
		String target = r.getCell(3).getStringCellValue();
		System.out.println(campname);
		
		
		


		
		
		
		
		
	}

}
