package org.geekbrains.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.geekbrains.lesson6.InventoryPage.shoppingCartLink;

public class CartPage extends AbstractPage{
    public static By cartContentsContainer = By.id("cart_contents_container");
    public static By cartItem = By.cssSelector("div.cart_item");
    public static By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public void openCart() {
        getDriver().findElement(shoppingCartLink).click();
        new WebDriverWait( getDriver(), 5).until(ExpectedConditions.presenceOfElementLocated(cartContentsContainer));
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
    }

    @Step
    public void checkGoodCountInCart(int count) {
        new WebDriverWait( getDriver(), 10).until(ExpectedConditions.numberOfElementsToBe(cartItem, count));
    }
}
