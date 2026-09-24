package khanhpham.keywords;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.clipboard.HasClipboard;
import khanhpham.constants.DataConfig;
import khanhpham.driver.DriverManager;
import khanhpham.helpers.LogHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class MobileUI {
    private static final int TIMEOUT_EXPLICIT_DEFAULT = Integer.parseInt(DataConfig.TIMEOUT_EXPLICIT_DEFAULT);

    // 1. CLICK ACTIONS
    public static void click(WebElement element, int seconds) {
        LogHelper.info("Ready to click into element: " + element);
        WebElement result = WaitUtils.waitForElementTobeClick(element, seconds);
        result.click();
        LogHelper.info("Click success for element: " + element + " within: " + seconds + "s");
    }

    public static void click(WebElement element) {
        click(element, TIMEOUT_EXPLICIT_DEFAULT);
    }

    public static void click(By element, int seconds) {
        LogHelper.info("Ready to click into element: " + element);
        WebElement result = WaitUtils.waitForElementTobeClick(element, seconds);
        result.click();
        LogHelper.info("Click success for element: " + element + " within: " + seconds + "s");
    }

    public static void click(By element) {
        click(element, TIMEOUT_EXPLICIT_DEFAULT);
    }

    // 2. CLEAR ACTIONS
    public static void clear(WebElement element, int seconds) {
        LogHelper.info("Ready to clear all values in field for element: " + element);
        WebElement result = WaitUtils.waitForElementVisibility(element, seconds);
        result.click();
        result.clear();
        LogHelper.info("Clear success for all values in the field of element: " + element + " within: " + seconds + "s");
    }

    public static void clear(WebElement element) {
        clear(element, TIMEOUT_EXPLICIT_DEFAULT);
    }

    public static void clear(By element, int seconds) {
        LogHelper.info("Ready to clear all values in field for element: " + element);
        WebElement result = WaitUtils.waitForElementVisibility(element, seconds);
        result.click();
        result.clear();
        LogHelper.info("Clear success for all values in the field of element: " + element + " within: " + seconds + "s");
    }

    public static void clear(By element) {
        clear(element, TIMEOUT_EXPLICIT_DEFAULT);
    }

    // 3. SENDKEY ACTIONS
    public static void sendKey(WebElement element, String key, int seconds) {
        LogHelper.info("Ready to send key: '" + key + "' into the field of element: " + element);
        WebElement result = WaitUtils.waitForElementVisibility(element, seconds);
        result.click();
        result.clear();
        result.sendKeys(key);
        LogHelper.info("Send key: '" + key + "' success into the field of element: " + element + " within: " + seconds + "s");
    }

    public static void sendKey(WebElement element, String key) {
        sendKey(element, key, TIMEOUT_EXPLICIT_DEFAULT);
    }

    public static void sendKey(By element, String key, int seconds) {
        LogHelper.info("Ready to send key: '" + key + "' into the field of element: " + element);
        WebElement result = WaitUtils.waitForElementVisibility(element, seconds);
        result.click();
        result.clear();
        result.sendKeys(key);
        LogHelper.info("Send key: '" + key + "' success into the field of element: " + element + " within: " + seconds + "s");
    }

    public static void sendKey(By element, String key) {
        sendKey(element, key, TIMEOUT_EXPLICIT_DEFAULT);
    }

    // 4. GET TEXT ACTIONS
    public static String getText(WebElement element, int seconds) {
        LogHelper.info("Ready to get text from element: " + element);
        WebElement result = WaitUtils.waitForElementVisibility(element, seconds);
        String value = result.getText();

        if ((value == null || value.trim().isEmpty()) && DriverManager.isIos()) {
            value = result.getAttribute("label");
        }

        LogHelper.info("Get text: '" + value + "' success from element: " + element + " within: " + seconds + "s");
        return value;
    }

    public static String getText(WebElement element) {
        return getText(element, TIMEOUT_EXPLICIT_DEFAULT);
    }

    public static String getText(By element, int seconds) {
        LogHelper.info("Ready to get text from element: " + element);
        WebElement result = WaitUtils.waitForElementVisibility(element, seconds);
        String value = result.getText();

        if ((value == null || value.trim().isEmpty()) && DriverManager.isIos()) {
            value = result.getAttribute("label");
        }

        LogHelper.info("Get text: '" + value + "' success from element: " + element + " within: " + seconds + "s");
        return value;
    }

    public static String getText(By element) {
        return getText(element, TIMEOUT_EXPLICIT_DEFAULT);
    }

    // 5. GET ATTRIBUTE ACTIONS
    public static String getAttribute(WebElement element, String attributeName, int seconds) {
        LogHelper.info("Ready to get value for attribute: " + attributeName + " in element: " + element);
        WebElement result = WaitUtils.waitForElementVisibility(element, seconds);
        String value = result.getAttribute(attributeName);
        LogHelper.info("Get value: '" + value + "' success for attribute: " + attributeName + " in element: " + element + " within: " + seconds + "s");
        return value;
    }

    public static String getAttribute(WebElement element, String attributeName) {
        return getAttribute(element, attributeName, TIMEOUT_EXPLICIT_DEFAULT);
    }

    public static String getAttribute(By element, String attributeName, int seconds) {
        LogHelper.info("Ready to get value for attribute: " + attributeName + " in element: " + element);
        WebElement result = WaitUtils.waitForElementVisibility(element, seconds);
        String value = result.getAttribute(attributeName);
        LogHelper.info("Get value: '" + value + "' success for attribute: " + attributeName + " in element: " + element + " within: " + seconds + "s");
        return value;
    }

    public static String getAttribute(By element, String attributeName) {
        return getAttribute(element, attributeName, TIMEOUT_EXPLICIT_DEFAULT);
    }

    // 6. IS DISPLAY ACTIONS
    public static boolean isDisplay(WebElement element, int seconds) {
        try {
            LogHelper.info("Ready to check element: " + element + " is display ?");
            WebElement result = WaitUtils.waitForElementVisibility(element, seconds);
            boolean value = result.isDisplayed();
            LogHelper.info("Element: " + element + " is displayed on the screen within: " + seconds + "s");
            return value;
        } catch (Exception e) {
            LogHelper.error("Element: " + element + " can't display on the screen within: " + seconds + "s");
            return false;
        }
    }

    public static boolean isDisplay(WebElement element) {
        return isDisplay(element, TIMEOUT_EXPLICIT_DEFAULT);
    }

    public static boolean isDisplay(By element, int seconds) {
        try {
            LogHelper.info("Ready to check element: " + element + " is display ?");
            WebElement result = WaitUtils.waitForElementVisibility(element, seconds);
            boolean value = result.isDisplayed();
            LogHelper.info("Element: " + element + " is displayed on the screen within: " + seconds + "s");
            return value;
        } catch (Exception e) {
            LogHelper.error("Element: " + element + " can't display on the screen within: " + seconds + "s");
            return false;
        }
    }

    public static boolean isDisplay(By element) {
        return isDisplay(element, TIMEOUT_EXPLICIT_DEFAULT);
    }

    // 7. IS SELECTED ACTIONS
    public static boolean isSelected(WebElement element, int seconds) {
        try {
            LogHelper.info("Ready to check element: " + element + " is selected ?");
            WebElement result = WaitUtils.waitForElementVisibility(element, seconds);
            boolean value = result.isSelected();
            LogHelper.info("Element: " + element + " is selected on the screen within: " + seconds + "s");
            return value;
        } catch (Exception e) {
            LogHelper.error("Element: " + element + " is not selected on the screen within: " + seconds + "s");
            return false;
        }
    }

    public static boolean isSelected(WebElement element) {
        return isSelected(element, TIMEOUT_EXPLICIT_DEFAULT);
    }

    public static boolean isSelected(By element, int seconds) {
        try {
            LogHelper.info("Ready to check element: " + element + " is selected ?");
            WebElement result = WaitUtils.waitForElementVisibility(element, seconds);
            boolean value = result.isSelected();
            LogHelper.info("Element: " + element + " is selected on the screen within: " + seconds + "s");
            return value;
        } catch (Exception e) {
            LogHelper.error("Element: " + element + " is not selected on the screen within: " + seconds + "s");
            return false;
        }
    }

    public static boolean isSelected(By element) {
        return isSelected(element, TIMEOUT_EXPLICIT_DEFAULT);
    }

    // 8. IS ENABLE ACTIONS
    public static boolean isEnable(WebElement element, int seconds) {
        try {
            LogHelper.info("Ready to check element: " + element + " is enable ?");
            WebElement result = WaitUtils.waitForElementVisibility(element, seconds);
            boolean value = result.isEnabled();
            LogHelper.info("Element: " + element + " is enabled on the screen within: " + seconds + "s");
            return value;
        } catch (Exception e) {
            LogHelper.error("Element: " + element + " is not enabled on the screen within: " + seconds + "s");
            return false;
        }
    }

    public static boolean isEnable(WebElement element) {
        return isEnable(element, TIMEOUT_EXPLICIT_DEFAULT);
    }

    public static boolean isEnable(By element, int seconds) {
        try {
            LogHelper.info("Ready to check element: " + element + " is enable ?");
            WebElement result = WaitUtils.waitForElementVisibility(element, seconds);
            boolean value = result.isEnabled();
            LogHelper.info("Element: " + element + " is enabled on the screen within: " + seconds + "s");
            return value;
        } catch (Exception e) {
            LogHelper.error("Element: " + element + " is not enabled on the screen within: " + seconds + "s");
            return false;
        }
    }

    public static boolean isEnable(By element) {
        return isEnable(element, TIMEOUT_EXPLICIT_DEFAULT);
    }

    // 9. KEYBOARD & NOTIFICATIONS
    public static void hideKeyboard() {
        try {
            LogHelper.info("Ready to hide the keyboard on the screen");
            AppiumDriver driver = DriverManager.getDriver();
            if (driver instanceof AndroidDriver) {
                AndroidDriver androidDriver = (AndroidDriver) driver;
                if (androidDriver.isKeyboardShown()) {
                    androidDriver.hideKeyboard();
                    LogHelper.info("Hide keyboard success on the screen");
                }else if (DriverManager.isIos()) {
                    try {
                        driver.executeScript("mobile: hideKeyboard");
                        LogHelper.info("Hide keyboard success on iOS");
                    } catch (Exception ignored) {
                        LogHelper.info("Keyboard already hidden or does not require dismissal on iOS");
                    }
                } else {
                    LogHelper.info("The keyboard is not showing on the screen");
                }
            }
        } catch (Exception e) {
            LogHelper.warn("Notice when trying to hide keyboard: " + e.getMessage());
        }
    }

    public static void turnOffNotification() {
        try {
            AppiumDriver driver = DriverManager.getDriver();
            List<WebElement> notifies = driver.findElements(By.id("com.android.systemui:id/notification_panel"));
            if (!notifies.isEmpty() && notifies.get(0).isDisplayed()) {
                LogHelper.info("Ready to turn off notification");
                driver.navigate().back();
                LogHelper.info("Turn off notification success");
            } else {
                LogHelper.info("Notification panel is already closed");
            }
        } catch (Exception e) {
            LogHelper.error("An error occurred when trying to turn off notification - " + e.getMessage());
        }
    }

    public static void turnOnNotification() {
        try {
            AppiumDriver driver = DriverManager.getDriver();
            if (driver instanceof AndroidDriver) {
                List<WebElement> notifies = driver.findElements(By.id("com.android.systemui:id/notification_panel"));
                if (notifies.isEmpty() || !notifies.get(0).isDisplayed()) {
                    LogHelper.info("Ready to turn on notification");
                    ((AndroidDriver) driver).openNotifications();
                    LogHelper.info("Turn on notification success");
                } else {
                    LogHelper.info("Notification panel is already opened");
                }
            }
        } catch (Exception e) {
            LogHelper.error("An error occurred when trying to turn on notification - " + e.getMessage());
        }
    }

    // 10. APP MANAGEMENT (Cross-platform)
    public static void getAppToShowMainScreen(String appPackage) {
        try {
            LogHelper.info("Ready to activate app: " + appPackage);
            ((InteractsWithApps) DriverManager.getDriver()).activateApp(appPackage);
            LogHelper.info("Get app to show on main screen success: " + appPackage);
        } catch (Exception e) {
            LogHelper.error("An error occurred when trying to activate app - " + e.getMessage());
        }
    }

    public static void killApp(String appPackage) {
        try {
            LogHelper.info("Ready to kill app: " + appPackage);
            ((InteractsWithApps) DriverManager.getDriver()).terminateApp(appPackage);
            LogHelper.info("Kill app success: " + appPackage);
        } catch (Exception e) {
            LogHelper.error("An error occurred when trying to kill app - " + e.getMessage());
        }
    }

    public static void pushAppBackgroundWithin(int seconds) {
        try {
            LogHelper.info("Ready to push app into background within: " + seconds + "s");
            ((InteractsWithApps) DriverManager.getDriver()).runAppInBackground(Duration.ofSeconds(seconds));
            LogHelper.info("Push app into background success within: " + seconds + "s");
        } catch (Exception e) {
            LogHelper.error("An error occurred when trying to push app into background - " + e.getMessage());
        }
    }

    public static boolean isAppInstall(String appPackage) {
        try {
            LogHelper.info("Checking if app is installed: " + appPackage);
            boolean installed = ((InteractsWithApps) DriverManager.getDriver()).isAppInstalled(appPackage);
            if (installed) {
                LogHelper.info("The app is installed: " + appPackage);
                return true;
            } else {
                LogHelper.warn("The app is not installed: " + appPackage);
                return false;
            }
        } catch (Exception e) {
            LogHelper.error("An error occurred when checking if app is installed - " + e.getMessage());
            return false;
        }
    }


    // 11. CLIPBOARD
    public static String getTextFromClipboard() {
        try {
            LogHelper.info("Ready to get text from clipboard");
            String result = ((HasClipboard) DriverManager.getDriver()).getClipboardText();
            LogHelper.info("Get text from clipboard success: " + result);
            return result;
        } catch (Exception e) {
            LogHelper.error("An error occurred when trying to get text from clipboard - " + e.getMessage());
            return "";
        }
    }

    public static void setTextIntoClipboard(String text) {
        try {
            LogHelper.info("Ready to set text into clipboard: " + text);
            ((HasClipboard) DriverManager.getDriver()).setClipboardText(text);
            LogHelper.info("Set text into clipboard success: " + text);
        } catch (Exception e) {
            LogHelper.error("An error occurred when trying to set text into clipboard - " + e.getMessage());
        }
    }

    // 12. ANDROID HARDWARE KEYS
    public static void clickBackAndroid() {
        try {
            AppiumDriver driver = DriverManager.getDriver();
            if (driver instanceof AndroidDriver) {
                LogHelper.info("Ready to click back button of Android");
                ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
                LogHelper.info("Click back button success");
            }
        } catch (Exception e) {
            LogHelper.error("An error occurred when trying to click back button - " + e.getMessage());
        }
    }

    public static void clickHomeAndroid() {
        try {
            AppiumDriver driver = DriverManager.getDriver();
            if (driver instanceof AndroidDriver) {
                LogHelper.info("Ready to click home button of Android");
                ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.HOME));
                LogHelper.info("Click home button success");
            }
        } catch (Exception e) {
            LogHelper.error("An error occurred when trying to click home button - " + e.getMessage());
        }
    }

    public static void clickEnterAndroid() {
        try {
            AppiumDriver driver = DriverManager.getDriver();
            if (driver instanceof AndroidDriver) {
                LogHelper.info("Ready to click enter button of Android");
                ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
                LogHelper.info("Click enter button success");
            }
        } catch (Exception e) {
            LogHelper.error("An error occurred when trying to click enter button - " + e.getMessage());
        }
    }

    // 13. ALERTS / POPUPS
    public static void acceptAlert() {
        try {
            LogHelper.info("Ready to click accept in the Popup / Alert");
            DriverManager.getDriver().switchTo().alert().accept();
            LogHelper.info("Accept Popup / Alert success");
        } catch (Exception e) {
            LogHelper.error("An error occurred when trying to click accept Popup / Alert - " + e.getMessage());
        }
    }

    public static void dismissAlert() {
        try {
            LogHelper.info("Ready to click dismiss in the Popup / Alert");
            DriverManager.getDriver().switchTo().alert().dismiss();
            LogHelper.info("Dismiss Popup / Alert success");
        } catch (Exception e) {
            LogHelper.error("An error occurred when trying to click dismiss Popup / Alert - " + e.getMessage());
        }
    }
}