package khanhpham.dataprovider;

import khanhpham.constants.DataConfig;
import khanhpham.helpers.ExcelHelper;
import org.testng.annotations.DataProvider;
import java.io.*;

public class DataProviderFactory {
    private static final String excelPath = DataConfig.EXCEL_DATA_FILE_PATH;

    @DataProvider(name = "invalid_url")
    public static Object[][] invalid_url (){
        ExcelHelper excelHelper = new ExcelHelper();
        return excelHelper.getExcelData(excelPath,"InvalidUrl");
    }


    @DataProvider(name = "admin_account")
    public static Object[][] admin_account (){
        return new Object[][]{
                {"bod@example.com","10203040"}
        };
    }


    @DataProvider(name = "blocked_account")
    public static Object[][] blocked_account (){
        return new Object[][]{
                {"alice@example.com","10203040"}
        };
    }

    @DataProvider(name = "visual_account")
    public static Object[][] visual_account (){
        return new Object[][]{
                {"visual@example.com","10203040"}
        };
    }
}
