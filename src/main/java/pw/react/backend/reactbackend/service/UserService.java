package pw.react.backend.reactbackend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.react.backend.reactbackend.appException.ResourceNotFoundException;
import pw.react.backend.reactbackend.model.User;
import pw.react.backend.reactbackend.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean exists(User user){
        List<User> users = userRepository.findAllByLogin(user.getLogin());
        return users != null && !users.isEmpty();
    }

    public User updateUser(User user){
        Optional<User> existingUser = Optional.ofNullable(userRepository.findById(user.getId()));
        if(existingUser.isPresent()) {
            return userRepository.save(user);
        }
        throw new ResourceNotFoundException(String.format("User with id [%s] not found", user.getId()));
    }

}
