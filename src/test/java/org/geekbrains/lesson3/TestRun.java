package org.geekbrains.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestRun {
    public static By loginField = By.id("user-name");
    public static By passwordField = By.id("password");
    public static By loginButton = By.id("login-button");
    public static By menuButton = By.id("react-burger-menu-btn");
    public static By logoutLink = By.id("logout_sidebar_link");
    public static By errorMessageContainer = By.cssSelector("div.error-message-container");
    public static By loginContainer = By.id("login_button_container");
    public static By addBackpackButton = By.id("add-to-cart-sauce-labs-backpack");
    public static By removeBackpackButton = By.id("remove-sauce-labs-backpack");
    public static By shoppingCartBadge = By.cssSelector("span.shopping_cart_badge");


    public static void main(String[] args) {
        successLoginTest();
        failedLoginTest();
        successLogoutTest();
        addAndRemoveGoodsInCartTest();
    }


    public static void addAndRemoveGoodsInCartTest() {
        WebDriver driver = driverPrepare();
        loginUser(driver, "standard_user","secret_sauce");

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(addBackpackButton));
        driver.findElement(addBackpackButton).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(removeBackpackButton));
        assertEquals("1", driver.findElement(shoppingCartBadge).getText());
        driver.findElement(removeBackpackButton).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOfElementLocated(removeBackpackButton));

        successTestResult("addAndRemoveGoodsInCartTest");
        driver.quit();
    }

    public static void failedLoginTest() {
        WebDriver driver = driverPrepare();
        loginUser(driver, "standard_user","Password1");

        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(errorMessageContainer));
        assertEquals("Epic sadface: Username and password do not match any user in this service", driver.findElement(errorMessageContainer).getText());
        successTestResult("failedLoginTest");
        driver.quit();
    }

    public static void successLoginTest() {
        WebDriver driver = driverPrepare();
        loginUser(driver, "standard_user","secret_sauce");

        driver.findElement(menuButton).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
        successTestResult("successLoginTest");
        driver.quit();
    }

    public static void successLogoutTest() {
        WebDriver driver = driverPrepare();
        loginUser(driver, "standard_user","secret_sauce");

        driver.findElement(menuButton).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
        driver.findElement(logoutLink).click();

        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(loginContainer));
        driver.get("https://www.saucedemo.com/inventory.html");
        new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(errorMessageContainer));
        assertEquals("Epic sadface: You can only access '/inventory.html' when you are logged in.", driver.findElement(errorMessageContainer).getText());

        successTestResult("successLogoutTest");
        driver.quit();
    }

    private static void loginUser(WebDriver driver, String login, String password) {
        driver.findElement(loginField).sendKeys(login);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public static void successTestResult(String testName) {
        System.out.println("Тест " + testName + " прошел успешно!");
    }

    public static WebDriver driverPrepare() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        return (driver);
    }
}