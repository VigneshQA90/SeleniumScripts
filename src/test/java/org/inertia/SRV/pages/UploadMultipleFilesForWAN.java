package org.inertia.SRV.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UploadMultipleFilesForWAN {
	public WebDriver driver;
	public WebDriverWait wait;
	
	public UploadMultipleFilesForWAN(WebDriver driver) {
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
	
	WebElement WANTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'WAN')]")));
	WANTab.click();
	}
	
	
	
	
}
