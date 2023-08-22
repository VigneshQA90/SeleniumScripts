package org.inertia.SRV.testcases;

import org.inertia.SRV.pages.LoginPage;
import org.inertia.SRV.pages.OBSPDFReader;
import org.inertia.SRV.pages.OBSPDFReaderWithImgComp;
import org.inertia.SRV.pages.ObsPrintOptions;
import org.inertia.SRV.pages.Observation;
import org.inertia.SRV.pages.UploadFilesForObservation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC003_SRV_ObservationPrintFunctionality {
									
	
	
	public static void main(String[] args) throws Exception {
	
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	 driver.get("https://vignesh-v3-dev-internal.inertia.systems/");
	driver.manage().window().maximize();	
	
	LoginPage login = new LoginPage(driver);
	login.loginInertia("inertiaadmin", "devdb");
	
	Observation obs = new Observation(driver);
	obs.openObservationList();
	obs.CreateObservation();
	Thread.sleep(2000);
	UploadFilesForObservation upload = new UploadFilesForObservation(driver);
	upload.uploadfiles();
	//upload.openFirstObsID();
	Thread.sleep(2000);
	ObsPrintOptions print = new ObsPrintOptions(driver);
	print.clickOBSPrintButtonAndDownloadPrintPDFFile();
	Thread.sleep(5000);
//	OBSPDFReader reader = new OBSPDFReader(driver);
//	reader.OpenPDFAndVerifyContent();
//	OBSPDFReaderWithImgComp comp = new OBSPDFReaderWithImgComp(driver);
//	comp.OpenPDFAndVerifyContent();
	

	OBSPDFReaderWithImgComp comp = new OBSPDFReaderWithImgComp(driver);
	comp.OpenPDFAndVerifyContent();
	Thread.sleep(2000);
	driver.quit();


}
}
