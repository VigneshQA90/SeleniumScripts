package org.inertia.SRV.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UploadFilesForObservation {
	public WebDriver driver;
	public WebDriverWait wait;
	
	public UploadFilesForObservation(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 25);
	}
	
	public void uploadfiles() throws InterruptedException {
		WebElement element = driver.findElement(By.xpath("//input[@type='file']"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;


        // Execute JavaScript to scroll to the element
       jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        
		String path1 = "C:/Users/Vigneshwaran/Desktop/SELENIUM UPLOAD/PhotoImg1.png";
		String path2 = "C:/Users/Vigneshwaran/Desktop/SELENIUM UPLOAD/PhotoImg2.jpg";
		String path3 = "C:/Users/Vigneshwaran/Desktop/SELENIUM UPLOAD/docxbytesSize.docx";
		String path4 = "C:/Users/Vigneshwaran/Desktop/SELENIUM UPLOAD/NewCSVTestFile.csv";
		String path5 = "C:/Users/Vigneshwaran/Desktop/SELENIUM UPLOAD/OrignalNamePDF.pdf";

		WebElement uploadButton = driver.findElement(By.xpath("(//input[@type='file'])[last()]"));
		Thread.sleep(2000);
		
	
	//	actions.clickAndHold(uploadButton).release().perform();

		// Send the file paths
		uploadButton.sendKeys(path1 + "\n" + path2 + "\n" + path3 + "\n" + path4 + "\n" + path5);
		Thread.sleep(8000);
		
	WebElement Savebutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Save']")));
	Savebutton.click();
	Thread.sleep(3000);
	//span[contains(text(),'OB :')]
	WebElement Obstab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'OB :')]")));
	Obstab.click();
	

	}
	public void openFirstObsID( ) throws InterruptedException {
		 WebElement dashObs = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='x-treelist-toolstrip']/div[contains(@class,'observation-icon')]")));
	     
			org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver); // Initialize
			// actions
			// here
	             actions.moveToElement(dashObs).build().perform();

		WebElement OBSList = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Observation List']")));
		OBSList.click();

		WebElement FirstOBSID = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='x-grid-item-container'])[last()]//table[1]//td[2]")));
		FirstOBSID.click();
		
		Thread.sleep(4000);
		}
	
	
	
	
}
