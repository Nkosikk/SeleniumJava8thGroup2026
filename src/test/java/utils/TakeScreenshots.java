package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import java.io.File;
import java.io.IOException;

public class TakeScreenshots {

    //Using File.separator makes this path platform-independent (Windows vs Unix)
    private  static final String screenshotDir = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + "Screenshots";

    public static String takesSnapShot(WebDriver driver, String screenshotName) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationDirectory = new File(screenshotDir);

        if (!destinationDirectory.exists() && !destinationDirectory.mkdir()){
            throw new IOException("Unable to create screenshot directory: " + destinationDirectory.getAbsolutePath());
        }

        File destination = new File(destinationDirectory, screenshotName + ".png");
        FileUtils.copyFile(src, destination);

        return destination.getAbsolutePath();
    }
}
