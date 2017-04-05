package com.dfexamples.testtheinternet.Framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.dfexamples.testtheinternet.Framework.Enums.PathConfig.DRIVER_PATH_CHROME;
import static com.dfexamples.testtheinternet.Framework.Enums.PathConfig.DRIVER_PATH_GECKO;
import static com.dfexamples.testtheinternet.Framework.Enums.PathConfig.PROJ_LOCATION_HOME;
import static com.dfexamples.testtheinternet.Framework.Enums.PathConfig.VENDOR_PATH;

public class DriverManager {

    public static WebDriver DriverInstance;
    public static String BaseAddress = PropertyManager.getProperty("base_address");
    public static String OperatingSystem = PropertyManager.getProperty("operating_system");
    public static String UserHomeDir = System.getProperty("user.home");
    public static String ProjHomeDir = UserHomeDir + PROJ_LOCATION_HOME.getPath() + "/testtheinternet";
//    public static String ProjHomeDir = UserHomeDir + PROJ_LOCATION_WORK.getPath() + "/testtheinternet";
    public static String BrowserDriverVendorDir = ProjHomeDir + VENDOR_PATH.getPath();

    public static void Initialize() {
        String browsername = PropertyManager.getProperty("SelectedBrowser");

        if (browsername.isEmpty())
            browsername = System.getProperty("browserType");

        setUpDriverInstance(browsername);

        DriverInstance.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private static void setUpDriverInstance(String browsername) {
        switch (browsername) {
            case "Chrome":
                setUpDriverInstanceUsingChrome(
                        determineExtensionForBrowserDriver(
                                usingPathForThirdPartyBrowserDriverByOS(browsername)));
                break;

            case "Firefox":
                setUpDriverInstanceUsingFirefox(
                        determineExtensionForBrowserDriver(
                                usingPathForThirdPartyBrowserDriverByOS(browsername)));
                break;

            case "Marionette":
                setUpDriverInstanceUsingMarionette(
                        determineExtensionForBrowserDriver(
                                usingPathForThirdPartyBrowserDriverByOS(browsername)));
                break;

            default:
                setUpDriverInstanceUsingChrome(
                        determineExtensionForBrowserDriver(
                                usingPathForThirdPartyBrowserDriverByOS(browsername)));
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
                return DRIVER_PATH_CHROME.getPath();

            case "Firefox":
                return DRIVER_PATH_GECKO.getPath();

            case "Marionette":
                return DRIVER_PATH_GECKO.getPath();

            default:
                return DRIVER_PATH_CHROME.getPath();
        }
    }

    private static String determineExtensionForBrowserDriver(String driverPath) {
        return (OperatingSystem.equalsIgnoreCase("mac os x") ? driverPath : driverPath + ".exe");
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
