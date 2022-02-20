package org.geekbrains.lesson5.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static org.geekbrains.lesson5.tests.TestBase.getDriver;

public class HelperBase {

    public static boolean isElementPresent(By by) {
        try {
            getDriver().findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
