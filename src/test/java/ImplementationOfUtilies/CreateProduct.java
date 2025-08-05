package ImplementationOfUtilies;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import ObjectRepositories.Campaignpage;
import ObjectRepositories.Homepage;
import ObjectRepositories.LoginPage;
import ObjectRepositories.ProductPage;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;

public class CreateProduct extends BaseClass {

	@Test
	public void toCreateProduct() throws EncryptedDocumentException, IOException, InterruptedException {


		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();

		String prodname = eutil.toReadDataFromExcelFile("Product", 1, 0);
		String quantity1 = eutil.toReadDataFromExcelFile("Product", 1, 1);
		String price1 = eutil.toReadDataFromExcelFile("Product", 1, 2);

		// enter details

		Homepage hp = new Homepage(driver);
		hp.getProduct().click();
		ProductPage pp = new ProductPage(driver);
		pp.getAddProdBtn().click();

		pp.getProdName().sendKeys(prodname + jutil.togetRandomNumber());

		WebElement categorydropdown = pp.getProdCategory();
		// Dropdown 1

		wutil.select(categorydropdown, 3);
		WebElement quantity = pp.getQuantity();
		quantity.clear();
		quantity.sendKeys(quantity1);

		WebElement price = pp.getPrice();
		price.clear();
		price.sendKeys(price1);
		// DropDown 2
		WebElement vendordropdown = pp.getVendor();
		wutil.select(vendordropdown, 1);
		pp.getAddProdSubmitBtn().click();
		Thread.sleep(2000);
		Campaignpage cp = new Campaignpage(driver);
		cp.getClosemsg().click();

	}

}
