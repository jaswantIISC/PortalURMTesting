package PortalURM.Interface;

import org.openqa.selenium.WebDriver;

import net.sf.json.JSONObject;

public interface PortalCommands {

	public WebDriver loginUseCase(WebDriver driver, String user, String password) throws InterruptedException;
	public String loginSuccess(WebDriver driver, String user, String password) throws InterruptedException;
	public void forgotPassword(WebDriver driver, String passwordToSet, String emailId, String password) throws InterruptedException;
	public void logout(WebDriver driver, JSONObject userToCreate) throws InterruptedException;
	public void passwordChange(WebDriver driver, String passwordToSet, String currentPassword) throws InterruptedException;
	public void goToURMModule(WebDriver driver) throws InterruptedException;
				
}
