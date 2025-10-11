package com.example.BookMyMovie.service;

import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.model.User;
import com.example.BookMyMovie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User add(User user) {
        boolean isPresent=userRepository.existsById(user.getUserId());
        if(isPresent)
        {
            throw new DuplicateIdFoundException("Duplicate User found");
        }
        else {
            return userRepository.save(user);
        }
    }

    @Override
    public void delete(User user) {
        boolean isPresent=userRepository.existsById(user.getUserId());
        if(isPresent)
        {
           userRepository.delete(user);
        }
        else
        {
            throw new IdDoesNotExistException("User Id not found");
        }
    }

    @Override
    public User update(User user) {
        boolean isPresent=userRepository.existsById(user.getUserId());
        if(isPresent)
        {
            return userRepository.save(user);
        }
        else
        {
            throw new IdDoesNotExistException("User Id not found");
        }
    }
}
