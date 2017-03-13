package com.dfexamples.testtheinternet.Framework.Pages;

import com.dfexamples.testtheinternet.Framework.DriverManager;
import org.openqa.selenium.By;

public class SecureLoggedInPage {

    private static By SecurePageSuccessMsgSelector = By.cssSelector(".flash.success");
    private static By SecurePageLogoutButtonSelector = By.cssSelector(".icon-signout");
    private static By SecurePageSubheaderSelector = By.cssSelector("h4.subheader");

    public static Boolean IsAt() {

        DriverManager.waitForVisible(SecurePageLogoutButtonSelector, 10);
        DriverManager.waitForVisible(SecurePageSuccessMsgSelector, 10);

        // Confirm whether login success message is displayed

        if ((DriverManager.DriverInstance.findElement(SecurePageSuccessMsgSelector).getText()
                .contains("You logged into a secure area!"))
            && (DriverManager.DriverInstance.findElement(SecurePageSubheaderSelector).getText()
                .contains("Welcome to the Secure Area"))) {

            return true;
        }

        return false;
    }
}
