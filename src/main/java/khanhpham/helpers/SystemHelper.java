package khanhpham.helpers;

import org.apache.poi.util.StringUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class SystemHelper {
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    //thay thế những khoảng trắng thành dấu gạch dưới
    public static String makeSlug(String input) {
        if (StringUtil.isBlank(input))
            throw new IllegalArgumentException();

        String noWhiteSpace = WHITESPACE.matcher(input).replaceAll("_");
        String normalized = Normalizer.normalize(noWhiteSpace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }



    /**
     * @return Get the path to your source directory with a / at the end
     * lấy ra 1 cái đường dẫn từ ổ đĩa cho đến thư mục máy tính
     */
    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + File.separator;
        return current;
    }


//    public static void createFolder(String path) {
//        // File is a class inside java.io package
//        File file = new File(path);
//
//        String result = null;
//
//        int lengthSum = path.length();
//        int lengthSub = path.substring(0, path.lastIndexOf('/')).length();
//
//        result = path.substring(lengthSub, lengthSum);
//
//        if (!file.exists()) {
//            file.mkdir();  // mkdir is used to create folder
//            System.out.println("Folder " + file.getName() + " created: " + path);
//        } else {
//            System.out.println("Folder already created");
//        }
//    }

    /**
     * Create folder empty
     * để vào 1 đường dẫn sẽ tự động tạo folder trong thư mục máy tính
     * @param path path to create folder
     */
    public static boolean createFolder(String path) {
        try {
            File folder = new File(path);

            // Kiểm tra xem đã tồn tại và có phải là folder không
            if (folder.exists() && folder.isDirectory()) {
                LogHelper.warn("Folder đã tồn tại: " + path);
                return false;
            }

            // Tạo folder và các thư mục cha
            boolean created = folder.mkdirs();

            if (created) {
                LogHelper.info("Tạo folder thành công: " + path);
            } else {
                LogHelper.error("Tạo folder thất bại: " + path);
            }

            return created;
        } catch (Exception e) {
            LogHelper.error("Lỗi khi tạo folder: " + e.getMessage());
            return false;
        }
    }

    /**
     * @param str        string to be split based on condition
     * @param valueSplit the character to split the string into an array of values
     * @return array of string values after splitting
     * // tách chuỗi, đưa vào 1 chuỗi và 1 kí tự cần tách sau đó sẽ tách chuỗi theo ý mình (tách theo khoảng trắng, theo dấu phẩy)
     */
    public static ArrayList<String> splitString(String str, String valueSplit) {
        ArrayList<String> arrayListString = new ArrayList<>();
        for (String s : str.split(valueSplit, 0)) {
            arrayListString.add(s);
        }
        return arrayListString;
    }

    // kiểm tra xem trong chuỗi có các giá trị gì
    public static boolean checkValueInListString(String expected, String listValues[]) {
        boolean found = false;

        for (String s : listValues) {
            if (s.equals(expected)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public static boolean checkValueInListString(String expected, List<String> listValues) {
        boolean found = false;

        for (String s : listValues) {
            if (s.equals(expected)) {
                found = true;
                break;
            }
        }
        return found;
    }


    /**
     System.out.println(System.getProperty("os.name")); //hiển thị tên hệ điều hành đang chạy
     System.out.println(System.getProperty("user.dir")); //hiển thị địa chỉ gốc của project / thư mục đang chạy
     System.out.println(System.getProperty("user.name")); //hiển thị địa chỉ gốc của thư mục home của user
     System.out.println(System.getProperty("file.separator")); //hiển thị dấu path của Windows dùng \
     System.out.println(System.getProperty("file.encoding")); //hiển thị Encoding mặc định JVM dùng đọc file
     System.out.println(System.getProperty("java.class.path")); //hiển thị danh sách toàn bộ classpath
     **/
    public static void killPort (String port){
        String cmd = "";
        if (System.getProperty("os.name").toLowerCase().contains("win")){
            cmd = "cmd /c netstat -ano | findstr :" + port;
        } else {
            cmd = "lsof -i :" + port;
        }

        try {
            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null){
                String[] token = line.trim().split("\\s+");
                String processId = token[4];
                if (System.getProperty("os.name").toLowerCase().contains("win")){
                    Runtime.getRuntime().exec("taskkill /F /PID " + processId);
                } else {
                    Runtime.getRuntime().exec("kill -9 " + processId);
                }
            }
            reader.close();
            process.waitFor();
            LogHelper.info("####### Kill process on port " + port + " successfully.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void startAppiumWithPlugins(String server, String port) {
        ProcessBuilder processBuilder = new ProcessBuilder(
                "appium",
                "-a", server,
                "-p", port,
                "-ka", "800",
                "--use-plugins", "appium-reporter-plugin,element-wait,gestures,device-farm,appium-dashboard",
                "-pa", "/",
                "--plugin-device-farm-platform", "android"
        );

        // Redirect error and output streams
        processBuilder.redirectErrorStream(true);

        try {
            // Start the process
            Process process = processBuilder.start();
            LogHelper.info("Appium server started with plugins.");

            // Optional: Read the output (if needed for debugging)
            new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        LogHelper.info(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkTextContain (String text, String[] listText){
        String trimText = text.trim();
        for (int i = 0; i < listText.length; i++){
            if (listText[i].contains(trimText)){
                return true;
            }
        }
        return false;
    }
}
