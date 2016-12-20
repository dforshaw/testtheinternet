package com.dfexamples.testtheinternet.Framework.Pages;

import com.dfexamples.testtheinternet.Framework.DriverManager;

import static com.dfexamples.testtheinternet.Framework.DriverManager.BaseAddress;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPage {

    public static void GoTo() {
        DriverManager.DriverInstance.navigate().to(BaseAddress);
        assertThat(DriverManager.DriverInstance.getTitle()).isEqualToIgnoringCase("The Internet");
    }
}
