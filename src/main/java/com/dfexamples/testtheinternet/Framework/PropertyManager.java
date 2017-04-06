package com.dfexamples.testtheinternet.Framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import static com.dfexamples.testtheinternet.Framework.Enums.Browser.BROWSER_CHROME;
import static com.dfexamples.testtheinternet.Framework.Enums.PathConfig.PROPERTIES_PATH;

public class PropertyManager {

    private static Properties properties = new Properties();
    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_BROWSER = BROWSER_CHROME.getBrowser();

    public static String getProperty(String key) {
        if (properties.isEmpty()) {
            PropertyManager.Initialize();
        }

        return properties.getProperty(key);
    }

    private static void Initialize() {
        String host;
        String browser;
        String os;

        host = Optional.ofNullable(System.getProperty("targetHost")).orElse(DEFAULT_HOST);
        browser = Optional.ofNullable(System.getProperty("browserType")).orElse(DEFAULT_BROWSER);
        os = System.getProperty("os.name").toUpperCase();

        try {
            properties.load(new FileInputStream(PROPERTIES_PATH + "localhost.properties"));
            properties.setProperty("SelectedHost", host);
            properties.setProperty("SelectedBrowser", browser);
            properties.setProperty("operating_system", os);
        } catch (IOException e) {
            System.out.println("Failed to load properties: " + e.getMessage());
        }
    }
}
