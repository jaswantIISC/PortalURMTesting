package PortalURM.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertHandler {


	public static String alert(WebDriver driver){
		WebDriverWait wait = new WebDriverWait(driver, 2);
		
		String responseText="";
	
		if(isAlertPresent( driver )){
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			responseText = alert.getText();
			System.out.println("responseText : "+ responseText);
			alert.accept();
		}
		
		return responseText;
	}
	
	public static boolean isAlertPresent(WebDriver driver ){
		 try{
		  
			 driver.switchTo().alert();	 
			 return true;
		
		 }catch(NoAlertPresentException ex){
		 
			 return false;
		 }
		// return false;
	}

}
