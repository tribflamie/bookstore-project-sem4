package com.backend.bookstore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String status;
    
    public UserDto(Integer id, String name, String phone, String email, String status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.status = status;
    }

    
}
