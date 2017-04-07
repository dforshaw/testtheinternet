package com.dfexamples.testtheinternet.Framework.Enums;

public enum PathConfig {

    PROJECT_PROPERTIES_PATH("src/main/java/com/dfexamples/testtheinternet/Framework/Properties/"),
    PROJ_LOCATION_WORK("/Ideaprojects/"),
    PROJ_LOCATION_HOME("/Code/"),
    WEBDRIVER_CLIENTS("/_webdriver_clients"),
    PATH_TO_CHROME_DRIVER("/chromedriver/chromedriver"),
    PATH_TO_GECKO_DRIVER("/geckodriver/geckodriver");

    String path;

    private PathConfig(String path_to_use) {
        this.path = path_to_use;
    }

    public String getPath() {
        return path;
    }
}
