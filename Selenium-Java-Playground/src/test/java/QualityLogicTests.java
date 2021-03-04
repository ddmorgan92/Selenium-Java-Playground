import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class QualityLogicTests {
    @Test
    void mobileTestingNav() throws InterruptedException
    {
        try {
            //Chrome
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.qualitylogic.com");
            String expectedTitle = "QualityLogic | Home: Software Testing Company, Software QA Company";
            String expectedUrl = "https://www.qualitylogic.com/";

            System.out.println(driver.getTitle() + " - " + driver.getCurrentUrl());
            Assert.assertEquals(driver.getTitle(), expectedTitle);
            Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

            WebElement test_services = driver.findElement(By.xpath("/html/body/header/div[1]/nav/div/div[2]/nav/ul/li[1]/a"));
            WebElement what_we_test = driver.findElement(By.xpath("/html/body/header/div[1]/nav/div/div[2]/nav/ul/li[1]/ul/li[2]/a"));
            WebElement mobile_testing = driver.findElement(By.xpath("/html/body/header/div[1]/nav/div/div[2]/nav/ul/li[1]/ul/li[2]/ul/li[1]/a"));

            Actions actions = new Actions(driver);
            actions.moveToElement(test_services).build().perform();
            actions.moveToElement(what_we_test).build().perform();
            mobile_testing.click();
            expectedTitle = "QualityLogic | Mobile Application Testing Services";
            expectedUrl = "https://www.qualitylogic.com/what-we-test/mobile-apps/";
            Assert.assertEquals(driver.getTitle(), expectedTitle);
            Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

            Thread.sleep(2000);
            driver.close();
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
            driver.get("https://www.qualitylogic.com");
            driver.findElement(By.linkText("Schedule a Consultation")).click();

            //Information for Consultation Request
            String firstName = "Dustin";
            String lastName = "Morgan";
            String workEmail = "dmorgan@qualitylogic.com";
            String company = "Quality Logic";
            String aboutProject = "I am interested in getting feedback regarding an automation program I have created!";

            Thread.sleep(1000);
            WebElement submitButton = driver.findElement(By.className("hs-button"));
            submitButton.sendKeys(Keys.ENTER);

            //Count the number of errors, decrement expected amount by 1
            List<WebElement> errors = driver.findElements(By.className("hs-error-msg"));
            Integer actualErrorCount = errors.size();
            Integer expectedErrorCount = 6;
            Assert.assertEquals(actualErrorCount, expectedErrorCount);
            System.out.println("Number of errors: " + errors.size());
            expectedErrorCount--;

            //Submit w/ First Name only, assert the correct # of errors, decrement expected amount by 1
            driver.findElement(By.name("firstname")).sendKeys(firstName);
            submitButton.sendKeys(Keys.ENTER);
            errors = driver.findElements(By.className("hs-error-msg"));
            actualErrorCount = errors.size();
            Assert.assertEquals(actualErrorCount, expectedErrorCount);
            System.out.println("Number of errors: " + errors.size());
            expectedErrorCount--;

            //Submit w/ first name, last name
            driver.findElement(By.name("lastname")).sendKeys(lastName);
            submitButton.sendKeys(Keys.ENTER);
            errors = driver.findElements(By.className("hs-error-msg"));
            actualErrorCount = errors.size();
            Assert.assertEquals(actualErrorCount, expectedErrorCount);
            System.out.println("Number of errors: " + errors.size());
            expectedErrorCount--;

            //Submit w/ first name, last name, work email
            driver.findElement(By.name("email")).sendKeys(workEmail);
            submitButton.sendKeys(Keys.ENTER);
            errors = driver.findElements(By.className("hs-error-msg"));
            actualErrorCount = errors.size();
            Assert.assertEquals(actualErrorCount, expectedErrorCount);
            System.out.println("Number of errors: " + errors.size());
            expectedErrorCount--;

            //Submit w/ first name, last name, work email, company
            driver.findElement(By.name("company")).sendKeys(company);
            submitButton.sendKeys(Keys.ENTER);
            errors = driver.findElements(By.className("hs-error-msg"));
            actualErrorCount = errors.size();
            Assert.assertEquals(actualErrorCount, expectedErrorCount);
            System.out.println("Number of errors: " + errors.size());
            expectedErrorCount--;

            //Submit w/ firstname, last name, work email, company, area of interest
            Select areaOfInterest = new Select(driver.findElement(By.name("area_of_interest")));
            areaOfInterest.selectByVisibleText("Web, Mobile, eCommerce");
            submitButton.sendKeys(Keys.ENTER);
            errors = driver.findElements(By.className("hs-error-msg"));
            actualErrorCount = errors.size();
            Assert.assertEquals(actualErrorCount, expectedErrorCount);
            System.out.println("Number of errors: " + errors.size());
            expectedErrorCount--;

            //Submit w/ firstname, last name, work email, company, area of interest, about project
            //Final submit - encounters CAPTCHA
            driver.findElement(By.name("project_description")).sendKeys(aboutProject);
            submitButton.sendKeys(Keys.ENTER);
            errors = driver.findElements(By.className("hs-error-msg"));
            actualErrorCount = errors.size();
            Assert.assertEquals(actualErrorCount, expectedErrorCount);
            System.out.println("Number of errors: " + errors.size());
            //wait 10 seconds, then close
            Thread.sleep(10000);
            driver.close();
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
            driver.get("https://www.qualitylogic.com");
            driver.findElement(By.linkText("Watch How it Works")).sendKeys(Keys.ENTER);
            driver.switchTo().frame(1);

            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);

            WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(By.xpath("/html/body/div/div/div[4]/button"));
                }
            });
            WebElement playButton = driver.findElement(By.xpath("/html/body/div/div/div[4]/button"));
            Assert.assertEquals(playButton.getAttribute("aria-label"), "Play");
            playButton.click();
            //Skip video ahead 60 seconds
            js.executeScript("document.getElementsByTagName(\"video\")[0].currentTime += 60");
            Thread.sleep(1000);
            //Skip video back 30 seconds
            js.executeScript("document.getElementsByTagName(\"video\")[0].currentTime -= 30");
            //Pause
            Thread.sleep(1000);
            WebElement pauseButton = driver.findElement(By.xpath("/html/body/div/div/div[25]/div[2]/div[1]/button"));
            Assert.assertEquals(pauseButton.getAttribute("aria-label"), "Pause");
            pauseButton.click();

            Thread.sleep(5000);
            driver.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
