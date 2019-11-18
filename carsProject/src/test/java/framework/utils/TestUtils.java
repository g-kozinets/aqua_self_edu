package framework.utils;

import framework.driver.Browser;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Random;

public class TestUtils {

    public static Object getRandomElement(ArrayList list) {
        Random rand = new Random();
        if (list.size() > 1) {
            return list.get(rand.nextInt(list.size()));
        } else return list.get(0);
    }

    public static int getTagPosition(By by) {
        return Browser.getDriver().findElements(by).size() + 1;
    }
}
