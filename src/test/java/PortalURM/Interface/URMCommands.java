package PortalURM.Interface;

import java.util.List;

import org.openqa.selenium.WebDriver;

import net.sf.json.JSONObject;

public interface URMCommands {

	public void createCommand(WebDriver driver, JSONObject user, JSONObject role) throws InterruptedException ;
	public void viewCommand(WebDriver driver, JSONObject user) throws InterruptedException ;
	public void deleteCommand(WebDriver driver, List<String> list) throws InterruptedException ;
}
