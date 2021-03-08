package pages;

import org.openqa.selenium.WebDriver;

public class WebAppsPage {
    WebDriver driver;

    public WebAppsPage(WebDriver driver){
        this.driver=driver;
    }

    public String expectedTitle = "QualityLogic | Websites and Web Apps Testing: Software Testing Company";
    public String expectedUrl = "https://www.qualitylogic.com/what-we-test/web/";
}
