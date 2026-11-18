package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MenuPage {

    WebDriver driver;

    By menuLoginButton_xpath = By.xpath("//div[@class='nav-container']/div[@class='nav-user-section']");

    public MenuPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickMenuLoginButton(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(menuLoginButton_xpath));
        driver.findElement(menuLoginButton_xpath).click();
    }

}
