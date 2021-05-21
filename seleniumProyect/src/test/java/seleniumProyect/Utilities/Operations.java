package seleniumProyect.Utilities;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import seleniumProyect.KeywordDrivenFramework;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Operations {
    private static WebElement elementMemory;

    public static Sheet readExcel(String filePath, String fileName, String SheetName) throws IOException {
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        Workbook excelWorkbook = null;
        String fileExtension = fileName.substring(fileName.indexOf("."));
        if (fileExtension.equals(".xlsx")) {
            excelWorkbook = new XSSFWorkbook(inputStream);
        } else if (fileExtension.equals(".xls")) {
            excelWorkbook = new HSSFWorkbook(inputStream);
        }
        Sheet excelSheet = excelWorkbook.getSheet(SheetName);
        return excelSheet;
    }

    public static void execute(Properties properties, String operation, String objectName, String objectType, String value) throws InterruptedException {
        switch (operation.toUpperCase()){
            case "CLICK":
                KeywordDrivenFramework.driver.findElement(getObjectBy(properties,objectName,objectType)).click();
                break;
            case "MINIMIZESIZE":
                KeywordDrivenFramework.driver.manage().window().setSize(new Dimension(600,600));
                break;
            case "MAXIMIZE":
                KeywordDrivenFramework.driver.manage().window().maximize();
                break;
            case "SENDKEYS":
                KeywordDrivenFramework.driver.findElement(getObjectBy(properties,objectName,objectType)).sendKeys(value);
                break;
            case "OPENBROWSER":
                System.setProperty("webdriver.edge.driver","./src/test/resources/srcDriver/msedgedriver.exe");
                KeywordDrivenFramework.driver=new EdgeDriver();
                KeywordDrivenFramework.driver.manage().window().maximize();
                break;
            case "CLOSEBROWSER":
                KeywordDrivenFramework.driver.quit();
                break;
            case "GETVALUE":
                KeywordDrivenFramework.driver.findElement(getObjectBy(properties,objectName,objectType)).getText();
                break;
            case "IMPLICITWAIT":
                KeywordDrivenFramework.driver.manage().timeouts().implicitlyWait(Integer.parseInt(value), TimeUnit.SECONDS);
                break;
            case "EXPLICITWAIT":
                Thread.sleep(Long.parseLong(value));
                break;
            case "HOVEROVER":
                Actions action=new Actions(KeywordDrivenFramework.driver);
                WebElement element=KeywordDrivenFramework.driver.findElement(getObjectBy(properties,objectName,objectType));
                action.moveToElement(element).perform();
                break;
            case "FLUENTWAIT":
                FluentWait wait = new FluentWait(KeywordDrivenFramework.driver);
                wait.withTimeout(Integer.parseInt(value),TimeUnit.SECONDS);
                break;
            case "CLICKANDHOLD":
                action=new Actions(KeywordDrivenFramework.driver);
                element=KeywordDrivenFramework.driver.findElement(getObjectBy(properties,objectName,objectType));
                action.clickAndHold(element).build().perform();
                break;
            case "SETWINDOWSSIZE":
                String[] arr=value.split(":");
                int x=Integer.parseInt(arr[0]);
                int y=Integer.parseInt(arr[1]);
                KeywordDrivenFramework.driver.manage().window().setSize(new Dimension(x,y));
                break;
            case "ASSERT":
                Assert.assertEquals(KeywordDrivenFramework.driver.findElement(getObjectBy(properties,objectName,objectType)).getText(),value);
                break;
            case "GETCOOKIES":
                KeywordDrivenFramework.driver.manage().getCookies();
                break;
            case "SELECTBYINDEX":
                Select select=new Select(KeywordDrivenFramework.driver.findElement(getObjectBy(properties,objectName,objectType)));
                select.selectByIndex(Integer.parseInt(value));
                break;
            case "SETPOSITION":
                arr=value.split(":");
                x=Integer.parseInt(arr[0]);
                y=Integer.parseInt(arr[1]);
                KeywordDrivenFramework.driver.manage().window().setPosition(new Point(x,y));
                break;
            case "SELECTBYVALUE":
                select=new Select(KeywordDrivenFramework.driver.findElement(getObjectBy(properties,objectName,objectType)));
                select.selectByValue(value);
                break;
            case "JSCLICKBYID":
                JavascriptExecutor executor=(JavascriptExecutor) KeywordDrivenFramework.driver;
                executor.executeScript("document.getElementByID("+objectName+").click();");
                break;
            case "JSCHANGEVALUEBYID":
                executor=(JavascriptExecutor) KeywordDrivenFramework.driver;
                executor.executeScript("document.getElementByID("+objectName+").value="+value+";");
                break;
            case "DRAG":
                elementMemory=KeywordDrivenFramework.driver.findElement(getObjectBy(properties,objectName,objectType));
                break;
            case "DROP":
                action=new Actions(KeywordDrivenFramework.driver);
                action.dragAndDrop(elementMemory,KeywordDrivenFramework.driver.findElement(getObjectBy(properties,objectName,objectType)));
                break;
        }

    }

    public static By getObjectBy(Properties properties, String objectName, String objectType){
        if (objectType.equalsIgnoreCase("XPATH")) {
            return By.xpath(properties.getProperty(objectName));
        }
        else if (objectType.equalsIgnoreCase("CLASSNAME")) {
            return By.className(properties.getProperty(objectName));
        }
        else if (objectType.equalsIgnoreCase("NAME")) {
            return By.name(properties.getProperty(objectName));
        }
        else if (objectType.equalsIgnoreCase("CSS")) {
            return By.cssSelector(properties.getProperty(objectName));
        }
        else if (objectType.equalsIgnoreCase("LINK")) {
            return By.linkText(properties.getProperty(objectName));
        }
        else if (objectType.equalsIgnoreCase("PARTIALLINK")) {
            return By.partialLinkText(properties.getProperty(objectName));
        }
        else if(objectType.equalsIgnoreCase("ID")){
            return By.id(properties.getProperty(objectName));
        }
        return null;
    }

}
