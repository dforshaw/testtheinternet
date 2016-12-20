package com.dfexamples.testtheinternet.Tests;

import com.dfexamples.testtheinternet.Framework.Driver;
import org.junit.After;
import org.junit.Before;

public class BaseTest implements Config {

    @Before
    public void setUp() {
        Driver.Initialize(browserType);
    }

    @After
    public void tearDown() {
        Driver.Close();
        Driver.Quit();
    }
}
