package org.inertia.SRV.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InspecReqPrintOptions {
    public static  WebDriver driver;
    public WebDriverWait wait;
    public static String firstLinkText;
    public static String secondLinkText;

    public InspecReqPrintOptions(WebDriver driver) {
        InspecReqPrintOptions.driver = driver;
        wait = new WebDriverWait(InspecReqPrintOptions.driver, 25);
    }

    public String clickIRPrintButtonAndDownloadPrintPDFFile() throws InterruptedException, AWTException {
        try {
            Thread.sleep(4000);
            WebElement DocxLink = driver.findElement(By.xpath("(//div[contains(@class,'x-panel inertia-margin-top')])[7]//a[contains(text(),'.docx')][1]"));
             firstLinkText = DocxLink.getText();
            System.out.println("Text of the DOCX link: " + firstLinkText);
            Thread.sleep(3000);

            // Find the second link
            WebElement CSVLink = driver.findElement(By.xpath("(//div[contains(@class,'x-panel inertia-margin-top')])[7]//a[contains(text(),'.csv')][1]"));
             secondLinkText = CSVLink.getText();
            System.out.println("Text of the CSV link: " + secondLinkText);

            WebElement Printoption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Print']")));
            Printoption.click();

            WebElement Printbutton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Print W/ Attachment']")));
            Printbutton.click();

            Thread.sleep(13000);

            // Switch to the new window
            String originalWindowHandle = driver.getWindowHandle();
            for (String handle : driver.getWindowHandles()) {
                if (!handle.equals(originalWindowHandle)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }

            Thread.sleep(9000);

            // Get the URL of the new window and verify it's not empty or null
            String newWindowUrl = driver.getCurrentUrl();
            if (newWindowUrl != null && !newWindowUrl.isEmpty()) {
                System.out.println("New window URL: " + newWindowUrl);

                Thread.sleep(8000);

                Robot robot = new Robot();

                // Press 'Tab' key 8 times
                for (int i = 0; i < 8; i++) {
                    robot.keyPress(KeyEvent.VK_TAB);
                    robot.keyRelease(KeyEvent.VK_TAB);
                }

                // Press 'Enter' key
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);

                Thread.sleep(2000);

                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(7000);
            }
        } catch (InterruptedException | AWTException e) {
            e.printStackTrace();
        }

        return "Print and download actions completed successfully."; // Return something meaningful here
    }

    public static String getFirstLinkText() {
        WebElement DocxLink1 = driver.findElement(By.xpath("(//div[contains(@class,'x-panel inertia-margin-top')])[6]//a[contains(text(),'.docx')][1]"));
        String firstLinkText1 = DocxLink1.getText();
        System.out.println("Text of the DOCX link: " + firstLinkText1);

        return firstLinkText1;
    }

    public static String getSecondLinkText() {
        // Find the second link
        WebElement CSVLink = driver.findElement(By.xpath("(//div[contains(@class,'x-panel inertia-margin-top')])[6]//a[contains(text(),'.csv')][1]"));
        String secondLinkText = CSVLink.getText();
        System.out.println("Text of the CSV link: " + secondLinkText);

        return secondLinkText;
    }
}
