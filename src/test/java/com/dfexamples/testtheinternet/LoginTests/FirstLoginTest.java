package com.dfexamples.testtheinternet.LoginTests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstLoginTest {

    @Test
    public void basicTest() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://the-internet.herokuapp.com/");
        assertThat(driver.getTitle()).isEqualToIgnoringCase("The Internet");
    }
}
