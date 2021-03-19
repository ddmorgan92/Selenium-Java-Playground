import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.Homepage;
import pages.SoftwareTestServicesPage;
import pages.MobileAppsPage;
import pages.WebAppsPage;
import pages.OTTPage;
import pages.AccessibilityTestingPage;


public class HomepageTests {

    @BeforeClass
    void beforeClass(){
        System.out.println("Class Initiated - Home");
    }

    @AfterClass
    void afterClass(){
        System.out.println("Class Completed - Home");
    }

    @BeforeMethod
    void beforeMethod(){
        System.out.println("Method Initiated - Home");
    }

    @AfterMethod
    void afterMethod(){
        System.out.println("Method Completed - Home");
    }

    @Test
    public static void SoftwareServicesNavTest()
    {
        try {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();

            //Creating page objects
            Homepage home = new Homepage(driver);
            SoftwareTestServicesPage softwareTestServices = new SoftwareTestServicesPage(driver);

            //Navigate to homepage, assert title and URL are correct
            driver.get(home.expectedUrl);
            Assert.assertEquals(driver.getTitle(), home.expectedTitle);
            Assert.assertEquals(driver.getCurrentUrl(), home.expectedUrl);

            //Click a link on the page
            //For nested links (e.g. navbar), enter the links that need to be hovered, ending with the link to click
            //Input must be in the form of a By[] array (e.g. new By[]{home.TestServicesLink})
            home.clickNavLink(new By[]{home.TestServicesLink, home.SoftwareTestServicesLink});
            Assert.assertEquals(driver.getTitle(), softwareTestServices.expectedTitle);
            Assert.assertEquals(driver.getCurrentUrl(), softwareTestServices.expectedUrl);

            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public static void MobileAppsNavTest()
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

            //Click a link on the page
            //For nested links (e.g. navbar), enter the links that need to be hovered, ending with the link to click
            //Input must be in the form of a By[] array (e.g. new By[]{home.TestServicesLink})
            home.clickNavLink(new By[]{home.TestServicesLink, home.WhatWeTestLink, home.MobileAppsLink});
            Assert.assertEquals(driver.getTitle(), mobileApps.expectedTitle);
            Assert.assertEquals(driver.getCurrentUrl(), mobileApps.expectedUrl);

            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public static void WebAppsNavTest()
    {
        try {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();

            //Creating page objects
            Homepage home = new Homepage(driver);
            WebAppsPage webApps = new WebAppsPage(driver);

            //Navigate to homepage, assert title and URL are correct
            driver.get(home.expectedUrl);
            Assert.assertEquals(driver.getTitle(), home.expectedTitle);
            Assert.assertEquals(driver.getCurrentUrl(), home.expectedUrl);

            //Click a link on the page
            //For nested links (e.g. navbar), enter the links that need to be hovered, ending with the link to click
            //Input must be in the form of a By[] array (e.g. new By[]{home.TestServicesLink})
            home.clickNavLink(new By[]{home.TestServicesLink, home.WhatWeTestLink, home.WebsitesWebAppsLink});
            Assert.assertEquals(driver.getTitle(), webApps.expectedTitle);
            Assert.assertEquals(driver.getCurrentUrl(), webApps.expectedUrl);

            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public static void OTTNavTest()
    {
        try {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();

            //Creating page objects
            Homepage home = new Homepage(driver);
            OTTPage ottTesting = new OTTPage(driver);

            //Navigate to homepage, assert title and URL are correct
            driver.get(home.expectedUrl);
            Assert.assertEquals(driver.getTitle(), home.expectedTitle);
            Assert.assertEquals(driver.getCurrentUrl(), home.expectedUrl);

            //Click a link on the page
            //For nested links (e.g. navbar), enter the links that need to be hovered, ending with the link to click
            //Input must be in the form of a By[] array (e.g. new By[]{home.TestServicesLink})
            home.clickNavLink(new By[]{home.TestServicesLink, home.WhatWeTestLink, home.OttStreamingMediaLink});
            Assert.assertEquals(driver.getTitle(), ottTesting.expectedTitle);
            Assert.assertEquals(driver.getCurrentUrl(), ottTesting.expectedUrl);

            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public static void LargeNavTest()
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

