package framework.configuration;


public class Configuration {
    private static Environment currentEnvironment;

    private Configuration() {
    }

    public static synchronized Environment getCurrentEnvironment() {
        if (currentEnvironment == null)
            currentEnvironment = Environment.valueOf((System.getProperty("environment") != null ? System.getProperty("environment")
                    : "stage").toUpperCase());
        return currentEnvironment;
    }

}
