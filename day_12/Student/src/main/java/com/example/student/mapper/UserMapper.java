package com.example.student.mapper;

import com.example.student.dto.UserDto;
import com.example.student.model.User;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setAvatar(user.getAvatar());

        return userDto;
    }
}
