package ssm.commands.Invoker;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateSSN {

	@Test
	public void testDriver(String subscriber, String ssp) throws InterruptedException, AWTException{

		Thread.sleep(2000);

		WebDriver driver = InvokerSsm.getDriver();

		WebDriverWait wait = new WebDriverWait(driver,60);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SSNCommand")));


		System.out.println(driver.findElement(By.id("SSNCommand")).getTagName());

		driver.findElement(By.id("SSNCommand")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Create SSN")));
		System.out.println(driver.getCurrentUrl());


		driver.findElement(By.id("Create SSN")).click();


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("selectedSubscriber"))); 
		Select ssnSubscriber = new Select(driver.findElement(By.id("selectedSubscriber")));
		ssnSubscriber.selectByVisibleText(subscriber);


		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='selectedSspEle']")));

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Select ssnssp = new Select(driver.findElement(By.id("selectedSspEle")));
		ssnssp.selectByVisibleText(ssp);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='selectedSecureEle']/option[2]")));
		Select sa = new Select(driver.findElement(By.id("selectedSecureEle")));
		sa.selectByIndex(1);


		driver.findElement(By.id("nodeFile")).sendKeys("/home/rahul/eclipse-workspace/CCSP_oneM2M_Tester/utils/Data/configuration/ae2.csv");



		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_TAB);	
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			
			robot.keyPress(KeyEvent.VK_ENTER);	
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			
			robot.keyPress(KeyEvent.VK_DOWN);	
			robot.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);
		
			robot.keyPress(KeyEvent.VK_DOWN);	
			robot.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);
			
			robot.keyPress(KeyEvent.VK_DOWN);	
			robot.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);
			
			robot.keyPress(KeyEvent.VK_DOWN);	
			robot.keyRelease(KeyEvent.VK_DOWN);
			Thread.sleep(1000);
			
			robot.keyPress(KeyEvent.VK_ENTER);	
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			
			robot.keyPress(KeyEvent.VK_TAB);	
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			robot.keyPress(KeyEvent.VK_TAB);	
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			robot.keyPress(KeyEvent.VK_TAB);	
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			
			robot.keyPress(KeyEvent.VK_TAB);	
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			robot.keyPress(KeyEvent.VK_TAB);	
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			robot.keyPress(KeyEvent.VK_TAB);	
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			robot.keyPress(KeyEvent.VK_TAB);	
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			robot.keyPress(KeyEvent.VK_TAB);	
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			robot.keyPress(KeyEvent.VK_TAB);	
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			robot.keyPress(KeyEvent.VK_TAB);	
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);

			robot.keyPress(KeyEvent.VK_TAB);	
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			

			robot.keyPress(KeyEvent.VK_ENTER);	
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);

			
			System.out.println("hello");
		/*
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("OkButtonEle")));
			driver.findElement(By.id("OkButtonEle")).click();
		 */				
		/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='button'][value='Ok']")));
			driver.findElement(By.cssSelector("input[type='button'][value='Ok']")).click();
		 */

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='createSSNForm']/table/tbody/tr[6]/td/input")));
		WebElement element8 =driver.findElement(By.xpath("//*[@id='createSSNForm']/table/tbody/tr[6]/td/input"));
		JavascriptExecutor executor8 = (JavascriptExecutor)driver;
		executor8.executeScript("arguments[0].click();", element8);

		WebDriverWait wait3 = new WebDriverWait(driver,30);
		wait3.until(ExpectedConditions.visibilityOfElementLocated(By.className("successMessageDiv")));
		System.out.println("Sucess Message after SSN creation is ::" +  driver.findElement(By.xpath("//*[@id='content']/div")).getText());

	}
}
