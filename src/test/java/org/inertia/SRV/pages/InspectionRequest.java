package org.inertia.SRV.pages;

import java.util.Calendar;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class InspectionRequest {

	public WebDriver driver;
	public WebDriverWait wait;

	public InspectionRequest(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 25);
	}

	public void openIRMainList() throws InterruptedException {
		WebElement dashboardIR = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='x-treelist-toolstrip']/div[contains(@class,'inspection-icon')]")));

		org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver); // Initialize
																													// actions
																													// here
		actions.moveToElement(dashboardIR).build().perform();
		Thread.sleep(1000);
		WebElement ClickAddIR = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Add Inspection']")));
		ClickAddIR.click();

		Thread.sleep(4000);

	}

	public void CreateNewIR() {
		// Wait for the IRPermit element to be clickable and click it
		WebElement irPermit = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='subStructure']")));
		irPermit.click();

		// Wait for the IRPermitValue element to be clickable and click it
		WebElement irPermitValue = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//span[text()='Permit:']/following::li[@class='x-boundlist-item'][1]")));
		irPermitValue.click();

		// Wait for the IRInstallCO element to be clickable and click it
		WebElement irInstallCO = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='installingCo']")));
		irInstallCO.click();

		// Wait for the IRInstallCOValue element to be clickable and click it
		WebElement irInstallCOValue = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//input[@name='installingCo']/following::ul[@role='listbox'][2]//li[1]")));
		irInstallCOValue.click();

		// Wait for the CommonIRType element to be clickable and click it
		WebElement commonIRType = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Common IR Type:']/following::input[@name='type']")));
		commonIRType.click();

		WebElement element = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(
				By.xpath("//span[text()='Common IR Type:']/following::li[@class='x-boundlist-item'][last()]")));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);

		// Wait for the IRInspectionDetail element to be clickable and enter text
		WebElement irInspectionDetail = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name='detail']")));
		String timeStamp = new java.text.SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		String Title = "QAAutoIR" + timeStamp;
		irInspectionDetail.sendKeys(Title);
		
		java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("PST"));
		// Create a Calendar instance for the current time in PST
		java.util.Calendar cal = java.util.Calendar.getInstance(TimeZone.getTimeZone("PST"));
		// Set the minute field to 0 and add 1 hour 30 minutes to the calendar
		cal.set(Calendar.MINUTE, 0);
		cal.add(Calendar.HOUR_OF_DAY, 1);
		cal.add(Calendar.MINUTE, 30);
		// Format the selected time in the desired format of "10:00 PM"
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("h:mm a");
		String selectedTime = dateFormat.format(cal.getTime());
		
		
		WebElement InspReqRequestedTime = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='requestTime']")));
		InspReqRequestedTime.sendKeys(selectedTime);

	}

}
