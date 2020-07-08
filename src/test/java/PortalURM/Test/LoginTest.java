package PortalURM.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import PortalURM.Impl.PortalCommandsImplementation;
import PortalURM.Interface.PortalCommands;


public class LoginTest extends WebDriverSetup{

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
	Date date = new Date(); 
	String currentTime=formatter.format(date);
	PortalCommands portalCommands = new PortalCommandsImplementation();
	
	@Test
	public void LOGIN_1() throws InterruptedException {
		System.out.println(" LOGIN_1 : Login without entering userId and password");
		String username = users[1];// "";
		String password = passwords[1];// "";
		//System.out.println("users[1]:  "+ users[1]+" passwords[1]: "+passwords[1]);
		driver.navigate().refresh();
		portalCommands.loginUseCase(driver,username, password);			
		driver.navigate().refresh();
	}

	@Test
	public void LOGIN_2() throws InterruptedException {
		System.out.println(" LOGIN_2 : Login with either username or password is missing. ");
		String username = users[2];// "super@cdot.in";
		String password = passwords[2];// "";
		//System.out.println("users[2]:  "+ users[2]+" passwords[2]: "+passwords[2]);
		driver.navigate().refresh();
		portalCommands.loginUseCase(driver,username, password);			
		driver.navigate().refresh();
	}
	
	@Test
	public void LOGIN_3() throws InterruptedException {
		System.out.println(" LOGIN_3 :  Username is correct but password entered with wrong value. ");
		driver.navigate().refresh();
		portalCommands.loginUseCase(driver, users[3], passwords[3]);	// "super@cdotin";	"super123";	
		driver.navigate().refresh();
	}
	
	@Test
	public void LOGIN_4() throws InterruptedException {
		System.out.println(" LOGIN_4 : Username does not have the correct format but password is correct. ");
		String username = users[4];// "super@cdot.in";
		String password = passwords[4];// "super123";
		driver.navigate().refresh();
		portalCommands.loginUseCase(driver,username, password);			

	}
	
	@Test
	public void LOGIN_5() throws InterruptedException {
		System.out.println(" LOGIN_5 : Login with correct username and password");
		String username = users[0];// "super@cdot.in";
		String password = passwords[0];// "super123";
		driver.navigate().refresh();
		portalCommands.loginSuccess(driver,username, password);			
	}


	

}
