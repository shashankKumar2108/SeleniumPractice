package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.ExcelUtils;

public class Day3DDTTest {

    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;

    String excelPath = "./testdata/LoginData.xlsx";
    String sheetName = "Sheet1";

    @BeforeClass
    public void setUp() {
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    // DataProvider reads from Excel using YOUR style
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Throwable {
        Object[][] data = new Object[3][2];

        // Row 1, 2, 3 — skipping row 0 (header row)
        for (int i = 0; i < 3; i++) {
            data[i][0] = ExcelUtils.getData(excelPath, sheetName, i + 1, 0); // Email
            data[i][1] = ExcelUtils.getData(excelPath, sheetName, i + 1, 1); // Password
        }
        return data;
    }

    // Test runs 3 times automatically — once per row!
    @Test(priority = 1, dataProvider = "loginData")
    public void verifyLoginWithMultipleData(String email, String password)throws Throwable {
        driver.get("https://automationexercise.com");
        Thread.sleep(2000);
        homePage.clickLogin();
        Thread.sleep(2000);
        loginPage.login(email, password);
        Thread.sleep(2000);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),"Error message not shown for: " + email);
        System.out.println("Passed for → Email: " + email + "Password: " + password);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
        System.out.println("Browser closed!");
    }
}