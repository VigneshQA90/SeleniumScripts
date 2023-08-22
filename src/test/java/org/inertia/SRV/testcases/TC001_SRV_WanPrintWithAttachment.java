package org.inertia.SRV.testcases;

import org.inertia.SRV.pages.CreateWAN;
import org.inertia.SRV.pages.DirectlyClickPrint;
import org.inertia.SRV.pages.LoginPage;
import org.inertia.SRV.pages.UploadMultipleFilesForWAN;
import org.inertia.SRV.pages.WANPDFReaderWithImgComp;
import org.inertia.SRV.pages.WANPrintOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC001_SRV_WanPrintWithAttachment {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();								
		
		
		 driver.get("https://vignesh-v3-dev-internal.inertia.systems/");
		driver.manage().window().maximize();	
					
		
		
		LoginPage login = new LoginPage(driver);
		login.loginInertia("inertiaadmin", "devdb");
		
//		DirectlyClickPrint click = new DirectlyClickPrint(driver);
//		click.openFirstWanid();
		
		CreateWAN wan = new CreateWAN(driver);
		wan.openWANList();
		wan.AddNewWAN();
		Thread.sleep(2000);
		UploadMultipleFilesForWAN upload = new UploadMultipleFilesForWAN(driver);
		upload.uploadfiles();
	
		Thread.sleep(2000);
		WANPrintOptions print = new WANPrintOptions(driver);
		print.clickWANPrintButtonAndDownloadPrintPDFFile();
		Thread.sleep(5000);
		WANPDFReaderWithImgComp reader = new WANPDFReaderWithImgComp(driver);
		reader.OpenPDFAndVerifyContent();
		Thread.sleep(2000);
		driver.quit();
		
		
		
		
		
		

	}

}
