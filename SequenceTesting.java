package PortalURM.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import PortalURM.Impl.PortalCommandsImplementation;
import PortalURM.Impl.RoleManagementImplementation;
import PortalURM.Impl.UserManagementImplementation;
import PortalURM.Interface.PortalCommands;
import PortalURM.Interface.URMCommands;
import PortalURM.utils.OTPReader;
import net.sf.json.JSONObject;


public class SequenceTesting extends WebDriverSetup {

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
	Date date = new Date(); 
	String currentTime=formatter.format(date);
	PortalCommands portalCommands = new PortalCommandsImplementation();
	URMCommands roleManagement = new RoleManagementImplementation();
	URMCommands userManagement = new UserManagementImplementation();
	JSONObject userToCreate ;
	JSONObject roleToCreate ;
	String username = users[0];  // "super@cdot.in";
	String password = passwords[0]; // "super123";
	
	// sequence 1: createRole --> createUser --> viewUser 
	@Test
	public void sequence1() throws InterruptedException {
		System.out.println(" [SequenceTesting] [sequence1()] :createRole -> createUser -> viewUser ");
		userToCreate = userArray.getJSONObject(0);
		roleToCreate = roleArray.getJSONObject(0);
		List<String> deleteUserList =  Arrays.asList("jmeena@cdot.in","R33");
		portalCommands.loginSuccess(driver,username, password);
		
		//System.out.println("\n**************** role createCommand is being invoked ******************");
		//roleManagement.createCommand(driver, userToCreate, roleToCreate);
		
		//System.out.println("\n************** user deleteCommand is being invoked ******************");
		//userManagement.deleteCommand(driver, deleteUserList); 
		
		System.out.println("\n*************** user createCommand is being invoked ******************");
		userManagement.createCommand(driver, userToCreate, roleToCreate);
		
		//System.out.println("\n************** user viewCommand is being invoked ******************");
	//	userManagement.viewCommand(driver, userToCreate);
		
		System.out.println("\n************** user viewCommand is being vinvoked ******************");
		portalCommands.logout(driver, userToCreate);
		
		String subject = "User Successfully Created";	
		System.out.println("\n************** user OTPReader.read is being invoked ******************");
		String OTP = OTPReader.read( userToCreate.getString("emailId"), userToCreate.getString("webMailPassword"),  subject);
		
		System.out.println(" OTP read from Webmail : "+  OTP);
		
		System.out.println("\n************** WebDriver new setup is being invoked******************");
		setUp();
		
		System.out.println("\n************** user loginSuccess 2 times is being invoked ******************");
		String loginStatus = portalCommands.loginSuccess(driver, userToCreate.getString("emailId"), OTP);
		
		if(loginStatus.equals("FIRST_TIME_LOGIN")) {
			System.out.println("\n************** user passwordChange is being invoked ******************");
			portalCommands.passwordChange(driver, "cdot@123",  OTP);
		}
		
		
		Thread.sleep(5100);
		
		System.out.println("\n************** user forgotPasswords is being  invoked ******************");
		portalCommands.forgotPassword(driver, "jaswant@123",  userToCreate.getString("emailId"), userToCreate.getString("webMailPassword"));	

		
		
		/*
		 * portalCommands.loginSuccess(driver,username, password);
		 * System.out.println(" role createCommand is invoked");
		 * roleManagement.createCommand(driver, userToCreate, roleToCreate);
		 * System.out.println(" user createCommand is invoked");
		 * userManagement.createCommand(driver, userToCreate, roleToCreate);
		 * portalCommands.logout(driver, userToCreate);
		 */
		//portalCommands.forgotPassword(driver, username, password);
		
	/*	portalCommands.loginSuccess(driver,username, password);	
		
		System.out.println(" role createCommand is invoked");
		roleManagement.createCommand(driver, userToCreate, roleToCreate);
		
		System.out.println(" user createCommand is invoked");
		userManagement.createCommand(driver, userToCreate, roleToCreate);
		
		System.out.println(" user viewCommand is invoked");
		userManagement.viewCommand(driver, userToCreate);		
		
		portalCommands.logout(driver, userToCreate);	
		System.out.println(" end sequence1 :createRole->createUser->viewUser ");
		
		*/
	}
	  
}
