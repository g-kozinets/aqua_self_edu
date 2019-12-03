package framework.utils;

import net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveUnboxingDelegate;

import java.util.Random;

public class TextGenerator {
    public static String runGenerator(int length) {
        int leftLimit = 97;
        int rightLimit = 122;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedText = buffer.toString();


        return generatedText;
    }

    public static String generate(int length) {
        return runGenerator(length);
    }

    public static String generate(String length) {
        return runGenerator(Integer.parseInt(length));
    }
}
