package vn.techmaster.userbackend.mapper;

import vn.techmaster.userbackend.dto.UserDto;
import vn.techmaster.userbackend.model.User;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .avatar(user.getAvatar())
                .build();
    }
}
