package PortalURM.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.management.DescriptorKey;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PortalURM.Impl.PortalCommandsImplementation;
import PortalURM.Impl.PrivilegeManagementImplementation;
import PortalURM.Impl.RoleManagementImplementation;
import PortalURM.Impl.UserManagementImplementation;
import PortalURM.Interface.PortalCommands;
import PortalURM.Interface.URMCommands;
import net.sf.json.JSONObject;


public class ViewRole extends WebDriverSetup{

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
	Date date = new Date(); 
	String currentTime=formatter.format(date);
	URMCommands roleManagement = new RoleManagementImplementation();	
	PortalCommands portalCommands = new PortalCommandsImplementation();
	String username = users[0];  // "super@in";
	String password = passwords[0]; // "super123";

	@Test
	public void testDriver() throws InterruptedException {
		System.out.println("[ViewRole.java][testDriver()]: ");
		portalCommands.loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		roleManagement.viewCommand(driver, userToCreate);
	}

/*	
	@Test
	public void viewRole_1() throws InterruptedException {
		System.out.println("[ViewRole.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		userManagement.viewCommand(driver, userToCreate);
	}
	

	@Test
	public void viewRole_2() throws InterruptedException {
		System.out.println("[ViewRole.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		userManagement.viewCommand(driver, userToCreate);
	}
	
	
	@Test
	public void viewRole_3() throws InterruptedException {
		System.out.println("[ViewRole.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		userManagement.viewCommand(driver, userToCreate);
	}
	
	
	@Test
	public void viewRole_4() throws InterruptedException {
		System.out.println("[ViewRole.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		userManagement.viewCommand(driver, userToCreate);
	}
	
	
	@Test
	public void viewRole_5() throws InterruptedException {
		System.out.println("[ViewRole.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		userManagement.viewCommand(driver, userToCreate);
	}
	
*/

	

}
