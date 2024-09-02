package com.backend.bookstore.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.backend.bookstore.dtos.UserDto;
import com.backend.bookstore.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findByEmailId(@Param("email") String email);

    List<UserDto> getAllUser();

    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);
}
