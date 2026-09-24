package khanhpham.testcases;

import khanhpham.common.BaseClass;
import khanhpham.dataprovider.DataProviderFactory;
import khanhpham.pages.WebViewPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebViewTest extends BaseClass {
    WebViewPage webViewPage;

    @BeforeMethod (alwaysRun = true)
    public void initPage (){
        webViewPage = new WebViewPage();
    }

    @Test(priority = 1)
    public void verifyMainMenuDisplay (){
        Assert.assertTrue(webViewPage.isMainMenuDisplay());
    }

    @Test(priority = 2)
    public void verifyWebViewPageDisplay (){
        webViewPage.clickIntoMainMenu();
        Assert.assertTrue(webViewPage.isWebViewMenuDisplay());
        Assert.assertEquals(webViewPage.getTextOfWebViewMenu(),"WebView");
        webViewPage.clickIntoWebViewMenu();
    }

    @Test(priority = 3)
    public void verifyTitleWebViewPageDisplay (){
        Assert.assertTrue(webViewPage.isTitleWebViewPageDisplay());
        Assert.assertEquals(webViewPage.getTextOfTitleWebViewPage(),"Webview");
    }

    @Test(priority = 4)
    public void verifyUrlFieldDisplay (){
        Assert.assertTrue(webViewPage.isUrlFieldDisplay());
        Assert.assertEquals(webViewPage.getTextOfUrlField(),"URL");
    }

    @Test(priority = 5)
    public void verifyPlaceHolderInputFieldDisplay (){
        Assert.assertTrue(webViewPage.isUrlInputDisplay());
        Assert.assertEquals(webViewPage.getTextOfUrlInput(),"https://www.website.com");
    }

    @Test(priority = 6)
    public void verifyGuideLineFieldDisplay (){
        Assert.assertTrue(webViewPage.isGuideLineFieldDisplay());
        Assert.assertEquals(webViewPage.getTextOfGuideLineField(),"Enter an HTTPS url");
    }

    @Test(priority = 7)
    public void verifyRedirectButtonDisplay (){
        Assert.assertTrue(webViewPage.isRedirectButtonDisplay());
        Assert.assertEquals(webViewPage.getTextOfRedirectButton(),"Go To Site");
    }

    @Test(priority = 8)
    public void inputEmptyUrl (){
        webViewPage.inputUrl("");
        Assert.assertTrue(webViewPage.isAlertInvalidUrlDisplay());
        Assert.assertEquals(webViewPage.getTextOfAlertInvalidUrl(),"Please provide a correct https url.");
    }

    @Test(dataProvider = "invalid_url", dataProviderClass = DataProviderFactory.class, priority = 9)
    public void inputInvalidUrl (String url){
        webViewPage.inputUrl(url);
        Assert.assertTrue(webViewPage.isAlertInvalidUrlDisplay());
        Assert.assertEquals(webViewPage.getTextOfAlertInvalidUrl(),"Please provide a correct https url.");
    }
}
