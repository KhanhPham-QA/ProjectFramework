package khanhpham.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class PropertiesHelper {
    private static Properties properties;
    private static String linkFile;
    private static FileInputStream file;
    private static FileOutputStream out;
    private static final String relPropertiesFilePathDefault = "src/test/resources/configs/config.properties";

    /**
     * Add tất cả file Properties vào đây theo mẫu
     */
    public static Properties loadAllFiles() {
        LinkedList<String> files = new LinkedList<>();
        files.add("src/test/resources/configs/config.properties");
        // files.add("src/test/resources/configs/local.properties");
        // files.add("src/test/resources/configs/production.properties");

        try {
            properties = new Properties();

            for (String f : files) {
                Properties tempProp = new Properties();
                linkFile = SystemHelper.getCurrentDir() + f;
                file = new FileInputStream(linkFile);
                tempProp.load(file);
                properties.putAll(tempProp);
            }
            return properties;
        } catch (IOException ioe) {
            return new Properties();
        }
    }

    /**
     * set 1 file / đọc 1 file
     */
    public static void setFile(String relPropertiesFilePath) {
        properties = new Properties();
        try {
            linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePath;
            file = new FileInputStream(linkFile);
            properties.load(file);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * set default file / đọc default file
     */
    public static void setDefaultFile() {
        setFile(relPropertiesFilePathDefault);
    }


    /**
     * get value from file properties
     */
    public static String getValue(String key) {
        String value = null;
        try {
            if (file == null) {
                properties = new Properties();
                linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePathDefault;
                file = new FileInputStream(linkFile);
                properties.load(file);
                file.close();
            }
            // Lấy giá trị từ file đã Set
            value = properties.getProperty(key);
        } catch (Exception e) {
            LogHelper.error(e.getMessage());
        }
        return value;
    }


    /**
     * set key & value into file properties
     */
    public static void setDefaultValue(String key, String keyValue) {
        setValue(relPropertiesFilePathDefault, key, keyValue);
    }

    public static void setValue(String filePropertiesRelativePath, String key, String keyValue) {
        try {

            Properties properties = new Properties();
            FileInputStream file = new FileInputStream(SystemHelper.getCurrentDir() + filePropertiesRelativePath);
            properties.load(file);
            file.close();

            properties.setProperty(key, keyValue);
            FileOutputStream out = new FileOutputStream(SystemHelper.getCurrentDir() + filePropertiesRelativePath);
            properties.store(out, null);
            out.close();
            LogHelper.info("Set value '" + keyValue + "' to file " + filePropertiesRelativePath);
        } catch (Exception e) {
            LogHelper.error(e.getMessage());
        }
    }

    public static void removeKey(String filePropertiesRelativePath, String key) {
        try {
            // Đọc file properties
            Properties properties = new Properties();
            File file = new File(SystemHelper.getCurrentDir() + filePropertiesRelativePath);
            FileInputStream inputStream = new FileInputStream(file);
            properties.load(inputStream);
            inputStream.close();

            // Xoá key nếu tồn tại
            if (properties.containsKey(key)) {
                properties.remove(key);
                LogHelper.info("🔑 Đã xoá key: " + key);
            } else {
                LogHelper.warn("⚠️ Key không tồn tại: " + key);
            }

            // Ghi lại file
            FileOutputStream outputStream = new FileOutputStream(file);
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
