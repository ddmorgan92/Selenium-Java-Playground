import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Homepage;
import pages.SoftwareTestServicesPage;
import pages.MobileAppsPage;
import pages.WebAppsPage;
import pages.AccessibilityTestingPage;
import pages.ContactPage;


public class QualityLogicTests {

    @Test
    public static void WebAppsNavTest()
    {
        try {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();

            //Creating page objects
            Homepage home = new Homepage(driver);
            MobileAppsPage mobileApps = new MobileAppsPage(driver);
            SoftwareTestServicesPage softwareTestServices = new SoftwareTestServicesPage(driver);
            WebAppsPage webApps = new WebAppsPage(driver);
            AccessibilityTestingPage accessibility = new AccessibilityTestingPage(driver);


            //Navigate to homepage, assert title and URL are correct
            driver.get(home.expectedUrl);
            Assert.assertEquals(driver.getTitle(), home.expectedTitle);
            Assert.assertEquals(driver.getCurrentUrl(), home.expectedUrl);

            //Click a link on the page
            //For nested links (e.g. navbar), enter the links that need to be hovered, ending with the link to click
            //Input must be in the form of a By[] array (e.g. new By[]{home.TestServicesLink})
            home.clickNavLink(new By[]{home.TestServicesLink, home.WhatWeTestLink, home.MobileAppsLink});
            Assert.assertEquals(driver.getTitle(), mobileApps.expectedTitle);
            Assert.assertEquals(driver.getCurrentUrl(), mobileApps.expectedUrl);

            driver.navigate().back();
            home.clickNavLink(new By[]{home.TestServicesLink, home.TestSolutionsLink, home.AccessibilityTestingLink});
            Assert.assertEquals(driver.getTitle(), accessibility.expectedTitle);
            Assert.assertEquals(driver.getCurrentUrl(), accessibility.expectedUrl);

            driver.navigate().back();
            home.clickNavLink(new By[]{home.TestServicesLink, home.SoftwareTestServicesLink});
            Assert.assertEquals(driver.getTitle(), softwareTestServices.expectedTitle);
            Assert.assertEquals(driver.getCurrentUrl(), softwareTestServices.expectedUrl);

            driver.navigate().back();
            home.clickNavLink(new By[]{home.TestServicesLink, home.WhatWeTestLink, home.WebsitesWebAppsLink});
            Assert.assertEquals(driver.getTitle(), webApps.expectedTitle);
            Assert.assertEquals(driver.getCurrentUrl(), webApps.expectedUrl);

            driver.navigate().back();
            home.clickNavLink(new By[]{home.TestServicesLink});
            //assert equal to homepage because this link has an href value of '#'
            Assert.assertEquals(driver.getTitle(), home.expectedTitle);
            Assert.assertEquals(driver.getCurrentUrl(), home.expectedUrl);

            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void verifyContactFormErrorMessages()
    {
        try {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();

            //Creating page objects
            Homepage home = new Homepage(driver);
            ContactPage contact = new ContactPage(driver);

            //Navigate to homepage
            driver.get(home.expectedUrl);
            Assert.assertEquals(driver.getCurrentUrl(), home.expectedUrl);
            Assert.assertEquals(driver.getTitle(), home.expectedTitle);

            //Navigate to Consultation Request Page
            home.clickNavLink(new By[]{home.ScheduleConsultLink});
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
    void youtubeAriaLabelTest()
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
            System.out.println("Current video time (in seconds): " + home.currentVideoTime());
            home.skipVideo(30);
            System.out.println("Current video time (in seconds): " + home.currentVideoTime());
            home.skipVideo(-10);
            System.out.println("Current video time (in seconds): " + home.currentVideoTime());
            home.skipVideo(20);
            System.out.println("Current video time (in seconds): " + home.currentVideoTime());
            Assert.assertEquals(home.PauseAriaLabel, home.expectedPauseAria);
            home.pauseVideo();

            driver.quit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
