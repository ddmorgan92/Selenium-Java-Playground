import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.StorePage;

public class StoreTests {

    @Test
    void AddSingleProductToCart()
    {
        try {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();

            //Creating page objects
            StorePage store = new StorePage(driver);

            //Navigate to the Contact Page
            driver.get(store.expectedUrl);
            Assert.assertEquals(driver.getCurrentUrl(), store.expectedUrl);
            Assert.assertEquals(driver.getTitle(), store.expectedTitle);

            store.generateItems();
            store.navigateToItem(0);
            store.enterQuantity(900);
            store.addToCart();
            driver.findElement(store.cartLink).click();

            String amountStr = driver.findElement(By.xpath("/html/body/section/div/article/div/div/form/table/tbody/tr[1]/td[6]/span/bdi")).getText();
            String amountStrRemoveSymbols = amountStr.replaceAll("[$,]", "");
            System.out.println(amountStrRemoveSymbols + " - " + store.expectedPrice);

            Assert.assertEquals(Float.parseFloat(amountStrRemoveSymbols), store.expectedPrice);

            driver.close();
            driver.quit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}


