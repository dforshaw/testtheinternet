package com.dfexamples.testtheinternet.Framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DriverManager {

    public static WebDriver DriverInstance;
    public static String BaseAddress = "http://the-internet.herokuapp.com/";
    public static String OperatingSystem = System.getProperty("os.name").toUpperCase();
    public static String UserHomeDir = System.getProperty("user.home");
    public static String ProjHomeDir = UserHomeDir + "/Ideaprojects/testtheinternet";
    public static String Webdriver_Clients = ProjHomeDir + "/vendors";
    public static String FirefoxDriverPathForMac = "/geckodriver/geckodriver";
    public static String FirefoxDriverPathForWindows10 = "/geckodriver/geckodriver";
    public static String MarionetteDriverPathForMac = "/geckodriver/geckodriver";
    public static String MarionetteDriverPathForWindows10 = "/geckodriver/geckodriver";
    public static String ChromeDriverPathForMac = "/chromedriver/chromedriver";
    public static String ChromeDriverPathForWindows10 = "/chromedriver/chromedriver.exe";

    public static void Initialize(String browsername) {
        switch (browsername) {
            case "":
                DriverInstance = new FirefoxDriver();
                break;

            case "Firefox":
                setDriverToFirefox();
                break;

            case "Marionette":
                setDriverToMarionette();
                break;

            case "Chrome":
                setDriverToChrome();
                break;
        }

        DriverInstance.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private static void setDriverToFirefox() {
        String fireFoxDriverLocation = Webdriver_Clients + setDriverPathForFirefox(OperatingSystem);
        System.setProperty("webdriver.gecko.driver", fireFoxDriverLocation);
        DriverInstance = new FirefoxDriver();
    }

    private static String setDriverPathForFirefox(String os) {
        if (os == "Mac OS X")           return FirefoxDriverPathForMac;
        else if (os == "WINDOWS 10")    return FirefoxDriverPathForWindows10;
        else                            return FirefoxDriverPathForMac;
    }

    private static void setDriverToMarionette() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        String marionetteDriverLocation = Webdriver_Clients + setDriverPathForMarionette(OperatingSystem);
        System.setProperty("webdriver.gecko.driver", marionetteDriverLocation);
        DriverInstance = new FirefoxDriver(capabilities);
    }

    private static String setDriverPathForMarionette(String os) {
        if (os == "Mac OS X")           return MarionetteDriverPathForMac;
        else if (os == "WINDOWS 10")    return MarionetteDriverPathForWindows10;
        else                            return MarionetteDriverPathForMac;
    }

    private static void setDriverToChrome() {
        String chromeDriverLocation = Webdriver_Clients + setDriverPathForChrome(OperatingSystem);
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
        DriverInstance = new ChromeDriver();
    }

    private static String setDriverPathForChrome(String os) {
        if (os == "Mac OS X")           return ChromeDriverPathForMac;
        else if (os == "WINDOWS 10")    return ChromeDriverPathForWindows10;
        else                            return ChromeDriverPathForMac;
    }

    public static void Close() {

        DriverInstance.close();
    }

    public static void Quit() {

        DriverInstance.quit();
    }

    public static String getBaseAddress() {
        return BaseAddress;
    }

    public static void waitForClickable(By locator, int timeUnit) {

        WebDriverWait wait = new WebDriverWait(DriverManager.DriverInstance,timeUnit);

        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForVisible(By locator, int timeUnit) {

        WebDriverWait wait = new WebDriverWait(DriverManager.DriverInstance,timeUnit);

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
