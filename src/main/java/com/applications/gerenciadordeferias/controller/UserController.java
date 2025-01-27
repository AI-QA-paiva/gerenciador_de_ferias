package com.applications.gerenciadordeferias.controller;

import com.applications.gerenciadordeferias.dto.UserRequestDto;
import com.applications.gerenciadordeferias.dto.UserResponseDto;
import com.applications.gerenciadordeferias.dto.UserUpdateDto;
import com.applications.gerenciadordeferias.model.User;
import com.applications.gerenciadordeferias.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        try {
            UserResponseDto userResponseDto = userService.registerUser(userRequestDto);
            return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/usersall")
    public ResponseEntity<List<User>> displayRegisteredUsers() {
        return ResponseEntity.ok(userService.displayRegisteredUsers());
    }

    @GetMapping(path = "/{email}")
    public ResponseEntity<User> displayUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.displayUserByEmail(email));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponseDto> updateRegisteredUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userService.changeRegisteredUser(id, userUpdateDto));
    }

    @DeleteMapping(path = "/inactive/{email}")
    public void userInactiveStatus(@PathVariable String email) {
        userService.updateStatusUser(email);
    }

}
