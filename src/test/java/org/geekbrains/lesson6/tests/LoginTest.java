package org.geekbrains.lesson6.tests;

import org.geekbrains.lesson6.InventoryPage;
import org.geekbrains.lesson6.LoginPage;
import org.junit.jupiter.api.Test;

public class LoginTest extends AbstractTest {

    @Test
    public void successLoginTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.setLogin("standard_user").setPassword("secret_sauce").loginIn();
        loginPage.assertSuccessLogin();
    }

    @Test
    public void failedLoginTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.setLogin("standard_user").setPassword("Password1").loginIn();
        loginPage.assertFailedLoginText();
        loginPage.checkUnavailabilityInventoryPage();
    }

    @Test
    public void successLogoutTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        loginPage.setLogin("standard_user").setPassword("secret_sauce").loginIn();
        inventoryPage.logOut();
        loginPage.checkUnavailabilityInventoryPage();
    }
}
