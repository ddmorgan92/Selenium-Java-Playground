package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;

public class Homepage {
    WebDriver driver;

    public Homepage(WebDriver driver){
        this.driver=driver;
    }

    //Expected page title and URL
    public String expectedTitle = "QualityLogic | Home: Software Testing Company, Software QA Company";
    public String expectedUrl = "https://www.qualitylogic.com/";

    //Navbar Links
        //Test Services Section
    public By TestServicesLink = By.linkText("Test Services");
    public By SoftwareTestServicesLink = By.linkText("Software Test Services");
        //What We Test subsection
    public By WhatWeTestLink = By.linkText("What We Test");
    public By MobileAppsLink = By.linkText("Mobile Apps");
    public By WebsitesWebAppsLink = By.linkText("Websites and Web Apps");

    public By OttStreamingMediaLink = By.linkText("OTT & Streaming Media");
    public By SmartEnergyStandardsLink = By.linkText("Smart Energy Standards");
    public By BigDataAnalyticsLink = By.linkText("Big Data Analytics & Telemetry");
    public By ApiTestingLink = By.linkText("API Testing");
    public By VirtualARTestingLink = By.linkText("Virtual and Augmented Reality Testing");
    public By ECommerceLink = By.linkText("eCommerce");
    public By IOTLink = By.linkText("Internet of Things (IoT)");
    public By PrintSystemsLink = By.linkText("Print Systems");
    public By FaxLink = By.linkText("Fax & Fax over IP Testing");
        //Test Solutions subsection
    public By TestSolutionsLink = By.linkText("Test Solutions");
    public By FunctionalTestingLink = By.linkText("Functional Testing");
    public By TestAutomationLink = By.linkText("Test Automation Services");
    public By UsabilityTestingLink = By.linkText("Usability Testing");
    public By AccessibilityTestingLink = By.linkText("Accessibility Testing");
    public By WCAGLink = By.linkText("WCAG Compliance Testing & Certification");
    public By LoadTestingLink = By.linkText("Load & Performance Testing");
    public By RegressionTestingLink = By.linkText("Regression Testing");
    public By ExploratoryTestingLink = By.linkText("Exploratory Testing");
    public By InteroperabilityLink = By.linkText("Interoperability Testing");
    public By QATestToolsLink = By.linkText("QA Test Tools");
    public By AgileQALink = By.linkText("Agile QA");
    public By TestTechTrainingLink = By.linkText("Test & Technology Training");
        //Many more links - WIP

    //Contact Us Links
    public By ScheduleConsultLink = By.linkText("Schedule a Consultation");
//    By ContactUsTodayLink = By.linkText("Contact us today");
//    By GetStartedNowLink = By.linkText("Get Started Now");
//    By ContactUsLink = By.linkText("Contact Us");

    //Navigation method
    public void clickNavLink(By [] array) {
        Actions actions = new Actions(driver);
        int x = 0;
        while(x < array.length){
            if(x == array.length - 1){
                driver.findElement(array[x]).click();
            } else {
                actions.moveToElement(driver.findElement(array[x])).build().perform();
            }
            x++;
        }

    }

    //Video Modal Elements
    By WatchHowItWorksLink = By.linkText("Watch How it Works");
    By PlayButton;
    By PauseButton;
    public String PlayAriaLabel;
    public String PauseAriaLabel;
    public String expectedPlayAria = "Play";
    public String expectedPauseAria = "Pause (k)";


    //video methods
        //Open Video Modal, assign Play button locator and actual aria label
    public void engageVideoModal() {
        driver.findElement(WatchHowItWorksLink).sendKeys(Keys.ENTER);
        driver.switchTo().frame(1);

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        wait.until(driver -> {
            PlayButton = By.xpath("/html/body/div/div/div[4]/button");
            PlayAriaLabel = driver.findElement(By.xpath("/html/body/div/div/div[4]/button")).getAttribute("aria-label");
            return driver.findElement(By.xpath("/html/body/div/div/div[4]/button"));
        });
    }
        //Play video, assign Pause button locator and actual aria label
    public void playVideo() {
        driver.findElement(PlayButton).click();
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        wait.until(driver -> {
            PauseButton = By.xpath("/html/body/div/div/div[25]/div[2]/div[1]/button");
            PauseAriaLabel = driver.findElement(By.xpath("/html/body/div/div/div[25]/div[2]/div[1]/button")).getAttribute("aria-label");
            return driver.findElement(By.xpath("/html/body/div/div/div[25]/div[2]/div[1]/button"));
        });
    }

    public void pauseVideo() {
        driver.findElement(PauseButton).click();
    }

        //Skip video, forward or backward
    public void skipVideo(int x) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementsByTagName(\"video\")[0].currentTime += " + x);
    }

    public Double currentVideoTime(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        return (Double) js.executeScript("return document.getElementsByTagName(\"video\")[0].currentTime;");
    }

}
