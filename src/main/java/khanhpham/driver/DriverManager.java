package khanhpham.driver;

import io.appium.java_client.AppiumDriver;

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

}
