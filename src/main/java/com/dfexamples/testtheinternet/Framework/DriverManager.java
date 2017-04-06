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
import static com.dfexamples.testtheinternet.Framework.Enums.PathConfig.VENDOR_PATH_LOCAL;

public class DriverManager {

    public static WebDriver DriverInstance;
    public static String BaseAddress = PropertyManager.getProperty("base_address");
    public static String RepoName = PropertyManager.getProperty("repo_name");
    public static String OperatingSystem = PropertyManager.getProperty("operating_system");
    public static String UserHomeDir = System.getProperty("user.home");
    public static String ProjHomeDir = UserHomeDir + PROJ_LOCATION_HOME.getPath() + RepoName;
//    public static String ProjHomeDir = UserHomeDir + PROJ_LOCATION_WORK.getPath() + RepoName;
//    public static String BrowserDriverVendorDir = ProjHomeDir + VENDOR_PATH.getPath();
    public static String BrowserDriverVendorDir = ProjHomeDir + "/.." + VENDOR_PATH_LOCAL.getPath();

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
                setUpDriverInstanceUsingChrome();
                break;

            case "Firefox":
                setUpDriverInstanceUsingFirefox();
                break;

            case "Marionette":
                setUpDriverInstanceUsingMarionette();
                break;

            default:
                setUpDriverInstanceUsingChrome();
                break;
        }
    }

    private static void setUpDriverInstanceUsingChrome() {
        String path = BrowserDriverVendorDir + DRIVER_PATH_CHROME.getPath();
        path = determineExtensionForBrowserDriver(path);

        System.setProperty("webdriver.chrome.driver", path);
        DriverInstance = new ChromeDriver();
    }

    private static void setUpDriverInstanceUsingFirefox() {
        String path = BrowserDriverVendorDir + DRIVER_PATH_GECKO.getPath();
        path = determineExtensionForBrowserDriver(path);

        System.setProperty("webdriver.gecko.driver", path);
        DriverInstance = new FirefoxDriver();
    }

    private static void setUpDriverInstanceUsingMarionette() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);

        String path = BrowserDriverVendorDir + DRIVER_PATH_GECKO.getPath();
        path = determineExtensionForBrowserDriver(path);

        System.setProperty("webdriver.gecko.driver", path);
        DriverInstance = new FirefoxDriver(capabilities);
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
