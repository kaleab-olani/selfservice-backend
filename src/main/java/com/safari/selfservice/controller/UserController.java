package com.safari.selfservice.controller;

import com.safari.selfservice.models.SystemUser;
import com.safari.selfservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<SystemUser>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
    }
    @PostMapping()
    public ResponseEntity<SystemUser> register(@RequestBody SystemUser user) {
        System.out.println(user);
        try {
            SystemUser newUser = userService.createNewUser(user);
            if (newUser !=null){

            }
            System.out.println(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/deregister/{user_id}")
    public ResponseEntity<Boolean> deRegister(@PathVariable("user_id") Integer userID) {
        try {
            Boolean deRegistered = userService.deRegister(userID);
            return new ResponseEntity<>(deRegistered, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping()
    public ResponseEntity<SystemUser> updateUser(@RequestBody SystemUser user) {
        try {
            SystemUser updatedUser = userService.updateUser(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
