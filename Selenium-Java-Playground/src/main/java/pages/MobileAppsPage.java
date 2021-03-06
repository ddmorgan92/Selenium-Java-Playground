package pages;

import org.openqa.selenium.WebDriver;

public class MobileAppsPage
{
    WebDriver driver;

    public MobileAppsPage(WebDriver driver){
        this.driver=driver;
    }

    public String expectedTitle = "QualityLogic | Mobile Application Testing Services";
    public String expectedUrl = "https://www.qualitylogic.com/what-we-test/mobile-apps/";
}
