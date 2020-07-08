package ssm.commands.Invoker;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkSSAR {

	@Test
	public void testDriver(String subscriber, String ssp) throws InterruptedException{
		
		Thread.sleep(2000);

		WebDriver driver = InvokerSsm.getDriver();

		WebDriverWait wait = new WebDriverWait(driver,60);

		System.out.println(driver.findElement(By.id("SSARCommand")).getTagName());

		driver.findElement(By.id("SSARCommand")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Link SSAR")));
		System.out.println(driver.getCurrentUrl());

		driver.findElement(By.id("Link SSAR")).click();


		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		Select subscriberEle = new Select(driver.findElement(By.id("selectedSubscriber")));
		subscriberEle.selectByVisibleText(subscriber);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='selectedSspEle']/option[1]")));
		Select sspEle = new Select(driver.findElement(By.id("selectedSspEle")));
		sspEle.selectByVisibleText(ssp);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='createSSARForm']/table/tbody/tr[4]/td[2]/button")));		
		driver.findElement(By.xpath("//*[@id='createSSARForm']/table/tbody/tr[4]/td[2]/button")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-multiselect-selectedSsarEle-option-0']")));		
		driver.findElement(By.xpath("//*[@id='ui-multiselect-selectedSsarEle-option-0']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-multiselect-selectedSsarEle-option-1']")));		
		driver.findElement(By.xpath("//*[@id='ui-multiselect-selectedSsarEle-option-1']")).click();

		driver.findElement(By.xpath("//*[@id='createSSARForm']/table/tbody/tr[4]/td[2]/button")).click();


		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-select-all")));
		driver.findElement(By.id("example-select-all")).click();


		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='Create']")));
		WebElement element5 =driver.findElement(By.xpath("//*[@id='Create']"));
		JavascriptExecutor executor5 = (JavascriptExecutor)driver;
		executor5.executeScript("arguments[0].click();", element5);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("successMessageDiv")));
		System.out.println("Sucess Message is ::" +  driver.findElement(By.xpath("//*[@id='content']/div")).getText());

	}
}
