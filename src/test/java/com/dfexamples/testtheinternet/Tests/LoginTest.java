package com.dfexamples.testtheinternet.Tests;

import com.dfexamples.testtheinternet.Framework.Pages.LoginPage;
import com.dfexamples.testtheinternet.Framework.Pages.SecureLoggedInPage;
import com.dfexamples.testtheinternet.Framework.Utilities.Capture;
import org.junit.Test;

import java.io.IOException;

import static com.dfexamples.testtheinternet.Framework.Enums.TestUser.USER_W_BAD_LOGIN;
import static com.dfexamples.testtheinternet.Framework.Enums.TestUser.USER_W_BAD_PWD;
import static com.dfexamples.testtheinternet.Framework.Enums.TestUser.USER_W_GOOD_INFO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;

public class LoginTest extends BaseTest {

    @Test
    public void User_Can_Login() {

        LoginPage.GoTo();

        LoginPage.LoginAs(USER_W_GOOD_INFO.getUsername()).WithPassword(USER_W_GOOD_INFO.getPassword()).Login();

        assertThat("Failed to login",
                SecureLoggedInPage.IsAt(),
                is(true));
    }

    @Test
    public void User_With_Wrong_Password_Gets_Error() {

        LoginPage.GoTo();

        LoginPage.LoginAs(USER_W_BAD_PWD.getUsername()).WithPassword(USER_W_BAD_PWD.getPassword()).Login();

        try {
            assertThat("Received error message from bad password",
                    LoginPage.getErrorMsg(),
                    startsWith("Your password is invalid!"));
        } finally {
            try {
                Capture.TakeScreenshot("LoginPage");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void User_With_Wrong_Username_Gets_Error() {

        LoginPage.GoTo();

        LoginPage.LoginAs(USER_W_BAD_LOGIN.getUsername()).WithPassword(USER_W_BAD_LOGIN.getPassword()).Login();

        try {
        assertThat("Received error message from bad password",
                LoginPage.getErrorMsg(),
                startsWith("Your username is invalid!"));
        } finally {
            try {
                Capture.TakeScreenshot("LoginPage");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
