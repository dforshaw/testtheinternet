package com.dfexamples.testtheinternet.Framework.Utilities;

import com.dfexamples.testtheinternet.Framework.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class Capture {
    public static void TakeScreenshot(String testSet) throws IOException {

        try{
            if(((HasCapabilities) DriverManager.DriverInstance).getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)) {

                TakesScreenshot snapper = (TakesScreenshot)DriverManager.DriverInstance;
                File tempImageFile = snapper.getScreenshotAs(OutputType.FILE);

                assertThat(tempImageFile.length(), is(greaterThan(0L)));

                File testTempDir = createATempDirectoryForScreenshots(testSet);
                String newImageFileName = testSet + "_" +
                        new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                        "." +
                        getExtension(tempImageFile);

                File testTempImage = new File(testTempDir, newImageFileName);

                // move screenshot to our local store
                FileUtils.moveFile(tempImageFile, testTempImage);
                assertThat(testTempImage.length(), is(greaterThan(0L)));

                // use these lines in debug mode
                System.out.println("Temp file written to " + testTempImage.getAbsolutePath());
                DriverManager.DriverInstance.get("File://"+ testTempImage.getAbsolutePath());
            }else{
                fail("Driver did not support screenshots");
            }
        }catch(ClassCastException e){
            // if we cannot cast it to TakesScreenshot we will get a ClassCastException
            System.out.println(e);
            fail("Driver did not support screenshots");
        }
    }

    private static String getExtension(File fileWithExtension) {
        String fileName = fileWithExtension.getName();
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }

    private static File createATempDirectoryForScreenshots(String testSet) {
        String s = File.separator;
        String ourTestTempPathName = System.getProperty("user.dir") +
                String.format("%ssrc%stest%sresources%stemp%sscreenshots%s"+testSet,s,s,s,s,s,s);

        File testTempDir = new File(ourTestTempPathName);
        if(testTempDir.exists()){
            if(!testTempDir.isDirectory()){
                fail("Test path exists but is not a directory");
            }
        }else{
            testTempDir.mkdirs();
        }

        return testTempDir;
    }
}
