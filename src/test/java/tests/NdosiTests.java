package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Base;

public class NdosiTests extends Base {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return testData.ReadFromExcel.getLoginDataFromExcel("src/test/java/testData/data.xlsx", "LoginData");
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String email, String password) {
        menuPage.clickMenuLoginButton();
        loginPage.enterUsername(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        dashboardPage.validateUserLoggedInSuccessfully();
        dashboardPage.clickMenuButton();
        dashboardPage.clickLogoutButton();
        dashboardPage.closeAlertWindow();
    }

}
