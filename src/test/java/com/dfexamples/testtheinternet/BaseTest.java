package com.dfexamples.testtheinternet;

import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest {

    public static String baseUrl = "http://the-internet.herokuapp.com/";

    private final String os = System.getProperty("os.name").toUpperCase();
    private String driverPath = "";

    public static String userHomeDir = System.getProperty("user.home");
    public static String projHomeDir = userHomeDir + "/Ideaprojects/testtheinternet";
    public static String Webdriver_Clients = projHomeDir + "/vendors";

    public static WebDriver driver;

    public void setDriverToChrome() {

        driverPath = "/chromedriver/chromedriver.exe";    // needs to be fixed for cross-platform

        String chromeDriverLocation = Webdriver_Clients + driverPath;
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
        driver = new ChromeDriver();
    }

    public void setDriverToFirefox() {
        String fireFoxDriverLocation = Webdriver_Clients + "/geckodriver/geckodriver";
        System.setProperty("webdriver.gecko.driver", fireFoxDriverLocation);
        driver = new FirefoxDriver();
    }

    public void setDriverToMarionette() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        String marionetteDriverLocation = Webdriver_Clients + "/geckodriver/geckodriver";
        System.setProperty("webdriver.gecko.driver", marionetteDriverLocation);
        driver = new FirefoxDriver(capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
