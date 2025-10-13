package com.example.BookMyMovie.service;

import com.example.BookMyMovie.model.UserProfile;

public interface IUserService {
    UserProfile add(UserProfile userProfile);

    void delete(UserProfile userProfile);

    UserProfile update(UserProfile userProfile);

}
