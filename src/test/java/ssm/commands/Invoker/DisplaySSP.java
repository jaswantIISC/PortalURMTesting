package ssm.commands.Invoker;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DisplaySSP {

	@Test
	public void testDriver(String subscriber, String ssp) throws InterruptedException{
	Thread.sleep(2000);

	WebDriver driver = InvokerSsm.getDriver();

	WebDriverWait wait = new WebDriverWait(driver,60);


	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SSPCommand")));



	System.out.println(driver.findElement(By.id("SSPCommand")).getTagName());

	driver.findElement(By.id("SSPCommand")).click();


	driver.findElement(By.id("Display SSP")).click();


	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='selectedSubscriber']/option[1]")));
	Select displaysubscriber = new Select(driver.findElement(By.id("selectedSubscriber")));
	displaysubscriber.selectByVisibleText(subscriber);
	
	
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='selectedSsp']/option[1]")));
	Select sspEle = new Select(driver.findElement(By.id("selectedSsp")));
	sspEle.selectByVisibleText(ssp);	
	
	//ssp.selectByVisibleText("C-DOT SERVICE SUBSCRIPTION");
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='sspName']")));
	System.out.println("SSP is:" + driver.findElement(By.xpath("//*[@id='sspName']")).getText());
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='displaySubStatus']")));
	System.out.println("Status of SSP: " + driver.findElement(By.xpath("//*[@id='displaySubStatus']")).getText());
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-id-1']")));
	System.out.println("Section 1: " + driver.findElement(By.xpath("//*[@id='ui-id-1']")).getText());
	
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='addedGeneralProperties']")));
	
	WebElement table1 = driver.findElement(By.xpath("//*[@id='addedGeneralProperties']"));

	  //To locate rows of table.
	  List < WebElement > rows_table1 = table1.findElements(By.tagName("tr"));

	  //To calculate no of rows In table.
	  int rows_count = rows_table1.size();


	  //Loop will execute for all the rows of the table
	  for (int row = 0; row < rows_count; row++) {

	   //To locate columns(cells) of that specific row.
	   List < WebElement > Columns_row = rows_table1.get(row).findElements(By.tagName("td"));

	   //To calculate no of columns(cells) In that specific row.
	   int columns_count = Columns_row.size();
	 
	   
	   //Loop will execute till the last cell of that specific row.
	   for (int column = 0; column < columns_count; column++) {
	    //To retrieve text from the cells.
	    String celltext = Columns_row.get(column).getText();
	    System.out.println("Cell Value Of row number " + row + " and column number " + column + " Is " + celltext);
	   }
	  }
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	


	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-id-3']")));
	driver.findElement(By.xpath("//*[@id='ui-id-3']")).click();
	
	
	
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	

	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-id-3']")));
	System.out.println("section 2 : " + driver.findElement(By.xpath("//*[@id='ui-id-3']")).getText());
	

	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	  
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-id-5']")));
	driver.findElement(By.xpath("//*[@id='ui-id-5']")).click();
	
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	

	System.out.println("section 3 : " + driver.findElement(By.xpath("//*[@id='ui-id-5']")).getText());

	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	


	}
}
