package com.dfexamples.testtheinternet.Framework;

import com.dfexamples.testtheinternet.Framework.Utilities.Props;
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
    public static String BaseAddress = Props.getProperty("base_address");
    public static String OperatingSystem = Props.getProperty("operating_system");
    public static String UserHomeDir = System.getProperty("user.home");
//    public static String ProjHomeDir = UserHomeDir + "/Code/testtheinternet";
    public static String ProjHomeDir = UserHomeDir + "/Ideaprojects/testtheinternet";
    public static String BrowserDriverVendorDir = ProjHomeDir + "/vendors";
    public static String FirefoxDriverPathForMac = "/geckodriver/geckodriver";
    public static String FirefoxDriverPathForWindows = "/geckodriver/geckodriver.exe";
    public static String MarionetteDriverPathForMac = "/geckodriver/geckodriver";
    public static String MarionetteDriverPathForWindows = "/geckodriver/geckodriver.exe";
    public static String ChromeDriverPathForMac = "/chromedriver/chromedriver";
    public static String ChromeDriverPathForWindows = "/chromedriver/chromedriver.exe";

    public static void Initialize() {
        String browsername = Props.getProperty("SelectedBrowser");

        if (browsername.isEmpty())
            browsername = System.getProperty("browserType");

        setUpDriverInstance(browsername);

        DriverInstance.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private static void setUpDriverInstance(String browsername) {
        switch (browsername) {
            case "Chrome":
                setUpDriverInstanceUsingChrome(usingPathForThirdPartyBrowserDriverByOS(browsername));
                break;

            case "Firefox":
                setUpDriverInstanceUsingFirefox(usingPathForThirdPartyBrowserDriverByOS(browsername));
                break;

            case "Marionette":
                setUpDriverInstanceUsingMarionette(usingPathForThirdPartyBrowserDriverByOS(browsername));
                break;

            default:
                setUpDriverInstanceUsingChrome(usingPathForThirdPartyBrowserDriverByOS(browsername));
                break;
        }
    }

    private static void setUpDriverInstanceUsingChrome(String driverPath) {
        System.setProperty("webdriver.chrome.driver", BrowserDriverVendorDir + driverPath);
        DriverInstance = new ChromeDriver();
    }

    private static void setUpDriverInstanceUsingFirefox(String driverPath) {
        System.setProperty("webdriver.gecko.driver", BrowserDriverVendorDir + driverPath);
        DriverInstance = new FirefoxDriver();
    }

    private static void setUpDriverInstanceUsingMarionette(String driverPath) {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        System.setProperty("webdriver.gecko.driver", BrowserDriverVendorDir + driverPath);
        DriverInstance = new FirefoxDriver(capabilities);
    }

    private static String usingPathForThirdPartyBrowserDriverByOS(String browsername) {
        switch (browsername) {
            case "Chrome":
                return (OperatingSystem.equalsIgnoreCase("mac os x"))
                        ? ChromeDriverPathForMac : ChromeDriverPathForWindows;

            case "Firefox":
                return (OperatingSystem.equalsIgnoreCase("mac os x"))
                        ? FirefoxDriverPathForMac : FirefoxDriverPathForWindows;

            case "Marionette":
                return (OperatingSystem.equalsIgnoreCase("mac os x"))
                        ? MarionetteDriverPathForMac : MarionetteDriverPathForWindows;

            default:
                return (OperatingSystem.equalsIgnoreCase("mac os x"))
                        ? ChromeDriverPathForMac : ChromeDriverPathForWindows;
        }
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
