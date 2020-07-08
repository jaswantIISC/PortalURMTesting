package ssm.commands.Invoker;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DIsplaySSN {

	@Test
	public void testDriver(String subscriber, String ssp) throws InterruptedException{
		
		Thread.sleep(2000);

		WebDriver driver = InvokerSsm.getDriver();

		WebDriverWait wait = new WebDriverWait(driver,60);

		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SSNCommand")));
		 
		 driver.findElement(By.id("SSNCommand")).click();
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Display SSN")));
		 System.out.println(driver.getCurrentUrl());

		 driver.findElement(By.id("Display SSN")).click();

		 try {
		 Thread.sleep(2000);
		 } catch (InterruptedException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }

		  Select displayssnSubscriber = new Select(driver.findElement(By.tagName("select")));
		  displayssnSubscriber.selectByVisibleText(subscriber);


		 try {
		 Thread.sleep(2000);
		 } catch (InterruptedException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }

		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='selectedSspEle']")));

		 try {
		 Thread.sleep(2000);
		 } catch (InterruptedException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }

		 Select displayssnssp = new Select(driver.findElement(By.id("selectedSspEle")));
		 displayssnssp.selectByVisibleText(ssp);

		 try {
		 Thread.sleep(2000);
		 } catch (InterruptedException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }


		


		/* wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn btn btn-info sysdownload")));
		 driver.findElement(By.className("btn btn btn-info sysdownload")).click();
*/
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='table1']")));
			
			WebElement table1 = driver.findElement(By.xpath("//*[@id='table1']"));
		
			  List < WebElement > rows_table1 = table1.findElements(By.tagName("tr"));

			  //To calculate no of rows In table.
			  int rows_count = rows_table1.size();


			  //Loop will execute for all the rows of the table
			  for (int row = 0; row < rows_count; row++) {

			   //To locate columns(cells) of that specific row.
			   List < WebElement > Columns_row = rows_table1.get(row).findElements(By.tagName("td"));

			   //To calculate no of columns(cells) In that specific row.
			   int columns_count = Columns_row.size();
			 
			   System.out.println("size of column is :"  + columns_count);

			   //Loop will execute till the last cell of that specific row.
			   for (int column = 0; column < columns_count; column++) {
			    //To retrieve text from the cells.
			    String celltext = Columns_row.get(column).getText();
			    System.out.println("Cell Value Of row number " + row + " and column number " + column + " Is " + celltext);
			    
			    if(column == columns_count-2)
			    {
			    				    	
					   Columns_row.get(column).click();
						 Thread.sleep(2000);

			    }
			   }
			   		   
			  }

	}
}
