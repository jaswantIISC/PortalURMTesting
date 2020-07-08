package PortalURM.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import PortalURM.Impl.PortalCommandsImplementation;
import PortalURM.Impl.UserManagementImplementation;
import PortalURM.Interface.PortalCommands;
import PortalURM.Interface.URMCommands;
import net.sf.json.JSONObject;


public class PasswordChangeOnFirstLogin extends WebDriverSetup{

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
	Date date = new Date(); 
	String currentTime=formatter.format(date);
	URMCommands userManagement = new UserManagementImplementation();
	PortalCommands portalCommands = new PortalCommandsImplementation();
	String loginId = "jmeena@cdot.in";	//"super@cdot.in";
	String password = "2CC0GV"; //"super123"; 
	String passwordToSet = "cdot@123";
	
	@Test
	public void testDriver() throws InterruptedException {
		System.out.println("[PasswordChangeOnFirstLogin.java][testDriver()]: ");
		
		String loginStatus = portalCommands.loginSuccess(driver, loginId, password);
		
		if(loginStatus.equals("FIRST_TIME_LOGIN")) {
			portalCommands.passwordChange(driver, passwordToSet,  password);
		}
				
	}
		

}
