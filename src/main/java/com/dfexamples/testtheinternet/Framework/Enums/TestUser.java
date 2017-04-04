package com.dfexamples.testtheinternet.Framework.Enums;

public enum TestUser {

    USER_W_GOOD_INFO("tomsmith","SuperSecretPassword!"),
    USER_W_BAD_PWD("tomsmith","wrongpassword"),
    USER_W_BAD_LOGIN("wrongusername","SuperSecretPassword!");

    String username;
    String password;

    private TestUser(String username_to_login_with, String password_to_login_with) {
        this.username = username_to_login_with;
        this.password = password_to_login_with;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
