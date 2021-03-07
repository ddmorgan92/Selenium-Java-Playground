package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ContactPage {
    WebDriver driver;

    public ContactPage(WebDriver driver){
        this.driver=driver;
    }

    //Consultation Request Form Elements
    By SubmitButton = By.className("hs-button");
    By ErrorMessages = By.className("hs-error-msg");
    By FirstNameField = By.name("firstname");
    By LastNameField = By.name("lastname");
    By EmailField = By.name("email");
    By CompanyField = By.name("company");
    By ProjectDescriptionField = By.name("project_description");


    //Error message validation
    public Integer expectedErrorCount = 6;
    public Integer actualErrorCount() {
        List<WebElement> errorList = driver.findElements(ErrorMessages);
        return errorList.size();
    }

    public void inputFirstName(String firstName) {
        driver.findElement(FirstNameField).sendKeys(firstName);
        expectedErrorCount--;
    }

    public void inputLastName(String lastName) {
        driver.findElement(LastNameField).sendKeys(lastName);
        expectedErrorCount--;
    }

    public void inputEmail(String workEmail) {
        driver.findElement(EmailField).sendKeys(workEmail);
        expectedErrorCount--;
    }

    public void inputCompany(String company) {
        driver.findElement(CompanyField).sendKeys(company);
        expectedErrorCount--;
    }

    public void selectAreaOfInterest(){
          Select areaOfInterest = new Select(driver.findElement(By.name("area_of_interest")));
          areaOfInterest.selectByVisibleText("Web, Mobile, eCommerce");
          expectedErrorCount--;
    }

    public void inputProjectDescription(String aboutProject) {
        driver.findElement(ProjectDescriptionField).sendKeys(aboutProject);
        expectedErrorCount--;
    }

    public void submitForm() {
        driver.findElement(SubmitButton).sendKeys(Keys.ENTER);
    }
}
