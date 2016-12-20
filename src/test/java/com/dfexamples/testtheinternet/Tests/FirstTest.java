package com.dfexamples.testtheinternet.Tests;

import com.dfexamples.testtheinternet.Framework.Pages.MainPage;
import org.junit.Test;

public class FirstTest extends BaseTest {

    @Test
    public void basicTest() {
        MainPage.GoTo();
    }
}
