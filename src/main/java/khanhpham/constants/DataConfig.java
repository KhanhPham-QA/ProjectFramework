package khanhpham.constants;

import khanhpham.helpers.JsonHelper;
import khanhpham.helpers.LogHelper;
import khanhpham.helpers.PropertiesHelper;

public class DataConfig {

    static {
        PropertiesHelper.setFile("src/test/resources/configs/config.properties");
    }

    public static String APPIUM_DRIVER_LOCAL_SERVICE = PropertiesHelper.getValue("APPIUM_DRIVER_LOCAL_SERVICE");
    public static String TIMEOUT_SERVICE = PropertiesHelper.getValue("TIMEOUT_SERVICE");
    public static String TIMEOUT_EXPLICIT_DEFAULT = PropertiesHelper.getValue("TIMEOUT_EXPLICIT_DEFAULT");
    public static String TIMEOUT_SCROLL_DEFAULT = PropertiesHelper.getValue("TIMEOUT_SCROLL_DEFAULT");
    public static String NUMBER_RETRY = PropertiesHelper.getValue("NUMBER_RETRY");
    public static String JSON_CONFIG_FILE_PATH = PropertiesHelper.getValue("JSON_CONFIG_FILE_PATH");
    public static String JSON_DEVICE_FILE_PATH = PropertiesHelper.getValue("JSON_DEVICE_FILE_PATH");
    public static String EXCEL_DATA_FILE_PATH = PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH");
    public static String TEST_DATA_FOLDER_PATH = PropertiesHelper.getValue("TEST_DATA_FOLDER_PATH");
    public static String LOCATE = PropertiesHelper.getValue("LOCATE");
    public static String SCREENSHOT_FAIL = PropertiesHelper.getValue("SCREENSHOT_FAIL");
    public static String SCREENSHOT_PASS = PropertiesHelper.getValue("SCREENSHOT_PASS");
    public static String SCREENSHOT_ALL = PropertiesHelper.getValue("SCREENSHOT_ALL");
    public static String SCREENSHOT_ALL_STEP = PropertiesHelper.getValue("SCREENSHOT_ALL_STEP");
    public static String SCREENSHOT_PATH = PropertiesHelper.getValue("SCREENSHOT_PATH");
    public static String RECORD_VIDEO = PropertiesHelper.getValue("RECORD_VIDEO");
    public static String RECORD_VIDEO_PATH = PropertiesHelper.getValue("RECORD_VIDEO_PATH");
    public static String EXTENT_REPORT_PATH = PropertiesHelper.getValue("EXTENT_REPORT_PATH");
    public static String ALLURE_REPORT_PATH = PropertiesHelper.getValue("ALLURE_REPORT_PATH");

    public static String getValueJsonConfig (String platform, String device, String propertyName) {
        String result = JsonHelper.getValueJsonObject_FilePath(
                JSON_DEVICE_FILE_PATH,
                "platforms",
                platform.trim().toLowerCase(),
                "devices",
                device.trim().toLowerCase(),
                propertyName
        );


        LogHelper.info("***" + propertyName + ": " + result);
        return result;
    }
}
