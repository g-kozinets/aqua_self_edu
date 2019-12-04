package framework.configuration;

import aquality.selenium.utils.JsonFile;

public enum Environment {
    PROD("prod"),
    STAGE("stage");

    private JsonFile configurationFile;

    Environment(String name) {
        configurationFile = new JsonFile(String.format("environment/%1$s/config.json", name));
    }

    public String getStartUrl() {
        return String.valueOf(configurationFile.getValue("/startUrl"));
    }

    public int getWidth() {
        return Integer.parseInt(String.valueOf(configurationFile.getValue("/width")));
    }

    public int getHeight() {
        return Integer.parseInt(String.valueOf(configurationFile.getValue("/height")));
    }
}