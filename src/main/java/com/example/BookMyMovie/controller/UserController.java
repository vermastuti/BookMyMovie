package com.example.BookMyMovie.controller;

import com.example.BookMyMovie.model.User;
import com.example.BookMyMovie.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody User user)
    {
       User addedUser= userService.add(user);
       return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<?> delete(@Valid @RequestBody User user)
    {
        userService.delete(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> update(@Valid @RequestBody User user)
    {
        User updatedUser=userService.update(user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }
}
