package khanhpham.pages;

import com.beust.ah.A;
import io.appium.java_client.AppiumBy;
import khanhpham.helpers.PlatformBy;
import khanhpham.keywords.MobileUI;
import org.openqa.selenium.By;

public class WebViewPage {

    private final By mainMenu = PlatformBy.isPlatform(
            AppiumBy.accessibilityId("View menu"),
            AppiumBy.accessibilityId("Menu Icons")
    );
    private final By webViewMenu = PlatformBy.isPlatform(
            AppiumBy.xpath("//android.widget.TextView[@text='WebView']"),
            AppiumBy.accessibilityId("Webview")
    );
    private final By titleWebViewPage = PlatformBy.isPlatform(
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/webViewTV"),
            AppiumBy.accessibilityId("Webview")
    );
    private final By urlField = PlatformBy.isPlatform(
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/urlTV"),
            AppiumBy.accessibilityId("URL")
    );
    private final By urlInput = PlatformBy.isPlatform(
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/urlET"),
            AppiumBy.className("XCUIElementTypeTextField")
    );
    private final By alertInvalidUrl = AppiumBy.id("com.saucelabs.mydemoapp.android:id/urlErrorTV");

    private final By guidField = PlatformBy.isPlatform(
            AppiumBy.id("com.saucelabs.mydemoapp.android:id/enterTV"),
            AppiumBy.accessibilityId("Enter an https URL")
    );

    private final By redirectButton = PlatformBy.isPlatform(
            AppiumBy.accessibilityId("Tap to view content of given url"),
            AppiumBy.accessibilityId("Go To Site")
    );

    public boolean isMainMenuDisplay (){
        return MobileUI.isDisplay(mainMenu);
    }

    public boolean isWebViewMenuDisplay (){
        return MobileUI.isDisplay(webViewMenu);
    }

    public String getTextOfWebViewMenu (){
        return MobileUI.getText(webViewMenu);
    }

    public boolean isTitleWebViewPageDisplay (){
        return MobileUI.isDisplay(titleWebViewPage);
    }

    public String getTextOfTitleWebViewPage (){
        return MobileUI.getText(titleWebViewPage);
    }

    public boolean isUrlFieldDisplay (){
        return MobileUI.isDisplay(urlField);
    }

    public String getTextOfUrlField (){
        return MobileUI.getText(urlField);
    }

    public boolean isUrlInputDisplay (){
        return MobileUI.isDisplay(urlInput);
    }

    public String getTextOfUrlInput (){
        return MobileUI.getText(urlInput);
    }

    public boolean isAlertInvalidUrlDisplay (){
        return MobileUI.isDisplay(alertInvalidUrl);
    }

    public String getTextOfAlertInvalidUrl (){
        return MobileUI.getText(alertInvalidUrl);
    }

    public boolean isGuideLineFieldDisplay (){
        return MobileUI.isDisplay(guidField);
    }

    public String getTextOfGuideLineField (){
        return MobileUI.getText(guidField);
    }

    public boolean isRedirectButtonDisplay (){
        return MobileUI.isDisplay(redirectButton);
    }

    public String getTextOfRedirectButton (){
        return MobileUI.getText(redirectButton);
    }

    public void clickIntoMainMenu (){
        MobileUI.click(mainMenu);
    }

    public void clickIntoWebViewMenu (){
        MobileUI.click(webViewMenu);
    }

    public void inputUrl (String url){
        MobileUI.click(urlInput);
        MobileUI.clear(urlInput);
        MobileUI.sendKey(urlInput, url);
        MobileUI.click(redirectButton);
        MobileUI.hideKeyboard();
    }
}
