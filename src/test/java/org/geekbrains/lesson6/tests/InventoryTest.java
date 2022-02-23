package org.geekbrains.lesson6.tests;


import org.geekbrains.lesson6.CartPage;
import org.geekbrains.lesson6.InventoryPage;
import org.geekbrains.lesson6.LoginPage;
import org.junit.jupiter.api.Test;

import static org.geekbrains.lesson6.InventoryPage.*;

public class InventoryTest extends AbstractTest {

    @Test
    public void addAndRemoveGoodsTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.setLogin("standard_user").setPassword("secret_sauce").loginIn();
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        inventoryPage.addToCart(addBackpackButton);
        inventoryPage.removeFromCart(removeBackpackButton);

        inventoryPage.goToGoodsCard(backpackTittle);
        inventoryPage.addToCart(addBackpackButton);
        inventoryPage.removeFromCart(removeBackpackButton);
    }

    @Test
    public void addAndRemoveGoodsInCartTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.setLogin("standard_user").setPassword("secret_sauce").loginIn();
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());

        inventoryPage.addToCart(addBackpackButton);
        inventoryPage.addToCart(addBikeLightButton);
        inventoryPage.addToCart(addBoltTshirtButton);

        cartPage.openCart();
        cartPage.checkGoodCountInCart(3);

        inventoryPage.removeFromCart(removeBackpackButton);
        inventoryPage.removeFromCart(removeBikeLightButton);
        inventoryPage.removeFromCart(removeBoltTshirtButton);
        cartPage.checkGoodCountInCart(0);
    }

    @Test
    public void checkMenuContentTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        loginPage.setLogin("standard_user").setPassword("secret_sauce").loginIn();
        inventoryPage.assertHeaderContent();
        inventoryPage.assertFooterContent();
    }
}
