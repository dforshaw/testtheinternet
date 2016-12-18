package com.dfexamples.testtheinternet.LoginTests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstLoginTest {

    public static String userHomeDir = System.getProperty("user.home");
    public static String Webdriver_Clients = userHomeDir + "/proj/webdriver_clients";

    @Test
    public void basicTest() {
        String fireFoxDriverLocation = Webdriver_Clients + "/geckodriver/geckodriver";
        System.setProperty("webdriver.gecko.driver", fireFoxDriverLocation);
        WebDriver driver = new FirefoxDriver();

        driver.get("http://the-internet.herokuapp.com/");
        assertThat(driver.getTitle()).isEqualToIgnoringCase("The Internet1");

        driver.close();
        driver.quit();
    }
}
