package com.dfexamples.testtheinternet.Framework.Enums;

public enum PathConfig {

    VENDOR_PATH("/vendors"),
    VENDOR_PATH_LOCAL("/_webdriver_clients"),
    DRIVER_PATH_GECKO("/geckodriver/geckodriver"),
    DRIVER_PATH_CHROME("/chromedriver/chromedriver"),
    PROJ_LOCATION_WORK("/Ideaprojects"),
    PROJ_LOCATION_HOME("/Code");

    String path;

    private PathConfig(String path_to_use) {
        this.path = path_to_use;
    }

    public String getPath() {
        return path;
    }
}
