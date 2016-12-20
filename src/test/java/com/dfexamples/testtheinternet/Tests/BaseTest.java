package com.dfexamples.testtheinternet.Tests;

import com.dfexamples.testtheinternet.Framework.DriverManager;
import org.junit.After;
import org.junit.Before;

public class BaseTest implements Config {

    @Before
    public void setUp() {
        DriverManager.Initialize(browserType);
    }

    @After
    public void tearDown() {
        DriverManager.Close();
        DriverManager.Quit();
    }
}
