package com.example.BookMyMovie.service;

import com.example.BookMyMovie.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface IUserService {
    User add(User user);

    void delete(User user);

    User update(User user);

}
