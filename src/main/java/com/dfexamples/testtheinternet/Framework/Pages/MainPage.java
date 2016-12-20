package com.dfexamples.testtheinternet.Framework.Pages;

import com.dfexamples.testtheinternet.Framework.Driver;

import static com.dfexamples.testtheinternet.Framework.Driver.BaseAddress;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPage {

    public static void GoTo() {
        Driver.Instance.navigate().to(BaseAddress);
        assertThat(Driver.Instance.getTitle()).isEqualToIgnoringCase("The Internet");
    }
}
