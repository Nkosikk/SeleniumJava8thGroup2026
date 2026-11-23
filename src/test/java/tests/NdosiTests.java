package tests;

import org.testng.annotations.Test;
import utils.Base;

public class NdosiTests extends Base {

    @Test
    public void loginTest() {
        menuPage.clickMenuLoginButton();
        loginPage.enterUsername("nkwanyana@gmail.com");
        loginPage.enterPassword("#12345678");
        loginPage.clickLoginButton();
        dashboardPage.validateUserLoggedInSuccessfully();
    }

}
