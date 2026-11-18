package utils;

import org.openqa.selenium.WebDriver;
import pages.MenuPage;

public class Base {

    static final BrowserFactory factory = new BrowserFactory();
    public static  final WebDriver driver;

    static {
        try {
            driver = factory.startBrowser("chrome", "https://ndosisimplifiedautomation.vercel.app/");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public MenuPage menuPage = new MenuPage(driver);
}
