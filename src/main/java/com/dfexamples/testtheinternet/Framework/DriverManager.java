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
    public static String FirefoxDriverPathForWindows10 = "/geckodriver/geckodriver.exe";
    public static String MarionetteDriverPathForMac = "/geckodriver/geckodriver";
    public static String MarionetteDriverPathForWindows10 = "/geckodriver/geckodriver.exe";
    public static String ChromeDriverPathForMac = "/chromedriver/chromedriver";
    public static String ChromeDriverPathForWindows10 = "/chromedriver/chromedriver.exe";

    public static void Initialize(String browsername) {

        switch (browsername) {
            case "":
                DriverInstance = new FirefoxDriver();
                break;

            case "Firefox":
                System.setProperty("webdriver.gecko.driver",
                        Webdriver_Clients + getDriverPath("Firefox", OperatingSystem));
                DriverInstance = new FirefoxDriver();
                break;

            case "Marionette":
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", true);
                System.setProperty("webdriver.gecko.driver",
                        Webdriver_Clients + getDriverPath("Marionette", OperatingSystem));
                DriverInstance = new FirefoxDriver(capabilities);
                break;

            case "Chrome":
                System.setProperty("webdriver.chrome.driver",
                        Webdriver_Clients + getDriverPath("Chrome", OperatingSystem));
                DriverInstance = new ChromeDriver();
                break;
        }

        DriverInstance.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private static String getDriverPath(String browsername, String operatingSystem) {

        String path ="";

        if (browsername.equalsIgnoreCase("Firefox")) {
            if (operatingSystem.equalsIgnoreCase("mac os x"))
                path = FirefoxDriverPathForMac;
            else if (operatingSystem.equalsIgnoreCase("windows 10"))
                path = FirefoxDriverPathForWindows10;
        }
        else if (browsername.equalsIgnoreCase("Marionette")) {
            if (operatingSystem.equalsIgnoreCase("mac os x"))
                path = MarionetteDriverPathForMac;
            else if (operatingSystem.equalsIgnoreCase("windows 10"))
                path = MarionetteDriverPathForWindows10;
        }
        else if (browsername.equalsIgnoreCase("Chrome")) {
            if (operatingSystem.equalsIgnoreCase("mac os x"))
                path = ChromeDriverPathForMac;
            else if (operatingSystem.equalsIgnoreCase("windows 10"))
                path = ChromeDriverPathForWindows10;
        }

        return path;
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
