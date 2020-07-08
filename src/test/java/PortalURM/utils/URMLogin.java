package PortalURM.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
import PortalURM.Impl.PrivilegeManagementImplementation;
import PortalURM.Impl.RoleManagementImplementation;
import PortalURM.Impl.UserManagementImplementation;
import PortalURM.Interface.URMCommands;


public class URMLogin {
	private WebDriver driver;
	URMCommands userManagement = new UserManagementImplementation();
	URMCommands roleManagement = new RoleManagementImplementation();
	URMCommands privilegeManagement = new PrivilegeManagementImplementation();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
	Date date = new Date(); 
	String currentTime=formatter.format(date);
	
	//@SuppressWarnings("deprecation")
	@BeforeClass
	public void setUp(){
	
		// SSL Certificate Error Handling in Chrome....
		ChromeOptions profile = new ChromeOptions();// DesiredCapabilities.chrome();
		
		// Set the ACCEPT_INSECURE_CERT variable to true
		profile.setAcceptInsecureCerts(true); 
		
		profile.addArguments("--ignore-certificate-errors");
		
		// Set the driver path 
		System.setProperty("webdriver.chrome.driver", "/Users/jaswantmeena/Documents/workspace/jars/Drivers/Chrome-81.0/chromedriver");
		
		// Open browser with capability
		driver = new ChromeDriver(profile);
		
		String url= "https://192.168.105.209:9543/urm/login";
		
		driver.get(url);
		
		System.out.println("Website title :: "+ driver.getTitle()+ " url : "+ driver.getCurrentUrl());
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		System.out.println("WEB DRIVER INITIALIZED !...APPLICATION STARTED ... ");
	}
	
	@Test
	public void TestDriver() throws InterruptedException {
		String[] users = {"super@cdot.in", "", "super@cdot.in", " super@cdotin", "super@cdot.in"};// first one is correct
		
		String[] passwords = {"super123", "", "", "super123", "super@123"}; // first one is correct
		
		//int start =0, end= users.length;
		System.out.println(" user length : "+ users.length);
		//for(int i=start; i< end; i++){
			String username = users[0];//"super@cdot.in";
	
			String password = passwords[0];// super123";
			
			driver = urmLogin(driver,username, password);
			
			goToURMModule(driver);
			
		//}
	}



