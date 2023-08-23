package org.inertia.SRV.testcases;

import org.inertia.SRV.pages.InspReqPDFReaderWithImgComparisonFORCLOSINGDOC;
import org.inertia.SRV.pages.InspecReqPDFReader;
import org.inertia.SRV.pages.InspecReqPrintOptions;
import org.inertia.SRV.pages.InspectionRequest;
import org.inertia.SRV.pages.LoginPage;
import org.inertia.SRV.pages.UploadFilesForInspecReq;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class TC005_SRV_InspectionRequestPrintFunctionality {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		 driver.get("https://vignesh-v3-dev-internal.inertia.systems/");
		driver.manage().window().maximize();	
		
		LoginPage login = new LoginPage(driver);
		login.loginInertia("inertiaadmin", "devdb");
		InspectionRequest ir = new InspectionRequest(driver);
	    ir.openIRMainList();
		ir.CreateNewIR();
		
		UploadFilesForInspecReq upload = new UploadFilesForInspecReq(driver);
		upload.uploadfiles();
									
		
	//	upload.openFirstIRID();
		
		InspecReqPrintOptions print = new InspecReqPrintOptions(driver);
		print.clickIRPrintButtonAndDownloadPrintPDFFile();
		
//		InspecReqPDFReader reader = new InspecReqPDFReader(driver);		
		// reader.OpenPDFAndVerifyContent();
		

		
		InspReqPDFReaderWithImgComparisonFORCLOSINGDOC close = new InspReqPDFReaderWithImgComparisonFORCLOSINGDOC(driver);
		close.OpenPDFAndVerifyContent();
		driver.quit();

}
}
