package vn.techmaster.userbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.userbackend.dto.UserDto;
import vn.techmaster.userbackend.exception.NotFoundException;
import vn.techmaster.userbackend.mapper.UserMapper;
import vn.techmaster.userbackend.model.User;
import vn.techmaster.userbackend.request.UpdatePasswordRequest;
import vn.techmaster.userbackend.request.UpdateUserRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    // Lưu danh sách tất cả user
    private List<User> users;

    @Autowired
    private MailService mailService;

    @Autowired
    private FileService fileService;

    // Tạo 1 số dữ liệu
    public UserService() {
        users = new ArrayList<>();
        users.add(new User(1, "Nguyễn Văn A", "a@gmail.com", "0987654111", "Tỉnh Thái Bình", null, "111"));
        users.add(new User(2, "Trần Văn B", "b@gmail.com", "0987654222", "Tỉnh Nam Định", null, "222"));
        users.add(new User(3, "Ngô Thị C", "c@gmail.com", "0987654333", "Tỉnh Hưng Yên", null, "333"));
        users.add(new User(4, "Đoàn Thị D", "d@gmail.com", "0987654444", "Thành phố Hà Nội", null, "444"));
    }

    // Lấy danh sách user
    public List<UserDto> getUsers() {
        return users
                .stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    // Tìm kiếm user theo tên
    public List<UserDto> searchUser(String name) {
        return users
                .stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    // Xóa user theo id
    public void deleteUser(int id) {
        // Kiểm tra xem user có tồn tại hay không
        Optional<User> userOptional = findUserById(id);
        if(userOptional.isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        // Xóa user
        users.removeIf(user -> user.getId() == id);
    }

    // Helper method
    public Optional<User> findUserById(int id) {
        return users
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    public UserDto getUserById(int id) {
        // Kiểm tra xem user có tồn tại hay không
        // Nếu không -> throw exception
        // Nếu có -> trả về userDto
        return null;
    }

    public UserDto updateUser(int id, UpdateUserRequest request) {
        // Kiểm tra xem user có tồn tại hay không
        // Nếu không -> throw exception
        // Nếu có -> cập nhật lại thông tin của user theo các thông tin có trong request
        return null;
    }

    public void updatePassword(int id, UpdatePasswordRequest request) {
        // Kiểm tra xem user có tồn tại hay không
        // Nếu không -> throw exception
        // Kiểm tra password cũ có chính xác hay không
        // Kiểm tra password mới có trùng với password cũ hay không
        // Cập nhật password mới cho user
    }

    public String forgotPassword(int id) {
        // Kiểm tra xem user có tồn tại hay không
        // Nếu không -> throw exception
        // Nếu có -> generate chuỗi password ngẫu nhiên -> cập nhật password cho user -> trả về kết quả
        mailService.sendSimpleEmail("hien@techmaster.vn", "Quên mật khẩu", "111");
        return null;
    }

    // Upload file
    public String uploadFile(int id, MultipartFile file)  {
        if(findUserById(id).isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        // Upload file
        return fileService.uploadFile(id, file);
    }

    // Xem file
    public byte[] readFile(int id, String fileId) {
        return fileService.readFile(id, fileId);
    }

    // Upload avatar
    public String uploadAvatar(int id, MultipartFile file) {
        // Kiểm tra user có tồn tại hay không
        Optional<User> userOptional = findUserById(id);
        if(userOptional.isEmpty()) {
            throw new NotFoundException("Không tồn tại user có id = " + id);
        }

        // Lấy ra thông tin của user
        User user = userOptional.get();

        // Upload file
        String filePath = uploadFile(id, file);

        // Cập nhật lại avatar
        user.setAvatar(filePath);

        return filePath;
    }

    // Lấy danh sách file của user
    public List<String> getFiles(int id) {
        return fileService.getFiles(id);
    }
}
