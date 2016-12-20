package com.dfexamples.testtheinternet.Framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Driver {

    public static WebDriver Instance;
    public static String BaseAddress = "http://the-internet.herokuapp.com/";
    public static String OperatingSystem = System.getProperty("os.name").toUpperCase();
    public static String UserHomeDir = System.getProperty("user.home");
    public static String ProjHomeDir = UserHomeDir + "/Ideaprojects/testtheinternet";
    public static String Webdriver_Clients = ProjHomeDir + "/vendors";

    public static void Initialize(String browsername) {
        switch (browsername) {
            case "":
                Instance = new FirefoxDriver();
                break;

            case "Firefox":
                setDriverToFirefox();
                break;

            case "Marionette":
                setDriverToMarionette();
                break;

            case "Chrome":
                setDriverToChrome(OperatingSystem);
                break;
        }

        Instance.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private static void setDriverToFirefox() {
        String fireFoxDriverLocation = Webdriver_Clients + "/geckodriver/geckodriver";
        System.setProperty("webdriver.gecko.driver", fireFoxDriverLocation);
        Instance = new FirefoxDriver();
    }

    private static void setDriverToMarionette() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        String marionetteDriverLocation = Webdriver_Clients + "/geckodriver/geckodriver";
        System.setProperty("webdriver.gecko.driver", marionetteDriverLocation);
        Instance = new FirefoxDriver(capabilities);
    }

    private static void setDriverToChrome(String os) {
        String driverPath = "";
        if (os == "Mac OS X")           driverPath = "/chromedriver/chromedriver";
        else if (os == "WINDOWS 10")    driverPath = "/chromedriver/chromedriver.exe";
        else                            driverPath = "/chromedriver/chromedriver";

        String chromeDriverLocation = Webdriver_Clients + driverPath;
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
        Instance = new ChromeDriver();
    }

    public static void Close() {

        Instance.close();
    }

    public static void Quit() {

        Instance.quit();
    }

    public static String getBaseAddress() {
        return BaseAddress;
    }

    public static void waitForClickable(By locator, int timeUnit) {

        WebDriverWait wait = new WebDriverWait(Driver.Instance,timeUnit);

        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForVisible(By locator, int timeUnit) {

        WebDriverWait wait = new WebDriverWait(Driver.Instance,timeUnit);

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
