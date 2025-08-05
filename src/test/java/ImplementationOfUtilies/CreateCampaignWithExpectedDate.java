package ImplementationOfUtilies;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import BaseTest.BaseClass;
import ObjectRepositories.Campaignpage;
import ObjectRepositories.Homepage;
import ObjectRepositories.LoginPage;
import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;

public class CreateCampaignWithExpectedDate extends BaseClass {

	@Test
	public void toCreateCampaignWithExpDate() throws IOException {

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

		if (msg.contains(campname)) {
			System.out.println("campaign created");
		}

		else {
			System.out.println("campaign not created");
		}
		cp.getClosemsg().click();

	}

}
