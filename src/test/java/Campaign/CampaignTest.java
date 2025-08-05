package Campaign;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import ObjectRepositories.Campaignpage;
import ObjectRepositories.Homepage;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;

@Listeners(ListenerUtility.ListenerImplementation.class)
public class CampaignTest extends BaseClass {

	@Test(groups = "smoke")
	public void toCreateCampaignWithMandatoryFieldsTest() throws IOException {

		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		String campname = eutil.toReadDataFromExcelFile("Campaign", 1, 2);
		String target = eutil.toReadDataFromExcelFile("Campaign", 1, 3);
		
		// create campaign
		Homepage hp = new Homepage(driver);
		hp.getCreateCampBtn().click();
		Campaignpage cp = new Campaignpage(driver);
		cp.getCampaignNameTF().sendKeys(campname);
		cp.getTargetTF().sendKeys(target);
		cp.getCreateCampSubmitBtn().click();
		
		// validation
		WebElement toastmsg = cp.getToastmsg();
		wutil.waitForVisibilityOfElement(driver, toastmsg);
		String msg = toastmsg.getText();
		Assert.assertTrue(msg.contains(campname));
		cp.getClosemsg().click();
	}

	@Test(groups = "regression")
	public void toCreateCampaignWithExpDateTest() throws IOException {

		PropertiesFileUtility putil = new PropertiesFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		String campname = eutil.toReadDataFromExcelFile("Campaign", 1, 2);
		String target = eutil.toReadDataFromExcelFile("Campaign", 1, 3);

		String daterequired = jutil.togetRequiredDate(30);

		// create campaign
		Homepage hp = new Homepage(driver);
		hp.getCreateCampBtn().click();
		Campaignpage cp = new Campaignpage(driver);
		cp.getCampaignNameTF().sendKeys(campname);
		cp.getTargetTF().sendKeys(target);

		WebElement expClosedate = cp.getDateTF();
		wutil.passInput(driver, expClosedate, daterequired);

		cp.getCreateCampSubmitBtn().click();

		// validation
		WebElement toastmsg = cp.getToastmsg();
		wutil.waitForVisibilityOfElement(driver, toastmsg);

		String msg = toastmsg.getText();
		Assert.assertTrue(msg.contains(campname));
		cp.getClosemsg().click();

	}

	@Test(groups = "smoke")
	public void toCreateCampaignWithStatusTest() throws IOException {

		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();

		String campname = eutil.toReadDataFromExcelFile("Campaign", 1, 2);
		String target = eutil.toReadDataFromExcelFile("Campaign", 1, 3);

		// create campaign
		Homepage hp = new Homepage(driver);
		hp.getCreateCampBtn().click();
		Campaignpage cp = new Campaignpage(driver);
		cp.getCampaignNameTF().sendKeys(campname);
		cp.getStatusTF().sendKeys("pass");
		cp.getTargetTF().sendKeys(target);
		cp.getCreateCampSubmitBtn().click();

		// validation
		WebElement toastmsg = cp.getToastmsg();

		wutil.waitForVisibilityOfElement(driver, toastmsg);
		String msg = toastmsg.getText();
		Assert.assertTrue(msg.contains(campname));
		cp.getClosemsg().click();

	}

}
