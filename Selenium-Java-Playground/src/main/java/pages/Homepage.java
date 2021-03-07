package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

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
    By TestServicesLink = By.linkText("Test Services");
//    By SoftwareTestServicesLink = By.linkText("Software Test Services");
        //What We Test subsection
    By WhatWeTestLink = By.linkText("What We Test");
    By MobileAppsLink = By.linkText("Mobile Apps");
//    By WebsitesWebAppsLink = By.linkText("Websites and Web Apps");
//    By OttStreamingMediaLink = By.linkText("OTT & Streaming Media");
//    By SmartEnergyStandardsLink = By.linkText("Smart Energy Standards");
//    By BigDataAnalyticsLink = By.linkText("Big Data Analytics & Telemetry");
//    By ApiTestingLink = By.linkText("API Testing");
//    By VirtualARTestingLink = By.linkText("Virtual and Augmented Reality Testing");
//    By ECommerceLink = By.linkText("eCommerce");
//    By IOTLink = By.linkText("Internet of Things (IoT)");
//    By PrintSystemsLink = By.linkText("Print Systems");
//    By FaxLink = By.linkText("Fax & Fax over IP Testing");
//        //Test Solutions subsection
//    By TestSolutionsLink = By.linkText("Test Solutions");
//    By FunctionalTestingLink = By.linkText("Functional Testing");
//    By TestAutomationLink = By.linkText("Test Automation Services");
//    By UsabilityTestingLink = By.linkText("Usability Testing");
//    By AccessibilityTestingLink = By.linkText("Accessibility Testing");
//    By WCAGLink = By.linkText("WCAG Compliance Testing & Certification");
//    By LoadTestingLink = By.linkText("Load & Performance Testing");
//    By RegressionTestingLink = By.linkText("Regression Testing");
//    By ExploratoryTestingLink = By.linkText("Exploratory Testing");
//    By InteroperabilityLink = By.linkText("Interoperability Testing");
//    By QATestToolsLink = By.linkText("QA Test Tools");
//    By AgileQALink = By.linkText("Agile QA");
//    By TestTechTrainingLink = By.linkText("Test & Technology Training");
        //Many more links - WIP

    //Contact Us Links
    By ScheduleConsultLink = By.linkText("Schedule a Consultation");
//    By ContactUsTodayLink = By.linkText("Contact us today");
//    By GetStartedNowLink = By.linkText("Get Started Now");
//    By ContactUsLink = By.linkText("Contact Us");

    //Video Modal Elements
    By WatchHowItWorksLink = By.linkText("Watch How it Works");
    By PlayButton;
    By PauseButton;
    public String PlayAriaLabel;
    public String PauseAriaLabel;
    public String expectedPlayAria = "Play";
    public String expectedPauseAria = "Pause (k)";


    //video methods
    public void engageVideoModal() throws InterruptedException {
        driver.findElement(WatchHowItWorksLink).sendKeys(Keys.ENTER);
        driver.switchTo().frame(1);
        Thread.sleep(2000);
        PlayButton = By.xpath("/html/body/div/div/div[4]/button");
        PlayAriaLabel = driver.findElement(By.xpath("/html/body/div/div/div[4]/button")).getAttribute("aria-label");
    }

    public void playVideo() throws InterruptedException{
        driver.findElement(PlayButton).click();
        Thread.sleep(1000);
        PauseButton = By.xpath("/html/body/div/div/div[25]/div[2]/div[1]/button");
        PauseAriaLabel = driver.findElement(By.xpath("/html/body/div/div/div[25]/div[2]/div[1]/button")).getAttribute("aria-label");
    }

    public void pauseVideo() {
        driver.findElement(PauseButton).click();
    }

    public void clickMobileAppsLink() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(TestServicesLink)).build().perform();
        actions.moveToElement(driver.findElement(WhatWeTestLink)).build().perform();
        driver.findElement(MobileAppsLink).click();
    }

    public void clickScheduleConsultLink() throws InterruptedException{
        driver.findElement(ScheduleConsultLink).click();
        Thread.sleep(1000);
    }


    public void skipVideo(int x) throws InterruptedException{
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.getElementsByTagName(\"video\")[0].currentTime += " + x);
        Thread.sleep(2000);
    }

}
