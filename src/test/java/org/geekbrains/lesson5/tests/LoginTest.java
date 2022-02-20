package org.geekbrains.lesson5.tests;

import org.junit.jupiter.api.Test;

import static org.geekbrains.lesson5.helpers.LoginHelper.*;

public class LoginTest extends TestBase {

    @Test
    public void successLoginTest() {
        userLogin();
        assertSuccessLogin();
    }

    @Test
    public void failedLoginTest() {
        userLogin("standard_user","Password1");
        assertFailedLoginText();
        checkUnavailabilityInventoryPage();
    }

    @Test
    public void successLogoutTest() {
        userLogin();
        userLogout();
        checkUnavailabilityInventoryPage();
    }
}
