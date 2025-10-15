package testNGPractice;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Assertion {

	@Test
	public void demo()
	{
		String expTitle=null;;
		
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.facebook.com/");
	
		String actTitle = driver.getTitle();
		//Assert.assertEquals(actTitle, expTitle);
	SoftAssert soft=new SoftAssert();
	soft.assertNull(expTitle);
		System.out.println("abc");
		System.out.println("zxcv");
		soft.assertAll();
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
