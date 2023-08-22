package org.inertia.SRV.pages;

import java.util.Calendar;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

public class CreateWAN {

	public WebDriver driver;
	public WebDriverWait wait;
	private By dashWAN = By.xpath("//div[@class='x-treelist-toolstrip']/div[contains(@class,'wan-icon')]");
	private By WANList = By.xpath("//div[text()='WAN List']");
	private By FirstWANID = By.xpath("(//div[@class='x-grid-item-container'])[last()]//table[1]//td[2]");
	private By AddWAN = By.xpath("//div[text()='Add WAN']");
	private By Printoption = By.xpath("//span[text()='Print Options']");
	private By Printbutton = By.xpath("//span[text()='Print With Attachment']");
	//div[text()='Add WAN']

	public CreateWAN(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 25);
	}

	public void openWANList() throws InterruptedException {
		WebElement dashboardWAN = wait.until(ExpectedConditions.visibilityOfElementLocated(dashWAN));

		org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver); // Initialize
																													// actions
																													// here
		actions.moveToElement(dashboardWAN).build().perform();

		WebElement ClickAddWAN = wait.until(ExpectedConditions.visibilityOfElementLocated(AddWAN));
		ClickAddWAN.click();

		Thread.sleep(4000);


	}

	public void AddNewWAN() throws InterruptedException {
		
		Thread.sleep(4000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Click Work Type Drop down
		WebElement workTypeDropdown = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//span[text()='Work Type:']/following::div[contains(@id,'trigger-picker')][1]")));
		workTypeDropdown.click();

		try {
		    WebElement workTypeValue = wait.until(ExpectedConditions.elementToBeClickable(
		            By.xpath("//span[text()='Work Type:']/following::li[@class='x-boundlist-item'][1]")));
		    workTypeValue.click();
		} catch (StaleElementReferenceException e) {
		    // Handle the StaleElementReferenceException as needed
		    // You might want to log the exception or retry locating and clicking the element
		    System.out.println("StaleElementReferenceException occurred. Refinding it here.");

		    // Re-locate the element
		    WebElement workTypeValue = wait.until(ExpectedConditions.elementToBeClickable(
		            By.xpath("//span[text()='Work Type:']/following::li[@class='x-boundlist-item'][1]")));
		    workTypeValue.click();
		}

		// Click Work Title Input
		WebElement workTitleInput = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='title']")));
		workTitleInput.click();
		Thread.sleep(4000);
		
		java.util.Random random = new java.util.Random();
		int randomNum = random.nextInt((int) Math.pow(10, 3));
		String randomDigits = String.format("%03d", randomNum);

		String Title = "QAAutoWAN" + randomDigits;

		workTitleInput.sendKeys(Title);
		Thread.sleep(2000);

		// Click Work Description
		WebElement workDescription = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name='description']")));
		workDescription.click();
		workDescription.sendKeys("Test");
		Thread.sleep(3000);

		// Click Work Location
		WebElement workLocation = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@name='location']")));
		workLocation.click();
		workLocation.sendKeys("Test Location ");
		Thread.sleep(3000);

		// Click Permit Dropdown
		WebElement permitDropdown = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//span[text()='Permit:']/following::div[contains(@id,'trigger-picker')][1]")));
		permitDropdown.click();
		Thread.sleep(3000);

		// Select Permit Value
		WebElement permitValue = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Permit:']/following::ul[2]/li[1]")));
		//permitValue.click();
		js.executeScript("arguments[0].click();", permitValue);
		Thread.sleep(3000);
		// Click Installing Company Dropdown
		WebElement installingCompanyDropdown = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//span[text()='Installing company:']/following::div[contains(@id,'trigger-picker')][1]")));
		installingCompanyDropdown.click();
		Thread.sleep(3000);

		// Select Installing Company Value
		WebElement installingCompanyValue = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[text()='Installing company:']/following::ul[3]/li[1]")));
		installingCompanyValue.click();
		Thread.sleep(3000);

		// Click Work Start Date
		WebElement workStartDate = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='workStartDate']")));
		workStartDate.click();
		Thread.sleep(3000);
		WebElement datePickerInput = driver
				.findElement(By.xpath("(//span[text()='Work Start Date:']/following::input)[1]"));
		// Get the current date
		java.time.LocalDate currentDate = java.time.LocalDate.now();

		// Add 6 days to the current date
		java.time.LocalDate futureDate = currentDate.plusDays(6);

		// Format the future date as per the date picker format
		java.time.format.DateTimeFormatter dateFormatter = java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedFutureDate = futureDate.format(dateFormatter);

		// Enter the future date into the date picker input field
		datePickerInput.sendKeys(formattedFutureDate);
		Thread.sleep(3000);

		// Click Work Start Time
		WebElement workStartTime = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='workStartTime']")));
		workStartTime.click();
		Thread.sleep(3000);
		// Set the timezone to PST
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

		System.out.println("Current PST time is : " + selectedTime);
		  String time = selectedTime ;
		  
		  workStartTime.sendKeys(time);
		  WebElement label = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Work End Date:']")));
			label.click();
		Thread.sleep(3000);
		// Click Work End Date
		WebElement workEndDate = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='workEndDate']")));
		workEndDate.click();
		Thread.sleep(3000);
		WebElement datePickerInput1 = driver
				.findElement(By.xpath("(//span[text()='Work End Date:']/following::input)[1]"));
		// Get the current date
		java.time.LocalDate currentDate1 = java.time.LocalDate.now();

		// Add 6 days to the current date
		java.time.LocalDate futureDate1 = currentDate1.plusDays(6);

		// Format the future date as per the date picker format
		java.time.format.DateTimeFormatter dateFormatter1 = java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formattedFutureDate1 = futureDate1.format(dateFormatter1);

		// Enter the future date into the date picker input field
		datePickerInput1.sendKeys(formattedFutureDate1);
		Thread.sleep(3000);
		// Click Work End Time
		WebElement workEndTime = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='workEndTime']")));
		workEndTime.click();
		workEndTime.sendKeys(time);
	
		label.click();
		
		
		Thread.sleep(3000);
		// Click Submittal Input
		WebElement submittalInput = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='submittal']")));
		submittalInput.click();
		submittalInput.sendKeys("Test");
		Thread.sleep(3000);
		// Click Change Order Input
		WebElement changeOrderInput = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='changeOrder']")));
		changeOrderInput.click();
		changeOrderInput.sendKeys("test");
		WebElement RFIInput = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='rfi']")));
		RFIInput.click();
		RFIInput.sendKeys("test");
		
		Thread.sleep(3000);

	}
}