	public WebDriver urmLogin(WebDriver driver, String user, String password) throws InterruptedException{
		System.out.println(" inside urmLogin() Website title :: "+ driver.getTitle()+ " url : "+ driver.getCurrentUrl());
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		//2.  After navigating to URM Login credentials are required to login into URM.
		WebElement username = driver.findElement(By.id("login-id"));
		
		WebElement pass = driver.findElement(By.id("login-pwd"));
		
		WebElement loginBtn = driver.findElement(By.id("login-btn"));
		
		username.sendKeys(user);
		
		pass.sendKeys(password);
		
		// 3. Navigating to URM user Dashboard "product.jsp" 
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		loginBtn.click();
		//Thread.sleep(2000);
		
		// capturing screenShot After submitting create user form
		File loginUrm = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
				FileUtils.copyFile(loginUrm,
						new File(
								"/Users/jaswantmeena/Documents/workspace/Selenium_Training_Udemy/utils/resources/screenShot/testCases/urmLogin-"
										+ "_" + currentTime + ".png"));
				System.out.println(" login user Command Test executed! ");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		wait = new WebDriverWait(driver, 20);

		wait.until(ExpectedConditions.alertIsPresent());
	
		driver.switchTo().alert().accept();
		
		String ProductPage = driver.getCurrentUrl();
		
		String expectedPage = "https://192.168.105.209:9543/urm/login";
		
		System.out.println("Current page title :: "+ driver.getTitle()+ " url : "+ driver.getCurrentUrl());
		
		WebElement userEmailId = driver.findElement(By.xpath("//span[@class='user-emailId']"));
		
		System.out.println(" userProfile section userId :: "+ userEmailId.getText());
			
		if(ProductPage.equalsIgnoreCase(expectedPage)){
			System.out.println(" TEST 2: PASSED | login successful ");
		}
		else{
			System.out.println(" TEST 2: FAILED | | login failed ");
		}
	
		/*if(userEmailId!=null && (!userEmailId.getText().equalsIgnoreCase(""))){
			System.out.println(" TEST 2: PASSED | login successful ");
		}
		else{
			System.out.println(" TEST 2: FAILED | | login failed ");
		} */
		assert ProductPage.equalsIgnoreCase(expectedPage);
			
		return driver;
	}
	
	
	public void goToURMModule(WebDriver driver) throws InterruptedException {

		System.out.println(" inside goToURMModule() !!...");
		
		WebElement urmModule = driver.findElement(By.xpath("//a[text()= 'URM']"));
		
		System.out.println(" urmModule href :: "+ urmModule.getAttribute("href"));
		
		Thread.sleep(2000);
		
		urmModule.click();

		Thread.sleep(3000);
		
		String parent = driver.getWindowHandle();
		
		System.out.println(" parent window :: "+ parent);
		
		Set<String> tab_handles = driver.getWindowHandles();
	    
		int count = tab_handles.size();
	    
		System.out.println("tab size "+ count);
		
		for(String child : tab_handles){
			
			if(!parent.equalsIgnoreCase(child)){
				
				System.out.println(" child window :: "+ child);
				
				driver.switchTo().window(child);
			}
		}
	
		String ModulePage = driver.getCurrentUrl();
		
		String expectedPage = "https://192.168.105.209:9543/urm/module";
		
		System.out.println("Current page title :: "+ driver.getTitle()+ " url : "+ driver.getCurrentUrl());
		
		if(ModulePage.equalsIgnoreCase(expectedPage)){
			
			System.out.println(" TEST 3: PASSED | URM dashboard navigatation successful ");
		}
		else{
			
			System.out.println(" TEST 3: FAILED | | URM dashboard navigatation failed ");
		}
		
		assert ModulePage.equalsIgnoreCase(expectedPage);
		
		executeURMCommands(driver);
		
		//testCasesURMCommands(driver);
			
	}
	
	
	public void executeURMCommands(WebDriver driver) throws InterruptedException {
		executeRoleManagementCommand(driver);
				
		//executeUserManagementCommand(driver);
		
		//executePrivilegeManagementCommand(driver);
		
	}
	
	
	public void testCasesURMCommands(WebDriver driver) {
		testRoleManagementCommand(driver);
		
		testUserManagementCommand(driver);
		
		testPrivilegeManagementCommand(driver);
		
	}
	
	
	public void executePrivilegeManagementCommand(WebDriver driver) throws InterruptedException {
		System.out.println(" inside executePrivilegeManagementCommand method");	
		//privilegeManagement.viewCommand(driver);
		
		//******Old pattern of interface and implementation ***********
		//privilegeManagement.viewPrivilegeCommand(driver);
		
	}

	
	public void executeUserManagementCommand(WebDriver driver) throws InterruptedException {
		System.out.println(" inside executeUserManagementCommand method");		
		//userManagement.createCommand(driver);
		
		//******Old pattern of interface and implementation ***********
		//userManagement.createUserCommand(driver);
		//userManagement.viewUserCommand(driver);
		//userManagement.deleteUserCommand(driver);
		//userManagement.viewUserCommand(driver);
				
	}

	
	public void executeRoleManagementCommand(WebDriver driver) throws InterruptedException {
		System.out.println(" inside executeRoleManagementCommand method");
		//roleManagement.createCommand(driver);
		//roleManagement.viewCommand(driver);
		//******Old pattern of interface and implementation ***********
		//roleManagement.createRoleCommand(driver);
		//roleManagement.viewRoleCommand(driver);
		//roleManagement.deleteRoleCommand(driver);
		
	}
	
	
	public void testPrivilegeManagementCommand(WebDriver driver) {
		System.out.println(" inside testPrivilegeManagementCommand method");
		
	}

	
	public void testUserManagementCommand(WebDriver driver) {
		System.out.println(" inside testUserManagementCommand method");
		
	}

	
	public void testRoleManagementCommand(WebDriver driver) {
		System.out.println(" inside testRoleManagementCommand method");
		
	}

	
	
	
	@After
	public void cleanUp(){
		//driver.quit();
	}
	

}
