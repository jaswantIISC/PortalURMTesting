package ssm.commands.Invoker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateSSP {


	@Test
	public String testDriver(String subscriber) throws InterruptedException{

		Thread.sleep(2000);

		WebDriver driver = InvokerSsm.getDriver();

		WebDriverWait wait = new WebDriverWait(driver,60);


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SSPCommand")));

		System.out.println(driver.findElement(By.id("SSPCommand")).getTagName());

		driver.findElement(By.id("SSPCommand")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Create SSP")));
		System.out.println(driver.getCurrentUrl());

		driver.findElement(By.id("Create SSP")).click();


		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		Select subscriber_ssp = new Select(driver.findElement(By.tagName("select")));
		subscriber_ssp.selectByVisibleText(subscriber);


		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	


		String sspName = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder sspString = new StringBuilder();
		java.util.Random rndssp = new java.util.Random();
		while (sspString.length() < 10) { // length of the random string.
			int index = (int) (rndssp.nextFloat() * sspName.length());
			sspString.append(sspName.charAt(index));
		}
		String sspStr = sspString.toString();
		System.out.println(sspStr);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subscriptionNameEle")));
		driver.findElement(By.id("subscriptionNameEle")).click();
		driver.findElement(By.id("subscriptionNameEle")).sendKeys(sspStr);
		WebElement elementsSSP = driver.findElement(By.id("subscriptionNameEle"));
		String textssp = elementsSSP.getAttribute("value");
		System.out.println(textssp);


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("agreementDateEle")));
		WebElement dateWidget = driver.findElement(By.id("agreementDateEle"));
		dateWidget.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		String [] dateSplit = dtf.format(now).split("/");

		if(dateSplit[2].startsWith("0"))
		{
			dateSplit[2] = dateSplit[2].substring(1);
		}
		//	driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[4]/td[7]/a")).click();


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-datepicker-div")));
		WebElement date = driver.findElement(By.id("ui-datepicker-div"));

		List<WebElement> columns=date.findElements(By.tagName("td"));
		System.out.println("size of date is:: "+ columns.size());




		for (WebElement cell: columns){
			//Select Date 
			if (cell.getText().equals(dateSplit[2])){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				cell.findElement(By.linkText(dateSplit[2])).click();
				break;

			}
		}



		String numssp = "1234567890";
		StringBuilder numstr = new StringBuilder();
		java.util.Random rndnumssp = new java.util.Random();
		while (numstr.length() < 3) { // length of the random string.
			int index = (int) (rndnumssp.nextFloat() * numssp.length());
			numstr.append(numssp.charAt(index));
		}
		String numsspStr = numstr.toString();

		System.out.println(numsspStr);


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("maxNodesNumEle")));
		driver.findElement(By.id("maxNodesNumEle")).click();
		driver.findElement(By.id("maxNodesNumEle")).clear();
		driver.findElement(By.id("maxNodesNumEle")).sendKeys(numsspStr);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("maxAppsNumEle")));


		driver.findElement(By.id("maxAppsNumEle")).click();
		driver.findElement(By.id("maxAppsNumEle")).clear();
		driver.findElement(By.id("maxAppsNumEle")).sendKeys(numsspStr);



		driver.findElement(By.xpath("//*[@id='nodePropertiesForm']/table/tbody/tr[6]/td[2]/button/span[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[3]/div/ul/li[1]/a/span[2]")));
		driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[1]/a/span[2]")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	


		System.out.println("hello");



		driver.findElement(By.xpath("//*[@id='step1Button']")).click();


		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='button'][value='Skip']")));


		driver.findElement(By.cssSelector("input[type='button'][value='Skip']")).click();


		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/div/div/input[1]")));
		WebElement elementssp =driver.findElement(By.xpath("//*[@id='content']/div/div/input[1]"));
		JavascriptExecutor executorssp = (JavascriptExecutor)driver;
		executorssp.executeScript("arguments[0].click();", elementssp);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("successMessageDiv")));
		System.out.println("Sucess Message after subscription creation is ::" +  driver.findElement(By.xpath("//*[@id='content']/div")).getText());
		
		return sspStr;
	}
}