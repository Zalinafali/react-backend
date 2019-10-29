package pw.react.backend.reactbackend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw.react.backend.reactbackend.model.User;
import pw.react.backend.reactbackend.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public List<User> findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public boolean exists(User user){
        List<User> users = userRepository.findByLogin(user.getLogin());
        return users != null && !users.isEmpty();
    }

}
