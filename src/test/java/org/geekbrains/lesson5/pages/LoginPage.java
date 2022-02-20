package org.geekbrains.lesson5.pages;

import org.openqa.selenium.By;

public class LoginPage {
    public static By loginField = By.id("user-name");
    public static By passwordField = By.id("password");
    public static By loginButton = By.id("login-button");
    public static By errorMessageContainer = By.cssSelector("div.error-message-container");
    public static By loginContainer = By.id("login_button_container");
}
