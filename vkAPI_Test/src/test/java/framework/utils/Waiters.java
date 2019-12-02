package framework.utils;

import aquality.selenium.waitings.ConditionalWait;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Waiters {
    public static void waitForInvisibility(By locator, String message) {
        ConditionalWait.waitFor(ExpectedConditions.invisibilityOfElementLocated(locator), message);
    }

    public static void waitForVisibility(By locator, String message) {
        ConditionalWait.waitFor(ExpectedConditions.visibilityOfElementLocated(locator), message);
    }
}


