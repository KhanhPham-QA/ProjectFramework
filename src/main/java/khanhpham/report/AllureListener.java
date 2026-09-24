package khanhpham.report;

import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import khanhpham.constants.DataConfig;
import khanhpham.driver.DriverManager;
import khanhpham.helpers.CaptureHelper;
import khanhpham.helpers.LogHelper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

public class AllureListener implements TestLifecycleListener {

    @Override
    public void beforeTestStart(TestResult result) {
        if (DataConfig.RECORD_VIDEO.equalsIgnoreCase("true")) {
            CaptureHelper.startRecording();
        }
    }

    @Override
    public void beforeTestStop(TestResult result) {
        if (DriverManager.getDriver() == null) {
            return;
        }

        boolean isFailed = result.getStatus().equals(Status.FAILED) || result.getStatus().equals(Status.BROKEN);

        if (isFailed) {

            // A. Chụp ảnh sau đó đính kèm vào Allure Report
            try {
                byte[] screenshotBytes = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(result.getName() + "_Failed_Screenshot", new ByteArrayInputStream(screenshotBytes));
            } catch (Exception e) {
                LogHelper.error("Lỗi chụp ảnh Allure: " + e.getMessage());
            }

            // B. Dừng quay video -> thực hiện lưu file trên máy tính và đính kèm trên Allure Report
            if (DataConfig.RECORD_VIDEO.equalsIgnoreCase("true")) {
                try {
                    Thread.sleep(1500); // Chờ 1.5s để render video
                } catch (InterruptedException ignored) {}

                File videoFile = CaptureHelper.stopRecording(result.getName());

                if (videoFile != null && videoFile.exists()) {
                    try (InputStream is = Files.newInputStream(videoFile.toPath())) {
                        Allure.addAttachment("Video Record: " + result.getName(), "video/mp4", is, "mp4");
                    } catch (Exception e) {
                        LogHelper.error("Lỗi đính kèm video vào Allure: " + e.getMessage());
                    }
                }
            }
        } else {
            // C. Status Pass/Skip sẽ dừng quay và không lưu lại file trong máy tính
            if (DataConfig.RECORD_VIDEO.equalsIgnoreCase("true")) {
                CaptureHelper.stopAndDiscardRecording();
            }
        }
    }

    @Override public void beforeTestSchedule(TestResult result) {}
    @Override public void afterTestSchedule(TestResult result) {}
    @Override public void beforeTestUpdate(TestResult result) {}
    @Override public void afterTestUpdate(TestResult result) {}
    @Override public void afterTestStart(TestResult result) {}
    @Override public void afterTestStop(TestResult result) {}
    @Override public void beforeTestWrite(TestResult result) {}
    @Override public void afterTestWrite(TestResult result) {}
}