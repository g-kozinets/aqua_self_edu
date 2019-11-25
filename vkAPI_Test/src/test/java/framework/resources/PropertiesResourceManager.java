package framework.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesResourceManager {
    private Properties properties = new Properties();

    /**
     * Default Constructor
     */
    public PropertiesResourceManager() {
        properties = new Properties();
    }

    /**
     * Constructor
     *
     * @param resourceName Name of resource
     */
    public PropertiesResourceManager(final String resourceName) {
        properties = appendFromResource(properties, resourceName);
    }

    /**
     * Constructor for creation of one object from two aqa.properties-files
     *
     * @param defaultResourceName Default Resource Name
     * @param resourceName        Resource Name
     */
    public PropertiesResourceManager(final String defaultResourceName, final String resourceName) {
        this(defaultResourceName);
        properties = appendFromResource(new Properties(properties), resourceName);
    }

    /**
     * Merging of two aqa.properties-files (parameters from the second override parameters from the first)
     *
     * @param objProperties Properties
     * @param resourceName  Resource Name
     * @return Properties
     */
    private Properties appendFromResource(final Properties objProperties, final String resourceName) {
        InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);

        if (inStream != null) {
            try {
                objProperties.load(inStream);
                inStream.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println(String.format("Resource \"%1$s\" could not be found", resourceName));
        }
        return objProperties;
    }

    /**
     * Get value by key
     *
     * @param key Key
     * @return Value
     */
    public String getProperty(final String key) {
        return System.getProperty(key, properties.getProperty(key));
    }

    /**
     * Get value by key
     *
     * @param key          Key
     * @param defaultValue Default Value
     * @return Value
     */
    public String getProperty(final String key, final String defaultValue) {
        return System.getProperty(key, this.properties.getProperty(key, defaultValue));
    }

    /**
     * Sets the property
     *
     * @param key   Key
     * @param value Value
     */
    public void setProperty(final String key, final String value) {
        properties.setProperty(key, value);
    }
}
