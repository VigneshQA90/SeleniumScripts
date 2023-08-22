package org.inertia.SRV.pages;


import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class OBSPDFReaderWithImgComp {

    public static WebDriver driver;
    public WebDriverWait wait;

    public OBSPDFReaderWithImgComp(WebDriver driver) {
    	OBSPDFReader.driver = driver;
        wait = new WebDriverWait(OBSPDFReader.driver, 25);
    }

    public void OpenPDFAndVerifyContent() throws Exception {
    	Thread.sleep(2000);
        String downloadFolderPath = System.getProperty("user.home") + File.separator + "Downloads";
        File downloadFolder = new File(downloadFolderPath);
        Thread.sleep(2000);

        System.out.println("Download folder path: " + downloadFolder.getAbsolutePath());

        if (downloadFolder.exists() && downloadFolder.isDirectory()) {
            File latestPDF = getLatestPDFFile(downloadFolder.toPath());

            if (latestPDF != null) {
                PDDocument document = null;
                try {
                    document = PDDocument.load(latestPDF);
             //       Desktop.getDesktop().open(latestPDF);
                    verifyTextAndImagesInPDF(document, latestPDF);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (document != null) {
                        try {
                            document.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                System.out.println("No PDF files found in the download folder.");
            }
        } else {
            System.out.println("Download folder does not exist.");
        }
    }
    


    private static File getLatestPDFFile(Path folderPath) throws InterruptedException {
        final File[] pdfFiles = new File[1];
    	Thread.sleep(2000);

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

    private static void verifyTextAndImagesInPDF(PDDocument document, File pdfFile) throws Exception {
    	   //     PDDocument document = null;

    	        try {
    	       //     document = PDDocument.load(pdfFile);
    	            // Call getPDFImagesCount method
    	            int imageCount = getPDFImagesCount(document);
    	            System.out.println("Total number of images in PDF: " + imageCount);

    	            // Call PDFBoxExtractImages method
    	            PDFBoxExtractImages(document);
    	            
    	        	File exp = new File("./pdfimages/Image1.png");
    	    		File act = new File("./pdfimages/PhotoImg1.png");
    	    		File exp1 = new File("./pdfimages/Image2.png");
    	    		File act1 = new File("./pdfimages/PhotoImg2.jpg");
    	    		imageCompare(exp, act);
    	    		System.out.println(compareImage(exp, act));
    	    		Thread.sleep(2000);
    	    		imageCompare(exp1, act1);
    	    		System.out.println(compareImage(exp1, act1));

            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String pdfText = pdfTextStripper.getText(document);

            String firstLinkText1 = ObsPrintOptions.firstLinkText;
            String secondLinkText1 = ObsPrintOptions.secondLinkText;

            boolean containsCsv = pdfText.contains(firstLinkText1);
            boolean containsDocx = pdfText.contains(secondLinkText1);
            boolean containsIOROBSERVATION = pdfText.contains("IOR OBSERVATION LOG ITEM");
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

            if (containsIOROBSERVATION) {
                System.out.println("Text 'IOR OBSERVATION LOG ITEM' found in the PDF.");
            } else {
                System.out.println("Text 'IOR OBSERVATION LOG ITEM' not found in the PDF.");
            }
            if (containspdftext) {
                System.out.println("Text 'Sharp IOR Daily Report - Grossmont' found in the PDF.");
            } else {
                System.out.println("Text 'Sharp IOR Daily Report - Grossmont' not found in the PDF.");
            }

            // Verify images using SikuliX
//            Screen screen = new Screen();
//            ImagePath.add("C:/Users/Vigneshwaran/Desktop/SELENIUM UPLOAD/"); // Path to folder containing expected images

            String[] expectedImagePaths = {
                "PhotoImg1.png",
                "PhotoImg2.jpg"
            };
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

            public static int getPDFImagesCount(PDDocument document) throws IOException {
            	 int imageCount = getImagesFromPDF(document).size();
            	    System.out.println("Number of Images in PDF: " + imageCount);
					return imageCount;
            }

            public static List<RenderedImage> getImagesFromPDF(PDDocument document) throws IOException {
                List<RenderedImage> images = new ArrayList<>();
                for (PDPage page : document.getPages()) {
                    images.addAll(getImagesFromResources(page.getResources()));
                }
                return images;
            }
        	private static List<RenderedImage> getImagesFromResources(PDResources resources) throws IOException {
        		List<RenderedImage> images = new ArrayList<>();

        		for (COSName xObjectName : resources.getXObjectNames()) {
        			PDXObject xObject = resources.getXObject(xObjectName);

        			if (xObject instanceof PDFormXObject) {
        				images.addAll(getImagesFromResources(((PDFormXObject) xObject).getResources()));
        			} else if (xObject instanceof PDImageXObject) {
        				images.add(((PDImageXObject) xObject).getImage());
        			}
        		}
        		
        		return images;
        	}

            public static void PDFBoxExtractImages(PDDocument document) throws Exception {
                PDPageTree list = document.getPages();
                for (PDPage page : list) {
                    PDResources pdResources = page.getResources();
                    for (COSName c : pdResources.getXObjectNames()) {
                        PDXObject o = pdResources.getXObject(c);
                        if (o instanceof org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject) {
                            File file = new File("./pdfimages/" + System.nanoTime() + ".png");
                            ImageIO.write(((org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject) o).getImage(), "png", file);
                        }
                    }
                }
          
            }
            
            public static void imageCompare(File fileA, File fileB) {
        		// Initially assigning null
                BufferedImage imgA = null;
                BufferedImage imgB = null;
          
                // Try block to check for exception
                try {
          
                    // Reading file from local directory by
                    // creating object of File class
                    
          
                    // Reading files
                    imgA = ImageIO.read(fileA);
                    imgB = ImageIO.read(fileB);
                }
          
                // Catch block to check for exceptions
                catch (IOException e) {
                    // Display the exceptions on console
                    System.out.println(e);
                }
          
                // Assigning dimensions to image
                int width1 = imgA.getWidth();
                int width2 = imgB.getWidth();
                int height1 = imgA.getHeight();
                int height2 = imgB.getHeight();
          
                // Checking whether the images are of same size or
                // not
                if ((width1 != width2) || (height1 != height2))
          
                    // Display message straightaway
                    System.out.println("Error: Images dimensions"
                                       + " mismatch");
                else {
          
                    // By now, images are of same size
          
                    long difference = 0;
          
                    // treating images likely 2D matrix
          
                    // Outer loop for rows(height)
                    for (int y = 0; y < height1; y++) {
          
                        // Inner loop for columns(width)
                        for (int x = 0; x < width1; x++) {
          
                            int rgbA = imgA.getRGB(x, y);
                            int rgbB = imgB.getRGB(x, y);
                            int redA = (rgbA >> 16) & 0xff;
                            int greenA = (rgbA >> 8) & 0xff;
                            int blueA = (rgbA)&0xff;
                            int redB = (rgbB >> 16) & 0xff;
                            int greenB = (rgbB >> 8) & 0xff;
                            int blueB = (rgbB)&0xff;
          
                            difference += Math.abs(redA - redB);
                            difference += Math.abs(greenA - greenB);
                            difference += Math.abs(blueA - blueB);
                        }
                    }
          
                    // Total number of red pixels = width * height
                    // Total number of blue pixels = width * height
                    // Total number of green pixels = width * height
                    // So total number of pixels = width * height *
                    // 3
                    double total_pixels = width1 * height1 * 3;
          
                    // Normalizing the value of different pixels
                    // for accuracy
          
                    // Note: Average pixels per color component
                    double avg_different_pixels
                        = difference / total_pixels;
          
                    // There are 255 values of pixels in total
                    double percentage
                        = (avg_different_pixels / 255) * 100;
          
                    // Lastly print the difference percentage
                    System.out.println("Difference Percentage-->"
                                       + percentage);
                }
        	}
        	
        	
        	
        	
        	public static float compareImage(File fileA, File fileB) {

        	    float percentage = 0;
        	    try {
        	        // take buffer data from both image files //
        	        BufferedImage biA = ImageIO.read(fileA);
        	        DataBuffer dbA = biA.getData().getDataBuffer();
        	        int sizeA = dbA.getSize();
        	        BufferedImage biB = ImageIO.read(fileB);
        	        DataBuffer dbB = biB.getData().getDataBuffer();
        	        int sizeB = dbB.getSize();
        	        int count = 0;
        	        // compare data-buffer objects //
        	        if (sizeA == sizeB) {

        	            for (int i = 0; i < sizeA; i++) {

        	                if (dbA.getElem(i) == dbB.getElem(i)) {
        	                    count = count + 1;
        	                }

        	            }
        	            percentage = (count * 100) / sizeA;
        	        } else {
        	            System.out.println("Both the images are not of same size");
        	        }

        	    } catch (Exception e) {
        	        System.out.println("Failed to compare image files ...");
        	    }
        	    return percentage;
        	}


    }

