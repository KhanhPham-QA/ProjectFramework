package khanhpham.pages;

import com.beust.ah.A;
import io.appium.java_client.AppiumBy;
import khanhpham.helpers.PlatformBy;
import khanhpham.keywords.MobileUI;
import org.openqa.selenium.By;

public class LoginPage {

    private final By mainMenu = PlatformBy.isPlatform(
            AppiumBy.accessibilityId("View menu"),
            AppiumBy.accessibilityId("More-tab-item")
    );
    private final By loginMenu = PlatformBy.isPlatform(
            AppiumBy.accessibilityId("Login Menu Item"),
            AppiumBy.accessibilityId("LogOut-menu-item")
    );
    private final By titleLoginPage = PlatformBy.isPlatform(
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/loginTV"),
            AppiumBy.accessibilityId("Login")
    );
    private final By descriptionLoginPage = PlatformBy.isPlatform(
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/selectTextTV"),
            AppiumBy.accessibilityId("Select a username from the list below")
    );
    private final By nameUserNameField = PlatformBy.isPlatform(
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/usernameTV"),
            AppiumBy.accessibilityId("User Name")
    );
    private final By userNameInput = PlatformBy.isPlatform(
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET"),
            AppiumBy.className("XCUIElementTypeTextField")
    );
    private final By alertEmptyUserName = PlatformBy.isPlatform(
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameErrorTV"),
            AppiumBy.accessibilityId("Username is required")
    );
    private final By namePassWordField = PlatformBy.isPlatform(
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordTV"),
            AppiumBy.accessibilityId("Password")
    );
    private final By passWordInput = PlatformBy.isPlatform(
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET"),
            AppiumBy.className("XCUIElementTypeSecureTextField")
    );
    private final By alertEmptyPassWord = PlatformBy.isPlatform(
            AppiumBy.xpath("//android.widget.TextView[@text='Enter Password']"),
            AppiumBy.accessibilityId("Password is required")
    );
    private final By alertBlockAccount = PlatformBy.isPlatform(
            AppiumBy.xpath("//android.widget.TextView[contains(@text, 'has been locked')]"),
            AppiumBy.accessibilityId("alice@example.com")
    );
    private final By loginButton = PlatformBy.isPlatform(
            AppiumBy.accessibilityId("Tap to login with given credentials"),
            AppiumBy.xpath("//XCUIElementTypeButton[@name='Login']")
    );
    private final By nameUserNameList = PlatformBy.isPlatform(
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/savedNamesTV"),
            AppiumBy.accessibilityId("Usernames")
    );
    private final By validLoginAccount = PlatformBy.isPlatform(
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/username1TV"),
            AppiumBy.accessibilityId("bob@example.com")
    );
    private final By blockLoginAccount = PlatformBy.isPlatform(
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/username2TV"),
            AppiumBy.accessibilityId("alice@example.com")
    );
    private final By visualLoginAccount = PlatformBy.isPlatform(
            AppiumBy.accessibilityId("Visual User Login"),
            AppiumBy.accessibilityId("visual@example.com")
    );
    private final By logOutMenu = PlatformBy.isPlatform(
            AppiumBy.accessibilityId("Logout Menu Item"),
            AppiumBy.accessibilityId("LogOut-menu-item")
    );

    public boolean isMainMenuDisplay (){
        return MobileUI.isDisplay(mainMenu);
    }

    public boolean isLoginMenuDisplay (){
        return MobileUI.isDisplay(loginMenu);
    }

    public String getTextOfLoginMenu (){
        return MobileUI.getText(loginMenu);
    }

    public boolean isTitleLoginPageDisplay (){
        return MobileUI.isDisplay(titleLoginPage);
    }

    public String getTextOfTitleLoginPage (){
        return MobileUI.getText(titleLoginPage);
    }

    public boolean isDescriptionLoginPageDisplay (){
        return MobileUI.isDisplay(descriptionLoginPage);
    }

    public String getTextOfDescriptionLoginPage (){
        return MobileUI.getText(descriptionLoginPage);
    }

    public boolean isNameUserNameFieldDisplay (){
        return MobileUI.isDisplay(nameUserNameField);
    }

    public String getTextOfNameUserNameField (){
        return MobileUI.getText(nameUserNameField);
    }

    public boolean isAlertEmptyUserNameDisplay (){
        return MobileUI.isDisplay(alertEmptyUserName);
    }

    public String getTextOfAlertEmptyUserName (){
        return MobileUI.getText(alertEmptyUserName);
    }

    public boolean isNamePassWordFieldDisplay (){
        return MobileUI.isDisplay(namePassWordField);
    }

    public String getTextOfNamePassWordField (){
        return MobileUI.getText(namePassWordField);
    }

    public boolean isAlertEmptyPassWordDisplay (){
        return MobileUI.isDisplay(alertEmptyPassWord);
    }

    public String getTextOfAlertEmptyPassWord () {
        return MobileUI.getText(alertEmptyPassWord);
    }

    public boolean isAlertBlockAccountDisplay (){
        return MobileUI.isDisplay(alertBlockAccount);
    }

    public String getTextOfAlertBlockAccount () {
        return MobileUI.getText(alertBlockAccount);
    }

    public boolean isLoginButtonDisplay (){
        return MobileUI.isDisplay(loginButton);
    }

    public String getTextOfLoginButton () {
        return MobileUI.getText(loginButton);
    }

    public boolean isNameUserNameListDisplay (){
        return MobileUI.isDisplay(nameUserNameList);
    }

    public String getTextOfNameUserNameList () {
        return MobileUI.getText(nameUserNameList);
    }

    public boolean isValidLoginAccountDisplay (){
        return MobileUI.isDisplay(validLoginAccount);
    }

    public String getTextOfValidLoginAccount () {
        return MobileUI.getText(validLoginAccount);
    }

    public boolean isBlockLoginAccountDisplay (){
        return MobileUI.isDisplay(blockLoginAccount);
    }

    public String getTextOfBlockLoginAccount () {
        return MobileUI.getText(blockLoginAccount);
    }

    public boolean isVisualLoginAccountDisplay (){
        return MobileUI.isDisplay(visualLoginAccount);
    }

    public String getTextOfVisualLoginAccount () {
        return MobileUI.getText(visualLoginAccount);
    }

    public boolean isLogOutMenuDisplay (){
        return MobileUI.isDisplay(logOutMenu);
    }

    public String getTextOfLogOutMenu (){
        return MobileUI.getText(logOutMenu);
    }

    public void clickIntoMainMenu (){
        MobileUI.click(mainMenu);
    }

    public void clickIntoLoginMenu (){
        MobileUI.click(loginMenu);
    }

    public void loginWithUserNameAndPassWord (String userName, String passWord){
        MobileUI.click(userNameInput);
        MobileUI.clear(userNameInput);
        MobileUI.sendKey(userNameInput, userName);

        MobileUI.click(passWordInput);
        MobileUI.clear(passWordInput);
        MobileUI.sendKey(passWordInput, passWord);

        MobileUI.click(loginButton);
        MobileUI.hideKeyboard();
    }

    public void loginBlockedAccount (){
        MobileUI.click(blockLoginAccount);
        MobileUI.click(loginButton);
        MobileUI.hideKeyboard();
    }


}
