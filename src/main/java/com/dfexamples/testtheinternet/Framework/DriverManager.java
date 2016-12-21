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
    public static String ProjHomeDir = UserHomeDir + "/Ideaprojects/testtheinternet";
    public static String BrowserDriverVendorDir = ProjHomeDir + "/vendors";
    public static String FirefoxDriverPathForMac = "/geckodriver/geckodriver";
    public static String FirefoxDriverPathForWindows10 = "/geckodriver/geckodriver.exe";
    public static String MarionetteDriverPathForMac = "/geckodriver/geckodriver";
    public static String MarionetteDriverPathForWindows10 = "/geckodriver/geckodriver.exe";
    public static String ChromeDriverPathForMac = "/chromedriver/chromedriver";
    public static String ChromeDriverPathForWindows10 = "/chromedriver/chromedriver.exe";

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
                setUpDriverInstanceUsingChrome();
                break;

            case "Firefox":
                setUpDriverInstanceUsingFirefox();
                break;

            case "Marionette":
                setUpDriverInstanceUsingmMarionette();
                break;

            default:
                setUpDriverInstanceUsingChrome();
                break;
        }
    }

    private static void setUpDriverInstanceUsingChrome() {
        System.setProperty("webdriver.chrome.driver",setPathForThirdPartyBrowserDriver("Chrome"));
        DriverInstance = new ChromeDriver();
    }

    private static void setUpDriverInstanceUsingFirefox() {
        System.setProperty("webdriver.gecko.driver",setPathForThirdPartyBrowserDriver("Firefox"));
        DriverInstance = new FirefoxDriver();
    }

    private static void setUpDriverInstanceUsingmMarionette() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        System.setProperty("webdriver.gecko.driver",setPathForThirdPartyBrowserDriver("Marionette"));
        DriverInstance = new FirefoxDriver(capabilities);
    }

    private static String setPathForThirdPartyBrowserDriver(String browsername) {

        String thirdPartyBrowserDriverPath ="";

        if (browsername.equalsIgnoreCase("Chrome")) {
            thirdPartyBrowserDriverPath = (OperatingSystem.equalsIgnoreCase("mac os x"))
                    ? ChromeDriverPathForMac : ChromeDriverPathForWindows10;
        }
        else if (browsername.equalsIgnoreCase("Firefox")) {
            thirdPartyBrowserDriverPath = (OperatingSystem.equalsIgnoreCase("mac os x"))
                    ? FirefoxDriverPathForMac : FirefoxDriverPathForWindows10;
        }
        else if (browsername.equalsIgnoreCase("Marionette")) {
            thirdPartyBrowserDriverPath = (OperatingSystem.equalsIgnoreCase("mac os x"))
                    ? MarionetteDriverPathForMac : MarionetteDriverPathForWindows10;
        }

        return BrowserDriverVendorDir + thirdPartyBrowserDriverPath;
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
