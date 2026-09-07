package khanhpham.helpers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.screenrecording.CanRecordScreen;
import khanhpham.constants.DataConfig;
import khanhpham.driver.DriverManager;
import khanhpham.utils.DateUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Base64;

public class CaptureHelper {
    /**
     * Hàm static để chụp ảnh màn hình và lưu vào đường dẫn file được chỉ định.
     *
     * @param fileName Đường dẫn file nơi muốn lưu ảnh chụp màn hình (ví dụ: "screenshots/image.png").
     */
    public static void captureScreenshot(String fileName) {
        try {
            // Ép kiểu driver thành TakesScreenshot để lấy ảnh màn hình
            File srcFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

            SystemHelper.createFolder(SystemHelper.getCurrentDir() + DataConfig.SCREENSHOT_PATH);
//            String filePath = SystemHelper.getCurrentDir() + DataConfig.SCREENSHOT_PATH.concat("/") + fileName + "_" + Thread.currentThread().getId() + "_" + SystemHelper.makeSlug(DateUtils.getCurrentDateTime()) + ".png";
            String filePath = SystemHelper.getCurrentDir() + DataConfig.SCREENSHOT_PATH + File.separator + fileName + "_" + Thread.currentThread().getId() + "_" + SystemHelper.makeSlug(DateUtils.getCurrentDateTime()) + ".png";

            // Tạo đối tượng Path cho file đích
            Path targetPath = new File(filePath).toPath();

            // Sao chép file từ nguồn sang đích, thay thế file nếu đã tồn tại
            Files.copy(srcFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            LogHelper.info("Chụp ảnh màn hình thành công, lưu tại: " + targetPath.toAbsolutePath());
        } catch (IOException e) {
            LogHelper.error("Lỗi trong quá trình lưu file ảnh: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            LogHelper.error("Lỗi trong quá trình chụp ảnh màn hình: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Bắt đầu ghi video
    public static void startRecording() {
        if (DriverManager.getDriver() != null) {
            try {
                ((AndroidDriver) DriverManager.getDriver()).startRecordingScreen(
                        new AndroidStartScreenRecordingOptions()
                                .withBitRate(4000000) // default: 4000000
                                .withVideoSize("1080x2400") // 720 x 1600, 1080 x 2400 pixels
                                .withTimeLimit(Duration.ofMinutes(10))); // 10 minutes max video length
                LogHelper.info("Bắt đầu ghi video cho " + DriverManager.getDriver().getCapabilities().getCapability("deviceName"));
            } catch (Exception e) {
                LogHelper.error("Lỗi khi bắt đầu ghi video: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // Dừng ghi video và lưu file
    public static void stopRecording(String fileName) {
        if (DriverManager.getDriver() != null) {
            try {
                String base64Video = ((CanRecordScreen) ((AndroidDriver) DriverManager.getDriver())).stopRecordingScreen();
                LogHelper.info("Base64 video length: " + (base64Video != null ? base64Video.length() : "null"));
                if (base64Video != null && !base64Video.isEmpty()) {
                    byte[] videoBytes = Base64.getDecoder().decode(base64Video);
                    LogHelper.info("Video bytes length: " + videoBytes.length);
                    SystemHelper.createFolder(SystemHelper.getCurrentDir() + DataConfig.RECORD_VIDEO_PATH);
//                    String videoFilePathAndName = SystemHelper.getCurrentDir() + DataConfig.RECORD_VIDEO_PATH.concat("/recording_") + fileName + "_" + Thread.currentThread().getId() + "_" + SystemHelper.makeSlug(DateUtils.getCurrentDateTime()) + ".mp4";
                    String videoFilePathAndName = SystemHelper.getCurrentDir() + DataConfig.RECORD_VIDEO_PATH + File.separator + "recording_" + fileName + "_" + Thread.currentThread().getId() + "_" + SystemHelper.makeSlug(DateUtils.getCurrentDateTime()) + ".mp4";
                    File videoFile = new File(videoFilePathAndName);
                    try (FileOutputStream fos = new FileOutputStream(videoFile)) {
                        fos.write(videoBytes);
                    }
                    LogHelper.info("Video được lưu tại: " + videoFile.getAbsolutePath() + " (Size: " + videoFile.length() + " bytes)");
                } else {
                    LogHelper.error("Không có dữ liệu video để lưu.");
                }
            } catch (Exception e) {
                LogHelper.info("Lỗi khi dừng ghi video: " + e.getMessage());
            }
        }
    }

    // Thêm hàm này để tắt quay video mà KHÔNG lưu ra file
    public static void stopAndDiscardRecording() {
        if (DriverManager.getDriver() != null) {
            try {
                // Chỉ gọi lệnh stop của Appium để giải phóng RAM, lấy chuỗi base64 nhưng KHÔNG làm gì với nó cả (Vứt đi)
                ((CanRecordScreen) ((AndroidDriver) DriverManager.getDriver())).stopRecordingScreen();
                LogHelper.info("Đã hủy video vì test chạy Pass.");
            } catch (Exception e) {
                LogHelper.error("Lỗi khi hủy video: " + e.getMessage());
            }
        }
    }
}
