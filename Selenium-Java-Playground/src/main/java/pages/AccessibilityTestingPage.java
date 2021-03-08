package pages;

import org.openqa.selenium.WebDriver;

public class AccessibilityTestingPage {
    WebDriver driver;

    public AccessibilityTestingPage(WebDriver driver){
        this.driver=driver;
    }

    public String expectedTitle = "QualityLogic | Accessibility Testing Services: ADA & WCAG Compliance";
    public String expectedUrl = "https://www.qualitylogic.com/testing-solutions/accessibility-testing/";
}
