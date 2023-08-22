package org.inertia.SRV.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	   public WebDriver driver;
	    private By loginUsername = By.xpath("//input[@name='identity']");
	    private By loginPwd = By.xpath("//input[@name='password']");
	    private By loginButton = By.xpath("//*[contains(text(),'Login')]");
	    private By ProjectDD = By.xpath("(//div[@class='x-form-text-wrap x-form-text-wrap-default']/following-sibling::div)[1]");
	    private By SelectProj = By.xpath("//li[text()='Test Project 1']");
	    private By dashWAN = By.xpath("//div[@class='x-treelist-toolstrip']/div[contains(@class,'wan-icon')]");
	    private By WANList = By.xpath("//div[text()='WAN List']");
	    private By FirstWANID = By.xpath("(//div[@class='x-grid-item-container'])[last()]//table[1]//td[2]");
	    private WebDriverWait wait;

	    public LoginPage(WebDriver driver) {
	        this.driver = driver;
	        wait = new WebDriverWait(driver, 25);
	    }

	    public void loginInertia(String username, String password) throws InterruptedException {
	        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(loginUsername));
	        usernameField.click();
	        usernameField.sendKeys(username);
	        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPwd));
	        passwordField.sendKeys(password);
	        WebElement loginButtonElement = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
	        loginButtonElement.click();
	        Thread.sleep(10000);
	        WebElement ProjectDDoption = wait.until(ExpectedConditions.elementToBeClickable(ProjectDD));
	        ProjectDDoption.click();
	        WebElement SelectProject = wait.until(ExpectedConditions.elementToBeClickable(SelectProj));
	        SelectProject.click();
	        Thread.sleep(4000);
	
	        
	    }

}
