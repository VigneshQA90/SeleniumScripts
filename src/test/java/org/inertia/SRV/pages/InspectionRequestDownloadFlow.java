package org.inertia.SRV.pages;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InspectionRequestDownloadFlow {

	public WebDriver driver;
	public WebDriverWait wait;

	public InspectionRequestDownloadFlow(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 25);
	}

	public void DownloadWANFIleAndVerifyDownloadedFileInDownloadFolder() throws InterruptedException {
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

		WebElement UploadedFileFirstLink = wait.until(org.openqa.selenium.support.ui.ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Uploaded File')]/following::a[1]")));
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
