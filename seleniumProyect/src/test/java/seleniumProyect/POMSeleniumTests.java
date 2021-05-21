package seleniumProyect;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import seleniumProyect.Utilities.TestUtilities;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class POMSeleniumTests {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.edge.driver","../src/test/resources/srcDriver/msedgedriver.exe");
        driver=new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(TestUtilities.siteURL);
    }

    @Test
    public void Message(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement userText=driver.findElement(By.id("user-message"));
        userText.clear();
        userText.sendKeys(TestUtilities.getMessage());
        WebElement button=driver.findElement(By.xpath("//*[@id=\"get-input\"]/button"));
        button.click();
        String display=driver.findElement(By.id("display")).getText();
        assertEquals(TestUtilities.getMessage(),display);
    }


    @Test
    public void Sum() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement val1=driver.findElement(By.id("sum1"));
        val1.sendKeys(String.valueOf(TestUtilities.getValue1()));
        WebElement val2=driver.findElement(By.id("sum2"));
        val2.sendKeys(String.valueOf(TestUtilities.getValue2()));
        WebElement button=driver.findElement(By.xpath("//*[@id=\"gettotal\"]/button"));
        button.click();
        assertEquals(String.valueOf(TestUtilities.getValue1()+TestUtilities.getValue2()),driver.findElement(By.id("displayvalue")).getText());
    }
    @Test
    public void FormFull() {
        driver.get("https://www.seleniumeasy.com/test/input-form-demo.html");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement boxFirstName=driver.findElement(By.name("first_name"));
        boxFirstName.sendKeys("Eduardo");
        WebElement boxLastname=driver.findElement(By.name("last_name"));
        boxLastname.sendKeys("Castillo");
        WebElement boxEmail=driver.findElement(By.name("email"));
        boxEmail.sendKeys("eduardo@valuelabs.com");
        WebElement boxPhone=driver.findElement(By.name("phone"));
        boxPhone.sendKeys("(50)88395948");
        WebElement boxAddress=driver.findElement(By.name("address"));
        boxAddress.sendKeys("Alajuela");
        WebElement boxCity=driver.findElement(By.name("city"));
        boxCity.sendKeys("Alajuela");
        Select dropdown=new Select(driver.findElement(By.name("state")));
        dropdown.selectByVisibleText("Colorado");
        WebElement boxZipCode=driver.findElement(By.name("zip"));
        boxZipCode.sendKeys("20101");
        WebElement boxWebSite=driver.findElement(By.name("website"));
        boxWebSite.sendKeys("www.google.com");
        WebElement radioButton=driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/section/form/fieldset/div[10]/div/div[1]/label/input"));
        radioButton.click();
        WebElement boxProyectDescription=driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/section/form/fieldset/div[11]/div/div/textarea"));
        boxProyectDescription.sendKeys("Project description");
        WebElement buttonSubmit=driver.findElement(By.xpath("//*[@id=\"contact_form\"]/fieldset/div[13]/div/button"));
        buttonSubmit.click();
    }


    @After
    public void tearDown2() {
        driver.quit();
    }
}
