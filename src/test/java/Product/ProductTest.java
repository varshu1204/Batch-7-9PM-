package Product;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import ObjectRepositories.Campaignpage;
import ObjectRepositories.Homepage;
import ObjectRepositories.ProductPage;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;

public class ProductTest extends BaseClass{

	@Test(groups = "smoke")
	public void toCreateProductTest() throws EncryptedDocumentException, IOException, InterruptedException {


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
