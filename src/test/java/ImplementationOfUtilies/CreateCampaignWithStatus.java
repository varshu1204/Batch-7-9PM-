package ImplementationOfUtilies;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import ObjectRepositories.Campaignpage;
import ObjectRepositories.Homepage;
import ObjectRepositories.LoginPage;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;

public class CreateCampaignWithStatus extends BaseClass {

	@Test
	public void toCreateCampaignWithStatus() throws IOException {

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

		if (msg.contains(campname)) {
			System.out.println("campaign created");
		}

		else {
			System.out.println("campaign not created");
		}
		cp.getClosemsg().click();

	}

}
