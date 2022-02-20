package org.geekbrains.lesson5.helpers;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.geekbrains.lesson5.pages.InventoryPage.*;
import static org.geekbrains.lesson5.pages.InventoryPage.logoutLink;
import static org.geekbrains.lesson5.pages.LoginPage.*;
import static org.geekbrains.lesson5.tests.TestBase.getDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class LoginHelper {
    public static void userLogin(String login, String password) {
        getDriver().findElement(loginField).sendKeys(login);
        getDriver().findElement(passwordField).sendKeys(password);
        getDriver().findElement(loginButton).click();
    }

    public static void userLogin() {
        userLogin("standard_user","secret_sauce");
        new WebDriverWait( getDriver(), 5).until(visibilityOfElementLocated(inventoryContainer));
    }

    public static void assertFailedLoginText() {
        new WebDriverWait( getDriver(), 5).until(ExpectedConditions.presenceOfElementLocated(errorMessageContainer));
        Assertions.assertEquals("Epic sadface: Username and password do not match any user in this service",  getDriver().findElement(errorMessageContainer).getText());
    }

    public static void userLogout() {
        getDriver().findElement(menuButton).click();
        new WebDriverWait( getDriver(), 5).until(visibilityOfElementLocated(logoutLink));
        getDriver().findElement(logoutLink).click();
        new WebDriverWait( getDriver(), 5).until(ExpectedConditions.presenceOfElementLocated(loginContainer));
    }

    public static void checkUnavailabilityInventoryPage() {
        getDriver().get("https://www.saucedemo.com/inventory.html");
        new WebDriverWait( getDriver(), 5).until(ExpectedConditions.presenceOfElementLocated(errorMessageContainer));
        assertEquals("Epic sadface: You can only access '/inventory.html' when you are logged in.",  getDriver().findElement(errorMessageContainer).getText());
    }

    public static void assertSuccessLogin() {
        Assertions.assertTrue(getDriver().findElement(inventoryContainer).isDisplayed());
        Assertions.assertTrue(getDriver().findElement(menuButton).isDisplayed());
    }
}
