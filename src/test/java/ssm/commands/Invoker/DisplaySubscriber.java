package ssm.commands.Invoker;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DisplaySubscriber {

	@Test
	public void testDriver(String subscriber) throws InterruptedException{
		
	Thread.sleep(2000);
		
		WebDriver driver = InvokerSsm.getDriver();
		
		WebDriverWait wait = new WebDriverWait(driver,60);
		
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SubscriberCommand")));
	System.out.println(driver.findElement(By.id("SubscriberCommand")).getTagName());
	
	driver.findElement(By.id("SubscriberCommand")).click();
	

	
	 driver.manage().window().maximize();		
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Display Subscriber")));
	System.out.println(driver.getCurrentUrl());
	
	
	driver.findElement(By.id("Display Subscriber")).click();
	
	System.out.println("hello");
	
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	 Select subscriberEle = new Select(driver.findElement(By.tagName("select")));
	 subscriberEle.selectByVisibleText(subscriber);
	
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 
	 WebElement mytable = driver.findElement(By.xpath(" /html/body/div/div/div[2]/div[2]/div/div/div[2]/div/div/div[2]/div/div/div[2]/table/tbody"));

	  //To locate rows of table.
	  List < WebElement > rows_table = mytable.findElements(By.tagName("tr"));

	  //To calculate no of rows In table.
	  int rows_count = rows_table.size();


	  //Loop will execute for all the rows of the table
	  for (int row = 0; row < rows_count; row++) {

	   //To locate columns(cells) of that specific row.
	   List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));

	   //To calculate no of columns(cells) In that specific row.
	   int columns_count = Columns_row.size();
	 
	   
	   //Loop will execute till the last cell of that specific row.
	   for (int column = 0; column < columns_count; column++) {
	    //To retrieve text from the cells.
	    String celltext = Columns_row.get(column).getText();
	    System.out.println("Cell Value Of row number " + row + " and column number " + column + " Is " + celltext);
	   }
	  }
	
	}
}
