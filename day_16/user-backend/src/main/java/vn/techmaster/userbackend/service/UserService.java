package vn.techmaster.userbackend.service;

import org.springframework.stereotype.Service;
import vn.techmaster.userbackend.dto.UserDto;
import vn.techmaster.userbackend.exception.NotFoundException;
import vn.techmaster.userbackend.mapper.UserMapper;
import vn.techmaster.userbackend.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    // Lưu danh sách tất cả user
    private List<User> users;

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
}
