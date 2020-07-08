package PortalURM.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import PortalURM.utils.User;
import net.sf.json.JSONArray;

public class WebDriverSetup {
	static  WebDriver driver;
	public String[] users = {"super@cdot.in", "", "super@cdot.in", " super@cdot.in", "super@cdotin"};// first one is correct		
	public String[] passwords = {"super123", "", "", "super@123", "super@123"}; // first one is correct
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
	Date date = new Date(); 
	String currentTime=formatter.format(date);
	protected User user = new User();
	JSONArray userArray = user.getUsers();
	JSONArray roleArray = user.getRoles();
	protected String username= "Super";
		
	//@SuppressWarnings("deprecation")
	@BeforeClass
	public static void setUp(){
	System.out.println(" Setup loading....");
		// SSL Certificate Error Handling in Chrome....
		ChromeOptions profile = new ChromeOptions();// DesiredCapabilities.chrome();
		
		// Set the ACCEPT_INSECURE_CERT variable to true
		profile.setAcceptInsecureCerts(true); 

		profile.addArguments("--ignore-certificate-errors");

		// Set the driver path 
		System.setProperty("webdriver.chrome.driver", "utils/lib/chromedriver_linux64/chromedriver");
		
		// Open browser with capability
		driver = new ChromeDriver(profile);		
		String url= "https://192.168.105.209:9543/urm/login";
		driver.get(url);		
		System.out.println("Website title = "+ driver.getTitle()+ " |  url = "+ driver.getCurrentUrl());		
		driver.manage().window().maximize();		
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		System.out.println(" Setup loading complete !");
	}
	
	@AfterClass
	public static void cleanUp(){
		//driver.quit();
	}
	

	
/*		
	public void executeURMCommands(WebDriver driver) throws InterruptedException {
				
		//executeUserManagementCommand(driver);
		
		//executePrivilegeManagementCommand(driver);
		
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
	//	roleManagement.viewCommand(driver);
		//******Old pattern of interface and implementation ***********
		//roleManagement.createRoleCommand(driver);
		//roleManagement.viewRoleCommand(driver);
		//roleManagement.deleteRoleCommand(driver);
		
	}
	
	
	//@SuppressWarnings("deprecation")
/*		@Before
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
			
			String url= "https://192.168.105.209:9543/home";
			
			driver.get(url);
			
			System.out.println("Website title :: "+ driver.getTitle()+ " url : "+ driver.getCurrentUrl());
			
			driver.manage().window().maximize();
			
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		}*/
		
	/*
		public void portalLogin(WebDriver driver, String user, String password) throws InterruptedException {
			System.out.println(" inside TestDriver() Website title :: "+ driver.getTitle()+ " url : "+ driver.getCurrentUrl());
			
			WebDriverWait wait = new WebDriverWait(driver, 20);
			
			WebElement loginLink = driver.findElement(By.xpath("//a[@href='/urm/login']"));
			
			wait.until(ExpectedConditions.visibilityOf(loginLink));
			
			System.out.println(" logingLink details : "+ loginLink.getText()+ "  href : "+ loginLink.getAttribute("href"));
			
			// 1. Navigating to URM Page from Portal 
			loginLink.click();

			wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-id")));
			
			String currentPage = driver.getCurrentUrl();
			
			String expectedPage = "https://192.168.105.209:9543/urm/login";
			
			System.out.println("Current page title :: "+ driver.getTitle()+ " url : "+ driver.getCurrentUrl());
			
			assert currentPage.equalsIgnoreCase(expectedPage);
			
			if(currentPage.equalsIgnoreCase(expectedPage)){
				System.out.println(" TEST 1: PASSED ");
			}
			else{
				System.out.println(" TEST 1: FAILED ");
			}
			loginSuccess(driver, user, password);
		}*/
}
