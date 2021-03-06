import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Homepage;
import pages.ContactPage;
import pages.MobileAppsPage;

public class QualityLogicTests {
    @Test
    public static void mobileTestingNav() throws InterruptedException
    {
        try {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();

            //Creating page objects
            Homepage home = new Homepage(driver);
            MobileAppsPage mobileApps = new MobileAppsPage(driver);

            //Navigate to homepage, assert title and URL are correct
            driver.get(home.expectedUrl);
            Assert.assertEquals(driver.getTitle(), home.expectedTitle);
            Assert.assertEquals(driver.getCurrentUrl(), home.expectedUrl);

            //Click on mobile testing link (nested)
            home.clickMobileAppsLink();
            Assert.assertEquals(driver.getTitle(), mobileApps.expectedTitle);
            Assert.assertEquals(driver.getCurrentUrl(), mobileApps.expectedUrl);

            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void scheduleConsult() throws InterruptedException
    {
        try {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();

            //Creating page objects
            Homepage home = new Homepage(driver);
            ContactPage contact = new ContactPage(driver);

            //Navigate to homepage, select Consultation link, press Submit button
            driver.get(home.expectedUrl);
            home.clickScheduleConsultLink();
            contact.submitForm();
            System.out.println("Initial expected error count: " + contact.expectedErrorCount);

            //Count the number of errors, decrement expected amount by 1
            Assert.assertEquals(contact.actualErrorCount(), contact.expectedErrorCount);
            System.out.println("Number of errors: " + contact.actualErrorCount());

            //Submit w/ First Name only, assert the correct # of errors, decrement expected amount by 1
            contact.inputFirstName();
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.expectedErrorCount);
            System.out.println("Number of errors: " + contact.actualErrorCount());

            //Submit w/ first name, last name
            contact.inputLastName();
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.expectedErrorCount);
            System.out.println("Number of errors: " + contact.actualErrorCount());

            //Submit w/ first name, last name, work email
            contact.inputEmail();
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.expectedErrorCount);
            System.out.println("Number of errors: " + contact.actualErrorCount());

            //Submit w/ first name, last name, work email, company
            contact.inputCompany();
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.expectedErrorCount);
            System.out.println("Number of errors: " + contact.actualErrorCount());

            //Submit w/ firstname, last name, work email, company, area of interest
            contact.selectAreaOfInterest();
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.expectedErrorCount);
            System.out.println("Number of errors: " + contact.actualErrorCount());

            //Submit w/ firstname, last name, work email, company, area of interest, about project
            //Final submit - encounters CAPTCHA
            contact.inputProjectDescription();
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.expectedErrorCount);
            System.out.println("Number of errors: " + contact.actualErrorCount());
            driver.quit();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void youtubeEmbed() throws InterruptedException
    {
        try{
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            //JavaScript executor
            JavascriptExecutor js = (JavascriptExecutor)driver;
            driver.manage().window().maximize();

            //Creating page objects
            Homepage home = new Homepage(driver);

            driver.get(home.expectedUrl);
            //Open Video Modal
            home.engageVideoModal();
            Assert.assertEquals(home.PlayAriaLabel, "Play");
            //Select Play Button
            home.playVideo();
            //Skip video forward and back
            js.executeScript("document.getElementsByTagName(\"video\")[0].currentTime += 60");
            js.executeScript("document.getElementsByTagName(\"video\")[0].currentTime -= 30");
            //Pause
            Assert.assertEquals(home.PauseAriaLabel, "Pause (k)");
            home.pauseVideo();

            Thread.sleep(2000);
            driver.quit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
