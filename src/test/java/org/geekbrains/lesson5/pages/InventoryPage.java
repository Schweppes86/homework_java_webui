package org.geekbrains.lesson5.pages;

import org.openqa.selenium.By;

public class InventoryPage {
    //menu
    public static By menuButton = By.id("react-burger-menu-btn");
    public static By logoutLink = By.id("logout_sidebar_link");

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

    //cart
    public static By cartContentsContainer = By.id("cart_contents_container");
    public static By cartItem = By.cssSelector("div.cart_item");
    public static By checkoutButton = By.id("checkout");
    public static By continueShoppingButton = By.id("continue-shopping");
}
