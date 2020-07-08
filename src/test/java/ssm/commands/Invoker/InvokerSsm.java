package ssm.commands.Invoker;

import java.awt.AWTException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class InvokerSsm {

	public static WebDriver driver;
	
	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		InvokerSsm.driver = driver;
	}

	@Before    
	public void setUp(){
		
		ChromeOptions profile = new ChromeOptions();
		
		profile.setAcceptInsecureCerts(true);
		
		profile.addArguments("--ignore-certificate-errors");
				
		System.setProperty("webdriver.chrome.driver",
				"utils/lib/chromedriver_linux64/chromedriver");
		
		driver = new ChromeDriver(profile);

	}

	@Test
	public void testDriver() throws InterruptedException, AWTException{

		
		driver.get("https://192.168.5.125:9543/");

		WebDriverWait wait = new WebDriverWait(driver,60);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("LOGIN")));

		WebElement LocationLink = driver.findElement(By.linkText("LOGIN"));
		System.out.println(LocationLink.getAttribute("href"));
		LocationLink.click();

/*		String URL = LocationLink.getAttribute("href");
		driver.get (URL);
*/
		
		ArrayList<String> windowHandles = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(1));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String actualUrl="https://192.168.5.125:9543/urm/login"; 
		String expectedUrl= driver.getCurrentUrl(); 

		System.out.println(expectedUrl);

		Assert.assertEquals(expectedUrl,actualUrl);
		// assert equals(expectedUrl,actualUrl);

		if(actualUrl.equalsIgnoreCase(expectedUrl)) { 

			System.out.println("Test passed");
		} 
		else 
		{ 
			System.out.println("Test failed");
		} 


		driver.findElement(By.id("login-id")).click();
		driver.findElement(By.id("login-id")).sendKeys("super@cdot.in");
		
		WebElement element = driver.findElement(By.id("login-id"));
		String text = element.getAttribute("value");
		System.out.println(text);
		
		driver.findElement(By.id("login-pwd")).click();
		driver.findElement(By.id("login-pwd")).clear();
		driver.findElement(By.id("login-pwd")).sendKeys("super123");
		
		WebElement element2 = driver.findElement(By.id("login-pwd"));
		String text2 = element2.getAttribute("value");
		System.out.println(text2);
		
		//driver.findElement(By.id("login-btn")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-btn")));

		WebElement element4 =driver.findElement(By.id("login-btn"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element4);


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("section-header")));

		System.out.println(driver.findElement(By.className("section-header")).getText());  


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SSM-logo")));
		System.out.println(driver.findElement(By.id("SSM-logo")).getTagName());



		System.out.println(driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/div/h2/a")).getText());

		WebElement ssm = driver.findElement(By.xpath("/html/body/main/section/div/div/div[1]/div/h2/a"));

		WebDriverWait wait2 = new WebDriverWait(driver,10);
		wait2.until(ExpectedConditions.elementToBeClickable(ssm));


		WebElement element3 =driver.findElement(By.xpath("/html/body/main/section/div/div/div[1]/div/h2/a"));
		JavascriptExecutor executor2 = (JavascriptExecutor)driver;
		executor2.executeScript("arguments[0].click();", element3);


		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		ArrayList<String> windowHandles2 = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(windowHandles2.get(2));
		
		
		
		System.out.println(driver.getCurrentUrl()); 
		
		driver.manage().window().maximize();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("iframe-pageloader")));

		WebElement frame1 = driver.findElement(By.id("iframe-pageloader"));
		driver.switchTo().frame(frame1);
		
		CreateSubscriber createSubscriber = new CreateSubscriber();		
		String subscriber = createSubscriber.testDriver();
		
		DisplaySubscriber displaySubscriber = new DisplaySubscriber();
		displaySubscriber.testDriver(subscriber);
		
		CreateSSP createSSP = new CreateSSP();
		String ssp = createSSP.testDriver(subscriber);
		
		DisplaySSP displaySSP = new DisplaySSP();
		displaySSP.testDriver(subscriber, ssp);
				
		CreateSSN createSSN = new CreateSSN();
		createSSN.testDriver(subscriber, ssp);
				
		CreateSSAR createSSAR = new CreateSSAR();
		createSSAR.testDriver(subscriber);
		
		DisplaySSAR displaySSAR = new DisplaySSAR();
		displaySSAR.testDriver(subscriber);
		
//		LinkSSAR linkSSAR = new LinkSSAR();
//		linkSSAR.testDriver(subscriber, ssp);
	
		ConfigureNode configureNode = new ConfigureNode();
		configureNode.testDriver(subscriber, ssp);
		
		DIsplaySSN displaySSN = new DIsplaySSN();
		displaySSN.testDriver(subscriber, ssp);

		
	}
	
	@After  
	public void cleanUp()
	{
		driver.quit();
	}

}
