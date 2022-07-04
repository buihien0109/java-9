package vn.techmaster.userbackend.util;

import java.util.Arrays;
import java.util.List;

public class Utils {
    // Lấy extension file
    // image01.png -> png
    // image02.jpg -> jpg
    public static String getFileExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");
        if(lastIndexOf == -1) {
            return "";
        }
        return fileName.substring(lastIndexOf + 1);
    }

    // Kiểm tra extension có hợp lệ hay không
    public static boolean checkFileExtension(String fileExtension) {
        List<String> fileExtensions = Arrays.asList("png", "jpg", "jpeg");
        return fileExtensions.contains(fileExtension);
    }
}
