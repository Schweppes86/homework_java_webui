package org.geekbrains.lesson6.tests;

import org.geekbrains.lesson6.InventoryPage;
import org.geekbrains.lesson6.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты на логин пользователя")
public class LoginTest extends AbstractTest {

    @Test
    @DisplayName ("Успешный логин")
    public void successLoginTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.setLogin("standard_user").setPassword("secret_sauce").loginIn();
        loginPage.assertSuccessLogin();
    }

    @Test
    @DisplayName ("Неуспешный логин")
    public void failedLoginTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.setLogin("standard_user").setPassword("Password1").loginIn();
        loginPage.assertFailedLoginText();
        loginPage.checkUnavailabilityInventoryPage();
    }

    @Test
    @DisplayName ("Успешный выход из аккаунта")
    public void successLogoutTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        loginPage.setLogin("standard_user").setPassword("secret_sauce").loginIn();
        inventoryPage.logOut();
        loginPage.checkUnavailabilityInventoryPage();
    }
}
