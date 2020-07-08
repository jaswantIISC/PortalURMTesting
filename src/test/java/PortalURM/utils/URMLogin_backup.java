package PortalURM.utils;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class URMLogin_backup {
	private WebDriver driver;
	
	
	//@SuppressWarnings("deprecation")
	@Before
	public void setUp(){
		
		// SSL Certificate Error Handling in Chrome....
		ChromeOptions profile = new ChromeOptions();// DesiredCapabilities.chrome();
		
		// Set the ACCEPT_INSECURE_CERT variable to true
		profile.setAcceptInsecureCerts(true); 
		
		profile.addArguments("--ignore-certificate-errors");
		
		// Set the driver path 
		System.setProperty("webdriver.chrome.driver", "/Users/jaswantmeena/Documents/workspace/jars/Drivers/Chrome-81.0/chromedriver");
		
		// Open browser with capability
		driver = new ChromeDriver(profile);
		
		String url= "https://192.168.105.209:9543/urm/login";
		
		driver.get(url);
		
		System.out.println("Website title :: "+ driver.getTitle()+ " url : "+ driver.getCurrentUrl());
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	
	}
	
	@Test
	public void TestDriver() {
		System.out.println(" inside TestDriver() Website title :: "+ driver.getTitle()+ " url : "+ driver.getCurrentUrl());
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		//2.  After navigating to URM Login credentials are required to login into URM.
		WebElement username = driver.findElement(By.id("login-id"));
		
		WebElement pass = driver.findElement(By.id("login-pwd"));
		
		WebElement loginBtn = driver.findElement(By.id("login-btn"));
		
		username.sendKeys("super@cdot.in");
		
		pass.sendKeys("super123");
		
		// 3. Navigating to URM user Dashboard "product.jsp" 
		loginBtn.click();
			
		//WebElement userEmailId = driver.findElement(By.xpath("//span[@class='user-emailId']"));
		
		wait = new WebDriverWait(driver, 20);
		//wait.until(ExpectedConditions.visibilityOf(userEmailId));
		wait.until(ExpectedConditions.alertIsPresent());
	
		driver.switchTo().alert().accept();
		
		String currentPage = driver.getCurrentUrl();
		
		String expectedPage = "https://192.168.105.209:9543/urm/login";
		
		assert currentPage.equalsIgnoreCase(expectedPage);
		
		if(currentPage.equalsIgnoreCase(expectedPage)){
			System.out.println(" TEST 2: PASSED | login successful ");
		}
		else{
			System.out.println(" TEST 2: FAILED | | login failed ");
		}
		
	}
	
	@After
	public void cleanUp(){
		//driver.quit();
	}
}
