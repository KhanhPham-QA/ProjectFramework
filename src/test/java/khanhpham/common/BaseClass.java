package khanhpham.common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.qameta.allure.Step;
import khanhpham.constants.DataConfig;
import khanhpham.driver.DriverManager;
import khanhpham.helpers.LogHelper;
import khanhpham.helpers.SystemHelper;
import khanhpham.listener.TestListener;
import khanhpham.report.AllureManager;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;
import java.util.Objects;

@Listeners(TestListener.class)
public class BaseClass {

    private AppiumDriverLocalService service;
    private String HOST = "127.0.0.1";
    private String PORT = "4723";
    private final int TIMEOUT_SERVICE = Integer.parseInt(DataConfig.TIMEOUT_SERVICE);
    /**
     * Chạy Appium server với host và port được chỉ định.
     *
     * @param host Địa chỉ host của Appium server
     * @param port Port của Appium server
     */

    @Step ("Run appium service with host: {0} & port: {1}")
    public void runAppiumServer(String host, String port) {
        LogHelper.info("HOST: " + host);
        LogHelper.info("PORT: " + port);

        //Set host and port
        if (host == null || host.isEmpty()) {
            host = HOST;
        } else {
            HOST = host;
        }

        if (port == null || port.isEmpty()) {
            port = PORT;
        } else {
            PORT = port;
        }

        //Kill process on port
        SystemHelper.killPort(port);

        //Build the Appium service
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress(host);
        builder.usingPort(Integer.parseInt(port));
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "info"); // Set log level (optional)
        builder.withTimeout(Duration.ofSeconds(TIMEOUT_SERVICE));

        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();

