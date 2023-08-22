package org.inertia.SRV.testcases;

import java.awt.AWTException;

import org.inertia.SRV.pages.InspectionRequestDownloadFlow;
import org.inertia.SRV.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC004_SRV_InspecRequestVerifyDownloadFunctionality {
	
	public static void main(String[] args) throws InterruptedException, AWTException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		 driver.get("https://vignesh-v3-dev-internal.inertia.systems/");
		driver.manage().window().maximize();	
		
		LoginPage login = new LoginPage(driver);
		login.loginInertia("inertiaadmin", "devdb");
		InspectionRequestDownloadFlow df = new InspectionRequestDownloadFlow(driver);
		df.DownloadWANFIleAndVerifyDownloadedFileInDownloadFolder();
		driver.quit();
	}

}
