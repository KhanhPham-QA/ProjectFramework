package khanhpham.report;

import khanhpham.driver.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class AllureManager {

    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }


    //Screenshot attachment for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }





//    // Cơ chế chụp ảnh mới: An toàn tuyệt đối, tự động đồng bộ luồng dữ liệu Appium
//    public static void saveScreenshotPNG () {
//        try {
//            if (DriverManager.getDriver() != null) {
//                // Lấy mảng byte ảnh trực tiếp từ AppiumDriver
//                byte[] screenshotBytes = DriverManager.getDriver().getScreenshotAs(OutputType.BYTES);
//                // Ép Allure đẩy trực tiếp luồng byte này vào kịch bản hiện tại
//                Allure.addAttachment("Page Screenshot", "image/png", new ByteArrayInputStream(screenshotBytes), "png");
//                System.out.println("✅ [Allure] Chụp ảnh màn hình thành công.");
//            } else {
//                System.out.println("❌ [Allure] Không thể chụp ảnh vì Driver bị NULL.");
//            }
//        } catch (Exception e) {
//            System.out.println("❌ [Allure] Lỗi hệ thống khi chụp ảnh: " + e.getMessage());
//        }
//    }
}
