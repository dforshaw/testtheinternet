package com.dfexamples.testtheinternet.Framework.Enums;

public enum TestURL {

    LOGIN_PAGE("/login"),
    MAIN_PAGE("/");

    String url;

    private TestURL(String url_to_use) {
        this.url = url_to_use;
    }

    public String getUrl() {
        return url;
    }
}
