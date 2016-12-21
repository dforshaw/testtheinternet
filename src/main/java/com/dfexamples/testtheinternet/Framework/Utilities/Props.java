package com.dfexamples.testtheinternet.Framework.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class Props {

    private static Properties properties = new Properties();
    private static final String DEFAULT_HOST = "localhost";
    private static final String DEFAULT_BROWSER = Props.BROWSER_CHROME;
    private static final String BROWSER_CHROME = "Chrome";
    private static final String BROWSER_MARIONETTE = "Marionette";
    private static final String BROWSER_FIREFOX = "Firefox";

    public static String getProperty(String key) {
        if (properties.isEmpty()) {
            Props.Initialize();
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
            properties.load(new FileInputStream(
                    "src/main/java/com/dfexamples/testtheinternet/Framework/Resources/Properties/localhost.properties"));
            properties.setProperty("SelectedHost", host);
            properties.setProperty("SelectedBrowser", browser);
            properties.setProperty("operating_system", os);
        } catch (IOException e) {
            System.out.println("Failed to load properties: " + e.getMessage());
        }
    }
}
