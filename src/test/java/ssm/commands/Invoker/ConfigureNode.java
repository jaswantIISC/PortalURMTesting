package ssm.commands.Invoker;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfigureNode {

	@Test
	public void testDriver(String subscriber, String ssp) throws InterruptedException{

		Thread.sleep(2000);

		WebDriver driver = InvokerSsm.getDriver();

		WebDriverWait wait = new WebDriverWait(driver,60);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("NODETOPOLOGYCommand")));
		
		System.out.println(driver.findElement(By.id("NODETOPOLOGYCommand")).getTagName());

		driver.findElement(By.id("NODETOPOLOGYCommand")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Configure Node Topology")));
		System.out.println(driver.getCurrentUrl());

		driver.findElement(By.id("Configure Node Topology")).click();


		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		Select subscriberEle = new Select(driver.findElement(By.id("selectedSubscriber")));
		subscriberEle.selectByVisibleText(subscriber);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='sspSelEle']/option[1]")));
		Select sspEle = new Select(driver.findElement(By.id("sspSelEle")));
		sspEle.selectByVisibleText(ssp);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='nodeTypeSelEle']/option[2]")));
		Select nodeType = new Select(driver.findElement(By.id("nodeTypeSelEle")));
		nodeType.selectByVisibleText("ADN");


		//*[@id="nodeTypeSelEle"]/option[2]

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='registrarCseEle']/option[2]")));
		Select registrar = new Select(driver.findElement(By.id("registrarCseEle")));
		registrar.selectByIndex(1);


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='mgmtTechEle']/option[2]")));
		Select mgmtTech = new Select(driver.findElement(By.id("mgmtTechEle")));
		mgmtTech.selectByIndex(1);


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-select-all")));
		driver.findElement(By.id("example-select-all")).click();

		//*[@id="count"]

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("count")));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='configureNodeForm']/table/tbody/tr[6]/td[2]/button")));
		driver.findElement(By.xpath("//*[@id='configureNodeForm']/table/tbody/tr[6]/td[2]/button")).click();


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-multiselect-addSsarEle-option-0']")));
		driver.findElement(By.xpath("//*[@id='ui-multiselect-addSsarEle-option-0']")).click();



		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='configureNodeSection']/div[2]/table/tbody/tr[2]/td/input")));
		WebElement element5 =driver.findElement(By.xpath("//*[@id='configureNodeSection']/div[2]/table/tbody/tr[2]/td/input"));
		JavascriptExecutor executor5 = (JavascriptExecutor)driver;
		executor5.executeScript("arguments[0].click();", element5);


		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("successMessageDiv")));
		System.out.println("Sucess Message is ::" +  driver.findElement(By.xpath("//*[@id='content']/div")).getText());
	}

}
