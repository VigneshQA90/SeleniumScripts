package org.inertia.SRV.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DirectlyClickPrint {
	public WebDriver driver;
	public WebDriverWait wait;
	
	public DirectlyClickPrint(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 25);
	}
	
	public void openFirstWanid( ) throws InterruptedException {
	 WebElement dashWAN = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='x-treelist-toolstrip']/div[contains(@class,'wan-icon')]")));
     
		org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver); // Initialize
		// actions
		// here
             actions.moveToElement(dashWAN).build().perform();

	WebElement WANList = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='WAN List']")));
	WANList.click();

	WebElement FirstWANID = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='x-grid-item-container'])[last()]//table[1]//td[2]")));
	FirstWANID.click();
	
	Thread.sleep(4000);
	}
}
