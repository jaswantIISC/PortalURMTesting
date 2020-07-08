package PortalURM.Impl;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import PortalURM.Interface.URMCommands;
import PortalURM.Test.WebDriverSetup;
import PortalURM.utils.AlertHandler;
import PortalURM.utils.User;

import net.sf.json.JSONObject;

public class UserManagementImplementation extends WebDriverSetup implements URMCommands{
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
    Date date = new Date(); 
    String currentTime=formatter.format(date);
    
	public void createCommand(WebDriver driver, JSONObject user, JSONObject role ) throws InterruptedException {
		System.out.println("[UserManagementImplementation.java]:[createUserCommand method]: starts");	
		
		WebElement userCommandDiv = driver.findElement(By.id("userManagementCommand"));
		userCommandDiv.click();
		
		WebElement createUserCommand = driver.findElement(By.id("Create User"));		
		
		Thread.sleep(2000);
		createUserCommand.click();		
		Thread.sleep(3000);
		
		WebElement subscriber = driver.findElement(By.id("subscriber"));
		Select subsDropDwon = new Select(subscriber);
		subsDropDwon.selectByVisibleText(user.getString("subscriber"));
		System.out.println(" subscriber Selected : "+ user.getString("subscriber"));
		//AlertHandler.alert(driver);

		WebElement name = driver.findElement(By.id("testerName"));
		System.out.println(" name : "+ user.getString("name"));
		name.sendKeys(user.getString("name"));
			
		WebElement company = driver.findElement(By.id("company"));
		System.out.println(" company : "+ user.getString("company"));
		company.sendKeys(user.getString("company"));
		
		WebElement emailId = driver.findElement(By.id("emailId"));
		System.out.println(" emailId : "+ user.getString("emailId"));
		emailId.sendKeys(user.getString("emailId"));
		
		WebElement phone = driver.findElement(By.id("phone"));
		System.out.println(" phone : "+ user.getString("phone"));
		phone.sendKeys(user.getString("phone"));
		
		WebElement roleElement = driver.findElement(By.id("role"));
		Select roleDropDwon = new Select(roleElement);
		System.out.println(" role : "+ user.getString("role"));
		roleDropDwon.selectByValue(user.getString("role"));
			
		// capturing screenShot before submitting create user form
		File beforeSubmit= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the  screenshot to desired location using copyFile //method
			FileUtils.copyFile(beforeSubmit, new File("target/PortalURM/resources/screenShot/all/beforeCreateUserSubmit.png"));
		}	 
		catch (IOException e){
		  System.out.println(e.getMessage());		 
		 }
		
		WebElement submit = driver.findElement(By.id("create-user-form"));
		submit.click();
		Thread.sleep(3000);
		
		WebElement res = driver.findElement(By.className("alert"));
		
	// capturing screenShot After submitting create user form
		File afterSubmit= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			if (res.getText().contains("Success!")) {
				FileUtils.copyFile(afterSubmit, new File(
						"target/PortalURM/resources/screenShot/passedTest/createUser/"+username+"_"+currentTime+".png"));
				System.out.println(" Test cases paseed user creation: " + res.getText());

			} else {
				FileUtils.copyFile(afterSubmit, new File(
						"target/PortalURM/resources/screenShot/failedTest/createUser/"+username+"_"+currentTime+".png"));
				System.out.println(" Test cases failed user creation: " + res.getText());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
			
		System.out.println("[UserManagementImplementation.java]:[createUserCommand method]: Ends");	
		
	}
	

	public void viewCommand(WebDriver driver,  JSONObject user) throws InterruptedException {
		System.out.println(" inside viewUser Command method");	
		//currentTime= formatter.format(date);
		
		WebElement userCommandDiv = driver.findElement(By.id("userManagementCommand"));
		userCommandDiv.click();
		
		WebElement viewUserCommand = driver.findElement(By.id("View User"));		

		viewUserCommand.click();	
		Thread.sleep(2000);
		
		// capturing screenShot After submitting create user form
		File viewUser= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(viewUser, new File(
					"target/PortalURM/resources/screenShot/passedTest/viewUser/"+username+"_"+currentTime+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(" currentUser.getString('name'): "+user.getString("name"));

	//	WebElement viewRoleLink = driver.findElement(By.xpath("//tr/td[contains(text(),'"+ user.getString("name")+"')]/following-sibling::td/a"));
		
		//System.out.println(" tableText selected for macthed:: "+ trElement.getText());
		
		//WebElement viewRoleLink = trElement.findElement(By.className("view-role"));
		//WebElement viewRoleLink = driver.findElement(By.className("view-role"));
		
	//	System.out.println(" tableText selected for macthed:: "+ viewRoleLink.getText());
	//	viewRoleLink.click();
		
		//WebDriverWait wait = new WebDriverWait(driver, 4);
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("viewRoleModal")));
		
		
	}
	
	//*[@id="user-table"]/tbody/tr/td[2]/a
	public void deleteCommand(WebDriver driver, List<String> userList) throws InterruptedException {
		System.out.println(" inside deleteUserCommand method");	
		//currentTime= formatter.format(date);
		String responseText="";
		
		WebElement userCommandDiv = driver.findElement(By.id("userManagementCommand"));
		userCommandDiv.click();
		
		WebElement deleteUserCommand = driver.findElement(By.id("Delete User"));		
		
		Thread.sleep(1000);
		deleteUserCommand.click();	
		
	//	AlertHandler.alert(driver);
		
		// capturing screenShot After submitting create user form
		File testCase = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(testCase,
						new File(
								"target/PortalURM/resources/screenShot/testCases/deleteUser-"
										+ username + "_" + currentTime + ".png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//AlertHandler.alert(driver);
		
		//WebDriverWait wait = new WebDriverWait(driver, 3);
		WebElement deleteButton = driver.findElement(By.id("submit-delete-command"));
		//wait.until(ExpectedConditions.visibilityOf(deleteButton));
			
		String userToDelete = userList.get(0);
		System.out.println(" user to be deleted :: "+ userToDelete);	
			
		//WebElement tableText = driver.findElement(By.cssSelector("td:contains('"+userDeleted+"')"));
		WebElement tableText = driver.findElement(By.xpath("//td[contains(text(),'"+userToDelete+"')]"));
		System.out.println(" tableText selected for macthed:: "+ tableText.getText());
		
		tableText.click();
		deleteButton.click();
		
		AlertHandler.alert(driver);
		responseText = AlertHandler.alert(driver);
			
		// capturing screenShot After submitting create user form
		File viewUser = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			if(responseText.contains("DELTED SUCCESSFULLY!")){
				FileUtils.copyFile(viewUser,
						new File(
								"target/PortalURM/resources/screenShot/passedTest/deleteUser/"
										+ username + "_" + currentTime + ".png"));
				System.out.println(" Delete User Command Test Passed! ");
			}else{
				FileUtils.copyFile(viewUser,
						new File(
								"target/PortalURM/resources/screenShot/failedTest/deleteUser/"
										+ username + "_" + currentTime + ".png"));
				System.out.println(" Delete User Command Test Passed! ");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	


}
