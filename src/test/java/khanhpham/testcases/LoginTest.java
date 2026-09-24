package khanhpham.testcases;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import khanhpham.common.BaseClass;
import khanhpham.dataprovider.DataProviderFactory;
import khanhpham.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {

    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void initPage (){
        loginPage = new LoginPage();
    }

    @Test (priority = 1)
    @Severity(SeverityLevel.MINOR)
    @Description("Test case to verify Login Menu display ?")
    public void verifyLoginMenuDisplay (){
        Assert.assertTrue(loginPage.isMainMenuDisplay());
        loginPage.clickIntoMainMenu();
        Assert.assertTrue(loginPage.isLoginMenuDisplay());
        Assert.assertEquals(loginPage.getTextOfLoginMenu(),"Log In");
    }

    @Test (priority = 2)
    public void verifyTitleLoginPage (){
        loginPage.clickIntoLoginMenu();
        Assert.assertTrue(loginPage.isTitleLoginPageDisplay());
        Assert.assertEquals(loginPage.getTextOfTitleLoginPage(),"Login");
    }

    @Test (priority = 3)
    public void verifyDescriptionLoginPage (){
        Assert.assertTrue(loginPage.isDescriptionLoginPageDisplay());
        Assert.assertEquals(loginPage.getTextOfDescriptionLoginPage(),"Select a username and password from the list below, or click on the username to automatically populate the username and password.");
    }

    @Test (priority = 4)
    public void verifyUserNameFieldDisplay (){
        Assert.assertTrue(loginPage.isNameUserNameFieldDisplay());
        Assert.assertEquals(loginPage.getTextOfNameUserNameField(),"Username");
    }

    @Test (priority = 5)
    public void verifyPassWordFieldDisplay (){
        Assert.assertTrue(loginPage.isNamePassWordFieldDisplay());
        Assert.assertEquals(loginPage.getTextOfNamePassWordField(),"Password");
    }

    @Test (priority = 6)
    public void verifyLoginButtonDisplay (){
        Assert.assertTrue(loginPage.isLoginButtonDisplay());
        Assert.assertEquals(loginPage.getTextOfLoginButton(),"Login");
    }

    @Test (priority = 7)
    public void verifyNameUserNameListDisplay(){
        Assert.assertTrue(loginPage.isNameUserNameListDisplay());
        Assert.assertEquals(loginPage.getTextOfNameUserNameList(),"Usernames");
    }

    @Test (priority = 8)
    public void verifyValidLoginAccountDisplay(){
        Assert.assertTrue(loginPage.isValidLoginAccountDisplay());
        Assert.assertEquals(loginPage.getTextOfValidLoginAccount(),"bod@example.com");
    }

    @Test (priority = 9)
    public void verifyBlockLoginAccountDisplay(){
        Assert.assertTrue(loginPage.isBlockLoginAccountDisplay());
        Assert.assertEquals(loginPage.getTextOfBlockLoginAccount(),"alice@example.com (locked out)");
    }

    @Test (priority = 10)
    public void verifyVisualLoginAccountDisplay(){
        Assert.assertTrue(loginPage.isVisualLoginAccountDisplay());
        Assert.assertEquals(loginPage.getTextOfVisualLoginAccount(),"visual@example.com");
    }

    @Test (priority = 11)
    public void loginWithEmptyUserName(){
        loginPage.loginWithUserNameAndPassWord("","123456");
        Assert.assertTrue(loginPage.isAlertEmptyUserNameDisplay());
        Assert.assertEquals(loginPage.getTextOfAlertEmptyUserName(),"Username is required");
    }

    @Test (priority = 12)
    public void loginWithEmptyPassWord(){
        loginPage.loginWithUserNameAndPassWord("123456","");
        Assert.assertTrue(loginPage.isAlertEmptyPassWordDisplay());
        Assert.assertEquals(loginPage.getTextOfAlertEmptyPassWord(),"Enter Password");
    }

    @Test (priority = 13)
    public void loginWithBlockedAccount(){
        loginPage.loginBlockedAccount();
        Assert.assertTrue(loginPage.isAlertBlockAccountDisplay());
        Assert.assertEquals(loginPage.getTextOfAlertBlockAccount(),"Sorry this user has been locked out.");
    }

    @Test (dataProvider = "admin_account", dataProviderClass = DataProviderFactory.class, priority = 14)
    public void loginAdminAccount(String userName, String passWord){
        loginPage.loginWithUserNameAndPassWord(userName,passWord);
        loginPage.clickIntoMainMenu();
        Assert.assertTrue(loginPage.isLogOutMenuDisplay());
        Assert.assertEquals(loginPage.getTextOfLogOutMenu(),"Log Out");
    }


}
