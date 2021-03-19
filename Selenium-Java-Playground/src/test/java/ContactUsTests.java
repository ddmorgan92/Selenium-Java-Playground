import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ContactPage;

public class ContactUsTests {

    @BeforeClass
    void beforeClass(){
        System.out.println("Class Initiated - Contact");
    }

    @AfterClass
    void afterClass(){
        System.out.println("Class Completed - Contact");
    }

    @BeforeTest
    void beforeMethod(){
        System.out.println("Method Initiated - Contact");
    }

    @AfterTest
    void afterMethod(){
        System.out.println("Method Completed - Contact");
    }


    @Test
    void verifyContactFormErrorMessages()
    {
        try {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();

            //Creating page objects
            ContactPage contact = new ContactPage(driver);

            //Navigate to the Contact Page
            driver.get(contact.expectedUrl);
            Assert.assertEquals(driver.getCurrentUrl(), contact.expectedUrl);
            Assert.assertEquals(driver.getTitle(), contact.expectedTitle);


            //Generate expected error count based on fields with 'required' attribute
            contact.generateExpectedErrorCount();
            //Once Submit button is present, submit Form
            contact.submitForm();
            //Count the number of errors, decrement expected amount by 1
            Assert.assertEquals(contact.actualErrorCount(), contact.expectedErrorCount);

            //Submit w/ First Name only, assert the correct # of errors, decrement expected amount by 1
            contact.inputFirstName("Dustin");
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.expectedErrorCount);

            //Submit w/ first name, last name
            contact.inputLastName("Morgan");
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.expectedErrorCount);

            //Submit w/ first name, last name, work email
            contact.inputEmail("dmorgan@qualitylogic.com");
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.expectedErrorCount);

            //Submit w/ first name, last name, work email, company
            contact.inputCompany("Quality Logic");
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.expectedErrorCount);

            //Submit w/ firstname, last name, work email, company, area of interest
            //Choose area of interest by index of Select element
            //Do not use Index 0
            contact.selectAreaOfInterest(3);
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.expectedErrorCount);

            //Submit w/ firstname, last name, work email, company, area of interest, about project
            //Final submit - encounters CAPTCHA
            contact.inputProjectDescription("Selenium Automation Project");
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.expectedErrorCount);

            driver.close();
            driver.quit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void verifyContactFormInvalidEntries()
    {
        try {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();

            //Creating page objects
            ContactPage contact = new ContactPage(driver);

            //Navigate to the Contact Page
            driver.get(contact.expectedUrl);
            Assert.assertEquals(driver.getCurrentUrl(), contact.expectedUrl);
            Assert.assertEquals(driver.getTitle(), contact.expectedTitle);


            //Generate expected error count based on fields with 'required' attribute
            contact.generateExpectedErrorCount();
            //Once Submit button is present, submit Form
            contact.submitForm();
            //Count the number of errors, decrement expected amount by 1
            Assert.assertEquals(contact.actualErrorCount(), contact.expectedErrorCount);

            //Submit w/ invalid First Name only
            contact.inputFirstName("");
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.immutableExpectedErrorCount);

            //Submit w/ invalid first name, last name
            contact.inputLastName("");
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.immutableExpectedErrorCount);

            //Submit w/ invalid first name, last name, work email
            contact.inputEmail("");
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.immutableExpectedErrorCount);

            //Submit w/ invalid first name, last name, work email, company
            contact.inputCompany("");
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.immutableExpectedErrorCount);

            //Submit w/ invalid firstname, last name, work email, company, area of interest
            //Choose area of interest by index of Select element
            //Do not use Index 0 except for invalid entries
            contact.selectAreaOfInterest(0);
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.immutableExpectedErrorCount);

            //Submit w/ invalid firstname, last name, work email, company, area of interest, about project
            //Final submit - encounters CAPTCHA
            contact.inputProjectDescription("");
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.immutableExpectedErrorCount);

            driver.close();
            driver.quit();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
