package com.example.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// Dependence : ModelMapper, MapStruct
public class UserDto {
    private String username;
    private String email;
    private String avatar;
}
