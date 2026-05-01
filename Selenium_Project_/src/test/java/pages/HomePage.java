package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;

    // Define all locators here
    By productsLink = By.xpath("//a[contains(@href,'products')]");
    By loginLink = By.xpath("//a[contains(@href,'login')]");
    By cartLink = By.xpath("//a[contains(@href,'cart')]");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Define all actions here
    public void clickProducts() {
        driver.findElement(productsLink).click();
    }

    public void clickLogin() {
        driver.findElement(loginLink).click();
    }

    public void clickCart() {
        driver.findElement(cartLink).click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}