package khanhpham.keywords;

import io.appium.java_client.AppiumDriver;
import khanhpham.constants.DataConfig;
import khanhpham.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {
    private static final int DEFAULT_EXPLICIT_WAIT = Integer.parseInt(DataConfig.TIMEOUT_EXPLICIT_DEFAULT);
    private static AppiumDriver driver = DriverManager.getDriver();

    public static WebElement waitForElementVisibility (WebElement locator, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.visibilityOf(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Locator: " + locator + " of element don't display on the screen within: " + seconds + "s");
        }
    }

    public static WebElement waitForElementVisibility (WebElement locator){
        return waitForElementVisibility(locator, DEFAULT_EXPLICIT_WAIT);
    }

    public static WebElement waitForElementVisibility (By locator, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Locator: " + locator + " of element don't display on the screen within: " + seconds + "s");
        }
    }

    public static WebElement waitForElementVisibility (By locator){
        return waitForElementVisibility(locator, DEFAULT_EXPLICIT_WAIT);
    }

    //-----------------------------------------------------------------------------------------------------------------

    public static List<WebElement> waitForElementVisibilityList (WebElement locator, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.visibilityOfAllElements(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Locator: " + locator + " of element don't display on the screen within: " + seconds + "s");
        }
    }

    public static List<WebElement> waitForElementVisibilityList (WebElement locator){
        return waitForElementVisibilityList(locator, DEFAULT_EXPLICIT_WAIT);
    }

    public static List<WebElement> waitForElementVisibilityList (By locator, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Locator: " + locator + " of element don't display on the screen within: " + seconds + "s");
        }
    }

    public static List<WebElement> waitForElementVisibilityList (By locator){
        return waitForElementVisibilityList(locator, DEFAULT_EXPLICIT_WAIT);
    }

    //-----------------------------------------------------------------------------------------------------------------

    public static boolean waitForElementInVisibility (WebElement locator, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.invisibilityOf(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Locator: " + locator + " of element displaying on the screen within: " + seconds + "s");
        }
    }

    public static boolean waitForElementInVisibility (WebElement locator){
        return waitForElementInVisibility(locator, DEFAULT_EXPLICIT_WAIT);
    }

    public static boolean waitForElementInVisibility (By locator, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Locator: " + locator + " of element displaying on the screen within: " + seconds + "s");
        }
    }

    public static boolean waitForElementInVisibility (By locator){
        return waitForElementInVisibility(locator, DEFAULT_EXPLICIT_WAIT);
    }

    //-----------------------------------------------------------------------------------------------------------------

    public static boolean waitForElementInvisibilityList (WebElement locator, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.invisibilityOfAllElements(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Locator: " + locator + " of element displaying on the screen within: " + seconds + "s");
        }
    }

    public static boolean waitForElementInvisibilityList (WebElement locator){
        return waitForElementInvisibilityList(locator, DEFAULT_EXPLICIT_WAIT);
    }

    //-----------------------------------------------------------------------------------------------------------------


    public static boolean waitForAttributeContain (WebElement locator, String attributeName, String value, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.attributeContains(locator, attributeName, value));
        } catch (TimeoutException e) {
            throw new RuntimeException("Value: " + value + " of attribute : " + attributeName + " in element: " + locator + " can't display within: " + seconds + "s");
        }
    }

    public static boolean waitForAttributeContain (WebElement locator, String attributeName, String value){
        return waitForAttributeContain(locator, attributeName, value, DEFAULT_EXPLICIT_WAIT);
    }

    public static boolean waitForAttributeContain (By locator, String attributeName, String value, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.attributeContains(locator, attributeName, value));
        } catch (TimeoutException e) {
            throw new RuntimeException("Value: " + value + " of attribute : " + attributeName + " in element: " + locator + " can't display within: " + seconds + "s");
        }
    }

    public static boolean waitForAttributeContain (By locator, String attributeName, String value){
        return waitForAttributeContain(locator, attributeName, value, DEFAULT_EXPLICIT_WAIT);
    }

    //-----------------------------------------------------------------------------------------------------------------

    public static boolean waitForAttributeTobe (WebElement locator, String attributeName, String value, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.attributeToBe(locator, attributeName, value));
        } catch (TimeoutException e) {
            throw new RuntimeException("Value: " + value + " of attribute : " + attributeName + " in element: " + locator + " can't display within: " + seconds + "s");
        }
    }

    public static boolean waitForAttributeTobe (WebElement locator, String attributeName, String value){
        return waitForAttributeTobe(locator, attributeName, value, DEFAULT_EXPLICIT_WAIT);
    }

    public static boolean waitForAttributeTobe (By locator, String attributeName, String value, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.attributeToBe(locator, attributeName, value));
        } catch (TimeoutException e) {
            throw new RuntimeException("Value: " + value + " of attribute : " + attributeName + " in element: " + locator + " can't display within: " + seconds + "s");
        }
    }

    public static boolean waitForAttributeTobe (By locator, String attributeName, String value){
        return waitForAttributeTobe(locator, attributeName, value, DEFAULT_EXPLICIT_WAIT);
    }

    //-----------------------------------------------------------------------------------------------------------------

    public static boolean waitForAttributeNotEmpty (WebElement locator, String attributeName, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.attributeToBeNotEmpty(locator, attributeName));
        } catch (TimeoutException e) {
            throw new RuntimeException("Attribute : " + attributeName + " in element: " + locator + " is empty within: " + seconds + "s");
        }
    }

    public static boolean waitForAttributeNotEmpty (WebElement locator, String attributeName){
        return waitForAttributeNotEmpty(locator, attributeName, DEFAULT_EXPLICIT_WAIT);
    }

    //-----------------------------------------------------------------------------------------------------------------

    public static WebElement waitForElementTobeClick (WebElement locator, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Locator: " + locator + " of element don't click within: " + seconds + "s");
        }
    }

    public static WebElement waitForElementTobeClick (WebElement locator, String attributeName){
        return waitForElementTobeClick(locator, DEFAULT_EXPLICIT_WAIT);
    }

    public static WebElement waitForElementTobeClick (By locator, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Locator: " + locator + " of element don't click within: " + seconds + "s");
        }
    }

    public static WebElement waitForElementTobeClick (By locator, String attributeName){
        return waitForElementTobeClick(locator, DEFAULT_EXPLICIT_WAIT);
    }

    //-----------------------------------------------------------------------------------------------------------------

    public static boolean waitForElementSelected (WebElement locator, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.elementToBeSelected(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Locator: " + locator + " of element don't selected within: " + seconds + "s");
        }
    }

    public static boolean waitForElementSelected (WebElement locator, String attributeName){
        return waitForElementSelected(locator, DEFAULT_EXPLICIT_WAIT);
    }

    public static boolean waitForElementSelected (By locator, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.elementToBeSelected(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Locator: " + locator + " of element don't selected within: " + seconds + "s");
        }
    }

    public static boolean waitForElementSelected (By locator, String attributeName){
        return waitForElementSelected(locator, DEFAULT_EXPLICIT_WAIT);
    }

    //-----------------------------------------------------------------------------------------------------------------

    public static boolean waitForElementInVisibilityWithText (By locator, String value, int seconds){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            return wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, value));
        } catch (TimeoutException e) {
            throw new RuntimeException("Text : " + value + " in element: " + locator + " is displaying within: " + seconds + "s");
        }
    }

    public static boolean waitForElementInVisibilityWithText (By locator, String value){
        return waitForElementInVisibilityWithText(locator, value, DEFAULT_EXPLICIT_WAIT);
    }


}
