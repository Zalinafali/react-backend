package pw.react.backend.reactbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pw.react.backend.reactbackend.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    List<User> findByLogin(String login);
    User findById(int id);
}
