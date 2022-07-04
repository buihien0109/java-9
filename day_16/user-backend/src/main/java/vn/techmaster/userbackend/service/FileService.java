package vn.techmaster.userbackend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.userbackend.exception.BadRequestException;
import vn.techmaster.userbackend.util.Utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

@Service
public class FileService {
    // Path folder để upload file
    private final Path rootDir = Paths.get("uploads");

    public FileService() {
        createFolder(rootDir.toString());
    }

    // Tạo folder trong trường hợp chưa tồn tại
    public void createFolder(String path) {
        File file = new File(path);
        if(!file.exists()) {
            file.mkdirs();
        }
    }

    // Xử lý phần upload file
    public String uploadFile(int id, MultipartFile file) {
        // Tạo folder tương ứng với userId
        // /upload/1
        Path userDir = Paths.get("uploads").resolve(String.valueOf(id));
        createFolder(userDir.toString());

        // Validate file
        validateFile(file);

        // Tạo file path tương ứng với file upload
        // /upload/1/633782844
        String generateFileId = String.valueOf(Instant.now().getEpochSecond());
        File fileServer = new File(userDir + "/" + generateFileId);

        try {
            // Sử dụng buffer để lưu dữ liệu từ file
            FileOutputStream fileOutputStream = new FileOutputStream(fileServer);
            BufferedOutputStream stream = new BufferedOutputStream(fileOutputStream);

            stream.write(file.getBytes());
            stream.close();

            // /api/v1/users/1/files/633782844
            return "/api/v1/users/" + id + "/files/" + generateFileId;

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi upload file");
        }
    }

    // Validate file
    public void validateFile(MultipartFile file) {
        // Kiểm tra tên file
        String fileName = file.getOriginalFilename();
        if(fileName == null || fileName.equals("")) {
            throw new BadRequestException("Tên file không được để trống");
        }

        // image01.png -> png
        // image02.jpg -> jpg
        // Kiểm tra extension
        String fileExtension = Utils.getFileExtension(fileName);
        if(!Utils.checkFileExtension(fileExtension)) {
            throw new BadRequestException("File không hợp lệ");
        }

        // Kiểm tra size file (tối đa 2MB)
        double sizeFile = (double) file.getSize() / 1_000_000L;
        if(sizeFile > 2) {
            throw new BadRequestException("File không được vượt quá 2MB");
        }
    }
}
