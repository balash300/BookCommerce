package com.books.service;

import com.books.dto.UserDTO;
import com.books.dto.UserLoginDTO;
import com.books.model.Users;

public interface UserService {
    Users register(UserDTO userDTO);
    Users login(UserLoginDTO userLoginDTO);
}
