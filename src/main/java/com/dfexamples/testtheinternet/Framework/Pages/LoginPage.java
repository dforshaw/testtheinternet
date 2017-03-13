package com.dfexamples.testtheinternet.Framework.Pages;

import com.dfexamples.testtheinternet.Framework.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static com.dfexamples.testtheinternet.Framework.DriverManager.BaseAddress;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginPage {

    private static By usernameSelector = By.id("username");
    private static By errorMsgSelector = By.cssSelector(".flash.error");;

    public static void GoTo() {

        DriverManager.DriverInstance.navigate().to(BaseAddress + "/login");
        assertThat(DriverManager.DriverInstance.findElement(usernameSelector).isDisplayed());
    }

    public static LoginCommand LoginAs(String userName) {

        return new LoginCommand(userName);
    }

    public static String getErrorMsg() {

        try {
            return DriverManager.DriverInstance.findElement(errorMsgSelector).getText();

        } catch (NoSuchElementException e) {
            return String.valueOf(e);
        }
    }
}
