package com.dfexamples.testtheinternet;

import org.junit.After;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    public static WebDriver driver;

    @After
    public void tearDown() {
        driver.quit();
    }
}
