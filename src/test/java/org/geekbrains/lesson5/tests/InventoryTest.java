package org.geekbrains.lesson5.tests;


import org.junit.jupiter.api.Test;

import static org.geekbrains.lesson5.helpers.InventoryHelper.*;
import static org.geekbrains.lesson5.helpers.LoginHelper.userLogin;
import static org.geekbrains.lesson5.pages.InventoryPage.*;


public class InventoryTest extends TestBase {

    @Test
    public void addAndRemoveGoodsTest() {
        userLogin();
        addToCart(addBackpackButton);
        removeFromCart(removeBackpackButton);

        getDriver().findElement(backpackTittle).click();
        addToCart(addBackpackButton);
        removeFromCart(removeBackpackButton);
    }

    @Test
    public void addAndRemoveGoodsInCartTest() {
        userLogin();
        addToCart(addBackpackButton);
        addToCart(addBikeLightButton);
        addToCart(addBoltTshirtButton);

        openCart();
        checkGoodCountInCart(3);

        removeFromCart(removeBackpackButton);
        removeFromCart(removeBikeLightButton);
        removeFromCart(removeBoltTshirtButton);
        checkGoodCountInCart(0);
    }

    @Test
    public void checkMenuContentTest() {
        userLogin();
        assertHeaderContent();
        assertFooterContent();
    }
}
