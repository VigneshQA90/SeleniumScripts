package org.inertia.SRV.pages;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WANDownloadFlow {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	public WANDownloadFlow(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 25);
	}
	
	public void DownloadWANFIleAndVerifyDownloadedFileInDownloadFolder () throws InterruptedException {
		WebElement dashWAN = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='x-treelist-toolstrip']/div[contains(@class,'wan-icon')]")));
		org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
		 actions.moveToElement(dashWAN).build().perform();
		 Thread.sleep(2000);

		 WebElement WanList = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='WAN List']")));
		 WanList.click();
		 Thread.sleep(2000);
		 WebElement FirstWANID = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='x-grid-item-container'])[last()]//table[1]//td[2]")));
		 FirstWANID.click();
		 
		 Thread.sleep(6000);
		 
		 WebElement UploadedFileFirstLink = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Uploaded File')]/following::a[1]")));
		 UploadedFileFirstLink.click();
		 
		 String linkText = UploadedFileFirstLink.getText();
		 String[] parts1 = linkText.split("_");
		 String fname = parts1[0];
		 
		 System.out.println("Text of the first link which is downloaded: " + fname);
	
		

		
		driver.get("https://vignesh-v3-dev-internal.inertia.systems/");
		Thread.sleep(5000);
	 

	// Validate the downloaded file
	 String downloadFolderPath = "C:\\Users\\Vigneshwaran\\Downloads";

	 // Create a Path object for the download folder
	 Path downloadFolder = Paths.get(downloadFolderPath);

	 // Get a list of files in the download folder
	 File[] filesInDownloadFolder = downloadFolder.toFile().listFiles();

	 if (filesInDownloadFolder != null && filesInDownloadFolder.length > 0) {
	     // Sort files based on last modified timestamp in descending order
	     Arrays.sort(filesInDownloadFolder, new Comparator<File>() {
	         @Override
	         public int compare(File file1, File file2) {
	             return Long.compare(file2.lastModified(), file1.lastModified());
	         }
	     });
	     
	     // Get the most recent file (first element after sorting)
	     File mostRecentFile = filesInDownloadFolder[0];
	     System.out.println("Most recent downloaded file: " + mostRecentFile.getName());

	     // Compare fname with the name of the most recent file
	     if (mostRecentFile.getName().contains(fname)) {
	         System.out.println("File downloaded successfully and matches the most recent file.");
	     } else {
	         System.out.println("File downloaded successfully but does not match the most recent file.");
	     }
	 } else {
	     System.out.println("No files found in the download folder.");
	 }
		
	}
	
	
	

}
