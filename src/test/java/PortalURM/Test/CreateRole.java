package PortalURM.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import PortalURM.Impl.PortalCommandsImplementation;
import PortalURM.Impl.RoleManagementImplementation;
import PortalURM.Impl.UserManagementImplementation;
import PortalURM.Interface.PortalCommands;
import PortalURM.Interface.URMCommands;
import net.sf.json.JSONObject;


public class CreateRole extends WebDriverSetup{

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
	Date date = new Date(); 
	String currentTime=formatter.format(date);
	URMCommands roleManagement = new RoleManagementImplementation();
	PortalCommands portalCommands = new PortalCommandsImplementation();
	String username = users[0];  // "super@cdot.in";
	String password = passwords[0]; // "super123";
	
	@Test
	public void testDriver() throws InterruptedException {
		System.out.println("[CreateRoleTest.java][testDriver()]: ");
		portalCommands.loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		JSONObject roleToCreate = roleArray.getJSONObject(1);
		roleManagement.createCommand(driver, userToCreate, roleToCreate);
	}
	
/*
	@Test
	public void CreateRole_1() throws InterruptedException {
		System.out.println("[CreateRoleTest.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		JSONObject roleToCreate = roleArray.getJSONObject(1);
		userManagement.createCommand(driver, userToCreate, roleToCreate);
	}

	@Test
	public void CreateRole_2() throws InterruptedException {
		System.out.println("[CreateRoleTest.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		JSONObject roleToCreate = roleArray.getJSONObject(1);
		userManagement.createCommand(driver, userToCreate, roleToCreate);
	}
	
	@Test
	public void CreateRole_3() throws InterruptedException {
		System.out.println("[CreateRoleTest.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		JSONObject roleToCreate = roleArray.getJSONObject(1);
		userManagement.createCommand(driver, userToCreate, roleToCreate);
	}
	
	@Test
	public void CreateRole_4() throws InterruptedException {
		System.out.println("[CreateRoleTest.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		JSONObject roleToCreate = roleArray.getJSONObject(1);
		userManagement.createCommand(driver, userToCreate, roleToCreate);
	}
	
	@Test
	public void CreateRole_5() throws InterruptedException {
		System.out.println("[CreateRoleTest.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		JSONObject userToCreate = userArray.getJSONObject(1);
		JSONObject roleToCreate = roleArray.getJSONObject(1);
		userManagement.createCommand(driver, userToCreate, roleToCreate);
	}*/


	

}
