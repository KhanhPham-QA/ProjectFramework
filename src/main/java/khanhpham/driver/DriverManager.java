package khanhpham.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class DriverManager {

    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static void setDriver (AppiumDriver tempDriver) {
        driver.set(tempDriver);
    }

    public static AppiumDriver getDriver () {
        return driver.get();
    }

    public static void tearDownDriver () {
        if (driver.get() != null){
            getDriver().quit();
            driver.remove();
        }
    }

    public static boolean isIos (){
        return getDriver() instanceof IOSDriver;
    }

    public static boolean isAndroid (){
        return getDriver() instanceof AndroidDriver;
    }

}
