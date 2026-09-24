package khanhpham.keywords;

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

    private static WebDriverWait getWait(int seconds) {
        return new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(seconds));
    }

    //------------------------------------------------------------------------------------------------------------------

    public static WebElement waitForElementVisibility(WebElement locator, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.visibilityOf(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element " + locator + " is not displayed within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static WebElement waitForElementVisibility(WebElement locator) {
        return waitForElementVisibility(locator, DEFAULT_EXPLICIT_WAIT);
    }

    public static WebElement waitForElementVisibility(By locator, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Locator " + locator + " is not displayed within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static WebElement waitForElementVisibility(By locator) {
        return waitForElementVisibility(locator, DEFAULT_EXPLICIT_WAIT);
    }

    //------------------------------------------------------------------------------------------------------------------

    public static List<WebElement> waitForElementVisibilityList(WebElement locator, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.visibilityOfAllElements(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("List of elements " + locator + " is not fully displayed within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static List<WebElement> waitForElementVisibilityList(WebElement locator) {
        return waitForElementVisibilityList(locator, DEFAULT_EXPLICIT_WAIT);
    }

    public static List<WebElement> waitForElementVisibilityList(By locator, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("List with locator " + locator + " is not fully displayed within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static List<WebElement> waitForElementVisibilityList(By locator) {
        return waitForElementVisibilityList(locator, DEFAULT_EXPLICIT_WAIT);
    }

    //------------------------------------------------------------------------------------------------------------------

    public static boolean waitForElementInVisibility(WebElement locator, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.invisibilityOf(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element " + locator + " is still displayed after " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static boolean waitForElementInVisibility(WebElement locator) {
        return waitForElementInVisibility(locator, DEFAULT_EXPLICIT_WAIT);
    }

    public static boolean waitForElementInVisibility(By locator, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Locator " + locator + " is still displayed after " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static boolean waitForElementInVisibility(By locator) {
        return waitForElementInVisibility(locator, DEFAULT_EXPLICIT_WAIT);
    }

    public static boolean waitForElementInvisibilityList(WebElement locator, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.invisibilityOfAllElements(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("List of elements " + locator + " is still displayed after " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static boolean waitForElementInvisibilityList(WebElement locator) {
        return waitForElementInvisibilityList(locator, DEFAULT_EXPLICIT_WAIT);
    }

    //------------------------------------------------------------------------------------------------------------------

    public static boolean waitForAttributeContain(WebElement locator, String attributeName, String value, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.attributeContains(locator, attributeName, value));
        } catch (TimeoutException e) {
            throw new RuntimeException("Attribute '" + attributeName + "' of element " + locator + " does not contain '" + value + "' within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static boolean waitForAttributeContain(WebElement locator, String attributeName, String value) {
        return waitForAttributeContain(locator, attributeName, value, DEFAULT_EXPLICIT_WAIT);
    }

    public static boolean waitForAttributeContain(By locator, String attributeName, String value, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.attributeContains(locator, attributeName, value));
        } catch (TimeoutException e) {
            throw new RuntimeException("Attribute '" + attributeName + "' of locator " + locator + " does not contain '" + value + "' within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static boolean waitForAttributeContain(By locator, String attributeName, String value) {
        return waitForAttributeContain(locator, attributeName, value, DEFAULT_EXPLICIT_WAIT);
    }

    public static boolean waitForAttributeTobe(WebElement locator, String attributeName, String value, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.attributeToBe(locator, attributeName, value));
        } catch (TimeoutException e) {
            throw new RuntimeException("Attribute '" + attributeName + "' of element " + locator + " does not equal '" + value + "' within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static boolean waitForAttributeTobe(WebElement locator, String attributeName, String value) {
        return waitForAttributeTobe(locator, attributeName, value, DEFAULT_EXPLICIT_WAIT);
    }

    public static boolean waitForAttributeTobe(By locator, String attributeName, String value, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.attributeToBe(locator, attributeName, value));
        } catch (TimeoutException e) {
            throw new RuntimeException("Attribute '" + attributeName + "' of locator " + locator + " does not equal '" + value + "' within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static boolean waitForAttributeTobe(By locator, String attributeName, String value) {
        return waitForAttributeTobe(locator, attributeName, value, DEFAULT_EXPLICIT_WAIT);
    }

    public static boolean waitForAttributeNotEmpty(WebElement locator, String attributeName, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.attributeToBeNotEmpty(locator, attributeName));
        } catch (TimeoutException e) {
            throw new RuntimeException("Attribute '" + attributeName + "' of element " + locator + " is still empty within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static boolean waitForAttributeNotEmpty(WebElement locator, String attributeName) {
        return waitForAttributeNotEmpty(locator, attributeName, DEFAULT_EXPLICIT_WAIT);
    }

    //------------------------------------------------------------------------------------------------------------------

    public static WebElement waitForElementTobeClick(WebElement locator, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element " + locator + " is not clickable within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static WebElement waitForElementTobeClick(WebElement locator) {
        return waitForElementTobeClick(locator, DEFAULT_EXPLICIT_WAIT);
    }

    public static WebElement waitForElementTobeClick(By locator, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Locator " + locator + " is not clickable within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static WebElement waitForElementTobeClick(By locator) {
        return waitForElementTobeClick(locator, DEFAULT_EXPLICIT_WAIT);
    }

    public static boolean waitForElementSelected(WebElement locator, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.elementToBeSelected(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element " + locator + " is not selected within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static boolean waitForElementSelected(WebElement locator) {
        return waitForElementSelected(locator, DEFAULT_EXPLICIT_WAIT);
    }

    public static boolean waitForElementSelected(By locator, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.elementToBeSelected(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Locator " + locator + " is not selected within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static boolean waitForElementSelected(By locator) {
        return waitForElementSelected(locator, DEFAULT_EXPLICIT_WAIT);
    }

    //------------------------------------------------------------------------------------------------------------------

    public static boolean waitForElementInVisibilityWithText(By locator, String value, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.invisibilityOfElementWithText(locator, value));
        } catch (TimeoutException e) {
            throw new RuntimeException("Text '" + value + "' in locator " + locator + " is still displaying within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static boolean waitForElementInVisibilityWithText(By locator, String value) {
        return waitForElementInVisibilityWithText(locator, value, DEFAULT_EXPLICIT_WAIT);
    }

    public static List<WebElement> waitForNumberElementVisibility(By locator, int quantity, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.numberOfElementsToBe(locator, quantity));
        } catch (TimeoutException e) {
            throw new RuntimeException("Quantity of locator " + locator + " is not equal to " + quantity + " within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static List<WebElement> waitForNumberElementVisibility(By locator, int quantity) {
        return waitForNumberElementVisibility(locator, quantity, DEFAULT_EXPLICIT_WAIT);
    }

    public static List<WebElement> waitForNumberElementVisibilityLessThan(By locator, int quantity, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.numberOfElementsToBeLessThan(locator, quantity));
        } catch (TimeoutException e) {
            throw new RuntimeException("Quantity of locator " + locator + " is not less than " + quantity + " within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static List<WebElement> waitForNumberElementVisibilityLessThan(By locator, int quantity) {
        return waitForNumberElementVisibilityLessThan(locator, quantity, DEFAULT_EXPLICIT_WAIT);
    }

    public static List<WebElement> waitForNumberElementVisibilityMoreThan(By locator, int quantity, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, quantity));
        } catch (TimeoutException e) {
            throw new RuntimeException("Quantity of locator " + locator + " is not more than " + quantity + " within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static List<WebElement> waitForNumberElementVisibilityMoreThan(By locator, int quantity) {
        return waitForNumberElementVisibilityMoreThan(locator, quantity, DEFAULT_EXPLICIT_WAIT);
    }

    //------------------------------------------------------------------------------------------------------------------

    public static WebElement waitForElementVisibilityDom(By locator, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Locator " + locator + " is not present in DOM within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static WebElement waitForElementVisibilityDom(By locator) {
        return waitForElementVisibilityDom(locator, DEFAULT_EXPLICIT_WAIT);
    }

    public static List<WebElement> waitForElementVisibilityDomList(By locator, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("List locator " + locator + " is not present in DOM within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static List<WebElement> waitForElementVisibilityDomList(By locator) {
        return waitForElementVisibilityDomList(locator, DEFAULT_EXPLICIT_WAIT);
    }

    //------------------------------------------------------------------------------------------------------------------

    public static boolean waitForTextOfElementVisibility(WebElement locator, String text, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.textToBePresentInElement(locator, text));
        } catch (TimeoutException e) {
            throw new RuntimeException("Text '" + text + "' in element " + locator + " did not appear within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static boolean waitForTextOfElementVisibility(WebElement locator, String text) {
        return waitForTextOfElementVisibility(locator, text, DEFAULT_EXPLICIT_WAIT);
    }

    public static boolean waitForTextOfElementVisibility(By locator, String text, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (TimeoutException e) {
            throw new RuntimeException("Text '" + text + "' in locator " + locator + " did not appear within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static boolean waitForTextOfElementVisibility(By locator, String text) {
        return waitForTextOfElementVisibility(locator, text, DEFAULT_EXPLICIT_WAIT);
    }

    public static boolean waitForTextOfElementVisibilityWithValue(WebElement locator, String value, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.textToBePresentInElementValue(locator, value));
        } catch (TimeoutException e) {
            throw new RuntimeException("Value '" + value + "' in element " + locator + " does not match within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static boolean waitForTextOfElementVisibilityWithValue(WebElement locator, String value) {
        return waitForTextOfElementVisibilityWithValue(locator, value, DEFAULT_EXPLICIT_WAIT);
    }

    public static boolean waitForTextOfElementVisibilityWithValue(By locator, String value, int seconds) {
        try {
            return getWait(seconds).until(ExpectedConditions.textToBePresentInElementValue(locator, value));
        } catch (TimeoutException e) {
            throw new RuntimeException("Value '" + value + "' in locator " + locator + " does not match within " + seconds + "s" + " - " + e.getMessage());
        }
    }

    public static boolean waitForTextOfElementVisibilityWithValue(By locator, String value) {
        return waitForTextOfElementVisibilityWithValue(locator, value, DEFAULT_EXPLICIT_WAIT);
    }
}