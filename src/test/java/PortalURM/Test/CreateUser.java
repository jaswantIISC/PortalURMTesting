package PortalURM.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import PortalURM.Impl.PortalCommandsImplementation;
import PortalURM.Impl.UserManagementImplementation;
import PortalURM.Interface.PortalCommands;
import PortalURM.Interface.URMCommands;
import net.sf.json.JSONObject;


public class CreateUser extends WebDriverSetup{

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
	Date date = new Date(); 
	String currentTime=formatter.format(date);
	URMCommands userManagement = new UserManagementImplementation();
	PortalCommands portalCommands = new PortalCommandsImplementation();
	String username = users[0];  // "super@cdot.in";
	String password = passwords[0]; // "super123";
	
	@Test
	public void testDriver() throws InterruptedException {
		System.out.println("[CreateUser.java][testDriver()]: ");
		portalCommands.loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(0);
		JSONObject roleToCreate = roleArray.getJSONObject(0);
		userManagement.createCommand(driver, userToCreate, roleToCreate);
	}
	
	
	
	/*@Test
	public void CREATE_USER_1() throws InterruptedException {
		System.out.println("[CreateUser.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		JSONObject roleToCreate = roleArray.getJSONObject(1);
		userManagement.createCommand(driver, userToCreate, roleToCreate);
	}

	@Test
	public void CREATE_USER_2() throws InterruptedException {
	System.out.println("[CreateUser.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		JSONObject roleToCreate = roleArray.getJSONObject(1);
		userManagement.createCommand(driver, userToCreate, roleToCreate);
	}
	
	@Test
	public void CREATE_USER_3() throws InterruptedException {
	System.out.println("[CreateUser.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		JSONObject roleToCreate = roleArray.getJSONObject(1);
		userManagement.createCommand(driver, userToCreate, roleToCreate);
	}
	
	@Test
	public void CREATE_USER_4() throws InterruptedException {
	System.out.println("[CreateUser.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		JSONObject roleToCreate = roleArray.getJSONObject(1);
		userManagement.createCommand(driver, userToCreate, roleToCreate);
	}
	*/



	

}
