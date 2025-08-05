package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateProduct {

	public static void main(String[] args) throws InterruptedException, IOException {

		// properties
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\Commondata.properties");

		Properties prop = new Properties();
		prop.load(fis);

		String BROWSER = prop.getProperty("Browser");
		String URL = prop.getProperty("Url");
		String USERNAME = prop.getProperty("Username");
		String PASSWORD = prop.getProperty("Password");

		// actual script
		WebDriver driver = null;

		if (BROWSER.equals("Edge")) {

			driver = new EdgeDriver();
		} else if (BROWSER.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("Firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();

		Random ran = new Random();
		int randcount = ran.nextInt(500);

		// excel
		FileInputStream fis1 = new FileInputStream(".\\src\\test\\resources\\TestScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Product");
		String prodname = sh.getRow(1).getCell(0).getStringCellValue();
		String quantity1 = sh.getRow(1).getCell(1).getStringCellValue();
		String price1 = sh.getRow(1).getCell(2).getStringCellValue();

		// enter details
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Add Product')]")).click();

		driver.findElement(By.name("productName")).sendKeys(prodname + randcount);

		WebElement categorydropdown = driver.findElement(By.name("productCategory"));
		// Dropdown 1
		Select drop1 = new Select(categorydropdown);
		drop1.selectByValue("Electricals");
		WebElement quantity = driver.findElement(By.name("quantity"));
		quantity.clear();
		quantity.sendKeys(quantity1);

		WebElement price = driver.findElement(By.name("price"));
		quantity.clear();
		price.sendKeys(price1);
		// DropDown 2
		WebElement vendordropdown = driver.findElement(By.name("vendorId"));
		Select drop2 = new Select(vendordropdown);
		drop2.selectByValue("VID_007");
		driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();

		// logout
		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions act = new Actions(driver);
		act.moveToElement(icon).perform();
		WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		act.moveToElement(logout).click().perform();

		driver.quit();

	}

}
