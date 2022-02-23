package org.geekbrains.lesson6;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.geekbrains.lesson6.CartPage.*;
import static org.geekbrains.lesson6.LoginPage.loginContainer;

public class InventoryPage extends AbstractPage{

    //inventory
    public static By inventoryContainer = By.id("inventory_container");
    public static By addBackpackButton = By.id("add-to-cart-sauce-labs-backpack");
    public static By removeBackpackButton = By.id("remove-sauce-labs-backpack");
    public static By addBikeLightButton = By.id("add-to-cart-sauce-labs-bike-light");
    public static By removeBikeLightButton = By.id("remove-sauce-labs-bike-light");
    public static By addBoltTshirtButton = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    public static By removeBoltTshirtButton = By.id("remove-sauce-labs-bolt-t-shirt");
    public static By backpackTittle = By.id("item_4_title_link");

    //header
    public static By shoppingCartLink = By.cssSelector("a.shopping_cart_link");
    public static By shoppingCartBadge = By.cssSelector("span.shopping_cart_badge");
    public static By headerLogo = By.cssSelector(".app_logo");
    public static By productsTitle = By.cssSelector(".header_secondary_container>.title");
    public static By robotIcon = By.cssSelector(".header_secondary_container>.peek");
    public static By productSortContainer = By.cssSelector(".product_sort_container");

    //footer
    public static By footerRobot = By.cssSelector("img.footer_robot");
    public static By privacyPoliticTittle = By.cssSelector("div.footer_copy");
    public static By twitterLink = By.cssSelector("li.social_twitter a");
    public static By facebookLink = By.cssSelector("li.social_facebook a");
    public static By linkedinLink = By.cssSelector("li.social_linkedin a");

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutButton;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void logOut(){
        this.menuButton.click();
        this.logoutButton.click();
        new WebDriverWait( getDriver(), 5).until(ExpectedConditions.presenceOfElementLocated(loginContainer));
    }

    public void addToCart(By addButton) {
        int presentCount = goodsCount();
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions.visibilityOfElementLocated(addButton));
        getDriver().findElement(addButton).click();
        Assertions.assertTrue(isElementPresent(shoppingCartBadge));
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions.textToBePresentInElementLocated(shoppingCartBadge, String.valueOf((presentCount + 1))));
    }

    public void removeFromCart(By removeButton) {
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

    public void goToGoodsCard(By id) {
        getDriver().findElement(id).click();
    }

    public int goodsCount() {
        try {
            return Integer.parseInt(getDriver().findElement(shoppingCartBadge).getText());
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

    public void assertHeaderContent() {
        Assertions.assertTrue(getDriver().findElement(headerLogo).isDisplayed());
        Assertions.assertTrue(getDriver().findElement(shoppingCartLink).isDisplayed());
        Assertions.assertTrue(getDriver().findElement(productsTitle).isDisplayed());
        Assertions.assertTrue(getDriver().findElement(robotIcon).isDisplayed());
        Assertions.assertTrue(getDriver().findElement(productSortContainer).isDisplayed());
    }

    public void assertFooterContent() {
        Assertions.assertTrue(getDriver().findElement(footerRobot).isDisplayed());
        Assertions.assertEquals("© 2022 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy", getDriver().findElement(privacyPoliticTittle).getText());
        Assertions.assertEquals("https://twitter.com/saucelabs", getDriver().findElement(twitterLink).getAttribute("href"));
        Assertions.assertEquals("https://www.facebook.com/saucelabs", getDriver().findElement(facebookLink).getAttribute("href"));
        Assertions.assertEquals("https://www.linkedin.com/company/sauce-labs/", getDriver().findElement(linkedinLink).getAttribute("href"));
    }
}
