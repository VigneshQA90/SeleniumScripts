package org.inertia.SRV.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UploadFilesForInspecReq {
	public WebDriver driver;
	public WebDriverWait wait;
	
	public UploadFilesForInspecReq(WebDriver driver) {
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
		String path6 = "C:/Users/Vigneshwaran/Desktop/SELENIUM UPLOAD/TIO I220012-19-01 Approved.pdf";
		String path7 = "C:/Users/Vigneshwaran/Desktop/SELENIUM UPLOAD/P5 Sheet Metal Pan OSFM Map.pdf";

		WebElement uploadButton = driver.findElement(By.xpath("(//input[@type='file'])[last()]"));
		Thread.sleep(2000);
		
	
	//	actions.clickAndHold(uploadButton).release().perform();

		// Send the file paths
		uploadButton.sendKeys(path1 + "\n" + path2 + "\n" + path3 + "\n" + path4 + "\n" + path5 + "\n" + path6 + "\n" + path7 );
		Thread.sleep(15000);
		
	WebElement Savebutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Save']")));
	Savebutton.click();
	Thread.sleep(3000);
	
	WebElement IRtab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'IR :')]")));
	IRtab.click();
	Thread.sleep(3000);

    try {
       
           // Find all elements matching the given XPath
           List<WebElement> elements = driver.findElements(By.xpath("(//div[contains(@class,'x-panel inertia-margin-top')])[6]/div//table//a"));

           // Check if the number of elements is 7
           if (elements.size() != 7) {
               throw new RuntimeException("Some problem in Uploading files");
           } else {
               System.out.println("All good! Number of files Uploaded : " + elements.size());
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
	

	}
	public void openFirstIRID( ) throws InterruptedException {
		WebElement dashboardIR = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='x-treelist-toolstrip']/div[contains(@class,'inspection-icon')]")));

		org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver); // Initialize
																													// actions
																													// here
		actions.moveToElement(dashboardIR).build().perform();

		WebElement IRList = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='IR Main List']")));
		IRList.click();

		WebElement FirstIRID = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class='code'])[1]")));
		FirstIRID.click();
		
		Thread.sleep(4000);
		}
	
	
	
	
}
