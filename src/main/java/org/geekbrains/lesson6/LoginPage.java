package org.geekbrains.lesson6;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.geekbrains.lesson6.InventoryPage.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage extends AbstractPage {
    @FindBy(id = "user-name")
    private WebElement login;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement submit;

    public static By errorMessageContainer = By.cssSelector("div.error-message-container");
    public static By loginContainer = By.id("login_button_container");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void loginIn(){
        this.submit.click();
    }

    public LoginPage setLogin(String login){
        this.login.sendKeys(login);
        return this;
    }

    public LoginPage setPassword(String password){
        this.password.sendKeys(password);
        return this;
    }

    public void assertSuccessLogin() {
        Assertions.assertTrue(getDriver().findElement(inventoryContainer).isDisplayed());
    }

    public void assertFailedLoginText() {
        assertEquals("Epic sadface: Username and password do not match any user in this service",
                getDriver().findElement(By.cssSelector("div.error-message-container")).getText());
    }

    public void checkUnavailabilityInventoryPage() {
        getDriver().get("https://www.saucedemo.com/inventory.html");
        new WebDriverWait( getDriver(), 5).until(ExpectedConditions.presenceOfElementLocated(errorMessageContainer));
        assertEquals("Epic sadface: You can only access '/inventory.html' when you are logged in.",  getDriver().findElement(errorMessageContainer).getText());
    }


}
