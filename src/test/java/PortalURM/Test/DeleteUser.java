package PortalURM.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import PortalURM.Impl.PortalCommandsImplementation;
import PortalURM.Impl.UserManagementImplementation;
import PortalURM.Interface.PortalCommands;
import PortalURM.Interface.URMCommands;


public class DeleteUser extends WebDriverSetup{

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
	Date date = new Date(); 
	String currentTime=formatter.format(date);
	URMCommands userManagement = new UserManagementImplementation();
	PortalCommands portalCommands = new PortalCommandsImplementation();
	String username = users[0];  // "super@cdot.in";
	String password = passwords[0]; // "super123";
	List<String> userList =  Arrays.asList("jmeena@cdot.in","R33");
	
	@Test
	public void testDriver() throws InterruptedException {
		System.out.println("[DeleteUser.java][testDriver()]: ");
		portalCommands.loginSuccess(driver,username, password);	
		userManagement.deleteCommand(driver, userList);
	}

/*	@Test
	public void deleteUser_1() throws InterruptedException {
		System.out.println("[DeleteUser.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		userManagement.deleteCommand(driver);
	}

	@Test
	public void deleteUser_2() throws InterruptedException {
		System.out.println("[DeleteUser.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		userManagement.deleteCommand(driver);
	}
	
	@Test
	public void deleteUser_3() throws InterruptedException {
		System.out.println("[DeleteUser.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		userManagement.deleteCommand(driver);
	}
	
	@Test
	public void deleteUser_4() throws InterruptedException {
		System.out.println("[DeleteUser.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		userManagement.deleteCommand(driver);
	}
	
	@Test
	public void deleteUser_5() throws InterruptedException {
		System.out.println("[DeleteUser.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		userManagement.deleteCommand(driver);
	}

*/
	

}
