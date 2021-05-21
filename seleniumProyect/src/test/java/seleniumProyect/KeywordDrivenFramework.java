package seleniumProyect;
import static org.junit.Assert.assertEquals;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import seleniumProyect.Utilities.Operations;

public class KeywordDrivenFramework {

	public static WebDriver driver;


	
	
	@Test
	public void kdfExecutable() throws IOException, InterruptedException {
		Object[][] object=null;
		FileInputStream fis=new FileInputStream(new File("D:\\Users\\ashre\\eclipse-workspace\\seleniumProyect\\src\\test\\java\\seleniumProyect.xlsx"));
		HSSFWorkbook wb=new HSSFWorkbook(fis);
		HSSFSheet sheet=wb.getSheetAt(0);
		int count= sheet.getLastRowNum()-sheet.getFirstRowNum();
		object=new Object[count][5];
		for(int i=0;i<count;i++){
			Row row=sheet.getRow(i+1);
			//TODO Read well and execute
			Operations.execute(new Properties(),row.getCell(2).toString(),row.getCell(3).toString(),row.getCell(4).toString(),row.getCell(5).toString());
		}
	}
	
	
	@After
	public void tearDown2() {
		driver.quit();
	}




}
