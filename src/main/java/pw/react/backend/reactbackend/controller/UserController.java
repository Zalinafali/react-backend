package pw.react.backend.reactbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.react.backend.reactbackend.model.User;
import pw.react.backend.reactbackend.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user/")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        if(userService.exists(user)){
            throw new RuntimeException("User already exists!");
        }
        else{
            return ResponseEntity.ok(userService.save(user));
        }
    }



}