        if (service.isRunning()) {
            LogHelper.info("##### Appium server started on " + HOST + ":" + PORT);
            AllureManager.saveTextLog("Appium server started successfully");
        } else {
            LogHelper.error("Failed to start Appium server on LOCAL.");
            AllureManager.saveTextLog("Appium server started fail");
        }

    }

    /**
     * Thiết lập (khởi tạo và lưu trữ) AppiumDriver cho luồng hiện tại.
     *
     * @param platformName Tên platform (Android/iOS)
     * @param deviceName   Tên thiết bị trong device.json
     * @param udid         UDID của thiết bị Android (quan trọng cho parallel)
     * @param host         Địa chỉ host của Appium server
     * @param port         Port của Appium server
     * @param bundleId     Bundle ID của app iOS
     * @param wdaLocalPort Port WDA (iOS parallel)
     * @param systemPort   Port System (Android parallel)
     */
    @Step ("Set up Appium driver for platform {0} on device {1}")
    @BeforeTest(alwaysRun = true)
    @Parameters({"platformName", "deviceName", "udid", "host", "port", "bundleId", "wdaLocalPort", "systemPort"})
    public void setUpDriver(String platformName, String deviceName, @Optional String udid, String host, String port, @Optional String bundleId, @Optional String wdaLocalPort, @Optional String systemPort) {
        //Khởi động Appium server dưới máy local
        if (DataConfig.APPIUM_DRIVER_LOCAL_SERVICE.trim().equalsIgnoreCase("true")) {
            LogHelper.info("Khởi động Appium server LOCAL: " + host + ":" + port);
            AllureManager.saveTextLog("Appium is running");
            runAppiumServer(host, port);
        } else {
            LogHelper.warn("Chạy Appium server từ xa hoặc đã bật sẵn.");
            AllureManager.saveTextLog("Appium is remote or existing");
        }

        //Print tất cả các thông số
        LogHelper.info("platformName: " + platformName);
        LogHelper.info("platformVersion: " + DataConfig.getValueJsonConfig(platformName, deviceName, "platformVersion"));
        LogHelper.info("deviceName: " + DataConfig.getValueJsonConfig(platformName, deviceName, "deviceName"));
        LogHelper.info("udid: " + DataConfig.getValueJsonConfig(platformName, deviceName, "udid"));
        LogHelper.info("automationName: " + DataConfig.getValueJsonConfig(platformName, deviceName, "automationName"));
        LogHelper.info("appPackage: " + DataConfig.getValueJsonConfig(platformName, deviceName, "appPackage"));
        LogHelper.info("appActivity: " + DataConfig.getValueJsonConfig(platformName, deviceName, "appActivity"));
        LogHelper.info("noReset: " + DataConfig.getValueJsonConfig(platformName, deviceName, "noReset"));
        LogHelper.info("fullReset: " + DataConfig.getValueJsonConfig(platformName, deviceName, "fullReset"));
        LogHelper.info("autoGrantPermissions: " + DataConfig.getValueJsonConfig(platformName, deviceName, "autoGrantPermissions"));
        LogHelper.info("host: " + host);
        LogHelper.info("port: " + port);
        LogHelper.info("bundleId: " + bundleId);
        LogHelper.info("wdaLocalPort: " + wdaLocalPort);
        LogHelper.info("systemPort: " + systemPort);

        AppiumDriver driver = null;

        try {
            if (platformName.equalsIgnoreCase("Android")) {
                UiAutomator2Options options = new UiAutomator2Options();
                options.setPlatformName(platformName);
                options.setPlatformVersion(DataConfig.getValueJsonConfig(platformName, deviceName, "platformVersion"));
//                options.setDeviceName(DataConfig.getValueJsonConfig(platformName, deviceName, "deviceName"));

                String avdName = DataConfig.getValueJsonConfig(platformName, deviceName, "deviceName");
                options.setDeviceName(avdName);

                // Tự động tìm cổng emulator đang chạy AVD này hoặc tự bật nếu chưa mở
                options.setAvd(avdName);
                options.setAvdLaunchTimeout(Duration.ofSeconds(120));
                options.setAvdReadyTimeout(Duration.ofSeconds(120));

                if (udid != null && !udid.isEmpty()) {
                    options.setUdid(udid);
                }
                String appPackage = DataConfig.getValueJsonConfig(platformName, deviceName, "appPackage");
                if (appPackage != null && !appPackage.isEmpty()) {
                    options.setAppPackage(appPackage);
                }
                String appActivity = DataConfig.getValueJsonConfig(platformName, deviceName, "appActivity");
                if (appActivity != null && !appActivity.isEmpty()) {
                    options.setAppActivity(appActivity);
                }
                // options.setApp("/path/to/your/app.apk");
                options.setAutomationName(Objects.requireNonNullElse(DataConfig.getValueJsonConfig(platformName, deviceName, "automationName"), "UiAutomator2"));
                options.setNoReset(Boolean.parseBoolean(DataConfig.getValueJsonConfig(platformName, deviceName, "noReset")));
                options.setFullReset(Boolean.parseBoolean(DataConfig.getValueJsonConfig(platformName, deviceName, "fullReset")));

                if (systemPort != null && !systemPort.isEmpty()) {
                    options.setSystemPort(Integer.parseInt(systemPort));
                }

                driver = new AndroidDriver(new URL("http://" + host + ":" + port), options);
                LogHelper.info("Khởi tạo AndroidDriver cho thread: " + Thread.currentThread().getId() + " trên thiết bị: " + deviceName);
                AllureManager.saveTextLog("Khởi tạo AndroidDriver cho thread: " + Thread.currentThread().getId() + " trên thiết bị: " + deviceName);


            } else if (platformName.equalsIgnoreCase("iOS")) {
                XCUITestOptions options = new XCUITestOptions();
                options.setPlatformName(platformName);
                options.setPlatformVersion(DataConfig.getValueJsonConfig(platformName, deviceName, "platformVersion"));
                options.setDeviceName(DataConfig.getValueJsonConfig(platformName, deviceName, "deviceName"));

                // Lấy UDID từ XML hoặc devices.json
                String deviceUdid = (udid != null && !udid.isEmpty()) ? udid : DataConfig.getValueJsonConfig(platformName, deviceName, "udid");
                if (deviceUdid != null && !deviceUdid.isEmpty()) {
                    options.setUdid(deviceUdid);
                }
//                // options.setApp("/path/to/your/app.app or .ipa");
//                if (bundleId != null && !bundleId.isEmpty()) {
//                    options.setBundleId(bundleId);
//                }

                String deviceBundleId = (bundleId != null && !bundleId.isEmpty() ? bundleId : DataConfig.getValueJsonConfig(platformName, deviceName, "bundleId"));
                if (deviceBundleId != null && !deviceBundleId.isEmpty()) {
                    options.setBundleId(deviceBundleId);
                }

                options.setAutomationName(Objects.requireNonNullElse(DataConfig.getValueJsonConfig(platformName, deviceName, "automationName"), "XCUITest"));
                options.setNoReset(Boolean.parseBoolean(DataConfig.getValueJsonConfig(platformName, deviceName, "noReset")));
                options.setFullReset(Boolean.parseBoolean(DataConfig.getValueJsonConfig(platformName, deviceName, "fullReset")));

                if (wdaLocalPort != null && !wdaLocalPort.isEmpty()) {
                    options.setWdaLocalPort(Integer.parseInt(wdaLocalPort));
                }
                // options.setXcodeOrgId("YOUR_TEAM_ID");
                // options.setXcodeSigningId("iPhone Developer");

                driver = new IOSDriver(new URL("http://" + host + ":" + port), options);
                LogHelper.info("Khởi tạo IOSDriver cho thread: " + Thread.currentThread().getId() + " trên thiết bị: " + deviceName);
                AllureManager.saveTextLog("Khởi tạo IOSDriver cho thread: " + Thread.currentThread().getId() + " trên thiết bị: " + deviceName);

            } else {
                throw new IllegalArgumentException("Platform không hợp lệ: " + platformName);
            }

            // Lưu driver vào ThreadLocal
            DriverManager.setDriver(driver);

        } catch (Exception e) {
            LogHelper.error("❌Lỗi nghiêm trọng khi khởi tạo driver cho thread " + Thread.currentThread().getId() + " trên device " + deviceName + ": " + e.getMessage());
            throw new RuntimeException("❌Không thể khởi tạo Appium driver ", e);
        }

    }


    @Step ("Close driver and appium server")
    @AfterTest(alwaysRun = true)
    public void tearDownDriver() {
        if (DriverManager.getDriver() != null) {

            DriverManager.tearDownDriver();
            LogHelper.info("##### Driver quit and removed.");
        }

        //Dừng Appium server LOCAL nếu đã khởi động
        if (DataConfig.APPIUM_DRIVER_LOCAL_SERVICE.trim().equalsIgnoreCase("true")) {
            stopAppiumServer();
        }
    }

    /**
     * Stop Appium server.
     */
    public void stopAppiumServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            LogHelper.info("##### Appium server stopped on " + HOST + ":" + PORT);
        }
        //Kill process on port
        SystemHelper.killPort(PORT);
    }
}
