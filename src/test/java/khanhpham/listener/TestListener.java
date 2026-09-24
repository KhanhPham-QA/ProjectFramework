package khanhpham.listener;

import khanhpham.constants.DataConfig;
import khanhpham.helpers.CaptureHelper;
import khanhpham.helpers.LogHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext result) {
        LogHelper.info("♻\uFE0F Setup môi trường: " + result.getStartDate());
    }

    @Override
    public void onFinish(ITestContext result) {
        LogHelper.info("\uD83D\uDD06 Kết thúc chạy test: " + result.getEndDate());
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogHelper.info("➡\uFE0F Bắt đầu chạy test case: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogHelper.info("✅ Test case " + result.getName() + " is passed.");

        if (DataConfig.SCREENSHOT_PASS.equalsIgnoreCase("true") || DataConfig.SCREENSHOT_ALL.equalsIgnoreCase("true")){
            CaptureHelper.captureScreenshot(result.getName());
        }

    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogHelper.error("❌ Test case " + result.getName() + " is failed.");

        LocalDateTime now = LocalDateTime.now(); // lấy ngày giờ hiện tại
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        LogHelper.info("Thời gian: " + formattedDate);
        LogHelper.error("Nguyên nhân: " + result.getThrowable());

        if (DataConfig.SCREENSHOT_FAIL.equalsIgnoreCase("true") || DataConfig.SCREENSHOT_ALL.equalsIgnoreCase("true")){
            CaptureHelper.captureScreenshot(result.getName());
        }
//        if (DataConfig.RECORD_VIDEO.equalsIgnoreCase("true")){
//            try {
//                Thread.sleep(Duration.ofSeconds(2));
//            }catch (Exception e) {
//                throw new RuntimeException(e.getMessage());
//            }
//            CaptureHelper.stopRecording(result.getName());
//        }

        //Allure Report
//        AllureManager.saveTextLog(result.getName()+ " is failed");
//        AllureManager.saveScreenshotPNG();



    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogHelper.warn("⛔\uFE0F Test case " + result.getName() + " is skipped.");

//        CaptureHelper.stopAndDiscardRecording();
    }
}
