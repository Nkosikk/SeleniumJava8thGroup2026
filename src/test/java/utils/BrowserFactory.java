package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {

    static WebDriver driver;

    public static WebDriver startBrowser(String browserType, String url){

        if (browserType.equalsIgnoreCase("cHrOme")){
            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless=new");
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);

        } else if (browserType.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();

        } else if (browserType.equalsIgnoreCase("Safari")){
            driver = new SafariDriver();

        } else {
            driver = new EdgeDriver();
        }

        driver.get(url);
        driver.manage().window().maximize();

        return driver;
    }


}
