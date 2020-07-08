package PortalURM.Impl;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import PortalURM.Interface.URMCommands;
import PortalURM.Test.WebDriverSetup;
import PortalURM.utils.AlertHandler;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RoleManagementImplementation extends WebDriverSetup implements URMCommands {
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
    Date date = new Date(); 
    String currentTime=formatter.format(date);
 
	public void createCommand(WebDriver driver, JSONObject user, JSONObject role) throws InterruptedException {
			System.out.println(" inside createRoleCommand method");	
			List<String> xpathList = (List<String>) role.get("xpath");
			
			WebElement roleCommandDiv = driver.findElement(By.id("roleManagementCommand"));
			roleCommandDiv.click();
			
			WebElement createRoleCommand = driver.findElement(By.id("Create Role"));		
			
			Thread.sleep(1000);
			createRoleCommand.click();		
			
			WebElement subscriber = driver.findElement(By.id("subscriberToRole"));
			Select subsDropDwon = new Select(subscriber);
			subsDropDwon.selectByVisibleText(user.getString("subscriber"));

			WebElement roleElement = driver.findElement(By.id("role"));		
			roleElement.sendKeys(role.getString("name")); //
			System.out.println(" role Created : "+role.getString("name"));
					
			for(String xpath: xpathList){
				System.out.println(" xpath: "+ xpath);
				WebElement privilegeElement = driver.findElement(By.xpath(xpath));
				System.out.println(" privilegeElement: "+ privilegeElement.getText());
				privilegeElement.click();	
			}
					 	
			// capturing screenShot before submitting create user form
			File beforeSubmit= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				// now copy the  screenshot to desired location using copyFile //method
				FileUtils.copyFile(beforeSubmit, new File("target/PortalURM/resources/screenShot/testCases/createRole-"
						+ username + "_" + currentTime + ".png"));
			}	 
			catch (IOException e){
			  System.out.println(e.getMessage());		 
			 }
			
			WebElement submit = driver.findElement(By.id("privilege-apply"));
			submit.click();
			Thread.sleep(3000);
			
			WebElement res = driver.findElement(By.className("alert"));
			
		// capturing screenShot After submitting create user form
			File afterSubmit= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try {
				if (res.getText().contains("Success!")) {
					FileUtils.copyFile(afterSubmit, new File(
							"target/PortalURM/resources/screenShot/passedTest/createRole/"+username+"_"+currentTime+".png"));
					System.out.println(" Test cases paseed role creation: " + res.getText());

				} else {
					FileUtils.copyFile(afterSubmit, new File(
							"target/PortalURM/resources/screenShot/failedTest/createRole/"+username+"_"+currentTime+".png"));
					System.out.println(" Test cases failed role creation: " + res.getText());
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
				
			
	}

	
	public void viewCommand(WebDriver driver,  JSONObject user) throws InterruptedException {
		System.out.println(" inside viewPrivilegeCommand method");	
		//currentTime= formatter.format(date);
		
		WebElement roleCommandDiv = driver.findElement(By.id("roleManagementCommand"));
		roleCommandDiv.click();
		
		WebElement createUserCommand = driver.findElement(By.id("View Role"));		

		createUserCommand.click();	
		Thread.sleep(2000);
		
		// capturing screenShot Before running test case of viewPrivilege 
		File testCase = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(testCase,
						new File(
								"target/PortalURM/resources/screenShot/testCases/viewRole-"
										+ username + "_" + currentTime + ".png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		WebElement otherRole = driver.findElement(By.id("otherRole"));
		otherRole.click();
 		
		// capturing screenShot After submitting create user form
		File viewUser = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(viewUser,
					new File(
							"target/PortalURM/resources/screenShot/testCases/viewRole-"
									+ username+ "_" + currentTime + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void deleteCommand(WebDriver driver, List<String> roleToDeleteList) throws InterruptedException {
		System.out.println(" inside deleteRoleCommand method");	
		String responseText="";		
		WebElement roleCommandDiv = driver.findElement(By.id("roleManagementCommand"));
		roleCommandDiv.click();
		
		WebElement deleteRoleCommand = driver.findElement(By.id("Delete Role"));		
		
		Thread.sleep(1000);
		deleteRoleCommand.click();	
	
		AlertHandler.alert(driver);
		
		// capturing screenShot After submitting create user form
		File testCase = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(testCase,
						new File(
								"target/PortalURM/resources/screenShot/testCases/deleteRole-"
										+ username + "_" + currentTime + ".png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		WebElement deleteButton = driver.findElement(By.id("submit-delete-role-frm"));
		
		String roleToDelete = roleToDeleteList.get(0);
		System.out.println(" Role to be deleted :: "+ roleToDelete);	
	
		WebElement tableText = driver.findElement(By.xpath("//td[contains(text(),'"+ roleToDelete +"')]"));
		System.out.println(" tableText selected for macthed:: "+ tableText.getText());
		
		tableText.click();
		deleteButton.click();
		
		//AlertHandler.alert(driver);
		responseText = 	AlertHandler.alert(driver);
		
			
		// capturing screenShot After submitting create user form
		File viewUser = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			if(responseText.contains("DELTED SUCCESSFULLY!")){
				FileUtils.copyFile(viewUser,
						new File(
								"target/PortalURM/resources/screenShot/passedTest/deleteRole/"
										+ username + "_" + currentTime + ".png"));
				System.out.println(" Delete Role Command Test Passed! ");
			}else{
				FileUtils.copyFile(viewUser,
						new File(
								"target/PortalURM/resources/screenShot/failedTest/deleteRole/"
										+ username + "_" + currentTime + ".png"));
				
				System.out.println(" Delete Role Command Test Failed! ");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}



}
