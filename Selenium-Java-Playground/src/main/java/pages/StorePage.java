package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;


public class StorePage {
    WebDriver driver;

    public StorePage(WebDriver driver){
        this.driver=driver;
    }

    public String expectedTitle = "Store - QualityLogic";
    public String expectedUrl = "https://www.qualitylogic.com/shop/";

    public By cartLink = By.className("ql-cart");

    public ArrayList<WebElement> itemArray;
    public ArrayList<String> linkList;
    public void generateItems(){
        itemArray = (ArrayList<WebElement>) driver.findElements(By.className("button"));
        System.out.println(linkList);
    }


    public void navigateToItem(Integer x){
        String itemLink = itemArray.get(x).getAttribute("href");
        driver.get(itemLink);
    }


    public float expectedPrice;
    public void enterQuantity(Integer x){
        WebElement cookieBanner = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[2]/table/tbody/tr/td/div[2]/a")));
        cookieBanner.click();

        WebElement inputText = driver.findElement(By.className("input-text"));
        inputText.clear();
        inputText.sendKeys(x.toString());


        WebElement itemPrice = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"product-8269\"]/div[2]/p/span/bdi")));
        String itemPriceRemoveSymbols = itemPrice.getText().replaceAll("[$,]", "");
        expectedPrice = Float.parseFloat(itemPriceRemoveSymbols) * x;
        System.out.println(expectedPrice);

    }

    public void addToCart(){
        WebElement addButton = driver.findElement(By.name("add-to-cart"));
            Actions actions = new Actions(driver);
            actions.moveToElement(addButton);
            actions.perform();
            addButton.click();
    }

    public int countUniqueCartItems(){
        List<WebElement> itemsInCart = driver.findElements(By.className("product-remove"));
        return itemsInCart.size();
    }
}
