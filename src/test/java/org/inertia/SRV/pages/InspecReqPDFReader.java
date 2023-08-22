
package org.inertia.SRV.pages;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.EnumSet;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InspecReqPDFReader {

	public static WebDriver driver;
	public WebDriverWait wait;
	

	public InspecReqPDFReader(WebDriver driver) {
		InspecReqPDFReader.driver = driver;
		wait = new WebDriverWait(InspecReqPDFReader.driver, 25);
	}
	
	
	public void OpenPDFAndVerifyContent () throws InterruptedException
	{
		Thread.sleep(2000);
		String downloadFolderPath = System.getProperty("user.home") + File.separator + "Downloads";
        File downloadFolder = new File(downloadFolderPath);
        Thread.sleep(2000);
        System.out.println("Download folder path: " + downloadFolder.getAbsolutePath());

        if (downloadFolder.exists() && downloadFolder.isDirectory()) {
            File latestPDF = getLatestPDFFile(downloadFolder.toPath());

            if (latestPDF != null) {
                try {
                    Desktop.getDesktop().open(latestPDF);
                    verifyTextInPDF(latestPDF);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("No PDF files found in the download folder.");
            }
        } else {
            System.out.println("Download folder does not exist.");
        }
    }

    private static File getLatestPDFFile(Path folderPath) throws InterruptedException {
    	Thread.sleep(2000);
        final File[] pdfFiles = new File[1];

        try {
            Files.walkFileTree(folderPath, EnumSet.noneOf(FileVisitOption.class), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toString().toLowerCase().endsWith(".pdf")) {
                        if (pdfFiles[0] == null || getFileCreationTime(file.toFile()) > getFileCreationTime(pdfFiles[0])) {
                            pdfFiles[0] = file.toFile();
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });

            return pdfFiles[0];
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static long getFileCreationTime(File file) {
        try {
            BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            FileTime creationTime = attributes.creationTime();
            return creationTime.toMillis();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static void verifyTextInPDF(File pdfFile) throws IOException {
    	try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String pdfText = pdfTextStripper.getText(document);

            // Create an instance of WANPrintOptions
//            WANPrintOptions wanPrintOptions = new WANPrintOptions(driver);

//            String firstLinkText1 = wanPrintOptions.getFirstLinkText();
//            String secondLinkText1 = wanPrintOptions.getSecondLinkText();
            
            String firstLinkText1 = InspecReqPrintOptions.firstLinkText;
            String secondLinkText1 = InspecReqPrintOptions.secondLinkText;


            boolean containsCsv = pdfText.contains(firstLinkText1);
            boolean containsDocx = pdfText.contains(secondLinkText1);
            boolean containsIR = pdfText.contains("Inspection Request");
            boolean containspdftext = pdfText.contains("Sharp IOR Daily Report - Grossmont");

            if (containsCsv) {
                System.out.println("Text '" + firstLinkText1 + "'  found in the PDF.");
            } else {
                System.out.println("Text '" + firstLinkText1 + "' not found in the PDF.");
            }

            if (containsDocx) {
                System.out.println("Text '" + secondLinkText1 + "'  found in the PDF.");
            } else {
                System.out.println("Text '" + secondLinkText1 + "' not found in the PDF.");
            }

            if (containsIR) {
                System.out.println("Text 'Inspection Request' found in the PDF.");
            } else {
                System.out.println("Text 'Inspection Request' not found in the PDF.");
            }
            if (containspdftext) {
                System.out.println("Text 'Sharp IOR Daily Report - Grossmont' found in the PDF.");
            } else {
                System.out.println("Text 'Sharp IOR Daily Report - Grossmont' not found in the PDF.");
            }
        } catch (IOException e) {
            System.out.println("Error opening PDF file: " + e.getMessage());
        }
    	
       	
    	
    	
    	
    }
    }
	
	
	
	

