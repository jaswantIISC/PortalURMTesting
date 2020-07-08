package PortalURM.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OTPReader {


	public static String read( String emailId, String emailPassword, String subject) throws InterruptedException {
		System.out.println("[PortalCommandsImplementation.java][ passwordChange() ()]: Start");
		// WebDriver prevDriver = driver;
		WebDriver  driver;
		// DesiredCapabilities.chrome();		
		ChromeOptions profile = new ChromeOptions();
		// Set the ACCEPT_INSECURE_CERT variable to true
		profile.setAcceptInsecureCerts(true); 
		profile.addArguments("--ignore-certificate-errors");

		// Set the driver path 
		System.setProperty("webdriver.chrome.driver", "utils/lib/chromedriver_linux64/chromedriver");
		
		// Open browser with capability
		driver = new ChromeDriver(profile);	
		String url= "http://webmail/cgi-bin/openwebmail/openwebmail.pl";
		driver.get(url);		
		System.out.println("Website title = "+ driver.getTitle()+ " |  url = "+ driver.getCurrentUrl());		
		
		//driver.manage().window().maximize();		
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		WebElement emailIdElement = driver.findElement(By.name("loginname"));
		emailIdElement.clear();
		emailIdElement.sendKeys(emailId);		
		
		WebElement passwordElement = driver.findElement(By.name("password"));
		passwordElement.clear();
		passwordElement.sendKeys(emailPassword);
		
		WebElement loginbutton = driver.findElement(By.name("loginbutton"));
		loginbutton.click();
		
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(1000);
		System.out.println("Inside Webmail Website title = "+ driver.getTitle()+ " |  url = "+ driver.getCurrentUrl());
		
		WebElement webMailSubject = driver.findElement(By.xpath("//a[contains(text(), '"+subject+"')]"));		
		System.out.println(" mail subject heading href: "+ webMailSubject.getAttribute("href"));
		webMailSubject.click();
				
		WebElement otpElement = driver.findElement(By.id("pin"));
		System.out.println(" otpReader OTP : "+ otpElement.getText());
	
		return otpElement.getText();
	}
		
		

}
