package PortalURM.Impl;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.xml.xpath.XPath;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import PortalURM.Interface.PortalCommands;
import PortalURM.Interface.URMCommands;
import PortalURM.Test.WebDriverSetup;
import PortalURM.utils.AlertHandler;
import PortalURM.utils.OTPReader;
import PortalURM.utils.User;

import net.sf.json.JSONObject;

public class PortalCommandsImplementation extends WebDriverSetup implements PortalCommands{
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
    Date date = new Date(); 
    String currentTime=formatter.format(date);
    
    
	@Override
	// on successful validation user will be logged in to Portal URM and redirected to URM modules (product page)
	public String loginSuccess(WebDriver driver, String user, String password) throws InterruptedException{
		System.out.println(" [PortalCommandsImplementation.java][loginSuccess()]:  Website title = "+ driver.getTitle()+ "| url = "+ driver.getCurrentUrl());
		
		//2.  After navigating to URM Login credentials are required to login into URM.
		WebElement username = driver.findElement(By.id("login-id"));		
		WebElement pass = driver.findElement(By.id("login-pwd"));		
		WebElement loginBtn = driver.findElement(By.id("login-btn"));
		
		username.clear();
		username.sendKeys(user);
		
		pass.clear();
		pass.sendKeys(password);
		
		Thread.sleep(1000);

		// capturing screenShot After submitting create user form
		File loginUrm = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(loginUrm,
					new File(
							"target/PortalURM/resources/screenShot/testCases/urmLogin-"
									+ "_" + currentTime + ".png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
			
		loginBtn.click();
		Thread.sleep(2000);	
		
		WebElement h2Element = driver.findElement(By.tagName("h2"));
		System.out.println("H2 page heading element : "+ h2Element.getText());
		String loginStatus ;
		
		if(h2Element.getText().equals("Password Reset")) {
			loginStatus = "FIRST_TIME_LOGIN";			
		}else{
			goToURMModule(driver);	
			loginStatus = "SECOND_TIME_LOGIN";
		}
			
		System.out.println(" [PortalCommandsImplementation.java][loginSuccess()]: End");
		return loginStatus;
	}
	
	
	@Override
	public void passwordChange(WebDriver driver, String passwordToSet, String currentPassword) throws InterruptedException {
		System.out.println("[PortalCommandsImplementation.java][ passwordChange() ()]: Start");

		WebElement currentPasswordElem = driver.findElement(By.id("current-pwd"));
		currentPasswordElem.clear();
		currentPasswordElem.sendKeys(currentPassword);
		
		WebElement newPasswordElem = driver.findElement(By.id("new-pwd"));
		newPasswordElem.clear();
		newPasswordElem.sendKeys(passwordToSet);
		
		WebElement confirmPasswordElem = driver.findElement(By.id("confirm-pwd"));
		confirmPasswordElem.clear();
		confirmPasswordElem.sendKeys(passwordToSet); //password-reset
		
		WebElement passwordResetBtn = driver.findElement(By.id("password-reset"));
		passwordResetBtn.click();
		
		Thread.sleep(3000);
		
		WebElement logoutBtn = driver.findElement(By.xpath("//a[contains(text(),'log out')]"));
		logoutBtn.click();
		System.out.println("[PortalCommandsImplementation.java][ passwordChange() ()]: END");
	}

	
	@Override
	public void goToURMModule(WebDriver driver) throws InterruptedException {	
		System.out.println("[PortalCommandsImplementation.java][ goToURMModule() ()]: Start");
		System.out.println("Current page title :: "+ driver.getTitle()+ " url : "+ driver.getCurrentUrl());		
		
		WebElement urmModule = driver.findElement(By.xpath("//a[text()= 'URM']"));	
				
		Thread.sleep(1000);	
		urmModule.click();
		Thread.sleep(1000);
		
		String parent = driver.getWindowHandle();		
		System.out.println(" parent window :: "+ parent);		
		Set<String> tab_handles = driver.getWindowHandles();
	    
		//int count = tab_handles.size();	    
		//System.out.println("tab size "+ count);
		
		for(String child : tab_handles){
			
			if(!parent.equalsIgnoreCase(child)){								
				driver.switchTo().window(child);
			}
		}
	
		String ModulePage = driver.getCurrentUrl();		
		String expectedPage = "https://192.168.105.209:9543/urm/module";
		
		System.out.println("Current page title :: "+ driver.getTitle()+ " url : "+ driver.getCurrentUrl());
		
		if(ModulePage.equalsIgnoreCase(expectedPage)){			
			System.out.println(" TEST : PASSED | URM dashboard navigatation successful ");
		}
		else{			
			System.out.println(" TEST : FAILED | | URM dashboard navigatation failed ");
		}
		
		assert ModulePage.equalsIgnoreCase(expectedPage);
		System.out.println("[PortalCommandsImplementation.java][ goToURMModule() ()] : End ");	
	}
	
	
	@Override
	// This is the case where login attempts fails because of not passing the validation on inputs
	// This function is meant for LoginTest cases (Login use cases) 
	public WebDriver loginUseCase(WebDriver driver, String user, String password) throws InterruptedException{
		System.out.println("[PortalCommandsImplementation.java][urmLogin()]:  Website title = "+ driver.getTitle()+ "| url =  "+ driver.getCurrentUrl());
		System.out.println("user name  : "+ user+" and password: "+ password);
			
		//2.  After navigating to URM Login credentials are required to login into URM.
		WebElement username = driver.findElement(By.id("login-id"));		
		WebElement pass = driver.findElement(By.id("login-pwd"));		
		WebElement loginBtn = driver.findElement(By.id("login-btn"));		
		List<WebElement> errorList = driver.findElements(By.className("error")) ;
		
		username.clear();
		username.sendKeys(user);
		
		pass.clear();
		pass.sendKeys(password);
		
		Thread.sleep(1000);
	
		// capturing screenShot After submitting create user form
		File loginUrm = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(loginUrm,
					new File(
							"target/PortalURM/resources/screenShot/testCases/urmLogin-"
									+ "_" + currentTime + ".png"));
	
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		// 3. Login to URM....
		loginBtn.click();
		Thread.sleep(2000);
		
		boolean isErrorDetected = false;		
		System.out.println(" errorList : "+ errorList );
		
		for(WebElement error : errorList){
			System.out.println(" error text : "+ error.getText() );	
			if(!error.getText().isEmpty()){
				isErrorDetected=true;
			}
		}
		System.out.println(" isErrorDetected : "+ isErrorDetected );	
		
		if(isErrorDetected){
			File testCase = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
					FileUtils.copyFile(testCase,
							new File(
									"target/PortalURM/resources/screenShot/failedTest/Login/urmLogin-"
											+ "_" + currentTime + ".png"));
					System.out.println(" login user Command Test Failed! ");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			File testCase = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
					FileUtils.copyFile(testCase,
							new File(
									"target/PortalURM/resources/screenShot/passedTest/Login/urmLogin-"
											+ "_" + currentTime + ".png"));
					System.out.println(" login user Command Test Passed! ");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		WebDriverWait wait = new WebDriverWait(driver, 20);	
		wait.until(ExpectedConditions.alertIsPresent());	
		driver.switchTo().alert().accept();		
		return driver;
	}
		
	@Override
	public void logout(WebDriver driver, JSONObject userToCreate) throws InterruptedException {
		WebElement userProfile = driver.findElement(By.className("user-profile"));
		userProfile.click();
		
		//WebDriverWait wait = new WebDriverWait(driver, 2);		
		WebElement logoutElem = driver.findElement(By.xpath("//li/a[contains(text(), 'Logout')]"));		
		////*[@id="wrapper"]/nav/ul/li[2]/ul/li[4]/a/text()
		//wait.until(ExpectedConditions.visibilityOf(logoutElem));	
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='wrapper']/nav/ul/li[2]/ul/li[4]/a/")));	
		Thread.sleep(2000);
		logoutElem.click();
	}

	@Override
	public void forgotPassword(WebDriver driver, String passwordToSet, String emailId, String emailPassword) throws InterruptedException {
		System.out.println("[PortalCommandsImplementation][ ForgotPassword() ()]: Start");
		System.out.println("Current page title :: "+ driver.getTitle()+ " | login url : "+ driver.getCurrentUrl());		
		
		WebElement forgotPasswordElement = driver.findElement(By.xpath("//*[@id=\"Login\"]/div[4]/a"));	
		System.out.println("forgotPasswordElement text  : "+ forgotPasswordElement.getText());				

		Thread.sleep(1000);	
		forgotPasswordElement.click();
		Thread.sleep(1000);
		
		System.out.println("Current page title :: "+ driver.getTitle()+ " | forgotPasswordReq url : "+ driver.getCurrentUrl());		
		
		WebElement loginId = driver.findElement(By.id("login-id"));
		loginId.clear();
		loginId.sendKeys(emailId);
				
		WebElement loginBtn = driver.findElement(By.id("forgot-password"));	
		loginBtn.click();
		
		System.out.println("Current page title :: "+ driver.getTitle()+ " | forgotPasswordAuthentication_1 url : "+ driver.getCurrentUrl());	
		String newUrl = driver.getCurrentUrl();
		
		if(newUrl.contains("forgotPasswordAuthentication_1")){
			System.out.println(" Redirected to forgotPasswordAuthentication_1 page !");
			System.out.println(" ******Starts Reading Gmail OTP ***************");
			
			String subject = "Password Reset Request";
			String OTP = OTPReader.read( emailId, emailPassword, subject);
			System.out.println("Inside forgotPasword() OTP read from Mail : "+ OTP);
			
			WebElement otpWebElement = driver.findElement(By.id("password-reset-pin"));
			otpWebElement.clear();
			otpWebElement.sendKeys(OTP);
			
			WebElement forgotPasswordOTPSubmit = driver.findElement(By.id("forgot-password"));
			forgotPasswordOTPSubmit.click();
			
			Thread.sleep(2000);
			newUrl = driver.getCurrentUrl();
			System.out.println(" current URL : "+ newUrl);
			
			if(newUrl.contains("forgotPasswordAuthentication_2")){
				WebElement newPasswordElem = driver.findElement(By.id("new-pwd"));
				newPasswordElem.clear();
				newPasswordElem.sendKeys(passwordToSet);
				
				WebElement confirmPasswordElem = driver.findElement(By.id("confirm-pwd"));
				confirmPasswordElem.clear();
				confirmPasswordElem.sendKeys(passwordToSet); //password-reset
				
				WebElement passwordResetBtn = driver.findElement(By.id("password-reset"));
				passwordResetBtn.click();
				
			}else {
				System.out.println("FORGOT_PASSWORD_PIN_AUTHENTICATION_ERROR !");
			}
			
		}else {
			System.out.println("FORGOT_PASSWORD_EMAIL_AUTHENTICATION_ERROR !");
		}
		
	}

	
	
	
}
