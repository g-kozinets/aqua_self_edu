package framework.utils;


import java.util.ArrayList;
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
        return list.get(rand.nextInt(list.size() - 1));
    }
}
