package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class Day2POMTest {

    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Create page objects here
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    // Test 1 — Verify home page title using POM
    @Test(priority = 1)
    public void verifyHomePageTitle() {
        driver.get("https://automationexercise.com");
        String title = homePage.getPageTitle();
        Assert.assertEquals(title, "Automation Exercise");
        System.out.println("Test 1 Passed — Title: " + title);
    }

    // Test 2 — Navigate to products using POM
    @Test(priority = 2)
    public void verifyProductsNavigation() throws Throwable {
        driver.get("https://automationexercise.com");
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
        Thread.sleep(1000);
        // Click using JavaScript instead of normal click
        WebElement productsBtn = driver.findElement(By.xpath("//header//a[contains(@href,'/products')]"));
        js.executeScript("arguments[0].click();", productsBtn);
        Thread.sleep(3000);
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("products"));
        System.out.println("Test 2 Passed — Products page opened!");
    }

    // Test 3 — Login with wrong credentials using POM
    @Test(priority = 3)
    public void verifyLoginWithWrongCredentials() throws Throwable {
        driver.get("https://automationexercise.com");
        Thread.sleep(2000);
        homePage.clickLogin();
        Thread.sleep(2000);
        loginPage.login("wrong@email.com", "wrongpassword");
        Thread.sleep(2000);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        System.out.println("Test 3 Passed — Error message displayed for wrong credentials!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("Browser closed!");
    }
}