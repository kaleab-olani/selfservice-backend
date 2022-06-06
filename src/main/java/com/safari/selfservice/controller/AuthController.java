package com.safari.selfservice.controller;


import com.safari.selfservice.UserState;
import com.safari.selfservice.models.SystemUser;
import com.safari.selfservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<SystemUser> login(@RequestBody SystemUser user) {
        System.out.println(user);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            if (user.getPhoneNumber() == null) {
                new ResponseEntity<>(null, HttpStatus.PARTIAL_CONTENT);
            }
            SystemUser newUser = new SystemUser(user.getFullName(), user.getPhoneNumber(), user.getEmail(), 10, user.getPassword(), UserState.NEW.name());
            SystemUser authenticatedUser = userService.authenticate(user.getPhoneNumber(), user.getPassword());

            if (authenticatedUser != null) {
                return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
            }
        }
    }
    @GetMapping("/activate/{id}")
    public ResponseEntity<SystemUser> activate(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            if (! userService.canActivateUserWithId(id)) {
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                SystemUser user = userService.activate(id);
                if (user != null){
                    return new ResponseEntity<>(user, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
