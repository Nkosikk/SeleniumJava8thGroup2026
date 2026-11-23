package utils;

import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.LoginPage;
import pages.MenuPage;

public class Base {

    static final BrowserFactory factory = new BrowserFactory();
    public static  final WebDriver driver = factory.startBrowser("chrome", "https://ndosisimplifiedautomation.vercel.app/");
    public MenuPage menuPage = new MenuPage(driver);
    public LoginPage loginPage = new LoginPage(driver);
    public DashboardPage dashboardPage = new DashboardPage(driver);
}
