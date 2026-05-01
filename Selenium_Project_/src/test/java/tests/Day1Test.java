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

public class Day1Test {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void verifyHomePageTitle() {
        driver.get("https://automationexercise.com");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Automation Exercise";
        Assert.assertEquals(actualTitle, expectedTitle);
        System.out.println("Test 1 Passed — Title is: " + actualTitle);
    }

    @Test(priority = 2)
    public void verifyProductsPageNavigation() throws InterruptedException {
        driver.get("https://automationexercise.com");
        Thread.sleep(3000);
        
        // ✅ JavaScript click instead of normal click
        JavascriptExecutor js =(JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
        Thread.sleep(1000);
        WebElement productsBtn = driver.findElement(By.xpath("//header//a[contains(@href,'/products')]"));
        js.executeScript("arguments[0].click();", productsBtn);
        Thread.sleep(3000);
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        Assert.assertTrue(currentUrl.contains("products"));
        System.out.println("Test 2 Passed — Navigated to: " + currentUrl);
    }

    @Test(priority = 3)
    public void verifyLoginPageNavigation() throws Throwable {
        driver.get("https://automationexercise.com");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(@href,'login')]")).click();Thread.sleep(2000);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("login"));
        System.out.println("Test 3 Passed — Login page opened: " + currentUrl);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("Browser closed successfully!");
    }
}