package PortalURM.Impl;


import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.UserDefinedFileAttributeView;
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
import PortalURM.utils.User;

import net.sf.json.JSONObject;

public class PrivilegeManagementImplementation implements URMCommands{
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
    Date date = new Date(); 
    String currentTime=formatter.format(date);
    User userObj = new User();
    JSONObject user = userObj.getUsers().getJSONObject(2);
    String username = user.getString("name"); 
	
    @Override
    public void viewCommand(WebDriver driver, JSONObject user) throws InterruptedException {
		System.out.println(" inside viewPrivilegeCommand method");	
			
		WebElement privilegeCommandDiv = driver.findElement(By.id("privilegeManagementCommand"));
		privilegeCommandDiv.click();
		
		WebElement viewPrivilegeCommand = driver.findElement(By.id("View Privilege"));		

		viewPrivilegeCommand.click();	
		Thread.sleep(2000);
		
		// capturing screenShot Before running test case of viewPrivilege 
		File testCase = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(testCase,
						new File(
								"target/PortalURM/resources/screenShot/testCases/viewPrivilege-"
										+ username + "_" + currentTime + ".png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// capturing screenShot After  running test case of viewPrivilege
		File viewPrivilege = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(viewPrivilege,
					new File(
							"target/PortalURM/resources/screenShot/passedTest/viewPrivileges/"
									+ username + "_" + currentTime + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void createCommand(WebDriver driver, JSONObject user, JSONObject role) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteCommand(WebDriver driver, List<String> list) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

}
