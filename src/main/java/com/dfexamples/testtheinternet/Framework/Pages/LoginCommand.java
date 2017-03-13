package com.dfexamples.testtheinternet.Framework.Pages;

import com.dfexamples.testtheinternet.Framework.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginCommand {

    private static By usernameField = By.id("username");
    private static By passwordField = By.id("password");
    private static By loginButtonSelector = By.cssSelector("button[type='submit']");

    private String userNameToUse;
    private String passwordToUse;

    public LoginCommand (String enteredUsername){

        this.userNameToUse = enteredUsername;
    }

    public LoginCommand WithPassword(String enteredPassword) {

        this.passwordToUse = enteredPassword;
        return this;
    }

    public void Login() {

        WebElement loginInput = DriverManager.DriverInstance.findElement(usernameField);
        loginInput.sendKeys(userNameToUse);

        WebElement passwordInput = DriverManager.DriverInstance.findElement(passwordField);
        passwordInput.sendKeys(passwordToUse);

        WebElement loginButton = DriverManager.DriverInstance.findElement(loginButtonSelector);
        loginButton.click();
    }
}
