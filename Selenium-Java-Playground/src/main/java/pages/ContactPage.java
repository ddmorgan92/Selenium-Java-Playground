package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class ContactPage {
    WebDriver driver;

    public ContactPage(WebDriver driver){
        this.driver=driver;
    }

    public String expectedTitle = "Software Testing Company - Contact Us - QualityLogic";
    public String expectedUrl = "https://www.qualitylogic.com/contact/";


    //Error message validation
    public Integer expectedErrorCount;

    public void generateExpectedErrorCount() {

        List<WebElement> requiredInputs = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@required]")));
        List<WebElement> requiredSelects = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//select[@required]")));
        List<WebElement> requiredTextAreas = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//textarea[@required]")));
        expectedErrorCount = requiredInputs.size() + requiredSelects.size() + requiredTextAreas.size();
        System.out.println(expectedErrorCount);
    }

    public Integer actualErrorCount() {
        //To avoid fail condition on wait for final assertion, use error check without explicit wait
        //This could probably be handled better - rewrite?
        if(expectedErrorCount == 0 ){
            List<WebElement> errorCheck = driver.findElements(By.className("hs-error-msg"));
            if(errorCheck.size() == 0){
                return 0;
            }
        }

        List<WebElement> errorList = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("hs-error-msg")));
        return errorList.size();
    }

    public void inputFirstName(String firstName) {
        WebElement firstNameField = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.name("firstname")));
        firstNameField.sendKeys(firstName);
        expectedErrorCount--;
    }

    public void inputLastName(String lastName) {
        WebElement lastNameField = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.name("lastname")));
        lastNameField.sendKeys(lastName);
        expectedErrorCount--;
    }

    public void inputEmail(String workEmail) {
        WebElement emailField = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.name("email")));
        emailField.sendKeys(workEmail);
        expectedErrorCount--;
    }

    public void inputCompany(String company) {
        WebElement companyField = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.name("company")));
        companyField.sendKeys(company);
        expectedErrorCount--;
    }

    public void selectAreaOfInterest(Integer index){
          Select areaOfInterest = new Select(driver.findElement(By.name("area_of_interest")));
          //Choose select field entry by index
          areaOfInterest.selectByIndex(index);
          expectedErrorCount--;
    }

    public void inputProjectDescription(String aboutProject) {
        WebElement projectDescField = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.name("project_description")));
        projectDescField.sendKeys(aboutProject);
        expectedErrorCount--;
    }

    public void submitForm() {
        WebElement submitButton = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.className("hs-button")));
        submitButton.sendKeys(Keys.ENTER);
    }
}
