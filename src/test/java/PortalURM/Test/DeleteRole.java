package PortalURM.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import PortalURM.Impl.PortalCommandsImplementation;
import PortalURM.Impl.RoleManagementImplementation;
import PortalURM.Interface.PortalCommands;
import PortalURM.Interface.URMCommands;


public class DeleteRole extends WebDriverSetup{

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
	Date date = new Date(); 
	String currentTime=formatter.format(date);
	URMCommands roleManagement = new RoleManagementImplementation();
	PortalCommands portalCommands = new PortalCommandsImplementation();
	String username = users[0];  // "super@cdot.in";
	String password = passwords[0]; // "super123";
	List<String> roleList =  Arrays.asList("R1","R33");
		 
	@Test
	public void testDriver() throws InterruptedException {
		System.out.println("[DeleteRole.java][testDriver()]: ");
		portalCommands.loginSuccess(driver,username, password);	
		roleManagement.deleteCommand(driver, roleList);
	}


/*	@Test
	public void DeleteRole_1() throws InterruptedException {
		System.out.println("[DeleteRole.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		roleManagement.deleteCommand(driver);
	}

	@Test
	public void DeleteRole_2() throws InterruptedException {
		System.out.println("[DeleteRole.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		roleManagement.deleteCommand(driver);
	}
	
	@Test
	public void DeleteRole_3() throws InterruptedException {
		System.out.println("[DeleteRole.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		roleManagement.deleteCommand(driver);
	}
	
	@Test
	public void DeleteRole_4() throws InterruptedException {
		System.out.println("[DeleteRole.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		roleManagement.deleteCommand(driver);
	}
	
	@Test
	public void DeleteRole_5() throws InterruptedException {
		System.out.println("[DeleteRole.java][testDriver()]: ");
		loginSuccess(driver,username, password);	
		roleManagement.deleteCommand(driver);
	}

*/
	

}
