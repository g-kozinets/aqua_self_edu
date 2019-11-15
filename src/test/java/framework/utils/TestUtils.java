package framework.utils;


import carsProject.models.Car;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class TestUtils {

    public static boolean compareCategories(ArrayList<String> popCategories, ArrayList<String> allCategories) {
        boolean result = true;
        for (String popCateg : popCategories) {
            if (!allCategories.contains(popCateg)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static Object getRandomElement(ArrayList list) {
        Random rand = new Random();
        if (list.size() > 1) {
            return list.get(rand.nextInt(list.size()));
        } else return list.get(0);
    }
}
