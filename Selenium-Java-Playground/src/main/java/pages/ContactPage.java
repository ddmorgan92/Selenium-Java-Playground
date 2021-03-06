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


    //Information for Consultation Request
    public String firstName = "Dustin";
    public String lastName = "Morgan";
    public String workEmail = "dmorgan@qualitylogic.com";
    public String company = "Quality Logic";
    public String aboutProject = "I am interested in getting feedback regarding an automation program I have created!";

    //Error message validation
    public Integer expectedErrorCount = 6;
    public Integer actualErrorCount() {
        List<WebElement> errorList = driver.findElements(ErrorMessages);
        return errorList.size();
    }

    public void inputFirstName() {
        driver.findElement(FirstNameField).sendKeys(firstName);
        expectedErrorCount--;
    }

    public void inputLastName() {
        driver.findElement(LastNameField).sendKeys(lastName);
        expectedErrorCount--;
    }

    public void inputEmail() {
        driver.findElement(EmailField).sendKeys(workEmail);
        expectedErrorCount--;
    }

    public void inputCompany() {
        driver.findElement(CompanyField).sendKeys(company);
        expectedErrorCount--;
    }

    public void selectAreaOfInterest(){
          Select areaOfInterest = new Select(driver.findElement(By.name("area_of_interest")));
          areaOfInterest.selectByVisibleText("Web, Mobile, eCommerce");
          expectedErrorCount--;
    }

    public void inputProjectDescription() {
        driver.findElement(ProjectDescriptionField).sendKeys(aboutProject);
        expectedErrorCount--;
    }

    public void submitForm() {
        driver.findElement(SubmitButton).sendKeys(Keys.ENTER);
    }
}
