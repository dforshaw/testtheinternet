package com.dfexamples.testtheinternet.LoginTests;

import com.dfexamples.testtheinternet.BaseTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstTest extends BaseTest {

    @Test
    public void basicFirefoxTest() {
        setDriverToFirefox();
        driver.get(baseUrl);
        assertThat(driver.getTitle()).isEqualToIgnoringCase("The Internet");
        driver.close();
    }

    @Test
    public void basicMarionetteTest() {
        setDriverToMarionette();
        driver.get(baseUrl);
        assertThat(driver.getTitle()).isEqualToIgnoringCase("The Internet");
        driver.close();
    }

    @Test
    public void basicChromeTest() {
        setDriverToChrome();
        driver.get(baseUrl);
        assertThat(driver.getTitle()).isEqualToIgnoringCase("The Internet");
        driver.close();
    }
}
