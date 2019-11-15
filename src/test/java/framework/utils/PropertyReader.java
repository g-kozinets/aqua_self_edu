package framework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static FileInputStream fis;
    private static Properties property = new Properties();

    public static String getProp(String propName) {
        String propertyValue = new String();
        try {
            fis = new FileInputStream("config.properties");
            property.load(fis);
            propertyValue = property.getProperty(propName);

        } catch (
        IOException e) {
            System.err.println("Property file not found :(");
        }

        return propertyValue;
    }
}
