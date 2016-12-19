package com.dfexamples.testtheinternet.LoginTests;

import com.dfexamples.testtheinternet.BaseTest;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstTest extends BaseTest{

    public static String userHomeDir = System.getProperty("user.home");
    public static String projHomeDir = userHomeDir + "/Ideaprojects/testtheinternet";
    public static String Webdriver_Clients = projHomeDir + "/vendors";

    @Test
    public void basicFirefoxTest() {
        String fireFoxDriverLocation = Webdriver_Clients + "/geckodriver/geckodriver";
        System.setProperty("webdriver.gecko.driver", fireFoxDriverLocation);
        driver = new FirefoxDriver();

        driver.get("http://the-internet.herokuapp.com/");
        assertThat(driver.getTitle()).isEqualToIgnoringCase("The Internet");
        driver.close();
    }

    @Test
    public void basicMarionetteTest() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        String marionetteDriverLocation = Webdriver_Clients + "/geckodriver/geckodriver";
        System.setProperty("webdriver.gecko.driver", marionetteDriverLocation);
        driver = new FirefoxDriver(capabilities);

        driver.get("http://the-internet.herokuapp.com/");
        assertThat(driver.getTitle()).isEqualToIgnoringCase("The Internet");
        driver.close();
    }

    @Test
    public void basicChromeTest() {
        String chromeDriverLocation = Webdriver_Clients + "/chromedriver/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
        driver = new ChromeDriver();

        driver.get("http://the-internet.herokuapp.com/");
        assertThat(driver.getTitle()).isEqualToIgnoringCase("The Internet");
        driver.close();
    }
}
