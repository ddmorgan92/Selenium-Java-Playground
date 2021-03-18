package pages;

import org.openqa.selenium.WebDriver;

public class OTTPage {
    WebDriver driver;

    public OTTPage(WebDriver driver){
        this.driver=driver;
    }

    public String expectedTitle = "QualityLogic | OTT Testing: Streaming Media Testing, OTT Test Automation";
    public String expectedUrl = "https://www.qualitylogic.com/what-we-test/ott-streaming-media/";
}
