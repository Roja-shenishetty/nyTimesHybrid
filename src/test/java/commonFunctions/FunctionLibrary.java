package commonFunctions;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class FunctionLibrary {
	public static WebDriver driver;
	public static Properties conpro;
	
	
	public static WebDriver startBrowser() throws Throwable
	{
	  conpro=new Properties();
	  conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			Reporter.log("browser value is not matching");
			
		}
		
		return driver;
		
	}
	
	
	public static void openUrl()
	{
		driver.get(conpro.getProperty("url"));
	}
	
	
	public static void waitForElement(String ltype,String lvalue,String Tdata) throws Throwable
	{
		WebDriverWait mywait=new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(Tdata)));
		
		if(ltype.equalsIgnoreCase("xpath"))
		{
			Thread.sleep(2000);
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(lvalue)));
		}
		if(ltype.equalsIgnoreCase("name"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(lvalue)));
		}	
		if(ltype.equalsIgnoreCase("id"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(lvalue)));
		}
		
	}
	
	
	public static void typeAction(String ltype,String lvalue,String Tdata)
	{
		if(ltype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(lvalue)).clear();
			driver.findElement(By.xpath(lvalue)).sendKeys(Tdata);
		}
		if(ltype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(lvalue)).clear();
			driver.findElement(By.name(lvalue)).sendKeys(Tdata);
		}
		if(ltype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(lvalue)).clear();
			driver.findElement(By.id(lvalue)).sendKeys(Tdata);
		}

	}
	
	
	public static void clickAction(String ltype,String lvalue) throws Throwable
	{
		if(ltype.equalsIgnoreCase("xpath"))
		{
			Thread.sleep(3000);
			driver.findElement(By.xpath(lvalue)).click();
		}
		if(ltype.equalsIgnoreCase("name"))
		{
			Thread.sleep(3000);
			driver.findElement(By.name(lvalue)).click();
		}
		if(ltype.equalsIgnoreCase("id"))
		{
			Thread.sleep(3000);
			driver.findElement(By.id(lvalue)).sendKeys(Keys.ENTER);
		}
	}
	
	
	public static void validateTitle(String exp_title)
	{
		String act_title=driver.getTitle();
		try
		{
			Assert.assertEquals(act_title, exp_title,"title not matching");
		}catch(AssertionError a)
		{
			System.out.println(a.getMessage());
		}
	}
	
	
	public static void closeBrowser() throws Throwable
	{
		Thread.sleep(2000);
		driver.quit();
	}
	
	
	public static void dropDownAction(String ltype,String lvalue,String tdata)
	{
		if(ltype.equalsIgnoreCase("xpath"))
		{
			int value=Integer.parseInt(tdata);
			Select element=new Select(driver.findElement(By.xpath(lvalue)));
			element.selectByIndex(value);
		}
		if(ltype.equalsIgnoreCase("name"))
		{
			int value=Integer.parseInt(tdata);
			Select element=new Select(driver.findElement(By.name(lvalue)));
			element.selectByIndex(value);
		}
		if(ltype.equalsIgnoreCase("id"))
		{
			int value=Integer.parseInt(tdata);
			Select element=new Select(driver.findElement(By.id(lvalue)));
			element.selectByIndex(value);
		}
		
	}
	
	public static String generateDate()
	{
		Date date=new Date();
		DateFormat df=new SimpleDateFormat("yyyy_mm_dd");
		return df.format(date);
	}
	
	public static void moveToElement(String ltype,String lvalue)
	{
		Actions act=new Actions(driver);
		if(ltype.equalsIgnoreCase("xpath"))
		{
		
			act.moveToElement(driver.findElement(By.xpath(lvalue))).build().perform();
		}
		if(ltype.equalsIgnoreCase("id"))
		{
			act.moveToElement(driver.findElement(By.id(lvalue))).perform();
			
		}
		if(ltype.equalsIgnoreCase("name"))
		{
			act.moveToElement(driver.findElement(By.name(lvalue))).perform();
			
		}
		
	}
	
	public static void scrollToElement(String ltype,String lvalue)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		if(ltype.equalsIgnoreCase("xpath")) 
		{
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath(lvalue)));
		}
		if(ltype.equalsIgnoreCase("name")) 
		{
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.name(lvalue)));
		}
		if(ltype.equalsIgnoreCase("id")) 
		{
			js.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.id(lvalue)));
		}
}
	


}
