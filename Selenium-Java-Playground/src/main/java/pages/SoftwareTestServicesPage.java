package pages;

import org.openqa.selenium.WebDriver;

public class SoftwareTestServicesPage {
    WebDriver driver;

    public SoftwareTestServicesPage(WebDriver driver){
        this.driver=driver;
    }

    public String expectedTitle = "QualityLogic | What We Test: Software Testing Services, Software Testing, QA Testing Services Company";
    public String expectedUrl = "https://www.qualitylogic.com/what-we-test/";
}
