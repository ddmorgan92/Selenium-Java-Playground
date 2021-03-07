import org.openqa.selenium.*;
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
            contact.selectAreaOfInterest();
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.expectedErrorCount);

            //Submit w/ firstname, last name, work email, company, area of interest, about project
            //Final submit - encounters CAPTCHA
            contact.inputProjectDescription("Selenium Automation Project");
            contact.submitForm();
            Assert.assertEquals(contact.actualErrorCount(), contact.expectedErrorCount);
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
            driver.manage().window().maximize();

            //Creating page objects
            Homepage home = new Homepage(driver);

            //Navigate to Homepage and Open Video Modal
            driver.get(home.expectedUrl);
            home.engageVideoModal();
            Assert.assertEquals(home.PlayAriaLabel, home.expectedPlayAria);
            //Play video, skip forward and backwards
            home.playVideo();
            home.skipVideo(60);
            home.skipVideo(-30);
            home.skipVideo(90);
            Assert.assertEquals(home.PauseAriaLabel, home.expectedPauseAria);
            home.pauseVideo();

            Thread.sleep(2000);
            driver.quit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
