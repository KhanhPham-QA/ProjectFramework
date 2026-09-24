package khanhpham.helpers;

import khanhpham.driver.DriverManager;
import org.openqa.selenium.By;

public class PlatformBy {
    public static By isPlatform (By androidBy, By iosBy){
        if (DriverManager.isAndroid()){
            return androidBy;
        }
        if (DriverManager.isIos()){
            return iosBy;
        }
        throw new IllegalStateException("Driver chưa khởi tạo hoặc không thuộc nền tảng Android/iOS hỗ trợ!");
    }
}
