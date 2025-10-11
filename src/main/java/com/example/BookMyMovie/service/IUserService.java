package com.example.BookMyMovie.service;

import com.example.BookMyMovie.model.User;

public interface IUserService {
    User add(User user);
    void delete(User user);
    User update(User user);

}
