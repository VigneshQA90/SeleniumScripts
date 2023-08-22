package org.inertia.SRV.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Observation {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	public Observation(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 25);
	}
	public void openObservationList() throws InterruptedException {
		WebElement dashboardObs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='x-treelist-toolstrip']/div[contains(@class,'observation-icon')]")));

		org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver); // Initialize
																													// actions
																													// here
		actions.moveToElement(dashboardObs).build().perform();
		Thread.sleep(1000);
		WebElement ClickAddItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Observation List']/preceding::div[text()='Add Item'][1]")));
		ClickAddItem.click();

		Thread.sleep(4000);


	}
	
	public void CreateObservation() {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
        // Click ObsType
        WebElement obsType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='typeName']")));
        obsType.click();

        // Click ObsTypeValue
        WebElement obsTypeValue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@name='typeName']/following::div[contains(@class,'x-boundlist-list-ct x')])[last()]//li[1]")));
        obsTypeValue.click();

        // Click ObsReason
        WebElement obsReason = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='reason']")));
        obsReason.click();

        // Click ObsReasonValue
        WebElement obsReasonValue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='reason']/following::ul[@role='listbox'][2]//li[1]")));
  //      obsReasonValue.click();
         js.executeScript("arguments[0].click();", obsReasonValue);

        // Click ObsLevel
        WebElement obsLevel = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@name='level'])[last()]")));
        obsLevel.click();

        // Click ObsLevelValue
        WebElement obsLevelValue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@name='level'])[last()]/following::ul[@role='listbox'][3]//li[1]")));
       // obsLevelValue.click();
        js.executeScript("arguments[0].click();", obsLevelValue);

        // Click ObsPermit
        WebElement obsPermit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Permit']")));
        obsPermit.click();

        // Click ObsPermitValue
        WebElement obsPermitValue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Permit']/following::ul[@role='listbox'][4]//li[1]")));
        obsPermitValue.click();

        // Click ObsInstallingComp
        WebElement obsInstallingComp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Company']")));
        obsInstallingComp.click();

        // Click ObsInstallingCompValue
        WebElement obsInstallingCompValue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Select Company']/following::ul[@role='listbox'][5]//li[1]")));
        obsInstallingCompValue.click();

        // Click ObservationLocation
        WebElement observationLocation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Location:']/following::input)[1]")));
        observationLocation.click();
        observationLocation.sendKeys("Test lOcation");

        // Click ObsSubjectInput
        WebElement obsSubjectInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Subject:']/following::input)[1]")));
        obsSubjectInput.click();
		
		java.util.Random random = new java.util.Random();
		int randomNum = random.nextInt((int) Math.pow(10, 3));
		String randomDigits = String.format("%03d", randomNum);

		String Title = "QAAutoOBS" + randomDigits;
		obsSubjectInput.sendKeys(Title);
	}
	
	
	
	
}
