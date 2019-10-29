package pw.react.backend.reactbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.react.backend.reactbackend.model.User;
import pw.react.backend.reactbackend.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") int id){
        User res = userService.findById(id);
        if(res == null){
            throw new RuntimeException("User not found!");
        }
        else{
            return ResponseEntity.ok(res);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestBody User user) {
        if(userService.exists(user)) {
            return ResponseEntity.ok(user);
        }
        else{
            throw new RuntimeException("User not found!");
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") int id, @Valid @RequestBody User user){
        User userToUpdate = userService.findById(id);
        if(userToUpdate == null){
            throw new RuntimeException("User not found!");
        }
        else{
            userToUpdate.setUser(user.getLogin(),user.getFirstName(),user.getLastName(),user.getDateOfBirth(),user.getIsActive());
            return ResponseEntity.ok(userService.save(userToUpdate));
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable(value = "id") int id){
        User userToDelete = userService.findById(id);
        if(userToDelete == null){
            throw new RuntimeException("User not found!");
        }
        else{
            userService.delete(userToDelete);
            Map<String, Boolean> message = new HashMap<>();
            message.put("deleted", Boolean.TRUE);
            return ResponseEntity.ok(message);
        }

    }

}
