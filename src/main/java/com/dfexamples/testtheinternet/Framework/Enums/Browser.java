package com.dfexamples.testtheinternet.Framework.Enums;

public enum Browser {
    BROWSER_CHROME("Chrome"),
    BROWSER_MARIONETTE("Marionette"),
    BROWSER_FIREFOX("Firefox");

    String browser;

    private Browser(String browser_to_use) {
        this.browser = browser_to_use;
    }

    public String getBrowser() {
        return browser;
    }
}
