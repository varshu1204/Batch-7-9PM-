package BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import ObjectRepositories.Homepage;
import ObjectRepositories.LoginPage;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver = null;
	public static WebDriver sdriver=null; //listener
	public PropertiesFileUtility putil = new PropertiesFileUtility();
	public WebDriverUtility wutil = new WebDriverUtility();

	@BeforeSuite(groups = {"smoke","regression"})
	public void beforeSuite() {
		System.out.println("DB connectivity open");
	}

	@Parameters("BROWSER")
	@BeforeClass(groups = {"smoke","regression"})
	public void beforeClass(String browser) throws IOException {
		String BROWSER=browser;
		//String BROWSER = putil.togetDataFromPropertiesFile("Browser");
		
		if (BROWSER.equals("Edge")) {
			
			driver = new EdgeDriver();
		} else if (BROWSER.equals("Chrome")) {
			ChromeOptions settings = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
		    prefs.put("profile.password_manager_leak_detection", false);
		    settings.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(settings);
		} else if (BROWSER.equals("Firefox")) {
			driver = new FirefoxDriver();
		}
		sdriver=driver; 
		System.out.println("launching browser");

	}

	@BeforeMethod(groups = {"smoke","regression"})
	public void beforeMethod() throws IOException {
		String URL = putil.togetDataFromPropertiesFile("Url");
		String USERNAME = putil.togetDataFromPropertiesFile("Username");
		String PASSWORD = putil.togetDataFromPropertiesFile("Password");
		driver.manage().window().maximize();
		wutil.waitForPagetoLoad(driver);
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.getUN().sendKeys(USERNAME);
		lp.getPW().sendKeys(PASSWORD);
		lp.getLoginBtn().click();

		System.out.println("login");
	}

	@AfterMethod(groups = {"smoke","regression"})
	public void afterMethod() {
		Homepage hp = new Homepage(driver);
		WebElement icon = hp.getUserIcon();
		wutil.mouseHover(driver, icon);
		WebElement logout = hp.getLogoutBtn();
		wutil.clickOnWebElement(driver, logout);

		System.out.println("logout");
	}

	@AfterClass(groups = {"smoke","regression"})
	public void afterClass() {
		driver.quit();
		System.out.println("closing browser");
	}

	@AfterSuite(groups = {"smoke","regression"})
	public void afterSuite() {
		System.out.println("DB close");
	}

}
