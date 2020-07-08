package ssm.commands.Invoker;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateSubscriber {

	@Test
	public String testDriver() throws InterruptedException{
		
		Thread.sleep(2000);
		
		WebDriver driver = InvokerSsm.getDriver();
		
		WebDriverWait wait = new WebDriverWait(driver,60);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubscriberCommand")));
		
		System.out.println(driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div[2]/div/a/div/span[1]")).getText());

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubscriberCommand")));

		System.out.println(driver.findElement(By.id("SubscriberCommand")).getTagName());

		driver.findElement(By.id("SubscriberCommand")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Create Subscriber")));
		System.out.println(driver.getCurrentUrl());

		driver.findElement(By.id("Create Subscriber")).click();

		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
	      StringBuilder subs = new StringBuilder();
	      java.util.Random rndsubs = new java.util.Random();
	      while (subs.length() < 10) { // length of the random string.
	          int index = (int) (rndsubs.nextFloat() * CHARS.length());
	          subs.append(CHARS.charAt(index));
	      }
	      String subsStr = subs.toString();

		    System.out.println(subsStr);
		

		driver.findElement(By.id("subscriberName")).click();
		driver.findElement(By.id("subscriberName")).sendKeys(subsStr);
		WebElement elements1 = driver.findElement(By.id("subscriberName"));
		String texts1 = elements1.getAttribute("value");
		System.out.println(texts1);

		
		String nums = "1234567890";
	      StringBuilder phone = new StringBuilder();
	      java.util.Random rndphone = new java.util.Random();
	      while (phone.length() < 10) { // length of the random string.
	          int index = (int) (rndphone.nextFloat() * nums.length());
	          phone.append(nums.charAt(index));
	      }
	      String phoneStr = "+91-" + phone.toString();

		    System.out.println(phoneStr);
		
		
		driver.findElement(By.id("phoneNo")).click();
		driver.findElement(By.id("phoneNo")).clear();
		driver.findElement(By.id("phoneNo")).sendKeys(phoneStr);
		WebElement elements2 = driver.findElement(By.id("phoneNo"));
		String texts2 = elements2.getAttribute("value");
		System.out.println(texts2);
		
		String email = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
	      StringBuilder emailSub = new StringBuilder();
	      java.util.Random rndemail = new java.util.Random();
	      while (emailSub.length() < 10) { // length of the random string.
	          int index = (int) (rndemail.nextFloat() * email.length());
	          emailSub.append(email.charAt(index));
	      }
	      String emailStr = emailSub.toString() + "@cdot.in";
		  System.out.println(emailStr);
		
		driver.findElement(By.name("subscriberInfo.email")).click();
		driver.findElement(By.name("subscriberInfo.email")).clear();
		driver.findElement(By.name("subscriberInfo.email")).sendKeys(emailStr);
		WebElement elements3 = driver.findElement(By.name("subscriberInfo.email"));
		String texts3 = elements3.getAttribute("value");
		System.out.println(texts3);
		
		String company = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
	      StringBuilder comSub = new StringBuilder();
	      java.util.Random rndcom = new java.util.Random();
	      while (comSub.length() < 10) { // length of the random string.
	          int index = (int) (rndcom.nextFloat() * company.length());
	          comSub.append(company.charAt(index));
	      }
	      String comStr = comSub.toString();
		  System.out.println(comStr);
		driver.findElement(By.name("subscriberInfo.companyAddress")).click();
		driver.findElement(By.name("subscriberInfo.companyAddress")).clear();
		driver.findElement(By.name("subscriberInfo.companyAddress")).sendKeys(comStr);
		WebElement elements4 = driver.findElement(By.name("subscriberInfo.companyAddress"));
		String texts4 = elements4.getAttribute("value");
		System.out.println(texts4);
		
		driver.findElement(By.name("subscriberInfo.billingAddress")).click();
		driver.findElement(By.name("subscriberInfo.billingAddress")).clear();
		driver.findElement(By.name("subscriberInfo.billingAddress")).sendKeys(comStr);
		WebElement elements5 = driver.findElement(By.name("subscriberInfo.billingAddress"));
		String texts5 = elements5.getAttribute("value");
		System.out.println(texts5);
		
		driver.findElement(By.name("subscriberInfo.domain")).click();
		driver.findElement(By.name("subscriberInfo.domain")).clear();
		driver.findElement(By.name("subscriberInfo.domain")).sendKeys(comStr);
		WebElement elements6 = driver.findElement(By.name("subscriberInfo.domain"));
		String texts6 = elements6.getAttribute("value");
		System.out.println(texts6);
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='createSubForm']/table/tbody/tr[7]/td/input")));
		WebElement element5 =driver.findElement(By.xpath("//*[@id='createSubForm']/table/tbody/tr[7]/td/input"));
		JavascriptExecutor executor5 = (JavascriptExecutor)driver;
		executor5.executeScript("arguments[0].click();", element5);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div")));
		
		System.out.println("Sucess Message after subscriber creation:" +  driver.findElement(By.xpath("//*[@id='content']/div")).getText());
		
		return subsStr;
		
	}
}
