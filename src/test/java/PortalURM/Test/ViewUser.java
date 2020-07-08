package PortalURM.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import PortalURM.Impl.PortalCommandsImplementation;
import PortalURM.Impl.UserManagementImplementation;
import PortalURM.Interface.PortalCommands;
import PortalURM.Interface.URMCommands;
import net.sf.json.JSONObject;


public class ViewUser extends WebDriverSetup{

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
	Date date = new Date(); 
	String currentTime=formatter.format(date);
	URMCommands userManagement = new UserManagementImplementation();
	PortalCommands portalCommands = new PortalCommandsImplementation();
	String username = users[0];  // "super@cdot.in";
	String password = passwords[0]; // "super123";
		

	@Test
	public void testDriver() throws InterruptedException {
		System.out.println("[ViewUser.java][testDriver()]: ");
		portalCommands.loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		userManagement.viewCommand(driver, userToCreate);
	}
	
/*	
 	@Test
	public void viewUser_1() throws InterruptedException {
		System.out.println("[ViewUser.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		userManagement.viewCommand(driver, userToCreate);
	}

	@Test
	public void viewUser_2() throws InterruptedException {
		System.out.println("[ViewUser.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		userManagement.viewCommand(driver, userToCreate);
	}
	
	@Test
	public void viewUser_3() throws InterruptedException {
		System.out.println("[ViewUser.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		userManagement.viewCommand(driver, userToCreate);
	}
	
	@Test
	public void viewUser_4() throws InterruptedException {
		System.out.println("[ViewUser.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		userManagement.viewCommand(driver, userToCreate);
	}
	
	@Test
	public void viewUser_5() throws InterruptedException {
		System.out.println(" viewUser_1 : viewUser with correct username and password");
		String username = users[0];// "super@cdot.in";
		String password = passwords[0];// "super123";
		driver = viewUserFailed(driver,username, password);			
		goToURMModule(driver);
	}
*/

	

}
