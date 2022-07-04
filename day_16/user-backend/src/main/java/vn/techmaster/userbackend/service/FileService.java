package vn.techmaster.userbackend.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.userbackend.exception.BadRequestException;
import vn.techmaster.userbackend.exception.NotFoundException;
import vn.techmaster.userbackend.util.Utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    // Xem file
    public byte[] readFile(int id, String fileId) {
        // Lấy ra đường dẫn file tương ứng với user_id
        Path userPath = rootDir.resolve(String.valueOf(id));

        // Kiểm tra xem đường dẫn file có tồn tại hay không
        if(!Files.exists(userPath)) {
            throw new NotFoundException("Không thể đọc file " + fileId);
        }

        try {
            // Lấy ra đường dẫn file tương ứng với user_id và file_id
            Path file = userPath.resolve(fileId);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()) {
                InputStream stream = resource.getInputStream();
                byte[] bytes = StreamUtils.copyToByteArray(stream);

                stream.close();
                return bytes;
            } else {
                throw new RuntimeException("Không thể đọc file " + fileId);
            }

        } catch (Exception e) {
            throw new RuntimeException("Không thể đọc file " + fileId);
        }
    }

    // Lấy danh sách file của user
    public List<String> getFiles(int id) {
        // Lấy ra đường dẫn file tương ứng với user_id
        Path userPath = rootDir.resolve(String.valueOf(id));

        // Kiểm tra xem đường dẫn file có tồn tại hay không
        if(!Files.exists(userPath)) {
            return new ArrayList<>();
        }

        // Lấy ra danh sách file tương ứng với user_id
        List<File> files = List.of(userPath.toFile().listFiles());

        // Lấy ra danh sách filePath
        List<String> filesPath = files.stream()
                .map(File::getName)
                .sorted(Comparator.reverseOrder())
                .map(file -> "/api/v1/users/" + id + "/files/" + file)
                .toList();

        return filesPath;
    }

    // Xóa file
    public void deleteFile(int id, String fileId) {
        // Lấy ra đường dẫn file tương ứng với user_id
        Path userPath = rootDir.resolve(String.valueOf(id));

        // Kiểm tra folder chứa file có tồn tại hay không
        if(!Files.exists(userPath)) {
            throw new NotFoundException("File " + fileId + " không tồn tại");
        }

        // Lấy ra đường dẫn file tương ứng với user_id và file_id
        Path file = userPath.resolve(fileId);

        // Kiểm tra file có tồn tại hay không
        if(!file.toFile().exists()) {
            throw new NotFoundException("File " + fileId + " không tồn tại");
        }

        // Tiến hành xóa file
        file.toFile().delete();
    }
}
