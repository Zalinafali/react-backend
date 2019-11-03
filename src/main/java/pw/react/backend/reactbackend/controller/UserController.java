package pw.react.backend.reactbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pw.react.backend.reactbackend.model.User;
import pw.react.backend.reactbackend.repository.UserRepository;
import pw.react.backend.reactbackend.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository){
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping(path = "")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        if(userService.exists(user)){
            throw new RuntimeException("User already exists!");
        }
        else{
            return ResponseEntity.ok().body(userRepository.save(user));
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int id){
        User res = userRepository.findById(id);
        if(res == null){
            throw new RuntimeException("User not found!");
        }
        else{
            return ResponseEntity.ok().body(res);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<List<User>> getUserByLogin(@PathVariable(value = "login") String login){
        return ResponseEntity.ok().body(userRepository.findAllByLogin(login));
    }

    @PutMapping("")
    public ResponseEntity<User> updateWholeUser(@Valid @RequestBody User user){
        return ResponseEntity.ok().body(userRepository.save(user));
    }

    @PatchMapping(path = "")
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user){
        return ResponseEntity.ok().body(userService.updateUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUserById(@PathVariable(value = "id") int id){
        User userToDelete = userRepository.findById(id);
        if(userToDelete == null){
            throw new RuntimeException("User not found!");
        }
        else{
            userRepository.delete(userToDelete);
            Map<String, Boolean> message = new HashMap<>();
            message.put("deleted", Boolean.TRUE);
            return ResponseEntity.ok(message);
        }

    }

}
