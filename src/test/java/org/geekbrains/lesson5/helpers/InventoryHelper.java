package org.geekbrains.lesson5.helpers;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.geekbrains.lesson5.helpers.HelperBase.isElementPresent;
import static org.geekbrains.lesson5.pages.InventoryPage.*;
import static org.geekbrains.lesson5.tests.TestBase.getDriver;


public class InventoryHelper {

    public static void addToCart(By addButton) {
        int presentCount = goodsCount();
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions.visibilityOfElementLocated(addButton));
        getDriver().findElement(addButton).click();
        Assertions.assertTrue(isElementPresent(shoppingCartBadge));
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions.textToBePresentInElementLocated(shoppingCartBadge, String.valueOf((presentCount + 1))));
    }

    public static void removeFromCart(By removeButton) {
        int presentCount = goodsCount();
        getDriver().findElement(removeButton).click();
        new WebDriverWait( getDriver(), 5).until(ExpectedConditions.invisibilityOfElementLocated(removeButton));
        if (presentCount != 1) {
            new WebDriverWait(getDriver(), 5).until(ExpectedConditions.textToBePresentInElementLocated(shoppingCartBadge, String.valueOf((presentCount - 1))));
        }
        else {
            Assertions.assertFalse(isElementPresent(shoppingCartBadge));
        }
    }

    public static void openCart() {
        getDriver().findElement(shoppingCartLink).click();
        new WebDriverWait( getDriver(), 5).until(ExpectedConditions.presenceOfElementLocated(cartContentsContainer));
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
    }

    public static void checkGoodCountInCart(int count) {
        new WebDriverWait( getDriver(), 10).until(ExpectedConditions.numberOfElementsToBe(cartItem, count));
    }

    public static int goodsCount() {
        try {
            return Integer.parseInt(getDriver().findElement(shoppingCartBadge).getText());
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

    public static void assertHeaderContent() {
        Assertions.assertTrue(getDriver().findElement(headerLogo).isDisplayed());
        Assertions.assertTrue(getDriver().findElement(menuButton).isDisplayed());
        Assertions.assertTrue(getDriver().findElement(shoppingCartLink).isDisplayed());
        Assertions.assertTrue(getDriver().findElement(productsTitle).isDisplayed());
        Assertions.assertTrue(getDriver().findElement(robotIcon).isDisplayed());
        Assertions.assertTrue(getDriver().findElement(productSortContainer).isDisplayed());
    }

    public static void assertFooterContent() {
        Assertions.assertTrue(getDriver().findElement(footerRobot).isDisplayed());
        Assertions.assertEquals("© 2022 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy", getDriver().findElement(privacyPoliticTittle).getText());
        Assertions.assertEquals("https://twitter.com/saucelabs", getDriver().findElement(twitterLink).getAttribute("href"));
        Assertions.assertEquals("https://www.facebook.com/saucelabs", getDriver().findElement(facebookLink).getAttribute("href"));
        Assertions.assertEquals("https://www.linkedin.com/company/sauce-labs/", getDriver().findElement(linkedinLink).getAttribute("href"));
    }
}
