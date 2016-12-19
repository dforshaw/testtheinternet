package com.dfexamples.testtheinternet.LoginTests;

import com.dfexamples.testtheinternet.BaseTest;
import com.dfexamples.testtheinternet.Pages.MainPage;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstTest extends BaseTest {

    @Test
    public void basicFirefoxTest() {
        setDriverToFirefox();
        MainPage.GoTo();         // Not implemented yet
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

    @Test
    public void getSystemProperties() {
        String os = System.getProperty("os.name").toUpperCase();
        System.out.println("Current Operating System: " + os);
        String sysArch = System.getProperty("os.arch").toUpperCase();
        System.out.println("Current System Architecture: " + sysArch);
    }
}
