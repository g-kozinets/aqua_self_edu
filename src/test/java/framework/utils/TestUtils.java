package framework.utils;

import java.util.ArrayList;
import java.util.Random;

public class TestUtils {

    public static Object getRandomElement(ArrayList list) {
        Random rand = new Random();
        if (list.size() > 1) {
            return list.get(rand.nextInt(list.size()));
        } else return list.get(0);
    }
}
