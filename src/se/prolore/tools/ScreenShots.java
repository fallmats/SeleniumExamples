package se.prolore.tools;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ScreenShots {
    private WebDriver driver;

    public ScreenShots(WebDriver driver) {
        this.driver = driver;

    }

    public void takeScreenShot(String testName) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("c:\\tmp\\seleniumScreenshots\\" + testName + "_" + formater.format(calendar.getTime()) + ".png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
