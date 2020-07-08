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

public class CreateSSAR {

	@Test
	public void testDriver(String subscriber) throws InterruptedException, AWTException{
		
		Thread.sleep(2000);

		WebDriver driver = InvokerSsm.getDriver();

		WebDriverWait wait = new WebDriverWait(driver,60);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SSARCommand")));

		System.out.println(driver.findElement(By.id("SSARCommand")).getTagName());

		driver.findElement(By.id("SSARCommand")).click();

		driver.manage().window().maximize();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Create SSAR")));
		System.out.println(driver.getCurrentUrl());
		
		driver.findElement(By.id("Create SSAR")).click();


		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		Select subscriberEle = new Select(driver.findElement(By.id("selectedSubscriber")));
		subscriberEle.selectByVisibleText(subscriber);
		

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		
		String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
	      StringBuilder rule = new StringBuilder();
	      java.util.Random randomString = new java.util.Random();
	      while (rule.length() < 10) { // length of the random string.
	          int index = (int) (randomString.nextFloat() * CHARS.length());
	          rule.append(CHARS.charAt(index));
	      }
	      String ruleStr = rule.toString();

		    System.out.println(ruleStr);
		

	

	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ruleLinkName")));
		driver.findElement(By.id("ruleLinkName")).click();
		driver.findElement(By.id("ruleLinkName")).sendKeys(ruleStr);
		
		WebElement elements1 = driver.findElement(By.id("ruleLinkName"));
		String texts1 = elements1.getAttribute("value");
		System.out.println(texts1);

		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("credId")));
		Select credId = new Select(driver.findElement(By.id("credId")));
		credId.selectByIndex(1);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		driver.findElement(By.id("credBtn")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("appId")));
		Select appId = new Select(driver.findElement(By.id("appId")));
		appId.selectByIndex(1);	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		driver.findElement(By.id("appBtn")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aeId")));
		Select aeId = new Select(driver.findElement(By.id("aeId")));
		aeId.selectByIndex(1);	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		driver.findElement(By.id("aeBtn")).click();
		
		
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='roleIdRow']/td[2]/button/span[2]")));
		driver.findElement(By.xpath("//*[@id='roleIdRow']/td[2]/button/span[2]")).click();	
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	/*	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div/ul/li[1]/a/span[2]")));
		driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[1]/a/span[2]")).click();	
		*/
		
		Robot robot = new Robot();
		
		robot.keyPress(KeyEvent.VK_ENTER);	
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		
		robot.keyPress(KeyEvent.VK_DOWN);	
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		
		robot.keyPress(KeyEvent.VK_ENTER);	
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		
		robot.keyPress(KeyEvent.VK_DOWN);	
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		
		robot.keyPress(KeyEvent.VK_ENTER);	
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		
		robot.keyPress(KeyEvent.VK_DOWN);	
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		
		robot.keyPress(KeyEvent.VK_ENTER);	
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		
		robot.keyPress(KeyEvent.VK_DOWN);	
		robot.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		
		driver.findElement(By.id("roleBtn")).click();
		
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='createAppForm1']/input")));
		WebElement element5 =driver.findElement(By.xpath("//*[@id='createAppForm1']/input"));
		JavascriptExecutor executor5 = (JavascriptExecutor)driver;
		executor5.executeScript("arguments[0].click();", element5);
		


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("successMessageDiv")));
		System.out.println("Sucess Message after SSN creation is ::" +  driver.findElement(By.xpath("//*[@id='content']/div")).getText());


		
	}
	
}
